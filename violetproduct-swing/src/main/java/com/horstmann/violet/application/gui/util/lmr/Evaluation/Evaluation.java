package com.horstmann.violet.application.gui.util.lmr.Evaluation;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.opreationTreePane.ModelExistValidationPanel;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.PathTuple;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.UppaalLocation;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.UppaalTransition;

public class Evaluation {

	private MainFrame mainFrame;
	
	private String uppaalName;
	private int uppaalType;
	private List<UppaalLocation> uppaalLocations=new ArrayList<UppaalLocation>();
	private List<UppaalTransition> uppaalTransitions=new ArrayList<UppaalTransition>();
	
	private HashMap<String, UppaalLocation> uppaalLocationByIdMap = new HashMap<String, UppaalLocation>();
	private List<List<PathTuple>> uppaalPaths=new ArrayList<List<PathTuple>>();
	
	private static boolean FindUppaalPathTupleEndState=false;
	
	public Evaluation(String uppaalName, int uppaalType){
		this.uppaalName=uppaalName;
		this.uppaalType=uppaalType;
		mainFrame=ModelExistValidationPanel.getMainFrame();
	}
	
	public void Ready(){
		try {
			LoadUppaalXmlData();
			LinkUppaalLocationToUppaalTransition();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void LoadUppaalXmlData() throws DocumentException{
		
		String baseUrl = "D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\";
		File uppaalfile;
		
		if(uppaalType==1){
			uppaalfile=new File(baseUrl+"SequenceToUppal\\"+uppaalName+"ForXStream.xml");
			LoadSequenceUppaalXmlData(uppaalfile);
		}
		else if(uppaalType==2){
			uppaalfile=new File(baseUrl+"TimingToUppal\\"+uppaalName+"ForXStream.xml");
		}
		
	}

	private void LoadSequenceUppaalXmlData(File uppaalfile) throws DocumentException {
		
		SAXReader reader = new SAXReader();
		Document dom = reader.read(uppaalfile);
		Element root = dom.getRootElement();
		
		Element template=root.element("templateList").element("template");
		List<Element> locationElements=template.element("locationList").elements("location");
		List<Element> transitionElements=template.element("transitionList").elements("transition");
		
		uppaalLocations=new ArrayList<UppaalLocation>();
		uppaalTransitions=new ArrayList<UppaalTransition>();
		
		for(Element locationElement:locationElements){
			UppaalLocation uppaalLocation=new UppaalLocation();
			uppaalLocation.setId(locationElement.element("id").getText());
			uppaalLocation.setName(locationElement.element("name").getText());
			uppaalLocation.setTimeDuration(locationElement.element("timeDuration").getText());
			uppaalLocation.setInit(Boolean.valueOf(locationElement.element("init").getText()));
			uppaalLocation.setFinl(Boolean.valueOf(locationElement.element("final").getText()));
			uppaalLocations.add(uppaalLocation);
			mainFrame.getModelExistValidationPanel().TextAreaPrint(uppaalLocation.toString());
		}
		
		for(Element transitionElement:transitionElements){
			UppaalTransition uppaalTransition=new UppaalTransition();
			uppaalTransition.setId(transitionElement.element("id").getText());
			uppaalTransition.setName(transitionElement.element("name").getText());
			uppaalTransition.setSource(transitionElement.element("source").getText());
			uppaalTransition.setTarget(transitionElement.element("target").getText());
			uppaalTransition.setTimeDuration(transitionElement.element("timeDuration").getText());
			uppaalTransitions.add(uppaalTransition);
			mainFrame.getModelExistValidationPanel().TextAreaPrint(uppaalTransition.toString());
		}
		
	}
	
	public void LinkUppaalLocationToUppaalTransition(){
		
		for(UppaalLocation uppaalLocation:uppaalLocations){
			uppaalLocationByIdMap.put(uppaalLocation.getId(), uppaalLocation);
		}
		
		for(UppaalTransition uppaalTransition:uppaalTransitions){
			UppaalLocation uppaalLocation=uppaalLocationByIdMap.get(uppaalTransition.getSource());
			uppaalLocation.getUppaalTransitions().add(uppaalTransition);
		}
		
		for(UppaalLocation uppaalLocation:uppaalLocations){
			if(uppaalLocation.getUppaalTransitions().size()>0){
				uppaalLocation.setFinl(false);
			}
		}
		
	}
	
	public List<UppaalTransition> FindUppaalTransitionByMessage(String message){
		
		List<UppaalTransition> findTransitions=new ArrayList<UppaalTransition>();
		
		for(UppaalTransition uppaalTransition:uppaalTransitions){
			if(message.equals(uppaalTransition.getName())){
				findTransitions.add(uppaalTransition);
				mainFrame.getModelExistValidationPanel().TextAreaPrint("匹配到一条消息：");
				mainFrame.getModelExistValidationPanel().TextAreaPrint(uppaalTransition.toString());
			}
		}
		
		return findTransitions;
		
	}
	
//	public List<EvaluationPathTuple> FindUppaalPathTupleByMessages(String messageA, String messageB){
//		
//		List<EvaluationPathTuple> uppaalPathTuples=new ArrayList<>();
//		boolean startflag=false;
//		boolean endflag=false;
//		
//		for(List<EvaluationPathTuple> uppaalPath:uppaalPaths){
//			uppaalPathTuples=new ArrayList<>();
//			startflag=false;
//			endflag=false;
//			for(EvaluationPathTuple uppaalTuple:uppaalPath){
//				if(uppaalTuple.getUppaalTransition()==null){
//					break;
//				}
//				if(startflag){
//					uppaalPathTuples.add(uppaalTuple);
//					if(endflag){
//						
//					}
//					else{
//						if(uppaalTuple.getUppaalTransition().getName().equals(messageB)){
//							endflag=true;
//							return uppaalPathTuples;
//						}
//					}
//				}
//				else{
//					if(uppaalTuple.getUppaalTransition().getName().equals(messageA)){
//						startflag=true;
//						uppaalPathTuples.add(uppaalTuple);
//					}
//				}
//			}
//		}
//		
//		return null;
//		
//	}
	
	public List<PathTuple> FindUppaalPathTupleByMessages(String messageA, String messageB){
		
		InitLocationVisit();
		
		List<PathTuple> uppaalPathTuples=new ArrayList<PathTuple>();
		
		List<UppaalTransition> uppaalTransitionsA=FindUppaalTransitionByMessage(messageA);
		List<UppaalTransition> uppaalTransitionsB=FindUppaalTransitionByMessage(messageB);
		System.out.println(uppaalTransitionsA.size()+" - "+uppaalTransitionsB.size());
		if(uppaalTransitionsA.size()>0&&uppaalTransitionsB.size()>0){
			for(UppaalTransition uppaalTransition:uppaalTransitionsA){
				FindUppaalPathTupleEndState=false;
				UppaalLocation uppaalLocationSource=uppaalLocationByIdMap.get(uppaalTransition.getSource());
				PathTuple uppaalTuple=new PathTuple(uppaalLocationSource, uppaalTransition);
				uppaalLocationSource.visit++;
				uppaalPathTuples.add(uppaalTuple);
				DFSFindUppaalPathTuple(uppaalLocationByIdMap.get(uppaalTransition.getTarget()),uppaalPathTuples,messageB);
				if(FindUppaalPathTupleEndState){
					break;
				}
				else{
					uppaalPathTuples.remove(uppaalTuple);
				}
			}
		}
		
		return uppaalPathTuples;
		
	}

	private void InitLocationVisit() {
		
		for(UppaalLocation location:uppaalLocations){
			location.visit=0;
		}
		
	}

	private void DFSFindUppaalPathTuple(UppaalLocation uppaalLocation, List<PathTuple> uppaalPathTuples, String message) {
		if(FindUppaalPathTupleEndState){
			return ;
		}
		if(uppaalLocation.visit>=1){
			uppaalPathTuples.remove(uppaalPathTuples.size()-1);
			return ;
		}
		if(uppaalLocation.isFinl()){
			return ;
		}
		uppaalLocation.visit++;
		for(UppaalTransition uppaalTransition:uppaalLocation.getUppaalTransitions()){
			if(uppaalTransition.getName().equals(message)){
				FindUppaalPathTupleEndState=true;
				PathTuple uppaalTuple=new PathTuple(uppaalLocation, uppaalTransition);
				uppaalPathTuples.add(uppaalTuple);
				return ;
			}
			else{
				PathTuple uppaalTuple=new PathTuple(uppaalLocation, uppaalTransition);
				uppaalPathTuples.add(uppaalTuple);
				DFSFindUppaalPathTuple(uppaalLocationByIdMap.get(uppaalTransition.getTarget()),uppaalPathTuples,message);
				if(FindUppaalPathTupleEndState){
					return ;
				}
				else{
					uppaalPathTuples.remove(uppaalTuple);
				}
			}
		}
		
	}

	public boolean CheckTimeByInput(String input) {
		
		if(!CheckTimeDuration()){
			return false;
		}
		
		FindAllUppaalPath();
		
		System.out.println(uppaalPaths.size());
		mainFrame.getModelExistValidationPanel().TextAreaPrint("共找到"+uppaalPaths.size()+"条路径，开始计算最大最小时间");
		
		int minTime=0;
		int maxTime=0;
		
//		System.out.println();
//		for(List<PathTuple> uppaalTuples:uppaalPaths){
//			for(PathTuple tuple:uppaalTuples){
//				System.out.print(tuple.getLocation().getName()+" : "+tuple.getLocation().getTimeDuration()+" - ");
//			}
//			System.out.println();
//		}
		
		for(List<PathTuple> uppaalTuples:uppaalPaths){
			int min=0;
			int max=0;
			for(PathTuple tuple:uppaalTuples){
				String timeD=tuple.getLocation().getTimeDuration();
				if(timeD==null||timeD.equals("null")){
				}
				else{
					if(timeD.contains("=")) {
						max += timeD.contains("<=") ? Integer.valueOf(timeD.split("<=")[1]) : 0;
						min += timeD.contains(">=") ? Integer.valueOf(timeD.split(">=")[1]) : 0;
					} else {
						max += timeD.contains("<") ? Integer.valueOf(timeD.split("<")[1]) : 0;
						min += timeD.contains(">") ? Integer.valueOf(timeD.split(">")[1]) : 0;
					}
				}
			}
			if(minTime==0){
				minTime=min;
			}
			else{
				if(min==0){
				}
				else{
					minTime=Math.min(minTime, min);
				}
			}
			if(maxTime==0){
				maxTime=max;
			}
			else{
				if(max==0){
				}
				else{
					maxTime=Math.max(maxTime, max);
				}
			}
		}
		
		System.out.println(minTime+" - - "+maxTime);
		mainFrame.getModelExistValidationPanel().TextAreaPrint("最小时间： "+minTime);
		mainFrame.getModelExistValidationPanel().TextAreaPrint("最大时间： "+maxTime);
		
		if(input.contains("<")){
			if(input.contains("<=")){
				int inputTime=Integer.valueOf(input.split("<=")[1]);
				if(minTime<=inputTime){
					return true;
				}
				else{
					return false;
				}
			}
			else{
				int inputTime=Integer.valueOf(input.split("<")[1]);
				if(minTime<inputTime){
					return true;
				}
				else{
					return false;
				}
			}
		}
		else if(input.contains(">")){
			if(maxTime==0){
				return true;
			}
			if(input.contains(">=")){
				int inputTime=Integer.valueOf(input.split(">=")[1]);
				if(maxTime>=inputTime){
					return true;
				}
				else{
					return false;
				}
			}
			else{
				int inputTime=Integer.valueOf(input.split(">")[1]);
				if(maxTime>inputTime){
					return true;
				}
				else{
					return false;
				}
			}
		}
		
		return false;

	}
	
	public UppaalLocation FindStartUppaalLocation(){
		
		for(UppaalLocation uppaalLocation:uppaalLocations){
			if(uppaalLocation.isInit()){
				return uppaalLocation;
			}
		}
		return null;
		
	}
	
	public void FindAllUppaalPath(){
		
		InitLocationVisit();
		
		UppaalLocation uppaalLocation=FindStartUppaalLocation();
		
		List<PathTuple> uppaalTuples=new ArrayList<PathTuple>();
		
		uppaalLocation.visit++;
		for(UppaalTransition uppaalTransition:uppaalLocation.getUppaalTransitions()){
			PathTuple tuple=new PathTuple(uppaalLocation, uppaalTransition);
			uppaalTuples.add(tuple);
			UppaalLocation nextUppaalLocation=uppaalLocationByIdMap.get(uppaalTransition.getTarget());
			nextUppaalLocation.visit++;
			DFSUppaalPath(nextUppaalLocation, uppaalTuples);
			nextUppaalLocation.visit--;
			uppaalTuples.remove(tuple);
		}
		uppaalLocation.visit--;
		
	}
	
	public void DFSUppaalPath(UppaalLocation uppaalLocation, List<PathTuple> uppaalTuples){

		if(uppaalLocation.isFinl()||uppaalLocation.visit>1){
			PathTuple tuple=new PathTuple(uppaalLocation, null);
			uppaalTuples.add(tuple);
			uppaalPaths.add(new ArrayList<PathTuple>(uppaalTuples));
			uppaalTuples.remove(tuple);
		}
		else{
			for(UppaalTransition uppaalTransition:uppaalLocation.getUppaalTransitions()){
				PathTuple tuple=new PathTuple(uppaalLocation, uppaalTransition);
				uppaalTuples.add(tuple);
				UppaalLocation nextUppaalLocation=uppaalLocationByIdMap.get(uppaalTransition.getTarget());
				nextUppaalLocation.visit++;
				DFSUppaalPath(nextUppaalLocation, uppaalTuples);
				nextUppaalLocation.visit--;
				uppaalTuples.remove(tuple);
			}
		}
		
	}

//	public void FindAllUppaalPath(){
//		
//		UppaalLocation uppaalLocation=FindStartUppaalLocation();
//		
//		DFSUppaalPath(uppaalLocation);
//		
//	}
//	
//	public void DFSUppaalPath(UppaalLocation uppaalLocation){
//
//		if(uppaalLocation.visit>1){
//			return ;
//		}
//		
//		if(uppaalLocation.isFinl()){
//			PathTuple tuple = new PathTuple(uppaalLocation, null);
//			List<PathTuple> list=new ArrayList<>();
//			list.add(tuple);
//			uppaalLocation.getUppaalPathTuples().add(list);
//			uppaalLocation.visit++;
//			return ;
//		}
//		
//		uppaalLocation.visit++;
//		for (UppaalTransition uppaalTransition : uppaalLocation.getUppaalTransitions()) {
//			UppaalLocation nextUppaalLocation = uppaalLocationByIdMap.get(uppaalTransition.getTarget());
//			DFSUppaalPath(nextUppaalLocation);
//			PathTuple tuple = new PathTuple(uppaalLocation, uppaalTransition);
//			if(nextUppaalLocation.getUppaalPathTuples().size()==0){
//				List<PathTuple> uppaalTuples=new ArrayList<>();
//				uppaalTuples.add(tuple);
//				uppaalLocation.getUppaalPathTuples().add(uppaalTuples);
//			}
//			else{
////				for(List<PathTuple> nextPathTuples:nextUppaalLocation.getUppaalPathTuples()){
////					List<PathTuple> uppaalTuples=new ArrayList<>(nextPathTuples);
////					uppaalTuples.add(tuple);
////					uppaalLocation.getUppaalPathTuples().add(uppaalTuples);
////				}
//				for(int i=0;i<nextUppaalLocation.getUppaalPathTuples().size();i++){
//					List<PathTuple> uppaalTuples=new ArrayList<>(nextUppaalLocation.getUppaalPathTuples().get(i));
//					uppaalTuples.add(tuple);
//					uppaalLocation.getUppaalPathTuples().add(uppaalTuples);
//				}
//			}
//		}
//
//	}
	
	public boolean CheckTimeDuration(){
		
		boolean result=false;

		for(UppaalLocation location:uppaalLocations){
			if(location.getTimeDuration()!=null&&!location.getTimeDuration().equals("null")){
				result=true;
				break;
			}
		}
		
		return result;
		
	}

	public String getUppaalName() {
		return uppaalName;
	}

	public int getUppaalType() {
		return uppaalType;
	}

	public List<UppaalLocation> getUppaalLocations() {
		return uppaalLocations;
	}

	public List<UppaalTransition> getUppaalTransitions() {
		return uppaalTransitions;
	}

	public HashMap<String, UppaalLocation> getUppaalLocationByIdMap() {
		return uppaalLocationByIdMap;
	}

	public List<List<PathTuple>> getUppaalPaths() {
		return uppaalPaths;
	}
	
	
}

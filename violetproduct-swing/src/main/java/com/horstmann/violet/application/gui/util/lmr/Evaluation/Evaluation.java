package com.horstmann.violet.application.gui.util.lmr.Evaluation;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Evaluation {

	private String uppaalName;
	private int uppaalType;
	private List<EvaluationUppaalLocation> uppaalLocations=new ArrayList<>();
	private List<EvaluationUppaalTransition> uppaalTransitions=new ArrayList<>();
	
	private HashMap<String, EvaluationUppaalLocation> uppaalLocationByIdMap = new HashMap<>();
	private List<List<EvaluationUppaalTuple>> uppaalPaths=new ArrayList<>();
	
	private static boolean FindUppaalPathTupleEndState=false;
	
	public Evaluation(String uppaalName, int uppaalType){
		this.uppaalName=uppaalName;
		this.uppaalType=uppaalType;
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
		
		uppaalLocations=new ArrayList<>();
		uppaalTransitions=new ArrayList<>();
		
		for(Element locationElement:locationElements){
			EvaluationUppaalLocation uppaalLocation=new EvaluationUppaalLocation();
			uppaalLocation.setId(locationElement.element("id").getText());
			uppaalLocation.setName(locationElement.element("name").getText());
			uppaalLocation.setTimeDuration(locationElement.element("timeDuration").getText());
			uppaalLocation.setInit(Boolean.valueOf(locationElement.element("init").getText()));
			uppaalLocation.setFinl(Boolean.valueOf(locationElement.element("final").getText()));
			uppaalLocations.add(uppaalLocation);
		}
		
		for(Element transitionElement:transitionElements){
			EvaluationUppaalTransition uppaalTransition=new EvaluationUppaalTransition();
			uppaalTransition.setId(transitionElement.element("id").getText());
			uppaalTransition.setName(transitionElement.element("name").getText());
			uppaalTransition.setSource(transitionElement.element("source").getText());
			uppaalTransition.setTarget(transitionElement.element("target").getText());
			uppaalTransition.setTimeDuration(transitionElement.element("timeDuration").getText());
			uppaalTransitions.add(uppaalTransition);
		}
		
	}
	
	public void LinkUppaalLocationToUppaalTransition(){
		
		for(EvaluationUppaalLocation uppaalLocation:uppaalLocations){
			uppaalLocationByIdMap.put(uppaalLocation.getId(), uppaalLocation);
		}
		
		for(EvaluationUppaalTransition uppaalTransition:uppaalTransitions){
			EvaluationUppaalLocation uppaalLocation=uppaalLocationByIdMap.get(uppaalTransition.getSource());
			uppaalLocation.getUppaalTransitions().add(uppaalTransition);
		}
		
		for(EvaluationUppaalLocation uppaalLocation:uppaalLocations){
			if(uppaalLocation.getUppaalTransitions().size()>0){
				uppaalLocation.setFinl(false);
			}
		}
		
	}
	
	public List<EvaluationUppaalTransition> FindUppaalTransitionByMessage(String message){
		
		List<EvaluationUppaalTransition> findTransitions=new ArrayList<>();
		
		for(EvaluationUppaalTransition uppaalTransition:uppaalTransitions){
			if(message.equals(uppaalTransition.getName())){
				findTransitions.add(uppaalTransition);
			}
		}
		
		return findTransitions;
		
	}
	
//	public List<EvaluationUppaalTuple> FindUppaalPathTupleByMessages(String messageA, String messageB){
//		
//		List<EvaluationUppaalTuple> uppaalPathTuples=new ArrayList<>();
//		boolean startflag=false;
//		boolean endflag=false;
//		
//		for(List<EvaluationUppaalTuple> uppaalPath:uppaalPaths){
//			uppaalPathTuples=new ArrayList<>();
//			startflag=false;
//			endflag=false;
//			for(EvaluationUppaalTuple uppaalTuple:uppaalPath){
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
	
	public List<EvaluationUppaalTuple> FindUppaalPathTupleByMessages(String messageA, String messageB){
		
		List<EvaluationUppaalTuple> uppaalPathTuples=new ArrayList<>();
		
		List<EvaluationUppaalTransition> uppaalTransitionsA=FindUppaalTransitionByMessage(messageA);
		List<EvaluationUppaalTransition> uppaalTransitionsB=FindUppaalTransitionByMessage(messageB);
		System.out.println(uppaalTransitionsA.size()+" - "+uppaalTransitionsB.size());
		if(uppaalTransitionsA.size()>0&&uppaalTransitionsB.size()>0){
			for(EvaluationUppaalTransition uppaalTransition:uppaalTransitionsA){
				FindUppaalPathTupleEndState=false;
				EvaluationUppaalLocation uppaalLocationSource=uppaalLocationByIdMap.get(uppaalTransition.getSource());
				EvaluationUppaalTuple uppaalTuple=new EvaluationUppaalTuple(uppaalLocationSource, uppaalTransition);
				uppaalLocationSource.setVisit(true);
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

	private void DFSFindUppaalPathTuple(EvaluationUppaalLocation uppaalLocation, List<EvaluationUppaalTuple> uppaalPathTuples, String message) {
		if(FindUppaalPathTupleEndState){
			return ;
		}
		if(uppaalLocation.isVisit()||uppaalLocation.isFinl()){
			return ;
		}
		uppaalLocation.setVisit(true);
		for(EvaluationUppaalTransition uppaalTransition:uppaalLocation.getUppaalTransitions()){
			if(uppaalTransition.getName().equals(message)){
				FindUppaalPathTupleEndState=true;
				EvaluationUppaalTuple uppaalTuple=new EvaluationUppaalTuple(uppaalLocation, uppaalTransition);
				uppaalPathTuples.add(uppaalTuple);
				return ;
			}
			else{
				EvaluationUppaalTuple uppaalTuple=new EvaluationUppaalTuple(uppaalLocation, uppaalTransition);
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
		
		int minTime=0;
		int maxTime=0;
		
		for(List<EvaluationUppaalTuple> uppaalTuples:uppaalPaths){
			int min=0;
			int max=0;
			for(EvaluationUppaalTuple tuple:uppaalTuples){
				String timeD=tuple.getUppaalLocation().getTimeDuration();
				if(timeD==null||timeD.equals("null")){
				}
				else{
					if(timeD.contains("=")) {
						min += timeD.contains("<=") ? Integer.valueOf(timeD.split("<=")[1]) : 0;
						max += timeD.contains(">=") ? Integer.valueOf(timeD.split(">=")[1]) : 0;
					} else {
						min += timeD.contains("<") ? Integer.valueOf(timeD.split("<")[1]) : 0;
						max += timeD.contains(">") ? Integer.valueOf(timeD.split(">")[1]) : 0;
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
		
		if(input.contains("<")){
			if(minTime==0){
				return true;
			}
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
	
	public EvaluationUppaalLocation FindStartUppaalLocation(){
		
		for(EvaluationUppaalLocation uppaalLocation:uppaalLocations){
			if(uppaalLocation.isInit()){
				return uppaalLocation;
			}
		}
		return null;
		
	}
	
//	public void FindAllUppaalPath(){
//		
//		EvaluationUppaalLocation uppaalLocation=FindStartUppaalLocation();
//		
//		List<EvaluationUppaalTuple> uppaalTuples=new ArrayList<>();
//		
//		uppaalLocation.setVisit(true);
//		for(EvaluationUppaalTransition uppaalTransition:uppaalLocation.getUppaalTransitions()){
//			EvaluationUppaalTuple tuple=new EvaluationUppaalTuple(uppaalLocation, uppaalTransition);
//			uppaalTuples.add(tuple);
//			EvaluationUppaalLocation nextUppaalLocation=uppaalLocationByIdMap.get(uppaalTransition.getTarget());
//			if(nextUppaalLocation.isVisit()){
//				DFSUppaalPath(nextUppaalLocation, uppaalTuples, true);
//			}
//			else{
//				nextUppaalLocation.setVisit(true);
//				DFSUppaalPath(nextUppaalLocation, uppaalTuples, false);
//			}
////			uppaalLocation.setVisit(false);
//			uppaalTuples.remove(tuple);
//		}
//		
//	}
//	
//	public void DFSUppaalPath(EvaluationUppaalLocation uppaalLocation, List<EvaluationUppaalTuple> uppaalTuples, boolean visit){
//
//		if(uppaalLocation.isFinl()||visit){
//			EvaluationUppaalTuple tuple=new EvaluationUppaalTuple(uppaalLocation, null);
//			uppaalTuples.add(tuple);
//			uppaalPaths.add(new ArrayList<>(uppaalTuples));
//			uppaalTuples.remove(tuple);
//		}
//		else{
//			for(EvaluationUppaalTransition uppaalTransition:uppaalLocation.getUppaalTransitions()){
//				EvaluationUppaalTuple tuple=new EvaluationUppaalTuple(uppaalLocation, uppaalTransition);
//				uppaalTuples.add(tuple);
//				EvaluationUppaalLocation nextUppaalLocation=uppaalLocationByIdMap.get(uppaalTransition.getTarget());
//				if(nextUppaalLocation.isVisit()){
//					DFSUppaalPath(nextUppaalLocation, uppaalTuples, true);
//				}
//				else{
//					nextUppaalLocation.setVisit(true);
//					DFSUppaalPath(nextUppaalLocation, uppaalTuples, false);
//				}
////					uppaalLocation.setVisit(false);
//				uppaalTuples.remove(tuple);
//			}
//		}
//		
//	}
	private int num=0;
	public void FindAllUppaalPath(){
		
		EvaluationUppaalLocation uppaalLocation=FindStartUppaalLocation();
		
		DFSUppaalPath(uppaalLocation);
		System.out.println(num+" +-+ ");
	}
	
	public void DFSUppaalPath(EvaluationUppaalLocation uppaalLocation){
		num++;
		if(uppaalLocation.isVisit()){
			return ;
		}
		
		if(uppaalLocation.isFinl()){
			EvaluationUppaalTuple tuple = new EvaluationUppaalTuple(uppaalLocation, null);
			List<EvaluationUppaalTuple> list=new ArrayList<>();
			list.add(tuple);
			uppaalLocation.getUppaalPathTuples().add(list);
			uppaalLocation.setVisit(true);
			return ;
		}
		
		uppaalLocation.setVisit(true);
		for (EvaluationUppaalTransition uppaalTransition : uppaalLocation.getUppaalTransitions()) {
			EvaluationUppaalLocation nextUppaalLocation = uppaalLocationByIdMap.get(uppaalTransition.getTarget());
			DFSUppaalPath(nextUppaalLocation);
			EvaluationUppaalTuple tuple = new EvaluationUppaalTuple(uppaalLocation, uppaalTransition);
			if(nextUppaalLocation.getUppaalPathTuples().size()==0){
				List<EvaluationUppaalTuple> uppaalTuples=new ArrayList<>();
				uppaalTuples.add(tuple);
				uppaalLocation.getUppaalPathTuples().add(uppaalTuples);
			}
			else{
//				for(List<EvaluationUppaalTuple> nextUppaalTuples:nextUppaalLocation.getUppaalPathTuples()){
//					List<EvaluationUppaalTuple> uppaalTuples=new ArrayList<>(nextUppaalTuples);
//					uppaalTuples.add(tuple);
//					uppaalLocation.getUppaalPathTuples().add(uppaalTuples);
//				}
				for(int i=0;i<nextUppaalLocation.getUppaalPathTuples().size();i++){
					List<EvaluationUppaalTuple> uppaalTuples=new ArrayList<>(nextUppaalLocation.getUppaalPathTuples().get(i));
					uppaalTuples.add(tuple);
					uppaalLocation.getUppaalPathTuples().add(uppaalTuples);
				}
			}
		}

	}
	

	public String getUppaalName() {
		return uppaalName;
	}

	public int getUppaalType() {
		return uppaalType;
	}

	public List<EvaluationUppaalLocation> getUppaalLocations() {
		return uppaalLocations;
	}

	public List<EvaluationUppaalTransition> getUppaalTransitions() {
		return uppaalTransitions;
	}

	public HashMap<String, EvaluationUppaalLocation> getUppaalLocationByIdMap() {
		return uppaalLocationByIdMap;
	}

	public List<List<EvaluationUppaalTuple>> getUppaalPaths() {
		return uppaalPaths;
	}
	
	
}

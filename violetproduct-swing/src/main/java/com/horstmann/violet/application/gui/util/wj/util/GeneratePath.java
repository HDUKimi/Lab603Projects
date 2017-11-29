package com.horstmann.violet.application.gui.util.wj.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.horstmann.violet.application.gui.util.ckt.handle.Automatic;
import com.horstmann.violet.application.gui.util.ckt.handle.State;
import com.horstmann.violet.application.gui.util.ckt.handle.Transition;
import com.horstmann.violet.application.gui.util.ckt.output.ShowInfor;

public class GeneratePath {
	private static Automatic mAutomatic;
	private static HashMap<String, State> findStateByID;
	private static Map<Automatic, Double> findPointByPath= new HashMap<Automatic, Double>();//////通过路径找到该路径的重要度
	private static double[] statePoints;
	private static ArrayList<Automatic> beforeSort;
	
	
	public static ArrayList<Automatic> getBeforeSort() {
		return beforeSort;
	}
	public static void setBeforeSort(ArrayList<Automatic> beforeSort) {
		GeneratePath.beforeSort = beforeSort;
	}
	
	public static Map<Automatic, Double> getFindPointByPath() {
		return findPointByPath;
	}
	public static void setFindPointByPath(Map<Automatic, Double> findPointByPath) {
		GeneratePath.findPointByPath = findPointByPath;
	}



	// 路径（自动机的形式） + 路径的重要度
	static class PathAndImpPoint implements Comparable<PathAndImpPoint>{
		Automatic automatic;
		double impPoint;
		public PathAndImpPoint(ArrayList<Transition> transitions) {
			Automatic auto = new Automatic();
			ArrayList<State> states = new ArrayList<State>();
			HashSet<State> stateSet = new HashSet<State>();
			for(Transition tran : transitions) {
				// 去掉标记符
				tran.setName(tran.getName().replaceAll("@@", ""));
				tran.setName(tran.getName().replaceAll("##", ""));
				stateSet.add(findStateByID.get(tran.getSource()));
				stateSet.add(findStateByID.get(tran.getTarget()));
			}
			states.addAll(stateSet);
			auto.setStateSet(states);
			auto.setTransitionSet(transitions);
			automatic = auto;
			// 累加路径上状态的重要度 作为路径的重要度
			double count = 0;
			for(State s : states) {
				count += statePoints[s.getId()];
			}
			impPoint = count;
			
		}
		@Override
		public int compareTo(PathAndImpPoint object) {
			if (this.impPoint < object.impPoint) return -1;
			if (this.impPoint > object.impPoint) return 1;
			return 0;
		}
	}
	// 获取路径
	public static ArrayList<Automatic> getFormatPathFromAutomatic(Automatic automatic, int wantedSize, int m) {
		
		List<State> ostates=new ArrayList<>();
		List<Transition> otransitions=new ArrayList<>();
		ostates=automatic.getStateSet();
		for(Transition transition:automatic.getTransitionSet()){
			Transition tran=transition.clone();
			otransitions.add(tran);
		}
		
		findPointByPath=new HashMap<Automatic, Double>();
		
		mAutomatic = automatic;
		findStateByID = DataHelper.getStateIdHashMap(automatic.getStateSet());
		statePoints = DataHelper.getStatesImportantPoint(automatic);
		/*
		 *  可以设置开始到终止路径库 每一个终止有xxxxxxxxxxx条
		 *  1条时结果为204  15000条时结果为60018（全迁移覆盖除了回路）
		 */
		ArrayList<ArrayList<Transition>> paths = 
				TranCoverUtil.getTranCoverTestCaseFromAutomatic(automatic, wantedSize, statePoints);
		
		ShowInfor.print(3, "共有" + paths.size() + "条路径");
		
		int i=1;
		ArrayList<Automatic> res = new ArrayList<Automatic>();
		for(ArrayList<Transition> tranList : paths) {
			Automatic auto=GeneratePath.fromTranListToAuto(tranList);
			auto.setName("测试用例"+(i++));
			res.add(auto);
		}
		
		CheckPathIsCorrect(ostates,otransitions,res);
		
		GeneratePath.setBeforeSort(res);
		//beforeSort = res;
		
//		System.out.println("------------------------");
//		for(Automatic auto:res){
//			System.out.println(auto.getName()+" - - "+auto.getStateSet().size()+" - - "+auto.getTransitionSet().size()+" - "+(auto.getStateSet().size()-auto.getTransitionSet().size()==1?"True":"False"));
//		}
//		System.out.println("------------------------");
		
		if(m==0){
			return res;
		}else{
			Map<Automatic, Double> map = new HashMap<Automatic, Double>();
			map = findPointByPath;		
			List<Map.Entry<Automatic, Double>> list=new ArrayList<Entry<Automatic, Double>>();
	        list.addAll(map.entrySet());  
	        ValueComparator vc=new ValueComparator();  
	        Collections.sort(list,vc); 
	        ArrayList<Automatic> afterSort = new ArrayList<Automatic>();
	        for(Iterator<Entry<Automatic, Double>> it=list.iterator();it.hasNext();)  
	        {  
	            //System.out.println(it.next()); 
	        	afterSort.add(it.next().getKey());
	        } 
//	        for(Automatic a: afterSort){
//	        	System.out.println(a.getName() + "------重要度为：" + findPointByPath.get(a));
//	        }
			return afterSort;
		}	
	}
	 
	private static void CheckPathIsCorrect(List<State> ostates, List<Transition> otransitions, ArrayList<Automatic> res) {
		 
		 String target="";
		 String source="";
		 String oldtarget="";
		 
		 for(Automatic auto:res){
			 List<State> states=auto.getStateSet();
			 List<Transition> transitions=auto.getTransitionSet();
			 if(states.size()-transitions.size()>1){
//				 System.out.println(auto.getName()+" - - "+auto.getStateSet().size()+" - - "+auto.getTransitionSet().size()+" - "+(auto.getStateSet().size()-auto.getTransitionSet().size()==1?"True":"False"));
				 target=transitions.get(0).getTarget();
				 for(int i=1;i<transitions.size();i++){
					 source=transitions.get(i).getSource();
					 oldtarget=transitions.get(i).getTarget();
					 if(!target.equals(source)){
						 Transition tran=null;
						 for(Transition otransition:otransitions){
							 if(otransition.getSource().equals(target)&&otransition.getTarget().equals(source)){
								 tran=otransition;
								 break;
							 }
						 }
						 if(tran!=null){
//							 System.out.println(tran.getName());
							 transitions.add(i, tran.clone());
							 i++;
						 }
						 else{
//							 System.out.println("target "+target+" - - source "+source);
							 for(Transition otransition:otransitions){
								 if(otransition.getSource().equals(target)){
									 tran=otransition;
									 break;
								 }
							 }
							if (tran != null) {
//								System.out.println(tran.getName());
								transitions.add(i, tran.clone());
								i++;

//								System.out.println(tran.getName() + " - " + tran.getSource() + " - " + tran.getTarget());
								State state = FindStateById(ostates, tran.getTarget());
								if(state!=null){
									states.add(state);
//									System.out.println(state.getName());
									for (Transition otransition : otransitions) {
										if (otransition.getSource().equals(tran.getTarget())) {
											tran = otransition;
											break;
										}
									}
									if (tran != null) {
//										System.out.println(tran.getName());
										transitions.add(i, tran.clone());
										i++;
									}
//									System.out.println(tran.getName() + " - " + tran.getSource() + " - " + tran.getTarget());
								}
//								System.out.println(":::::::::");
							}
						 }
					 }
					 target=oldtarget;
				 }
//				 System.out.println(auto.getName()+" - - "+auto.getStateSet().size()+" - - "+auto.getTransitionSet().size()+" - "+(auto.getStateSet().size()-auto.getTransitionSet().size()==1?"True":"False"));
//				 System.out.println("-*+-++++++++++++++++");
			 }
		 }
		 
//		 for(Automatic am:res){
//			 System.out.println(am.getName()+" - - "+am.getStateSet().size()+" - - "+am.getTransitionSet().size()+" - "+(am.getStateSet().size()-am.getTransitionSet().size()==1?"True":"False"));
//		 }
		 
	}



	private static State FindStateById(List<State> states, String target) {
		for(State state:states){
			if(state.getName().equals(target)){
				return state;
			}
		}
		return null;
	}



	private static class ValueComparator implements Comparator<Map.Entry<Automatic, Double>>  
	    {  
	        public int compare(Map.Entry<Automatic, Double> m,Map.Entry<Automatic, Double> n)  
	        {  
	            //return m.getValue()-n.getValue();//从小到大排序，从大到小排序需要  
	        	//return m.getValue().compareTo(n.getValue());
	        	return n.getValue().compareTo(m.getValue());
	        }  
	    }  
	
	// 性能测试的一条路径
	public static Automatic getPerformPathFromAutomatic(Automatic automatic) throws Exception {
		mAutomatic = automatic;
		findStateByID = DataHelper.getStateIdHashMap(automatic.getStateSet());
		statePoints = DataHelper.getStatesImportantPoint(automatic);
		
		ArrayList<Transition> tranList = PerformTestPath.getPerformTestPathFromAutomatic(automatic);
		for(Transition tran : tranList) {
			
			
			ShowInfor.print();
			ShowInfor.print(3, tran.getName());
			ShowInfor.print(3, "condition:" + tran.getCondition());
			
		}
		return GeneratePath.fromTranListToAuto(tranList);
	}
	
	public static Automatic fromTranListToAuto(ArrayList<Transition> tranList) {
		PathAndImpPoint pp = new PathAndImpPoint(tranList);
		//findPointByPath.put(pp.automatic, String.valueOf(pp.impPoint));
//		System.out.println(pp.impPoint + "**********************************");
//		System.out.println(pp.automatic.toString());
		findPointByPath.put(pp.automatic, pp.impPoint);
		return pp.automatic;
	}
}

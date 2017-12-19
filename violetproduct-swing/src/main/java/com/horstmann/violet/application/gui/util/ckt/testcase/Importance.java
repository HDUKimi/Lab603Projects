package com.horstmann.violet.application.gui.util.ckt.testcase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.horstmann.violet.application.gui.util.ckt.handle.*;
import com.horstmann.violet.application.gui.util.ckt.xml.borderTestXML;

public class Importance {

	public static void main(String[] args) {
	//	String xml = "电梯模拟ForXStream.xml";
		String xml = "EA4.1.0 功能场景1ForXStream.xml";
		Automatic automatic = GetAutomatic.getAutomatic(xml);// 获得原始的时间自动机
		ArrayList<Automatic> testCase = MinTC.sideCoverage(automatic);
		ArrayList<Automatic> SoreOftestcase = pathOfImportance(testCase,automatic);
		for(Automatic a : SoreOftestcase){
			System.out.println("----------------------");
			System.out.println(a.getName() + "---" + a.getPathOfImportance() + "---" + a.getPathOfAllImpPercentage());
		}				
	}
	
	/**
	 * 根据传入的路径计算路径的重要度
	 * @param testCase
	 */
	public static ArrayList<Automatic> pathOfImportanceToCalculate(ArrayList<Automatic> testCase, Automatic automatic){
		Automatic auto = impOfAllState(automatic);
		for(Automatic a : testCase){
			int importance = 0;
			double impPercentage = 0;
			for(State s :a.getStateSet()){
				for(State state : auto.getStateSet()){
					if(s.getName().equals(state.getName())){
						importance  = importance + state.getImportance();
						impPercentage = impPercentage + state.getImpPercentage();
						break;
					}
				}
			}
			a.setPathOfImportance(importance);
			a.setPathOfAllImpPercentage(impPercentage);
		}

		return testCase;
	}
	
	/**
	 * 根据传入的路径计算路径的重要度
	 * @param testCase
	 */
	public static ArrayList<Automatic> pathOfImportanceToSort(ArrayList<Automatic> testCase){
		testCase.sort(new Comparator<Automatic>() {
			@Override
			public int compare(Automatic o1, Automatic o2) {
				return o1.getPathOfAllImpPercentage()-o2.getPathOfAllImpPercentage()<0?1:-1;
			}
			
		});
		
		return testCase;
	}
	
	
	/**
	 * 根据传入的路径计算路径的重要度
	 * @param testCase
	 */
	public static ArrayList<Automatic> pathOfImportance(ArrayList<Automatic> testCase, Automatic automatic){
		Automatic auto = impOfAllState(automatic);
		for(Automatic a : testCase){
			int importance = 0;
			double impPercentage = 0;
			for(State s :a.getStateSet()){
				for(State state : auto.getStateSet()){
					if(s.getName().equals(state.getName())){
						importance  = importance + state.getImportance();
						impPercentage = impPercentage + state.getImpPercentage();
						break;
					}
				}
			}
			a.setPathOfImportance(importance);
			a.setPathOfAllImpPercentage(impPercentage);
		}
		
		
		testCase.sort(new Comparator<Automatic>() {
			@Override
			public int compare(Automatic o1, Automatic o2) {
				return o1.getPathOfAllImpPercentage()-o2.getPathOfAllImpPercentage()<0?1:-1;
			}
			
		});
		
		
		return testCase;
	}
	
	
	
	/**
	 * 计算原始时间自动机的重要度，不改变原始时间自动机信息
	 * @param automatic
	 * @return
	 */
	private static Automatic impOfAllState(Automatic automatic){
		Automatic auto1=automatic.clone();
		deletCirTranByDFS(auto1);
		deletCirTran(auto1);
		importanceOfState(auto1);
		double max = auto1.getMax();
		double min = auto1.getMin();
//		System.out.println("max---------" + max);
//		System.out.println("min---------" + min);
		for(State state : auto1.getStateSet()){
			double percent = (state.getImportance() - min) / (max- min);
			state.setImpPercentage(percent);
		}
//		for(State s : auto1.getStateSet()){
//			System.out.println(s.getName() + "---" + s.getId() + "-- " + s.getImportance());
//		}
//		System.out.println("-----------------------------------------");
//		System.out.println();
//		for(Transition t : auto1.getTransitionSet()){
//			System.out.println("   ---------------------");
//			State source = t.getSourceState();
//			State target = t.getTargetState(); 
//			System.out.println(source.getName() + "---" + source.getId() + "-- " + source.getImportance()  + "-- " + source.getImpPercentage());
//			System.out.println(target.getName() + "---" + target.getId() + "-- " + target.getImportance()  + "-- " + target.getImpPercentage());
//			System.out.println("   ---------------------");
//			System.out.println();
//		}
		return auto1;
	}
	
	
	private static void deletCirTranByDFS(Automatic auto) {
		
		boolean onStack[]=new boolean[auto.getStateSet().size()];
		
		State initstate=auto.getInitState();
		initstate.setVisited(true);
		onStack[0]=true;
		
		
		List<Transition> nextTranList=initstate.getNextTranSet();
		Iterator<Transition> iterator=nextTranList.iterator();
		while (iterator.hasNext()) {
			Transition transition = (Transition) iterator.next();
			if(onStack[auto.getStateSet().indexOf(transition.getTargetState())]){
				iterator.remove();
				auto.getTransitionSet().remove(transition);
			}
			else if(!transition.getTargetState().isVisited()){
				DFS(auto,transition.getTargetState(),onStack);
			}
		}
				
		
	}

	private static void DFS(Automatic auto, State state, boolean[] onStack) {
		state.setVisited(true);
		onStack[auto.getStateSet().indexOf(state)]=true;
		
		List<Transition> nextTranList=state.getNextTranSet();
		Iterator<Transition> iterator=nextTranList.iterator();
		while (iterator.hasNext()) {
			Transition transition = (Transition) iterator.next();
			if(onStack[auto.getStateSet().indexOf(transition.getTargetState())]){
				iterator.remove();
				auto.getTransitionSet().remove(transition);
			}
			else if(!transition.getTargetState().isVisited()){
				DFS(auto,transition.getTargetState(),onStack);
			}
		}
		
		onStack[auto.getStateSet().indexOf(state)]=false;
	}

	/**
	 * 去除回路，以及更新自动机信息
	 * @param a
	 */
	public static void deletCirTran(Automatic a){
		//ArrayList<Transition> tranSet = new ArrayList<Transition>();
		//int i = 0;
//		for(Transition t : a.getTransitionSet()){
//			if(t.getSourceState().getId() < t.getTargetState().getId()){//不是回路
//				tranSet.add(t);
//			}
////			else{
////				i++;
////				System.out.println(i + "---");
////			}
//		}
		//更新每个状态的前驱和后继迁移集合，以及每个迁移的源状态和目的状态信息
		//a.setTransitionSet(tranSet);
		for(State s : a.getStateSet()){
			ArrayList<Transition> nextTranSet = new ArrayList<Transition>();
			ArrayList<Transition> proTranSet = new ArrayList<Transition>();
			for(Transition t : a.getTransitionSet()){
				if(t.getSource().equals(s.getName())){
					nextTranSet.add(t);
				}
				if(t.getTarget().equals(s.getName())){
					proTranSet.add(t);
				}
			}
			s.setProTranSet(proTranSet);
			s.setNextTranSet(nextTranSet);
		}
		for(Transition t : a.getTransitionSet()){
			State sourceState = new State();
			State targetState = new State();
			sourceState = GetAutomatic.findStateFromString(t.getSource(), a);
			targetState = GetAutomatic.findStateFromString(t.getTarget(), a);
			t.setSourceState(sourceState);
			t.setTargetState(targetState);
		}
		
		//为去除回路的自动机设置终止节点
		for(State state:a.getStateSet()) {
			int k1= 0;
			for(Transition tran:a.getTransitionSet()){
				if(state.getName().equals(tran.getSource())){
					k1=1;
				}
			}
			if(k1==0){
				state.setFinalState(true);
			}else{
				state.setFinalState(false);
			}
		}
		
		
	}
	/**
	 * 为自动机设置重要度
	 * @param auto
	 * @return
	 */
	
	public static void importanceOfState(Automatic auto){
		//为初始状态设置重要度为1
		for(State s1 : auto.getStateSet()){
			if(s1.getName().equals(auto.getInitState().getName())){
				s1.setProImportance(1);
				//System.out.println("------------");
			}
			if(s1.isFinalState()){
				s1.setNextImportance(1);
				//System.out.println("+++++++++++++");
			}
		}
		
//		for(State ss : auto.getStateSet()){
//			System.out.println(ss.getProImportance() + "--" + ss.getNextImportance());
//		}
		
		for(State s : auto.getStateSet()){
			//System.out.println("源 ： " + s.getProImportance() + "--" + s.getNextImportance());
			if(s.getProImportance() == 0){
				int pro = impProDfs(s/*,auto*/);
			//	System.out.println("pro-------------" + pro);
				s.setProImportance(pro);
			}
		//	System.out.println("1求得前节点====" + s.getProImportance() + "--" + s.getNextImportance());
			if(s.getProImportance() == 0){
			//	System.out.println("  === " + s);
			//	System.out.println("  === " + s.getName() + "--" + s.getProTranSet() );
				for(Transition t : auto.getTransitionSet()){
					if(t.getTarget().equals(s.getName())){
					//	System.out.println("  ===   " + t);
					}
				}
			}
			/*if(s.getNextImportance() == 0){
				//System.out.println("*******************");
				int next = impnextDfs(s);
				System.out.println("next-------------" + next);
				s.setNextImportance(next);
				
			}
			System.out.println("2求得后节点====" + s.getProImportance() + "--" + s.getNextImportance());
			if(!(s.getProImportance()==0) && !(s.getNextImportance()==0)){
				int importance = s.getProImportance() * s.getNextImportance();
				s.setImportance(importance);
			}
			System.out.println("3总节点====" + s.getImportance());
			System.out.println("----------------------------");*/
		}
		
		for(int i=auto.getStateSet().size()-1; i>=0; i--){
			State s = auto.getStateSet().get(i);
			if(s.getNextImportance() == 0){
				//System.out.println("*******************");
				int next = impnextDfs(s);
			//	System.out.println("next-------------" + next);
				s.setNextImportance(next);		
			}
			//System.out.println("2求得后节点====" + s.getProImportance() + "--" + s.getNextImportance());		
		}

		
		for(State s1 : auto.getStateSet()){
			//System.out.println();
			//System.out.println("-------------                  -----------------");
			//System.out.println(s1);
			if(!(s1.getProImportance()==0) && !(s1.getNextImportance()==0)){
				int importance = s1.getProImportance() * s1.getNextImportance();
				s1.setImportance(importance);
				//System.out.println(s1.getName() + " --- " + s1.getImportance());
				//System.out.println(s1.getProImportance() + " --- " + s1.getNextImportance());
			}
			//System.out.println("-------------                  -----------------");
		}
		//return 0;
		
		
	}
	/**
	 * 
	 * @param s
	 * @return
	 */
	public static int impProDfs(State s/*, Automatic auto*/){
		int importance = 0;
		if(s.getProImportance() == 0){
			//System.out.println("*************");
			ArrayList<Transition> tSet = s.getProTranSet();
			//System.out.println(tSet);
			//System.out.println("*************");
			for(Transition t : tSet){
				State state = t.getSourceState();
				if(state.getProImportance() == 0){
					importance = /*importance +*/ impProDfs(state/*,auto*/);
				}else{
					importance += state.getProImportance();
				}
			}

		}
		return importance;
	}
	/**
	 * 
	 * @param s
	 * @return
	 */
	public static int impnextDfs(State s/*, Automatic auto*/){
		//System.out.println("-----------------------------------------------------------------");
		int importance = 0;
		if(s.getNextImportance() == 0){
			ArrayList<Transition> tSet = s.getNextTranSet();
			//System.out.println("nextTSet-------" + tSet.size() + "--" + tSet);
			//System.out.println();
			for(Transition t : tSet){
				State state = t.getTargetState();
				//System.out.println("t------" + t);
				//System.out.println("1-------" + importance);
				//System.out.println(state.getNextImportance());
				if(state.getNextImportance() == 0){
					importance = /*importance +*/impnextDfs(state/*,auto*/);
				}else{
					importance = importance + state.getNextImportance();
				}
				//System.out.println("2-------" + importance);
				//System.out.println("*************");
			}

		}
		//System.out.println("-----------------------------------------------------------------");
		return importance;
		
	}
	

}

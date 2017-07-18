package com.horstmann.violet.application.gui.util.ckt.testcase;

import java.util.ArrayList;
import java.util.HashSet;

import com.horstmann.violet.application.gui.util.ckt.handle.Automatic;
import com.horstmann.violet.application.gui.util.ckt.handle.DBM_element;
import com.horstmann.violet.application.gui.util.ckt.handle.DBM_elementToDBM;
import com.horstmann.violet.application.gui.util.ckt.handle.State;
import com.horstmann.violet.application.gui.util.ckt.handle.StringToDBM_element;
import com.horstmann.violet.application.gui.util.ckt.handle.StringToDBM_element__1;
import com.horstmann.violet.application.gui.util.ckt.handle.Transition;
import com.horstmann.violet.application.gui.util.wj.bean.UppaalLocation;
import com.horstmann.violet.application.gui.util.wj.bean.UppaalTemPlate;
import com.horstmann.violet.application.gui.util.wj.bean.UppaalTransition;

public class PathCoverage_new {
	private static Automatic a;	
	public static int maxCircle = 1;// 最少环走一次
	public static ArrayList<Transition> transitionList = new ArrayList<Transition>();// 将集合当做栈来用 收集每一个路径
	static ArrayList<ArrayList<Transition>> TT=new ArrayList<ArrayList<Transition>>();//测试序列集合（边）

	
	public static void main(String[] args) {	
		//第2种
		Automatic auto=getAutomatic();
		//搜索终止状态集合
		for(State state:auto.getStateSet()) {
			int k1= 0;
			for(Transition tran:auto.getTransitionSet()){//判断目标状态是否已被访问
				if(state.getName().equals(tran.getSource())){//找出以此状态为起点的迁移
					k1=1;
				}
			}
			if(k1==0){
				System.out.println("终止状态------"+state.getName());
				state.setFinalState(true);
				//System.out.println();
			}		
		}
		a = auto;
		State initialState = auto.getInitState();
		initialState.setStateAccessTimes(1);
		dfs(initialState);
		ArrayList<Automatic> testcaseSet = GetAutomaticSet(TT);
		System.out.println("测试路径个数："+testcaseSet.size());
		for(int i=0;i<testcaseSet.size();i++){
			System.out.println("第"+(i+1)+"条测试路径");
			for(Transition tran:testcaseSet.get(i).getTransitionSet()){
				System.out.println(tran.getName());
			}
			//System.out.println(testcaseSet.get(i).getTransitionSet());
		}				
	}
	/**
	 * 
	 * @param TT
	 * @return
	 */
	public static ArrayList<Automatic> testCase(Automatic auto){
		
		//Automatic auto=getAutomatic();
		/*//搜索终止状态集合
		for(State state:auto.getStateSet()) {
			int k1= 0;
			for(Transition tran:auto.getTransitionSet()){//判断目标状态是否已被访问
				if(state.getName().equals(tran.getSource())){//找出以此状态为起点的迁移
					k1=1;
				}
			}
			if(k1==0){
				System.out.println("终止状态------"+state.getName());
				state.setFinalState(true);
				//System.out.println();
			}		
		}*/
		a = auto;
		State initialState = auto.getInitState();
		initialState.setStateAccessTimes(1);
		dfs(initialState);
		ArrayList<Automatic> testcaseSet = GetAutomaticSet(TT);
		System.out.println("测试路径个数："+testcaseSet.size());
		for(int i=0;i<testcaseSet.size();i++){
			System.out.println("第"+(i+1)+"条测试路径");
			for(Transition tran:testcaseSet.get(i).getTransitionSet()){
				System.out.println(tran.getName());
			}
			//System.out.println(testcaseSet.get(i).getTransitionSet());
		}				
	return testcaseSet;
	}
	
	/**
	 * 寻找一条与状态X相连的没有被访问的迁移
	 */
	public static ArrayList<Transition> nextTranSet(State X){
		ArrayList<Transition> transition = new ArrayList<>();
		for(Transition tran:a.getTransitionSet()){
			if(X.getName().equals(tran.getSource())){
				transition.add(tran);
			}
		}
		return transition;
	}
	
	
	/**
	 * 对Automatic进行一次深度优先搜索即搜集所有路径
	 * @param initialState 初始状态
	 */
	private static void dfs(State initialState) {		
		ArrayList<State> a_StateSet=a.getStateSet();//获得时间自动机状态集合	
		ArrayList<Transition> outTransitions = nextTranSet(initialState);
		if (outTransitions != null && outTransitions.size() != 0) {
			for (Transition nextTransition : outTransitions) {
				System.out.println(nextTransition.getName()+"=======");
				transitionList.add(nextTransition);
				for(State s:a_StateSet){	
					if(nextTransition.getTarget().equals(s.getName())){//找到这条迁移的目标状态
						// 遇到节点已经访问过两次就换迁移，以此保证环路只走一次
						if (s.getStateAccessTimes() == maxCircle) {
							continue;
						}	
						// 每访问下一个状态节点，使其节点访问次数+1
						s.setStateAccessTimes(s.getStateAccessTimes() + 1);
						//transitionList.add(nextTransition);
						State nextState = s; //指向地址
						
						if(s.isFinalState()){//判断目标状态s是否是终止节点
							//s是终止节点就搜集迁移栈中的一条测试路径
							ArrayList<Transition> oneRoute = new ArrayList<Transition>();
							oneRoute.addAll(transitionList);
							ArrayList<Transition> TransitionSet=new ArrayList<Transition>();//一条测试序列中的边
							for(Transition t:transitionList){
								TransitionSet.add(t);
							}
							TT.add(TransitionSet);									
						}
						
						dfs(nextState);
						transitionList.remove(transitionList.size() - 1);
						nextState.setStateAccessTimes(nextState.getStateAccessTimes() - 1);
						break;
					}
				}				
			}
		}
	}
	public static ArrayList<Automatic> GetAutomaticSet(ArrayList<ArrayList<Transition>> TT){
		int n=TT.size();//测试用例个数
		ArrayList<Automatic> testcaseSet=new ArrayList<Automatic>();//测试用例集合
		for(int i=0;i<n;i++){
			Automatic test_case=new Automatic();
			test_case.setClockSet(a.getClockSet());
			test_case.setName("测试用例"+(i+1));
			test_case.setTransitionSet(TT.get(i));
			HashSet<State> Stateset = new HashSet<State>();
			for(Transition t : TT.get(i)){
				for(State s:a.getStateSet()){
					if(t.getTarget().equals(s.getName()) || t.getSource().equals(s.getName())){//找到这条迁移的目标状态
						Stateset.add(s);
					}
				}
			}
			
			ArrayList<State> stateSet = new ArrayList<State>();
			for(State s:Stateset){
				stateSet.add(s);
			}
			test_case.setStateSet(stateSet);
				
			
			test_case.setInitState(a.getInitState());
			testcaseSet.add(test_case);
		}
		return testcaseSet;
	}
	
	
	
	
	
	/**
	 * 获得 一个时间自动机
	 * @return
	 */
	public static Automatic getAutomatic(){
		UppaalTemPlate temPlate=new UppaalTemPlate();
		ArrayList<UppaalTransition> T_transitions=new ArrayList<UppaalTransition>();
		ArrayList<UppaalLocation> T_locations=new ArrayList<UppaalLocation>();
		UppaalLocation T_InitState=new UppaalLocation();
		String T_name="第一个时间自动机";
		ArrayList<String> T_Clocks=new ArrayList<String>();
		T_Clocks.add("x");

		ArrayList<String> ar1 =new ArrayList<String>();
		ar1.add("x<2");
		ar1.add("x>=0");
		ArrayList<String> ar2 =new ArrayList<String>();
		ar2.add("x>=2");
		ar2.add("x<5");
		ArrayList<String> ar3 =new ArrayList<String>();
		ar3.add("x<=1");
		ar3.add("x>=0");
		ArrayList<String> ar4 =new ArrayList<String>();
		ar4.add("x>=0");
		ar4.add("x<=3");
//		ar4.add("x>1");
//		ar4.add("x<=3");
		ArrayList<String> ar5 =new ArrayList<String>();
		ar5.add("x>3");
		ar5.add("x<5");
		ArrayList<String> ar6 =new ArrayList<String>();
		ar6.add("x>=0");
		ar6.add("x<1");
		ArrayList<String> ar7 =new ArrayList<String>();
		ar7.add("x>=0");
		ar7.add("x<3");
//		ar7.add("x>=1");
//		ar7.add("x<3");
		ArrayList<String> ar8 =new ArrayList<String>();
		ArrayList<String> ar9 =new ArrayList<String>();

		
		UppaalLocation l1=new UppaalLocation();
		l1.setName("l1");
		l1.setInvariant(ar1);
		l1.setFinalState(false);
		UppaalLocation l2=new UppaalLocation();
		l2.setName("l2");
		l2.setInvariant(ar2);
		l2.setFinalState(false);
		UppaalLocation l3=new UppaalLocation();
		l3.setName("l3");
		l3.setInvariant(ar3);
		l3.setFinalState(false);
		UppaalLocation l4=new UppaalLocation();
		l4.setName("l4");
		l4.setInvariant(ar4);
		l4.setFinalState(false);
		UppaalLocation l5=new UppaalLocation();
		l5.setName("l5");
		l5.setInvariant(ar5);
		l5.setFinalState(false);
		UppaalLocation l6=new UppaalLocation();
		l6.setName("l6");
		l6.setInvariant(ar6);
		l6.setFinalState(false);
		UppaalLocation l7=new UppaalLocation();
		l7.setName("l7");
		l7.setInvariant(ar7);
		l7.setFinalState(true);
		UppaalLocation l8=new UppaalLocation();
		l8.setName("l8");
		l8.setInvariant(ar8);
		l8.setFinalState(true);
		UppaalLocation l9=new UppaalLocation();
		l9.setName("l9");
		l9.setInvariant(ar9);
		l9.setFinalState(false);
	
		
		T_locations.add(l1);
		T_locations.add(l2);
		T_locations.add(l3);
		T_locations.add(l4);
		T_locations.add(l5);
		T_locations.add(l6);
		T_locations.add(l7);
		T_locations.add(l8);
		T_locations.add(l9);
		
		
		UppaalTransition e1=new UppaalTransition();
		e1.setName("e1");
		e1.setSource(l1.getName());
		e1.setTarget(l2.getName());
		ArrayList<String> reset1 =new ArrayList<String>();
		e1.setResetClocks(reset1);
		ArrayList<String> constraint1 =new ArrayList<>();
		e1.setConstraint(constraint1);
		ArrayList<String> events1=new ArrayList<String>();
		e1.setEvents(events1);
		ArrayList<String> types1=new ArrayList<String>();
		e1.setTypes(types1);
		ArrayList<String> typeIds1=new ArrayList<String>();
		e1.setTypeIds(typeIds1);
		
		UppaalTransition e2 =new UppaalTransition();
		e2.setName("e2");
		e2.setSource(l2.getName());
		e2.setTarget(l3.getName());
		ArrayList<String> reset2 =new ArrayList<String>();
		reset2.add("x");
		e2.setResetClocks(reset2);
		ArrayList<String> constraint2 =new ArrayList<>();
		e2.setConstraint(constraint2);
		ArrayList<String> events2=new ArrayList<String>();
		e2.setEvents(events2);
		ArrayList<String> types2=new ArrayList<String>();
		e2.setTypes(types2);
		ArrayList<String> typeIds2=new ArrayList<String>();
		e2.setTypeIds(typeIds2);
		
		UppaalTransition e3 =new UppaalTransition();
		e3.setName("e3");
		e3.setSource(l3.getName());
		e3.setTarget(l4.getName());
		ArrayList<String> reset3 =new ArrayList<String>();
		reset3.add("x");
		e3.setResetClocks(reset3);
		ArrayList<String> constraint3 =new ArrayList<>();
		e3.setConstraint(constraint3);
		ArrayList<String> events3=new ArrayList<String>();
		e3.setEvents(events3);
		ArrayList<String> types3=new ArrayList<String>();
		e3.setTypes(types3);
		ArrayList<String> typeIds3=new ArrayList<String>();
		e3.setTypeIds(typeIds3);
		
		UppaalTransition e4 =new UppaalTransition();
		e4.setName("e4");
		e4.setSource(l4.getName());
		e4.setTarget(l5.getName());
		ArrayList<String> reset4 =new ArrayList<String>();
		e4.setResetClocks(reset4);
		ArrayList<String> constraint4 =new ArrayList<>();
		e4.setConstraint(constraint4);
		ArrayList<String> events4=new ArrayList<String>();
		e4.setEvents(events4);
		ArrayList<String> types4=new ArrayList<String>();
		e4.setTypes(types4);
		ArrayList<String> typeIds4=new ArrayList<String>();
		e4.setTypeIds(typeIds4);
		
		UppaalTransition e5 =new UppaalTransition();
		e5.setName("e5");
		e5.setSource(l5.getName());
		e5.setTarget(l6.getName());
		ArrayList<String> reset5 =new ArrayList<String>();
		reset5.add("x");
		e5.setResetClocks(reset5);
		ArrayList<String> constraint5 =new ArrayList<>();
		e5.setConstraint(constraint5);
		ArrayList<String> events5=new ArrayList<String>();
		e5.setEvents(events5);
		ArrayList<String> types5=new ArrayList<String>();
		e5.setTypes(types5);
		ArrayList<String> typeIds5=new ArrayList<String>();
		e5.setTypeIds(typeIds5);
		
		UppaalTransition e6 =new UppaalTransition();
		e6.setName("e6");
		e6.setSource(l6.getName());
		e6.setTarget(l7.getName());
		ArrayList<String> reset6 =new ArrayList<String>();
		reset6.add("x");
		e6.setResetClocks(reset6);
		ArrayList<String> constraint6 =new ArrayList<>();
		e6.setConstraint(constraint6);
		ArrayList<String> events6=new ArrayList<String>();
		e6.setEvents(events6);
		ArrayList<String> types6=new ArrayList<String>();
		e6.setTypes(types6);
		ArrayList<String> typeIds6=new ArrayList<String>();
		e6.setTypeIds(typeIds6);
		
		UppaalTransition e7 =new UppaalTransition();
		e7.setName("e7");
		e7.setSource(l3.getName());
		e7.setTarget(l8.getName());
		ArrayList<String> reset7 =new ArrayList<String>();
		e7.setResetClocks(reset7);
		ArrayList<String> constraint7 =new ArrayList<>();
		e7.setConstraint(constraint7);
		ArrayList<String> events7=new ArrayList<String>();
		e7.setEvents(events7);
		ArrayList<String> types7=new ArrayList<String>();
		e7.setTypes(types7);
		ArrayList<String> typeIds7=new ArrayList<String>();
		e7.setTypeIds(typeIds7);
		
		UppaalTransition e8 =new UppaalTransition();
		e8.setName("e8");
		e8.setSource(l1.getName());
		e8.setTarget(l9.getName());
		ArrayList<String> reset8 =new ArrayList<String>();
		e8.setResetClocks(reset8);
		ArrayList<String> constraint8 =new ArrayList<>();
		e8.setConstraint(constraint8);
		ArrayList<String> events8=new ArrayList<String>();
		e8.setEvents(events8);
		ArrayList<String> types8=new ArrayList<String>();
		e8.setTypes(types8);
		ArrayList<String> typeIds8=new ArrayList<String>();
		e8.setTypeIds(typeIds8);
		
		UppaalTransition e9 =new UppaalTransition();
		e9.setName("e9");
		e9.setSource(l9.getName());
		e9.setTarget(l3.getName());
		ArrayList<String> reset9 =new ArrayList<String>();
		e9.setResetClocks(reset9);
		ArrayList<String> constraint9 =new ArrayList<>();
		e9.setConstraint(constraint9);
		ArrayList<String> events9=new ArrayList<String>();
		e9.setEvents(events9);
		ArrayList<String> types9=new ArrayList<String>();
		e9.setTypes(types9);
		ArrayList<String> typeIds9=new ArrayList<String>();
		e9.setTypeIds(typeIds9);
		
		T_transitions.add(e1);
		T_transitions.add(e2);
		T_transitions.add(e3);
		T_transitions.add(e4);
		T_transitions.add(e5);
		T_transitions.add(e6);
		T_transitions.add(e7);
		T_transitions.add(e8);
		T_transitions.add(e9);

		
		temPlate.setTransitions(T_transitions);
		temPlate.setLocations(T_locations);
		temPlate.setClockSet(T_Clocks);
		temPlate.setInitState(l1);
		temPlate.setName(T_name);
		
//		for(UppaalTransition tran:T_transitions){
//			System.out.println(tran.getName()+"=====");
//		}
		
		T_InitState=l1;
		temPlate.setInitState(T_InitState);
		
		Automatic automatic=new Automatic();
		ArrayList<Transition> TransitionSet=new ArrayList<Transition>();//automatic中的转换集合
		ArrayList<State> StateSet = new ArrayList<State>();//automatic中的状态集合

		
		ArrayList<String> Clocks=temPlate.getClocks();//获取时间自动机中的时钟集合
		
		ArrayList<UppaalLocation> locations=temPlate.getLocations();//获取时间自动机中的所有状态
	
		for(UppaalLocation loc:locations){//遍历所有状态
		
			if(loc.getInvariant().size()!=0){
				ArrayList<String> invar=loc.getInvariant();
				ArrayList<String> invariant=new ArrayList<String>();
				for(String i:invar){
					String[] s=i.split("s");
					invariant.add(s[0]);
				}
			
				DBM_element[][] DBM=DBM_elementToDBM.buildDBM(Clocks,StringToDBM_element__1.stringToDBM_element(Clocks, invariant));//将状态中的不变式转成DBM矩阵
				
				State state=new State();
				state.setName(loc.getName());
				state.setInvariantDBM(DBM);
				//state.setFinalState(loc.isFinalState());
				state.setPosition(loc.getName());
				state.setFinalState(loc.isFinalState());
				StateSet.add(state);
			}
			else{
				ArrayList<String> invariant=new ArrayList<String>();
				DBM_element[][] DBM=DBM_elementToDBM.buildDBM(Clocks,StringToDBM_element.stringToDBM_element(Clocks, invariant));//将状态中的不变式为空，则DBM矩阵为全集
				
				State state=new State();
				state.setName(loc.getName());
				state.setInvariantDBM(DBM);
				//state.setFinalState(loc.isFinalState());
				state.setFinalState(loc.isFinalState());
				state.setPosition(loc.getName());
				StateSet.add(state);
			}
			
		}
		automatic.setStateSet(StateSet);//设定automatic中的状态集合
		
		ArrayList<UppaalTransition> transitions=temPlate.getTransitions();//获取template中的所有转换
		for(UppaalTransition tran:transitions){//遍历转换集合
			//System.out.println(tran.getName()+"========00");
			if(tran.getConstraint().size()!=0){
				ArrayList<String> cons=tran.getConstraint();//获取转换中的约束
				ArrayList<String> constraint=new ArrayList<String>();
				for(String c:cons){
					String[] s=c.split("s");
					constraint.add(s[0]);
				}
				
				DBM_element[][] DBM=DBM_elementToDBM.buildDBM(Clocks,StringToDBM_element__1.stringToDBM_element(Clocks, constraint));//将转换中的约束转成DBM矩阵
				
				Transition transition=new Transition();
				transition.setConstraintDBM(DBM);
						
				ArrayList<String> events=new ArrayList<String>();
				if(tran.getEvents().size()!=0){
					String event=new String();
					
					for(String e:tran.getEvents())
					{
						event+=e+";";
					}
					events.add(event);
				}
				transition.setEventSet(events);
				transition.setName(tran.getName());
				transition.setResetClockSet(tran.getResetClocks());
				transition.setSource(tran.getSource());
				transition.setTarget(tran.getTarget());
				transition.setTypeIds(tran.getTypeIds());
				transition.setTypes(tran.getTypes());
				//临时添加
				//ArrayList<DBM_element[][]> com_constraint=Complement.complement(constraint, Clocks);
				//transition.setCom_constraint(com_constraint);
				
				TransitionSet.add(transition);
			}
			else{
				ArrayList<String> constraint=new ArrayList<String>();
				DBM_element[][] DBM=DBM_elementToDBM.buildDBM(Clocks,StringToDBM_element.stringToDBM_element(Clocks, constraint));//转换中的约束为空，则DBM矩阵为全集
				
				Transition transition=new Transition();
				transition.setConstraintDBM(DBM);
				
				ArrayList<String> events=new ArrayList<String>();
				if(tran.getEvents().size()!=0){
					String event=new String();
					
					for(String e:tran.getEvents())
					{
						event+=e+";";
					}
					events.add(event);
				}
				//System.out.println(tran.getName()+"========00");
				transition.setEventSet(events);
				transition.setName(tran.getName());
				transition.setResetClockSet(tran.getResetClocks());
				transition.setSource(tran.getSource());
				transition.setTarget(tran.getTarget());
				TransitionSet.add(transition);
				transition.setTypeIds(tran.getTypeIds());
				transition.setTypes(tran.getTypes());
			}
			
		}
		automatic.setTransitionSet(TransitionSet);//设定automatic中的转换集合
		
		
		
		ArrayList<String> ClockSet=temPlate.getClocks();
		automatic.setClockSet(ClockSet);//设定autotimatic中的时钟集合
		
		//设定automatic中的初始状态
		State initstate=new State();
		initstate.setFinalState(temPlate.getInitState().isFinalState());
		initstate.setName(temPlate.getInitState().getName());
		if(temPlate.getInitState().getInvariant().size()!=0){
			initstate.setInvariantDBM(DBM_elementToDBM.buildDBM(Clocks,StringToDBM_element__1.stringToDBM_element(Clocks, temPlate.getInitState().getInvariant())));
			initstate.setAddClockRelationDBM(initstate.getInvariantDBM());
			initstate.setPosition(temPlate.getInitState().getName());
		}
		else{
			ArrayList<String> invariant=new ArrayList<String>();
			DBM_element[][] DBM=DBM_elementToDBM.buildDBM(Clocks,StringToDBM_element.stringToDBM_element(Clocks, invariant));//将状态中的不变式转成DBM矩阵
			initstate.setInvariantDBM(DBM);
			initstate.setAddClockRelationDBM(initstate.getInvariantDBM());
			initstate.setPosition(temPlate.getInitState().getName());
		}
		automatic.setInitState(initstate);
		//设定automatic的name
		String name=temPlate.getName();
		automatic.setName(name);
		
		
		ArrayList<State> States=automatic.getStateSet();
		for(State s:States){//设置添加了时钟复位后的时钟约束
			s.setAddClockRelationDBM(s.getInvariantDBM());
		}
		
		return automatic;
	}
	
	
	
}

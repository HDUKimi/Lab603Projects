package com.horstmann.violet.application.gui.util.ckt.testcase;

import java.util.ArrayList;
import java.util.Stack;

import com.horstmann.violet.application.gui.util.ckt.handle.*;


public class PathCoverage1 {

	public static void main(String[] args) {
		String xml="rc_loopForXStream1.01.xml";
		Automatic auto=GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
		ArrayList<Automatic> testcaseSet=testCase(auto);

	}
	
	/**
	 * 根据深度优先生成树产生满足状态覆盖准则的测试序列集合
	 * @param a
	 * @return
	 */
	public static ArrayList<Automatic> testCase(Automatic aaa){
		ArrayList<State> StateList=new ArrayList<State>();//终止状态集合
		ArrayList<State> StateList1=new ArrayList<State>();//终止状态集合
		//搜索终止状态集合
		for(State state1:aaa.getStateSet()){
			int k1= 0;
			for(Transition tran:aaa.getTransitionSet()){//判断目标状态是否已被访问
				if(state1.getName().equals(tran.getSource())){//找出以此状态为起点的迁移
					k1=1;
				}
			}
			if(k1==0){
				StateList.add(state1);
			}				
		}
		for(int ii=0;ii<StateList.size();ii++){
			System.out.println("终止节点id："+StateList.get(ii).getId());
		}		

		/*//搜索终止状态集合
		for(State state1:aaa.getStateSet()){
			int k1= 0;
			for(Transition tran:aaa.getTransitionSet()){//判断目标状态是否已被访问
				if(state1.getName().equals(tran.getSource())){//找出以此状态为起点的迁移
					k1=1;
				}
			}
			if(k1==0){
				StateList1.add(state1);
			}				
		}
		for(int ii=0;ii<StateList1.size();ii++){
			System.out.println("---终止节点id："+StateList1.get(ii).getId());
		}*/


		ArrayList<Transition> aaa_Transition=aaa.getTransitionSet();//获得时间自动机迁移集合
		ArrayList<State> aaa_StateSet=aaa.getStateSet();//获得时间自动机状态集合

		ArrayList<State> visitedState=new ArrayList<State>();//（保存已访问过的状态集合）
		ArrayList<ArrayList<State>> S=new ArrayList<ArrayList<State>>();//测试序列集合（状态）
		ArrayList<ArrayList<Transition>> T=new ArrayList<ArrayList<Transition>>();//测试序列集合（边）

		Stack<State> sstack=new Stack<State>();//状态栈
		Stack<Transition> tstack=new Stack<Transition>();//迁移栈
		State intstate=aaa.getInitState();//获得时间自动机的初始状态
		sstack.push(intstate);//初始状态入栈
		visitedState.add(intstate);//将表示为已访问过
		while(!sstack.isEmpty()){
			State X=sstack.peek();//获得栈顶元素，但不出栈

			int leaf=1;//是叶子节点
			for(Transition t:aaa_Transition){//判断X是否是叶子节点
				if(t.getSource().equals(X.getName())){
					leaf=0;
					break;
				}
			}
			if(leaf==0){//X不是叶子节点
				int flag=0;//标志X的邻接点都被访问过
				for(Transition tran:aaa_Transition){//判断目标状态是否已被访问
					if(X.getName().equals(tran.getSource())){//找到状态连接的其中一条迁移
						int k=0;
						for(State state:visitedState){
							if(tran.getTarget().equals(state.getName())){
								k=1;
								break;
							}
						}
						if(k==0){//目标状态没有被访问过，目标状态加入栈，并加入StateSet，并将这条迁移加入TransitionSet
							flag=1;
							for(State s:aaa_StateSet){
								if(tran.getTarget().equals(s.getName())){//找到目标状态
									sstack.push(s);//将这个目标状态加入栈
									/*State ss=new State();
									ss.setName(s.getName());
									ss.setPosition(s.getPosition());
									ss.setInvariantDBM(s.getInvariantDBM());*/
									visitedState.add(s);//将这个目标状态标志为已访问过
									tstack.push(tran);//将这条迁移如入栈
									break;
								}
							}
						}
						if(flag==1){break;}//如果这条迁移的目的状态没有被访问过，则跳出遍历迁移的循环
						if(k==1){//如果这条迁移的目标状态已被访问过，则遍历下一条迁移
							continue;
						}
					}
				}
				if(flag==0){//标志X的邻接点都被访问过，则出栈
					sstack.pop();
					if(!tstack.isEmpty()){tstack.pop();}

				}
			}
			if(leaf==1){//X是叶子节点,获得状态栈中状态，迁移栈中迁移，构成一个测试序列，加入测试序列集合
				ArrayList<State> StateSet=new ArrayList<State>();//一条测试序列中的状态
				for(State s:sstack){
					StateSet.add(s);
				}
				S.add(StateSet);
				/*for(State s:StateSet){
					System.out.println(s.getName());
				}
				System.out.println("-------");*/

				ArrayList<Transition> TransitionSet=new ArrayList<Transition>();//一条测试序列中的边
				for(Transition t:tstack){
					TransitionSet.add(t);
				}
				T.add(TransitionSet);
				/*for(Transition t:TransitionSet){
					System.out.println(t.getSource()+"-->"+t.getTarget());
				}
				System.out.println("***********");*/

				sstack.pop();
				tstack.pop();
			}

		}


		//System.out.println(S.size());
		int n=S.size();//测试用例个数
		ArrayList<Automatic> testcaseSet=new ArrayList<Automatic>();//测试用例集合
		for(int i=0;i<n;i++){
			Automatic test_case=new Automatic();
			test_case.setClockSet(aaa.getClockSet());
			test_case.setName("测试用例"+(i+1));
			test_case.setStateSet(S.get(i));
			test_case.setTransitionSet(T.get(i));
			test_case.setInitState(aaa.getInitState());
			testcaseSet.add(test_case);
		}
		System.out.println("测试用例个数："+testcaseSet.size());
		for(Automatic auto:testcaseSet){
			System.out.println(auto.getName());
			for(State s:auto.getStateSet()){
				System.out.println(s.getName());
				//System.out.println(s.getPosition());
			/*	for(int i=0;i<3;i++){
					for(int j=0;j<3;j++){
						DBM_element cons=s.getInvariantDBM()[i][j];

						//System.out.println("DBM_i:"+cons.getDBM_i());
						//System.out.println("DBM_j:"+cons.getDBM_j());
						System.out.println("value:"+cons.getValue());
						System.out.println("Strictness:"+cons.isStrictness());									
					}
				}*/
				System.out.println("-------------");
			}
			for(Transition t:auto.getTransitionSet()){
				System.out.println(t.getSource()+"-->"+t.getTarget());			
				System.out.println(t.getEventSet().get(0));
				System.out.println(t.getEventSet().get(1));
				if(t.getResetClockSet().size()!=0){
					System.out.println(t.getResetClockSet().get(0));
				}
				System.out.println("-------------");
			}
		}

		return testcaseSet;
	}
	
	
	
	

}

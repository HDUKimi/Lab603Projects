package com.horstmann.violet.application.gui.util.ckt.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

import com.horstmann.violet.application.gui.util.ckt.handle.*;

public class path {
//	private static Automatic mAutomatic;
//	private static ArrayList<State> states;
//	private static ArrayList<Transition> transitions;
//	private static State initState;
//	private static ArrayList<State> finalStates;
//	private static int count;// 计算路径的条数
	public static void main(String[] args) {

		String xml="EASmallTime7ForXStream.xml";
		Automatic auto=GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
		ArrayList<Automatic> testcase=testcase(auto);
		System.out.println(testcase.size());
		
		
	}

	public static ArrayList<Automatic> testcase(Automatic a) {
		ArrayList<Transition> aaa_Transition = a.getTransitionSet();// 获得时间自动机迁移集合
		ArrayList<State> aaa_StateSet = a.getStateSet();//获得时间自动机状态集合
		ArrayList<State> visitedState = new ArrayList<State>();//（保存已访问过的状态集合）
		ArrayList<ArrayList<State>> S = new ArrayList<ArrayList<State>>();// 测试序列集合（状态）
		ArrayList<ArrayList<Transition>> T = new ArrayList<ArrayList<Transition>>();// 测试序列集合（边）

		Stack<State> sstack = new Stack<State>();// 状态栈
		Stack<Transition> tstack = new Stack<Transition>();// 迁移栈
		State intstate = a.getInitState();// 获得时间自动机的初始状态
		sstack.push(intstate);// 初始状态入栈
		visitedState.add(intstate);// 将表示为已访问过
		while (!sstack.isEmpty()) {
			State X = sstack.peek();// 获得栈顶元素，但不出栈

			int leaf = 1;// 是叶子节点
			for (Transition t : aaa_Transition) {// 判断X是否是叶子节点
				if (t.getSource().equals(X.getName())) {
					leaf = 0;
					break;
				}
			}
			if (leaf == 0) {// X不是叶子节点
				int flag = 0;// 标志X的邻接点都被访问过
				for (Transition tran : aaa_Transition) {// 判断目标状态是否已被访问
					if (X.getName().equals(tran.getSource())) {// 找到状态连接的其中一条迁移
						int k = 0;
						for (State state : visitedState) {
							if (tran.getTarget().equals(state.getName())) {
								k = 1;
								break;
							}
						}
						if (k == 0) {// 目标状态没有被访问过，目标状态加入栈，并加入StateSet，并将这条迁移加入TransitionSet
							flag = 1;
							for (State s : aaa_StateSet) {
								if (tran.getTarget().equals(s.getName())) {// 找到目标状态
									sstack.push(s);// 将这个目标状态加入栈
									/*
									 * State ss=new State();
									 * ss.setName(s.getName());
									 * ss.setPosition(s.getPosition());
									 * ss.setInvariantDBM(s.getInvariantDBM());
									 */
									visitedState.add(s);// 将这个目标状态标志为已访问过
									tstack.push(tran);// 将这条迁移如入栈
									break;
								}
							}
						}
						if (flag == 1) {
							break;
						} // 如果这条迁移的目的状态没有被访问过，则跳出遍历迁移的循环
						if (k == 1) {// 如果这条迁移的目标状态已被访问过，则遍历下一条迁移
							continue;
						}
					}
				}
				if (flag == 0) {// 标志X的邻接点都被访问过，则出栈
					sstack.pop();
					if (!tstack.isEmpty()) {
						tstack.pop();
					}

				}
			}
			if (leaf == 1) {// X是叶子节点,获得状态栈中状态，迁移栈中迁移，构成一个测试序列，加入测试序列集合
				ArrayList<State> StateSet = new ArrayList<State>();// 一条测试序列中的状态
				for (State s : sstack) {
					StateSet.add(s);
				}
				S.add(StateSet);
				/*
				 * for(State s:StateSet){ System.out.println(s.getName()); }
				 * System.out.println("-------");
				 */

				ArrayList<Transition> TransitionSet = new ArrayList<Transition>();// 一条测试序列中的边
				for (Transition t : tstack) {
					TransitionSet.add(t);
				}
				T.add(TransitionSet);
				/*
				 * for(Transition t:TransitionSet){
				 * System.out.println(t.getSource()+"-->"+t.getTarget()); }
				 * System.out.println("***********");
				 */

				sstack.pop();
				tstack.pop();
			}

		}

		// System.out.println(S.size());
		int n = S.size();// 测试用例个数
		ArrayList<Automatic> testcaseSet = new ArrayList<Automatic>();// 测试用例集合
		for (int i = 0; i < n; i++) {
			Automatic test_case = new Automatic();
			test_case.setClockSet(a.getClockSet());
			test_case.setName("测试用例" + (i + 1));
			test_case.setStateSet(S.get(i));
			test_case.setTransitionSet(T.get(i));
			test_case.setInitState(a.getInitState());
			testcaseSet.add(test_case);
		}

		return testcaseSet;
	}
}

package com.horstmann.violet.application.gui.util.wj.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.horstmann.violet.application.gui.util.ckt.handle.Automatic;
import com.horstmann.violet.application.gui.util.ckt.handle.State;
import com.horstmann.violet.application.gui.util.ckt.handle.Transition;

public class PathCoverUtil {
	private static Automatic mAutomatic;
	private static ArrayList<State> states;
	private static ArrayList<Transition> transitions;
	private static State initState;
	private static ArrayList<State> finalStates;	
	private static HashMap<String, State> findStateByID;
	private static HashSet<String> fromToIsCircle;
	private static int count;
	static class pathBack{
		State start;
		State end;
		public pathBack(State start, State end) {
			this.start = start;
			this.end = end;
		}
	}
	private static ArrayList<pathBack> pathBacks;
	// 根据自动机生成   路径覆盖的测试用例
	public static HashSet<ArrayList<Transition>> getPathCoverTestCaseFromAutomatic(Automatic automatic) {
		mAutomatic = automatic;
		states = automatic.getStateSet();//所有的状态
		transitions = automatic.getTransitionSet();//所有的迁移
		initState = automatic.getInitState();// 获取初始状态
		finalStates = getFinalStates();//获取终止状态
		pathBacks = new ArrayList<>();// 初始化 回路记录
		fromToIsCircle = new HashSet<>();// 记录所有回路   "a到b"    String
		count = 0;// 记录路径的条数
		if (isEmpty()) {// 如果以上的成员变量有一个为空 则直接返回null
			System.err.println("空指针");
			return null;
		}
		// 设置key为id 找state的hashmap
		findStateByID = DataHelper.getStateIdHashMap(states);
		
		
		
		HashSet<ArrayList<Transition>> noCirclePathsFromInitState = new HashSet<>();
		for(State finalState : finalStates) {
			HashSet<ArrayList<Transition>> directPaths = new HashSet<>();
//			if (finalState.getId() ==  initState.getId()) {
//				continue;
//			}
			for(Transition tran : transitions) {
				if (tran.getSource().equals(initState.getPosition())) { // 从初始点出发的迁移
					ArrayList<Transition> path = new ArrayList<Transition>();
					
					int[] visited = new int[transitions.size() + 1]; 	
					
					dfsPathFromStartToEndExceptCircle(tran, finalState, 
							visited, path, directPaths);
				}
			}
			noCirclePathsFromInitState.addAll(directPaths);
		}
		
		
		return noCirclePathsFromInitState;// 返回所有路径
	}

	// 路径覆盖的 去掉环的 从tran开始到finalState的所有路径最终为directPaths
	private static void dfsPathFromStartToEndExceptCircle(Transition tran, State finalState, int[] visited,
			ArrayList<Transition> onePath, HashSet<ArrayList<Transition>> directPaths) {
		State sourceState = findStateByID.get(tran.getSource());
		State targetState = findStateByID.get(tran.getTarget());
		
		visited[tran.getId()] = 1;
		onePath.add(tran);
//		if (targetState.getId() == 1) {
//			System.out.println(1);
//		}
		if (targetState == finalState) { // 到达目标节点
			directPaths.add(new ArrayList<>(onePath));
			count++;
			System.out.println("得到一条路径到状态" +finalState.getId() + "----count :" +count);
			
				
			
			for(Transition tran1 : onePath) {
				System.out.println(tran1.getName());
			}
			onePath.remove(tran);
			visited[tran.getId()] = 0;
			return ;
		}
		
		if (sourceState.getId() >= targetState.getId()) { // 是回路 
			if (fromToIsCircle.add(sourceState.getId() + "到" + targetState.getId())) {// 没有相关记录
				pathBacks.add(new pathBack(sourceState, targetState));// 记录
			}
			
			onePath.remove(tran);
			visited[tran.getId()] = 0;
			return ;
		}
		

		
		
		boolean in = false;
		for(Transition nextTran : transitions) {
			State sourceStateNow = findStateByID.get(nextTran.getSource());
			State targetStateNow = findStateByID.get(nextTran.getTarget());
			if (targetState.getId() > sourceStateNow.getId() || targetStateNow.getId() > finalState.getId() ) {
				continue;
			} else {
//				System.out.println(nextTran.getName());
			}
			if (nextTran.getName().equals("loopSSSSSSSSSSSSSSSS()")) {
//				System.out.println(123);
			}
			if (sourceStateNow == targetState && visited[nextTran.getId()] == 0) { // 为连接的两条迁移
				dfsPathFromStartToEndExceptCircle(nextTran, finalState, visited, onePath, directPaths);
				
			}
		}
		if (!in) {
//			System.out.println("无路可走");
		}
		onePath.remove(tran);
		visited[tran.getId()] = 0;
		return ;
	}
	// 判断数据是否为空
	private static boolean isEmpty() {
		if (mAutomatic != null && states.size() > 0 && transitions.size() > 0
				&& initState != null && finalStates.size() > 0) {
			return false;
		}
		return true;
	}

	private static ArrayList<State> getFinalStates() {
		ArrayList<State> finalStates = new ArrayList<>();
		for(State state : states) {
			if (state.isFinalState()) {
				finalStates.add(state);
			}
		}
		return finalStates;
	}

	
}

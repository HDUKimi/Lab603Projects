package com.horstmann.violet.application.gui.util.wj.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.horstmann.violet.application.gui.util.ckt.handle.Automatic;
import com.horstmann.violet.application.gui.util.ckt.handle.State;
import com.horstmann.violet.application.gui.util.ckt.handle.Transition;
import com.horstmann.violet.application.gui.util.ckt.output.ShowInfor;

public class TranCoverUtil {
	private static Automatic mAutomatic;
	private static ArrayList<State> states;
	private static ArrayList<Transition> transitions;
	private static State initState;
	private static ArrayList<State> finalStates;	
	private static HashMap<String, State> findStateByID;
	private static HashSet<String> fromToIsCircle;
	private static int originTranSize;
	private static int wantedSize;
	private static double[] points;
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
	public static ArrayList<ArrayList<Transition>> getTranCoverTestCaseFromAutomatic(Automatic automatic, int pathsLimitedSize, double[] statePoints){
		mAutomatic = automatic;
		states = automatic.getStateSet();//所有的状态
		transitions = automatic.getTransitionSet();//所有的迁移
		initState = automatic.getInitState();// 获取初始状态
		finalStates = getFinalStates();//获取终止状态
		pathBacks = new ArrayList<>();// 初始化 回路记录
		fromToIsCircle = new HashSet<>();// 记录所有回路   "a到b"    String
		originTranSize = transitions.size();//初始的transition数目
		wantedSize = pathsLimitedSize;// 大致需要的测试路径数目
		points = statePoints;// 状态重要度
		count = 0;
		if (isEmpty()) {// 如果以上的成员变量有一个为空 则直接返回null
			System.err.println("空指针");
			return null;
		}
		
		// 设置key为id 找state的hashmap
		findStateByID = DataHelper.getStateIdHashMap(states);
		
		// 去掉回路的边
		removeCircleTran();
		
		ShowInfor.print("去掉回路的边剩余" + transitions.size());
		
		
		// 找出开始节点到终止节点的路径
		ArrayList<ArrayList<Transition>> noCirclePathsFromInitState = new ArrayList<>();
		for(State finalState : finalStates) {
			HashSet<ArrayList<Transition>> directPaths = new HashSet<>();
			for(Transition tran : transitions) {
				if (tran.getSource().equals(initState.getPosition())) { // 从初始点出发的迁移
					ArrayList<Transition> path = new ArrayList<Transition>();
					
					int[] visited = new int[originTranSize * 2]; 	
					
					dfsPathFromStartToEnd(tran, finalState, 
							visited, path, directPaths);
				}
			}
			noCirclePathsFromInitState.addAll(directPaths);
		}
		
		// 去掉开始节点到终止节点的路径的所有tran
		for(ArrayList<Transition> trans : noCirclePathsFromInitState) {
			transitions.removeAll(trans);
		}
		
		ShowInfor.print("去掉终止节点路径的所有tran后剩余" + transitions.size());
		// 找出剩下的tran组成的部分路径 -------------------------
		while(true) {
			if (transitions.size() == 0) {
				break;
			}
			int[] visitedTran = new int[originTranSize * 2];
			ArrayList<Transition> pathPart = new ArrayList<>();
			
			dfsPathPart(transitions.get(0), visitedTran, pathPart);

			// 连接头尾--------------------------------
			fixPathPartFromOriginPaths(pathPart, noCirclePathsFromInitState);
			noCirclePathsFromInitState.add(pathPart);
			
			transitions.removeAll(pathPart);
			
		}
		return noCirclePathsFromInitState;// 返回所有路径
	}
	// 组合路径片段与原有的路径为完整路径
	private static void fixPathPartFromOriginPaths(ArrayList<Transition> pathPart,
			ArrayList<ArrayList<Transition>> noCirclePathsFromInitState) {
		Transition firstTran = pathPart.get(0);
		Transition lastTran = pathPart.get(pathPart.size() - 1);
		ArrayList<Transition> from = new ArrayList<>();
		ArrayList<Transition> to = new ArrayList<>();
		int indexFrom = -1, indexTo = -1;
		for(ArrayList<Transition> arr : noCirclePathsFromInitState) {
			for(int i = 0; i < arr.size(); i ++) {
				Transition tran = arr.get(i);
				State sourceState = findStateByID.get(tran.getSource());
				State targetState = findStateByID.get(tran.getTarget());
				State sourceStatePart = findStateByID.get(pathPart.get(0).getSource());
				State targetStatePart = findStateByID.get(pathPart.get(pathPart.size() - 1).getTarget());
				
				if (targetState == sourceStatePart) {
					from = arr;
					indexFrom = i;
				}
				if (sourceState == targetStatePart) {
					to = arr;
					indexTo = i;
				}
				if (indexFrom != -1 && indexTo != -1) {
					break;
				}
			}
			if (indexFrom != -1 && indexTo != -1) {
				break;
			}
		}
		try{
			pathPart.addAll(0, from.subList(0, indexFrom + 1));
			pathPart.addAll(to.subList(indexTo + 1, to.size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}


	// 找出多出的路径片段
	private static boolean dfsPathPart(Transition tran, int[] visitedTran, ArrayList<Transition> pathPart) {
		pathPart.add(tran);
		visitedTran[tran.getId()] = 1;
		State targetState = findStateByID.get(tran.getTarget());
		boolean hasNext = false;
		for(Transition nextTran : transitions) {
			if (visitedTran[nextTran.getId()] == 1) {
				continue;
			}
			State sourceStateNow = findStateByID.get(nextTran.getSource());
			if (targetState == sourceStateNow) {
				hasNext = true;
				if (dfsPathPart(nextTran, visitedTran, pathPart)) {
					return true;
				}
			}
		}
		if (!hasNext) {
			return true;
		}
		pathPart.remove(tran);
		visitedTran[tran.getId()] = 0;
		return true;
	}
	
	// 从tran开始到finalState的xxxxxxxxxxx条路径directPaths
	private static boolean dfsPathFromStartToEnd(Transition tran, State finalState, int[] visited,
			ArrayList<Transition> onePath, HashSet<ArrayList<Transition>> directPaths) {
		State targetState = findStateByID.get(tran.getTarget());
		visited[tran.getId()] = 1;
		onePath.add(tran);
		if (targetState == finalState) { // 到达目标节点
			directPaths.add(new ArrayList<>(onePath));
			ShowInfor.print("得到一条路径到状态" +finalState.getId() + "----count :" +count++);
			for(Transition tran1 : onePath) {
				ShowInfor.print(tran1.getName());
			}
			onePath.remove(tran);
			visited[tran.getId()] = 0;
			if (directPaths.size() >= wantedSize / finalStates.size()) { //可以控制开始节点到终止节点的路径数目15000
				return true;
			} else {
				return false;
			}
		}
		ArrayList<Transition> nextTrans = new ArrayList<>();
		for(Transition nextTran : transitions) {// 根据概率生成下一个tran
			State sourceStateNow = findStateByID.get(nextTran.getSource());
			State targetStateNow = findStateByID.get(nextTran.getTarget());
			if (targetStateNow.getId() > finalState.getId() ) { // 超出终止节点的id
				continue;
			} 
			
			if (sourceStateNow == targetState && visited[nextTran.getId()] == 0) { // 为连接的两条迁移
				nextTrans.add(nextTran);
			}
		}
		ArrayList<Transition> nextTranSet = new ArrayList<>();
		while(true) {
			Transition selectedNextTran = selectNextTran(nextTrans);
			nextTranSet.add(selectedNextTran);
			
			if (dfsPathFromStartToEnd(selectedNextTran, finalState, visited, onePath, directPaths)) {
				return true;
			}
			if (nextTranSet.size() >= nextTrans.size()) {
				break;
			}
		}
		
		onePath.remove(tran);
		visited[tran.getId()] = 0;
		return false;
	}
	
	
	private static Transition selectNextTran(ArrayList<Transition> nextTrans) {
		double sum = 0;
		ArrayList<Double> rates = new ArrayList<>();
		int index = 0;
		for(Transition tran : nextTrans) {
			State targetState = findStateByID.get(tran.getTarget());
			sum += points[targetState.getId()];
			rates.add( points[targetState.getId()]);
		}
		for(int i = 0; i < rates.size(); i++) {
			rates.set(i, rates.get(i) / sum);
		}
		int resIndex = DataHelper.randomPoints(rates);
		return nextTrans.get(resIndex);
	}



	// 去掉回路的transition
	private static void removeCircleTran() {
		int removeCount = 0;
		for(int i = 0; i < transitions.size(); i++) {
			Transition tran = transitions.get(i);
			State sourceState = findStateByID.get(tran.getSource());
			State targetState = findStateByID.get(tran.getTarget());
			if (sourceState.getId() >= targetState.getId()) {
				fromToIsCircle.add(sourceState.getId() + "->" + targetState.getId());
				transitions.remove(i);
				removeCount++;
				i--;
			}
		}
		ShowInfor.print("去掉了" + removeCount + "/" + originTranSize + "条循环迁移");
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

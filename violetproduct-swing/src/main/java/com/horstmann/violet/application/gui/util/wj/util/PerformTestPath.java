package com.horstmann.violet.application.gui.util.wj.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.horstmann.violet.application.gui.util.ckt.handle.Automatic;
import com.horstmann.violet.application.gui.util.ckt.handle.State;
import com.horstmann.violet.application.gui.util.ckt.handle.Transition;

public class PerformTestPath {
	
	private static Automatic mAutomatic;
	private static ArrayList<State> states;
	private static ArrayList<Transition> transitions;
	private static State initState;
	private static HashMap<String, State> findStateByID;
	private static int originTranSize;

	public static ArrayList<Transition> getPerformTestPathFromAutomatic(Automatic automatic) throws Exception {
		mAutomatic = automatic;
		states = automatic.getStateSet();//所有的状态
		transitions = automatic.getTransitionSet();//所有的迁移
		originTranSize = transitions.size();
		initState = automatic.getInitState();// 获取初始状态
		if (isEmpty()) {// 如果以上的成员变量有一个为空 则直接返回null
			System.err.println("空指针");
			return null;
		}
		
		// 设置key为id 找state的hashmap
		findStateByID = DataHelper.getStateIdHashMap(states);
		
		// 去掉回路的边
		removeCircleTran();
		
		System.out.println("去掉回路的边剩余" + transitions.size());
		
		// * 找出标记的一条路径
		Transition endTran = findMarkedEndTran();
		ArrayList<Transition> performTestPath = new ArrayList<Transition>();
		for(Transition tran : transitions) {
			if (tran.getSource().equals(initState.getPosition()) && tranIsMarked(tran)) {// 初始被标记的边
				
				dfsMarkedPath(tran, new int[originTranSize * 2], performTestPath, endTran);
				break;
			}
		}
		
		return performTestPath;
	}
	
	// 找出有标记的路径
	private static boolean dfsMarkedPath(Transition tran, int[] visitedTran, ArrayList<Transition> path, Transition endTran) throws Exception {
		
		path.add(tran);
		System.out.println("-------11111111111111---------------"+visitedTran.length+"  "+tran.getId());
		visitedTran[tran.getId()] = 1;
		System.out.println("-------2222222222222222---------------");
		if (tran == endTran) {
			System.out.println("搜索性能测试路径 到达目标迁移");
			return true;
		}
		State targetState = findStateByID.get(tran.getTarget());
		boolean hasNext = false;
		ArrayList<Transition> nextTrans = new ArrayList<>();
		int markedIndex = -1;
		for(int i = 0; i < transitions.size(); i++) {
			Transition nextTran = transitions.get(i);
			if (visitedTran[nextTran.getId()] == 1) {
				continue;
			}
			State sourceStateNow = findStateByID.get(nextTran.getSource());
			if (targetState == sourceStateNow) {
				hasNext = true;
				nextTrans.add(nextTran);
				if (tranIsMarked(nextTran)) {
					markedIndex = i;
				}
			}
			
		}
		if (!hasNext) {
			System.out.println("搜索性能测试路径 未到达目标迁移");
			throw new Exception();
		}
		
		if (nextTrans.size() == 1) {
			if (dfsMarkedPath(nextTrans.get(0), visitedTran, path, endTran)) {
				return true;
			}
		} else {// 分支情况  必须要有标记的tran
			if (markedIndex == -1) {
				System.out.println("搜索性能测试路径 搜索分支时未找到标记");
				throw new Exception();
			}
			if (dfsMarkedPath(transitions.get(markedIndex), visitedTran, path, endTran)) {
				return true;
			}
		}
		
		// 只有一条路径 不需要回溯
//		pathPart.remove(tran);
//		visitedTran[tran.getId()] = 0;
		return true;
	}
	// 找出被标记为##的tran(结束标志)
	private static Transition findMarkedEndTran() {
		for(Transition tran : transitions) {
			if (tran.getName().contains("##")) {
				return tran;
			}
		}
		return null;
	}
	// tran被标记则返回true
	private static boolean tranIsMarked(Transition nextTran) {
		return nextTran.getName().contains("@@");
	}
	
	// 判断数据是否为空
	private static boolean isEmpty() {
		if (mAutomatic != null && states.size() > 0 && transitions.size() > 0
				&& initState != null) {
			return false;
		}
		return true;
	}
	
	// 去掉回路的transition
	private static void removeCircleTran() {
		int removeCount = 0;
//		for(int i = 0; i < transitions.size(); i++) {
//			Transition tran = transitions.get(i);
//			State sourceState = findStateByID.get(tran.getSource());
//			State targetState = findStateByID.get(tran.getTarget());
//			if (sourceState.getId() >= targetState.getId()) {
//				transitions.remove(i);
//				removeCount++;
//				i--;
//			}
//		}
		
		Iterator<Transition> iterator=transitions.iterator();
		while (iterator.hasNext()) {
			Transition tran = (Transition) iterator.next();
			State sourceState = findStateByID.get(tran.getSource());
			State targetState = findStateByID.get(tran.getTarget());
			if (sourceState.getId() >= targetState.getId()) {
				iterator.remove();
				removeCount++;
			}
		}
		
	}
	
}

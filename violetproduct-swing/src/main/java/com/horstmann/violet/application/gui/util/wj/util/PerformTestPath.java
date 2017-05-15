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
		states = automatic.getStateSet();//���е�״̬
		transitions = automatic.getTransitionSet();//���е�Ǩ��
		originTranSize = transitions.size();
		initState = automatic.getInitState();// ��ȡ��ʼ״̬
		if (isEmpty()) {// ������ϵĳ�Ա������һ��Ϊ�� ��ֱ�ӷ���null
			System.err.println("��ָ��");
			return null;
		}
		
		// ����keyΪid ��state��hashmap
		findStateByID = DataHelper.getStateIdHashMap(states);
		
		// ȥ����·�ı�
		removeCircleTran();
		
		System.out.println("ȥ����·�ı�ʣ��" + transitions.size());
		
		// * �ҳ���ǵ�һ��·��
		Transition endTran = findMarkedEndTran();
		ArrayList<Transition> performTestPath = new ArrayList<Transition>();
		for(Transition tran : transitions) {
			if (tran.getSource().equals(initState.getPosition()) && tranIsMarked(tran)) {// ��ʼ����ǵı�
				
				dfsMarkedPath(tran, new int[originTranSize * 2], performTestPath, endTran);
				break;
			}
		}
		
		return performTestPath;
	}
	
	// �ҳ��б�ǵ�·��
	private static boolean dfsMarkedPath(Transition tran, int[] visitedTran, ArrayList<Transition> path, Transition endTran) throws Exception {
		
		path.add(tran);
		System.out.println("-------11111111111111---------------"+visitedTran.length+"  "+tran.getId());
		visitedTran[tran.getId()] = 1;
		System.out.println("-------2222222222222222---------------");
		if (tran == endTran) {
			System.out.println("�������ܲ���·�� ����Ŀ��Ǩ��");
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
			System.out.println("�������ܲ���·�� δ����Ŀ��Ǩ��");
			throw new Exception();
		}
		
		if (nextTrans.size() == 1) {
			if (dfsMarkedPath(nextTrans.get(0), visitedTran, path, endTran)) {
				return true;
			}
		} else {// ��֧���  ����Ҫ�б�ǵ�tran
			if (markedIndex == -1) {
				System.out.println("�������ܲ���·�� ������֧ʱδ�ҵ����");
				throw new Exception();
			}
			if (dfsMarkedPath(transitions.get(markedIndex), visitedTran, path, endTran)) {
				return true;
			}
		}
		
		// ֻ��һ��·�� ����Ҫ����
//		pathPart.remove(tran);
//		visitedTran[tran.getId()] = 0;
		return true;
	}
	// �ҳ������Ϊ##��tran(������־)
	private static Transition findMarkedEndTran() {
		for(Transition tran : transitions) {
			if (tran.getName().contains("##")) {
				return tran;
			}
		}
		return null;
	}
	// tran������򷵻�true
	private static boolean tranIsMarked(Transition nextTran) {
		return nextTran.getName().contains("@@");
	}
	
	// �ж������Ƿ�Ϊ��
	private static boolean isEmpty() {
		if (mAutomatic != null && states.size() > 0 && transitions.size() > 0
				&& initState != null) {
			return false;
		}
		return true;
	}
	
	// ȥ����·��transition
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

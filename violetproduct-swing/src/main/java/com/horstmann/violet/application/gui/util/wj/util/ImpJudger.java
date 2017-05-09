package com.horstmann.violet.application.gui.util.wj.util;

import java.util.ArrayList;
import java.util.HashMap;

import com.horstmann.violet.application.gui.util.ckt.handle.Automatic;
import com.horstmann.violet.application.gui.util.ckt.handle.State;
import com.horstmann.violet.application.gui.util.ckt.handle.Transition;

import Jama.Matrix;
public class ImpJudger {
	private Automatic mAutomatic;
	private ArrayList<State> states;
	private ArrayList<Transition> transitions;
	private HashMap<String, State> findStateByID;
	private double[][] map;
	private static final double ALPHA = 0.99;
	public ImpJudger(Automatic automatic) {
		mAutomatic = automatic;
		states = mAutomatic.getStateSet();
		transitions = mAutomatic.getTransitionSet();
	}
	
	// ��ȡÿһ��״̬id ��Ӧ����Ҫ��
	public double[] getStatesImportantPoints() {
		// ״̬id ��״̬��hashmap
		findStateByID = DataHelper.getStateIdHashMap(states);
		// �����ڽӾ���
		setAdjacencyMatrix();
		
		return calculateImp();
	}
	
	private double[] calculateImp() {
		// ��ʼ������
		double[][] inPoint = new double[states.size() + 1][1];
		double[][] outPoint = new double[states.size() + 1][1];
		double[][] left = new double[states.size() + 1][1];
		for(int i = 0; i < states.size() + 1; i++) {
			inPoint[i][0] = outPoint[i][0]  = 1;
			left[i][0] = 1 - ALPHA;
		}
		
		Matrix a = calculateIpOrOp(new Matrix(inPoint), new Matrix(left), new Matrix(map));
		Matrix b = calculateIpOrOp(new Matrix(outPoint), new Matrix(left), new Matrix(map).transpose());

		double[] res = new double[states.size() + 1];
		for(int i = 0; i < res.length; i++) {
			res[i] = a.get(i, 0) * b.get(i, 0);
		}
		return res;
	}
//	public Matrix calculateIpOrOp(double[][] point, double[][] vectorI, double[][] map2) {
//		return calculateIpOrOp(new Matrix(point), new Matrix(vectorI), new Matrix(map2));
//	}
	// ��ʽ
	public Matrix calculateIpOrOp(Matrix point, Matrix left, Matrix map) {
		Matrix oldPoint;
		do {
			oldPoint = point;
			Matrix MAPxI = map.times(point);
			Matrix right = MAPxI.times(ALPHA / MAPxI.normF());
			point  = left.plus(right);
			
		} while (closeToOldPoint(oldPoint.getArray(), point.getArray()));
		return point;
		
	}
	// ���㷽��
	private boolean closeToOldPoint(double[][] oldInPoint, double[][] inPoint) {
		double dif = 0;
		for(int i = 0; i < oldInPoint.length; i++) {
			dif += (oldInPoint[i][0] - inPoint[i][0]) * (oldInPoint[i][0] - inPoint[i][0]);
		}
		return Math.sqrt(dif / oldInPoint.length) >= 1E-15;
	}

	// ����Ǩ�������ڽӾ���
	private void setAdjacencyMatrix() {
		map = new double[states.size() + 1][states.size() + 1];
		for(Transition tran : mAutomatic.getTransitionSet()) {
			State sourceState = findStateByID.get(tran.getSource());
			State targetState = findStateByID.get(tran.getTarget());
//			if((sourceState!=null)&&(targetState!=null)){
//				map[sourceState.getId()][targetState.getId()] = 1;
//			}
			map[sourceState.getId()][targetState.getId()] = 1;
		}
	}
}

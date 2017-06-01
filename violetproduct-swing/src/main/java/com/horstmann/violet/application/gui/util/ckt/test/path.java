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
//	private static int count;// ����·��������
	public static void main(String[] args) {

		String xml="EASmallTime7ForXStream.xml";
		Automatic auto=GetAutomatic.getAutomatic(xml);//���ԭʼ��ʱ���Զ���
		ArrayList<Automatic> testcase=testcase(auto);
		System.out.println(testcase.size());
		
		
	}

	public static ArrayList<Automatic> testcase(Automatic a) {
		ArrayList<Transition> aaa_Transition = a.getTransitionSet();// ���ʱ���Զ���Ǩ�Ƽ���
		ArrayList<State> aaa_StateSet = a.getStateSet();//���ʱ���Զ���״̬����
		ArrayList<State> visitedState = new ArrayList<State>();//�������ѷ��ʹ���״̬���ϣ�
		ArrayList<ArrayList<State>> S = new ArrayList<ArrayList<State>>();// �������м��ϣ�״̬��
		ArrayList<ArrayList<Transition>> T = new ArrayList<ArrayList<Transition>>();// �������м��ϣ��ߣ�

		Stack<State> sstack = new Stack<State>();// ״̬ջ
		Stack<Transition> tstack = new Stack<Transition>();// Ǩ��ջ
		State intstate = a.getInitState();// ���ʱ���Զ����ĳ�ʼ״̬
		sstack.push(intstate);// ��ʼ״̬��ջ
		visitedState.add(intstate);// ����ʾΪ�ѷ��ʹ�
		while (!sstack.isEmpty()) {
			State X = sstack.peek();// ���ջ��Ԫ�أ�������ջ

			int leaf = 1;// ��Ҷ�ӽڵ�
			for (Transition t : aaa_Transition) {// �ж�X�Ƿ���Ҷ�ӽڵ�
				if (t.getSource().equals(X.getName())) {
					leaf = 0;
					break;
				}
			}
			if (leaf == 0) {// X����Ҷ�ӽڵ�
				int flag = 0;// ��־X���ڽӵ㶼�����ʹ�
				for (Transition tran : aaa_Transition) {// �ж�Ŀ��״̬�Ƿ��ѱ�����
					if (X.getName().equals(tran.getSource())) {// �ҵ�״̬���ӵ�����һ��Ǩ��
						int k = 0;
						for (State state : visitedState) {
							if (tran.getTarget().equals(state.getName())) {
								k = 1;
								break;
							}
						}
						if (k == 0) {// Ŀ��״̬û�б����ʹ���Ŀ��״̬����ջ��������StateSet����������Ǩ�Ƽ���TransitionSet
							flag = 1;
							for (State s : aaa_StateSet) {
								if (tran.getTarget().equals(s.getName())) {// �ҵ�Ŀ��״̬
									sstack.push(s);// �����Ŀ��״̬����ջ
									/*
									 * State ss=new State();
									 * ss.setName(s.getName());
									 * ss.setPosition(s.getPosition());
									 * ss.setInvariantDBM(s.getInvariantDBM());
									 */
									visitedState.add(s);// �����Ŀ��״̬��־Ϊ�ѷ��ʹ�
									tstack.push(tran);// ������Ǩ������ջ
									break;
								}
							}
						}
						if (flag == 1) {
							break;
						} // �������Ǩ�Ƶ�Ŀ��״̬û�б����ʹ�������������Ǩ�Ƶ�ѭ��
						if (k == 1) {// �������Ǩ�Ƶ�Ŀ��״̬�ѱ����ʹ����������һ��Ǩ��
							continue;
						}
					}
				}
				if (flag == 0) {// ��־X���ڽӵ㶼�����ʹ������ջ
					sstack.pop();
					if (!tstack.isEmpty()) {
						tstack.pop();
					}

				}
			}
			if (leaf == 1) {// X��Ҷ�ӽڵ�,���״̬ջ��״̬��Ǩ��ջ��Ǩ�ƣ�����һ���������У�����������м���
				ArrayList<State> StateSet = new ArrayList<State>();// һ�����������е�״̬
				for (State s : sstack) {
					StateSet.add(s);
				}
				S.add(StateSet);
				/*
				 * for(State s:StateSet){ System.out.println(s.getName()); }
				 * System.out.println("-------");
				 */

				ArrayList<Transition> TransitionSet = new ArrayList<Transition>();// һ�����������еı�
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
		int n = S.size();// ������������
		ArrayList<Automatic> testcaseSet = new ArrayList<Automatic>();// ������������
		for (int i = 0; i < n; i++) {
			Automatic test_case = new Automatic();
			test_case.setClockSet(a.getClockSet());
			test_case.setName("��������" + (i + 1));
			test_case.setStateSet(S.get(i));
			test_case.setTransitionSet(T.get(i));
			test_case.setInitState(a.getInitState());
			testcaseSet.add(test_case);
		}

		return testcaseSet;
	}
}

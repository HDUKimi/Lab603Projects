package com.horstmann.violet.application.gui.util.ckt.testcase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import com.horstmann.violet.application.gui.util.ckt.handle.ATDTR__1;
import com.horstmann.violet.application.gui.util.ckt.handle.Automatic;
import com.horstmann.violet.application.gui.util.ckt.handle.GetAutomatic;
import com.horstmann.violet.application.gui.util.ckt.handle.IPR__1;
import com.horstmann.violet.application.gui.util.ckt.handle.State;
import com.horstmann.violet.application.gui.util.ckt.handle.Transition;

import jdk.nashorn.internal.runtime.FindProperty;

public class MinTC {

	public static void main(String[] args) {
		String xml = "����ģ��ForXStream.xml";
		//String xml = "EA4.1.0 ���ܳ���1ForXStream.xml";
		// String xml = "arm_motors_checkForXStream.xml";
		// String xml = "compass_accumulateForXStream.xml";
		// String xml = "do_user_takeoffForXStream.xml";
		// String xml =
		// "failsafe_battery_eventForXStream.xml";//-------------������
		// String xml = "failsafe_gcs_checkForXStream.xml";//-------������
		// String xml = "fast_loop-----display0ForXStream.xml";
		// String xml = "fast_loop-----display1ForXStream.xml";//���Կ�һ��
		// String xml = "fast_loop-----display2ForXStream.xml";//25�� ���Կ�һ��
		// String xml = "fast_loopForXStream.xml";//29�� ���Կ�һ��
		// String xml = "gcs_check_inputForXStream.xml";//------������
		// String xml = "gcs_data_stream_sendForXStream.xml";
		// String xml = "guided_angle_control_runForXStream.xml";
		// String xml = "guided_pos_control_runForXStream.xml";
		// String xml = "guided_posvel_control_runForXStream.xml";
		// String xml = "guided_run-----display0ForXStream.xml";
		// String xml = "guided_run-----display1ForXStream.xml";
		// String xml = "guided_run-----display2ForXStream.xml";
		// String xml = "guided_run-----display3ForXStream.xml";
		// String xml = "guided_run-----display4ForXStream.xml";
		// String xml = "guided_runForXStream.xml";
		// String xml = "guided_set_destinationForXStream.xml";
		// String xml = "guided_set_velocityForXStream.xml";
		// String xml = "guided_takeoff_runForXStream.xml";
		// String xml = "guided_vel_control_runForXStream.xml";
		// String xml = "handleMessage-----display0ForXStream.xml";
		// String xml =
		// "handleMessage-----display1ForXStream.xml";//----------2�� ������
		// String xml = "handleMessage-----display2ForXStream.xml";//---------4��
		// ������
		// String xml = "handleMessage-----display3ForXStream.xml";//--------6��
		// ������
		// String xml = "handleMessage-----display4ForXStream.xml";//---------8��
		// ������
		// String xml =
		// "handleMessage-----display5ForXStream.xml";//---------10�� ������
		// String xml =
		// "handleMessage-----display6ForXStream.xml";//---------12�� ������
		// String xml =
		// "handleMessage-----display7ForXStream.xml";//----------15�� ������
		// String xml = "handleMessageForXStream.xml";//----------17�� ������
		// String xml = "land_gps_runForXStream.xml";
		// String xml = "land_nogps_runForXStream.xml";
		// String xml = "land_run-----display0ForXStream.xml";
		// String xml = "land_run-----display1ForXStream.xml";
		// String xml = "land_runForXStream.xml";
		// String xml = "loop-----display0ForXStream.xml";
		// String xml = "loop-----display1ForXStream.xml";//31 ������
		// String xml = "loop-----display2ForXStream.xml";//44 ������
		// String xml = "loop-----display3ForXStream.xml";//51 ������
		// String xml = "loop-----display4ForXStream.xml";//92������
		// String xml = "loop-----display5ForXStream.xml";//136
		// String xml = "loop-----display6ForXStream.xml";
		// String xml = "loop-----display74ForXStream.xml";
		// String xml = "loop-----display8ForXStream.xml";
		// String xml = "loop-----display9ForXStream.xml";
		// String xml = "loop-----display10ForXStream.xml";
		// String xml = "loop-----display11ForXStream.xml";
		// String xml = "loopForXStream.xml";//������

		// String xml = "motors_outputForXStream.xml";
		// String xml = "one_hz_loopForXStream.xml";
		// String xml = "rc_loop-----display0ForXStream.xml";
		// String xml = "rc_loopForXStream.xml";
		// String xml = "read_AHRSForXStream.xml";
		// String xml = "motors_outputForXStream.xml";
		// String xml = "read_radioForXStream.xml";
		// String xml = "setup-----display0ForXStream.xml";
		// String xml = "setupForXStream.xml";
		// String xml = "three_hz_loopForXStream.xml";
		// String xml = "UAV-----display0ForXStream.xml";
		// String xml = "update_altitudeForXStream.xml";
		// String xml = "update_batt_compassForXStream.xml";
		// String xml = "update_flight_mode-----display0ForXStream.xml";
		// String xml = "update_flight_mode-----display1ForXStream.xml";
		// String xml = "update_flight_modeForXStream.xml";
		// String xml = "update_GPSForXStream.xml";
		// String xml =
		// "update_land_and_crash_detectors-----display0ForXStream.xml";
		// String xml = "update_land_and_crash_detectorsForXStream.xml";
		// String xml = "update_land_detectorForXStream.xml";

		Automatic automatic = GetAutomatic.getAutomatic(xml);// ���ԭʼ��ʱ���Զ���
		 Automatic new_automatic = IPR__1.iPR(automatic);// ��ò�ֺ��ʱ���Զ���
		 Automatic aTDRTAutomatic = ATDTR__1.aTDRT(new_automatic, automatic);// ���ȥ������ʱ��Ǩ�ƺ��ʱ���Զ���

		Automatic autoOfSource = automatic;//���������ʱ���Զ���
		// ������ֹ״̬����
		/*
		 * for(State state:aTDRTAutomatic.getStateSet()) { int k1= 0;
		 * for(Transition tran:aTDRTAutomatic.getTransitionSet()){//�ж�Ŀ��״̬�Ƿ��ѱ�����
		 * if(state.getName().equals(tran.getSource())){//�ҳ��Դ�״̬Ϊ����Ǩ�� k1=1; } }
		 * if(k1==0){ state.setFinalState(true); } }
		 */
		/*
		 * int i = 1; for(State state:automatic.getStateSet()) { int k1= 0;
		 * for(Transition tran:automatic.getTransitionSet()){//�ж�Ŀ��״̬�Ƿ��ѱ�����
		 * if(state.getName().equals(tran.getSource())){//�ҳ��Դ�״̬Ϊ����Ǩ�� k1=1; } }
		 * if(k1==0){ //state.setFinalState(true); //System.out.println("��ֹ״̬--- "
		 * + i + "---" + state); }else{ //state.setFinalState(false);
		 * ////System.out.println("������ֹ״̬--- " + i + "---" + state); } i++; }
		 */

		ArrayList<ArrayList<Transition>> ss = MinSSOfFirstForTC(autoOfSource);
		
		
		
		//==============================================================================
		// Ǩ�Ƹ���(��Ҫƽ̨��ʾ�ӿ�)
		ArrayList<Automatic> notMinTestCase = NotMinsideCoverage(ss, autoOfSource);
		// �����Ϣ
		System.out.println(notMinTestCase.size() + "-----����·���ĸ���");
		for (Automatic auto : notMinTestCase) {
			for (Transition tran : auto.getTransitionSet()) {
				System.out.println(tran.getName() + "------" + tran.getTranTimeName());
			}
			System.out.println();
			System.out.println("-------");
		}
		System.out.println();
		
		//==============================================================================
		// Ǩ�Ƹ���(��Ҫƽ̨��ʾ�ӿ�)
		ArrayList<Automatic> testCase = MinsideCoverage(ss, autoOfSource);
		// �����Ϣ
		System.out.println();
		System.out.println(testCase.size() + "-----����·���ĸ���");// --------------------------
		for (Automatic auto : testCase) {
			for (Transition tran : auto.getTransitionSet()) {
				System.out.println(tran.getName() + "------" + tran.getTranTimeName());
			}
			System.out.println();
			System.out.println("-------");
		}

	}

	/**
	 * ���Ե�һ���㷨
	 * 
	 * @param auto
	 * @return
	 */
	public static ArrayList<Automatic> test1(Automatic auto) {
		ArrayList<ArrayList<Transition>> ss = minSS(auto);
		ArrayList<Automatic> test1 = MinsideCoverage(ss, auto);
		return test1;
	}

	public static ArrayList<Automatic> NotMinsideCoverage(ArrayList<ArrayList<Transition>> ss, Automatic auto) {
		// ArrayList<ArrayList<Transition>> ss = MinSSOfFirstForTC(auto);
		ArrayList<ArrayList<Transition>> TestcaseOfSide = TestcaseOfSide(ss, auto);
		// ArrayList<ArrayList<Transition>> minTestcaseOfSide =
		// MinTestcaseOfSide(ss, auto);
		int n = TestcaseOfSide.size();// ������������
		ArrayList<Automatic> testcaseSet = new ArrayList<Automatic>();// ������������
		for (int i = 0; i < n; i++) {
			Automatic test_case = new Automatic();
			test_case.setClockSet(auto.getClockSet());
			test_case.setName("��������" + (i + 1));
			test_case.setTransitionSet(TestcaseOfSide.get(i));
			ArrayList<State> StateSet = new ArrayList<State>();
			State s = TestcaseOfSide.get(i).get(0).getSourceState();
			// findStateFromString((minTestcaseOfSide.get(i).get(0).getSource()),auto);
			StateSet.add(s);
			for (Transition tran : TestcaseOfSide.get(i)) {
				State state = tran.getTargetState();
				// findStateFromString((tran.getTarget()),auto);
				StateSet.add(state);
			}
			test_case.setStateSet(StateSet);
			test_case.setInitState(auto.getInitState());
			testcaseSet.add(test_case);
		}
		return testcaseSet;
	}
	/**
	 * ��Ҫ�ĵ��÷�������MinTestcaseOfSide������Ǩ�Ƹ��ǵ���С��Ǩ�Ƽ���ת��Ϊ������������
	 * 
	 * @param auto
	 *            �����ʱ���Զ�������ֹ״̬�Ѿ���ȷ���
	 * @return
	 */
	public static ArrayList<Automatic> MinsideCoverage(ArrayList<ArrayList<Transition>> ss, Automatic auto) {
		// ArrayList<ArrayList<Transition>> ss = MinSSOfFirstForTC(auto);
		ArrayList<ArrayList<Transition>> minTestcaseOfSide = MinTestcaseOfSide(ss, auto);
		int n = minTestcaseOfSide.size();// ������������
		ArrayList<Automatic> testcaseSet = new ArrayList<Automatic>();// ������������
		for (int i = 0; i < n; i++) {
			Automatic test_case = new Automatic();
			test_case.setClockSet(auto.getClockSet());
			test_case.setName("��������" + (i + 1));
			test_case.setTransitionSet(minTestcaseOfSide.get(i));
			ArrayList<State> StateSet = new ArrayList<State>();
			State s = minTestcaseOfSide.get(i).get(0).getSourceState();
			// findStateFromString((minTestcaseOfSide.get(i).get(0).getSource()),auto);
			StateSet.add(s);
			for (Transition tran : minTestcaseOfSide.get(i)) {
				State state = tran.getTargetState();
				// findStateFromString((tran.getTarget()),auto);
				StateSet.add(state);
			}
			test_case.setStateSet(StateSet);
			test_case.setInitState(auto.getInitState());
			testcaseSet.add(test_case);
		}
		return testcaseSet;
	}
	
	public static ArrayList<ArrayList<Transition>> MinSSOfFirstForTC(Automatic auto) {
		// ��һ���㷨
		ArrayList<ArrayList<Transition>> ss = minSS(auto);
		return ss;
	}

	public static ArrayList<ArrayList<Transition>> TestcaseOfSide(ArrayList<ArrayList<Transition>> ss, Automatic auto) {
		ArrayList<ArrayList<Transition>> propath = new ArrayList<ArrayList<Transition>>();
		// Ϊ������Ƭ�μ���ǰ��
		for (ArrayList<Transition> transet : ss) {
			if (transet.get(0).getSource().equals(auto.getInitState().getName())) {
				propath.add(transet);
				/*
				 * System.out.println("---��Ƭ���Ǵӳ�ʼ״̬��ʼ,��·��Ϊ---"); for (Transition
				 * t : transet) { System.out.print(t.getName() + "--  "); }
				 * System.out.println();
				 */
			} else {
				// System.out.println("��ǰ��ǰ����Ǩ��------ " + transet.get(0));
				ArrayList<Transition> shortPath = oneOfPathforStartToOne(auto, transet.get(0));
				// System.out.println("--- " + shortPath);
				for (Transition tran : transet) {
					shortPath.add(tran);
				}
				propath.add(shortPath);
				/*
				 * System.out.println("---����ǰ����·��--- "); for (Transition t :
				 * shortPath) { System.out.print(t.getName() + "--  "); }
				 * System.out.println();
				 */
			}
			// System.out.println("------------------------------");
		}

		// System.out.println();
		// System.out.println("-------------------��ǰ����·��-------------------");
		// System.out.println("********************************************");
		// for (ArrayList<Transition> tset : propath) {
		// for (Transition t : tset) {
		// System.out.print(t.getName() + "-- ");
		// }
		// System.out.println();
		// }
		// System.out.println("********************************************");

		// �ҵ�Ǩ��Ƭ�ε���ֹ�ڵ�����·��
		for (ArrayList<Transition> transet : propath) {
			// System.out.println(" ----------------------------------------");
			// System.out.println(auto.getInitState());
			// System.out.println("====Ŀ��״̬====" + transet.get(transet.size() -
			// 1).getTargetState());
			// System.out.println(" ----------------------------------------");
			// System.out.println("---------***** " + transet.get(transet.size()
			// - 1));
			// System.out.println("---------***** " + transet.get(transet.size()
			// - 1).getTargetState());
			if (!(transet.get(transet.size() - 1).getTargetState().isFinalState())) {
				// System.out.println("====��Ҫ�Ӻ���Ƭ��====");
				ArrayList<Transition> lastPath = oneOfPathforOneToEnd(auto, transet.get(transet.size() - 1));
				// System.out.println("����Ƭ��-- " + lastPath);
				for (Transition tran : lastPath) {
					transet.add(tran);
				}
			}
		}
		// System.out.println();
		// System.out.println("--------------------����·��-------------------");
		// System.out.println("********************************************");
		// for (ArrayList<Transition> tset : propath) {
		// for (Transition t : tset) {
		// System.out.print(t.getName() + "-- ");
		// }
		// System.out.println();
		// }
		// System.out.println("********************************************");
		// System.out.println();
		return propath;
	}
	/**
	 * ��Ҫ������������ʱ���Զ���������Ǩ�Ƹ��ǵ���С�Ĳ�������
	 * 
	 * @param auto
	 *            �����ʱ���Զ�������ֹ״̬�Ѿ���ȷ���
	 * @return
	 */
	public static ArrayList<ArrayList<Transition>> MinTestcaseOfSide(ArrayList<ArrayList<Transition>> ss,Automatic auto) {

		/*
		 * System.out.println("-------------------�㷨ǰ����Ǩ��-------------------");
		 * System.out.println("********************************************");
		 * System.out.println("�㷨ǰ��ʼ״̬--  " + auto.getInitState().getName());
		 * System.out.println("�㷨ǰ״̬������  " + auto.getStateSet().size());
		 * System.out.println("�㷨ǰǨ�Ƹ�����  " + auto.getTransitionSet().size());
		 * System.out.println("�㷨ǰǨ��--  " + auto.getTransitionSet());
		 * System.out.println();
		 * 
		 * for (Transition tran : auto.getTransitionSet()) {
		 * 
		 * if (tran.getSource().equals(auto.getInitState().getName())) {
		 * System.out.println(
		 * "*************************************************");
		 * System.out.println(" ��ʼǨ��-- " + tran); System.out.println(
		 * "*************************************************");
		 * System.out.println(); } State state = tran.getTargetState(); //
		 * findStateFromString(tran.getTarget(), auto);
		 * 
		 * if(state.isFinalState()){ System.out.println(); System.out.println(
		 * "========================================================");
		 * System.out.println(" ��ֹ״̬-- " + state); System.out.
		 * println("              --------------------------------");
		 * System.out.println(" ��ֹǨ��-- " + tran); System.out.println(
		 * "========================================================"); }
		 * 
		 * } System.out.println(); System.out.println("�㷨ǰ���г�ʼ״̬--  " +
		 * auto.getInitState()); System.out.println("�㷨ǰ����Ǩ��--  ");
		 * 
		 * for (Transition t : auto.getTransitionSet()) {
		 * System.out.print(t.getName() + "-- "); } System.out.println();
		 * System.out.println("********************************************");
		 * 
		 * // ��һ���㷨 ArrayList<ArrayList<Transition>> ss = minSS(auto);
		 * 
		 * System.out.println();
		 * System.out.println("-------------------1���õ���Ǩ��Ƭ��-------------------")
		 * ; System.out.println("********************************************");
		 * for (ArrayList<Transition> tset : ss) { for (Transition t : tset) {
		 * System.out.print(t.getName() + "-- "); } System.out.println(); }
		 * System.out.println("********************************************");
		 */

		// System.out.println("-----------------------------------------------");
		// System.out.println();
		// System.out.println("������1���㷨�õ���Ǩ��Ƭ�θ��� --- " + ss.size());

		// �ڶ����㷨
		// ArrayList<ArrayList<Transition>> lastminSS = lastMinSS(ss, auto);
		ArrayList<ArrayList<Transition>> lastminSS = lastMinSS1(ss, auto);

		// System.out.println();
		// System.out.println("-------------------2��Լ��Ǩ��Ƭ��-------------------");
		// System.out.println("********************************************");
		// for (ArrayList<Transition> tset : lastminSS) {
		// for (Transition t : tset) {
		// System.out.print(t.getName() + "-- ");
		// }
		// System.out.println();
		// }
		// System.out.println("********************************************");

		// System.out.println("������2���㷨�õ���Ǩ��Ƭ�θ��� --- " + lastminSS.size());
		// System.out.println("-----------------------------------------------");
		// System.out.println();

		ArrayList<ArrayList<Transition>> propath = new ArrayList<ArrayList<Transition>>();

		/*
		 * //��������shortPathforStartToOne�㷨 System.out.println("2���㷨���Ǩ��Ƭ��--  " +
		 * lastminSS.get(3)); //System.out.println("transet.get(0)--  " +
		 * transet.get(0));
		 * if(lastminSS.get(3).get(0).getSource().equals(auto.getInitState().
		 * getName())){ propath.add(lastminSS.get(3));
		 * System.out.println("---��Ƭ���Ǵӳ�ʼ״̬��ʼ---");
		 * System.out.println(lastminSS.get(3)); for(Transition t :
		 * lastminSS.get(3)){ System.out.print(t.getName() + "--  "); }
		 * System.out.println(); }else{ ArrayList<Transition> shortPath =
		 * shortPathforStartToOne(auto, lastminSS.get(3).get(0));
		 * System.out.println("2���㷨�����ǰ����Ǩ��Ƭ��  " + shortPath); for(Transition
		 * tran : lastminSS.get(3)){ shortPath.add(tran); }
		 * propath.add(shortPath); System.out.println();
		 * System.out.println(shortPath); for(Transition t : shortPath){
		 * System.out.print(t.getName() + "--  "); } System.out.println(); }
		 */

		// Ϊ������Ƭ�μ���ǰ��
		for (ArrayList<Transition> transet : lastminSS) {

			// System.out.println();
			// System.out.println("------------------------------");
			// System.out.println("2���㷨���Ǩ��Ƭ��-- " + transet);
			if (transet.get(0).getSource().equals(auto.getInitState().getName())) {
				propath.add(transet);
				// System.out.println("---��Ƭ���Ǵӳ�ʼ״̬��ʼ,��·��Ϊ---");
				// System.out.println(transet);
				// for (Transition t : transet) {
				// System.out.print(t.getName() + "-- ");
				// }
				// System.out.println();
			} else {
				// System.out.println("��ǰ��ǰ����Ǩ��------ " + transet.get(0));
				// ArrayList<Transition> shortPath =
				// shortPathforStartToOne(auto, transet.get(0));
				// ArrayList<Transition> shortPath = addProPath(auto,
				// transet.get(0));//----------------------------------
				ArrayList<Transition> shortPath = oneOfPathforStartToOne(auto, transet.get(0));////////////////////////////////////////////////////////////
				// System.out.println("--- " + shortPath);

				// System.out.println("2���㷨�����ǰ����Ǩ��Ƭ�� " + shortPath);
				for (Transition tran : transet) {
					shortPath.add(tran);
				}
				propath.add(shortPath);
				// System.out.println("---����ǰ����·��--- ");
//				for (Transition t : shortPath) {
//					System.out.print(t.getName() + "--  ");
//				}
				// System.out.println();
			}
			// System.out.println("------------------------------");
		}

		// System.out.println();
		// System.out.println("-------------------��ǰ����·��-------------------");
		// System.out.println("********************************************");
		// for (ArrayList<Transition> tset : propath) {
		// for (Transition t : tset) {
		// System.out.print(t.getName() + "-- ");
		// }
		// System.out.println();
		// }
		// System.out.println("********************************************");

		// �ҵ�Ǩ��Ƭ�ε���ֹ�ڵ�����·��
		for (ArrayList<Transition> transet : propath) {
			// System.out.println(" ----------------------------------------");
			// System.out.println(auto.getInitState());
			// System.out.println("====Ŀ��״̬====" + transet.get(transet.size() -
			// 1).getTargetState());
			// System.out.println(" ----------------------------------------");
			// System.out.println("---------***** " + transet.get(transet.size()
			// - 1));
			// System.out.println("---------***** " + transet.get(transet.size()
			// - 1).getTargetState());
			if (!(transet.get(transet.size() - 1).getTargetState().isFinalState())) {
				// System.out.println("====��Ҫ�Ӻ���Ƭ��====");
				// if(!(findStateFromString(transet.get(transet.size()-1).getTarget(),
				// auto).isFinalState())){
				// ArrayList<Transition> lastPath = shortPathforOneToEnd(auto,
				// transet.get(transet.size()-1));//----------------------------------
				// ArrayList<Transition> lastPath = addPostPath(auto,
				// transet.get(transet.size()-1));
				ArrayList<Transition> lastPath = oneOfPathforOneToEnd(auto, transet.get(transet.size() - 1));
				// System.out.println("����Ƭ��-- " + lastPath);
				for (Transition tran : lastPath) {
					transet.add(tran);
				}
			}
		}
		// System.out.println();
		// System.out.println("--------------------����·��-------------------");
		// System.out.println("********************************************");
		// for (ArrayList<Transition> tset : propath) {
		// for (Transition t : tset) {
		// System.out.print(t.getName() + "-- ");
		// }
		// System.out.println();
		// }
		// System.out.println("********************************************");
		// System.out.println();
		return propath;

	}

	/**
	 * �Ż�Ǩ�Ƹ����㷨
	 * 
	 * @param auto
	 * @return
	 */
	public static ArrayList<ArrayList<Transition>> minSS(Automatic auto) {
		ArrayList<ArrayList<Transition>> SS = new ArrayList<ArrayList<Transition>>();
		ArrayList<Transition> ss = new ArrayList<Transition>();
		ArrayList<Transition> TS = new ArrayList<Transition>();
		ArrayList<Transition> T = new ArrayList<Transition>();
		// ������Ǩ�ƴ����Ǩ�Ƽ���T��
		for (Transition tran1 : auto.getTransitionSet()) {
			T.add(tran1);
		}

		while (T.size() > 0) {
			//System.out.println("T.size-- " + T.size());
			// //System.out.println("1--t
			// "+T.toString()+"******************************************");
			// //System.out.println("1--ts " + TS
			// +"**************************************");
			for (int i = 0; i < T.size(); i++) {// ��Ǩ�Ƽ���T��ѡȡһ����Ǩ��t
				//System.out.println("i----  " + i);
				Transition tran = T.get(i);
				// //System.out.println("tran-- " +tran + "************");
				// //System.out.println("2--tran " + tran + "************");
				// //System.out.println("2--tǰ
				// "+T.toString()+"******************************************");
				// //System.out.println("2--tsǰ " + TS
				// +"**************************************");
				TS.add(tran); // ��ѡȡ��Ǩ��t����TS�У�����Ǩ�Ƽ���T��ɾ����Ǩ��t
				T.remove(tran);
				--i;
				// //System.out.println("2--t
				// "+T.toString()+"******************************************");
				// //System.out.println("2--ts
				// "+TS.toString()+"******************************************");
				// //System.out.println(TS.size()-1);
				// //System.out.println(TS.get(0));
				for (int j = 0; j < T.size(); j++) {
					Transition transition = T.get(j);
					Transition tslast = TS.get(TS.size() - 1); // �ж�TS�����һ��Ǩ���Ƿ��к��Ǩ��
					if (tslast.getTarget().equals(transition.getSource())) {// transition��TS�����һ��Ǩ�Ƶĺ��Ǩ�ƣ��Ѹ�Ǩ�Ƽ���TS�У�����T��ɾ��
						TS.add(transition);
						T.remove(transition);
						--j;
					}
				}
				// �ж�SS�е�ÿ��Ǩ��Ƭ���Ƿ���TS�е�Ǩ��Ƭ�������һ��
				if (SS.size() > 0) {
					for (int k = 0; k < SS.size(); k++) {
						ArrayList<Transition> s = SS.get(k);
						String first = TS.get(0).getSource();// TS������Ǩ��Ƭ�εĵ�һ��Ǩ�Ƶ�Դ״̬
						String last = TS.get(TS.size() - 1).getTarget();// TS������Ǩ��Ƭ�ε����һ��Ǩ�Ƶ�Ŀ��״̬
						// s�����һ��Ǩ�Ƶ�Ŀ��״̬����TS�е�һ��Ǩ�Ƶ�Դ״̬ʱ��s�����ӵ�TS��ͷ��
						if (s.get(s.size() - 1).getTarget().equals(first)) {
							ArrayList<Transition> ts = new ArrayList<Transition>();
							for (Transition tr : s) {
								ts.add(tr);
							}
							SS.remove(s);
							--k;
							for (Transition tr : TS) {
								ts.add(tr);
							}
							TS = new ArrayList<Transition>();
							for (Transition tr : ts) {
								TS.add(tr);
							}
						}
						// s�ĵ�һ��Ǩ�Ƶ�Դ״̬����TS�����һ��Ǩ�Ƶ�Ŀ��״̬ʱ��s�����ӵ�TS��β��
						if (s.get(0).getSource().equals(last)) {
							for (Transition tr : s) {
								TS.add(tr);
							}
							SS.remove(s);
							--k;
						}
					}
				}
				// ����Ǹ������TS����SS��ȥ�������TS
				SS.add(TS);
				TS = new ArrayList<Transition>();

			}
		}
		// for (ArrayList<Transition> s : SS) {
		// System.out.println("***********");
		// for (Transition t : s) {
		// // System.out.print(t.getId()+" --- ");
		// System.out.print(t.getName() + " -- ");
		// }
		// System.out.println("***********");
		// }
		return SS;
	}

	/**
	 * ��С���Գɱ�Ǩ�Ƹ����㷨
	 * 
	 * @param SS
	 *            �Ż�Ǩ�Ƹ����㷨���������SS
	 * @return ����M����С���Գɱ�Ǩ�Ƹ��ǵ����м���SS1
	 */
	public static ArrayList<ArrayList<Transition>> lastMinSS(ArrayList<ArrayList<Transition>> SS, Automatic auto) {
		ArrayList<ArrayList<Transition>> SS1 = new ArrayList<ArrayList<Transition>>();
		while (SS.size() > 0 && SS != null) {
			//System.out.println();
			//System.out.println("************************");
			//System.out.println();
			ArrayList<Transition> TSk = new ArrayList<Transition>();
			TSk = SS.get(0);
			SS.remove(TSk);
			if (SS1.size() == 0) {
				SS1.add(TSk);
			} else {
				int f = 0;
				if (TSk.size() == 1) {// TSk��ֻ��һ��Ǩ��
					for (int i = 0; i < SS1.size(); i++) {
						ArrayList<Transition> TSi = SS1.get(i);
						for (Transition t : TSi) {
							if (t.equals(TSk.get(0))) {
								f = 1; // ��TSk��ֻ��һ��Ǩ�ƣ�����SS1���ҵ���������SS1�����ж�
								//System.out.println("TSk��ֻ��һ��Ǩ�ƣ�����SS1���ҵ�");
							}
						}
					}
				}
				if ((f == 0) || (TSk.size() > 1)) {
					//System.out.println(" SS1.size--  " + SS1.size() + "--------------------------------------------------------");
					ArrayList<Transition> TS = new ArrayList<Transition>();// ���TSK��TSi�����ӵĽ��

					// ��TSK��SS1������Ƭ�ν������
					int finish = 0;
					for (int i = 0; i < SS1.size(); i++) {
						ArrayList<Transition> TSi = SS1.get(i);
						// //System.out.println(SS1.size());
						//System.out.println(" TSk--  " + TSk);
						//System.out.println(" TSi--  " + TSi);
						//System.out.println("SS1.get(i)--  " + i);
						// TSk��TSi�Ƿ��ཻ
						int cross = 0;
						// if(TSk.get(0).getSource().equals(anObject))
						for (Transition t1 : TSk) {
							for (Transition t2 : TSi) {
								if (t1.getSource().equals(t2.getSource()) || t1.getTarget().equals(t2.getSource())
										|| t1.getSource().equals(t2.getTarget())
										|| t1.getTarget().equals(t2.getTarget())) {
									cross = 1; // �ཻ
								}
							}
						}
						// if((TSi.get(0).getSource().equals(TSk.get(TSk.size()-1).getTarget()))
						// ||
						// (TSi.get(TSi.size()-1).getTarget().equals(TSk.get(0).getSource()))){
						// TSk��TSi�ཻ
						if (cross == 1) { // �ཻ
							//System.out.println("TSk �� TSi �ཻ  *************************");

							if (TSi.get(0).getSource().equals(TSk.get(TSk.size() - 1).getTarget())) {// TSk������TSi
								//System.out.println("TSk������TSi  *************************");
								ArrayList<Transition> TS1 = new ArrayList<Transition>();
								for (Transition tran : TSk) {
									TS1.add(tran);
								}
								for (Transition tran : TSi) {
									TS1.add(tran);
								}
								SS1.remove(TSi);
								--i;
								TS = TS1;
								finish = 1;
								// SS1.add(TS1);
							} else {
								if (TSi.get(TSi.size() - 1).getTarget().equals(TSk.get(0).getSource())) {// TSi������TSk
									//System.out.println("TSi������TSk  *************************");
									ArrayList<Transition> TS1 = new ArrayList<Transition>();
									for (Transition tran : TSi) {
										TS1.add(tran);
									}
									for (Transition tran : TSk) {
										TS1.add(tran);
									}
									SS1.remove(TSi);
									--i;
									TS = TS1;
									finish = 1;
									// SS1.add(TS1);
								} else {
									if ((TSk.get(0).getSource().equals(TSk.get(TSk.size() - 1).getTarget()))
											|| (TSi.get(0).getSource().equals(TSi.get(TSi.size() - 1).getTarget()))) {// TSk����TSi�ǻ�·
										//System.out.println("TSk����TSi�ǻ�·  *************************");
										ArrayList<Transition> TSk1 = new ArrayList<Transition>();
										ArrayList<Transition> TSk2 = new ArrayList<Transition>();
										ArrayList<Transition> TSi1 = new ArrayList<Transition>();
										ArrayList<Transition> TSi2 = new ArrayList<Transition>();
										ArrayList<Transition> TSKCache = new ArrayList<Transition>();
										ArrayList<Transition> TSiCache = new ArrayList<Transition>();
										for (Transition tran : TSk) {
											int j = 0;
											for (Transition tran1 : TSi) {
												if (tran.getTarget().equals(tran1.getSource())) {// �ҵ��ཻ���
													for (Transition trann : TSk) {
														TSKCache.add(trann);
													}
													for (Transition trann : TSi) {
														TSiCache.add(trann);
													}
													for (Transition trank : TSk) {
														if (!(trank.getSource().equals(tran.getTarget()))) {
															TSk1.add(trank);
															TSKCache.remove(trank);
														} else {
															TSk2 = TSKCache;
														}
													}
													for (Transition trani : TSi) {
														if (!(trani.getSource().equals(tran1.getSource()))) {
															TSi1.add(trani);
															TSiCache.remove(trani);
														} else {
															TSi2 = TSiCache;
														}
													}
													j = 1;
													break;
												}
											}
											if (j == 1) {
												break;
											}
										}
										if ((TSi.get(0).getSource().equals(TSi.get(TSi.size() - 1).getTarget()))) {// TSi���ڻ�·
											ArrayList<Transition> TSii = new ArrayList<Transition>();
											for (Transition trann : TSk1) {
												TSii.add(trann);
											}
											for (Transition trann : TSi2) {
												TSii.add(trann);
											}
											for (Transition trann : TSi1) {
												TSii.add(trann);
											}
											for (Transition trann : TSk2) {
												TSii.add(trann);
											}
											TS = TSii;
											finish = 1;
											// SS1.add(TSii);
										} else {
											if (TSk.get(0).getSource().equals(TSk.get(TSk.size() - 1).getTarget())) {// TSi���ڻ�·
												ArrayList<Transition> TSii = new ArrayList<Transition>();
												for (Transition trann : TSi1) {
													TSii.add(trann);
												}
												for (Transition trann : TSk2) {
													TSii.add(trann);
												}
												for (Transition trann : TSk1) {
													TSii.add(trann);
												}
												for (Transition trann : TSi2) {
													TSii.add(trann);
												}
												TS = TSii;
												finish = 1;
												// SS1.add(TSii);
											}
										}
									} else {// ���ǻ�·
										//System.out.println("TSk����TSi���ǻ�·  *************************");
										TS = TSk;
										// SS1.add(TSk);
									}
								}
							}
						} else {
							// ���TSk��TSi���뽻����ô��TSK�ϲ���SS1��
							//System.out.println("TSk��TSi���ཻ  *************************");
							// if(!(TSi.get(0).getSource().equals(TSk.get(TSk.size()-1).getTarget()))
							// //TSk��TSi���ཻ
							// &&
							// !(TSi.get(TSi.size()-1).getTarget().equals(TSk.get(0).getSource()))){
							if (cross == 0) {
								// ���TSK��TSi���ཻ�����ж��Ƿ����һ��Ǩ���ܰ�����Ǩ��������һ��
								int flag = 0;
								for (Transition tran1 : auto.getTransitionSet()) {
									// TSK + һ��Ǩ�� + TSi
									if ((tran1.getSource().equals(TSk.get(TSk.size() - 1).getTarget()))
											&& (tran1.getTarget().equals(TSi.get(0).getSource()))) {
										//System.out.println("TSK + һ��Ǩ��  + TSi  *************************");
										ArrayList<Transition> TS1 = new ArrayList<Transition>();
										for (Transition tran : TSk) {
											TS1.add(tran);
										}
										TS1.add(tran1);
										for (Transition tran : TSi) {
											TS1.add(tran);
										}
										SS1.remove(TSi);
										--i;
										TS = TS1;
										// SS1.add(TS1);
										flag = 1;
										finish = 1;
										break;
									} else {
										// TSi + һ��Ǩ�� + TSk
										if ((tran1.getSource().equals(TSi.get(TSi.size() - 1).getTarget()))
												&& (tran1.getTarget().equals(TSk.get(0).getSource()))) {
											ArrayList<Transition> TS1 = new ArrayList<Transition>();
											//System.out.println("TSi + һ��Ǩ��  + TSk  *************************");
											for (Transition tran : TSi) {
												TS1.add(tran);
											}
											TS1.add(tran1);
											for (Transition tran : TSk) {
												TS1.add(tran);
											}
											SS1.remove(TSi);
											--i;
											TS = TS1;
											// SS1.add(TS1);
											flag = 1;
											finish = 1;
											break;
										}
									}
								}
								if (flag == 0) {
									//System.out.println("TSi �� TSk���ཻ  �������޷���һ��Ǩ������  *************************");
									// //System.out.println("ss1-- " + SS1);
									// //System.out.println("tsk-- " + TSk);
									TS = TSk;
									// SS1.add(TSk);
								}
							}
						}
						if (finish == 1) {
							break;
						}
					}
					SS1.add(TS);
				}

			}
		}
		return SS1;
	}

	/**
	 * ��С���Գɱ�Ǩ�Ƹ����㷨
	 * 
	 * @param SS
	 *            �Ż�Ǩ�Ƹ����㷨���������SS
	 * @return ����M����С���Գɱ�Ǩ�Ƹ��ǵ����м���SS1
	 */
	public static ArrayList<ArrayList<Transition>> lastMinSS1(ArrayList<ArrayList<Transition>> SS, Automatic auto) {
		ArrayList<ArrayList<Transition>> SS1 = new ArrayList<ArrayList<Transition>>();
		while (SS.size() > 0 && SS != null) {
//			//System.out.println();
//			//System.out.println("************************");
//			//System.out.println();
			ArrayList<Transition> TSk = new ArrayList<Transition>();
			TSk = SS.get(0);
			SS.remove(TSk);
			if (SS1.size() == 0) {
				SS1.add(TSk);
			} else {
				int f = 0;
				if (TSk.size() == 1) {// TSk��ֻ��һ��Ǩ��
					for (int i = 0; i < SS1.size(); i++) {
						ArrayList<Transition> TSi = SS1.get(i);
						for (Transition t : TSi) {
							if (t.equals(TSk.get(0))) {
								f = 1; // ��TSk��ֻ��һ��Ǩ�ƣ�����SS1���ҵ���������SS1�����ж�
//								//System.out.println("TSk��ֻ��һ��Ǩ�ƣ�����SS1���ҵ�");
							}
						}
					}
				}
				if ((f == 0) || (TSk.size() > 1)) {
//					//System.out.println(
//							" SS1.size--  " + SS1.size() + "--------------------------------------------------------");
					ArrayList<Transition> TS = new ArrayList<Transition>();// ���TSK��TSi�����ӵĽ��

					// ��TSK��SS1������Ƭ�ν������
					int finish = 0;
					for (int i = 0; i < SS1.size(); i++) {
						ArrayList<Transition> TSi = SS1.get(i);
						// //System.out.println(SS1.size());
//						//System.out.println(" TSk--  " + TSk);
//						//System.out.println(" TSi--  " + TSi);
//						//System.out.println("SS1.get(i)--  " + i);
						// TSk��TSi�Ƿ��ཻ
						int cross = 0;
						// if(TSk.get(0).getSource().equals(anObject))
						for (Transition t1 : TSk) {
							for (Transition t2 : TSi) {
								if (t1.getSource().equals(t2.getSource()) || t1.getTarget().equals(t2.getSource())
										|| t1.getSource().equals(t2.getTarget())
										|| t1.getTarget().equals(t2.getTarget())) {
									cross = 1; // �ཻ
								}
							}
						}
						// if((TSi.get(0).getSource().equals(TSk.get(TSk.size()-1).getTarget()))
						// ||
						// (TSi.get(TSi.size()-1).getTarget().equals(TSk.get(0).getSource()))){
						// TSk��TSi�ཻ
						if (cross == 1) { // �ཻ
//							//System.out.println("TSk �� TSi �ཻ  *************************");

							if (TSi.get(0).getSource().equals(TSk.get(TSk.size() - 1).getTarget())) {// TSk������TSi
//								//System.out.println("TSk������TSi  *************************");
								ArrayList<Transition> TS1 = new ArrayList<Transition>();
								for (Transition tran : TSk) {
									TS1.add(tran);
								}
								for (Transition tran : TSi) {
									TS1.add(tran);
								}
								SS1.remove(TSi);
								--i;
								TS = TS1;
								finish = 1;
								break;
								// SS1.add(TS1);
							} else {
								if (TSi.get(TSi.size() - 1).getTarget().equals(TSk.get(0).getSource())) {// TSi������TSk
//									//System.out.println("TSi������TSk  *************************");
									ArrayList<Transition> TS1 = new ArrayList<Transition>();
									for (Transition tran : TSi) {
										TS1.add(tran);
									}
									for (Transition tran : TSk) {
										TS1.add(tran);
									}
									SS1.remove(TSi);
									--i;
									TS = TS1;
									finish = 1;
									break;
									// SS1.add(TS1);
								} else {
									if ((TSk.get(0).getSource().equals(TSk.get(TSk.size() - 1).getTarget()))
											|| (TSi.get(0).getSource().equals(TSi.get(TSi.size() - 1).getTarget()))) {// TSk����TSi�ǻ�·
//										//System.out.println("TSk����TSi�ǻ�·  *************************");
										ArrayList<Transition> TSk1 = new ArrayList<Transition>();
										ArrayList<Transition> TSk2 = new ArrayList<Transition>();
										ArrayList<Transition> TSi1 = new ArrayList<Transition>();
										ArrayList<Transition> TSi2 = new ArrayList<Transition>();
										ArrayList<Transition> TSKCache = new ArrayList<Transition>();
										ArrayList<Transition> TSiCache = new ArrayList<Transition>();
										for (Transition tran : TSk) {
											int j = 0;
											for (Transition tran1 : TSi) {
												if (tran.getTarget().equals(tran1.getSource())) {// �ҵ��ཻ���
													for (Transition trann : TSk) {
														TSKCache.add(trann);
													}
													for (Transition trann : TSi) {
														TSiCache.add(trann);
													}
													for (Transition trank : TSk) {
														if (!(trank.getSource().equals(tran.getTarget()))) {
															TSk1.add(trank);
															TSKCache.remove(trank);
														} else {
															TSk2 = TSKCache;
														}
													}
													for (Transition trani : TSi) {
														if (!(trani.getSource().equals(tran1.getSource()))) {
															TSi1.add(trani);
															TSiCache.remove(trani);
														} else {
															TSi2 = TSiCache;
														}
													}
													j = 1;
													break;
												}
											}
											if (j == 1) {
												break;
											}
										}
										if ((TSi.get(0).getSource().equals(TSi.get(TSi.size() - 1).getTarget()))) {// TSi���ڻ�·
											ArrayList<Transition> TSii = new ArrayList<Transition>();
											for (Transition trann : TSk1) {
												TSii.add(trann);
											}
											for (Transition trann : TSi2) {
												TSii.add(trann);
											}
											for (Transition trann : TSi1) {
												TSii.add(trann);
											}
											for (Transition trann : TSk2) {
												TSii.add(trann);
											}
											TS = TSii;
											finish = 1;
											break;
											// SS1.add(TSii);
										} else {
											if (TSk.get(0).getSource().equals(TSk.get(TSk.size() - 1).getTarget())) {// TSi���ڻ�·
												ArrayList<Transition> TSii = new ArrayList<Transition>();
												for (Transition trann : TSi1) {
													TSii.add(trann);
												}
												for (Transition trann : TSk2) {
													TSii.add(trann);
												}
												for (Transition trann : TSk1) {
													TSii.add(trann);
												}
												for (Transition trann : TSi2) {
													TSii.add(trann);
												}
												TS = TSii;
												finish = 1;
												break;
												// SS1.add(TSii);
											}
										}
									} else {// ���ǻ�·
//										//System.out.println("TSk����TSi���ǻ�·  *************************");
										TS = TSk;
										// SS1.add(TSk);
									}
								}
							}
						} /*
							 * else{
							 * 
							 * }
							 */
						if (finish == 1) {
							break;
						}
					}
					if (finish == 0) {
						for (int i = 0; i < SS1.size(); i++) {
							ArrayList<Transition> TSi = SS1.get(i);

							// ���TSk��TSi���뽻����ô��TSK�ϲ���SS1��
//							//System.out.println("TSk��TSi���ཻ  *************************");
							// if(!(TSi.get(0).getSource().equals(TSk.get(TSk.size()-1).getTarget()))
							// //TSk��TSi���ཻ
							// &&
							// !(TSi.get(TSi.size()-1).getTarget().equals(TSk.get(0).getSource()))){
							// ���TSK��TSi���ཻ�����ж��Ƿ����һ��Ǩ���ܰ�����Ǩ��������һ��
							int flag = 0;
							for (Transition tran1 : auto.getTransitionSet()) {
								// TSK + һ��Ǩ�� + TSi
								if ((tran1.getSource().equals(TSk.get(TSk.size() - 1).getTarget()))
										&& (tran1.getTarget().equals(TSi.get(0).getSource()))) {
//									//System.out.println("TSK + һ��Ǩ��  + TSi  *************************");
									ArrayList<Transition> TS1 = new ArrayList<Transition>();
									for (Transition tran : TSk) {
										TS1.add(tran);
									}
									TS1.add(tran1);
									for (Transition tran : TSi) {
										TS1.add(tran);
									}
									SS1.remove(TSi);
									--i;
									TS = TS1;
//									//System.out.println("------  " + TS);
									// SS1.add(TS1);
									flag = 1;
									finish = 1;
									break;
								} else {
									// TSi + һ��Ǩ�� + TSk
									if ((tran1.getSource().equals(TSi.get(TSi.size() - 1).getTarget()))
											&& (tran1.getTarget().equals(TSk.get(0).getSource()))) {
										ArrayList<Transition> TS1 = new ArrayList<Transition>();
//										//System.out.println("TSi + һ��Ǩ��  + TSk  *************************");
										for (Transition tran : TSi) {
											TS1.add(tran);
										}
										TS1.add(tran1);
										for (Transition tran : TSk) {
											TS1.add(tran);
										}
										SS1.remove(TSi);
										--i;
										TS = TS1;
										// SS1.add(TS1);
										flag = 1;
										finish = 1;
										break;
									}
								}
							}
							if (flag == 0) {
//								//System.out.println("TSi �� TSk���ཻ  �������޷���һ��Ǩ������  *************************");
								// //System.out.println("ss1-- " + SS1);
								// //System.out.println("tsk-- " + TSk);
								TS = TSk;
								// SS1.add(TSk);
							}else{
								break;
							}
						}
					}

					/*//System.out.println("-----TS--- ");
					//System.out.println("    " + TS);
					for(Transition t : TS){
						//System.out.println(" " + t.getName() + " -- ");
					}*/
					SS1.add(TS);
				}

			}
		}
		return SS1;
	}

	/**
	 * ���ù�����ȱ�����˼���ҵ��ӿ�ʼ�ڵ㵽���Ǩ��Ƭ����ͷ�������·��
	 * 
	 * @param auto
	 * @param goalTransition
	 * @return
	 */
	public static ArrayList<Transition> shortPathforStartToOne(Automatic auto, Transition goalTransition) {
		if (goalTransition.getSourceState().equals(auto.getInitState())) {
			return null;
		} else {
			// //System.out.println("Ŀ��Ǩ��-- " + goalTransition);
			Queue<State> queue = new LinkedList<State>();
			ArrayList<Transition> path = new ArrayList<Transition>();
			ArrayList<Transition> path1 = new ArrayList<Transition>();
			ArrayList<ArrayList<Transition>> pathset = new ArrayList<ArrayList<Transition>>();
			State initial = auto.getInitState();
			State u = new State();
			queue.add(initial);
			while (!queue.isEmpty()) {
				// //System.out.println();
				// //System.out.println("-----------------------");
				//System.out.println("�������ֵ-- " + queue);
				int finish = 0;
				u = queue.poll();
				// //System.out.println("�������ֵ-- " + queue);
				// //System.out.println("���е�����״̬u����-- " + u.getName() + " -- " +
				// u.getId());
				if (u != null && !(u.isFinalState())) {
					//System.out.println("******* " + u);
					//System.out.println("----" + u.getNextTranSet());
					ArrayList<Transition> nextTranSet = removeTran(u, u.getNextTranSet());
					// ArrayList<Transition> nextTranSet = nextTranSet(u, auto);
					// //System.out.println("u״̬�ĺ���Ǩ�Ƽ���-- " + nextTranSet);
					// //System.out.println("��ȱ���Ŀǰ���е�·�����ϴ�С-- " +
					// pathset.size());
					// //System.out.println("-- " + pathset);
					if (pathset.size() == 0) {
						for (Transition tran : nextTranSet) {
							ArrayList<Transition> t = new ArrayList<Transition>();
							t.add(tran);
							pathset.add(t);
						}
					} else {
						int n = pathset.size();
						ArrayList<ArrayList<Transition>> teanset = new ArrayList<ArrayList<Transition>>();// ���Ҫɾ����Ǩ��Ƭ��
						for (int i = 0; i < n; i++) {
							if (pathset.get(i).get(pathset.get(i).size() - 1).getTarget().equals(u.getName())) {// �жϼ��������Ԫ���Ƿ��Ǹճ�ջ��״̬���������е�Ǩ�������ܷ���nextTranset�е�Ǩ��������
								ArrayList<Transition> tean = new ArrayList<Transition>();
								tean = pathset.get(i);
								teanset.add(tean);
								for (Transition tran : nextTranSet) {
									ArrayList<Transition> t = new ArrayList<Transition>();
									for (Transition tr : tean) {
										t.add(tr);
									}
									t.add(tran);
									pathset.add(t);
									if (tran.getTarget().equals(goalTransition.getSource())) {
										path1 = pathset.get(i);
										finish = 1;
										return path1;
										// break;
									}
								}
							}
							if (finish == 1) {
								break;
							}
						}
						// ��pathset��Ŀǰ��ȱ�����·�� ���Ѿ������Ǩ�����ӵ�Ǩ��ɾ��
						for (ArrayList<Transition> t : teanset) {
							pathset.remove(t);
						}
					}
					// //System.out.println("��ȱ���Ŀǰ���е�·�����ϴ�С-- " +
					// pathset.size());
					// //System.out.println("��ȱ������м���-- " + pathset);
					for (Transition tran : nextTranSet) {
						if (tran.getTarget().equals(goalTransition.getSource())) {
							// for(int i=0; i<pathset.size(); i++){
							for (int i = pathset.size() - 1; i >= 0; i--) {
								// ============================================
								if (pathset.get(i).get(pathset.get(i).size() - 1).equals(
										tran) /*
												 * && pathset.get(i).get(0).
												 * getSource().equals(auto.
												 * getInitState().getName())
												 */) {
									path = pathset.get(i);
									/*
									 * //System.out.println("���ص�Ŀ��·��-- " + path);
									 * for(Transition t : path){
									 * //System.out.print(t.getName() + "-- "); }
									 * //System.out.println();
									 */
									return path;
								}
							}
						} else {
							for (State state : auto.getStateSet()) {
								if (state.getName().equals(tran.getTarget())) {
									queue.add(state);
								}
							}

						}
					}
				}
			}
			return path;
		}
	}

	/**
	 * ���ù�����ȱ���˼�룬�ҵ�ĳһ���ڵ㵽�����κ�һ����ֹ�ڵ�����·��
	 * 
	 * @param auto
	 * @param goalTransition
	 * @return
	 */
	public static ArrayList<Transition> shortPathforOneToEnd(Automatic auto, Transition goalTransition) {
		// if(findStateFromString(goalTransition.getTarget(),
		// auto).isFinalState()){
		if (goalTransition.getTargetState().isFinalState()) {
			return null;
		} else {
			Queue<State> queue = new LinkedList<State>();
			ArrayList<Transition> path = new ArrayList<Transition>();
			ArrayList<ArrayList<Transition>> pathset = new ArrayList<ArrayList<Transition>>();
			// State initial = auto.getInitState();
			State start = goalTransition.getTargetState();
			// findStateFromString((goalTransition.getTarget()),auto);
			State u = new State();
			queue.add(start);
			while (!queue.isEmpty()) {
				int finish = 0;
				u = queue.poll();
				if (u != null && !(u.isFinalState())) {
					//System.out.println("�������ڷ���״̬--   " + u);
					//System.out.println("�������ڷ���״̬ǰ��--   " + u.getProTranSet());
					//System.out.println("�������ڷ���״̬����--   " + u.getNextTranSet());
					//System.out.println("�������ڷ���״̬�Ƿ�����ֹ״̬--   " + u.isFinalState());
					ArrayList<Transition> nextTranSet = removeTran(u, u.getNextTranSet());
					//System.out.println("�������ڷ���״̬����--   " + nextTranSet);
					// nextTranSet(u, auto); //��ȡ�Ե�ǰ״̬ΪԴ���Ǩ�Ƽ���
					if (pathset.size() == 0) {
						for (Transition tran : nextTranSet) {
							ArrayList<Transition> t = new ArrayList<Transition>();
							t.add(tran);
							pathset.add(t);
						}
					} else {
						int n = pathset.size();
						ArrayList<ArrayList<Transition>> teanset = new ArrayList<ArrayList<Transition>>();
						for (int i = 0; i < n; i++) {
							if (pathset.get(i).get(pathset.get(i).size() - 1).getTarget().equals(u.getName())) {
								ArrayList<Transition> tean = new ArrayList<Transition>();
								tean = pathset.get(i);
								teanset.add(tean);
								for (Transition tran : nextTranSet) {
									ArrayList<Transition> t = new ArrayList<Transition>();
									for (Transition tr : tean) {
										t.add(tr);
									}
									t.add(tran);
									pathset.add(t);
									if (tran.getTarget().equals(goalTransition.getSource())) {
										finish = 1;
										break;
									}
								}
							}
							if (finish == 1) {
								break;
							}
						}
						for (ArrayList<Transition> t : teanset) {
							pathset.remove(t);
						}
					}
					for (Transition tran : nextTranSet) {
						// if(findStateFromString(tran.getTarget(),auto).isFinalState()){
						if (tran.getTargetState().isFinalState()) {
							for (int i = pathset.size() - 1; i >= 0; i--) {
								if (pathset.get(i).get(pathset.get(i).size() - 1).equals(tran)) {
									path = pathset.get(i);
									return path;
								}
							}
						} else {
							/*
							 * for(State state : auto.getStateSet()){
							 * if(state.getName().equals(tran.getTarget())){
							 * queue.add(state); } }
							 */
							queue.add(tran.getTargetState());
						}
					}
				}
			}
			return path;
		}
	}

	public static ArrayList<Transition> oneOfPathforStartToOne(Automatic auto, Transition goalTransition) {

		//System.out.println("----------------------------------------------");
		String sourceName = goalTransition.getSource();
		String startName = auto.getInitState().getName();
		if (sourceName.equals(startName)) {
			//System.out.println("----------------------------------------------");
			return null;
		} else {
			ArrayList<Transition> path = new ArrayList<Transition>();
			ArrayList<Transition> path1 = new ArrayList<Transition>();
			while (!(sourceName.equals(startName))) {
				int i = 1;
				//System.out.println("�� " + i + " �β���");
				// State state = findStateFromString(sourceName, auto);
				Transition proTran = oneOfproTranSet(sourceName, auto);
				//System.out.println("ǰ��Ǩ�� --  " + proTran);
				path.add(proTran);
				sourceName = proTran.getSource();
				i++;
			}
			for (int j = path.size() - 1; j >= 0; j--) {
				path1.add(path.get(j));
			}
			//System.out.println("----------------------------------------------");
			return path1;

		}
	}

	/**
	 * ���ù�����ȱ���˼�룬�ҵ�ĳһ���ڵ㵽�����κ�һ����ֹ�ڵ�
	 * 
	 * @param auto
	 * @param goalTransition
	 * @return
	 */
	public static ArrayList<Transition> oneOfPathforOneToEnd(Automatic auto, Transition goalTransition) {
		State state = goalTransition.getTargetState();
		// findStateFromString(goalTransition.getTarget(), auto);
		if (state.isFinalState()) {
			return null;
		} else {
			ArrayList<Transition> path = new ArrayList<Transition>();
			while (!(state.isFinalState())) {
				Transition nextTran = oneOfnextTranSet(state, auto);
				path.add(nextTran);
				state = nextTran.getTargetState();
				// findStateFromString(nextTran.getTarget(), auto);
			}
			return path;
		}
	}

	/**
	 * ����״̬�����ҵ�״̬
	 * 
	 * @param name
	 * @param auto
	 * @return
	 */
	/*
	 * public static State findStateFromString (String name, Automatic auto){
	 * State state = new State(); for(State s : auto.getStateSet()){
	 * if(s.getName().equals(name)){ state = s; break; } } return state; }
	 */

	/**
	 * Ѱ��һ����״̬X������״̬
	 */
	/*
	 * public static ArrayList<String> nextStateSet(State X, Automatic a){
	 * ArrayList<String> StateSet = new ArrayList<>(); for(Transition
	 * tran:a.getTransitionSet()){ if(X.getName().equals(tran.getSource())){
	 * StateSet.add(tran.getTarget()); } } return StateSet; }
	 */
	/**
	 * Ѱ��һ����״̬X������Ǩ��
	 */

	/*
	 * public static ArrayList<Transition> nextTranSet(State X, Automatic a){
	 * ArrayList<Transition> transition = new ArrayList<>(); for(Transition
	 * tran:a.getTransitionSet()){ if(X.getName().equals(tran.getSource())){
	 * transition.add(tran); } } return transition; }
	 */
	/**
	 * Ѱ��һ����״̬X������Ǩ��
	 */
	public static Transition oneOfnextTranSet(State X, Automatic a) {
		Transition transition = new Transition();
		for (Transition tran : a.getTransitionSet()) {
			if (X.getName().equals(tran.getSource())) {
				transition = tran;
			}
		}
		return transition;
	}

	/**
	 * ����
	 * 
	 * @param auto
	 * @param goalTransition
	 * @return
	 */
	public static ArrayList<Transition> addProPath(Automatic auto, Transition goalTransition) {
		if (goalTransition.getSource().equals(auto.getInitState().getName())) {
			return null;
		} else {
			// //System.out.println("Ŀ��Ǩ��-- " + goalTransition);
			Queue<State> queue = new LinkedList<State>();
			ArrayList<Transition> path = new ArrayList<Transition>();
			ArrayList<Transition> path1 = new ArrayList<Transition>();
			ArrayList<ArrayList<Transition>> pathset = new ArrayList<ArrayList<Transition>>();
			// State initial = auto.getInitState();
			State start = goalTransition.getSourceState();
			// findStateFromString(goalTransition.getSource(), auto);
			State u = new State();
			// queue.add(initial);
			queue.add(start);
			while (!queue.isEmpty()) {
				// //System.out.println();
				// //System.out.println("-----------------------");
				// //System.out.println("�������ֵ-- " + queue);
				int finish = 0;
				u = queue.poll();
				// //System.out.println("�������ֵ-- " + queue);
				// //System.out.println("���е�����״̬u����-- " + u.getName() + " -- " +
				// u.getId());
				// if(u!=null && !(u.isFinalState())){
				if (u != null && !(u.equals(auto.getInitState()))) {
					// ArrayList<Transition> nextTranSet = nextTranSet(u, auto);
					ArrayList<Transition> proTranSet = u.getProTranSet();
					// proTranSet(u, auto);
					// //System.out.println("u״̬�ĺ���Ǩ�Ƽ���-- " + nextTranSet);
					// //System.out.println("��ȱ���Ŀǰ���е�·�����ϴ�С-- " +
					// pathset.size());
					// //System.out.println("-- " + pathset);
					if (pathset.size() == 0) {
						for (Transition tran : proTranSet) {
							ArrayList<Transition> t = new ArrayList<Transition>();
							t.add(tran);
							pathset.add(t);
						}
					} else {
						int n = pathset.size();
						ArrayList<ArrayList<Transition>> teanset = new ArrayList<ArrayList<Transition>>();// ���Ҫɾ����Ǩ��Ƭ��
						for (int i = 0; i < n; i++) {
							// if(pathset.get(i).get(pathset.get(i).size()-1).getTarget().equals(u.getName())){//�жϼ��������Ԫ���Ƿ��Ǹճ�ջ��״̬���������е�Ǩ�������ܷ���proTranset�е�Ǩ��������
							if (pathset.get(i).get(pathset.get(i).size() - 1).getSource().equals(u.getName())) {
								ArrayList<Transition> tean = new ArrayList<Transition>();
								tean = pathset.get(i);
								teanset.add(tean);
								for (Transition tran : proTranSet) {
									ArrayList<Transition> t = new ArrayList<Transition>();
									for (Transition tr : tean) {
										t.add(tr);
									}
									t.add(tran);
									pathset.add(t);
									// if(tran.getTarget().equals(goalTransition.getSource())){
									if (tran.getSource().equals(auto.getInitState().getName())) {
										finish = 1;
										break;
									}
								}
							}
							if (finish == 1) {
								break;
							}
						}
						// ��pathset��Ŀǰ��ȱ�����·�� ���Ѿ������Ǩ�����ӵ�Ǩ��ɾ��
						for (ArrayList<Transition> t : teanset) {
							pathset.remove(t);
						}
					}
					// //System.out.println("��ȱ���Ŀǰ���е�·�����ϴ�С-- " +
					// pathset.size());
					// //System.out.println("��ȱ������м���-- " + pathset);
					for (Transition tran : proTranSet) {
						// if(tran.getTarget().equals(goalTransition.getSource())){
						if (tran.getSource().equals(auto.getInitState().getName())) {// Ǩ�Ƶ�Դ״̬�ǳ�ʼ�ڵ�
							for (int i = 0; i < pathset.size(); i++) {
								// ============================================
								if (pathset.get(i).get(pathset.get(i).size() - 1).equals(tran)
										&& pathset.get(i).get(0).getTarget().equals(goalTransition.getSource())) {
									path = pathset.get(i);
									for (int j = path.size() - 1; j >= 0; j--) {
										path1.add(path.get(j));
									}
									/*
									 * //System.out.println("���ص�Ŀ��·��-- " + path);
									 * for(Transition t : path){
									 * //System.out.print(t.getName() + "-- "); }
									 * //System.out.println();
									 */
									return path1;
								}
							}
						} else {// Ǩ�Ƶ�Դ״̬���ǳ�ʼ�ڵ㣬����Ǩ�����м�Ǩ��
							/*
							 * for(State state : auto.getStateSet()){
							 * if(state.getName().equals(tran.getSource())){
							 * queue.add(state); } }
							 */
							queue.add(tran.getSourceState());
						}
					}
				}
			}
			return path1;
		}
	}

	public static ArrayList<Transition> addPostPath(Automatic auto, Transition goalTransition) {
		if (goalTransition.getTargetState().isFinalState()) {
			return null;
		} else {
			// //System.out.println("Ŀ��Ǩ��-- " + goalTransition);
			Queue<State> queue = new LinkedList<State>();
			ArrayList<Transition> path = new ArrayList<Transition>();
			ArrayList<Transition> path1 = new ArrayList<Transition>();
			ArrayList<ArrayList<Transition>> pathset = new ArrayList<ArrayList<Transition>>();
			// State initial = auto.getInitState();

			State start = goalTransition.getTargetState();
			// findStateFromString(goalTransition.getSource(), auto);
			//System.out.println("==Ŀ��״̬��  " + start);
			State u = new State();
			// queue.add(initial);
			queue.add(start);
			while (!queue.isEmpty()) {
				// //System.out.println();
				// //System.out.println("-----------------------");
				//System.out.println("�������ֵ-- " + queue);
				int finish = 0;
				u = queue.poll();
				// //System.out.println("�������ֵ-- " + queue);
				// //System.out.println("���е�����״̬u����-- " + u.getName() + " -- " +
				// u.getId());
				// if(u!=null && !(u.isFinalState())){
				if (u != null && !(u.isFinalState())) {
					ArrayList<Transition> nextTranSet = removeTran(u, u.getNextTranSet());
					// ArrayList<Transition> proTranSet = u.getProTranSet();
					// proTranSet(u, auto);
					//System.out.println(nextTranSet.size());
					// //System.out.println(pathset);
					//System.out.println("u״̬�ĺ���Ǩ�Ƽ���-- " + nextTranSet);
					// //System.out.println("��ȱ���Ŀǰ���е�·�����ϴ�С-- " +
					// pathset.size());
					// //System.out.println("-- " + pathset);
					if (pathset.size() == 0) {
						for (Transition tran : nextTranSet) {
							tran.setVisited(true);
							ArrayList<Transition> t = new ArrayList<Transition>();
							t.add(tran);
							pathset.add(t);
						}
						//System.out.println(" ====pathset.size()==0 ====  ");
					} else {
						//System.out.println(" ====pathset.size()��=0 ====  ");
						int n = pathset.size();
						ArrayList<ArrayList<Transition>> teanset = new ArrayList<ArrayList<Transition>>();// ���Ҫɾ����Ǩ��Ƭ��
						for (int i = 0; i < n; i++) {
							if (pathset.get(i).get(pathset.get(i).size() - 1).getTarget().equals(u.getName())) {// �жϼ��������Ԫ���Ƿ��Ǹճ�ջ��״̬���������е�Ǩ�������ܷ���proTranset�е�Ǩ��������
								ArrayList<Transition> tean = new ArrayList<Transition>();
								tean = pathset.get(i);
								teanset.add(tean);
								for (Transition tran : nextTranSet) {
									ArrayList<Transition> t = new ArrayList<Transition>();
									for (Transition tr : tean) {
										t.add(tr);
									}
									t.add(tran);
									pathset.add(t);
									// if(tran.getTarget().equals(goalTransition.getSource())){
									if (tran.getTargetState().isFinalState()) {
										path1 = pathset.get(i);
										finish = 1;
										return path1;
										// break;
									}
								}
							}
							if (finish == 1) {
								break;
							}
						}
						// ��pathset��Ŀǰ��ȱ�����·�� ���Ѿ������Ǩ�����ӵ�Ǩ��ɾ��
						for (ArrayList<Transition> t : teanset) {
							pathset.remove(t);
						}
					}
					// //System.out.println("��ȱ���Ŀǰ���е�·�����ϴ�С-- " +
					// pathset.size());
					// //System.out.println("��ȱ������м���-- " + pathset);
					for (Transition tran : nextTranSet) {
						// if(tran.getTarget().equals(goalTransition.getSource())){
						if (tran.getTargetState().isFinalState()) {// Ǩ�Ƶ�Դ״̬�ǳ�ʼ�ڵ�
							for (int i = pathset.size() - 1; i >= 0; i--) {
								// ============================================
								if (pathset.get(i).get(pathset.get(i).size() - 1).equals(tran)
										&& pathset.get(i).get(0).getTarget().equals(goalTransition.getSource())) {
									path1 = pathset.get(i);
									/*
									 * for(int j=path.size()-1; j>=0; j--){
									 * path1.add(path.get(j)); }
									 */
									/*
									 * //System.out.println("���ص�Ŀ��·��-- " + path);
									 * for(Transition t : path){
									 * //System.out.print(t.getName() + "-- "); }
									 * //System.out.println();
									 */
									return path1;
								}
							}
						} else {// Ǩ�Ƶ�Դ״̬���ǳ�ʼ�ڵ㣬����Ǩ�����м�Ǩ��
							queue.add(tran.getTargetState());

						}
					}
				}
			}
			return path1;
		}
	}

	public static ArrayList<Transition> removeTran(State u, ArrayList<Transition> tranSet) {
		//System.out.println("-------------" + u);
		//System.out.println("-------------" + tranSet);
		for (int i = 0; i < tranSet.size(); i++) {
			Transition t = tranSet.get(i);
			if (t.getSource().equals(t.getTarget())) {
				//System.out.println("-------------1" + t);
				tranSet.remove(t);
				i--;
				break;
			}
			for (Transition tran : u.getProTranSet()) {
				if ((tran.getSource().equals(t.getTarget())) && (tran.getTarget().equals(t.getSource()))) {
					//System.out.println("-------------2" + t);
					tranSet.remove(t);
					i--;
					break;
				}
			}

		}
		return tranSet;
	}

	/**
	 * Ѱ��һ����״̬XΪ��ֹ�ڵ��Ǩ�Ƽ���
	 *//*
		 * public static ArrayList<Transition> proTranSet(State X, Automatic
		 * auto){ ArrayList<Transition> path = new ArrayList<Transition>();
		 * for(Transition t : auto.getTransitionSet()){
		 * if(X.getName().equals(t.getTarget())){ path.add(t); } } return path;
		 * }
		 */

	/**
	 * Ѱ��һ����״̬XΪ��ֹ�ڵ��Ǩ�Ƽ���
	 */
	public static Transition oneOfproTranSet(String X, Automatic auto) {
		Transition path = new Transition();
		for (Transition t : auto.getTransitionSet()) {
			if (X.equals(t.getTarget())) {
				path = t;
				break;
			}
		}
		return path;
	}

	public static ArrayList<Transition> removeCircle(Automatic auto) {
		ArrayList<Transition> tranSet = auto.getTransitionSet();
		ArrayList<Transition> trans = new ArrayList<Transition>();
		for (Transition tt : tranSet) {
			trans.add(tt);
		}
		for (int i = 0; i < tranSet.size(); i++) {
			Transition tran = tranSet.get(i);
			int k = 0;
			for (int j = i; j < tranSet.size(); j++) {
				Transition t = tranSet.get(j);
				if ((tran.getSource().equals(t.getTarget())) && (tran.getTarget().equals(t.getSource()))) {
					if (i < j) {
						trans.remove(t);
						break;
					}
				}
			}
		}
		return trans;
	}

	/*
	 * // ��tran��ʼ��finalState��xxxxxxxxxxx��·��directPaths private static boolean
	 * dfsPathFromStartToEnd(Transition tran, State finalState, int[] visited,
	 * ArrayList<Transition> onePath, HashSet<ArrayList<Transition>>
	 * directPaths) { State targetState = tran.getTargetState();
	 * visited[tran.getId()] = 1; onePath.add(tran); if (targetState ==
	 * finalState) { // ����Ŀ��ڵ� directPaths.add(new ArrayList<>(onePath));
	 * //System.out.println("�õ�һ��·����״̬" +finalState.getId() + "----");
	 * for(Transition tran1 : onePath) { //System.out.println(tran1.getName()); }
	 * onePath.remove(tran); visited[tran.getId()] = 0; } ArrayList<Transition>
	 * nextTrans = new ArrayList<>(); for(Transition nextTran : transitions) {//
	 * ���ݸ���������һ��tran State sourceStateNow = nextTran.getSourceState(); State
	 * targetStateNow = nextTran.getTargetState(); if (targetStateNow.getId() >
	 * finalState.getId() ) { // ������ֹ�ڵ��id continue; }
	 * 
	 * if (sourceStateNow == targetState && visited[nextTran.getId()] == 0) { //
	 * Ϊ���ӵ�����Ǩ�� nextTrans.add(nextTran); } } ArrayList<Transition> nextTranSet =
	 * new ArrayList<>(); while(true) { Transition selectedNextTran =
	 * selectNextTran(nextTrans); nextTranSet.add(selectedNextTran);
	 * 
	 * if (dfsPathFromStartToEnd(selectedNextTran, finalState, visited, onePath,
	 * directPaths)) { return true; } if (nextTranSet.size() >=
	 * nextTrans.size()) { break; } }
	 * 
	 * onePath.remove(tran); visited[tran.getId()] = 0; return false; }
	 * 
	 * 
	 */

}

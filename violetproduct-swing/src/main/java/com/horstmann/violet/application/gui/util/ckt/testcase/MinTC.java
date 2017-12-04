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
import com.horstmann.violet.application.gui.util.ckt.output.ShowInfor;

public class MinTC {
	
	public static void main(String[] args) {
		String xml = "����ģ��ForXStream.xml";
		//String xml = "EA4.1.0 ���ܳ���1ForXStream.xml";
		//String xml = "arm_motors_checkForXStream.xml";
		//String xml = "compass_accumulateForXStream.xml";
		//String xml = "do_user_takeoffForXStream.xml";
		//String xml = "failsafe_battery_eventForXStream.xml";//-------------������
		//String xml = "failsafe_gcs_checkForXStream.xml";//-------������
		//String xml = "fast_loop-----display0ForXStream.xml";
		//String xml = "fast_loop-----display1ForXStream.xml";//���Կ�һ��
		//String xml = "fast_loop-----display2ForXStream.xml";//25�� ���Կ�һ��
		//String xml = "fast_loopForXStream.xml";//29�� ���Կ�һ��
		//String xml = "gcs_check_inputForXStream.xml";//------������
		//String xml = "gcs_data_stream_sendForXStream.xml";
		//String xml = "guided_angle_control_runForXStream.xml";
		//String xml = "guided_pos_control_runForXStream.xml";
		//String xml = "guided_posvel_control_runForXStream.xml";
		//String xml = "guided_run-----display0ForXStream.xml";
		//String xml = "guided_run-----display1ForXStream.xml";
		//String xml = "guided_run-----display2ForXStream.xml";
		//String xml = "guided_run-----display3ForXStream.xml";
		//String xml = "guided_run-----display4ForXStream.xml";
		//String xml = "guided_runForXStream.xml";
		//String xml = "guided_set_destinationForXStream.xml";
		//String xml = "guided_set_velocityForXStream.xml";
		//String xml = "guided_takeoff_runForXStream.xml";
		//String xml = "guided_vel_control_runForXStream.xml";
		//String xml = "handleMessage-----display0ForXStream.xml";
		//String xml = "handleMessage-----display1ForXStream.xml";//----------2�� ������
		//String xml = "handleMessage-----display2ForXStream.xml";//---------4�� ������
		//String xml = "handleMessage-----display3ForXStream.xml";//--------6�� ������
		//String xml = "handleMessage-----display4ForXStream.xml";//---------8�� ������
		//String xml = "handleMessage-----display5ForXStream.xml";//---------10�� ������
		//String xml = "handleMessage-----display6ForXStream.xml";//---------12�� ������
		//String xml = "handleMessage-----display7ForXStream.xml";//----------15�� ������
		//String xml = "handleMessageForXStream.xml";//----------17�� ������
		//String xml = "land_gps_runForXStream.xml";
		//String xml = "land_nogps_runForXStream.xml";
		//String xml = "land_run-----display0ForXStream.xml";
		//String xml = "land_run-----display1ForXStream.xml";
		//String xml = "land_runForXStream.xml";
		//String xml = "loop-----display0ForXStream.xml";
		//String xml = "loop-----display1ForXStream.xml";//31 ������
		//String xml = "loop-----display2ForXStream.xml";//44 ������
		//String xml = "loop-----display3ForXStream.xml";//51 ������
		//String xml = "loop-----display4ForXStream.xml";//92������
		//String xml = "loop-----display5ForXStream.xml";//136		
//		String xml = "loop-----display6ForXStream.xml";
//		String xml = "loop-----display74ForXStream.xml";
//		String xml = "loop-----display8ForXStream.xml";
//		String xml = "loop-----display9ForXStream.xml";
//		String xml = "loop-----display10ForXStream.xml";
//		String xml = "loop-----display11ForXStream.xml";
		//String xml = "loopForXStream.xml";//������
		
		//String xml = "motors_outputForXStream.xml";
		//String xml = "one_hz_loopForXStream.xml";
		//String xml = "rc_loop-----display0ForXStream.xml";
		//String xml = "rc_loopForXStream.xml";
		//String xml = "read_AHRSForXStream.xml";
		//String xml = "motors_outputForXStream.xml";
		//String xml = "read_radioForXStream.xml";
		//String xml = "setup-----display0ForXStream.xml";
		//String xml = "setupForXStream.xml";
		//String xml = "three_hz_loopForXStream.xml";
		//String xml = "UAV-----display0ForXStream.xml";
		//String xml = "update_altitudeForXStream.xml";
		//String xml = "update_batt_compassForXStream.xml";
		//String xml = "update_flight_mode-----display0ForXStream.xml";
		//String xml = "update_flight_mode-----display1ForXStream.xml";
		//String xml = "update_flight_modeForXStream.xml";
		//String xml = "update_GPSForXStream.xml";
		//String xml = "update_land_and_crash_detectors-----display0ForXStream.xml";
		//String xml = "update_land_and_crash_detectorsForXStream.xml";
		//String xml = "update_land_detectorForXStream.xml";
		
		
		Automatic automatic = GetAutomatic.getAutomatic(xml);// ���ԭʼ��ʱ���Զ���
		//Automatic new_automatic = IPR__1.iPR(automatic);// ��ò�ֺ��ʱ���Զ���
		//Automatic aTDRTAutomatic = ATDTR__1.aTDRT(new_automatic, automatic);// ���ȥ������ʱ��Ǩ�ƺ��ʱ���Զ���
		//������ֹ״̬����
		/*for(State state:aTDRTAutomatic.getStateSet()) {
			int k1= 0;
			for(Transition tran:aTDRTAutomatic.getTransitionSet()){//�ж�Ŀ��״̬�Ƿ��ѱ�����
				if(state.getName().equals(tran.getSource())){//�ҳ��Դ�״̬Ϊ����Ǩ��
					k1=1;
				}
			}
			if(k1==0){
				state.setFinalState(true);
			}		
		}*/
		/*int i = 1;
		for(State state:automatic.getStateSet()) {
			int k1= 0;
			for(Transition tran:automatic.getTransitionSet()){//�ж�Ŀ��״̬�Ƿ��ѱ�����
				if(state.getName().equals(tran.getSource())){//�ҳ��Դ�״̬Ϊ����Ǩ��
					k1=1;
				}
			}
			if(k1==0){
				state.setFinalState(true);
				//ShowInfor.print(3, "��ֹ״̬--- " + i + "---" + state);
			}else{
				state.setFinalState(false);
				////ShowInfor.print(3, "������ֹ״̬--- " + i + "---" + state);
			}
			i++;
		}
		*/
		
		/*//Ǩ�Ƹ���(��Ҫƽ̨��ʾ�ӿ�)
		ArrayList<Automatic> testCase = sideCoverage(aTDRTAutomatic);	*/

		ArrayList<Automatic> testCase = sideCoverage(automatic);	//-----------------------
		
		
		
		//�����Ϣ
		//ShowInfor.print(3, testCase.size() + "-----����·���ĸ���");//--------------------------
		for(Automatic auto : testCase){
			for(Transition tran : auto.getTransitionSet()){
				//ShowInfor.print(3, tran.getName() + "------" + tran.getTranTimeName());
			}	
			//ShowInfor.print();
			//ShowInfor.print(3, "-------");
		}
		
	}
	/**
	 * ���Ե�һ���㷨
	 * @param auto
	 * @return
	 */
	public static ArrayList<Automatic> test1(Automatic auto){
		//ArrayList<ArrayList<Transition>> minSS = minSS(auto);
		ArrayList<Automatic> test1 = sideCoverage(auto);
		return test1;
	}
	/**
	 * ��Ҫ�ĵ��÷�������MinTestcaseOfSide������Ǩ�Ƹ��ǵ���С��Ǩ�Ƽ���ת��Ϊ������������
	 * @param auto  �����ʱ���Զ�������ֹ״̬�Ѿ���ȷ���
	 * @return
	 */
	public static ArrayList<Automatic> sideCoverage (Automatic auto){
		ArrayList<ArrayList<Transition>> minTestcaseOfSide = MinTestcaseOfSide(auto);
		int n=minTestcaseOfSide.size();//������������
		ArrayList<Automatic> testcaseSet=new ArrayList<Automatic>();//������������
		for(int i=0;i<n;i++){
			Automatic test_case=new Automatic();
			test_case.setClockSet(auto.getClockSet());
			test_case.setName("��������"+(i+1));
			test_case.setTransitionSet(minTestcaseOfSide.get(i));
			ArrayList<State> StateSet = new ArrayList<State>();
			State s = minTestcaseOfSide.get(i).get(0).getSourceState();
					//findStateFromString((minTestcaseOfSide.get(i).get(0).getSource()),auto);
			StateSet.add(s);
			for(Transition tran : minTestcaseOfSide.get(i)){
				State state = tran.getTargetState();
						//findStateFromString((tran.getTarget()),auto);
				StateSet.add(state);
			}
            test_case.setStateSet(StateSet);
			test_case.setInitState(auto.getInitState());
			testcaseSet.add(test_case);
		}
		return testcaseSet;
	}
	
	
	/**
	 * ��Ҫ������������ʱ���Զ���������Ǩ�Ƹ��ǵ���С�Ĳ�������
	 * @param auto �����ʱ���Զ�������ֹ״̬�Ѿ���ȷ���
	 * @return
	 */
	public static ArrayList<ArrayList<Transition>> MinTestcaseOfSide (Automatic auto){
		
		//ShowInfor.print(3, "-------------------�㷨ǰ����Ǩ��-------------------");
		//ShowInfor.print(3, "********************************************");
		//ShowInfor.print(3, "�㷨ǰ��ʼ״̬--  " + auto.getInitState().getName());
		//ShowInfor.print(3, "�㷨ǰ״̬������  " + auto.getStateSet().size());
		//ShowInfor.print(3, "�㷨ǰǨ�Ƹ�����  " + auto.getTransitionSet().size());
		//ShowInfor.print(3, "�㷨ǰǨ��--  " + auto.getTransitionSet());
		//ShowInfor.print();
		
//		for(Transition tran : auto.getTransitionSet()){
//		
//			if(tran.getSource().equals(auto.getInitState().getName())){
//				//ShowInfor.print(3, "*************************************************");
//				//ShowInfor.print(3, " ��ʼǨ��-- " + tran);
//				//ShowInfor.print(3, "*************************************************");
//				//ShowInfor.print();
//			}
//			State state= tran.getTargetState();
//					//findStateFromString(tran.getTarget(), auto);
//			if(state.isFinalState()){
//				//ShowInfor.print();
//				//ShowInfor.print(3, "========================================================");
//				//ShowInfor.print(3, " ��ֹ״̬-- " + state);
//				//ShowInfor.print(3, "              --------------------------------");
//				//ShowInfor.print(3, " ��ֹǨ��-- " + tran);
//				//ShowInfor.print(3, "========================================================");
//			}
//		}
		//ShowInfor.print();
		//ShowInfor.print(3, "�㷨ǰ���г�ʼ״̬--  " + auto.getInitState());
		//ShowInfor.print(3, "�㷨ǰ����Ǩ��--  " );
		
		for(Transition t : auto.getTransitionSet()){
			//ShowInfor.print(3, t.getName() + "-- ");
		}
		//ShowInfor.print();
		//ShowInfor.print(3, "********************************************");
		
		//��һ���㷨
		ArrayList<ArrayList<Transition>> ss = minSS(auto);
		
		//ShowInfor.print();
		//ShowInfor.print(3, "-------------------1���õ���Ǩ��Ƭ��-------------------");
		//ShowInfor.print(3, "********************************************");
		for(ArrayList<Transition> tset : ss){
			for(Transition t : tset){
				//ShowInfor.print(3, t.getName() + "-- ");
			}
			//ShowInfor.print();
		}
		//ShowInfor.print(3, "********************************************");
		
		
//		//ShowInfor.print(3, "-----------------------------------------------");
//		//ShowInfor.print();
//		//ShowInfor.print(3, "������1���㷨�õ���Ǩ��Ƭ�θ��� ---   " + ss.size());
		
		//�ڶ����㷨
		ArrayList<ArrayList<Transition>> lastminSS = lastMinSS(ss, auto);
		
		//ShowInfor.print();
		//ShowInfor.print(3, "-------------------2��Լ��Ǩ��Ƭ��-------------------");
		//ShowInfor.print(3, "********************************************");
		for(ArrayList<Transition> tset : lastminSS){
			for(Transition t : tset){
				//ShowInfor.print(3, t.getName() + "-- ");
			}
			//ShowInfor.print();
		}
		//ShowInfor.print(3, "********************************************");
		
//		//ShowInfor.print(3, "������2���㷨�õ���Ǩ��Ƭ�θ��� ---   " + lastminSS.size());
//		//ShowInfor.print(3, "-----------------------------------------------");
//		//ShowInfor.print();
		
		
		ArrayList<ArrayList<Transition>> propath = new ArrayList<ArrayList<Transition>>();
		
		
		
		/*//��������shortPathforStartToOne�㷨
		//ShowInfor.print(3, "2���㷨���Ǩ��Ƭ��--  " + lastminSS.get(3));
		////ShowInfor.print(3, "transet.get(0)--  " + transet.get(0));		
		if(lastminSS.get(3).get(0).getSource().equals(auto.getInitState().getName())){
			propath.add(lastminSS.get(3));
			//ShowInfor.print(3, "---��Ƭ���Ǵӳ�ʼ״̬��ʼ---");
			//ShowInfor.print(3, lastminSS.get(3));
			for(Transition t : lastminSS.get(3)){
				//ShowInfor.print(3, t.getName() + "--  ");
			}
			//ShowInfor.print();
		}else{
			ArrayList<Transition> shortPath = shortPathforStartToOne(auto, lastminSS.get(3).get(0));
			//ShowInfor.print(3, "2���㷨�����ǰ����Ǩ��Ƭ��  " + shortPath);
			for(Transition tran : lastminSS.get(3)){
				shortPath.add(tran);
			}
			propath.add(shortPath);
			//ShowInfor.print();
			//ShowInfor.print(3, shortPath);
			for(Transition t : shortPath){
				//ShowInfor.print(3, t.getName() + "--  ");
			}
			//ShowInfor.print();
		}*/
								
		//Ϊ������Ƭ�μ���ǰ��
		for(ArrayList<Transition> transet : lastminSS){
			
			//ShowInfor.print();
			//ShowInfor.print(3, "------------------------------");
			//ShowInfor.print(3, "2���㷨���Ǩ��Ƭ��--  " + transet);
			if(transet.get(0).getSource().equals(auto.getInitState().getName())){
				propath.add(transet);
				//ShowInfor.print(3, "---��Ƭ���Ǵӳ�ʼ״̬��ʼ,��·��Ϊ---");
				////ShowInfor.print(3, transet);
				for(Transition t : transet){
					//ShowInfor.print(3, t.getName() + "--  ");
				}
				//ShowInfor.print();
			}else{
//				//ShowInfor.print(3, "��ǰ��ǰ����Ǩ��------    " + transet.get(0));
				//ArrayList<Transition> shortPath = shortPathforStartToOne(auto, transet.get(0));
				//ArrayList<Transition> shortPath = addProPath(auto, transet.get(0));//----------------------------------
				ArrayList<Transition> shortPath = oneOfPathforStartToOne(auto, transet.get(0));
				//ShowInfor.print(3, "--- " + shortPath);
				
				////ShowInfor.print(3, "2���㷨�����ǰ����Ǩ��Ƭ��  " + shortPath);
				for(Transition tran : transet){
					shortPath.add(tran);
				}
				propath.add(shortPath);
				//ShowInfor.print(3, "---����ǰ����·��--- ");
				for(Transition t : shortPath){
					//ShowInfor.print(3, t.getName() + "--  ");
				}
				//ShowInfor.print();
			}
			//ShowInfor.print(3, "------------------------------");
		}
		
		//ShowInfor.print();
		//ShowInfor.print(3, "-------------------��ǰ����·��-------------------");
		//ShowInfor.print(3, "********************************************");
		for(ArrayList<Transition> tset : propath){
			for(Transition t : tset){
				//ShowInfor.print(3, t.getName() + "-- ");
			}
			//ShowInfor.print();
		}
		//ShowInfor.print(3, "********************************************");
		
		
	    //�ҵ�Ǩ��Ƭ�ε���ֹ�ڵ�����·��
		for(ArrayList<Transition> transet : propath){
			//ShowInfor.print(3, "                      ----------------------------------------");
			////ShowInfor.print(3, auto.getInitState());
			//ShowInfor.print(3, "====Ŀ��״̬====" + transet.get(transet.size()-1).getTargetState());
			//ShowInfor.print(3, "                      ----------------------------------------");
			if(!(transet.get(transet.size()-1).getTargetState().isFinalState())){
				//ShowInfor.print(3, "====��Ҫ�Ӻ���Ƭ��====");
			//if(!(findStateFromString(transet.get(transet.size()-1).getTarget(), auto).isFinalState())){
				//ArrayList<Transition> lastPath = shortPathforOneToEnd(auto, transet.get(transet.size()-1));//----------------------------------
				//ArrayList<Transition> lastPath = addPostPath(auto, transet.get(transet.size()-1));
				ArrayList<Transition> lastPath = oneOfPathforOneToEnd(auto, transet.get(transet.size()-1));
				//ShowInfor.print(3, "����Ƭ��-- " + lastPath);
				for(Transition tran : lastPath){
					transet.add(tran);
				}
			}	
		}
		//ShowInfor.print();
		//ShowInfor.print(3, "--------------------����·��-------------------");
		//ShowInfor.print(3, "********************************************");
		for(ArrayList<Transition> tset : propath){
			for(Transition t : tset){
				//ShowInfor.print(3, t.getName() + "-- ");
			}
			//ShowInfor.print();
		}
		//ShowInfor.print(3, "********************************************");
		//ShowInfor.print();
		return propath;
		
	}
	
	/**
	 * �Ż�Ǩ�Ƹ����㷨
	 * @param auto
	 * @return
	 */
	public static ArrayList<ArrayList<Transition>> minSS (Automatic auto){
		ArrayList<ArrayList<Transition>> SS = new ArrayList<ArrayList<Transition>>();
		ArrayList<Transition> ss = new ArrayList<Transition>();
		ArrayList<Transition> TS = new ArrayList<Transition>();
		ArrayList<Transition> T = new ArrayList<Transition>();
		//������Ǩ�ƴ����Ǩ�Ƽ���T��
		for(Transition tran1 : auto.getTransitionSet()){
			T.add(tran1);
		}
		
		while(T.size() > 0){
			//ShowInfor.print(3, "T.size-- "+ T.size());
			////ShowInfor.print(3, "1--t  "+T.toString()+"******************************************");
			////ShowInfor.print(3, "1--ts  " + TS +"**************************************");
			for(int i=0; i<T.size(); i++){//��Ǩ�Ƽ���T��ѡȡһ����Ǩ��t
				//ShowInfor.print(3, "i----  " + i);
				Transition tran = T.get(i);
				////ShowInfor.print(3, "tran--  " +tran + "************");
              //  //ShowInfor.print(3, "2--tran  " + tran + "************");
                ////ShowInfor.print(3, "2--tǰ  "+T.toString()+"******************************************");
    			////ShowInfor.print(3, "2--tsǰ  " + TS +"**************************************");
				TS.add(tran);        //��ѡȡ��Ǩ��t����TS�У�����Ǩ�Ƽ���T��ɾ����Ǩ��t
				T.remove(tran);
				--i;
				////ShowInfor.print(3, "2--t  "+T.toString()+"******************************************");
				////ShowInfor.print(3, "2--ts  "+TS.toString()+"******************************************");
				////ShowInfor.print(3, TS.size()-1);
				////ShowInfor.print(3, TS.get(0));
				for(int j=0; j<T.size(); j++){
					Transition transition = T.get(j);
					Transition tslast = TS.get(TS.size()-1); //�ж�TS�����һ��Ǩ���Ƿ��к��Ǩ��
					if(tslast.getTarget().equals(transition.getSource())){//transition��TS�����һ��Ǩ�Ƶĺ��Ǩ�ƣ��Ѹ�Ǩ�Ƽ���TS�У�����T��ɾ��
						TS.add(transition);
						T.remove(transition);
						--j;
					}
				}
				//�ж�SS�е�ÿ��Ǩ��Ƭ���Ƿ���TS�е�Ǩ��Ƭ�������һ��
				if(SS.size() > 0){
					for(int k=0; k<SS.size(); k++){	
						ArrayList<Transition> s = SS.get(k);
						String first = TS.get(0).getSource();//TS������Ǩ��Ƭ�εĵ�һ��Ǩ�Ƶ�Դ״̬
						String last = TS.get(TS.size()-1).getTarget();//TS������Ǩ��Ƭ�ε����һ��Ǩ�Ƶ�Ŀ��״̬
					    //s�����һ��Ǩ�Ƶ�Ŀ��״̬����TS�е�һ��Ǩ�Ƶ�Դ״̬ʱ��s�����ӵ�TS��ͷ��
						if(s.get(s.size()-1).getTarget().equals(first)){
							ArrayList<Transition> ts = new ArrayList<Transition>();
							for(Transition tr : s){
								ts.add(tr);
							}
							SS.remove(s);
							--k;
							for(Transition tr : TS){
								ts.add(tr);
							}
							TS = new ArrayList<Transition>();
							for(Transition tr : ts){
								TS.add(tr);
							}
						}
						//s�ĵ�һ��Ǩ�Ƶ�Դ״̬����TS�����һ��Ǩ�Ƶ�Ŀ��״̬ʱ��s�����ӵ�TS��β��
						if(s.get(0).getSource().equals(last)){
							for(Transition tr : s){
								TS.add(tr);
							}
							SS.remove(s);
							--k;
						}
					}
				}
				//����Ǹ������TS����SS��ȥ�������TS
				SS.add(TS);
				TS = new ArrayList<Transition>();	
				
			}
		}
        for(ArrayList<Transition> s : SS){
        	//ShowInfor.print(3, "***********");
			for(Transition t : s){
				////ShowInfor.print(3, t.getId()+"   ---   ");
				//ShowInfor.print(3, t.getName() + " -- ");
			}
			//ShowInfor.print(3, "***********");
		}
		return SS;
	}
	
	/**
	 * ��С���Գɱ�Ǩ�Ƹ����㷨
	 * @param SS �Ż�Ǩ�Ƹ����㷨���������SS
	 * @return ����M����С���Գɱ�Ǩ�Ƹ��ǵ����м���SS1
	 */
	public static ArrayList<ArrayList<Transition>> lastMinSS (ArrayList<ArrayList<Transition>> SS, Automatic auto){	
		ArrayList<ArrayList<Transition>> SS1 = new ArrayList<ArrayList<Transition>>();
		while(SS.size()>0 && SS!=null){
			//ShowInfor.print();
			//ShowInfor.print(3, "************************");
			//ShowInfor.print();
			ArrayList<Transition> TSk = new ArrayList<Transition>();
			TSk = SS.get(0);
			SS.remove(TSk);
			if(SS1.size() == 0){
				SS1.add(TSk);
			}else{
				int f = 0;
				if(TSk.size() == 1){//TSk��ֻ��һ��Ǩ��
					for(int i=0;i<SS1.size();i++){
						ArrayList<Transition> TSi = SS1.get(i);
						for(Transition t : TSi){
							if(t.equals(TSk.get(0))){
								f = 1; //��TSk��ֻ��һ��Ǩ�ƣ�����SS1���ҵ���������SS1�����ж�
								//ShowInfor.print(3, "TSk��ֻ��һ��Ǩ�ƣ�����SS1���ҵ�");
							}
						}
					}
				}
				if((f==0) || (TSk.size() > 1)){
					//ShowInfor.print(3, " SS1.size--  " + SS1.size()+"--------------------------------------------------------");
					ArrayList<Transition> TS = new ArrayList<Transition>();//���TSK��TSi�����ӵĽ��
					
					//��TSK��SS1������Ƭ�ν������
					int finish = 0;
					for(int i=0;i<SS1.size();i++){
						ArrayList<Transition> TSi = SS1.get(i);
						////ShowInfor.print(3, SS1.size());
						//ShowInfor.print(3, " TSk--  " + TSk);
						//ShowInfor.print(3, " TSi--  " + TSi);
						//ShowInfor.print(3, "SS1.get(i)--  " + i);
					    //TSk��TSi�Ƿ��ཻ
						int cross = 0;
						//if(TSk.get(0).getSource().equals(anObject))
						for(Transition t1: TSk){
							for(Transition t2 : TSi){
								if(t1.getSource().equals(t2.getSource()) || t1.getTarget().equals(t2.getSource()) || 
										t1.getSource().equals(t2.getTarget()) || t1.getTarget().equals(t2.getTarget())){
									cross = 1; //�ཻ
								}
							}
						}
						//if((TSi.get(0).getSource().equals(TSk.get(TSk.size()-1).getTarget()))
						//|| (TSi.get(TSi.size()-1).getTarget().equals(TSk.get(0).getSource()))){
						//TSk��TSi�ཻ
						if(cross == 1){
							//ShowInfor.print(3, "TSk �� TSi �ཻ  *************************" );
							
							if(TSi.get(0).getSource().equals(TSk.get(TSk.size()-1).getTarget())){//TSk������TSi
								//ShowInfor.print(3, "TSk������TSi  *************************" );
								ArrayList<Transition> TS1 = new ArrayList<Transition>();
								for(Transition tran : TSk){
									TS1.add(tran);
								}
								for(Transition tran : TSi){
									TS1.add(tran);
								}
								SS1.remove(TSi);
								--i;
								TS = TS1;
								finish = 1;
								//SS1.add(TS1);
							}else{
								if(TSi.get(TSi.size()-1).getTarget().equals(TSk.get(0).getSource())){//TSi������TSk
									//ShowInfor.print(3, "TSi������TSk  *************************" );
									ArrayList<Transition> TS1 = new ArrayList<Transition>();
									for(Transition tran : TSi){
										TS1.add(tran);
									}
									for(Transition tran : TSk){
										TS1.add(tran);
									}
									SS1.remove(TSi);
									--i;
									TS = TS1;
									finish = 1;
									//SS1.add(TS1);
								}else{
									if((TSk.get(0).getSource().equals(TSk.get(TSk.size()-1).getTarget())) 
											|| (TSi.get(0).getSource().equals(TSi.get(TSi.size()-1).getTarget()))){//TSk����TSi�ǻ�·
										//ShowInfor.print(3, "TSk����TSi�ǻ�·  *************************" );
										ArrayList<Transition> TSk1 = new ArrayList<Transition>();
										ArrayList<Transition> TSk2 = new ArrayList<Transition>();
										ArrayList<Transition> TSi1 = new ArrayList<Transition>();
										ArrayList<Transition> TSi2 = new ArrayList<Transition>();
										ArrayList<Transition> TSKCache = new ArrayList<Transition>();
										ArrayList<Transition> TSiCache = new ArrayList<Transition>();
										for(Transition tran : TSk){
											int j = 0;
											for(Transition tran1 : TSi){
												if(tran.getTarget().equals(tran1.getSource())){//�ҵ��ཻ���
													for(Transition trann : TSk){
														TSKCache.add(trann);
													}
													for(Transition trann : TSi){
														TSiCache.add(trann);
													}
													for(Transition trank : TSk){
														if(!(trank.getSource().equals(tran.getTarget()))){
															TSk1.add(trank);
															TSKCache.remove(trank);
														}else{
															TSk2 = TSKCache;
														}	
													}
													for(Transition trani : TSi){
														if(!(trani.getSource().equals(tran1.getSource()))){
															TSi1.add(trani);
															TSiCache.remove(trani);
														}else{
															TSi2 = TSiCache;
														}	
													}
													j = 1;
													break;
												}
											}
											if(j == 1){
												break;
											}
										}
										if((TSi.get(0).getSource().equals(TSi.get(TSi.size()-1).getTarget()))){//TSi���ڻ�·
											ArrayList<Transition> TSii = new ArrayList<Transition>();
											for(Transition trann : TSk1){
												TSii.add(trann);
											}
											for(Transition trann : TSi2){
												TSii.add(trann);
											}
											for(Transition trann : TSi1){
												TSii.add(trann);
											}
											for(Transition trann : TSk2){
												TSii.add(trann);
											}
											TS = TSii;
											finish = 1;
											//SS1.add(TSii);
										}else{
											if(TSk.get(0).getSource().equals(TSk.get(TSk.size()-1).getTarget())){//TSi���ڻ�·
												ArrayList<Transition> TSii = new ArrayList<Transition>();
												for(Transition trann : TSi1){
													TSii.add(trann);
												}
												for(Transition trann : TSk2){
													TSii.add(trann);
												}
												for(Transition trann : TSk1){
													TSii.add(trann);
												}
												for(Transition trann : TSi2){
													TSii.add(trann);
												}
												TS = TSii;
												finish = 1;
												//SS1.add(TSii);
											}
										}
									}else{//���ǻ�·
										//ShowInfor.print(3, "TSk����TSi���ǻ�·  *************************" );
										TS = TSk;
										//SS1.add(TSk);
									}
								}
							}
						}else{
							//���TSk��TSi���뽻����ô��TSK�ϲ���SS1��
							//ShowInfor.print(3, "TSk��TSi���ཻ  *************************" );
							//if(!(TSi.get(0).getSource().equals(TSk.get(TSk.size()-1).getTarget())) //TSk��TSi���ཻ
								//	&& !(TSi.get(TSi.size()-1).getTarget().equals(TSk.get(0).getSource()))){
							if(cross == 0){
								//���TSK��TSi���ཻ�����ж��Ƿ����һ��Ǩ���ܰ�����Ǩ��������һ��
								int flag = 0;
								for(Transition tran1 : auto.getTransitionSet()){
									//TSK + һ��Ǩ��  + TSi
									if((tran1.getSource().equals(TSk.get(TSk.size()-1).getTarget())) && (tran1.getTarget().equals(TSi.get(0).getSource()))){
										//ShowInfor.print(3, "TSK + һ��Ǩ��  + TSi  *************************" );
										ArrayList<Transition> TS1 = new ArrayList<Transition>();
										for(Transition tran : TSk){
											TS1.add(tran);
										}
										TS1.add(tran1);
										for(Transition tran : TSi){
											TS1.add(tran);
										}
										SS1.remove(TSi);
										--i;
										TS = TS1;
										//SS1.add(TS1);
										flag = 1;
										finish = 1;
										break;
									}else{
										//TSi + һ��Ǩ��  + TSk
										if((tran1.getSource().equals(TSi.get(TSi.size()-1).getTarget())) && (tran1.getTarget().equals(TSk.get(0).getSource()))){
											ArrayList<Transition> TS1 = new ArrayList<Transition>();
											//ShowInfor.print(3, "TSi + һ��Ǩ��  + TSk  *************************" );
											for(Transition tran : TSi){
												TS1.add(tran);
											}
											TS1.add(tran1);
											for(Transition tran : TSk){
												TS1.add(tran);
											}
											SS1.remove(TSi);
											--i;
											TS = TS1;
											//SS1.add(TS1);
											flag = 1;
											finish = 1;
											break;
										}
									}
								}	
								if(flag == 0){
									//ShowInfor.print(3, "TSi �� TSk���ཻ  �������޷���һ��Ǩ������  *************************" );
									////ShowInfor.print(3, "ss1--  " + SS1);
									////ShowInfor.print(3, "tsk--  " + TSk);
									TS = TSk;
									//SS1.add(TSk);
								}
							}
						}	
						if(finish == 1){
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
	 * ���ù�����ȱ�����˼���ҵ��ӿ�ʼ�ڵ㵽���Ǩ��Ƭ����ͷ�������·��
	 * @param auto
	 * @param goalTransition
	 * @return
	 */
	public static ArrayList<Transition> shortPathforStartToOne(Automatic auto, Transition goalTransition){
		if(goalTransition.getSourceState().equals(auto.getInitState())){
			return null;
		}else{
			////ShowInfor.print(3, "Ŀ��Ǩ��--  " + goalTransition);
			Queue<State> queue = new LinkedList<State>();
			ArrayList<Transition> path = new ArrayList<Transition>();
			ArrayList<Transition> path1 = new ArrayList<Transition>();
			ArrayList<ArrayList<Transition>> pathset = new ArrayList<ArrayList<Transition>>();
			State initial = auto.getInitState();
			State u = new State();
			queue.add(initial);
			while(!queue.isEmpty()){
				////ShowInfor.print();
				////ShowInfor.print(3, "-----------------------");
				//ShowInfor.print(3, "�������ֵ-- " + queue);
				int finish = 0;
				u = queue.poll();
				////ShowInfor.print(3, "�������ֵ-- " + queue);
				////ShowInfor.print(3, "���е�����״̬u����-- " + u.getName() + " -- " + u.getId());
				if(u!=null && !(u.isFinalState())){
					//ShowInfor.print(3, "----"  + u.getNextTranSet());
					ArrayList<Transition> nextTranSet =  removeTran(u, u.getNextTranSet());
					//ArrayList<Transition> nextTranSet =  nextTranSet(u, auto);
					////ShowInfor.print(3, "u״̬�ĺ���Ǩ�Ƽ���-- " + nextTranSet);
					////ShowInfor.print(3, "��ȱ���Ŀǰ���е�·�����ϴ�С-- " + pathset.size());
					////ShowInfor.print(3, "-- " + pathset);
					if(pathset.size() == 0){	
						for(Transition tran : nextTranSet){
							ArrayList<Transition> t = new ArrayList<Transition>();
							t.add(tran);
							pathset.add(t);
						}	
					}else{
						int n = pathset.size();
						ArrayList<ArrayList<Transition>> teanset = new ArrayList<ArrayList<Transition>>();//���Ҫɾ����Ǩ��Ƭ��
						for(int i=0; i<n; i++){
							if(pathset.get(i).get(pathset.get(i).size()-1).getTarget().equals(u.getName())){//�жϼ��������Ԫ���Ƿ��Ǹճ�ջ��״̬���������е�Ǩ�������ܷ���nextTranset�е�Ǩ��������
								ArrayList<Transition> tean = new ArrayList<Transition>();
								tean = pathset.get(i);	
								teanset.add(tean);
								for(Transition tran : nextTranSet){
									ArrayList<Transition> t = new ArrayList<Transition>();
									for(Transition tr : tean){
										t.add(tr);
									}
									t.add(tran);
									pathset.add(t);
									if(tran.getTarget().equals(goalTransition.getSource())){
										path1 = pathset.get(i);
										finish = 1;
										return path1;
										//break;
									}
								}	
							}
							if(finish == 1){
								break;
							}
						}
						//��pathset��Ŀǰ��ȱ�����·�� ���Ѿ������Ǩ�����ӵ�Ǩ��ɾ��
						for(ArrayList<Transition> t: teanset){
							pathset.remove(t);
						}
					}	
					////ShowInfor.print(3, "��ȱ���Ŀǰ���е�·�����ϴ�С-- " + pathset.size());
					////ShowInfor.print(3, "��ȱ������м���-- " + pathset);
					for(Transition tran : nextTranSet){
						if(tran.getTarget().equals(goalTransition.getSource())){
							//for(int i=0; i<pathset.size(); i++){
							for(int i=pathset.size()-1; i>=0; i--){
								//============================================
								if(pathset.get(i).get(pathset.get(i).size()-1).equals(tran) /*&& pathset.get(i).get(0).getSource().equals(auto.getInitState().getName())*/){
									path = pathset.get(i);
									/*//ShowInfor.print(3, "���ص�Ŀ��·��-- " + path);
									for(Transition t : path){
										//ShowInfor.print(3, t.getName() + "-- ");
									}
									//ShowInfor.print();*/
							        return path;
								}
							}
						}else{
							for(State state : auto.getStateSet()){
								if(state.getName().equals(tran.getTarget())){
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
	 * @param auto
	 * @param goalTransition
	 * @return
	 */
	public static ArrayList<Transition> shortPathforOneToEnd(Automatic auto, Transition goalTransition){
		//if(findStateFromString(goalTransition.getTarget(), auto).isFinalState()){
		if(goalTransition.getTargetState().isFinalState()){
			return null;
		}else{
			Queue<State> queue = new LinkedList<State>();
			ArrayList<Transition> path = new ArrayList<Transition>();
			ArrayList<ArrayList<Transition>> pathset = new ArrayList<ArrayList<Transition>>();
			//State initial = auto.getInitState();
			State start = goalTransition.getTargetState();
					//findStateFromString((goalTransition.getTarget()),auto);
			State u = new State();
			queue.add(start);
			while(!queue.isEmpty()){
				int finish = 0;
				u = queue.poll();
				if(u!=null && !(u.isFinalState())){
					//ShowInfor.print(3, "�������ڷ���״̬--   " + u);
					//ShowInfor.print(3, "�������ڷ���״̬ǰ��--   " + u.getProTranSet());
					//ShowInfor.print(3, "�������ڷ���״̬����--   " + u.getNextTranSet());
					//ShowInfor.print(3, "�������ڷ���״̬�Ƿ�����ֹ״̬--   " + u.isFinalState());
					ArrayList<Transition> nextTranSet =  removeTran(u, u.getNextTranSet());
					//ShowInfor.print(3, "�������ڷ���״̬����--   " + nextTranSet);
							//nextTranSet(u, auto); //��ȡ�Ե�ǰ״̬ΪԴ���Ǩ�Ƽ���
					if(pathset.size() == 0){	
						for(Transition tran : nextTranSet){
							ArrayList<Transition> t = new ArrayList<Transition>();
							t.add(tran);
							pathset.add(t);
						}	
					}else{
						int n = pathset.size();
						ArrayList<ArrayList<Transition>> teanset = new ArrayList<ArrayList<Transition>>();
						for(int i=0; i<n; i++){
							if(pathset.get(i).get(pathset.get(i).size()-1).getTarget().equals(u.getName())){
								ArrayList<Transition> tean = new ArrayList<Transition>();	
								tean = pathset.get(i);
								teanset.add(tean);
								for(Transition tran : nextTranSet){
									ArrayList<Transition> t = new ArrayList<Transition>();
									for(Transition tr : tean){
										t.add(tr);
									}
									t.add(tran);
									pathset.add(t);
									if(tran.getTarget().equals(goalTransition.getSource())){
										finish = 1;
										break;
									}
								}	
							}
							if(finish == 1){
								break;
							}
						}
						for(ArrayList<Transition> t: teanset){
							pathset.remove(t);
						}
					}	
					for(Transition tran : nextTranSet){
						//if(findStateFromString(tran.getTarget(),auto).isFinalState()){
						if(tran.getTargetState().isFinalState()){
							for(int i=pathset.size()-1; i>=0; i--){
								if(pathset.get(i).get(pathset.get(i).size()-1).equals(tran)){
									path = pathset.get(i);
							        return path;
								}
							}
						}else{
							/*for(State state : auto.getStateSet()){
								if(state.getName().equals(tran.getTarget())){
									queue.add(state);
								}
							}*/
							queue.add(tran.getTargetState());
						}
					}
				}
			}
			return path;
		}
	}
	
	
	
	
	
	
	
	public static ArrayList<Transition> oneOfPathforStartToOne(Automatic auto, Transition goalTransition){
		
		//ShowInfor.print(3, "----------------------------------------------");
		String sourceName = goalTransition.getSource();
		String startName = auto.getInitState().getName();
		if(sourceName.equals(startName)){
			//ShowInfor.print(3, "----------------------------------------------");
			return null;
		}else{
			ArrayList<Transition> path = new ArrayList<Transition>();
			ArrayList<Transition> path1 = new ArrayList<Transition>();
			while(!(sourceName.equals(startName))){
				int i = 1;
				//ShowInfor.print(3, "�� " + i + " �β���");
				//State state = findStateFromString(sourceName, auto);
				Transition proTran = oneOfproTranSet(sourceName, auto);
				//ShowInfor.print(3, "ǰ��Ǩ�� --  " + proTran);
				path.add(proTran);
				sourceName = proTran.getSource();
				i++;
			}
			for(int j=path.size()-1; j>=0; j--){
				path1.add(path.get(j));
			}
			//ShowInfor.print(3, "----------------------------------------------");
			return path1;
			
		}		
	}
	
	
	/**
	 * ���ù�����ȱ���˼�룬�ҵ�ĳһ���ڵ㵽�����κ�һ����ֹ�ڵ�
	 * @param auto
	 * @param goalTransition
	 * @return
	 */
	public static ArrayList<Transition> oneOfPathforOneToEnd(Automatic auto, Transition goalTransition){
		State state = goalTransition.getTargetState();
				//findStateFromString(goalTransition.getTarget(), auto);
		if(state.isFinalState()){
			return null;
		}else{
			ArrayList<Transition> path = new ArrayList<Transition>();
			while(!(state.isFinalState())){	
				Transition nextTran = oneOfnextTranSet(state, auto);
				path.add(nextTran);
				state = nextTran.getTargetState();
						//findStateFromString(nextTran.getTarget(), auto);
			}			
			return path;
		}
	}

	/**
	 * ����״̬�����ҵ�״̬
	 * @param name
	 * @param auto
	 * @return
	 */
	/*public static State findStateFromString (String name, Automatic auto){
		State state = new State();
		for(State s : auto.getStateSet()){
			if(s.getName().equals(name)){
				state = s;
				break;
			}
		}
		return state;
	}*/
	
	/**
	 * Ѱ��һ����״̬X������״̬
	 */
	/*public static ArrayList<String> nextStateSet(State X, Automatic a){
		ArrayList<String> StateSet = new ArrayList<>();
		for(Transition tran:a.getTransitionSet()){
			if(X.getName().equals(tran.getSource())){
				StateSet.add(tran.getTarget());
			}
		}
		return StateSet;
	}*/
	/**
	 * Ѱ��һ����״̬X������Ǩ��
	 */
	/*public static ArrayList<Transition> nextTranSet(State X, Automatic a){
		ArrayList<Transition> transition = new ArrayList<>();
		for(Transition tran:a.getTransitionSet()){
			if(X.getName().equals(tran.getSource())){
				transition.add(tran);
			}
		}
		return transition;
	}*/
	/**
	 * Ѱ��һ����״̬X������Ǩ��
	 */
	public static Transition oneOfnextTranSet(State X, Automatic a){
		Transition transition = new Transition();
		for(Transition tran:a.getTransitionSet()){
			if(X.getName().equals(tran.getSource())){
				transition = tran;
			}
		}
		return transition;
	}
	
	/**
	 * ����
	 * @param auto
	 * @param goalTransition
	 * @return
	 */
	public static ArrayList<Transition> addProPath(Automatic auto, Transition goalTransition){
		if(goalTransition.getSource().equals(auto.getInitState().getName())){
			return null;
		}else{
			////ShowInfor.print(3, "Ŀ��Ǩ��--  " + goalTransition);
			Queue<State> queue = new LinkedList<State>();
			ArrayList<Transition> path = new ArrayList<Transition>();
			ArrayList<Transition> path1 = new ArrayList<Transition>();
			ArrayList<ArrayList<Transition>> pathset = new ArrayList<ArrayList<Transition>>();
			//State initial = auto.getInitState();
			State start = goalTransition.getSourceState();
					//findStateFromString(goalTransition.getSource(), auto);
			State u = new State();
			//queue.add(initial);
			queue.add(start);
			while(!queue.isEmpty()){
				////ShowInfor.print();
				////ShowInfor.print(3, "-----------------------");
				////ShowInfor.print(3, "�������ֵ-- " + queue);
				int finish = 0;
				u = queue.poll();
				////ShowInfor.print(3, "�������ֵ-- " + queue);
				////ShowInfor.print(3, "���е�����״̬u����-- " + u.getName() + " -- " + u.getId());
				//if(u!=null && !(u.isFinalState())){
				if(u!=null && !(u.equals(auto.getInitState()))){
					//ArrayList<Transition> nextTranSet =  nextTranSet(u, auto);
					ArrayList<Transition> proTranSet =  u.getProTranSet();
							//proTranSet(u, auto);
					////ShowInfor.print(3, "u״̬�ĺ���Ǩ�Ƽ���-- " + nextTranSet);
					////ShowInfor.print(3, "��ȱ���Ŀǰ���е�·�����ϴ�С-- " + pathset.size());
					////ShowInfor.print(3, "-- " + pathset);
					if(pathset.size() == 0){	
						for(Transition tran : proTranSet){
							ArrayList<Transition> t = new ArrayList<Transition>();
							t.add(tran);
							pathset.add(t);
						}	
					}else{
						int n = pathset.size();
						ArrayList<ArrayList<Transition>> teanset = new ArrayList<ArrayList<Transition>>();//���Ҫɾ����Ǩ��Ƭ��
						for(int i=0; i<n; i++){
							//if(pathset.get(i).get(pathset.get(i).size()-1).getTarget().equals(u.getName())){//�жϼ��������Ԫ���Ƿ��Ǹճ�ջ��״̬���������е�Ǩ�������ܷ���proTranset�е�Ǩ��������
							if(pathset.get(i).get(pathset.get(i).size()-1).getSource().equals(u.getName())){
								ArrayList<Transition> tean = new ArrayList<Transition>();
								tean = pathset.get(i);	
								teanset.add(tean);
								for(Transition tran : proTranSet){
									ArrayList<Transition> t = new ArrayList<Transition>();
									for(Transition tr : tean){
										t.add(tr);
									}
									t.add(tran);
									pathset.add(t);
									//if(tran.getTarget().equals(goalTransition.getSource())){
									if(tran.getSource().equals(auto.getInitState().getName())){
										finish = 1;
										break;
									}
								}	
							}
							if(finish == 1){
								break;
							}
						}
						//��pathset��Ŀǰ��ȱ�����·�� ���Ѿ������Ǩ�����ӵ�Ǩ��ɾ��
						for(ArrayList<Transition> t: teanset){
							pathset.remove(t);
						}
					}	
					////ShowInfor.print(3, "��ȱ���Ŀǰ���е�·�����ϴ�С-- " + pathset.size());
					////ShowInfor.print(3, "��ȱ������м���-- " + pathset);
					for(Transition tran : proTranSet){
						//if(tran.getTarget().equals(goalTransition.getSource())){
						if(tran.getSource().equals(auto.getInitState().getName())){//Ǩ�Ƶ�Դ״̬�ǳ�ʼ�ڵ�
							for(int i=0; i<pathset.size(); i++){
								//============================================
								if(pathset.get(i).get(pathset.get(i).size()-1).equals(tran) && pathset.get(i).get(0).getTarget().equals(goalTransition.getSource())){
									path = pathset.get(i);
									for(int j=path.size()-1; j>=0; j--){
										path1.add(path.get(j));
									}
									/*//ShowInfor.print(3, "���ص�Ŀ��·��-- " + path);
									for(Transition t : path){
										//ShowInfor.print(3, t.getName() + "-- ");
									}
									//ShowInfor.print();*/
							        return path1;
								}
							}
						}else{//Ǩ�Ƶ�Դ״̬���ǳ�ʼ�ڵ㣬����Ǩ�����м�Ǩ��
							/*for(State state : auto.getStateSet()){
								if(state.getName().equals(tran.getSource())){
									queue.add(state);
								}
							}*/
							queue.add(tran.getSourceState());
						}
					}
				}
			}
			return path1;
		}		
	}
	
	public static ArrayList<Transition> addPostPath(Automatic auto, Transition goalTransition){
		if(goalTransition.getTargetState().isFinalState()){
			return null;
		}else{
			////ShowInfor.print(3, "Ŀ��Ǩ��--  " + goalTransition);
			Queue<State> queue = new LinkedList<State>();
			ArrayList<Transition> path = new ArrayList<Transition>();
			ArrayList<Transition> path1 = new ArrayList<Transition>();
			ArrayList<ArrayList<Transition>> pathset = new ArrayList<ArrayList<Transition>>();
			//State initial = auto.getInitState();
			
			State start = goalTransition.getTargetState();
					//findStateFromString(goalTransition.getSource(), auto);
			//ShowInfor.print(3, "==Ŀ��״̬��  " + start);
			State u = new State();
			//queue.add(initial);
			queue.add(start);
			while(!queue.isEmpty()){
				////ShowInfor.print();
				////ShowInfor.print(3, "-----------------------");
				//ShowInfor.print(3, "�������ֵ-- " + queue);
				int finish = 0;
				u = queue.poll();
				////ShowInfor.print(3, "�������ֵ-- " + queue);
				////ShowInfor.print(3, "���е�����״̬u����-- " + u.getName() + " -- " + u.getId());
				//if(u!=null && !(u.isFinalState())){
				if(u!=null && !(u.isFinalState())){
					ArrayList<Transition> nextTranSet =  removeTran(u, u.getNextTranSet());
					//ArrayList<Transition> proTranSet =  u.getProTranSet();
							//proTranSet(u, auto);
					//ShowInfor.print(3, nextTranSet.size()+"");
					////ShowInfor.print(3, pathset);
					//ShowInfor.print(3, "u״̬�ĺ���Ǩ�Ƽ���-- " + nextTranSet);
					////ShowInfor.print(3, "��ȱ���Ŀǰ���е�·�����ϴ�С-- " + pathset.size());
					////ShowInfor.print(3, "-- " + pathset);
					if(pathset.size() == 0){	
						for(Transition tran : nextTranSet){
							tran.setVisited(true);
							ArrayList<Transition> t = new ArrayList<Transition>();
							t.add(tran);
							pathset.add(t);
						}	
						//ShowInfor.print(3, " ====pathset.size()==0 ====  ");
					}else{
						//ShowInfor.print(3, " ====pathset.size()��=0 ====  ");
						int n = pathset.size();
						ArrayList<ArrayList<Transition>> teanset = new ArrayList<ArrayList<Transition>>();//���Ҫɾ����Ǩ��Ƭ��
						for(int i=0; i<n; i++){
							if(pathset.get(i).get(pathset.get(i).size()-1).getTarget().equals(u.getName())){//�жϼ��������Ԫ���Ƿ��Ǹճ�ջ��״̬���������е�Ǩ�������ܷ���proTranset�е�Ǩ��������
								ArrayList<Transition> tean = new ArrayList<Transition>();
								tean = pathset.get(i);	
								teanset.add(tean);
								for(Transition tran : nextTranSet){
									ArrayList<Transition> t = new ArrayList<Transition>();
									for(Transition tr : tean){
										t.add(tr);
									}
									t.add(tran);
									pathset.add(t);
									//if(tran.getTarget().equals(goalTransition.getSource())){
									if(tran.getTargetState().isFinalState()){
										path1 = pathset.get(i);
										finish = 1;
										return path1;
										//break;
									}
								}	
							}
							if(finish == 1){
								break;
							}
						}
						//��pathset��Ŀǰ��ȱ�����·�� ���Ѿ������Ǩ�����ӵ�Ǩ��ɾ��
						for(ArrayList<Transition> t: teanset){
							pathset.remove(t);
						}
					}	
					////ShowInfor.print(3, "��ȱ���Ŀǰ���е�·�����ϴ�С-- " + pathset.size());
					////ShowInfor.print(3, "��ȱ������м���-- " + pathset);
					for(Transition tran : nextTranSet){
						//if(tran.getTarget().equals(goalTransition.getSource())){
						if(tran.getTargetState().isFinalState()){//Ǩ�Ƶ�Դ״̬�ǳ�ʼ�ڵ�
							for(int i=pathset.size()-1; i>=0; i--){
								//============================================
								if(pathset.get(i).get(pathset.get(i).size()-1).equals(tran) && pathset.get(i).get(0).getTarget().equals(goalTransition.getSource())){
									path1 = pathset.get(i);
									/*for(int j=path.size()-1; j>=0; j--){
										path1.add(path.get(j));
									}*/
									/*//ShowInfor.print(3, "���ص�Ŀ��·��-- " + path);
									for(Transition t : path){
										//ShowInfor.print(3, t.getName() + "-- ");
									}
									//ShowInfor.print();*/
							        return path1;
								}
							}
						}else{//Ǩ�Ƶ�Դ״̬���ǳ�ʼ�ڵ㣬����Ǩ�����м�Ǩ��
							queue.add(tran.getTargetState());
							
						}
					}
				}
			}
			return path1;
		}		
	}
	
	
	public static ArrayList<Transition> removeTran(State u, ArrayList<Transition> tranSet){
		//ShowInfor.print(3, "-------------" + u);
		//ShowInfor.print(3, "-------------" + tranSet);
		for(int i=0;i<tranSet.size();i++){
			Transition t = tranSet.get(i);
			if(t.getSource().equals(t.getTarget())){
				//ShowInfor.print(3, "-------------1" + t);
				tranSet.remove(t);
				i--;
				break;
			}
			for(Transition tran : u.getProTranSet()){
				if((tran.getSource().equals(t.getTarget())) && (tran.getTarget().equals(t.getSource()))){
					//ShowInfor.print(3, "-------------2" + t);
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
	public static ArrayList<Transition> proTranSet(State X, Automatic auto){
		ArrayList<Transition> path = new ArrayList<Transition>();
		for(Transition t : auto.getTransitionSet()){
			if(X.getName().equals(t.getTarget())){
				path.add(t);	
			}
		}
		return path;
	}*/
	
	/**
	 * Ѱ��һ����״̬XΪ��ֹ�ڵ��Ǩ�Ƽ���
	 */
	public static Transition oneOfproTranSet(String X, Automatic auto){
		Transition path = new Transition();
		for(Transition t : auto.getTransitionSet()){
			if(X.equals(t.getTarget())){
				path = t;
				break;
			}
		}
		return path;
	}
	
	public static ArrayList<Transition> removeCircle(Automatic auto){
		ArrayList<Transition> tranSet = auto.getTransitionSet();
		ArrayList<Transition> trans = new ArrayList<Transition>();
		for(Transition tt : tranSet){
			trans.add(tt);
		}
		for(int i=0; i<tranSet.size(); i++){
			Transition tran = tranSet.get(i);
			int k = 0;
			for(int j=i; j<tranSet.size(); j++){
				Transition t = tranSet.get(j);
				if((tran.getSource().equals(t.getTarget())) && (tran.getTarget().equals(t.getSource()))){
					if(i<j){
						trans.remove(t);
						break;
					}	
				}
			}
		}
		return trans;
	}
	
	
	
	
	
	
	/*  // ��tran��ʼ��finalState��xxxxxxxxxxx��·��directPaths
		private static boolean dfsPathFromStartToEnd(Transition tran, State finalState, int[] visited,
				ArrayList<Transition> onePath, HashSet<ArrayList<Transition>> directPaths) {
			State targetState = tran.getTargetState();
			visited[tran.getId()] = 1;
			onePath.add(tran);
			if (targetState == finalState) { // ����Ŀ��ڵ�
				directPaths.add(new ArrayList<>(onePath));
				//ShowInfor.print(3, "�õ�һ��·����״̬" +finalState.getId() + "----");
				for(Transition tran1 : onePath) {
					//ShowInfor.print(3, tran1.getName());
				}
				onePath.remove(tran);
				visited[tran.getId()] = 0;
			}
			ArrayList<Transition> nextTrans = new ArrayList<>();
			for(Transition nextTran : transitions) {// ���ݸ���������һ��tran
				State sourceStateNow = nextTran.getSourceState();
				State targetStateNow = nextTran.getTargetState();
				if (targetStateNow.getId() > finalState.getId() ) { // ������ֹ�ڵ��id
				} 
				
				if (sourceStateNow == targetState && visited[nextTran.getId()] == 0) { // Ϊ���ӵ�����Ǩ��
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
	
	
	*/
	
	
	
	
}

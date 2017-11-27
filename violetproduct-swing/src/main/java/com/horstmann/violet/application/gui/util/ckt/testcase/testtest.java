package com.horstmann.violet.application.gui.util.ckt.testcase;

import java.util.ArrayList;

import com.horstmann.violet.application.gui.util.ckt.handle.ATDTR__1;
import com.horstmann.violet.application.gui.util.ckt.handle.Automatic;
import com.horstmann.violet.application.gui.util.ckt.handle.GetAutomatic;
import com.horstmann.violet.application.gui.util.ckt.handle.IPR__1;
import com.horstmann.violet.application.gui.util.ckt.handle.State;
import com.horstmann.violet.application.gui.util.ckt.handle.Transition;

public class testtest {

	public static void main(String[] args) {
		String xml = "EAElevatorForXStream.xml";
		Automatic automatic = GetAutomatic.getAutomatic(xml);// ���ԭʼ��ʱ���Զ���
		Automatic new_automatic = IPR__1.iPR(automatic);// ��ò�ֺ��ʱ���Զ���
		Automatic aTDRTAutomatic = ATDTR__1.aTDRT(new_automatic, automatic);// ���ȥ������ʱ��Ǩ�ƺ��ʱ���Զ���
		//������ֹ״̬����
		for(State state:aTDRTAutomatic.getStateSet()) {
			int k1= 0;
			for(Transition tran:aTDRTAutomatic.getTransitionSet()){//�ж�Ŀ��״̬�Ƿ��ѱ�����
				if(state.getName().equals(tran.getSource())){//�ҳ��Դ�״̬Ϊ����Ǩ��
					k1=1;
				}
			}
			if(k1==0){
				state.setFinalState(true);
			}		
		}
		

		ArrayList<Automatic> test1 = test1(aTDRTAutomatic);

	}
	/**
	 * ���Ե�һ���㷨
	 * @param auto
	 * @return
	 */
	public static ArrayList<Automatic> test1(Automatic auto){
		ArrayList<ArrayList<Transition>> minSS = minSS(auto);
		ArrayList<Automatic> test1 = sideCoverage(auto, minSS);
		return test1;
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
			System.out.println("T.size-- "+ T.size());
			//System.out.println("1--t  "+T.toString()+"******************************************");
			//System.out.println("1--ts  " + TS +"**************************************");
			for(int i=0; i<T.size(); i++){//��Ǩ�Ƽ���T��ѡȡһ����Ǩ��t
				System.out.println("i----  " + i);
				Transition tran = T.get(i);
				System.out.println("tran--  " +tran + "************");
              //  System.out.println("2--tran  " + tran + "************");
                System.out.println("2--tǰ  "+T.toString()+"******************************************");
    			System.out.println("2--tsǰ  " + TS +"**************************************");
				TS.add(tran);        //��ѡȡ��Ǩ��t����TS�У�����Ǩ�Ƽ���T��ɾ����Ǩ��t
				T.remove(tran);
				--i;
				System.out.println("2--t  "+T.toString()+"******************************************");
				System.out.println("2--ts  "+TS.toString()+"******************************************");
				//System.out.println(TS.size()-1);
				//System.out.println(TS.get(0));
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
        	System.out.println("***********");
			for(Transition t : s){
				//System.out.print(t.getId()+"   ---   ");
				System.out.print(t.getName() + " -- ");
			}
			System.out.println("***********");
		}
		return SS;
	}
	/**
	 * ��Ҫ�ĵ��÷�������MinTestcaseOfSide������Ǩ�Ƹ��ǵ���С��Ǩ�Ƽ���ת��Ϊ������������
	 * @param auto  �����ʱ���Զ�������ֹ״̬�Ѿ���ȷ���
	 * @return
	 */
	public static ArrayList<Automatic> sideCoverage (Automatic auto , ArrayList<ArrayList<Transition>> minTestcaseOfSide){
		//ArrayList<ArrayList<Transition>> minTestcaseOfSide = MinTestcaseOfSide(auto);
		int n=minTestcaseOfSide.size();//������������
		ArrayList<Automatic> testcaseSet=new ArrayList<Automatic>();//������������
		for(int i=0;i<n;i++){
			Automatic test_case=new Automatic();
			test_case.setClockSet(auto.getClockSet());
			test_case.setName("��������"+(i+1));
			test_case.setTransitionSet(minTestcaseOfSide.get(i));
			ArrayList<State> StateSet = new ArrayList<State>();
			State s = findStateFromString((minTestcaseOfSide.get(i).get(0).getSource()),auto);
			StateSet.add(s);
			for(Transition tran : minTestcaseOfSide.get(i)){
				State state = findStateFromString((tran.getTarget()),auto);
				StateSet.add(state);
			}
            test_case.setStateSet(StateSet);
			test_case.setInitState(auto.getInitState());
			testcaseSet.add(test_case);
		}
		return testcaseSet;
	}
	/**
	 * ����״̬�����ҵ�״̬
	 * @param name
	 * @param auto
	 * @return
	 */
	public static State findStateFromString (String name, Automatic auto){
		State state = new State();
		for(State s : auto.getStateSet()){
			if(s.getName().equals(name)){
				state = s;
				break;
			}
		}
		return state;
	}

}

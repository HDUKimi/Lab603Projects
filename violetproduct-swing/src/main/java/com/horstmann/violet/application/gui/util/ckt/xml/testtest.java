package com.horstmann.violet.application.gui.util.ckt.xml;

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
		
		//�����Ϣ
		System.out.println(test1.size() + "-----����·���ĸ���");
		for(Automatic auto : test1){
			for(Transition tran : auto.getTransitionSet()){
				System.out.println(tran.getName() + "------" + tran.getTranTimeName());
			}	
			System.out.println("-------------------");
			System.out.println();
		}

	}
	/**
	 * ���Ե�һ���㷨
	 * @param auto
	 * @return
	 */
	public static ArrayList<Automatic> test1(Automatic auto){
		ArrayList<ArrayList<Transition>> minSS = minSS(auto);
		
		ArrayList<ArrayList<Transition>> lastminSS = lastMinSS(minSS, auto); 
		
		ArrayList<Automatic> test1 = sideCoverage(auto, lastminSS);
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
	 * ��С���Գɱ�Ǩ�Ƹ����㷨
	 * @param SS �Ż�Ǩ�Ƹ����㷨���������SS
	 * @return ����M����С���Գɱ�Ǩ�Ƹ��ǵ����м���SS1
	 */
	public static ArrayList<ArrayList<Transition>> lastMinSS (ArrayList<ArrayList<Transition>> SS, Automatic auto){	
		ArrayList<ArrayList<Transition>> SS1 = new ArrayList<ArrayList<Transition>>();
		while(SS.size()>0 && SS!=null){
			System.out.println();
			System.out.println("************************");
			System.out.println();
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
								System.out.println("TSk��ֻ��һ��Ǩ�ƣ�����SS1���ҵ�");
							}
						}
					}
				}
				if((f==0) || (TSk.size() > 1)){
					System.out.println(" SS1.size--  " + SS1.size()+"--------------------------------------------------------");
					ArrayList<Transition> TS = new ArrayList<Transition>();//���TSK��TSi�����ӵĽ��
					
					//��TSK��SS1������Ƭ�ν������
					int finish = 0;
					for(int i=0;i<SS1.size();i++){
						ArrayList<Transition> TSi = SS1.get(i);
						//System.out.println(SS1.size());
						System.out.println(" TSk--  " + TSk);
						System.out.println(" TSi--  " + TSi);
						System.out.println("SS1.get(i)--  " + i);
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
							System.out.println("TSk �� TSi �ཻ  *************************" );
							
							if(TSi.get(0).getSource().equals(TSk.get(TSk.size()-1).getTarget())){//TSk������TSi
								System.out.println("TSk������TSi  *************************" );
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
									System.out.println("TSi������TSk  *************************" );
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
										System.out.println("TSk����TSi�ǻ�·  *************************" );
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
										System.out.println("TSk����TSi���ǻ�·  *************************" );
										TS = TSk;
										//SS1.add(TSk);
									}
								}
							}
						}else{
							//���TSk��TSi���뽻����ô��TSK�ϲ���SS1��
							System.out.println("TSk��TSi���ཻ  *************************" );
							//if(!(TSi.get(0).getSource().equals(TSk.get(TSk.size()-1).getTarget())) //TSk��TSi���ཻ
								//	&& !(TSi.get(TSi.size()-1).getTarget().equals(TSk.get(0).getSource()))){
							if(cross == 0){
								//���TSK��TSi���ཻ�����ж��Ƿ����һ��Ǩ���ܰ�����Ǩ��������һ��
								int flag = 0;
								for(Transition tran1 : auto.getTransitionSet()){
									//TSK + һ��Ǩ��  + TSi
									if((tran1.getSource().equals(TSk.get(TSk.size()-1).getTarget())) && (tran1.getTarget().equals(TSi.get(0).getSource()))){
										System.out.println("TSK + һ��Ǩ��  + TSi  *************************" );
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
											System.out.println("TSi + һ��Ǩ��  + TSk  *************************" );
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
									System.out.println("TSi �� TSk���ཻ  �������޷���һ��Ǩ������  *************************" );
									//System.out.println("ss1--  " + SS1);
									//System.out.println("tsk--  " + TSk);
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

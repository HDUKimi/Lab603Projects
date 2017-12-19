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
		Automatic automatic = GetAutomatic.getAutomatic(xml);// 获得原始的时间自动机
		Automatic new_automatic = IPR__1.iPR(automatic);// 获得拆分后的时间自动机
		Automatic aTDRTAutomatic = ATDTR__1.aTDRT(new_automatic, automatic);// 获得去除抽象时间迁移后的时间自动机
		//搜索终止状态集合
		for(State state:aTDRTAutomatic.getStateSet()) {
			int k1= 0;
			for(Transition tran:aTDRTAutomatic.getTransitionSet()){//判断目标状态是否已被访问
				if(state.getName().equals(tran.getSource())){//找出以此状态为起点的迁移
					k1=1;
				}
			}
			if(k1==0){
				state.setFinalState(true);
			}		
		}
		

		ArrayList<Automatic> test1 = test1(aTDRTAutomatic);
		
		//输出信息
		System.out.println(test1.size() + "-----测试路径的个数");
		for(Automatic auto : test1){
			for(Transition tran : auto.getTransitionSet()){
				System.out.println(tran.getName() + "------" + tran.getTranTimeName());
			}	
			System.out.println("-------------------");
			System.out.println();
		}

	}
	/**
	 * 测试第一个算法
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
	 * 优化迁移覆盖算法
	 * @param auto
	 * @return
	 */
	public static ArrayList<ArrayList<Transition>> minSS (Automatic auto){
		ArrayList<ArrayList<Transition>> SS = new ArrayList<ArrayList<Transition>>();
		ArrayList<Transition> ss = new ArrayList<Transition>();
		ArrayList<Transition> TS = new ArrayList<Transition>();
		ArrayList<Transition> T = new ArrayList<Transition>();
		//把所有迁移存放在迁移集合T中
		for(Transition tran1 : auto.getTransitionSet()){
			T.add(tran1);
		}
		
		while(T.size() > 0){
			System.out.println("T.size-- "+ T.size());
			//System.out.println("1--t  "+T.toString()+"******************************************");
			//System.out.println("1--ts  " + TS +"**************************************");
			for(int i=0; i<T.size(); i++){//从迁移集合T中选取一个单迁移t
				System.out.println("i----  " + i);
				Transition tran = T.get(i);
				System.out.println("tran--  " +tran + "************");
              //  System.out.println("2--tran  " + tran + "************");
                System.out.println("2--t前  "+T.toString()+"******************************************");
    			System.out.println("2--ts前  " + TS +"**************************************");
				TS.add(tran);        //把选取的迁移t存入TS中，并从迁移集合T中删除该迁移t
				T.remove(tran);
				--i;
				System.out.println("2--t  "+T.toString()+"******************************************");
				System.out.println("2--ts  "+TS.toString()+"******************************************");
				//System.out.println(TS.size()-1);
				//System.out.println(TS.get(0));
				for(int j=0; j<T.size(); j++){
					Transition transition = T.get(j);
					Transition tslast = TS.get(TS.size()-1); //判断TS中最后一条迁移是否有后继迁移
					if(tslast.getTarget().equals(transition.getSource())){//transition是TS中最后一条迁移的后继迁移，把该迁移加入TS中，并从T中删除
						TS.add(transition);
						T.remove(transition);
						--j;
					}
				}
				//判断SS中的每个迁移片段是否与TS中的迁移片段能组合一起
				if(SS.size() > 0){
					for(int k=0; k<SS.size(); k++){	
						ArrayList<Transition> s = SS.get(k);
						String first = TS.get(0).getSource();//TS集合中迁移片段的第一个迁移的源状态
						String last = TS.get(TS.size()-1).getTarget();//TS集合中迁移片段的最后一个迁移的目标状态
					    //s的最后一个迁移的目标状态等于TS中第一个迁移的源状态时，s能连接到TS的头部
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
						//s的第一个迁移的源状态等于TS中最后一个迁移的目的状态时，s能连接到TS的尾部
						if(s.get(0).getSource().equals(last)){
							for(Transition tr : s){
								TS.add(tr);
							}
							SS.remove(s);
							--k;
						}
					}
				}
				//将但歉意序列TS加入SS中去，并清空TS
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
	 * 主要的调用方法，将MinTestcaseOfSide中满足迁移覆盖的最小的迁移集合转换为测试用例集合
	 * @param auto  传入的时间自动机中终止状态已经明确标出
	 * @return
	 */
	public static ArrayList<Automatic> sideCoverage (Automatic auto , ArrayList<ArrayList<Transition>> minTestcaseOfSide){
		//ArrayList<ArrayList<Transition>> minTestcaseOfSide = MinTestcaseOfSide(auto);
		int n=minTestcaseOfSide.size();//测试用例个数
		ArrayList<Automatic> testcaseSet=new ArrayList<Automatic>();//测试用例集合
		for(int i=0;i<n;i++){
			Automatic test_case=new Automatic();
			test_case.setClockSet(auto.getClockSet());
			test_case.setName("测试用例"+(i+1));
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
	 * 最小测试成本迁移覆盖算法
	 * @param SS 优化迁移覆盖算法的输出集合SS
	 * @return 满足M的最小测试成本迁移覆盖的序列集合SS1
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
				if(TSk.size() == 1){//TSk里只有一条迁移
					for(int i=0;i<SS1.size();i++){
						ArrayList<Transition> TSi = SS1.get(i);
						for(Transition t : TSi){
							if(t.equals(TSk.get(0))){
								f = 1; //若TSk中只有一条迁移，且在SS1中找到，则不用与SS1中做判断
								System.out.println("TSk中只有一条迁移，且在SS1中找到");
							}
						}
					}
				}
				if((f==0) || (TSk.size() > 1)){
					System.out.println(" SS1.size--  " + SS1.size()+"--------------------------------------------------------");
					ArrayList<Transition> TS = new ArrayList<Transition>();//存放TSK与TSi中连接的结果
					
					//对TSK与SS1中所有片段进行组合
					int finish = 0;
					for(int i=0;i<SS1.size();i++){
						ArrayList<Transition> TSi = SS1.get(i);
						//System.out.println(SS1.size());
						System.out.println(" TSk--  " + TSk);
						System.out.println(" TSi--  " + TSi);
						System.out.println("SS1.get(i)--  " + i);
					    //TSk与TSi是否相交
						int cross = 0;
						//if(TSk.get(0).getSource().equals(anObject))
						for(Transition t1: TSk){
							for(Transition t2 : TSi){
								if(t1.getSource().equals(t2.getSource()) || t1.getTarget().equals(t2.getSource()) || 
										t1.getSource().equals(t2.getTarget()) || t1.getTarget().equals(t2.getTarget())){
									cross = 1; //相交
								}
							}
						}
						//if((TSi.get(0).getSource().equals(TSk.get(TSk.size()-1).getTarget()))
						//|| (TSi.get(TSi.size()-1).getTarget().equals(TSk.get(0).getSource()))){
						//TSk与TSi相交
						if(cross == 1){
							System.out.println("TSk 与 TSi 相交  *************************" );
							
							if(TSi.get(0).getSource().equals(TSk.get(TSk.size()-1).getTarget())){//TSk能连接TSi
								System.out.println("TSk能连接TSi  *************************" );
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
								if(TSi.get(TSi.size()-1).getTarget().equals(TSk.get(0).getSource())){//TSi能连接TSk
									System.out.println("TSi能连接TSk  *************************" );
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
											|| (TSi.get(0).getSource().equals(TSi.get(TSi.size()-1).getTarget()))){//TSk或者TSi是回路
										System.out.println("TSk或者TSi是回路  *************************" );
										ArrayList<Transition> TSk1 = new ArrayList<Transition>();
										ArrayList<Transition> TSk2 = new ArrayList<Transition>();
										ArrayList<Transition> TSi1 = new ArrayList<Transition>();
										ArrayList<Transition> TSi2 = new ArrayList<Transition>();
										ArrayList<Transition> TSKCache = new ArrayList<Transition>();
										ArrayList<Transition> TSiCache = new ArrayList<Transition>();
										for(Transition tran : TSk){
											int j = 0;
											for(Transition tran1 : TSi){
												if(tran.getTarget().equals(tran1.getSource())){//找到相交结点
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
										if((TSi.get(0).getSource().equals(TSi.get(TSi.size()-1).getTarget()))){//TSi存在回路
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
											if(TSk.get(0).getSource().equals(TSk.get(TSk.size()-1).getTarget())){//TSi存在回路
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
									}else{//不是回路
										System.out.println("TSk或者TSi不是回路  *************************" );
										TS = TSk;
										//SS1.add(TSk);
									}
								}
							}
						}else{
							//如果TSk与TSi不想交，那么吧TSK合并到SS1中
							System.out.println("TSk与TSi不相交  *************************" );
							//if(!(TSi.get(0).getSource().equals(TSk.get(TSk.size()-1).getTarget())) //TSk与TSi不相交
								//	&& !(TSi.get(TSi.size()-1).getTarget().equals(TSk.get(0).getSource()))){
							if(cross == 0){
								//如果TSK与TSi不相交，则判断是否存在一条迁移能把两条迁移连接再一起
								int flag = 0;
								for(Transition tran1 : auto.getTransitionSet()){
									//TSK + 一条迁移  + TSi
									if((tran1.getSource().equals(TSk.get(TSk.size()-1).getTarget())) && (tran1.getTarget().equals(TSi.get(0).getSource()))){
										System.out.println("TSK + 一条迁移  + TSi  *************************" );
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
										//TSi + 一条迁移  + TSk
										if((tran1.getSource().equals(TSi.get(TSi.size()-1).getTarget())) && (tran1.getTarget().equals(TSk.get(0).getSource()))){
											ArrayList<Transition> TS1 = new ArrayList<Transition>();
											System.out.println("TSi + 一条迁移  + TSk  *************************" );
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
									System.out.println("TSi 与 TSk不相交  且两者无法用一条迁移连接  *************************" );
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
	 * 根据状态名称找到状态
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

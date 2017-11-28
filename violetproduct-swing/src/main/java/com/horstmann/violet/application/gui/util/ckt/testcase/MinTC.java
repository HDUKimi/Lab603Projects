package com.horstmann.violet.application.gui.util.ckt.testcase;

import java.util.ArrayList;
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
		
		
		//迁移覆盖(主要平台显示接口)
		ArrayList<Automatic> testCase = sideCoverage(aTDRTAutomatic);	
		
		
		
		//输出信息
		System.out.println(testCase.size() + "-----测试路径的个数");
		for(Automatic auto : testCase){
			for(Transition tran : auto.getTransitionSet()){
				System.out.println(tran.getName() + "------" + tran.getTranTimeName());
			}	
			System.out.println();
			System.out.println("-------");
		}
		
	}
	/**
	 * 测试第一个算法
	 * @param auto
	 * @return
	 */
	public static ArrayList<Automatic> test1(Automatic auto){
		//ArrayList<ArrayList<Transition>> minSS = minSS(auto);
		ArrayList<Automatic> test1 = sideCoverage(auto);
		return test1;
	}
	/**
	 * 主要的调用方法，将MinTestcaseOfSide中满足迁移覆盖的最小的迁移集合转换为测试用例集合
	 * @param auto  传入的时间自动机中终止状态已经明确标出
	 * @return
	 */
	public static ArrayList<Automatic> sideCoverage (Automatic auto){
		ArrayList<ArrayList<Transition>> minTestcaseOfSide = MinTestcaseOfSide(auto);
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
	 * 主要方法，求出这个时间自动机中满足迁移覆盖的最小的测试用例
	 * @param auto 传入的时间自动机中终止状态已经明确标出
	 * @return
	 */
	public static ArrayList<ArrayList<Transition>> MinTestcaseOfSide (Automatic auto){
		ArrayList<ArrayList<Transition>> ss = minSS(auto);
		System.out.println("-----------------------------------------------");
		System.out.println();
		System.out.println("经过第1个算法得到的迁移片段个数 ---   " + ss.size());
		ArrayList<ArrayList<Transition>> lastminSS = lastMinSS(ss, auto);
		System.out.println("经过第2个算法得到的迁移片段个数 ---   " + lastminSS.size());
		System.out.println("-----------------------------------------------");
		System.out.println();
		
		
		ArrayList<ArrayList<Transition>> propath = new ArrayList<ArrayList<Transition>>();
		
		
		
		/*//单独测试shortPathforStartToOne算法
		System.out.println("2次算法后的迁移片段--  " + lastminSS.get(3));
		//System.out.println("transet.get(0)--  " + transet.get(0));		
		if(lastminSS.get(3).get(0).getSource().equals(auto.getInitState().getName())){
			propath.add(lastminSS.get(3));
			System.out.println("---该片段是从初始状态开始---");
			System.out.println(lastminSS.get(3));
			for(Transition t : lastminSS.get(3)){
				System.out.print(t.getName() + "--  ");
			}
			System.out.println();
		}else{
			ArrayList<Transition> shortPath = shortPathforStartToOne(auto, lastminSS.get(3).get(0));
			System.out.println("2次算法后加入前驱的迁移片段  " + shortPath);
			for(Transition tran : lastminSS.get(3)){
				shortPath.add(tran);
			}
			propath.add(shortPath);
			System.out.println();
			System.out.println(shortPath);
			for(Transition t : shortPath){
				System.out.print(t.getName() + "--  ");
			}
			System.out.println();
		}*/
								
		//为最简组合片段加入前驱
		for(ArrayList<Transition> transet : lastminSS){
			
			System.out.println();
			System.out.println("------------------------------");
			System.out.println("2次算法后的迁移片段--  " + transet);
			if(transet.get(0).getSource().equals(auto.getInitState().getName())){
				propath.add(transet);
				System.out.println("---该片段是从初始状态开始,且路径为---");
				//System.out.println(transet);
				for(Transition t : transet){
					System.out.print(t.getName() + "--  ");
				}
				System.out.println();
			}else{
				ArrayList<Transition> shortPath = shortPathforStartToOne(auto, transet.get(0));
				//System.out.println("2次算法后加入前驱的迁移片段  " + shortPath);
				for(Transition tran : transet){
					shortPath.add(tran);
				}
				propath.add(shortPath);
				System.out.println("---加入前驱后路径--- ");
				for(Transition t : shortPath){
					System.out.print(t.getName() + "--  ");
				}
				System.out.println();
			}
			System.out.println("------------------------------");
		}
		
		System.out.println();
		System.out.println("-------------------加前驱后路径-------------------");
		System.out.println("********************************************");
		for(ArrayList<Transition> tset : propath){
			for(Transition t : tset){
				System.out.print(t.getName() + "-- ");
			}
			System.out.println();
		}
		System.out.println("********************************************");
		System.out.println();
		
		
	    //找到迁移片段到终止节点的最短路径
		for(ArrayList<Transition> transet : propath){
			if(!(findStateFromString(transet.get(transet.size()-1).getTarget(), auto).isFinalState())){
				ArrayList<Transition> lastPath = shortPathforOneToEnd(auto, transet.get(transet.size()-1));
				//System.out.println("后续片段-- " + lastPath);
				for(Transition tran : lastPath){
					transet.add(tran);
				}
			}	
		}
		System.out.println();
		System.out.println("--------------------最终路径-------------------");
		System.out.println("********************************************");
		for(ArrayList<Transition> tset : propath){
			for(Transition t : tset){
				System.out.print(t.getName() + "-- ");
			}
			System.out.println();
		}
		System.out.println("********************************************");
		System.out.println();
		return propath;
		
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
				//System.out.println("tran--  " +tran + "************");
              //  System.out.println("2--tran  " + tran + "************");
                //System.out.println("2--t前  "+T.toString()+"******************************************");
    			//System.out.println("2--ts前  " + TS +"**************************************");
				TS.add(tran);        //把选取的迁移t存入TS中，并从迁移集合T中删除该迁移t
				T.remove(tran);
				--i;
				//System.out.println("2--t  "+T.toString()+"******************************************");
				//System.out.println("2--ts  "+TS.toString()+"******************************************");
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
	 * 利用广度优先遍历的思想找到从开始节点到最短迁移片段中头结点的最短路径
	 * @param auto
	 * @param goalTransition
	 * @return
	 */
	public static ArrayList<Transition> shortPathforStartToOne(Automatic auto, Transition goalTransition){
		if(goalTransition.getSource().equals(auto.getInitState().getName())){
			return null;
		}else{
			//System.out.println("目标迁移--  " + goalTransition);
			Queue<State> queue = new LinkedList<State>();
			ArrayList<Transition> path = new ArrayList<Transition>();
			ArrayList<ArrayList<Transition>> pathset = new ArrayList<ArrayList<Transition>>();
			State initial = auto.getInitState();
			State u = new State();
			queue.add(initial);
			while(!queue.isEmpty()){
				//System.out.println();
				//System.out.println("-----------------------");
				//System.out.println("队列里的值-- " + queue);
				int finish = 0;
				u = queue.poll();
				//System.out.println("队列里的值-- " + queue);
				//System.out.println("队列弹出的状态u名称-- " + u.getName() + " -- " + u.getId());
				if(u!=null && !(u.isFinalState())){
					ArrayList<Transition> nextTranSet =  nextTranSet(u, auto);
					//System.out.println("u状态的后续迁移集合-- " + nextTranSet);
					//System.out.println("广度遍历目前所有的路径集合大小-- " + pathset.size());
					//System.out.println("-- " + pathset);
					if(pathset.size() == 0){	
						for(Transition tran : nextTranSet){
							ArrayList<Transition> t = new ArrayList<Transition>();
							t.add(tran);
							pathset.add(t);
						}	
					}else{
						int n = pathset.size();
						ArrayList<ArrayList<Transition>> teanset = new ArrayList<ArrayList<Transition>>();//存放要删除的迁移片段
						for(int i=0; i<n; i++){
							if(pathset.get(i).get(pathset.get(i).size()-1).getTarget().equals(u.getName())){//判断集合里面的元素是否是刚出栈的状态，即集合中的迁移序列能否与nextTranset中的迁移相连接
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
						//将pathset即目前广度遍历的路径 中已经与后续迁移连接的迁移删除
						for(ArrayList<Transition> t: teanset){
							pathset.remove(t);
						}
					}	
					//System.out.println("广度遍历目前所有的路径集合大小-- " + pathset.size());
					//System.out.println("广度遍历所有集合-- " + pathset);
					for(Transition tran : nextTranSet){
						if(tran.getTarget().equals(goalTransition.getSource())){
							for(int i=0; i<pathset.size(); i++){
								if(pathset.get(i).get(pathset.get(i).size()-1).equals(tran)){
									path = pathset.get(i);
									/*System.out.println("返回的目标路径-- " + path);
									for(Transition t : path){
										System.out.print(t.getName() + "-- ");
									}
									System.out.println();*/
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
	 * 利用广度优先遍历思想，找到某一个节点到其中任何一个终止节点的最短路径
	 * @param auto
	 * @param goalTransition
	 * @return
	 */
	public static ArrayList<Transition> shortPathforOneToEnd(Automatic auto, Transition goalTransition){
		if(findStateFromString(goalTransition.getTarget(), auto).isFinalState()){
			return null;
		}else{
			Queue<State> queue = new LinkedList<State>();
			ArrayList<Transition> path = new ArrayList<Transition>();
			ArrayList<ArrayList<Transition>> pathset = new ArrayList<ArrayList<Transition>>();
			//State initial = auto.getInitState();
			State start = findStateFromString((goalTransition.getTarget()),auto);
			State u = new State();
			queue.add(start);
			while(!queue.isEmpty()){
				int finish = 0;
				u = queue.poll();
				if(u!=null && !(u.isFinalState())){
					ArrayList<Transition> nextTranSet =  nextTranSet(u, auto); //获取以当前状态为源点的迁移集合
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
						if(findStateFromString(tran.getTarget(),auto).isFinalState()){
							for(int i=0; i<pathset.size(); i++){
								if(pathset.get(i).get(pathset.get(i).size()-1).equals(tran)){
									path = pathset.get(i);
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
	
	/**
	 * 寻找一条与状态X相连的状态
	 */
	public static ArrayList<String> nextStateSet(State X, Automatic a){
		ArrayList<String> StateSet = new ArrayList<>();
		for(Transition tran:a.getTransitionSet()){
			if(X.getName().equals(tran.getSource())){
				StateSet.add(tran.getTarget());
			}
		}
		return StateSet;
	}
	/**
	 * 寻找一条与状态X相连的迁移
	 */
	public static ArrayList<Transition> nextTranSet(State X, Automatic a){
		ArrayList<Transition> transition = new ArrayList<>();
		for(Transition tran:a.getTransitionSet()){
			if(X.getName().equals(tran.getSource())){
				transition.add(tran);
			}
		}
		return transition;
	}
}

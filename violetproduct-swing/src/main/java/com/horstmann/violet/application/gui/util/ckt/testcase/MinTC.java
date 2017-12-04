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
		String xml = "电梯模拟ForXStream.xml";
		//String xml = "EA4.1.0 功能场景1ForXStream.xml";
		//String xml = "arm_motors_checkForXStream.xml";
		//String xml = "compass_accumulateForXStream.xml";
		//String xml = "do_user_takeoffForXStream.xml";
		//String xml = "failsafe_battery_eventForXStream.xml";//-------------有问题
		//String xml = "failsafe_gcs_checkForXStream.xml";//-------有问题
		//String xml = "fast_loop-----display0ForXStream.xml";
		//String xml = "fast_loop-----display1ForXStream.xml";//可以看一下
		//String xml = "fast_loop-----display2ForXStream.xml";//25条 可以看一下
		//String xml = "fast_loopForXStream.xml";//29条 可以看一下
		//String xml = "gcs_check_inputForXStream.xml";//------有问题
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
		//String xml = "handleMessage-----display1ForXStream.xml";//----------2条 有问题
		//String xml = "handleMessage-----display2ForXStream.xml";//---------4条 有问题
		//String xml = "handleMessage-----display3ForXStream.xml";//--------6条 有问题
		//String xml = "handleMessage-----display4ForXStream.xml";//---------8条 有问题
		//String xml = "handleMessage-----display5ForXStream.xml";//---------10条 有问题
		//String xml = "handleMessage-----display6ForXStream.xml";//---------12条 有问题
		//String xml = "handleMessage-----display7ForXStream.xml";//----------15条 有问题
		//String xml = "handleMessageForXStream.xml";//----------17条 有问题
		//String xml = "land_gps_runForXStream.xml";
		//String xml = "land_nogps_runForXStream.xml";
		//String xml = "land_run-----display0ForXStream.xml";
		//String xml = "land_run-----display1ForXStream.xml";
		//String xml = "land_runForXStream.xml";
		//String xml = "loop-----display0ForXStream.xml";
		//String xml = "loop-----display1ForXStream.xml";//31 有问题
		//String xml = "loop-----display2ForXStream.xml";//44 有问题
		//String xml = "loop-----display3ForXStream.xml";//51 有问题
		//String xml = "loop-----display4ForXStream.xml";//92有问题
		//String xml = "loop-----display5ForXStream.xml";//136		
//		String xml = "loop-----display6ForXStream.xml";
//		String xml = "loop-----display74ForXStream.xml";
//		String xml = "loop-----display8ForXStream.xml";
//		String xml = "loop-----display9ForXStream.xml";
//		String xml = "loop-----display10ForXStream.xml";
//		String xml = "loop-----display11ForXStream.xml";
		//String xml = "loopForXStream.xml";//有问题
		
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
		
		
		Automatic automatic = GetAutomatic.getAutomatic(xml);// 获得原始的时间自动机
		//Automatic new_automatic = IPR__1.iPR(automatic);// 获得拆分后的时间自动机
		//Automatic aTDRTAutomatic = ATDTR__1.aTDRT(new_automatic, automatic);// 获得去除抽象时间迁移后的时间自动机
		//搜索终止状态集合
		/*for(State state:aTDRTAutomatic.getStateSet()) {
			int k1= 0;
			for(Transition tran:aTDRTAutomatic.getTransitionSet()){//判断目标状态是否已被访问
				if(state.getName().equals(tran.getSource())){//找出以此状态为起点的迁移
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
			for(Transition tran:automatic.getTransitionSet()){//判断目标状态是否已被访问
				if(state.getName().equals(tran.getSource())){//找出以此状态为起点的迁移
					k1=1;
				}
			}
			if(k1==0){
				state.setFinalState(true);
				//ShowInfor.print(3, "终止状态--- " + i + "---" + state);
			}else{
				state.setFinalState(false);
				////ShowInfor.print(3, "不是终止状态--- " + i + "---" + state);
			}
			i++;
		}
		*/
		
		/*//迁移覆盖(主要平台显示接口)
		ArrayList<Automatic> testCase = sideCoverage(aTDRTAutomatic);	*/

		ArrayList<Automatic> testCase = sideCoverage(automatic);	//-----------------------
		
		
		
		//输出信息
		//ShowInfor.print(3, testCase.size() + "-----测试路径的个数");//--------------------------
		for(Automatic auto : testCase){
			for(Transition tran : auto.getTransitionSet()){
				//ShowInfor.print(3, tran.getName() + "------" + tran.getTranTimeName());
			}	
			//ShowInfor.print();
			//ShowInfor.print(3, "-------");
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
	 * 主要方法，求出这个时间自动机中满足迁移覆盖的最小的测试用例
	 * @param auto 传入的时间自动机中终止状态已经明确标出
	 * @return
	 */
	public static ArrayList<ArrayList<Transition>> MinTestcaseOfSide (Automatic auto){
		
		//ShowInfor.print(3, "-------------------算法前所有迁移-------------------");
		//ShowInfor.print(3, "********************************************");
		//ShowInfor.print(3, "算法前初始状态--  " + auto.getInitState().getName());
		//ShowInfor.print(3, "算法前状态个数：  " + auto.getStateSet().size());
		//ShowInfor.print(3, "算法前迁移个数：  " + auto.getTransitionSet().size());
		//ShowInfor.print(3, "算法前迁移--  " + auto.getTransitionSet());
		//ShowInfor.print();
		
//		for(Transition tran : auto.getTransitionSet()){
//		
//			if(tran.getSource().equals(auto.getInitState().getName())){
//				//ShowInfor.print(3, "*************************************************");
//				//ShowInfor.print(3, " 初始迁移-- " + tran);
//				//ShowInfor.print(3, "*************************************************");
//				//ShowInfor.print();
//			}
//			State state= tran.getTargetState();
//					//findStateFromString(tran.getTarget(), auto);
//			if(state.isFinalState()){
//				//ShowInfor.print();
//				//ShowInfor.print(3, "========================================================");
//				//ShowInfor.print(3, " 终止状态-- " + state);
//				//ShowInfor.print(3, "              --------------------------------");
//				//ShowInfor.print(3, " 终止迁移-- " + tran);
//				//ShowInfor.print(3, "========================================================");
//			}
//		}
		//ShowInfor.print();
		//ShowInfor.print(3, "算法前所有初始状态--  " + auto.getInitState());
		//ShowInfor.print(3, "算法前所有迁移--  " );
		
		for(Transition t : auto.getTransitionSet()){
			//ShowInfor.print(3, t.getName() + "-- ");
		}
		//ShowInfor.print();
		//ShowInfor.print(3, "********************************************");
		
		//第一个算法
		ArrayList<ArrayList<Transition>> ss = minSS(auto);
		
		//ShowInfor.print();
		//ShowInfor.print(3, "-------------------1、得到的迁移片段-------------------");
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
//		//ShowInfor.print(3, "经过第1个算法得到的迁移片段个数 ---   " + ss.size());
		
		//第二格算法
		ArrayList<ArrayList<Transition>> lastminSS = lastMinSS(ss, auto);
		
		//ShowInfor.print();
		//ShowInfor.print(3, "-------------------2、约简迁移片段-------------------");
		//ShowInfor.print(3, "********************************************");
		for(ArrayList<Transition> tset : lastminSS){
			for(Transition t : tset){
				//ShowInfor.print(3, t.getName() + "-- ");
			}
			//ShowInfor.print();
		}
		//ShowInfor.print(3, "********************************************");
		
//		//ShowInfor.print(3, "经过第2个算法得到的迁移片段个数 ---   " + lastminSS.size());
//		//ShowInfor.print(3, "-----------------------------------------------");
//		//ShowInfor.print();
		
		
		ArrayList<ArrayList<Transition>> propath = new ArrayList<ArrayList<Transition>>();
		
		
		
		/*//单独测试shortPathforStartToOne算法
		//ShowInfor.print(3, "2次算法后的迁移片段--  " + lastminSS.get(3));
		////ShowInfor.print(3, "transet.get(0)--  " + transet.get(0));		
		if(lastminSS.get(3).get(0).getSource().equals(auto.getInitState().getName())){
			propath.add(lastminSS.get(3));
			//ShowInfor.print(3, "---该片段是从初始状态开始---");
			//ShowInfor.print(3, lastminSS.get(3));
			for(Transition t : lastminSS.get(3)){
				//ShowInfor.print(3, t.getName() + "--  ");
			}
			//ShowInfor.print();
		}else{
			ArrayList<Transition> shortPath = shortPathforStartToOne(auto, lastminSS.get(3).get(0));
			//ShowInfor.print(3, "2次算法后加入前驱的迁移片段  " + shortPath);
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
								
		//为最简组合片段加入前驱
		for(ArrayList<Transition> transet : lastminSS){
			
			//ShowInfor.print();
			//ShowInfor.print(3, "------------------------------");
			//ShowInfor.print(3, "2次算法后的迁移片段--  " + transet);
			if(transet.get(0).getSource().equals(auto.getInitState().getName())){
				propath.add(transet);
				//ShowInfor.print(3, "---该片段是从初始状态开始,且路径为---");
				////ShowInfor.print(3, transet);
				for(Transition t : transet){
					//ShowInfor.print(3, t.getName() + "--  ");
				}
				//ShowInfor.print();
			}else{
//				//ShowInfor.print(3, "加前驱前的首迁移------    " + transet.get(0));
				//ArrayList<Transition> shortPath = shortPathforStartToOne(auto, transet.get(0));
				//ArrayList<Transition> shortPath = addProPath(auto, transet.get(0));//----------------------------------
				ArrayList<Transition> shortPath = oneOfPathforStartToOne(auto, transet.get(0));
				//ShowInfor.print(3, "--- " + shortPath);
				
				////ShowInfor.print(3, "2次算法后加入前驱的迁移片段  " + shortPath);
				for(Transition tran : transet){
					shortPath.add(tran);
				}
				propath.add(shortPath);
				//ShowInfor.print(3, "---加入前驱后路径--- ");
				for(Transition t : shortPath){
					//ShowInfor.print(3, t.getName() + "--  ");
				}
				//ShowInfor.print();
			}
			//ShowInfor.print(3, "------------------------------");
		}
		
		//ShowInfor.print();
		//ShowInfor.print(3, "-------------------加前驱后路径-------------------");
		//ShowInfor.print(3, "********************************************");
		for(ArrayList<Transition> tset : propath){
			for(Transition t : tset){
				//ShowInfor.print(3, t.getName() + "-- ");
			}
			//ShowInfor.print();
		}
		//ShowInfor.print(3, "********************************************");
		
		
	    //找到迁移片段到终止节点的最短路径
		for(ArrayList<Transition> transet : propath){
			//ShowInfor.print(3, "                      ----------------------------------------");
			////ShowInfor.print(3, auto.getInitState());
			//ShowInfor.print(3, "====目标状态====" + transet.get(transet.size()-1).getTargetState());
			//ShowInfor.print(3, "                      ----------------------------------------");
			if(!(transet.get(transet.size()-1).getTargetState().isFinalState())){
				//ShowInfor.print(3, "====需要加后续片段====");
			//if(!(findStateFromString(transet.get(transet.size()-1).getTarget(), auto).isFinalState())){
				//ArrayList<Transition> lastPath = shortPathforOneToEnd(auto, transet.get(transet.size()-1));//----------------------------------
				//ArrayList<Transition> lastPath = addPostPath(auto, transet.get(transet.size()-1));
				ArrayList<Transition> lastPath = oneOfPathforOneToEnd(auto, transet.get(transet.size()-1));
				//ShowInfor.print(3, "后续片段-- " + lastPath);
				for(Transition tran : lastPath){
					transet.add(tran);
				}
			}	
		}
		//ShowInfor.print();
		//ShowInfor.print(3, "--------------------最终路径-------------------");
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
			//ShowInfor.print(3, "T.size-- "+ T.size());
			////ShowInfor.print(3, "1--t  "+T.toString()+"******************************************");
			////ShowInfor.print(3, "1--ts  " + TS +"**************************************");
			for(int i=0; i<T.size(); i++){//从迁移集合T中选取一个单迁移t
				//ShowInfor.print(3, "i----  " + i);
				Transition tran = T.get(i);
				////ShowInfor.print(3, "tran--  " +tran + "************");
              //  //ShowInfor.print(3, "2--tran  " + tran + "************");
                ////ShowInfor.print(3, "2--t前  "+T.toString()+"******************************************");
    			////ShowInfor.print(3, "2--ts前  " + TS +"**************************************");
				TS.add(tran);        //把选取的迁移t存入TS中，并从迁移集合T中删除该迁移t
				T.remove(tran);
				--i;
				////ShowInfor.print(3, "2--t  "+T.toString()+"******************************************");
				////ShowInfor.print(3, "2--ts  "+TS.toString()+"******************************************");
				////ShowInfor.print(3, TS.size()-1);
				////ShowInfor.print(3, TS.get(0));
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
	 * 最小测试成本迁移覆盖算法
	 * @param SS 优化迁移覆盖算法的输出集合SS
	 * @return 满足M的最小测试成本迁移覆盖的序列集合SS1
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
				if(TSk.size() == 1){//TSk里只有一条迁移
					for(int i=0;i<SS1.size();i++){
						ArrayList<Transition> TSi = SS1.get(i);
						for(Transition t : TSi){
							if(t.equals(TSk.get(0))){
								f = 1; //若TSk中只有一条迁移，且在SS1中找到，则不用与SS1中做判断
								//ShowInfor.print(3, "TSk中只有一条迁移，且在SS1中找到");
							}
						}
					}
				}
				if((f==0) || (TSk.size() > 1)){
					//ShowInfor.print(3, " SS1.size--  " + SS1.size()+"--------------------------------------------------------");
					ArrayList<Transition> TS = new ArrayList<Transition>();//存放TSK与TSi中连接的结果
					
					//对TSK与SS1中所有片段进行组合
					int finish = 0;
					for(int i=0;i<SS1.size();i++){
						ArrayList<Transition> TSi = SS1.get(i);
						////ShowInfor.print(3, SS1.size());
						//ShowInfor.print(3, " TSk--  " + TSk);
						//ShowInfor.print(3, " TSi--  " + TSi);
						//ShowInfor.print(3, "SS1.get(i)--  " + i);
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
							//ShowInfor.print(3, "TSk 与 TSi 相交  *************************" );
							
							if(TSi.get(0).getSource().equals(TSk.get(TSk.size()-1).getTarget())){//TSk能连接TSi
								//ShowInfor.print(3, "TSk能连接TSi  *************************" );
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
									//ShowInfor.print(3, "TSi能连接TSk  *************************" );
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
										//ShowInfor.print(3, "TSk或者TSi是回路  *************************" );
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
										//ShowInfor.print(3, "TSk或者TSi不是回路  *************************" );
										TS = TSk;
										//SS1.add(TSk);
									}
								}
							}
						}else{
							//如果TSk与TSi不想交，那么吧TSK合并到SS1中
							//ShowInfor.print(3, "TSk与TSi不相交  *************************" );
							//if(!(TSi.get(0).getSource().equals(TSk.get(TSk.size()-1).getTarget())) //TSk与TSi不相交
								//	&& !(TSi.get(TSi.size()-1).getTarget().equals(TSk.get(0).getSource()))){
							if(cross == 0){
								//如果TSK与TSi不相交，则判断是否存在一条迁移能把两条迁移连接再一起
								int flag = 0;
								for(Transition tran1 : auto.getTransitionSet()){
									//TSK + 一条迁移  + TSi
									if((tran1.getSource().equals(TSk.get(TSk.size()-1).getTarget())) && (tran1.getTarget().equals(TSi.get(0).getSource()))){
										//ShowInfor.print(3, "TSK + 一条迁移  + TSi  *************************" );
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
											//ShowInfor.print(3, "TSi + 一条迁移  + TSk  *************************" );
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
									//ShowInfor.print(3, "TSi 与 TSk不相交  且两者无法用一条迁移连接  *************************" );
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
	 * 利用广度优先遍历的思想找到从开始节点到最短迁移片段中头结点的最短路径
	 * @param auto
	 * @param goalTransition
	 * @return
	 */
	public static ArrayList<Transition> shortPathforStartToOne(Automatic auto, Transition goalTransition){
		if(goalTransition.getSourceState().equals(auto.getInitState())){
			return null;
		}else{
			////ShowInfor.print(3, "目标迁移--  " + goalTransition);
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
				//ShowInfor.print(3, "队列里的值-- " + queue);
				int finish = 0;
				u = queue.poll();
				////ShowInfor.print(3, "队列里的值-- " + queue);
				////ShowInfor.print(3, "队列弹出的状态u名称-- " + u.getName() + " -- " + u.getId());
				if(u!=null && !(u.isFinalState())){
					//ShowInfor.print(3, "----"  + u.getNextTranSet());
					ArrayList<Transition> nextTranSet =  removeTran(u, u.getNextTranSet());
					//ArrayList<Transition> nextTranSet =  nextTranSet(u, auto);
					////ShowInfor.print(3, "u状态的后续迁移集合-- " + nextTranSet);
					////ShowInfor.print(3, "广度遍历目前所有的路径集合大小-- " + pathset.size());
					////ShowInfor.print(3, "-- " + pathset);
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
						//将pathset即目前广度遍历的路径 中已经与后续迁移连接的迁移删除
						for(ArrayList<Transition> t: teanset){
							pathset.remove(t);
						}
					}	
					////ShowInfor.print(3, "广度遍历目前所有的路径集合大小-- " + pathset.size());
					////ShowInfor.print(3, "广度遍历所有集合-- " + pathset);
					for(Transition tran : nextTranSet){
						if(tran.getTarget().equals(goalTransition.getSource())){
							//for(int i=0; i<pathset.size(); i++){
							for(int i=pathset.size()-1; i>=0; i--){
								//============================================
								if(pathset.get(i).get(pathset.get(i).size()-1).equals(tran) /*&& pathset.get(i).get(0).getSource().equals(auto.getInitState().getName())*/){
									path = pathset.get(i);
									/*//ShowInfor.print(3, "返回的目标路径-- " + path);
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
	 * 利用广度优先遍历思想，找到某一个节点到其中任何一个终止节点的最短路径
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
					//ShowInfor.print(3, "现在正在访问状态--   " + u);
					//ShowInfor.print(3, "现在正在访问状态前驱--   " + u.getProTranSet());
					//ShowInfor.print(3, "现在正在访问状态后续--   " + u.getNextTranSet());
					//ShowInfor.print(3, "现在正在访问状态是否是终止状态--   " + u.isFinalState());
					ArrayList<Transition> nextTranSet =  removeTran(u, u.getNextTranSet());
					//ShowInfor.print(3, "现在正在访问状态后续--   " + nextTranSet);
							//nextTranSet(u, auto); //获取以当前状态为源点的迁移集合
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
				//ShowInfor.print(3, "第 " + i + " 次查找");
				//State state = findStateFromString(sourceName, auto);
				Transition proTran = oneOfproTranSet(sourceName, auto);
				//ShowInfor.print(3, "前驱迁移 --  " + proTran);
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
	 * 利用广度优先遍历思想，找到某一个节点到其中任何一个终止节点
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
	 * 根据状态名称找到状态
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
	 * 寻找一条与状态X相连的状态
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
	 * 寻找一条与状态X相连的迁移
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
	 * 寻找一条与状态X相连的迁移
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
	 * 反向
	 * @param auto
	 * @param goalTransition
	 * @return
	 */
	public static ArrayList<Transition> addProPath(Automatic auto, Transition goalTransition){
		if(goalTransition.getSource().equals(auto.getInitState().getName())){
			return null;
		}else{
			////ShowInfor.print(3, "目标迁移--  " + goalTransition);
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
				////ShowInfor.print(3, "队列里的值-- " + queue);
				int finish = 0;
				u = queue.poll();
				////ShowInfor.print(3, "队列里的值-- " + queue);
				////ShowInfor.print(3, "队列弹出的状态u名称-- " + u.getName() + " -- " + u.getId());
				//if(u!=null && !(u.isFinalState())){
				if(u!=null && !(u.equals(auto.getInitState()))){
					//ArrayList<Transition> nextTranSet =  nextTranSet(u, auto);
					ArrayList<Transition> proTranSet =  u.getProTranSet();
							//proTranSet(u, auto);
					////ShowInfor.print(3, "u状态的后续迁移集合-- " + nextTranSet);
					////ShowInfor.print(3, "广度遍历目前所有的路径集合大小-- " + pathset.size());
					////ShowInfor.print(3, "-- " + pathset);
					if(pathset.size() == 0){	
						for(Transition tran : proTranSet){
							ArrayList<Transition> t = new ArrayList<Transition>();
							t.add(tran);
							pathset.add(t);
						}	
					}else{
						int n = pathset.size();
						ArrayList<ArrayList<Transition>> teanset = new ArrayList<ArrayList<Transition>>();//存放要删除的迁移片段
						for(int i=0; i<n; i++){
							//if(pathset.get(i).get(pathset.get(i).size()-1).getTarget().equals(u.getName())){//判断集合里面的元素是否是刚出栈的状态，即集合中的迁移序列能否与proTranset中的迁移相连接
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
						//将pathset即目前广度遍历的路径 中已经与后续迁移连接的迁移删除
						for(ArrayList<Transition> t: teanset){
							pathset.remove(t);
						}
					}	
					////ShowInfor.print(3, "广度遍历目前所有的路径集合大小-- " + pathset.size());
					////ShowInfor.print(3, "广度遍历所有集合-- " + pathset);
					for(Transition tran : proTranSet){
						//if(tran.getTarget().equals(goalTransition.getSource())){
						if(tran.getSource().equals(auto.getInitState().getName())){//迁移的源状态是初始节点
							for(int i=0; i<pathset.size(); i++){
								//============================================
								if(pathset.get(i).get(pathset.get(i).size()-1).equals(tran) && pathset.get(i).get(0).getTarget().equals(goalTransition.getSource())){
									path = pathset.get(i);
									for(int j=path.size()-1; j>=0; j--){
										path1.add(path.get(j));
									}
									/*//ShowInfor.print(3, "返回的目标路径-- " + path);
									for(Transition t : path){
										//ShowInfor.print(3, t.getName() + "-- ");
									}
									//ShowInfor.print();*/
							        return path1;
								}
							}
						}else{//迁移的源状态不是初始节点，表明迁移是中间迁移
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
			////ShowInfor.print(3, "目标迁移--  " + goalTransition);
			Queue<State> queue = new LinkedList<State>();
			ArrayList<Transition> path = new ArrayList<Transition>();
			ArrayList<Transition> path1 = new ArrayList<Transition>();
			ArrayList<ArrayList<Transition>> pathset = new ArrayList<ArrayList<Transition>>();
			//State initial = auto.getInitState();
			
			State start = goalTransition.getTargetState();
					//findStateFromString(goalTransition.getSource(), auto);
			//ShowInfor.print(3, "==目标状态：  " + start);
			State u = new State();
			//queue.add(initial);
			queue.add(start);
			while(!queue.isEmpty()){
				////ShowInfor.print();
				////ShowInfor.print(3, "-----------------------");
				//ShowInfor.print(3, "队列里的值-- " + queue);
				int finish = 0;
				u = queue.poll();
				////ShowInfor.print(3, "队列里的值-- " + queue);
				////ShowInfor.print(3, "队列弹出的状态u名称-- " + u.getName() + " -- " + u.getId());
				//if(u!=null && !(u.isFinalState())){
				if(u!=null && !(u.isFinalState())){
					ArrayList<Transition> nextTranSet =  removeTran(u, u.getNextTranSet());
					//ArrayList<Transition> proTranSet =  u.getProTranSet();
							//proTranSet(u, auto);
					//ShowInfor.print(3, nextTranSet.size()+"");
					////ShowInfor.print(3, pathset);
					//ShowInfor.print(3, "u状态的后续迁移集合-- " + nextTranSet);
					////ShowInfor.print(3, "广度遍历目前所有的路径集合大小-- " + pathset.size());
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
						//ShowInfor.print(3, " ====pathset.size()！=0 ====  ");
						int n = pathset.size();
						ArrayList<ArrayList<Transition>> teanset = new ArrayList<ArrayList<Transition>>();//存放要删除的迁移片段
						for(int i=0; i<n; i++){
							if(pathset.get(i).get(pathset.get(i).size()-1).getTarget().equals(u.getName())){//判断集合里面的元素是否是刚出栈的状态，即集合中的迁移序列能否与proTranset中的迁移相连接
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
						//将pathset即目前广度遍历的路径 中已经与后续迁移连接的迁移删除
						for(ArrayList<Transition> t: teanset){
							pathset.remove(t);
						}
					}	
					////ShowInfor.print(3, "广度遍历目前所有的路径集合大小-- " + pathset.size());
					////ShowInfor.print(3, "广度遍历所有集合-- " + pathset);
					for(Transition tran : nextTranSet){
						//if(tran.getTarget().equals(goalTransition.getSource())){
						if(tran.getTargetState().isFinalState()){//迁移的源状态是初始节点
							for(int i=pathset.size()-1; i>=0; i--){
								//============================================
								if(pathset.get(i).get(pathset.get(i).size()-1).equals(tran) && pathset.get(i).get(0).getTarget().equals(goalTransition.getSource())){
									path1 = pathset.get(i);
									/*for(int j=path.size()-1; j>=0; j--){
										path1.add(path.get(j));
									}*/
									/*//ShowInfor.print(3, "返回的目标路径-- " + path);
									for(Transition t : path){
										//ShowInfor.print(3, t.getName() + "-- ");
									}
									//ShowInfor.print();*/
							        return path1;
								}
							}
						}else{//迁移的源状态不是初始节点，表明迁移是中间迁移
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
	 * 寻找一条以状态X为终止节点的迁移集合
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
	 * 寻找一条以状态X为终止节点的迁移集合
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
	
	
	
	
	
	
	/*  // 从tran开始到finalState的xxxxxxxxxxx条路径directPaths
		private static boolean dfsPathFromStartToEnd(Transition tran, State finalState, int[] visited,
				ArrayList<Transition> onePath, HashSet<ArrayList<Transition>> directPaths) {
			State targetState = tran.getTargetState();
			visited[tran.getId()] = 1;
			onePath.add(tran);
			if (targetState == finalState) { // 到达目标节点
				directPaths.add(new ArrayList<>(onePath));
				//ShowInfor.print(3, "得到一条路径到状态" +finalState.getId() + "----");
				for(Transition tran1 : onePath) {
					//ShowInfor.print(3, tran1.getName());
				}
				onePath.remove(tran);
				visited[tran.getId()] = 0;
			}
			ArrayList<Transition> nextTrans = new ArrayList<>();
			for(Transition nextTran : transitions) {// 根据概率生成下一个tran
				State sourceStateNow = nextTran.getSourceState();
				State targetStateNow = nextTran.getTargetState();
				if (targetStateNow.getId() > finalState.getId() ) { // 超出终止节点的id
				} 
				
				if (sourceStateNow == targetState && visited[nextTran.getId()] == 0) { // 为连接的两条迁移
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

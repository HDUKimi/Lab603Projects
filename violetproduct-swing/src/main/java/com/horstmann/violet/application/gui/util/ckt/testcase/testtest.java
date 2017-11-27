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

	}
	/**
	 * 测试第一个算法
	 * @param auto
	 * @return
	 */
	public static ArrayList<Automatic> test1(Automatic auto){
		ArrayList<ArrayList<Transition>> minSS = minSS(auto);
		ArrayList<Automatic> test1 = sideCoverage(auto, minSS);
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

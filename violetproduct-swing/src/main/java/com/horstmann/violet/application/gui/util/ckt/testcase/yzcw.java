package com.horstmann.violet.application.gui.util.ckt.testcase;
//验证错误

import java.util.ArrayList;

import com.horstmann.violet.application.gui.util.ckt.handle.*;


public class yzcw {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String xml="loopForXStream3.13.xml";
		String xml="Package2ForXStream.xml";//简单图  验证是否能出来！
		Automatic auto=GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
		Automatic automatic=AddType.addType(auto);
		/*//ArrayList<State> new_stateSet=Minimization__1.minimization(automatic);
		Automatic new_automatic=IPR__1.iPR(automatic);//获得拆分后的时间自动机
		Automatic aTDRTAutomatic=ATDTR__1.aTDRT(new_automatic,automatic);//获得去除抽象时间迁移后的时间自动机
		//Automatic DFStree=StateCoverage__1.DFSTree(aTDRTAutomatic);
		ArrayList<Automatic> testCase=StateCoverage__1.testCase(aTDRTAutomatic);//获得满足状态覆盖的抽象测试序列
		ArrayList<ArrayList<String>> all_inequalitys=Get_inequality__1.get_AllInequalitys(testCase);//每个抽象测试序列有一个不等式组
		*/
		ArrayList<Automatic> testCase=StateCoverage__1.testCase(automatic);//获得满足状态覆盖的抽象测试序列
		System.out.println("时间自动机名字:"+automatic.getName());						
		System.out.println("总迁移个数"+automatic.getTransitionSet().size());
	/*	int p=0;
		for(Transition tran:automatic.getTransitionSet()){
			System.out.println("第"+p+"条迁移");
			p++;			
			System.out.println("源:"+tran.getSource());
			System.out.println("目的："+tran.getTarget());						
			if(tran.getEventSet()==null){
//				System.out.println("事件为空");
			}
			else if(tran.getEventSet().size()==0){
//				System.out.println("事件为不空，但是没有内容");
			}
			else{
				ArrayList<String> events=tran.getEventSet();
				for(String e:events){
					System.out.println("事件："+e);
				}
				String con=tran.getCondition();///添加/////////////
				System.out.println("事件里con："+con);////添加/////////////
			}
			System.out.println("********************");
		}*/
		
		
		
		
		
		System.out.println("抽象测试序列个数："+testCase.size());
		for(Automatic a:testCase){			
			System.out.println("测试用例名字:"+a.getName());						
			System.out.println("迁移个数"+a.getTransitionSet().size());
			int pp=0;
			for(Transition tran:a.getTransitionSet()){
				System.out.println("第"+pp+"条迁移");
				pp++;				
				System.out.println("源:"+tran.getSource());
				System.out.println("目的："+tran.getTarget());								
				if(tran.getEventSet()==null){
					System.out.println("事件为空");
				}
				else if(tran.getEventSet().size()==0){
					System.out.println("事件为不空，但是没有内容");
				}
				else{
					ArrayList<String> events=tran.getEventSet();
					for(String e:events){
						System.out.println("事件："+e);
					}
					String con=tran.getCondition();///添加/////////////
					System.out.println("事件里con："+con);////添加/////////////
				}								
				System.out.println("********************");
			}
//			System.out.println("_________________");
			//源:loc_id_5d0a332f-7daf-43c1-9f08-1d0ba3c5466d
			//目的：loc_id_b85aeaf4-c9dd-4eb8-b49f-ea6f28e89e24_5
			/////////////////////////////////////////////////////////////////////////////
			
//			for(Transition tran:a.getTransitionSet()){
//				System.out.println(/*tran.getSource()+"---->"+tran.getTarget()+"约束： "+*/tran.getEventSet());
//			}
			System.out.println("*********************************");
		}
		
		///////////////////////////////////////////////////////////////////////////////
		
		
		/*System.out.println("总共"+all_inequalitys.size()+"个不等式组");
		int e=1;
		for(ArrayList<String> inequalitys:all_inequalitys){
			System.out.println("第"+e+"个不等式组");
			for(String s:inequalitys){
				System.out.println(s);
			}
			System.out.println("***************");
			e++;
		}*/
		
	}

}

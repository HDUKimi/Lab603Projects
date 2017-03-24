package com.horstmann.violet.application.gui.util.ckt.testcase;
//第7个例子，5个测试用例
import java.util.ArrayList;

import org.junit.Test;

import com.horstmann.violet.application.gui.util.ckt.handle.*;
import com.wolfram.jlink.KernelLink;
import com.wolfram.jlink.MathLinkException;
import com.wolfram.jlink.MathLinkFactory;

public class Test__6 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String xml="UAV2ForXStream.xml";//第7个例子，5个测试用例
		//String xml="read_radioForXStream.xml";
		/*String xml="Draw MoneyForXStream(2).xml";
		Automatic auto=GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
		ArrayList<Automatic> testCase=StateCoverage__1.testCase(auto);//获得满足状态覆盖的抽象测试序列
*/		
		String xml="Draw MoneyForXStream(2).xml";
		Automatic automatic=GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
		Automatic new_automatic=IPR__1.iPR(automatic);//获得拆分后的时间自动机
		Automatic aTDRTAutomatic=ATDTR__1.aTDRT(new_automatic,automatic);//获得去除抽象时间迁移后的时间自动机
		ArrayList<Automatic> testCase=StateCoverage__1.testCase(aTDRTAutomatic);//获得满足状态覆盖的抽象测试序列
		
		System.out.println("测试用例名字:"+automatic.getName());
		for(Transition tran:automatic.getTransitionSet()){
			System.out.println(tran.getId()+"---"+tran.getName());
		}
		System.out.println("*************************************************");
		System.out.println("测试用例名字:"+new_automatic.getName());
		for(Transition tran:new_automatic.getTransitionSet()){
			System.out.println(tran.getId()+"---"+tran.getName());
		}
		System.out.println("*************************************************");
		System.out.println("测试用例名字:"+aTDRTAutomatic.getName());
		for(Transition tran:aTDRTAutomatic.getTransitionSet()){
			System.out.println(tran.getId()+"---"+tran.getName());
		}
		System.out.println("*************************************************");
		
		System.out.println("抽象测试序列个数："+testCase.size());
		for(Automatic a:testCase){
			
			System.out.println("测试用例名字:"+a.getName());

            int k=0;
			for(Transition tran:a.getTransitionSet()){
				//System.out.println("激励名称"+tran.getName());
				//System.out.println(tran.getSource()+"---->"+tran.getTarget()+"约束： "+tran.getEventSet());
			//未处理的约束条件	
				System.out.println(++k+"---"+"约束："+tran.getEventSet());
				String s[] = tran.getEventSet().toString().replace("[", "").replace("]", "").split(",");
				System.out.println(tran.getId()+"----"+tran.getName()+"----"+s[s.length-1].trim());
				//int i = tran.getEventSet().size();
				//System.out.println("  "+k+"---"+tran.getEventSet().get(i-1).substring(tran.getEventSet().get(i-1).length()-2,tran.getEventSet().get(i-1).length()));
				/*for(int i=0;i<tran.getEventSet().size();i++){
					System.out.println(i+"---"+tran.getEventSet().get(i).substring(tran.getEventSet().get(i).length()-2,tran.getEventSet().get(i).length()));
				    //System.out.println("-----");
				}*/
				
			//数字型不等式和参数
			/*	String bds1=Get_str.get_bds(tran.getEventSet().toString());
				System.out.println(bds1);
				String cs1=Get_str.get_cs(bds1);
				System.out.println(cs1);
				//System.out.println("bds---------->"+bds);
              //布尔型不等式和参数
				String boolbds=Get_str.get_bool_bds(tran.getEventSet().toString());
				System.out.println(boolbds);
				String boolcs=Get_str.get_bool_cs(boolbds);
				System.out.println(boolcs);
			 //==0的不等式即为解 ==换为=
				String bds0=Get_str.get_bds_0(tran.getEventSet().toString());
			
			//调用mma软件求解方程组
				if((bds1!=null)&&(cs1!=null)){
					String solution1 = Mathematica.getSolution2(bds1, cs1);
					System.out.println("$$$$$$$$$$$$$$$$$$"+solution1);
				}
				if((boolbds!=null)&&(boolcs!=null)){
					String solution2 = Mathematica.getSolution3(boolbds, boolcs);
					System.out.println("##################"+solution2);
				}
			//调用mma软件求解方程组
				//String solution1 = Mathematica2.getSolution2(bds1, cs1);
				//String solution2 = Mathematica2.getSolution3(boolbds, boolcs);
				*/
				
			}
			System.out.println("*********************************");
		}
		
		
	
	}

}


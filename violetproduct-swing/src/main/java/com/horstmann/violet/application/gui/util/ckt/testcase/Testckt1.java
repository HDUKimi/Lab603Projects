package com.horstmann.violet.application.gui.util.ckt.testcase;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;

import com.horstmann.violet.application.gui.util.ckt.handle.Automatic;
import com.horstmann.violet.application.gui.util.ckt.handle.GetAutomatic;
import com.horstmann.violet.application.gui.util.ckt.handle.StateCoverage__1;
import com.horstmann.violet.application.gui.util.ckt.handle.Transition;

public class Testckt1 {

	public static void main(String[] args) {
		//读取的xml文件
		String xml="UAVForXStream3.2.1.xml";//有时间约束
		
		//第一步 测试是否含有时钟
		Automatic auto=GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
		/*if((auto.getClockSet().toString().equals("[]"))){
			System.out.println("1-->此时间自动机无时间约束");
		}else{
			System.out.println();
			System.out.println("1-->此时间自动机有时间约束");
			System.out.print("    时间自动机时钟集合：");
			for(String c:auto.getClockSet()){
				System.out.print(" "+c+"  ");
			}
			System.out.println();
		}*/
		//System.out.println("----------------------------------");
		ArrayList<Automatic> testCase=StateCoverage__1.testCase(auto);//获得满足状态覆盖的抽象测试序列
		System.out.println("抽象测试序列个数："+testCase.size());
		//第二步 查看in里条件	
		for(Automatic a:testCase){
			System.out.println("----------------------------------");
			System.out.println("测试用例名字:"+a.getName());			
			for(Transition tran:a.getTransitionSet()){	
				System.out.println("约束："+tran.getEventSet());
				System.out.println("2-->in:"+tran.getIn());	//in里面的内容	
				System.out.println("3-->condition:"+tran.getCondition());//condition里面的内容
				//System.out.println("----------------------------------");
			}
		}
		//第三步 查看condition里条件			
		/*for(Automatic a:testCase){
			System.out.println("测试用例名字:"+a.getName());
			System.out.println("----------------------------------");
			for(Transition tran:a.getTransitionSet()){
				//System.out.println("----------------------------------");			
				System.out.println("3-->condition:"+tran.getCondition());//condition里面的内容
				if(!tran.getCondition().equals("null")){
					tran.getCondition().replaceAll("false", "False").replaceAll("true", "True");				
					if(tran.getCondition().contains("/")){
						//System.out.println("condition/---->"+conditonValue);
					}else{
						if(!tran.getCondition().contains("/")){
							//System.out.println("condition---->"+tran.getCondition());							
						}
					}
				}
				else{
					if(tran.getCondition().equals("null")){
						//System.out.println("condition--null-->"+tran.getCondition());	
					}
				}
			}
		}*/
	}
}

package com.horstmann.violet.application.gui.util.ckt.handle;
//第六个例子，四个测试用例
import java.util.ArrayList;

public class Test_5 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String xml="read_radioForXStream.xml";
		Automatic automatic=GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
		Automatic auto = AddType.addType(automatic);
		
		ArrayList<Automatic> testCase=StateCoverage__1.testCase(auto);//获得满足状态覆盖的抽象测试序列

			int i=1;	
		System.out.println("抽象测试序列个数："+testCase.size());
		for(Automatic a:testCase){
			
			System.out.println("测试用例名字:"+a.getName());
			
		/*	for(Transition tran:a.getTransitionSet()){
				System.out.println(tran.getName()+"----********----迁移边名称");
				System.out.println(tran.getSource()+"---->"+tran.getTarget()+"约束： "+tran.getEventSet());
				
				//System.out.println("第二个输出：i等于"+i+"----"+Get_yueshu.get_yueshu(tran.getEventSet().toString()));
				//System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				//i++;
			}*/
			for(Transition tran:a.getTransitionSet()){
				System.out.println(/*tran.getSource()+"---->"+tran.getTarget()+*/"第一个输出：约束： "+tran.getEventSet());
				System.out.println(tran.getName()+"----********----迁移边名称");
				//System.out.println(/*"第二个输出：i等于"+i+"----"+*/Get_yueshu.get_yueshu(tran.getEventSet().toString()));
				//System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				//i++;
			}
			System.out.println("*********************************");
		}
	}

}


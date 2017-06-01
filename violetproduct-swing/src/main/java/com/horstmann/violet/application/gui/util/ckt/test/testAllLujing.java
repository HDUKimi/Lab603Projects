package com.horstmann.violet.application.gui.util.ckt.test;

import java.util.ArrayList;
import com.horstmann.violet.application.gui.util.ckt.handle.*;


public class testAllLujing {
	public static void main(String[] args) {
		String xml = "EAElevatorV1ForXStream.xml";
		Automatic automatic = GetAutomatic.getAutomatic(xml);// 获得原始的时间自动机
		//Automatic new_automatic = IPR__1.iPR(automatic);// 获得拆分后的时间自动机
		//Automatic aTDRTAutomatic = ATDTR__1.aTDRT(new_automatic, automatic);// 获得去除抽象时间迁移后的时间自动机
		//ArrayList<Automatic> testCase = StateCoverage__1.testCase(aTDRTAutomatic);// 获得满足状态覆盖的抽象测试序列
		ArrayList<Automatic> testCase = path.testcase(automatic);
		System.out.println(testCase.size()+"-----测试路径的个数");
	}	
	
}

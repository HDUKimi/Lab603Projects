package com.horstmann.violet.application.gui.util.wj.util;

import java.io.File;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import com.horstmann.violet.application.gui.util.ckt.handle.*;
import com.horstmann.violet.application.gui.util.ckt.testcase.*;
import com.horstmann.violet.application.gui.util.wj.bean.*;

import Jama.Matrix;

public class Test {
	public static void main(String args[]) throws Exception {
		/*SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long time1=System.currentTimeMillis();  
		String TimeString = time.format(new java.util.Date());
		System.out.println(TimeString);
		
		// 用法
		Automatic automatic = GetAutomatic.getAutomatic("UAVForXStream.xml");
		GeneratePath.getFormatPathFromAutomatic(automatic, 8000, 0);
//		GeneratePath.getPerformPathFromAutomatic(automatic);
		

		TimeString = time.format(new java.util.Date());
		System.out.println(TimeString);*/
		
		for(int i=0;i<10;i++){
			
			String xml = "D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\FunctionalTest\\EA4.1.0 功能场景1ForXStream.xml";
			Automatic auto=GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
//			ArrayList<Automatic> testCase=GeneratePath.getFormatPathFromAutomatic(auto, 100, 0);//获得满足路径覆盖的抽象测试序列
//			System.err.println("----------------------------------------------");
//			System.out.println("测试路径个数"+testCase.size());
//	        for(Automatic a:testCase){
//	        	System.out.println(a.getName());
//	        }
//	        System.err.println("----------------------------------------------");
	        ArrayList<Automatic> testCase1=GeneratePath.getFormatPathFromAutomatic(auto, 100, 1);
	        ArrayList<Automatic> testCase2 = GeneratePath.getBeforeSort();
	        System.out.println("测试路径个数"+testCase1.size());
//	        for(Automatic a:testCase1){
//	        	System.out.println(a.getName());
//	        }
//	        System.out.println("=========================");
//	        for(Automatic a:testCase2){
//	        	System.out.println(a.getName());
//	        }
	        System.out.println("===========END===========");
	        
	        Thread.sleep(1000);
	        
//	        auto=GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
//			ArrayList<Automatic> testCase=GeneratePath.getFormatPathFromAutomatic(auto, 100, 0);//获得满足路径覆盖的抽象测试序列
//			System.err.println("------------------------2----------------------");
//			System.out.println("测试路径个数"+testCase.size());
//	        for(Automatic a:testCase){
//	        	System.out.println(a.getName());
//	        }
//	        System.err.println("------------------------*----------------------");
			
		}
		
	}
}

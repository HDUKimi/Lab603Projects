package com.horstmann.violet.application.gui.util.chengzuo.Verfication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestCaseUtil {

	public static List<TestCase> string2TestCaseList(String stringData){
		List<TestCase> testCaseList = new ArrayList<TestCase>();
		
		//按照 \n*进行划分
		String[] stringContent = stringData.split("\n\\*");
		for (String tmp : stringContent) {
			TestCase testcase = new TestCase();
			//获取每一行
			String[] con = tmp.split("\n");
			int strlen = 0;
			//获取用例ID
			strlen = "测试用例ID: ".length();
			testcase.setTestCaseID(con[0].substring(strlen));
			//构造激励链表
			String s = "";
			for (int i = 2; i < con.length - 2; i++) {
				strlen = " √ 激励ID : 1 激励名称 : ".length();
				s += con[i].substring(strlen+1);
			}
			testcase.setContent(s);
			//设置执行状态
			strlen = " -->测试执行状态: [ ".length();
			testcase.setState(con[con.length - 2].substring(strlen, con[con.length - 2].length() - 2));
			//设置结果状态
			strlen = " -->结果状态: [ ".length();
			testcase.setResult(con[con.length - 1].substring(strlen, con[con.length - 1].length() - 2));
			//设置整个激励内容
			testcase.setDetail(tmp);
			Date t = new Date();
			System.out.println(t + " : " + testcase);
			testCaseList.add(testcase);
		}
		
		return testCaseList;
	}
	
	public static void string2File(String fileName,String fileContent) throws IOException{
		// 文件内容编码转换 GBK -> UTF-8
		fileContent = new String(fileContent.getBytes("GBK"), "UTF-8");

		File file = new File(fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		int len = fileContent.getBytes().length;
		byte bytes[] = new byte[len];
		bytes = fileContent.getBytes();
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(bytes, 0, len);
		fos.close();
	}
	
}

package com.horstmann.violet.application.gui.util.chengzuo.Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.horstmann.violet.application.gui.util.chengzuo.Bean.TestCase;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.TestCaseResult;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.myProcess;

/**
 * 测试用例 操作工具类
 * 
 * @author geek
 */
public class TestCaseConvertUtil {

	/**
	 * 对字符串进行 正则匹配，获取结果
	 * 
	 * @param current 匹配串
	 * @param regEx 正则表达式
	 * @return
	 */
	public static List<String> stringRegEx(String current, String regEx) {
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(current);
		List<String> result = new ArrayList<String>();
		while (mat.find()) {
			result.add(mat.group(1).replace("[", " ").replace("]", " ").trim());
		}
		return result;
	}

	/**
	 * 将激励链表字符串转换成激励链表实体
	 * 
	 * @param processList  激励链表字符串
	 * @return
	 */
	public static List<myProcess> string2ProcessList(String processList) {
		List<myProcess> list = new ArrayList<myProcess>();
		// 1.划分各个激励链表字符串
		String[] tmp = processList.split("\\)");
		// 2.对每个激励节点进行处理
		for (String process : tmp) {
			// 2.1.字符串格式处理
			process += ")";
			process = process.trim();
			// 2.2. 激励节点生成
			myProcess my = new myProcess();
			// 激励 是否成功执行 # 表示成功，& 表示失败
			if (process.contains("#")) {
				my.setProcessExec(true);
			} else {
				my.setProcessExec(false);
			}
			// 2.3.激励ID
			my.setProcessID(Integer.parseInt(stringRegEx(process, "ProcessID:([\\s|\\S]*?)ProcessName:").get(0)));
			// 2.4.激励Name
			my.setProcessName(stringRegEx(process, "ProcessName:([\\s|\\S]*?)\\(").get(0));
			// 2.5.激励参数
			my.setProcessParam(stringRegEx(process, "ProcessParameter:([\\s|\\S]*?)ProcessStatus:").get(0));
			// 2.6.激励状态
			my.setProcessStatus(stringRegEx(process, "ProcessStatus:([\\s|\\S]*?)\\)").get(0));
			// System.out.println(my);
			list.add(my);
		}
		return list;
	}

	/***
	 * 根据字符串 构造测试用例链表
	 * @param str 从服务器获取的字符串
	 * @return
	 */
	public static void buildTestCaseList(List<TestCase> list,String str) {
		// 1.按*号将测试用例划分
		String[] tmp = str.split("\\*");
		// 2.对每个测试用例字符串进行解析封装
		for (String s : tmp) {
			TestCase testCase = new TestCase();
			// 2.1.字符串格式处理
			s = s.replace("\n", "");
			// 2.2.获取测试用例ID
			testCase.setTestCaseID(stringRegEx(s, "testcCaseID:([\\s|\\S]*?)-->processList:").get(0));
			// 2.3.构造激励链表
			String processList = stringRegEx(s, "processList:([\\s|\\S]*?)-->execStatus").get(0);
			testCase.setProcessList(string2ProcessList(processList));
			// 2.4.测试用例执行状态
			// 类型 说明 : 1.测试用例有误,无法对应到执行程序，且测试耗时:[不准确] 2.测试耗时:
			// 3.程序执行过程中出现死循环或者抛出异常!
			TestCaseResult testCaseResult = new TestCaseResult();
			String exeState = "", t = stringRegEx(s, "execStatus:([\\s|\\S]*?)-->resultStatus:").get(0);
			if (t.contains(":")) {
				String[] r = t.split(":");
				switch (r[0]) {
					case "1":
						exeState = "测试用例有误,无法对应到执行程序，且测试耗时:" + r[1] + "[不准确]";
						break;
					case "2":
						exeState = "测试耗时:" + r[1];
						break;
				}
				
				testCaseResult.setExeTime(Double.valueOf(r[1]));
				testCaseResult.setTakeoff_alt(Double.valueOf(r[2].substring("takeoff_alt".length())));
				testCaseResult.setBattery_remaining(Double.valueOf(r[3].substring("battery_remaining".length())));
				testCaseResult.setTime(Double.valueOf(r[4].substring("time".length())));
				testCaseResult.setWind_speed(Double.valueOf(r[5].substring("wind_speed".length())));
			} else {
				exeState = "程序执行过程中出现死循环或者抛出异常!";
			}
			testCaseResult.setResultDetail(exeState);
			testCase.setResult(testCaseResult);
			// 2.5.测试用例结果
			// 类型 说明 : 1.测试用例有误,无法对应到执行程序! 2.测试执行成功!耗时: 3.程序出现出现死循环或者抛出异常!
			String result = "";
			t = stringRegEx(s, "resultStatus:([\\s|\\S]*?)]").get(0);
			if (!t.contains(":")) {
				switch (t) {
				case "1":
					result = "测试用例有误,无法对应到执行程序!";
					break;
				case "3":
					result = "程序出现出现死循环或者抛出异常!";
					break;
				}
			} else {
				result = "测试执行成功!耗时:" + t.split(":")[1];
			}
			testCase.setState(result);
			//2.6.测试用例表现格式
			testCase.setDetail(testCase.showTestCase());
			//2.7.加入测试用例链表
			list.add(testCase);
		}
	}

	/**
	 * 字符串写入文件 由于测试数据太大需要通过保存在文件的形式查看
	 * 
	 * @param content
	 */
	public static void string2File(String content) {
		System.out.println(content);
		try {
			File file = new File("testFile.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWritter = new FileWriter(file.getName(), true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(content);
			bufferWritter.close();

			System.out.println("File Save Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

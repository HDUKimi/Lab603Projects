package com.horstmann.violet.application.gui.util.chengzuo.Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.horstmann.violet.application.gui.util.chengzuo.Bean.TestCase;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.myProcess;

public class TestCaseUtil {
	
	public static List<myProcess> string2ProcessList(String[] stringData) {
		List<myProcess> processList = new ArrayList<myProcess>();
		for (int i = 2; i < stringData.length - 2; i++) {
			myProcess process = new myProcess();
			// 当前测试激励字符串
			String current = stringData[i];

			// 获取激励执行情况
			if (current.contains("√"))
				process.setProcessExec(true);
			else
				process.setProcessExec(false);

			// 获取激励编号
			process.setProcessID(i - 1);

			// 获取激励名称
			String start = "ProcessName", end = "(";
			int sIndex = current.indexOf(start) + start.length(), eIndex = current.indexOf(end);
			String pName = current.substring(sIndex, eIndex).replace(':', ' ').trim();
			process.setProcessName(pName);

			// 获取激励参数
			start = "ProcessParameter";
			end = "ProcessStatus";
			sIndex = current.indexOf(start) + start.length();
			eIndex = current.indexOf(end);
			String pParam = current.substring(sIndex, eIndex).replace(':', ' ').trim();
			process.setProcessParam(pParam);

			// 获取激励状态
			start = "ProcessStatus";
			end = ")";
			sIndex = current.indexOf(start) + start.length();
			eIndex = current.indexOf(end);
			String pStatus = current.substring(sIndex, eIndex).replace(':', ' ').trim();
			process.setProcessStatus(pStatus);
			processList.add(process);
		}
		return processList;
	}

	public static List<TestCase> string2TestCaseList(String stringData) {
		List<TestCase> testCaseList = new ArrayList<TestCase>();

		// 1.按照"\n*\n"进行划分成单个测试用例
		String[] stringContent = stringData.split("\n\\*\n");

		// 2.对每个测试用例进行处理
		for (String tmp : stringContent) {
			TestCase testcase = new TestCase();
			// 获取每一行
			String[] con = tmp.split("\n");
			String testcaseID = con[0], testcaseState = con[con.length - 2], testcaseResult = con[con.length - 1];
			// 获取用例ID
			testcaseID = testcaseID.substring(testcaseID.indexOf(":") + 1);
			testcase.setTestCaseID(testcaseID);
			// 构造激励链表
			testcase.setProcessList(string2ProcessList(con));
			// 设置执行状态
			testcaseState = testcaseState.substring(testcaseState.indexOf("[") + 1, testcaseState.indexOf("]")).trim();
			testcase.setState(testcaseState);
			// 设置结果状态
			testcaseResult = testcaseResult.substring(testcaseResult.indexOf("[") + 1, testcaseResult.indexOf("]"))
					.trim();
			testcase.setResult(testcaseResult);
			// 设置整个激励内容
//			testcase.setDetail(tmp);
			Date t = new Date();
			System.out.println(t + " : " + testcase);
			testCaseList.add(testcase);
		}

		return testCaseList;
	}

	public static void string2File(String fileName, String fileContent) throws IOException {
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

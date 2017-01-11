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
			// ��ǰ���Լ����ַ���
			String current = stringData[i];

			// ��ȡ����ִ�����
			if (current.contains("��"))
				process.setProcessExec(true);
			else
				process.setProcessExec(false);

			// ��ȡ�������
			process.setProcessID(i - 1);

			// ��ȡ��������
			String start = "ProcessName", end = "(";
			int sIndex = current.indexOf(start) + start.length(), eIndex = current.indexOf(end);
			String pName = current.substring(sIndex, eIndex).replace(':', ' ').trim();
			process.setProcessName(pName);

			// ��ȡ��������
			start = "ProcessParameter";
			end = "ProcessStatus";
			sIndex = current.indexOf(start) + start.length();
			eIndex = current.indexOf(end);
			String pParam = current.substring(sIndex, eIndex).replace(':', ' ').trim();
			process.setProcessParam(pParam);

			// ��ȡ����״̬
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

		// 1.����"\n*\n"���л��ֳɵ�����������
		String[] stringContent = stringData.split("\n\\*\n");

		// 2.��ÿ�������������д���
		for (String tmp : stringContent) {
			TestCase testcase = new TestCase();
			// ��ȡÿһ��
			String[] con = tmp.split("\n");
			String testcaseID = con[0], testcaseState = con[con.length - 2], testcaseResult = con[con.length - 1];
			// ��ȡ����ID
			testcaseID = testcaseID.substring(testcaseID.indexOf(":") + 1);
			testcase.setTestCaseID(testcaseID);
			// ���켤������
			testcase.setProcessList(string2ProcessList(con));
			// ����ִ��״̬
			testcaseState = testcaseState.substring(testcaseState.indexOf("[") + 1, testcaseState.indexOf("]")).trim();
			testcase.setState(testcaseState);
			// ���ý��״̬
			testcaseResult = testcaseResult.substring(testcaseResult.indexOf("[") + 1, testcaseResult.indexOf("]"))
					.trim();
			testcase.setResult(testcaseResult);
			// ����������������
//			testcase.setDetail(tmp);
			Date t = new Date();
			System.out.println(t + " : " + testcase);
			testCaseList.add(testcase);
		}

		return testCaseList;
	}

	public static void string2File(String fileName, String fileContent) throws IOException {
		// �ļ����ݱ���ת�� GBK -> UTF-8
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

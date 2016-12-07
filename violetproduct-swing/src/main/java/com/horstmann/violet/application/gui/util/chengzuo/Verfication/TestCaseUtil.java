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
		
		//���� \n*���л���
		String[] stringContent = stringData.split("\n\\*");
		for (String tmp : stringContent) {
			TestCase testcase = new TestCase();
			//��ȡÿһ��
			String[] con = tmp.split("\n");
			int strlen = 0;
			//��ȡ����ID
			strlen = "��������ID: ".length();
			testcase.setTestCaseID(con[0].substring(strlen));
			//���켤������
			String s = "";
			for (int i = 2; i < con.length - 2; i++) {
				strlen = " �� ����ID : 1 �������� : ".length();
				s += con[i].substring(strlen+1);
			}
			testcase.setContent(s);
			//����ִ��״̬
			strlen = " -->����ִ��״̬: [ ".length();
			testcase.setState(con[con.length - 2].substring(strlen, con[con.length - 2].length() - 2));
			//���ý��״̬
			strlen = " -->���״̬: [ ".length();
			testcase.setResult(con[con.length - 1].substring(strlen, con[con.length - 1].length() - 2));
			//����������������
			testcase.setDetail(tmp);
			Date t = new Date();
			System.out.println(t + " : " + testcase);
			testCaseList.add(testcase);
		}
		
		return testCaseList;
	}
	
	public static void string2File(String fileName,String fileContent) throws IOException{
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

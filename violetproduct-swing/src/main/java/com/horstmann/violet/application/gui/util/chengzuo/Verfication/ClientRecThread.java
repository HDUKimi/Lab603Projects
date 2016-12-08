package com.horstmann.violet.application.gui.util.chengzuo.Verfication;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ClientRecThread implements Runnable {

	volatile boolean keepRunning;
	// ���߳��������socket����Ӧ��������
	private DataInputStream dis = null;
	private String content = null;
	private List<TestCase> testCaseList = null;

	public ClientRecThread(Socket socket,List<TestCase> testCaseList) {
		try {
			this.dis = new DataInputStream(socket.getInputStream());
			this.testCaseList = testCaseList;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �ر� ������
	 */
	public void close() {
		try {
			this.dis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<TestCase> getTestCaseList() {
		return testCaseList;
	}

	/**
	 * �����ַ��������װ��ģ�ͣ����洢��List��
	 *
	 */
	public void model() {
		String[] str = content.split("\n");
		System.out.println("content:"+content);
		TestCase testcase = new TestCase();
		testcase.setDetail(content);
		int len = 0;
		len = "��������ID: ".length();
		testcase.setTestCaseID(str[0].substring(len));
		String s = "";
		for (int i = 2; i < str.length - 2; i++) {
			len = "	�� ����ID : 1    �������� : ".length();
			s += str[i].substring(len);
		}
		testcase.setContent(s);
		len = "  -->����ִ��״̬: [ ".length();
		testcase.setState(str[str.length - 2].substring(len, str[str.length - 2].length() - 2));
		len = "  -->���״̬: [ ".length();
		testcase.setResult(str[str.length - 1].substring(len, str[str.length - 1].length() - 2));
		Date t = new Date();
		System.out.println(t+" : "+testcase);
		testCaseList.add(testcase);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println("���ս�������");
		int bufferSize = 919;
		byte[] buf = new byte[bufferSize];
		try {
			// �����̵߳Ľ��ܻ�������Ϊ��ʱ��
			while(keepRunning){
				while (dis.read(buf) != -1) {
					// 1.�������Է��������ַ���
					content = new String(buf, "UTF-8").trim();
					// 2.�ַ��������װ��ģ�ͣ����洢��List��
					model();
				   
					// 3.����ֽ�����
					Arrays.fill(buf, (byte) 0);
				}
			}
			
			System.out.println("���ս��̽���");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
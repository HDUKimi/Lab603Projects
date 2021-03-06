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
	// 该线程所处理的socket所对应的输入流
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
	 * 关闭 输入流
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
	 * 接受字符串处理封装成模型，并存储在List中
	 *
	 */
	public void model() {
		String[] str = content.split("\n");
		System.out.println("content:"+content);
		TestCase testcase = new TestCase();
		testcase.setDetail(content);
		int len = 0;
		len = "测试用例ID: ".length();
		testcase.setTestCaseID(str[0].substring(len));
		String s = "";
		for (int i = 2; i < str.length - 2; i++) {
			len = "	√ 激励ID : 1    激励名称 : ".length();
			s += str[i].substring(len);
		}
		testcase.setContent(s);
		len = "  -->测试执行状态: [ ".length();
		testcase.setState(str[str.length - 2].substring(len, str[str.length - 2].length() - 2));
		len = "  -->结果状态: [ ".length();
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
		System.out.println("接收进程运行");
		int bufferSize = 919;
		byte[] buf = new byte[bufferSize];
		try {
			// 接受线程的接受缓冲区不为空时候
			while(keepRunning){
				while (dis.read(buf) != -1) {
					// 1.接受来自服务器的字符串
					content = new String(buf, "UTF-8").trim();
					// 2.字符串处理封装成模型，并存储在List中
					model();
				   
					// 3.清空字节数组
					Arrays.fill(buf, (byte) 0);
				}
			}
			
			System.out.println("接收进程结束");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
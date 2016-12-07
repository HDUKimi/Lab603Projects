package com.horstmann.violet.application.gui.util.chengzuo.Verfication;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class ClientRecThread implements Runnable {

	volatile boolean keepRunning;
	// 该线程所处理的socket所对应的输入流
	private DataInputStream dis = null;
	private String content = "";
	private List<TestCase> testCaseList = null;

	public ClientRecThread(Socket socket) {
		try {
			this.dis = new DataInputStream(socket.getInputStream());
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
	 * @throws IOException
	 * @throws InterruptedException 
	 *
	 */
	public void string2model() throws IOException, InterruptedException {
		// 以 # 号 为分隔符
		String[] str = content.split("#");
		/**
		 * 传输内容 分隔符划分
		 * 顺序是  字符串内容;文件名;文件内容
		 */
		String stringData = str[0];
//		String fileName = str[1];
//		String fileContent = str[2];
		ClientSocket.testCaseList = TestCaseUtil.string2TestCaseList(stringData);
		System.out.println(ClientSocket.testCaseList.size());
	}

	@Override
	public void run() {
		System.out.println("接收进程运行");
		int bufferSize = 1000;
		byte[] buf = new byte[bufferSize];
		String tmp = "";
		try {
			// 接受线程的接受缓冲区不为空时候
			while (keepRunning) {
				while (dis.read(buf) != -1) {
					// 1.接受来自服务器的字符串
					tmp = new String(buf, "UTF-8").trim();
					content += tmp;
					// 2.清空字节数组
					Arrays.fill(buf, (byte) 0);
				}
				// 3.字符串处理封装成模型，并存储在List中
				string2model();
				break;
			}
			System.out.println("接收进程结束");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
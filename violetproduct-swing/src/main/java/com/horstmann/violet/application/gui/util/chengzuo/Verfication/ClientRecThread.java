package com.horstmann.violet.application.gui.util.chengzuo.Verfication;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class ClientRecThread implements Runnable {

	volatile boolean keepRunning;
	// ���߳��������socket����Ӧ��������
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
	 * @throws IOException
	 * @throws InterruptedException 
	 *
	 */
	public void string2model() throws IOException, InterruptedException {
		// �� # �� Ϊ�ָ���
		String[] str = content.split("#");
		/**
		 * �������� �ָ�������
		 * ˳����  �ַ�������;�ļ���;�ļ�����
		 */
		String stringData = str[0];
//		String fileName = str[1];
//		String fileContent = str[2];
		ClientSocket.testCaseList = TestCaseUtil.string2TestCaseList(stringData);
		System.out.println(ClientSocket.testCaseList.size());
	}

	@Override
	public void run() {
		System.out.println("���ս�������");
		int bufferSize = 1000;
		byte[] buf = new byte[bufferSize];
		String tmp = "";
		try {
			// �����̵߳Ľ��ܻ�������Ϊ��ʱ��
			while (keepRunning) {
				while (dis.read(buf) != -1) {
					// 1.�������Է��������ַ���
					tmp = new String(buf, "UTF-8").trim();
					content += tmp;
					// 2.����ֽ�����
					Arrays.fill(buf, (byte) 0);
				}
				// 3.�ַ��������װ��ģ�ͣ����洢��List��
				string2model();
				break;
			}
			System.out.println("���ս��̽���");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
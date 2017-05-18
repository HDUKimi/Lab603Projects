package com.horstmann.violet.application.gui.util.chengzuo.Util;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.TestCase;
import com.mysql.fabric.xmlrpc.base.Array;

public class ClientSocket {

	// ���ӷ�������IP�Լ��˿ں�
	protected String IP;
	protected int PORT;

//	public static List<TestCase> testCaseList = Collections.synchronizedList(new ArrayList<TestCase>());

	// �����׽���
	private Socket socket = null;

	// ���������߳��Լ��߳���
	Thread recThread = null;
	ClientRecThread clientRecThread = null;

	// �ļ������߳��Լ��߳���
	Thread fileThread = null;
	ClientFileThread clientFileThread = null;
	// �ļ�����
	File[] file = null;

	// �����׽���
	public ClientSocket(String ip, int port) {
		this.IP = ip;
		this.PORT = port;
	}

//	public static synchronized List<TestCase> getTestCaseList() {
//		synchronized(testCaseList){
//			return testCaseList;
//		}
//	}

	// ��ʼ�������߳�
	public void initThread() {
		clientRecThread = new ClientRecThread(socket);
		clientRecThread.keepRunning = true;
		recThread = new Thread(clientRecThread);
		recThread.start();

		clientFileThread = new ClientFileThread(socket);
		fileThread = new Thread(clientFileThread);
	}

	/**
	 * �ж����Ӻ���
	 */
	public boolean isConnect() {
		if (socket == null || socket.isClosed()) {
			return false;
		}
		return true;
	}

	/**
	 * ����socket����
	 */
	public boolean Connection() {
		// 1.���� socket
		try {
			socket = new Socket(IP, PORT);
			if(isConnect()){
				// 2.��ʼ���߳�
				initThread();
				// 3.������ʾ
				JOptionPane.showMessageDialog(null, "�ɹ����ӵ�������", null, JOptionPane.OK_CANCEL_OPTION);
				return true;
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "������δ����", null, JOptionPane.CANCEL_OPTION);
		}
		return false;
	}

	public void close(){
		// 2.socket�ر�
		try {
			socket.close();
			System.out.println("socket close!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �ļ����亯��
	 */
	public void sendFile(File[] files) {
		try {
			if (files.length > 0) {
				clientFileThread.setFiles(files);
				fileThread.join();
				fileThread.start();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
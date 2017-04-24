package com.horstmann.violet.application.gui.util.chengzuo.Util;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class ClientFileThread implements Runnable {
	// ���߳��������socket����Ӧ��������
	private DataOutputStream dos = null;
	private File[] files = null;

	public ClientFileThread(Socket socket) {
		try {
			this.dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
	/**
	 * �ر� ������
	 */
	public void close(){
		try {
			this.dos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

	@Override
	public void run() {
		System.out.println("�ļ������������");
		// ѡ����д�����ļ�
		DataInputStream fis = null;
		try {
			int bufferSize = 1024;
			byte[] buf = new byte[bufferSize * bufferSize];

			for (File fi : files) {
				String filePath = fi.getAbsolutePath();
				fis = new DataInputStream(new BufferedInputStream(new FileInputStream(filePath)));
				String type;

				// ���ļ���
				type = "fileName:" + fi.getName();
				dos.write(type.getBytes());
				dos.flush();

				// �ļ�����
				type = "fileSize:" + Long.toString(fi.length());
				dos.write(type.getBytes());
				dos.flush();

				// ��ջ�����
				Arrays.fill(buf, (byte) 0);
				int read = 0;
				while ((read = fis.read(buf)) != -1) {
					dos.write(buf, 0, read);
				}
				dos.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("�ļ��������\n");
			try {
				fis.close();
				System.out.println("�ļ��������\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
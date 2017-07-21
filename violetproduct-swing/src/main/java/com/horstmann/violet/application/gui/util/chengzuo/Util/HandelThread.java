package com.horstmann.violet.application.gui.util.chengzuo.Util;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import com.horstmann.violet.application.gui.util.chengzuo.Bean.Pair;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.TestCase;


public class HandelThread implements Callable<Pair<String,List<TestCase>>> {

	private Logger logger = Logger.getLogger(this.getClass());

	// server's IP and Port
	protected String IP;
	protected int PORT = 5555;

	private Socket socket = null;

	// Stream based on socket
	DataOutputStream dos = null;
	DataInputStream dis = null;

	// testcases' Files
	File[] files = null;

	// type:0,function;1,performance;2,time
	String type = null;

	public List<TestCase> testCaseList = Collections.synchronizedList(new ArrayList<TestCase>());

	public HandelThread(String ip, File files, String type) {
		this(ip,new File[]{files},type);
	}

	public HandelThread(String ip, File[] files, String type) {
		this.IP = ip;
		this.files = files;
		this.type = type;
	}

	// connect socket
	public boolean connection() {
		try {
			socket = new Socket(IP, PORT);
			if (socket != null) {
				logger.debug("success connection");
				return true;
			}
		} catch (Exception e) {
			logger.error("fail to connect server");
		}
		return false;
	}

	// close socket
	public void close() {
		try {
			dos.close();
			dis.close();
			socket.close();
			logger.debug("socket close");
		} catch (IOException e) {
			logger.error("close socket error ,cause by " + e.getMessage());
		}
	}

	// send xml files
	public void send() {
		DataInputStream fis = null;
		try {

			dos = new DataOutputStream(socket.getOutputStream());

			int bufferSize = 1024;
			byte[] buf = new byte[bufferSize * bufferSize];

			for (File fi : files) {
				String filePath = fi.getAbsolutePath();
				fis = new DataInputStream(new BufferedInputStream(new FileInputStream(filePath)));
				String type;

				// send fileName
				type = fi.getName()+"*";
				dos.write(type.getBytes());
				dos.flush();

				// send fileSize
				type = Long.toString(fi.length())+"*";
				dos.write(type.getBytes());
				dos.flush();

				// flush
				Arrays.fill(buf, (byte) 0);
				int read = 0;
				while ((read = fis.read(buf)) != -1) {
					dos.write(buf, 0, read);
				}
				dos.flush();
				
				//exit flag
				dos.write("*#exit#".getBytes());
				dos.flush();
			}
			logger.debug("success sendfile");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				logger.error("close fis error ,cause by " + e.getMessage());
			}
		}
	}

	// receive result
	public void recv() {
		String content = "";
		int bufferSize = 500;
		byte[] buf = new byte[bufferSize];
		String tmp = "";
		try {
			dis = new DataInputStream(socket.getInputStream());
			while (dis.read(buf) != -1) {
				tmp = new String(buf, "UTF-8").trim();
				content += tmp;
//				logger.debug("recevice data:" + content);
				Arrays.fill(buf, (byte) 0);
			}
			// str2model store in list
			TestCaseConvertUtil.buildTestCaseList(type, testCaseList, content);

			logger.debug("success recevice");
		} catch (Exception e) {
			logger.debug("failed recevice , cause by " + e.getCause());
		}
	}

	public Pair<String,List<TestCase>> call() throws Exception {
		// 1.create connection
		boolean isCon = connection();
		if (isCon) {
			// 2.send data
			send();
			// 3.receive result
			recv();
			// 4.close socket
			close();
		}
		return new Pair<String,List<TestCase>>(type,testCaseList);
	}

}

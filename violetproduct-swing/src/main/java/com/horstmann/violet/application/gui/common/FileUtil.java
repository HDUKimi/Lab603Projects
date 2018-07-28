package com.horstmann.violet.application.gui.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtil {

	public static String DefaultRoute;
	public static List<String> pathlist = new ArrayList<String>();

	public static void FileCheck() {

		File[] roots = File.listRoots();
		DefaultRoute = roots[0].getAbsolutePath() + "ModelDriverProjectFile\\";

		File defaultFile = new File(DefaultRoute);
		if (!defaultFile.exists()) {
			defaultFile.mkdirs();
		}

		pathlist.add(DefaultRoute + "MarkovDiagram\\");
		pathlist.add(DefaultRoute + "TestCase\\");
		pathlist.add(DefaultRoute + "TestCaseResult\\");
		pathlist.add(DefaultRoute + "FailureData\\");

		File file;

		System.out.println(pathlist.size());
		for (String path : pathlist) {
			file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			if (!file.exists()) {
				file.mkdirs();
			} else {
				System.out.println(path + " is exist");
			}
		}

	}

	public static HashMap<String, String> FileList(String name) {

		File[] fileList;
		HashMap<String, String> fileMap = new HashMap<>();

		if (name.equals("MarkovDiagram")) {
			fileList = new File(pathlist.get(0)).listFiles();
			for (File file : fileList) {
				String fileName = file.getName();
				if (fileName.lastIndexOf(".markov.violet.xml") > 0) {
					fileMap.put(fileName.substring(0, fileName.lastIndexOf(".violet.xml")), file.getAbsolutePath());
				}
			}
		} else if (name.equals("TestCase")) {
			fileList = new File(pathlist.get(1)).listFiles();
			for (File file : fileList) {
				String fileName = file.getName();
				if (fileName.lastIndexOf(".testcase.violet.xml") > 0) {
					fileMap.put(fileName.substring(0, fileName.lastIndexOf(".violet.xml")), file.getAbsolutePath());
				}
			}
		} else if (name.equals("FailureData")) {
			fileList = new File(pathlist.get(3)).listFiles();
			for (File file : fileList) {
				String fileName = file.getName();
				if (fileName.lastIndexOf(".failure.violet.txt") > 0) {
					fileMap.put(fileName.substring(0, fileName.lastIndexOf(".violet.txt")), file.getAbsolutePath());
				}
			}
		}

		System.out.println(fileMap.size());

		return fileMap;

	}
	
	public static void WriteFailureData(List<Integer> failureDataList,String name) throws IOException {
		
		String path=FileUtil.pathlist.get(3)+name+".violet.txt";
		File file=new File(path);
		
		while(!file.exists()){
			file.createNewFile();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
		for(Integer time:failureDataList){
			writer.write(time+"\n");
		}
		writer.close();
	}
	
	public static List<Integer> ReadFailureData(File file) throws IOException {
		
		List<Integer> failureDataList=new ArrayList<>();
		
		FileInputStream inputStream = new FileInputStream(file);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

		String str;
		while ((str = bufferedReader.readLine()) != null) {
			
			failureDataList.add(Integer.parseInt(str.trim()));
			
		}
		
		bufferedReader.close();
		inputStream.close();
		
		return failureDataList;
		
	}

	public static Map<Integer, String> ReadTestCaseResult(File file) throws IOException {
		
		Map<Integer, String> map=new HashMap<>();
		
		FileInputStream inputStream = new FileInputStream(file);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

		String str;
		while ((str = bufferedReader.readLine()) != null) {
			if (str.length() < 3 ) {
				continue;
			}
			System.out.println(str);
			String[] s = str.split("  ");
			
			map.put(Integer.parseInt(s[0].trim()), s[1].trim());
			
		}
		
		bufferedReader.close();
		inputStream.close();
		
		return map;
	}

}

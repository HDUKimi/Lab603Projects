package com.horstmann.violet.application.gui.common;

import java.io.File;
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
				if (fileName.lastIndexOf(".xml") > 0) {
					fileMap.put(fileName.substring(0, fileName.lastIndexOf(".xml")), file.getAbsolutePath());
				}
			}
		} else if (name.equals("TestCase")) {

		}

		System.out.println(fileMap.size());

		return fileMap;

	}

}

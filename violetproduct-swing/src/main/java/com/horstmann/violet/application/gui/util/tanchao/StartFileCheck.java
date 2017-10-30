package com.horstmann.violet.application.gui.util.tanchao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StartFileCheck {

	public void FileCheck(){
		
		List<String> pathlist=new ArrayList<String>();
		pathlist.add("D:\\ModelDriverProjectFile\\ActivityDiagram\\Violet\\");
		pathlist.add("D:\\ModelDriverProjectFile\\StateDiagram\\Violet\\");
		pathlist.add("D:\\ModelDriverProjectFile\\TimingDiagram\\Violet\\");
		pathlist.add("D:\\ModelDriverProjectFile\\UsecaseDiagram\\Violet\\");
		pathlist.add("D:\\ModelDriverProjectFile\\SequenceDiagram\\Violet\\FunctionalTest\\");
		pathlist.add("D:\\ModelDriverProjectFile\\SequenceDiagram\\Violet\\PerformanceTest\\");
		pathlist.add("D:\\ModelDriverProjectFile\\SequenceDiagram\\Violet\\TimeTest\\");
		pathlist.add("D:\\ModelDriverProjectFile\\SequenceDiagram\\Violet\\Test\\");
		pathlist.add("D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\FunctionalTest\\");
		pathlist.add("D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\PerformanceTest\\");
		pathlist.add("D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\TimeTest\\");
		pathlist.add("D:\\ModelDriverProjectFile\\UPPAL\\3.Abstract_TestCase\\FunctionalTest\\");
		pathlist.add("D:\\ModelDriverProjectFile\\UPPAL\\3.Abstract_TestCase\\PerformanceTest\\");
		pathlist.add("D:\\ModelDriverProjectFile\\UPPAL\\3.Abstract_TestCase\\TimeTest\\");
		pathlist.add("D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\FunctionalTest\\");
		pathlist.add("D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\PerformanceTest\\");
		pathlist.add("D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\TimeTest\\");
		pathlist.add("D:\\ModelDriverProjectFile\\SqlTestCase\\");
		pathlist.add("D:\\ModelDriverProjectFile\\WJXML\\");
		
		File file;
		
		System.out.println(pathlist.size());
		for(String path:pathlist){
			file=new File(path);
			if(!file.exists()){
				file.mkdirs();
			}
			if(!file.exists()){
				file.mkdirs();
			}
			else{
				System.out.println(path+" is exist");
			}
		}
		
	}
	
	public static void main(String[] args){
		
		StartFileCheck sfc=new StartFileCheck();
		
		System.out.println("------START------");
		sfc.FileCheck();
		System.out.println("-------END-------");
		
	}
	
}

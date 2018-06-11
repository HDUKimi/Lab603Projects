package com.horstmann.violet.application.gui.util.tanchao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StartFileCheck {
	
	public static String DefaultRoute;

	public void FileCheck(){
		
		File[] roots = File.listRoots();
    	DefaultRoute = roots[0].getAbsolutePath() + "ModelDriverProjectFile\\";
    	
    	File defaultFile = new File(DefaultRoute);
    	if(!defaultFile.exists()){
    		defaultFile.mkdirs();
    	}
		
		List<String> pathlist=new ArrayList<String>();
		pathlist.add(StartFileCheck.DefaultRoute+"ActivityDiagram\\Violet\\");
		pathlist.add(StartFileCheck.DefaultRoute+"StateDiagram\\Violet\\");
		pathlist.add(StartFileCheck.DefaultRoute+"TimingDiagram\\Violet\\");
		pathlist.add(StartFileCheck.DefaultRoute+"UsecaseDiagram\\Violet\\");
		pathlist.add(StartFileCheck.DefaultRoute+"SequenceDiagram\\Violet\\FunctionalTest\\");
		pathlist.add(StartFileCheck.DefaultRoute+"SequenceDiagram\\Violet\\PerformanceTest\\");
		pathlist.add(StartFileCheck.DefaultRoute+"SequenceDiagram\\Violet\\TimeTest\\");
		pathlist.add(StartFileCheck.DefaultRoute+"SequenceDiagram\\Violet\\Test\\");
		pathlist.add(StartFileCheck.DefaultRoute+"UPPAL\\2.UML_Model_Transfer\\FunctionalTest\\");
		pathlist.add(StartFileCheck.DefaultRoute+"UPPAL\\2.UML_Model_Transfer\\PerformanceTest\\");
		pathlist.add(StartFileCheck.DefaultRoute+"UPPAL\\2.UML_Model_Transfer\\TimeTest\\");
		pathlist.add(StartFileCheck.DefaultRoute+"UPPAL\\2.UML_Model_Transfer\\SequenceToUppal\\");
		pathlist.add(StartFileCheck.DefaultRoute+"UPPAL\\2.UML_Model_Transfer\\TimingToUppal\\");
		pathlist.add(StartFileCheck.DefaultRoute+"UPPAL\\3.Abstract_TestCase\\FunctionalTest\\");
		pathlist.add(StartFileCheck.DefaultRoute+"UPPAL\\3.Abstract_TestCase\\PerformanceTest\\");
		pathlist.add(StartFileCheck.DefaultRoute+"UPPAL\\3.Abstract_TestCase\\TimeTest\\");
		pathlist.add(StartFileCheck.DefaultRoute+"UPPAL\\4.Real_TestCase\\FunctionalTest\\");
		pathlist.add(StartFileCheck.DefaultRoute+"UPPAL\\4.Real_TestCase\\PerformanceTest\\");
		pathlist.add(StartFileCheck.DefaultRoute+"UPPAL\\4.Real_TestCase\\TimeTest\\");
		pathlist.add(StartFileCheck.DefaultRoute+"SqlTestCase\\");
		pathlist.add(StartFileCheck.DefaultRoute+"WJXML\\");
		
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

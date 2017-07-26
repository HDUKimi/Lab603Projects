package com.horstmann.violet.application.gui.util.ckt.output;

import javax.swing.JTextArea;

import com.horstmann.violet.application.gui.StepButtonPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.TestCaseInstantiationProcessTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.TestCaseProcessTabbedPanel;

public class ShowInfor {

	public static void print(){
		System.out.println();
	}
	
	public static void print(String s){
		
		System.out.println(s);
	}
	
	public static void print(int stepindex, String s){
		
//		int stepindex=StepButtonPanel.getIndex();
		
		if(stepindex==3){
			TestCaseProcessTabbedPanel.TextAreaPrint(s);
		}
		else if(stepindex==4){
			TestCaseInstantiationProcessTabbedPanel.TextAreaPrint(s);
		}
		
//		System.out.println(s);
	}
	
}

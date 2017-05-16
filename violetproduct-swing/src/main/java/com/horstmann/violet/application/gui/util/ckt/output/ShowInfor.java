package com.horstmann.violet.application.gui.util.ckt.output;

import javax.swing.JTextArea;

import com.horstmann.violet.application.gui.stepCenterTabbedPane.TestCaseProcessTabbedPanel;

public class ShowInfor {

	public static void print(){
		System.out.println();
	}
	
	public static void print(String s){
		TestCaseProcessTabbedPanel.TextAreaPrint(s);
		System.out.println(s);
	}
	
}

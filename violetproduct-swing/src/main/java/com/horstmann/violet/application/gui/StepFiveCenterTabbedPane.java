package com.horstmann.violet.application.gui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class StepFiveCenterTabbedPane extends JTabbedPane{
	private JPanel testcaseFile;
	private JPanel testcaseFile1;
	public JPanel getTestcaseFile1() {
		return testcaseFile1;
	}

	public void setTestcaseFile1(JPanel testcaseFile1) {
		this.testcaseFile1 = testcaseFile1;
	}

	public JPanel getTestcaseFile() {
		return this.testcaseFile;
	}

	public void setTestcaseFile(JPanel testcaseFile) {
		this.testcaseFile = testcaseFile;
	}

	public StepFiveCenterTabbedPane()
	{
		testcaseFile=new JPanel();
		this.add("测试用例实例化测试报告",testcaseFile);	
		testcaseFile1=new JPanel();
		this.add("测试用例实例化测试报告",testcaseFile1);	
	}

}

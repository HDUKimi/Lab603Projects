package com.horstmann.violet.application.gui;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import com.horstmann.violet.application.consolepart.ConsolePartScrollPane;

public class StepFourCenterTabbedPane extends JTabbedPane{
	
	private JScrollPane ConsolePartScrollPane;
	public StepFourCenterTabbedPane()
	{			
		ConsolePartScrollPane=new JScrollPane();
		this.add("�����������ʵ����",ConsolePartScrollPane);			
	}
	public JScrollPane getConsolePartScrollPane() {
		return ConsolePartScrollPane;
	}
	public void setConsolePartScrollPane(JScrollPane consolePartScrollPane) {
		ConsolePartScrollPane = consolePartScrollPane;
	}
	
}

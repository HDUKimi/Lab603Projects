package com.horstmann.violet.application.gui;

import javax.swing.JTabbedPane;

import com.horstmann.violet.application.consolepart.ConsolePartScrollPane;

public class StepThreeCenterTabbedPane extends JTabbedPane{
	

		
		private JTabbedPane abstractUppaalTabbedPane;
		private JTabbedPane uppaalTabbedPane;	  
		private JTabbedPane abstractTestCaseOptimize;
		public StepThreeCenterTabbedPane()
		{
		
			uppaalTabbedPane=new JTabbedPane();	
			abstractTestCaseOptimize=new JTabbedPane();
			abstractUppaalTabbedPane=new JTabbedPane();
			this.add("����ʱ��Ǩ�Ƶ��Զ���",abstractUppaalTabbedPane);
			this.add("����ʱ��Ǩ�Ƶ��Զ���",uppaalTabbedPane);
			this.add("���������������",new ConsolePartScrollPane(0));
            this.add("������������Ż�Լ��",abstractTestCaseOptimize);
				
		}
	}


package com.horstmann.violet.application.gui;

import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import com.horstmann.violet.application.consolepart.ConsolePartScrollPane;

public class StepThreeCenterTabbedPane extends JTabbedPane{
	

		
		private JPanel abstractUppaalTabbedPane;
		private JPanel uppaalTabbedPane;	  
		private JPanel abstractTestCaseOptimize;
		private JScrollPane consolePartScrollPane;
		public StepThreeCenterTabbedPane()
		{
		
			uppaalTabbedPane=new JPanel();	
			abstractTestCaseOptimize=new JPanel();
			abstractUppaalTabbedPane=new JPanel();
			consolePartScrollPane=new JScrollPane();
			
			uppaalTabbedPane.setLayout(new GridBagLayout());
			abstractUppaalTabbedPane.setLayout(new GridBagLayout());
//			this.add("����ʱ��Ǩ�Ƶ��Զ���",abstractUppaalTabbedPane);
//			this.add("����ʱ��Ǩ�Ƶ��Զ���",uppaalTabbedPane);
//			this.add("���������������",consolePartScrollPane);
//            this.add("������������Ż�Լ��",abstractTestCaseOptimize);
			this.add("������������",consolePartScrollPane);
			this.add("���������Ż�Լ��",abstractTestCaseOptimize);
				
		}
		public JPanel getAbstractUppaalTabbedPane() {
			return abstractUppaalTabbedPane;
		}
		public void setAbstractUppaalTabbedPane(JPanel abstractUppaalTabbedPane) {
			this.abstractUppaalTabbedPane = abstractUppaalTabbedPane;
		}
		public JPanel getUppaalTabbedPane() {
			return uppaalTabbedPane;
		}
		public void setUppaalTabbedPane(JPanel uppaalTabbedPane) {
			this.uppaalTabbedPane = uppaalTabbedPane;
		}
		public JPanel getAbstractTestCaseOptimize() {
			return abstractTestCaseOptimize;
		}
		public void setAbstractTestCaseOptimize(JPanel abstractTestCaseOptimize) {
			this.abstractTestCaseOptimize = abstractTestCaseOptimize;
		}
		public JScrollPane getConsolePartScrollPane() {
			return consolePartScrollPane;
		}
		public void setConsolePartScrollPane(JScrollPane consolePartScrollPane) {
			this.consolePartScrollPane = consolePartScrollPane;
		}
		
	}


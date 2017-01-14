package com.horstmann.violet.application.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import com.horstmann.violet.application.gui.stepCenterTabbedPane.FixedButtonTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.TestCaseReportTabbedPanel;

public class StepFiveCenterTabbedPane extends JPanel{
	
	private MainFrame mainFrame;
	
	private JPanel buttonPanel;
	public JPanel diagramPanel;

	private JButton testCaseReportDiagramButton;
	private FixedButtonTabbedPanel testCaseReportDiagramButtonPanel;
	private TestCaseReportTabbedPanel testCaseReportTabbedPane;
	
	private JButton testCasePieChartDiagramButton;
	private FixedButtonTabbedPanel testCasePieChartDiagramButtonPanel;
	private JPanel testCasePieChartTabbedPane;
	
	private JButton testCaseBarChartDiagramButton;
	private FixedButtonTabbedPanel testCaseBarChartDiagramButtonPanel;
	private JPanel testCaseBarChartTabbedPane;
	
	private JButton testCaseLineChartDiagramButton;
	private FixedButtonTabbedPanel testCaseLineChartDiagramButtonPanel;
	private JPanel testCaseLineChartTabbedPane;
	
	private int selectedIndex=1;
	
	public StepFiveCenterTabbedPane(MainFrame mainFrame)
	{
		
		this.mainFrame = mainFrame;
		
		testCaseReportTabbedPane=new TestCaseReportTabbedPanel(mainFrame);
		
		testCasePieChartTabbedPane=new JPanel();
		testCasePieChartTabbedPane.setLayout(new GridLayout());
		
		testCaseBarChartTabbedPane=new JPanel();
		testCaseBarChartTabbedPane.setLayout(new GridLayout());
		
		testCaseLineChartTabbedPane=new JPanel();
		testCaseLineChartTabbedPane.setLayout(new GridLayout());
		
		buttonPanel=new JPanel();
		diagramPanel=new JPanel();
		
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		buttonPanel.setBackground(new Color(41, 57, 85));
		
		initbuttonpanel();
		
		initdiagrampanel();
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(buttonPanel);
		this.add(diagramPanel);
		layout.setConstraints(buttonPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(diagramPanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();
		
		this.setMinimumSize(new Dimension(screenWidth/2, screenHeight/2));
		
//		testcaseFile=new JPanel();
//		this.add("测试用例实例化测试报告",testcaseFile);	
//		testcaseFile1=new JPanel();
//		this.add("测试用例实例化测试报告",testcaseFile1);	
	}

	private void initbuttonpanel() {
		// TODO Auto-generated method stub
		
		testCaseReportDiagramButtonPanel=new FixedButtonTabbedPanel("测试用例实例化测试报告");
		testCaseReportDiagramButtonPanel.setBackground(new Color(58, 105, 190));
		testCaseReportDiagramButton=testCaseReportDiagramButtonPanel.getTabbedbutton();
		
		testCasePieChartDiagramButtonPanel=new FixedButtonTabbedPanel("测试用例实例化测试报表");
		testCasePieChartDiagramButtonPanel.setBackground(new Color(77, 96, 130));
		testCasePieChartDiagramButton=testCasePieChartDiagramButtonPanel.getTabbedbutton();
		
		testCaseBarChartDiagramButtonPanel=new FixedButtonTabbedPanel("测试用例实例化测试报表");
		testCaseBarChartDiagramButtonPanel.setBackground(new Color(77, 96, 130));
		testCaseBarChartDiagramButton=testCaseBarChartDiagramButtonPanel.getTabbedbutton();
		
		testCaseLineChartDiagramButtonPanel=new FixedButtonTabbedPanel("测试用例实例化测试报表");
		testCaseLineChartDiagramButtonPanel.setBackground(new Color(77, 96, 130));
		testCaseLineChartDiagramButton=testCaseLineChartDiagramButtonPanel.getTabbedbutton();
		
		
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		buttonPanel.setBackground(new Color(41, 57, 85));
		buttonPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(58, 105, 190)));
		
		buttonPanel.add(testCaseReportDiagramButtonPanel);
		buttonPanel.add(testCasePieChartDiagramButtonPanel);
		buttonPanel.add(testCaseBarChartDiagramButtonPanel);
		buttonPanel.add(testCaseLineChartDiagramButtonPanel);
		
		setButtonActionListener();
		
	}

	private void setButtonActionListener() {
		// TODO Auto-generated method stub
		
		testCaseReportDiagramButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				selectedIndex=1;
				getDiagramPanel().removeAll();
				getDiagramPanel().setLayout(new GridLayout());
				getDiagramPanel().add(testCaseReportTabbedPane);
				
				testCaseReportDiagramButtonPanel.setBackground(new Color(58, 105, 190));
				testCasePieChartDiagramButtonPanel.setBackground(new Color(77, 96, 130));
				testCaseBarChartDiagramButtonPanel.setBackground(new Color(77, 96, 130));
				testCaseLineChartDiagramButtonPanel.setBackground(new Color(77, 96, 130));
				
				ChangeRepaint();
				
			}
		});
		
		testCasePieChartDiagramButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				selectedIndex=2;
				getDiagramPanel().removeAll();
				getDiagramPanel().setLayout(new GridLayout());
				getDiagramPanel().add(testCasePieChartTabbedPane);
				
				testCasePieChartDiagramButtonPanel.setBackground(new Color(58, 105, 190));
				testCaseReportDiagramButtonPanel.setBackground(new Color(77, 96, 130));
				testCaseBarChartDiagramButtonPanel.setBackground(new Color(77, 96, 130));
				testCaseLineChartDiagramButtonPanel.setBackground(new Color(77, 96, 130));
				
				ChangeRepaint();
				
			}
		});
		
		testCaseBarChartDiagramButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				selectedIndex=3;
				getDiagramPanel().removeAll();
				getDiagramPanel().setLayout(new GridLayout());
				getDiagramPanel().add(testCaseBarChartTabbedPane);
				
				testCaseBarChartDiagramButtonPanel.setBackground(new Color(58, 105, 190));
				testCaseReportDiagramButtonPanel.setBackground(new Color(77, 96, 130));
				testCasePieChartDiagramButtonPanel.setBackground(new Color(77, 96, 130));
				testCaseLineChartDiagramButtonPanel.setBackground(new Color(77, 96, 130));
				
				ChangeRepaint();
				
			}
		});
		
		testCaseLineChartDiagramButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				selectedIndex=4;
				getDiagramPanel().removeAll();
				getDiagramPanel().setLayout(new GridLayout());
				getDiagramPanel().add(testCaseLineChartTabbedPane);
				
				testCaseLineChartDiagramButtonPanel.setBackground(new Color(58, 105, 190));
				testCaseReportDiagramButtonPanel.setBackground(new Color(77, 96, 130));
				testCasePieChartDiagramButtonPanel.setBackground(new Color(77, 96, 130));
				testCaseBarChartDiagramButtonPanel.setBackground(new Color(77, 96, 130));
				
				ChangeRepaint();
				
			}
		});
		
	}

	private void initdiagrampanel() {
		// TODO Auto-generated method stub
		
		diagramPanel.setLayout(new GridLayout());
		diagramPanel.add(testCaseReportTabbedPane);
		diagramPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(142, 155, 188)));
		
	}
	
	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}

	public JPanel getDiagramPanel() {
		return diagramPanel;
	}

	public void setDiagramPanel(JPanel diagramPanel) {
		this.diagramPanel = diagramPanel;
	}

	public JButton getTestCaseReportDiagramButton() {
		return testCaseReportDiagramButton;
	}

	public FixedButtonTabbedPanel getTestCaseReportDiagramButtonPanel() {
		return testCaseReportDiagramButtonPanel;
	}

	public TestCaseReportTabbedPanel getTestCaseReportTabbedPane() {
		return testCaseReportTabbedPane;
	}
	
	public JButton getTestCasePieChartDiagramButton() {
		return testCasePieChartDiagramButton;
	}

	public FixedButtonTabbedPanel getTestCasePieChartDiagramButtonPanel() {
		return testCasePieChartDiagramButtonPanel;
	}

	public JPanel getTestCasePieChartTabbedPane() {
		return testCasePieChartTabbedPane;
	}
	
	public JButton getTestCaseBarChartDiagramButton() {
		return testCaseBarChartDiagramButton;
	}

	public FixedButtonTabbedPanel getTestCaseBarChartDiagramButtonPanel() {
		return testCaseBarChartDiagramButtonPanel;
	}

	public JPanel getTestCaseBarChartTabbedPane() {
		return testCaseBarChartTabbedPane;
	}
	
	public JButton getTestCaseLineChartDiagramButton() {
		return testCaseLineChartDiagramButton;
	}

	public FixedButtonTabbedPanel getTestCaseLineChartDiagramButtonPanel() {
		return testCaseLineChartDiagramButtonPanel;
	}

	public JPanel getTestCaseLineChartTabbedPane() {
		return testCaseLineChartTabbedPane;
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}
	
	

}

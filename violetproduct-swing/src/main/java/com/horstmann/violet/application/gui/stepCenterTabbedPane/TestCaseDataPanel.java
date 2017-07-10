package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.horstmann.violet.application.gui.MainFrame;

public class TestCaseDataPanel extends JPanel{

	private MainFrame mainFrame;
	
	private String testCaseName;
	
	private JButton testCaseReportDiagramButton;
	private FixedButtonTabbedPanel testCaseReportDiagramButtonPanel;
	private TestCaseReportTabbedPanel testCaseReportTabbedPane;
	
	private JButton testCaseChartDiagramButton;
	private FixedButtonTabbedPanel testCaseChartDiagramButtonPanel;
	private JPanel testCaseChartTabbedPanel;
	
}

package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.TestCase;

public class TestCaseDataPanel{

	private MainFrame mainFrame;
	
	private String testCaseName;
	private String testCasePath;
	
	private int testCaseType;
	
	private JButton testCaseReportDiagramButton;
	private FixedButtonTabbedPanel testCaseReportDiagramButtonPanel;
	private TestCaseReportTabbedPanel testCaseReportTabbedPane;
	
	private JButton testCaseChartDiagramButton;
	private FixedButtonTabbedPanel testCaseChartDiagramButtonPanel;
	private JPanel testCaseChartTabbedPanel;
	
	private List<TestCase> testcaselist=new ArrayList<TestCase>();
	private List<JPanel> testcasereportlist=new ArrayList<JPanel>();
	
	public TestCaseDataPanel(MainFrame mainFrame, String testCaseName) {

		this.mainFrame=mainFrame;
		this.testCaseName=testCaseName;
		
		initbuttonpanel();
		
		initData();
		
//		mainFrame.getStepFiveCenterTabbedPane().ChangeDiagramButtonPanelColor();
//		
//		testCaseReportDiagramButtonPanel.setBackground(new Color(58, 105, 190));
	
	}
	
	public void initData(){
		
		findTestCaseXMLPath();
		
		extractTestCaseData();
		
//		testCaseReportTabbedPane=new TestCaseReportTabbedPanel(this,mainFrame,testCaseName,testCaseType,testcasereportlist);
		testCaseReportTabbedPane=new TestCaseReportTabbedPanel(this,mainFrame);
		testCaseChartTabbedPanel=new JPanel();
		testCaseChartTabbedPanel.setLayout(new GridLayout());
		
		showTestCase();
		
	}

	private void extractTestCaseData() {
		
		if(testCaseType==1){
			testcaselist=mainFrame.getTestCaseConfirmationPanel().extractFunctionalTestDataFromXml(testCasePath);
		}
		else if(testCaseType==2){
			testcaselist=mainFrame.getTestCaseConfirmationPanel().extractPerformanceTestDataFromXml(testCasePath);
		}
		else if(testCaseType==3){
			testcaselist=mainFrame.getTestCaseConfirmationPanel().extractTimeTestDataFromXml(testCasePath);
		}
		
	}

	private void showTestCase() {
		
		if(testCaseType==1){
			showFunctionalTestCase();
		}
		else if(testCaseType==2){
			showPerformanceTestCase();
		}
		else if(testCaseType==3){
			showTimeTestCase();
		}
		
	}
	
	private void showFunctionalTestCase() {

		JPanel resultpanel=new JPanel();
		JPanel emptypanel=new JPanel();
		resultpanel.setOpaque(false);
		emptypanel.setOpaque(false);
		
		GridBagLayout layout = new GridBagLayout();
		resultpanel.setLayout(layout);
		int i=0;
//		functionaltestcasereportlist.clear();
		testcasereportlist.clear();
		for(TestCase tc:testcaselist){
			FunctionalTestCaseReportPartPanel ftcrppanel=new FunctionalTestCaseReportPartPanel(tc);
			resultpanel.add(ftcrppanel);
			layout.setConstraints(ftcrppanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//			functionaltestcasereportlist.add(ftcrppanel);
			testcasereportlist.add(ftcrppanel);
		}
		resultpanel.add(emptypanel);
		layout.setConstraints(emptypanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		JPanel jp = testCaseReportTabbedPane.getTableresultpanel();
		jp.removeAll();
		jp.add(resultpanel);
		
	}
	
	private void showPerformanceTestCase() {

		JPanel resultpanel = new JPanel();
		JPanel emptypanel = new JPanel();
		resultpanel.setOpaque(false);
		emptypanel.setOpaque(false);

		GridBagLayout layout = new GridBagLayout();
		resultpanel.setLayout(layout);
		int i = 0;

		PerformanceTestCaseReportTableHeaderPanel ptcrthpanel = new PerformanceTestCaseReportTableHeaderPanel();
		resultpanel.add(ptcrthpanel);
		layout.setConstraints(ptcrthpanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));

//		performancetestcasereportlist.clear();
		testcasereportlist.clear();
		for (TestCase tc : testcaselist) {
			PerformanceTestCaseReportPartPanel ptcrppanel = new PerformanceTestCaseReportPartPanel(tc);
			resultpanel.add(ptcrppanel);
			layout.setConstraints(ptcrppanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//			performancetestcasereportlist.add(ptcrppanel);
			testcasereportlist.add(ptcrppanel);
		}
		resultpanel.add(emptypanel);
		layout.setConstraints(emptypanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));

		JPanel jp = testCaseReportTabbedPane.getTableresultpanel();
		jp.removeAll();
		jp.add(resultpanel);

	}
	
	private void showTimeTestCase() {
		// TODO Auto-generated method stub

		JPanel resultpanel=new JPanel();
		JPanel emptypanel=new JPanel();
		resultpanel.setOpaque(false);
		emptypanel.setOpaque(false);
		
		GridBagLayout layout = new GridBagLayout();
		resultpanel.setLayout(layout);
		int i=0;
		int index=0;
//		timetestcasereportlist.clear();
		testcasereportlist.clear();
		for(TestCase tc:testcaselist){
			TimeTestCaseReportPartPanel ttcrppanel=new TimeTestCaseReportPartPanel(tc);
			resultpanel.add(ttcrppanel);
			layout.setConstraints(ttcrppanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//			timetestcasereportlist.add(ttcrppanel);
			testcasereportlist.add(ttcrppanel);
			index++;
		}
		resultpanel.add(emptypanel);
		layout.setConstraints(emptypanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		JPanel jp = testCaseReportTabbedPane.getTableresultpanel();
		jp.removeAll();
		jp.add(resultpanel);
		
	}

	private void findTestCaseXMLPath() {
		
		String baseUrl = "D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\";
		
		int starttype=mainFrame.getHomeAllTabbedPanel().getStarttype();
		if(starttype == 1){
			baseUrl += "\\FunctionalTest\\";
		} else if (starttype == 2) {
			baseUrl += "\\PerformanceTest\\";
		} else if (starttype == 3) {
			baseUrl += "\\TimeTest\\";
		}
		
		testCasePath=baseUrl+testCaseName+".xml";
		
		File file=new File(testCasePath);
		if(!file.exists()){
			
			baseUrl = "D:\\ModelDriverProjectFile\\SqlTestCase\\";
			
			testCasePath=baseUrl+testCaseName+".xml";
		}
		
		testCaseType=starttype;
		
		if(testCaseName.contains("����")){
			testCaseType=1;
		}
		else if(testCaseName.contains("����")){
			testCaseType=2;
		}
		else{
			testCaseType=3;
		}
		
	}

	private void initbuttonpanel() {
		// TODO Auto-generated method stub
		
		testCaseReportDiagramButtonPanel=new FixedButtonTabbedPanel(testCaseName+"�Ĳ��Խ���");
		testCaseReportDiagramButtonPanel.setBackground(new Color(77, 96, 130));
		testCaseReportDiagramButton=testCaseReportDiagramButtonPanel.getTabbedbutton();
		
		testCaseChartDiagramButtonPanel=new FixedButtonTabbedPanel(testCaseName+"�Ĳ��Ա���");
		testCaseChartDiagramButtonPanel.setBackground(new Color(77, 96, 130));
		testCaseChartDiagramButton=testCaseChartDiagramButtonPanel.getTabbedbutton();
		
		mainFrame.getStepFiveCenterTabbedPane().getButtonTabbedPanel().add(testCaseReportDiagramButtonPanel);
		mainFrame.getStepFiveCenterTabbedPane().getButtonTabbedPanel().add(testCaseChartDiagramButtonPanel);
		
		mainFrame.getStepFiveCenterTabbedPane().getTestCaseDiagramButtonPanelList().add(testCaseReportDiagramButtonPanel);
		mainFrame.getStepFiveCenterTabbedPane().getTestCaseDiagramButtonPanelList().add(testCaseChartDiagramButtonPanel);

		setButtonActionListener();
		
		testCaseChartDiagramButtonPanel.setVisible(false);
		
	}

	private void setButtonActionListener() {
		// TODO Auto-generated method stub
		
		testCaseReportDiagramButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				mainFrame.getStepFiveCenterTabbedPane().getDiagramPanel().removeAll();
				mainFrame.getStepFiveCenterTabbedPane().getDiagramPanel().setLayout(new GridLayout());
				mainFrame.getStepFiveCenterTabbedPane().getDiagramPanel().add(testCaseReportTabbedPane);
				
				mainFrame.getStepFiveCenterTabbedPane().ChangeDiagramButtonPanelColor();
				
				testCaseReportDiagramButtonPanel.setBackground(new Color(58, 105, 190));
				
				if(mainFrame.getStepindex()==5){
					mainFrame.getStepFiveCenterTabbedPane().ChangeRepaint();
				}
				
			}
		});
		
		testCaseChartDiagramButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				mainFrame.getStepFiveCenterTabbedPane().getDiagramPanel().removeAll();
				mainFrame.getStepFiveCenterTabbedPane().getDiagramPanel().setLayout(new GridLayout());
				mainFrame.getStepFiveCenterTabbedPane().getDiagramPanel().add(testCaseChartTabbedPanel);
				
				mainFrame.getStepFiveCenterTabbedPane().ChangeDiagramButtonPanelColor();
				
				testCaseChartDiagramButtonPanel.setBackground(new Color(58, 105, 190));
				
				mainFrame.getStepFiveCenterTabbedPane().ChangeRepaint();
				
			}
		});
		
	}

	public FixedButtonTabbedPanel getTestCaseReportDiagramButtonPanel() {
		return testCaseReportDiagramButtonPanel;
	}

	public FixedButtonTabbedPanel getTestCaseChartDiagramButtonPanel() {
		return testCaseChartDiagramButtonPanel;
	}

	public String getTestCaseName() {
		return testCaseName;
	}

	public String getTestCasePath() {
		return testCasePath;
	}

	public int getTestCaseType() {
		return testCaseType;
	}

	public List<TestCase> getTestcaselist() {
		return testcaselist;
	}

	public List<JPanel> getTestcasereportlist() {
		return testcasereportlist;
	}

	public JPanel getTestCaseChartTabbedPanel() {
		return testCaseChartTabbedPanel;
	}
	
}
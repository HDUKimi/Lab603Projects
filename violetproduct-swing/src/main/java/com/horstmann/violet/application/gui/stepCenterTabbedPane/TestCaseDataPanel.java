package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.util.chenzuo.Bean.TestCase;

public class TestCaseDataPanel{

	private MainFrame mainFrame;
	
	private String testCaseName;
	private String testCasePath;
	
	private int starttype;
	private int hastime;
	
	private JButton testCaseReportDiagramButton;
	private JLabel testCaseReportDiagramDeleteLabel;
	private FixedButtonTabbedPanel testCaseReportDiagramButtonPanel;
	private TestCaseReportTabbedPanel testCaseReportTabbedPane;
	
	private JButton testCaseChartDiagramButton;
	private JLabel testCaseChartDiagramDeleteLabel;
	private FixedButtonTabbedPanel testCaseChartDiagramButtonPanel;
	private JPanel testCaseChartTabbedPanel;
	
	private List<TestCase> testcaselist=new ArrayList<TestCase>();
	private List<JPanel> testcasereportlist=new ArrayList<JPanel>();
	
	public TestCaseDataPanel(MainFrame mainFrame, String testCaseName, List<TestCase> testcaselist, int starttype, int hastime) {

		this.mainFrame=mainFrame;
		this.testCaseName=testCaseName;
		this.testcaselist=testcaselist;
		this.starttype=starttype;
		this.hastime=hastime;
		
		initbuttonpanel();
		
		initData();
		
//		mainFrame.getStepFiveCenterTabbedPane().ChangeDiagramButtonPanelColor();
//		
//		testCaseReportDiagramButtonPanel.setBackground(new Color(58, 105, 190));
	
	}
	
	public void initData(){
		
//		findTestCaseXMLPath();
//		
//		extractTestCaseData();
		
//		testCaseReportTabbedPane=new TestCaseReportTabbedPanel(this,mainFrame,testCaseName,testCaseType,testcasereportlist);
		testCaseReportTabbedPane=new TestCaseReportTabbedPanel(this,mainFrame);
		testCaseChartTabbedPanel=new JPanel();
		testCaseChartTabbedPanel.setLayout(new GridLayout());
		
		int index=1;
		for (TestCase tc : testcaselist) {
			tc.setTestCaseID(String.valueOf(index));
			index++;
		}
		
		showTestCase();
		
	}

	private void extractTestCaseData() {
		
		if(starttype==1&&hastime==0){
			testcaselist=mainFrame.getTestCaseConfirmationPanel().extractFunctionalTestDataFromXml(testCasePath);
		}
		else if(starttype==2&&hastime==0){
			testcaselist=mainFrame.getTestCaseConfirmationPanel().extractPerformanceTestDataFromXml(testCasePath);
		}
		else if(hastime==1){
			testcaselist=mainFrame.getTestCaseConfirmationPanel().extractTimeTestDataFromXml(testCasePath);
		}
		
	}

	private void showTestCase() {
		
		if(starttype==1&&hastime==0){
			showFunctionalTestCase();
		}
		else if(starttype==2&&hastime==0){
//			showPerformanceTestCase();
			showFunctionalTestCase();
		}
		else if(hastime==1){
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

//	private void findTestCaseXMLPath() {
//		
//		String baseUrl = "D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\";
//		
//		File file = null;
//		int type=1;
//		if(type == 1){
//			testCasePath=baseUrl+"\\FunctionalTest\\"+testCaseName+".xml";
//			file=new File(testCasePath);
//			if(!file.exists()){
//				type++;
//			}
//		} 
//		if (type == 2) {
//			testCasePath=baseUrl+"\\PerformanceTest\\"+testCaseName+".xml";
//			file=new File(testCasePath);
//			if(!file.exists()){
//				type++;
//			}
//		} 
//		if (type == 3) {
//			testCasePath=baseUrl+"\\TimeTest\\"+testCaseName+".xml";
//			file=new File(testCasePath);
//			if(!file.exists()){
//				type++;
//			}
//		}
//		
//		if(!file.exists()){
//			
//			baseUrl = "D:\\ModelDriverProjectFile\\SqlTestCase\\";
//			
//			testCasePath=baseUrl+testCaseName+".xml";
//		}
//		
//		try {
//			testCaseType=TestCaseXMLType(testCasePath);
//		} catch (DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
	public int TestCaseXMLType(String path) throws DocumentException{
		
		int type=1;
		
		SAXReader reader=new SAXReader();
		File file=new File(path);
		
		Document document=reader.read(file);
		
		Element root=document.getRootElement();
		
//		List<Element> testcaseElements=root.elements("testcase");
		Element testcaseElement=root.element("testcase");
		
		Element limitElement=null;
		limitElement=testcaseElement.element("limit");
		if(limitElement!=null){
			type=3;
		}
		else{
			
			Element outputElement=null;
			outputElement=testcaseElement.element("process").element("output");
			if(outputElement!=null){
				type=2;
			}
			
		}
		
		return type;
		
	}

	private void initbuttonpanel() {
		// TODO Auto-generated method stub
		
		testCaseReportDiagramButtonPanel=new FixedButtonTabbedPanel(testCaseName);
		testCaseReportDiagramButtonPanel.setBackground(new Color(77, 96, 130));
		testCaseReportDiagramButton=testCaseReportDiagramButtonPanel.getTabbedbutton();
		testCaseReportDiagramDeleteLabel=testCaseReportDiagramButtonPanel.getDelectlabel();
		
		testCaseChartDiagramButtonPanel=new FixedButtonTabbedPanel(testCaseName+"结果报告");
		testCaseChartDiagramButtonPanel.setBackground(new Color(77, 96, 130));
		testCaseChartDiagramButton=testCaseChartDiagramButtonPanel.getTabbedbutton();
		testCaseChartDiagramDeleteLabel=testCaseChartDiagramButtonPanel.getDelectlabel();
		
		mainFrame.getStepFiveCenterTabbedPane().getButtonTabbedPanel().add(testCaseReportDiagramButtonPanel);
		mainFrame.getStepFiveCenterTabbedPane().getButtonTabbedPanel().add(testCaseChartDiagramButtonPanel);
		
		mainFrame.getStepFiveCenterTabbedPane().getTestCaseDiagramButtonPanelList().add(testCaseReportDiagramButtonPanel);
		mainFrame.getStepFiveCenterTabbedPane().getTestCaseDiagramButtonPanelList().add(testCaseChartDiagramButtonPanel);

		setButtonActionListener();
		
		setDeleteLabelActionListener();
		
		testCaseChartDiagramButtonPanel.setVisible(false);
		
	}

	private void setDeleteLabelActionListener() {
		// TODO Auto-generated method stub
		
		testCaseReportDiagramDeleteLabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				testCaseReportDiagramButtonPanel.setVisible(false);
				
				if(mainFrame.getStepFiveCenterTabbedPane().getSelectedFixedButtonTabbedPanel().equals(testCaseReportDiagramButtonPanel)){
					mainFrame.getStepFiveCenterTabbedPane().getDiagramPanel().removeAll();
				}
				
				if(mainFrame.getStepindex()==5){
					mainFrame.getStepFiveCenterTabbedPane().ChangeRepaint();
				}
				
			}
		});
		
		testCaseChartDiagramDeleteLabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				testCaseChartDiagramButtonPanel.setVisible(false);
				
				if(mainFrame.getStepFiveCenterTabbedPane().getSelectedFixedButtonTabbedPanel().equals(testCaseChartDiagramButtonPanel)){
					mainFrame.getStepFiveCenterTabbedPane().getDiagramPanel().removeAll();
				}
				
				if(mainFrame.getStepindex()==5){
					mainFrame.getStepFiveCenterTabbedPane().ChangeRepaint();
				}
				
			}
		});
		
	}

	private void setButtonActionListener() {
		// TODO Auto-generated method stub
		
		testCaseReportDiagramButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				mainFrame.getStepFiveCenterTabbedPane().setSelectedFixedButtonTabbedPanel(testCaseReportDiagramButtonPanel);
				
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
				
				mainFrame.getStepFiveCenterTabbedPane().setSelectedFixedButtonTabbedPanel(testCaseChartDiagramButtonPanel);
				
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

	public int getStarttype() {
		return starttype;
	}

	public int getHastime() {
		return hastime;
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

package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.FunctionFailedStatisticsPieChart;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.FunctionSuccessFailedPieChart;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.PerformanceExeTimeLineChart;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.PerformanceExeTimeScatterPlotChart;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.PerformanceHighBatteryLineChart;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.PerformanceHighSpeedBarChart;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.PerformanceHighTimeLineChart;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.PerformanceTimeSpeedBarChart;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.TimeFailedStatisticsPieChart;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.TimeSuccessFailedPieChart;
import com.horstmann.violet.application.gui.util.chenzuo.Bean.Pair;
import com.horstmann.violet.application.gui.util.chenzuo.Bean.TestCase;
import com.horstmann.violet.application.gui.util.chenzuo.Bean.TestCaseResult;
import com.horstmann.violet.application.gui.util.chenzuo.Bean.myProcess;
import com.horstmann.violet.application.gui.util.chenzuo.Util.TcConvertUtil;

public class TestCaseDataPanel{

	private MainFrame mainFrame;
	
	private String testCaseTabName;
	private String testCaseName;
	private String testCasePath;
	
	private int hastime;//是否含有时间，0：不含，1含有
	private int showAll;//0:本地	1:DB
	private int testcasetype;//测试用例显示类别，1：功能，2：性能，3：时间
	private int testcaseattribute;//测试用例xml类别，1：功能，2：性能，3：边界值
	
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
	
	public TestCaseDataPanel(MainFrame mainFrame, String testCaseTabName, String testCaseName, List<TestCase> testcaselist, int testcasetype, int testcaseattribute, int hastime, int showAll) {

		this.mainFrame=mainFrame;
		this.testCaseTabName=testCaseTabName;
		this.testCaseName=testCaseName;
		this.testcaselist=testcaselist;
		this.testcasetype=testcasetype;
		this.testcaseattribute=testcaseattribute;
		this.hastime=hastime;
		this.showAll=showAll;
		
		this.testCaseTabName="功能测试";
		
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
		
		Collections.sort(testcaselist, new Comparator<TestCase>() {

			@Override
			public int compare(TestCase o1, TestCase o2) {
				
				int id1=Integer.parseInt(o1.getTestCaseID());
				int id2=Integer.parseInt(o2.getTestCaseID());
				
				return id1-id2;
			}
		});
		
		showTestCase();
		
		if(showAll==1){
			showStatisticsDataByType(testcasetype,testcaseattribute);
			testCaseChartDiagramButtonPanel.setVisible(true);
		}
		
	}
	
	protected void showStatisticsDataByType(int type, int attribute) {
		
		if(type==1){//统计功能数据
			
			if(attribute==2){
				List<Double> exetimelist=new ArrayList<>();
				
				for(TestCase testCase:testcaselist){
					exetimelist.add(Double.parseDouble(testCase.getExetime()));
				}
				
				FunctionalTestCaseChartTabbedPanel functionalTestCaseChartTabbedPanel=new FunctionalTestCaseChartTabbedPanel(mainFrame);
				
				testCaseChartTabbedPanel.removeAll();
				testCaseChartTabbedPanel.add(functionalTestCaseChartTabbedPanel);
				
				PerformanceExeTimeLineChart petlc=new PerformanceExeTimeLineChart(exetimelist);
				functionalTestCaseChartTabbedPanel.getPerformanceexetimelinepanel().removeAll();
				functionalTestCaseChartTabbedPanel.getPerformanceexetimelinepanel().add(petlc.createChart());
				
				functionalTestCaseChartTabbedPanel.showPerformaneExeTimeLine();
				
//				PerformanceExeTimeScatterPlotChart petspc=new PerformanceExeTimeScatterPlotChart(exetimelist);
//				functionalTestCaseChartTabbedPanel.getSuccessfailedpiepanel().removeAll();
//				functionalTestCaseChartTabbedPanel.getSuccessfailedpiepanel().add(petspc.createChart());
				
			}
			else{
				Map<String, Object> testcasemap=TcConvertUtil.functionStatistics(testcaselist);
				List<Integer> caseSuccess=(List<Integer>) testcasemap.get("caseSuccess");
				List<Integer> caseFailed=(List<Integer>) testcasemap.get("caseFailed");
				Map<String,List<Map<Integer,List<Integer>>>> failedStatistics=(Map<String, List<Map<Integer, List<Integer>>>>) testcasemap.get("failedStatistics");
						
				FunctionalTestCaseChartTabbedPanel functionalTestCaseChartTabbedPanel=new FunctionalTestCaseChartTabbedPanel(mainFrame);
				
				testCaseChartTabbedPanel.removeAll();
				testCaseChartTabbedPanel.add(functionalTestCaseChartTabbedPanel);
				
				DefaultTableModel tabelmodel=functionalTestCaseChartTabbedPanel.getAttributetablemodel();
				
				int cs=0,cf=0,csum=0;
				cs=caseSuccess.size();
				cf=caseFailed.size();
				
//				cs=7935;
//				cf=39;
				
				csum=cs+cf;
				DefaultTableModel successfailedtabelmodel=functionalTestCaseChartTabbedPanel.getSuccessfailedattributetablemodel();
				while(successfailedtabelmodel.getRowCount()>0){
					successfailedtabelmodel.removeRow(0);
				}
				Object[] rowData1={"合计：",cs, cf, csum};
				successfailedtabelmodel.addRow(rowData1);
				Object[] rowData2={"百分比：",calcper(cs, csum), calcper(cf, csum), calcper(csum, csum)};
				successfailedtabelmodel.addRow(rowData2);
				
				int f1=0,f2=0;
				if (failedStatistics.containsKey("测试用例有误")) {
					f1=failedStatistics.get("测试用例有误").size();
				}
				if(failedStatistics.containsKey("程序出现死循环或者抛出异常")){
					f2=failedStatistics.get("程序出现死循环或者抛出异常").size();
				}
				
//				f1=39;
//				f2=0;
				
				DefaultTableModel failedstatisticstabelmodel=functionalTestCaseChartTabbedPanel.getFailedstatisticsattributetablemodel();
				while(failedstatisticstabelmodel.getRowCount()>0){
					failedstatisticstabelmodel.removeRow(0);
				}
				Object[] rowData3={"合计：", f1, f2, cf};
				failedstatisticstabelmodel.addRow(rowData3);
				Object[] rowData4={"百分比：", calcper(f1, cf), calcper(f2, cf), calcper(cf, cf)};
				failedstatisticstabelmodel.addRow(rowData4);
				
				
				FunctionSuccessFailedPieChart fsfpc=new FunctionSuccessFailedPieChart(cs, cf);
				functionalTestCaseChartTabbedPanel.getSuccessfailedpiepanel().removeAll();
				functionalTestCaseChartTabbedPanel.getSuccessfailedpiepanel().add(fsfpc.createChart());
				
				FunctionFailedStatisticsPieChart ffspc=new FunctionFailedStatisticsPieChart(f1, f2);
				functionalTestCaseChartTabbedPanel.getFailedstatisticspiepanel().removeAll();
				functionalTestCaseChartTabbedPanel.getFailedstatisticspiepanel().add(ffspc.createChart());
				
				if(cf==0){
					functionalTestCaseChartTabbedPanel.showSuccessFailedPie(0);
				}
				else{
					functionalTestCaseChartTabbedPanel.showSuccessFailedPie(1);
				}
			}
			
		}
		else if(type==2){//统计性能数据

			Map testcasemap=TcConvertUtil.testCaseStatistics(testcaselist);

			List<Pair> highspeeddata=(List<Pair>) testcasemap.get("high-speed");
			List<Pair> timespeeddata=(List<Pair>) testcasemap.get("time-speed");
			Map<String, List<Pair>> highbatterydata=(Map<String, List<Pair>>) testcasemap.get("high-battery");
			Map<String, List<Pair>> hightimedata=(Map<String, List<Pair>>) testcasemap.get("high-time");
			
			PerformanceTestCaseChartTabbedPanel performanceTestCaseChartTabbedPanel=new PerformanceTestCaseChartTabbedPanel(mainFrame);
			
			testCaseChartTabbedPanel.removeAll();
			testCaseChartTabbedPanel.add(performanceTestCaseChartTabbedPanel);
			
			DefaultTableModel tabelmodel=performanceTestCaseChartTabbedPanel.getAttributetablemodel();
			while(tabelmodel.getRowCount()>0){
				tabelmodel.removeRow(0);
			}
			for(TestCase tc:testcaselist){
				TestCaseResult tcr=tc.getResult();
				Object[] rowData={tc.getTestCaseID(),tcr.getWind_speed(),tcr.getTakeoff_alt(),tcr.getBattery_remaining(),tcr.getTime()};
				tabelmodel.addRow(rowData);
			}
			
			PerformanceHighBatteryLineChart phblc=new PerformanceHighBatteryLineChart(highbatterydata);
			performanceTestCaseChartTabbedPanel.getHighbatterylinepanel().removeAll();
			performanceTestCaseChartTabbedPanel.getHighbatterylinepanel().add(phblc.createChart());
			
			PerformanceHighTimeLineChart phtlc=new PerformanceHighTimeLineChart(hightimedata);
			performanceTestCaseChartTabbedPanel.getHightimelinepanel().removeAll();
			performanceTestCaseChartTabbedPanel.getHightimelinepanel().add(phtlc.createChart());
			
			PerformanceHighSpeedBarChart phsbc=new PerformanceHighSpeedBarChart(highspeeddata);
			performanceTestCaseChartTabbedPanel.getHighspeedbarpanel().removeAll();
			performanceTestCaseChartTabbedPanel.getHighspeedbarpanel().add(phsbc.createChart());
			
			PerformanceTimeSpeedBarChart ptsbc=new PerformanceTimeSpeedBarChart(timespeeddata);
			performanceTestCaseChartTabbedPanel.getTimespeedbarpanel().removeAll();
			performanceTestCaseChartTabbedPanel.getTimespeedbarpanel().add(ptsbc.createChart());
			
		}
		else if(type==3){
			
			if(attribute==2){
				List<Double> exetimelist=new ArrayList<>();
				
				for(TestCase testCase:testcaselist){
					exetimelist.add(Double.parseDouble(testCase.getExetime()));
				}
				
				TimeTestCaseChartTabbedPanel timeTestCaseChartTabbedPanel=new TimeTestCaseChartTabbedPanel(mainFrame);
				
				testCaseChartTabbedPanel.removeAll();
				testCaseChartTabbedPanel.add(timeTestCaseChartTabbedPanel);
				
				PerformanceExeTimeLineChart petlc=new PerformanceExeTimeLineChart(exetimelist);
				timeTestCaseChartTabbedPanel.getPerformanceexetimelinepanel().removeAll();
				timeTestCaseChartTabbedPanel.getPerformanceexetimelinepanel().add(petlc.createChart());
				
				timeTestCaseChartTabbedPanel.showPerformaneExeTimeLine();
				
//				PerformanceExeTimeScatterPlotChart petspc=new PerformanceExeTimeScatterPlotChart(exetimelist);
//				timeTestCaseChartTabbedPanel.getSuccessfailedpiepanel().removeAll();
//				timeTestCaseChartTabbedPanel.getSuccessfailedpiepanel().add(petspc.createChart());
				
			}
			else{
				Map<String, Integer> resultmap=TcConvertUtil.timeStatistics(testcaselist);
				
				TimeTestCaseChartTabbedPanel timeTestCaseChartTabbedPanel=new TimeTestCaseChartTabbedPanel(mainFrame);
				
				testCaseChartTabbedPanel.removeAll();
				testCaseChartTabbedPanel.add(timeTestCaseChartTabbedPanel);
				
				DefaultTableModel tabelmodel=timeTestCaseChartTabbedPanel.getAttributetablemodel();
				
				int cs=0,cf=0,csum=0;
				cs=resultmap.get("success");
				cf=resultmap.get("failed");
				
//				cs=180;
//				cf=39;
				
				csum=cs+cf;
				DefaultTableModel successfailedtabelmodel=timeTestCaseChartTabbedPanel.getSuccessfailedattributetablemodel();
				while(successfailedtabelmodel.getRowCount()>0){
					successfailedtabelmodel.removeRow(0);
				}
				Object[] rowData1={"合计：",cs, cf, csum};
				successfailedtabelmodel.addRow(rowData1);
				Object[] rowData2={"百分比：",calcper(cs, csum), calcper(cf, csum), calcper(csum, csum)};
				successfailedtabelmodel.addRow(rowData2);
				
				int f1=0,f2=0;
				
				f1=resultmap.get("testcasefailed");
				f2=resultmap.get("timefailed");
				
//				f1=0;
//				f2=0;
//				cf=0;
				
				DefaultTableModel failedstatisticstabelmodel=timeTestCaseChartTabbedPanel.getFailedstatisticsattributetablemodel();
				while(failedstatisticstabelmodel.getRowCount()>0){
					failedstatisticstabelmodel.removeRow(0);
				}
				Object[] rowData3={"合计：", f1, f2, cf};
				failedstatisticstabelmodel.addRow(rowData3);
				Object[] rowData4={"百分比：", calcper(f1, cf), calcper(f2, cf), calcper(cf, cf)};
				failedstatisticstabelmodel.addRow(rowData4);
				
				TimeSuccessFailedPieChart tsfpc=new TimeSuccessFailedPieChart(cs, cf);
				timeTestCaseChartTabbedPanel.getSuccessfailedpiepanel().removeAll();
				timeTestCaseChartTabbedPanel.getSuccessfailedpiepanel().add(tsfpc.createChart());
				
				TimeFailedStatisticsPieChart tfspc=new TimeFailedStatisticsPieChart(f1, f2);
				timeTestCaseChartTabbedPanel.getFailedstatisticspiepanel().removeAll();
				timeTestCaseChartTabbedPanel.getFailedstatisticspiepanel().add(tfspc.createChart());
				
				if(cf==0){
					timeTestCaseChartTabbedPanel.showSuccessFailedPie(0);
				}
				else{
					timeTestCaseChartTabbedPanel.showSuccessFailedPie(1);
				}
			}
			
		}
		
	}

	public String calcper(int a,int b){
		if(a==b){
			return "100 %";
		}
		else if(a==0){
			return "0 %";
		}
		else{
			
			NumberFormat ddf1=NumberFormat.getNumberInstance() ; 
			ddf1.setMaximumFractionDigits(2); 
			
			return ddf1.format(a*1.0/b*100)+" %";
		}
	}
	
	private void extractTestCaseData() {
		
		if(testcasetype==1){
			testcaselist=mainFrame.getTestCaseConfirmationPanel().extractFunctionalTestDataFromXml(testCasePath);
		}
		else if(testcasetype==2){
			testcaselist=mainFrame.getTestCaseConfirmationPanel().extractPerformanceTestDataFromXml(testCasePath);
		}
		else if(testcasetype==3){
			testcaselist=mainFrame.getTestCaseConfirmationPanel().extractTimeTestDataFromXml(testCasePath);
		}
		
	}

	private void showTestCase() {
//		System.out.println("-------------------------");
//		for(TestCase tc:testcaselist){
//			for(myProcess process:tc.getProcessList()){
//				if(process.getProcessName().equals("thisFloorPeopleIn()")){
//					System.out.println(process.getProcessParam());
//				}
//			}
//		}
//		System.out.println("-------------------------");
		
		if(testcasetype==1){
			showFunctionalTestCase();
		}
		else if(testcasetype==2){
			showPerformanceTestCase();
//			showFunctionalTestCase();
		}
		else if(testcasetype==3){
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
			FunctionalTestCaseReportPartPanel ftcrppanel=new FunctionalTestCaseReportPartPanel(mainFrame, tc, testcaseattribute, showAll);
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
			PerformanceTestCaseReportPartPanel ptcrppanel = new PerformanceTestCaseReportPartPanel(mainFrame, tc, showAll);
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
			TimeTestCaseReportPartPanel ttcrppanel=new TimeTestCaseReportPartPanel(mainFrame, tc, testcaseattribute, showAll);
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
		
		testCaseReportDiagramButtonPanel=new FixedButtonTabbedPanel(testCaseTabName);
		testCaseReportDiagramButtonPanel.setBackground(new Color(77, 96, 130));
		testCaseReportDiagramButton=testCaseReportDiagramButtonPanel.getTabbedbutton();
		testCaseReportDiagramDeleteLabel=testCaseReportDiagramButtonPanel.getDelectlabel();
		
		testCaseChartDiagramButtonPanel=new FixedButtonTabbedPanel(testCaseTabName+"结果报告");
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

	public String getTestCaseTabName() {
		return testCaseTabName;
	}

	public String getTestCaseName() {
		return testCaseName;
	}

	public String getTestCasePath() {
		return testCasePath;
	}

	public int getTestcasetype() {
		return testcasetype;
	}

	public int getTestcaseattribute() {
		return testcaseattribute;
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

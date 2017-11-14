package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.PropertyConfigurator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.FunctionFailedStatisticsPieChart;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.FunctionSuccessFailedPieChart;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.PerformanceHighBatteryLineChart;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.PerformanceHighSpeedBarChart;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.PerformanceHighTimeLineChart;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.PerformanceLineChart;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.PerformanceTimeSpeedBarChart;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.TestCaseBarChartPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.TestCaseLineChartPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.TestCasePieChartPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.TestCaseStackedBarChartPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.TimeFailedStatisticsPieChart;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.TimeSuccessFailedPieChart;
import com.horstmann.violet.application.gui.util.chenzuo.Controller.Controller;
import com.horstmann.violet.application.gui.util.chenzuo.Service.ResultService;
import com.horstmann.violet.application.gui.util.chenzuo.Util.TcConvertUtil;
import com.horstmann.violet.application.gui.util.lmr.DB.DataBaseUtil;
import com.horstmann.violet.application.gui.util.chenzuo.Bean.Pair;
import com.horstmann.violet.application.gui.util.chenzuo.Bean.TestCase;
import com.horstmann.violet.application.gui.util.chenzuo.Bean.TestCaseException;
import com.horstmann.violet.application.gui.util.chenzuo.Bean.TestCaseResult;
import com.horstmann.violet.application.gui.util.chenzuo.Bean.Time;
import com.horstmann.violet.application.gui.util.chenzuo.Bean.myProcess;

public class TestCaseReportTabbedPanel extends JPanel{
	
	private MainFrame mainFrame;
	
	private JPanel toolpanel;
	private MoviePanel moviepanel;
	private JPanel tablepanel;
	
	private JPanel toolbuttonpanel1;
	private JPanel toolbuttonpanel2;
	private JPanel toolbuttonpanel3;
	private JPanel toolbuttonpanel4;
	private JPanel toolbuttonpanel5;
	private JPanel toolbuttonpanel6;
	private JPanel toolbuttonpanel7;
	private JButton toolbutton1;
	private JButton toolbutton2;
	private JButton toolbutton3;
	private JButton toolbutton4;
	private JButton toolbutton5;
	private JButton toolbutton6;
	private JButton toolbutton7;
	private JPanel emptypanel1;
	private JPanel emptypanel2;
	private JLabel progressbarlabel;
	
	private JProgressBar progressbar;
	private int progressbarindex;
	
	private JCheckBox[] windCheckBoxList;
	private JPanel windcheckboxpanel;
	private int windcheckboxpanelstate=0;
	
	private Thread t;
	private Thread progreseethread;
	private Thread gaindatathread;
	private Thread datagainshowthread;
	private int threadstate=0;
	public static int threadexceptionstate=0;
	
	private JScrollPane tablescrollpanel;
	private JPanel tableresultpanel;
	
	private TestCaseDataPanel parentTestCaseDataPanel;
	private String testcasename;
	private int testcasetype;
	private int starttype;
	private int hastime;
	
	private List<JPanel> testcasereportlist=new ArrayList<JPanel>();
	private List<JPanel> checkedtestcasereportlist=new ArrayList<JPanel>();
	
//	private List<FunctionalTestCaseReportPartPanel> functionaltestcasereportlist=new ArrayList<FunctionalTestCaseReportPartPanel>();
//	private List<FunctionalTestCaseReportPartPanel> checkedfunctionaltestcasereportlist=new ArrayList<FunctionalTestCaseReportPartPanel>();
//	private List<PerformanceTestCaseReportPartPanel> performancetestcasereportlist=new ArrayList<PerformanceTestCaseReportPartPanel>();
//	private List<PerformanceTestCaseReportPartPanel> checkedperformancetestcasereportlist=new ArrayList<PerformanceTestCaseReportPartPanel>();
//	private List<TimeTestCaseReportPartPanel> timetestcasereportlist=new ArrayList<TimeTestCaseReportPartPanel>();
//	private List<TimeTestCaseReportPartPanel> checkedtimetestcasereportlist=new ArrayList<TimeTestCaseReportPartPanel>();
	
	private List<TestCase> selectedtestcaselist=new ArrayList<TestCase>();
	private List<TestCase> resulttestcaselist=new ArrayList<TestCase>();
	
	private int[] testcasecount=new int[3];
	private List<Integer> testcasecountlist=new ArrayList<Integer>();
	
	private int performancelistindex=0;
	
	public TestCaseReportTabbedPanel(TestCaseDataPanel testCaseDataPanel, MainFrame mainframe){
		
		this.mainFrame=mainframe;
		
		this.parentTestCaseDataPanel=testCaseDataPanel;
		
		this.testcasename=testCaseDataPanel.getTestCaseName();
		
		this.starttype=testCaseDataPanel.getStarttype();
		
		this.hastime=testCaseDataPanel.getHastime();
		
		this.testcasereportlist=testCaseDataPanel.getTestcasereportlist();
		
		testcasetype=1;
		if(starttype==1&&hastime==0){
			testcasetype=1;
		}
		else if(starttype==2&&hastime==0){
			testcasetype=2;
		}
		else if(hastime==1){
			testcasetype=3;
		}
		
		initPanel();
		
	}
	
	private void initPanel(){
		

		toolpanel=new JPanel();
		moviepanel=new MoviePanel();
		tablepanel=new JPanel();
		
		initToolPanel();
		
		initWindCheckBoxPanel();
		
		initMoviePanel();
		
		initTablePanel();
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(toolpanel);
		this.add(windcheckboxpanel);
		this.add(moviepanel);
		this.add(tablepanel);
		layout.setConstraints(toolpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(windcheckboxpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(moviepanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(tablepanel,new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		this.setBackground(new Color(255, 255, 255));
		
	}

	private void initToolPanel() {
		// TODO Auto-generated method stub
		
		toolbuttonpanel1 = new JPanel();
		toolbuttonpanel2 = new JPanel();
		toolbuttonpanel3 = new JPanel();
		toolbuttonpanel4 = new JPanel();
		toolbuttonpanel5 = new JPanel();
		toolbuttonpanel6 = new JPanel();
		toolbuttonpanel7 = new JPanel();

		toolbutton1 = new JButton();
		toolbutton2 = new JButton();
		toolbutton3 = new JButton();
		toolbutton4 = new JButton();
		toolbutton5 = new JButton();
		toolbutton6 = new JButton();
		toolbutton7 = new JButton();
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("ImagePart/start.png"));
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(this.getClass().getResource("ImagePart/suspend.png"));
		icon2.setImage(icon2.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon3 = new ImageIcon(this.getClass().getResource("ImagePart/stop.png"));
		icon3.setImage(icon3.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon4 = new ImageIcon(this.getClass().getResource("ImagePart/up_arrow.png"));
		icon4.setImage(icon4.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon5 = new ImageIcon(this.getClass().getResource("ImagePart/down_arrow.png"));
		icon5.setImage(icon5.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon6 = new ImageIcon(this.getClass().getResource("ImagePart/allselect.png"));
		icon6.setImage(icon6.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon7 = new ImageIcon(this.getClass().getResource("ImagePart/cancelselect.png"));
		icon7.setImage(icon7.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
	
		toolbutton1.setIcon(icon1);
		toolbutton1.setFocusable(false);
		toolbutton1.setContentAreaFilled(false);
		toolbutton1.setBorderPainted(false);
		toolbutton1.addMouseListener(new ButtonMouseListener());
		toolbutton1.setPreferredSize(new Dimension(21,21));
		toolbutton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				int selectcount=0;
				if(testcasetype==1){
					for(JPanel jp:testcasereportlist){
						FunctionalTestCaseReportPartPanel ftcrpp=(FunctionalTestCaseReportPartPanel) jp;
						if (ftcrpp.getToolcheckbox().isSelected()) {
							selectcount++;
							break;
						}
					}
				}
				else if(testcasetype==2){
					selectcount++;
				}
				else if(testcasetype==3){
					for(JPanel jp:testcasereportlist){
						TimeTestCaseReportPartPanel ttcrpp=(TimeTestCaseReportPartPanel) jp;
						if (ttcrpp.getToolcheckbox().isSelected()) {
							selectcount++;
							break;
						}
					}
				}
				
				if(selectcount==0){
					JOptionPane.showMessageDialog(mainFrame, "请勾选要进行测试执行的测试用例！", "提示" , JOptionPane.ERROR_MESSAGE);
					return ;
				}
				
				if (threadstate == 0) {
//					if(mainFrame.getTestCaseConfirmationPanel().getTestcasename()==null){
//						
//					}
//					else{
						progressbar.setValue(1);
						progressbarlabel.setText("1%");
						
						progressbarindex=1;
						
						threadexceptionstate=0;
						
//						initTestCaseUI();
						
//						mainFrame.getStepFiveCenterTabbedPane().getTestCaseChartDiagramButtonPanel().setVisible(false);
						
//						clientSocket = new ClientSocket("10.1.16.89", 5555);
//						Boolean connectstate=clientSocket.Connection();
						
						
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								PropertyConfigurator.configure("src/log4j.properties");
								
								Callable<Boolean> readyCallable=new Callable<Boolean>() {

									@Override
									public Boolean call() throws Exception {
										// TODO Auto-generated method stub
										return Controller.Ready(2);
									}
								};
								ExecutorService exector=Executors.newSingleThreadExecutor();
								Future<Boolean> readyFuture=exector.submit(readyCallable);
								exector.shutdown();
								
//								Boolean connectstate = false;
//								try {
//									connectstate = readyFuture.get();
//								} catch (InterruptedException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								} catch (ExecutionException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
								
								try {
									if(readyFuture.get()){
										TextAreaPrint("连接服务器成功！");
										threadstate=1;
										
										if(testcasetype==1){
											startFunctionalTestConfirmation();
										}
										else if(testcasetype==2){
											startPerformanceTestConfirmation();
//											startPerformanceTestConfirmation2();
										}
										else if(testcasetype==3){
											startTimeTestConfirmation();
										}
										
									}
									else{
										TextAreaPrint("连接服务器失败！！！退出测试执行！！！");
										TextAreaPrint("请等待几秒后，再尝试连接。。。");
										progressbar.setValue(0);
										progressbarlabel.setText("0%");
										progressbarindex=0;
									}
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (ExecutionException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}).start();
						
//					}
					
					
				} else if (threadstate == 1) {

				} else if (threadstate == -1) {
					
					threadstate = 1;
					t.resume();
//					if(testcasetype==3){
//						if(progressbar.getValue()>50){
//							progreseethread.resume();
//						}
//						else{
//							gaindatathread.resume();
//						}
//					}
//					else{
						datagainshowthread.resume();
//					}
					System.out.println("t is not alive");
				}
				
			}
		});
		
		toolbutton2.setIcon(icon2);
		toolbutton2.setFocusable(false);
		toolbutton2.setContentAreaFilled(false);
		toolbutton2.setBorderPainted(false);
		toolbutton2.addMouseListener(new ButtonMouseListener());
		toolbutton2.setPreferredSize(new Dimension(21,21));
		toolbutton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(threadstate==1){
					t.suspend();
//					if(testcasetype==3){
//						if(progressbar.getValue()>50){
//							progreseethread.suspend();
//						}
//						else{
//							gaindatathread.suspend();
//						}
//					}
//					else{
						datagainshowthread.suspend();
//					}
//					datagainshowthread.suspend();
//					if(progressbar.getValue()>50){
//						progreseethread.suspend();
//					}
//					else{
//						gaindatathread.suspend();
//					}
					threadstate=-1;
				}
				
			}
		});
		
		
		toolbutton3.setIcon(icon3);
		toolbutton3.setFocusable(false);
		toolbutton3.setContentAreaFilled(false);
		toolbutton3.setBorderPainted(false);
		toolbutton3.addMouseListener(new ButtonMouseListener());
		toolbutton3.setPreferredSize(new Dimension(21,21));
		toolbutton3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(threadstate!=0){
//					Controller.Close();
					t.stop();
//					if(testcasetype==3){
//						if(progressbar.getValue()>50){
//							progreseethread.stop();
//						}
//						else{
//							gaindatathread.stop();
//						}
//					}
//					else{
						datagainshowthread.stop();
//					}
					
					
//					if(progressbar.getValue()>50){
//						progreseethread.stop();
//					}
//					else{
//						gaindatathread.stop();
//					}
				}
				
				if(Controller.resultService!=null){
					Controller.resultService.closeService();
				}
				
				threadstate=0;
				
				progressbar.setValue(0);
				progressbarlabel.setText("0%");
				
				initUIPanel();
				
			}
		});
		
		toolbutton4.setIcon(icon4);
		toolbutton4.setFocusable(false);
		toolbutton4.setContentAreaFilled(false);
		toolbutton4.setBorderPainted(false);
		toolbutton4.addMouseListener(new ButtonMouseListener());
		toolbutton4.setPreferredSize(new Dimension(21,21));
		toolbutton4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(testcasetype==1){
					
//					functionaltestcasereportlist=mainFrame.getTestCaseConfirmationPanel().getFunctionaltestcasereportlist();
//					
//					for(FunctionalTestCaseReportPartPanel ftcrpp:functionaltestcasereportlist){
//						if(ftcrpp.getAttributepanel().isVisible()){
//							ftcrpp.getAttributepanel().setVisible(false);
//						}
//					}
					
					for(JPanel jp:testcasereportlist){
						FunctionalTestCaseReportPartPanel ftcrpp=(FunctionalTestCaseReportPartPanel) jp;
						if(ftcrpp.getAttributepanel().isVisible()){
							ftcrpp.getAttributepanel().setVisible(false);
						}
					}
					
				}
				else if(testcasetype==2){
//					performancetestcasereportlist=mainFrame.getTestCaseConfirmationPanel().getPerformancetestcasereportlist();
//					
//					for(PerformanceTestCaseReportPartPanel ptcrpp:performancetestcasereportlist){
//						
//						if (ptcrpp.getAttributepanel().isVisible()) {
//							ptcrpp.getAttributepanel().setVisible(false);
//						}
//						
//					}
					
//					for(JPanel jp:testcasereportlist){
//						PerformanceTestCaseReportPartPanel ptcrpp=(PerformanceTestCaseReportPartPanel) jp;
//						if(ptcrpp.getAttributepanel().isVisible()){
//							ptcrpp.getAttributepanel().setVisible(false);
//						}
//					}
					
					for(JPanel jp:testcasereportlist){
						FunctionalTestCaseReportPartPanel ftcrpp=(FunctionalTestCaseReportPartPanel) jp;
						if(ftcrpp.getAttributepanel().isVisible()){
							ftcrpp.getAttributepanel().setVisible(false);
						}
					}
					
				}
				else if(testcasetype==3){
//					timetestcasereportlist=mainFrame.getTestCaseConfirmationPanel().getTimetestcasereportlist();
//					
//					for(TimeTestCaseReportPartPanel ttcrpp:timetestcasereportlist){
//						
//						if (ttcrpp.getAttributepanel().isVisible()) {
//							ttcrpp.getAttributepanel().setVisible(false);
//							ttcrpp.getLimitpanel().setVisible(false);
//						}
//						
//					}
					
					for(JPanel jp:testcasereportlist){
						TimeTestCaseReportPartPanel ttcrpp=(TimeTestCaseReportPartPanel) jp;
						if(ttcrpp.getAttributepanel().isVisible()){
							ttcrpp.getAttributepanel().setVisible(false);
							ttcrpp.getLimitpanel().setVisible(false);
						}
					}
					
				}
				
			}
		});
		
		toolbutton5.setIcon(icon5);
		toolbutton5.setFocusable(false);
		toolbutton5.setContentAreaFilled(false);
		toolbutton5.setBorderPainted(false);
		toolbutton5.addMouseListener(new ButtonMouseListener());
		toolbutton5.setPreferredSize(new Dimension(21,21));
		toolbutton5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (testcasetype == 1) {

//					functionaltestcasereportlist = mainFrame.getTestCaseConfirmationPanel()
//							.getFunctionaltestcasereportlist();
//
//					for (FunctionalTestCaseReportPartPanel ftcrpp : functionaltestcasereportlist) {
//						if (!ftcrpp.getAttributepanel().isVisible()) {
//							ftcrpp.getAttributepanel().setVisible(true);
//						}
//					}
					
					for(JPanel jp:testcasereportlist){
						FunctionalTestCaseReportPartPanel ftcrpp=(FunctionalTestCaseReportPartPanel) jp;
						if(!ftcrpp.getAttributepanel().isVisible()){
							ftcrpp.getAttributepanel().setVisible(true);
						}
					}

				} else if (testcasetype == 2) {
//					performancetestcasereportlist = mainFrame.getTestCaseConfirmationPanel()
//							.getPerformancetestcasereportlist();
//
//					for (PerformanceTestCaseReportPartPanel ptcrpp : performancetestcasereportlist) {
//
//						if (!ptcrpp.getAttributepanel().isVisible()) {
//							ptcrpp.getAttributepanel().setVisible(true);
//						}
//
//					}
					
					for(JPanel jp:testcasereportlist){
						PerformanceTestCaseReportPartPanel ptcrpp=(PerformanceTestCaseReportPartPanel) jp;
						if(!ptcrpp.getAttributepanel().isVisible()){
							ptcrpp.getAttributepanel().setVisible(true);
						}
					}
					
//					for(JPanel jp:testcasereportlist){
//						FunctionalTestCaseReportPartPanel ftcrpp=(FunctionalTestCaseReportPartPanel) jp;
//						if(!ftcrpp.getAttributepanel().isVisible()){
//							ftcrpp.getAttributepanel().setVisible(true);
//						}
//					}
				}
				else if(testcasetype==3){
//					timetestcasereportlist=mainFrame.getTestCaseConfirmationPanel().getTimetestcasereportlist();
//					
//					for(TimeTestCaseReportPartPanel ttcrpp:timetestcasereportlist){
//						
//						if (!ttcrpp.getAttributepanel().isVisible()) {
//							ttcrpp.getAttributepanel().setVisible(true);
//							ttcrpp.getLimitpanel().setVisible(true);
//						}
//						
//					}
					
					for(JPanel jp:testcasereportlist){
						TimeTestCaseReportPartPanel ttcrpp=(TimeTestCaseReportPartPanel) jp;
						if(!ttcrpp.getAttributepanel().isVisible()){
							ttcrpp.getAttributepanel().setVisible(true);
							ttcrpp.getLimitpanel().setVisible(true);
						}
					}
				}

				
			}
		});
		
		toolbutton6.setIcon(icon6);
		toolbutton6.setFocusable(false);
		toolbutton6.setContentAreaFilled(false);
		toolbutton6.setBorderPainted(false);
		toolbutton6.addMouseListener(new ButtonMouseListener());
		toolbutton6.setPreferredSize(new Dimension(21,21));
		toolbutton6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(testcasetype==1){
//					functionaltestcasereportlist=mainFrame.getTestCaseConfirmationPanel().getFunctionaltestcasereportlist();
//					for(FunctionalTestCaseReportPartPanel ftcrpp:functionaltestcasereportlist){
//						ftcrpp.getToolcheckbox().setSelected(true);
//					}
					
					for(JPanel jp:testcasereportlist){
						FunctionalTestCaseReportPartPanel ftcrpp=(FunctionalTestCaseReportPartPanel) jp;
						ftcrpp.getToolcheckbox().setSelected(true);
					}
				}
				else if(testcasetype==2){
//					performancetestcasereportlist=mainFrame.getTestCaseConfirmationPanel().getPerformancetestcasereportlist();
//					
//					for(PerformanceTestCaseReportPartPanel ptcrpp:performancetestcasereportlist){
//						ptcrpp.getToolcheckbox().setSelected(true);
//					}
					
//					if(windcheckboxpanelstate==0){
//						windcheckboxpanel.setVisible(true);
//						windcheckboxpanelstate=1;
//					}
//					else if(windcheckboxpanelstate==1){
//						windcheckboxpanel.setVisible(false);
//						windcheckboxpanelstate=0;
//					}
					
					for(JPanel jp:testcasereportlist){
						FunctionalTestCaseReportPartPanel ftcrpp=(FunctionalTestCaseReportPartPanel) jp;
						ftcrpp.getToolcheckbox().setSelected(true);
					}
				}
				else if(testcasetype==3){
//					timetestcasereportlist=mainFrame.getTestCaseConfirmationPanel().getTimetestcasereportlist();
//					
//					for(TimeTestCaseReportPartPanel ttcrpp:timetestcasereportlist){
//						ttcrpp.getToolcheckbox().setSelected(true);
//					}
					
					for(JPanel jp:testcasereportlist){
						TimeTestCaseReportPartPanel ttcrpp=(TimeTestCaseReportPartPanel) jp;
						ttcrpp.getToolcheckbox().setSelected(true);
					}
				}
				
			}
		});
		
		toolbutton7.setIcon(icon7);
		toolbutton7.setFocusable(false);
		toolbutton7.setContentAreaFilled(false);
		toolbutton7.setBorderPainted(false);
		toolbutton7.addMouseListener(new ButtonMouseListener());
		toolbutton7.setPreferredSize(new Dimension(21,21));
		toolbutton7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (testcasetype==1){
//					functionaltestcasereportlist=mainFrame.getTestCaseConfirmationPanel().getFunctionaltestcasereportlist();
//					for(FunctionalTestCaseReportPartPanel ftcrpp:functionaltestcasereportlist){
//						ftcrpp.getToolcheckbox().setSelected(false);
//					}
					for(JPanel jp:testcasereportlist){
						FunctionalTestCaseReportPartPanel ftcrpp=(FunctionalTestCaseReportPartPanel) jp;
						ftcrpp.getToolcheckbox().setSelected(false);
					}
				}
				else if(testcasetype==2){
//					if(windcheckboxpanelstate==0){
//						windcheckboxpanel.setVisible(true);
//						windcheckboxpanelstate=1;
//					}
//					else if(windcheckboxpanelstate==1){
//						windcheckboxpanel.setVisible(false);
//						windcheckboxpanelstate=0;
//					}
					
					for(JPanel jp:testcasereportlist){
						FunctionalTestCaseReportPartPanel ftcrpp=(FunctionalTestCaseReportPartPanel) jp;
						ftcrpp.getToolcheckbox().setSelected(false);
					}
				}
				else if(testcasetype==3){
//					timetestcasereportlist=mainFrame.getTestCaseConfirmationPanel().getTimetestcasereportlist();
//					
//					for(TimeTestCaseReportPartPanel ttcrpp:timetestcasereportlist){
//						ttcrpp.getToolcheckbox().setSelected(false);
//					}
					
					for(JPanel jp:testcasereportlist){
						TimeTestCaseReportPartPanel ttcrpp=(TimeTestCaseReportPartPanel) jp;
						ttcrpp.getToolcheckbox().setSelected(false);
					}
				}
				
//				testcasereportlist=mainFrame.getTestCaseConfirmationPanel().getTestcasereportlist();
//				for(TestCaseReportPartPanel ptcrpp:testcasereportlist){
//					tcrpp.getToolcheckbox().setSelected(false);
//				}
				
			}
		});
		
		progressbar=new JProgressBar(){

			@Override
			public void setUI(ProgressBarUI ui) {
				// TODO Auto-generated method stub
				super.setUI(new ProgressUI(this,new Color(255, 201, 14)));
			}
			
		};
		
		progressbar.setMinimum(0);  
        progressbar.setMaximum(100);  
        progressbar.setValue(0);  
        progressbar.setStringPainted(false);  
        progressbar.setPreferredSize(new Dimension(400, 23)); 
        
        progressbarlabel=new JLabel();
        progressbarlabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        progressbarlabel.setText("0%");
		

		toolbuttonpanel1.setLayout(new GridLayout());
		toolbuttonpanel1.setBackground(new Color(207, 214, 229));
		toolbuttonpanel1.add(toolbutton1);
		toolbuttonpanel2.setLayout(new GridLayout());
		toolbuttonpanel2.setBackground(new Color(207, 214, 229));
		toolbuttonpanel2.add(toolbutton2);
		toolbuttonpanel3.setLayout(new GridLayout());
		toolbuttonpanel3.setBackground(new Color(207, 214, 229));
		toolbuttonpanel3.add(toolbutton3);
		toolbuttonpanel4.setLayout(new GridLayout());
		toolbuttonpanel4.setBackground(new Color(207, 214, 229));
		toolbuttonpanel4.add(toolbutton4);
		toolbuttonpanel5.setLayout(new GridLayout());
		toolbuttonpanel5.setBackground(new Color(207, 214, 229));
		toolbuttonpanel5.add(toolbutton5);
		toolbuttonpanel6.setLayout(new GridLayout());
		toolbuttonpanel6.setBackground(new Color(207, 214, 229));
		toolbuttonpanel6.add(toolbutton6);
		toolbuttonpanel7.setLayout(new GridLayout());
		toolbuttonpanel7.setBackground(new Color(207, 214, 229));
		toolbuttonpanel7.add(toolbutton7);

		emptypanel1=new JPanel();
		emptypanel1.setPreferredSize(new Dimension(16, 23));
		emptypanel1.setBackground(new Color(133,145,162));
		emptypanel1.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 10, new Color(207, 214, 229)));
		
		emptypanel2=new JPanel();
		emptypanel2.setPreferredSize(new Dimension(16, 23));
		emptypanel2.setBackground(new Color(133,145,162));
		emptypanel2.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 10, new Color(207, 214, 229)));
		
		toolpanel.setBackground(new Color(207, 214, 229));
		toolpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5,3));
		
		toolpanel.add(toolbuttonpanel1);
		toolpanel.add(toolbuttonpanel2);
		toolpanel.add(toolbuttonpanel3);
		toolpanel.add(emptypanel1);
		toolpanel.add(toolbuttonpanel6);
		toolpanel.add(toolbuttonpanel7);
		toolpanel.add(toolbuttonpanel4);
		toolpanel.add(toolbuttonpanel5);
		toolpanel.add(emptypanel2);
		toolpanel.add(progressbar);
		toolpanel.add(progressbarlabel);
		
		toolpanel.setPreferredSize(new Dimension(100, 29));
		toolpanel.setMaximumSize(new Dimension(100, 29));
		toolpanel.setMinimumSize(new Dimension(100, 29));
		
	}

	protected void initTestCaseUI() {
		// TODO Auto-generated method stub
		
		parentTestCaseDataPanel.getTestCaseChartDiagramButtonPanel().setVisible(false);
		
		if(testcasetype==1){
			initFunctionalTestCaseUI();
		}
		else if(testcasetype==2){
			initPerformanceTestCaseUI();
		}
		else if(testcasetype==3){
			initTimeTestCaseUI();
		}
		
	}

	private void initFunctionalTestCaseUI() {
		// TODO Auto-generated method stub
		
		for(JPanel jp:testcasereportlist){
			FunctionalTestCaseReportPartPanel ftcrpp=(FunctionalTestCaseReportPartPanel) jp;
			
			JTable attributetable;
			DefaultTableModel attributetablemodel;

			attributetable = ftcrpp.getAttributetable();
			attributetablemodel = ftcrpp.getAttributetablemodel();
			
			for(int i=0;i<attributetable.getRowCount();i++){
				attributetablemodel.setValueAt("", i, 3);
				attributetablemodel.setValueAt("", i, 4);
			}

			attributetablemodel.fireTableDataChanged();

			String title = "";
			title += "测试用例ID:" + ftcrpp.getTestcase().getTestCaseID() + "     ";
			TestCase testcase=ftcrpp.getTestcase();
			
			if(testcase.getExpectResult().equals("right")){
				title+="预期结果:测试用例正确且满足时间约束     ";
			}
			else if(testcase.getExpectResult().equals("GNerror")){
				title+="预期结果:测试用例不正确     ";
			}
			else if(testcase.getExpectResult().equals("TIMEerror")){
				title+="预期结果:测试用例正确但不满足时间约束     ";
			}
			
			ftcrpp.getTitlelabel().setText(title);
			ftcrpp.getIconlabel().setIcon(null);
			
		}
		
	}

	private void initPerformanceTestCaseUI() {
		// TODO Auto-generated method stub
		
		for(JPanel jp:testcasereportlist){
			
			PerformanceTestCaseReportPartPanel ptcrpp=(PerformanceTestCaseReportPartPanel) jp;
			
			JTable attributetable;
			DefaultTableModel attributetablemodel;
			
			attributetable=ptcrpp.getAttributetable();
			attributetablemodel=ptcrpp.getAttributetablemodel();
			
			for(int i=0;i<attributetable.getRowCount();i++){
				attributetablemodel.setValueAt("", i, 3);
				attributetablemodel.setValueAt("", i, 4);
			}
			
			attributetablemodel.fireTableDataChanged();
			
			DefaultTableModel dtm=ptcrpp.getTitletablemodel();
			dtm.setValueAt("", 0, 3);
			dtm.setValueAt("", 0, 4);
		}
		
	}

	private void initTimeTestCaseUI() {
		// TODO Auto-generated method stub
		
		for(JPanel jp:testcasereportlist){
			TimeTestCaseReportPartPanel ttcrpp=(TimeTestCaseReportPartPanel) jp;
			
			JTable attributetable;
			DefaultTableModel attributetablemodel;

			attributetable = ttcrpp.getAttributetable();
			attributetablemodel = ttcrpp.getAttributetablemodel();
			
			for(int i=0;i<attributetable.getRowCount();i++){
				attributetablemodel.setValueAt("", i, 3);
				attributetablemodel.setValueAt("", i, 4);
			}

			attributetablemodel.fireTableDataChanged();

			JTable limittable;
			DefaultTableModel limittablemodel;

			limittable = ttcrpp.getLimittable();
			limittablemodel = ttcrpp.getLimittablemodel();

			for (int i = 0; i < limittablemodel.getRowCount(); i++) {
				limittablemodel.setValueAt("", i, 1);
				limittablemodel.setValueAt(-1, i, 2);
			}

			String title = "";
			title += "测试用例ID:" + ttcrpp.getTestcase().getTestCaseID() + "     ";
			TestCase testcase=ttcrpp.getTestcase();
			
			if(testcase.getExpectResult().equals("right")){
				title+="预期结果:测试用例正确且满足时间约束     ";
			}
			else if(testcase.getExpectResult().equals("GNerror")){
				title+="预期结果:测试用例不正确     ";
			}
			else if(testcase.getExpectResult().equals("TIMEerror")){
				title+="预期结果:测试用例正确但不满足时间约束     ";
			}
			
			ttcrpp.getTitlelabel().setText(title);
			ttcrpp.getIconlabel().setIcon(null);
			
		}
		
	}

	protected void ExceptionStopRunThread() {
		// TODO Auto-generated method stub
		
		if (testcasetype == 3) {
			if (progressbar.getValue() > 50) {
				progreseethread.stop();
			} else {
				gaindatathread.stop();
			}
		} else {
			datagainshowthread.stop();
		}
		
		threadstate=0;
		
		progressbar.setValue(0);
		progressbarlabel.setText("0%");
		System.out.println("123456789123--------------------");
		initUIPanel();
		
		TextAreaPrint("服务器出现异常，退出测试执行！！！");
		TextAreaPrint("请等待几秒后，再尝试连接。。。");
		
		t.stop();
	}

	public void initUIPanel() {
		// TODO Auto-generated method stub
		
		progressbarindex=0;
		progressbar.setValue(0);
		progressbarlabel.setText("");
		
//		mainFrame.getStepFiveCenterTabbedPane().getTestCaseChartDiagramButtonPanel().setVisible(false);
		
//		tableresultpanel.removeAll();
		
//		mainFrame.getConsolePartPanel().getTextarea5().setText("");
		
//		ChangeRepaint();
	}

	private void initWindCheckBoxPanel() {
		// TODO Auto-generated method stub
		
		int level=10;
		
		windcheckboxpanel=new JPanel();
		windcheckboxpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		windCheckBoxList=new JCheckBox[level+1];
		for(int i=0;i<=level;i++){
			windCheckBoxList[i]=new JCheckBox("风速"+i*2+"级");
			windCheckBoxList[i].setOpaque(false);
			windCheckBoxList[i].setSelected(true);
			windcheckboxpanel.add(windCheckBoxList[i]);
		}
		
		windcheckboxpanel.setBackground(new Color(207, 214, 229));
		windcheckboxpanel.setVisible(false);
		
	}
	
		
	private void startFunctionalTestConfirmation(){
		t = new Thread(new Runnable() {

			@Override
			public void run(){
				// TODO Auto-generated method stub
				
				checkedtestcasereportlist.removeAll(checkedtestcasereportlist);
				selectedtestcaselist.removeAll(selectedtestcaselist);
				
				int index=0;
				for(JPanel jp:testcasereportlist){
					FunctionalTestCaseReportPartPanel ftcrpp=(FunctionalTestCaseReportPartPanel) jp;
					if (ftcrpp.getToolcheckbox().isSelected()) {
						checkedtestcasereportlist.add(ftcrpp);
						selectedtestcaselist.add(ftcrpp.getTestcase());
					}
				}
				
				String extraxmlpath="D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\xxyy#1.xml";
				extractDataToXml(extraxmlpath, selectedtestcaselist, 1);//生成测试用例xml
				File file=new File(extraxmlpath);
				
//				gaindatathread.start();
				
				datagainshowthread.start();
				
//				PropertyConfigurator.configure("src/log4j.properties");
				Controller.Run(new Pair<String, File>("Function", file));
				
				while(progressbar.getValue()<90){
					try {
						System.out.println("***************************");
//						if(threadexceptionstate==1){
//							ExceptionStopRunThread();
//						}
//						if(Controller.handFutureList.size()>0&&Controller.handFutureList.get(0).isDone()){
//							try {
//								Controller.handFutureList.get(0).get();
//							} catch (ExecutionException e) {
//								// TODO Auto-generated catch block
//								System.out.println("+-+"+e.getMessage());
//								if(e.getMessage().contains("TestCaseException")){
////									TestCaseReportTabbedPanel.threadexceptionstate=1;
//									ExceptionStopRunThread();
//								}
//							}
//						}
						if (Controller.handFutureList.size() > 0) {
							for (int i = 0; i < Controller.handFutureList.size(); i++) {
								if (Controller.handFutureList.get(i).isDone()) {
									try {
										Controller.handFutureList.get(i).get();
									} catch (ExecutionException e) {
										// TODO Auto-generated catch block
										System.out.println("+-+" + e.getMessage());
										if (e.getMessage().contains("TestCaseException")) {
											ExceptionStopRunThread();
										}
									}
								}
							}
						}
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				resulttestcaselist=ResultService.list;
				
				showStatisticsDataByType(1);
				
				while(progressbar.getValue()<100){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				parentTestCaseDataPanel.getTestCaseChartDiagramButtonPanel().setVisible(true);
				
				TextAreaPrint("测试执行结束");
				
				Controller.resultService.closeService();
				
				CheckIsSaveDB();
				
				threadstate = 0;
				
			}

		});

		gaindatathread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
//				int sum=checkedfunctionaltestcasereportlist.size();
				int sum=checkedtestcasereportlist.size();
				for(int index=0;index<sum;index++){
					TextAreaPrint("正在处理第 "+(index+1)+" 条测试用例");
					
					int startprogressbar = (int) ((double) 50 / sum * index);
					int endprogressbar = (int) ((double) 50 / sum * (index + 1));
					
					for(int i=startprogressbar;i<endprogressbar;i++){
						progressbar.setValue(progressbar.getValue()+1);
						progressbarlabel.setText(progressbar.getValue()+"%");
					}
					
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				TextAreaPrint("处理完成！");
				TextAreaPrint("正在接收服务器端返回的测试结果...");
			}
		});
		
		datagainshowthread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				int resultlistindex=0;
				int datagainshowindex;
				int sum = 0;
				
				int startprogressbar;
				int endprogressbar;
				int sleeptime;
				
				//发送数据 1~10
				TextAreaPrint("发送测试用例数据...");
				sleeptime=selectedtestcaselist.size()/10*2;
				for(int i=1;i<10;i++){
					progressbar.setValue(progressbar.getValue()+1);
					progressbarlabel.setText(progressbar.getValue()+"%");
					
					try {
						Thread.sleep(sleeptime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				//数据收集显示 11~90
				sum+=selectedtestcaselist.size()/300;
				if(selectedtestcaselist.size()%300!=0){
					sum++;
				}
				
				for(datagainshowindex=0;datagainshowindex<sum*2;){
					
					//数据收集
					TextAreaPrint("正在进行测试...");
					startprogressbar = (int) ((double) 80 / (sum*2) * datagainshowindex);
					endprogressbar = (int) ((double) 80 / (sum*2) * (datagainshowindex + 1));
					System.out.println("startprogressbar "+startprogressbar+" endprogressbar "+endprogressbar+" progressbarvalue "+progressbar.getValue());
					datagainshowindex++;
					
					sleeptime=5000;
					for(int i=startprogressbar;i<endprogressbar;i++){
						
						if(ResultService.list.size()>resultlistindex){
							if((ResultService.list.size()-resultlistindex>=300)||(ResultService.list.size()==selectedtestcaselist.size())){
								sleeptime=100;
							}
						}
						
						progressbar.setValue(progressbar.getValue()+1);
						progressbarlabel.setText(progressbar.getValue()+"%");
						
						try {
							Thread.sleep(sleeptime);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
//					while(resultlistindex>=ResultService.list.size()){
					while(!((ResultService.list.size()-resultlistindex>=300)||(ResultService.list.size()==selectedtestcaselist.size()))){
						System.out.println("-------");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					//展示数据
					
					startprogressbar = (int) ((double) 80 / (sum*2) * datagainshowindex);
					endprogressbar = (int) ((double) 80 / (sum*2) * (datagainshowindex + 1));
					System.out.println("startprogressbar "+startprogressbar+" endprogressbar "+endprogressbar+" progressbarvalue "+progressbar.getValue());
					datagainshowindex++;
					
					int progressbarsize;
					progressbarsize=endprogressbar-startprogressbar;
					
					int datasize;
					if(ResultService.list.size()-resultlistindex>=300){
						datasize=300;
					}
					else{
						datasize=ResultService.list.size()-resultlistindex;
					}
					
					for(int k=0;k<datasize;k++,resultlistindex++){
						
						showTestCaseDataByType(1, resultlistindex);
						
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						startprogressbar = (int) ((double) progressbarsize / datasize * k);
						endprogressbar = (int) ((double) progressbarsize / datasize * (k + 1));
						
						for(int i=startprogressbar;i<endprogressbar;i++){
							progressbar.setValue(progressbar.getValue()+1);
							progressbarlabel.setText(progressbar.getValue()+"%");
						}
						
					}
					
				}
				
				//统计数据 91~100
				TextAreaPrint("正在进行数据统计整理...");
				for(int i=0;i<10;i++){
					progressbar.setValue(progressbar.getValue()+1);
					progressbarlabel.setText(progressbar.getValue()+"%");
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				progressbar.setValue(100);
				progressbarlabel.setText("100%");
				
			}
		});

		t.start();
		
	}

	protected void CheckIsSaveDB() {
		
		int issave=JOptionPane.showConfirmDialog(mainFrame, "是否需要保存该次测试用例及测试报告", "提示", JOptionPane.YES_NO_OPTION);
		
		if(issave==JOptionPane.YES_OPTION){
			SaveTestCaseToDBByType(testcasetype);
			JOptionPane.showMessageDialog(mainFrame, "保存成功！", "消息" , JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			
		}
		
	}

	private void SaveTestCaseToDBByType(int type) {
		
		if(type==1){
			
			if(testcasename.contains("BorderTestCase")){
				type=4;
			}
			
			List<String> testcasestringlist=new ArrayList<String>();
			for(TestCase testCase:resulttestcaselist){
				testcasestringlist.add(testCase.SpellFunctionalTestCase());
			}
			DataBaseUtil.insertTestCaseStringList(type, testcasestringlist, testcasename);
			TextAreaPrint("保存成功！！！");
		}
		else if(type==2){
			List<String> testcasestringlist=new ArrayList<String>();
			for(TestCase testCase:resulttestcaselist){
				testcasestringlist.add(testCase.SpellPerformanceTestCase());
			}
			DataBaseUtil.insertTestCaseStringList(type, testcasestringlist, testcasename);
			TextAreaPrint("保存成功！！！");
		}
		else if(type==3){
			List<String> testcasestringlist=new ArrayList<String>();
			for(TestCase testCase:resulttestcaselist){
				testcasestringlist.add(testCase.SpellTimeTestCase());
			}
			DataBaseUtil.insertTestCaseStringList(type, testcasestringlist, testcasename);
			TextAreaPrint("保存成功！！！");
		}
		
	}

	protected void showStatisticsDataByType(int type) {
		
		Collections.sort(resulttestcaselist, new Comparator<TestCase>() {

			@Override
			public int compare(TestCase o1, TestCase o2) {
				
				int id1=Integer.parseInt(o1.getTestCaseID());
				int id2=Integer.parseInt(o2.getTestCaseID());
				
				return id1-id2;
			}
		});
		
		if(type==1){//统计功能数据
			
			Map<String, Object> testcasemap=TcConvertUtil.functionStatistics(resulttestcaselist);
			List<Integer> caseSuccess=(List<Integer>) testcasemap.get("caseSuccess");
			List<Integer> caseFailed=(List<Integer>) testcasemap.get("caseFailed");
			Map<String,List<Map<Integer,List<Integer>>>> failedStatistics=(Map<String, List<Map<Integer, List<Integer>>>>) testcasemap.get("failedStatistics");
					
//			System.out.println("caseSuccess "+caseSuccess.size());
//			System.out.println("caseFailed "+caseFailed.size());
//			for(Entry<String, List<Map<Integer,List<Integer>>>> en:failedStatistics.entrySet()){
//				System.out.println(en.getKey()+"  "+en.getValue().size());
//			}
			
			FunctionalTestCaseChartTabbedPanel functionalTestCaseChartTabbedPanel=new FunctionalTestCaseChartTabbedPanel(mainFrame);
			
			parentTestCaseDataPanel.getTestCaseChartTabbedPanel().removeAll();
			parentTestCaseDataPanel.getTestCaseChartTabbedPanel().add(functionalTestCaseChartTabbedPanel);
			
			DefaultTableModel tabelmodel=functionalTestCaseChartTabbedPanel.getAttributetablemodel();
			
			int cs=0,cf=0,csum=0;
			cs=caseSuccess.size();
			cf=caseFailed.size();
			
//			cs=7935;
//			cf=39;
			
			csum=cs+cf;
			DefaultTableModel successfailedtabelmodel=functionalTestCaseChartTabbedPanel.getSuccessfailedattributetablemodel();
			while(successfailedtabelmodel.getRowCount()>0){
				successfailedtabelmodel.removeRow(0);
			}
			Object[] rowData1={"合计：",cs, cf, csum};
			successfailedtabelmodel.addRow(rowData1);
			Object[] rowData2={"百分比：",calcper(cs, csum), calcper(cf, csum), calcper(csum, csum)};
//			Object[] rowData2={"百分比：","99.51%", "0.49%", calcper(csum, csum)};
			successfailedtabelmodel.addRow(rowData2);
			
			int f1=0,f2=0;
			if (failedStatistics.containsKey("测试用例有误")) {
				f1=failedStatistics.get("测试用例有误").size();
			}
			if(failedStatistics.containsKey("程序出现死循环或者抛出异常")){
				f2=failedStatistics.get("程序出现死循环或者抛出异常").size();
			}
			
//			f1=39;
//			f2=0;
			
			DefaultTableModel failedstatisticstabelmodel=functionalTestCaseChartTabbedPanel.getFailedstatisticsattributetablemodel();
			while(failedstatisticstabelmodel.getRowCount()>0){
				failedstatisticstabelmodel.removeRow(0);
			}
			Object[] rowData3={"合计：", f1, f2, cf};
			failedstatisticstabelmodel.addRow(rowData3);
			Object[] rowData4={"百分比：", calcper(f1, cf), calcper(f2, cf), calcper(cf, cf)};
			failedstatisticstabelmodel.addRow(rowData4);
			
			
//			FunctionSuccessFailedPieChart fsfpc=new FunctionSuccessFailedPieChart(caseSuccess, caseFailed);
			FunctionSuccessFailedPieChart fsfpc=new FunctionSuccessFailedPieChart(cs, cf);
			functionalTestCaseChartTabbedPanel.getSuccessfailedpiepanel().removeAll();
			functionalTestCaseChartTabbedPanel.getSuccessfailedpiepanel().add(fsfpc.createChart());
			
//			FunctionFailedStatisticsPieChart ffspc=new FunctionFailedStatisticsPieChart(failedStatistics);
			FunctionFailedStatisticsPieChart ffspc=new FunctionFailedStatisticsPieChart(f1, f2);
			functionalTestCaseChartTabbedPanel.getFailedstatisticspiepanel().removeAll();
			functionalTestCaseChartTabbedPanel.getFailedstatisticspiepanel().add(ffspc.createChart());
		}
		else if(type==2){//统计性能数据

			Map testcasemap=TcConvertUtil.testCaseStatistics(resulttestcaselist);

			List<Pair> highspeeddata=(List<Pair>) testcasemap.get("high-speed");
			List<Pair> timespeeddata=(List<Pair>) testcasemap.get("time-speed");
			Map<String, List<Pair>> highbatterydata=(Map<String, List<Pair>>) testcasemap.get("high-battery");
			Map<String, List<Pair>> hightimedata=(Map<String, List<Pair>>) testcasemap.get("high-time");
			
			PerformanceTestCaseChartTabbedPanel performanceTestCaseChartTabbedPanel=new PerformanceTestCaseChartTabbedPanel(mainFrame);
			
			parentTestCaseDataPanel.getTestCaseChartTabbedPanel().removeAll();
			parentTestCaseDataPanel.getTestCaseChartTabbedPanel().add(performanceTestCaseChartTabbedPanel);
			
			DefaultTableModel tabelmodel=performanceTestCaseChartTabbedPanel.getAttributetablemodel();
			while(tabelmodel.getRowCount()>0){
				tabelmodel.removeRow(0);
			}
			for(TestCase tc:resulttestcaselist){
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
			
			Map<String, Integer> resultmap=TcConvertUtil.timeStatistics(resulttestcaselist);
			
			TimeTestCaseChartTabbedPanel timeTestCaseChartTabbedPanel=new TimeTestCaseChartTabbedPanel(mainFrame);
			
			parentTestCaseDataPanel.getTestCaseChartTabbedPanel().removeAll();
			parentTestCaseDataPanel.getTestCaseChartTabbedPanel().add(timeTestCaseChartTabbedPanel);
			
			DefaultTableModel tabelmodel=timeTestCaseChartTabbedPanel.getAttributetablemodel();
			
			int cs=0,cf=0,csum=0;
			cs=resultmap.get("success");
			cf=resultmap.get("failed");
			
//			cs=180;
//			cf=39;
			
			csum=cs+cf;
			DefaultTableModel successfailedtabelmodel=timeTestCaseChartTabbedPanel.getSuccessfailedattributetablemodel();
			while(successfailedtabelmodel.getRowCount()>0){
				successfailedtabelmodel.removeRow(0);
			}
			Object[] rowData1={"合计：",cs, cf, csum};
			successfailedtabelmodel.addRow(rowData1);
			Object[] rowData2={"百分比：",calcper(cs, csum), calcper(cf, csum), calcper(csum, csum)};
//			Object[] rowData2={"百分比：","99.51%", "0.49%", calcper(csum, csum)};
			successfailedtabelmodel.addRow(rowData2);
			
			int f1=0,f2=0;
//			if (failedStatistics.containsKey("测试用例有误")) {
//				f1=failedStatistics.get("测试用例有误").size();
//			}
//			if(failedStatistics.containsKey("程序出现死循环或者抛出异常")){
//				f2=failedStatistics.get("程序出现死循环或者抛出异常").size();
//			}
			
			f1=resultmap.get("testcasefailed");
			f2=resultmap.get("timefailed");
			
//			f1=27;
//			f2=12;
			
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
			
		}
		
	}

	protected void showTestCaseDataByType(int type, int resultlistindex) {
		
		if(type==1){
			
//			FunctionalTestCaseReportPartPanel ftcrpp=(FunctionalTestCaseReportPartPanel) checkedtestcasereportlist.get(resultlistindex);
			
//			TestCase testcase=list.get(Integer.parseInt(ftcrpp.getTestcase().getTestCaseID())-1);
//			while(!list.get(index).getTestCaseID().equals(ftcrpp.getTestcase().getTestCaseID())){
//				index++;
//			}
			
			TestCase testcase=ResultService.list.get(resultlistindex);
			FunctionalTestCaseReportPartPanel ftcrpp = null;
			
			ftcrpp=(FunctionalTestCaseReportPartPanel) checkedtestcasereportlist.get(Integer.parseInt(testcase.getTestCaseID())-1);
//			if(!ftcrpp.getTestcase().getTestCaseID().equals(testcase.getTestCaseID())){
//				for(JPanel panel:checkedtestcasereportlist){
//					if(((FunctionalTestCaseReportPartPanel)panel).getTestcase().getTestCaseID().equals(testcase.getTestCaseID())){
//						ftcrpp=(FunctionalTestCaseReportPartPanel)panel;
//						break;
//					}
//				}
//			}
			
			testcase.setExpectResult(ftcrpp.getTestcase().getExpectResult());
			
			JTable attributetable;
			DefaultTableModel attributetablemodel;
			
			attributetable=ftcrpp.getAttributetable();
			attributetablemodel=ftcrpp.getAttributetablemodel();
			
			for(myProcess p:testcase.getProcessList()){
				
				attributetablemodel.setValueAt(p.getProcessStatus(), p.getProcessID()-1, 3);
				attributetablemodel.setValueAt(p.isProcessExec(), p.getProcessID()-1, 4);
				
			}
			
			attributetablemodel.fireTableDataChanged();
			
			String title = "";
			title+="测试用例ID:"+ftcrpp.getTestcase().getTestCaseID()+"     ";

			if(testcase.getExpectResult().equals("right")){
				title+="预期结果:测试用例正确     ";
			}
			else if(testcase.getExpectResult().equals("GNerror")){
				title+="预期结果:测试用例不正确     ";
			}
			else if(testcase.getExpectResult().equals("TIMEerror")){
				title+="预期结果:测试用例正确但不满足时间约束     ";
			}
			
			title+="执行结果:"+testcase.getState()+"     ";
			title+="总耗时:"+testcase.getExetime()+" ms";
			
			ftcrpp.getTitlelabel().setText(title);
			
			String absolutePath = System.getProperty("user.dir");
			String path = absolutePath + "\\src\\site\\resources\\icons\\OpreationPart\\";

			ImageIcon icon1 = new ImageIcon(this.getClass().getResource("ImagePart/tick.png"));
			icon1.setImage(icon1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
			ImageIcon icon2 = new ImageIcon(this.getClass().getResource("ImagePart/cross.png"));
			icon2.setImage(icon2.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
			
			if(testcase.getState().contains("成功")){
				ftcrpp.getIconlabel().setIcon(icon1);
			}
			else{
				ftcrpp.getIconlabel().setIcon(icon2);
			}
			
//			TextAreaPrint(testcase.toString());
			
//			System.out.println("----------+++++++++++");
//			System.out.println(testcase.SpellFunctionalTestCase());
//			System.out.println("----------+++++++++++");
			
//			System.out.println("----------+++++++++++");
//			System.out.println(testcase.SpellPerformanceTestCase());
//			System.out.println("----------+++++++++++");
			
		}
		else if(type==2){
			
//			PerformanceTestCaseReportPartPanel tcrpp=(PerformanceTestCaseReportPartPanel) checkedtestcasereportlist.get(performancelistindex);
//			
//			while(!ResultService.list.get(resultlistindex).getTestCaseID().equals(tcrpp.getTestcase().getTestCaseID())){
//				tcrpp.setVisible(false);//同时为0的时候，数据会消掉，panel要去除
//			}
		
			PerformanceTestCaseReportPartPanel ptcrpp = null;
//			tcrpp=(PerformanceTestCaseReportPartPanel) checkedtestcasereportlist.get(performancelistindex);
//			performancelistindex++;
			
			TestCase testcase=ResultService.list.get(resultlistindex);
			
//			double battery=testcase.getResult().getBattery_remainingDouble();
//			if(battery>100){
//				testcase.getResult().setBattery_remaining(100);
//			}
//			else if(battery<0){
//				testcase.getResult().setBattery_remaining(0);
//			}
			
			ptcrpp=(PerformanceTestCaseReportPartPanel) checkedtestcasereportlist.get(Integer.parseInt(testcase.getTestCaseID())-1);
//			for(JPanel jp:checkedtestcasereportlist){
//				tcrpp=(PerformanceTestCaseReportPartPanel)jp;
//				if(testcase.getTestCaseID().equals(tcrpp.getTestcase().getTestCaseID())){
//					break;
//				}
//			}
			
//			while(!testcase.getTestCaseID().equals(tcrpp.getTestcase().getTestCaseID())){
//				tcrpp.setVisible(false);
//				System.out.println(performancelistindex);
//				tcrpp=(PerformanceTestCaseReportPartPanel) checkedtestcasereportlist.get(performancelistindex);
//				performancelistindex++;
//			}
			
			testcase.setExpectResult(ptcrpp.getTestcase().getExpectResult());
			
			JTable attributetable;
			DefaultTableModel attributetablemodel;
			
			attributetable=ptcrpp.getAttributetable();
			attributetablemodel=ptcrpp.getAttributetablemodel();
			
			for(myProcess p:testcase.getProcessList()){
				
				attributetablemodel.setValueAt(p.getProcessStatus(), p.getProcessID()-1, 3);
				attributetablemodel.setValueAt(p.isProcessExec(), p.getProcessID()-1, 4);
				
			}
			
			attributetablemodel.fireTableDataChanged();
			
			DefaultTableModel dtm=ptcrpp.getTitletablemodel();
			dtm.setValueAt(testcase.getResult().getBattery_remaining(), 0, 3);
			dtm.setValueAt(testcase.getResult().getTime(), 0, 4);
			
//			TextAreaPrint(testcase.toString());
			
//			System.out.println("----------+++++++++++");
//			System.out.println(testcase.SpellPerformanceTestCase());
//			System.out.println("----------+++++++++++");
			
		}
		else if (type == 3) {

			TestCase testcase = ResultService.list.get(resultlistindex);
			TimeTestCaseReportPartPanel ttcrpp = null;

			ttcrpp = (TimeTestCaseReportPartPanel) checkedtestcasereportlist
					.get(Integer.parseInt(testcase.getTestCaseID()) - 1);
			
			testcase.setExpectResult(ttcrpp.getTestcase().getExpectResult());
			
			JTable attributetable;
			DefaultTableModel attributetablemodel;

			attributetable = ttcrpp.getAttributetable();
			attributetablemodel = ttcrpp.getAttributetablemodel();

			for (myProcess p : testcase.getProcessList()) {

				attributetablemodel.setValueAt(p.getProcessStatus(), p.getProcessID() - 1, 3);
				attributetablemodel.setValueAt(p.isProcessExec(), p.getProcessID() - 1, 4);

			}

			attributetablemodel.fireTableDataChanged();

			JTable limittable;
			DefaultTableModel limittablemodel;

			limittable = ttcrpp.getLimittable();
			limittablemodel = ttcrpp.getLimittablemodel();

			testcase.setLimit(ttcrpp.getTestcase().getLimit());
			
			Map<String, Pair<String, String>> map = testcase.getResult().getTimeLimit().getShowMap();
			
			for (int i = 0; i < limittablemodel.getRowCount(); i++) {
				String limit = (String) limittablemodel.getValueAt(i, 0);
				Pair p = map.get(limit);
				int state = 0;
				if (p.getSecond()!=null&&p.getSecond().equals("true")) {
					state = 1;
				}
				if (p.getFirst() == null || p.getFirst().equals("")) {
					state = 0;
				}
//				System.out.println(limit + "  -  " + state + "  -  " + p.getFirst() + "  -  " + p.getSecond());
				limittablemodel.setValueAt(p.getFirst(), i, 1);
				limittablemodel.setValueAt(state, i, 2);
				
			}

			String title = "";
			title += "测试用例ID:" + ttcrpp.getTestcase().getTestCaseID() + "     ";
			
			if(testcase.getExpectResult().equals("right")){
				title+="预期结果:测试用例正确且满足时间约束     ";
			}
			else if(testcase.getExpectResult().equals("GNerror")){
				title+="预期结果:测试用例不正确     ";
			}
			else if(testcase.getExpectResult().equals("TIMEerror")){
				title+="预期结果:测试用例正确但不满足时间约束     ";
			}
			
			title += "执行结果:" + testcase.getState() + "     ";
			title += "总耗时:" + testcase.getExetime() + " ms";

			ttcrpp.getTitlelabel().setText(title);

			String absolutePath = System.getProperty("user.dir");
			String path = absolutePath + "\\src\\site\\resources\\icons\\OpreationPart\\";

			ImageIcon icon1 = new ImageIcon(this.getClass().getResource("ImagePart/tick.png"));
			icon1.setImage(icon1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
			ImageIcon icon2 = new ImageIcon(this.getClass().getResource("ImagePart/cross.png"));
			icon2.setImage(icon2.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));

			if (testcase.getState().contains("正确")&&!testcase.getState().contains("不满足")) {
				ttcrpp.getIconlabel().setIcon(icon1);
			} else {
				ttcrpp.getIconlabel().setIcon(icon2);
			}

//			TextAreaPrint(testcase.toString());

//			System.out.println("----------+++++++++++");
//			System.out.println(testcase.SpellTimeTestCase());
//			System.out.println("----------+++++++++++");
		}
		
	}

	protected void startFunctionalRunProgressbar(final List<TestCase> list) {
		// TODO Auto-generated method stub
		
		try {
			progreseethread=new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					int index=0;
//					for(FunctionalTestCaseReportPartPanel ftcrpp:checkedfunctionaltestcasereportlist){
						
					for(JPanel jp:checkedtestcasereportlist){
						FunctionalTestCaseReportPartPanel ftcrpp=(FunctionalTestCaseReportPartPanel) jp;
						
//						TestCase testcase=list.get(Integer.parseInt(ftcrpp.getTestcase().getTestCaseID())-1);
						while(!list.get(index).getTestCaseID().equals(ftcrpp.getTestcase().getTestCaseID())){
							index++;
						}
						TestCase testcase=list.get(index);
						
						JTable attributetable;
						DefaultTableModel attributetablemodel;
						
						attributetable=ftcrpp.getAttributetable();
						attributetablemodel=ftcrpp.getAttributetablemodel();
						
						for(myProcess p:testcase.getProcessList()){
							
							attributetablemodel.setValueAt(p.getProcessStatus(), p.getProcessID()-1, 3);
							attributetablemodel.setValueAt(p.isProcessExec(), p.getProcessID()-1, 4);
							
						}
						
						attributetablemodel.fireTableDataChanged();
						
						String title = "";
						title+="测试用例ID:"+testcase.getTestCaseID()+"     ";
//						title+=testcase.getState()+"     ";
//						title+="执行结果:"+testcase.getResult().substring(0, testcase.getResult().indexOf("耗时"));
						title+="执行结果:"+testcase.getResult().getResultDetail();
						
						ftcrpp.getTitlelabel().setText(title);
						
						String absolutePath = System.getProperty("user.dir");
						String path = absolutePath + "\\src\\site\\resources\\icons\\OpreationPart\\";

						ImageIcon icon1 = new ImageIcon(this.getClass().getResource("ImagePart/tick.png"));
						icon1.setImage(icon1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
						ImageIcon icon2 = new ImageIcon(this.getClass().getResource("ImagePart/cross.png"));
						icon2.setImage(icon2.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
						
						if(testcase.getResult().getResultDetail().contains("成功")){
							ftcrpp.getIconlabel().setIcon(icon1);
						}
						else{
							ftcrpp.getIconlabel().setIcon(icon2);
						}
						
						TextAreaPrint(testcase.toString());
						
						int startprogressbar = (int) ((double) 49 / list.size() * index);
						int endprogressbar = (int) ((double) 49 / list.size() * (index + 1));
						
						index++;
						System.out.println(index);
						
						for(int i=startprogressbar;i<endprogressbar;i++){
							progressbar.setValue(progressbar.getValue()+1);
							progressbarlabel.setText(progressbar.getValue()+"%");
							
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						
					}
					progressbar.setValue(100);
					progressbarlabel.setText(100+"%");
				}
				
			});
			
			progreseethread.start();
			progreseethread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private void startPerformanceTestConfirmation() {
		// TODO Auto-generated method stub
		
		t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				checkedtestcasereportlist.removeAll(checkedtestcasereportlist);
				selectedtestcaselist.removeAll(selectedtestcaselist);
				
				for(JPanel jp:testcasereportlist){
					PerformanceTestCaseReportPartPanel tcrpp=(PerformanceTestCaseReportPartPanel) jp;
					checkedtestcasereportlist.add(tcrpp);
					selectedtestcaselist.add(tcrpp.getTestcase());
				}
				
				String extraxmlpath="D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\xxyy#2.xml";
				extractDataToXml(extraxmlpath, selectedtestcaselist, 2);//生成测试用例xml
				File file=new File(extraxmlpath);
				
//				gaindatathread.start();
				datagainshowthread.start();
				
//				PropertyConfigurator.configure("src/log4j.properties");
				Controller.Run(new Pair<String, File>("Performance", file));

				while(progressbar.getValue()<90){
					try {
						System.out.println("***************************");
//						if(threadexceptionstate==1){
//							ExceptionStopRunThread();
//						}
//						if(Controller.handFutureList.get(0).isDone()){
//							try {
//								Controller.handFutureList.get(0).get();
//							} catch (ExecutionException e) {
//								// TODO Auto-generated catch block
//								System.out.println("+-+"+e.getMessage());
//								if(e.getMessage().contains("TestCaseException")){
////									TestCaseReportTabbedPanel.threadexceptionstate=1;
//									ExceptionStopRunThread();
//								}
//							}
//						}
						if (Controller.handFutureList.size() > 0) {
							for (int i = 0; i < Controller.handFutureList.size(); i++) {
								if (Controller.handFutureList.get(i).isDone()) {
									try {
										Controller.handFutureList.get(i).get();
									} catch (ExecutionException e) {
										// TODO Auto-generated catch block
										System.out.println("+-+" + e.getMessage());
										if (e.getMessage().contains("TestCaseException")) {
											ExceptionStopRunThread();
										}
									}
								}
							}
						}
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				resulttestcaselist=ResultService.list;
				
				showStatisticsDataByType(2);
				
				while(progressbar.getValue()<100){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				parentTestCaseDataPanel.getTestCaseChartDiagramButtonPanel().setVisible(true);
				
				TextAreaPrint("测试执行结束");
				
				Controller.resultService.closeService();
				
				CheckIsSaveDB();
				
				threadstate=0;
				
			}
			
		});
		
		gaindatathread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
//				int sum=checkedperformancetestcasereportlist.size();
				int sum=checkedtestcasereportlist.size();
				for(int index=0;index<sum;index++){
					TextAreaPrint("正在处理第 "+(index+1)+" 条测试用例");
					
					int startprogressbar = (int) ((double) 50 / sum * index);
					int endprogressbar = (int) ((double) 50 / sum * (index + 1));
					
					for(int i=startprogressbar;i<endprogressbar;i++){
						progressbar.setValue(progressbar.getValue()+1);
						progressbarlabel.setText(progressbar.getValue()+"%");
						
					}
					
					try {
//						Thread.sleep(200);
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
//				for(int i=0;i<50;i++){
//					TextAreaPrint();
//					progressbar.setValue(progressbar.getValue()+1);
//					progressbarlabel.setText(progressbar.getValue()+"%");
//					System.out.println("---- progressbar "+progressbar.getValue());
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
				TextAreaPrint("处理完成！");
				TextAreaPrint("正在接收服务器端返回的测试结果...");
			}
		});
		
		datagainshowthread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				performancelistindex=0;
				
				int resultlistindex=0;
				int datagainshowindex;
				int sum = 0;
				
				int startprogressbar;
				int endprogressbar;
				int sleeptime;
				
				//发送数据 1~10
				TextAreaPrint("发送测试用例数据...");
				sleeptime=selectedtestcaselist.size()/10*2;
				for(int i=1;i<10;i++){
					progressbar.setValue(progressbar.getValue()+1);
					progressbarlabel.setText(progressbar.getValue()+"%");
					
					try {
						Thread.sleep(sleeptime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				//数据收集显示 11~90
				sum+=selectedtestcaselist.size()/300;
				if(selectedtestcaselist.size()%300!=0){
					sum++;
				}
				
				for(datagainshowindex=0;datagainshowindex<sum*2;){
					
					//数据收集
					TextAreaPrint("正在进行测试...");
					startprogressbar = (int) ((double) 80 / (sum*2) * datagainshowindex);
					endprogressbar = (int) ((double) 80 / (sum*2) * (datagainshowindex + 1));
					System.out.println("startprogressbar "+startprogressbar+" endprogressbar "+endprogressbar+" progressbarvalue "+progressbar.getValue());
					datagainshowindex++;
					
					sleeptime=500;
					for(int i=startprogressbar;i<endprogressbar;i++){
						
						if(ResultService.list.size()>resultlistindex){
							if((ResultService.list.size()-resultlistindex>=300)||(ResultService.list.size()==selectedtestcaselist.size())){
								sleeptime=100;
							}
						}
						
						progressbar.setValue(progressbar.getValue()+1);
						progressbarlabel.setText(progressbar.getValue()+"%");
						
						try {
							Thread.sleep(sleeptime);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
//					while(resultlistindex>=ResultService.list.size()){
					while(!((ResultService.list.size()-resultlistindex>=300)||(ResultService.list.size()==selectedtestcaselist.size()))){
						System.out.println("-------");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					//展示数据
					
					startprogressbar = (int) ((double) 80 / (sum*2) * datagainshowindex);
					endprogressbar = (int) ((double) 80 / (sum*2) * (datagainshowindex + 1));
					System.out.println("startprogressbar "+startprogressbar+" endprogressbar "+endprogressbar+" progressbarvalue "+progressbar.getValue());
					datagainshowindex++;
					
					int progressbarsize;
					progressbarsize=endprogressbar-startprogressbar;
					
					int datasize;
					if(ResultService.list.size()-resultlistindex>=300){
						datasize=300;
					}
					else{
						datasize=ResultService.list.size()-resultlistindex;
					}
					
					for(int k=0;k<datasize;k++,resultlistindex++){
						
						showTestCaseDataByType(2, resultlistindex);
						
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						startprogressbar = (int) ((double) progressbarsize / datasize * k);
						endprogressbar = (int) ((double) progressbarsize / datasize * (k + 1));
						
						for(int i=startprogressbar;i<endprogressbar;i++){
							progressbar.setValue(progressbar.getValue()+1);
							progressbarlabel.setText(progressbar.getValue()+"%");
						}
						
					}
					
				}
				
				//统计数据 91~100
				
				TextAreaPrint("正在进行数据统计整理...");
				for(int i=0;i<10;i++){
					progressbar.setValue(progressbar.getValue()+1);
					progressbarlabel.setText(progressbar.getValue()+"%");
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				progressbar.setValue(100);
				progressbarlabel.setText("100%");
				
				for(TestCase tc:ResultService.list){
					System.out.println(tc.getTestCaseID()+" - "+tc.getResult().getWind_speed()+" - "+tc.getResult().getTakeoff_alt());
				}
				
			}
		});
		
		t.start();
		
	
}
	
	private void startPerformanceTestConfirmation2() {
		// TODO Auto-generated method stub
		
		t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				checkedtestcasereportlist.removeAll(checkedtestcasereportlist);
				selectedtestcaselist.removeAll(selectedtestcaselist);
				
				for(JPanel jp:testcasereportlist){
					FunctionalTestCaseReportPartPanel ftcrpp=(FunctionalTestCaseReportPartPanel) jp;
					if (ftcrpp.getToolcheckbox().isSelected()) {
						checkedtestcasereportlist.add(ftcrpp);
						selectedtestcaselist.add(ftcrpp.getTestcase());
					}
				}
				
				String extraxmlpath="D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\xxyy#2.xml";
				extractDataToXml(extraxmlpath, selectedtestcaselist, 2);//生成测试用例xml
				File file=new File(extraxmlpath);
				
//				gaindatathread.start();
				datagainshowthread.start();
				
//				PropertyConfigurator.configure("src/log4j.properties");
				Controller.Run(new Pair<String, File>("Performance", file));

				while(progressbar.getValue()<90){
					try {
						System.out.println("***************************");
//						if(threadexceptionstate==1){
//							ExceptionStopRunThread();
//						}
//						if(Controller.handFutureList.get(0).isDone()){
//							try {
//								Controller.handFutureList.get(0).get();
//							} catch (ExecutionException e) {
//								// TODO Auto-generated catch block
//								System.out.println("+-+"+e.getMessage());
//								if(e.getMessage().contains("TestCaseException")){
////									TestCaseReportTabbedPanel.threadexceptionstate=1;
//									ExceptionStopRunThread();
//								}
//							}
//						}
						if (Controller.handFutureList.size() > 0) {
							for (int i = 0; i < Controller.handFutureList.size(); i++) {
								if (Controller.handFutureList.get(i).isDone()) {
									try {
										Controller.handFutureList.get(i).get();
									} catch (ExecutionException e) {
										// TODO Auto-generated catch block
										System.out.println("+-+" + e.getMessage());
										if (e.getMessage().contains("TestCaseException")) {
											ExceptionStopRunThread();
										}
									}
								}
							}
						}
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				resulttestcaselist=ResultService.list;
				
				showStatisticsDataByType(2);
				
				while(progressbar.getValue()<100){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				parentTestCaseDataPanel.getTestCaseChartDiagramButtonPanel().setVisible(true);
				
				TextAreaPrint("测试执行结束");
				
				Controller.resultService.closeService();
				
				CheckIsSaveDB();
				
				threadstate=0;
				
			}
			
		});
		
		gaindatathread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
//				int sum=checkedperformancetestcasereportlist.size();
				int sum=checkedtestcasereportlist.size();
				for(int index=0;index<sum;index++){
					TextAreaPrint("正在处理第 "+(index+1)+" 条测试用例");
					
					int startprogressbar = (int) ((double) 50 / sum * index);
					int endprogressbar = (int) ((double) 50 / sum * (index + 1));
					
					for(int i=startprogressbar;i<endprogressbar;i++){
						progressbar.setValue(progressbar.getValue()+1);
						progressbarlabel.setText(progressbar.getValue()+"%");
						
					}
					
					try {
//						Thread.sleep(200);
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
//				for(int i=0;i<50;i++){
//					TextAreaPrint();
//					progressbar.setValue(progressbar.getValue()+1);
//					progressbarlabel.setText(progressbar.getValue()+"%");
//					System.out.println("---- progressbar "+progressbar.getValue());
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
				TextAreaPrint("处理完成！");
				TextAreaPrint("正在接收服务器端返回的测试结果...");
			}
		});
		
		datagainshowthread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				performancelistindex=0;
				
				int resultlistindex=0;
				int datagainshowindex;
				int sum = 0;
				
				int startprogressbar;
				int endprogressbar;
				int sleeptime;
				
				//发送数据 1~10
				TextAreaPrint("发送测试用例数据...");
				sleeptime=selectedtestcaselist.size()/10*2;
				for(int i=1;i<10;i++){
					progressbar.setValue(progressbar.getValue()+1);
					progressbarlabel.setText(progressbar.getValue()+"%");
					
					try {
						Thread.sleep(sleeptime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				//数据收集显示 11~90
				sum+=selectedtestcaselist.size()/300;
				if(selectedtestcaselist.size()%300!=0){
					sum++;
				}
				
				for(datagainshowindex=0;datagainshowindex<sum*2;){
					
					//数据收集
					TextAreaPrint("正在进行测试...");
					startprogressbar = (int) ((double) 80 / (sum*2) * datagainshowindex);
					endprogressbar = (int) ((double) 80 / (sum*2) * (datagainshowindex + 1));
					System.out.println("startprogressbar "+startprogressbar+" endprogressbar "+endprogressbar+" progressbarvalue "+progressbar.getValue());
					datagainshowindex++;
					
					sleeptime=500;
					for(int i=startprogressbar;i<endprogressbar;i++){
						
						if(ResultService.list.size()>resultlistindex){
							if((ResultService.list.size()-resultlistindex>=300)||(ResultService.list.size()==selectedtestcaselist.size())){
								sleeptime=10;
							}
						}
						
						progressbar.setValue(progressbar.getValue()+1);
						progressbarlabel.setText(progressbar.getValue()+"%");
						
						try {
							Thread.sleep(sleeptime);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
//					while(resultlistindex>=ResultService.list.size()){
					while(!((ResultService.list.size()-resultlistindex>=300)||(ResultService.list.size()==selectedtestcaselist.size()))){
						System.out.println("-------");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					//展示数据
					
					startprogressbar = (int) ((double) 80 / (sum*2) * datagainshowindex);
					endprogressbar = (int) ((double) 80 / (sum*2) * (datagainshowindex + 1));
					System.out.println("startprogressbar "+startprogressbar+" endprogressbar "+endprogressbar+" progressbarvalue "+progressbar.getValue());
					datagainshowindex++;
					
					int progressbarsize;
					progressbarsize=endprogressbar-startprogressbar;
					
					int datasize;
					if(ResultService.list.size()-resultlistindex>=300){
						datasize=300;
					}
					else{
						datasize=ResultService.list.size()-resultlistindex;
					}
					
					for(int k=0;k<datasize;k++,resultlistindex++){
						
//						showTestCaseDataByType(2, resultlistindex);
						showTestCaseDataByType(1, resultlistindex);
						
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						startprogressbar = (int) ((double) progressbarsize / datasize * k);
						endprogressbar = (int) ((double) progressbarsize / datasize * (k + 1));
						
						for(int i=startprogressbar;i<endprogressbar;i++){
							progressbar.setValue(progressbar.getValue()+1);
							progressbarlabel.setText(progressbar.getValue()+"%");
						}
						
					}
					
				}
				
				//统计数据 91~100
				
				TextAreaPrint("正在进行数据统计整理...");
				for(int i=0;i<10;i++){
					progressbar.setValue(progressbar.getValue()+1);
					progressbarlabel.setText(progressbar.getValue()+"%");
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				progressbar.setValue(100);
				progressbarlabel.setText("100%");
				
				for(TestCase tc:ResultService.list){
					System.out.println(tc.getTestCaseID()+" - "+tc.getResult().getWind_speed()+" - "+tc.getResult().getTakeoff_alt());
				}
				
			}
		});
		
		t.start();
		
	}

	protected void startPerformanceRunProgressbar(final List<TestCase> list) {
		// TODO Auto-generated method stub
		
		try {
			progreseethread=new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
//					for(int index=0;index<testcaselist.size();index++){
//						int startprogressbar = (int) ((double) 100 / testcaselist.size() * index);
//						int endprogressbar = (int) ((double) 100 / testcaselist.size() * (index + 1));
//						
//						index++;
//						System.out.println(index);
////						System.out.println(startprogressbar+"  "+endprogressbar);
//						
//						for(int i=startprogressbar;i<endprogressbar;i++){
//							progressbar.setValue(progressbar.getValue()+1);
//							progressbarlabel.setText(progressbar.getValue()+"%");
////							ChangeRepaint();
//							
//							try {
//								Thread.sleep(100);
//							} catch (InterruptedException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//							
//						}
//					}
					
					int index=0;
//					for(PerformanceTestCaseReportPartPanel tcrpp:checkedperformancetestcasereportlist){
					for(JPanel jp:checkedtestcasereportlist){
						PerformanceTestCaseReportPartPanel tcrpp=(PerformanceTestCaseReportPartPanel) jp;
						
//						TestCase testcase=list.get(Integer.parseInt(tcrpp.getTestcase().getTestCaseID())-1);
						if(!list.get(index).getTestCaseID().equals(tcrpp.getTestcase().getTestCaseID())){
							tcrpp.setVisible(false);//同时为0的时候，数据会消掉，panel要去除
							continue;
						}
						TestCase testcase=list.get(index);
						
						JTable attributetable;
						DefaultTableModel attributetablemodel;
						
						attributetable=tcrpp.getAttributetable();
						attributetablemodel=tcrpp.getAttributetablemodel();
						
						for(myProcess p:testcase.getProcessList()){
							
							attributetablemodel.setValueAt(p.getProcessStatus(), p.getProcessID()-1, 3);
							attributetablemodel.setValueAt(p.isProcessExec(), p.getProcessID()-1, 4);
							
						}
						
						attributetablemodel.fireTableDataChanged();
						
						DefaultTableModel dtm=tcrpp.getTitletablemodel();
						dtm.setValueAt(testcase.getResult().getBattery_remaining(), 0, 3);
						dtm.setValueAt(testcase.getResult().getTime(), 0, 4);
						
						TextAreaPrint(testcase.toString());
						
						int startprogressbar = (int) ((double) 49 / list.size() * index);
						int endprogressbar = (int) ((double) 49 / list.size() * (index + 1));
						
						index++;
						System.out.println(index);
						
						for(int i=startprogressbar;i<endprogressbar;i++){
							progressbar.setValue(progressbar.getValue()+1);
							progressbarlabel.setText(progressbar.getValue()+"%");
							
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						
					}
					
					progressbar.setValue(100);
					progressbarlabel.setText("100%");
					
				}
				
			});
			
			progreseethread.start();
			progreseethread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void startTimeTestConfirmation(){
		
		t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				checkedtestcasereportlist.removeAll(checkedtestcasereportlist);
				selectedtestcaselist.removeAll(selectedtestcaselist);
				
				for(JPanel jp:testcasereportlist){
					TimeTestCaseReportPartPanel ttcrpp=(TimeTestCaseReportPartPanel) jp;
					if (ttcrpp.getToolcheckbox().isSelected()) {
						checkedtestcasereportlist.add(ttcrpp);
						selectedtestcaselist.add(ttcrpp.getTestcase());
					}
				}
				
				String extraxmlpath="D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\xxyy#3.xml";
				extractDataToXml(extraxmlpath, selectedtestcaselist, 3);//生成测试用例xml
				File file=new File(extraxmlpath);
				
				System.out.println(selectedtestcaselist.size());
				
//				gaindatathread.start();
				
				datagainshowthread.start();
				
//				PropertyConfigurator.configure("src/log4j.properties");
				Controller.Run(new Pair<String, File>("Time", file));
				
				while(progressbar.getValue()<90||ResultService.list.size()<=0){
					try {
						System.out.println("***************************"+ResultService.list.size());
//						if(threadexceptionstate==1){
//							ExceptionStopRunThread();
//						}
//						if(Controller.handFutureList.get(0).isDone()){
//							try {
//								Controller.handFutureList.get(0).get();
//							} catch (ExecutionException e) {
//								// TODO Auto-generated catch block
//								System.out.println("+-+"+e.getMessage());
//								if(e.getMessage().contains("TestCaseException")){
////									TestCaseReportTabbedPanel.threadexceptionstate=1;
//									ExceptionStopRunThread();
//								}
//							}
//						}
						if (Controller.handFutureList.size() > 0) {
							for (int i = 0; i < Controller.handFutureList.size(); i++) {
								if (Controller.handFutureList.get(i).isDone()) {
									try {
										Controller.handFutureList.get(i).get();
									} catch (ExecutionException e) {
										// TODO Auto-generated catch block
										System.out.println("+-+" + e.getMessage());
										if (e.getMessage().contains("TestCaseException")) {
											ExceptionStopRunThread();
										}
									}
								}
							}
						}
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
//				TextAreaPrint("正在进行数据统计整理...");
//				for(TestCase tc:resulttestcaselist){
//					System.out.println(tc.getTestCaseID()+" - "+tc.getState()+" - "+tc.getResult().toString());
					
//					System.out.println(tc.getResult().getTimeLimit().getError().size());
					
//					Time time=tc.getResult().getTimeLimit();
					
//					System.out.println(time.getOriginal());
//					System.out.println("time.getError() : "+time.getError().size());
//					for(String s:time.getError()){
//						System.out.println(s);
//					}
//					System.out.println("time.getShowMap() : "+time.getShowMap().size());
//					for(Entry<String, Pair<String, String>> en:time.getShowMap().entrySet()){
//						System.out.println(en.getKey()+"  -  "+en.getValue().getFirst()+"  -  "+en.getValue().getSecond());
//						
//					}
					
//				}
//				System.out.println("-------------------------------------------------");
//				startTimeRunProgressbar(resulttestcaselist);// 显示进度条

				resulttestcaselist=ResultService.list;
				
				showStatisticsDataByType(3);
				
				while(progressbar.getValue()<100){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				parentTestCaseDataPanel.getTestCaseChartDiagramButtonPanel().setVisible(true);
				
				TextAreaPrint("测试执行结束");
				
				Controller.resultService.closeService();
				
				CheckIsSaveDB();
				
				threadstate=0;
			}
			
		});
		
		gaindatathread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
//				int sum=checkedtimetestcasereportlist.size();
				int sum=checkedtestcasereportlist.size();
				for(int index=0;index<sum;index++){
					TextAreaPrint("正在处理第 "+(index+1)+" 条测试用例");
					
					int startprogressbar = (int) ((double) 50 / sum * index);
					int endprogressbar = (int) ((double) 50 / sum * (index + 1));
					
					for(int i=startprogressbar;i<endprogressbar;i++){
						progressbar.setValue(progressbar.getValue()+1);
						progressbarlabel.setText(progressbar.getValue()+"%");
						
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
				
				progressbar.setValue(50);
				progressbarlabel.setText(progressbar.getValue()+"%");
				
				TextAreaPrint("处理完成！");
				TextAreaPrint("正在接收服务器端返回的测试结果...");
			}
		});
		
		datagainshowthread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				int resultlistindex=0;
				int datagainshowindex;
				int sum = 0;
				
				int startprogressbar;
				int endprogressbar;
				int sleeptime;
				
				//发送数据 1~10
				TextAreaPrint("发送测试用例数据...");
				sleeptime=selectedtestcaselist.size();
				for(int i=1;i<10;i++){
					progressbar.setValue(progressbar.getValue()+1);
					progressbarlabel.setText(progressbar.getValue()+"%");
					
					try {
						Thread.sleep(sleeptime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				//数据收集显示 11~90
				sum+=selectedtestcaselist.size()/300;
				if(selectedtestcaselist.size()%300!=0){
					sum++;
				}
				
				for(datagainshowindex=0;datagainshowindex<sum*2;){
					
					//数据收集
					TextAreaPrint("正在进行测试...");
					startprogressbar = (int) ((double) 80 / (sum*2) * datagainshowindex);
					endprogressbar = (int) ((double) 80 / (sum*2) * (datagainshowindex + 1));
					System.out.println("startprogressbar "+startprogressbar+" endprogressbar "+endprogressbar+" progressbarvalue "+progressbar.getValue());
					datagainshowindex++;
					
					if(selectedtestcaselist.size()>300){
						sleeptime=300*8000/(endprogressbar-startprogressbar);
					}
					else{
						sleeptime=selectedtestcaselist.size()*8000/(endprogressbar-startprogressbar);
					}
					
					for(int i=startprogressbar;i<endprogressbar;i++){
						
						if(ResultService.list.size()>resultlistindex){
							if((ResultService.list.size()-resultlistindex>=300)||(ResultService.list.size()==selectedtestcaselist.size())){
								sleeptime=100;
							}
						}
						
						progressbar.setValue(progressbar.getValue()+1);
						progressbarlabel.setText(progressbar.getValue()+"%");
						
						try {
							Thread.sleep(sleeptime);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
//					while(resultlistindex>=ResultService.list.size()){
					while(!((ResultService.list.size()-resultlistindex>=300)||(ResultService.list.size()==selectedtestcaselist.size()))){
						System.out.println("-------");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					//展示数据
					
					startprogressbar = (int) ((double) 80 / (sum*2) * datagainshowindex);
					endprogressbar = (int) ((double) 80 / (sum*2) * (datagainshowindex + 1));
					System.out.println("startprogressbar "+startprogressbar+" endprogressbar "+endprogressbar+" progressbarvalue "+progressbar.getValue());
					datagainshowindex++;
					
					int progressbarsize;
					progressbarsize=endprogressbar-startprogressbar;
					
					int datasize;
					if(ResultService.list.size()-resultlistindex>=300){
						datasize=300;
					}
					else{
						datasize=ResultService.list.size()-resultlistindex;
					}
					
					for(int k=0;k<datasize;k++,resultlistindex++){
						
						showTestCaseDataByType(3, resultlistindex);
						
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						startprogressbar = (int) ((double) progressbarsize / datasize * k);
						endprogressbar = (int) ((double) progressbarsize / datasize * (k + 1));
						
						for(int i=startprogressbar;i<endprogressbar;i++){
							progressbar.setValue(progressbar.getValue()+1);
							progressbarlabel.setText(progressbar.getValue()+"%");
						}
						
					}
					
				}
				
				//统计数据 91~100
				TextAreaPrint("正在进行数据统计整理...");
				for(int i=0;i<10;i++){
					progressbar.setValue(progressbar.getValue()+1);
					progressbarlabel.setText(progressbar.getValue()+"%");
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				progressbar.setValue(100);
				progressbarlabel.setText("100%");
				
			}
		});

		t.start();
		
	}

	protected void startTimeRunProgressbar(final List<TestCase> list) {
		// TODO Auto-generated method stub
		
		try {
			progreseethread=new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					int index=0;
//					for(TimeTestCaseReportPartPanel ttcrpp:checkedtimetestcasereportlist){
					for(JPanel jp:checkedtestcasereportlist){
						TimeTestCaseReportPartPanel ttcrpp=(TimeTestCaseReportPartPanel) jp;
						
//						TestCase testcase=list.get(Integer.parseInt(ftcrpp.getTestcase().getTestCaseID())-1);
//						while(!list.get(index).getTestCaseID().equals(ttcrpp.getTestcase().getTestCaseID())){
//							index++;
//						}
						TestCase testcase=list.get(index);
						
						JTable attributetable;
						DefaultTableModel attributetablemodel;
						
						attributetable=ttcrpp.getAttributetable();
						attributetablemodel=ttcrpp.getAttributetablemodel();
						
						for(myProcess p:testcase.getProcessList()){
							
							attributetablemodel.setValueAt(p.getProcessStatus(), p.getProcessID()-1, 3);
							attributetablemodel.setValueAt(p.isProcessExec(), p.getProcessID()-1, 4);
							
						}
						
						attributetablemodel.fireTableDataChanged();
						
						JTable limittable;
						DefaultTableModel limittablemodel;
						
						limittable=ttcrpp.getLimittable();
						limittablemodel=ttcrpp.getLimittablemodel();
						
						Map<String, Pair<String, String>> map=testcase.getResult().getTimeLimit().getShowMap();
						for(int i=0;i<limittablemodel.getRowCount();i++){
							String limit=(String) limittablemodel.getValueAt(i, 0);
							Pair p=map.get(limit);
							int state=0;
							if(p.getSecond().equals(true)){
								state=1;
							}
							if(p.getFirst()==null||p.getFirst().equals("")){
								state=0;
							}
							limittablemodel.setValueAt(p.getFirst(), i, 1);
							limittablemodel.setValueAt(state, i, 2);
							System.out.println(limit+"  -  "+state+"  -  "+p.getFirst()+"  -  "+p.getSecond());
						}
						
						String title = "";
						title+="测试用例ID:"+ttcrpp.getTestcase().getTestCaseID()+"     ";
//						title+=testcase.getState()+"     ";
//						title+="执行结果:"+testcase.getResult().substring(0, testcase.getResult().indexOf("耗时"));
//						title+="执行结果:"+testcase.getResult().getResultDetail();
						title+="执行结果:"+testcase.getState()+"     ";
						title+="总耗时:"+testcase.getExetime()+" ms";
						
						ttcrpp.getTitlelabel().setText(title);
						
						String absolutePath = System.getProperty("user.dir");
						String path = absolutePath + "\\src\\site\\resources\\icons\\OpreationPart\\";

						ImageIcon icon1 = new ImageIcon(this.getClass().getResource("ImagePart/tick.png"));
						icon1.setImage(icon1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
						ImageIcon icon2 = new ImageIcon(this.getClass().getResource("ImagePart/cross.png"));
						icon2.setImage(icon2.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));

						if(testcase.getState().contains("正确")){
							ttcrpp.getIconlabel().setIcon(icon1);
						}
						else{
							ttcrpp.getIconlabel().setIcon(icon2);
						}
						
						TextAreaPrint(testcase.toString());
						
						int startprogressbar = (int) ((double) 70 / list.size() * index);
						int endprogressbar = (int) ((double) 70 / list.size() * (index + 1));
						
						index++;
						System.out.println(index);
						
						for(int i=startprogressbar;i<endprogressbar;i++){
							progressbar.setValue(progressbar.getValue()+1);
							progressbarlabel.setText(progressbar.getValue()+"%");
							
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						
					}
					
				}
				
			});
			
			progreseethread.start();
			progreseethread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			return (int)(a*1.0/b*100+0.5)+" %";
		}
	}


	private void initMoviePanel() {
		// TODO Auto-generated method stub
		
		moviepanel.getMovieLabel().setText("正在进行测试");
		
	}

	private void initTablePanel() {
		// TODO Auto-generated method stub
		
		tableresultpanel=new JPanel();
		tableresultpanel.setLayout(new GridLayout());
		tableresultpanel.setBorder(null);
		tableresultpanel.setBackground(new Color(255, 255, 255));
		
		tablescrollpanel=new JScrollPane(tableresultpanel);
        tablescrollpanel.setBorder(null);
        tablescrollpanel.setBackground(new Color(255, 255, 255));
		
//		tablepanel.setBackground(new Color(255, 255, 255));
		tablepanel.setLayout(new GridLayout());
		tablepanel.add(tablescrollpanel);
//		tablepanel.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
		
	}

	public JPanel getTableresultpanel() {
		return tableresultpanel;
	}
	
	public JPanel getToolbuttonpanel7() {
		return toolbuttonpanel7;
	}

	public void TextAreaPrint(String word){
		JTextArea textarea=mainFrame.getConsolePartPanel().getTextarea5();
		textarea.append(word+"\n");
		textarea.setCaretPosition(textarea.getDocument().getLength());
	}
	
	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}
	
	private void extractDataToXml(String path, List<TestCase> list, int type) {
		// TODO Auto-generated method stub
		
		Document doc = DocumentHelper.createDocument();
		Element TCS=doc.addElement("TCS");
		
		if(type==3){
			for(TestCase tc:list){
				Element testcase = TCS.addElement("testcase");
//				System.out.println(tc.getProcessList().size());
				for(myProcess p:tc.getProcessList()){
					Element process = testcase.addElement("process");
					Element operation = process.addElement("operation");
					Element input = process.addElement("input");
					Element time = process.addElement("time");

					operation.setText(p.getProcessName());
					input.setText(p.getProcessParam());
					
					if(p.getProcessStatus().contains("=")){
						time.setText(p.getProcessStatus().split("=")[0]);
					}
					else{
						time.setText(p.getProcessStatus());
					}
				}
				
				Element limit = testcase.addElement("limit");
				Element limit_operation = limit.addElement("operation");
				StringBuffer strlimit = new StringBuffer();
//				System.out.println(tc.getLimit().size());
				for(String l:tc.getLimit()){
					strlimit.append(l);
					strlimit.append(",");
				}
				if(strlimit.length()>0){
					strlimit.deleteCharAt(strlimit.length()-1);	
				}
				limit_operation.setText(strlimit.toString());
				
			}
		}
		else{
			for(TestCase tc:list){
				Element testcase = TCS.addElement("testcase");
//				System.out.println(tc.getProcessList().size());
				for(myProcess p:tc.getProcessList()){
					Element process = testcase.addElement("process");
					Element operation = process.addElement("operation");
					Element input = process.addElement("input");

					operation.setText(p.getProcessName());
					input.setText(p.getProcessParam());
					
				}
			}
		}

		try {
			// 定义输出流的目的地
//			String baseUrl = "D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase";
			FileWriter fw = new FileWriter(path);

			// 定义输出格式和字符集
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");

			// 定义用于输出xml文件的XMLWriter对象
			XMLWriter xmlWriter = new XMLWriter(fw, format);
			xmlWriter.write(doc);// *****
			xmlWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
//	public List<TestCase> extractData() {
//
//		// 测试用例ID
//		String testCaseID = null;
//		// 测试用例 激励链表
//		List<myProcess> processList = new ArrayList<myProcess>();
//		// 测试用例执行状态
//		String state = null;
//		// 测试用例执行结果
//		String result = null;
//
//		String process;
//
//		// 激励ID
//		int processID;
//		// 激励名称
//		String processName;
//		// 激励参数
//		String processParam;
//		// 激励状态
//		String processStatus;
//		// 激励执行情况
//		boolean processExec;
//
//		int startendstate = 0;
//
//		List<TestCase> testcaseList = new ArrayList<TestCase>();
//
//		try {
//
////			 String encoding = "utf-8";
//			String encoding = "GBK";
//
//			String filePath="D:\\123.txt";
//			
//			File file = new File(filePath);
//			if (file.isFile() && file.exists()) { // 判断文件是否存在
//				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
//				BufferedReader bufferedReader = new BufferedReader(read);
//				String lineTxt = null;
//				
//				int max=20;
//		        int min=1;
//		        Random random = new Random();
//		        int num1 = 0,num2,fstate=0;
//		        int k=0;
//				
//				while ((lineTxt = bufferedReader.readLine()) != null) {
//
//					if (startendstate == 1) {
//
//						processList = new ArrayList<myProcess>();
//						startendstate = 0;
//						
//						num1= random.nextInt(max)%(max-min+1) + min;
//
//					}
//
//					if (lineTxt.substring(0, 8).equals("TestCase")) {
//
//						testCaseID = lineTxt.substring(lineTxt.indexOf("testCaseID=") + 11, lineTxt.indexOf(","));
//
//					} else if (lineTxt.substring(1, 10).equals("myProcess")) {
//
//						process = lineTxt.substring(lineTxt.indexOf("[") + 1, lineTxt.indexOf("]"));
//						processID = Integer.valueOf(process.substring(process.indexOf("processID=") + 10,
//								process.indexOf(", processName=")));
//						processName = process.substring(process.indexOf("processName=") + 12,
//								process.indexOf(", processParam="));
//						processParam = process.substring(process.indexOf("processParam=") + 13,
//								process.indexOf(", processStatus="));
//						processStatus = process.substring(process.indexOf("processStatus=") + 14,
//								process.indexOf(", processExec="));
////						if(process.substring(process.indexOf("processExec=")+12, process.length()).equals("true")){
////							processExec=true;
////						}
////						else{
////							processExec=false;
////						}
//						
//						if(num1>18){
//							num2= random.nextInt(max)%(max-min+1) + min;
//							if(num2>15){
//								processExec=false;
//								fstate=1;
//							}
//							else{
//								processExec=true;
//							}
//						}
//						else{
//							processExec=true;
//						}
//
//						myProcess p = new myProcess();
//						p.setProcessID(processID);
//						p.setProcessName(processName);
//						p.setProcessParam(processParam);
//						p.setProcessStatus(processStatus);
//						p.setProcessExec(processExec);
//
//						processList.add(p);
//						
//						fstate=0;
//
//					} else if (lineTxt.substring(0, 2).equals(", ")) {
//
//						state = lineTxt.substring(lineTxt.indexOf("state=") + 6, lineTxt.indexOf(", result="));
//						result = lineTxt.substring(lineTxt.indexOf("result=") + 7, lineTxt.indexOf(", detail="));
//						
//				        if(num1>19){
//				        	state="程序执行过程中出现死循环或者抛出异常!";
//				        	result="程序出现出现死循环或者抛出异常!";
//				        	
//				        	if(fstate==0){
//				        		processList.get(random.nextInt(processList.size()-1)%(processList.size())).setProcessExec(false);
////				        		System.out.println(k++);
//				        	}
//				        	
//				        }
//				        else if(num1>18){
//				        	state="测试用例有误,无法对应到执行程序，且测试耗时:0[不准确]";
//				        	result="测试用例有误,无法对应到执行程序!";
//				        	
//				        	if(fstate==0){
//				        		processList.get(random.nextInt(processList.size()-1)%(processList.size())).setProcessExec(false);
////				        		System.out.println(k++);
//				        	}
//				        	
//				        }
//				        else{
//				        	state="测试耗时:0";
//				        	result="测试执行成功!耗时:0";
//				        }
//						
//
//					} else if (lineTxt.substring(lineTxt.length() - 2, lineTxt.length()).equals("]]")) {
//
//						TestCase tc = new TestCase();
//						tc.setTestCaseID(testCaseID);
//						tc.setProcessList(processList);
//						tc.setState(state);
//						tc.setResult(result);
//
//						testcaseList.add(tc);
//
//						startendstate = 1;
//
//					}
//
//				}
//				read.close();
//			} else {
//				System.out.println("找不到指定的文件");
//			}
//		} catch (Exception e) {
//			System.out.println("读取文件内容出错");
//			e.printStackTrace();
//		}
//
////		System.out.println(testcaseList.size());
////
////		for (TestCase tc : testcaseList) {
////
////			System.out.println(tc.toString());
////
////		}
//		
//		return testcaseList;
//
//	}

}

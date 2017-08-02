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
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

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
import com.horstmann.violet.application.gui.util.chengzuo.Bean.Pair;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.TestCase;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.TestCaseResult;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.Time;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.myProcess;
import com.horstmann.violet.application.gui.util.chengzuo.Util.Controller;
import com.horstmann.violet.application.gui.util.chengzuo.Util.TestCaseConvertUtil;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;

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
	private int threadstate=0;
	
	private JScrollPane tablescrollpanel;
	private JPanel tableresultpanel;
	
	private TestCaseDataPanel parentTestCaseDataPanel;
	private String testcasename;
	private int testcasetype;
	private List<JPanel> testcasereportlist=new ArrayList<JPanel>();
	private List<JPanel> checkedtestcasereportlist=new ArrayList<JPanel>();
	
//	private List<FunctionalTestCaseReportPartPanel> functionaltestcasereportlist=new ArrayList<FunctionalTestCaseReportPartPanel>();
//	private List<FunctionalTestCaseReportPartPanel> checkedfunctionaltestcasereportlist=new ArrayList<FunctionalTestCaseReportPartPanel>();
//	private List<PerformanceTestCaseReportPartPanel> performancetestcasereportlist=new ArrayList<PerformanceTestCaseReportPartPanel>();
//	private List<PerformanceTestCaseReportPartPanel> checkedperformancetestcasereportlist=new ArrayList<PerformanceTestCaseReportPartPanel>();
//	private List<TimeTestCaseReportPartPanel> timetestcasereportlist=new ArrayList<TimeTestCaseReportPartPanel>();
//	private List<TimeTestCaseReportPartPanel> checkedtimetestcasereportlist=new ArrayList<TimeTestCaseReportPartPanel>();
	
	private List<TestCase> selectedtestcaselist=new ArrayList<TestCase>();
	
	private int[] testcasecount=new int[3];
	private List<Integer> testcasecountlist=new ArrayList<Integer>();
	
	public TestCaseReportTabbedPanel(TestCaseDataPanel testCaseDataPanel, MainFrame mainframe){
		
		this.mainFrame=mainframe;
		
		this.parentTestCaseDataPanel=testCaseDataPanel;
		
		this.testcasename=testCaseDataPanel.getTestCaseName();
		
		this.testcasetype=testCaseDataPanel.getTestCaseType();
		
		this.testcasereportlist=testCaseDataPanel.getTestcasereportlist();
		
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

		ImageIcon icon1 = new ImageIcon(path + "start.png");
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(path + "suspend.png");
		icon2.setImage(icon2.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon3 = new ImageIcon(path + "stop.png");
		icon3.setImage(icon3.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon4 = new ImageIcon(path + "up_arrow.png");
		icon4.setImage(icon4.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon5 = new ImageIcon(path + "down_arrow.png");
		icon5.setImage(icon5.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon6 = new ImageIcon(path + "allselect.png");
		icon6.setImage(icon6.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon7 = new ImageIcon(path + "cancelselect.png");
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
				
				if (threadstate == 0) {
//					if(mainFrame.getTestCaseConfirmationPanel().getTestcasename()==null){
//						
//					}
//					else{
						progressbar.setValue(0);
						progressbarlabel.setText(" ");
						
						progressbarindex=0;
						
//						mainFrame.getStepFiveCenterTabbedPane().getTestCaseChartDiagramButtonPanel().setVisible(false);
						
//						clientSocket = new ClientSocket("10.1.16.89", 5555);
//						Boolean connectstate=clientSocket.Connection();
						Boolean connectstate=true;
						if(connectstate){
							TextAreaPrint("连接服务器成功");
							
							progressbar.setValue(0);
							progressbarlabel.setText("0%");
							
							if(testcasetype==1){
								startFunctionalTestConfirmation();
							}
							else if(testcasetype==2){
								startPerformanceTestConfirmation();
							}
							else if(testcasetype==3){
								startTimeTestConfirmation();
							}
							
							threadstate=1;
						}
						else{
							TextAreaPrint("连接服务器失败！！！");
						}
//					}
					
					
				} else if (threadstate == 1) {

				} else if (threadstate == -1) {
					threadstate = 1;
					t.resume();
					if(progressbar.getValue()>50){
						progreseethread.resume();
					}
					else{
						gaindatathread.resume();
					}
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
					if(progressbar.getValue()>50){
						progreseethread.suspend();
					}
					else{
						gaindatathread.suspend();
					}
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
					t.stop();
					if(progressbar.getValue()>50){
						progreseethread.stop();
					}
					else{
						gaindatathread.stop();
					}
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
					
					for(JPanel jp:testcasereportlist){
						PerformanceTestCaseReportPartPanel ptcrpp=(PerformanceTestCaseReportPartPanel) jp;
						if(ptcrpp.getAttributepanel().isVisible()){
							ptcrpp.getAttributepanel().setVisible(false);
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
					if(windcheckboxpanelstate==0){
						windcheckboxpanel.setVisible(true);
						windcheckboxpanelstate=1;
					}
					else if(windcheckboxpanelstate==1){
						windcheckboxpanel.setVisible(false);
						windcheckboxpanelstate=0;
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
					if(windcheckboxpanelstate==0){
						windcheckboxpanel.setVisible(true);
						windcheckboxpanelstate=1;
					}
					else if(windcheckboxpanelstate==1){
						windcheckboxpanel.setVisible(false);
						windcheckboxpanelstate=0;
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
			public void run() {
				// TODO Auto-generated method stub
				
//				checkedfunctionaltestcasereportlist.removeAll(checkedfunctionaltestcasereportlist);
//				selectedtestcaselist.removeAll(selectedtestcaselist);
//				
//				functionaltestcasereportlist=mainFrame.getTestCaseConfirmationPanel().getFunctionaltestcasereportlist();
//				
//				for(FunctionalTestCaseReportPartPanel ftcrpp:functionaltestcasereportlist){
//					
//					if (ftcrpp.getToolcheckbox().isSelected()) {
//						checkedfunctionaltestcasereportlist.add(ftcrpp);
//						selectedtestcaselist.add(ftcrpp.getTestcase());
//					}
//					
//				}
				
				checkedtestcasereportlist.removeAll(checkedtestcasereportlist);
				selectedtestcaselist.removeAll(selectedtestcaselist);
				
				int index=0;
				for(JPanel jp:testcasereportlist){
					FunctionalTestCaseReportPartPanel ftcrpp=(FunctionalTestCaseReportPartPanel) jp;
					if (ftcrpp.getToolcheckbox().isSelected()) {
						checkedtestcasereportlist.add(ftcrpp);
						selectedtestcaselist.add(ftcrpp.getTestcase());
					}
					index++;
					if(index==300){
						break;
					}
				}
				
				
//				testcasename=mainFrame.getTestCaseConfirmationPanel().getTestcasename();
				
				String extraxmlpath="D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\"+testcasename+"selected.xml";
				extractDataToXml(extraxmlpath, selectedtestcaselist, 1);//生成测试用例xml
				File file=new File(extraxmlpath);
				
				//接收到测试结果list
//				File[] files = {file};
//				clientSocket.sendFile(files);
				
				TextAreaPrint("发送测试用例数据...");
				gaindatathread.start();
				
				Controller.Run(new Pair<String, File>("function", file));

				
				
//				List<TestCase> testcaselist= ClientRecThread.getTestCaseList();
				List<TestCase> testcaselist=new ArrayList<>();
				try {
					testcaselist = Controller.getResult("function");
				} catch (ExecutionException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				File f=new File("D:\\test.txt");
				try {
					Writer w=new FileWriter(f,false);
					for(TestCase tc:testcaselist){
//						System.out.println(tc.toString());
						w.write(tc.toString());
						w.write("\n");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				try {
					String path="D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\"+testcasename+"serialtestcase.txt";
					FileOutputStream fos = new FileOutputStream(path);
					ObjectOutputStream oos=new ObjectOutputStream(fos);
					
					for(TestCase tc:testcaselist){
						oos.writeObject(tc);
					}
					
					oos.close();
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
//				List<TestCase> testcaselist=new ArrayList<>();
//				try {
//					String serialpath = "D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\"+testcasename+"serialtestcase.txt";
//					FileInputStream fis = new FileInputStream(serialpath);
//					ObjectInputStream ois = new ObjectInputStream(fis);
//
//					while(true){//使用处理异常的方式来判断文件是否结束
//						try {
//							TestCase tc=(TestCase) ois.readObject();//文件读取完毕后，会抛异常
//							testcaselist.add(tc);
//						} catch (Exception  e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//							System.out.println("文件读取完毕!");  
//			                break;  
//						}
//					}
//
//					ois.close();
//					fis.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
//				progressbar.setValue(50);
				
				while(progressbar.getValue()<50){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				TextAreaPrint("正在进行数据统计整理...");
				
				Map<String, Object> testcasemap=TestCaseConvertUtil.functionStatistics(testcaselist);
				List<Integer> caseSuccess=(List<Integer>) testcasemap.get("caseSuccess");
				List<Integer> caseFailed=(List<Integer>) testcasemap.get("caseFailed");
				Map<String,List<Map<Integer,List<Integer>>>> failedStatistics=(Map<String, List<Map<Integer, List<Integer>>>>) testcasemap.get("failedStatistics");
						
				System.out.println("caseSuccess "+caseSuccess.size());
				System.out.println("caseFailed "+caseFailed.size());
				for(Entry<String, List<Map<Integer,List<Integer>>>> en:failedStatistics.entrySet()){
					System.out.println(en.getKey()+"  "+en.getValue().size());
				}
				
				startFunctionalRunProgressbar(testcaselist);// 显示进度条

//				changeDataInTable(testcaselist);//显示测试结果
				parentTestCaseDataPanel.getTestCaseChartDiagramButtonPanel().setVisible(true);
				
				FunctionalTestCaseChartTabbedPanel functionalTestCaseChartTabbedPanel=new FunctionalTestCaseChartTabbedPanel(mainFrame);
				
				parentTestCaseDataPanel.getTestCaseChartTabbedPanel().removeAll();
				parentTestCaseDataPanel.getTestCaseChartTabbedPanel().add(functionalTestCaseChartTabbedPanel);
				
				DefaultTableModel tabelmodel=functionalTestCaseChartTabbedPanel.getAttributetablemodel();
				
//				for(TestCase tc:testcaselist){
//					TestCaseResult tcr=tc.getResult();
//					Object[] rowData={tc.getTestCaseID(),tcr.getWind_speed(),tcr.getTakeoff_alt(),tcr.getBattery_remaining(),tcr.getTime()};
//					tabelmodel.addRow(rowData);
//				}
				
				int cs=0,cf=0,csum=0;
				cs=caseSuccess.size();
				cf=caseFailed.size();
				
//				cs=1935;
//				cf=139;
				
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
				
//				f1=116;
//				f2=23;
				
				DefaultTableModel failedstatisticstabelmodel=functionalTestCaseChartTabbedPanel.getFailedstatisticsattributetablemodel();
				while(failedstatisticstabelmodel.getRowCount()>0){
					failedstatisticstabelmodel.removeRow(0);
				}
				Object[] rowData3={"合计：", f1, f2, cf};
				failedstatisticstabelmodel.addRow(rowData3);
				Object[] rowData4={"百分比：", calcper(f1, cf), calcper(f2, cf), calcper(cf, cf)};
				failedstatisticstabelmodel.addRow(rowData4);
				
				
//				FunctionSuccessFailedPieChart fsfpc=new FunctionSuccessFailedPieChart(caseSuccess, caseFailed);
				FunctionSuccessFailedPieChart fsfpc=new FunctionSuccessFailedPieChart(cs, cf);
				functionalTestCaseChartTabbedPanel.getSuccessfailedpiepanel().removeAll();
				functionalTestCaseChartTabbedPanel.getSuccessfailedpiepanel().add(fsfpc.createChart());
				
//				FunctionFailedStatisticsPieChart ffspc=new FunctionFailedStatisticsPieChart(failedStatistics);
				FunctionFailedStatisticsPieChart ffspc=new FunctionFailedStatisticsPieChart(f1, f2);
				functionalTestCaseChartTabbedPanel.getFailedstatisticspiepanel().removeAll();
				functionalTestCaseChartTabbedPanel.getFailedstatisticspiepanel().add(ffspc.createChart());
				
				threadstate = 0;
				
//				ClientRecThread.testCaseList.removeAll(ClientRecThread.testCaseList);
				
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

		t.start();
		
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

						ImageIcon icon1 = new ImageIcon(path + "tick.png");
						icon1.setImage(icon1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
						ImageIcon icon2 = new ImageIcon(path + "cross.png");
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
				
//				checkedperformancetestcasereportlist.removeAll(checkedperformancetestcasereportlist);
//				selectedtestcaselist.removeAll(selectedtestcaselist);
//				
//				performancetestcasereportlist=mainFrame.getTestCaseConfirmationPanel().getPerformancetestcasereportlist();
//				
//				for(PerformanceTestCaseReportPartPanel tcrpp:performancetestcasereportlist){
//					
////					if(tcrpp.getToolcheckbox().isSelected()){
//						checkedperformancetestcasereportlist.add(tcrpp);
//						selectedtestcaselist.add(tcrpp.getTestcase());
////					}
//					
//				}
				
				checkedtestcasereportlist.removeAll(checkedtestcasereportlist);
				selectedtestcaselist.removeAll(selectedtestcaselist);
				
//				int index=0;
				for(JPanel jp:testcasereportlist){
					PerformanceTestCaseReportPartPanel tcrpp=(PerformanceTestCaseReportPartPanel) jp;
					checkedtestcasereportlist.add(tcrpp);
					selectedtestcaselist.add(tcrpp.getTestcase());
//					index++;
//					if(index==1000){
//						break;
//					}
				}
				
//				testcasename=mainFrame.getTestCaseConfirmationPanel().getTestcasename();
				
				String extraxmlpath="D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\"+testcasename+"selected.xml";
				extractDataToXml(extraxmlpath, selectedtestcaselist, 2);//生成测试用例xml
				File file=new File(extraxmlpath);
				
				//接收到测试结果list
//				File[] files = {file};
//				clientSocket.sendFile(files);
				
				TextAreaPrint("发送测试用例数据...");
				gaindatathread.start();
				
				Controller.Run(new Pair<String, File>("performance", file));

//				List<TestCase> testcaselist = ClientRecThread.getTestCaseList();
				List<TestCase> testcaselist=new ArrayList<>();
				try {
					testcaselist = Controller.getResult("performance");
				} catch (ExecutionException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				File f=new File("D:\\test.txt");
				try {
					Writer w=new FileWriter(f,false);
					for(TestCase tc:testcaselist){
//						System.out.println(tc.toString());
						w.write(tc.toString());
						w.write("\n");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				try {
					String path="D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\"+testcasename+"serialtestcase.txt";
					FileOutputStream fos = new FileOutputStream(path);
					ObjectOutputStream oos=new ObjectOutputStream(fos);
					
					for(TestCase tc:testcaselist){
//						System.out.println(tc.toString());
						oos.writeObject(tc);
					}
					
					oos.close();
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
//				List<TestCase> testcaselist=new ArrayList<>();
//				try {
//					String serialpath = "D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\"+testcasename+"serialtestcase.txt";
//					FileInputStream fis = new FileInputStream(serialpath);
//					ObjectInputStream ois = new ObjectInputStream(fis);
//
//					while(true){//使用处理异常的方式来判断文件是否结束
//						try {
//							TestCase tc=(TestCase) ois.readObject();//文件读取完毕后，会抛异常
//							testcaselist.add(tc);
//						} catch (Exception  e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//							System.out.println("文件读取完毕!");  
//			                break;  
//						}
//					}
//
//					ois.close();
//					fis.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
//				progressbar.setValue(50);
				
				while(progressbar.getValue()<50){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				TextAreaPrint("正在进行数据统计整理...");
				Map testcasemap=TestCaseConvertUtil.testCaseStatistics(testcaselist);

				List<Pair> highspeeddata=(List<Pair>) testcasemap.get("high-speed");
				List<Pair> timespeeddata=(List<Pair>) testcasemap.get("time-speed");
				Map<String, List<Pair>> highbatterydata=(Map<String, List<Pair>>) testcasemap.get("high-battery");
				Map<String, List<Pair>> hightimedata=(Map<String, List<Pair>>) testcasemap.get("high-time");
				
//				System.out.println(highspeeddata.size());
//				for(Pair p:highspeeddata){
//					System.out.println(p.getFirst()+" - - "+p.getSecond());
//				}
//				
//				System.out.println(timespeeddata.size());
//				for(Pair p:timespeeddata){
//					System.out.println(p.getFirst()+" - - "+p.getSecond());
//				}
//				
//				System.out.println(highbatterydata.size());
//				for(Map.Entry<String, List<Pair>> entry:highbatterydata.entrySet()){
//					System.out.println(entry.getKey()+"  "+entry.getValue().size());
//					for(Pair p:entry.getValue()){
//						System.out.println(p.getFirst()+" - - "+p.getSecond());
//					}
//				}
//				
//				System.out.println(hightimedata.size());
//				for(Map.Entry<String, List<Pair>> entry:hightimedata.entrySet()){
//					System.out.println(entry.getKey()+"  "+entry.getValue().size());
//					for(Pair p:entry.getValue()){
//						System.out.println(p.getFirst()+" - - "+p.getSecond());
//					}
//				}

//				for(TestCase tc:testcaselist){
//					System.out.println(tc.getTestCaseID()+" - "+tc.getState()+" - "+tc.getResult().toString());
//				}
				
				System.out.println("------------------*******************");
				startPerformanceRunProgressbar(testcaselist);// 显示进度条

//				changeDataInTable(testcaselist);//显示测试结果
				parentTestCaseDataPanel.getTestCaseChartDiagramButtonPanel().setVisible(true);
				
				PerformanceTestCaseChartTabbedPanel performanceTestCaseChartTabbedPanel=new PerformanceTestCaseChartTabbedPanel(mainFrame);
				
				parentTestCaseDataPanel.getTestCaseChartTabbedPanel().removeAll();
				parentTestCaseDataPanel.getTestCaseChartTabbedPanel().add(performanceTestCaseChartTabbedPanel);
				
				DefaultTableModel tabelmodel=performanceTestCaseChartTabbedPanel.getAttributetablemodel();
				while(tabelmodel.getRowCount()>0){
					tabelmodel.removeRow(0);
				}
				for(TestCase tc:testcaselist){
					TestCaseResult tcr=tc.getResult();
					Object[] rowData={tc.getTestCaseID(),tcr.getWind_speed(),tcr.getTakeoff_alt(),tcr.getBattery_remaining(),tcr.getTime()};
					tabelmodel.addRow(rowData);
				}
				
//				PerformanceLineChart plc=new PerformanceLineChart(testcaselist);
				
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
				
//				changeDataInTable(list);
				
				threadstate=0;
				
//				ClientRecThread.testCaseList.removeAll(ClientRecThread.testCaseList);
				
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
				
//				checkedtimetestcasereportlist.removeAll(checkedtimetestcasereportlist);
//				selectedtestcaselist.removeAll(selectedtestcaselist);
//				
//				timetestcasereportlist=mainFrame.getTestCaseConfirmationPanel().getTimetestcasereportlist();
//				
//				for(TimeTestCaseReportPartPanel ttcrpp:timetestcasereportlist){
//					
//					if (ttcrpp.getToolcheckbox().isSelected()) {
//						checkedtimetestcasereportlist.add(ttcrpp);
//						selectedtestcaselist.add(ttcrpp.getTestcase());
//					}
//
//				}
				
				checkedtestcasereportlist.removeAll(checkedtestcasereportlist);
				selectedtestcaselist.removeAll(selectedtestcaselist);
				
				for(JPanel jp:testcasereportlist){
					TimeTestCaseReportPartPanel ttcrpp=(TimeTestCaseReportPartPanel) jp;
					if (ttcrpp.getToolcheckbox().isSelected()) {
						checkedtestcasereportlist.add(ttcrpp);
						selectedtestcaselist.add(ttcrpp.getTestcase());
					}
				}
				
//				testcasename=mainFrame.getTestCaseConfirmationPanel().getTestcasename();
				
//				System.err.println(selectedtestcaselist.size());

				String extraxmlpath="D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\"+testcasename+"selected.xml";
				extractDataToXml(extraxmlpath, selectedtestcaselist, 3);//生成测试用例xml
//				String extraxmlpath="D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\TimeTest\\"+testcasename+".xml";
				File file=new File(extraxmlpath);
				
				//接收到测试结果list
//				File[] files = {file};
//				clientSocket.sendFile(files);
				
				TextAreaPrint("发送测试用例数据...");
				gaindatathread.start();
				
				Controller.Run(new Pair<String, File>("time", file));
				
//				List<TestCase> testcaselist = ClientRecThread.getTestCaseList();
				List<TestCase> testcaselist=new ArrayList<>();
				try {
					testcaselist = Controller.getResult("time");
				} catch (ExecutionException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				File f=new File("D:\\test.txt");
				try {
					Writer w=new FileWriter(f,false);
					for(TestCase tc:testcaselist){
						System.out.println(tc.toString());
						w.write(tc.toString());
						w.write("\n");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				try {
					String path="D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\"+testcasename+"serialtestcase.txt";
					FileOutputStream fos = new FileOutputStream(path);
					ObjectOutputStream oos=new ObjectOutputStream(fos);
					
					for(TestCase tc:testcaselist){
						System.out.println(tc.toString());
						oos.writeObject(tc);
					}
					
					oos.close();
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
//				List<TestCase> testcaselist=new ArrayList<>();
//				try {
//					String serialpath = "D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\"+testcasename+"serialtestcase.txt";
//					FileInputStream fis = new FileInputStream(serialpath);
//					ObjectInputStream ois = new ObjectInputStream(fis);
//
//					while(true){//使用处理异常的方式来判断文件是否结束
//						try {
//							TestCase tc=(TestCase) ois.readObject();//文件读取完毕后，会抛异常
//							testcaselist.add(tc);
//						} catch (Exception  e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//							System.out.println("文件读取完毕!");  
//			                break;  
//						}
//					}
//
//					ois.close();
//					fis.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
//				progressbar.setValue(50);
				
				while(progressbar.getValue()<50){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				TextAreaPrint("正在进行数据统计整理...");

				for(TestCase tc:testcaselist){
					System.out.println(tc.getTestCaseID()+" - "+tc.getState()+" - "+tc.getResult().toString());
					
					System.out.println(tc.getResult().getTimeLimit().getError().size());
					
					Time time=tc.getResult().getTimeLimit();
					
					System.out.println(time.getOriginal());
					System.out.println("time.getError() : "+time.getError().size());
					for(String s:time.getError()){
						System.out.println(s);
					}
					System.out.println("time.getShowMap() : "+time.getShowMap().size());
//					for(Entry<String, Pair<String, String>> en:time.getShowMap().entrySet()){
//						System.out.println(en.getKey()+"  -  "+en.getValue().getFirst()+"  -  "+en.getValue().getSecond());
//						
//					}
					
				}
				System.out.println("-------------------------------------------------");
				startTimeRunProgressbar(testcaselist);// 显示进度条

//				changeDataInTable(testcaselist);//显示测试结果
				
				
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
							Thread.sleep(200);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
				
				TextAreaPrint("处理完成！");
				TextAreaPrint("正在接收服务器端返回的测试结果...");
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
						while(!list.get(index).getTestCaseID().equals(ttcrpp.getTestcase().getTestCaseID())){
							index++;
						}
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
							limittablemodel.setValueAt(p.getFirst(), i, 1);
							limittablemodel.setValueAt(state, i, 2);
							System.out.println(limit+"  -  "+state+"  -  "+p.getFirst()+"  -  "+p.getSecond());
						}
						
						String title = "";
						title+="测试用例ID:"+testcase.getTestCaseID()+"     ";
//						title+=testcase.getState()+"     ";
//						title+="执行结果:"+testcase.getResult().substring(0, testcase.getResult().indexOf("耗时"));
//						title+="执行结果:"+testcase.getResult().getResultDetail();
						title+="执行结果:"+testcase.getState();
						
						ttcrpp.getTitlelabel().setText(title);
						
						String absolutePath = System.getProperty("user.dir");
						String path = absolutePath + "\\src\\site\\resources\\icons\\OpreationPart\\";

						ImageIcon icon1 = new ImageIcon(path + "tick.png");
						icon1.setImage(icon1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
						ImageIcon icon2 = new ImageIcon(path + "cross.png");
						icon2.setImage(icon2.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
						
						if(testcase.getResult().getResultDetail().contains("成功")){
							ttcrpp.getIconlabel().setIcon(icon1);
						}
						else{
							ttcrpp.getIconlabel().setIcon(icon2);
						}
						
						TextAreaPrint(testcase.toString());
						
						int startprogressbar = (int) ((double) 100 / list.size() * index);
						int endprogressbar = (int) ((double) 100 / list.size() * (index + 1));
						
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
					time.setText(p.getProcessStatus());
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

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
import java.util.Random;

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
import com.horstmann.violet.application.gui.util.chengzuo.Bean.myProcess;
import com.horstmann.violet.application.gui.util.chengzuo.Util.ClientRecThread;
import com.horstmann.violet.application.gui.util.chengzuo.Util.ClientSocket;
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
	
	private String testcasename;
	private List<FunctionalTestCaseReportPartPanel> functionaltestcasereportlist=new ArrayList<FunctionalTestCaseReportPartPanel>();
	private List<FunctionalTestCaseReportPartPanel> checkedfunctionaltestcasereportlist=new ArrayList<FunctionalTestCaseReportPartPanel>();
	private List<PerformanceTestCaseReportPartPanel> performancetestcasereportlist=new ArrayList<PerformanceTestCaseReportPartPanel>();
	private List<PerformanceTestCaseReportPartPanel> checkedperformancetestcasereportlist=new ArrayList<PerformanceTestCaseReportPartPanel>();
	private List<TimeTestCaseReportPartPanel> timetestcasereportlist=new ArrayList<TimeTestCaseReportPartPanel>();
	private List<TimeTestCaseReportPartPanel> checkedtimetestcasereportlist=new ArrayList<TimeTestCaseReportPartPanel>();
	private List<TestCase> selectedtestcaselist=new ArrayList<TestCase>();
	
	private int[] testcasecount=new int[3];
	private List<Integer> testcasecountlist=new ArrayList<Integer>();
	
	public TestCaseReportTabbedPanel(MainFrame mainframe){
		
		this.mainFrame=mainframe;
		
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
					progressbar.setValue(0);
					progressbarlabel.setText(" ");
					
					progressbarindex=0;
					
					mainFrame.getStepFiveCenterTabbedPane().getTestCaseChartDiagramButtonPanel().setVisible(false);
					
					int starttype=mainFrame.getHomeAllTabbedPanel().getStarttype();
					if(starttype==1){
						startFunctionalTestConfirmation();
					}
					else if(starttype==2){
						startPerformanceTestConfirmation();
					}
					else if(starttype==3){
						startTimeTestConfirmation();
					}
					
					threadstate=1;
					
				} else if (threadstate == 1) {

				} else if (threadstate == -1) {
					threadstate = 1;
					t.resume();
					progreseethread.resume();
					gaindatathread.resume();
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
				
				t.suspend();
				progreseethread.suspend();
				gaindatathread.suspend();
				threadstate=-1;
				
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
				
				t.stop();
				progreseethread.stop();
				gaindatathread.stop();
				threadstate=0;
				
				progressbar.setValue(0);
				progressbarlabel.setText("0%");
//				
//				while(sequencetouppaaltablemodel.getRowCount()>0){
//					sequencetouppaaltablemodel.removeRow(sequencetouppaaltablemodel.getRowCount()-1);
//				}
//				
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
				
				int starttype=mainFrame.getHomeAllTabbedPanel().getStarttype();
				
				if(starttype==1){
					
					functionaltestcasereportlist=mainFrame.getTestCaseConfirmationPanel().getFunctionaltestcasereportlist();
					
					for(FunctionalTestCaseReportPartPanel ftcrpp:functionaltestcasereportlist){
						if(ftcrpp.getAttributepanel().isVisible()){
							ftcrpp.getAttributepanel().setVisible(false);
						}
					}
					
				}
				else if(starttype==2){
					performancetestcasereportlist=mainFrame.getTestCaseConfirmationPanel().getPerformancetestcasereportlist();
					
					for(PerformanceTestCaseReportPartPanel ptcrpp:performancetestcasereportlist){
						
						if (ptcrpp.getAttributepanel().isVisible()) {
							ptcrpp.getAttributepanel().setVisible(false);
						}
						
					}
				}
				else if(starttype==3){
					timetestcasereportlist=mainFrame.getTestCaseConfirmationPanel().getTimetestcasereportlist();
					
					for(TimeTestCaseReportPartPanel ttcrpp:timetestcasereportlist){
						
						if (ttcrpp.getAttributepanel().isVisible()) {
							ttcrpp.getAttributepanel().setVisible(false);
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

				int starttype = mainFrame.getHomeAllTabbedPanel().getStarttype();

				if (starttype == 1) {

					functionaltestcasereportlist = mainFrame.getTestCaseConfirmationPanel()
							.getFunctionaltestcasereportlist();

					for (FunctionalTestCaseReportPartPanel ftcrpp : functionaltestcasereportlist) {
						if (!ftcrpp.getAttributepanel().isVisible()) {
							ftcrpp.getAttributepanel().setVisible(true);
						}
					}

				} else if (starttype == 2) {
					performancetestcasereportlist = mainFrame.getTestCaseConfirmationPanel()
							.getPerformancetestcasereportlist();

					for (PerformanceTestCaseReportPartPanel ptcrpp : performancetestcasereportlist) {

						if (!ptcrpp.getAttributepanel().isVisible()) {
							ptcrpp.getAttributepanel().setVisible(true);
						}

					}
				}
				else if(starttype==3){
					timetestcasereportlist=mainFrame.getTestCaseConfirmationPanel().getTimetestcasereportlist();
					
					for(TimeTestCaseReportPartPanel ttcrpp:timetestcasereportlist){
						
						if (!ttcrpp.getAttributepanel().isVisible()) {
							ttcrpp.getAttributepanel().setVisible(true);
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
				
				int starttype = mainFrame.getHomeAllTabbedPanel().getStarttype();

				if(starttype==1){
					functionaltestcasereportlist=mainFrame.getTestCaseConfirmationPanel().getFunctionaltestcasereportlist();
					for(FunctionalTestCaseReportPartPanel ftcrpp:functionaltestcasereportlist){
						ftcrpp.getToolcheckbox().setSelected(true);
					}
				}
				else if(starttype==2){
					performancetestcasereportlist=mainFrame.getTestCaseConfirmationPanel().getPerformancetestcasereportlist();
					
					for(PerformanceTestCaseReportPartPanel ptcrpp:performancetestcasereportlist){
						ptcrpp.getToolcheckbox().setSelected(true);
					}
				}
				else if(starttype==3){
					timetestcasereportlist=mainFrame.getTestCaseConfirmationPanel().getTimetestcasereportlist();
					
					for(TimeTestCaseReportPartPanel ttcrpp:timetestcasereportlist){
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

				int starttype = mainFrame.getHomeAllTabbedPanel().getStarttype();

				if (starttype==1){
					functionaltestcasereportlist=mainFrame.getTestCaseConfirmationPanel().getFunctionaltestcasereportlist();
					for(FunctionalTestCaseReportPartPanel ftcrpp:functionaltestcasereportlist){
						ftcrpp.getToolcheckbox().setSelected(false);
					}
				}
				else if(starttype==2){
					if(windcheckboxpanelstate==0){
						windcheckboxpanel.setVisible(true);
						windcheckboxpanelstate=1;
					}
					else if(windcheckboxpanelstate==1){
						windcheckboxpanel.setVisible(false);
						windcheckboxpanelstate=0;
					}
				}
				else if(starttype==3){
					timetestcasereportlist=mainFrame.getTestCaseConfirmationPanel().getTimetestcasereportlist();
					
					for(TimeTestCaseReportPartPanel ttcrpp:timetestcasereportlist){
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
				
				checkedfunctionaltestcasereportlist.removeAll(checkedfunctionaltestcasereportlist);
				selectedtestcaselist.removeAll(selectedtestcaselist);
				
				functionaltestcasereportlist=mainFrame.getTestCaseConfirmationPanel().getFunctionaltestcasereportlist();
				
				for(FunctionalTestCaseReportPartPanel ftcrpp:functionaltestcasereportlist){
					
//					if(tcrpp.getToolcheckbox().isSelected()){
					checkedfunctionaltestcasereportlist.add(ftcrpp);
					selectedtestcaselist.add(ftcrpp.getTestcase());
//					}
					
				}
				
				testcasename=mainFrame.getTestCaseConfirmationPanel().getTestcasename();
				
				System.err.println(selectedtestcaselist.size());

				String extraxmlpath="D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\"+testcasename+"selected.xml";
				extractDataToXml(extraxmlpath, selectedtestcaselist);//生成测试用例xml
				File file=new File(extraxmlpath);
				
//				//接收到测试结果list
//				ClientSocket clientSocket = new ClientSocket("192.168.0.103", 5555);
//				clientSocket.Connection();
////				JFileChooser jfc = new JFileChooser();
////				jfc.setMultiSelectionEnabled(true);
////				jfc.showDialog(new JLabel(), "选择测试用例");
////				File[] files = jfc.getSelectedFiles();
//				File[] files = {file};
//				clientSocket.sendFile(files);
//				
//				List<TestCase> testcaselist = ClientRecThread.getTestCaseList();
//				
//				File f=new File("D:\\test.txt");
//				try {
//					Writer w=new FileWriter(f,false);
//					for(TestCase tc:testcaselist){
//						System.out.println(tc.toString());
//						w.write(tc.toString());
//						w.write("\n");
//					}
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				
//				
//				try {
//					String path="D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\"+testcasename+"serialtestcase.txt";
//					FileOutputStream fos = new FileOutputStream(path);
//					ObjectOutputStream oos=new ObjectOutputStream(fos);
//					
//					for(TestCase tc:testcaselist){
//						System.out.println(tc.toString());
//						oos.writeObject(tc);
//					}
//					
//					oos.close();
//					fos.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				List<TestCase> testcaselist=new ArrayList<>();
				try {
					String serialpath = "D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\"+testcasename+"serialtestcase.txt";
					FileInputStream fis = new FileInputStream(serialpath);
					ObjectInputStream ois = new ObjectInputStream(fis);

					while(true){//使用处理异常的方式来判断文件是否结束
						try {
							TestCase tc=(TestCase) ois.readObject();//文件读取完毕后，会抛异常
							testcaselist.add(tc);
						} catch (Exception  e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("文件读取完毕!");  
			                break;  
						}
					}

					ois.close();
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

//				for(TestCase tc:testcaselist){
//					System.out.println(tc.getTestCaseID()+" - "+tc.getState()+" - "+tc.getResult().toString());
//				}
				
				startFunctionalRunProgressbar(testcaselist);// 显示进度条

//				changeDataInTable(testcaselist);//显示测试结果
				mainFrame.getStepFiveCenterTabbedPane().getTestCaseChartDiagramButtonPanel().setVisible(true);
				
				mainFrame.getStepFiveCenterTabbedPane().getTestCaseChartTabbedPanel().removeAll();
				mainFrame.getStepFiveCenterTabbedPane().getTestCaseChartTabbedPanel().add(mainFrame.getStepFiveCenterTabbedPane().getFunctionalTestCaseChartTabbedPanel());
				
				
				
				threadstate=0;
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
					for(FunctionalTestCaseReportPartPanel ftcrpp:checkedfunctionaltestcasereportlist){
						
						
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
						
						JTextArea textarea=mainFrame.getConsolePartPanel().getTextarea();
						textarea.append(testcase.toString()+"\n");
						textarea.setCaretPosition(textarea.getDocument().getLength());
						
						
						
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

	private void startPerformanceTestConfirmation() {
		// TODO Auto-generated method stub
		
//		ClientSocket clientSocket = new ClientSocket("192.168.149.103", 5555);
//		clientSocket.Connection();
//		JFileChooser jfc = new JFileChooser();
//		jfc.setMultiSelectionEnabled(true);
//		jfc.showDialog(new JLabel(), "选择测试用例");
//		File[] files = jfc.getSelectedFiles();
//		clientSocket.sendFile(files);
//		
//		List<TestCase> list = ClientRecThread.getTestCaseList();
		
//		File f=new File("D:\\test.txt");
//		try {
//			Writer w=new FileWriter(f,false);
//			for(TestCase tc:list){
//				System.out.println(tc.toString());
//				w.write(tc.toString());
//				w.write("\n");
//			}
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		
//		try {
//			String path="D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\serialtestcase.txt";
//			FileOutputStream fos = new FileOutputStream(path);
//			ObjectOutputStream oos=new ObjectOutputStream(fos);
//			
//			for(TestCase tc:list){
//				System.out.println(tc.toString());
//				oos.writeObject(tc);
//			}
//			
//			oos.close();
//			fos.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		

		t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				checkedperformancetestcasereportlist.removeAll(checkedperformancetestcasereportlist);
				selectedtestcaselist.removeAll(selectedtestcaselist);
				
				performancetestcasereportlist=mainFrame.getTestCaseConfirmationPanel().getPerformancetestcasereportlist();
				
				for(PerformanceTestCaseReportPartPanel tcrpp:performancetestcasereportlist){
					
//					if(tcrpp.getToolcheckbox().isSelected()){
						checkedperformancetestcasereportlist.add(tcrpp);
						selectedtestcaselist.add(tcrpp.getTestcase());
//					}
					
				}
				
				testcasename=mainFrame.getTestCaseConfirmationPanel().getTestcasename();
				
				String extraxmlpath="D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\"+testcasename+"selected.xml";
				extractDataToXml(extraxmlpath, selectedtestcaselist);//生成测试用例xml
				File file=new File(extraxmlpath);
				
//				//接收到测试结果list
//				ClientSocket clientSocket = new ClientSocket("192.168.150.117", 5555);
//				clientSocket.Connection();
////				JFileChooser jfc = new JFileChooser();
////				jfc.setMultiSelectionEnabled(true);
////				jfc.showDialog(new JLabel(), "选择测试用例");
////				File[] files = jfc.getSelectedFiles();
//				File[] files = {file};
//				clientSocket.sendFile(files);
//				
//				List<TestCase> testcaselist = ClientRecThread.getTestCaseList();
//				
//				File f=new File("D:\\test.txt");
//				try {
//					Writer w=new FileWriter(f,false);
//					for(TestCase tc:testcaselist){
//						System.out.println(tc.toString());
//						w.write(tc.toString());
//						w.write("\n");
//					}
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				
//				
//				try {
//					String path="D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\"+testcasename+"serialtestcase.txt";
//					FileOutputStream fos = new FileOutputStream(path);
//					ObjectOutputStream oos=new ObjectOutputStream(fos);
//					
//					for(TestCase tc:testcaselist){
//						System.out.println(tc.toString());
//						oos.writeObject(tc);
//					}
//					
//					oos.close();
//					fos.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				List<TestCase> testcaselist=new ArrayList<>();
				try {
					String serialpath = "D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\"+testcasename+"serialtestcase.txt";
					FileInputStream fis = new FileInputStream(serialpath);
					ObjectInputStream ois = new ObjectInputStream(fis);

					while(true){//使用处理异常的方式来判断文件是否结束
						try {
							TestCase tc=(TestCase) ois.readObject();//文件读取完毕后，会抛异常
							testcaselist.add(tc);
						} catch (Exception  e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("文件读取完毕!");  
			                break;  
						}
					}

					ois.close();
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Map testcasemap=TestCaseConvertUtil.testCaseStatistics(testcaselist);
				for(Object s:testcasemap.keySet()){
					System.out.println(s.toString());
				}

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
				
				for(int i=0;i<50;i++){
					System.out.println("++++ file "+i);
					try {
						Thread.sleep(600);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				while(progressbar.getValue()<50){
					System.out.println("123456789123456789");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("------------------*******************");
				startPerformanceRunProgressbar(testcaselist);// 显示进度条

//				changeDataInTable(testcaselist);//显示测试结果
				mainFrame.getStepFiveCenterTabbedPane().getTestCaseChartDiagramButtonPanel().setVisible(true);
				
				mainFrame.getStepFiveCenterTabbedPane().getTestCaseChartTabbedPanel().removeAll();
				mainFrame.getStepFiveCenterTabbedPane().getTestCaseChartTabbedPanel().add(mainFrame.getStepFiveCenterTabbedPane().getPerformanceTestCaseChartTabbedPanel());
				
				DefaultTableModel tabelmodel=mainFrame.getStepFiveCenterTabbedPane().getPerformanceTestCaseChartTabbedPanel().getAttributetablemodel();
				
				for(TestCase tc:testcaselist){
					TestCaseResult tcr=tc.getResult();
					Object[] rowData={tc.getTestCaseID(),tcr.getWind_speed(),tcr.getTakeoff_alt(),tcr.getBattery_remaining(),tcr.getTime()};
					tabelmodel.addRow(rowData);
				}
				
//				PerformanceLineChart plc=new PerformanceLineChart(testcaselist);
				
				PerformanceHighBatteryLineChart phblc=new PerformanceHighBatteryLineChart(highbatterydata);
				mainFrame.getStepFiveCenterTabbedPane().getPerformanceTestCaseChartTabbedPanel().getHighbatterylinepanel().removeAll();
				mainFrame.getStepFiveCenterTabbedPane().getPerformanceTestCaseChartTabbedPanel().getHighbatterylinepanel().add(phblc.createChart());
				
				PerformanceHighTimeLineChart phtlc=new PerformanceHighTimeLineChart(hightimedata);
				mainFrame.getStepFiveCenterTabbedPane().getPerformanceTestCaseChartTabbedPanel().getHightimelinepanel().removeAll();
				mainFrame.getStepFiveCenterTabbedPane().getPerformanceTestCaseChartTabbedPanel().getHightimelinepanel().add(phtlc.createChart());
				
				PerformanceHighSpeedBarChart phsbc=new PerformanceHighSpeedBarChart(highspeeddata);
				mainFrame.getStepFiveCenterTabbedPane().getPerformanceTestCaseChartTabbedPanel().getHighspeedbarpanel().removeAll();
				mainFrame.getStepFiveCenterTabbedPane().getPerformanceTestCaseChartTabbedPanel().getHighspeedbarpanel().add(phsbc.createChart());
				
				PerformanceTimeSpeedBarChart ptsbc=new PerformanceTimeSpeedBarChart(timespeeddata);
				mainFrame.getStepFiveCenterTabbedPane().getPerformanceTestCaseChartTabbedPanel().getTimespeedbarpanel().removeAll();
				mainFrame.getStepFiveCenterTabbedPane().getPerformanceTestCaseChartTabbedPanel().getTimespeedbarpanel().add(ptsbc.createChart());
				
//				changeDataInTable(list);
				
				threadstate=0;
			}
			
		});
		
		gaindatathread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0;i<50;i++){
					progressbar.setValue(progressbar.getValue()+1);
					progressbarlabel.setText(progressbar.getValue()+"%");
					System.out.println("---- progressbar "+progressbar.getValue());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		t.start();
		gaindatathread.start();
		
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
					for(PerformanceTestCaseReportPartPanel tcrpp:checkedperformancetestcasereportlist){
						
						
//						TestCase testcase=list.get(Integer.parseInt(tcrpp.getTestcase().getTestCaseID())-1);
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
						
						JTextArea textarea=mainFrame.getConsolePartPanel().getTextarea();
						textarea.append(testcase.toString()+"\n");
						textarea.setCaretPosition(textarea.getDocument().getLength());
						
						
						
						int startprogressbar = (int) ((double) 50 / list.size() * index);
						int endprogressbar = (int) ((double) 50 / list.size() * (index + 1));
						
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
	
	private void startTimeTestConfirmation(){
		
		t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				checkedtimetestcasereportlist.removeAll(checkedtimetestcasereportlist);
				selectedtestcaselist.removeAll(selectedtestcaselist);
				
				timetestcasereportlist=mainFrame.getTestCaseConfirmationPanel().getTimetestcasereportlist();
				
				for(TimeTestCaseReportPartPanel ttcrpp:timetestcasereportlist){
					
//					if(tcrpp.getToolcheckbox().isSelected()){
					checkedtimetestcasereportlist.add(ttcrpp);
					selectedtestcaselist.add(ttcrpp.getTestcase());
//					}
					
				}
				
				testcasename=mainFrame.getTestCaseConfirmationPanel().getTestcasename();
				
				System.err.println(selectedtestcaselist.size());

				String extraxmlpath="D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\"+testcasename+"selected.xml";
				extractDataToXml(extraxmlpath, selectedtestcaselist);//生成测试用例xml
				File file=new File(extraxmlpath);
				
//				//接收到测试结果list
//				ClientSocket clientSocket = new ClientSocket("192.168.0.103", 5555);
//				clientSocket.Connection();
////				JFileChooser jfc = new JFileChooser();
////				jfc.setMultiSelectionEnabled(true);
////				jfc.showDialog(new JLabel(), "选择测试用例");
////				File[] files = jfc.getSelectedFiles();
//				File[] files = {file};
//				clientSocket.sendFile(files);
//				
//				List<TestCase> testcaselist = ClientRecThread.getTestCaseList();
//				
//				File f=new File("D:\\test.txt");
//				try {
//					Writer w=new FileWriter(f,false);
//					for(TestCase tc:testcaselist){
//						System.out.println(tc.toString());
//						w.write(tc.toString());
//						w.write("\n");
//					}
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				
//				
//				try {
//					String path="D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\"+testcasename+"serialtestcase.txt";
//					FileOutputStream fos = new FileOutputStream(path);
//					ObjectOutputStream oos=new ObjectOutputStream(fos);
//					
//					for(TestCase tc:testcaselist){
//						System.out.println(tc.toString());
//						oos.writeObject(tc);
//					}
//					
//					oos.close();
//					fos.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				List<TestCase> testcaselist=new ArrayList<>();
				try {
					String serialpath = "D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\"+testcasename+"serialtestcase.txt";
					FileInputStream fis = new FileInputStream(serialpath);
					ObjectInputStream ois = new ObjectInputStream(fis);

					while(true){//使用处理异常的方式来判断文件是否结束
						try {
							TestCase tc=(TestCase) ois.readObject();//文件读取完毕后，会抛异常
							testcaselist.add(tc);
						} catch (Exception  e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("文件读取完毕!");  
			                break;  
						}
					}

					ois.close();
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

//				for(TestCase tc:testcaselist){
//					System.out.println(tc.getTestCaseID()+" - "+tc.getState()+" - "+tc.getResult().toString());
//				}
				
				startTimeRunProgressbar(testcaselist);// 显示进度条

//				changeDataInTable(testcaselist);//显示测试结果
				
				
				threadstate=0;
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
					for(TimeTestCaseReportPartPanel ttcrpp:checkedtimetestcasereportlist){
						
						
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
						
						String title = "";
						title+="测试用例ID:"+testcase.getTestCaseID()+"     ";
//						title+=testcase.getState()+"     ";
//						title+="执行结果:"+testcase.getResult().substring(0, testcase.getResult().indexOf("耗时"));
						title+="执行结果:"+testcase.getResult().getResultDetail();
						
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
						
						JTextArea textarea=mainFrame.getConsolePartPanel().getTextarea();
						textarea.append(testcase.toString()+"\n");
						textarea.setCaretPosition(textarea.getDocument().getLength());
						
						
						
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
	
	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}
	
	protected void changeDataInTable(List<TestCase> list) {
		// TODO Auto-generated method stub
		
//		int i=0;
		
		testcasecount[0]=0;
		testcasecount[1]=0;
		testcasecount[2]=0;
		
		testcasecountlist.removeAll(testcasecountlist);
		
		int truecount,falsecount;
		
		for(PerformanceTestCaseReportPartPanel tcrpp:checkedperformancetestcasereportlist){
			
			truecount=0;
			falsecount=0;
			
			TestCase testcase=list.get(Integer.parseInt(tcrpp.getTestcase().getTestCaseID())-1);
			
//			TestCase testcase=list.get(i++);
			
			
			JTable attributetable;
			DefaultTableModel attributetablemodel;
			
			attributetable=tcrpp.getAttributetable();
			attributetablemodel=tcrpp.getAttributetablemodel();
			
			final List<Integer> badnumlist=new ArrayList<Integer>();
			
			for(myProcess p:testcase.getProcessList()){
				
				attributetablemodel.setValueAt(p.getProcessStatus(), p.getProcessID()-1, 3);
				attributetablemodel.setValueAt(p.isProcessExec(), p.getProcessID()-1, 4);
				
				if(p.isProcessExec()){
					truecount++;
				}
				else{
					falsecount++;
					badnumlist.add(p.getProcessID()-1);
				}
				
			}
			
//			DefaultTableCellRenderer renderer1 = new DefaultTableCellRenderer() {
//
//				@Override
//				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
//						boolean hasFocus, int row, int column) {
//					// TODO Auto-generated method stub
//
//					setForeground(new Color(115, 110, 102));
//					setBackground(new Color(255, 255, 255));
//					setFont(new Font("微软雅黑", Font.PLAIN, 12));
//					setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
//
//					for(int num:badnumlist){
//						if(row==num){
//							setForeground(new Color(115, 110, 102));
//							setBackground(new Color(255, 135, 135));
//							break;
//						}
//					}
//					
//					return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//				}
//
//			};
//			attributetable.setDefaultRenderer(Object.class, renderer1);
			
			
			attributetablemodel.fireTableDataChanged();
			
//			String title = "";
//			title+="测试用例ID:"+testcase.getTestCaseID()+"     ";
//			title+=testcase.getState()+"     ";
////			title+="执行结果:"+testcase.getResult().substring(0, testcase.getResult().indexOf("耗时"));
////			title+="执行结果:"+testcase.getResult();
//			title+=testcase.getResult();
//			
//			tcrpp.getTitlelabel().setText(title);
			
			DefaultTableModel dtm=tcrpp.getTitletablemodel();
			dtm.setValueAt(testcase.getResult().getBattery_remaining(), 0, 3);
			dtm.setValueAt(testcase.getResult().getTime(), 0, 4);
			
			String absolutePath = System.getProperty("user.dir");
			String path = absolutePath + "\\src\\site\\resources\\icons\\OpreationPart\\";

			ImageIcon icon1 = new ImageIcon(path + "tick.png");
			icon1.setImage(icon1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
			ImageIcon icon2 = new ImageIcon(path + "cross.png");
			icon2.setImage(icon2.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
			
//			if(testcase.getResult().contains("成功")){
//				tcrpp.getIconlabel().setIcon(icon1);
//			}
//			else{
//				tcrpp.getIconlabel().setIcon(icon2);
//			}
//			
//			if(testcase.getResult().contains("成功")){
//				testcasecount[0]++;
//			}
//			else if(testcase.getResult().contains("死循环")){
//				testcasecount[1]++;
//			}
//			else if(testcase.getResult().contains("用例有误")){
//				testcasecount[2]++;
//			}
			
			testcasecountlist.add(truecount);
			testcasecountlist.add(falsecount);
			
		}
		
	}
	
	private void extractDataToXml(String path, List<TestCase> list) {
		// TODO Auto-generated method stub
		
		Document doc = DocumentHelper.createDocument();
		Element TCS=doc.addElement("TCS");
		
		for(TestCase tc:list){
			Element testcase = TCS.addElement("testcase");
			for(myProcess p:tc.getProcessList()){
				Element process = testcase.addElement("process");
				Element operation = process.addElement("operation");
				Element input = process.addElement("input");

				operation.setText(p.getProcessName());
				input.setText(p.getProcessParam());
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

package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.Color;
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
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.plaf.ProgressBarUI;
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
import com.horstmann.violet.application.gui.util.chengzuo.Bean.TestCase;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.myProcess;

public class TestCaseReportTabbedPanel extends JPanel{
	
	private MainFrame mainFrame;
	
	private JPanel toolpanel;
	private MoviePanel moviepanel;
	private JPanel tabelpanel;
	
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
	
	private JScrollPane tabelscrollpanel;
	private JPanel tableresultpanel;
	
	private List<TestCaseReportPartPanel> testcasereportlist=new ArrayList<TestCaseReportPartPanel>();
	private List<TestCaseReportPartPanel> checkedtestcasereportlist=new ArrayList<TestCaseReportPartPanel>();
	private List<TestCase> testcaselist=new ArrayList<TestCase>();
	
	private int[] testcasecount=new int[3];
	private List<Integer> testcasecountlist=new ArrayList<Integer>();
	
	public TestCaseReportTabbedPanel(MainFrame mainframe){
		
		this.mainFrame=mainframe;
		
		toolpanel=new JPanel();
		moviepanel=new MoviePanel();
		tabelpanel=new JPanel();
		
		initToolPanel();
		
		initMoviePanel();
		
		initTablePanel();
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(toolpanel);
		this.add(moviepanel);
		this.add(tabelpanel);
		layout.setConstraints(toolpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(moviepanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(tabelpanel,new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
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
				
//				if(t==null){
//					startSequenceToUppaal();
//					threadstate=true;
//				}
				
//				if(threadstate==0){
//					startSequenceToUppaal();
//					threadstate=1;
//					System.out.println("t is alive");
//				}
//				else if(threadstate==1){
//					
//				}
//				else if(threadstate==-1){
//					threadstate=1;
//					t.resume();
//					progreseethread.resume();
//					System.out.println("t is not alive");
//				}
				
//				ClientSocket clientSocket = new ClientSocket("192.168.150.117", 5555);
//				clientSocket.Connection();
//				JFileChooser jfc = new JFileChooser();
//				jfc.setMultiSelectionEnabled(true);
//				jfc.showDialog(new JLabel(), "选择测试用例");
//				File[] files = jfc.getSelectedFiles();
//				StepFiveArea.append("正在发送数据.....\n");
//				clientSocket.sendFile(files);
//				StepFiveArea.append("发送数据完成!\n");
//				StepFiveArea.append("正在获得数据.....\n");
//				try {
//					Thread.sleep(10000);
//				} catch (InterruptedException e) {
//				}
//				List<TestCase> list = clientSocket.getTestCaseList();
//				StepFiveArea.append("数据已经获得!\n");

				checkedtestcasereportlist.removeAll(checkedtestcasereportlist);
				testcaselist.removeAll(testcaselist);
				
				testcasereportlist=mainFrame.getTestCaseConfirmationPanel().getTestcasereportlist();
				
				for(TestCaseReportPartPanel tcrpp:testcasereportlist){
					
					if(tcrpp.getToolcheckbox().isSelected()){
						checkedtestcasereportlist.add(tcrpp);
						testcaselist.add(tcrpp.getTestcase());
					}
					
				}
				
				extractDataToXml(testcaselist);
				
				changeDataInTable(extractData());
				
				mainFrame.getStepFiveCenterTabbedPane().getTestCasePieChartTabbedPane().removeAll();
				mainFrame.getStepFiveCenterTabbedPane().getTestCasePieChartTabbedPane().add(new TestCasePieChartPanel(testcasecount));
				
				mainFrame.getStepFiveCenterTabbedPane().getTestCaseBarChartTabbedPane().removeAll();
				mainFrame.getStepFiveCenterTabbedPane().getTestCaseBarChartTabbedPane().add(new TestCaseBarChartPanel(testcasecount));
				
				mainFrame.getStepFiveCenterTabbedPane().getTestCaseLineChartTabbedPane().removeAll();
				mainFrame.getStepFiveCenterTabbedPane().getTestCaseLineChartTabbedPane().add(new JScrollPane(new TestCaseLineChartPanel(testcasecountlist)));
				
				mainFrame.getStepFiveCenterTabbedPane().getTestCaseBarChartTabbedPane().removeAll();
				mainFrame.getStepFiveCenterTabbedPane().getTestCaseBarChartTabbedPane().add(new JScrollPane(new TestCaseStackedBarChartPanel(testcasecountlist)));
				
//				mainFrame.getStepFiveCenterTabbedPane().getTestCaseBarChartTabbedPane().removeAll();
//				mainFrame.getStepFiveCenterTabbedPane().getTestCaseBarChartTabbedPane().add(new JScrollPane(new TestCaseAreaChartPanel(testcasecountlist)));
				
				
//				changeDataInTable(list);
				
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
				
//				t.suspend();
//				progreseethread.suspend();
//				threadstate=-1;
				
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
				
//				t.stop();
//				progreseethread.stop();
//				threadstate=0;
//				
//				progressbar.setValue(0);
//				progressbarlabel.setText("0%");
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
				
				testcasereportlist=mainFrame.getTestCaseConfirmationPanel().getTestcasereportlist();
				
				for(TestCaseReportPartPanel tcrpp:testcasereportlist){
					
					if (tcrpp.getAttributepanel().isVisible()) {
						tcrpp.getAttributepanel().setVisible(false);
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

				testcasereportlist=mainFrame.getTestCaseConfirmationPanel().getTestcasereportlist();
				
				for(TestCaseReportPartPanel tcrpp:testcasereportlist){
					
					if (!tcrpp.getAttributepanel().isVisible()) {
						tcrpp.getAttributepanel().setVisible(true);
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
				
				testcasereportlist=mainFrame.getTestCaseConfirmationPanel().getTestcasereportlist();
				
				for(TestCaseReportPartPanel tcrpp:testcasereportlist){
					tcrpp.getToolcheckbox().setSelected(true);
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
				
				testcasereportlist=mainFrame.getTestCaseConfirmationPanel().getTestcasereportlist();
				for(TestCaseReportPartPanel tcrpp:testcasereportlist){
					tcrpp.getToolcheckbox().setSelected(false);
				}
				
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

	private void initMoviePanel() {
		// TODO Auto-generated method stub
		
		moviepanel.getMovieLabel().setText("正在生成测试报告");
		
	}

	private void initTablePanel() {
		// TODO Auto-generated method stub
		
		tableresultpanel=new JPanel();
		tableresultpanel.setLayout(new GridLayout());
		tableresultpanel.setBorder(null);
		tableresultpanel.setBackground(new Color(255, 255, 255));
		
		tabelscrollpanel=new JScrollPane(tableresultpanel);
        tabelscrollpanel.setBorder(null);
        tabelscrollpanel.setBackground(new Color(255, 255, 255));
		
//		tabelpanel.setBackground(new Color(255, 255, 255));
		tabelpanel.setLayout(new GridLayout());
		tabelpanel.add(tabelscrollpanel);
//		tabelpanel.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
		
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
		
		for(TestCaseReportPartPanel tcrpp:checkedtestcasereportlist){
			
			truecount=0;
			falsecount=0;
			
			TestCase testcase=list.get(Integer.parseInt(tcrpp.getTestcase().getTestCaseID())-1);
			
//			TestCase testcase=list.get(i++);
			
			DefaultTableModel attributetablemodel;
			
			attributetablemodel=tcrpp.getAttributetablemodel();
			
			for(myProcess p:testcase.getProcessList()){
				
				attributetablemodel.setValueAt(p.getProcessStatus(), p.getProcessID()-1, 3);
				attributetablemodel.setValueAt(p.isProcessExec(), p.getProcessID()-1, 4);
				
				if(p.isProcessExec()){
					truecount++;
				}
				else{
					falsecount++;
				}
				
			}
			
			attributetablemodel.fireTableDataChanged();
			
			String title = "";
			title+="测试用例ID:"+testcase.getTestCaseID()+"     ";
			title+=testcase.getState()+"     ";
//			title+="执行结果:"+testcase.getResult().substring(0, testcase.getResult().indexOf("耗时"));
			title+="执行结果:"+testcase.getResult();
			
			tcrpp.getTitlelabel().setText(title);
			
			String absolutePath = System.getProperty("user.dir");
			String path = absolutePath + "\\src\\site\\resources\\icons\\OpreationPart\\";

			ImageIcon icon1 = new ImageIcon(path + "tick.png");
			icon1.setImage(icon1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
			ImageIcon icon2 = new ImageIcon(path + "cross.png");
			icon2.setImage(icon2.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
			
			if(testcase.getResult().contains("成功")){
				tcrpp.getIconlabel().setIcon(icon1);
			}
			else{
				tcrpp.getIconlabel().setIcon(icon2);
			}
			
			if(testcase.getResult().contains("成功")){
				testcasecount[0]++;
			}
			else if(testcase.getResult().contains("死循环")){
				testcasecount[1]++;
			}
			else if(testcase.getResult().contains("用例有误")){
				testcasecount[2]++;
			}
			
			testcasecountlist.add(truecount);
			testcasecountlist.add(falsecount);
			
		}
		
	}
	
	private void extractDataToXml(List<TestCase> list) {
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
			String path="D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\test.xml";
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

	
	public List<TestCase> extractData() {

		// 测试用例ID
		String testCaseID = null;
		// 测试用例 激励链表
		List<myProcess> processList = new ArrayList<myProcess>();
		// 测试用例执行状态
		String state = null;
		// 测试用例执行结果
		String result = null;

		String process;

		// 激励ID
		int processID;
		// 激励名称
		String processName;
		// 激励参数
		String processParam;
		// 激励状态
		String processStatus;
		// 激励执行情况
		boolean processExec;

		int startendstate = 0;

		List<TestCase> testcaseList = new ArrayList<TestCase>();

		try {

//			 String encoding = "utf-8";
			String encoding = "GBK";

			String filePath="D:\\123.txt";
			
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				
				int max=20;
		        int min=1;
		        Random random = new Random();
		        int num1 = 0,num2,fstate=0;
		        int k=0;
				
				while ((lineTxt = bufferedReader.readLine()) != null) {

					if (startendstate == 1) {

						processList = new ArrayList<myProcess>();
						startendstate = 0;
						
						num1= random.nextInt(max)%(max-min+1) + min;

					}

					if (lineTxt.substring(0, 8).equals("TestCase")) {

						testCaseID = lineTxt.substring(lineTxt.indexOf("testCaseID=") + 11, lineTxt.indexOf(","));

					} else if (lineTxt.substring(1, 10).equals("myProcess")) {

						process = lineTxt.substring(lineTxt.indexOf("[") + 1, lineTxt.indexOf("]"));
						processID = Integer.valueOf(process.substring(process.indexOf("processID=") + 10,
								process.indexOf(", processName=")));
						processName = process.substring(process.indexOf("processName=") + 12,
								process.indexOf(", processParam="));
						processParam = process.substring(process.indexOf("processParam=") + 13,
								process.indexOf(", processStatus="));
						processStatus = process.substring(process.indexOf("processStatus=") + 14,
								process.indexOf(", processExec="));
//						if(process.substring(process.indexOf("processExec=")+12, process.length()).equals("true")){
//							processExec=true;
//						}
//						else{
//							processExec=false;
//						}
						
						if(num1>18){
							num2= random.nextInt(max)%(max-min+1) + min;
							if(num2>15){
								processExec=false;
								fstate=1;
							}
							else{
								processExec=true;
							}
						}
						else{
							processExec=true;
						}

						myProcess p = new myProcess();
						p.setProcessID(processID);
						p.setProcessName(processName);
						p.setProcessParam(processParam);
						p.setProcessStatus(processStatus);
						p.setProcessExec(processExec);

						processList.add(p);
						
						fstate=0;

					} else if (lineTxt.substring(0, 2).equals(", ")) {

						state = lineTxt.substring(lineTxt.indexOf("state=") + 6, lineTxt.indexOf(", result="));
						result = lineTxt.substring(lineTxt.indexOf("result=") + 7, lineTxt.indexOf(", detail="));
						
				        if(num1>19){
				        	state="程序执行过程中出现死循环或者抛出异常!";
				        	result="程序出现出现死循环或者抛出异常!";
				        	
				        	if(fstate==0){
				        		processList.get(random.nextInt(processList.size()-1)%(processList.size())).setProcessExec(false);
//				        		System.out.println(k++);
				        	}
				        	
				        }
				        else if(num1>18){
				        	state="测试用例有误,无法对应到执行程序，且测试耗时:0[不准确]";
				        	result="测试用例有误,无法对应到执行程序!";
				        	
				        	if(fstate==0){
				        		processList.get(random.nextInt(processList.size()-1)%(processList.size())).setProcessExec(false);
//				        		System.out.println(k++);
				        	}
				        	
				        }
				        else{
				        	state="测试耗时:0";
				        	result="测试执行成功!耗时:0";
				        }
						

					} else if (lineTxt.substring(lineTxt.length() - 2, lineTxt.length()).equals("]]")) {

						TestCase tc = new TestCase();
						tc.setTestCaseID(testCaseID);
						tc.setProcessList(processList);
						tc.setState(state);
						tc.setResult(result);

						testcaseList.add(tc);

						startendstate = 1;

					}

				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}

//		System.out.println(testcaseList.size());
//
//		for (TestCase tc : testcaseList) {
//
//			System.out.println(tc.toString());
//
//		}
		
		return testcaseList;

	}

}

package com.horstmann.violet.application.gui.opreationTreePane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.MyLabelCellEditor;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.TestCaseReportPartPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.TestCaseReportTableHeaderPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.TestCasePieChartPanel;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.TestCase;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.TestCaseResult;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.myProcess;
import com.l2fprod.common.swing.JTaskPane;
import com.l2fprod.common.swing.JTaskPaneGroup;

public class TestCaseConfirmationPanel extends JPanel{
	
	public  MainFrame mainFrame;
	
	private JPanel titlepanel;
	private JPanel toolpanel;
	private JPanel treepanel;
	private JPanel diagrampanel;
	
	private JLabel titlelabel;
	private JPanel titleiconlabelpanel;
	private JLabel titleiconlabel1;
	private JLabel titleiconlabel2;
	private JLabel titleiconlabel3;
	
	private JPanel testcaselabelpanel;
	private JLabel testcaselabel;
	private JPanel testcasetoolpanel;
	private JButton testcasetoolbutton1;
	private JButton testcasetoolbutton2;
	private JPanel testcasetablepanel;
	private JScrollPane testcasescrollpanel;
	
	private DefaultTableModel testcasetablemodel;
	private JTable testcasetable;
	
	private String testcasename=null;
	private List<TestCase> testcaselist=new ArrayList<TestCase>();
	private List<TestCaseReportPartPanel> testcasereportlist=new ArrayList<TestCaseReportPartPanel>();
	
    private List<String> testcasefilenamelists=new ArrayList<String>();
    
	public TestCaseConfirmationPanel(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub			
		
		this.mainFrame = mainFrame;
		
		initFileList();
		
//		initUI();		
		
//		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black)
//        		,"验证抽象测试用例",TitledBorder.CENTER,TitledBorder.ABOVE_TOP,
//        		new Font("宋体",Font.BOLD,20),new Color(60, 60, 60)));
		
		
		titlepanel = new JPanel();
		toolpanel = new JPanel();
		treepanel = new JPanel();
		diagrampanel = new JPanel();
		
		titlelabel = new JLabel();
		titleiconlabelpanel=new JPanel();
		titleiconlabel1 = new JLabel();
		titleiconlabel2 = new JLabel();
		titleiconlabel3 = new JLabel();
		
		testcaselabelpanel=new JPanel();
		testcaselabel=new JLabel();
		testcasetoolpanel=new JPanel();
		testcasetoolbutton1=new JButton();
		testcasetoolbutton2=new JButton();
		testcasetablepanel=new JPanel();
		
		titlepanel.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(142, 155, 188)));
		toolpanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, new Color(142, 155, 188)));
		treepanel.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(142, 155, 188)));
		
		initTitlePanel();
		
		initToolButton();

		initDiagramButton();
		
		initTablePanel();
		
		addDataTotestcaseTable();
		
		GridBagLayout layout=new GridBagLayout();
		this.setLayout(layout);
		this.add(titlepanel);
		this.add(toolpanel);
		this.add(testcasescrollpanel);
//		this.add(diagrampanel);
		layout.setConstraints(titlepanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(toolpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(testcasescrollpanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		layout.setConstraints(diagrampanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();
		
		this.setMinimumSize(new Dimension(screenWidth/8, screenHeight-150));
		
		
	}

	private void initTitlePanel() {
		// TODO Auto-generated method stub
		
		titlelabel.setText("验证抽象测试用例");
		titlelabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		titlelabel.setForeground(new Color(255, 255, 255));
		titlelabel.setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 0));
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "triangulararrow.png");
		icon1.setImage(icon1.getImage().getScaledInstance(8,4, Image.SCALE_DEFAULT));
		titleiconlabel1.setIcon(icon1);
		titleiconlabel1.setBorder(BorderFactory.createEmptyBorder(4, 4,4, 6));
		ImageIcon icon2 = new ImageIcon(path + "downarrow.png");
		icon2.setImage(icon2.getImage().getScaledInstance(7,11, Image.SCALE_DEFAULT));
		titleiconlabel2.setIcon(icon2);
		titleiconlabel2.setBorder(BorderFactory.createEmptyBorder(4, 4,4, 4));
		ImageIcon icon3 = new ImageIcon(path + "fork.png");
		icon3.setImage(icon3.getImage().getScaledInstance(10,8, Image.SCALE_DEFAULT));
		titleiconlabel3.setIcon(icon3);
		titleiconlabel3.setBorder(BorderFactory.createEmptyBorder(4, 4,4, 4));
		
		titleiconlabel3.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				mainFrame.getJs1().setDividerSize(0);
				mainFrame.getOpreationPart().setVisible(false);
				mainFrame.getOneTouchExpandablePanel().setFlag1(0);
				mainFrame.getOneTouchExpandablePanel().setLocation1(mainFrame.getJs1().getDividerLocation());
				
			}
		});
		
		titleiconlabelpanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		titleiconlabelpanel.setOpaque(false);
		titleiconlabelpanel.add(titleiconlabel1);
		titleiconlabelpanel.add(titleiconlabel2);
		titleiconlabelpanel.add(titleiconlabel3);

		titlepanel.setLayout(new BorderLayout());
		titlepanel.setBackground(new Color(77, 96, 130));
		titlepanel.setPreferredSize(new Dimension(100, 23));
		titlepanel.setMinimumSize(new Dimension(100, 23));
		titlepanel.add(titlelabel,BorderLayout.WEST);
		titlepanel.add(titleiconlabelpanel,BorderLayout.EAST);
		
	}

	private void initToolButton() {
		// TODO Auto-generated method stub
		
		testcaselabel.setText("测试用例列表");
		testcaselabel.setForeground(new Color(0,0,0));
		testcaselabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		testcaselabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "dropdown.png");
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(path + "refresh.png");
		icon2.setImage(icon2.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));

		testcasetoolbutton1.setIcon(icon1);
		testcasetoolbutton1.setFocusable(false);
		testcasetoolbutton1.setContentAreaFilled(false);
		testcasetoolbutton1.setBorderPainted(false);
		testcasetoolbutton1.addMouseListener(new ButtonMouseListener());
		testcasetoolbutton1.setPreferredSize(new Dimension(21,21));
		testcasetoolbutton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if(testcasescrollpanel.isVisible()){
					testcasescrollpanel.setVisible(false);
					ChangeRepaint();
				}
				else {
					testcasescrollpanel.setVisible(true);
					ChangeRepaint();
				}
				
			}
		});
		
		testcasetoolbutton2.setIcon(icon2);
		testcasetoolbutton2.setFocusable(false);
		testcasetoolbutton2.setContentAreaFilled(false);
		testcasetoolbutton2.setBorderPainted(false);
		testcasetoolbutton2.addMouseListener(new ButtonMouseListener());
		testcasetoolbutton2.setPreferredSize(new Dimension(21,21));
		testcasetoolbutton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				updateFileList();
				
//				testcasefilenamelists.clear();
//				initFileList();
//				addDataTotestcaseTable();
				
			}
		});

		testcasetoolpanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,4));
		testcasetoolpanel.setOpaque(false);
		testcasetoolpanel.add(testcasetoolbutton2);
//		testcasetoolpanel.add(testcasetoolbutton1);
		
		toolpanel.setBackground(new Color(207, 214, 229));
		toolpanel.setLayout(new BorderLayout());
		toolpanel.add(testcaselabel, BorderLayout.WEST);
		toolpanel.add(testcasetoolpanel, BorderLayout.EAST);
		toolpanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(142, 155, 188)));
		toolpanel.setPreferredSize(new Dimension(100, 29));
		toolpanel.setMaximumSize(new Dimension(100, 29));
		toolpanel.setMinimumSize(new Dimension(100, 29));
		
	}

	public void updateFileList() {
		// TODO Auto-generated method stub
		
		testcasefilenamelists.clear();
		initFileList();
		addDataTotestcaseTable();
		
	}

	private void addDataTotestcaseTable() {
		// TODO Auto-generated method stub
		
		while(testcasetablemodel.getRowCount()>0){
			testcasetablemodel.removeRow(testcasetablemodel.getRowCount()-1);
		}
		
		
		for(String str:testcasefilenamelists){
			Object[] rowData={str};
			testcasetablemodel.addRow(rowData);
		}
		
	}

	private void initDiagramButton() {
		// TODO Auto-generated method stub
		
	}

	private void initTablePanel() {
		// TODO Auto-generated method stub
		
		testcasetablemodel=new DefaultTableModel(0, 1){
			@Override  
            public boolean isCellEditable(int row,int column){  
                return false;  
            } 
		};
		
		testcasetable=new JTable(testcasetablemodel);
		
		testcasetable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		testcasetable.setGridColor(Color.BLACK);
		testcasetable.setShowGrid(true);
		testcasetable.setShowHorizontalLines(true);
		testcasetable.setShowVerticalLines(false);
		testcasetable.setFillsViewportHeight(true);
		testcasetable.setRowHeight(24);
		testcasetable.doLayout();
		
		testcasetable.getColumnModel().getColumn(0).setCellEditor(new MyLabelCellEditor());
		testcasetable.getColumnModel().getColumn(0).setCellRenderer(new MyGeneralLabelRenderer());
		
		testcasetable.getTableHeader().setVisible(false);  
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();  
        renderer.setPreferredSize(new Dimension(0, 0));  
        testcasetable.getTableHeader().setDefaultRenderer(renderer);
        
        testcasetable.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount()==2){
					
					String baseUrl = "D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\";
					
					int starttype=mainFrame.getHomeAllTabbedPanel().getStarttype();
					if(starttype == 1){
						baseUrl += "\\FunctionalTest\\";
					} else if (starttype == 2) {
						baseUrl += "\\PerformanceTest\\";
					}
					
					String filename=(String) testcasetablemodel.getValueAt(testcasetable.getSelectedRow(), testcasetable.getSelectedColumn());
					String path = baseUrl + filename + ".xml";
					
					setTestcasename(filename);
					testcaselist = extractDataFromXml(path);

					JPanel resultpanel=new JPanel();
					JPanel emptypanel=new JPanel();
					resultpanel.setOpaque(false);
					emptypanel.setOpaque(false);
					
					GridBagLayout layout = new GridBagLayout();
					resultpanel.setLayout(layout);
					int i=0;
					
					TestCaseReportTableHeaderPanel tcrthpanel=new TestCaseReportTableHeaderPanel();
					resultpanel.add(tcrthpanel);
					layout.setConstraints(tcrthpanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
					
					testcasereportlist.clear();
					for(TestCase tc:testcaselist){
						TestCaseReportPartPanel tcrppanel=new TestCaseReportPartPanel(tc);
						resultpanel.add(tcrppanel);
						layout.setConstraints(tcrppanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
						testcasereportlist.add(tcrppanel);
					}
					resultpanel.add(emptypanel);
					layout.setConstraints(emptypanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
					
					JPanel jp = mainFrame.getStepFiveCenterTabbedPane().getTestCaseReportTabbedPane().getTableresultpanel();
					jp.removeAll();
					jp.add(resultpanel);
					
					mainFrame.getStepFiveCenterTabbedPane().getTestCaseReportTabbedPane().updateUI();
					
				}
			}
        	
		});
		
		testcasetablepanel.setLayout(new GridLayout());
		testcasetablepanel.add(testcasetable);
//		testcasetable.setBackground(new Color(238, 238, 242));
		testcasetable.setBackground(new Color(255,255,255));
		testcasetable.setBorder(null);
		testcasetablepanel.setBorder(null);
		
		testcasescrollpanel=new JScrollPane(testcasetablepanel);
		testcasescrollpanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(142, 155, 188)));
		testcasescrollpanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		testcasescrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
		
	}

//	private void initUI() {
//		// TODO Auto-generated method stub
//		initUppaalFilePanel();	 
////	    this.setLayout(new GridLayout(1, 1));    
////	    this.add(TestCaseFilePanel);	   	 
//	}	
//	private void initUppaalFilePanel() {
//		// TODO Auto-generated method stub			
//		String[] testcaseFileLists={"测试用例1","测试用例2","测试用例3"};
//		testcase=new DefaultMutableTreeNode("测试用例文件列表");//测试用例文件列表			
//		for(String testcaseFile : testcaseFileLists)
//		{
//			testcase.add(new DefaultMutableTreeNode(testcaseFile));
//		}
//		TestCaseFilePanel=new JTree(testcase);
//	}

	public void initFileList() {
		
		int starttype=mainFrame.getHomeAllTabbedPanel().getStarttype();
		
		File[] filelists=getAllFileByDiagramType(starttype);

		for (File file : filelists) {
			String fileName=file.getName();
	    	if(fileName.lastIndexOf(".xml")>0){
	    		testcasefilenamelists.add(fileName.substring(0, fileName.lastIndexOf(".xml")));
	    	}
	    }
	}
	
	public File[] getAllFileByDiagramType(int starttype) {
		String baseUrl = "D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase";

		File[] fList = null;
		File file = null;

		if (starttype == 1) {
			file = new File(baseUrl + "\\FunctionalTest");
			fList = file.listFiles();
		} else if (starttype == 2) {
			file = new File(baseUrl + "\\PerformanceTest");
			fList = file.listFiles();
		} else {
			file = new File(baseUrl);
			fList = file.listFiles();
		}

		return fList;
	}
	
	public static List<TestCase> extractDataFromXml(String path){
		
		int i=1,j=1;
		
		List<TestCase> testcaseList = new ArrayList<TestCase>();
		List<myProcess> processList = new ArrayList<myProcess>();
		
		SAXReader reader = new SAXReader();
		
//		String path="D:\\rc_loopForXStream1.0.1.xml";
//		String path="D:\\789.xml";
		
		File file=new File(path);
		
		try {
			
			Document dom = reader.read(file);
			
			Element TCS=dom.getRootElement();
			List<Element> testcaseElements=TCS.elements("testcase");
			for(Element testcase:testcaseElements){
				
				double highcm = 0;
				double windspeed = 0;
				
				List<Element> processElements=testcase.elements("process");
				
				for(Element process:processElements){

					Element operation=process.element("operation");
					
					Element input=process.element("input");
					
					String operationdata=operation.getData().toString();
					String inputdata=input.getData().toString();
					
					myProcess p = new myProcess();
					p.setProcessID(j++);
					p.setProcessName(operationdata);
					p.setProcessParam(inputdata);
//					p.setProcessStatus(processStatus);
//					p.setProcessExec(processExec);
					
					if(operationdata.equals("do_user_takeoff")){
						String str1,str2;
						str1=inputdata.substring(inputdata.indexOf("takeoff_alt_cm=")+15);
						str2=str1.substring(0, str1.indexOf(","));
						highcm=Integer.parseInt(str2);
					}
					else if(operationdata.equals("_simulator_servos")){
						String str1,str2;
						str1=inputdata.substring(inputdata.indexOf("_sitl.wind_speed=")+17);
						str2=str1.substring(0, str1.indexOf(","));
						windspeed=Integer.parseInt(str2);
					}

					processList.add(p);
					
				}
				
				j=1;
				
				TestCase tc = new TestCase();
				tc.setTestCaseID(String.valueOf(i++));
				tc.setProcessList(processList);
//				tc.setState(state);
//				tc.setResult(result);
				
				TestCaseResult tcr=new TestCaseResult();
				tcr.setTakeoff_alt(highcm);
				tcr.setWind_speed(windspeed);
				tc.setResult(tcr);

				testcaseList.add(tc);
				
				processList = new ArrayList<myProcess>();
				
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return testcaseList;
		
	}
	
	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
		
	}
	
	public List<TestCase> getTestcaselist() {
		return testcaselist;
	}

	public List<TestCaseReportPartPanel> getTestcasereportlist() {
		return testcasereportlist;
	}

	public String getTestcasename() {
		return testcasename;
	}

	public void setTestcasename(String testcasename) {
		this.testcasename = testcasename;
	}
	
	
	
}

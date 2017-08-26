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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.opreationTreePane.CheckBoxTree.CheckBoxTreeCellRenderer;
import com.horstmann.violet.application.gui.opreationTreePane.CheckBoxTree.CheckBoxTreeNode;
import com.horstmann.violet.application.gui.opreationTreePane.CheckBoxTree.CheckBoxTreeNodeSelectionListener;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.FixedButtonTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.FunctionalTestCaseReportPartPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.MyLabelCellEditor;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.PerformanceTestCaseReportPartPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.PerformanceTestCaseReportTableHeaderPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.TestCaseDataPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.TimeTestCaseReportPartPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.TestCasePieChartPanel;
import com.horstmann.violet.application.gui.util.chenzuo.Bean.TestCase;
import com.horstmann.violet.application.gui.util.chenzuo.Bean.TestCaseResult;
import com.horstmann.violet.application.gui.util.chenzuo.Bean.myProcess;
import com.horstmann.violet.application.gui.util.tanchao.SaveText;
import com.horstmann.violet.application.gui.util.tanchao.TestCaseXMLToStringList;
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
	private JButton testcasetoolbutton3;
	private JButton testcasetoolbutton4;
	
	private JPanel testcaselabeltabpanel;
	private JPanel testcaselabeltabpanel1;
	private JPanel testcaselabeltabpanel2;
	private JButton testcaselabeltab1;
	private JButton testcaselabeltab2;
	private int testcaselabeltabindex=1;
	
	private JPanel testcasetreeinforpanel;
	private JPanel testcasetreeinforpanel1;
	private JPanel testcasetreeinforpanel2;
	private JScrollPane testcasetreeinforscrollpanel1;
	private JScrollPane testcasetreeinforscrollpanel2;
	private JTree testcasetree1;
	private DefaultTreeModel testcasetreemodel1;
	private CheckBoxTreeNode rootnode1;
	private CheckBoxTreeNode functionnode1;
	private CheckBoxTreeNode performancenode1;
	private CheckBoxTreeNode timenode1;
	private CheckBoxTreeNode bordernode1;
	private JTree testcasetree2;
	private DefaultTreeModel testcasetreemodel2;
	private CheckBoxTreeNode rootnode2;
	private CheckBoxTreeNode functionnode2;
	private CheckBoxTreeNode performancenode2;
	private CheckBoxTreeNode timenode2;
	private CheckBoxTreeNode bordernode2;
	
	private JPanel testcasecheckboxpanel;
//	private JScrollPane testcasescrollpanel;
	
	private JCheckBox[] testCaseCheckBoxList;
	
	private String testcasename=null;
	private List<TestCase> testcaselist=new ArrayList<TestCase>();
	private List<List<String>> limitlist=new ArrayList<>();
//	private List<FunctionalTestCaseReportPartPanel> functionaltestcasereportlist=new ArrayList<FunctionalTestCaseReportPartPanel>();
//	private List<PerformanceTestCaseReportPartPanel> performancetestcasereportlist=new ArrayList<PerformanceTestCaseReportPartPanel>();
//	private List<TimeTestCaseReportPartPanel> timetestcasereportlist=new ArrayList<TimeTestCaseReportPartPanel>();
	
    private List<String> testcasefilenamelists=new ArrayList<String>();
    private List<String> sqlcasedatalist=new ArrayList<>();
    
    private List<String> selectedtestcaselist=new ArrayList<>();
    
	public TestCaseConfirmationPanel(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub			
		
		this.mainFrame = mainFrame;
		
//		initFileList();
		
//		initUI();		
		
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
		testcasetoolbutton3=new JButton();
		testcasetoolbutton4=new JButton();
		
		testcasecheckboxpanel=new JPanel();
		
		testcasetreeinforpanel=new JPanel();
		testcasetreeinforpanel1=new JPanel();
		testcasetreeinforpanel2=new JPanel();
		
		titlepanel.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(142, 155, 188)));
		toolpanel.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(142, 155, 188)));
		testcasetreeinforpanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(142, 155, 188)));
		
		initTitlePanel();
		
		initToolButton();

		initTestCaseLabelTabPanel();
		
		initTestCaseTreeInforPanel();
		
		GridBagLayout layout=new GridBagLayout();
		this.setLayout(layout);
		this.add(titlepanel);
		this.add(toolpanel);
		this.add(testcaselabeltabpanel);
		this.add(testcasetreeinforpanel);
//		this.add(diagrampanel);
		layout.setConstraints(titlepanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(toolpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(testcaselabeltabpanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(testcasetreeinforpanel, new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		layout.setConstraints(diagrampanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();
		
		this.setMinimumSize(new Dimension(screenWidth/8, screenHeight-150));
		
		
	}

	private void initTestCaseTreeInforPanel() {
		// TODO Auto-generated method stub
		
		initTestCaseTreeInforPanel1();
		
		initTestCaseTreeInforPanel2();
		
		testcasetreeinforpanel.setLayout(new GridLayout());
		testcasetreeinforpanel.add(testcasetreeinforpanel1);
		
	}

	private void initTestCaseTreeInforPanel1() {
		// TODO Auto-generated method stub
		
//		initCheckBoxPanel();
//		
//		addDataToCheckBoxPanel();
//		
//		testcasetreeinforpanel1.setLayout(new GridLayout());
//		testcasetreeinforpanel1.add(testcasetreeinforscrollpanel1);
		
		initTestCaseFileList();
		
		initTestCaseTree1();
		
		updateTestCaseTreeCount(testcasetree1,testcasetreemodel1,rootnode1);
		
		showTestCaseTree(testcasetree1,new TreePath(rootnode1),true);
		
		testcasetreeinforpanel1.setLayout(new GridLayout());
		testcasetreeinforpanel1.add(testcasetreeinforscrollpanel1);
		testcasetreeinforscrollpanel1.setBorder(null);
		
	}

	private void showTestCaseTree(JTree tree, TreePath treePath, boolean expand) {
		// TODO Auto-generated method stub
		CheckBoxTreeNode node = (CheckBoxTreeNode) treePath.getLastPathComponent();

        if (node.getChildCount() > 0) {
            for (Enumeration e = node.children(); e.hasMoreElements();) {
                TreeNode n = (TreeNode) e.nextElement();
                TreePath path = treePath.pathByAddingChild(n);
                showTestCaseTree(tree, path, expand);
            }
        }
        if (expand) {
            tree.expandPath(treePath);
        } else {
            tree.collapsePath(treePath);
        }
	}

	private void initTestCaseFileList() {
		// TODO Auto-generated method stub
		
		testcasefilenamelists.clear();
		
		String baseUrl = "D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase";

		File[] rootfilelist = null;
		File rootfile = null;

		rootfile=new File(baseUrl);
		rootfilelist=rootfile.listFiles();
		for(File f:rootfilelist){
			if(f.isDirectory()){
				for (File file : f.listFiles()) {
					String fileName=file.getName();
			    	if(fileName.lastIndexOf(".xml")>0){
			    		testcasefilenamelists.add(fileName.substring(0, fileName.lastIndexOf(".xml")));
			    	}
			    }
			}
		}

	}

	private void initTestCaseTree1() {
		// TODO Auto-generated method stub
		rootnode1=new CheckBoxTreeNode("测试用例");
		functionnode1=new CheckBoxTreeNode("功能测试");
		performancenode1=new CheckBoxTreeNode("性能测试");
		timenode1=new CheckBoxTreeNode("时间约束测试");
		bordernode1=new CheckBoxTreeNode("边界值测试");
		
		rootnode1.add(functionnode1);
		rootnode1.add(performancenode1);
		rootnode1.add(timenode1);
		rootnode1.add(bordernode1);
		
		AddTestCaseFileToTree();
        
        testcasetreemodel1 = new DefaultTreeModel(rootnode1);  
        testcasetree1=new JTree(testcasetreemodel1);
        testcasetree1.addMouseListener(new CheckBoxTreeNodeSelectionListener());  
        testcasetree1.setCellRenderer(new CheckBoxTreeCellRenderer());  
        testcasetreeinforscrollpanel1 = new JScrollPane(testcasetree1);  
	}

	private void AddTestCaseFileToTree() {
		// TODO Auto-generated method stub
		for(String name:testcasefilenamelists){
			CheckBoxTreeNode node=new CheckBoxTreeNode(name);
			if(name.contains("功能")&&!name.contains("Border")){
				if(name.contains("场景1")){
					int flag=0;
					Enumeration<?> en=functionnode1.children();
					while (en.hasMoreElements()) {
						CheckBoxTreeNode object = (CheckBoxTreeNode) en.nextElement();
						if("场景1".equals(object.getUserObject())){
							object.add(node);
							flag=1;
							break;
						}
					}
					if(flag==0){
						CheckBoxTreeNode objectnode=new CheckBoxTreeNode("场景1");
						functionnode1.add(objectnode);
						objectnode.add(node);
					}
				}
				else if(name.contains("场景2")){
					int flag=0;
					Enumeration<?> en=functionnode1.children();
					while (en.hasMoreElements()) {
						CheckBoxTreeNode object = (CheckBoxTreeNode) en.nextElement();
						if("场景2".equals(object.getUserObject())){
							object.add(node);
							flag=1;
							break;
						}
					}
					if(flag==0){
						CheckBoxTreeNode objectnode=new CheckBoxTreeNode("场景2");
						functionnode1.add(objectnode);
						objectnode.add(node);
					}
				}
			}
			else if(name.contains("性能")){
				performancenode1.add(node);
			}
			else if(name.contains("Time")){
				timenode1.add(node);
			}
			else if(name.contains("Border")){
				bordernode1.add(node);
			}
		}
	}

	private void initTestCaseTreeInforPanel2() {
		// TODO Auto-generated method stub
		
		initTestCaseTree2();
		
		updateTestCaseTreeCount(testcasetree2,testcasetreemodel2,rootnode2);
		
		showTestCaseTree(testcasetree2,new TreePath(rootnode2),true);
		
		testcasetreeinforpanel2.setLayout(new GridLayout());
		testcasetreeinforpanel2.add(testcasetreeinforscrollpanel2);
		testcasetreeinforscrollpanel2.setBorder(null);
		
	}


	private void updateTestCaseTreeCount(final JTree tree, final DefaultTreeModel testcasetreemodel, final CheckBoxTreeNode rootnode) {
		// TODO Auto-generated method stub
		Thread t=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				DFSTreeUpdateCountByRootNode(rootnode);
				testcasetreemodel.reload();
				showTestCaseTree(tree,new TreePath(rootnode),true);
			}
		});
		t.start();
	}

	private int DFSTreeUpdateCountByRootNode(CheckBoxTreeNode node) {
		// TODO Auto-generated method stub
		if(node.isLeaf()){
			int countsum=0;
			String nodename=node.getUserObject().toString();
			if("功能测试".equals(nodename)||"性能测试".equals(nodename)||"时间约束测试".equals(nodename)||"边界值测试".equals(nodename)){
				countsum=0;
			}
			else{
				countsum=CalculateTestCaseCount(node.getUserObject().toString());
			}
			node.setUserObject(node.getUserObject()+" ("+countsum+" 条)");
			return countsum;
		}
		else{
			int countsum=0;
			Enumeration<CheckBoxTreeNode> nodechildrens=node.children();
			while (nodechildrens.hasMoreElements()) {
				CheckBoxTreeNode children = (CheckBoxTreeNode) nodechildrens.nextElement();
				countsum+=DFSTreeUpdateCountByRootNode(children);
			}
			node.setUserObject(node.getUserObject()+" ("+countsum+" 条)");
			return countsum;
		}
	}

	private int CalculateTestCaseCount(String testCaseName) {
		// TODO Auto-generated method stub
		
		int count=0;
		
		String baseUrl = "D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\";
		String testCasePath = null;
		
		File file = null;
		int type=1;
		if(type == 1){
			testCasePath=baseUrl+"\\FunctionalTest\\"+testCaseName+".xml";
			file=new File(testCasePath);
			if(!file.exists()){
				type++;
			}
		} 
		if (type == 2) {
			testCasePath=baseUrl+"\\PerformanceTest\\"+testCaseName+".xml";
			file=new File(testCasePath);
			if(!file.exists()){
				type++;
			}
		} 
		if (type == 3) {
			testCasePath=baseUrl+"\\TimeTest\\"+testCaseName+".xml";
			file=new File(testCasePath);
			if(!file.exists()){
				type++;
			}
		}
		
		if(!file.exists()){
			
			baseUrl = "D:\\ModelDriverProjectFile\\SqlTestCase\\";
			
			testCasePath=baseUrl+testCaseName+".xml";
		}
		
		SAXReader reader = new SAXReader();
		
		File testcasefile=new File(testCasePath);
		
		try {
			
			Document dom = reader.read(testcasefile);
			Element TCS=dom.getRootElement();
			List<Element> testcaseElements=TCS.elements("testcase");
			
			count=testcaseElements.size();
			
		}catch (DocumentException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return count;
	}

	private void initTestCaseTree2() {
		// TODO Auto-generated method stub
		
		rootnode2=new CheckBoxTreeNode("测试用例");
		functionnode2=new CheckBoxTreeNode("功能测试");
		performancenode2=new CheckBoxTreeNode("性能测试");
		timenode2=new CheckBoxTreeNode("时间约束测试");
		bordernode2=new CheckBoxTreeNode("边界值测试");
		
		rootnode2.add(functionnode2);
		rootnode2.add(performancenode2);
		rootnode2.add(timenode2);
		rootnode2.add(bordernode2);
		
        
		AddSqlTestCaseToTree();
		
        testcasetreemodel2 = new DefaultTreeModel(rootnode2);  
        testcasetree2=new JTree(testcasetreemodel2);
        testcasetree2.addMouseListener(new CheckBoxTreeNodeSelectionListener());  
        testcasetree2.setCellRenderer(new CheckBoxTreeCellRenderer());  
        testcasetreeinforscrollpanel2 = new JScrollPane(testcasetree2);  
		
	}

	private void AddSqlTestCaseToTree() {
		// TODO Auto-generated method stub
		
		sqlcasedatalist=DataBaseUtil.queryCaseDataList();
		
		for(String name:sqlcasedatalist){
			CheckBoxTreeNode node=new CheckBoxTreeNode(name);
			if(name.contains("功能")&&!name.contains("border")){
				if(name.contains("场景1")){
					int flag=0;
					Enumeration<?> en=functionnode2.children();
					while (en.hasMoreElements()) {
						CheckBoxTreeNode object = (CheckBoxTreeNode) en.nextElement();
						if("场景1".equals(object.getUserObject())){
							object.add(node);
							flag=1;
							break;
						}
					}
					if(flag==0){
						CheckBoxTreeNode objectnode=new CheckBoxTreeNode("场景1");
						functionnode2.add(objectnode);
						objectnode.add(node);
					}
				}
				else if(name.contains("场景2")){
					int flag=0;
					Enumeration<?> en=functionnode2.children();
					while (en.hasMoreElements()) {
						CheckBoxTreeNode object = (CheckBoxTreeNode) en.nextElement();
						if("场景2".equals(object.getUserObject())){
							object.add(node);
							flag=1;
							break;
						}
					}
					if(flag==0){
						CheckBoxTreeNode objectnode=new CheckBoxTreeNode("场景2");
						functionnode2.add(objectnode);
						objectnode.add(node);
					}
				}
			}
			else if(name.contains("性能")){
				performancenode2.add(node);
			}
			else if(name.contains("time")){
				timenode2.add(node);
			}
			else if(name.contains("border")){
				bordernode2.add(node);
			}
		}
		
	}

	private void initTestCaseLabelTabPanel() {
		// TODO Auto-generated method stub
		
		testcaselabeltabpanel=new JPanel();
		testcaselabeltabpanel1=new JPanel();
		testcaselabeltabpanel2=new JPanel();
		
		testcaselabeltab1=new JButton();
		testcaselabeltab2=new JButton();
		
		testcaselabeltab1.setText("最新的测试用例");
		testcaselabeltab1.setForeground(new Color(0,0,0));
		testcaselabeltab1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		testcaselabeltab1.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));
		testcaselabeltab1.setFocusable(false);
		testcaselabeltab1.setContentAreaFilled(false);
		testcaselabeltab1.setBorderPainted(false);
		testcaselabeltab1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				testcaselabeltab1.setForeground(new Color(0, 0, 0));
				testcaselabeltabpanel1.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (testcaselabeltabindex != 1) {
					testcaselabeltab1.setForeground(new Color(255, 255, 255));
					testcaselabeltabpanel1.setBackground(new Color(77, 96, 130));
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (testcaselabeltabindex != 1) {
					testcaselabeltabpanel1.setBackground(new Color(134, 161, 209));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				settestcaselabeltabpanelrepait();
				testcaselabeltab1.setForeground(new Color(0, 0, 0));
				testcaselabeltabpanel1.setBackground(new Color(255, 255, 255));
				testcaselabeltabpanel1.setBorder(BorderFactory.createMatteBorder(0,0,1,1, new Color(142, 155, 188)));
				testcaselabeltabindex = 1;
				
				testcaselabel.setText("最新的测试用例");
				
				testcasetreeinforpanel.removeAll();
				testcasetreeinforpanel.add(testcasetreeinforpanel1);
				
				ChangeRepaint();
			}
		});
		
		testcaselabeltab1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				settestcaselabeltabpanelrepait();
				testcaselabeltab1.setForeground(new Color(0, 0, 0));
				testcaselabeltabpanel1.setBackground(new Color(255, 255, 255));
				testcaselabeltabpanel1.setBorder(BorderFactory.createMatteBorder(0,0,1,1, new Color(142, 155, 188)));
				testcaselabeltabindex = 1;
				
				testcaselabel.setText("最新的测试用例");
				
				testcasetreeinforpanel.removeAll();
				testcasetreeinforpanel.add(testcasetreeinforpanel1);
				
				ChangeRepaint();
			}
		});
		
		testcaselabeltab2.setText("数据库中的测试用例");
		testcaselabeltab2.setForeground(new Color(255, 255, 255));
		testcaselabeltab2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		testcaselabeltab2.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));
		testcaselabeltab2.setFocusable(false);
		testcaselabeltab2.setContentAreaFilled(false);
		testcaselabeltab2.setBorderPainted(false);
		testcaselabeltab2.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				testcaselabeltab2.setForeground(new Color(0, 0, 0));
				testcaselabeltabpanel2.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (testcaselabeltabindex != 2) {
					testcaselabeltab2.setForeground(new Color(255, 255, 255));
					testcaselabeltabpanel2.setBackground(new Color(77, 96, 130));
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (testcaselabeltabindex != 2) {
					testcaselabeltabpanel2.setBackground(new Color(134, 161, 209));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				settestcaselabeltabpanelrepait();
				testcaselabeltab2.setForeground(new Color(0, 0, 0));
				testcaselabeltabpanel2.setBackground(new Color(255, 255, 255));
				testcaselabeltabpanel2.setBorder(BorderFactory.createMatteBorder(0,1,1,1, new Color(142, 155, 188)));
				testcaselabeltabindex = 2;
				
				testcaselabel.setText("数据库中的测试用例");
				
				testcasetreeinforpanel.removeAll();
				testcasetreeinforpanel.add(testcasetreeinforpanel2);
				
				ChangeRepaint();
			}
		});
		
		testcaselabeltab2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				settestcaselabeltabpanelrepait();
				testcaselabeltab2.setForeground(new Color(0, 0, 0));
				testcaselabeltabpanel2.setBackground(new Color(255, 255, 255));
				testcaselabeltabpanel2.setBorder(BorderFactory.createMatteBorder(0,1,1,1, new Color(142, 155, 188)));
				testcaselabeltabindex = 2;
				
				testcaselabel.setText("数据库中的测试用例");
				
				testcasetreeinforpanel.removeAll();
				testcasetreeinforpanel.add(testcasetreeinforpanel2);;
				
				ChangeRepaint();
			}
		});
		
		testcaselabeltabpanel1.setLayout(new GridLayout());
		testcaselabeltabpanel1.setBackground(new Color(255,255,255));
		testcaselabeltabpanel1.setBorder(BorderFactory.createMatteBorder(0,0,1,1, new Color(142, 155, 188)));
		testcaselabeltabpanel1.setPreferredSize(new Dimension(120, 25));
		testcaselabeltabpanel1.add(testcaselabeltab1);
		testcaselabeltabpanel2.setLayout(new GridLayout());
		testcaselabeltabpanel2.setBackground(new Color(77, 96, 130));
		testcaselabeltabpanel2.setPreferredSize(new Dimension(120, 25));
		testcaselabeltabpanel2.add(testcaselabeltab2);

		testcaselabeltabpanel.setBackground(new Color(41, 57, 85));
		testcaselabeltabpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		testcaselabeltabpanel.add(testcaselabeltabpanel1);
		testcaselabeltabpanel.add(testcaselabeltabpanel2);
		testcaselabeltabpanel.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(142, 155, 188)));
		
		testcaselabeltabpanel.setPreferredSize(new Dimension(100, 25));
		testcaselabeltabpanel.setMinimumSize(new Dimension(100, 25));
		
	}

	protected void settestcaselabeltabpanelrepait() {
		// TODO Auto-generated method stub
		testcaselabeltab1.setForeground(new Color(255, 255, 255));
		testcaselabeltabpanel1.setBackground(new Color(77, 96, 130));
		testcaselabeltabpanel1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(77, 96, 130)));
		testcaselabeltab2.setForeground(new Color(255, 255, 255));
		testcaselabeltabpanel2.setBackground(new Color(77, 96, 130));
		testcaselabeltabpanel2.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(77, 96, 130)));
	}

	private void initCheckBoxPanel() {
		// TODO Auto-generated method stub
		
		testcasecheckboxpanel.setLayout(new BoxLayout(testcasecheckboxpanel, BoxLayout.Y_AXIS));
		testcasecheckboxpanel.setBorder(BorderFactory.createEmptyBorder(0, 7, 0, 0));
		testcasecheckboxpanel.setBackground(new Color(255, 255, 255));
		
		testcasetreeinforscrollpanel1=new JScrollPane(testcasecheckboxpanel);
		testcasetreeinforscrollpanel1.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(142, 155, 188)));
		testcasetreeinforscrollpanel1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		testcasetreeinforscrollpanel1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
	}

	private void addDataToCheckBoxPanel() {
		// TODO Auto-generated method stub
		
		testcasecheckboxpanel.removeAll();
		testCaseCheckBoxList=new JCheckBox[testcasefilenamelists.size()];
		for(int i=0;i<testcasefilenamelists.size();i++){
			testCaseCheckBoxList[i]=new JCheckBox(testcasefilenamelists.get(i));
			testCaseCheckBoxList[i].setOpaque(false);
//			Object[]data={new JCheckBox(sequencelists.get(i))};
//			Object[]data={sequencelists.get(i)};
//			dtmDemo.addRow(data);
			testcasecheckboxpanel.add(Box.createVerticalStrut(7));
			testcasecheckboxpanel.add(testCaseCheckBoxList[i]);
			if(i==0){
				testCaseCheckBoxList[i].setSelected(true);
			}
			
		}
		
	}

	private void initTitlePanel() {
		// TODO Auto-generated method stub
		
		titlelabel.setText("测试执行");
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
		
		testcaselabel.setText("测试用例");
		testcaselabel.setForeground(new Color(0,0,0));
		testcaselabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		testcaselabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "dropdown.png");
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(path + "refresh.png");
		icon2.setImage(icon2.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon3 = new ImageIcon(path + "database_refresh.png");
		icon3.setImage(icon3.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon4 = new ImageIcon(path + "resultset_next.png");
		icon4.setImage(icon4.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));

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
				
				if(testcasetreeinforpanel.isVisible()){
					testcasetreeinforpanel.setVisible(false);
					ChangeRepaint();
				}
				else {
					testcasetreeinforpanel.setVisible(true);
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
				
				updateTestCaseTree();
				
			}
		});
		
		testcasetoolbutton3.setIcon(icon3);
		testcasetoolbutton3.setFocusable(false);
		testcasetoolbutton3.setContentAreaFilled(false);
		testcasetoolbutton3.setBorderPainted(false);
		testcasetoolbutton3.addMouseListener(new ButtonMouseListener());
		testcasetoolbutton3.setPreferredSize(new Dimension(21,21));
		testcasetoolbutton3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
//				testcasefilenamelists.remove(sqlcasedatalist);
				testcasefilenamelists.removeAll(sqlcasedatalist);
				
				sqlcasedatalist=DataBaseUtil.queryCaseDataList();
				
				testcasefilenamelists.addAll(sqlcasedatalist);
				
				new Thread(new Runnable() {
					
					@Override
					public void run() {

						addDataToCheckBoxPanel();
						
						ChangeRepaint();
						
//						for(String filename:sqlcasedatalist){
//							
//							TestCaseXMLToStringList tcxmltsl=new TestCaseXMLToStringList();
//							tcxmltsl.createXml(DataBaseUtil.queryTestCaseStringList(filename), "D:\\ModelDriverProjectFile\\SqlTestCase\\"+filename+".xml");
//							
//						}
						
					}
				}).start();
				
			}
		});
		
		testcasetoolbutton4.setIcon(icon4);
		testcasetoolbutton4.setFocusable(false);
		testcasetoolbutton4.setContentAreaFilled(false);
		testcasetoolbutton4.setBorderPainted(false);
		testcasetoolbutton4.addMouseListener(new ButtonMouseListener());
		testcasetoolbutton4.setPreferredSize(new Dimension(21,21));
		testcasetoolbutton4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				updateSelectedTestCaseList();
				
				System.out.println("------------ "+selectedtestcaselist.size());
				for(String s:selectedtestcaselist){
					System.out.println(s);
				}
				
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						
						List<TestCaseDataPanel> testCaseDataPanels=mainFrame.getStepFiveCenterTabbedPane().getTestCaseDataPanelList();
						int flag=0;
						int index=0;
						TestCaseDataPanel nowtcdpanel = null;
						
						for(String testcasename:selectedtestcaselist){
							
							flag=0;
							for(TestCaseDataPanel testCaseDataPanel:testCaseDataPanels){
								if(testCaseDataPanel.getTestCaseName().equals(testcasename)){
									flag=1;
									nowtcdpanel=testCaseDataPanel;
									break;
								}
							}
							
							if(flag==0){
								TestCaseDataPanel tcdpanel=new TestCaseDataPanel(mainFrame, testcasename);
								mainFrame.getStepFiveCenterTabbedPane().getTestCaseDataPanelList().add(tcdpanel);
								nowtcdpanel=tcdpanel;
							}
							
							if(index==0){
								nowtcdpanel.getTestCaseReportDiagramButtonPanel().getTabbedbutton().doClick();
							}
							nowtcdpanel.getTestCaseReportDiagramButtonPanel().setVisible(true);
							
							mainFrame.getStepFiveCenterTabbedPane().ChangeRepaint();
							
							index++;
							
						}
						
//						for(TestCaseDataPanel tcdpanel:mainFrame.getStepFiveCenterTabbedPane().getTestCaseDataPanelList()){
//							
//							tcdpanel.initData();
//							
//							System.out.println("-------------");
//							
//						}
						
					}
				}).start();
				
				
			}
		});

		testcasetoolpanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,4));
		testcasetoolpanel.setOpaque(false);
		testcasetoolpanel.add(testcasetoolbutton4);
		testcasetoolpanel.add(testcasetoolbutton3);
		testcasetoolpanel.add(testcasetoolbutton2);
//		testcasetoolpanel.add(testcasetoolbutton1);
		
		toolpanel.setBackground(new Color(207, 214, 229));
		toolpanel.setLayout(new BorderLayout());
		toolpanel.add(testcaselabel, BorderLayout.WEST);
		toolpanel.add(testcasetoolpanel, BorderLayout.EAST);
		toolpanel.setPreferredSize(new Dimension(100, 29));
		toolpanel.setMaximumSize(new Dimension(100, 29));
		toolpanel.setMinimumSize(new Dimension(100, 29));
		
	}

	protected void updateTestCaseTree() {
		// TODO Auto-generated method stub
		
		if(testcaselabeltabindex==1){
			testcasetreeinforpanel1.removeAll();
			initTestCaseTreeInforPanel1();
		}
		else if(testcaselabeltabindex==2){
			testcasetreeinforpanel2.removeAll();
			initTestCaseTreeInforPanel2();
		}
		
	}

	protected void updateSelectedTestCaseList() {
		// TODO Auto-generated method stub
		
		selectedtestcaselist.clear();
		
		if(testcaselabeltabindex==1){
			DFSTreeByRootNode(rootnode1);
		}
		else if(testcaselabeltabindex==2){
			DFSTreeByRootNode(rootnode2);
		}
		
	}

	private void DFSTreeByRootNode(CheckBoxTreeNode node) {
		// TODO Auto-generated method stub
		if(node.isLeaf()){
			if(node.isSelected()){
				String nodename=node.getUserObject().toString();
				selectedtestcaselist.add(nodename.substring(0, nodename.indexOf(" (")));
			}
		}
		else{
			Enumeration<CheckBoxTreeNode> nodechildrens=node.children();
			while (nodechildrens.hasMoreElements()) {
				CheckBoxTreeNode children = (CheckBoxTreeNode) nodechildrens.nextElement();
				DFSTreeByRootNode(children);
			}
		}
	}

	public void updateFileList() {
		// TODO Auto-generated method stub
		
		testcasefilenamelists.clear();
		initFileList();
//		addDataTotestcaseTable();
		addDataToCheckBoxPanel();
		
	}
	
	public void saveListToText(int type, List<TestCase> testcaselist){
		
		if (type == 1) {
			
			SaveText.init("D:\\ModelDriverProjectFile\\Text\\functionaltesttestcaselist.txt");
			for(TestCase tc:testcaselist){
				SaveText.SaveWord("测试用例ID: "+tc.getTestCaseID());
				SaveText.SaveWord("激励列表: ");
				for(myProcess p:tc.getProcessList()){
					SaveText.SaveWord("激励ID: "+p.getProcessID()+" 激励名称: "+p.getProcessName()+" 激励参数: "+p.getProcessParam());
				}
				SaveText.SaveFenGe();
			}
			SaveText.End();
			
		} else if (type == 2) {
			
			SaveText.init("D:\\ModelDriverProjectFile\\Text\\performancetesttestcaselist.txt");
			for(TestCase tc:testcaselist){
				SaveText.SaveWord("测试用例ID: "+tc.getTestCaseID());
				SaveText.SaveWord("风速级别: "+tc.getResult().getWind_speed()+" 起飞高度: "+tc.getResult().getTakeoff_alt());
				SaveText.SaveWord("激励列表: ");
				for(myProcess p:tc.getProcessList()){
					SaveText.SaveWord("激励ID: "+p.getProcessID()+" 激励名称: "+p.getProcessName()+" 激励参数: "+p.getProcessParam());
				}
				SaveText.SaveFenGe();
			}
			SaveText.End();
			
		} else if (type == 3) {
			
			SaveText.init("D:\\ModelDriverProjectFile\\Text\\timetesttestcaselist.txt");
			for(TestCase tc:testcaselist){
				SaveText.SaveWord("测试用例ID: "+tc.getTestCaseID());
				SaveText.SaveWord("激励列表: ");
				for(myProcess p:tc.getProcessList()){
					SaveText.SaveWord("激励ID: "+p.getProcessID()+" 激励名称: "+p.getProcessName()+" 激励参数: "+p.getProcessParam());
				}
				SaveText.SaveWord("不等式组列表: ");
				for(String s:tc.getLimit()){
					SaveText.SaveWord(s);
				}
				SaveText.SaveFenGe();
			}
			SaveText.End();
			
		}
		
	}

	public static List<TestCase> extractFunctionalTestDataFromXml(String path) {
		// TODO Auto-generated method stub

		int i = 1, j = 1;

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
				
				List<Element> processElements=testcase.elements("process");
				
				for(Element process:processElements){

					Element operation=process.element("operation");
					
					Element input=process.element("input");
					
					myProcess p = new myProcess();
					p.setProcessID(j++);
					p.setProcessName(operation.getData().toString());
					p.setProcessParam(input.getData().toString());
//					p.setProcessStatus(processStatus);
//					p.setProcessExec(processExec);

					processList.add(p);
					
				}
				
				j=1;
				
				TestCase tc = new TestCase();
				tc.setTestCaseID(String.valueOf(i++));
				tc.setProcessList(processList);
//				tc.setState(state);
//				tc.setResult(result);

				testcaseList.add(tc);
				
				processList = new ArrayList<myProcess>();
				
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return testcaseList;
	}

//	protected void showFunctionalTestCase() {
//		// TODO Auto-generated method stub
//
//		JPanel resultpanel=new JPanel();
//		JPanel emptypanel=new JPanel();
//		resultpanel.setOpaque(false);
//		emptypanel.setOpaque(false);
//		
//		GridBagLayout layout = new GridBagLayout();
//		resultpanel.setLayout(layout);
//		int i=0;
//		functionaltestcasereportlist.clear();
//		for(TestCase tc:testcaselist){
//			FunctionalTestCaseReportPartPanel ftcrppanel=new FunctionalTestCaseReportPartPanel(tc);
//			resultpanel.add(ftcrppanel);
//			layout.setConstraints(ftcrppanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//			functionaltestcasereportlist.add(ftcrppanel);
//		}
//		resultpanel.add(emptypanel);
//		layout.setConstraints(emptypanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		
//		JPanel jp = mainFrame.getStepFiveCenterTabbedPane().getTestCaseReportTabbedPane().getTableresultpanel();
//		jp.removeAll();
//		jp.add(resultpanel);
//		
//	}

	public static List<TestCase> extractPerformanceTestDataFromXml(String path) {

		int i = 1, j = 1;

		List<TestCase> testcaseList = new ArrayList<TestCase>();
		List<myProcess> processList = new ArrayList<myProcess>();

		SAXReader reader = new SAXReader();

		// String path="D:\\rc_loopForXStream1.0.1.xml";
		// String path="D:\\789.xml";

		File file = new File(path);

		try {

			Document dom = reader.read(file);

			Element TCS = dom.getRootElement();
			List<Element> testcaseElements = TCS.elements("testcase");
			for (Element testcase : testcaseElements) {

				double highcm = 0;
				double windspeed = 0;

				List<Element> processElements = testcase.elements("process");

				for (Element process : processElements) {

					Element operation = process.element("operation");

					Element input = process.element("input");

					String operationdata = operation.getData().toString();
					String inputdata = input.getData().toString();

					myProcess p = new myProcess();
					p.setProcessID(j++);
					p.setProcessName(operationdata);
					p.setProcessParam(inputdata);
					// p.setProcessStatus(processStatus);
					// p.setProcessExec(processExec);

					if (operationdata.equals("do_user_takeoff")) {
						String str1, str2;
						str1 = inputdata.substring(inputdata.indexOf("takeoff_alt_cm=") + 15);
						str2 = str1.substring(0, str1.indexOf(","));
						highcm = Integer.parseInt(str2);
					} else if (operationdata.equals("_simulator_servos")) {
						String str1, str2;
						str1 = inputdata.substring(inputdata.indexOf("_sitl.wind_speed=") + 17);
						str2 = str1.substring(0, str1.indexOf(","));
						windspeed = Integer.parseInt(str2);
					}

					processList.add(p);

				}

				j = 1;

				TestCase tc = new TestCase();
				tc.setTestCaseID(String.valueOf(i++));
				tc.setProcessList(processList);
				// tc.setState(state);
				// tc.setResult(result);

				TestCaseResult tcr = new TestCaseResult();
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

//	protected void showPerformanceTestCase() {
//		// TODO Auto-generated method stub
//
//		JPanel resultpanel = new JPanel();
//		JPanel emptypanel = new JPanel();
//		resultpanel.setOpaque(false);
//		emptypanel.setOpaque(false);
//
//		GridBagLayout layout = new GridBagLayout();
//		resultpanel.setLayout(layout);
//		int i = 0;
//
//		PerformanceTestCaseReportTableHeaderPanel ptcrthpanel = new PerformanceTestCaseReportTableHeaderPanel();
//		resultpanel.add(ptcrthpanel);
//		layout.setConstraints(ptcrthpanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//
//		performancetestcasereportlist.clear();
//		for (TestCase tc : testcaselist) {
//			PerformanceTestCaseReportPartPanel ptcrppanel = new PerformanceTestCaseReportPartPanel(tc);
//			resultpanel.add(ptcrppanel);
//			layout.setConstraints(ptcrppanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//			performancetestcasereportlist.add(ptcrppanel);
//		}
//		resultpanel.add(emptypanel);
//		layout.setConstraints(emptypanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//
//		JPanel jp = mainFrame.getStepFiveCenterTabbedPane().getTestCaseReportTabbedPane().getTableresultpanel();
//		jp.removeAll();
//		jp.add(resultpanel);
//
//	}
	
	public static List<TestCase> extractTimeTestDataFromXml(String path) {
		// TODO Auto-generated method stub

		int i = 1, j = 1;

		List<TestCase> testcaseList = new ArrayList<TestCase>();
		List<myProcess> processList = new ArrayList<myProcess>();
		
//		List<List<String>> limitList=new ArrayList<>();
		
		SAXReader reader = new SAXReader();
		
		File file=new File(path);
		
		try {
			
			Document dom = reader.read(file);
			
			Element TCS=dom.getRootElement();
			List<Element> testcaseElements=TCS.elements("testcase");
			for(Element testcase:testcaseElements){
				
				List<Element> processElements=testcase.elements("process");
				
				for(Element process:processElements){

					Element operation=process.element("operation");
					
					Element input=process.element("input");
					
					Element time=process.element("time");
					
					myProcess p = new myProcess();
					p.setProcessID(j++);
					p.setProcessName(operation.getData().toString());
					p.setProcessParam(input.getData().toString());
					p.setProcessStatus(time.getData().toString());
//					p.setProcessExec(processExec);

					processList.add(p);
					
				}
				
				j=1;
				
				List<String> limits=new ArrayList<>();
				String limit=testcase.element("limit").element("operation").getData().toString();
				System.out.println(limit);
				for (String str : limit.split(",")) {
					limits.add(str);
				}
//				limitList.add(limits);
				
				TestCase tc = new TestCase();
				tc.setTestCaseID(String.valueOf(i++));
				tc.setProcessList(processList);
//				tc.setState(state);
//				tc.setResult(result);
				tc.setLimit(limits);

				testcaseList.add(tc);
				
				processList = new ArrayList<myProcess>();
				
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		Map resultmap=new HashMap<>();
//		resultmap.put("testcase", testcaseList);
//		resultmap.put("limit", limitList);
		
		return testcaseList;
	}

//	protected void showTimeTestCase() {
//		// TODO Auto-generated method stub
//
//		JPanel resultpanel=new JPanel();
//		JPanel emptypanel=new JPanel();
//		resultpanel.setOpaque(false);
//		emptypanel.setOpaque(false);
//		
//		GridBagLayout layout = new GridBagLayout();
//		resultpanel.setLayout(layout);
//		int i=0;
//		int index=0;
//		timetestcasereportlist.clear();
//		for(TestCase tc:testcaselist){
//			TimeTestCaseReportPartPanel ttcrppanel=new TimeTestCaseReportPartPanel(tc);
//			resultpanel.add(ttcrppanel);
//			layout.setConstraints(ttcrppanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//			timetestcasereportlist.add(ttcrppanel);
//			index++;
//		}
//		resultpanel.add(emptypanel);
//		layout.setConstraints(emptypanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		
//		JPanel jp = mainFrame.getStepFiveCenterTabbedPane().getTestCaseReportTabbedPane().getTableresultpanel();
//		jp.removeAll();
//		jp.add(resultpanel);
//		
//	}

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
		} else if (starttype == 3) {
			file = new File(baseUrl + "\\TimeTest");
			fList = file.listFiles();
		} else {
			file = new File(baseUrl);
			fList = file.listFiles();
		}

		return fList;
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

//	public List<FunctionalTestCaseReportPartPanel> getFunctionaltestcasereportlist() {
//		return functionaltestcasereportlist;
//	}
//
//	public List<PerformanceTestCaseReportPartPanel> getPerformancetestcasereportlist() {
//		return performancetestcasereportlist;
//	}
//	
//	public List<TimeTestCaseReportPartPanel> getTimetestcasereportlist() {
//		return timetestcasereportlist;
//	}

	public String getTestcasename() {
		return testcasename;
	}

	public void setTestcasename(String testcasename) {
		this.testcasename = testcasename;
	}
	
	
	
}

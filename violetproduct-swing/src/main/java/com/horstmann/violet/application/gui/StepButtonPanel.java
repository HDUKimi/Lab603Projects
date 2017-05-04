package com.horstmann.violet.application.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.horstmann.violet.application.consolepart.ConsoleMessageTabbedPane;
import com.horstmann.violet.application.consolepart.ConsolePart;
import com.horstmann.violet.application.consolepart.ConsolePartDataTestDao;
import com.horstmann.violet.application.consolepart.ConsolePartDetailInfoTable;
import com.horstmann.violet.application.consolepart.ConsolePartTextArea;
import com.horstmann.violet.application.gui.opreationTreePane.TestCaseConfirmationPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.ButtonTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.MoviePanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.PerformanceTestCaseReportPartPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.ValidationToolPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.TestCasePieChartPanel;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.TestCase;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.myProcess;
import com.horstmann.violet.application.gui.util.chengzuo.Util.ClientSocket;
import com.horstmann.violet.application.gui.util.wqq.AutoMataTransfrom1.GetAutomatic;
import com.horstmann.violet.application.gui.util.wqq.AutoMataTransfrom1.Main;
import com.horstmann.violet.application.gui.util.wujun.SequenceTransfrom.SD2UppaalMain;
import com.horstmann.violet.application.gui.util.xiaole.GraghLayout.LayoutUppaal;
import com.horstmann.violet.application.gui.util.xiaole.UppaalTransfrom.ImportByDoubleClick;
import com.horstmann.violet.application.gui.util.xiaole.UppaalTransfrom.TransToVioletUppaal;
import com.horstmann.violet.application.menu.FileMenu;
import com.horstmann.violet.application.menu.util.zhangjian.Database.AbstractState;
import com.horstmann.violet.application.menu.util.zhangjian.Database.RealTestCaseVO;
import com.horstmann.violet.application.menu.util.zhangjian.UMLTransfrom.AbstractTestCaseInsertByTan;
import com.horstmann.violet.application.menu.util.zhangjian.UMLTransfrom.AbstractTestCaseUppaalCreate;
import com.horstmann.violet.application.menu.util.zhangjian.UMLTransfrom.AbstractTestCaseUppaalSequenceCreate;
import com.horstmann.violet.application.menu.util.zhangjian.UMLTransfrom.AbstractTrasitionAndStateInsertByTan;
import com.horstmann.violet.application.menu.util.zhangjian.UMLTransfrom.RealTestCaseXMLRead;
import com.horstmann.violet.framework.file.GraphFile;
import com.horstmann.violet.framework.file.IFile;
import com.horstmann.violet.framework.file.IGraphFile;
import com.horstmann.violet.framework.file.chooser.IFileChooserService;
import com.horstmann.violet.framework.file.naming.ExtensionFilter;
import com.horstmann.violet.framework.file.naming.FileNamingService;
import com.horstmann.violet.framework.file.persistence.IFileReader;
import com.horstmann.violet.framework.file.persistence.XHTMLPersistenceService;
import com.horstmann.violet.framework.injection.bean.ManiocFramework.InjectedBean;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;

public class StepButtonPanel extends JPanel {
	@InjectedBean
    private IFileChooserService fileChooserService;
	@InjectedBean
	private FileNamingService fileNamingService;
	private JPanel homebuttonpanel;
	private JPanel step1buttonpanel;
	private JPanel step2buttonpanel;
	private JPanel step3buttonpanel;
	private JPanel step4buttonpanel;
	private JPanel step5buttonpanel;
	private JPanel step6buttonpanel;
	
	private int index=0;
	private int firstclickstep1button=0;
	
	private JButton homebutton;
	private JButton step1button;
	private JButton step2button;
	private JButton step3button;
	private JButton step4button;
	private JButton step5button;
	private JButton step6button;
	private List<JButton> stepButtonGroup;
	private MainFrame mainFrame;
//    private ConsolePart consolePart;
    private JPanel operationPanel;
    private JPanel attributePanel;
	
//    private JButton Threestart=new JButton("开始");
//    private JButton Fourstart=new JButton("开始");
//    private JButton Twostart=new JButton("开始");
//    private JButton fivestart=new JButton("开始");
    private JButton fiveshow=new JButton("显示");
//    private JButton Sixstart=new JButton("开始");
	
	private JButton Buttonstart=new JButton();
    private JButton Buttonstop=new JButton();
    
    private JButton Twostart=new JButton();
    private JButton Threestart=new JButton();
    private JButton Fourstart=new JButton();
    private JButton Fivestart=new JButton();
    private JButton Sixstart=new JButton();
    
    private JButton tabbutton=new JButton();
	
    JTextArea StepOneArea=new JTextArea();
	JTextArea StepTwoArea=new JTextArea();
	JTextArea StepThreeArea=new JTextArea();
	JTextArea StepFourArea=new JTextArea();
	JTextArea StepFiveArea=new JTextArea();
	JTextArea StepSixArea=new JTextArea();
	
	JPanel StepOneTreePanel=new JPanel();
	JPanel StepTwoTreePanel=new JPanel();
	JPanel StepThreeTreePanel=new JPanel();
	
	JScrollPane StepOneScrollTree;
	JScrollPane StepTwoScrollTree;
	JScrollPane StepThreeScrollTree;
	JScrollPane StepSixScrollTree;
	
	
	List<PerformanceTestCaseReportPartPanel> testcasereportlist=new ArrayList<PerformanceTestCaseReportPartPanel>();
	
	public StepButtonPanel(MainFrame mainFrame) {
//		this.setBackground(new Color(174,199,225));
		this.mainFrame=mainFrame;
		init();
	}
    
	private void init() {
		
		
		this.setLayout(new GridLayout(8,1));
		
//		GridBagLayout layout = new GridBagLayout();
//		this.setLayout(layout);
		initButton();
//		GridBagConstraints s = new GridBagConstraints();// 定义一个GridBagConstraints，
//		// 是用来控制添加进的组件的显示位置
//		s.fill = GridBagConstraints.BOTH;
//		s.anchor = GridBagConstraints.FIRST_LINE_START;
//		s.insets = new Insets(0, 0, 0,0);
        this.add(homebuttonpanel);
		this.add(step1buttonpanel);
		this.add(step2buttonpanel);
		this.add(step6buttonpanel);
		this.add(step3buttonpanel);
//		this.add(step4buttonpanel);
		this.add(step5buttonpanel);
		
		
//		s.gridx = 0;
//		s.gridy = 0;
//		s.weighty = 1;
//		s.weightx=1;
//		layout.setConstraints(homebutton, s);// 设置组件
////		s.gridx = 0;
////		s.gridy = 1;
////		layout.setConstraints(step1button, s);
////		s.gridx = 0;
////		s.gridy = 2;
////		layout.setConstraints(step2button, s);
////		s.gridx = 0;
////		s.gridy = 3;
////		layout.setConstraints(step3button, s);
////		s.gridx = 0;
////		s.gridy = 4;
////		layout.setConstraints(step4button, s);
////		s.gridx = 0;
////		s.gridy = 5;
////		layout.setConstraints(step5button, s);
//		
//		s.gridx = 1;
//		s.gridy = 0;
//		layout.setConstraints(step1button, s);
//		s.gridx = 2;
//		s.gridy = 0;
//		layout.setConstraints(step2button, s);
//		s.gridx = 3;
//		s.gridy = 0;
//		layout.setConstraints(step3button, s);
//		s.gridx = 4;
//		s.gridy = 0;
//		layout.setConstraints(step4button, s);
//		s.gridx = 5;
//		s.gridy = 0;
//		layout.setConstraints(step5button, s);
		
		// TODO Auto-generated method stub

		SetButtonListener();
		
		this.setMinimumSize(new Dimension(75, 500));
		this.setMaximumSize(new Dimension(75, 500));

	}

	private void initButton() {
		
		homebuttonpanel =new JPanel();
		step1buttonpanel = new JPanel();
		step2buttonpanel = new JPanel();
		step3buttonpanel = new JPanel();
		step4buttonpanel = new JPanel();
		step5buttonpanel = new JPanel();
		step6buttonpanel = new JPanel();
		
		homebutton =new JButton();
		step1button = new JButton();
		step2button = new JButton();
		step3button = new JButton();
		step4button = new JButton();
		step5button = new JButton(); 
		step6button = new JButton();
		
//	    homebutton.setText("首页");
//	    homebutton.setForeground(Color.RED);
//		step1button.setText("第一步:UML模型建立及导入");
//		step2button.setText("第二步:UML模型转化时间自动机");
//		step3button.setText("第三步:抽象测试用例生成");
//		step4button.setText("第四步:测试用例实例化");
//		step5button.setText("第五步:测试用例实例化验证");
		
//		URL url=this.getClass().getResource("testbutton.png");
//        ImageIcon icon=new ImageIcon(url);
//        icon.setImage(icon.getImage().getScaledInstance(22, 22, Image.SCALE_DEFAULT));
        
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon0 = new ImageIcon(path + "stepbutton0.png");
		icon0.setImage(icon0.getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT));
		ImageIcon icon1 = new ImageIcon(path + "stepbutton1.png");
		icon1.setImage(icon1.getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(path + "stepbutton2.png");
		icon2.setImage(icon2.getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT));
		ImageIcon icon3 = new ImageIcon(path + "stepbutton3.png");
		icon3.setImage(icon3.getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT));
		ImageIcon icon4 = new ImageIcon(path + "stepbutton4.png");
		icon4.setImage(icon4.getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT));
		ImageIcon icon5 = new ImageIcon(path + "stepbutton5.png");
		icon5.setImage(icon5.getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT));
		ImageIcon icon6 = new ImageIcon(path + "stepbutton1.png");
		icon6.setImage(icon6.getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT));
		
//		step1button.setContentAreaFilled(false);//btn背景透明
//		step1button.setBorderPainted(false);//btn无边框
//        step1button.setFocusPainted(false);//btn无内边框
        homebutton.setIcon(icon0);
        homebutton.setText("首页");
        homebutton.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        homebutton.setForeground(new Color(255,255,255));
        homebutton.setHorizontalTextPosition(SwingConstants.CENTER);
        homebutton.setVerticalTextPosition(SwingConstants.BOTTOM);
        homebutton.setMargin(new Insets(0, 0, 0, 0));
        homebutton.setFocusable(false);
        homebutton.setContentAreaFilled(false);
        homebutton.setBorderPainted(false);
        homebutton.setPreferredSize(new Dimension(75,80));
        homebutton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				homebuttonpanel.setBackground(new Color(53,55,59));
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(index!=0){
					homebuttonpanel.setBackground(new Color(71,80,93));
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(index!=0){
					homebuttonpanel.setBackground(new Color(72,76,81));
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				setstepbuttonpanelrepaint();
				homebuttonpanel.setBackground(new Color(53,55,59));
				index=0;
				mainFrame.getBottomPanel().setBackground(new Color(104,33,122));
				mainFrame.getBottomPanel().getMessagelable().setText("就绪");
			}
		});
        step1button.setIcon(icon1);
        step1button.setText("系统建模");
        step1button.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        step1button.setForeground(new Color(255,255,255));
        step1button.setHorizontalTextPosition(SwingConstants.CENTER);
        step1button.setVerticalTextPosition(SwingConstants.BOTTOM);
        step1button.setMargin(new Insets(0, 0, 0, 0));
		step1button.setFocusable(false);//btn里图片focus边框不显示
		step1button.setContentAreaFilled(false);
		step1button.setBorderPainted(false);
		step1button.setPreferredSize(new Dimension(75,80));
		step1button.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				step1buttonpanel.setBackground(new Color(53,55,59));
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(index!=1){
					step1buttonpanel.setBackground(new Color(71,80,93));
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(index!=1){
					step1buttonpanel.setBackground(new Color(72,76,81));
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				setstepbuttonpanelrepaint();
				step1buttonpanel.setBackground(new Color(53,55,59));
				index=1;
				mainFrame.getBottomPanel().setBackground(new Color(0, 122, 204));
				mainFrame.getBottomPanel().getMessagelable().setText("全部重新生成");
			}
		});
		step2button.setIcon(icon2);
		step2button.setText("模型转换");
        step2button.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        step2button.setForeground(new Color(255,255,255));
        step2button.setHorizontalTextPosition(SwingConstants.CENTER);
        step2button.setVerticalTextPosition(SwingConstants.BOTTOM);
        step2button.setMargin(new Insets(0, 0, 0, 0));
		step2button.setFocusable(false);
		step2button.setContentAreaFilled(false);
		step2button.setBorderPainted(false);
		step2button.setPreferredSize(new Dimension(75,80));
		step2button.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				step2buttonpanel.setBackground(new Color(53,55,59));
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(index!=2){
					step2buttonpanel.setBackground(new Color(71,80,93));
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(index!=2){
					step2buttonpanel.setBackground(new Color(72,76,81));
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				setstepbuttonpanelrepaint();
				step2buttonpanel.setBackground(new Color(53,55,59));
				index=2;
			}
		});
		step3button.setIcon(icon3);
		step3button.setText("测试用例");
        step3button.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        step3button.setForeground(new Color(255,255,255));
        step3button.setHorizontalTextPosition(SwingConstants.CENTER);
        step3button.setVerticalTextPosition(SwingConstants.BOTTOM);
        step3button.setMargin(new Insets(0, 0, 0, 0));
		step3button.setFocusable(false);
		step3button.setContentAreaFilled(false);
		step3button.setBorderPainted(false);
		step3button.setPreferredSize(new Dimension(75,80));
		step3button.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				step3buttonpanel.setBackground(new Color(53,55,59));
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(index!=3){
					step3buttonpanel.setBackground(new Color(71,80,93));
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(index!=3){
					step3buttonpanel.setBackground(new Color(72,76,81));
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				setstepbuttonpanelrepaint();
				step3buttonpanel.setBackground(new Color(53,55,59));
				index=3;
			}
		});
		step4button.setIcon(icon4);
		step4button.setText("用例实例化");
        step4button.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        step4button.setForeground(new Color(255,255,255));
        step4button.setHorizontalTextPosition(SwingConstants.CENTER);
        step4button.setVerticalTextPosition(SwingConstants.BOTTOM);
        step4button.setMargin(new Insets(0, 0, 0, 0));
		step4button.setFocusable(false);
		step4button.setContentAreaFilled(false);
		step4button.setBorderPainted(false);
		step4button.setPreferredSize(new Dimension(75,80));
		step4button.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				step4buttonpanel.setBackground(new Color(53,55,59));
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(index!=4){
					step4buttonpanel.setBackground(new Color(71,80,93));
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(index!=4){
					step4buttonpanel.setBackground(new Color(72,76,81));
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				setstepbuttonpanelrepaint();
				step4buttonpanel.setBackground(new Color(53,55,59));
				index=4;
			}
		});
		step5button.setIcon(icon5);
		step5button.setText("测试报告");
        step5button.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        step5button.setForeground(new Color(255,255,255));
        step5button.setHorizontalTextPosition(SwingConstants.CENTER);
        step5button.setVerticalTextPosition(SwingConstants.BOTTOM);
        step5button.setMargin(new Insets(0, 0, 0, 0));
		step5button.setFocusable(false);
		step5button.setContentAreaFilled(false);
		step5button.setBorderPainted(false);
		step5button.setPreferredSize(new Dimension(75,80));
		step5button.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				step5buttonpanel.setBackground(new Color(53,55,59));
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(index!=5){
					step5buttonpanel.setBackground(new Color(71,80,93));
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(index!=5){
					step5buttonpanel.setBackground(new Color(72,76,81));
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				setstepbuttonpanelrepaint();
				step5buttonpanel.setBackground(new Color(53,55,59));
				index=5;
			}
		});
		step6button.setIcon(icon6);
		step6button.setText("一致性测试");
        step6button.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        step6button.setForeground(new Color(255,255,255));
        step6button.setHorizontalTextPosition(SwingConstants.CENTER);
        step6button.setVerticalTextPosition(SwingConstants.BOTTOM);
        step6button.setMargin(new Insets(0, 0, 0, 0));
		step6button.setFocusable(false);
		step6button.setContentAreaFilled(false);
		step6button.setBorderPainted(false);
		step6button.setPreferredSize(new Dimension(75,80));
		step6button.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				step6buttonpanel.setBackground(new Color(53,55,59));
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(index!=6){
					step6buttonpanel.setBackground(new Color(71,80,93));
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(index!=6){
					step6buttonpanel.setBackground(new Color(72,76,81));
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				setstepbuttonpanelrepaint();
				step6buttonpanel.setBackground(new Color(53,55,59));
				index=6;
			}
		});
//		homebutton.addMouseListener(new ButtonMouseListener());
//		step1button.addMouseListener(new ButtonMouseListener());
//		step2button.addMouseListener(new ButtonMouseListener());
//		step3button.addMouseListener(new ButtonMouseListener());
//		step4button.addMouseListener(new ButtonMouseListener());
//		step5button.addMouseListener(new ButtonMouseListener());
//		step6button.addMouseListener(new ButtonMouseListener());
		
		homebuttonpanel.setLayout(new GridLayout());
//		homebuttonpanel.setBackground(new Color(71,80,93));
		homebuttonpanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		homebuttonpanel.add(homebutton);
		
		homebuttonpanel.setBackground(new Color(53,55,59));
		index=0;
		
		step1buttonpanel.setLayout(new GridLayout());
		step1buttonpanel.setBackground(new Color(71,80,93));
		step1buttonpanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		step1buttonpanel.add(step1button);
		step2buttonpanel.setLayout(new GridLayout());
		step2buttonpanel.setBackground(new Color(71,80,93));
		step2buttonpanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		step2buttonpanel.add(step2button);
		step3buttonpanel.setLayout(new GridLayout());
		step3buttonpanel.setBackground(new Color(71,80,93));
		step3buttonpanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		step3buttonpanel.add(step3button);
		step4buttonpanel.setLayout(new GridLayout());
		step4buttonpanel.setBackground(new Color(71,80,93));
		step4buttonpanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		step4buttonpanel.add(step4button);
		step5buttonpanel.setLayout(new GridLayout());
		step5buttonpanel.setBackground(new Color(71,80,93));
		step5buttonpanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		step5buttonpanel.add(step5button);
		step6buttonpanel.setLayout(new GridLayout());
		step6buttonpanel.setBackground(new Color(71,80,93));
		step6buttonpanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		step6buttonpanel.add(step6button);
		
		
//		URL urlstart=this.getClass().getResource("teststart.png");
        ImageIcon iconstart=new ImageIcon(path + "teststart.png");
        iconstart.setImage(iconstart.getImage().getScaledInstance(22, 22, Image.SCALE_DEFAULT));
        Twostart.setIcon(iconstart);
        Twostart.setFocusable(false);
        Twostart.setContentAreaFilled(false);
        Twostart.setBorderPainted(false);
        Twostart.addMouseListener(new ButtonMouseListener());
        Twostart.setPreferredSize(new Dimension(30,30));
        Threestart.setIcon(iconstart);
        Threestart.setFocusable(false);
        Threestart.setContentAreaFilled(false);
        Threestart.setBorderPainted(false);
        Threestart.addMouseListener(new ButtonMouseListener());
        Threestart.setPreferredSize(new Dimension(30,30));
        Fourstart.setIcon(iconstart);
        Fourstart.setFocusable(false);
        Fourstart.setContentAreaFilled(false);
        Fourstart.setBorderPainted(false);
        Fourstart.addMouseListener(new ButtonMouseListener());
        Fourstart.setPreferredSize(new Dimension(30,30));
        Fivestart.setIcon(iconstart);
        Fivestart.setFocusable(false);
        Fivestart.setContentAreaFilled(false);
        Fivestart.setBorderPainted(false);
        Fivestart.addMouseListener(new ButtonMouseListener());
        Fivestart.setPreferredSize(new Dimension(30,30));
        Sixstart.setIcon(iconstart);
        Sixstart.setFocusable(false);
        Sixstart.setContentAreaFilled(false);
        Sixstart.setBorderPainted(false);
        Sixstart.addMouseListener(new ButtonMouseListener());
        Sixstart.setPreferredSize(new Dimension(30,30));
        
        
//        String absolutePath=System.getProperty("user.dir");
//		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

//        URL urlstop=this.getClass().getResource("teststop.png");
        ImageIcon iconstop=new ImageIcon(path + "teststop.png");
        iconstop.setImage(iconstop.getImage().getScaledInstance(22, 22, Image.SCALE_DEFAULT));
        Buttonstop.setIcon(iconstop);
        Buttonstop.setFocusable(false);
        Buttonstop.setContentAreaFilled(false);
        Buttonstop.setBorderPainted(false);
        Buttonstop.addMouseListener(new ButtonMouseListener());
        Buttonstop.setPreferredSize(new Dimension(30,30));
		
		stepButtonGroup = new ArrayList<JButton>();
		stepButtonGroup.add(homebutton);
		stepButtonGroup.add(step1button);
		stepButtonGroup.add(step2button);
		stepButtonGroup.add(step3button);
		stepButtonGroup.add(step4button);
		stepButtonGroup.add(step5button);
		stepButtonGroup.add(step6button);
		operationPanel=mainFrame.getOpreationPart();
		operationPanel.setLayout(new GridLayout(1,1));
		attributePanel=mainFrame.getAttributePart();
		attributePanel.setLayout(new GridLayout(1,1));
//		consolePart=mainFrame.getConsolePart();	
		// TODO Auto-generated method stub
//        step2button.setEnabled(false);//初始其他步骤按钮都不可点击
//        step3button.setEnabled(false);
//        step4button.setEnabled(false);
//        step5button.setEnabled(false);
	}
	protected void setstepbuttonpanelrepaint() {
		// TODO Auto-generated method stub
		homebuttonpanel.setBackground(new Color(71,80,93));
		step1buttonpanel.setBackground(new Color(71,80,93));
		step2buttonpanel.setBackground(new Color(71,80,93));
		step3buttonpanel.setBackground(new Color(71,80,93));
		step4buttonpanel.setBackground(new Color(71,80,93));
		step5buttonpanel.setBackground(new Color(71,80,93));
		step6buttonpanel.setBackground(new Color(71,80,93));
	}

	//初始化阶段
	public void clearSelection() {
		for (JButton stepButton : stepButtonGroup) {			
			stepButton.setForeground(new Color(255,255,255));
		}
	}
//	public void clearConsolePart(){
//		this.consolePart.getContentPane().removeAll();;
//	}
  private void ClearOpreationPanel()
  {
	  this.operationPanel.removeAll();
  }
  private void ClearAttributePanel()
  {
	  this.attributePanel.removeAll();
  }
    //设置监听器
	private void SetButtonListener() {
		Twostart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StepTwoArea.append("UML模型正在转换中......\n");	
				// TODO Auto-generated method stub
			   	try {
			   		//事件分发线程(gum处理事件和画图的时候)
			   		SwingUtilities.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							try {
								//uml转化成事件自动机
                                 //用于获得当前工作的sequence
								String name=FileMenu.getFileName();//sequence.getGraphFile().getFilename();
								String directoryPath=FileMenu.getDirectory();
								String path=directoryPath+"\\"+name;//菜单栏打开文件的路径
								System.out.println(path+"路劲");
								if(name.contains("EA")){//打开ea平台的xml文件
//									System.out.println("++++"+mainFrame.getStepOneCenterTabbedPane().getSelectedButtonTabbedPanel().getButtontabbedpanelindex()+"++++"+mainFrame.getSequenceWorkspaceList().get(mainFrame.getStepOneCenterTabbedPane().getSelectedButtonTabbedPanel().getButtontabbedpanelindex()).getGraphFile().getDirectory()+"++++"+mainFrame.getSequenceWorkspaceList().get(mainFrame.getStepOneCenterTabbedPane().getSelectedButtonTabbedPanel().getButtontabbedpanelindex()).getGraphFile().getFilename());
//									path=mainFrame.getSequenceWorkspaceList().get(mainFrame.getStepOneCenterTabbedPane().getSelectedButtonTabbedPanel().getButtontabbedpanelindex()).getGraphFile().getDirectory()+"\\"+mainFrame.getSequenceWorkspaceList().get(mainFrame.getStepOneCenterTabbedPane().getSelectedButtonTabbedPanel().getButtontabbedpanelindex()).getGraphFile().getFilename();
//									System.out.println("::::----"+path);
									SD2UppaalMain.transEA(path,mainFrame);//主要是将ea的xml转换成我们的wujun的xml(里面有他的路径)
									//以下d盘中写的文件是死的路径，但是上面是动态生成的需要修改
									LayoutUppaal.layout("D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\UseCase1-Sequence1-Normal.xml");//("sequence.xml");
//								    String filename1=TransToVioletUppaal.TransToViolet();
									//String filename1="uppaalTest1.uppaal.violet.xml";
//								    GraphFile fGraphFile1=ImportByDoubleClick.importFileByDoubleClick("UPPAAL",filename1);
//				    			    IWorkspace workspace1=new Workspace(fGraphFile1);  
//				    			    mainFrame.addTabbedPane(workspace1,2);
				    			    mainFrame.repaint();
				    			    Thread.sleep(5000);
				    				//String filename2=TransToVioletUppaal.TransToViolet();
				    			
								   // GraphFile fGraphFile2=ImportByDoubleClick.importFileByDoubleClick("UPPAAL",filename2);
				    			    //IWorkspace workspace2=new Workspace(fGraphFile2);  			    			  
				    			    StepTwoArea.append("UML模型到时间自动机模型已经转换完成!\n");
								}
								else{//打开我们平台的xml文件
									
								}
//								SD2UppaalMain.transEA(path);//主要是将ea的xml转换成我们的wujun的xml(里面有他的路径)
//							    String filename1=TransToVioletUppaal.TransToViolet();
//							    GraphFile fGraphFile1=ImportByDoubleClick.importFileByDoubleClick("UPPAAL",filename1);
//			    			    IWorkspace workspace1=new Workspace(fGraphFile1);  
//			    			    mainFrame.addTabbedPane(workspace1,2);
//			    			    mainFrame.repaint();
//			    			    Thread.sleep(5000);
								//先进行布局
			    			    //将时间自动机展示在我们的平台上
//								LayoutUppaal.layout
//								("C:\\Users\\Admin\\Desktop\\项目最新代码\\violetumleditor-master\\violetproduct-swing\\sequence.xml");//("stabilize_run.xml");
//								String filename2=TransToVioletUppaal.TransToViolet();
//							    GraphFile fGraphFile2=ImportByDoubleClick.importFileByDoubleClick("UPPAAL",filename2);
//			    			    IWorkspace workspace2=new Workspace(fGraphFile2);  			    			  
//			    			    StepTwoArea.append("UML模型到时间自动机模型已经转换完成!\n");
			    			    //mainFrame.addTabbedPane(workspace1,2);
			    			   
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
							StepTwoArea.append("UML模型转换完成......\n");	
//							tabbutton=StepTwoCenterTabbedPane.getUppaalDiagramButton();
//							tabbutton.doClick();
					}});			   				  		   
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
Threestart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StepThreeArea.append("抽象时间迁移的时间自动机正在生成.....\n");
				
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						
						//GetAutomatic.getAutomatic("Draw MoneyForXStream(2).xml");
						//by tan 将抽象测试用例的state加入到数据库中
						//new AbstractStateInsertByTan().wqq2zhangExchange("Draw MoneyForXStream(2).xml");
						//BY TAN 强抽象测试的迁移trasition加入到数据库中
						//new AbstractTrasitionAndStateInsertByTan().w2zExchange("Draw MoneyForXStream(2).xml");
						
						String absolutePath=System.getProperty("user.dir");
						
						System.out.println("----------------------------------");
						try {
							//获取第三步自动机的节点和边的信息，存放在squence。xml中，在调用布局算法，生成节点坐标。存放在stabilize_run.xml中。
							new AbstractTestCaseUppaalSequenceCreate().createXML("D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\UseCase1-Sequence1-NormalForXStream.xml");
							LayoutUppaal.layout
							(absolutePath+"\\sequence.xml");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						};
						System.out.println("-----------------------------------");
						 //将信息存入数据库
						new AbstractTestCaseUppaalCreate().createXML("D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\UseCase1-Sequence1-NormalForXStream.xml", "D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\abs.uppaal.violet.xml");
						//new AbstractTestCaseInsertByTan().w2zAbstractTestCaseExchange("D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\sequenceForXStream.xml");//已经整合到上面的方法中
						//String absfilename="abs.uppaal.violet.xml";
				        //String no_time_absfilename="no_time_abs.uppaal.violet.xml";
					    
					    //String filename2=TransToVioletUppaal.TransToViolet();
						
						GraphFile absfGraphFile=ImportByDoubleClick.importFileByDoubleClick("UPPAAL","abs.uppaal.violet.xml");
						//GraphFile no_time_absfGraphFile=ImportByDoubleClick.importFileByDoubleClick("UPPAAL","a.xml");
		 			    IWorkspace absworkspace=new Workspace(absfGraphFile);
		 			    //IWorkspace no_time_absworkspace=new Workspace(no_time_absfGraphFile);
		 			    //展示时间迁移的自动机
//		 			    mainFrame.addTabbedPane(absworkspace,3);
		 			    
		 			    
		 			    StepThreeArea.append("抽象时间迁移的时间自动机生成完成!\n");
		 			    StepThreeArea.append("不含时间迁移的时间自动机正在生成.....\n");
		 			    //展示去时间迁移的自动机
		 			    //mainFrame.addTabbedPane(no_time_absworkspace,3);
		 			    
		 			    StepThreeArea.append("不含时间迁移的时间自动机生成完成!\n");
		 			    StepThreeArea.append("抽象测试用例正在生成.....\n");
						//展示抽象测试用例的生成
//		 			    mainFrame.getStepThreeCenterTabbedPane().getConsolePartScrollPane()
//						.getViewport().add(new ConsolePartDetailInfoTable(0));			
//		 			   mainFrame.getStepThreeCenterTabbedPane().getConsolePartScrollPane().getViewport().repaint();
					    
		 			   mainFrame.getStepThreeCenterTabbedPane().getTestCaseProduceTabbedPanel().getTabelscrollpanel().setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
		 			   mainFrame.getStepThreeCenterTabbedPane().getTestCaseProduceTabbedPanel().getTabelscrollpanel()
						.getViewport().add(new ConsolePartDetailInfoTable(0));			
		 			   mainFrame.getStepThreeCenterTabbedPane().getTestCaseProduceTabbedPanel().getTabelscrollpanel().getViewport().repaint();
					    
		 			    
		 			    StepThreeArea.append("抽象测试用例生成完成!.....\n");
					    
//					    tabbutton=StepThreeCenterTabbedPane.getAbstractUppaalDiagramButton();
//						tabbutton.doClick();
						
					}
				});
				
			}
		});
Fourstart.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		StepFourArea.append("实例化测试用例正在生成.....\n");
//		SwingUtilities.invokeLater(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
				new RealTestCaseXMLRead("tcs.xml");
//				mainFrame.getStepFourCenterTabbedPane().getConsolePartScrollPane()
//				.getViewport().add(new ConsolePartDetailInfoTable(1));			
//				mainFrame.getStepFourCenterTabbedPane().getConsolePartScrollPane().getViewport().repaint();
				
				mainFrame.getStepFourCenterTabbedPane().getTestCaseInstantiationTabbedPanel().getTableresultpanel().removeAll();
				mainFrame.getStepFourCenterTabbedPane().getTestCaseInstantiationTabbedPanel().getTableresultpanel().setLayout(new BorderLayout());
				JTable jt=new ConsolePartDetailInfoTable(1);
				mainFrame.getStepFourCenterTabbedPane().getTestCaseInstantiationTabbedPanel().getTableresultpanel().add(jt.getTableHeader(),BorderLayout.NORTH);
				mainFrame.getStepFourCenterTabbedPane().getTestCaseInstantiationTabbedPanel().getTableresultpanel().add(jt, BorderLayout.CENTER);
//				mainFrame.getStepFourCenterTabbedPane().getTestCaseInstantiationTabbedPanel().getTableresultpanel().add(new ConsolePartDetailInfoTable(1));
				
//				mainFrame.getStepFourCenterTabbedPane().getTestCaseInstantiationTabbedPanel().getTabelscrollpanel().removeAll();
//				mainFrame.getStepFourCenterTabbedPane().getTestCaseInstantiationTabbedPanel().getTabelscrollpanel().setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
//				mainFrame.getStepFourCenterTabbedPane().getTestCaseInstantiationTabbedPanel().getTabelscrollpanel().getViewport().add(new ConsolePartDetailInfoTable(1));
//				mainFrame.getStepFourCenterTabbedPane().getTestCaseInstantiationTabbedPanel().getTabelscrollpanel().getViewport().repaint();
//				
				
//				StartThread();
				
				
			    StepFourArea.append("实例化测试用例生成成功!");
			}

		});
		
		
//	}
//});

		Fivestart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StepFiveArea.append("实例化测试用例验证正在进行中.....\n");
				StepFiveArea.append("正在导入测试用例.....\n");

				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
//						ClientSocket clientSocket = new ClientSocket("192.168.150.117", 5555);
//						clientSocket.Connection();
//						JFileChooser jfc = new JFileChooser();
//						jfc.setMultiSelectionEnabled(true);
//						jfc.showDialog(new JLabel(), "选择测试用例");
//						File[] files = jfc.getSelectedFiles();
//						StepFiveArea.append("正在发送数据.....\n");
//						clientSocket.sendFile(files);
//						StepFiveArea.append("发送数据完成!\n");
//						StepFiveArea.append("正在获得数据.....\n");
//						try {
//							Thread.sleep(10000);
//						} catch (InterruptedException e) {
//						}
//						List<TestCase> list = clientSocket.getTestCaseList();
//						StepFiveArea.append("数据已经获得!\n");
						// 获得root的容器

						System.out.println("///////////////////////////////");
						
//						List<TestCase> list = extractData();
						List<TestCase> list = extractDataToXml();
						
						for(TestCase tc:list){
							System.out.println(tc.toString());
						}

						System.out.println("///////////////////////////////");

						JPanel jp = mainFrame.getStepFiveCenterTabbedPane().getTestCaseReportTabbedPane().getTableresultpanel();
						
						System.out.println("++++++++++++++++++++");
						
						JPanel resultpanel=new JPanel();
						JPanel emptypanel=new JPanel();
						resultpanel.setOpaque(false);
						emptypanel.setOpaque(false);
						
						GridBagLayout layout = new GridBagLayout();
						resultpanel.setLayout(layout);
						int i=0;
						testcasereportlist.clear();
						for(TestCase tc:list){
							PerformanceTestCaseReportPartPanel tcrppanel=new PerformanceTestCaseReportPartPanel(tc);
							resultpanel.add(tcrppanel);
							layout.setConstraints(tcrppanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
							testcasereportlist.add(tcrppanel);
						}
						resultpanel.add(emptypanel);
						layout.setConstraints(emptypanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
						
						jp.removeAll();
						jp.add(resultpanel);
						
						System.out.println("++++++++++++++++++++");
						
						// 获得报表的容器
//						JPanel jp1 = JFreeChartTest.getJFreeChartTest(list);
//						// 将表格放到JScrollPane容器中
//						JScrollPane jsp = new JScrollPane(new ConsolePartTestCaseInfoTable(list));
//						JSplitPane js = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jsp,
//								new JScrollPane(new ConsolePartTestCaseInfoTable(list).getjTextArea()));
//						js.setDividerLocation(300);
//						jp.add(js);
//						mainFrame.getStepFiveCenterTabbedPane().getTestCaseReportTabbedPane().updateUI();
//						
//						mainFrame.getStepFiveCenterTabbedPane().getTestCaseChartTabbedPane().removeAll();
//						mainFrame.getStepFiveCenterTabbedPane().getTestCaseChartTabbedPane().add(new TestCaseChartPanel());
						
						// 第二个tab页
//						JPanel jpTab2 = mainFrame.getStepFiveCenterTabbedPane().getTestcaseFile1();
//						// 报表放到第二个tab页
//						jpTab2.add(jp1);

					}
				});
			}
		});

Sixstart.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		StepSixArea.append("一致性测试正在进行.....\n");
//		SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
				try {
//					JSplitPane jp=new ExistTest().existTest();
//					mainFrame.getStepSixCenterTabbedPane().getConsolePartScrollPane().setLayout(new BorderLayout());
//					mainFrame.getStepSixCenterTabbedPane().getConsolePartScrollPane().validate();
//					mainFrame.getStepSixCenterTabbedPane().getConsolePartScrollPane().add(jp,BorderLayout.CENTER);
//					mainFrame.getStepSixCenterTabbedPane().getConsolePartScrollPane().revalidate();
					 StepSixArea.append("一致性测试测试成功!");
//					 Thread.sleep(5000);
					//System.out.println(jvp);
					//.add(jp);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			   
			}
		});
		
		
//	}
//});
		homebutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearSelection();		
//				homebutton.setForeground(Color.RED);	
				JLabel jLabel=new JLabel();
//				jLabel.setText(homebutton.getText());
				jLabel.setFont(new Font("宋体", Font.BOLD, 20));
				jLabel.setForeground(Color.white);
				JPanel labelpanel=mainFrame.getStepJLabel();
				labelpanel.setLayout(new GridBagLayout());
				labelpanel.removeAll();
				labelpanel.add(jLabel,new GBC(0, 0).setWeight(1, 0));				     				
			    mainFrame.getCenterPanel().removeAll();
				mainFrame.getCenterPanel().add(mainFrame.getHomeAllTabbedPanel());
				mainFrame.setStepindex(0);
				mainFrame.getConsolePartPanel().setVisible(false);
				mainFrame.getOpreationPart().setVisible(false);	
				mainFrame.getOneTouchExpandablePanel().setVisible(false);	
				mainFrame.setVisible(false);
				mainFrame.getContentPane().repaint();
				mainFrame.setVisible(true);
			}
		});						
		// TODO Auto-generated method stub
		step1button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearSelection();
				//显示控制面板和操作面板			  			
				//步骤一按钮高亮			
//				step1button.setForeground(Color.RED);							
				//首先移除欢迎界面
			
				//修改原来的标题面板
				JLabel jLabel=new JLabel();
//				jLabel.setText(step1button.getText());
				jLabel.setFont(new Font("宋体", Font.BOLD, 20));
				jLabel.setForeground(Color.white);
				JPanel labelpanel=mainFrame.getStepJLabel();
				labelpanel.setLayout(new GridBagLayout());
				labelpanel.removeAll();
//				labelpanel.add(jLabel,new GBC(0, 0).setWeight(1, 0));
							
				mainFrame.getCenterPanel().removeAll();
				mainFrame.getCenterPanel().add(mainFrame.getJs1());
				
			    //添加操作面板
				ClearOpreationPanel();
				operationPanel.add(mainFrame.getProjectTree());	
				
//				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//				int screenWidth = (int) screenSize.getWidth();
//				int screenHeight = (int) screenSize.getHeight();
//				
//				mainFrame.getJs1().setDividerLocation(screenWidth/8);
//				mainFrame.getJs2().setDividerLocation(screenWidth*7/8);
//				mainFrame.getJs3().setDividerLocation(screenHeight*4/5);
				
				
				mainFrame.setStepindex(1);
				mainFrame.getCenterTabPanel().removeAll();
				
//				System.out.println(mainFrame.getStepOneCenterTabbedPane().getButtonPanel().getComponentCount());
				int count=mainFrame.getStepOneCenterTabbedPane().getButtonPanel().getComponentCount();
//				int sum=0;
				if(firstclickstep1button==0){
					
					for(int i=0;i<count;i++){
//						((ButtonTabbedPanel) mainFrame.getStepOneCenterTabbedPane().getButtonPanel().getComponent(i)).getTabbedbutton();
						
						Object ob=mainFrame.getStepOneCenterTabbedPane().getButtonPanel().getComponent(i);
						if(ob instanceof ButtonTabbedPanel){
							((ButtonTabbedPanel) ob).setVisible(false);
//							mainFrame.getStepOneCenterTabbedPane().getButtonPanel().remove((ButtonTabbedPanel) ob);
//							((ButtonTabbedPanel) ob).removeAll();
//							mainFrame.getStepOneCenterTabbedPane().getButtonPanel().remove(((ButtonTabbedPanel) ob));
//							sum++;
						}
						
					}
					
					firstclickstep1button=1;
				}
//				System.out.println(sum);
//				System.out.println(mainFrame.getStepOneCenterTabbedPane().getButtonPanel().getComponentCount());
//				mainFrame.getStepOneCenterTabbedPane().getButtonPanel().setVisible(false);
//				mainFrame.getStepOneCenterTabbedPane().getButtonPanel().updateUI();
//				mainFrame.getStepOneCenterTabbedPane().getButtonPanel().repaint();
//				mainFrame.getStepOneCenterTabbedPane().getButtonPanel().setVisible(true);
				
				mainFrame.getCenterTabPanel().add(mainFrame.getStepOneCenterTabbedPane());	
				
				ClearAttributePanel();
				attributePanel.add(mainFrame.getAttributePartPanel());
				
				mainFrame.getAttributePartPanel().getNamepanel().removeAll();
			    mainFrame.getAttributePartPanel().getNamepanel().add(mainFrame.getAttributePartPanel().getSteponenamelabel());
				
			    mainFrame.getAttributePartPanel().getAttributepanel().removeAll();
			    StepOneScrollTree=new JScrollPane(mainFrame.getAttributePartPanel().getSteponeattributetree());
			    StepOneScrollTree.setBorder(null);
			    StepOneScrollTree.setBackground(new Color(255, 255, 255));
			    mainFrame.getAttributePartPanel().getAttributepanel().add(StepOneScrollTree);
			    
				mainFrame.getConsolePartPanel().getTitlelabel().setText("UML模型建立过程信息");
				mainFrame.getConsolePartPanel().getTextpanel().removeAll();
				StepOneArea.setEditable(false);
				StepOneArea.getCaret().addChangeListener(new ChangeListener()   {
		            public void stateChanged(ChangeEvent e)   {
		            	StepOneArea.getCaret().setVisible(true);   //使Text区的文本光标显示
		            }
		        });
//				StepOneArea.setEnabled(true);
//				StepOneArea.setFocusable(true);
//				StepOneArea.setBorder(null);
				mainFrame.getConsolePartPanel().setTextarea(StepOneArea);
				JScrollPane StepOneScrollArea=new JScrollPane(StepOneArea);
				StepOneScrollArea.setBorder(null);
			    mainFrame.getConsolePartPanel().getTextpanel().add(StepOneScrollArea);
//				mainFrame.getConsolePartPanel().getTextarea().setText("");
				StepOneArea.append("UML模型正在建立中......\n");
			    //对点开的xml文件的处理 by tan
//			    IFile selectedFile=null;
//			    ExtensionFilter[] filters = fileNamingService.getFileFilters();
//			    IFileReader fileOpener;
//				try {
//					fileOpener = fileChooserService.chooseAndGetFileReader(filters);
//					selectedFile = fileOpener.getFileDefinition();
//					boolean flag=!(selectedFile.getFilename().contains("EA"));//是EA格式的文件
//					//如果是平台保存的XML文件
//					IGraphFile graphFile = null;
//					//增加转换的方法11
//					selectedFile= new FileMenu(mainFrame).exchangeFile(selectedFile, graphFile , flag);
//				
//					IFileReader file1 = fileChooserService.getFileReader(selectedFile);
//					InputStream in = file1.getInputStream();
//					//通过流获得文件的内容
//					jta.append(new XHTMLPersistenceService().getInputStreamContent(in));
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				consolePart.add(new ConsoleMessageTabbedPane("详细信息",new JTextArea("UML模型正在建立中......\n\n\n\n\n\n")));
				
				setstepbuttonpanelrepaint();
				step1buttonpanel.setBackground(new Color(53,55,59));
				
				wakeupUI();
				mainFrame.setVisible(false);
				mainFrame.getContentPane().repaint();
				mainFrame.setVisible(true);
				step2button.setEnabled(true);//第一步点击之后，第二步可点击
			}
		});
		step2button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearSelection();
										
//				step2button.setForeground(Color.RED);
				//标题
				JLabel jLabel=new JLabel();
				jLabel.setText(step2button.getText());
				jLabel.setFont(new Font("宋体", Font.BOLD, 20));
				jLabel.setForeground(Color.white);
				JPanel labelpanel=mainFrame.getStepJLabel();
				labelpanel.setLayout(new GridBagLayout());
				labelpanel.removeAll();
//				labelpanel.add(jLabel,new GBC(0, 0).setWeight(1, 0));
				labelpanel.add(Twostart,new GBC(0, 1));
//				labelpanel.add(Buttonstop,new GBC(0, 2));
				
				mainFrame.getCenterPanel().removeAll();
				mainFrame.getCenterPanel().add(mainFrame.getJs1());
				
				ClearOpreationPanel();
			    operationPanel.add(mainFrame.getModelTransformationPanel());
			    
			    mainFrame.getConsolePartPanel().getTitlelabel().setText("UML模型转化时间自动机过程信息");
			    mainFrame.getConsolePartPanel().getTextpanel().removeAll();
			    StepTwoArea.setEditable(false);
			    StepTwoArea.getCaret().addChangeListener(new ChangeListener()   {
		            public void stateChanged(ChangeEvent e)   {
		            	StepTwoArea.getCaret().setVisible(true);   //使Text区的文本光标显示
		            }
		        });
			    mainFrame.getConsolePartPanel().setTextarea(StepTwoArea);
			    mainFrame.getConsolePartPanel().getTextpanel().add(new JScrollPane(StepTwoArea));
			    
			    ClearAttributePanel();
			    attributePanel.add(mainFrame.getAttributePartPanel());
			    
			    mainFrame.getAttributePartPanel().getNamepanel().removeAll();
			    mainFrame.getAttributePartPanel().getNamepanel().add(mainFrame.getAttributePartPanel().getSteptwonamelabel());
			    
			    mainFrame.getAttributePartPanel().getAttributepanel().removeAll();
			    StepTwoScrollTree=new JScrollPane(mainFrame.getAttributePartPanel().getSteptwoattributetree());
			    StepTwoScrollTree.setBorder(null);
				StepTwoScrollTree.setBackground(new Color(255, 255, 255));
			    mainFrame.getAttributePartPanel().getAttributepanel().add(StepTwoScrollTree);
						
				mainFrame.getCenterTabPanel().removeAll();
				mainFrame.getCenterTabPanel().add(mainFrame.getStepTwoCenterTabbedPane());
			
				setstepbuttonpanelrepaint();
				step2buttonpanel.setBackground(new Color(53,55,59));
				index=2;

				mainFrame.setStepindex(2);
				wakeupUI();
				mainFrame.setVisible(false);
				mainFrame.getContentPane().repaint();
				mainFrame.setVisible(true);
				step3button.setEnabled(true);
			}
		});
		step3button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearSelection();
				
				mainFrame.getCenterPanel().removeAll();
				mainFrame.getCenterPanel().add(mainFrame.getJs1());
				
				mainFrame.setStepindex(3);
			 
//				step3button.setForeground(Color.RED);
			
				mainFrame.getCenterTabPanel().removeAll();
				
				mainFrame.getCenterTabPanel().add(mainFrame.getStepThreeCenterTabbedPane());
				
				JLabel jLabel=new JLabel();
				jLabel.setText(step3button.getText());
				jLabel.setFont(new Font("宋体", Font.BOLD, 20));
				jLabel.setForeground(Color.white);
				JPanel labelpanel=mainFrame.getStepJLabel();
				labelpanel.setLayout(new GridLayout(1, 1));
				labelpanel.removeAll();
//				labelpanel.add(jLabel,new GBC(0, 0).setWeight(1, 0));																			
				labelpanel.add(Threestart);
//				labelpanel.add(Buttonstop);		
				
				ClearOpreationPanel();
				operationPanel.add(mainFrame.getTestCaseGenerationPanel());	
				
//				clearConsolePart();		
				mainFrame.getConsolePartPanel().getTitlelabel().setText("抽象测试用例生成过程信息");
//				StepThreeArea=mainFrame.getConsolePartPanel().getTextarea();
//				StepThreeArea.setText("");
				mainFrame.getConsolePartPanel().getTextpanel().removeAll();
				StepThreeArea.setEditable(false);
				mainFrame.getConsolePartPanel().setTextarea(StepThreeArea);
			    mainFrame.getConsolePartPanel().getTextpanel().add(new JScrollPane(StepThreeArea));
//				consolePart.setTitle("抽象测试用例生成过程信息");
//			    consolePart.add(new ConsoleMessageTabbedPane("详细信息",StepThreeArea));	
			    
//			    ClearAttributePanel();
//			    attributePanel.add(mainFrame.getAttributePartPanel());
//			    
//			    mainFrame.getAttributePartPanel().getNamepanel().removeAll();
//			    mainFrame.getAttributePartPanel().getNamepanel().add(mainFrame.getAttributePartPanel().getStepthreenamelabel());
//			    
//			    mainFrame.getAttributePartPanel().getAttributepanel().removeAll();
//			    StepThreeScrollTree=new JScrollPane(mainFrame.getAttributePartPanel().getStepthreeattributetree());
//			    StepThreeScrollTree.setBorder(null);
//			    StepThreeScrollTree.setBackground(new Color(255, 255, 255));
//			    mainFrame.getAttributePartPanel().getAttributepanel().add(StepThreeScrollTree);
			    
			    ClearAttributePanel();
			    attributePanel.add(mainFrame.getAbstractTestCaseResultPanel());
			    
			    wakeupUI();
			    mainFrame.setVisible(false);
				mainFrame.getContentPane().repaint();
				mainFrame.setVisible(true);
				step4button.setEnabled(true);
				step5button.setEnabled(true);
			}
		});
		step4button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearSelection();
				
				mainFrame.getCenterPanel().removeAll();
				mainFrame.getCenterPanel().add(mainFrame.getJs1());
				
				mainFrame.setStepindex(4);
			
//				step4button.setForeground(Color.RED);
				mainFrame.getMainPanel().remove(mainFrame.getWelcomePanel());
				
				JLabel jLabel=new JLabel();
				jLabel.setText(step4button.getText());
				jLabel.setFont(new Font("宋体", Font.BOLD, 20));
				jLabel.setForeground(Color.white);
				JPanel labelpanel=mainFrame.getStepJLabel();
				labelpanel.setLayout(new GridLayout(1, 1));
				labelpanel.removeAll();
//				labelpanel.add(jLabel,new GBC(0, 0).setWeight(1, 0));
				labelpanel.add(Fourstart);
//				labelpanel.add(Buttonstop);
				
				ClearOpreationPanel();
				operationPanel.add(mainFrame.getTestCaseInstantiationPanel());
				
				mainFrame.getCenterTabPanel().removeAll();
				mainFrame.getCenterTabPanel().add(mainFrame.getStepFourCenterTabbedPane());
				
//				clearConsolePart();	
				mainFrame.getConsolePartPanel().getTitlelabel().setText("抽象测试用例实例化过程信息");
//				StepFourArea=mainFrame.getConsolePartPanel().getTextarea();
//				StepFourArea.setText("");
				mainFrame.getConsolePartPanel().getTextpanel().removeAll();
				StepFourArea.setEditable(false);
				mainFrame.getConsolePartPanel().setTextarea(StepFourArea);
			    mainFrame.getConsolePartPanel().getTextpanel().add(new JScrollPane(StepFourArea));
//				consolePart.setTitle("抽象测试用例实例化过程信息");
//				consolePart.add(new ConsoleMessageTabbedPane("详细信息",StepFourArea));
			    
			    ClearAttributePanel();
			    attributePanel.add(mainFrame.getTestCaseInstantiationResultPanel());
			    
			    wakeupUI();
				mainFrame.setVisible(false);
				mainFrame.getContentPane().repaint();
				mainFrame.setVisible(true);
				step5button.setEnabled(true);
			}
		});
		step5button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearSelection();		
				
				mainFrame.getCenterPanel().removeAll();
				mainFrame.getCenterPanel().add(mainFrame.getJs1());
				
				mainFrame.setStepindex(5);
				
//				step5button.setForeground(Color.RED);
				mainFrame.getMainPanel().remove(mainFrame.getWelcomePanel());
				
				JLabel jLabel=new JLabel();
				jLabel.setText(step5button.getText());
				jLabel.setFont(new Font("宋体", Font.BOLD, 20));
				jLabel.setForeground(Color.white);
				JPanel labelpanel=mainFrame.getStepJLabel();
				labelpanel.setLayout(new GridLayout(1, 1));
				labelpanel.removeAll();
//				labelpanel.add(jLabel,new GBC(0, 0).setWeight(1, 0));
				labelpanel.add(Fivestart);
//				labelpanel.add(Buttonstop);
				
				ClearOpreationPanel();
				operationPanel.add(mainFrame.getTestCaseConfirmationPanel());
				
				
				mainFrame.getCenterTabPanel().removeAll();
				mainFrame.getCenterTabPanel().add(mainFrame.getStepFiveCenterTabbedPane());
//				clearConsolePart();
			
				mainFrame.getConsolePartPanel().getTitlelabel().setText("测试用例实例验证过程信息");
//				StepFiveArea=mainFrame.getConsolePartPanel().getTextarea();
//				StepFiveArea.setText("");
				mainFrame.getConsolePartPanel().getTextpanel().removeAll();
				StepFiveArea.setEditable(false);
				mainFrame.getConsolePartPanel().setTextarea(StepFiveArea);
			    mainFrame.getConsolePartPanel().getTextpanel().add(new JScrollPane(StepFiveArea));
//				consolePart.setTitle("测试用例实例验证过程信息");
//    			consolePart.add(new ConsoleMessageTabbedPane("详细信息",StepFiveArea));	
			    
			    ClearAttributePanel();
			    attributePanel.add(mainFrame.getTestCaseConfirmResultPanel());
							
			    wakeupUI();
				mainFrame.setVisible(false);
				mainFrame.getContentPane().repaint();
				mainFrame.setVisible(true);
			}
		});
		//测试按钮的触发事件
		step6button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearSelection();		
				
				mainFrame.getCenterPanel().removeAll();
				mainFrame.getCenterPanel().add(mainFrame.getJs1());
				
				mainFrame.setStepindex(6);
				
//				step5button.setForeground(Color.RED);
				mainFrame.getMainPanel().remove(mainFrame.getWelcomePanel());
				
				JLabel jLabel=new JLabel();
				jLabel.setText(step6button.getText());
				jLabel.setFont(new Font("宋体", Font.BOLD, 20));
				jLabel.setForeground(Color.white);
				JPanel labelpanel=mainFrame.getStepJLabel();
				labelpanel.setLayout(new GridLayout(1, 1));
				labelpanel.removeAll();
//				labelpanel.add(jLabel,new GBC(0, 0).setWeight(1, 0));
				labelpanel.add(Sixstart);
//				labelpanel.add(Buttonstop);
				//labelpanel.add(new JButton("暂停"),new GBC(2, 0));
				
				ClearOpreationPanel();
			    operationPanel.add(mainFrame.getModelExistValidationPanel());
				
				mainFrame.getCenterTabPanel().removeAll();
				mainFrame.getCenterTabPanel().add(mainFrame.getStepSixCenterTabbedPane());
//				clearConsolePart();
			
				mainFrame.getConsolePartPanel().getTitlelabel().setText("一致性的验证");
				mainFrame.getConsolePartPanel().getTextpanel().removeAll();
				StepSixArea.setEditable(false);
				mainFrame.getConsolePartPanel().setTextarea(StepSixArea);
			    mainFrame.getConsolePartPanel().getTextpanel().add(new JScrollPane(StepSixArea));
							
			    ClearAttributePanel();
			    attributePanel.add(mainFrame.getValidationResultPanel());
			    
			    wakeupUI();
				mainFrame.setVisible(false);
				mainFrame.getContentPane().repaint();
				mainFrame.setVisible(true);
				
			}
		});
	}
	protected void StartThread() {
		// TODO Auto-generated method stub
		
		Thread thread1=new Thread(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=1;i<100;i++){
//					mainFrame.getStepFourCenterTabbedPane().getTestCaseInstantiationTabbedPanel().getProgressbar().setValue(i);
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		};
		
		Thread thread2=new Thread(){

			@Override
			public void run() {
				
//				List<RealTestCaseVO> list = ConsolePartDataTestDao.getRealTestCaseList();
//				System.out.println("list.size()"+list.size());
				
				// TODO Auto-generated method stub
//					try {
						mainFrame.getStepFourCenterTabbedPane().getTestCaseInstantiationTabbedPanel().getTabelscrollpanel().getViewport().add(new ConsolePartDetailInfoTable(1));
						mainFrame.getStepFourCenterTabbedPane().getTestCaseInstantiationTabbedPanel().getTabelscrollpanel().getViewport().repaint();
//						sleep(100);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
				}
			
		};
		
		thread1.start();
		thread2.start();
		
	}

	/*
	 * 使原来不可见的界面重新可见(除了首页,都需要重新可见,首页已使其他界面不可见)
	 */
	public void wakeupUI()
	{
		mainFrame.getConsolePartPanel().setVisible(true);
		mainFrame.getOpreationPart().setVisible(true);
		mainFrame.getOneTouchExpandablePanel().setVisible(true);
	}

	public JButton getButtonstop() {
		return Buttonstop;
	}

	public JButton getTwostart() {
		return Twostart;
	}

	public List<PerformanceTestCaseReportPartPanel> getTestcasereportlist() {
		return testcasereportlist;
	}
	
	public List<TestCase> extractDataToXml(){
		
		int i=1,j=1;
		
		List<TestCase> testcaseList = new ArrayList<TestCase>();
		List<myProcess> processList = new ArrayList<myProcess>();
		
		SAXReader reader = new SAXReader();
		
		String path="D:\\rc_loopForXStream1.0.1.xml";
		
		File file=new File(path);
		
		try {
			
			Document dom = reader.read(file);
			
			Element TCS=dom.getRootElement();
			List<Element> testcaseElements=TCS.elements("testcase");
			for(Element testcase:testcaseElements){
				
//				System.out.println(i++);
				
				List<Element> processElements=testcase.elements("process");
				
				for(Element process:processElements){
					
//					System.out.println(j++);
					
					Element operation=process.element("operation");
//					System.out.println(operation.getData());
					
					Element input=process.element("input");
//					System.out.println(input.getData());
					
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
		
//		System.out.println(testcaseList.size());
//		
//		for(TestCase tc:testcaseList){
//			
//			System.out.println(tc);
//			
//		}
		return testcaseList;
		
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
				while ((lineTxt = bufferedReader.readLine()) != null) {

					if (startendstate == 1) {

						processList = new ArrayList<myProcess>();
						startendstate = 0;

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
						processExec = Boolean
								.valueOf(process.substring(process.indexOf("processExec="), process.length()));

						myProcess p = new myProcess();
						p.setProcessID(processID);
						p.setProcessName(processName);
						p.setProcessParam(processParam);
						p.setProcessStatus(processStatus);
						p.setProcessExec(processExec);

						processList.add(p);

					} else if (lineTxt.substring(0, 2).equals(", ")) {

						state = lineTxt.substring(lineTxt.indexOf("state=") + 6, lineTxt.indexOf(", result="));
						result = lineTxt.substring(lineTxt.indexOf("result=") + 7, lineTxt.indexOf(", detail="));

					} else if (lineTxt.substring(lineTxt.length() - 2, lineTxt.length()).equals("]]")) {

						TestCase tc = new TestCase();
						tc.setTestCaseID(testCaseID);
						tc.setProcessList(processList);
						tc.setState(state);
//						tc.setResult(result);

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

	public JButton getStep1button() {
		return step1button;
	}
	
	public JButton getStep2button() {
		return step2button;
	}

	
	
}

package com.horstmann.violet.application.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import com.horstmann.violet.application.gui.stepCenterTabbedPane.FixedButtonTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.FunctionalTestCaseChartTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.PerformanceTestCaseChartTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.TestCaseDataPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.TestCaseReportTabbedPanel;

public class StepFiveCenterTabbedPane extends JPanel{
	
	private MainFrame mainFrame;
	
	private JPanel buttonPanel;
	public JPanel diagramPanel;
	
	private JPanel buttonTabbedPanel;
	private JScrollPane buttonScrollPanel;
	private JButton leftButton;
	private JButton rightButton;

	private JButton testCaseReportDiagramButton;
	private FixedButtonTabbedPanel testCaseReportDiagramButtonPanel;
	private TestCaseReportTabbedPanel testCaseReportTabbedPane;
	
	private JButton testCaseChartDiagramButton;
	private FixedButtonTabbedPanel testCaseChartDiagramButtonPanel;
	private JPanel testCaseChartTabbedPanel;
	
	private PerformanceTestCaseChartTabbedPanel performanceTestCaseChartTabbedPanel;
	private FunctionalTestCaseChartTabbedPanel functionalTestCaseChartTabbedPanel;
	
	private List<FixedButtonTabbedPanel> testCaseDiagramButtonPanelList=new ArrayList<FixedButtonTabbedPanel>();
	
	private List<TestCaseDataPanel> testCaseDataPanelList=new ArrayList<TestCaseDataPanel>();
	
	private int selectedIndex=1;
	
	private FixedButtonTabbedPanel selectedFixedButtonTabbedPanel;
	
	private static String BecomeRunFileName=null;
	private static int BecomeRunFileNameType=-1;
	
	public StepFiveCenterTabbedPane(MainFrame mainFrame)
	{
		
		this.mainFrame = mainFrame;
		
//		testCaseReportTabbedPane=new TestCaseReportTabbedPanel(mainFrame);
		
		testCaseChartTabbedPanel=new JPanel();
		testCaseChartTabbedPanel.setLayout(new GridLayout());
		
		performanceTestCaseChartTabbedPanel=new PerformanceTestCaseChartTabbedPanel(mainFrame);
		functionalTestCaseChartTabbedPanel =new FunctionalTestCaseChartTabbedPanel(mainFrame);
		
//		testCasePieChartTabbedPane=new JPanel();
//		testCasePieChartTabbedPane.setLayout(new GridLayout());
//		
//		testCaseBarChartTabbedPane=new JPanel();
//		testCaseBarChartTabbedPane.setLayout(new GridLayout());
//		
//		testCaseLineChartTabbedPane=new JPanel();
//		testCaseLineChartTabbedPane.setLayout(new GridLayout());
		
		selectedFixedButtonTabbedPanel=null;
		
		buttonPanel=new JPanel();
		diagramPanel=new JPanel();
		
		buttonTabbedPanel=new JPanel();
		
		initbuttonpanel();
		
		initdiagrampanel();
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(buttonPanel);
		this.add(diagramPanel);
		layout.setConstraints(buttonPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(diagramPanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();
		
		this.setMinimumSize(new Dimension(screenWidth/2, screenHeight/2));
		
//		testcaseFile=new JPanel();
//		this.add("测试用例实例化测试报告",testcaseFile);	
//		testcaseFile1=new JPanel();
//		this.add("测试用例实例化测试报告",testcaseFile1);	
	}

	private void initbuttonpanel() {
		// TODO Auto-generated method stub
		
		initleftrightbuttonpanel();
		
		testCaseReportDiagramButtonPanel=new FixedButtonTabbedPanel("测试用例实例化测试进程");
		testCaseReportDiagramButtonPanel.setBackground(new Color(58, 105, 190));
		testCaseReportDiagramButton=testCaseReportDiagramButtonPanel.getTabbedbutton();
		
		testCaseChartDiagramButtonPanel=new FixedButtonTabbedPanel("测试用例实例化测试报告");
		testCaseChartDiagramButtonPanel.setBackground(new Color(77, 96, 130));
		testCaseChartDiagramButton=testCaseChartDiagramButtonPanel.getTabbedbutton();
		
//		testCasePieChartDiagramButtonPanel=new FixedButtonTabbedPanel("测试用例实例化测试报表");
//		testCasePieChartDiagramButtonPanel.setBackground(new Color(77, 96, 130));
//		testCasePieChartDiagramButton=testCasePieChartDiagramButtonPanel.getTabbedbutton();
//		
//		testCaseBarChartDiagramButtonPanel=new FixedButtonTabbedPanel("测试用例实例化测试报表");
//		testCaseBarChartDiagramButtonPanel.setBackground(new Color(77, 96, 130));
//		testCaseBarChartDiagramButton=testCaseBarChartDiagramButtonPanel.getTabbedbutton();
//		
//		testCaseLineChartDiagramButtonPanel=new FixedButtonTabbedPanel("测试用例实例化测试报表");
//		testCaseLineChartDiagramButtonPanel.setBackground(new Color(77, 96, 130));
//		testCaseLineChartDiagramButton=testCaseLineChartDiagramButtonPanel.getTabbedbutton();
		
		
		buttonTabbedPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		buttonTabbedPanel.setBackground(new Color(41, 57, 85));
		
//		buttonTabbedPanel.add(testCaseReportDiagramButtonPanel);
//		buttonTabbedPanel.add(testCaseChartDiagramButtonPanel);
//		buttonPanel.add(testCasePieChartDiagramButtonPanel);
//		buttonPanel.add(testCaseBarChartDiagramButtonPanel);
//		buttonPanel.add(testCaseLineChartDiagramButtonPanel);
		
		setButtonActionListener();
		
		testCaseChartDiagramButtonPanel.setVisible(false);
		
		buttonScrollPanel=new JScrollPane(buttonTabbedPanel);
		buttonScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		buttonScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		buttonScrollPanel.setBorder(null);

		buttonPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(58, 105, 190)));
		buttonPanel.setBackground(new Color(41, 57, 85));
		buttonPanel.setPreferredSize(new Dimension(300, 23));
		buttonPanel.setMinimumSize(new Dimension(300, 23));
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.add(leftButton, BorderLayout.WEST);
		buttonPanel.add(buttonScrollPanel, BorderLayout.CENTER);
		buttonPanel.add(rightButton, BorderLayout.EAST);
		
	}
	
	private void initleftrightbuttonpanel() {
		// TODO Auto-generated method stub
		
		leftButton=new JButton();
		rightButton=new JButton();
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		final ImageIcon icon1 = new ImageIcon(this.getClass().getResource("ImagePart/left1.png"));
		icon1.setImage(icon1.getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
		final ImageIcon icon2 = new ImageIcon(this.getClass().getResource("ImagePart/right1.png"));
		icon2.setImage(icon2.getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
		final ImageIcon icon3 = new ImageIcon(this.getClass().getResource("ImagePart/left3.png"));
		icon1.setImage(icon1.getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
		final ImageIcon icon4 = new ImageIcon(this.getClass().getResource("ImagePart/right3.png"));
		icon2.setImage(icon2.getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
		
		leftButton.setIcon(icon1);
		leftButton.setFocusable(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setBorderPainted(false);
//		leftButton.addMouseListener(new ButtonMouseListener());
		leftButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				leftButton.setIcon(icon3);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				leftButton.setIcon(icon1);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				leftButton.setIcon(icon3);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		leftButton.setPreferredSize(new Dimension(21,21));
		leftButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(41, 57, 85)));
		leftButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JScrollBar bar=buttonScrollPanel.getHorizontalScrollBar();
				bar.setValue(bar.getValue()-100);
			}
		});
		
		rightButton.setIcon(icon2);
		rightButton.setFocusable(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setBorderPainted(false);
//		rightButton.addMouseListener(new ButtonMouseListener());
		rightButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				rightButton.setIcon(icon4);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				rightButton.setIcon(icon2);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				rightButton.setIcon(icon4);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		rightButton.setPreferredSize(new Dimension(21,21));
		rightButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(41, 57, 85)));
		rightButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JScrollBar bar=buttonScrollPanel.getHorizontalScrollBar();
				bar.setValue(bar.getValue()+100);
			}
		});
		
	}

	private void setButtonActionListener() {
		// TODO Auto-generated method stub
		
		testCaseReportDiagramButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				selectedIndex=1;
				getDiagramPanel().removeAll();
				getDiagramPanel().setLayout(new GridLayout());
				getDiagramPanel().add(testCaseReportTabbedPane);
				
				testCaseReportDiagramButtonPanel.setBackground(new Color(58, 105, 190));
				testCaseChartDiagramButtonPanel.setBackground(new Color(77, 96, 130));
				
				if(mainFrame.getStepindex()==5){
					ChangeRepaint();
				}
				
			}
		});
		
		testCaseChartDiagramButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				selectedIndex=2;
				getDiagramPanel().removeAll();
				getDiagramPanel().setLayout(new GridLayout());
				getDiagramPanel().add(testCaseChartTabbedPanel);
				
				testCaseChartDiagramButtonPanel.setBackground(new Color(58, 105, 190));
				testCaseReportDiagramButtonPanel.setBackground(new Color(77, 96, 130));
				
				ChangeRepaint();
				
			}
		});
		
//		testCasePieChartDiagramButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				
//				selectedIndex=2;
//				getDiagramPanel().removeAll();
//				getDiagramPanel().setLayout(new GridLayout());
//				getDiagramPanel().add(testCasePieChartTabbedPane);
//				
////				initWeb();
//				
////				getDiagramPanel().add(webframe.getContentPane());
//				
//				
//				testCasePieChartDiagramButtonPanel.setBackground(new Color(58, 105, 190));
//				testCaseReportDiagramButtonPanel.setBackground(new Color(77, 96, 130));
//				testCaseBarChartDiagramButtonPanel.setBackground(new Color(77, 96, 130));
//				testCaseLineChartDiagramButtonPanel.setBackground(new Color(77, 96, 130));
//				
//				ChangeRepaint();
//				
//			}
//		});
//		
//		testCaseBarChartDiagramButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				
//				selectedIndex=3;
//				getDiagramPanel().removeAll();
//				getDiagramPanel().setLayout(new GridLayout());
//				getDiagramPanel().add(testCaseBarChartTabbedPane);
//				
//				testCaseBarChartDiagramButtonPanel.setBackground(new Color(58, 105, 190));
//				testCaseReportDiagramButtonPanel.setBackground(new Color(77, 96, 130));
//				testCasePieChartDiagramButtonPanel.setBackground(new Color(77, 96, 130));
//				testCaseLineChartDiagramButtonPanel.setBackground(new Color(77, 96, 130));
//				
//				ChangeRepaint();
//				
//			}
//		});
//		
//		testCaseLineChartDiagramButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				
//				selectedIndex=4;
//				getDiagramPanel().removeAll();
//				getDiagramPanel().setLayout(new GridLayout());
//				getDiagramPanel().add(testCaseLineChartTabbedPane);
//				
//				testCaseLineChartDiagramButtonPanel.setBackground(new Color(58, 105, 190));
//				testCaseReportDiagramButtonPanel.setBackground(new Color(77, 96, 130));
//				testCasePieChartDiagramButtonPanel.setBackground(new Color(77, 96, 130));
//				testCaseBarChartDiagramButtonPanel.setBackground(new Color(77, 96, 130));
//				
//				ChangeRepaint();
//				
//			}
//		});
		
	}

	private void initdiagrampanel() {
		// TODO Auto-generated method stub
		
		diagramPanel.setLayout(new GridLayout());
//		diagramPanel.add(testCaseReportTabbedPane);
		diagramPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(142, 155, 188)));
		
	}
	
	public void ChangeDiagramButtonPanelColor(){
		
		for(FixedButtonTabbedPanel fbtpanel:testCaseDiagramButtonPanelList){
			fbtpanel.setBackground(new Color(77, 96, 130));
		}
		
	}
	
	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}
	
	public void initUIPanelData(){
		
		buttonTabbedPanel.removeAll();
		diagramPanel.removeAll();
		
		testCaseDiagramButtonPanelList=new ArrayList<FixedButtonTabbedPanel>();
		testCaseDataPanelList=new ArrayList<TestCaseDataPanel>();
		selectedFixedButtonTabbedPanel=null;
		
	}

	public JPanel getDiagramPanel() {
		return diagramPanel;
	}

	public void setDiagramPanel(JPanel diagramPanel) {
		this.diagramPanel = diagramPanel;
	}

	public JButton getTestCaseReportDiagramButton() {
		return testCaseReportDiagramButton;
	}

	public FixedButtonTabbedPanel getTestCaseReportDiagramButtonPanel() {
		return testCaseReportDiagramButtonPanel;
	}

	public TestCaseReportTabbedPanel getTestCaseReportTabbedPane() {
		return testCaseReportTabbedPane;
	}
	
	public JButton getTestCaseChartDiagramButton() {
		return testCaseChartDiagramButton;
	}

	public FixedButtonTabbedPanel getTestCaseChartDiagramButtonPanel() {
		return testCaseChartDiagramButtonPanel;
	}

	public JPanel getTestCaseChartTabbedPanel() {
		return testCaseChartTabbedPanel;
	}

	public PerformanceTestCaseChartTabbedPanel getPerformanceTestCaseChartTabbedPanel() {
		return performanceTestCaseChartTabbedPanel;
	}
	
	public FunctionalTestCaseChartTabbedPanel getFunctionalTestCaseChartTabbedPanel() {
		return functionalTestCaseChartTabbedPanel;
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}

	public JPanel getButtonTabbedPanel() {
		return buttonTabbedPanel;
	}

	public List<FixedButtonTabbedPanel> getTestCaseDiagramButtonPanelList() {
		return testCaseDiagramButtonPanelList;
	}

	public List<TestCaseDataPanel> getTestCaseDataPanelList() {
		return testCaseDataPanelList;
	}

	public FixedButtonTabbedPanel getSelectedFixedButtonTabbedPanel() {
		return selectedFixedButtonTabbedPanel;
	}

	public void setSelectedFixedButtonTabbedPanel(FixedButtonTabbedPanel selectedFixedButtonTabbedPanel) {
		this.selectedFixedButtonTabbedPanel = selectedFixedButtonTabbedPanel;
	}

	public static String getBecomeRunFileName() {
		return BecomeRunFileName;
	}

	public static void setBecomeRunFileName(String becomeRunFileName) {
		BecomeRunFileName = becomeRunFileName;
	}

	public static int getBecomeRunFileNameType() {
		return BecomeRunFileNameType;
	}

	public static void setBecomeRunFileNameType(int becomeRunFileNameType) {
		BecomeRunFileNameType = becomeRunFileNameType;
	}
	
}

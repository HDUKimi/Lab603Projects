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
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.horstmann.violet.application.gui.stepCenterTabbedPane.FixedButtonTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.TestCaseConstraintTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.TestCaseCoverTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.TestCaseInstantiationTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.TestCaseProcessTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.TestCaseProduceTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.TestCaseShowTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.TestCaseSortContrastTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.TestCaseUppaalTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.UppaalOptimizationTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.UppaalParseInforTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.UppaalRemoveMigrateTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.UppaalSplitStateTabbedPanel;
import com.horstmann.violet.application.gui.util.xiaole.UppaalTransfrom.ImportByDoubleClick;
import com.horstmann.violet.framework.file.GraphFile;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;

public class StepThreeCenterTabbedPane extends JPanel {

	private MainFrame mainFrame;
	private IWorkspace workspace=null;
	
	private JPanel buttonPanel;
	public JPanel diagramPanel;
	
	private JPanel buttonTabbedPanel;
	private JScrollPane buttonScrollPanel;
	private JButton leftButton;
	private JButton rightButton;
	
	private int fixButtonTabbedPanelSelectedIndex=0;
	private List<FixedButtonTabbedPanel> fixButtonTabbedPanelList=new ArrayList<FixedButtonTabbedPanel>();

	private FixedButtonTabbedPanel testCaseProcessButtonPanel;
	private FixedButtonTabbedPanel uppaalParseInforButtonPanel;
	private FixedButtonTabbedPanel uppaalOptimizationButtonPanel;
	private FixedButtonTabbedPanel uppaalSplitStateButtonPanel;
	private FixedButtonTabbedPanel uppaalRemoveMigrateButtonPanel;
	private FixedButtonTabbedPanel testCaseConstraintButtonPanel;
	private FixedButtonTabbedPanel testCaseUppaalButtonPanel;
	private FixedButtonTabbedPanel testCaseCoverButtonPanel;
	private FixedButtonTabbedPanel testCaseSortContrastButtonPanel;
	private FixedButtonTabbedPanel testCaseProduceButtonPanel;
	private FixedButtonTabbedPanel testCaseInstantiationButtonPanel;
	private FixedButtonTabbedPanel testCaseShowButtonPanel;
	
	private JButton testCaseProcessButton;
	private JButton uppaalParseInforButton;
	private JButton uppaalOptimizationButton;
	private JButton uppaalSplitStateButton;
	private JButton uppaalRemoveMigrateButton;
	private JButton testCaseConstraintButton;
	private JButton testCaseUppaalButton;
	private JButton testCaseCoverButton;
	private JButton testCaseSortContrastButton;
	private JButton testCaseProduceButton;
	private JButton testCaseInstantiationButton;
	private JButton testCaseShowButton;
	
	private TestCaseProcessTabbedPanel testCaseProcessTabbedPanel;
	private UppaalParseInforTabbedPanel uppaalParseInforTabbedPanel;
	private UppaalOptimizationTabbedPanel uppaalOptimizationTabbedPanel;
	private UppaalSplitStateTabbedPanel uppaalSplitStateTabbedPanel;
	private UppaalRemoveMigrateTabbedPanel uppaalRemoveMigrateTabbedPanel; 
	private TestCaseConstraintTabbedPanel testCaseConstraintTabbedPanel;
	private TestCaseUppaalTabbedPanel testCaseUppaalTabbedPanel;
	private TestCaseCoverTabbedPanel testCaseCoverTabbedPanel;
	private TestCaseSortContrastTabbedPanel testCaseSortContrastTabbedPanel;
	private TestCaseProduceTabbedPanel testCaseProduceTabbedPanel;
	private TestCaseInstantiationTabbedPanel testCaseInstantiationTabbedPanel;
	private TestCaseShowTabbedPanel testCaseShowTabbedPanel;
	
	
	public StepThreeCenterTabbedPane(MainFrame mainFrame) {
		
		this.mainFrame = mainFrame;
		
		testCaseProcessTabbedPanel=new TestCaseProcessTabbedPanel(mainFrame);
		uppaalParseInforTabbedPanel=new UppaalParseInforTabbedPanel(mainFrame);
		uppaalOptimizationTabbedPanel=new UppaalOptimizationTabbedPanel(mainFrame);
		uppaalSplitStateTabbedPanel=new UppaalSplitStateTabbedPanel(mainFrame);
		uppaalRemoveMigrateTabbedPanel=new UppaalRemoveMigrateTabbedPanel(mainFrame);
		testCaseConstraintTabbedPanel=new TestCaseConstraintTabbedPanel(mainFrame);
		testCaseUppaalTabbedPanel=new TestCaseUppaalTabbedPanel(mainFrame,workspace);
		testCaseCoverTabbedPanel=new TestCaseCoverTabbedPanel(mainFrame,workspace);
		testCaseSortContrastTabbedPanel=new TestCaseSortContrastTabbedPanel(mainFrame,workspace);
		testCaseProduceTabbedPanel=new TestCaseProduceTabbedPanel(mainFrame);
		testCaseInstantiationTabbedPanel=new TestCaseInstantiationTabbedPanel(mainFrame);
		testCaseShowTabbedPanel=new TestCaseShowTabbedPanel(mainFrame);

		buttonPanel = new JPanel();
		diagramPanel = new JPanel();
		diagramPanel.setLayout(new GridLayout());

		buttonTabbedPanel=new JPanel();

		// initButton();

		initbuttonpanel();
		
		initdiagrampanel();
		
//		initTestCaseUppaalPanel();
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(buttonPanel);
		this.add(diagramPanel);
		layout.setConstraints(buttonPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(diagramPanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();

		this.setMinimumSize(new Dimension(screenWidth / 2, screenHeight / 2));

	}
	
	private void initdiagrampanel() {
		// TODO Auto-generated method stub
		
		diagramPanel.setLayout(new GridLayout());
		diagramPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(142, 155, 188)));
		diagramPanel.add(testCaseProcessTabbedPanel);
		
	}

	private void initTestCaseUppaalPanel() {
		// TODO Auto-generated method stub
		
		GraphFile absfGraphFile=ImportByDoubleClick.importFileByDoubleClick("UPPAAL","abs.uppaal.violet.xml");
		IWorkspace workspace1=new Workspace(absfGraphFile);
		TestCaseUppaalTabbedPanel tcut=new TestCaseUppaalTabbedPanel(mainFrame, workspace1);
		setTestCaseUppaalTabbedPanel(tcut);
		IWorkspace workspace2=new Workspace(absfGraphFile);
		TestCaseCoverTabbedPanel tcct=new TestCaseCoverTabbedPanel(mainFrame, workspace2);
		setTestCaseCoverTabbedPanel(tcct);
		
	}

	private void initbuttonpanel() {
		// TODO Auto-generated method stub
		
		initleftrightbuttonpanel();

		testCaseProcessButtonPanel = new FixedButtonTabbedPanel("测试用例生成进程");
		testCaseProcessButton = testCaseProcessButtonPanel.getTabbedbutton();
		
		uppaalParseInforButtonPanel = new FixedButtonTabbedPanel("获取时间自动机");
		uppaalParseInforButton = uppaalParseInforButtonPanel.getTabbedbutton();
		
		uppaalOptimizationButtonPanel = new FixedButtonTabbedPanel("约简状态空间");
		uppaalOptimizationButton = uppaalOptimizationButtonPanel.getTabbedbutton();

		uppaalSplitStateButtonPanel = new FixedButtonTabbedPanel("符号状态拆分");
		uppaalSplitStateButton = uppaalSplitStateButtonPanel.getTabbedbutton();

		uppaalRemoveMigrateButtonPanel = new FixedButtonTabbedPanel("抽象时间延迟迁移去除");
		uppaalRemoveMigrateButton = uppaalRemoveMigrateButtonPanel.getTabbedbutton();

		testCaseConstraintButtonPanel = new FixedButtonTabbedPanel("抽象时间延迟迁移");
		testCaseConstraintButton = testCaseConstraintButtonPanel.getTabbedbutton();

		testCaseUppaalButtonPanel = new FixedButtonTabbedPanel("深度优先生成树");
		testCaseUppaalButton = testCaseUppaalButtonPanel.getTabbedbutton();
		
		testCaseCoverButtonPanel = new FixedButtonTabbedPanel("路径覆盖");
		testCaseCoverButton = testCaseCoverButtonPanel.getTabbedbutton();
		
		testCaseSortContrastButtonPanel=new FixedButtonTabbedPanel("对比");
		testCaseSortContrastButton=testCaseSortContrastButtonPanel.getTabbedbutton();

		testCaseProduceButtonPanel = new FixedButtonTabbedPanel("获取测试序列");
		testCaseProduceButton = testCaseProduceButtonPanel.getTabbedbutton();
		
		testCaseInstantiationButtonPanel = new FixedButtonTabbedPanel("实例化");
		testCaseInstantiationButton = testCaseInstantiationButtonPanel.getTabbedbutton();
		
		testCaseShowButtonPanel = new FixedButtonTabbedPanel("生成测试用例");
		testCaseShowButton = testCaseShowButtonPanel.getTabbedbutton();


		buttonTabbedPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		buttonTabbedPanel.setBackground(new Color(41, 57, 85));
		
		buttonTabbedPanel.add(testCaseProcessButtonPanel);
		buttonTabbedPanel.add(uppaalParseInforButtonPanel);
		buttonTabbedPanel.add(uppaalOptimizationButtonPanel);
//		buttonTabbedPanel.add(uppaalSplitStateButtonPanel);
//		buttonTabbedPanel.add(uppaalRemoveMigrateButtonPanel);
//		buttonTabbedPanel.add(testCaseConstraintButtonPanel);
		buttonTabbedPanel.add(testCaseUppaalButtonPanel);
		buttonTabbedPanel.add(testCaseCoverButtonPanel);
		buttonTabbedPanel.add(testCaseSortContrastButtonPanel);
		buttonTabbedPanel.add(testCaseProduceButtonPanel);
		buttonTabbedPanel.add(testCaseInstantiationButtonPanel);
		buttonTabbedPanel.add(testCaseShowButtonPanel);
		
		buttonScrollPanel=new JScrollPane(buttonTabbedPanel);
		buttonScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		buttonScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		buttonScrollPanel.setBorder(null);

		setButtonActionListener();
				
		fixButtonTabbedPanelList.add(testCaseProcessButtonPanel);
		fixButtonTabbedPanelList.add(uppaalParseInforButtonPanel);
		fixButtonTabbedPanelList.add(uppaalOptimizationButtonPanel);
//		fixButtonTabbedPanelList.add(uppaalSplitStateButtonPanel);
//		fixButtonTabbedPanelList.add(uppaalRemoveMigrateButtonPanel);
//		fixButtonTabbedPanelList.add(testCaseConstraintButtonPanel);
		fixButtonTabbedPanelList.add(testCaseUppaalButtonPanel);
		fixButtonTabbedPanelList.add(testCaseCoverButtonPanel);
		fixButtonTabbedPanelList.add(testCaseSortContrastButtonPanel);
		fixButtonTabbedPanelList.add(testCaseProduceButtonPanel);
		fixButtonTabbedPanelList.add(testCaseInstantiationButtonPanel);
		fixButtonTabbedPanelList.add(testCaseShowButtonPanel);

		setFixButtonTabbedPanelVisible();
		
		buttonPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(58, 105, 190)));
		buttonPanel.setBackground(new Color(41, 57, 85));
		buttonPanel.setPreferredSize(new Dimension(300, 23));
		buttonPanel.setMinimumSize(new Dimension(300, 23));
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.add(leftButton, BorderLayout.WEST);
		buttonPanel.add(buttonScrollPanel, BorderLayout.CENTER);
		buttonPanel.add(rightButton, BorderLayout.EAST);

	}

	private void setFixButtonTabbedPanelVisible() {
		// TODO Auto-generated method stub
		
		for(FixedButtonTabbedPanel fbtpanel:fixButtonTabbedPanelList){
			fbtpanel.setVisible(false);
		}
		testCaseProcessButtonPanel.setVisible(true);
		testCaseProcessButtonPanel.setBackground(new Color(58, 105, 190));
		fixButtonTabbedPanelSelectedIndex=0;
		
	}

	private void initleftrightbuttonpanel() {
		// TODO Auto-generated method stub
		
		leftButton=new JButton();
		rightButton=new JButton();
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		final ImageIcon icon1 = new ImageIcon(path + "left1.png");
		icon1.setImage(icon1.getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
		final ImageIcon icon2 = new ImageIcon(path + "right1.png");
		icon2.setImage(icon2.getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
		final ImageIcon icon3 = new ImageIcon(path + "left3.png");
		icon1.setImage(icon1.getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
		final ImageIcon icon4 = new ImageIcon(path + "right3.png");
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
		testCaseProcessButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getDiagramPanel().removeAll();
				getDiagramPanel().add(testCaseProcessTabbedPanel);

				ChangeAllButtonPanelState();
				testCaseProcessButtonPanel.setBackground(new Color(58, 105, 190));
				
				fixButtonTabbedPanelSelectedIndex=0;

				if(mainFrame.getStepindex()==3){
					ChangeRepaint();
				}
			}
		});
		uppaalParseInforButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getDiagramPanel().removeAll();
				getDiagramPanel().add(uppaalParseInforTabbedPanel);

				ChangeAllButtonPanelState();
				uppaalParseInforButtonPanel.setBackground(new Color(58, 105, 190));
				
				fixButtonTabbedPanelSelectedIndex=1;

				ChangeRepaint();
			}
		});
		uppaalOptimizationButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getDiagramPanel().removeAll();
				getDiagramPanel().add(uppaalOptimizationTabbedPanel);

				ChangeAllButtonPanelState();
				uppaalOptimizationButtonPanel.setBackground(new Color(58, 105, 190));
				
				fixButtonTabbedPanelSelectedIndex=2;

				ChangeRepaint();
			}
		});
//		uppaalSplitStateButton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				getDiagramPanel().removeAll();
//				getDiagramPanel().add(uppaalSplitStateTabbedPanel);
//
//				ChangeAllButtonPanelState();
//				uppaalSplitStateButtonPanel.setBackground(new Color(58, 105, 190));
//
//				ChangeRepaint();
//			}
//		});
//		uppaalRemoveMigrateButton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				getDiagramPanel().removeAll();
//				getDiagramPanel().add(uppaalRemoveMigrateTabbedPanel);
//
//				ChangeAllButtonPanelState();
//				uppaalRemoveMigrateButtonPanel.setBackground(new Color(58, 105, 190));
//
//				ChangeRepaint();
//			}
//		});
//		testCaseConstraintButton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				getDiagramPanel().removeAll();
//				getDiagramPanel().add(testCaseConstraintTabbedPanel);
//
//				ChangeAllButtonPanelState();
//				testCaseConstraintButtonPanel.setBackground(new Color(58, 105, 190));
//
//				ChangeRepaint();
//			}
//		});
		testCaseUppaalButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getDiagramPanel().removeAll();
				getDiagramPanel().add(testCaseUppaalTabbedPanel);

				ChangeAllButtonPanelState();
				testCaseUppaalButtonPanel.setBackground(new Color(58, 105, 190));
				
				fixButtonTabbedPanelSelectedIndex=3;

				ChangeRepaint();
			}
		});
		testCaseCoverButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getDiagramPanel().removeAll();
				getDiagramPanel().add(testCaseCoverTabbedPanel);

				ChangeAllButtonPanelState();
				testCaseCoverButtonPanel.setBackground(new Color(58, 105, 190));
				
				fixButtonTabbedPanelSelectedIndex=4;

				ChangeRepaint();
			}
		});
		testCaseSortContrastButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getDiagramPanel().removeAll();
				getDiagramPanel().add(testCaseSortContrastTabbedPanel);

				ChangeAllButtonPanelState();
				testCaseSortContrastButtonPanel.setBackground(new Color(58, 105, 190));
				
				fixButtonTabbedPanelSelectedIndex=5;

				ChangeRepaint();
			}
		});
		testCaseProduceButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getDiagramPanel().removeAll();
				getDiagramPanel().add(testCaseProduceTabbedPanel);

				ChangeAllButtonPanelState();
				testCaseProduceButtonPanel.setBackground(new Color(58, 105, 190));
				
				fixButtonTabbedPanelSelectedIndex=6;

				ChangeRepaint();
			}
		});
//		testCaseInstantiationButton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				getDiagramPanel().removeAll();
////				getDiagramPanel().add(testCaseInstantiationTabbedPanel);
//				getDiagramPanel().add(mainFrame.getStepThreeCenterTabbedPane().getTestCaseInstantiationTabbedPanel());
//
//				ChangeAllButtonPanelState();
//				testCaseInstantiationButtonPanel.setBackground(new Color(58, 105, 190));
//				
//				fixButtonTabbedPanelSelectedIndex=7;
//
//				ChangeRepaint();
//			}
//		});
//		testCaseShowButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				getDiagramPanel().removeAll();
//				getDiagramPanel().add(testCaseShowTabbedPanel);
//
//				ChangeAllButtonPanelState();
//				testCaseShowButtonPanel.setBackground(new Color(58, 105, 190));
//				
//				fixButtonTabbedPanelSelectedIndex=8;
//
//				ChangeRepaint();
//			}
//		});
	}

	protected void ChangeAllButtonPanelState() {
		// TODO Auto-generated method stub
		
		testCaseProcessButtonPanel.setBackground(new Color(77, 96, 130));
		uppaalParseInforButtonPanel.setBackground(new Color(77, 96, 130));
		uppaalOptimizationButtonPanel.setBackground(new Color(77, 96, 130));
		uppaalSplitStateButtonPanel.setBackground(new Color(77, 96, 130));
		uppaalRemoveMigrateButtonPanel.setBackground(new Color(77, 96, 130));
		testCaseConstraintButtonPanel.setBackground(new Color(77, 96, 130));
		testCaseUppaalButtonPanel.setBackground(new Color(77, 96, 130));
		testCaseCoverButtonPanel.setBackground(new Color(77, 96, 130));
		testCaseSortContrastButtonPanel.setBackground(new Color(77, 96, 130));
		testCaseProduceButtonPanel.setBackground(new Color(77, 96, 130));
		testCaseInstantiationButtonPanel.setBackground(new Color(77, 96, 130));
		testCaseShowButtonPanel.setBackground(new Color(77, 96, 130));
		
	}

	protected void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}

	public JPanel getDiagramPanel() {
		return diagramPanel;
	}

	public void setDiagramPanel(JPanel diagramPanel) {
		this.diagramPanel = diagramPanel;
	}

	public JButton getTestCaseProcessButton() {
		return testCaseProcessButton;
	}

	public JButton getUppaalParseInforButton() {
		return uppaalParseInforButton;
	}
	
	public JButton getUppaalOptimizationButton() {
		return uppaalOptimizationButton;
	}

	public JButton getTestCaseConstraintButton() {
		return testCaseConstraintButton;
	}

	public JButton getTestCaseCoverButton() {
		return testCaseCoverButton;
	}

	public JButton getTestCaseProduceButton() {
		return testCaseProduceButton;
	}

	public JButton getTestCaseInstantiationButton() {
		return testCaseInstantiationButton;
	}

	public TestCaseProcessTabbedPanel getTestCaseProcessTabbedPanel() {
		return testCaseProcessTabbedPanel;
	}

	public UppaalParseInforTabbedPanel getUppaalParseInforTabbedPanel() {
		return uppaalParseInforTabbedPanel;
	}
	
	public UppaalOptimizationTabbedPanel getUppaalOptimizationTabbedPanel() {
		return uppaalOptimizationTabbedPanel;
	}

	public TestCaseConstraintTabbedPanel getTestCaseConstraintTabbedPanel() {
		return testCaseConstraintTabbedPanel;
	}

	public TestCaseCoverTabbedPanel getTestCaseCoverTabbedPanel() {
		return testCaseCoverTabbedPanel;
	}

	public void setTestCaseCoverTabbedPanel(TestCaseCoverTabbedPanel testCaseCoverTabbedPanel) {
		this.testCaseCoverTabbedPanel = testCaseCoverTabbedPanel;
	}

	public TestCaseProduceTabbedPanel getTestCaseProduceTabbedPanel() {
		return testCaseProduceTabbedPanel;
	}

	public TestCaseInstantiationTabbedPanel getTestCaseInstantiationTabbedPanel() {
		return testCaseInstantiationTabbedPanel;
	}

	public TestCaseUppaalTabbedPanel getTestCaseUppaalTabbedPanel() {
		return testCaseUppaalTabbedPanel;
	}

	public void setTestCaseUppaalTabbedPanel(TestCaseUppaalTabbedPanel testCaseUppaalTabbedPanel) {
		this.testCaseUppaalTabbedPanel = testCaseUppaalTabbedPanel;
	}

	public JButton getTestCaseUppaalButton() {
		return testCaseUppaalButton;
	}

	public List<FixedButtonTabbedPanel> getFixButtonTabbedPanelList() {
		return fixButtonTabbedPanelList;
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public JPanel getButtonTabbedPanel() {
		return buttonTabbedPanel;
	}

	public JButton getLeftButton() {
		return leftButton;
	}

	public void setFixButtonTabbedPanelSelectedIndex(int fixButtonTabbedPanelSelectedIndex) {
		this.fixButtonTabbedPanelSelectedIndex = fixButtonTabbedPanelSelectedIndex;
	}

	public int getFixButtonTabbedPanelSelectedIndex() {
		return fixButtonTabbedPanelSelectedIndex;
	}

	public FixedButtonTabbedPanel getTestCaseProcessButtonPanel() {
		return testCaseProcessButtonPanel;
	}

	public FixedButtonTabbedPanel getUppaalParseInforButtonPanel() {
		return uppaalParseInforButtonPanel;
	}

	public FixedButtonTabbedPanel getUppaalOptimizationButtonPanel() {
		return uppaalOptimizationButtonPanel;
	}

	public FixedButtonTabbedPanel getTestCaseConstraintButtonPanel() {
		return testCaseConstraintButtonPanel;
	}

	public FixedButtonTabbedPanel getTestCaseUppaalButtonPanel() {
		return testCaseUppaalButtonPanel;
	}

	public FixedButtonTabbedPanel getTestCaseCoverButtonPanel() {
		return testCaseCoverButtonPanel;
	}

	public FixedButtonTabbedPanel getTestCaseProduceButtonPanel() {
		return testCaseProduceButtonPanel;
	}

	public FixedButtonTabbedPanel getTestCaseInstantiationButtonPanel() {
		return testCaseInstantiationButtonPanel;
	}

	public FixedButtonTabbedPanel getTestCaseShowButtonPanel() {
		return testCaseShowButtonPanel;
	}

	public JButton getTestCaseShowButton() {
		return testCaseShowButton;
	}

	public TestCaseShowTabbedPanel getTestCaseShowTabbedPanel() {
		return testCaseShowTabbedPanel;
	}

	public FixedButtonTabbedPanel getTestCaseSortContrastButtonPanel() {
		return testCaseSortContrastButtonPanel;
	}

	public JButton getTestCaseSortContrastButton() {
		return testCaseSortContrastButton;
	}

	public TestCaseSortContrastTabbedPanel getTestCaseSortContrastTabbedPanel() {
		return testCaseSortContrastTabbedPanel;
	}

	public void setTestCaseSortContrastTabbedPanel(TestCaseSortContrastTabbedPanel testCaseSortContrastTabbedPanel) {
		this.testCaseSortContrastTabbedPanel = testCaseSortContrastTabbedPanel;
	}

	
}

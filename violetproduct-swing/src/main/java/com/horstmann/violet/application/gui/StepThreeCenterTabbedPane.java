package com.horstmann.violet.application.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.horstmann.violet.application.gui.stepCenterTabbedPane.FixedButtonTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.TestCaseConstraintTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.TestCaseCoverTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.TestCaseInstantiationTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.TestCaseProcessTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.TestCaseProduceTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.TestCaseUppaalTabbedPanel;
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

	private FixedButtonTabbedPanel testCaseProcessButtonPanel;
	private FixedButtonTabbedPanel uppaalParseInforButtonPanel;
	private FixedButtonTabbedPanel uppaalSplitStateButtonPanel;
	private FixedButtonTabbedPanel uppaalRemoveMigrateButtonPanel;
	private FixedButtonTabbedPanel testCaseConstraintButtonPanel;
	private FixedButtonTabbedPanel testCaseUppaalButtonPanel;
	private FixedButtonTabbedPanel testCaseCoverButtonPanel;
	private FixedButtonTabbedPanel testCaseProduceButtonPanel;
	private FixedButtonTabbedPanel testCaseInstantiationButtonPanel;

	private JButton testCaseProcessButton;
	private JButton uppaalParseInforButton;
	private JButton uppaalSplitStateButton;
	private JButton uppaalRemoveMigrateButton;
	private JButton testCaseConstraintButton;
	private JButton testCaseUppaalButton;
	private JButton testCaseCoverButton;
	private JButton testCaseProduceButton;
	private JButton testCaseInstantiationButton;
	
	private TestCaseProcessTabbedPanel testCaseProcessTabbedPanel;
	private UppaalParseInforTabbedPanel uppaalParseInforTabbedPanel;
	private UppaalSplitStateTabbedPanel uppaalSplitStateTabbedPanel;
	private UppaalRemoveMigrateTabbedPanel uppaalRemoveMigrateTabbedPanel; 
	private TestCaseConstraintTabbedPanel testCaseConstraintTabbedPanel;
	private TestCaseUppaalTabbedPanel testCaseUppaalTabbedPanel;
	private TestCaseCoverTabbedPanel testCaseCoverTabbedPanel;
	private TestCaseProduceTabbedPanel testCaseProduceTabbedPanel;
	private TestCaseInstantiationTabbedPanel testCaseInstantiationTabbedPanel;
	
	public StepThreeCenterTabbedPane(MainFrame mainFrame) {
		
		this.mainFrame = mainFrame;
		
		testCaseProcessTabbedPanel=new TestCaseProcessTabbedPanel(mainFrame);
		uppaalParseInforTabbedPanel=new UppaalParseInforTabbedPanel(mainFrame);
		uppaalSplitStateTabbedPanel=new UppaalSplitStateTabbedPanel(mainFrame);
		uppaalRemoveMigrateTabbedPanel=new UppaalRemoveMigrateTabbedPanel(mainFrame);
		testCaseConstraintTabbedPanel=new TestCaseConstraintTabbedPanel(mainFrame);
		testCaseUppaalTabbedPanel=new TestCaseUppaalTabbedPanel(mainFrame,workspace);
		testCaseCoverTabbedPanel=new TestCaseCoverTabbedPanel(mainFrame);
		testCaseProduceTabbedPanel=new TestCaseProduceTabbedPanel(mainFrame);
		testCaseInstantiationTabbedPanel=new TestCaseInstantiationTabbedPanel(mainFrame);

		buttonPanel = new JPanel();
		diagramPanel = new JPanel();
		diagramPanel.setLayout(new GridLayout());

		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		buttonPanel.setBackground(new Color(41, 57, 85));

		// initButton();

		initbuttonpanel();
		
		initTestCaseUppaalPanel();

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

	private void initTestCaseUppaalPanel() {
		// TODO Auto-generated method stub
		
		GraphFile absfGraphFile=ImportByDoubleClick.importFileByDoubleClick("UPPAAL","abs.uppaal.violet.xml");
		IWorkspace workspace=new Workspace(absfGraphFile);
		TestCaseUppaalTabbedPanel tcut=new TestCaseUppaalTabbedPanel(mainFrame, workspace);
		setTestCaseUppaalTabbedPanel(tcut);
		
	}

	private void initbuttonpanel() {
		// TODO Auto-generated method stub

		testCaseProcessButtonPanel = new FixedButtonTabbedPanel("测试用例生成进程",150);
		testCaseProcessButton = testCaseProcessButtonPanel.getTabbedbutton();
		
		uppaalParseInforButtonPanel = new FixedButtonTabbedPanel("解析时间自动机",150);
		uppaalParseInforButton = uppaalParseInforButtonPanel.getTabbedbutton();

		uppaalSplitStateButtonPanel = new FixedButtonTabbedPanel("符号状态拆分", 150);
		uppaalSplitStateButton = uppaalSplitStateButtonPanel.getTabbedbutton();

		uppaalRemoveMigrateButtonPanel = new FixedButtonTabbedPanel("抽象时间延迟迁移去除", 150);
		uppaalRemoveMigrateButton = uppaalRemoveMigrateButtonPanel.getTabbedbutton();

		testCaseConstraintButtonPanel = new FixedButtonTabbedPanel("抽象时间延迟迁移", 150);
		testCaseConstraintButton = testCaseConstraintButtonPanel.getTabbedbutton();

		testCaseUppaalButtonPanel = new FixedButtonTabbedPanel("时间自动机",150);
		testCaseUppaalButton = testCaseUppaalButtonPanel.getTabbedbutton();
		
		testCaseCoverButtonPanel = new FixedButtonTabbedPanel("路径覆盖",150);
		testCaseCoverButton = testCaseCoverButtonPanel.getTabbedbutton();

		testCaseProduceButtonPanel = new FixedButtonTabbedPanel("添加约束条件",150);
		testCaseProduceButton = testCaseProduceButtonPanel.getTabbedbutton();
		
		testCaseInstantiationButtonPanel = new FixedButtonTabbedPanel("实例化",150);
		testCaseInstantiationButton = testCaseInstantiationButtonPanel.getTabbedbutton();


		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		buttonPanel.setBackground(new Color(41, 57, 85));
		buttonPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(58, 105, 190)));

		buttonPanel.add(testCaseProcessButtonPanel);
		buttonPanel.add(uppaalParseInforButtonPanel);
		buttonPanel.add(uppaalSplitStateButtonPanel);
		buttonPanel.add(uppaalRemoveMigrateButtonPanel);
//		buttonPanel.add(testCaseConstraintButtonPanel);
		buttonPanel.add(testCaseUppaalButtonPanel);
		buttonPanel.add(testCaseCoverButtonPanel);
		buttonPanel.add(testCaseProduceButtonPanel);
		buttonPanel.add(testCaseInstantiationButtonPanel);

		setButtonActionListener();

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

				ChangeRepaint();
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

				ChangeRepaint();
			}
		});
		uppaalSplitStateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getDiagramPanel().removeAll();
				getDiagramPanel().add(uppaalSplitStateTabbedPanel);

				ChangeAllButtonPanelState();
				uppaalSplitStateButtonPanel.setBackground(new Color(58, 105, 190));

				ChangeRepaint();
			}
		});
		uppaalRemoveMigrateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getDiagramPanel().removeAll();
				getDiagramPanel().add(uppaalRemoveMigrateTabbedPanel);

				ChangeAllButtonPanelState();
				uppaalRemoveMigrateButtonPanel.setBackground(new Color(58, 105, 190));

				ChangeRepaint();
			}
		});
		testCaseConstraintButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getDiagramPanel().removeAll();
				getDiagramPanel().add(testCaseConstraintTabbedPanel);

				ChangeAllButtonPanelState();
				testCaseConstraintButtonPanel.setBackground(new Color(58, 105, 190));

				ChangeRepaint();
			}
		});
		testCaseUppaalButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getDiagramPanel().removeAll();
				getDiagramPanel().add(testCaseUppaalTabbedPanel);

				ChangeAllButtonPanelState();
				testCaseUppaalButtonPanel.setBackground(new Color(58, 105, 190));

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

				ChangeRepaint();
			}
		});
		testCaseInstantiationButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getDiagramPanel().removeAll();
				getDiagramPanel().add(testCaseInstantiationTabbedPanel);

				ChangeAllButtonPanelState();
				testCaseInstantiationButtonPanel.setBackground(new Color(58, 105, 190));

				ChangeRepaint();
			}
		});
	}

	protected void ChangeAllButtonPanelState() {
		// TODO Auto-generated method stub
		
		testCaseProcessButtonPanel.setBackground(new Color(77, 96, 130));
		uppaalParseInforButtonPanel.setBackground(new Color(77, 96, 130));
		uppaalSplitStateButtonPanel.setBackground(new Color(77, 96, 130));
		uppaalRemoveMigrateButtonPanel.setBackground(new Color(77, 96, 130));
		testCaseConstraintButtonPanel.setBackground(new Color(77, 96, 130));
		testCaseUppaalButtonPanel.setBackground(new Color(77, 96, 130));
		testCaseCoverButtonPanel.setBackground(new Color(77, 96, 130));
		testCaseProduceButtonPanel.setBackground(new Color(77, 96, 130));
		testCaseInstantiationButtonPanel.setBackground(new Color(77, 96, 130));
		
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

	public TestCaseConstraintTabbedPanel getTestCaseConstraintTabbedPanel() {
		return testCaseConstraintTabbedPanel;
	}

	public TestCaseCoverTabbedPanel getTestCaseCoverTabbedPanel() {
		return testCaseCoverTabbedPanel;
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

	

}

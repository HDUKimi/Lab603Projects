package com.horstmann.violet.application.gui.stepFour;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.horstmann.violet.application.ckt.entity.Markov;
import com.horstmann.violet.application.ckt.entity.Route;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.common.ColorData;

public class StepFourCenterPanel extends JPanel {

	private MainFrame mainFrame;

	private IntroducePanel introducePanel;
	private OperatePanel operatePanel;
	private ProgressPanel progressPanel;
	private JPanel workPanel;
	private JTabbedPane workTabbedPane;
	
	private TestCaseExecutePanel testCaseExecutePanel;
	private DealFailureDataPanel dealFailureDataPanel;
	
	private int step;
	private String testCaseName;
	private String testCasePath;
	private List<Route> routes;
	
	public StepFourCenterPanel(MainFrame mainFrame) {

		this.mainFrame = mainFrame;

		introducePanel = new IntroducePanel(mainFrame);
		operatePanel = new OperatePanel(mainFrame);
		progressPanel=new ProgressPanel(mainFrame);
		workPanel = new JPanel();
		
		workPanel.setOpaque(false);

//		initWorkPanel();
		
		testCaseExecutePanel=new TestCaseExecutePanel(mainFrame);
		dealFailureDataPanel=new DealFailureDataPanel(mainFrame);
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(introducePanel);
		this.add(operatePanel);
		this.add(progressPanel);
		this.add(workPanel);
		layout.setConstraints(introducePanel, new GBC(0, 0, 1, 2).setFill(GBC.BOTH).setWeight(0, 1));
		layout.setConstraints(operatePanel, new GBC(0, 2, 1, 2).setFill(GBC.BOTH).setWeight(0, 1));
		layout.setConstraints(progressPanel, new GBC(1, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(workPanel, new GBC(1, 1, 1, 3).setFill(GBC.BOTH).setWeight(1, 1));
		
		this.setBackground(ColorData.white);

	}

	public void initWorkPanel() {

		workTabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);

		workTabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println("当前选中的选项卡: " + workTabbedPane.getSelectedIndex());
			}
		});
		
		workTabbedPane.setOpaque(false);
		
		workPanel.setLayout(new GridLayout());
		workPanel.add(workTabbedPane);
		
	}

	public void TextAreaPrint(String word) {

	}

	public void ChangeRepaint() {
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}

	public JTabbedPane getWorkTabbedPane() {
		return workTabbedPane;
	}

	public ProgressPanel getProgressPanel() {
		return progressPanel;
	}

	public TestCaseExecutePanel getTestCaseExecutePanel() {
		return testCaseExecutePanel;
	}

	public DealFailureDataPanel getDealFailureDataPanel() {
		return dealFailureDataPanel;
	}

	public String getTestCaseName() {
		return testCaseName;
	}

	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}

	public String getTestCasePath() {
		return testCasePath;
	}

	public void setTestCasePath(String testCasePath) {
		this.testCasePath = testCasePath;
	}

	public List<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}
	
	
	
}

package com.horstmann.violet.application.gui.stepThree;

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

public class StepThreeCenterPanel extends JPanel {

	private MainFrame mainFrame;

	private IntroducePanel introducePanel;
	private OperatePanel operatePanel;
	private ProgressPanel progressPanel;
	private JPanel workPanel;
	private JTabbedPane workTabbedPane;
	
	private ParseMarkovPanel parseMarkovPanel;
	private TestSeqProducePanel testSeqProducePanel;
	private TestCaseProducePanel testCaseProducePanel;
	
	private String markovPath="connect.markov.violet.xml";
	private Markov markov;
	private List<Route> routes;
	
	public StepThreeCenterPanel(MainFrame mainFrame) {

		this.mainFrame = mainFrame;

		introducePanel = new IntroducePanel(mainFrame);
		operatePanel = new OperatePanel(mainFrame);
		progressPanel=new ProgressPanel(mainFrame);
		workPanel = new JPanel();
		
		workPanel.setOpaque(false);

//		initWorkPanel();
		
		parseMarkovPanel=new ParseMarkovPanel(mainFrame);
		testSeqProducePanel=new TestSeqProducePanel(mainFrame);
		testCaseProducePanel=new TestCaseProducePanel(mainFrame);
		
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

	public ParseMarkovPanel getParseMarkovPanel() {
		return parseMarkovPanel;
	}

	public TestSeqProducePanel getTestSeqProducePanel() {
		return testSeqProducePanel;
	}

	public TestCaseProducePanel getTestCaseProducePanel() {
		return testCaseProducePanel;
	}

	public String getMarkovPath() {
		return markovPath;
	}

	public void setMarkovPath(String markovPath) {
		this.markovPath = markovPath;
	}

	public Markov getMarkov() {
		return markov;
	}

	public void setMarkov(Markov markov) {
		this.markov = markov;
	}

	public List<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}
	
	
	
}

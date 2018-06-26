package com.horstmann.violet.application.gui.stepFour;

import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
	
	private ModelPredictPanel modelPredictPanel;
	private ModelEvaluatePanel modelEvaluatePanel;
	private ModelSelectPanel modelSelectPanel;
	private ReliabilityEvaluatePanel reliabilityEvaluatePanel;
	
	private int selectModel;

	public StepFourCenterPanel(MainFrame mainFrame) {

		this.mainFrame = mainFrame;

		introducePanel = new IntroducePanel(mainFrame);
		operatePanel = new OperatePanel(mainFrame);
		progressPanel=new ProgressPanel(mainFrame);
		workPanel = new JPanel();
		
		workPanel.setOpaque(false);

//		initWorkPanel();
		
		
		modelPredictPanel=new ModelPredictPanel(mainFrame);
		modelEvaluatePanel=new ModelEvaluatePanel(mainFrame);
		modelSelectPanel=new ModelSelectPanel(mainFrame);
		reliabilityEvaluatePanel=new ReliabilityEvaluatePanel(mainFrame);
		

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

	public ModelPredictPanel getModelPredictPanel() {
		return modelPredictPanel;
	}

	public ModelEvaluatePanel getModelEvaluatePanel() {
		return modelEvaluatePanel;
	}

	public ModelSelectPanel getModelSelectPanel() {
		return modelSelectPanel;
	}

	public ReliabilityEvaluatePanel getReliabilityEvaluatePanel() {
		return reliabilityEvaluatePanel;
	}
	
	public ProgressPanel getProgressPanel() {
		return progressPanel;
	}

	public int getSelectModel() {
		return selectModel;
	}

	public void setSelectModel(int selectModel) {
		this.selectModel = selectModel;
	}
	
}

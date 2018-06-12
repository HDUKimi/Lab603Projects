package com.horstmann.violet.application.gui.stepOne;

import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;

public class StepOneCenterPanel extends JPanel {

	private MainFrame mainFrame;

	private IntroducePanel introducePanel;
	private OperatePanel operatePanel;
	private JPanel workPanel;
	private JTabbedPane workTabbedPane;

	public StepOneCenterPanel(MainFrame mainFrame) {

		this.mainFrame = mainFrame;

		introducePanel = new IntroducePanel(mainFrame);
		operatePanel = new OperatePanel(mainFrame);
		workPanel = new JPanel();

//		initWorkPanel();

		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(introducePanel);
		this.add(operatePanel);
		this.add(workPanel);
		layout.setConstraints(introducePanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 1));
		layout.setConstraints(operatePanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(0, 1));
		layout.setConstraints(workPanel, new GBC(1, 0, 1, 2).setFill(GBC.BOTH).setWeight(1, 1));

	}

	public void initWorkPanel() {

		workTabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);

		workTabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println("当前选中的选项卡: " + workTabbedPane.getSelectedIndex());
			}
		});
		
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

}

package com.horstmann.violet.application.gui.stepOne;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.common.ColorData;

public class StepOneCenterPanel extends JPanel {

	private MainFrame mainFrame;

	private IntroducePanel introducePanel;
	private WorkPanel workPanel;

	public StepOneCenterPanel(MainFrame mainFrame) {

		this.mainFrame = mainFrame;

		introducePanel = new IntroducePanel(mainFrame);
		workPanel = new WorkPanel(mainFrame);
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(introducePanel);
		this.add(workPanel);
		layout.setConstraints(introducePanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 1));
		layout.setConstraints(workPanel, new GBC(1, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		this.setBackground(ColorData.white);

	}

	public WorkPanel getWorkPanel() {
		return workPanel;
	}

}

package com.horstmann.violet.application.gui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.horstmann.violet.application.gui.common.ColorData;

public class TopPanel extends JPanel {

	private MainFrame mainFrame;
	private JPanel mainpanel;

	public TopPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		mainpanel = new JPanel();

		mainpanel.setLayout(new BorderLayout());
		mainpanel.add(mainFrame.getStepButton(), BorderLayout.CENTER);
		mainpanel.setOpaque(false);

		this.setLayout(new BorderLayout());
		this.add(mainpanel, BorderLayout.CENTER);
//		this.setBackground(ColorData.gray);
		
		this.setBackground(ColorData.gray_5);
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, ColorData.gray));
		

	}

}

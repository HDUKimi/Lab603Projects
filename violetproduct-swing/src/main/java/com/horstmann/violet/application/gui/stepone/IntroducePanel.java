package com.horstmann.violet.application.gui.stepone;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.common.ColorData;

public class IntroducePanel extends JPanel{

	private MainFrame mainFrame;
	
	private JLabel detailLabel;
	
	public IntroducePanel(MainFrame mainFrame) {
		
		this.mainFrame=mainFrame;
		
		initDetailLabel();
		
		this.setLayout(new BorderLayout());
		this.add(detailLabel, BorderLayout.CENTER);
		
		this.setBackground(ColorData.white);
		
	}

	private void initDetailLabel() {
		
		detailLabel=new JLabel("ΩÈ…‹");
		
	}
	
}

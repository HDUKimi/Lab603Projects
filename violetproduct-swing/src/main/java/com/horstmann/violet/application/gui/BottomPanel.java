package com.horstmann.violet.application.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.horstmann.violet.application.gui.common.ColorData;

public class BottomPanel extends JPanel {

	private JLabel messagelable;
	private MainFrame mainFrame;
	
	public BottomPanel(MainFrame mainFrame){
		
		this.mainFrame=mainFrame;
		
		init();
	}

	private void init() {

		messagelable=new JLabel();
		messagelable.setText("¾ÍÐ÷");
		
		messagelable.setForeground(ColorData.black);
		messagelable.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 0));
		messagelable.setPreferredSize(new Dimension(100, 24));
		
		this.setLayout(new BorderLayout());
		this.add(messagelable,BorderLayout.CENTER);
		
		this.setBackground(ColorData.gray_5);
		this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, ColorData.gray));
		
	}

	public JLabel getMessagelable() {
		return messagelable;
	}

	public void setMessagelable(JLabel messagelable) {
		this.messagelable = messagelable;
	}
	
	

	
}

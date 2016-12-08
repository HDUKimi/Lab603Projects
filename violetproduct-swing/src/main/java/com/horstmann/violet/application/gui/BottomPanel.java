package com.horstmann.violet.application.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BottomPanel extends JPanel {

	private JPanel jpanel;
	private JLabel messagelable;
	private MainFrame mainFrame;
	
	public BottomPanel(MainFrame mainFrame){
		this.setBackground(new Color(104,33,122));
		this.mainFrame=mainFrame;
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		BorderLayout layout=new BorderLayout();
		this.setLayout(layout);
//		mainFrame.getOneTouchExpandablePanel().setPreferredSize(new Dimension(200, 30));
//		mainFrame.getOneTouchExpandablePanel().setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, new Color(110, 110, 110)));
//		this.add(mainFrame.getOneTouchExpandablePanel(),BorderLayout.WEST);
//		jpanel=new JPanel();
		messagelable=new JLabel();
		messagelable.setText("¾ÍÐ÷");
		
		messagelable.setForeground(new Color(255,255,255));
		messagelable.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 0));
		messagelable.setPreferredSize(new Dimension(100, 24));
//		messagelable.setSize(100, 30);
//		jpanel.setBackground(Color.BLUE);
//		jpanel.add(messagelable);
		
		this.add(messagelable,BorderLayout.CENTER);
		
		
	}

	public JLabel getMessagelable() {
		return messagelable;
	}

	public void setMessagelable(JLabel messagelable) {
		this.messagelable = messagelable;
	}
	
	

	
}

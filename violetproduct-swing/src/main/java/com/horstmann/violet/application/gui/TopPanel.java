package com.horstmann.violet.application.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

public class TopPanel extends JPanel {

	private MainFrame mainFrame;
	private JPanel mainpanel;

	public TopPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		mainpanel=new JPanel();
		
		mainpanel.setLayout(new BorderLayout());
		mainpanel.add(mainFrame.getStepButton(),BorderLayout.CENTER);
		mainpanel.setOpaque(false);
		
		this.setLayout(new BorderLayout());
		this.add(mainpanel,BorderLayout.CENTER);
		
	}

}

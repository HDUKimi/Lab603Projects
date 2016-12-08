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
	private JPanel buttonPanel;
	private JLabel buttonLabel;
//	private ImageIcon bgicon;
//	private Image bgimage;
//	private URL bgurl;

	public TopPanel(MainFrame mainFrame) {
		this.setBackground(new Color(214, 219, 233));
		this.mainFrame = mainFrame;
//		bgurl = this.getClass().getResource("toppanelbg.png");
//		bgicon = new ImageIcon(bgurl);
//		bgicon.setImage(bgicon.getImage().getScaledInstance(2000, 50, Image.SCALE_DEFAULT));
//		bgimage = bgicon.getImage();
		init();
	}

//	@Override
//	protected void paintComponent(Graphics g) {
//		// TODO Auto-generated method stub
//		super.paintComponent(g);
//		g.drawImage(bgimage, 0, 0, null);
//	}

	private void init() {
		// TODO Auto-generated method stub
		// JSplitPane js4=new
		// JSplitPane(JSplitPane.VERTICAL_SPLIT,this.getCenterTabPanel(),this.getConsolePart());
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		buttonPanel = new JPanel();
		buttonLabel=new JLabel();
		
		buttonLabel.setText("¸½¼Ó...");
		buttonLabel.setForeground(new Color(0, 0,0));
		buttonLabel.setFont(new Font("System", Font.PLAIN, 12));
		buttonLabel.setBorder(BorderFactory.createEmptyBorder(4,8,3,0));
		
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.setBackground(new Color(207, 214, 229));
		buttonPanel.setPreferredSize(new Dimension(600, 28));
		buttonPanel.setBorder(BorderFactory.createMatteBorder(1, 10, 2, 0, new Color(214, 219, 233)));
		buttonPanel.add(buttonLabel,BorderLayout.WEST);
		
		
		this.add(buttonPanel);
		

		// this.setLayout(new FlowLayout(FlowLayout.LEFT));
//		JSplitPane js2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mainFrame.getOneTouchExpandablePanel(),
//				mainFrame.getStepJLabel());
//		JSplitPane js1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mainFrame.getStepButton(), js2);
		// js2.setOneTouchExpandable(true);
//		js2.setContinuousLayout(true);
		// js1.setOneTouchExpandable(true);
//		js1.setContinuousLayout(true);
//		js1.setDividerSize(1);
//		js2.setDividerSize(1);
//		js1.setOpaque(false);
//		js2.setOpaque(false);
//		mainFrame.getStepButton().setOpaque(false);
//		mainFrame.getOneTouchExpandablePanel().setOpaque(false);
//		mainFrame.getStepJLabel().setOpaque(false);
//		this.add(js1);
//		this.add(mainFrame.getOneTouchExpandablePanel());
		// mainFrame.getStepButton().setOpaque(false);
		// this.add(mainFrame.getStepButton());

//		jl1.setBackground(new Color(0, 0, 0));
//		jl1.setPreferredSize(new Dimension(1000, 2));
//		jl1.setBorder(new EmptyBorder(0, 0, 0, 0));
//		this.add(jl1, BorderLayout.NORTH);
		
//		jl2.setBackground(new Color(255, 255, 255));
//		jl2.setPreferredSize(new Dimension(1000, 2));
//		jl2.setBorder(new EmptyBorder(0, 0, 0, 0));
//		this.add(jl2, BorderLayout.SOUTH);

	}

}

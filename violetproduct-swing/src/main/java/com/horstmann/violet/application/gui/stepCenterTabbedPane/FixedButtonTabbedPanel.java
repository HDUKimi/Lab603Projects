package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FixedButtonTabbedPanel extends JPanel{
	
	private String tabbedbuttontext;
	
	private JButton tabbedbutton;
	private JLabel fixedlabel;
	private JLabel delectlabel;
	
	private JPanel labelpanel;
	
	private int width=180;
	
	public FixedButtonTabbedPanel(String tabbedbuttontext){
		
		this.tabbedbuttontext=tabbedbuttontext;
		
//		width=tabbedbuttontext.length()*12+60;
		int bl=tabbedbuttontext.getBytes().length;
		int cl=tabbedbuttontext.length();
		width=(bl-cl)*12+(2*cl-bl)*7+60;
//		System.err.println(tabbedbuttontext+" - - - "+tabbedbuttontext.length()+" - - - "+width);
		
		tabbedbutton=new JButton();
		fixedlabel=new JLabel();
		delectlabel=new JLabel();
		labelpanel=new JPanel();
		
		init();
	}
	
//	public FixedButtonTabbedPanel(String tabbedbuttontext,int width){
//		
//		this.tabbedbuttontext=tabbedbuttontext;
//		this.width=width;
//		
//		this.width=tabbedbuttontext.length()*15+50;
//		System.err.println(tabbedbuttontext+" - - - "+tabbedbuttontext.length());
//		
//		tabbedbutton=new JButton();
//		fixedlabel=new JLabel();
//		delectlabel=new JLabel();
//		labelpanel=new JPanel();
//		
//		init();
//	}

	private void init() {
		// TODO Auto-generated method stub
		
		tabbedbutton.setText(tabbedbuttontext);
		tabbedbutton.setForeground(Color.WHITE);
		tabbedbutton.setBackground(new Color(77, 96, 130));
		tabbedbutton.setContentAreaFilled(false);
		tabbedbutton.setFocusable(false);
		tabbedbutton.setBorderPainted(false);
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "leftarrow.png");
		icon1.setImage(icon1.getImage().getScaledInstance(8, 7, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(path + "fork.png");
		icon2.setImage(icon2.getImage().getScaledInstance(10, 8, Image.SCALE_DEFAULT));

		fixedlabel.setIcon(icon1);
		fixedlabel.setBorder(BorderFactory.createEmptyBorder(5, 3, 0, 6));
		delectlabel.setIcon(icon2);
		delectlabel.setBorder(BorderFactory.createEmptyBorder(6, 3, 0, 6));
		
		labelpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		labelpanel.setOpaque(false);
		labelpanel.add(fixedlabel);
		labelpanel.add(delectlabel);
		
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(77, 96, 130));
		this.setPreferredSize(new Dimension(width, 23));
		this.setBorder(BorderFactory.createEmptyBorder(2, 0, 1, 0));
		this.add(tabbedbutton, BorderLayout.WEST);
		this.add(labelpanel, BorderLayout.EAST);
		
		
	}

	public JButton getTabbedbutton() {
		return tabbedbutton;
	}

	public void setTabbedbutton(JButton tabbedbutton) {
		this.tabbedbutton = tabbedbutton;
	}
	
}

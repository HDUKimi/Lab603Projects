package com.horstmann.violet.application.consolepart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TableHeadPanel extends JPanel{

	private ArrayList<String> headlist=new ArrayList<String>();
	
	private JPanel titlepanel;
	private JPanel linepanel;
	
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	
	private JLabel linelabel;
	
	public TableHeadPanel(ArrayList<String> headlist){
		
		this.headlist=headlist;
		
		init();
		
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		this.setBackground(new Color(255, 255, 255));
		
	}

	private void init() {
		// TODO Auto-generated method stub
		
		titlepanel=new JPanel();
		linepanel=new JPanel();
		
		label1=new JLabel();
		label2=new JLabel();
		label3=new JLabel();
		
		linelabel=new JLabel();
		
		initTitlePanel();
		
		initLinePanel();
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(titlepanel);
		this.add(linepanel);
		
	}
	
	private void initTitlePanel() {
		// TODO Auto-generated method stub
		
		label1.setText(headlist.get(0));
		label2.setText(headlist.get(1));
		label3.setText(headlist.get(2));
		
		label1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 12));
		label1.setPreferredSize(new Dimension(150, 23));
		
		label2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 12));
		label2.setPreferredSize(new Dimension(150, 23));
		
		label3.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 12));
		label3.setPreferredSize(new Dimension(60, 23));
		
		titlepanel.setLayout(new FlowLayout(0, 0, FlowLayout.LEFT));
		titlepanel.add(label1);
		titlepanel.add(label2);
		titlepanel.add(label3);
		titlepanel.setOpaque(false);
		
	}

	private void initLinePanel() {
		// TODO Auto-generated method stub
		
		linelabel.setText("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
		linelabel.setPreferredSize(new Dimension(100, 3));
		linelabel.setForeground(new Color(0,0,0));
		
		linepanel.setLayout(new GridLayout());
		linepanel.add(linelabel);
		linepanel.setOpaque(false);
		
	}
	
}

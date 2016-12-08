package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.workspace.IWorkspace;

public class OptionalToolPanel extends JPanel{

	private MainFrame mainFrame;
	private IWorkspace workspace;
	
	private JPanel buttonpanel1;
	private JPanel buttonpanel2;
	private JButton button1;
	private JButton button2;
	
	public OptionalToolPanel(MainFrame mainFrame,IWorkspace workspace){
		
		this.mainFrame=mainFrame;
		this.workspace=workspace;
		
		buttonpanel1=new JPanel();
		buttonpanel2=new JPanel();
		
		button1=new JButton();
		button2=new JButton();
		
		init();
		
	}

	private void init() {
		// TODO Auto-generated method stub
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\22x22\\";

		ImageIcon icon1 = new ImageIcon(path + "print.png");
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(path + "exporttoclipboard.png");
		icon2.setImage(icon2.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		
		button1.setIcon(icon1);
		button1.setFocusable(false);
		button1.setContentAreaFilled(false);
		button1.setBorderPainted(false);
		button1.addMouseListener(new ButtonMouseListener());
		button1.setPreferredSize(new Dimension(21,21));
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
//				workspace.getSideBar().getOptionalToolsBar().getPrintButton().doClick();
				workspace.getGraphFile().exportToPrinter();
				mainFrame.getConsolePartPanel().getTextarea().append("预览打印\n");
			}
		});
		
		button2.setIcon(icon2);
		button2.setFocusable(false);
		button2.setContentAreaFilled(false);
		button2.setBorderPainted(false);
		button2.addMouseListener(new ButtonMouseListener());
		button2.setPreferredSize(new Dimension(21,21));
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				workspace.getGraphFile().exportToClipboard();
				mainFrame.getConsolePartPanel().getTextarea().append("导出到剪贴板\n");
			}
		});
		
		buttonpanel1.setLayout(new GridLayout());
		buttonpanel1.setBackground(new Color(207, 214, 229));
		buttonpanel1.add(button1);
		buttonpanel2.setLayout(new GridLayout());
		buttonpanel2.setBackground(new Color(207, 214, 229));
		buttonpanel2.add(button2);
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 4,3));
		this.add(buttonpanel1);
		this.add(buttonpanel2);
		
	}
}

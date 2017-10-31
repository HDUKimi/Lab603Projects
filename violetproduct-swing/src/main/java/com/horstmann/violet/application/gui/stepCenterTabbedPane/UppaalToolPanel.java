package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.workspace.IWorkspace;

public class UppaalToolPanel extends JPanel{

	private MainFrame mainFrame;
	private IWorkspace workspace;

	private JPanel toolpanel;
	
	private JPanel toolbuttonpanel1;
	private JPanel toolbuttonpanel2;
	
	private JButton toolbutton1;
	private JButton toolbutton2;

	public UppaalToolPanel(MainFrame mainFrame,IWorkspace workspace) {

		this.mainFrame=mainFrame;
		this.workspace = workspace;

//		editortoolbuttonGrouph = new ArrayList<JButton>();
		
		toolpanel=new JPanel();
		toolbuttonpanel1 = new JPanel();
		toolbuttonpanel2 = new JPanel();

		toolbutton1 = new JButton();
		toolbutton2 = new JButton();

		init();
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT,3,0));
		this.setBackground(new Color(207,214,229));
		this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		this.setPreferredSize(new Dimension(700, 29));
		this.setMaximumSize(new Dimension(700, 29));
		this.setMinimumSize(new Dimension(700, 29));
		
		this.add(toolpanel);
	}

	private void init() {
		// TODO Auto-generated method stub
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("ImagePart/zoomin1.png"));
		icon1.setImage(icon1.getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(this.getClass().getResource("ImagePart/zoomout1.png"));
		icon2.setImage(icon2.getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));

		toolbutton1.setIcon(icon1);
		toolbutton1.setFocusable(false);
		toolbutton1.setContentAreaFilled(false);
		toolbutton1.setBorderPainted(false);
		toolbutton1.addMouseListener(new ButtonMouseListener());
		toolbutton1.setPreferredSize(new Dimension(21,21));
		toolbutton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				workspace.getSideBar().getEditorToolsBar().getZoomInButton().doClick();
				mainFrame.getConsolePartPanel().getTextarea2().append("放大一倍视图\n");
				
			}
		});
		
		toolbutton2.setIcon(icon2);
		toolbutton2.setFocusable(false);
		toolbutton2.setContentAreaFilled(false);
		toolbutton2.setBorderPainted(false);
		toolbutton2.addMouseListener(new ButtonMouseListener());
		toolbutton2.setPreferredSize(new Dimension(21,21));
		toolbutton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				workspace.getSideBar().getEditorToolsBar().getZoomOutButton().doClick();
				mainFrame.getConsolePartPanel().getTextarea2().append("缩小一倍视图\n");
				
			}
		});
		
		toolbuttonpanel1.setLayout(new GridLayout());
		toolbuttonpanel1.setBackground(new Color(207, 214, 229));
		toolbuttonpanel1.add(toolbutton1);
		toolbuttonpanel2.setLayout(new GridLayout());
		toolbuttonpanel2.setBackground(new Color(207, 214, 229));
		toolbuttonpanel2.add(toolbutton2);

		toolpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 4,3));
		toolpanel.add(toolbuttonpanel1);
		toolpanel.add(toolbuttonpanel2);
		
		toolpanel.setOpaque(false);
		toolpanel.setBorder(BorderFactory.createEmptyBorder(1, 0, 0, 0));
		
	}
}

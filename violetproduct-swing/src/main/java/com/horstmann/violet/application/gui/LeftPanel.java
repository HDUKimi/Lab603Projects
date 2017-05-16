package com.horstmann.violet.application.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.horstmann.violet.workspace.editorpart.behavior.ColorizeBehavior;
import com.horstmann.violet.workspace.sidebar.colortools.ColorChoice;
import com.horstmann.violet.workspace.sidebar.colortools.ColorToolsBarPanel;
import com.horstmann.violet.workspace.sidebar.editortools.EditorToolsPanel;
import com.horstmann.violet.workspace.sidebar.graphtools.GraphTool;
import com.horstmann.violet.workspace.sidebar.graphtools.GraphToolsBar;
import com.horstmann.violet.workspace.sidebar.graphtools.GraphToolsBarButton;
import com.horstmann.violet.workspace.sidebar.graphtools.GraphToolsBarPanel;
import com.horstmann.violet.workspace.sidebar.graphtools.IGraphToolsBar;

public class LeftPanel extends JPanel{

	private MainFrame mainFrame;
	
//	private JPanel emptypanel;
	private JPanel mainpanel;
	private JButton testbutton;
	
	public LeftPanel(MainFrame mainFrame){
		this.setBackground(new Color(71,80,93));
		this.mainFrame=mainFrame;
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		
//		emptypanel=new JPanel();
		mainpanel=new JPanel();
		
//		emptypanel.setBackground(Color.BLACK);
//		emptypanel.setPreferredSize(new Dimension(100, 30));
//		emptypanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		
		mainpanel.setLayout(new BorderLayout());
		mainpanel.add(mainFrame.getStepButton(),BorderLayout.NORTH);
//		mainpanel.add(mainFrame.getStepJLabel(),BorderLayout.SOUTH);
//		mainFrame.getStepJLabel().setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(110, 110, 110)));
		
		this.setLayout(new BorderLayout());
//		this.add(emptypanel,BorderLayout.NORTH);
		this.add(mainpanel,BorderLayout.CENTER);
		
		testbutton=new JButton();
//		testbutton.setBackground(Color.BLUE);
		
//		URL url=this.getClass().getResource("buttonUI.png");
//        ImageIcon icon=new ImageIcon(url);
//        icon.setImage(icon.getImage().getScaledInstance(50,30, Image.SCALE_DEFAULT));
        testbutton.setFocusable(false);
        testbutton.setContentAreaFilled(false);
        testbutton.setBorderPainted(false);
//		testbutton.setIcon(icon);
//		testbutton.addMouseListener(new TestButtonListener());
        
//		this.add(testbutton,BorderLayout.SOUTH);
		testbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
//				mainFrame.getActiveWorkspace().getAWTComponent().getWorkspacejs().setDividerLocation(2000);
				System.out.println("-------------------------------");
				
				GraphToolsBar toolbar= (GraphToolsBar) mainFrame.getActiveWorkspace().getAWTComponent().getWorkspace().getSideBar().getGraphToolsBar();
				List<GraphToolsBarButton> bl=GraphToolsBarPanel.getToggleButtons(toolbar.getNodeTools());
				int i=0;
				System.out.println("--++-------------+++++++++------"+bl.size());
				for(GraphToolsBarButton b:bl){
					if(i==1){
						((GraphToolsBarPanel) mainFrame.getActiveWorkspace().getAWTComponent().getWorkspace().getSideBar().getGraphToolsBar().getAWTComponent()).setOnClickSelectedButton(b);
						//GraphToolsBarPanel须设置相等条件
					}
					System.out.println(b.isSelected());
					i++;
				}
				
				
//				JButton jb=mainFrame.getActiveWorkspace().getAWTComponent().getWorkspace().getSideBar().getEditorToolsBar().getZoomInButton();
//				jb.doClick();
				
//				mainFrame.getActiveWorkspace().getEditorPart().changeZoom(1);
				
//				ColorChoice c=new ColorChoice(Color.BLUE, Color.BLUE, Color.WHITE);
//				((ColorToolsBarPanel)mainFrame.getActiveWorkspace().getAWTComponent().getWorkspace().getSideBar().getColorChoiceBar().getAWTComponent()).fireColorChoiceChanged(c);
				
				System.out.println("-------------------------------");
			}
		});
		
//		this.setLayout(new GridLayout());
//
//		// this.setLayout(new FlowLayout(FlowLayout.LEFT));
//		JSplitPane js2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, mainFrame.getOneTouchExpandablePanel(),
//				mainFrame.getStepJLabel());
//		JSplitPane js1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, mainFrame.getStepButton(), js2);
//		// js2.setOneTouchExpandable(true);
//		js2.setContinuousLayout(true);
//		// js1.setOneTouchExpandable(true);
//		js1.setContinuousLayout(true);
//		js1.setDividerSize(1);
//		js2.setDividerSize(1);
//		js1.setOpaque(false);
//		js2.setOpaque(false);
		mainFrame.getStepButton().setOpaque(false);
//		mainFrame.getOneTouchExpandablePanel().setOpaque(false);
		mainFrame.getStepJLabel().setOpaque(false);
//		emptypanel.setOpaque(false);
		mainpanel.setOpaque(false);
//		this.add(js1);
	}
	
}

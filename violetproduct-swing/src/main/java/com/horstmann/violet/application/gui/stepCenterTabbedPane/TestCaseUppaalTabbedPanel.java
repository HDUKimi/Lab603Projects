package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
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
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.util.tanchao.TranMessageText;
import com.horstmann.violet.workspace.IWorkspace;

public class TestCaseUppaalTabbedPanel extends JPanel{

	private MainFrame mainFrame;
	private IWorkspace workspace;
	
	private JPanel toolpanel;
//	private UppaalToolPanel toolPanel;
	private MoviePanel moviePanel;
	private JPanel diagramPanel;
	
	private JPanel toolbuttonpanel1;
	private JPanel toolbuttonpanel2;
	private JPanel toolbuttonpanel3;
	private JPanel toolbuttonpanel4;
	private JButton toolbutton1;
	private JButton toolbutton2;
	private JButton toolbutton3;
	private JButton toolbutton4;
	private JPanel emptypanel1;
	
	private int trantextstate=1;//1是id，0是name
	
	
	public TestCaseUppaalTabbedPanel(MainFrame mainFrame, IWorkspace workspace) {
		// TODO Auto-generated constructor stub
		
		this.mainFrame=mainFrame;
		this.workspace=workspace;
		
		init();
		
		this.setBackground(new Color(255, 255, 255));
		
	}


	private void init() {
		// TODO Auto-generated method stub
		
		toolpanel=new JPanel();
//		toolPanel=new UppaalToolPanel(mainFrame, workspace);
		moviePanel=new MoviePanel();
		diagramPanel=new JPanel();
		
		initToolPanel();
		
		moviePanel.getMovieLabel().setText("正在进行深度遍历时间自动机，生成深度优先生成树");
		
		initDiagramPanel();
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(toolpanel);
		this.add(moviePanel);
		this.add(diagramPanel);
		layout.setConstraints(toolpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(moviePanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(diagramPanel,new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		
	}
	
	private void initToolPanel() {
		// TODO Auto-generated method stub
		
		toolbuttonpanel1 = new JPanel();
		toolbuttonpanel2 = new JPanel();
		toolbuttonpanel3 = new JPanel();
		toolbuttonpanel4 = new JPanel();

		toolbutton1 = new JButton();
		toolbutton2 = new JButton();
		toolbutton3 = new JButton();
		toolbutton4 = new JButton();
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("ImagePart/zoomin1.png"));
		icon1.setImage(icon1.getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(this.getClass().getResource("ImagePart/zoomout1.png"));
		icon2.setImage(icon2.getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));
		final ImageIcon icon3 = new ImageIcon(this.getClass().getResource("ImagePart/test12.png"));
		icon3.setImage(icon3.getImage().getScaledInstance(18,18, Image.SCALE_DEFAULT));
		final ImageIcon icon4 = new ImageIcon(this.getClass().getResource("ImagePart/test11.png"));
		icon4.setImage(icon4.getImage().getScaledInstance(18,18, Image.SCALE_DEFAULT));
		ImageIcon icon5 = new ImageIcon(this.getClass().getResource("ImagePart/test13.png"));
		icon5.setImage(icon5.getImage().getScaledInstance(18,18, Image.SCALE_DEFAULT));
	
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
				mainFrame.getConsolePartPanel().getTextarea3().append("放大一倍视图\n");
				
//				JScrollPane js=workspace.getAWTComponent().getScrollableEditorPart();
//				JScrollBar hbar=js.getHorizontalScrollBar();
//				JScrollBar vbar=js.getVerticalScrollBar();
//				System.out.println("--------------------------------");
//				System.out.println(" hbar.getValue() "+hbar.getValue()+" hbar.getMinimum() "+hbar.getMinimum()+" hbar.getMaximum() "+hbar.getMaximum());
//				System.out.println(" vbar.getValue() "+vbar.getValue()+" vbar.getMinimum() "+vbar.getMinimum()+" vbar.getMaximum() "+vbar.getMaximum());
//				
//				hbar.setValue((hbar.getMaximum()-hbar.getBlockIncrement())/2);
//				vbar.setValue((vbar.getMaximum()-vbar.getBlockIncrement())/2);
				
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
				mainFrame.getConsolePartPanel().getTextarea3().append("缩小一倍视图\n");

//				JScrollPane js=workspace.getAWTComponent().getScrollableEditorPart();
//				JScrollBar hbar=js.getHorizontalScrollBar();
//				JScrollBar vbar=js.getVerticalScrollBar();
//				System.out.println("--------------------------------");
//				System.out.println(" hbar.getValue() "+hbar.getValue()+" hbar.getMinimum() "+hbar.getMinimum()+" hbar.getMaximum() "+hbar.getMaximum());
//				System.out.println(" vbar.getValue() "+vbar.getValue()+" vbar.getMinimum() "+vbar.getMinimum()+" vbar.getMaximum() "+vbar.getMaximum());
//				
//				hbar.setValue((hbar.getMaximum()-hbar.getBlockIncrement())/2);
//				vbar.setValue((vbar.getMaximum()-vbar.getBlockIncrement())/2);
				
			}
		});
		
		toolbutton3.setIcon(icon3);
		toolbutton3.setFocusable(false);
		toolbutton3.setContentAreaFilled(false);
		toolbutton3.setBorderPainted(false);
		toolbutton3.addMouseListener(new ButtonMouseListener());
		toolbutton3.setPreferredSize(new Dimension(21,21));
		toolbutton3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(trantextstate==0){
					TranMessageText tmt=new TranMessageText();
					tmt.TranTextToId(mainFrame, workspace);
					trantextstate=1;
					
					toolbutton3.setIcon(icon3);
					
					ChangeRepaint();
				}
				else{
					TranMessageText tmt=new TranMessageText();
					tmt.TranTextToName(mainFrame, workspace);
					trantextstate=0;
					
					toolbutton3.setIcon(icon4);
					
					ChangeRepaint();
				}
				
//				TranMessageText tmt=new TranMessageText();
//				tmt.TranTextToId(mainFrame, workspace);
//				trantextstate=1;
//				ChangeRepaint();
				
			}
		});

		toolbutton4.setIcon(icon4);
		toolbutton4.setFocusable(false);
		toolbutton4.setContentAreaFilled(false);
		toolbutton4.setBorderPainted(false);
		toolbutton4.addMouseListener(new ButtonMouseListener());
		toolbutton4.setPreferredSize(new Dimension(21,21));
		toolbutton4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				TranMessageText tmt=new TranMessageText();
				tmt.TranTextToName(mainFrame, workspace);
				trantextstate=0;
				ChangeRepaint();
			}
		});


		toolbuttonpanel1.setLayout(new GridLayout());
		toolbuttonpanel1.setBackground(new Color(207, 214, 229));
		toolbuttonpanel1.add(toolbutton1);
		toolbuttonpanel2.setLayout(new GridLayout());
		toolbuttonpanel2.setBackground(new Color(207, 214, 229));
		toolbuttonpanel2.add(toolbutton2);
		toolbuttonpanel3.setLayout(new GridLayout());
		toolbuttonpanel3.setBackground(new Color(207, 214, 229));
		toolbuttonpanel3.add(toolbutton3);
		toolbuttonpanel4.setLayout(new GridLayout());
		toolbuttonpanel4.setBackground(new Color(207, 214, 229));
		toolbuttonpanel4.add(toolbutton4);
		
		emptypanel1=new JPanel();
		emptypanel1.setPreferredSize(new Dimension(11, 23));
		emptypanel1.setBackground(new Color(133,145,162));
		emptypanel1.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 5, new Color(207, 214, 229)));

		toolpanel.setBackground(new Color(207, 214, 229));
		toolpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5,3));
		
		toolpanel.add(toolbuttonpanel1);
		toolpanel.add(toolbuttonpanel2);
		toolpanel.add(emptypanel1);
		toolpanel.add(toolbuttonpanel3);
//		toolpanel.add(toolbuttonpanel4);
		
		toolpanel.setPreferredSize(new Dimension(100, 29));
		toolpanel.setMaximumSize(new Dimension(100, 29));
		toolpanel.setMinimumSize(new Dimension(100, 29));
		
		
	}


	private void initDiagramPanel() {
		// TODO Auto-generated method stub
		
		diagramPanel.setLayout(new GridLayout());
		if(workspace!=null){
			diagramPanel.add(workspace.getAWTComponent());
		}
		
	}
	
	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}

	public IWorkspace getWorkspace() {
		return workspace;
	}


	public JPanel getDiagramPanel() {
		return diagramPanel;
	}
	
	
	
}

package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.workspace.IWorkspace;

public class EditorToolPanel extends JPanel {

	private MainFrame mainFrame;
	private IWorkspace workspace;

//	private int editortoolCount = 0;

//	private List<JButton> editortoolbuttonGrouph;

//	private JButton editortoolButton;
	
//    private JButton bZoomIn;
//    private JButton bZoomOut;
//    private JButton bUndo;
//    private JButton bRedo;
//    private JButton bDelete;
//    private JButton bCut;
//    private JButton bCopy;
//    private JButton bPaste;
	
	private JPanel toolbuttonpanel1;
	private JPanel toolbuttonpanel2;
	private JPanel toolbuttonpanel3;
	private JPanel toolbuttonpanel4;
	private JPanel toolbuttonpanel5;
	private JPanel toolbuttonpanel6;
	private JPanel toolbuttonpanel7;
	private JPanel toolbuttonpanel8;
	
	private JButton toolbutton1;
	private JButton toolbutton2;
	private JButton toolbutton3;
	private JButton toolbutton4;
	private JButton toolbutton5;
	private JButton toolbutton6;
	private JButton toolbutton7;
	private JButton toolbutton8;

	public EditorToolPanel(MainFrame mainFrame,IWorkspace workspace) {

		this.mainFrame=mainFrame;
		this.workspace = workspace;

//		editortoolbuttonGrouph = new ArrayList<JButton>();
		
		toolbuttonpanel1 = new JPanel();
		toolbuttonpanel2 = new JPanel();
		toolbuttonpanel3 = new JPanel();
		toolbuttonpanel4 = new JPanel();
		toolbuttonpanel5 = new JPanel();
		toolbuttonpanel6 = new JPanel();
		toolbuttonpanel7 = new JPanel();
		toolbuttonpanel8 = new JPanel();

		toolbutton1 = new JButton();
		toolbutton2 = new JButton();
		toolbutton3 = new JButton();
		toolbutton4 = new JButton();
		toolbutton5 = new JButton();
		toolbutton6 = new JButton();
		toolbutton7 = new JButton();
		toolbutton8 = new JButton();

		init();

	}

	private void init() {
		// TODO Auto-generated method stub
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\22x22\\";

		ImageIcon icon1 = new ImageIcon(path + "undo.png");
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(path + "redo.png");
		icon2.setImage(icon2.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon3 = new ImageIcon(path + "zoomin.png");
		icon3.setImage(icon3.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon4 = new ImageIcon(path + "zoomout.png");
		icon4.setImage(icon4.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon5 = new ImageIcon(path + "delete.png");
		icon5.setImage(icon5.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon6 = new ImageIcon(path + "cut.png");
		icon6.setImage(icon6.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon7 = new ImageIcon(path + "copy.png");
		icon7.setImage(icon7.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon8 = new ImageIcon(path + "paste.png");
		icon8.setImage(icon8.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));

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
				workspace.getSideBar().getEditorToolsBar().getUndoButton().doClick();
				mainFrame.getConsolePartPanel().getTextarea().append("撤销当前操作\n");
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
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				workspace.getSideBar().getEditorToolsBar().getRedoButton().doClick();
				mainFrame.getConsolePartPanel().getTextarea().append("返回上一步操作\n");
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
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				workspace.getSideBar().getEditorToolsBar().getZoomInButton().doClick();
				mainFrame.getConsolePartPanel().getTextarea().append("放大一倍视图\n");
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
				workspace.getSideBar().getEditorToolsBar().getZoomOutButton().doClick();
				mainFrame.getConsolePartPanel().getTextarea().append("缩小一倍视图\n");
			}
		});
		
		toolbutton5.setIcon(icon5);
		toolbutton5.setFocusable(false);
		toolbutton5.setContentAreaFilled(false);
		toolbutton5.setBorderPainted(false);
		toolbutton5.addMouseListener(new ButtonMouseListener());
		toolbutton5.setPreferredSize(new Dimension(21,21));
		toolbutton5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				workspace.getSideBar().getEditorToolsBar().getDeleteButton().doClick();
				mainFrame.getConsolePartPanel().getTextarea().append("删除组件\n");
			}
		});
		
		toolbutton6.setIcon(icon6);
		toolbutton6.setFocusable(false);
		toolbutton6.setContentAreaFilled(false);
		toolbutton6.setBorderPainted(false);
		toolbutton6.addMouseListener(new ButtonMouseListener());
		toolbutton6.setPreferredSize(new Dimension(21,21));
		toolbutton6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				workspace.getSideBar().getEditorToolsBar().getCutButton().doClick();
				mainFrame.getConsolePartPanel().getTextarea().append("剪切组件\n");
			}
		});
		
		toolbutton7.setIcon(icon7);
		toolbutton7.setFocusable(false);
		toolbutton7.setContentAreaFilled(false);
		toolbutton7.setBorderPainted(false);
		toolbutton7.addMouseListener(new ButtonMouseListener());
		toolbutton7.setPreferredSize(new Dimension(21,21));
		toolbutton7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				workspace.getSideBar().getEditorToolsBar().getCopyButton().doClick();
				mainFrame.getConsolePartPanel().getTextarea().append("复制组件\n");
			}
		});
		
		toolbutton8.setIcon(icon8);
		toolbutton8.setFocusable(false);
		toolbutton8.setContentAreaFilled(false);
		toolbutton8.setBorderPainted(false);
		toolbutton8.addMouseListener(new ButtonMouseListener());
		toolbutton8.setPreferredSize(new Dimension(21,21));
		toolbutton8.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				workspace.getSideBar().getEditorToolsBar().getPasteButton().doClick();
				mainFrame.getConsolePartPanel().getTextarea().append("粘贴组件\n");
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
//		toolbuttonpanel4.setMaximumSize(new Dimension(27, 27));
		toolbuttonpanel4.setBackground(new Color(207, 214, 229));
		toolbuttonpanel4.add(toolbutton4);
		toolbuttonpanel5.setLayout(new GridLayout());
//		toolbuttonpanel5.setMaximumSize(new Dimension(27, 27));
		toolbuttonpanel5.setBackground(new Color(207, 214, 229));
		toolbuttonpanel5.add(toolbutton5);
		toolbuttonpanel6.setLayout(new GridLayout());
//		toolbuttonpanel6.setMaximumSize(new Dimension(27, 27));
		toolbuttonpanel6.setBackground(new Color(207, 214, 229));
		toolbuttonpanel6.add(toolbutton6);
		toolbuttonpanel7.setLayout(new GridLayout());
//		toolbuttonpanel7.setMaximumSize(new Dimension(27, 27));
		toolbuttonpanel7.setBackground(new Color(207, 214, 229));
		toolbuttonpanel7.add(toolbutton7);
		toolbuttonpanel8.setLayout(new GridLayout());
//		toolbuttonpanel8.setMaximumSize(new Dimension(27, 27));
		toolbuttonpanel8.setBackground(new Color(207, 214, 229));
		toolbuttonpanel8.add(toolbutton8);

//		this.setBackground(new Color(207, 214, 229));
//		toolpanel.setBackground(Color.RED);
		// toolpanel.setLayout(new GridLayout(1, 6));
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 4,3));
//		toolpanel.setLayout(new BoxLayout(toolpanel, BoxLayout.X_AXIS));
		this.add(toolbuttonpanel1);
		this.add(toolbuttonpanel2);
		this.add(toolbuttonpanel3);
		this.add(toolbuttonpanel4);
		this.add(toolbuttonpanel5);
		this.add(toolbuttonpanel6);
		this.add(toolbuttonpanel7);
		this.add(toolbuttonpanel8);
		
	}

}

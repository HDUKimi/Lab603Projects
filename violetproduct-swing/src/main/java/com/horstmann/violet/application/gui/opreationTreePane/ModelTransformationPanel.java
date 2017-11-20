package com.horstmann.violet.application.gui.opreationTreePane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;

import com.horstmann.violet.application.consolepart.ConsolePartTextArea;
import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.util.xiaole.UppaalTransfrom.ImportByDoubleClick;
import com.horstmann.violet.framework.file.GraphFile;
import com.horstmann.violet.framework.file.LocalFile;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;
import com.l2fprod.common.swing.JTaskPane;
import com.l2fprod.common.swing.JTaskPaneGroup;

public class ModelTransformationPanel extends JPanel{
	 //转换功能区文本提示
		
	public  MainFrame mainFrame;
	private JDialog messageDialog;
     
    public DefaultMutableTreeNode allDiagram;
    public DefaultMutableTreeNode timingDiagram;
    public DefaultMutableTreeNode sequenceDiagram;
    public DefaultMutableTreeNode uppaalDiagram;
    public DefaultMutableTreeNode node;
    public JTree UMLDiagramTree;
	private List<String> tdlists=new ArrayList<String>();
	public JTree UppaalDiagramTree;
	private List<String> sdlists = new ArrayList<String>();
	private List<String> uppaallists = new ArrayList<String>();
	
//	private JPanel title1panel;
//	private JPanel title2panel;
//	private JPanel tree1panel;
//	private JPanel tree2panel;
//	private JLabel title1label;
//	private JLabel title2label;
//	private JSplitPane js1;
//	private JSplitPane js2;
	
	private JPanel titlepanel;
	private JPanel toolpanel;
	private JPanel treepanel;
	private JPanel diagrampanel;
	
	private JLabel titlelabel;
	private JPanel titleiconlabelpanel;
	private JLabel titleiconlabel1;
	private JLabel titleiconlabel2;
	private JLabel titleiconlabel3;
	
	private JPanel toolbuttonpanel1;
	private JPanel toolbuttonpanel2;
	private JButton toolbutton1;
	private JButton toolbutton2;
	
	private ModelSequenceTreePanel modelSequenceTreePanel;
	private ModelTimingTreePanel modelTimingTreePanel;
	
	private JPanel diagrambuttonpanel1;
	private JPanel diagrambuttonpanel2;
	private JPanel diagrambuttonpanel3;
	private JButton diagrambutton1;
	private JButton diagrambutton2;
	private JButton diagrambutton3;
	
	private int index = 1;

	public ModelTransformationPanel(MainFrame mainFram) {
		this.mainFrame = mainFram;
		initFileList();
//		initUI();
//		this.setLayout(new GridBagLayout());
//		JPanel umlDiagramPanel = new JPanel();
//		umlDiagramPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
//				"UML模型文件", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, new Font("宋体", Font.BOLD, 20),
//				new Color(60, 60, 60)));
//		umlDiagramPanel.setLayout(new GridBagLayout());
//		umlDiagramPanel.add(UMLDiagramTree, new GBC(0, 0).setFill(GBC.BOTH).setWeight(1, 1));
//
//		JPanel uppaalDiagramPanel = new JPanel();
//		uppaalDiagramPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
//				"TAS模型文件", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, new Font("宋体", Font.BOLD, 20),
//				new Color(60, 60, 60)));
//		uppaalDiagramPanel.setLayout(new GridBagLayout());
//		uppaalDiagramPanel.add(UppaalDiagramTree, new GBC(0, 0).setFill(GBC.BOTH).setWeight(1, 1));
//		this.add(umlDiagramPanel, new GBC(0, 0).setFill(GBC.BOTH).setWeight(1, 1));
//		this.add(uppaalDiagramPanel, new GBC(0, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
//		this.setLayout(new GridLayout(2, 1));
//		title1panel=new JPanel();
//		title2panel=new JPanel();
//		tree1panel=new JPanel();
//		tree2panel=new JPanel();
//		title1label=new JLabel();
//		title2label=new JLabel();
//		
//		title1label.setText("UML模型文件");
//		title1label.setForeground(Color.WHITE);
//		title2label.setText("TAS模型文件");
//		title2label.setForeground(Color.WHITE);
//		
//		title1panel.setLayout(new FlowLayout(FlowLayout.LEFT));
//		title1panel.setBackground(new Color(64,66, 68));
//		title1panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
//		title2panel.setLayout(new FlowLayout(FlowLayout.LEFT));
//		title2panel.setBackground(new Color(64,66, 68));
//		title2panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
//		
//		title1panel.add(title1label);
//		title2panel.add(title2label);
//		
//		UMLDiagramTree.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
//		UppaalDiagramTree.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
//		tree1panel.setLayout(new GridLayout());
//		tree2panel.setLayout(new GridLayout());
//		tree1panel.add(UMLDiagramTree);
//		tree2panel.add(UppaalDiagramTree);
//		
//		js1=new JSplitPane(JSplitPane.VERTICAL_SPLIT,title1panel,tree1panel);
//		js2=new JSplitPane(JSplitPane.VERTICAL_SPLIT,title2panel,tree2panel);
//		js1.setDividerLocation(30);
//		js1.setDividerSize(1);
//		js1.setEnabled(false);
//		js2.setDividerLocation(30);
//		js2.setDividerSize(1);
//		js2.setEnabled(false);
//		
//		this.add(js1);
//		this.add(js2);
		
		titlepanel = new JPanel();
		toolpanel = new JPanel();
		treepanel = new JPanel();
		diagrampanel = new JPanel();
		
		titlelabel = new JLabel();
		titleiconlabelpanel=new JPanel();
		titleiconlabel1 = new JLabel();
		titleiconlabel2 = new JLabel();
		titleiconlabel3 = new JLabel();
		
		modelSequenceTreePanel=new ModelSequenceTreePanel(mainFram);
		modelTimingTreePanel=new ModelTimingTreePanel(mainFram);
		
		titlepanel.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(142, 155, 188)));
		toolpanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, new Color(142, 155, 188)));
		treepanel.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(142, 155, 188)));
		
//		initDoubleClick();
		
		initTitlePanel();
		
//		initToolButton();

		initDiagramButton();
		
		initTreePanel();
		
		GridBagLayout layout=new GridBagLayout();
		this.setLayout(layout);
		this.add(titlepanel);
//		this.add(toolpanel);
		this.add(treepanel);
		this.add(diagrampanel);
		layout.setConstraints(titlepanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//		layout.setConstraints(toolpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(treepanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(diagrampanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();
		
		this.setMinimumSize(new Dimension(screenWidth/8, screenHeight-150));
		
	}

	private void initTreePanel() {
		// TODO Auto-generated method stub
		setdiagrambuttonpanelrepaint();
		diagrambuttonpanel1.setBackground(new Color(255, 255, 255));
		diagrambuttonpanel1.setBorder(BorderFactory.createMatteBorder(0,1,1,1, new Color(142, 155, 188)));
		diagrambuttonpanel2.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
		diagrambuttonpanel3.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
		index = 1;
		treepanel.removeAll();
		treepanel.setLayout(new GridLayout());
		treepanel.add(modelSequenceTreePanel);
//		mainFrame.setVisible(false);
//		mainFrame.getContentPane().repaint();
//		mainFrame.setVisible(true);
	}

	private void initDiagramButton() {
		// TODO Auto-generated method stub
		diagrambuttonpanel1 = new JPanel();
		diagrambuttonpanel2 = new JPanel();
		diagrambuttonpanel3 = new JPanel();
		diagrambutton1 = new JButton();
		diagrambutton2 = new JButton();
		diagrambutton3 = new JButton();

		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("ImagePart/sequence_diagram.png"));
		icon1.setImage(icon1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(this.getClass().getResource("ImagePart/timing_diagram.png"));
		icon2.setImage(icon2.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
		ImageIcon icon3 = new ImageIcon(this.getClass().getResource("ImagePart/usecase_diagram.png"));
		icon3.setImage(icon3.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));

		diagrambutton1.setIcon(icon1);
		diagrambutton1.setFocusable(false);
		diagrambutton1.setContentAreaFilled(false);
		diagrambutton1.setBorderPainted(false);
		diagrambutton1.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				diagrambuttonpanel1.setBackground(new Color(255, 255, 255));
//				diagrambuttonpanel1.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(142, 155, 188)));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (index != 1) {
					diagrambuttonpanel1.setBackground(new Color(77, 96, 130));
//					diagrambuttonpanel1.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(142, 155, 188)));
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (index != 1) {
					diagrambuttonpanel1.setBackground(new Color(134, 161, 209));
//					diagrambuttonpanel1.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(142, 155, 188)));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				setdiagrambuttonpanelrepaint();
				diagrambuttonpanel1.setBackground(new Color(255, 255, 255));
				diagrambuttonpanel1.setBorder(BorderFactory.createMatteBorder(0,1,1,1, new Color(142, 155, 188)));
				diagrambuttonpanel2.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
				diagrambuttonpanel3.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
				index = 1;
				
				treepanel.removeAll();
				treepanel.add(modelSequenceTreePanel);
				
				mainFrame.getStepTwoCenterTabbedPane().getTimingToUppaalDiagramButtonPanel().setVisible(false);
				mainFrame.getStepTwoCenterTabbedPane().getSequenceToUppaalDiagramButtonPanel().setVisible(true);
				
				if (mainFrame.getStepTwoCenterTabbedPane().getSelectedIndex() != 1) {
					mainFrame.getStepTwoCenterTabbedPane().getTimingToUppaalDiagramButton().doClick();
				}
				
				ChangeRepaint();
				
			}
		});
		diagrambutton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setdiagrambuttonpanelrepaint();
				diagrambuttonpanel1.setBackground(new Color(255, 255, 255));
				diagrambuttonpanel1.setBorder(BorderFactory.createMatteBorder(0,1,1,1, new Color(142, 155, 188)));
				diagrambuttonpanel2.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
				diagrambuttonpanel3.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
				index = 1;
				
				treepanel.removeAll();
				treepanel.add(modelSequenceTreePanel);
				
				mainFrame.getStepTwoCenterTabbedPane().getTimingToUppaalDiagramButtonPanel().setVisible(false);
				mainFrame.getStepTwoCenterTabbedPane().getSequenceToUppaalDiagramButtonPanel().setVisible(true);
				
				if(mainFrame.getStepTwoCenterTabbedPane().getSelectedIndex()!=1){
					mainFrame.getStepTwoCenterTabbedPane().getSequenceToUppaalDiagramButton().doClick();
				}
				
				if(mainFrame.getStepindex()==2){
					ChangeRepaint();
				}
				
			}
		});
		diagrambutton1.setPreferredSize(new Dimension(22,22));
		diagrambutton2.setIcon(icon2);
		diagrambutton2.setFocusable(false);
		diagrambutton2.setContentAreaFilled(false);
		diagrambutton2.setBorderPainted(false);
		diagrambutton2.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				diagrambuttonpanel2.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (index != 2) {
					diagrambuttonpanel2.setBackground(new Color(77, 96, 130));
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (index != 2) {
					diagrambuttonpanel2.setBackground(new Color(134, 161, 209));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				setdiagrambuttonpanelrepaint();
				diagrambuttonpanel2.setBackground(new Color(255, 255, 255));
				diagrambuttonpanel2.setBorder(BorderFactory.createMatteBorder(0,1,1,1, new Color(142, 155, 188)));
				diagrambuttonpanel1.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
				diagrambuttonpanel3.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
				index = 2;
				
				treepanel.removeAll();
				treepanel.add(modelTimingTreePanel);
				
				mainFrame.getStepTwoCenterTabbedPane().getTimingToUppaalDiagramButtonPanel().setVisible(true);
				mainFrame.getStepTwoCenterTabbedPane().getSequenceToUppaalDiagramButtonPanel().setVisible(false);

				if (mainFrame.getStepTwoCenterTabbedPane().getSelectedIndex() != 2) {
					mainFrame.getStepTwoCenterTabbedPane().getTimingToUppaalDiagramButton().doClick();
				}
				
				ChangeRepaint();
				
			}
		});
		diagrambutton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				setdiagrambuttonpanelrepaint();
				diagrambuttonpanel2.setBackground(new Color(255, 255, 255));
				diagrambuttonpanel2.setBorder(BorderFactory.createMatteBorder(0,1,1,1, new Color(142, 155, 188)));
				diagrambuttonpanel1.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
				diagrambuttonpanel3.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
				index = 2;
				
				treepanel.removeAll();
				treepanel.add(modelTimingTreePanel);
				
				mainFrame.getStepTwoCenterTabbedPane().getTimingToUppaalDiagramButtonPanel().setVisible(true);
				mainFrame.getStepTwoCenterTabbedPane().getSequenceToUppaalDiagramButtonPanel().setVisible(false);
				
				if (mainFrame.getStepTwoCenterTabbedPane().getSelectedIndex() != 2) {
					mainFrame.getStepTwoCenterTabbedPane().getTimingToUppaalDiagramButton().doClick();
				}
				
				ChangeRepaint();
				
			}
		});
		diagrambutton2.setPreferredSize(new Dimension(22,22));
		diagrambutton3.setIcon(icon3);
		diagrambutton3.setFocusable(false);
		diagrambutton3.setContentAreaFilled(false);
		diagrambutton3.setBorderPainted(false);
		diagrambutton3.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				diagrambuttonpanel3.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (index != 3) {
					diagrambuttonpanel3.setBackground(new Color(77, 96, 130));
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (index != 3) {
					diagrambuttonpanel3.setBackground(new Color(134, 161, 209));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				setdiagrambuttonpanelrepaint();
				diagrambuttonpanel3.setBackground(new Color(255, 255, 255));
				diagrambuttonpanel3.setBorder(BorderFactory.createMatteBorder(0,1,1,1, new Color(142, 155, 188)));
				diagrambuttonpanel1.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
				diagrambuttonpanel2.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
				index = 3;
				
				treepanel.removeAll();
				treepanel.add(UMLDiagramTree);

				ChangeRepaint();
				
			}
		});
		diagrambutton3.setPreferredSize(new Dimension(22,22));

		diagrambuttonpanel1.setLayout(new GridLayout());
		diagrambuttonpanel1.setBackground(new Color(255, 255, 255));
//		diagrambuttonpanel1.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		diagrambuttonpanel1.setBorder(BorderFactory.createMatteBorder(0,1,1,1, new Color(142, 155, 188)));
		diagrambuttonpanel1.add(diagrambutton1);
		diagrambuttonpanel2.setLayout(new GridLayout());
		diagrambuttonpanel2.setBackground(new Color(77, 96, 130));
//		diagrambuttonpanel2.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		diagrambuttonpanel2.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
		diagrambuttonpanel2.add(diagrambutton2);
		diagrambuttonpanel3.setLayout(new GridLayout());
		diagrambuttonpanel3.setBackground(new Color(77, 96, 130));
//		diagrambuttonpanel3.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		diagrambuttonpanel3.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
		diagrambuttonpanel3.add(diagrambutton3);

		diagrampanel.setLayout(new GridLayout(1, 3));
//		diagrampanel.setBackground(new Color(77, 96, 130));
		diagrampanel.setBackground(new Color(41, 56, 85));
		// diagrampanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		diagrampanel.add(diagrambuttonpanel1);
		diagrampanel.add(diagrambuttonpanel2);
		diagrampanel.add(diagrambuttonpanel3);
		
		diagrambuttonpanel3.setVisible(false);
		
		diagrampanel.setPreferredSize(new Dimension(100, 22));
		diagrampanel.setMinimumSize(new Dimension(100, 22));
		
//		diagrampanel.add(diagrambuttonpanel5);
	}

	protected void setdiagrambuttonpanelrepaint() {
		// TODO Auto-generated method stub
		diagrambuttonpanel1.setBackground(new Color(77, 96, 130));
		diagrambuttonpanel2.setBackground(new Color(77, 96, 130));
		diagrambuttonpanel3.setBackground(new Color(77, 96, 130));
	}
	
//	private void initToolButton() {
//		// TODO Auto-generated method stub
//		toolbuttonpanel1 = new JPanel();
//		toolbuttonpanel2 = new JPanel();
//
//		toolbutton1 = new JButton();
//		toolbutton2 = new JButton();
//		
//		String absolutePath=System.getProperty("user.dir");
//		String path = absolutePath+"\\src\\site\\resources\\icons\\16x16\\";
//
//		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("ImagePart/16x16/zoomin.png"));
//		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
//		ImageIcon icon2 = new ImageIcon(this.getClass().getResource("ImagePart/16x16/delete.png"));
//		icon2.setImage(icon2.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
//
//		toolbutton1.setIcon(icon1);
//		toolbutton1.setFocusable(false);
//		toolbutton1.setContentAreaFilled(false);
//		toolbutton1.setBorderPainted(false);
//		toolbutton1.addMouseListener(new ButtonMouseListener());
//		toolbutton1.setPreferredSize(new Dimension(21,21));
//		toolbutton1.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				
//				mainFrame.getStepButton().getTwostart().doClick();
//				
//			}
//		});
//		
//		toolbutton2.setIcon(icon2);
//		toolbutton2.setFocusable(false);
//		toolbutton2.setContentAreaFilled(false);
//		toolbutton2.setBorderPainted(false);
//		toolbutton2.addMouseListener(new ButtonMouseListener());
//		toolbutton2.setPreferredSize(new Dimension(21,21));
//		toolbutton2.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				
//				mainFrame.getStepButton().getButtonstop().doClick();
//				
//			}
//		});
//
//		toolbuttonpanel1.setLayout(new GridLayout());
//		toolbuttonpanel1.setBackground(new Color(207, 214, 229));
//		toolbuttonpanel1.add(toolbutton1);
//		toolbuttonpanel2.setLayout(new GridLayout());
//		toolbuttonpanel2.setBackground(new Color(207, 214, 229));
//		toolbuttonpanel2.add(toolbutton2);
//
//		toolpanel.setBackground(new Color(207, 214, 229));
//		toolpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5,4));
//		
//		toolpanel.add(toolbuttonpanel1);
//		toolpanel.add(toolbuttonpanel2);
//		
//		toolpanel.setPreferredSize(new Dimension(100, 29));
//		toolpanel.setMaximumSize(new Dimension(100, 29));
//		toolpanel.setMinimumSize(new Dimension(100, 29));
//	}

	private void initTitlePanel() {
		// TODO Auto-generated method stub
		titlelabel.setText("模型转换");
		titlelabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		titlelabel.setForeground(new Color(255, 255, 255));
		titlelabel.setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 0));
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("ImagePart/triangulararrow.png"));
		icon1.setImage(icon1.getImage().getScaledInstance(8,4, Image.SCALE_DEFAULT));
		titleiconlabel1.setIcon(icon1);
		titleiconlabel1.setBorder(BorderFactory.createEmptyBorder(4, 4,4, 6));
		ImageIcon icon2 = new ImageIcon(this.getClass().getResource("ImagePart/downarrow.png"));
		icon2.setImage(icon2.getImage().getScaledInstance(7,11, Image.SCALE_DEFAULT));
		titleiconlabel2.setIcon(icon2);
		titleiconlabel2.setBorder(BorderFactory.createEmptyBorder(4, 4,4, 4));
		ImageIcon icon3 = new ImageIcon(this.getClass().getResource("ImagePart/fork.png"));
		icon3.setImage(icon3.getImage().getScaledInstance(10,8, Image.SCALE_DEFAULT));
		titleiconlabel3.setIcon(icon3);
		titleiconlabel3.setBorder(BorderFactory.createEmptyBorder(4, 4,4, 4));
		
		titleiconlabel3.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				mainFrame.getJs1().setDividerSize(0);
				mainFrame.getOpreationPart().setVisible(false);
				mainFrame.getOneTouchExpandablePanel().setFlag1(0);
				mainFrame.getOneTouchExpandablePanel().setLocation1(mainFrame.getJs1().getDividerLocation());
				
			}
		});
		
		titleiconlabelpanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		titleiconlabelpanel.setOpaque(false);
		titleiconlabelpanel.add(titleiconlabel1);
		titleiconlabelpanel.add(titleiconlabel2);
		titleiconlabelpanel.add(titleiconlabel3);

		titlepanel.setLayout(new BorderLayout());
		titlepanel.setBackground(new Color(77, 96, 130));
		titlepanel.setPreferredSize(new Dimension(100, 23));
		titlepanel.setMinimumSize(new Dimension(100, 23));
		titlepanel.add(titlelabel,BorderLayout.WEST);
		titlepanel.add(titleiconlabelpanel,BorderLayout.EAST);
	}
	
	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}

	private void initDoubleClick() {
		UMLDiagramTree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub

				node = (DefaultMutableTreeNode) UMLDiagramTree.getLastSelectedPathComponent();

			}
		});

		UMLDiagramTree.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				// TODO Auto-generated method stub
				if (e.getClickCount() == 2) {

					if (node != null && node.isLeaf()) {
						GraphFile fGraphFile = ImportByDoubleClick.importFileByDoubleClick("SequenceDiagram",
								node.toString());
						IWorkspace workspace = new Workspace(fGraphFile);
						mainFrame.addTabbedPane(workspace, 2);
					}
				}
			}
		});
		//

		// TODO Auto-generated method stub
	    		
	}
		public void initUI() {
			// TODO Auto-generated method stub		 		   			 
		       allDiagram = new DefaultMutableTreeNode("要转化的UML模型文件");
		       uppaalDiagram=new DefaultMutableTreeNode("生成的时间自动机文件");
		       sequenceDiagram =new DefaultMutableTreeNode("顺序图模型文件");
		       timingDiagram=new DefaultMutableTreeNode("时序图模型文件");
		       for(String td:tdlists)
		       {
		    	   timingDiagram.add(new DefaultMutableTreeNode(td));
		    	  
		       }
		       for(String sd:sdlists)
		       {
		    	   sequenceDiagram.add(new DefaultMutableTreeNode(sd));
		    	  
		       }
		       for(String up :uppaallists){
		    	   uppaalDiagram.add(new DefaultMutableTreeNode(up));
		       }
	           allDiagram.add(sequenceDiagram);
	           allDiagram.add(timingDiagram);
	           UMLDiagramTree=new JTree(allDiagram);
	           UppaalDiagramTree=new JTree(uppaalDiagram);
	         
	           
	    }	
	    

	public void initFileList() {
		File[] sdFilelists = getAllFileByDiagramType("sequence");
	//	File[] tdFilelists= getAllFileByDiagramType("timing");	
	   // File[] uppaalFilelists=getAllFileByDiagramType("UPPAAL2");
	    for(File sdFile : sdFilelists)
	    {
	    	String fileName=sdFile.getName();
	    	sdlists.add(fileName);
	    }
//	    for(File tdFile : tdFilelists)
//	    {
//	    	String fileName=tdFile.getName();
//	    	tdlists.add(fileName);
//	    }
//	    for(File uppaalFile:uppaalFilelists)
//	    {
//	    	String fileName=uppaalFile.getName();
//	    	uppaallists.add(fileName);
//	    }
	}
	/**
	  * 根据类型获取文件夹下的所有文件
	  * @param type
	  * @return
	  */
	 public   File[] getAllFileByDiagramType(String type){
		 File f =FileSystemView.getFileSystemView().getHomeDirectory();
		String s =f .getAbsolutePath();
//		 String baseUrl ="D://ModelDriverProjectFile";
		String baseUrl =s+"//ModelDriverProjectFile";
//		File bFile = new File(baseUrl);
//		if(!bFile.exists()){
//			bFile.mkdirs();
//		}
		 File[] fList =null;
		 File file=null;
		 if("sequence".equals(type)){
			 file =new File(baseUrl+"\\SequenceDiagram\\Violet");
			 fList= file.listFiles();
		 }else if("timing".equals(type)){
			file =new File(baseUrl+"\\TimingDiagram\\Violet");
			 fList= file.listFiles();
		 }else if("UPPAAL2".equals(type)){
			 //第二步的UPPAAL涉及的自动机
			 file =new File(baseUrl+"\\UPPAAL\\2.UML Model Transfer");
			 fList=file.listFiles();
		 }else if("UPPAAL3".equals(type)){
			 //第三步的UPPAAL涉及的自动机
			 file =new File(baseUrl+"\\UPPAAL\\3.Abstract TestCase");
			 fList= file.listFiles();
		 }else if("UPPAAL4".equals(type)){
			 //第四步的UPPAAL涉及的自动机
			 file =new File(baseUrl+"\\UPPAAL\\4.Real TestCase");
			 fList=file.listFiles();
		 }else if("state".equals(type)){
			 file =new File(baseUrl+"\\StateDiagram\\Violet");
			 fList=file.listFiles();
		 }else if("usecase".equals(type)){
			 file =new File(baseUrl+"\\UsecaseDiagram\\Violet");
			 fList= file.listFiles();
		 }else if("class".equals(type)){
			 file =new File(baseUrl+"\\ClassDiagram\\Violet");
			 fList= file.listFiles();
		 }else if("activity".equals(type)){
			 file =new File(baseUrl+"\\ActivityDiagram\\Violet");
			 fList=file.listFiles();
		 }
		 return fList;
	}

	public ModelSequenceTreePanel getModelSequenceTreePanel() {
		return modelSequenceTreePanel;
	}
	
	public ModelTimingTreePanel getModelTimingTreePanel() {
		return modelTimingTreePanel;
	}

	public JButton getDiagrambutton1() {
		return diagrambutton1;
	}

	public JButton getDiagrambutton2() {
		return diagrambutton2;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	 
	 
	 
	 
}
	



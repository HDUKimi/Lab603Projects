/*
 Violet - A program for editing UML diagrams.

 Copyright (C) 2007 Cay S. Horstmann (http://horstmann.com)
 Alexandre de Pellegrin (http://alexdp.free.fr);

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.horstmann.violet.application.gui.opreationTreePane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.menu.FileMenu;
import com.l2fprod.common.swing.JTaskPane;
import com.l2fprod.common.swing.JTaskPaneGroup;

public class ProjectTree extends JPanel {

	private MainFrame mainFrame;
	private FileMenu fileMenu;

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
	private JPanel toolbuttonpanel3;
	private JPanel toolbuttonpanel4;
	private JPanel toolbuttonpanel5;
	private JPanel toolbuttonpanel6;
	private JButton toolbutton1;
	private JButton toolbutton2;
	private JButton toolbutton3;
	private JButton toolbutton4;
	private JButton toolbutton5;
	private JButton toolbutton6;

	private JPanel diagrambuttonpanel1;
	private JPanel diagrambuttonpanel2;
	private JPanel diagrambuttonpanel3;
	private JPanel diagrambuttonpanel4;
//	private JPanel diagrambuttonpanel5;
	private JButton diagrambutton1;
	private JButton diagrambutton2;
	private JButton diagrambutton3;
	private JButton diagrambutton4;
//	private JButton diagrambutton5;
	
	private UsecaseTreePanel usecaseTreePanel;
	private StateTreePanel stateTreePanel;
	private SequenceTreePanel sequenceTreePanel;
	private TimingTreePanel timingTreePanel;

	private int index = 1;
	
//	public DefaultMutableTreeNode top;
//	public JPopupMenu popupMenu;
//	public JMenuItem newDiagram;
//	public JMenuItem importDiagram;
//	public JTree projectjTree;
//	public DefaultTreeModel projectjTreeModel;

//	private JSplitPane js;
//	private JSplitPane js1;
//	private JSplitPane js2;

	public ProjectTree(FileMenu fileMenu, MainFrame mainFrame) {
		this.fileMenu = fileMenu;
		this.mainFrame = mainFrame;
//		setLayout(new GridLayout(1, 1));
//		setLayout(new BorderLayout());
		// this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black)
//		init();
		// ,"建立UML模型",TitledBorder.CENTER,TitledBorder.ABOVE_TOP,
		// new Font("宋体",Font.BOLD,20),new Color(60, 60, 60)));
		// this.add(projectjTree);
		// setSize(0,0);//不起作用

		titlepanel = new JPanel();
		toolpanel = new JPanel();
		treepanel = new JPanel();
		diagrampanel = new JPanel();
		titlelabel = new JLabel();
		titleiconlabelpanel=new JPanel();
		titleiconlabel1 = new JLabel();
		titleiconlabel2 = new JLabel();
		titleiconlabel3 = new JLabel();
		
		usecaseTreePanel=new UsecaseTreePanel(fileMenu, mainFrame);
		stateTreePanel=new StateTreePanel(fileMenu, mainFrame);
		sequenceTreePanel=new SequenceTreePanel(fileMenu, mainFrame);
		timingTreePanel=new TimingTreePanel(fileMenu, mainFrame);

		titlepanel.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(142, 155, 188)));
		toolpanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, new Color(142, 155, 188)));
		treepanel.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(142, 155, 188)));
		
		initTitlePanel();
		
		initToolButton();

		initDiagramButton();
		
		initTreePanel();

////		js2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, treepanel, diagrampanel);
//		js1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, toolpanel, treepanel);
//		js = new JSplitPane(JSplitPane.VERTICAL_SPLIT, titlepanel, js1);
////		js2.setDividerLocation(1000);
////		js2.setDividerSize(1);
//		js1.setDividerLocation(27);
//		js1.setDividerSize(0);
//		// js1.setEnabled(false);
//		js.setDividerLocation(22);
//		js.setDividerSize(1);
//		js.setEnabled(false);
//		js1.setEnabled(false);
////		js2.setEnabled(false);
//		js.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(142, 155, 188)));
//		setLayout(new BorderLayout());
//		this.add(js,BorderLayout.CENTER);
//		this.add(diagrampanel,BorderLayout.SOUTH);
		
		GridBagLayout layout=new GridBagLayout();
		this.setLayout(layout);
		this.add(titlepanel);
		this.add(toolpanel);
		this.add(treepanel);
		this.add(diagrampanel);
		layout.setConstraints(titlepanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(toolpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(treepanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(diagrampanel, new GBC(0,3, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();
		
		this.setMinimumSize(new Dimension(screenWidth/8, screenHeight-150));

	}

	private void initTreePanel() {
	// TODO Auto-generated method stub
		treepanel.setLayout(new GridLayout());
//		treepanel.add(sequenceTreePanel);

		setdiagrambuttonpanelrepaint();
		diagrambuttonpanel3.setBackground(new Color(255, 255, 255));
		diagrambuttonpanel3.setBorder(BorderFactory.createMatteBorder(0,1,1,1, new Color(142, 155, 188)));
		diagrambuttonpanel1.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
		diagrambuttonpanel2.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
		diagrambuttonpanel4.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
		index = 3;
		treepanel.removeAll();
		treepanel.add(sequenceTreePanel);
		mainFrame.setVisible(false);
		mainFrame.getContentPane().repaint();
		mainFrame.setVisible(true);
		
}

	private void initTitlePanel() {
	// TODO Auto-generated method stub
	
		titlelabel.setText("建立UML模型");
		titlelabel.setFont(new Font("System", Font.PLAIN, 12));
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
		titlepanel.setPreferredSize(new Dimension(200, 23));
		titlepanel.add(titlelabel,BorderLayout.WEST);
		titlepanel.add(titleiconlabelpanel,BorderLayout.EAST);
		
}

	private void initDiagramButton() {
		// TODO Auto-generated method stub
		diagrambuttonpanel1 = new JPanel();
		diagrambuttonpanel2 = new JPanel();
		diagrambuttonpanel3 = new JPanel();
		diagrambuttonpanel4 = new JPanel();
//		diagrambuttonpanel5 = new JPanel();
		diagrambutton1 = new JButton();
		diagrambutton2 = new JButton();
		diagrambutton3 = new JButton();
		diagrambutton4 = new JButton();
//		diagrambutton5 = new JButton();

		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("ImagePart/usecase_diagram.png"));
		icon1.setImage(icon1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(this.getClass().getResource("ImagePart/state_diagram.png"));
		icon2.setImage(icon2.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon3 = new ImageIcon(this.getClass().getResource("ImagePart/sequence_diagram.png"));
		icon3.setImage(icon3.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon4 = new ImageIcon(this.getClass().getResource("ImagePart/timing_diagram.png"));
		icon4.setImage(icon4.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));

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
				diagrambuttonpanel4.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
//				diagrambuttonpanel5.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
				index = 1;
				
				treepanel.removeAll();
				treepanel.add(usecaseTreePanel);
				
				mainFrame.getConsolePartPanel().getTextarea1().append("打开用例图列表\n");
				
				ChangeRepaint();
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
				diagrambuttonpanel4.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
//				diagrambuttonpanel5.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
				index = 2;
				
				treepanel.removeAll();
				treepanel.add(stateTreePanel);
				
				mainFrame.getConsolePartPanel().getTextarea1().append("打开状态图列表\n");
				
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
				diagrambuttonpanel4.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
//				diagrambuttonpanel5.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
				index = 3;
				
				treepanel.removeAll();
				treepanel.add(sequenceTreePanel);
				
				mainFrame.getConsolePartPanel().getTextarea1().append("打开顺序图列表\n");
				
				ChangeRepaint();
				
			}
		});
		diagrambutton3.setPreferredSize(new Dimension(22,22));
		diagrambutton4.setIcon(icon4);
		diagrambutton4.setFocusable(false);
		diagrambutton4.setContentAreaFilled(false);
		diagrambutton4.setBorderPainted(false);
		diagrambutton4.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				diagrambuttonpanel4.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (index != 4) {
					diagrambuttonpanel4.setBackground(new Color(77, 96, 130));
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (index != 4) {
					diagrambuttonpanel4.setBackground(new Color(134, 161, 209));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				setdiagrambuttonpanelrepaint();
				diagrambuttonpanel4.setBackground(new Color(255, 255, 255));
				diagrambuttonpanel4.setBorder(BorderFactory.createMatteBorder(0,1,1,1, new Color(142, 155, 188)));
				diagrambuttonpanel1.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
				diagrambuttonpanel2.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
				diagrambuttonpanel3.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
//				diagrambuttonpanel5.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
				index = 4;
				
				treepanel.removeAll();
				treepanel.add(timingTreePanel);
				
				mainFrame.getConsolePartPanel().getTextarea1().append("打开时序图列表\n");
				
				ChangeRepaint();
				
			}
		});
		diagrambutton4.setPreferredSize(new Dimension(22,22));
//		diagrambutton5.setIcon(icon1);
//		diagrambutton5.setFocusable(false);
//		diagrambutton5.setContentAreaFilled(false);
//		diagrambutton5.setBorderPainted(false);
//		diagrambutton5.addMouseListener(new MouseListener() {
//
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void mousePressed(MouseEvent e) {
//				// TODO Auto-generated method stub
//				diagrambuttonpanel5.setBackground(new Color(255, 255, 255));
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//				// TODO Auto-generated method stub
//				if (index != 5) {
//					diagrambuttonpanel5.setBackground(new Color(77, 96, 130));
//				}
//			}
//
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				// TODO Auto-generated method stub
//				if (index != 5) {
//					diagrambuttonpanel5.setBackground(new Color(134, 161, 209));
//				}
//			}
//
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				// TODO Auto-generated method stub
//				setdiagrambuttonpanelrepaint();
//				diagrambuttonpanel5.setBackground(new Color(255, 255, 255));
//				diagrambuttonpanel5.setBorder(BorderFactory.createMatteBorder(0,1,1,1, new Color(142, 155, 188)));
//				diagrambuttonpanel1.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
//				diagrambuttonpanel2.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
//				diagrambuttonpanel3.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
//				diagrambuttonpanel4.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
//				index = 5;
//				
//				treepanel.removeAll();
//				treepanel.add(projectjTree);
//				mainFrame.setVisible(false);
//				mainFrame.getContentPane().repaint();
//				mainFrame.setVisible(true);
//				
//			}
//		});
//		diagrambutton5.setPreferredSize(new Dimension(22,22));

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
		diagrambuttonpanel4.setLayout(new GridLayout());
		diagrambuttonpanel4.setBackground(new Color(77, 96, 130));
//		diagrambuttonpanel4.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		diagrambuttonpanel4.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
		diagrambuttonpanel4.add(diagrambutton4);
//		diagrambuttonpanel5.setLayout(new GridLayout());
//		diagrambuttonpanel5.setBackground(new Color(77, 96, 130));
//		diagrambuttonpanel5.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
//		diagrambuttonpanel5.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
//		diagrambuttonpanel5.add(diagrambutton5);

		diagrampanel.setLayout(new GridLayout(1, 4));
		diagrampanel.setBackground(new Color(77, 96, 130));
		// diagrampanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		diagrampanel.add(diagrambuttonpanel1);
		diagrampanel.add(diagrambuttonpanel2);
		diagrampanel.add(diagrambuttonpanel3);
		diagrampanel.add(diagrambuttonpanel4);
		
		diagrampanel.setPreferredSize(new Dimension(100, 22));
		diagrampanel.setMinimumSize(new Dimension(100, 22));
		
//		diagrampanel.add(diagrambuttonpanel5);
	}

	protected void setdiagrambuttonpanelrepaint() {
		// TODO Auto-generated method stub
		diagrambuttonpanel1.setBackground(new Color(77, 96, 130));
		diagrambuttonpanel2.setBackground(new Color(77, 96, 130));
		diagrambuttonpanel3.setBackground(new Color(77, 96, 130));
		diagrambuttonpanel4.setBackground(new Color(77, 96, 130));
//		diagrambuttonpanel5.setBackground(new Color(77, 96, 130));
	}

	private void initToolButton() {
		// TODO Auto-generated method stub

		toolbuttonpanel1 = new JPanel();
		toolbuttonpanel2 = new JPanel();
		toolbuttonpanel3 = new JPanel();
		toolbuttonpanel4 = new JPanel();
		toolbuttonpanel5 = new JPanel();
		toolbuttonpanel6 = new JPanel();

		toolbutton1 = new JButton();
		toolbutton2 = new JButton();
		toolbutton3 = new JButton();
		toolbutton4 = new JButton();
		toolbutton5 = new JButton();
		toolbutton6 = new JButton();
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\16x16\\";

		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("ImagePart/16x16/save.png"));
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(this.getClass().getResource("ImagePart/16x16/new.png"));
		icon2.setImage(icon2.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon3 = new ImageIcon(this.getClass().getResource("ImagePart/16x16/open.png"));
		icon3.setImage(icon3.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon4 = new ImageIcon(this.getClass().getResource("ImagePart/16x16/delete.png"));
		icon4.setImage(icon4.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon5 = new ImageIcon(this.getClass().getResource("ImagePart/16x16/save.png"));
		icon5.setImage(icon5.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon6 = new ImageIcon(this.getClass().getResource("ImagePart/16x16/saveas.png"));
		icon6.setImage(icon6.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));

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
				
				fileMenu.fileDsaveItem.doClick();
				
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
				
				if(index==1){
					((JMenu) fileMenu.getFileNewMenu().getItem(1)).getItem(0).doClick();
				}
				else if(index==2){
					((JMenu) fileMenu.getFileNewMenu().getItem(1)).getItem(1).doClick();
				}
				else if(index==3){
					((JMenu) fileMenu.getFileNewMenu().getItem(1)).getItem(3).doClick();
				}
				else if(index==4){
					((JMenu) fileMenu.getFileNewMenu().getItem(1)).getItem(5).doClick();
				}
				
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
				
				fileMenu.fileOpenItem.doClick();
				
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
				
				fileMenu.fileCloseItem.doClick();
				
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
				
				fileMenu.fileSaveItem.doClick();
				
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
				
				fileMenu.fileSaveAsItem.doClick();
				
			}
		});

		toolbuttonpanel1.setLayout(new GridLayout());
//		toolbuttonpanel1.setPreferredSize(new Dimension(27, 27));
//		toolbuttonpanel1.setMaximumSize(new Dimension(27, 27));
//		toolbuttonpanel1.setMinimumSize(new Dimension(27, 27));
		toolbuttonpanel1.setBackground(new Color(207, 214, 229));
//		toolbuttonpanel1.setBorder(BorderFactory.createEmptyBorder(1, 0, 0, 0));
		toolbuttonpanel1.add(toolbutton1);
		toolbuttonpanel2.setLayout(new GridLayout());
//		toolbuttonpanel2.setMaximumSize(new Dimension(27, 27));
		toolbuttonpanel2.setBackground(new Color(207, 214, 229));
//		toolbuttonpanel2.setBorder(BorderFactory.createEmptyBorder(1, 0, 0, 0));
		toolbuttonpanel2.add(toolbutton2);
		toolbuttonpanel3.setLayout(new GridLayout());
//		toolbuttonpanel3.setMaximumSize(new Dimension(27, 27));
		toolbuttonpanel3.setBackground(new Color(207, 214, 229));
//		toolbuttonpanel3.setBorder(BorderFactory.createEmptyBorder(1, 0, 0, 0));
		toolbuttonpanel3.add(toolbutton3);
		toolbuttonpanel4.setLayout(new GridLayout());
//		toolbuttonpanel4.setMaximumSize(new Dimension(27, 27));
		toolbuttonpanel4.setBackground(new Color(207, 214, 229));
//		toolbuttonpanel4.setBorder(BorderFactory.createEmptyBorder(1, 0, 0, 0));
		toolbuttonpanel4.add(toolbutton4);
		toolbuttonpanel5.setLayout(new GridLayout());
//		toolbuttonpanel5.setMaximumSize(new Dimension(27, 27));
		toolbuttonpanel5.setBackground(new Color(207, 214, 229));
//		toolbuttonpanel5.setBorder(BorderFactory.createEmptyBorder(1, 0, 0, 0));
		toolbuttonpanel5.add(toolbutton5);
		toolbuttonpanel6.setLayout(new GridLayout());
//		toolbuttonpanel6.setMaximumSize(new Dimension(27, 27));
		toolbuttonpanel6.setBackground(new Color(207, 214, 229));
//		toolbuttonpanel6.setBorder(BorderFactory.createEmptyBorder(1, 0, 0, 0));
		toolbuttonpanel6.add(toolbutton6);

		toolpanel.setBackground(new Color(207, 214, 229));
//		toolpanel.setBackground(Color.RED);
		// toolpanel.setLayout(new GridLayout(1, 6));
		toolpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5,4));
//		toolpanel.setLayout(new BoxLayout(toolpanel, BoxLayout.X_AXIS));
		
//		toolpanel.add(toolbutton1);
		toolpanel.add(toolbutton2);
		toolpanel.add(toolbutton3);
		toolpanel.add(toolbutton4);
		toolpanel.add(toolbutton5);
		toolpanel.add(toolbutton6);
		
		toolpanel.setPreferredSize(new Dimension(100, 29));
		toolpanel.setMaximumSize(new Dimension(100, 29));
		toolpanel.setMinimumSize(new Dimension(100, 29));
		
//		toolpanel.add(toolbutton1);
//		toolpanel.add(toolbutton2);
//		toolpanel.add(toolbutton3);
//		toolpanel.add(toolbutton4);
//		toolpanel.add(toolbutton5);
//		toolpanel.add(toolbutton6);
//		toolpanel.add(Box.createHorizontalGlue());

	}

//	private void init() {
//		// TODO Auto-generated method stub
//		top = new DefaultMutableTreeNode("UML模型");
//		final JMenu newMenu = this.fileMenu.getFileNewMenu();
//		for (int i = 0; i < newMenu.getItemCount(); i++) {
//			final JMenuItem item = newMenu.getItem(i);
//			boolean isSubMenu = JMenu.class.isInstance(item);
//			if (isSubMenu) {
//				for (int j = 0; j < ((JMenu) item).getItemCount(); j++) {
//					final JMenuItem subItem = ((JMenu) item).getItem(j);
//					String label = subItem.getText();
//					DefaultMutableTreeNode newTreeNode = new DefaultMutableTreeNode(label.toLowerCase());
//					top.add(newTreeNode);
//				}
//			}
//		}
//		
//		projectjTreeModel=new DefaultTreeModel(top);
//		projectjTree = new JTree(projectjTreeModel);
//		
//		projectjTree.addMouseListener(new MouseAdapter() {
//			public void mousePressed(MouseEvent e) {
//				if (e.getButton() == e.BUTTON3) { // BUTTON3是鼠标右键
//					
//					final TreePath selectionPath=projectjTree.getSelectionPath();
//					final DefaultMutableTreeNode node = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
//
//					for (int i = 0; i < newMenu.getItemCount(); i++) {
//						final JMenuItem item = newMenu.getItem(i);
//						for (int j = 0; j < ((JMenu) item).getItemCount(); j++) {
//							final JMenuItem subItem = ((JMenu) item).getItem(j);
//							if (node.toString().equals(subItem.getText().toLowerCase())) {
//								popupMenu = new JPopupMenu();
//								newDiagram = new JMenuItem("新建");
//								importDiagram = new JMenuItem("导入");
//								popupMenu.add(newDiagram);
//								popupMenu.add(importDiagram);
//								importDiagram.addActionListener(new ActionListener() {
//
//									@Override
//									public void actionPerformed(ActionEvent e) {
//										// TODO Auto-generated method stub
//										// 导入文件
//
//										fileMenu.fileOpenItem.doClick();// File目录下的导入标签
//										
//										DefaultMutableTreeNode newnode=new DefaultMutableTreeNode("1");
//										projectjTreeModel.insertNodeInto(newnode, node, node.getChildCount());
//										TreePath path=selectionPath.pathByAddingChild(newnode);
//										if(!projectjTree.isVisible(path)){
//											projectjTree.makeVisible(path);
//										}
////										node.add(new DefaultMutableTreeNode("1"));
//										// repaint();
//										// updateUI();
//										// revalidate();
//										// invalidate();
//
//									}
//								});
//								popupMenu.show(e.getComponent(), e.getX(), e.getY());
//								newDiagram.addActionListener(new ActionListener() {
//
//									@Override
//									public void actionPerformed(ActionEvent e) {
//										// TODO Auto-generated method stub
//										subItem.doClick();
//										
//										DefaultMutableTreeNode newnode=new DefaultMutableTreeNode("1");
//										projectjTreeModel.insertNodeInto(newnode, node, node.getChildCount());
//										TreePath path=selectionPath.pathByAddingChild(newnode);
//										if(!projectjTree.isVisible(path)){
//											projectjTree.makeVisible(path);
//										}
////										node.add(new DefaultMutableTreeNode(1));
//
//									}
//								});
//							}
//						}
//					}
//				}
//				if (e.getClickCount() == 2) {
//					final DefaultMutableTreeNode node = (DefaultMutableTreeNode) projectjTree
//							.getLastSelectedPathComponent();
//					
//					System.out.println(node.getUserObject().toString()+" ++ "+node.toString());
////					if (node.isLeaf()) // 是叶节点
////					{
////						// 对node.getUserObject()进行处理;
////
////						 mainFrame.addTabbedPane(workspace);
////					}
//				}
//			}
//		});
//	}
	
	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}
	

	public UsecaseTreePanel getUsecaseTreePanel() {
		return usecaseTreePanel;
	}

	public StateTreePanel getStateTreePanel() {
		return stateTreePanel;
	}

	public SequenceTreePanel getSequenceTreePanel() {
		return sequenceTreePanel;
	}

	public TimingTreePanel getTimingTreePanel() {
		return timingTreePanel;
	}


}

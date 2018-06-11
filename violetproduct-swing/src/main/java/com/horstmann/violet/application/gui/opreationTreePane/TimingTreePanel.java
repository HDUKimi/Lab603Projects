package com.horstmann.violet.application.gui.opreationTreePane;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.ButtonTabbedPanel;
import com.horstmann.violet.application.menu.FileMenu;

public class TimingTreePanel extends JPanel{
	
	private MainFrame mainFrame;
	private FileMenu fileMenu;
	
	public JPopupMenu popupMenu;
	public JMenuItem newDiagram;
	public JMenuItem importDiagram;
	public JMenuItem deleteDiagram;
	
	private JScrollPane timingscrollpanel;
	private JPanel timingpanel;
	
	public JTree timingtree;
	private DefaultTreeModel timingtreemodel;
	private DefaultMutableTreeNode timingtreerootnode;

	public TimingTreePanel(FileMenu fileMenu, MainFrame mainFrame){
		
		this.mainFrame=mainFrame;
		this.fileMenu=fileMenu;
		
		init();
		
		timingtree.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		timingpanel=new JPanel();
		timingpanel.setLayout(new GridLayout());
		timingpanel.add(timingtree);
		timingpanel.setBackground(new Color(238, 238, 242));
		timingpanel.setBorder(null);
		
		timingscrollpanel=new JScrollPane(timingpanel);
		timingscrollpanel.setBorder(null);
		timingscrollpanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		timingscrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		this.setLayout(new GridLayout());
		this.add(timingscrollpanel);
			
	}

	private void init() {
		// TODO Auto-generated method stub
		
		timingtreerootnode=new DefaultMutableTreeNode("时序图列表");
		timingtreemodel=new DefaultTreeModel(timingtreerootnode);
		timingtree=new JTree(timingtreemodel);
		
		timingtree.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
				if(e.getButton()==e.BUTTON3){
					
					popupMenu = new JPopupMenu();
					newDiagram = new JMenuItem("新建");
					importDiagram = new JMenuItem("导入");
					deleteDiagram=new JMenuItem("删除");
					popupMenu.add(newDiagram);
					popupMenu.add(importDiagram);
					popupMenu.add(deleteDiagram);
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
					
					newDiagram.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							
							((JMenu) fileMenu.getFileNewMenu().getItem(1)).getItem(6).doClick();
							
//							DefaultMutableTreeNode node=new DefaultMutableTreeNode("ti +_+");
//							timingtreemodel.insertNodeInto(node, timingtreerootnode, timingtreerootnode.getChildCount());
//							TreePath path=new TreePath(timingtreerootnode.getPath());
//							if(!timingtree.isVisible(path)){
//								timingtree.makeVisible(path);
//							}
//							timingtree.getSelectionModel().setSelectionPath(new TreePath(node.getPath()));
							
						}
					});
					
					importDiagram.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							
							fileMenu.fileOpenItem.doClick();
							
//							DefaultMutableTreeNode node=new DefaultMutableTreeNode("ti *_*");
//							timingtreemodel.insertNodeInto(node, timingtreerootnode, timingtreerootnode.getChildCount());
//							TreePath path=new TreePath(timingtreerootnode.getPath());
//							if(!timingtree.isVisible(path)){
//								timingtree.makeVisible(path);
//							}
//							timingtree.getSelectionModel().setSelectionPath(new TreePath(node.getPath()));
							
						}
					});
					
					deleteDiagram.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							TreePath selectionpath=timingtree.getSelectionPath();
							
							if(selectionpath!=null){
								DefaultMutableTreeNode parentNode=(DefaultMutableTreeNode) selectionpath.getLastPathComponent();
								
								if(parentNode.isLeaf()){
									mainFrame.getStepOneCenterTabbedPane().getTimingDiagramButtonTabbedPanelLists().get(timingtreerootnode.getIndex(parentNode)).getTabbedbutton().doClick();
									fileMenu.fileCloseItem.doClick();
									mainFrame.deleteTabbedPane(mainFrame.getTimingWorkspaceList().get(timingtreerootnode.getIndex(parentNode)));
									mainFrame.getConsolePartPanel().getTextarea1().append("删除顺序图 "+mainFrame.getStepOneCenterTabbedPane().getSelectedButtonTabbedPanel().getTabbedbutton().getText()+" 的绘图面板\n");
								}
								
							}
						}
					});
					
				}
				
				if(e.getClickCount()==2){
					TreePath selectionpath=timingtree.getSelectionPath();
					DefaultMutableTreeNode parentNode=(DefaultMutableTreeNode) selectionpath.getLastPathComponent();
					System.out.println(parentNode.toString());
					
					int i=0;
					Enumeration<?> en=timingtreerootnode.children();
					while(en.hasMoreElements()){
						DefaultMutableTreeNode node;
						node=(DefaultMutableTreeNode) en.nextElement();
						System.out.println(i+"  "+node.getUserObject());
						if(node.equals(parentNode)){
							System.out.println("------------ "+i);
							
							ButtonTabbedPanel buttonTabbedPanel=mainFrame.getStepOneCenterTabbedPane().getTimingDiagramButtonTabbedPanelLists().get(i);
							if(!buttonTabbedPanel.isVisible()){
								buttonTabbedPanel.setVisible(true);
							}
							buttonTabbedPanel.getTabbedbutton().doClick();
							
						}
						i++;
					}
				}

			}
			
		});
		
	}

	public JTree getTimingtree() {
		return timingtree;
	}

	public DefaultTreeModel getTimingtreemodel() {
		return timingtreemodel;
	}

	public DefaultMutableTreeNode getTimingtreerootnode() {
		return timingtreerootnode;
	}
	
	
	
}

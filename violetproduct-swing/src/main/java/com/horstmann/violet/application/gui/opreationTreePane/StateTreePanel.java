package com.horstmann.violet.application.gui.opreationTreePane;

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
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.ButtonTabbedPanel;
import com.horstmann.violet.application.menu.FileMenu;

public class StateTreePanel extends JPanel{
	
	private MainFrame mainFrame;
	private FileMenu fileMenu;
	
	public JPopupMenu popupMenu;
	public JMenuItem newDiagram;
	public JMenuItem importDiagram;
	public JMenuItem deleteDiagram;
	
	public JTree statetree;
	private DefaultTreeModel statetreemodel;
	private DefaultMutableTreeNode statetreerootnode;

	public StateTreePanel(FileMenu fileMenu, MainFrame mainFrame){
		
		this.mainFrame=mainFrame;
		this.fileMenu=fileMenu;
		
		init();
		
		statetree.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		this.setLayout(new GridLayout());
		this.add(statetree);
			
	}

	private void init() {
		// TODO Auto-generated method stub
		
		statetreerootnode=new DefaultMutableTreeNode("×´Ì¬Í¼ÁÐ±í");
		statetreemodel=new DefaultTreeModel(statetreerootnode);
		statetree=new JTree(statetreemodel);
		
		statetree.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
				if(e.getButton()==e.BUTTON3){
					
					popupMenu = new JPopupMenu();
					newDiagram = new JMenuItem("ÐÂ½¨");
					importDiagram = new JMenuItem("µ¼Èë");
					deleteDiagram=new JMenuItem("É¾³ý");
					popupMenu.add(newDiagram);
					popupMenu.add(importDiagram);
					popupMenu.add(deleteDiagram);
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
					
					newDiagram.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							
							((JMenu) fileMenu.getFileNewMenu().getItem(1)).getItem(1).doClick();
							
//							DefaultMutableTreeNode node=new DefaultMutableTreeNode("st +_+");
//							statetreemodel.insertNodeInto(node, statetreerootnode, statetreerootnode.getChildCount());
//							TreePath path=new TreePath(statetreerootnode.getPath());
//							if(!statetree.isVisible(path)){
//								statetree.makeVisible(path);
//							}
//							statetree.getSelectionModel().setSelectionPath(new TreePath(node.getPath()));
							
						}
					});
					
					importDiagram.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							
							fileMenu.fileOpenItem.doClick();
							
//							DefaultMutableTreeNode node=new DefaultMutableTreeNode("st *_*");
//							statetreemodel.insertNodeInto(node, statetreerootnode, statetreerootnode.getChildCount());
//							TreePath path=new TreePath(statetreerootnode.getPath());
//							if(!statetree.isVisible(path)){
//								statetree.makeVisible(path);
//							}
//							statetree.getSelectionModel().setSelectionPath(new TreePath(node.getPath()));
							
						}
					});
					
					deleteDiagram.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							TreePath selectionpath=statetree.getSelectionPath();
							
							if(selectionpath!=null){
								DefaultMutableTreeNode parentNode=(DefaultMutableTreeNode) selectionpath.getLastPathComponent();
								
								if(parentNode.isLeaf()){
									mainFrame.getStepOneCenterTabbedPane().getStateDiagramButtonTabbedPanelLists().get(statetreerootnode.getIndex(parentNode)).getTabbedbutton().doClick();
									fileMenu.fileCloseItem.doClick();
									mainFrame.deleteTabbedPane(mainFrame.getStateWorkspaceList().get(statetreerootnode.getIndex(parentNode)));
									mainFrame.getConsolePartPanel().getTextarea1().append("É¾³ýË³ÐòÍ¼ "+mainFrame.getStepOneCenterTabbedPane().getSelectedButtonTabbedPanel().getTabbedbutton().getText()+" µÄ»æÍ¼Ãæ°å\n");
								}
								
							}
						}
					});
					
				}
				
				if(e.getClickCount()==2){
					TreePath selectionpath=statetree.getSelectionPath();
					DefaultMutableTreeNode parentNode=(DefaultMutableTreeNode) selectionpath.getLastPathComponent();
					System.out.println(parentNode.toString());
					
					int i=0;
					Enumeration<?> en=statetreerootnode.children();
					while(en.hasMoreElements()){
						DefaultMutableTreeNode node;
						node=(DefaultMutableTreeNode) en.nextElement();
						System.out.println(i+"  "+node.getUserObject());
						if(node.equals(parentNode)){
							System.out.println("------------ "+i);
							
							ButtonTabbedPanel buttonTabbedPanel=mainFrame.getStepOneCenterTabbedPane().getStateDiagramButtonTabbedPanelLists().get(i);
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

	public JTree getStatetree() {
		return statetree;
	}

	public DefaultTreeModel getStatetreemodel() {
		return statetreemodel;
	}

	public DefaultMutableTreeNode getStatetreerootnode() {
		return statetreerootnode;
	}
	
	
}

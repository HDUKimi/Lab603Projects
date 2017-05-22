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

public class UsecaseTreePanel extends JPanel{
	
	private MainFrame mainFrame;
	private FileMenu fileMenu;
	
	public JPopupMenu popupMenu;
	public JMenuItem newDiagram;
	public JMenuItem importDiagram;
	public JMenuItem deleteDiagram;
	
	public JTree usecasetree;
	private DefaultTreeModel usecasetreemodel;
	private DefaultMutableTreeNode usecasetreerootnode;

	public UsecaseTreePanel(FileMenu fileMenu, MainFrame mainFrame){
		
		this.mainFrame=mainFrame;
		this.fileMenu=fileMenu;
		
		init();
		
		usecasetree.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		this.setLayout(new GridLayout());
		this.add(usecasetree);
			
	}

	private void init() {
		// TODO Auto-generated method stub
		
		usecasetreerootnode=new DefaultMutableTreeNode("用例图列表");
		usecasetreemodel=new DefaultTreeModel(usecasetreerootnode);
		usecasetree=new JTree(usecasetreemodel);
		
		usecasetree.addMouseListener(new MouseAdapter() {

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
							
							((JMenu) fileMenu.getFileNewMenu().getItem(1)).getItem(0).doClick();
							
//							DefaultMutableTreeNode node=new DefaultMutableTreeNode("uc +_+");
//							usecasetreemodel.insertNodeInto(node, usecasetreerootnode, usecasetreerootnode.getChildCount());
//							TreePath path=new TreePath(usecasetreerootnode.getPath());
//							if(!usecasetree.isVisible(path)){
//								usecasetree.makeVisible(path);
//							}
//							usecasetree.getSelectionModel().setSelectionPath(new TreePath(node.getPath()));
							
						}
					});
					
					importDiagram.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							
							fileMenu.fileOpenItem.doClick();
							
//							DefaultMutableTreeNode node=new DefaultMutableTreeNode("uc *_*");
//							usecasetreemodel.insertNodeInto(node, usecasetreerootnode, usecasetreerootnode.getChildCount());
//							TreePath path=new TreePath(usecasetreerootnode.getPath());
//							if(!usecasetree.isVisible(path)){
//								usecasetree.makeVisible(path);
//							}
//							usecasetree.getSelectionModel().setSelectionPath(new TreePath(node.getPath()));
							
						}
					});
					
					deleteDiagram.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							TreePath selectionpath=usecasetree.getSelectionPath();
							
							if(selectionpath!=null){
								DefaultMutableTreeNode parentNode=(DefaultMutableTreeNode) selectionpath.getLastPathComponent();
								
								if(parentNode.isLeaf()){
									mainFrame.getStepOneCenterTabbedPane().getUsecaseDiagramButtonTabbedPanelLists().get(usecasetreerootnode.getIndex(parentNode)).getTabbedbutton().doClick();
									fileMenu.fileCloseItem.doClick();
									mainFrame.deleteTabbedPane(mainFrame.getUseCaseWorkspaceList().get(usecasetreerootnode.getIndex(parentNode)));
									mainFrame.getConsolePartPanel().getTextarea1().append("删除顺序图 "+mainFrame.getStepOneCenterTabbedPane().getSelectedButtonTabbedPanel().getTabbedbutton().getText()+" 的绘图面板\n");
								}
								
							}
						}
					});
					
				}
				
				if(e.getClickCount()==2){
					TreePath selectionpath=usecasetree.getSelectionPath();
					DefaultMutableTreeNode parentNode=(DefaultMutableTreeNode) selectionpath.getLastPathComponent();
					System.out.println(parentNode.toString());
					
					int i=0;
					Enumeration<?> en=usecasetreerootnode.children();
					while(en.hasMoreElements()){
						DefaultMutableTreeNode node;
						node=(DefaultMutableTreeNode) en.nextElement();
						System.out.println(i+"  "+node.getUserObject());
						if(node.equals(parentNode)){
							System.out.println("------------ "+i);
							
							ButtonTabbedPanel buttonTabbedPanel=mainFrame.getStepOneCenterTabbedPane().getUsecaseDiagramButtonTabbedPanelLists().get(i);
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

	public JTree getUsecasetree() {
		return usecasetree;
	}

	public DefaultTreeModel getUsecasetreemodel() {
		return usecasetreemodel;
	}

	public DefaultMutableTreeNode getUsecasetreerootnode() {
		return usecasetreerootnode;
	}
	
}

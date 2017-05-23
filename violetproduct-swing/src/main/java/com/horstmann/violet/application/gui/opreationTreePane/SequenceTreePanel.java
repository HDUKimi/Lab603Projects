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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.ButtonTabbedPanel;
import com.horstmann.violet.application.menu.FileMenu;
import com.horstmann.violet.framework.dialog.DialogFactory;

public class SequenceTreePanel extends JPanel{

	private MainFrame mainFrame;
	private FileMenu fileMenu;
	
	public JPopupMenu popupMenu;
	public JMenuItem newDiagram;
	public JMenuItem importDiagram;
	public JMenuItem deleteDiagram;
	
	private DialogFactory dialogFactory;
	
	public JTree sequencetree;
	private DefaultTreeModel sequencetreemodel;
	private DefaultMutableTreeNode sequencetreerootnode;

	public SequenceTreePanel(FileMenu fileMenu, MainFrame mainFrame){
		
		this.mainFrame=mainFrame;
		this.fileMenu=fileMenu;
		
		init();
		
		sequencetree.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		this.setLayout(new GridLayout());
		this.add(sequencetree);
			
	}

	private void init() {
		// TODO Auto-generated method stub
		
		sequencetreerootnode=new DefaultMutableTreeNode("顺序图列表");
		sequencetreemodel=new DefaultTreeModel(sequencetreerootnode);
		sequencetree=new JTree(sequencetreemodel);
		
		sequencetree.addMouseListener(new MouseAdapter() {

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
							
							((JMenu) fileMenu.getFileNewMenu().getItem(1)).getItem(3).doClick();
							
//							String nodename=mainFrame.getStepOneCenterTabbedPane().getSelectedButtonTabbedPanel().getTabbedbutton().getText();
//							DefaultMutableTreeNode node=new DefaultMutableTreeNode(nodename);
//							sequencetreemodel.insertNodeInto(node, sequencetreerootnode, sequencetreerootnode.getChildCount());
//							TreePath path=new TreePath(sequencetreerootnode.getPath());
//							if(!sequencetree.isVisible(path)){
//								sequencetree.makeVisible(path);
//							}
//							sequencetree.getSelectionModel().setSelectionPath(new TreePath(node.getPath()));
							
						}
					});
					
					importDiagram.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							
							fileMenu.fileOpenItem.doClick();
//							System.out.println("nodename:");
//							String nodename=mainFrame.getStepOneCenterTabbedPane().getSelectedButtonTabbedPanel().getTabbedbutton().getText();
//							System.out.println("---"+nodename);
//							DefaultMutableTreeNode node=new DefaultMutableTreeNode(nodename);
//							sequencetreemodel.insertNodeInto(node, sequencetreerootnode, sequencetreerootnode.getChildCount());
//							TreePath path=new TreePath(sequencetreerootnode.getPath());
//							if(!sequencetree.isVisible(path)){
//								sequencetree.makeVisible(path);
//							}
//							sequencetree.getSelectionModel().setSelectionPath(new TreePath(node.getPath()));
							
						}
					});
					
					deleteDiagram.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							TreePath selectionpath=sequencetree.getSelectionPath();
							
							if(selectionpath!=null){
								
//								JOptionPane optionPane = new JOptionPane();
//		                        optionPane.setMessage("确定要删除");
//		                        optionPane.setOptionType(JOptionPane.YES_NO_CANCEL_OPTION);
//		                        optionPane.setIcon(null);
//		                        dialogFactory.showDialog(optionPane,"delete", true);
//
//		                        int result = JOptionPane.CANCEL_OPTION;
//		                        if(result==JOptionPane.YES_OPTION){
//		                        	System.out.println("yes");
//		                        }
//		                        if(result==JOptionPane.NO_OPTION){
//		                        	System.out.println("no");
//		                        }
//		                        if(result==JOptionPane.CANCEL_OPTION){
//		                        	System.out.println("cancel");
//		                        }
		                        
								
								DefaultMutableTreeNode parentNode=(DefaultMutableTreeNode) selectionpath.getLastPathComponent();
								
								if(parentNode.isLeaf()){
									mainFrame.getStepOneCenterTabbedPane().getSequenceDiagramButtonTabbedPanelLists().get(sequencetreerootnode.getIndex(parentNode)).getTabbedbutton().doClick();
									fileMenu.fileCloseItem.doClick();
									mainFrame.deleteTabbedPane(mainFrame.getSequenceWorkspaceList().get(sequencetreerootnode.getIndex(parentNode)));
									mainFrame.getConsolePartPanel().getTextarea1().append("删除顺序图 "+mainFrame.getStepOneCenterTabbedPane().getSelectedButtonTabbedPanel().getTabbedbutton().getText()+" 的绘图面板\n");
									
									mainFrame.getAttributePartOnePanel().getNamelabel().setText("");
									mainFrame.getAttributePartOnePanel().getAttributepanel().removeAll();
								}
								
							}
						}
					});
					
				}
				
				if(e.getClickCount()==2){
					TreePath selectionpath=sequencetree.getSelectionPath();
					DefaultMutableTreeNode parentNode=(DefaultMutableTreeNode) selectionpath.getLastPathComponent();
//					System.out.println(parentNode.toString());
					
					int i=0;
					Enumeration<?> en=sequencetreerootnode.children();
					while(en.hasMoreElements()){
						DefaultMutableTreeNode node;
						node=(DefaultMutableTreeNode) en.nextElement();
//						System.out.println(i+"  "+node.getUserObject());
						if(node.equals(parentNode)){
							
//							node.setUserObject("改名");  
//					        sequencetree.updateUI(); 
							
							ButtonTabbedPanel buttonTabbedPanel=mainFrame.getStepOneCenterTabbedPane().getSequenceDiagramButtonTabbedPanelLists().get(i);
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

	public JTree getSequencetree() {
		return sequencetree;
	}

	public DefaultTreeModel getSequencetreemodel() {
		return sequencetreemodel;
	}

	public DefaultMutableTreeNode getSequencetreerootnode() {
		return sequencetreerootnode;
	}
	
}

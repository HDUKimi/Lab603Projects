/*
 Violet - A program for editing UML diagrams.

 Copyright (C) 2008 Cay S. Horstmann (http://horstmann.com)
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

package com.horstmann.violet.application.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Paint;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.geom.Point2D;
import java.beans.BeanInfo;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.horstmann.violet.application.consolepart.AbstractTestCaseResultPanel;
import com.horstmann.violet.application.consolepart.AttributePartOnePanel;
import com.horstmann.violet.application.consolepart.AttributePartTwoPanel;
import com.horstmann.violet.application.consolepart.ConsolePart;
import com.horstmann.violet.application.consolepart.ConsolePartPanel;
import com.horstmann.violet.application.consolepart.TestCaseConfirmResultPanel;
import com.horstmann.violet.application.consolepart.TestCaseInstantiationResultPanel;
import com.horstmann.violet.application.consolepart.ValidationResultPanel;
import com.horstmann.violet.application.gui.opreationTreePane.TestCaseGenerationPanel;
import com.horstmann.violet.application.gui.homeTabbedPanel.HomeAllTabbedPanel;
import com.horstmann.violet.application.gui.opreationTreePane.ModelExistValidationPanel;
import com.horstmann.violet.application.gui.opreationTreePane.ModelTransformationPanel;
import com.horstmann.violet.application.gui.opreationTreePane.ProjectTree;
import com.horstmann.violet.application.gui.opreationTreePane.TestCaseConfirmationPanel;
import com.horstmann.violet.application.gui.opreationTreePane.TestCaseInstantiationPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.ButtonTabbedPanel;
import com.horstmann.violet.application.gui.util.tanchao.StartFileCheck;
import com.horstmann.violet.application.help.AboutDialog;
import com.horstmann.violet.application.menu.MenuFactory;
import com.horstmann.violet.framework.dialog.DialogFactory;
import com.horstmann.violet.framework.file.GraphFile;
import com.horstmann.violet.framework.file.IFile;
import com.horstmann.violet.framework.file.IGraphFile;
import com.horstmann.violet.framework.file.chooser.IFileChooserService;
import com.horstmann.violet.framework.injection.bean.ManiocFramework.BeanInjector;
import com.horstmann.violet.framework.injection.bean.ManiocFramework.InjectedBean;
import com.horstmann.violet.framework.injection.resources.ResourceBundleInjector;
import com.horstmann.violet.framework.injection.resources.annotation.ResourceBundleBean;
import com.horstmann.violet.framework.theme.ITheme;
import com.horstmann.violet.framework.theme.ThemeManager;
import com.horstmann.violet.product.diagram.abstracts.IGraph;
import com.horstmann.violet.product.diagram.abstracts.node.AbstractNode;
import com.horstmann.violet.product.diagram.abstracts.property.ArrowHead;
import com.horstmann.violet.product.diagram.abstracts.property.BentStyle;
import com.horstmann.violet.product.diagram.abstracts.property.LineStyle;
import com.horstmann.violet.product.diagram.sequence.RefNode;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.IWorkspaceListener;
import com.horstmann.violet.workspace.Workspace;
import com.horstmann.violet.workspace.editorpart.behavior.EditSelectedBehavior;

/**
 * This desktop frame contains panes that show graphs.
 * 
 * @author Alexandre de Pellegrin
 */
@ResourceBundleBean(resourceReference = AboutDialog.class)
public class MainFrame extends JFrame
{
    /**
     * Constructs a blank frame with a desktop pane but no graph windows.
     * 
     */
    public MainFrame()
    {
        BeanInjector.getInjector().inject(this);
        ResourceBundleInjector.getInjector().inject(this);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.dialogFactory.setDialogOwner(this);
        decorateFrame();
        setInitialSize();
		setUIManeger();
		createMenuBar();
		
		//��ʼ���ļ��б�
		StartFileCheck sfc=new StartFileCheck();
		sfc.FileCheck();
		
        getContentPane().add(this.getMainPanel());
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final int screenWidth = (int) screenSize.getWidth();
		final int screenHeight = (int) screenSize.getHeight();
//		
//		this.setMinimumSize(new Dimension(screenWidth/3, screenHeight/2));
		
		this.addWindowStateListener(new WindowStateListener() {
			
			@Override
			public void windowStateChanged(WindowEvent e) {
				// TODO Auto-generated method stub
				
				if(e.getNewState()==1||e.getNewState()==7){
//					System.out.println("---------------------������С��---------------------");
//					System.out.println(getSize());
//					System.out.println(getJs1().getDividerLocation()+"  "+getJs2().getDividerLocation()+"  "+getJs3().getDividerLocation());
//					System.out.println(getOpreationPart().size()+"  "+getCenterTabPanel().size()+"  "+getConsolePartPanel().size()+"  "+getAttributePartPanel().size());
				}else if(e.getNewState()==0){
//					System.out.println("---------------------���ڻظ���ʼ״̬---------------------");
//					System.out.println(getSize());
//					System.out.println(getJs1().getDividerLocation()+"  "+getJs2().getDividerLocation()+"  "+getJs3().getDividerLocation());
//					System.out.println(getOpreationPart().size()+"  "+getCenterTabPanel().size()+"  "+getConsolePartPanel().size()+"  "+getAttributePartPanel().size());					
				}else if(e.getNewState()==6){
//					System.out.println("---------------------�������---------------------");
//					System.out.println(getSize());
//					System.out.println(getJs1().getDividerLocation()+"  "+getJs2().getDividerLocation()+"  "+getJs3().getDividerLocation());
//					System.out.println(getOpreationPart().size()+"  "+getCenterTabPanel().size()+"  "+getConsolePartPanel().size()+"  "+getAttributePartPanel().size());
					
					getJs1().setDividerLocation(screenWidth/8);
					getJs2().setDividerLocation((int)getJs2().getSize().getWidth()-screenWidth/6);
					getJs3().setDividerLocation((int)getJs3().getSize().getHeight()-screenHeight/5);
					
				}
				
			}
		});
		
		this.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
//				System.out.println("---------------------������ʾ---------------------");
//				System.out.println(getSize());
//				System.out.println(getJs1().getDividerLocation()+"  "+getJs2().getDividerLocation()+"  "+getJs3().getDividerLocation());
//				System.out.println(getOpreationPart().size()+"  "+getCenterTabPanel().size()+"  "+getConsolePartPanel().size()+"  "+getAttributePartPanel().size());
				
//				getJs1().setDividerLocation(screenWidth/8);
//				getJs2().setDividerLocation((int)getJs2().getSize().getWidth()-screenWidth/8);
//				getJs3().setDividerLocation((int)getJs3().getSize().getHeight()-screenHeight/5);
				
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				
				if(getStepindex()>0){
//					System.out.println("---------------------�仯���ڴ�С---------------------");
//					System.out.println(getSize());
//					System.out.println(getJs1().getDividerLocation()+"  "+getJs2().getDividerLocation()+"  "+getJs3().getDividerLocation());
//					System.out.println(getOpreationPart().size()+"  "+getCenterTabPanel().size()+"  "+getConsolePartPanel().size()+"  "+getAttributePartPanel().size());
					
					if(getJs1().getDividerLocation()==0){
						getJs1().setDividerLocation(screenWidth/8);
					}
					if(getJs2().getDividerLocation()==0){
						getJs2().setDividerLocation((int)getJs2().getSize().getWidth()-screenWidth/6);
					}
					if(getJs3().getDividerLocation()==0){
						getJs3().setDividerLocation((int)getJs3().getSize().getHeight()-screenHeight/5);
					}
					
					if(getJs1().getSize().getWidth()<screenWidth*3/8){
						if(getOpreationPart().size().getWidth()<screenWidth/8){
							getJs1().setDividerLocation((int)(getJs1().getSize().getWidth())/3);
						}
						if(getAttributePart().size().getWidth()<screenWidth/6){
							getJs2().setDividerLocation((int)(getJs1().getSize().getWidth())/3);
						}
					}
					
					if(getJs3().getSize().getHeight()<screenHeight*2/5){
						if(getConsolePartPanel().size().getHeight()<screenHeight/5){
							getJs3().setDividerLocation((int)getJs3().getSize().getHeight()/2);
						}
					}
					
				}
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
//				System.out.println("---------------------�����ƶ�---------------------");
//				System.out.println(getSize());
//				System.out.println(getJs1().getDividerLocation()+"  "+getJs2().getDividerLocation()+"  "+getJs3().getDividerLocation());
//				System.out.println(getOpreationPart().size()+"  "+getCenterTabPanel().size()+"  "+getConsolePartPanel().size()+"  "+getAttributePartPanel().size());
				
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
//				System.out.println("---------------------��������---------------------");
//				System.out.println(getSize());
//				System.out.println(getJs1().getDividerLocation()+"  "+getJs2().getDividerLocation()+"  "+getJs3().getDividerLocation());
//				System.out.println(getOpreationPart().size()+"  "+getCenterTabPanel().size()+"  "+getConsolePartPanel().size()+"  "+getAttributePartPanel().size());
			}
		});

    }

	private void setUIManeger() {
		// TODO Auto-generated method stub
		
//		UIManager.put("TabbedPane.unselectedBackground", new Color(64, 66, 68));
		UIManager.put("TabbedPane.selected",new Color(64, 66, 68));
		UIManager.put("TabbedPane.unselected",new Color(64, 66, 68));
		UIManager.put("TabbedPane.selectedForeground", Color.WHITE);
		
//		URL Tree_collapsed_url = this.getClass().getResource("Tree_collapsed.png");
//		ImageIcon Tree_collapsed_icon = new ImageIcon(Tree_collapsed_url);
//		Tree_collapsed_icon.setImage(Tree_collapsed_icon.getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));
//
//		URL Tree_expanded_url = this.getClass().getResource("Tree_expanded.png");
//		ImageIcon Tree_expanded_icon = new ImageIcon(Tree_expanded_url);
//		Tree_expanded_icon.setImage(Tree_expanded_icon.getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));
		
//		String absolutePath=System.getProperty("user.dir");
//		String pngpath = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";
//        demoRenderer.setClosedIcon(new ImageIcon(pngpath+"jiahao.png"));
//        demoRenderer.setOpenIcon(new ImageIcon(pngpath+"jianhao.png"));
		
		
		UIManager.put("Tree.collapsedIcon", new ImageIcon(this.getClass().getResource("ImagePart/collapsed.png")));
		UIManager.put("Tree.expandedIcon", new ImageIcon(this.getClass().getResource("ImagePart/expanded.png")));
//		UIManager.put("Tree.openIcon", null);
//		UIManager.put("Tree.closeIcon", null);
		
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		UIDefaults uiDf = UIManager.getLookAndFeelDefaults();
//		uiDf.put("SplitPane.background", new ColorUIResource(Color.RED));
		
		UIManager.put("SplitPane.background", new Color(41,57,85));
		
//		UIManager.put("Menu.foreground", new Color(0, 0, 0));
//		UIManager.put("Menu.background", new Color(255,255,255));
		
	}

    /**
     * Sets initial size on startup
     */
    private void setInitialSize()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
//        setBounds(screenWidth / 16, screenHeight / 16, screenWidth * 7 / 8, screenHeight * 7 / 8);
//        setBounds(0, 0, screenWidth, screenHeight);
        setExtendedState(JFrame.MAXIMIZED_BOTH);  
        // For screenshots only -> setBounds(50, 50, 850, 650);
    }

    /**
     * Decorates the frame (title, icon...)
     */
    private void decorateFrame()
    {
        setTitle(this.applicationName);
        setIconImage(this.applicationIcon);
    }

    /**
     * Creates menu bar
     */
    private void createMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setFont(this.themeManager.getTheme().getMenubarFont());
        MenuFactory menuFactory = getMenuFactory();
        menuBar.add(menuFactory.getFileMenu(this));
        menuBar.add(menuFactory.getEditMenu(this));
        menuBar.add(menuFactory.getViewMenu(this));
        menuBar.add(menuFactory.getHelpMenu(this));
        setJMenuBar(menuBar);
      
    }

	public void deleteTabbedPane(final IWorkspace workspace) {
		if (workspace.getTitle().toString().contains(".ucase.violet.xml")
				|| workspace.getTitle().toString().substring(2, 4).equals("Us"))// ���������ͼ
		{

			JTree usecasetree=this.getProjectTree().getUsecaseTreePanel().getUsecasetree();
			DefaultTreeModel usecasetreemodel=this.getProjectTree().getUsecaseTreePanel().getUsecasetreemodel();
			DefaultMutableTreeNode usecasetreerootnode=this.getProjectTree().getUsecaseTreePanel().getUsecasetreerootnode();
			
			TreePath selectionpath=usecasetree.getSelectionPath();
			DefaultMutableTreeNode parentNode=(DefaultMutableTreeNode) selectionpath.getLastPathComponent();
			
			int index=usecasetreerootnode.getIndex(parentNode);
			usecasetreemodel.removeNodeFromParent(parentNode);
			this.UseCaseWorkspaceList.remove(index);
			this.getStepOneCenterTabbedPane().getButtonTabbedPanel().remove(this.getStepOneCenterTabbedPane().getUsecaseDiagramButtonTabbedPanelLists().get(index));
			this.getStepOneCenterTabbedPane().getAllDiagramButtonTabbedPanelLists().remove(this.getStepOneCenterTabbedPane().getUsecaseDiagramButtonTabbedPanelLists().get(index));
			this.getStepOneCenterTabbedPane().getUsecaseDiagramButtonTabbedPanelLists().remove(index);
			for(ButtonTabbedPanel buttonTabbedPanel:this.getStepOneCenterTabbedPane().getUsecaseDiagramButtonTabbedPanelLists()){
				if(buttonTabbedPanel.getButtontabbedpanelindex()>=index){
					buttonTabbedPanel.setButtontabbedpanelindex(buttonTabbedPanel.getButtontabbedpanelindex()-1);
				}
			}
			
		}
		if (workspace.getTitle().toString().contains(".timing.violet.xml")
				|| workspace.getTitle().toString().substring(2, 4).equals("Ti"))// ʱ��ͼ
		{

			JTree timingtree=this.getProjectTree().getTimingTreePanel().getTimingtree();
			DefaultTreeModel timingtreemodel=this.getProjectTree().getTimingTreePanel().getTimingtreemodel();
			DefaultMutableTreeNode timingtreerootnode=this.getProjectTree().getTimingTreePanel().getTimingtreerootnode();
			
			TreePath selectionpath=timingtree.getSelectionPath();
			DefaultMutableTreeNode parentNode=(DefaultMutableTreeNode) selectionpath.getLastPathComponent();
			
			int index=timingtreerootnode.getIndex(parentNode);
			timingtreemodel.removeNodeFromParent(parentNode);
			this.TimingWorkspaceList.remove(index);
			this.getStepOneCenterTabbedPane().getButtonTabbedPanel().remove(this.getStepOneCenterTabbedPane().getTimingDiagramButtonTabbedPanelLists().get(index));
			this.getStepOneCenterTabbedPane().getAllDiagramButtonTabbedPanelLists().remove(this.getStepOneCenterTabbedPane().getTimingDiagramButtonTabbedPanelLists().get(index));
			this.getStepOneCenterTabbedPane().getTimingDiagramButtonTabbedPanelLists().remove(index);
			for(ButtonTabbedPanel buttonTabbedPanel:this.getStepOneCenterTabbedPane().getTimingDiagramButtonTabbedPanelLists()){
				if(buttonTabbedPanel.getButtontabbedpanelindex()>=index){
					buttonTabbedPanel.setButtontabbedpanelindex(buttonTabbedPanel.getButtontabbedpanelindex()-1);
				}
			}
			
		}
		if (workspace.getTitle().toString().contains(".seq.violet.xml")
				|| workspace.getTitle().toString().substring(2, 4).equals("Se"))// �����˳��ͼ
		{

			JTree sequencetree=this.getProjectTree().getSequenceTreePanel().getSequencetree();
			DefaultTreeModel sequencetreemodel=this.getProjectTree().getSequenceTreePanel().getSequencetreemodel();
			DefaultMutableTreeNode sequencetreerootnode=this.getProjectTree().getSequenceTreePanel().getSequencetreerootnode();
			
			TreePath selectionpath=sequencetree.getSelectionPath();
			DefaultMutableTreeNode parentNode=(DefaultMutableTreeNode) selectionpath.getLastPathComponent();
			
			int index=sequencetreerootnode.getIndex(parentNode);
			sequencetreemodel.removeNodeFromParent(parentNode);
			this.SequenceWorkspaceList.remove(index);
			this.getStepOneCenterTabbedPane().getButtonTabbedPanel().remove(this.getStepOneCenterTabbedPane().getSequenceDiagramButtonTabbedPanelLists().get(index));
			this.getStepOneCenterTabbedPane().getAllDiagramButtonTabbedPanelLists().remove(this.getStepOneCenterTabbedPane().getSequenceDiagramButtonTabbedPanelLists().get(index));
			this.getStepOneCenterTabbedPane().getSequenceDiagramButtonTabbedPanelLists().remove(index);
			for(ButtonTabbedPanel buttonTabbedPanel:this.getStepOneCenterTabbedPane().getSequenceDiagramButtonTabbedPanelLists()){
				if(buttonTabbedPanel.getButtontabbedpanelindex()>=index){
					buttonTabbedPanel.setButtontabbedpanelindex(buttonTabbedPanel.getButtontabbedpanelindex()-1);
				}
			}
			
		}
		if (workspace.getTitle().toString().contains(".state.violet.xml")
				|| workspace.getTitle().toString().substring(2, 4).equals("St"))// �����״̬ͼ
		{

			JTree statetree=this.getProjectTree().getStateTreePanel().getStatetree();
			DefaultTreeModel statetreemodel=this.getProjectTree().getStateTreePanel().getStatetreemodel();
			DefaultMutableTreeNode statetreerootnode=this.getProjectTree().getStateTreePanel().getStatetreerootnode();
			
			TreePath selectionpath=statetree.getSelectionPath();
			DefaultMutableTreeNode parentNode=(DefaultMutableTreeNode) selectionpath.getLastPathComponent();
			
			int index=statetreerootnode.getIndex(parentNode);
			statetreemodel.removeNodeFromParent(parentNode);
			this.StateWorkspaceList.remove(index);
			this.getStepOneCenterTabbedPane().getButtonTabbedPanel().remove(this.getStepOneCenterTabbedPane().getStateDiagramButtonTabbedPanelLists().get(index));
			this.getStepOneCenterTabbedPane().getAllDiagramButtonTabbedPanelLists().remove(this.getStepOneCenterTabbedPane().getStateDiagramButtonTabbedPanelLists().get(index));
			this.getStepOneCenterTabbedPane().getStateDiagramButtonTabbedPanelLists().remove(index);
			for(ButtonTabbedPanel buttonTabbedPanel:this.getStepOneCenterTabbedPane().getStateDiagramButtonTabbedPanelLists()){
				if(buttonTabbedPanel.getButtontabbedpanelindex()>=index){
					buttonTabbedPanel.setButtontabbedpanelindex(buttonTabbedPanel.getButtontabbedpanelindex()-1);
				}
			}
			
		}
	}
	
	/**
	 * Adds a tabbed pane (only if not already added)
	 * 
	 * @param c
	 *            the component to display in the internal frame
	 */
	public void addTabbedPane(final IWorkspace workspace) {
//       replaceWelcomePanelByTabbedPane(); 
    	//�����ͼ��Ԫ�ص�ʱ�������ж���������ͼ��
    	
        if(workspace.getTitle().toString().contains(".ucase.violet.xml")
        		||workspace.getTitle().toString().substring(2, 4).equals("Us"))//���������ͼ
     	{
     		if(this.UseCaseWorkspaceList.contains(workspace))
     		{
     			
     			return;
     		}
//			this.UseCaseWorkspaceList.clear();// ���
//			int index=this.getStepOneCenterTabbedPane().getUsecaseDiagramButtonTabbedPanelLists().size();
			int index=this.getProjectTree().getUsecaseTreePanel().getUsecasetreerootnode().getChildCount();
//			int size=this.UseCaseWorkspaceList.size();
//			for(int i=size;i<=index;i++){
//				this.UseCaseWorkspaceList.add(i, workspace);//Ԥ���
//			}
//			this.UseCaseWorkspaceList.remove(index);
			
			if(index==0){
				this.UseCaseWorkspaceList.clear();
				this.getStepOneCenterTabbedPane().getUsecaseDiagramButtonTabbedPanelLists().clear();
			}
			
			this.UseCaseWorkspaceList.add(index,workspace);

			this.getStepOneCenterTabbedPane().getUsecaseDiagramTabbedPane().removeAll();// ֻ����һ��Tabҳ
//			this.getStepOneCenterTabbedPane().getUsecaseDiagramTabbedPane().add(workspace.getAWTComponent(),
//					new GBC(0, 0).setWeight(1, 1).setFill(GBC.BOTH));
			
			ButtonTabbedPanel buttontabbedpanel=new ButtonTabbedPanel(this, workspace,index, (workspace.getTitle().toString().split("\\."))[0]);
			this.getStepOneCenterTabbedPane().getUsecaseDiagramButtonTabbedPanelLists().add(index,buttontabbedpanel);
			this.getStepOneCenterTabbedPane().getButtonTabbedPanel().add(buttontabbedpanel);
			this.getStepOneCenterTabbedPane().getAllDiagramButtonTabbedPanelLists().add(buttontabbedpanel);
			
			listenToDiagramPanelEvents(workspace, UseCaseWorkspaceList);
     		
   		     repaint();    		    
     	}
        if(workspace.getTitle().toString().contains(".timing.violet.xml")
        		||workspace.getTitle().toString().substring(2, 4).equals("Ti"))//ʱ��ͼ
     	{
     		if(this.TimingWorkspaceList.contains(workspace))
     		{
     			return;
     		}
//			this.TimingWorkspaceList.clear();// ��֤ÿһ���½�����ֻ����1��Typeҳ
//			int index=this.getStepOneCenterTabbedPane().getTimingDiagramButtonTabbedPanelLists().size();
			int index=this.getProjectTree().getTimingTreePanel().getTimingtreerootnode().getChildCount();
//			int size=this.TimingWorkspaceList.size();
//			for(int i=size;i<=index;i++){
//				this.TimingWorkspaceList.add(i, workspace);
//			}
//			this.TimingWorkspaceList.remove(index);
			
			if(index==0){
				this.TimingWorkspaceList.clear();
				this.getStepOneCenterTabbedPane().getTimingDiagramButtonTabbedPanelLists().clear();
			}
			
			this.TimingWorkspaceList.add(index,workspace);
			
			this.getStepOneCenterTabbedPane().getTimingDiagramTabbedPane().removeAll();
//			this.getStepOneCenterTabbedPane().getTimingDiagramTabbedPane().add(workspace.getAWTComponent(),
//					new GBC(0, 0).setWeight(1, 1).setFill(GBC.BOTH));
			
			ButtonTabbedPanel buttontabbedpanel=new ButtonTabbedPanel(this, workspace,index, (workspace.getTitle().toString().split("\\."))[0]);
			this.getStepOneCenterTabbedPane().getTimingDiagramButtonTabbedPanelLists().add(index,buttontabbedpanel);
			this.getStepOneCenterTabbedPane().getButtonTabbedPanel().add(buttontabbedpanel);
			this.getStepOneCenterTabbedPane().getAllDiagramButtonTabbedPanelLists().add(buttontabbedpanel);
			
			listenToDiagramPanelEvents(workspace, TimingWorkspaceList);
     		
     	   
     	    repaint();    		    
     	}
     	if(workspace.getTitle().toString().contains(".seq.violet.xml")
     			||workspace.getTitle().toString().substring(2, 4).equals("Se"))//�����˳��ͼ
     			
 		{
     	
     		if(this.SequenceWorkspaceList.contains(workspace))
     		{
     			return;
     		}
//			this.SequenceWorkspaceList.clear();
//			int index=this.getStepOneCenterTabbedPane().getSequenceDiagramButtonTabbedPanelLists().size();
			int index=this.getProjectTree().getSequenceTreePanel().getSequencetreerootnode().getChildCount();
//			int size=this.SequenceWorkspaceList.size();
//			for(int i=size;i<=index;i++){
//				this.SequenceWorkspaceList.add(i, workspace);
//			}
//			this.SequenceWorkspaceList.remove(index);
			
			if(index==0){
				this.SequenceWorkspaceList.clear();
				this.getStepOneCenterTabbedPane().getSequenceDiagramButtonTabbedPanelLists().clear();
			}
			
			this.SequenceWorkspaceList.add(index,workspace);
			this.getStepOneCenterTabbedPane().getSequenceDiagramTabbedPane().removeAll();
//			this.getStepOneCenterTabbedPane().getSequenceDiagramTabbedPane().add(workspace.getAWTComponent(),
//					new GBC(0, 0).setWeight(1, 1).setFill(GBC.BOTH));
			
			ButtonTabbedPanel buttontabbedpanel=new ButtonTabbedPanel(this, workspace,index, (workspace.getTitle().toString().split("\\."))[0]);
			this.getStepOneCenterTabbedPane().getSequenceDiagramButtonTabbedPanelLists().add(index,buttontabbedpanel);
			this.getStepOneCenterTabbedPane().getButtonTabbedPanel().add(buttontabbedpanel);
			this.getStepOneCenterTabbedPane().getAllDiagramButtonTabbedPanelLists().add(buttontabbedpanel);
			
			listenToDiagramPanelEvents(workspace, SequenceWorkspaceList);
     	    repaint();    		              
 		}    
     	if(workspace.getTitle().toString().contains(".state.violet.xml")
     			||workspace.getTitle().toString().substring(2, 4).equals("St"))//�����״̬ͼ
 		{
     		if(this.StateWorkspaceList.contains(workspace))
     		{
     			return;
     		}
//			this.StateWorkspaceList.clear();
//			int index=this.getStepOneCenterTabbedPane().getStateDiagramButtonTabbedPanelLists().size();
			int index=this.getProjectTree().getStateTreePanel().getStatetreerootnode().getChildCount();
//			int size=this.StateWorkspaceList.size();
//			for(int i=size;i<=index;i++){
//				this.StateWorkspaceList.add(i, workspace);
//			}
//			this.StateWorkspaceList.remove(index);
			
			if(index==0){
				this.StateWorkspaceList.clear();
				this.getStepOneCenterTabbedPane().getStateDiagramButtonTabbedPanelLists().clear();
			}
			
			this.StateWorkspaceList.add(index,workspace);
			
			this.getStepOneCenterTabbedPane().getStateDiagramTabbedPane().removeAll();
//			this.getStepOneCenterTabbedPane().getStateDiagramTabbedPane().add(workspace.getAWTComponent(),
//					new GBC(0, 0).setWeight(1, 1).setFill(GBC.BOTH));
			
			ButtonTabbedPanel buttontabbedpanel=new ButtonTabbedPanel(this, workspace,index, (workspace.getTitle().toString().split("\\."))[0]);
			this.getStepOneCenterTabbedPane().getStateDiagramButtonTabbedPanelLists().add(index,buttontabbedpanel);
			this.getStepOneCenterTabbedPane().getButtonTabbedPanel().add(buttontabbedpanel);
			this.getStepOneCenterTabbedPane().getAllDiagramButtonTabbedPanelLists().add(buttontabbedpanel);

			listenToDiagramPanelEvents(workspace, StateWorkspaceList);
     	  
     	    repaint();     		    
 		}
     	 /*
     	  * չʾuppaal
     	  */
     	if(workspace.getTitle().toString().contains(".uppaal.violet.xml")
     			&&!workspace.getTitle().toString().substring(0, 3).equals(("abs")))
     	{		
     		if(this.UppaalWorkspaceList.contains(workspace))
     		{
     			return;
     		}
     		this.UppaalWorkspaceList.clear();
     		this.UppaalWorkspaceList.add(workspace);
     		this.getStepTwoCenterTabbedPane().getUppaalDiagramTabbedPane().removeAll();
     		this.getStepTwoCenterTabbedPane().getUppaalDiagramTabbedPane()
     		.add(workspace.getAWTComponent(),new GBC(0,0).setWeight(1, 1).setFill(GBC.BOTH));    		
     		listenToDiagramPanelEvents(workspace,UppaalWorkspaceList);    	  
     	    repaint();     		    
     	}
     	
//     	if(workspace.getTitle().toString().substring(0, 3).equals(("abs")))
//     	{
//     		
//     		this.getStepThreeCenterTabbedPane().getAbstractUppaalTabbedPane().removeAll();
//     		this.getStepThreeCenterTabbedPane().getAbstractUppaalTabbedPane()
//     		.add(workspace.getAWTComponent(),new GBC(0,0).setWeight(1, 1).setFill(GBC.BOTH));    		    		   	  
//     	    repaint();    
//     	 
//     	}
    }
    public void addTabbedPane(final IWorkspace workspace,int flag )
    {
//       replaceWelcomePanelByTabbedPane(); 
    	//�����ͼ��Ԫ�ص�ʱ�������ж���������ͼ��
    if(flag==21){
//        if(workspace.getTitle().toString().endsWith(".timing.violet.xml")
//        		||workspace.getTitle().toString().substring(2, 4).equals("Ti"))//ʱ��ͼ
//     	{
//     		if(this.TimingWorkspaceList.contains(workspace))
//     		{
//     			return;
//     		}
//     		this.TimingWorkspaceList.clear();//��֤ÿһ���½�����ֻ����1��Typeҳ
//     		this.TimingWorkspaceList.add(workspace);
//     		this.getStepTwoCenterTabbedPane().getUMLDiagramTabbedPane().removeAll();
//     		this.getStepTwoCenterTabbedPane().getUMLDiagramTabbedPane()
//     		.add(workspace.getAWTComponent(),new GBC(0,0).setWeight(1, 1).setFill(GBC.BOTH));
//     		 listenToDiagramPanelEvents(workspace,TimingWorkspaceList);    			  
//     	    repaint();    		    
//     	}
//     	if(workspace.getTitle().toString().endsWith(".seq.violet.xml")
//     			||workspace.getTitle().toString().substring(2, 4).equals("Se"))//�����˳��ͼ
//     			
// 		{
//     	
//     		if(this.StepTwoSequenceWorkspaceList.contains(workspace))
//     		{
//     			return;
//     		}
//     		this.StepTwoSequenceWorkspaceList.clear();
//     		this.StepTwoSequenceWorkspaceList.add(workspace);
//     		this.getStepTwoCenterTabbedPane().getUMLDiagramTabbedPane().removeAll();
//     		this.getStepTwoCenterTabbedPane().getUMLDiagramTabbedPane()
//     		.add(workspace.getAWTComponent(),new GBC(0,0).setWeight(1, 1).setFill(GBC.BOTH));
//     		listenToDiagramPanelEvents(workspace,StepTwoSequenceWorkspaceList);   		
//     	    repaint();    		              
// 		}    
    
     	 /*
     	  * չʾuppaal
     	  */
     	if(workspace.getTitle().toString().contains(".uppaal.violet.xml"))
     	{		
     		if(this.SequenceToUppaalWorkspaceList.contains(workspace))
     		{
     			return;
     		}
     		
//     		int index=this.getModelTransformationPanel().getModelSequenceTreePanel().getUppaaltablemodel().getRowCount();
//     		int index=this.getModelTransformationPanel().getModelSequenceTreePanel().getUppaaltreerootnode().getChildCount();
     		
     		int index=0;
     		
     		DefaultMutableTreeNode rootnode=this.getModelTransformationPanel().getModelSequenceTreePanel().getUppaaltreerootnode();
     		Enumeration<?> en=rootnode.children();
			while(en.hasMoreElements()){
				
				index++;
				
				DefaultMutableTreeNode node;
				node=(DefaultMutableTreeNode) en.nextElement();
				
				index+=node.getChildCount();
				
			}
     		
     		System.err.println(index+" - "+this.getStepTwoCenterTabbedPane().getSequenceToUppaalDiagramButtonTabbedPanelLists().size());

     		if(index==0){
				this.SequenceToUppaalWorkspaceList.clear();
				this.getStepTwoCenterTabbedPane().getSequenceToUppaalDiagramButtonTabbedPanelLists().clear();
			}
			
			this.SequenceToUppaalWorkspaceList.add(index,workspace);
			
//			System.err.println(workspace.getTitle());
//			ButtonTabbedPanel buttontabbedpanel=new ButtonTabbedPanel(this, workspace,index, (workspace.getTitle().toString().split("\\."))[0]);
			ButtonTabbedPanel buttontabbedpanel=new ButtonTabbedPanel(this, workspace,index, (workspace.getTitle().toString().replace(".uppaal.violet.xml", "")));
//			for(ButtonTabbedPanel btp:this.getStepTwoCenterTabbedPane().getSequenceToUppaalDiagramButtonTabbedPanelLists()){
//				if(btp.getTabbedbutton().getText().equals(workspace.getTitle().toString().replace(".uppaal.violet.xml", ""))){
//					btp.setVisible(false);
//					this.getStepTwoCenterTabbedPane().getSequenceToUppaalDiagramButtonTabbedPanelLists().remove(btp);
//					break;
//				}
//			}
			buttontabbedpanel.setVisible(false);
			this.getStepTwoCenterTabbedPane().getSequenceToUppaalDiagramButtonTabbedPanelLists().add(buttontabbedpanel);
			this.getStepTwoCenterTabbedPane().getButtonTabbedPanel().add(buttontabbedpanel);
//     		listenToDiagramPanelEvents(workspace,SequenceToUppaalWorkspaceList);    	  
//     	    repaint();     		    
     	}
    }
    
    if(flag==22){
    	if(workspace.getTitle().toString().contains(".uppaal.violet.xml"))
     	{		
     		if(this.TimingToUppaalWorkspaceList.contains(workspace))
     		{
     			return;
     		}
     		
//     		int index=this.getModelTransformationPanel().getModelTimingTreePanel().getUppaaltablemodel().getRowCount();
     		
     		int index=0;
     		
     		DefaultMutableTreeNode rootnode=this.getModelTransformationPanel().getModelTimingTreePanel().getUppaaltreerootnode();
     		Enumeration<?> en=rootnode.children();
			while(en.hasMoreElements()){
				
				index++;
				
				DefaultMutableTreeNode node;
				node=(DefaultMutableTreeNode) en.nextElement();
				
				index+=node.getChildCount();
				
			}
     		
     		System.err.println(index+" - "+this.getStepTwoCenterTabbedPane().getTimingToUppaalDiagramButtonTabbedPanelLists().size());

     		if(index==0){
				this.TimingToUppaalWorkspaceList.clear();
				this.getStepTwoCenterTabbedPane().getTimingToUppaalDiagramButtonTabbedPanelLists().clear();
			}
			
			this.TimingToUppaalWorkspaceList.add(index,workspace);
			
//			System.err.println(workspace.getTitle());
//			ButtonTabbedPanel buttontabbedpanel=new ButtonTabbedPanel(this, workspace,index, (workspace.getTitle().toString().split("\\."))[0]);
			ButtonTabbedPanel buttontabbedpanel=new ButtonTabbedPanel(this, workspace,index, (workspace.getTitle().toString().replace(".uppaal.violet.xml", "")));
			buttontabbedpanel.setVisible(false);
			this.getStepTwoCenterTabbedPane().getTimingToUppaalDiagramButtonTabbedPanelLists().add(buttontabbedpanel);
			this.getStepTwoCenterTabbedPane().getButtonTabbedPanel().add(buttontabbedpanel);
			
//     		listenToDiagramPanelEvents(workspace,TimingToUppaalWorkspaceList);    	  
//     	    repaint();     		    
     	}
    }
    
    if(flag==3)
    {
    	/*
    	 * ����ʱ��Ǩ���Զ���
    	 */
//    	if(workspace.getTitle().toString().startsWith("abs"))         		
//      	{
//      		if(this.UppaalWorkspaceList.contains(workspace))
//      		{
//      			return;
//      		}
//      		this.UppaalWorkspaceList.clear();//��֤ÿһ���½�����ֻ����1��Typeҳ
//      		this.UppaalWorkspaceList.add(workspace);
//      		this.getStepThreeCenterTabbedPane().getAbstractUppaalTabbedPane().removeAll();
//      		this.getStepThreeCenterTabbedPane().getAbstractUppaalTabbedPane()
//      		.add(workspace.getAWTComponent(),new GBC(0,0).setWeight(1, 1).setFill(GBC.BOTH));
//      		 listenToDiagramPanelEvents(workspace,UppaalWorkspaceList);    			  
//      	    repaint();    		    
//      	}
    	/*
    	 * ȥʱ��Ǩ���Զ���
    	 */
//    	if(workspace.getTitle().toString().startsWith("no_time_abs"))         		
//      	{
//      		if(this.UppaalWorkspaceList.contains(workspace))
//      		{
//      			return;
//      		}
//      		this.UppaalWorkspaceList.clear();//��֤ÿһ���½�����ֻ����1��Typeҳ
//      		this.UppaalWorkspaceList.add(workspace);
//      		this.getStepThreeCenterTabbedPane().getUppaalTabbedPane().removeAll();
//      		this.getStepThreeCenterTabbedPane().getUppaalTabbedPane()
//      		.add(workspace.getAWTComponent(),new GBC(0,0).setWeight(1, 1).setFill(GBC.BOTH));
//      		 listenToDiagramPanelEvents(workspace,UppaalWorkspaceList);    			  
//      	    repaint();    		    
//      	}
    }
     	
    }
    /**
     * Add a listener to perform action when something happens on this diagram
     * 
     * @param diagramPanel
     */
    private void listenToDiagramPanelEvents(final IWorkspace diagramPanel,final List<IWorkspace> workspaceList)
    {
        diagramPanel.addListener(new IWorkspaceListener()
        {
            public void titleChanged(String newTitle)
            {
                int pos = workspaceList.indexOf(diagramPanel);
                
            }

            public void graphCouldBeSaved()
            {
                // nothing to do here
            }

            public void mustOpenfile(IFile file)
            {
                try
                {
                    IGraphFile graphFile = new GraphFile(file);
                    IWorkspace newWorkspace = new Workspace(graphFile);
                    addTabbedPane(newWorkspace);
                }
                catch (IOException e)
                {
                    DialogFactory.getInstance().showErrorDialog(e.getMessage());
                }
            }
        });
    }

    private void replaceWelcomePanelByTabbedPane()
    {
        WelcomePanel welcomePanel = this.getWelcomePanel();
        //JTabbedPane tabbedPane = getTabbedPane();     
        getMainPanel().remove(welcomePanel);        
       // getMainPanel().add(tabbedPane, new GBC(1,1,1,1).setFill(GBC.BOTH).setWeight(1, 1));
        repaint();
    }

    private void replaceTabbedPaneByWelcomePanel()
    {
        this.welcomePanel = null;
        WelcomePanel welcomePanel = this.getWelcomePanel();
        JTabbedPane tabbedPane = getTabbedPane();
        getMainPanel().remove(tabbedPane);
        getMainPanel().add(welcomePanel, BorderLayout.CENTER);
        repaint();
    }

    /**
     * @return the tabbed pane that contains diagram panels
     */
    public JTabbedPane getTabbedPane()
    {
        if (this.tabbedPane == null)
        {
            this.tabbedPane = new JTabbedPane()
            {
                public void paint(Graphics g)
                {
                    Graphics2D g2 = (Graphics2D) g;
                    Paint currentPaint = g2.getPaint();
                    ITheme LAF = themeManager.getTheme();
                    GradientPaint paint = new GradientPaint(getWidth() / 2, -getHeight() / 4, LAF.getWelcomeBackgroundStartColor(),
                            getWidth() / 2, getHeight() + getHeight() / 4, LAF.getWelcomeBackgroundEndColor());
                    g2.setPaint(paint);
                    g2.fillRect(0, 0, getWidth(), getHeight());
                    g2.setPaint(currentPaint);
                    super.paint(g);
                }
            };
            this.tabbedPane.setOpaque(false);
            MouseWheelListener[] mouseWheelListeners = this.tabbedPane.getMouseWheelListeners();
            for (int i = 0; i < mouseWheelListeners.length; i++)
            {
                this.tabbedPane.removeMouseWheelListener(mouseWheelListeners[i]);
            }
        }
        return this.tabbedPane;
    }

    /**
     * Removes a diagram panel from this editor frame
     * 
     * @param diagramPanel
     */
    public void removeDiagramPanel(IWorkspace diagramPanel)
    {
        if (this.UseCaseWorkspaceList.contains(diagramPanel))       		
        {   
        int pos = this.UseCaseWorkspaceList.indexOf(diagramPanel);
//        getStepOneCenterTabbedPane().getUMLTabbedPane(diagramPanel).remove(pos);
//        this.UseCaseWorkspaceList.remove(diagramPanel);
        repaint();
        }
        if (this.TimingWorkspaceList.contains(diagramPanel))       		
        {   
        int pos = this.TimingWorkspaceList.indexOf(diagramPanel);
//        getStepOneCenterTabbedPane().getUMLTabbedPane(diagramPanel).remove(pos);
//        this.TimingWorkspaceList.remove(diagramPanel);
        repaint();
        }
        if (this.SequenceWorkspaceList.contains(diagramPanel))       		
        {   
        int pos = this.SequenceWorkspaceList.indexOf(diagramPanel);
//        getStepOneCenterTabbedPane().getUMLTabbedPane(diagramPanel).remove(pos);
//        this.SequenceWorkspaceList.remove(diagramPanel);
        repaint();
        }
        if (this.StateWorkspaceList.contains(diagramPanel))       		
        {   
        int pos = this.StateWorkspaceList.indexOf(diagramPanel);
//        getStepOneCenterTabbedPane().getUMLTabbedPane(diagramPanel).remove(pos);
//        this.StateWorkspaceList.remove(diagramPanel);
        repaint();
        }
        if (this.UppaalWorkspaceList.contains(diagramPanel))       		
        {   
        int pos = this.UppaalWorkspaceList.indexOf(diagramPanel);
        getStepTwoCenterTabbedPane().getUppaalDiagramTabbedPane().remove(pos);
        this.UppaalWorkspaceList.remove(diagramPanel);
        repaint();
        }
		
		this.getStepOneCenterTabbedPane().getSelectedButtonTabbedPanel().setVisible(false);
//		this.getStepOneCenterTabbedPane().getButtonPanel().remove(this.getStepOneCenterTabbedPane().getSelectedButtonTabbedPanel());
		this.getStepOneCenterTabbedPane().getDiagramPanel().removeAll();
		this.setVisible(false);
		this.getContentPane().repaint();
		this.setVisible(true);
		
    }
    
    /**
     * Looks for an opened diagram from its file path and focus it
     * 
     * @param diagramFilePath diagram file path
     */
    public void setActiveDiagramPanel(IFile aGraphFile)
    {
        if (aGraphFile == null) return;
        for (IWorkspace aDiagramPanel : this.UseCaseWorkspaceList)
        {
            IFile toCompare = aDiagramPanel.getGraphFile();
            boolean isSameFilename = aGraphFile.getFilename().equals(toCompare.getFilename());
            if (isSameFilename)
            {
                int pos = this.UseCaseWorkspaceList.indexOf(aDiagramPanel);
//                JTabbedPane tp = getStepOneCenterTabbedPane().getUsecaseDiagramTabbedPane();
//                tp.setSelectedIndex(pos);
                return;
            }
        }
        for (IWorkspace aDiagramPanel : this.TimingWorkspaceList)
        {
            IFile toCompare = aDiagramPanel.getGraphFile();
            boolean isSameFilename = aGraphFile.getFilename().equals(toCompare.getFilename());
            if (isSameFilename)
            {
                int pos = this.TimingWorkspaceList.indexOf(aDiagramPanel);
//                JTabbedPane tp = getStepOneCenterTabbedPane().getTimingDiagramTabbedPane();
//                tp.setSelectedIndex(pos);
                return;
            }
        }
        for (IWorkspace aDiagramPanel : this.SequenceWorkspaceList)
        {
            IFile toCompare = aDiagramPanel.getGraphFile();
            boolean isSameFilename = aGraphFile.getFilename().equals(toCompare.getFilename());
            if (isSameFilename)
            {
                int pos = this.SequenceWorkspaceList.indexOf(aDiagramPanel);
//                JTabbedPane tp = getStepOneCenterTabbedPane().getSequenceDiagramTabbedPane();
//                tp.setSelectedIndex(pos);
                return;
            }
        }
        for (IWorkspace aDiagramPanel : this.StateWorkspaceList)
        {
            IFile toCompare = aDiagramPanel.getGraphFile();
            boolean isSameFilename = aGraphFile.getFilename().equals(toCompare.getFilename());
            if (isSameFilename)
            {
                int pos = this.StateWorkspaceList.indexOf(aDiagramPanel);
//                JTabbedPane tp = getStepOneCenterTabbedPane().getStateDiagramTabbedPane();
//                tp.setSelectedIndex(pos);
                return;
            }
        }
    }

    /**
     * @return true if at least a diagram is displayed
     */
    public boolean isThereAnyDiagramDisplayed()
    {
        return !this.UseCaseWorkspaceList.isEmpty()
        		||!this.TimingWorkspaceList.isEmpty()
        		||!this.StateWorkspaceList.isEmpty()
        		||!this.SequenceWorkspaceList.isEmpty()
        		||!this.UppaalWorkspaceList.isEmpty();
    }

    public List<IWorkspace> getUseCaseWorkspaceList()
    {
        return UseCaseWorkspaceList;
    }
    public List<IWorkspace> getTimingWorkspaceList()
    {
        return TimingWorkspaceList;
    }
    public List<IWorkspace> getSequenceWorkspaceList()
    {
        return SequenceWorkspaceList;
    }
    public List<IWorkspace> getStateWorkspaceList()
    {
        return StateWorkspaceList;
    }
    /**
     * @return selected diagram file path (or null if not one is selected; that should never happen)
     */
    public IWorkspace getActiveWorkspace()
    {
    	StepOneCenterTabbedPane Onetp = getStepOneCenterTabbedPane();
    	StepTwoCenterTabbedPane Twotp=getStepTwoCenterTabbedPane();
        int pos1=Onetp.getSelectedIndex(); 
        int pos2=Twotp.getSelectedIndex();
//		System.out.println(pos1+"+++++++++++++++++"+pos2);
//		System.out.println(UppaalWorkspaceList.size());
		System.out.println("se "+SequenceWorkspaceList.size()+" + ti "+TimingWorkspaceList.size()+" + st "+StateWorkspaceList.size()+" + uc "+UseCaseWorkspaceList.size());
		if (pos1 == 0 && SequenceWorkspaceList.size() > 0) {
			return this.SequenceWorkspaceList.get(this.getStepOneCenterTabbedPane().getSelectedButtonTabbedPanel().getButtontabbedpanelindex());
		}
		if (pos1 == 1 && TimingWorkspaceList.size() > 0) {
			return this.TimingWorkspaceList.get(this.getStepOneCenterTabbedPane().getSelectedButtonTabbedPanel().getButtontabbedpanelindex());
		}
		if (pos1 == 2 && StateWorkspaceList.size() > 0) {
			return this.StateWorkspaceList.get(this.getStepOneCenterTabbedPane().getSelectedButtonTabbedPanel().getButtontabbedpanelindex());
		}
		if (pos1 == 3 && UseCaseWorkspaceList.size() > 0) {
			return this.UseCaseWorkspaceList.get(this.getStepOneCenterTabbedPane().getSelectedButtonTabbedPanel().getButtontabbedpanelindex());
		}
		if (pos2 == 1 && UppaalWorkspaceList.size() > 0) {
//			System.out.println("----------------");
			return this.UppaalWorkspaceList.get(0);
		} else {// ˵��û��workspace UMLͼ����Ҫ����
			return null;
		}
    }
   
   public WelcomePanel getWelcomePanel()
    {
        if (this.welcomePanel == null)
        {
            this.welcomePanel = new WelcomePanel(this.getMenuFactory().getFileMenu(this));
        }
        return this.welcomePanel;
    }
   public HomeAllTabbedPanel getHomeAllTabbedPanel()
   {
   	if(this.homepanel==null)
   	{
   		this.homepanel=new HomeAllTabbedPanel(this);
   	}
   	return this.homepanel;
   }
	public StepButtonPanel getStepButton() {
    
    	if(this.stepButton==null)
    	{
    		this.stepButton=new StepButtonPanel(this);
    	}
    	return this.stepButton;
    }
//    public ConsolePart getConsolePart()    {
//    	if(this.consolePart==null)
//    	{
//    		this.consolePart=new ConsolePart(this);
//    	}
//    	return this.consolePart;
//    }
	public ConsolePartPanel getConsolePartPanel() {
		if (this.consolePartPanel == null) {
			this.consolePartPanel = new ConsolePartPanel(this);
		}
		return this.consolePartPanel;
	}
	
	public AttributePartOnePanel getAttributePartOnePanel() {
		if (this.attributePartOnePanel == null) {
			this.attributePartOnePanel = new AttributePartOnePanel(this);
		}
		return this.attributePartOnePanel;
	}
	
	public AttributePartTwoPanel getAttributePartTwoPanel() {
		if (this.attributePartTwoPanel == null) {
			this.attributePartTwoPanel = new AttributePartTwoPanel(this);
		}
		return this.attributePartTwoPanel;
	}
	
	public ValidationResultPanel getValidationResultPanel(){
		if (this.validationResultPanel == null) {
			this.validationResultPanel = new ValidationResultPanel(this);
		}
		return this.validationResultPanel;
	}
	
	public AbstractTestCaseResultPanel getAbstractTestCaseResultPanel(){
		if (this.abstractTestCaseResultPanel == null) {
			this.abstractTestCaseResultPanel = new AbstractTestCaseResultPanel(this);
		}
		return this.abstractTestCaseResultPanel;
	}
	
	public TestCaseInstantiationResultPanel getTestCaseInstantiationResultPanel(){
		if (this.testCaseInstantiationResultPanel == null) {
			this.testCaseInstantiationResultPanel = new TestCaseInstantiationResultPanel(this);
		}
		return this.testCaseInstantiationResultPanel;
	}
	
	public TestCaseConfirmResultPanel getTestCaseConfirmResultPanel(){
		if (this.testCaseConfirmResultPanel == null) {
			this.testCaseConfirmResultPanel = new TestCaseConfirmResultPanel(this);
		}
		return this.testCaseConfirmResultPanel;
	}

	// public JPanel getConsolePart(){
	// return this.consolepartpanel;
	// }
	public ProjectTree getProjectTree() {
		if (this.projectTree == null) {
			this.projectTree = new ProjectTree(this.getMenuFactory().getFileMenu(this), this);
		}
		return this.projectTree;
	}

	public ModelTransformationPanel getModelTransformationPanel() {
		if (this.modelTransformationPanel == null) {
			this.modelTransformationPanel = new ModelTransformationPanel(this);

		}
		return this.modelTransformationPanel;
	}

	public ModelExistValidationPanel getModelExistValidationPanel() {
		if (this.modelExistValidationPanel == null) {
			this.modelExistValidationPanel = new ModelExistValidationPanel(this);

		}
		return this.modelExistValidationPanel;
	}

	public TestCaseGenerationPanel getTestCaseGenerationPanel() {
		if (this.abstractTestCaseGenerationPanel == null) {
			this.abstractTestCaseGenerationPanel = new TestCaseGenerationPanel(this);

		}
		return this.abstractTestCaseGenerationPanel;
	}
	
	public TestCaseInstantiationPanel getTestCaseInstantiationPanel() {
		if (this.testCaseInstantiationPanel == null) {
			this.testCaseInstantiationPanel = new TestCaseInstantiationPanel(this);

		}
		return this.testCaseInstantiationPanel;
	}

	public TestCaseConfirmationPanel getTestCaseConfirmationPanel() {
		if (this.testCaseConfirmationPanel == null) {
			this.testCaseConfirmationPanel = new TestCaseConfirmationPanel(this);

		}
		return this.testCaseConfirmationPanel;
	}

	private TopPanel getTopPanel() {
		if (this.topPanel == null) {
			this.topPanel = new TopPanel(this);
		}
		return this.topPanel;
	}

	private LeftPanel getLeftPanel() {
		if (this.leftPanel == null) {
			this.leftPanel = new LeftPanel(this);
		}
		return this.leftPanel;
	}

	public OneTouchExpandablePanel getOneTouchExpandablePanel() {
		if (this.oneTouchExpandablePanel == null) {
			this.oneTouchExpandablePanel = new OneTouchExpandablePanel(this);
		}
		return this.oneTouchExpandablePanel;
	}

	public BottomPanel getBottomPanel() {
		if (this.bottomPanel == null) {
			this.bottomPanel = new BottomPanel(this);
		}
		return this.bottomPanel;
	}
    public JPanel getMainPanel() {//����岼��
        if (this.mainPanel == null) {
        	GridBagLayout layout=new GridBagLayout();
            this.mainPanel = new JPanel(layout);
            this.mainPanel.setBorder(new EmptyBorder(0, 0, 0, 0));                                                 
			 
//			this.getOpreationPart().setBorder(new EmptyBorder(10, 0, 0, 0));
//			this.getOpreationPart().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(142, 155, 188)));
//			this.getOpreationPart().setBackground(new Color(53, 73, 105));
			this.getCenterPanel().setLayout(new GridLayout(1, 1));
			this.getCenterPanel().setBackground(new Color(53, 73, 105));
			this.getCenterPanel().add(this.getHomeAllTabbedPanel());// Ĭ�������ҳ
			this.setStepindex(0);
			this.getCenterTabPanel().setLayout(new GridLayout(1, 1));
			this.getCenterTabPanel().setBackground(new Color(53, 73, 105));
			
//			this.getCenterTabPanel().add(this.getHomePanel());// Ĭ�������ҳ
			this.getConsolePartPanel().setVisible(false);
			this.getOneTouchExpandablePanel().setVisible(false);
//			this.getConsolePartPanel().setBorder(new EmptyBorder(0, 0, 0, 10));
			this.getConsolePartPanel().setBackground(new Color(53, 73, 105));
			this.getStepJLabel().setBackground(new Color(235, 235, 235));
			// JLabel initlabel=new JLabel("��Ŀ��ʾ��");
			// initlabel.setFont(new Font("����", Font.BOLD, 20));
			// initlabel.setForeground(Color.white);
			// this.getStepJLabel().add(initlabel,JLabel.CENTER);

			// layout.setConstraints(this.getStepButton(),new GBC(0, 0, 1,
			// 3).setFill(GBC.BOTH).setWeight(0, 1));
			// layout.setConstraints(this.getStepJLabel(), new
			// GBC(1,0,2,1).setFill(GBC.BOTH).setWeight(1, 0));
			// layout.setConstraints(this.getConsolePart(), new
			// GBC(1,2,2,1).setFill(GBC.BOTH).setWeight(1, 0));
			// layout.setConstraints(this.getOpreationPart(), new
			// GBC(2,1,1,1).setFill(GBC.BOTH).setWeight(0, 1));
			// layout.setConstraints(this.getCenterTabPanel(), new
			// GBC(1,1,1,1).setFill(GBC.BOTH).setWeight(1, 1));
			
			// this.getCenterTabPanel().setLayout(new GridLayout(1, 1));
			// JSplitPane js4=new
			// JSplitPane(JSplitPane.VERTICAL_SPLIT,this.getCenterTabPanel(),this.getConsolePart());
			// JSplitPane js3=new
			// JSplitPane(JSplitPane.HORIZONTAL_SPLIT,this.getOpreationPart(),js4);
			// JSplitPane js2=new
			// JSplitPane(JSplitPane.VERTICAL_SPLIT,this.getStepJLabel(),js3);
			// JSplitPane js1=new
			// JSplitPane(JSplitPane.VERTICAL_SPLIT,this.getStepButton(),js2);
			// this.mainPanel=js1;

			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int screenWidth = (int) screenSize.getWidth();
			int screenHeight = (int) screenSize.getHeight();

			// this.getCenterTabPanel().setMinimumSize(new
			// Dimension(screenWidth*5/6, screenHeight*3/5));

//			js4 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, this.getCenterTabPanel(), this.getConsolePartPanel());
//			js3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.getOpreationPart(), js4);
//			js2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, js3, this.getBottomPanel());
//			js1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, this.getLeftPanel(), js2);
			
			js3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, this.getCenterTabPanel(), this.getConsolePartPanel());
			js2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, js3, this.getAttributePart());
			js1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.getOpreationPart(), js2);
			
//			js1.setResizeWeight(0.2);
//			js2.setResizeWeight(0.9);
//			js3.setResizeWeight(0.9);
			js1.setResizeWeight(0);
			js2.setResizeWeight(1);
			js3.setResizeWeight(1);
			
//			js3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.getLeftPanel(), js4);
//			js2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, js3, this.getBottomPanel());
//			js1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, this.getTopPanel(), js2);
//			this.mainPanel = js1;
			
			this.mainPanel.add(this.getTopPanel());
			this.mainPanel.add(this.getLeftPanel());
			this.mainPanel.add(this.getBottomPanel());
			this.mainPanel.add(this.getCenterPanel());
			this.mainPanel.add(this.getOneTouchExpandablePanel());
			
			layout.setConstraints(this.getTopPanel(), new GBC(0, 0, 3, 1).setFill(GBC.BOTH).setWeight(1, 0));
			layout.setConstraints(this.getLeftPanel(), new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(0, 1));
			layout.setConstraints(this.getBottomPanel(), new GBC(0, 2, 3, 1).setFill(GBC.BOTH).setWeight(1, 0));
			layout.setConstraints(this.getCenterPanel(), new GBC(1, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
			layout.setConstraints(this.getOneTouchExpandablePanel(), new GBC(2, 1, 1, 1).setFill(GBC.BOTH).setWeight(0, 1));
			
			
			this.getLeftPanel().setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(28,31,35)));
			this.getCenterPanel().setBorder(BorderFactory.createMatteBorder(6,6,6,6, new Color(41,57,85)));
//			js1.setBorder(BorderFactory.createMatteBorder(6,6,6,6, new Color(41,57,85)));

			// js1.setOneTouchExpandable(true);
			// js1.setContinuousLayout(true);
			// js2.setOneTouchExpandable(true);
			// js2.setContinuousLayout(true);
//			js3.setContinuousLayout(true);
//			js4.setOneTouchExpandable(true);
			js1.setContinuousLayout(true);
			js2.setContinuousLayout(true);
			js3.setContinuousLayout(true);
			js1.setDividerSize(6);
			js2.setDividerSize(6);
			js3.setDividerSize(6);
//			js1.setDividerLocation(screenWidth/8);
//			js2.setDividerLocation(screenWidth*7/8);
//			js3.setDividerLocation(screenHeight*4/5);
			js1.setDividerLocation(screenWidth/8);
			js2.setDividerLocation((int)js2.getSize().getWidth()-screenWidth/6);
			js3.setDividerLocation((int)js3.getSize().getHeight()-screenHeight/5);

//			js1.setDividerLocation(30);
//			js2.setDividerLocation(screenHeight - 30);
//			this.getBottomPanel().setPreferredSize(new Dimension(screenWidth-100, 10));
//			this.getBottomPanel().setMaximumSize(new Dimension(screenWidth-100, 10));
//			js3.setDividerLocation(200);
//			js4.setDividerLocation(100);

//			js1.disable();
//			js2.disable();

//			js1.setDividerSize(0);
//			js1.setBackground(Color.ORANGE);
//			js2.setDividerSize(6);
//			js2.setBackground(Color.BLUE);
//			js3.setBackground(Color.GREEN);
//			js4.setBackground(Color.PINK);

			// this.getTopPanel().setBorder(BorderFactory.createEmptyBorder(0,
			// 0, 10, 0));
			// this.getTopPanel().setBorder(BorderFactory.createLineBorder(Color.BLUE,
			// 10, false));
			// js4.setDividerLocation(800);
			// js4.setDividerSize(8);
			// js4.setOneTouchExpandable(true);
			// js4.setContinuousLayout(true);
			
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					while (true) {
						try {
							EditSelectedBehavior.Reflock.take();
                            
							if(getStepOneCenterTabbedPane().getSelectedIndex()==0){
								int index=getStepOneCenterTabbedPane().getSelectedButtonTabbedPanel().getButtontabbedpanelindex();
								RefNode refNode = (RefNode) (getSequenceWorkspaceList().get(index).getEditorPart().getSelectionHandler().getLastSelectedNode());
					            String refName = refNode.getText().getText();
					            
//					            System.err.println(refName+" - - "+index);
					            
					            for(ButtonTabbedPanel btpanel:getStepOneCenterTabbedPane().getSequenceDiagramButtonTabbedPanelLists()){
//					            	System.out.println(btpanel.getTabbedbuttontext());
					            	if(btpanel.getTabbedbuttontext().equals(refName)){
					            		btpanel.getTabbedbutton().doClick();
					            		break;
					            	}
					            }
							}
				            
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}).start();

        }
        return this.mainPanel;
    }
    
    public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.mainPanel.setVisible(false);
		this.mainPanel.getRootPane().repaint();
		this.mainPanel.setVisible(true);
	}

    public StepOneCenterTabbedPane getStepOneCenterTabbedPane()
    {
    if (this.stepOneCenterTabbedPane== null)
    {
       stepOneCenterTabbedPane=new StepOneCenterTabbedPane(this);
    }
    return this.stepOneCenterTabbedPane;
    	
    }
    public StepTwoCenterTabbedPane getStepTwoCenterTabbedPane()
    {
    if (this.stepTwoCenterTabbedPane== null)
    {
       stepTwoCenterTabbedPane=new StepTwoCenterTabbedPane(this);
    }
    return this.stepTwoCenterTabbedPane;
    	
    }   
    //��3��tab
    public StepThreeCenterTabbedPane getStepThreeCenterTabbedPane()
    {
    if (this.stepThreeCenterTabbedPane== null)
    {
       stepThreeCenterTabbedPane=new StepThreeCenterTabbedPane(this);
    }
    return this.stepThreeCenterTabbedPane;
    	
    }
    
    public StepFourCenterTabbedPane getStepFourCenterTabbedPane()
    {
    if (this.stepFourCenterTabbedPane== null)
    {
    	stepFourCenterTabbedPane=new StepFourCenterTabbedPane(this);
    }
    return this.stepFourCenterTabbedPane;
    	
    }
    
	// ��5��tab
	public StepFiveCenterTabbedPane getStepFiveCenterTabbedPane() {
		if (this.stepFiveCenterTabbedPane == null) {
			stepFiveCenterTabbedPane = new StepFiveCenterTabbedPane(this);
		}
		return this.stepFiveCenterTabbedPane;

	}

	// ��6��tab
	public StepSixCenterTabbedPane getStepSixCenterTabbedPane() {
		if (this.stepSixCenterTabbedPane == null) {
			stepSixCenterTabbedPane = new StepSixCenterTabbedPane(this);
		}
		return this.stepSixCenterTabbedPane;

	}
    
	public JPanel getOpreationPart() {
		// TODO Auto-generated method stub
		return this.opreationpanel;
	}
	public JPanel getAttributePart() {
		// TODO Auto-generated method stub
		return this.attributePanel;
	}
	public JPanel getCenterTabPanel(){
		// TODO Auto-generated method stub
		return this.centerTabPanel;
	}
	/**
     * @return the menu factory instance
     */
    public MenuFactory getMenuFactory()
    {
        if (this.menuFactory == null)
        {
            menuFactory = new MenuFactory();
        }
        return this.menuFactory;
    }

    public JPanel getStepJLabel() {   	
		return stepJLabel;
	}

	public JPanel getCenterPanel() {
		return centerPanel;
	}

	public void setCenterPanel(JPanel centerPanel) {
		this.centerPanel = centerPanel;
	}

	public void setStepJLabel(JPanel stepJLabel) {
		this.stepJLabel = stepJLabel;
	}

	public JSplitPane getJs1() {
		return js1;
	}

	public void setJs1(JSplitPane js1) {
		this.js1 = js1;
	}

	public JSplitPane getJs2() {
		return js2;
	}

	public void setJs2(JSplitPane js2) {
		this.js2 = js2;
	}

	public JSplitPane getJs3() {
		return js3;
	}

	public void setJs3(JSplitPane js3) {
		this.js3 = js3;
	}

	public JSplitPane getJs4() {
		return js4;
	}

	public void setJs4(JSplitPane js4) {
		this.js4 = js4;
	}
	
	public int getStepindex() {
		return stepindex;
	}

	public void setStepindex(int stepindex) {
		this.stepindex = stepindex;
	}
	
    public String getDirectory()
    {
    	return this.getSequenceWorkspaceList().get(0).getGraphFile().getDirectory();
    }
	/**
     * Tabbed pane instance
     */
    private JTabbedPane tabbedPane;
    
	/**
     * Panel added is not diagram is opened
     */
    private WelcomePanel welcomePanel;
    private HomeAllTabbedPanel homepanel;
    private JPanel stepJLabel=new JPanel();
    
    private ProjectTree projectTree;
    private ModelTransformationPanel modelTransformationPanel;
    private ModelExistValidationPanel modelExistValidationPanel;
    private TestCaseGenerationPanel abstractTestCaseGenerationPanel;
    private TestCaseInstantiationPanel testCaseInstantiationPanel;
    private TestCaseConfirmationPanel testCaseConfirmationPanel;
    
    private StepOneCenterTabbedPane stepOneCenterTabbedPane;
    private StepTwoCenterTabbedPane stepTwoCenterTabbedPane;
    private StepThreeCenterTabbedPane stepThreeCenterTabbedPane;
    private StepFourCenterTabbedPane stepFourCenterTabbedPane;
    private StepFiveCenterTabbedPane stepFiveCenterTabbedPane;
    private StepSixCenterTabbedPane stepSixCenterTabbedPane;
    
	private ConsolePartPanel consolePartPanel;
	private AttributePartOnePanel attributePartOnePanel;
	private AttributePartTwoPanel attributePartTwoPanel;
	private ValidationResultPanel validationResultPanel;
	private AbstractTestCaseResultPanel abstractTestCaseResultPanel; 
	private TestCaseInstantiationResultPanel testCaseInstantiationResultPanel;
	private TestCaseConfirmResultPanel testCaseConfirmResultPanel;
//	private ConsolePart consolePart;
    
    private StepButtonPanel stepButton;
	private JPanel centerPanel=new JPanel();;
    private JPanel opreationpanel=new JPanel();
    private JPanel attributePanel=new JPanel();
    private JPanel centerTabPanel=new JPanel();
//	private JPanel consolepartpanel = new JPanel();
    private String directory;
	public int stepindex=0;
	
	private TopPanel topPanel;
	private LeftPanel leftPanel;
	private OneTouchExpandablePanel oneTouchExpandablePanel;
	private BottomPanel bottomPanel;
	private JSplitPane js1;
	private JSplitPane js2;
	private JSplitPane js3;
	private JSplitPane js4;
	private JSplitPane js5;

    /**
     * Main panel
     */
    private JPanel mainPanel;

    /**
     * Menu factory instance
     */
    private MenuFactory menuFactory;

    /**
     * GUI Theme manager
     */
    @InjectedBean
    private ThemeManager themeManager;

    /**
     * Needed to display dialog boxes
     */
    @InjectedBean
    private DialogFactory dialogFactory;

    /**
     * Needed to open files
     */
    @InjectedBean
    private IFileChooserService fileChooserService;
    
    @ResourceBundleBean(key="app.name")
    private String applicationName;
    
    @ResourceBundleBean(key="app.icon")
    private Image applicationIcon;

    /**
     * All disgram workspaces
     */
    private List<IWorkspace> UseCaseWorkspaceList = new ArrayList<IWorkspace>(); //����ͼ    
    private List<IWorkspace> SequenceWorkspaceList=new ArrayList<IWorkspace>();//˳��ͼ
    private List<IWorkspace> TimingWorkspaceList=new ArrayList<IWorkspace>();//ʱ��ͼ
    private List<IWorkspace> StateWorkspaceList=new ArrayList<IWorkspace>();//״̬ͼ
    private List<IWorkspace> UppaalWorkspaceList=new ArrayList<IWorkspace>();//ʱ���Զ���
    
    private List<IWorkspace> SequenceToUppaalWorkspaceList=new ArrayList<IWorkspace>();//ʱ���Զ���
    private List<IWorkspace> TimingToUppaalWorkspaceList=new ArrayList<IWorkspace>();//ʱ���Զ���
    
    private List<IWorkspace> StepTwoSequenceWorkspaceList=new ArrayList<IWorkspace>();
    private List<IWorkspace> StepTwoTimingWorkspaceList=new ArrayList<IWorkspace>();
    
    // workaround for bug #4646747 in J2SE SDK 1.4.0
    private static java.util.HashMap<Class<?>, BeanInfo> beanInfos;
    static
    {
        beanInfos = new java.util.HashMap<Class<?>, BeanInfo>();
        Class<?>[] cls = new Class<?>[]
        {
                Point2D.Double.class,
                BentStyle.class,
                ArrowHead.class,
                LineStyle.class,
                IGraph.class,
                AbstractNode.class,
        };
        for (int i = 0; i < cls.length; i++)
        { 
            try
            {
                beanInfos.put(cls[i], java.beans.Introspector.getBeanInfo(cls[i]));
            }
            catch (java.beans.IntrospectionException ex)
            {
            }
        }
    }
}

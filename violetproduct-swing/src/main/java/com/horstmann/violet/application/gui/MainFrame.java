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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Toolkit;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;
import java.beans.BeanInfo;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.horstmann.violet.application.gui.common.ChartUtils;
import com.horstmann.violet.application.gui.common.FileUtil;
import com.horstmann.violet.application.gui.stepFive.StepFiveCenterPanel;
import com.horstmann.violet.application.gui.stepFour.StepFourCenterPanel;
import com.horstmann.violet.application.gui.stepOne.StepOneCenterPanel;
import com.horstmann.violet.application.gui.stepThree.StepThreeCenterPanel;
import com.horstmann.violet.application.gui.stepTwo.StepTwoCenterPanel;
import com.horstmann.violet.application.gui.stepZero.StepZeroCenterPanel;
import com.horstmann.violet.application.help.AboutDialog;
import com.horstmann.violet.application.menu.MenuFactory;
import com.horstmann.violet.framework.dialog.DialogFactory;
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
import com.horstmann.violet.workspace.IWorkspace;

/**
 * This desktop frame contains panes that show graphs.
 * 
 * @author Alexandre de Pellegrin
 */
@ResourceBundleBean(resourceReference = AboutDialog.class)
public class MainFrame extends JFrame {
	/**
	 * Constructs a blank frame with a desktop pane but no graph windows.
	 * 
	 */
	public MainFrame() {
		BeanInjector.getInjector().inject(this);
		ResourceBundleInjector.getInjector().inject(this);
		// setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.dialogFactory.setDialogOwner(this);
		decorateFrame();
		// setInitialSize();
//		 createMenuBar();

		// 初始化文件列表
		FileUtil.FileCheck();
		
		//装载chart插件
		ChartUtils cu=new ChartUtils();

		getContentPane().add(this.getMainPanel());

	}

	/**
	 * Sets initial size on startup
	 */
	private void setInitialSize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth() / 2;
		int screenHeight = (int) screenSize.getHeight() / 2;
		// setBounds(screenWidth / 16, screenHeight / 16, screenWidth * 7 / 8,
		// screenHeight * 7 / 8);
		setBounds(0, 0, screenWidth, screenHeight);
		setExtendedState(JFrame.NORMAL);
		// For screenshots only -> setBounds(50, 50, 850, 650);
	}

	/**
	 * Decorates the frame (title, icon...)
	 */
	private void decorateFrame() {
		setTitle(this.applicationName);
		setIconImage(this.applicationIcon);
	}

	/**
	 * Creates menu bar
	 */
	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(this.themeManager.getTheme().getMenubarFont());
		MenuFactory menuFactory = getMenuFactory();
		menuBar.add(menuFactory.getFileMenu(this));
		menuBar.add(menuFactory.getEditMenu(this));
		menuBar.add(menuFactory.getViewMenu(this));
		menuBar.add(menuFactory.getHelpMenu(this));
		setJMenuBar(menuBar);

	}

	/**
	 * Removes a diagram panel from this editor frame
	 * 
	 * @param diagramPanel
	 */
	public void removeDiagramPanel(IWorkspace diagramPanel) {

	}

	/**
	 * @return selected diagram file path (or null if not one is selected; that
	 *         should never happen)
	 */
	public IWorkspace getActiveWorkspace() {
		
		JTabbedPane workTabbedPanel=this.getStepTwoCenterPanel().getWorkTabbedPane();
		
		return this.getMarkovWorkspaceList().get(workTabbedPanel.getSelectedIndex());
	}

	public StepButtonPanel getStepButton() {

		if (this.stepButton == null) {
			this.stepButton = new StepButtonPanel(this);
		}
		return this.stepButton;
	}

	private TopPanel getTopPanel() {
		if (this.topPanel == null) {
			this.topPanel = new TopPanel(this);
		}
		return this.topPanel;
	}

	public BottomPanel getBottomPanel() {
		if (this.bottomPanel == null) {
			this.bottomPanel = new BottomPanel(this);
		}
		return this.bottomPanel;
	}

	public JPanel getMainPanel() {// 主面板布局
		if (this.mainPanel == null) {
			GridBagLayout layout = new GridBagLayout();
			this.mainPanel = new JPanel(layout);
			this.mainPanel.setBorder(new EmptyBorder(0, 0, 0, 0));

			this.getCenterPanel().setLayout(new GridLayout(1, 1));
			this.getCenterPanel().setBackground(new Color(53, 73, 105));
			this.getCenterPanel().add(this.getStepZeroCenterPanel());// 默认添加首页
			this.setStepindex(0);

			this.mainPanel.add(this.getTopPanel());
			this.mainPanel.add(this.getCenterPanel());
			this.mainPanel.add(this.getBottomPanel());

			layout.setConstraints(this.getTopPanel(), new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
			layout.setConstraints(this.getCenterPanel(), new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
			layout.setConstraints(this.getBottomPanel(), new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));

		}
		return this.mainPanel;
	}

	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.mainPanel.setVisible(false);
		this.mainPanel.getRootPane().repaint();
		this.mainPanel.setVisible(true);
	}
	
	public void ChangeRepaint(JPanel jPanel) {
		// TODO Auto-generated method stub
		jPanel.setVisible(false);
		jPanel.getRootPane().repaint();
		jPanel.setVisible(true);
	}

	public StepZeroCenterPanel getStepZeroCenterPanel() {
		if (this.stepZeroCenterPanel == null) {
			stepZeroCenterPanel = new StepZeroCenterPanel(this);
		}
		return stepZeroCenterPanel;
	}
	
	public StepOneCenterPanel getStepOneCenterPanel() {
		if (this.stepOneCenterPanel == null) {
			stepOneCenterPanel = new StepOneCenterPanel(this);
		}
		return stepOneCenterPanel;
	}

	public StepTwoCenterPanel getStepTwoCenterPanel() {
		if (this.stepTwoCenterPanel == null) {
			stepTwoCenterPanel = new StepTwoCenterPanel(this);
		}
		return stepTwoCenterPanel;
	}
	
	public StepThreeCenterPanel getStepThreeCenterPanel() {
		if (this.stepThreeCenterPanel == null) {
			stepThreeCenterPanel = new StepThreeCenterPanel(this);
		}
		return stepThreeCenterPanel;
	}
	
	public StepFourCenterPanel getStepFourCenterPanel() {
		if (this.stepFourCenterPanel == null) {
			stepFourCenterPanel = new StepFourCenterPanel(this);
		}
		return stepFourCenterPanel;
	}
	
	public StepFiveCenterPanel getStepFiveCenterPanel() {
		if (this.stepFiveCenterPanel == null) {
			stepFiveCenterPanel = new StepFiveCenterPanel(this);
		}
		return stepFiveCenterPanel;
	}

	/**
	 * @return the menu factory instance
	 */
	public MenuFactory getMenuFactory() {
		if (this.menuFactory == null) {
			menuFactory = new MenuFactory();
		}
		return this.menuFactory;
	}

	public JPanel getCenterPanel() {
		return centerPanel;
	}

	public void setCenterPanel(JPanel centerPanel) {
		this.centerPanel = centerPanel;
	}

	public int getStepindex() {
		return stepindex;
	}

	public void setStepindex(int stepindex) {
		this.stepindex = stepindex;
	}

	public List<IWorkspace> getMarkovWorkspaceList() {
		return MarkovWorkspaceList;
	}


	private StepZeroCenterPanel stepZeroCenterPanel;
	private StepOneCenterPanel stepOneCenterPanel;
	private StepTwoCenterPanel stepTwoCenterPanel;
	private StepThreeCenterPanel stepThreeCenterPanel;
	private StepFourCenterPanel stepFourCenterPanel;
	private StepFiveCenterPanel stepFiveCenterPanel;

	private StepButtonPanel stepButton;
	private JPanel centerPanel = new JPanel();;
	public int stepindex = 0;

	private TopPanel topPanel;
	private BottomPanel bottomPanel;

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

	@ResourceBundleBean(key = "app.name")
	private String applicationName;

	@ResourceBundleBean(key = "app.icon")
	private Image applicationIcon;

	/**
	 * All disgram workspaces
	 */

	private List<IWorkspace> MarkovWorkspaceList = new ArrayList<IWorkspace>();// 剖面图

	// workaround for bug #4646747 in J2SE SDK 1.4.0
	private static java.util.HashMap<Class<?>, BeanInfo> beanInfos;
	static {
		beanInfos = new java.util.HashMap<Class<?>, BeanInfo>();
		Class<?>[] cls = new Class<?>[] { Point2D.Double.class, BentStyle.class, ArrowHead.class, LineStyle.class,
				IGraph.class, AbstractNode.class, };
		for (int i = 0; i < cls.length; i++) {
			try {
				beanInfos.put(cls[i], java.beans.Introspector.getBeanInfo(cls[i]));
			} catch (java.beans.IntrospectionException ex) {
			}
		}
	}
}

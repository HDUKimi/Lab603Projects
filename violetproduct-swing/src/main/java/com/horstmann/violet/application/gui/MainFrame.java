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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.horstmann.violet.application.gui.opreationTreePane.TestCaseGenerationPanel;
import com.horstmann.violet.application.gui.homeTabbedPanel.HomeAllTabbedPanel;
import com.horstmann.violet.application.gui.opreationTreePane.ModelExistValidationPanel;
import com.horstmann.violet.application.gui.opreationTreePane.ModelTransformationPanel;
import com.horstmann.violet.application.gui.opreationTreePane.ProjectTree;
import com.horstmann.violet.application.gui.opreationTreePane.TestCaseConfirmationPanel;
import com.horstmann.violet.application.gui.opreationTreePane.TestCaseInstantiationPanel;
import com.horstmann.violet.application.gui.stepOne.StepOneCenterPanel;
import com.horstmann.violet.application.gui.stepZero.StepZeroCenterPanel;
import com.horstmann.violet.application.gui.util.tanchao.StartFileCheck;
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
		// setUIManeger();
		// createMenuBar();

		// 初始化文件列表
		StartFileCheck sfc = new StartFileCheck();
		sfc.FileCheck();

		getContentPane().add(this.getMainPanel());

	}

	private void setUIManeger() {
		// TODO Auto-generated method stub

//		UIManager.put("TabbedPane.selected", new Color(64, 66, 68));
//		UIManager.put("TabbedPane.unselected", new Color(64, 66, 68));
//		UIManager.put("TabbedPane.selectedForeground", Color.WHITE);

		UIManager.put("Tree.collapsedIcon", new ImageIcon(this.getClass().getResource("ImagePart/collapsed.png")));
		UIManager.put("Tree.expandedIcon", new ImageIcon(this.getClass().getResource("ImagePart/expanded.png")));

		UIManager.put("SplitPane.background", new Color(41, 57, 85));

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
	 * Adds a tabbed pane (only if not already added)
	 * 
	 * @param c
	 *            the component to display in the internal frame
	 */


	/**
	 * Add a listener to perform action when something happens on this diagram
	 * 
	 * @param diagramPanel
	 */

	private void replaceWelcomePanelByTabbedPane() {
		WelcomePanel welcomePanel = this.getWelcomePanel();
		// JTabbedPane tabbedPane = getTabbedPane();
		getMainPanel().remove(welcomePanel);
		// getMainPanel().add(tabbedPane, new
		// GBC(1,1,1,1).setFill(GBC.BOTH).setWeight(1, 1));
		repaint();
	}

	private void replaceTabbedPaneByWelcomePanel() {
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
	public JTabbedPane getTabbedPane() {
		if (this.tabbedPane == null) {
			this.tabbedPane = new JTabbedPane() {
				public void paint(Graphics g) {
					Graphics2D g2 = (Graphics2D) g;
					Paint currentPaint = g2.getPaint();
					ITheme LAF = themeManager.getTheme();
					GradientPaint paint = new GradientPaint(getWidth() / 2, -getHeight() / 4,
							LAF.getWelcomeBackgroundStartColor(), getWidth() / 2, getHeight() + getHeight() / 4,
							LAF.getWelcomeBackgroundEndColor());
					g2.setPaint(paint);
					g2.fillRect(0, 0, getWidth(), getHeight());
					g2.setPaint(currentPaint);
					super.paint(g);
				}
			};
			this.tabbedPane.setOpaque(false);
			MouseWheelListener[] mouseWheelListeners = this.tabbedPane.getMouseWheelListeners();
			for (int i = 0; i < mouseWheelListeners.length; i++) {
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
	public void removeDiagramPanel(IWorkspace diagramPanel) {

	}

	/**
	 * Looks for an opened diagram from its file path and focus it
	 * 
	 * @param diagramFilePath
	 *            diagram file path
	 */


	/**
	 * @return selected diagram file path (or null if not one is selected; that
	 *         should never happen)
	 */
	public IWorkspace getActiveWorkspace() {
			return null;
	}
	

	public WelcomePanel getWelcomePanel() {
		if (this.welcomePanel == null) {
			this.welcomePanel = new WelcomePanel(this.getMenuFactory().getFileMenu(this));
		}
		return this.welcomePanel;
	}

	public HomeAllTabbedPanel getHomeAllTabbedPanel() {
		if (this.homepanel == null) {
			this.homepanel = new HomeAllTabbedPanel(this);
		}
		return this.homepanel;
	}

	public StepButtonPanel getStepButton() {

		if (this.stepButton == null) {
			this.stepButton = new StepButtonPanel(this);
		}
		return this.stepButton;
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

	public StepOneCenterTabbedPane getStepOneCenterTabbedPane() {
		if (this.stepOneCenterTabbedPane == null) {
			stepOneCenterTabbedPane = new StepOneCenterTabbedPane(this);
		}
		return this.stepOneCenterTabbedPane;

	}

	public StepTwoCenterTabbedPane getStepTwoCenterTabbedPane() {
		if (this.stepTwoCenterTabbedPane == null) {
			stepTwoCenterTabbedPane = new StepTwoCenterTabbedPane(this);
		}
		return this.stepTwoCenterTabbedPane;

	}

	// 第3的tab
	public StepThreeCenterTabbedPane getStepThreeCenterTabbedPane() {
		if (this.stepThreeCenterTabbedPane == null) {
			stepThreeCenterTabbedPane = new StepThreeCenterTabbedPane(this);
		}
		return this.stepThreeCenterTabbedPane;

	}

	public StepFourCenterTabbedPane getStepFourCenterTabbedPane() {
		if (this.stepFourCenterTabbedPane == null) {
			stepFourCenterTabbedPane = new StepFourCenterTabbedPane(this);
		}
		return this.stepFourCenterTabbedPane;

	}

	// 第5的tab
	public StepFiveCenterTabbedPane getStepFiveCenterTabbedPane() {
		if (this.stepFiveCenterTabbedPane == null) {
			stepFiveCenterTabbedPane = new StepFiveCenterTabbedPane(this);
		}
		return this.stepFiveCenterTabbedPane;

	}

	// 第6的tab
	public StepSixCenterTabbedPane getStepSixCenterTabbedPane() {
		if (this.stepSixCenterTabbedPane == null) {
			stepSixCenterTabbedPane = new StepSixCenterTabbedPane(this);
		}
		return this.stepSixCenterTabbedPane;

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

	public List<IWorkspace> getMarkovWorkspaceList() {
		return MarkovWorkspaceList;
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

	private ProjectTree projectTree;
	private ModelTransformationPanel modelTransformationPanel;
	private ModelExistValidationPanel modelExistValidationPanel;
	private TestCaseGenerationPanel abstractTestCaseGenerationPanel;
	private TestCaseInstantiationPanel testCaseInstantiationPanel;
	private TestCaseConfirmationPanel testCaseConfirmationPanel;

	private StepZeroCenterPanel stepZeroCenterPanel;
	private StepOneCenterPanel stepOneCenterPanel;

	private StepOneCenterTabbedPane stepOneCenterTabbedPane;
	private StepTwoCenterTabbedPane stepTwoCenterTabbedPane;
	private StepThreeCenterTabbedPane stepThreeCenterTabbedPane;
	private StepFourCenterTabbedPane stepFourCenterTabbedPane;
	private StepFiveCenterTabbedPane stepFiveCenterTabbedPane;
	private StepSixCenterTabbedPane stepSixCenterTabbedPane;

	private StepButtonPanel stepButton;
	private JPanel centerPanel = new JPanel();;
	public int stepindex = 0;

	private TopPanel topPanel;
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

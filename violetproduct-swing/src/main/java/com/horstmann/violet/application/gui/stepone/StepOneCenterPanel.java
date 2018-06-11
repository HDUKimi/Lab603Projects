package com.horstmann.violet.application.gui.stepone;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.ButtonTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.MoviePanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.ToolPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.colorpicker.ColorPickerDemo;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;

public class StepOneCenterPanel extends JPanel {

	private MainFrame mainFrame;

	private IntroducePanel introducePanel;
	private OperatePanel operatePanel;
	private JPanel workPanel;
	private JTabbedPane workTabbedPane;

	public StepOneCenterPanel(MainFrame mainFrame) {

		this.mainFrame = mainFrame;

		introducePanel = new IntroducePanel(mainFrame);
		operatePanel = new OperatePanel(mainFrame);
		workPanel = new JPanel();

//		initWorkPanel();

		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(introducePanel);
		this.add(operatePanel);
		this.add(workPanel);
		layout.setConstraints(introducePanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 1));
		layout.setConstraints(operatePanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(0, 1));
		layout.setConstraints(workPanel, new GBC(1, 0, 1, 2).setFill(GBC.BOTH).setWeight(1, 1));

	}

	public void initWorkPanel() {

		workTabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);

		workTabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println("当前选中的选项卡: " + workTabbedPane.getSelectedIndex());
			}
		});
		
		workPanel.setLayout(new GridLayout());
		workPanel.add(workTabbedPane);

	}

	public void TextAreaPrint(String word) {

	}

	public void ChangeRepaint() {
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}

	public JTabbedPane getWorkTabbedPane() {
		return workTabbedPane;
	}

}

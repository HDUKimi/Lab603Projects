package com.horstmann.violet.application.gui.stepTwo;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.common.ColorData;

public class StepTwoCenterPanel extends JPanel {

	private MainFrame mainFrame;

	private IntroducePanel introducePanel;
	private OperatePanel operatePanel;
	private JPanel workPanel;
	private JTabbedPane workTabbedPane;

	public StepTwoCenterPanel(MainFrame mainFrame) {

		this.mainFrame = mainFrame;

		introducePanel = new IntroducePanel(mainFrame);
		operatePanel = new OperatePanel(mainFrame);
		workPanel = new JPanel();
		
		workPanel.setOpaque(false);

//		initWorkPanel();

		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(introducePanel);
		this.add(operatePanel);
		this.add(workPanel);
		layout.setConstraints(introducePanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 1));
		layout.setConstraints(operatePanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(0, 1));
		layout.setConstraints(workPanel, new GBC(1, 0, 1, 2).setFill(GBC.BOTH).setWeight(1, 1));
		
		this.setBackground(ColorData.white);

	}

	public void initWorkPanel() {

		workTabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);

		workTabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println("当前选中的选项卡: " + workTabbedPane.getSelectedIndex());
			}
		});
		
		workTabbedPane.setOpaque(false);
		
		workPanel.setLayout(new GridLayout());
		workPanel.add(workTabbedPane);
		
//		workPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
//		workPanel.setBorder(
//				BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorData.gray), "剖面图",
//						TitledBorder.LEFT, TitledBorder.TOP , new Font("微软雅黑", Font.PLAIN, 13), ColorData.black));


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

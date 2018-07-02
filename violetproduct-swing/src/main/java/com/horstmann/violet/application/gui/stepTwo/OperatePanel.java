package com.horstmann.violet.application.gui.stepTwo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.common.ColorData;

public class OperatePanel extends JPanel {

	private MainFrame mainFrame;

	private JPanel buttonPanel;

	private JPanel buttonPanel1;
	private JPanel buttonPanel2;

	private JButton button1;
	private JButton button2;

	private JPanel emptyPanel;

	public OperatePanel(MainFrame mainFrame) {

		this.mainFrame = mainFrame;

		buttonPanel = new JPanel();

		initButtonPanel();

		this.setLayout(new BorderLayout());
		this.add(buttonPanel, BorderLayout.CENTER);

		this.setPreferredSize(new Dimension(300, 300));
		this.setMinimumSize(new Dimension(300, 300));
		this.setBackground(ColorData.white);

//		buttonPanel.setBorder(
//				BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorData.gray), "操作",
//						TitledBorder.LEFT, TitledBorder.TOP , new Font("微软雅黑", Font.PLAIN, 16), ColorData.black));

//		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, ColorData.gray));
		
	}

	private void initButtonPanel() {

		buttonPanel1 = new JPanel();
		buttonPanel2 = new JPanel();

		button1 = new JButton();
		button2 = new JButton();

		button1.setText("新建");
		button1.setPreferredSize(new Dimension(100, 60));

		button2.setText("打开");
		button2.setPreferredSize(new Dimension(100, 60));

		buttonPanel1.setLayout(new GridLayout());
		buttonPanel1.add(button1);
		buttonPanel1.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		buttonPanel1.setOpaque(false);
		buttonPanel2.setLayout(new GridLayout());
		buttonPanel2.add(button2);
		buttonPanel2.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		buttonPanel2.setOpaque(false);

		emptyPanel = new JPanel();
		emptyPanel.setOpaque(false);

		// buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		// buttonPanel.add(buttonPanel1);
		// buttonPanel.add(buttonPanel2);

		GridBagLayout layout = new GridBagLayout();
		buttonPanel.setLayout(layout);
		buttonPanel.add(buttonPanel1);
		buttonPanel.add(buttonPanel2);
		buttonPanel.add(emptyPanel);
		layout.setConstraints(buttonPanel1, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
		layout.setConstraints(buttonPanel2, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
		layout.setConstraints(emptyPanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));

		buttonPanel.setOpaque(false);

		initButtonListener();

	}

	private void initButtonListener() {

		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (mainFrame.getStepTwoCenterPanel().getWorkTabbedPane() == null) {
					mainFrame.getStepTwoCenterPanel().initWorkPanel();
				}

				((JMenu) mainFrame.getMenuFactory().getFileMenu(mainFrame).getFileNewMenu().getItem(1)).getItem(6)
						.doClick();

				mainFrame.getStepTwoCenterPanel().getWorkTabbedPane().setSelectedIndex(mainFrame.getStepTwoCenterPanel().getWorkTabbedPane().getTabCount()-1);
				
			}
		});

		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (mainFrame.getStepTwoCenterPanel().getWorkTabbedPane() == null) {
					mainFrame.getStepTwoCenterPanel().initWorkPanel();
				}

				mainFrame.getMenuFactory().getFileMenu(mainFrame).fileOpenItem.doClick();
				
				mainFrame.getStepTwoCenterPanel().getWorkTabbedPane().setSelectedIndex(mainFrame.getStepTwoCenterPanel().getWorkTabbedPane().getTabCount()-1);
			}
		});

	}

}

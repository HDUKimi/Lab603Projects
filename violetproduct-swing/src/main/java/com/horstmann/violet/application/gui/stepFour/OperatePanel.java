package com.horstmann.violet.application.gui.stepFour;

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
import javax.swing.JComboBox;
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
	private JPanel buttonPanel3;
	private JPanel buttonPanel4;

	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;

	private JPanel comboBoxPanel;
	private JComboBox comboBox;

	private JPanel emptyPanel;

	public OperatePanel(MainFrame mainFrame) {

		this.mainFrame = mainFrame;

		comboBoxPanel = new JPanel();
		buttonPanel = new JPanel();

		initComboBoxPanel();
		initButtonPanel();

		this.setLayout(new BorderLayout());
//		this.add(comboBoxPanel, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.CENTER);

		this.setPreferredSize(new Dimension(300, 300));
		this.setMinimumSize(new Dimension(300, 300));
		this.setBackground(ColorData.white);

		// buttonPanel.setBorder(
		// BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1,
		// 1, 1, 1, ColorData.gray), "操作",
		// TitledBorder.LEFT, TitledBorder.TOP , new Font("微软雅黑", Font.PLAIN,
		// 16), ColorData.black));

		// this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, ColorData.gray));

	}

	private void initButtonPanel() {

		buttonPanel1 = new JPanel();
		buttonPanel2 = new JPanel();
		buttonPanel3 = new JPanel();
		buttonPanel4 = new JPanel();

		button1 = new JButton();
		button2 = new JButton();
		button3 = new JButton();
		button4 = new JButton();

		button1.setText("模型预测");
		button1.setPreferredSize(new Dimension(100, 60));

		button2.setText("模型评价");
		button2.setPreferredSize(new Dimension(100, 60));

		button3.setText("模型选择");
		button3.setPreferredSize(new Dimension(100, 60));

		button4.setText("可靠性评估");
		button4.setPreferredSize(new Dimension(100, 60));

		buttonPanel1.setLayout(new GridLayout());
		buttonPanel1.add(button1);
		buttonPanel1.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		buttonPanel1.setOpaque(false);
		buttonPanel2.setLayout(new GridLayout());
		buttonPanel2.add(button2);
		buttonPanel2.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		buttonPanel2.setOpaque(false);
		buttonPanel3.setLayout(new GridLayout());
		buttonPanel3.add(button3);
		buttonPanel3.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		buttonPanel3.setOpaque(false);
		buttonPanel4.setLayout(new GridLayout());
		buttonPanel4.add(button4);
		buttonPanel4.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		buttonPanel4.setOpaque(false);

		emptyPanel = new JPanel();
		emptyPanel.setOpaque(false);

		// buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		// buttonPanel.add(buttonPanel1);
		// buttonPanel.add(buttonPanel2);

		GridBagLayout layout = new GridBagLayout();
		buttonPanel.setLayout(layout);
		buttonPanel.add(comboBoxPanel);
		buttonPanel.add(buttonPanel1);
		buttonPanel.add(buttonPanel2);
		buttonPanel.add(buttonPanel3);
		buttonPanel.add(buttonPanel4);
//		buttonPanel.add(emptyPanel);
		layout.setConstraints(comboBoxPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(buttonPanel1, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(buttonPanel2, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(buttonPanel3, new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(buttonPanel4, new GBC(0, 4, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(emptyPanel, new GBC(0, 5, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));

		buttonPanel.setOpaque(false);

		initButtonListener();

	}

	@SuppressWarnings("unchecked")
	private void initComboBoxPanel() {

		comboBox = new JComboBox<>();

		comboBox.setPreferredSize(new Dimension(100, 30));

		comboBox.addItem("失效数据1");
		comboBox.addItem("失效数据2");
		comboBox.addItem("失效数据3");
		comboBox.addItem("失效数据4");
		comboBox.addItem("失效数据5");

		comboBoxPanel.setLayout(new GridLayout());
		comboBoxPanel.add(comboBox);
		comboBoxPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		comboBoxPanel.setOpaque(false);

	}

	private void initButtonListener() {

		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (mainFrame.getStepFourCenterPanel().getWorkTabbedPane() == null) {
					mainFrame.getStepFourCenterPanel().initWorkPanel();
				}

				if (mainFrame.getStepFourCenterPanel().getWorkTabbedPane().getTabCount() == 0) {

					mainFrame.getStepFourCenterPanel().getWorkTabbedPane().add("模型预测",
							mainFrame.getStepFourCenterPanel().getModelPredictPanel());

					new Thread(new Runnable() {

						@Override
						public void run() {
							mainFrame.getStepFourCenterPanel().getModelPredictPanel().dealAndShow();
						}
					}).start();

				}

				if (mainFrame.getStepFourCenterPanel().getWorkTabbedPane().getTabCount() >= 1
						&& mainFrame.getStepFourCenterPanel().getWorkTabbedPane().getSelectedIndex() != 0) {
					mainFrame.getStepFourCenterPanel().getWorkTabbedPane().setSelectedIndex(0);
				}

			}
		});

		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (mainFrame.getStepFourCenterPanel().getWorkTabbedPane().getTabCount() == 1) {
					mainFrame.getStepFourCenterPanel().getWorkTabbedPane().add("模型评价",
							mainFrame.getStepFourCenterPanel().getModelEvaluatePanel());

					new Thread(new Runnable() {

						@Override
						public void run() {
							mainFrame.getStepFourCenterPanel().getModelEvaluatePanel().dealAndShow();
						}
					}).start();
				}

				if (mainFrame.getStepFourCenterPanel().getWorkTabbedPane().getTabCount() >= 2
						&& mainFrame.getStepFourCenterPanel().getWorkTabbedPane().getSelectedIndex() != 1) {
					mainFrame.getStepFourCenterPanel().getWorkTabbedPane().setSelectedIndex(1);
				}

			}
		});

		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (mainFrame.getStepFourCenterPanel().getWorkTabbedPane().getTabCount() == 2) {
					mainFrame.getStepFourCenterPanel().getWorkTabbedPane().add("模型选择",
							mainFrame.getStepFourCenterPanel().getModelSelectPanel());

					new Thread(new Runnable() {

						@Override
						public void run() {
							mainFrame.getStepFourCenterPanel().getModelSelectPanel().dealAndShow();
						}
					}).start();

				}

				if (mainFrame.getStepFourCenterPanel().getWorkTabbedPane().getTabCount() >= 3
						&& mainFrame.getStepFourCenterPanel().getWorkTabbedPane().getSelectedIndex() != 2) {
					mainFrame.getStepFourCenterPanel().getWorkTabbedPane().setSelectedIndex(2);
				}
			}
		});

		button4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// mainFrame.getStepFourCenterPanel().getWorkTabbedPane().add("可靠性评估",mainFrame.getStepFourCenterPanel().getReliabilityEvaluatePanel());

				if (mainFrame.getStepFourCenterPanel().getWorkTabbedPane().getTabCount() == 3) {
					mainFrame.getStepFourCenterPanel().getWorkTabbedPane().add("可靠性评估",
							mainFrame.getStepFourCenterPanel().getReliabilityEvaluatePanel());

					new Thread(new Runnable() {

						@Override
						public void run() {
							mainFrame.getStepFourCenterPanel().getReliabilityEvaluatePanel().dealAndShow();
						}
					}).start();

				}

				if (mainFrame.getStepFourCenterPanel().getWorkTabbedPane().getTabCount() >= 4
						&& mainFrame.getStepFourCenterPanel().getWorkTabbedPane().getSelectedIndex() != 3) {
					mainFrame.getStepFourCenterPanel().getWorkTabbedPane().setSelectedIndex(3);
				}
			}
		});

	}

}

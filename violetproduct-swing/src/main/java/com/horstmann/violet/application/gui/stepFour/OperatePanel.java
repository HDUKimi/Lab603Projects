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
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

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
import com.horstmann.violet.application.gui.common.FileUtil;

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
	
	private HashMap<String, String> fileMap=new HashMap<>();
	
	public OperatePanel(MainFrame mainFrame) {

		this.mainFrame = mainFrame;

		comboBoxPanel = new JPanel();
		buttonPanel = new JPanel();

		initComboBoxPanel();
		initButtonPanel();
		
		initFileMap();

		this.setLayout(new BorderLayout());
		// this.add(comboBoxPanel, BorderLayout.NORTH);
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

		button1 = new JButton();
		button2 = new JButton();

		button1.setText("生成测试用例");
		button1.setPreferredSize(new Dimension(100, 60));

		button2.setText("获取失效数据");
		button2.setPreferredSize(new Dimension(100, 60));

		buttonPanel1.setLayout(new GridLayout());
		buttonPanel1.add(button1);
		buttonPanel1.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		buttonPanel1.setOpaque(false);
		buttonPanel2.setLayout(new GridLayout());
		buttonPanel2.add(button2);
		buttonPanel2.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		buttonPanel2.setOpaque(false);

		GridBagLayout layout = new GridBagLayout();
		buttonPanel.setLayout(layout);
		buttonPanel.add(comboBoxPanel);
		buttonPanel.add(buttonPanel1);
		buttonPanel.add(buttonPanel2);
		layout.setConstraints(comboBoxPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(buttonPanel1, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(buttonPanel2, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));

		buttonPanel.setOpaque(false);

		initButtonListener();

	}

	@SuppressWarnings("unchecked")
	private void initComboBoxPanel() {
		
		comboBox = new JComboBox<>();

		comboBox.setPreferredSize(new Dimension(100, 30));
		
		comboBoxPanel.setLayout(new GridLayout());
		comboBoxPanel.add(comboBox);
		comboBoxPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		comboBoxPanel.setOpaque(false);
		
	}

	private void initFileMap() {
		
		fileMap=FileUtil.FileList("TestCase");
		
		for(String name:fileMap.keySet()){
			comboBox.addItem(name);
		}
		
	}

	private void initButtonListener() {

		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (mainFrame.getStepFourCenterPanel().getWorkTabbedPane() == null) {
					mainFrame.getStepFourCenterPanel().initWorkPanel();
				}

				if (mainFrame.getStepFourCenterPanel().getWorkTabbedPane().getTabCount() == 0) {

					mainFrame.getStepFourCenterPanel().setTestCaseName(comboBox.getSelectedItem().toString());
					mainFrame.getStepFourCenterPanel().setTestCasePath(fileMap.get(comboBox.getSelectedItem()));
					
					mainFrame.getStepFourCenterPanel().getWorkTabbedPane().add("执行测试用例",
							mainFrame.getStepFourCenterPanel().getTestCaseExecutePanel());
					
					new Thread(new Runnable() {

						@Override
						public void run() {
							mainFrame.getStepFourCenterPanel().getTestCaseExecutePanel().dealAndShow();
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

					mainFrame.getStepFourCenterPanel().getWorkTabbedPane().add("获取失效数据",
							mainFrame.getStepFourCenterPanel().getDealFailureDataPanel());

					new Thread(new Runnable() {

						@Override
						public void run() {
							mainFrame.getStepFourCenterPanel().getDealFailureDataPanel().dealAndShow();
						}
					}).start();

				}

				if (mainFrame.getStepFourCenterPanel().getWorkTabbedPane().getTabCount() >= 2
						&& mainFrame.getStepFourCenterPanel().getWorkTabbedPane().getSelectedIndex() != 1) {
					mainFrame.getStepFourCenterPanel().getWorkTabbedPane().setSelectedIndex(1);
				}

			}
		});

	}

}

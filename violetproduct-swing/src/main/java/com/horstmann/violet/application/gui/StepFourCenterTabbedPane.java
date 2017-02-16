package com.horstmann.violet.application.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import com.horstmann.violet.application.consolepart.ConsolePartScrollPane;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.FixedButtonTabbedPanel;

public class StepFourCenterTabbedPane extends JPanel {

	private JScrollPane ConsolePartScrollPane;

	private JPanel buttonPanel;
	public JPanel diagramPanel;

	private FixedButtonTabbedPanel abstractTestCaseInstantiationButtonPanel;

	private JButton abstractTestCaseInstantiationButton;

	public StepFourCenterTabbedPane() {
		ConsolePartScrollPane = new JScrollPane();

		buttonPanel = new JPanel();
		diagramPanel = new JPanel();
		diagramPanel.setLayout(new GridLayout());

		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		buttonPanel.setBackground(new Color(41, 57, 85));

		initbuttonpanel();

		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(buttonPanel);
		this.add(diagramPanel);
		layout.setConstraints(buttonPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(diagramPanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();

		this.setMinimumSize(new Dimension(screenWidth / 2, screenHeight / 2));

		// this.add("�����������ʵ����",ConsolePartScrollPane);

	}

	private void initbuttonpanel() {
		// TODO Auto-generated method stub

		abstractTestCaseInstantiationButtonPanel = new FixedButtonTabbedPanel("�����������ʵ����");
		abstractTestCaseInstantiationButton = abstractTestCaseInstantiationButtonPanel.getTabbedbutton();

		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		buttonPanel.setBackground(new Color(41, 57, 85));
		buttonPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(58, 105, 190)));

		buttonPanel.add(abstractTestCaseInstantiationButtonPanel);

		setButtonActionListener();

	}

	private void setButtonActionListener() {
		// TODO Auto-generated method stub

		abstractTestCaseInstantiationButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getDiagramPanel().removeAll();
				getDiagramPanel().add(getConsolePartScrollPane());

				abstractTestCaseInstantiationButtonPanel.setBackground(new Color(58, 105, 190));

				ChangeRepaint();
			}
		});

	}

	protected void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}

	public JScrollPane getConsolePartScrollPane() {
		return ConsolePartScrollPane;
	}

	public void setConsolePartScrollPane(JScrollPane consolePartScrollPane) {
		ConsolePartScrollPane = consolePartScrollPane;
	}
	
	public JPanel getDiagramPanel() {
		return diagramPanel;
	}

	public void setDiagramPanel(JPanel diagramPanel) {
		this.diagramPanel = diagramPanel;
	}

}

package com.horstmann.violet.application.gui.stepOne;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JPanel;

import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.common.ColorData;

public class OperatePanel extends JPanel {

	private MainFrame mainFrame;

	private JPanel buttonPanel;
	private JButton button1;
	private JButton button2;

	public OperatePanel(MainFrame mainFrame) {

		this.mainFrame = mainFrame;

		buttonPanel = new JPanel();

		initButtonPanel();

		this.setLayout(new BorderLayout());
		this.add(buttonPanel, BorderLayout.CENTER);

	}

	private void initButtonPanel() {

		button1=new JButton();
		button1.setText("新建");
		
		button2=new JButton();
		button2.setText("打开");
		
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(button1);
		buttonPanel.add(button2);
		
		initButtonListener();
		
	}

	private void initButtonListener() {
		
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(mainFrame.getStepOneCenterPanel().getWorkTabbedPane()==null){
					mainFrame.getStepOneCenterPanel().initWorkPanel();
				}
				
				((JMenu) mainFrame.getMenuFactory().getFileMenu(mainFrame).getFileNewMenu().getItem(1)).getItem(6).doClick();
				
			}
		});
		
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(mainFrame.getStepOneCenterPanel().getWorkTabbedPane()==null){
					mainFrame.getStepOneCenterPanel().initWorkPanel();
				}
				
				mainFrame.getMenuFactory().getFileMenu(mainFrame).fileOpenItem.doClick();
			}
		});
		
	}

}

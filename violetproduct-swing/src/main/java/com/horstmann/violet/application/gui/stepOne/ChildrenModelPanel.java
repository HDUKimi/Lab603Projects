package com.horstmann.violet.application.gui.stepOne;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.horstmann.violet.application.gui.MainFrame;

public class ChildrenModelPanel extends JPanel{
	
	private MainFrame mainFrame;
	private RootModelPanel rootModelPanel;
	private ChildrenModelPanel childrenModelPanel;

	private JLabel nameLabel1;
	private JLabel nameLabel2;
	
	private JTextField textField1;
	private JTextField textField2;
	
	private JButton button;
	
	public ChildrenModelPanel(MainFrame mainFrame, RootModelPanel rootModelPanel) {
		
		this.mainFrame=mainFrame;
		this.rootModelPanel=rootModelPanel;
		childrenModelPanel=this;
		
		initLabelAndTextField();
		
		initButton();
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		this.add(nameLabel1);
		this.add(textField1);
		this.add(nameLabel2);
		this.add(textField2);
		this.add(button);
		
		this.setOpaque(false);
		this.setBorder(BorderFactory.createEmptyBorder(0, 13, 0, 0));
		
	}

	private void initLabelAndTextField() {
		
		nameLabel1=new JLabel();
		nameLabel2=new JLabel();
		
		textField1=new JTextField();
		textField2=new JTextField();
		
		textField1.setMinimumSize(new Dimension(80, 21));
		textField1.setMaximumSize(new Dimension(80, 21));
		textField1.setPreferredSize(new Dimension(80, 21));
		
		textField2.setMinimumSize(new Dimension(80, 21));
		textField2.setMaximumSize(new Dimension(80, 21));
		textField2.setPreferredSize(new Dimension(80, 21));
		
		nameLabel1.setText("子模块：");
		nameLabel2.setText("可靠性：");
		
	}

	private void initButton() {
		
		button=new JButton();
		
		button.setText("删除");
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				rootModelPanel.getChildrenModelList().remove(childrenModelPanel);
				childrenModelPanel.setVisible(false);
				mainFrame.ChangeRepaint(mainFrame.getStepOneCenterPanel());
				
			}
		});
		
	}

	public JLabel getNameLabel1() {
		return nameLabel1;
	}

	public JLabel getNameLabel2() {
		return nameLabel2;
	}

	public JTextField getTextField1() {
		return textField1;
	}

	public JTextField getTextField2() {
		return textField2;
	}
	
}

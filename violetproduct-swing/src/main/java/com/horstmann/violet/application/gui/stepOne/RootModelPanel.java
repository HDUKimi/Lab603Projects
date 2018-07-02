package com.horstmann.violet.application.gui.stepOne;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.common.ColorData;

public class RootModelPanel extends JPanel{
	
	private MainFrame mainFrame;
	private RootModelPanel rootModelPanel;
	
	private JPanel rootPanel;
	private JPanel childrenPanel;
	private JPanel emptyPanel;
	
	private JLabel nameLabel;
	private JTextField textField;
	
	private JButton button1;
	private JButton button2;
	
	private List<ChildrenModelPanel> childrenModelList=new ArrayList<>();

	public RootModelPanel(MainFrame mainFrame) {
		
		this.mainFrame=mainFrame;
		rootModelPanel=this;
		
		rootPanel=new JPanel();
		childrenPanel=new JPanel();
		emptyPanel=new JPanel();
		
		initRootPanel();
		initChildrenPanel();
		
		rootPanel.setOpaque(false);
		childrenPanel.setOpaque(false);
		emptyPanel.setOpaque(false);
		
		rootPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorData.gray));
		childrenPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, ColorData.gray));
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(rootPanel);
		this.add(childrenPanel);
		this.add(emptyPanel);
		layout.setConstraints(rootPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
		layout.setConstraints(childrenPanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
		layout.setConstraints(emptyPanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		
		this.setBackground(ColorData.white);
		this.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		
	}

	private void initRootPanel() {
		
		nameLabel=new JLabel();
		textField=new JTextField();
		
		nameLabel.setText("系统模块：");
		
		textField.setMinimumSize(new Dimension(80, 21));
		textField.setMaximumSize(new Dimension(80, 21));
		textField.setPreferredSize(new Dimension(80, 21));
		
		initButtonPanel();
		
		rootPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		rootPanel.add(nameLabel);
		rootPanel.add(textField);
		rootPanel.add(button1);
		rootPanel.add(button2);
		
	}

	private void initButtonPanel() {
		
		button1=new JButton();
		button2=new JButton();
		
		button1.setText("添加");
		
		button2.setText("删除");
		
		initButtonListener();
		
	}

	private void initButtonListener() {
		
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ChildrenModelPanel childrenModelPanel=new ChildrenModelPanel(mainFrame,rootModelPanel);
				
				childrenPanel.add(childrenModelPanel);
				childrenModelList.add(childrenModelPanel);
				
				mainFrame.ChangeRepaint(mainFrame.getStepOneCenterPanel());
				
			}
		});
		
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				mainFrame.getStepOneCenterPanel().getWorkPanel().getRootModelList().remove(rootModelPanel);
				rootModelPanel.setVisible(false);
				mainFrame.ChangeRepaint(mainFrame.getStepOneCenterPanel());
			}
		});
		
	}

	private void initChildrenPanel() {
		
		childrenPanel.setLayout(new BoxLayout(childrenPanel, BoxLayout.Y_AXIS));
		
		ChildrenModelPanel childrenModelPanel=new ChildrenModelPanel(mainFrame,rootModelPanel);
		childrenPanel.add(childrenModelPanel);
		childrenModelList.add(childrenModelPanel);
		
	}

	public List<ChildrenModelPanel> getChildrenModelList() {
		return childrenModelList;
	}

	public JLabel getNameLabel() {
		return nameLabel;
	}

	public JTextField getTextField() {
		return textField;
	}
	
}

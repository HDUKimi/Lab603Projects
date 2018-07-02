package com.horstmann.violet.application.gui.stepOne;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.common.ColorData;

public class WorkPanel extends JPanel{
	
	private MainFrame mainFrame;
	
	private JScrollPane scrollPanel;
	private JPanel mainPanel;
	
	private JPanel modelPanel;
	private JPanel buttonPanel;
	
	private JButton button1;
	private JButton button2;
	
	private JPanel emptyPanel;
	
	private List<RootModelPanel> rootModelList=new ArrayList<>();
	
	public WorkPanel(MainFrame mainFrame) {
		
		this.mainFrame=mainFrame;
		
		mainPanel=new JPanel();
		
		modelPanel=new JPanel();
		buttonPanel=new JPanel();
		emptyPanel=new JPanel();
		
//		mainPanel.setOpaque(false);
		modelPanel.setOpaque(false);
		buttonPanel.setOpaque(false);
		emptyPanel.setOpaque(false);
		
		initModelPanel();
		initButtonPanel();
		
		GridBagLayout layout = new GridBagLayout();
		mainPanel.setLayout(layout);
		mainPanel.add(modelPanel);
		mainPanel.add(buttonPanel);
		mainPanel.add(emptyPanel);
		layout.setConstraints(modelPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(buttonPanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
		layout.setConstraints(emptyPanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		mainPanel.setBackground(ColorData.white);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		
		scrollPanel=new JScrollPane(mainPanel);
		scrollPanel.setBorder(null);
		
		this.setLayout(new GridLayout());
		this.add(scrollPanel);
		
		this.setBackground(ColorData.white);
		
	}

	private void initModelPanel() {
		
		modelPanel.setLayout(new BoxLayout(modelPanel, BoxLayout.Y_AXIS));
		
		RootModelPanel rootModelPanel=new RootModelPanel(mainFrame);
		
		modelPanel.add(rootModelPanel);
		rootModelList.add(rootModelPanel);
		
	}

	private void initButtonPanel() {
		
		button1=new JButton();
		button2=new JButton();
		
		button1=new JButton("添加");
		button2=new JButton("提交");
		
		initButtonListener();
		
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		buttonPanel.add(button1);
		buttonPanel.add(button2);

	}

	private void initButtonListener() {
		
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				RootModelPanel rootModelPanel=new RootModelPanel(mainFrame);
				
				modelPanel.add(rootModelPanel);
				rootModelList.add(rootModelPanel);
				
				mainFrame.ChangeRepaint(mainFrame.getStepOneCenterPanel());
				
			}
		});
		
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("提交的信息");
				System.out.println("");
				for(RootModelPanel rootModelPanel:rootModelList){
					System.out.println(rootModelPanel.getNameLabel().getText()+" "+rootModelPanel.getTextField().getText().trim());
					for(ChildrenModelPanel childrenModelPanel:rootModelPanel.getChildrenModelList()){
						System.out.println(childrenModelPanel.getNameLabel1().getText()+" "+childrenModelPanel.getTextField1().getText()+" "+childrenModelPanel.getNameLabel2().getText()+" "+childrenModelPanel.getTextField2().getText());
					}
					System.out.println("");
				}
				
			}
		});
		
	}

	public List<RootModelPanel> getRootModelList() {
		return rootModelList;
	}
	
}

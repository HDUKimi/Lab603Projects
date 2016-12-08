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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import com.horstmann.violet.application.consolepart.ConsolePartScrollPane;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.FixedButtonTabbedPanel;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.ExistTest;

public class StepSixCenterTabbedPane extends JPanel {
	
//	private JPanel ConsolePartScrollPane;
//
//	public StepSixCenterTabbedPane() {
//		ConsolePartScrollPane = new JPanel();
//		this.add("一致性的验证", ConsolePartScrollPane);
//	}
//
//	public JPanel getConsolePartScrollPane() {
//		return ConsolePartScrollPane;
//	}
//
//	public void setConsolePartScrollPane(JPanel consolePartScrollPane) {
//		ConsolePartScrollPane = consolePartScrollPane;
//	}
	
	private MainFrame mainFrame;
	
	private JPanel buttonPanel;
	public JPanel diagramPanel;
	
	private JButton timingDiagramButton;
	private FixedButtonTabbedPanel timingDiagramButtonPanel;
	private JPanel timingDiagramTabbedPane;
	
	private JButton uppaalDiagramButton;
	private FixedButtonTabbedPanel uppaalDiagramButtonPanel;
	private JPanel uppaalDiagramTabbedPane;
	
	private JButton validationDiagramButton;
	private FixedButtonTabbedPanel validationDiagramButtonPanel;
	private JPanel validationDiagramTabbedPane;
	
	
	
	public StepSixCenterTabbedPane(MainFrame mainFrame){
		
		this.mainFrame = mainFrame;
		
		buttonPanel=new JPanel();
		diagramPanel=new JPanel();
		
		timingDiagramTabbedPane=new JPanel();
		timingDiagramTabbedPane.setLayout(new GridBagLayout());
		uppaalDiagramTabbedPane=new JPanel();
		uppaalDiagramTabbedPane.setLayout(new GridBagLayout());
		validationDiagramTabbedPane=new JPanel();
		validationDiagramTabbedPane.setLayout(new GridBagLayout());
		
		
		
		initbuttonpanel();
		initdiagrampanel();
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(buttonPanel);
		this.add(diagramPanel);
		layout.setConstraints(buttonPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(diagramPanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();
		
		this.setMinimumSize(new Dimension(screenWidth/2, screenHeight/2));
		
	}



	private void initbuttonpanel() {
		// TODO Auto-generated method stub
		
		timingDiagramButtonPanel=new FixedButtonTabbedPanel("时序图");
		timingDiagramButtonPanel.setBackground(new Color(58, 105, 190));
		timingDiagramButton=timingDiagramButtonPanel.getTabbedbutton();
		
		uppaalDiagramButtonPanel=new FixedButtonTabbedPanel("时间自动机");
		uppaalDiagramButtonPanel.setBackground(new Color(77, 96, 130));
		uppaalDiagramButton=uppaalDiagramButtonPanel.getTabbedbutton();
		
		validationDiagramButtonPanel=new FixedButtonTabbedPanel("一致性验证");
		validationDiagramButtonPanel.setBackground(new Color(77, 96, 130));
		validationDiagramButton=validationDiagramButtonPanel.getTabbedbutton();
		
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		buttonPanel.setBackground(new Color(41, 57, 85));
		buttonPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(58, 105, 190)));
		
		buttonPanel.add(timingDiagramButtonPanel);
		buttonPanel.add(uppaalDiagramButtonPanel);
		buttonPanel.add(validationDiagramButtonPanel);
		
		setButtonActionListener();
		
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		buttonPanel.setBackground(new Color(41, 57, 85));
		
	}



	private void setButtonActionListener() {
		// TODO Auto-generated method stub
		
		timingDiagramButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				getDiagramPanel().removeAll();
				getDiagramPanel().setLayout(new GridLayout());
				getDiagramPanel().add(timingDiagramTabbedPane);
				
				timingDiagramButtonPanel.setBackground(new Color(58, 105, 190));
				uppaalDiagramButtonPanel.setBackground(new Color(77, 96, 130));
				validationDiagramButtonPanel.setBackground(new Color(77, 96, 130));
				
				ChangeRepaint();
			}
		});
		
		uppaalDiagramButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				getDiagramPanel().removeAll();
				getDiagramPanel().setLayout(new GridLayout());
				getDiagramPanel().add(uppaalDiagramTabbedPane);
				
				uppaalDiagramButtonPanel.setBackground(new Color(58, 105, 190));
				timingDiagramButtonPanel.setBackground(new Color(77, 96, 130));
				validationDiagramButtonPanel.setBackground(new Color(77, 96, 130));
				
				ChangeRepaint();
				
			}
		});
		
		validationDiagramButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				getDiagramPanel().removeAll();
				getDiagramPanel().setLayout(new GridLayout());
				getDiagramPanel().add(validationDiagramTabbedPane);
				
				validationDiagramButtonPanel.setBackground(new Color(58, 105, 190));
				timingDiagramButtonPanel.setBackground(new Color(77, 96, 130));
				uppaalDiagramButtonPanel.setBackground(new Color(77, 96, 130));
				
				ChangeRepaint();
				
			}
		});
		
	}



	private void initdiagrampanel() {
		// TODO Auto-generated method stub
		
		diagramPanel.setLayout(new GridLayout());
		try {
			validationDiagramTabbedPane.setLayout(new GridLayout());
			validationDiagramTabbedPane.add(new ExistTest().existTest());
			diagramPanel.add(new ExistTest().existTest());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		diagramPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(142, 155, 188)));
		
	}

	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}


	public JPanel getDiagramPanel() {
		return diagramPanel;
	}

	public void setDiagramPanel(JPanel diagramPanel) {
		this.diagramPanel = diagramPanel;
	}

	public JPanel getTimingDiagramTabbedPane() {
		return timingDiagramTabbedPane;
	}

	public JPanel getUppaalDiagramTabbedPane() {
		return uppaalDiagramTabbedPane;
	}

	public JPanel getValidationDiagramTabbedPane() {
		return validationDiagramTabbedPane;
	}



	public JButton getTimingDiagramButton() {
		return timingDiagramButton;
	}



	public JButton getUppaalDiagramButton() {
		return uppaalDiagramButton;
	}
	
	
	
	

}

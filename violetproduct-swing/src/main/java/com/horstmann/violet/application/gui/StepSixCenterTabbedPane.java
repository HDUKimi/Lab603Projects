package com.horstmann.violet.application.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;

import com.horstmann.violet.application.consolepart.ConsolePartScrollPane;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.FixedButtonTabbedPanel;

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
	
	private JPanel buttonTabbedPanel;
	private JScrollPane buttonScrollPanel;
	private JButton leftButton;
	private JButton rightButton;
	
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
		
		buttonTabbedPanel=new JPanel();
		
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

	public void initUIPanel(){
		
		mainFrame.getModelExistValidationPanel().getValidationinfopanel().removeAll();
		mainFrame.getModelExistValidationPanel().getValidationcheckboxpanel().removeAll();
		
		mainFrame.getStepSixCenterTabbedPane().getUppaalDiagramTabbedPane().removeAll();
		mainFrame.getStepSixCenterTabbedPane().getTimingDiagramTabbedPane().removeAll();
		
		mainFrame.getValidationResultPanel().getOnenamelabel().setText("");
		mainFrame.getValidationResultPanel().getTwonamelabel().setText("");
		mainFrame.getValidationResultPanel().getThreenamelabel().setText("测试对比结果显示： ");
		mainFrame.getValidationResultPanel().getFournamelabel().setText("时间刻度显示： ");
		mainFrame.getValidationResultPanel().getOneresultpanel().removeAll();
		mainFrame.getValidationResultPanel().getTworesultpanel().removeAll();
		mainFrame.getValidationResultPanel().getStatelocationresultpanel().removeAll();
		mainFrame.getValidationResultPanel().getMessagetransitionresultpanel().removeAll();
		mainFrame.getValidationResultPanel().getFourresultpanel().removeAll();
		
		
		mainFrame.getConsolePartPanel().getTextarea6().setText("");
	}

	private void initbuttonpanel() {
		// TODO Auto-generated method stub
		
		initleftrightbuttonpanel();
		
		timingDiagramButtonPanel=new FixedButtonTabbedPanel("时序图");
		timingDiagramButtonPanel.setBackground(new Color(58, 105, 190));
		timingDiagramButton=timingDiagramButtonPanel.getTabbedbutton();
		
		uppaalDiagramButtonPanel=new FixedButtonTabbedPanel("时间自动机");
		uppaalDiagramButtonPanel.setBackground(new Color(77, 96, 130));
		uppaalDiagramButton=uppaalDiagramButtonPanel.getTabbedbutton();
		
		validationDiagramButtonPanel=new FixedButtonTabbedPanel("一致性验证");
		validationDiagramButtonPanel.setBackground(new Color(77, 96, 130));
		validationDiagramButton=validationDiagramButtonPanel.getTabbedbutton();
		
		buttonTabbedPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		buttonTabbedPanel.setBackground(new Color(41, 57, 85));
		
		buttonTabbedPanel.add(timingDiagramButtonPanel);
		buttonTabbedPanel.add(uppaalDiagramButtonPanel);
//		buttonPanel.add(validationDiagramButtonPanel);
		
		setButtonActionListener();
		
		buttonScrollPanel=new JScrollPane(buttonTabbedPanel);
		buttonScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		buttonScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		buttonScrollPanel.setBorder(null);

		buttonPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(58, 105, 190)));
		buttonPanel.setBackground(new Color(41, 57, 85));
		buttonPanel.setPreferredSize(new Dimension(300, 23));
		buttonPanel.setMinimumSize(new Dimension(300, 23));
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.add(leftButton, BorderLayout.WEST);
		buttonPanel.add(buttonScrollPanel, BorderLayout.CENTER);
		buttonPanel.add(rightButton, BorderLayout.EAST);
		
	}

	private void initleftrightbuttonpanel() {
		// TODO Auto-generated method stub
		
		leftButton=new JButton();
		rightButton=new JButton();
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		final ImageIcon icon1 = new ImageIcon(path + "left1.png");
		icon1.setImage(icon1.getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
		final ImageIcon icon2 = new ImageIcon(path + "right1.png");
		icon2.setImage(icon2.getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
		final ImageIcon icon3 = new ImageIcon(path + "left3.png");
		icon1.setImage(icon1.getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
		final ImageIcon icon4 = new ImageIcon(path + "right3.png");
		icon2.setImage(icon2.getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
		
		leftButton.setIcon(icon1);
		leftButton.setFocusable(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setBorderPainted(false);
//		leftButton.addMouseListener(new ButtonMouseListener());
		leftButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				leftButton.setIcon(icon3);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				leftButton.setIcon(icon1);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				leftButton.setIcon(icon3);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		leftButton.setPreferredSize(new Dimension(21,21));
		leftButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(41, 57, 85)));
		leftButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JScrollBar bar=buttonScrollPanel.getHorizontalScrollBar();
				bar.setValue(bar.getValue()-100);
			}
		});
		
		rightButton.setIcon(icon2);
		rightButton.setFocusable(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setBorderPainted(false);
//		rightButton.addMouseListener(new ButtonMouseListener());
		rightButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				rightButton.setIcon(icon4);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				rightButton.setIcon(icon2);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				rightButton.setIcon(icon4);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		rightButton.setPreferredSize(new Dimension(21,21));
		rightButton.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(41, 57, 85)));
		rightButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JScrollBar bar=buttonScrollPanel.getHorizontalScrollBar();
				bar.setValue(bar.getValue()+100);
			}
		});
		
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
//			validationDiagramTabbedPane.setLayout(new GridLayout());
//			validationDiagramTabbedPane.add(new ExistTest().existTest());
//			diagramPanel.add(new ExistTest().existTest());
			
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

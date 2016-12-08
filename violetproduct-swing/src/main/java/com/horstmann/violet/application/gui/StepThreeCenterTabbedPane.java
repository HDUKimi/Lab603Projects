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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import com.horstmann.violet.application.consolepart.ConsolePartScrollPane;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.FixedButtonTabbedPanel;

public class StepThreeCenterTabbedPane extends JPanel {

		
	private JPanel abstractUppaalTabbedPane;
	private JPanel uppaalTabbedPane;
	private JPanel abstractTestCaseOptimize;
	private JScrollPane consolePartScrollPane;
	
	private JPanel buttonPanel;
	public JPanel diagramPanel;
	
	private FixedButtonTabbedPanel abstractUppaalDiagramButtonPanel;
	private FixedButtonTabbedPanel uppaalDiagramButtonPanel;
	private FixedButtonTabbedPanel testCaseProduceButtonPanel;
	private FixedButtonTabbedPanel abstractTestCaseOptimizeButtonPanel;
	
	private JButton abstractUppaalDiagramButton;
	private JButton uppaalDiagramButton;
	private JButton testCaseProduceButton;
	private JButton abstractTestCaseOptimizeButton;
	
	private JLabel emptylabel;
	
	private JSplitPane js;

	public StepThreeCenterTabbedPane() {

		uppaalTabbedPane = new JPanel();
		abstractTestCaseOptimize = new JPanel();
		abstractUppaalTabbedPane = new JPanel();
		consolePartScrollPane = new JScrollPane();

		uppaalTabbedPane.setLayout(new GridBagLayout());
		abstractUppaalTabbedPane.setLayout(new GridBagLayout());

		buttonPanel=new JPanel();
		diagramPanel=new JPanel();
		diagramPanel.setLayout(new GridLayout());
		
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		buttonPanel.setBackground(new Color(41, 57, 85));
		
//		initButton();
		
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
		
		this.setMinimumSize(new Dimension(screenWidth/2, screenHeight/2));
		
//		this.add("抽象时间迁移的自动机", abstractUppaalTabbedPane);
//		this.add("不含时间迁移的自动机", uppaalTabbedPane);
//		this.add("抽象测试用例生成", consolePartScrollPane);
//		this.add("抽象测试用例优化约简", abstractTestCaseOptimize);

	}

	private void initbuttonpanel() {
		// TODO Auto-generated method stub
		
		abstractUppaalDiagramButtonPanel=new FixedButtonTabbedPanel("抽象时间迁移的自动机");
		abstractUppaalDiagramButton=abstractUppaalDiagramButtonPanel.getTabbedbutton();
		
		uppaalDiagramButtonPanel=new FixedButtonTabbedPanel("不含时间迁移的自动机");
		uppaalDiagramButton=uppaalDiagramButtonPanel.getTabbedbutton();

		testCaseProduceButtonPanel=new FixedButtonTabbedPanel("抽象测试用例生成");
		testCaseProduceButton=testCaseProduceButtonPanel.getTabbedbutton();
		
		abstractTestCaseOptimizeButtonPanel=new FixedButtonTabbedPanel("抽象测试用例优化约简");
		abstractTestCaseOptimizeButton=abstractTestCaseOptimizeButtonPanel.getTabbedbutton();
		
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		buttonPanel.setBackground(new Color(41, 57, 85));
		buttonPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(58, 105, 190)));
		
		buttonPanel.add(abstractUppaalDiagramButtonPanel);
		buttonPanel.add(uppaalDiagramButtonPanel);
		buttonPanel.add(testCaseProduceButtonPanel);
		buttonPanel.add(abstractTestCaseOptimizeButtonPanel);
		
		setButtonActionListener();
		
	}

	private void setButtonActionListener() {
		// TODO Auto-generated method stub
		abstractUppaalDiagramButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getDiagramPanel().removeAll();
				getDiagramPanel().add(getAbstractUppaalTabbedPane());
				
				abstractUppaalDiagramButtonPanel.setBackground(new Color(58, 105, 190));
				uppaalDiagramButtonPanel.setBackground(new Color(77, 96, 130));
				testCaseProduceButtonPanel.setBackground(new Color(77, 96, 130));
				abstractTestCaseOptimizeButtonPanel.setBackground(new Color(77, 96, 130));
				
				ChangeRepaint();
			}
		});
		uppaalDiagramButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getDiagramPanel().removeAll();
				getDiagramPanel().add(getUppaalTabbedPane());
				
				uppaalDiagramButtonPanel.setBackground(new Color(58, 105, 190));
				abstractUppaalDiagramButtonPanel.setBackground(new Color(77, 96, 130));
				testCaseProduceButtonPanel.setBackground(new Color(77, 96, 130));
				abstractTestCaseOptimizeButtonPanel.setBackground(new Color(77, 96, 130));
				
				ChangeRepaint();
			}
		});
		testCaseProduceButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getDiagramPanel().removeAll();
				getDiagramPanel().add(getConsolePartScrollPane());
				
				testCaseProduceButtonPanel.setBackground(new Color(58, 105, 190));
				abstractUppaalDiagramButtonPanel.setBackground(new Color(77, 96, 130));
				uppaalDiagramButtonPanel.setBackground(new Color(77, 96, 130));
				abstractTestCaseOptimizeButtonPanel.setBackground(new Color(77, 96, 130));
				
				ChangeRepaint();
			}
		});
		abstractTestCaseOptimizeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getDiagramPanel().removeAll();
				getDiagramPanel().add(getAbstractTestCaseOptimize());
				
				abstractUppaalDiagramButtonPanel.setBackground(new Color(77, 96, 130));
				uppaalDiagramButtonPanel.setBackground(new Color(77, 96, 130));
				testCaseProduceButtonPanel.setBackground(new Color(77, 96, 130));
				abstractTestCaseOptimizeButtonPanel.setBackground(new Color(58, 105, 190));
				
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

		public JPanel getAbstractUppaalTabbedPane() {
			return abstractUppaalTabbedPane;
		}
		public void setAbstractUppaalTabbedPane(JPanel abstractUppaalTabbedPane) {
			this.abstractUppaalTabbedPane = abstractUppaalTabbedPane;
		}
		public JPanel getUppaalTabbedPane() {
			return uppaalTabbedPane;
		}
		public void setUppaalTabbedPane(JPanel uppaalTabbedPane) {
			this.uppaalTabbedPane = uppaalTabbedPane;
		}
		public JPanel getAbstractTestCaseOptimize() {
			return abstractTestCaseOptimize;
		}
		public void setAbstractTestCaseOptimize(JPanel abstractTestCaseOptimize) {
			this.abstractTestCaseOptimize = abstractTestCaseOptimize;
		}
		public JScrollPane getConsolePartScrollPane() {
			return consolePartScrollPane;
		}
		public void setConsolePartScrollPane(JScrollPane consolePartScrollPane) {
			this.consolePartScrollPane = consolePartScrollPane;
		}
		
	public JPanel getDiagramPanel() {
		return diagramPanel;
	}

	public void setDiagramPanel(JPanel diagramPanel) {
		this.diagramPanel = diagramPanel;
	}

	public JButton getAbstractUppaalDiagramButton() {
		return abstractUppaalDiagramButton;
	}

	public JButton getUppaalDiagramButton() {
		return uppaalDiagramButton;
	}

	public JButton getTestCaseProduceButton() {
		return testCaseProduceButton;
	}

	public JButton getAbstractTestCaseOptimizeButton() {
		return abstractTestCaseOptimizeButton;
	}
	

}

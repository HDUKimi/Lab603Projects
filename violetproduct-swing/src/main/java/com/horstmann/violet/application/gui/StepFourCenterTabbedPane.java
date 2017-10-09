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
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.horstmann.violet.application.gui.stepCenterTabbedPane.FixedButtonTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.TestCaseInstantiationProcessTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.TestCaseInstantiationTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.TestCaseProduceTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.TestCaseShowTabbedPanel;

public class StepFourCenterTabbedPane extends JPanel {

	private MainFrame mainFrame;

	private JPanel buttonPanel;
	public JPanel diagramPanel;
	
	private JPanel buttonTabbedPanel;
	private JScrollPane buttonScrollPanel;
	private JButton leftButton;
	private JButton rightButton;

	private int fixButtonTabbedPanelSelectedIndex=0;
	private List<FixedButtonTabbedPanel> fixButtonTabbedPanelList=new ArrayList<FixedButtonTabbedPanel>();

	private FixedButtonTabbedPanel testCaseInstantiationProcessButtonPanel;
	private FixedButtonTabbedPanel testCaseProduceButtonPanel;
	private FixedButtonTabbedPanel testCaseInstantiationButtonPanel;
	private FixedButtonTabbedPanel testCaseShowButtonPanel;
	private FixedButtonTabbedPanel borderTestCaseShowButtonPanel;

	private JButton testCaseInstantiationProcessButton;
	private JButton testCaseProduceButton;
	private JButton testCaseInstantiationButton;
	private JButton testCaseShowButton;
	private JButton borderTestCaseShowButton;
	
	private TestCaseInstantiationProcessTabbedPanel testCaseInstantiationProcessTabbedPanel;
	private TestCaseProduceTabbedPanel testCaseProduceTabbedPanel;
	private TestCaseInstantiationTabbedPanel testCaseInstantiationTabbedPanel;
	private TestCaseShowTabbedPanel testCaseShowTabbedPanel;
	private TestCaseShowTabbedPanel borderTestCaseShowTabbedPanel;
	
	private static String BecomeRunFileName=null;
	private static int BecomeRunFileNameType=-1;
	
	public StepFourCenterTabbedPane(MainFrame mainFrame) {
		
		this.mainFrame = mainFrame;
		
		testCaseInstantiationProcessTabbedPanel=new TestCaseInstantiationProcessTabbedPanel(mainFrame);
		testCaseProduceTabbedPanel=new TestCaseProduceTabbedPanel(mainFrame);
		testCaseInstantiationTabbedPanel=new TestCaseInstantiationTabbedPanel(mainFrame);
		testCaseShowTabbedPanel=new TestCaseShowTabbedPanel(mainFrame,1);
		borderTestCaseShowTabbedPanel=new TestCaseShowTabbedPanel(mainFrame,1);
		borderTestCaseShowTabbedPanel.getMoviepanel().getMovieLabel().setText("正在生成边界值测试用例");

		buttonPanel = new JPanel();
		diagramPanel = new JPanel();
		diagramPanel.setLayout(new GridLayout());
		
		buttonTabbedPanel=new JPanel();

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

		this.setMinimumSize(new Dimension(screenWidth / 2, screenHeight / 2));

	}
	
	private void initdiagrampanel() {
		// TODO Auto-generated method stub
		
		diagramPanel.setLayout(new GridLayout());
		diagramPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(142, 155, 188)));
		diagramPanel.add(testCaseInstantiationProcessTabbedPanel);
		
	}

	private void initbuttonpanel() {
		// TODO Auto-generated method stub

		initleftrightbuttonpanel();
		
		testCaseInstantiationProcessButtonPanel = new FixedButtonTabbedPanel("测试用例实例化进程");
		testCaseInstantiationProcessButton = testCaseInstantiationProcessButtonPanel.getTabbedbutton();
		
		testCaseProduceButtonPanel = new FixedButtonTabbedPanel("读取测试序列");
		testCaseProduceButton = testCaseProduceButtonPanel.getTabbedbutton();
		
		testCaseInstantiationButtonPanel = new FixedButtonTabbedPanel("实例化");
		testCaseInstantiationButton = testCaseInstantiationButtonPanel.getTabbedbutton();
		
		testCaseShowButtonPanel = new FixedButtonTabbedPanel("生成测试用例");
		testCaseShowButton = testCaseShowButtonPanel.getTabbedbutton();
		
		borderTestCaseShowButtonPanel = new FixedButtonTabbedPanel("生成边界值测试用例");
		borderTestCaseShowButton = borderTestCaseShowButtonPanel.getTabbedbutton();

		buttonTabbedPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		buttonTabbedPanel.setBackground(new Color(41, 57, 85));
		
		buttonTabbedPanel.add(testCaseInstantiationProcessButtonPanel);
		buttonTabbedPanel.add(testCaseProduceButtonPanel);
		buttonTabbedPanel.add(testCaseInstantiationButtonPanel);
		buttonTabbedPanel.add(testCaseShowButtonPanel);
		buttonTabbedPanel.add(borderTestCaseShowButtonPanel);
		
		buttonScrollPanel=new JScrollPane(buttonTabbedPanel);
		buttonScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		buttonScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		buttonScrollPanel.setBorder(null);

		setButtonActionListener();
		
		fixButtonTabbedPanelList.add(testCaseInstantiationProcessButtonPanel);
		fixButtonTabbedPanelList.add(testCaseProduceButtonPanel);
		fixButtonTabbedPanelList.add(testCaseInstantiationButtonPanel);
		fixButtonTabbedPanelList.add(testCaseShowButtonPanel);
		fixButtonTabbedPanelList.add(borderTestCaseShowButtonPanel);
		
		setFixButtonTabbedPanelVisible();
		
		buttonPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(58, 105, 190)));
		buttonPanel.setBackground(new Color(41, 57, 85));
		buttonPanel.setPreferredSize(new Dimension(300, 23));
		buttonPanel.setMinimumSize(new Dimension(300, 23));
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.add(leftButton, BorderLayout.WEST);
		buttonPanel.add(buttonScrollPanel, BorderLayout.CENTER);
		buttonPanel.add(rightButton, BorderLayout.EAST);


	}
	
	private void setFixButtonTabbedPanelVisible() {
		// TODO Auto-generated method stub
		
		for(FixedButtonTabbedPanel fbtpanel:fixButtonTabbedPanelList){
			fbtpanel.setVisible(false);
		}
		testCaseInstantiationProcessButtonPanel.setVisible(true);
		testCaseInstantiationProcessButtonPanel.setBackground(new Color(58, 105, 190));
		fixButtonTabbedPanelSelectedIndex=0;
		
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

		testCaseInstantiationProcessButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getDiagramPanel().removeAll();
				getDiagramPanel().add(testCaseInstantiationProcessTabbedPanel);

				ChangeAllButtonPanelState();
				testCaseInstantiationProcessButtonPanel.setBackground(new Color(58, 105, 190));
				
				fixButtonTabbedPanelSelectedIndex=0;

				if(mainFrame.getStepindex()==4){
					ChangeRepaint();
				}
			}
		});
		
		testCaseProduceButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getDiagramPanel().removeAll();
				getDiagramPanel().add(testCaseProduceTabbedPanel);

				ChangeAllButtonPanelState();
				testCaseProduceButtonPanel.setBackground(new Color(58, 105, 190));
				
				fixButtonTabbedPanelSelectedIndex=1;

				ChangeRepaint();
			}
		});
		
		testCaseInstantiationButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getDiagramPanel().removeAll();
				getDiagramPanel().add(testCaseInstantiationTabbedPanel);

				ChangeAllButtonPanelState();
				testCaseInstantiationButtonPanel.setBackground(new Color(58, 105, 190));
				
				fixButtonTabbedPanelSelectedIndex=2;

				ChangeRepaint();
			}
		});
		
		testCaseShowButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getDiagramPanel().removeAll();
				getDiagramPanel().add(testCaseShowTabbedPanel);

				ChangeAllButtonPanelState();
				testCaseShowButtonPanel.setBackground(new Color(58, 105, 190));
				
				fixButtonTabbedPanelSelectedIndex=3;

				ChangeRepaint();
			}
		});
		
		borderTestCaseShowButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getDiagramPanel().removeAll();
				getDiagramPanel().add(borderTestCaseShowTabbedPanel);

				ChangeAllButtonPanelState();
				borderTestCaseShowButtonPanel.setBackground(new Color(58, 105, 190));
				
				fixButtonTabbedPanelSelectedIndex=4;

				ChangeRepaint();
			}
		});

	}

	protected void ChangeAllButtonPanelState() {
		// TODO Auto-generated method stub
		
		testCaseInstantiationProcessButtonPanel.setBackground(new Color(77, 96, 130));
		testCaseProduceButtonPanel.setBackground(new Color(77, 96, 130));
		testCaseInstantiationButtonPanel.setBackground(new Color(77, 96, 130));
		testCaseShowButtonPanel.setBackground(new Color(77, 96, 130));
		borderTestCaseShowButtonPanel.setBackground(new Color(77, 96, 130));
		
	}
	
	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}
	
	public void initUIPanelData(){
		
		mainFrame.getStepFourCenterTabbedPane().getTestCaseInstantiationProcessTabbedPanel().initUIPanel();
		
		testCaseInstantiationProcessButton.doClick();
		
	}
	
	public JPanel getDiagramPanel() {
		return diagramPanel;
	}

	public void setDiagramPanel(JPanel diagramPanel) {
		this.diagramPanel = diagramPanel;
	}

	public int getFixButtonTabbedPanelSelectedIndex() {
		return fixButtonTabbedPanelSelectedIndex;
	}

	public void setFixButtonTabbedPanelSelectedIndex(int fixButtonTabbedPanelSelectedIndex) {
		this.fixButtonTabbedPanelSelectedIndex = fixButtonTabbedPanelSelectedIndex;
	}

	public List<FixedButtonTabbedPanel> getFixButtonTabbedPanelList() {
		return fixButtonTabbedPanelList;
	}

	public TestCaseInstantiationProcessTabbedPanel getTestCaseInstantiationProcessTabbedPanel() {
		return testCaseInstantiationProcessTabbedPanel;
	}

	public TestCaseProduceTabbedPanel getTestCaseProduceTabbedPanel() {
		return testCaseProduceTabbedPanel;
	}

	public TestCaseInstantiationTabbedPanel getTestCaseInstantiationTabbedPanel() {
		return testCaseInstantiationTabbedPanel;
	}

	public TestCaseShowTabbedPanel getTestCaseShowTabbedPanel() {
		return testCaseShowTabbedPanel;
	}
	
	public TestCaseShowTabbedPanel getBorderTestCaseShowTabbedPanel() {
		return borderTestCaseShowTabbedPanel;
	}

	public FixedButtonTabbedPanel getTestCaseInstantiationProcessButtonPanel() {
		return testCaseInstantiationProcessButtonPanel;
	}

	public FixedButtonTabbedPanel getTestCaseProduceButtonPanel() {
		return testCaseProduceButtonPanel;
	}

	public FixedButtonTabbedPanel getTestCaseInstantiationButtonPanel() {
		return testCaseInstantiationButtonPanel;
	}

	public FixedButtonTabbedPanel getTestCaseShowButtonPanel() {
		return testCaseShowButtonPanel;
	}

	public FixedButtonTabbedPanel getBorderTestCaseShowButtonPanel() {
		return borderTestCaseShowButtonPanel;
	}

	public JButton getTestCaseInstantiationProcessButton() {
		return testCaseInstantiationProcessButton;
	}

	public JButton getTestCaseProduceButton() {
		return testCaseProduceButton;
	}

	public JButton getTestCaseInstantiationButton() {
		return testCaseInstantiationButton;
	}

	public JButton getTestCaseShowButton() {
		return testCaseShowButton;
	}

	public JButton getBorderTestCaseShowButton() {
		return borderTestCaseShowButton;
	}

	public static String getBecomeRunFileName() {
		return BecomeRunFileName;
	}

	public static void setBecomeRunFileName(String becomeRunFileName) {
		BecomeRunFileName = becomeRunFileName;
	}

	public static int getBecomeRunFileNameType() {
		return BecomeRunFileNameType;
	}

	public static void setBecomeRunFileNameType(int becomeRunFileNameType) {
		BecomeRunFileNameType = becomeRunFileNameType;
	}

}

package com.horstmann.violet.application.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import com.horstmann.violet.application.gui.stepCenterTabbedPane.ButtonTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.MoviePanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.ToolPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.colorpicker.ColorPickerDemo;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;

public class StepOneCenterTabbedPane extends JPanel {

	private MainFrame mainFrame;
	private IWorkspace workspace;

	private JPanel sequenceDiagramTabbedPane;
	private JPanel timingDiagramTabbedPane;
	private JPanel stateDiagramTabbedPane;
	private JPanel usecaseDiagramTabbedPane;

	private JPanel buttonPanel;
	public JPanel diagramPanel;
	private JPanel toolPanel;
	private JPanel moviePanel;
	
	private JPanel buttonTabbedPanel;
	private JScrollPane buttonScrollPanel;
	private JButton leftButton;
	private JButton rightButton;

	private static ButtonTabbedPanel sequenceDiagramButtonPanel;
	private static ButtonTabbedPanel timingDiagramButtonPanel;
	private static ButtonTabbedPanel stateDiagramButtonPanel;
	private static ButtonTabbedPanel usecaseDiagramButtonPanel;
	
	private List<ButtonTabbedPanel> sequenceDiagramButtonTabbedPanelLists;
	private List<ButtonTabbedPanel> timingDiagramButtonTabbedPanelLists;
	private List<ButtonTabbedPanel> stateDiagramButtonTabbedPanelLists;
	private List<ButtonTabbedPanel> usecaseDiagramButtonTabbedPanelLists;

	private int selectedIndex = 0;
	
	private ButtonTabbedPanel selectedButtonTabbedPanel;

	/*
	 * 添加4个不同类型图的TabbedPane
	 */
	public StepOneCenterTabbedPane(MainFrame mainFrame) {

		this.mainFrame = mainFrame;

		sequenceDiagramTabbedPane = new JPanel();
		timingDiagramTabbedPane = new JPanel();
		stateDiagramTabbedPane = new JPanel();
		usecaseDiagramTabbedPane = new JPanel();
		sequenceDiagramTabbedPane.setLayout(new GridBagLayout());
		timingDiagramTabbedPane.setLayout(new GridBagLayout());
		stateDiagramTabbedPane.setLayout(new GridBagLayout());
		usecaseDiagramTabbedPane.setLayout(new GridBagLayout());

		buttonPanel = new JPanel();
		diagramPanel = new JPanel();
		
		buttonTabbedPanel=new JPanel();
		
		sequenceDiagramButtonTabbedPanelLists=new ArrayList<ButtonTabbedPanel>();
		timingDiagramButtonTabbedPanelLists=new ArrayList<ButtonTabbedPanel>();
		stateDiagramButtonTabbedPanelLists=new ArrayList<ButtonTabbedPanel>();
		usecaseDiagramButtonTabbedPanelLists=new ArrayList<ButtonTabbedPanel>();
		
		selectedButtonTabbedPanel=null;

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
		
		int sequencecount=mainFrame.getProjectTree().getSequenceTreePanel().getSequencetree().getRowCount();
		int usecasecount=mainFrame.getProjectTree().getUsecaseTreePanel().getUsecasetree().getRowCount();
		int statecount=mainFrame.getProjectTree().getStateTreePanel().getStatetree().getRowCount();
		int timingcount=mainFrame.getProjectTree().getTimingTreePanel().getTimingtree().getRowCount();
		
//		System.out.println("sequencecount "+sequencecount+"  mainFrame.getSequenceWorkspaceList()  "+mainFrame.getSequenceWorkspaceList().size());
//		System.out.println("usecasecount "+usecasecount+" mainFrame.getUseCaseWorkspaceList().size()  "+mainFrame.getUseCaseWorkspaceList().size());
//		System.out.println("statecount "+statecount+"  mainFrame.getStateWorkspaceList().size() "+mainFrame.getStateWorkspaceList().size());
//		System.out.println("timingcount "+timingcount+" mainFrame.getTimingWorkspaceList().size() "+mainFrame.getTimingWorkspaceList().size());
		
		DefaultMutableTreeNode sequenceroot = mainFrame.getProjectTree().getSequenceTreePanel().getSequencetreerootnode();
		DefaultMutableTreeNode usecaseroot = mainFrame.getProjectTree().getUsecaseTreePanel().getUsecasetreerootnode();
		DefaultMutableTreeNode stateroot = mainFrame.getProjectTree().getStateTreePanel().getStatetreerootnode();
		DefaultMutableTreeNode timingroot = mainFrame.getProjectTree().getTimingTreePanel().getTimingtreerootnode();
				
		
		for(int se=0;se<sequencecount-1;se++){
			MutableTreeNode node=sequenceroot.getFirstLeaf();
			mainFrame.getProjectTree().getSequenceTreePanel().getSequencetreemodel().removeNodeFromParent(node);
		}
		mainFrame.getStepOneCenterTabbedPane().getSequenceDiagramButtonTabbedPanelLists().clear();
		mainFrame.getSequenceWorkspaceList().clear();
		
		for(int uc=0;uc<usecasecount-1;uc++){
			MutableTreeNode node=usecaseroot.getFirstLeaf();
			mainFrame.getProjectTree().getUsecaseTreePanel().getUsecasetreemodel().removeNodeFromParent(node);
		}
		mainFrame.getStepOneCenterTabbedPane().getUsecaseDiagramButtonTabbedPanelLists().clear();
		mainFrame.getUseCaseWorkspaceList().clear();
		
		for(int st=0;st<statecount-1;st++){
			MutableTreeNode node=stateroot.getFirstLeaf();
			mainFrame.getProjectTree().getStateTreePanel().getStatetreemodel().removeNodeFromParent(node);
		}
		mainFrame.getStepOneCenterTabbedPane().getStateDiagramButtonTabbedPanelLists().clear();
		mainFrame.getStateWorkspaceList().clear();
		
		for(int ti=0;ti<timingcount-1;ti++){
			MutableTreeNode node=timingroot.getFirstLeaf();
			mainFrame.getProjectTree().getTimingTreePanel().getTimingtreemodel().removeNodeFromParent(node);
		}
		mainFrame.getStepOneCenterTabbedPane().getTimingDiagramButtonTabbedPanelLists().clear();
		mainFrame.getTimingWorkspaceList().clear();
		
		mainFrame.getStepOneCenterTabbedPane().getButtonTabbedPanel().removeAll();
		mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().removeAll();
		
		mainFrame.getAttributePartOnePanel().getNamelabel().setText("");
		mainFrame.getAttributePartOnePanel().getAttributepanel().removeAll();
		
		mainFrame.getConsolePartPanel().getTextarea1().setText("");
		
	}

	private void initdiagrampanel() {
		// TODO Auto-generated method stub

		// diagramPanel.setLayout(new GridLayout());
		diagramPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(142, 155, 188)));

	}

	private void initbuttonpanel() {
		// TODO Auto-generated method stub

		initleftrightbuttonpanel();
		
		sequenceDiagramButtonPanel=new ButtonTabbedPanel(mainFrame,workspace,0,"SE顺序图");
		timingDiagramButtonPanel=new ButtonTabbedPanel(mainFrame,workspace,0,"TI时序图");
		stateDiagramButtonPanel=new ButtonTabbedPanel(mainFrame,workspace,0,"ST状态图");
		usecaseDiagramButtonPanel=new ButtonTabbedPanel(mainFrame,workspace,0,"UC用例图");
		
		setButtonActionListener();

		buttonTabbedPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		buttonTabbedPanel.setBackground(new Color(41, 57, 85));
		
		buttonScrollPanel=new JScrollPane(buttonTabbedPanel);
		buttonScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		buttonScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		buttonScrollPanel.setBorder(null);

		buttonPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(58, 105, 190)));
		buttonPanel.setBackground(new Color(41, 57, 85));
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.add(leftButton, BorderLayout.WEST);
		buttonPanel.add(buttonScrollPanel, BorderLayout.CENTER);
		buttonPanel.add(rightButton, BorderLayout.EAST);
		
//		buttonPanel.add(sequenceDiagramButtonPanel);
//		buttonPanel.add(timingDiagramButtonPanel);
//		buttonPanel.add(stateDiagramButtonPanel);
//		buttonPanel.add(usecaseDiagramButtonPanel);
		
//		sequenceDiagramButtonTabbedPanelLists.add(0,sequenceDiagramButtonPanel);
//		timingDiagramButtonTabbedPanelLists.add(0, timingDiagramButtonPanel);
//		stateDiagramButtonTabbedPanelLists.add(0, stateDiagramButtonPanel);
//		usecaseDiagramButtonTabbedPanelLists.add(0, usecaseDiagramButtonPanel);

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
//		sequenceDiagramButtonPanel.getTabbedbutton().addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				selectedIndex = 0;
//				getDiagramPanel().removeAll();
//
//				if(mainFrame.getActiveWorkspace()!=null){
//					ToolPanel toolPanel = new ToolPanel(mainFrame,mainFrame.getActiveWorkspace().getAWTComponent().getWorkspace());
//
//					System.out.println(mainFrame.getActiveWorkspace().getAWTComponent().getWorkspace().getTitle().toString());
//					
//					MoviePanel moviePanel = new MoviePanel();
//
//					GridBagLayout layout = new GridBagLayout();
//					getDiagramPanel().setLayout(layout);
//					getDiagramPanel().add(toolPanel);
//					getDiagramPanel().add(moviePanel);
//					getDiagramPanel().add(getSequenceDiagramTabbedPane());
//					layout.setConstraints(toolPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//					layout.setConstraints(moviePanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//					layout.setConstraints(getSequenceDiagramTabbedPane(),
//							new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//				}
//				
//				ChangeRepaint();
//			}
//		});
		timingDiagramButtonPanel.getTabbedbutton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selectedIndex = 1;
				getDiagramPanel().removeAll();

				if(mainFrame.getActiveWorkspace()==null){
				}
				else {
					ToolPanel toolPanel = new ToolPanel(mainFrame,mainFrame.getActiveWorkspace().getAWTComponent().getWorkspace());

					MoviePanel moviePanel = new MoviePanel();

					GridBagLayout layout = new GridBagLayout();
					getDiagramPanel().setLayout(layout);
					getDiagramPanel().add(toolPanel);
					getDiagramPanel().add(moviePanel);
					getDiagramPanel().add(getTimingDiagramTabbedPane());
					layout.setConstraints(toolPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
					layout.setConstraints(moviePanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
					layout.setConstraints(getTimingDiagramTabbedPane(),
							new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
				}
				
				

				ChangeRepaint();
			}
		});
		stateDiagramButtonPanel.getTabbedbutton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selectedIndex = 2;
				getDiagramPanel().removeAll();
				
				ToolPanel toolPanel = new ToolPanel(mainFrame,mainFrame.getActiveWorkspace().getAWTComponent().getWorkspace());

				MoviePanel moviePanel = new MoviePanel();

				GridBagLayout layout = new GridBagLayout();
				getDiagramPanel().setLayout(layout);
				getDiagramPanel().add(toolPanel);
				getDiagramPanel().add(moviePanel);
				getDiagramPanel().add(getStateDiagramTabbedPane());
				layout.setConstraints(toolPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
				layout.setConstraints(moviePanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
				layout.setConstraints(getStateDiagramTabbedPane(),
						new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));

				ChangeRepaint();
			}
		});
		usecaseDiagramButtonPanel.getTabbedbutton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selectedIndex = 3;
				getDiagramPanel().removeAll();

				ToolPanel toolPanel = new ToolPanel(mainFrame,mainFrame.getActiveWorkspace().getAWTComponent().getWorkspace());

				MoviePanel moviePanel = new MoviePanel();

				GridBagLayout layout = new GridBagLayout();
				getDiagramPanel().setLayout(layout);
				getDiagramPanel().add(toolPanel);
				getDiagramPanel().add(moviePanel);
				getDiagramPanel().add(getUsecaseDiagramTabbedPane());
				layout.setConstraints(toolPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
				layout.setConstraints(moviePanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
				layout.setConstraints(getUsecaseDiagramTabbedPane(),
						new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
				
				ChangeRepaint();
			}
		});
	}

	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}

	/*
	 * 通过传递Iworkspace参数来确定是在哪个TabbedPane下面添加图形
	 */
    public JPanel getUMLTabbedPane(IWorkspace workspace)
    {
    	//这里分两种情况
    	//1、新建:判断是不是类似于N.XXX类型
    	//2、导入:判断文件名后缀是不是.XXX.violet.xml
       if(workspace.getTitle().toString().endsWith(".seq.violet.xml")
    		   ||workspace.getTitle().toString().substring(2,4).equals("Se"))
    	{
    		return this.getSequenceDiagramTabbedPane();
        }
    	if(workspace.getTitle().toString().endsWith(".ucase.violet.xml")
    			||workspace.getTitle().toString().substring(2,4).equals("Us"))
		{
	        return this.getUsecaseDiagramTabbedPane();
		}
    	if(workspace.getTitle().toString().endsWith(".timing.violet.xml")
    ||workspace.getTitle().toString().substring(2,4).equals("Ti"))
		{
	        return this.getTimingDiagramTabbedPane();
		}
    	if(workspace.getTitle().toString().endsWith(".state.violet.xml")
    			||workspace.getTitle().toString().substring(2,4).equals("St"))
		{
	        return this.getStateDiagramTabbedPane();
		}
    	    return null;
    }
	public JPanel getSequenceDiagramTabbedPane() {
		return sequenceDiagramTabbedPane;
	}
	public void setSequenceDiagramTabbedPane(JPanel sequenceDiagramTabbedPane) {
		this.sequenceDiagramTabbedPane = sequenceDiagramTabbedPane;
	}
	public JPanel getTimingDiagramTabbedPane() {
		return timingDiagramTabbedPane;
	}
	public JPanel getStateDiagramTabbedPane() {
		return stateDiagramTabbedPane;
	}	
	public JPanel getUsecaseDiagramTabbedPane() {
		return usecaseDiagramTabbedPane;
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	public JPanel getDiagramPanel() {
		return diagramPanel;
	}

	public void setDiagramPanel(JPanel diagramPanel) {
		this.diagramPanel = diagramPanel;
	}

//	public static JButton getSequenceDiagramButton() {
//		return sequenceDiagramButtonPanel.getTabbedbutton();
//	}

	public static JButton getTimingDiagramButton() {
		return timingDiagramButtonPanel.getTabbedbutton();
	}

	public static JButton getStateDiagramButton() {
		return stateDiagramButtonPanel.getTabbedbutton();
	}

	public static JButton getUsecaseDiagramButton() {
		return usecaseDiagramButtonPanel.getTabbedbutton();
	}
	
	public JPanel getButtonTabbedPanel() {
		return buttonTabbedPanel;
	}

	public void setButtonTabbedPanel(JPanel buttonTabbedPanel) {
		this.buttonTabbedPanel = buttonTabbedPanel;
	}

//	public JPanel getButtonPanel() {
//		return buttonPanel;
//	}
//
//	public void setButtonPanel(JPanel buttonPanel) {
//		this.buttonPanel = buttonPanel;
//	}

	public ButtonTabbedPanel getSelectedButtonTabbedPanel() {
		return selectedButtonTabbedPanel;
	}

	public void setSelectedButtonTabbedPanel(ButtonTabbedPanel selectedButtonTabbedPanel) {
		this.selectedButtonTabbedPanel = selectedButtonTabbedPanel;
	}

	public List<ButtonTabbedPanel> getSequenceDiagramButtonTabbedPanelLists() {
		return sequenceDiagramButtonTabbedPanelLists;
	}

	public void setSequenceDiagramButtonTabbedPanelLists(List<ButtonTabbedPanel> sequenceDiagramButtonTabbedPanelLists) {
		this.sequenceDiagramButtonTabbedPanelLists = sequenceDiagramButtonTabbedPanelLists;
	}

	public List<ButtonTabbedPanel> getTimingDiagramButtonTabbedPanelLists() {
		return timingDiagramButtonTabbedPanelLists;
	}

	public void setTimingDiagramButtonTabbedPanelLists(List<ButtonTabbedPanel> timingDiagramButtonTabbedPanelLists) {
		this.timingDiagramButtonTabbedPanelLists = timingDiagramButtonTabbedPanelLists;
	}

	public List<ButtonTabbedPanel> getStateDiagramButtonTabbedPanelLists() {
		return stateDiagramButtonTabbedPanelLists;
	}

	public void setStateDiagramButtonTabbedPanelLists(List<ButtonTabbedPanel> stateDiagramButtonTabbedPanelLists) {
		this.stateDiagramButtonTabbedPanelLists = stateDiagramButtonTabbedPanelLists;
	}

	public List<ButtonTabbedPanel> getUsecaseDiagramButtonTabbedPanelLists() {
		return usecaseDiagramButtonTabbedPanelLists;
	}

	public void setUsecaseDiagramButtonTabbedPanelLists(List<ButtonTabbedPanel> usecaseDiagramButtonTabbedPanelLists) {
		this.usecaseDiagramButtonTabbedPanelLists = usecaseDiagramButtonTabbedPanelLists;
	}

	public JScrollPane getButtonScrollPanel() {
		return buttonScrollPanel;
	}
	
	
	


}

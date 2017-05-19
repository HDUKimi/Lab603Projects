package com.horstmann.violet.application.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import com.horstmann.violet.application.gui.stepCenterTabbedPane.ButtonTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.FixedButtonTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.SequenceToUppaalTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.TimingToUppaalTabbedPanel;
import com.horstmann.violet.framework.theme.ThemeManager;
import com.horstmann.violet.workspace.editorpart.IEditorPart;

public class StepTwoCenterTabbedPane extends JPanel{

	private MainFrame mainFrame;
	
	private JPanel uppaalDiagramTabbedPane;
	

	private JPanel buttonPanel;
	public JPanel diagramPanel;
	
	private JButton sequenceToUppaalDiagramButton;
	private FixedButtonTabbedPanel sequenceToUppaalDiagramButtonPanel;
	private SequenceToUppaalTabbedPanel sequenceToUppaalTabbedPane;
	
	private JButton timingToUppaalDiagramButton;
	private FixedButtonTabbedPanel timingToUppaalDiagramButtonPanel;
	private TimingToUppaalTabbedPanel timingToUppaalTabbedPane;
	
//	private List<ButtonTabbedPanel> uppaalDiagramButtonTabbedPanelLists;
	private List<ButtonTabbedPanel> sequenceToUppaalDiagramButtonTabbedPanelLists;
	private List<ButtonTabbedPanel> timingToUppaalDiagramButtonTabbedPanelLists;
	
	private int selectedIndex=1;
	
	private ButtonTabbedPanel selectedButtonTabbedPanel;
	
	public StepTwoCenterTabbedPane(MainFrame mainFrame)
	{
		
		this.mainFrame = mainFrame;
		
		sequenceToUppaalTabbedPane=new SequenceToUppaalTabbedPanel(mainFrame);
		timingToUppaalTabbedPane=new TimingToUppaalTabbedPanel(mainFrame);
		
		uppaalDiagramTabbedPane=new JPanel();
		uppaalDiagramTabbedPane.setLayout(new GridBagLayout());
		
		sequenceToUppaalDiagramButtonTabbedPanelLists=new ArrayList<ButtonTabbedPanel>(); 
		timingToUppaalDiagramButtonTabbedPanelLists=new ArrayList<ButtonTabbedPanel>();
		
		selectedButtonTabbedPanel=null;
		
		buttonPanel=new JPanel();
		diagramPanel=new JPanel();
		
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		buttonPanel.setBackground(new Color(41, 57, 85));
		
		initbuttonpanel();
		initdiagrampanel();
		
//		emptylabel=new JLabel();
//		emptylabel.setOpaque(false);
//		buttonPanel.add(emptylabel);
//		buttonPanel.add(emptylabel);
		
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
		
//		this.add("UML模型解析",UMLDiagramTabbedPane);
//		this.add("自动机解析",uppaalDiagramTabbedPane);
//		this.add("MarKov链",MarkovDiagramTabbedPane);
			
	}
  
	private void initdiagrampanel() {
		// TODO Auto-generated method stub

		diagramPanel.setLayout(new GridLayout());
		diagramPanel.add(sequenceToUppaalTabbedPane);
		diagramPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(142, 155, 188)));

	}
	
	private void initbuttonpanel() {
		// TODO Auto-generated method stub
		
		sequenceToUppaalDiagramButtonPanel=new FixedButtonTabbedPanel("顺序图转时间自动机");
		sequenceToUppaalDiagramButtonPanel.setBackground(new Color(58, 105, 190));
		sequenceToUppaalDiagramButton=sequenceToUppaalDiagramButtonPanel.getTabbedbutton();
		
		timingToUppaalDiagramButtonPanel=new FixedButtonTabbedPanel("时序图转时间自动机");
		timingToUppaalDiagramButtonPanel.setBackground(new Color(77, 96, 130));
		timingToUppaalDiagramButton=timingToUppaalDiagramButtonPanel.getTabbedbutton();
		
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		buttonPanel.setBackground(new Color(41, 57, 85));
		buttonPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(58, 105, 190)));
		
		buttonPanel.add(sequenceToUppaalDiagramButtonPanel);
		buttonPanel.add(timingToUppaalDiagramButtonPanel);
		
		setButtonActionListener();
		
		timingToUppaalDiagramButtonPanel.setVisible(false);
		
	}

	private void setButtonActionListener() {
		// TODO Auto-generated method stub
		sequenceToUppaalDiagramButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selectedIndex=1;
				getDiagramPanel().removeAll();
				getDiagramPanel().setLayout(new GridLayout());
				getDiagramPanel().add(sequenceToUppaalTabbedPane);
				
				for(JPanel jp:mainFrame.getStepTwoCenterTabbedPane().getSequenceToUppaalDiagramButtonTabbedPanelLists()){
					jp.setBackground(new Color(77, 96, 130));
				}
				for(JPanel jp:mainFrame.getStepTwoCenterTabbedPane().getTimingToUppaalDiagramButtonTabbedPanelLists()){
					jp.setBackground(new Color(77, 96, 130));
				}
				sequenceToUppaalDiagramButtonPanel.setBackground(new Color(58, 105, 190));
				timingToUppaalDiagramButtonPanel.setBackground(new Color(77, 96, 130));
				
				System.out.println("mainFrame.getModelTransformationPanel().getIndex(): "+mainFrame.getModelTransformationPanel().getIndex());
				
				if(mainFrame.getModelTransformationPanel().getIndex()!=1){
					mainFrame.getModelTransformationPanel().getDiagrambutton1().doClick();
				}
				
				System.out.println("mainFrame.getModelTransformationPanel().getIndex(): "+mainFrame.getModelTransformationPanel().getIndex());
				
				ChangeRepaint();
			}
		});
		
		timingToUppaalDiagramButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selectedIndex=2;
				getDiagramPanel().removeAll();
				getDiagramPanel().setLayout(new GridLayout());
				getDiagramPanel().add(timingToUppaalTabbedPane);
				
				for(JPanel jp:mainFrame.getStepTwoCenterTabbedPane().getSequenceToUppaalDiagramButtonTabbedPanelLists()){
					jp.setBackground(new Color(77, 96, 130));
				}
				for(JPanel jp:mainFrame.getStepTwoCenterTabbedPane().getTimingToUppaalDiagramButtonTabbedPanelLists()){
					jp.setBackground(new Color(77, 96, 130));
				}
				timingToUppaalDiagramButtonPanel.setBackground(new Color(58, 105, 190));
				sequenceToUppaalDiagramButtonPanel.setBackground(new Color(77, 96, 130));
				
				System.out.println("mainFrame.getModelTransformationPanel().getIndex(): "+mainFrame.getModelTransformationPanel().getIndex());
				
				if(mainFrame.getModelTransformationPanel().getIndex()!=2){
					mainFrame.getModelTransformationPanel().getDiagrambutton2().doClick();
				}
				
				
				System.out.println("mainFrame.getModelTransformationPanel().getIndex(): "+mainFrame.getModelTransformationPanel().getIndex());
				
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

	public JPanel getUppaalDiagramTabbedPane() {
		return uppaalDiagramTabbedPane;
	}
	public void setUppaalDiagramTabbedPane(JPanel uppaalDiagramTabbedPane) {
		uppaalDiagramTabbedPane = uppaalDiagramTabbedPane;
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

	public JButton getUmlDiagramButton() {
		return sequenceToUppaalDiagramButton;
	}

	public ButtonTabbedPanel getSelectedButtonTabbedPanel() {
		return selectedButtonTabbedPanel;
	}

	public void setSelectedButtonTabbedPanel(ButtonTabbedPanel selectedButtonTabbedPanel) {
		this.selectedButtonTabbedPanel = selectedButtonTabbedPanel;
	}
	
	public List<ButtonTabbedPanel> getSequenceToUppaalDiagramButtonTabbedPanelLists() {
		return sequenceToUppaalDiagramButtonTabbedPanelLists;
	}
	
	public List<ButtonTabbedPanel> getTimingToUppaalDiagramButtonTabbedPanelLists() {
		return timingToUppaalDiagramButtonTabbedPanelLists;
	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public JPanel getSequenceToUppaalDiagramButtonPanel() {
		return sequenceToUppaalDiagramButtonPanel;
	}

	public JButton getSequenceToUppaalDiagramButton() {
		return sequenceToUppaalDiagramButton;
	}

	public SequenceToUppaalTabbedPanel getSequenceToUppaalTabbedPane() {
		return sequenceToUppaalTabbedPane;
	}
	
	public JPanel getTimingToUppaalDiagramButtonPanel() {
		return timingToUppaalDiagramButtonPanel;
	}

	public JButton getTimingToUppaalDiagramButton() {
		return timingToUppaalDiagramButton;
	}

	public TimingToUppaalTabbedPanel getTimingToUppaalTabbedPane() {
		return timingToUppaalTabbedPane;
	}

	
	
}

package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.workspace.IWorkspace;

public class TestCaseUppaalTabbedPanel extends JPanel{

	private MainFrame mainFrame;
	private IWorkspace workspace;
	
	private UppaalToolPanel toolPanel;
	private MoviePanel moviePanel;
	private JPanel diagramPanel;
	
	
	public TestCaseUppaalTabbedPanel(MainFrame mainFrame, IWorkspace workspace) {
		// TODO Auto-generated constructor stub
		
		this.mainFrame=mainFrame;
		this.workspace=workspace;
		
		init();
		
		this.setBackground(new Color(255, 255, 255));
		
	}


	private void init() {
		// TODO Auto-generated method stub
		
		toolPanel=new UppaalToolPanel(mainFrame, workspace);
		moviePanel=new MoviePanel();
		diagramPanel=new JPanel();
		
		moviePanel.getMovieLabel().setText("正在生成时间机路径树");
		
		initDiagramPanel();
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(toolPanel);
		this.add(moviePanel);
		this.add(diagramPanel);
		layout.setConstraints(toolPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(moviePanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(diagramPanel,new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		
	}


	private void initDiagramPanel() {
		// TODO Auto-generated method stub
		
		diagramPanel.setLayout(new GridLayout());
		if(workspace!=null){
			diagramPanel.add(workspace.getAWTComponent());
		}
		
		
	}

}

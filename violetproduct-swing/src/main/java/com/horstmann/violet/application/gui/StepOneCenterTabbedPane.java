package com.horstmann.violet.application.gui;

import java.io.SequenceInputStream;

import javax.swing.JTabbedPane;

import com.horstmann.violet.workspace.IWorkspace;

public class StepOneCenterTabbedPane extends JTabbedPane{
	private JTabbedPane sequenceDiagramTabbedPane;
	private JTabbedPane timingDiagramTabbedPane;
	private JTabbedPane stateDiagramTabbedPane;
	private JTabbedPane usecaseDiagramTabbedPane;
	public StepOneCenterTabbedPane()
	{
		sequenceDiagramTabbedPane=new JTabbedPane();
		timingDiagramTabbedPane=new JTabbedPane();
		stateDiagramTabbedPane=new JTabbedPane();
		usecaseDiagramTabbedPane=new JTabbedPane();
		this.add("˳��ͼ",sequenceDiagramTabbedPane);
		this.add("ʱ��ͼ",timingDiagramTabbedPane);
		this.add("״̬ͼ",stateDiagramTabbedPane);
		this.add("����ͼ",usecaseDiagramTabbedPane);
		
	}
    public JTabbedPane getUMLTabbedPane(IWorkspace workspace)
    {
    	
       if(workspace.getTitle().toString().substring(2, 4).
    			equals("Se"))
    	{
    		return this.getSequenceDiagramTabbedPane();
        }
    	if(workspace.getTitle().toString().substring(2, 4).
    			equals("Us"))
		{
	        return this.getUsecaseDiagramTabbedPane();
		}
    	if(workspace.getTitle().toString().substring(2, 4).
    			equals("Ti"))
		{
	        return this.getTimingDiagramTabbedPane();
		}
    	if(workspace.getTitle().toString().substring(2, 4).
    			equals("St"))
		{
	        return this.getStateDiagramTabbedPane();
		}
    	    return null;
    }
	public JTabbedPane getSequenceDiagramTabbedPane() {
		return sequenceDiagramTabbedPane;
	}
	public void setSequenceDiagramTabbedPane(JTabbedPane sequenceDiagramTabbedPane) {
		this.sequenceDiagramTabbedPane = sequenceDiagramTabbedPane;
	}
	public JTabbedPane getTimingDiagramTabbedPane() {
		return timingDiagramTabbedPane;
	}
	public JTabbedPane getStateDiagramTabbedPane() {
		return stateDiagramTabbedPane;
	}	
	public JTabbedPane getUsecaseDiagramTabbedPane() {
		return usecaseDiagramTabbedPane;
	}
	
}

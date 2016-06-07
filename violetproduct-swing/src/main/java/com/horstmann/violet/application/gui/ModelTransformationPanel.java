package com.horstmann.violet.application.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import com.horstmann.violet.application.consolepart.ConsolePartTextArea;
import com.l2fprod.common.swing.JTaskPane;
import com.l2fprod.common.swing.JTaskPaneGroup;

public class ModelTransformationPanel extends JPanel{
	 //ת���������ı���ʾ
		
	private MainFrame mainFrame;
	private JDialog messageDialog;
     
    public DefaultMutableTreeNode allDiagram;
    public DefaultMutableTreeNode timingDiagram;
    public DefaultMutableTreeNode sequenceDiagram;
    public DefaultMutableTreeNode uppaalDiagram;
    public JTree UMLDiagramTree;
	private String[] tdlists;
	public JTree UppaalDiagramTree;
	private String[] sdlists;
	private String[] uppaallists;	     
		
	public ModelTransformationPanel(MainFrame mainFram){	
		this.mainFrame=mainFram;
		initFileList();
		initUI();
		this.setLayout(new GridLayout(2, 1));
		add(UMLDiagramTree);
		add(UppaalDiagramTree);
	}  		   
	    private void initUI() {
			// TODO Auto-generated method stub		 		   
			 
		       allDiagram = new DefaultMutableTreeNode("Ҫת����UMLģ���ļ�");
		       uppaalDiagram=new DefaultMutableTreeNode("���ɵ�ʱ���Զ����ļ�");
		       sequenceDiagram =new DefaultMutableTreeNode("˳��ͼģ���ļ�");
		       timingDiagram=new DefaultMutableTreeNode("ʱ��ͼģ���ļ�");
		       for(String td:tdlists)
		       {
		    	   timingDiagram.add(new DefaultMutableTreeNode(td));
		       }
		       for(String sd:sdlists)
		       {
		    	   sequenceDiagram.add(new DefaultMutableTreeNode(sd));
		       }
		       for(String up :uppaallists){
		    	   uppaalDiagram.add(new DefaultMutableTreeNode(up));
		       }
	           allDiagram.add(sequenceDiagram);
	           allDiagram.add(timingDiagram);
	           UMLDiagramTree=new JTree(allDiagram);
	           UppaalDiagramTree=new JTree(uppaalDiagram);
	           
	    }				            				            					 			
	private void initFileList() {
		sdlists = new String[] {"1.sequencediagram",
				"2.sequencediagram",
				"3.sequencediagram"};
	
		tdlists = new String[] {"1.timingdiagram",
				"2.timingdiagram",
				"3.timingdiagram"};	
	
		uppaallists = new String[] {"1.uppaal","2.uppaal","3.uppaal","4.uppaal"};				
	}

	
	
	}
	



package com.horstmann.violet.application.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import com.l2fprod.common.swing.JTaskPane;
import com.l2fprod.common.swing.JTaskPaneGroup;

public class TestCaseInstantiationPanel extends JPanel{
  
    private JTree TestCaseFilePanel;//��һ�����ɵĲ�������   
    private DefaultMutableTreeNode testcase;
   
     
	public TestCaseInstantiationPanel() {
		// TODO Auto-generated constructor stub			
		initUI();	
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black)
        		,"���������������",TitledBorder.CENTER,TitledBorder.ABOVE_TOP,
        		new Font("����",Font.BOLD,20),new Color(60, 60, 60)));
	}

	private void initUI() {
		// TODO Auto-generated method stub
		initUppaalFilePanel();	 
	    this.setLayout(new GridLayout(1, 1));    
	    this.add(TestCaseFilePanel);	   
	 
	  	    
	}
	
	private void initUppaalFilePanel() {
		// TODO Auto-generated method stub
		testcase=new DefaultMutableTreeNode("������������ļ�");		
		String[] testcaseFileLists={"�����������1","�����������2","�����������3"};//������������ļ��б�
	    for(String testcaseFile : testcaseFileLists)
	    {
	    	testcase.add(new DefaultMutableTreeNode(testcaseFile));
	    }
	    TestCaseFilePanel=new JTree(testcase);
     
	}

}

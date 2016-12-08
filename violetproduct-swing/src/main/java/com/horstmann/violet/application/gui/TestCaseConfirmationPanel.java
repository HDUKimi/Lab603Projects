package com.horstmann.violet.application.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

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

public class TestCaseConfirmationPanel extends JPanel{
  
    private JTree TestCaseFilePanel;//上一步生成的测试用例展示面板     
    private DefaultMutableTreeNode testcase;
	public TestCaseConfirmationPanel() {
		// TODO Auto-generated constructor stub			
		initUI();		
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black)
        		,"验证抽象测试用例",TitledBorder.CENTER,TitledBorder.ABOVE_TOP,
        		new Font("宋体",Font.BOLD,20),new Color(60, 60, 60)));
	}

	private void initUI() {
		// TODO Auto-generated method stub
		initUppaalFilePanel();	 
	    this.setLayout(new GridLayout(1, 1));    
	    this.add(TestCaseFilePanel);	   	 
	}	
	private void initUppaalFilePanel() {
		// TODO Auto-generated method stub			
		String[] testcaseFileLists={"测试用例1","测试用例2","测试用例3"};
		testcase=new DefaultMutableTreeNode("测试用例文件列表");//测试用例文件列表			
		for(String testcaseFile : testcaseFileLists)
		{
			testcase.add(new DefaultMutableTreeNode(testcaseFile));
		}
		TestCaseFilePanel=new JTree(testcase);
	}

}

package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;

public class TestCaseProcessEndPanel extends JPanel{
	
	private MainFrame mainFrame;
	
	private JPanel panel;
	
	private JLabel label;
	
	public TestCaseProcessEndPanel(MainFrame mainFrame){
		
		this.mainFrame = mainFrame;
		
		panel=new JPanel();
		
		initPanel();
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(panel);
		layout.setConstraints(panel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));

		
		this.setBackground(new Color(255, 255, 255));
		
	}

	private void initPanel() {
		// TODO Auto-generated method stub
		
		label=new JLabel();
		label.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		
		label.setText("<html><body>"
				+ "���������ɹ����ɣ��ܺ�ʱ183ms<br>"
				+ "�������̿ɸ���Ϊ������ʱ���Զ���==>����״̬��֣�ȥ������ʱ��Ǩ��==>·������==>���ʵ����Լ������==>ʵ����==>���ɲ�������xml<br>"
				+ "<br>"
				+ "����ʱ���Զ�����ִ�гɹ�����ʱ10ms<br>"
				+ "ʱ���Զ������֣�template_<br>"
				+ "ʱ���Զ���ʱ�Ӽ��ϣ�t<br>"
				+ "ģ���й���119��״̬��220��Ǩ��<br>"
				+ "<br>"
				+ "ȥ������ʱ��Ǩ�ƣ�ִ�гɹ�����ʱ30ms<br>"
				+ "��������20������ʽ��<br>"
				+ "<br>"
				+ "·�����ǣ�ִ�гɹ�����ʱ60ms<br>"
				+ "��������100��������������<br>"
				+ "<br>"
				+ "���ʵ����Լ��������ִ�гɹ�����ʱ50ms<br>"
				+ "��������100��������������<br>"
				+ "<br>"
				+ "ʵ������ִ�гɹ�����ʱ40ms<br>"
				+ "��������100��������������<br>"
				+ "<br>"
				+ "���ɲ�������xml��ִ�гɹ�����ʱ10ms<br>"
				+ "testcase.xml����·����D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\test.xml<br>"
				+ "<br>"
				+ "</html></body>");
		
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.setBackground(new Color(255, 255, 255));
		panel.setLayout(new BorderLayout());
		panel.add(label,BorderLayout.NORTH);
		
	}

}

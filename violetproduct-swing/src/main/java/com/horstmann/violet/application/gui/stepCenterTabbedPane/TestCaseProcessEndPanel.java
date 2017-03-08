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
		label.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		
		label.setText("<html><body>"
				+ "测试用例成功生成，总耗时183ms<br>"
				+ "整个进程可概括为：解析时间自动机==>符号状态拆分，去除抽象时间迁移==>路径覆盖==>添加实例化约束条件==>实例化==>生成测试用例xml<br>"
				+ "<br>"
				+ "解析时间自动机，执行成功，耗时10ms<br>"
				+ "时间自动机名字：template_<br>"
				+ "时间自动机时钟集合：t<br>"
				+ "模型中共有119个状态，220个迁移<br>"
				+ "<br>"
				+ "去除抽象时间迁移，执行成功，耗时30ms<br>"
				+ "共产生了20个不等式组<br>"
				+ "<br>"
				+ "路径覆盖，执行成功，耗时60ms<br>"
				+ "共产生了100个测试用例序列<br>"
				+ "<br>"
				+ "添加实例化约束条件，执行成功，耗时50ms<br>"
				+ "共产生了100个测试用例序列<br>"
				+ "<br>"
				+ "实例化，执行成功，耗时40ms<br>"
				+ "共产生了100个测试用例序列<br>"
				+ "<br>"
				+ "生成测试用例xml，执行成功，耗时10ms<br>"
				+ "testcase.xml保存路径：D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\test.xml<br>"
				+ "<br>"
				+ "</html></body>");
		
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.setBackground(new Color(255, 255, 255));
		panel.setLayout(new BorderLayout());
		panel.add(label,BorderLayout.NORTH);
		
	}

}

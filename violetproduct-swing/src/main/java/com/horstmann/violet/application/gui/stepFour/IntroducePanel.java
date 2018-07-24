package com.horstmann.violet.application.gui.stepFour;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.common.ColorData;

public class IntroducePanel extends JPanel{

	private MainFrame mainFrame;
	
	private JPanel introduceLabelPanel1;
	private JPanel introduceLabelPanel2;

	private JLabel introduceLabel1;
	private JLabel introduceLabel2;
	
	private JPanel emptyPanel;
	
	public IntroducePanel(MainFrame mainFrame) {
		
		this.mainFrame=mainFrame;
		
		initIntroducePanel();
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(introduceLabelPanel1);
		this.add(introduceLabelPanel2);
		this.add(emptyPanel);
		layout.setConstraints(introduceLabelPanel1, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(introduceLabelPanel2, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(emptyPanel, new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));

		
		this.setPreferredSize(new Dimension(300, 300));
		this.setMinimumSize(new Dimension(300, 300));
		this.setBackground(ColorData.white);
		
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, ColorData.gray));
		
	}

	private void initIntroducePanel() {

		introduceLabelPanel1 = new JPanel();
		introduceLabelPanel2 = new JPanel();

		introduceLabel1 = new JLabel();
		introduceLabel2 = new JLabel();

		introduceLabel1.setText("介绍");
		introduceLabel1.setBorder(BorderFactory.createEmptyBorder(30, 35, 0, 0));
		introduceLabel1.setFont(new Font("微软雅黑", Font.PLAIN, 27));

		introduceLabel2.setText("<html><body>" + "<p>第一步：执行测试用例</p><br>" + "<p>第二步：获取执行结果</p><br>"+ "<p>第三步：生成失效数据</p><br>"
				 + "</body></html>");
		introduceLabel2.setBorder(BorderFactory.createEmptyBorder(20, 35, 0, 30));
		introduceLabel2.setFont(new Font("微软雅黑", Font.PLAIN, 15));

		introduceLabelPanel1.add(introduceLabel1);
		introduceLabelPanel1.setLayout(new GridLayout());
		introduceLabelPanel1.setOpaque(false);
		introduceLabelPanel2.add(introduceLabel2);
		introduceLabelPanel2.setLayout(new GridLayout());
		introduceLabelPanel2.setOpaque(false);
		
		emptyPanel=new JPanel();
		emptyPanel.setOpaque(false);

	}
	
}

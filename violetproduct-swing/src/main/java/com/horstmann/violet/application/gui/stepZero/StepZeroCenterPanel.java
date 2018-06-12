package com.horstmann.violet.application.gui.stepZero;

import java.awt.BorderLayout;
import java.awt.Color;
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

public class StepZeroCenterPanel extends JPanel {

	private MainFrame mainFrame;

	private JPanel introducePanel;
	private JPanel imagePanel;

	private JPanel introduceLabelPanel1;
	private JPanel introduceLabelPanel2;

	private JLabel introduceLabel1;
	private JLabel introduceLabel2;
	
	private JPanel emptyPanel;

	public StepZeroCenterPanel(MainFrame mainFrame) {

		this.mainFrame = mainFrame;

		introducePanel = new JPanel();
		imagePanel = new JPanel();

		initIntroducePanel();

		initImagePanel();

		// GridBagLayout layout = new GridBagLayout();
		// this.setLayout(layout);
		// this.add(introducePanel);
		// this.add(imagePanel);
		// layout.setConstraints(introducePanel, new GBC(0, 0, 1,
		// 1).setFill(GBC.BOTH).setWeight(0, 1));
		// layout.setConstraints(imagePanel, new GBC(1, 0, 1,
		// 1).setFill(GBC.BOTH).setWeight(1, 1));

		this.setLayout(new BorderLayout());
		this.add(introducePanel, BorderLayout.WEST);
		this.add(imagePanel, BorderLayout.CENTER);

	}

	private void initIntroducePanel() {

		introduceLabelPanel1 = new JPanel();
		introduceLabelPanel2 = new JPanel();

		introduceLabel1 = new JLabel();
		introduceLabel2 = new JLabel();

		introduceLabel1.setText("系统工作流程");
		introduceLabel1.setBorder(BorderFactory.createEmptyBorder(30, 35, 0, 0));
		introduceLabel1.setFont(new Font("微软雅黑", Font.PLAIN, 27));

		introduceLabel2.setText("<html><body>" + "<p>第一步：构建剖面图</p><br>" + "<p>第二步：测试用例生成</p><br>"
				+ "<p>第三步：收集失效数据</p><br>" + "<p>第四步：可靠性评估</p><br>" + "</body></html>");
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

		introducePanel.setPreferredSize(new Dimension(300, 600));
		introducePanel.setBackground(ColorData.white);
		introducePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, ColorData.gray));

		GridBagLayout layout = new GridBagLayout();
		introducePanel.setLayout(layout);
		introducePanel.add(introduceLabelPanel1);
		introducePanel.add(introduceLabelPanel2);
		introducePanel.add(emptyPanel);
		layout.setConstraints(introduceLabelPanel1, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(introduceLabelPanel2, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(emptyPanel, new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));

	}

	private void initImagePanel() {

		imagePanel.setBackground(ColorData.white);

	}

}

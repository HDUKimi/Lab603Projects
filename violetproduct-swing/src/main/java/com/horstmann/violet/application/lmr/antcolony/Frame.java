package com.horstmann.violet.application.lmr.antcolony;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.horstmann.violet.application.gui.GBC;

/**
 * 界面实现
 */
@SuppressWarnings("serial")
public class Frame extends JFrame {
	JPanel rootPanel;

	JPanel beginPanel;
	JLabel funIdLabel;
	JPanel antPanel;
	JPanel itPanel;
	JPanel funPanel;
	JLabel antLabel;
	JLabel itLabel;
	JLabel funLabel;
	JTextField antTF;
	JTextField itTF;
	JTextField funTF;
	JButton beginBtn;

	JPanel acoPanel;
	JPanel acoChartPanel;
	JLabel betterAntLabel;
	JLabel bestAntLabel;
	JTextArea betterAntTA;
	JTextArea bestAntTA;
	JScrollPane betterAntScroll;
	JScrollPane bestAntScroll;
	
	JTextArea TA_JM;
	JTextArea TA_GO;
	JTextArea TA_Musa;
	JTextArea TA_LV;
	
	JPanel funChartPanel;
	JPanel uChartPanel;
	JPanel yChartPanel;

	JLabel descLabel;

	public Frame() {
		Init();
	}

	/**
	 * 界面初始化函数
	 */
	private void Init() {
		rootPanel = new JPanel();

		initBeginPanel();
		initAcoPanel();

		GridBagLayout layout = new GridBagLayout();
		layout.setConstraints(beginPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 1));
		layout.setConstraints(acoPanel, new GBC(1, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		rootPanel.setLayout(layout);
		rootPanel.add(beginPanel);
		rootPanel.add(acoPanel);

		setBounds(300, 150, 1200, 800);
		setTitle("蚁群算法解决连续性函数问题演示程序");
		setLayout(new GridLayout(1, 1));

		getContentPane().add(rootPanel);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		beginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Begin();
				} catch (CloneNotSupportedException e1) {
					e1.printStackTrace();
				}
			}
		});
		setVisible(true);
	}

	private void initAcoPanel() {

		acoPanel = new JPanel();

		acoChartPanel = new JPanel();
		acoChartPanel.setLayout(new GridLayout(1, 1));
		
		funChartPanel=new JPanel();
		funChartPanel.setLayout(new GridLayout(1, 1));
		
		uChartPanel=new JPanel();
		uChartPanel.setLayout(new GridLayout(1, 1));
		
		yChartPanel=new JPanel();
		yChartPanel.setLayout(new GridLayout(1, 1));
		
		betterAntLabel = new JLabel("每一次迭代的局部较优和全局最优");
		bestAntLabel = new JLabel("结果");
		betterAntTA = new JTextArea();
		bestAntTA = new JTextArea();
		betterAntScroll = new JScrollPane(betterAntTA);
//		bestAntScroll = new JScrollPane(bestAntTA);
		
		
		TA_JM=new JTextArea();
		TA_GO=new JTextArea();
		TA_Musa=new JTextArea();
		TA_LV=new JTextArea();
		
		JPanel TA_Panel=new JPanel();
		TA_Panel.setLayout(new GridLayout(1, 4));
		TA_Panel.add(TA_JM);
		TA_Panel.add(TA_GO);
		TA_Panel.add(TA_Musa);
		TA_Panel.add(TA_LV);
		
		bestAntScroll = new JScrollPane(TA_Panel);
		

		GridBagLayout layout = new GridBagLayout();
		layout.setConstraints(betterAntLabel, new GBC(0, 0, 3, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(acoChartPanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(funChartPanel, new GBC(1, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		layout.setConstraints(uChartPanel, new GBC(1, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		layout.setConstraints(yChartPanel, new GBC(2, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		layout.setConstraints(betterAntScroll, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(bestAntLabel, new GBC(0, 2, 3, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(bestAntScroll, new GBC(0, 3, 3, 1).setFill(GBC.BOTH).setWeight(1, 1));
		acoPanel.setLayout(layout);
		acoPanel.add(betterAntLabel);
		acoPanel.add(acoChartPanel);
		acoPanel.add(funChartPanel);
//		acoPanel.add(uChartPanel);
//		acoPanel.add(yChartPanel);
//		acoPanel.add(betterAntScroll);
		acoPanel.add(bestAntLabel);
		acoPanel.add(bestAntScroll);
		
//		acoChartPanel.setVisible(false);

	}

	private void initBeginPanel() {

		beginPanel = new JPanel();
		antPanel = new JPanel();
		itPanel = new JPanel();
		funPanel = new JPanel();

//		funIdLabel = new JLabel(
//				"<html><body>"
//				+ "样本1：10个城市，最优结果293<br>"
//				+ "样本2：30个城市，最优结果423.741<br>"
//				+ "样本3：50个城市，最优结果427.96<br>"
//				+ "样本4：75个城市，最优结果542.31<br>"
//				+ "</body></html>");
		funIdLabel = new JLabel();
		antLabel = new JLabel("请输入蚂蚁数量:");
		itLabel = new JLabel("请输入迭代次数:");
		funLabel = new JLabel("请输入函数样本编号:");

		antTF = new JTextField(Common.N_ANT_COUNT + "");
		itTF = new JTextField(Common.N_IT_COUNT + "");
		funTF = new JTextField(Common.N_FUN_ID + "");
		
		beginBtn = new JButton("开始运行");

		antPanel.setLayout(new GridLayout(2, 1));
		antPanel.add(antLabel);
		antPanel.add(antTF);

		itPanel.setLayout(new GridLayout(2, 1));
		itPanel.add(itLabel);
		itPanel.add(itTF);

		funPanel.setLayout(new GridLayout(2, 1));
		funPanel.add(funLabel);
		funPanel.add(funTF);

		GridBagLayout layout = new GridBagLayout();
		layout.setConstraints(funIdLabel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(antPanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(funPanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(itPanel, new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(beginBtn, new GBC(0, 4, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		beginPanel.setLayout(layout);
		beginPanel.add(funIdLabel);
		beginPanel.add(antPanel);
		beginPanel.add(funPanel);
		beginPanel.add(itPanel);
		beginPanel.add(beginBtn);

	}

	/**
	 * 开始运算
	 */
	public void Begin() throws CloneNotSupportedException {
		betterAntTA.setText("");
		bestAntTA.setText("");
		String antCountStr = antTF.getText().replaceAll("[^\\d]", "");// 去除所有非数字字符
		String itCountStr = itTF.getText().replaceAll("[^\\d]", "");
		String funIdStr = funTF.getText().replaceAll("[^\\d]", "");

		if (!antCountStr.equals("") && !itCountStr.equals("") && !funIdStr.equals(""))// 不为"";
		{
			Common.N_FUN_ID=Integer.parseInt(funIdStr);
		}

		// 开始执行
		AcoMainFun fun = new AcoMainFun();
		fun.InitData();

	}

}

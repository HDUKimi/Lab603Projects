package com.horstmann.violet.application.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class StepButtonPanel extends JPanel {

	private JPanel homebuttonpanel;
	private JPanel step1buttonpanel;
	private JPanel step2buttonpanel;
	private JPanel step3buttonpanel;
	private JPanel step4buttonpanel;
	private JPanel step5buttonpanel;
	private JPanel step6buttonpanel;

	private List<JButton> stepButtonGroup;
	private JButton homebutton;
	private JButton step1button;
	private JButton step2button;
	private JButton step3button;
	private JButton step4button;
	private JButton step5button;
	private JButton step6button;
	
	private static int index = 0;
	
	private MainFrame mainFrame;

	public StepButtonPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		init();
	}

	private void init() {

		initButton();

		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		this.add(homebuttonpanel);
		this.add(step1buttonpanel);
		this.add(step2buttonpanel);
		this.add(step6buttonpanel);
		this.add(step3buttonpanel);
		this.add(step4buttonpanel);
		this.add(step5buttonpanel);

		SetButtonListener();

	}

	private void initButton() {

		homebuttonpanel = new JPanel();
		step1buttonpanel = new JPanel();
		step2buttonpanel = new JPanel();
		step3buttonpanel = new JPanel();
		step4buttonpanel = new JPanel();
		step5buttonpanel = new JPanel();
		step6buttonpanel = new JPanel();

		homebutton = new JButton();
		step1button = new JButton();
		step2button = new JButton();
		step3button = new JButton();
		step4button = new JButton();
		step5button = new JButton();
		step6button = new JButton();

		String absolutePath = System.getProperty("user.dir");
		String path = absolutePath + "\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon0 = new ImageIcon(this.getClass().getResource("ImagePart/stepbutton0.png"));
		icon0.setImage(icon0.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("ImagePart/stepbutton1.png"));
		icon1.setImage(icon1.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(this.getClass().getResource("ImagePart/stepbutton2.png"));
		icon2.setImage(icon2.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon icon3 = new ImageIcon(this.getClass().getResource("ImagePart/stepbutton3.png"));
		icon3.setImage(icon3.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon icon4 = new ImageIcon(this.getClass().getResource("ImagePart/stepbutton4.png"));
		icon4.setImage(icon4.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon icon5 = new ImageIcon(this.getClass().getResource("ImagePart/stepbutton5.png"));
		icon5.setImage(icon5.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon icon6 = new ImageIcon(this.getClass().getResource("ImagePart/stepbutton1.png"));
		icon6.setImage(icon6.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));

		// step1button.setContentAreaFilled(false);//btn背景透明
		// step1button.setBorderPainted(false);//btn无边框
		// step1button.setFocusPainted(false);//btn无内边框
		homebutton.setIcon(icon0);
		homebutton.setText("首页");
		homebutton.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		homebutton.setForeground(new Color(255, 255, 255));
		homebutton.setHorizontalTextPosition(SwingConstants.CENTER);
		homebutton.setVerticalTextPosition(SwingConstants.BOTTOM);
		homebutton.setMargin(new Insets(0, 0, 0, 0));
		homebutton.setFocusable(false);
		homebutton.setContentAreaFilled(false);
		homebutton.setBorderPainted(false);
		homebutton.setPreferredSize(new Dimension(75, 80));
		homebutton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				homebuttonpanel.setBackground(new Color(53, 55, 59));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (index != 0) {
					homebuttonpanel.setBackground(new Color(71, 80, 93));
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (index != 0) {
					homebuttonpanel.setBackground(new Color(72, 76, 81));
				}
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				setstepbuttonpanelrepaint();
				homebuttonpanel.setBackground(new Color(53, 55, 59));
				index = 0;
				mainFrame.getBottomPanel().setBackground(new Color(104, 33, 122));
				mainFrame.getBottomPanel().getMessagelable().setText("就绪");
			}
		});
		step1button.setIcon(icon1);
		step1button.setText("系统建模");
		step1button.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		step1button.setForeground(new Color(255, 255, 255));
		step1button.setHorizontalTextPosition(SwingConstants.CENTER);
		step1button.setVerticalTextPosition(SwingConstants.BOTTOM);
		step1button.setMargin(new Insets(0, 0, 0, 0));
		step1button.setFocusable(false);// btn里图片focus边框不显示
		step1button.setContentAreaFilled(false);
		step1button.setBorderPainted(false);
		step1button.setPreferredSize(new Dimension(75, 80));
		step1button.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				step1buttonpanel.setBackground(new Color(53, 55, 59));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (index != 1) {
					step1buttonpanel.setBackground(new Color(71, 80, 93));
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (index != 1) {
					step1buttonpanel.setBackground(new Color(72, 76, 81));
				}
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				setstepbuttonpanelrepaint();
				step1buttonpanel.setBackground(new Color(53, 55, 59));
				index = 1;
				mainFrame.getBottomPanel().setBackground(new Color(0, 122, 204));
				mainFrame.getBottomPanel().getMessagelable().setText("全部重新生成");
			}
		});
		step2button.setIcon(icon2);
		step2button.setText("模型转换");
		step2button.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		step2button.setForeground(new Color(255, 255, 255));
		step2button.setHorizontalTextPosition(SwingConstants.CENTER);
		step2button.setVerticalTextPosition(SwingConstants.BOTTOM);
		step2button.setMargin(new Insets(0, 0, 0, 0));
		step2button.setFocusable(false);
		step2button.setContentAreaFilled(false);
		step2button.setBorderPainted(false);
		step2button.setPreferredSize(new Dimension(75, 80));
		step2button.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				step2buttonpanel.setBackground(new Color(53, 55, 59));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (index != 2) {
					step2buttonpanel.setBackground(new Color(71, 80, 93));
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (index != 2) {
					step2buttonpanel.setBackground(new Color(72, 76, 81));
				}
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				setstepbuttonpanelrepaint();
				step2buttonpanel.setBackground(new Color(53, 55, 59));
				index = 2;
				mainFrame.getBottomPanel().setBackground(new Color(0, 122, 204));
				mainFrame.getBottomPanel().getMessagelable().setText("全部重新生成");
			}
		});
		step3button.setIcon(icon3);
		step3button.setText("<html><center>测试用例<br>优先排序</center></html>");
		step3button.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		step3button.setForeground(new Color(255, 255, 255));
		step3button.setHorizontalTextPosition(SwingConstants.CENTER);
		step3button.setVerticalTextPosition(SwingConstants.BOTTOM);
		step3button.setMargin(new Insets(0, 0, 0, 0));
		step3button.setFocusable(false);
		step3button.setContentAreaFilled(false);
		step3button.setBorderPainted(false);
		step3button.setPreferredSize(new Dimension(75, 80));
		step3button.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				step3buttonpanel.setBackground(new Color(53, 55, 59));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (index != 3) {
					step3buttonpanel.setBackground(new Color(71, 80, 93));
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (index != 3) {
					step3buttonpanel.setBackground(new Color(72, 76, 81));
				}
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				setstepbuttonpanelrepaint();
				step3buttonpanel.setBackground(new Color(53, 55, 59));
				index = 3;
				mainFrame.getBottomPanel().setBackground(new Color(0, 122, 204));
				mainFrame.getBottomPanel().getMessagelable().setText("全部重新生成");
			}
		});
		step4button.setIcon(icon4);
		step4button.setText("<html><center>测试用例<br>实例化</center></html>");
		step4button.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		step4button.setForeground(new Color(255, 255, 255));
		step4button.setHorizontalTextPosition(SwingConstants.CENTER);
		step4button.setVerticalTextPosition(SwingConstants.BOTTOM);
		step4button.setMargin(new Insets(0, 0, 0, 0));
		step4button.setFocusable(false);
		step4button.setContentAreaFilled(false);
		step4button.setBorderPainted(false);
		step4button.setPreferredSize(new Dimension(75, 80));
		step4button.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				step4buttonpanel.setBackground(new Color(53, 55, 59));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (index != 4) {
					step4buttonpanel.setBackground(new Color(71, 80, 93));
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (index != 4) {
					step4buttonpanel.setBackground(new Color(72, 76, 81));
				}
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				setstepbuttonpanelrepaint();
				step4buttonpanel.setBackground(new Color(53, 55, 59));
				index = 4;
				mainFrame.getBottomPanel().setBackground(new Color(0, 122, 204));
				mainFrame.getBottomPanel().getMessagelable().setText("全部重新生成");
			}
		});
		step5button.setIcon(icon5);
		step5button.setText("测试执行");
		step5button.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		step5button.setForeground(new Color(255, 255, 255));
		step5button.setHorizontalTextPosition(SwingConstants.CENTER);
		step5button.setVerticalTextPosition(SwingConstants.BOTTOM);
		step5button.setMargin(new Insets(0, 0, 0, 0));
		step5button.setFocusable(false);
		step5button.setContentAreaFilled(false);
		step5button.setBorderPainted(false);
		step5button.setPreferredSize(new Dimension(75, 80));
		step5button.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				step5buttonpanel.setBackground(new Color(53, 55, 59));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (index != 5) {
					step5buttonpanel.setBackground(new Color(71, 80, 93));
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (index != 5) {
					step5buttonpanel.setBackground(new Color(72, 76, 81));
				}
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				setstepbuttonpanelrepaint();
				step5buttonpanel.setBackground(new Color(53, 55, 59));
				index = 5;
				mainFrame.getBottomPanel().setBackground(new Color(0, 122, 204));
				mainFrame.getBottomPanel().getMessagelable().setText("全部重新生成");
			}
		});
		step6button.setIcon(icon6);
		step6button.setText("模型评估");
		step6button.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		step6button.setForeground(new Color(255, 255, 255));
		step6button.setHorizontalTextPosition(SwingConstants.CENTER);
		step6button.setVerticalTextPosition(SwingConstants.BOTTOM);
		step6button.setMargin(new Insets(0, 0, 0, 0));
		step6button.setFocusable(false);
		step6button.setContentAreaFilled(false);
		step6button.setBorderPainted(false);
		step6button.setPreferredSize(new Dimension(75, 80));
		step6button.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				step6buttonpanel.setBackground(new Color(53, 55, 59));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (index != 6) {
					step6buttonpanel.setBackground(new Color(71, 80, 93));
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (index != 6) {
					step6buttonpanel.setBackground(new Color(72, 76, 81));
				}
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				setstepbuttonpanelrepaint();
				step6buttonpanel.setBackground(new Color(53, 55, 59));
				index = 6;
				mainFrame.getBottomPanel().setBackground(new Color(0, 122, 204));
				mainFrame.getBottomPanel().getMessagelable().setText("全部重新生成");
			}
		});

		homebuttonpanel.setLayout(new GridLayout());
		// homebuttonpanel.setBackground(new Color(71,80,93));
		homebuttonpanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		homebuttonpanel.add(homebutton);

		homebuttonpanel.setBackground(new Color(53, 55, 59));
		index = 0;

		step1buttonpanel.setLayout(new GridLayout());
		step1buttonpanel.setBackground(new Color(71, 80, 93));
		step1buttonpanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		step1buttonpanel.add(step1button);
		step2buttonpanel.setLayout(new GridLayout());
		step2buttonpanel.setBackground(new Color(71, 80, 93));
		step2buttonpanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		step2buttonpanel.add(step2button);
		step3buttonpanel.setLayout(new GridLayout());
		step3buttonpanel.setBackground(new Color(71, 80, 93));
		step3buttonpanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		step3buttonpanel.add(step3button);
		step4buttonpanel.setLayout(new GridLayout());
		step4buttonpanel.setBackground(new Color(71, 80, 93));
		step4buttonpanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		step4buttonpanel.add(step4button);
		step5buttonpanel.setLayout(new GridLayout());
		step5buttonpanel.setBackground(new Color(71, 80, 93));
		step5buttonpanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		step5buttonpanel.add(step5button);
		step6buttonpanel.setLayout(new GridLayout());
		step6buttonpanel.setBackground(new Color(71, 80, 93));
		step6buttonpanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		step6buttonpanel.add(step6button);

		stepButtonGroup = new ArrayList<JButton>();
		stepButtonGroup.add(homebutton);
		stepButtonGroup.add(step1button);
		stepButtonGroup.add(step2button);
		stepButtonGroup.add(step3button);
		stepButtonGroup.add(step4button);
		stepButtonGroup.add(step5button);
		stepButtonGroup.add(step6button);
		
		step2button.setEnabled(false);// 初始其他步骤按钮都不可点击
		step3button.setEnabled(false);
		step4button.setEnabled(false);
		step5button.setEnabled(false);
		step6button.setEnabled(false);
	}

	protected void setstepbuttonpanelrepaint() {
		// TODO Auto-generated method stub
		homebuttonpanel.setBackground(new Color(71, 80, 93));
		step1buttonpanel.setBackground(new Color(71, 80, 93));
		step2buttonpanel.setBackground(new Color(71, 80, 93));
		step3buttonpanel.setBackground(new Color(71, 80, 93));
		step4buttonpanel.setBackground(new Color(71, 80, 93));
		step5buttonpanel.setBackground(new Color(71, 80, 93));
		step6buttonpanel.setBackground(new Color(71, 80, 93));
	}

	// 初始化阶段
	public void clearSelection() {
		for (JButton stepButton : stepButtonGroup) {
			stepButton.setForeground(new Color(255, 255, 255));
		}
	}


	// 设置监听器
	private void SetButtonListener() {
		homebutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearSelection();
				// homebutton.setForeground(Color.RED);
				mainFrame.getCenterPanel().removeAll();
				mainFrame.getCenterPanel().add(mainFrame.getHomeAllTabbedPanel());
				mainFrame.setStepindex(0);
				mainFrame.getConsolePartPanel().setVisible(false);
				mainFrame.getOpreationPart().setVisible(false);
				mainFrame.getOneTouchExpandablePanel().setVisible(false);
				mainFrame.ChangeRepaint();
			}
		});
		// TODO Auto-generated method stub
		step1button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				clearSelection();

				mainFrame.getCenterPanel().removeAll();
				mainFrame.getCenterPanel().add(mainFrame.getStepOneCenterPanel());

				mainFrame.setStepindex(1);

				setstepbuttonpanelrepaint();
				step1buttonpanel.setBackground(new Color(53, 55, 59));
				index = 1;
				mainFrame.getBottomPanel().setBackground(new Color(0, 122, 204));
				mainFrame.getBottomPanel().getMessagelable().setText("全部重新生成");

				System.gc();

				wakeupUI();
				mainFrame.ChangeRepaint();
				step2button.setEnabled(true);// 第一步点击之后，第二步可点击
			}
		});
		step2button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearSelection();


				mainFrame.getCenterPanel().removeAll();
				mainFrame.getCenterPanel().add(mainFrame.getJs1());

				mainFrame.setStepindex(2);

				mainFrame.getCenterTabPanel().removeAll();
				mainFrame.getCenterTabPanel().add(mainFrame.getStepTwoCenterTabbedPane());

				ClearOpreationPanel();
				operationPanel.add(mainFrame.getModelTransformationPanel());
				mainFrame.getModelTransformationPanel().getModelSequenceTreePanel().updateFileList();
				mainFrame.getModelTransformationPanel().getModelTimingTreePanel().updateFileList();

				mainFrame.getConsolePartPanel().getTitlelabel().setText("模型转换过程信息");
				mainFrame.getConsolePartPanel().getTextpanel().removeAll();
				mainFrame.getConsolePartPanel().getTextpanel()
						.add(mainFrame.getConsolePartPanel().getTextscrollpanel2());

				ClearAttributePanel();
				attributePanel.add(mainFrame.getAttributePartTwoPanel());

				// mainFrame.getAttributePartPanel().getNamepanel().removeAll();
				// mainFrame.getAttributePartPanel().getNamepanel().add(mainFrame.getAttributePartPanel().getSteptwonamelabel());
				//
				// mainFrame.getAttributePartPanel().getAttributepanel().removeAll();
				// StepTwoScrollTree=new
				// JScrollPane(mainFrame.getAttributePartPanel().getSteptwoattributetree());
				// StepTwoScrollTree.setBorder(null);
				// StepTwoScrollTree.setBackground(new Color(255, 255, 255));
				// mainFrame.getAttributePartPanel().getAttributepanel().add(StepTwoScrollTree);

				index = 2;
				setstepbuttonpanelrepaint();
				step2buttonpanel.setBackground(new Color(53, 55, 59));
				mainFrame.getBottomPanel().setBackground(new Color(0, 122, 204));
				mainFrame.getBottomPanel().getMessagelable().setText("全部重新生成");

				// mainFrame.getStepTwoCenterTabbedPane().initUIPanelData();

				if (StepTwoCenterTabbedPane.isNeedRefresh()) {
					mainFrame.getStepTwoCenterTabbedPane().initUIPanelData();
					StepTwoCenterTabbedPane.setNeedRefresh(false);
				}
				System.gc();

				wakeupUI();
				mainFrame.ChangeRepaint();

				step3button.setEnabled(true);
				step6button.setEnabled(true);
			}
		});
		step3button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearSelection();

				mainFrame.getCenterPanel().removeAll();
				mainFrame.getCenterPanel().add(mainFrame.getJs1());

				mainFrame.setStepindex(3);

				// step3button.setForeground(Color.RED);

				mainFrame.getCenterTabPanel().removeAll();

				mainFrame.getCenterTabPanel().add(mainFrame.getStepThreeCenterTabbedPane());

				// JLabel jLabel=new JLabel();
				// jLabel.setText(step3button.getText());
				// jLabel.setFont(new Font("宋体", Font.BOLD, 20));
				// jLabel.setForeground(Color.white);
				// JPanel labelpanel=mainFrame.getStepJLabel();
				// labelpanel.setLayout(new GridLayout(1, 1));
				// labelpanel.removeAll();
				// labelpanel.add(jLabel,new GBC(0, 0).setWeight(1, 0));
				// labelpanel.add(Threestart);
				// labelpanel.add(Buttonstop);

				ClearOpreationPanel();
				operationPanel.add(mainFrame.getTestCaseGenerationPanel());
				mainFrame.getTestCaseGenerationPanel().updateFileList();

				// clearConsolePart();
				mainFrame.getConsolePartPanel().getTitlelabel().setText("抽象测试用例生成过程信息");
				mainFrame.getConsolePartPanel().getTextpanel().removeAll();
				mainFrame.getConsolePartPanel().getTextpanel()
						.add(mainFrame.getConsolePartPanel().getTextscrollpanel3());

				// ClearAttributePanel();
				// attributePanel.add(mainFrame.getAttributePartPanel());
				//
				// mainFrame.getAttributePartPanel().getNamepanel().removeAll();
				// mainFrame.getAttributePartPanel().getNamepanel().add(mainFrame.getAttributePartPanel().getStepthreenamelabel());
				//
				// mainFrame.getAttributePartPanel().getAttributepanel().removeAll();
				// StepThreeScrollTree=new
				// JScrollPane(mainFrame.getAttributePartPanel().getStepthreeattributetree());
				// StepThreeScrollTree.setBorder(null);
				// StepThreeScrollTree.setBackground(new Color(255, 255, 255));
				// mainFrame.getAttributePartPanel().getAttributepanel().add(StepThreeScrollTree);

				ClearAttributePanel();
				attributePanel.add(mainFrame.getAbstractTestCaseResultPanel());

				if (StepThreeCenterTabbedPane.isNeedRefresh()) {
					mainFrame.getStepThreeCenterTabbedPane().initUIPanelData();
					StepThreeCenterTabbedPane.setNeedRefresh(false);
				}

				index = 3;
				setstepbuttonpanelrepaint();
				step3buttonpanel.setBackground(new Color(53, 55, 59));
				mainFrame.getBottomPanel().setBackground(new Color(0, 122, 204));
				mainFrame.getBottomPanel().getMessagelable().setText("全部重新生成");

				System.gc();

				wakeupUI();
				mainFrame.ChangeRepaint();

				step4button.setEnabled(true);
			}
		});
		step4button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearSelection();

				mainFrame.getCenterPanel().removeAll();
				mainFrame.getCenterPanel().add(mainFrame.getJs1());

				mainFrame.setStepindex(4);

				// step4button.setForeground(Color.RED);
				// mainFrame.getMainPanel().remove(mainFrame.getWelcomePanel());

				JLabel jLabel = new JLabel();
				jLabel.setText(step4button.getText());
				jLabel.setFont(new Font("宋体", Font.BOLD, 20));
				jLabel.setForeground(Color.white);
				JPanel labelpanel = mainFrame.getStepJLabel();
				labelpanel.setLayout(new GridLayout(1, 1));
				labelpanel.removeAll();
				// labelpanel.add(jLabel,new GBC(0, 0).setWeight(1, 0));
				// labelpanel.add(Fourstart);
				// labelpanel.add(Buttonstop);

				ClearOpreationPanel();
				operationPanel.add(mainFrame.getTestCaseInstantiationPanel());
				mainFrame.getTestCaseInstantiationPanel().updateFileList();

				mainFrame.getCenterTabPanel().removeAll();
				mainFrame.getCenterTabPanel().add(mainFrame.getStepFourCenterTabbedPane());

				mainFrame.getConsolePartPanel().getTitlelabel().setText("测试用例实例化过程信息");
				mainFrame.getConsolePartPanel().getTextpanel().removeAll();

				JScrollPane textscrollpanel4 = new JScrollPane(mainFrame.getConsolePartPanel().getTextarea4());
				textscrollpanel4.setBorder(null);

				mainFrame.getConsolePartPanel().getTextpanel().add(textscrollpanel4);

				ClearAttributePanel();
				attributePanel.add(mainFrame.getTestCaseInstantiationResultPanel());

				if (StepFourCenterTabbedPane.isNeedRefresh()) {
					mainFrame.getStepFourCenterTabbedPane().initUIPanelData();
					StepFourCenterTabbedPane.setNeedRefresh(false);
				}

				index = 4;
				setstepbuttonpanelrepaint();
				step4buttonpanel.setBackground(new Color(53, 55, 59));
				mainFrame.getBottomPanel().setBackground(new Color(0, 122, 204));
				mainFrame.getBottomPanel().getMessagelable().setText("全部重新生成");

				System.gc();

				wakeupUI();
				mainFrame.ChangeRepaint();

				step5button.setEnabled(true);
			}
		});
		step5button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearSelection();

				mainFrame.getCenterPanel().removeAll();
				mainFrame.getCenterPanel().add(mainFrame.getJs1());

				mainFrame.setStepindex(5);

				// step5button.setForeground(Color.RED);
				mainFrame.getMainPanel().remove(mainFrame.getWelcomePanel());

				// JLabel jLabel=new JLabel();
				// jLabel.setText(step5button.getText());
				// jLabel.setFont(new Font("宋体", Font.BOLD, 20));
				// jLabel.setForeground(Color.white);
				// JPanel labelpanel=mainFrame.getStepJLabel();
				// labelpanel.setLayout(new GridLayout(1, 1));
				// labelpanel.removeAll();
				// labelpanel.add(jLabel,new GBC(0, 0).setWeight(1, 0));
				// labelpanel.add(Fivestart);
				// labelpanel.add(Buttonstop);

				ClearOpreationPanel();
				operationPanel.add(mainFrame.getTestCaseConfirmationPanel());
				mainFrame.getTestCaseConfirmationPanel().updateFileList();

				mainFrame.getCenterTabPanel().removeAll();
				mainFrame.getCenterTabPanel().add(mainFrame.getStepFiveCenterTabbedPane());
				// clearConsolePart();

				mainFrame.getConsolePartPanel().getTitlelabel().setText("测试用例执行过程信息");
				mainFrame.getConsolePartPanel().getTextpanel().removeAll();
				mainFrame.getConsolePartPanel().getTextpanel()
						.add(mainFrame.getConsolePartPanel().getTextscrollpanel5());

				ClearAttributePanel();
				attributePanel.add(mainFrame.getTestCaseConfirmResultPanel());

				if (StepFiveCenterTabbedPane.isNeedRefresh()) {
					mainFrame.getStepFiveCenterTabbedPane().initUIPanelData();
					StepFiveCenterTabbedPane.setNeedRefresh(false);
				}

				index = 5;
				setstepbuttonpanelrepaint();
				step5buttonpanel.setBackground(new Color(53, 55, 59));
				mainFrame.getBottomPanel().setBackground(new Color(0, 122, 204));
				mainFrame.getBottomPanel().getMessagelable().setText("全部重新生成");

				System.gc();

				wakeupUI();
				mainFrame.ChangeRepaint();
			}
		});
		// 测试按钮的触发事件
		step6button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearSelection();

				mainFrame.getCenterPanel().removeAll();
				mainFrame.getCenterPanel().add(mainFrame.getJs1());

				mainFrame.setStepindex(6);

				// step5button.setForeground(Color.RED);
				mainFrame.getMainPanel().remove(mainFrame.getWelcomePanel());

				// JLabel jLabel=new JLabel();
				// jLabel.setText(step6button.getText());
				// jLabel.setFont(new Font("宋体", Font.BOLD, 20));
				// jLabel.setForeground(Color.white);
				// JPanel labelpanel=mainFrame.getStepJLabel();
				// labelpanel.setLayout(new GridLayout(1, 1));
				// labelpanel.removeAll();
				// labelpanel.add(jLabel,new GBC(0, 0).setWeight(1, 0));
				// labelpanel.add(Sixstart);
				// labelpanel.add(Buttonstop);
				// labelpanel.add(new JButton("暂停"),new GBC(2, 0));

				ClearOpreationPanel();
				operationPanel.add(mainFrame.getModelExistValidationPanel());
				mainFrame.getModelExistValidationPanel().updateFileList();

				mainFrame.getCenterTabPanel().removeAll();
				mainFrame.getCenterTabPanel().add(mainFrame.getStepSixCenterTabbedPane());
				// clearConsolePart();

				mainFrame.getConsolePartPanel().getTitlelabel().setText("模型评估过程信息");
				mainFrame.getConsolePartPanel().getTextpanel().removeAll();
				mainFrame.getConsolePartPanel().getTextpanel()
						.add(mainFrame.getConsolePartPanel().getTextscrollpanel6());

				ClearAttributePanel();
				attributePanel.add(mainFrame.getValidationResultPanel());

				index = 6;
				setstepbuttonpanelrepaint();
				step6buttonpanel.setBackground(new Color(53, 55, 59));
				mainFrame.getBottomPanel().setBackground(new Color(0, 122, 204));
				mainFrame.getBottomPanel().getMessagelable().setText("全部重新生成");

				if (StepSixCenterTabbedPane.isNeedRefresh()) {
					mainFrame.getStepSixCenterTabbedPane().initUIPanelData();
					StepSixCenterTabbedPane.setNeedRefresh(false);
				}

				System.gc();

				wakeupUI();
				mainFrame.ChangeRepaint();

			}
		});
	}
	// protected void StartThread() {
	// // TODO Auto-generated method stub
	//
	// Thread thread1=new Thread(){
	//
	// @Override
	// public void run() {
	// // TODO Auto-generated method stub
	// for(int i=1;i<100;i++){
	//// mainFrame.getStepFourCenterTabbedPane().getTestCaseInstantiationTabbedPanel().getProgressbar().setValue(i);
	// try {
	// sleep(1000);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// }
	//
	// };
	//
	// Thread thread2=new Thread(){
	//
	// @Override
	// public void run() {
	//
	//// List<RealTestCaseVO> list =
	// ConsolePartDataTestDao.getRealTestCaseList();
	//// System.out.println("list.size()"+list.size());
	//
	// // TODO Auto-generated method stub
	//// try {
	// mainFrame.getStepFourCenterTabbedPane().getTestCaseInstantiationTabbedPanel().getTabelscrollpanel().getViewport().add(new
	// ConsolePartDetailInfoTable(1));
	// mainFrame.getStepFourCenterTabbedPane().getTestCaseInstantiationTabbedPanel().getTabelscrollpanel().getViewport().repaint();
	//// sleep(100);
	//// } catch (InterruptedException e) {
	//// // TODO Auto-generated catch block
	//// e.printStackTrace();
	//// }
	// }
	//
	// };
	//
	// thread1.start();
	// thread2.start();
	//
	// }

	/*
	 * 使原来不可见的界面重新可见(除了首页,都需要重新可见,首页已使其他界面不可见)
	 */
	public void wakeupUI() {
		mainFrame.getConsolePartPanel().setVisible(true);
		mainFrame.getOpreationPart().setVisible(true);
		mainFrame.getOneTouchExpandablePanel().setVisible(true);
	}

	// public JButton getButtonstop() {
	// return Buttonstop;
	// }
	//
	// public JButton getTwostart() {
	// return Twostart;
	// }
	//
	// public List<PerformanceTestCaseReportPartPanel> getTestcasereportlist() {
	// return testcasereportlist;
	// }

	// public List<TestCase> extractDataToXml(){
	//
	// int i=1,j=1;
	//
	// List<TestCase> testcaseList = new ArrayList<TestCase>();
	// List<myProcess> processList = new ArrayList<myProcess>();
	//
	// SAXReader reader = new SAXReader();
	//
	// String path="D:\\rc_loopForXStream1.0.1.xml";
	//
	// File file=new File(path);
	//
	// try {
	//
	// Document dom = reader.read(file);
	//
	// Element TCS=dom.getRootElement();
	// List<Element> testcaseElements=TCS.elements("testcase");
	// for(Element testcase:testcaseElements){
	//
	//// System.out.println(i++);
	//
	// List<Element> processElements=testcase.elements("process");
	//
	// for(Element process:processElements){
	//
	//// System.out.println(j++);
	//
	// Element operation=process.element("operation");
	//// System.out.println(operation.getData());
	//
	// Element input=process.element("input");
	//// System.out.println(input.getData());
	//
	// myProcess p = new myProcess();
	// p.setProcessID(j++);
	// p.setProcessName(operation.getData().toString());
	// p.setProcessParam(input.getData().toString());
	//// p.setProcessStatus(processStatus);
	//// p.setProcessExec(processExec);
	//
	// processList.add(p);
	//
	// }
	//
	// j=1;
	//
	// TestCase tc = new TestCase();
	// tc.setTestCaseID(String.valueOf(i++));
	// tc.setProcessList(processList);
	//// tc.setState(state);
	//// tc.setResult(result);
	//
	// testcaseList.add(tc);
	//
	// processList = new ArrayList<myProcess>();
	//
	// }
	//
	// } catch (DocumentException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	//// System.out.println(testcaseList.size());
	////
	//// for(TestCase tc:testcaseList){
	////
	//// System.out.println(tc);
	////
	//// }
	// return testcaseList;
	//
	// }

	// public List<TestCase> extractData() {
	//
	// // 测试用例ID
	// String testCaseID = null;
	// // 测试用例 激励链表
	// List<myProcess> processList = new ArrayList<myProcess>();
	// // 测试用例执行状态
	// String state = null;
	// // 测试用例执行结果
	// String result = null;
	//
	// String process;
	//
	// // 激励ID
	// int processID;
	// // 激励名称
	// String processName;
	// // 激励参数
	// String processParam;
	// // 激励状态
	// String processStatus;
	// // 激励执行情况
	// boolean processExec;
	//
	// int startendstate = 0;
	//
	// List<TestCase> testcaseList = new ArrayList<TestCase>();
	//
	// try {
	//
	//// String encoding = "utf-8";
	// String encoding = "GBK";
	//
	// String filePath="D:\\123.txt";
	//
	// File file = new File(filePath);
	// if (file.isFile() && file.exists()) { // 判断文件是否存在
	// InputStreamReader read = new InputStreamReader(new FileInputStream(file),
	// encoding);// 考虑到编码格式
	// BufferedReader bufferedReader = new BufferedReader(read);
	// String lineTxt = null;
	// while ((lineTxt = bufferedReader.readLine()) != null) {
	//
	// if (startendstate == 1) {
	//
	// processList = new ArrayList<myProcess>();
	// startendstate = 0;
	//
	// }
	//
	// if (lineTxt.substring(0, 8).equals("TestCase")) {
	//
	// testCaseID = lineTxt.substring(lineTxt.indexOf("testCaseID=") + 11,
	// lineTxt.indexOf(","));
	//
	// } else if (lineTxt.substring(1, 10).equals("myProcess")) {
	//
	// process = lineTxt.substring(lineTxt.indexOf("[") + 1,
	// lineTxt.indexOf("]"));
	// processID =
	// Integer.valueOf(process.substring(process.indexOf("processID=") + 10,
	// process.indexOf(", processName=")));
	// processName = process.substring(process.indexOf("processName=") + 12,
	// process.indexOf(", processParam="));
	// processParam = process.substring(process.indexOf("processParam=") + 13,
	// process.indexOf(", processStatus="));
	// processStatus = process.substring(process.indexOf("processStatus=") + 14,
	// process.indexOf(", processExec="));
	// processExec = Boolean
	// .valueOf(process.substring(process.indexOf("processExec="),
	// process.length()));
	//
	// myProcess p = new myProcess();
	// p.setProcessID(processID);
	// p.setProcessName(processName);
	// p.setProcessParam(processParam);
	// p.setProcessStatus(processStatus);
	// p.setProcessExec(processExec);
	//
	// processList.add(p);
	//
	// } else if (lineTxt.substring(0, 2).equals(", ")) {
	//
	// state = lineTxt.substring(lineTxt.indexOf("state=") + 6,
	// lineTxt.indexOf(", result="));
	// result = lineTxt.substring(lineTxt.indexOf("result=") + 7,
	// lineTxt.indexOf(", detail="));
	//
	// } else if (lineTxt.substring(lineTxt.length() - 2,
	// lineTxt.length()).equals("]]")) {
	//
	// TestCase tc = new TestCase();
	// tc.setTestCaseID(testCaseID);
	// tc.setProcessList(processList);
	// tc.setState(state);
	//// tc.setResult(result);
	//
	// testcaseList.add(tc);
	//
	// startendstate = 1;
	//
	// }
	//
	// }
	// read.close();
	// } else {
	// System.out.println("找不到指定的文件");
	// }
	// } catch (Exception e) {
	// System.out.println("读取文件内容出错");
	// e.printStackTrace();
	// }
	//
	//// System.out.println(testcaseList.size());
	////
	//// for (TestCase tc : testcaseList) {
	////
	//// System.out.println(tc.toString());
	////
	//// }
	//
	// return testcaseList;
	//
	// }

	public JButton getStep1button() {
		return step1button;
	}

	public JButton getStep2button() {
		return step2button;
	}

	public static int getIndex() {
		return index;
	}

}

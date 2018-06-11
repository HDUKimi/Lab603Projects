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

	private List<JButton> stepButtonGroup;
	private JButton homebutton;
	private JButton step1button;
	private JButton step2button;
	private JButton step3button;
	private JButton step4button;
	
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
		this.add(step3buttonpanel);
		this.add(step4buttonpanel);

		SetButtonListener();

	}

	private void initButton() {

		homebuttonpanel = new JPanel();
		step1buttonpanel = new JPanel();
		step2buttonpanel = new JPanel();
		step3buttonpanel = new JPanel();
		step4buttonpanel = new JPanel();

		homebutton = new JButton();
		step1button = new JButton();
		step2button = new JButton();
		step3button = new JButton();
		step4button = new JButton();

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

		stepButtonGroup = new ArrayList<JButton>();
		stepButtonGroup.add(homebutton);
		stepButtonGroup.add(step1button);
		stepButtonGroup.add(step2button);
		stepButtonGroup.add(step3button);
		stepButtonGroup.add(step4button);
		
		step2button.setEnabled(false);// 初始其他步骤按钮都不可点击
		step3button.setEnabled(false);
		step4button.setEnabled(false);
	}

	protected void setstepbuttonpanelrepaint() {
		// TODO Auto-generated method stub
		homebuttonpanel.setBackground(new Color(71, 80, 93));
		step1buttonpanel.setBackground(new Color(71, 80, 93));
		step2buttonpanel.setBackground(new Color(71, 80, 93));
		step3buttonpanel.setBackground(new Color(71, 80, 93));
		step4buttonpanel.setBackground(new Color(71, 80, 93));
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

				mainFrame.ChangeRepaint();
				step2button.setEnabled(true);// 第一步点击之后，第二步可点击
			}
		});
		step2button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearSelection();


			}
		});
		step3button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearSelection();
			}
		});
		step4button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearSelection();

			}
		});
	}

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

package com.horstmann.violet.application.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.horstmann.violet.application.gui.common.ColorData;

public class StepButtonPanel extends JPanel {

	private JPanel step0buttonpanel;
	private JPanel step1buttonpanel;
	private JPanel step2buttonpanel;
	private JPanel step3buttonpanel;
	private JPanel step4buttonpanel;
	private JPanel step5buttonpanel;

	private List<JButton> stepButtonGroup;
	private JButton step0button;
	private JButton step1button;
	private JButton step3button;
	private JButton step2button;
	private JButton step4button;
	private JButton step5button;

	private static int index = 0;

	private MainFrame mainFrame;

	public StepButtonPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		init();
	}

	private void init() {

		initButton();

		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		this.add(step0buttonpanel);
		this.add(step1buttonpanel);
		this.add(step2buttonpanel);
		this.add(step3buttonpanel);
		this.add(step4buttonpanel);
		this.add(step5buttonpanel);
		this.setOpaque(false);

		this.setMinimumSize(new Dimension(1200, 96));
		this.setPreferredSize(new Dimension(1200, 96));
		this.setMaximumSize(new Dimension(1200, 96));

		SetButtonListener();

	}

	private void initButton() {

		step0buttonpanel = new JPanel();
		step1buttonpanel = new JPanel();
		step2buttonpanel = new JPanel();
		step3buttonpanel = new JPanel();
		step4buttonpanel = new JPanel();
		step5buttonpanel = new JPanel();

		step0button = new JButton();
		step1button = new JButton();
		step2button = new JButton();
		step3button = new JButton();
		step4button = new JButton();
		step5button = new JButton();
		
//		step0button.setText("首页");
//		step1button.setText("构建剖面图");
//		step2button.setText("生成测试用例");
//		step3button.setText("收集失效数据");
//		step4button.setText("可靠性评估");
//		
//		step0button.setPreferredSize(new Dimension(140, 80));
//		step1button.setPreferredSize(new Dimension(140, 80));
//		step2button.setPreferredSize(new Dimension(140, 80));
//		step3button.setPreferredSize(new Dimension(140, 80));
//		step4button.setPreferredSize(new Dimension(140, 80));

		String absolutePath = System.getProperty("user.dir");
		String path = absolutePath + "\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon0 = new ImageIcon(this.getClass().getResource("stepbutton0.png"));
		icon0.setImage(icon0.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("stepbutton0.png"));
		icon1.setImage(icon1.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(this.getClass().getResource("stepbutton0.png"));
		icon2.setImage(icon2.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon icon3 = new ImageIcon(this.getClass().getResource("stepbutton0.png"));
		icon3.setImage(icon3.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon icon4 = new ImageIcon(this.getClass().getResource("stepbutton0.png"));
		icon4.setImage(icon4.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon icon5 = new ImageIcon(this.getClass().getResource("stepbutton0.png"));
		icon5.setImage(icon5.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));

		
		// step1button.setContentAreaFilled(false);//btn背景透明
		// step1button.setBorderPainted(false);//btn无边框
		// step1button.setFocusPainted(false);//btn无内边框
//		step0button.setIcon(icon0);
		step0button.setText("首页");
		step0button.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		step0button.setForeground(ColorData.black);
		step0button.setHorizontalTextPosition(SwingConstants.CENTER);
		step0button.setVerticalTextPosition(SwingConstants.BOTTOM);
		step0button.setMargin(new Insets(0, 0, 0, 0));
		step0button.setFocusable(false);
		step0button.setContentAreaFilled(false);
		step0button.setBorderPainted(false);
		step0button.setPreferredSize(new Dimension(140, 80));
		step0button.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				step0buttonpanel.setBackground(ColorData.gray_65);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (index != 0) {
					step0buttonpanel.setBackground(ColorData.gray);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (index != 0) {
					step0buttonpanel.setBackground(ColorData.gray_50);
				}
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				setstepbuttonpanelrepaint();
				step0buttonpanel.setBackground(ColorData.gray_35);
				index = 0;

			}
		});
//		step1button.setIcon(icon1);
		step1button.setText("可靠性分配");
		step1button.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		step1button.setForeground(ColorData.black);
		step1button.setHorizontalTextPosition(SwingConstants.CENTER);
		step1button.setVerticalTextPosition(SwingConstants.BOTTOM);
		step1button.setMargin(new Insets(0, 0, 0, 0));
		step1button.setFocusable(false);
		step1button.setContentAreaFilled(false);
		step1button.setBorderPainted(false);
		step1button.setPreferredSize(new Dimension(140, 80));
		step1button.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				step1buttonpanel.setBackground(ColorData.gray_65);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (index != 1) {
					step1buttonpanel.setBackground(ColorData.gray);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (index != 1) {
					step1buttonpanel.setBackground(ColorData.gray_50);
				}
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				setstepbuttonpanelrepaint();
				step1buttonpanel.setBackground(ColorData.gray_35);
				index = 1;
			}
		});
//		step2button.setIcon(icon2);
		step2button.setText("构建剖面图");
		step2button.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		step2button.setForeground(ColorData.black);
		step2button.setHorizontalTextPosition(SwingConstants.CENTER);
		step2button.setVerticalTextPosition(SwingConstants.BOTTOM);
		step2button.setMargin(new Insets(0, 0, 0, 0));
		step2button.setFocusable(false);
		step2button.setContentAreaFilled(false);
		step2button.setBorderPainted(false);
		step2button.setPreferredSize(new Dimension(140, 80));
		step2button.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				step2buttonpanel.setBackground(ColorData.gray_65);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (index != 2) {
					step2buttonpanel.setBackground(ColorData.gray);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (index != 2) {
					step2buttonpanel.setBackground(ColorData.gray_50);
				}
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				setstepbuttonpanelrepaint();
				step2buttonpanel.setBackground(ColorData.gray_35);
				index = 2;
			}
		});
//		step3button.setIcon(icon3);
		step3button.setText("生成测试用例");
		step3button.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		step3button.setForeground(ColorData.black);
		step3button.setHorizontalTextPosition(SwingConstants.CENTER);
		step3button.setVerticalTextPosition(SwingConstants.BOTTOM);
		step3button.setMargin(new Insets(0, 0, 0, 0));
		step3button.setFocusable(false);
		step3button.setContentAreaFilled(false);
		step3button.setBorderPainted(false);
		step3button.setPreferredSize(new Dimension(140, 80));
		step3button.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				step3buttonpanel.setBackground(ColorData.gray_65);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (index != 3) {
					step3buttonpanel.setBackground(ColorData.gray);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (index != 3) {
					step3buttonpanel.setBackground(ColorData.gray_50);
				}
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				setstepbuttonpanelrepaint();
				step3buttonpanel.setBackground(ColorData.gray_35);
				index = 3;
			}
		});
//		step4button.setIcon(icon4);
		step4button.setText("收集失效数据");
		step4button.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		step4button.setForeground(ColorData.black);
		step4button.setHorizontalTextPosition(SwingConstants.CENTER);
		step4button.setVerticalTextPosition(SwingConstants.BOTTOM);
		step4button.setMargin(new Insets(0, 0, 0, 0));
		step4button.setFocusable(false);
		step4button.setContentAreaFilled(false);
		step4button.setBorderPainted(false);
		step4button.setPreferredSize(new Dimension(140, 80));
		step4button.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				step4buttonpanel.setBackground(ColorData.gray_65);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (index != 4) {
					step4buttonpanel.setBackground(ColorData.gray);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (index != 4) {
					step4buttonpanel.setBackground(ColorData.gray_50);
				}
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				setstepbuttonpanelrepaint();
				step4buttonpanel.setBackground(ColorData.gray_35);
				index = 4;
			}
		});
//		step5button.setIcon(icon5);
		step5button.setText("可靠性评估");
		step5button.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		step5button.setForeground(ColorData.black);
		step5button.setHorizontalTextPosition(SwingConstants.CENTER);
		step5button.setVerticalTextPosition(SwingConstants.BOTTOM);
		step5button.setMargin(new Insets(0, 0, 0, 0));
		step5button.setFocusable(false);
		step5button.setContentAreaFilled(false);
		step5button.setBorderPainted(false);
		step5button.setPreferredSize(new Dimension(140, 80));
		step5button.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				step5buttonpanel.setBackground(ColorData.gray_65);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (index != 5) {
					step5buttonpanel.setBackground(ColorData.gray);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (index != 5) {
					step5buttonpanel.setBackground(ColorData.gray_50);
				}
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				setstepbuttonpanelrepaint();
				step5buttonpanel.setBackground(ColorData.gray_35);
				index = 5;
			}
		});

		step0buttonpanel.setLayout(new GridLayout());
		// step0buttonpanel.setBackground(new Color(71,80,93));
		step0buttonpanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		step0buttonpanel.add(step0button);

		step0buttonpanel.setBackground(ColorData.gray_35);
		index = 0;

		step1buttonpanel.setLayout(new GridLayout());
		step1buttonpanel.setBackground(ColorData.gray);
		step1buttonpanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		step1buttonpanel.add(step1button);
		step2buttonpanel.setLayout(new GridLayout());
		step2buttonpanel.setBackground(ColorData.gray);
		step2buttonpanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		step2buttonpanel.add(step2button);
		step3buttonpanel.setLayout(new GridLayout());
		step3buttonpanel.setBackground(ColorData.gray);
		step3buttonpanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		step3buttonpanel.add(step3button);
		step4buttonpanel.setLayout(new GridLayout());
		step4buttonpanel.setBackground(ColorData.gray);
		step4buttonpanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		step4buttonpanel.add(step4button);
		step5buttonpanel.setLayout(new GridLayout());
		step5buttonpanel.setBackground(ColorData.gray);
		step5buttonpanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		step5buttonpanel.add(step5button);

		stepButtonGroup = new ArrayList<JButton>();
		stepButtonGroup.add(step0button);
		stepButtonGroup.add(step1button);
		stepButtonGroup.add(step2button);
		stepButtonGroup.add(step3button);
		stepButtonGroup.add(step4button);
		stepButtonGroup.add(step5button);

//		step2button.setEnabled(false);// 初始其他步骤按钮都不可点击
//		step3button.setEnabled(false);
//		step4button.setEnabled(false);
	}

	protected void setstepbuttonpanelrepaint() {
		step0buttonpanel.setBackground(ColorData.gray);
		step1buttonpanel.setBackground(ColorData.gray);
		step2buttonpanel.setBackground(ColorData.gray);
		step3buttonpanel.setBackground(ColorData.gray);
		step4buttonpanel.setBackground(ColorData.gray);
		step5buttonpanel.setBackground(ColorData.gray);
	}

	// 设置监听器
	private void SetButtonListener() {
		step0button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.getCenterPanel().removeAll();
				mainFrame.getCenterPanel().add(mainFrame.getStepZeroCenterPanel());
				mainFrame.setStepindex(0);
				mainFrame.ChangeRepaint();
			}
		});
		step1button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.getCenterPanel().removeAll();
				mainFrame.getCenterPanel().add(mainFrame.getStepOneCenterPanel());
				mainFrame.setStepindex(1);
				mainFrame.ChangeRepaint();
			}
		});
		step2button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.getCenterPanel().removeAll();
				mainFrame.getCenterPanel().add(mainFrame.getStepTwoCenterPanel());

				mainFrame.setStepindex(2);

				index = 2;

				mainFrame.ChangeRepaint();
			}
		});
		step3button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.getCenterPanel().removeAll();
				mainFrame.getCenterPanel().add(mainFrame.getStepThreeCenterPanel());

				mainFrame.setStepindex(3);

				index = 3;

				mainFrame.ChangeRepaint();
			}
		});
		step4button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				mainFrame.getCenterPanel().removeAll();
				mainFrame.getCenterPanel().add(mainFrame.getStepFourCenterPanel());

				mainFrame.setStepindex(4);

				index = 4;

				mainFrame.ChangeRepaint();
				
			}
		});
		step5button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				mainFrame.getCenterPanel().removeAll();
				mainFrame.getCenterPanel().add(mainFrame.getStepFiveCenterPanel());

				mainFrame.setStepindex(5);

				index = 5;

				mainFrame.ChangeRepaint();
				
			}
		});
	}

	public static int getIndex() {
		return index;
	}

}

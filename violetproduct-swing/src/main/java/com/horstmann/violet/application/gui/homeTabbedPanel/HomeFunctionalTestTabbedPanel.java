package com.horstmann.violet.application.gui.homeTabbedPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;

public class HomeFunctionalTestTabbedPanel extends JPanel {

	private MainFrame mainFrame;

	private JPanel inforpanel;

	private JPanel inforleftpanel;
	private JPanel inforrightpanel;

	private JPanel inforleftlabelpanel1;
	private JPanel inforleftlabelpanel2;

	private JLabel inforleftlabel1;
	private JLabel inforleftlabel2;

	private JPanel emptypanel;

	private JPanel startbuttonpanel;
	private JButton startbutton;

	private JPanel exitbuttonpanel;
	private JButton exitbutton;

	private JPanel inforrighttoppanel;
	private JPanel inforrightdetailpanel;

	private JLabel inforrightdetaillabel;
	private ImageCarouselPanel imagepanel;

	public HomeFunctionalTestTabbedPanel(MainFrame mainFrame) {

		this.mainFrame = mainFrame;

		inforpanel = new JPanel();

		initInforPanel();

		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(inforpanel);
		layout.setConstraints(inforpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));

	}

	private void initInforPanel() {
		// TODO Auto-generated method stub

		inforleftpanel = new JPanel();
		inforrightpanel = new JPanel();

		initInforLeftPanel();

		initInforrightPanel();

		inforpanel.setLayout(new BorderLayout());
		inforpanel.add(inforleftpanel, BorderLayout.WEST);
		inforpanel.add(inforrightpanel, BorderLayout.CENTER);

	}

	private void initInforLeftPanel() {
		// TODO Auto-generated method stub

		inforleftlabelpanel1 = new JPanel();
		inforleftlabelpanel2 = new JPanel();

		emptypanel = new JPanel();

		inforleftlabel1 = new JLabel();
		inforleftlabel2 = new JLabel();

		startbuttonpanel = new JPanel();
		startbutton = new JButton();

		inforleftlabel1.setText("功能测试");
		inforleftlabel1.setBorder(BorderFactory.createEmptyBorder(30, 30, 0, 0));
		inforleftlabel1.setFont(new Font("微软雅黑", Font.PLAIN, 27));
//		inforleftlabel2.setText("<html><body>了解新功能<br>查看新增功能<br>了解有关的更多信息<body></html>");
		inforleftlabel2.setText("<html><body>功能场景1<br>名称：从地图一个位置飞向另一个位置<br>参与者：地面站，操作员<body></html>");
		inforleftlabel2.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 0));
		inforleftlabel2.setFont(new Font("微软雅黑", Font.PLAIN, 15));

		inforleftlabelpanel1.add(inforleftlabel1);
		inforleftlabelpanel1.setLayout(new GridLayout());
		inforleftlabelpanel1.setOpaque(false);
		inforleftlabelpanel2.add(inforleftlabel2);
		inforleftlabelpanel2.setLayout(new GridLayout());
		inforleftlabelpanel2.setOpaque(false);

		startbutton.setText("开始");
		startbutton.setPreferredSize(new Dimension(80, 30));
		// startbutton.setMaximumSize(new Dimension(80, 30));
		// startbutton.setMinimumSize(new Dimension(80, 30));

		startbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				mainFrame.getHomeAllTabbedPanel().setStarttype(1);
				mainFrame.getStepButton().getStep2button().doClick();
				
				mainFrame.getModelTransformationPanel().getModelSequenceTreePanel().updateFileList();
				mainFrame.getTestCaseGenerationPanel().updateFileList();
				mainFrame.getTestCaseConfirmationPanel().updateFileList();

				mainFrame.getTestCaseGenerationPanel().getCoverpanel().setVisible(true);

			}
		});

		startbuttonpanel.add(startbutton);
		startbuttonpanel.setLayout(new FlowLayout(0, 0, 0));
		startbuttonpanel.setOpaque(false);
		startbuttonpanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 0));

		emptypanel.setOpaque(false);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final int screenWidth = (int) screenSize.getWidth();
		final int screenHeight = (int) screenSize.getHeight();

		inforleftpanel.setPreferredSize(new Dimension(screenWidth / 5, screenHeight));
		inforleftpanel.setBackground(Color.WHITE);
		inforleftpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(214, 214, 214)));
		// inforleftpanel.setLayout(new FlowLayout(FlowLayout.LEADING,0,0));
		// inforleftpanel.add(inforleftlabelpanel1);
		// inforleftpanel.add(inforleftlabelpanel2);
		// inforleftpanel.add(startbuttonpanel);

		GridBagLayout layout = new GridBagLayout();
		inforleftpanel.setLayout(layout);
		inforleftpanel.add(inforleftlabelpanel1);
		inforleftpanel.add(inforleftlabelpanel2);
		inforleftpanel.add(startbuttonpanel);
		inforleftpanel.add(emptypanel);
		layout.setConstraints(inforleftlabelpanel1, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(inforleftlabelpanel2, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(startbuttonpanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
		layout.setConstraints(emptypanel, new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));

	}

	private void initInforrightPanel() {
		// TODO Auto-generated method stub

		inforrighttoppanel = new JPanel();
		inforrightdetailpanel = new JPanel();

		exitbuttonpanel = new JPanel();
		exitbutton = new JButton();

		inforrightdetaillabel = new JLabel();

		exitbutton.setText("返回");
		exitbutton.setPreferredSize(new Dimension(80, 30));
		exitbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				mainFrame.getHomeAllTabbedPanel().getInforresultpanel().removeAll();
				mainFrame.getHomeAllTabbedPanel().getInforresultpanel()
						.add(mainFrame.getHomeAllTabbedPanel().getInforpanel());
				mainFrame.getHomeAllTabbedPanel().ChangeRepaint();

				mainFrame.getHomeAllTabbedPanel().setStarttype(0);

			}
		});

		exitbuttonpanel.add(exitbutton);
		exitbuttonpanel.setLayout(new GridLayout());
		exitbuttonpanel.setOpaque(false);
		exitbuttonpanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 20));

		inforrighttoppanel.setLayout(new BorderLayout());
		inforrighttoppanel.add(exitbuttonpanel, BorderLayout.EAST);
		inforrighttoppanel.setBackground(new Color(255, 255, 255));

		initDetailPanel();

		inforrightpanel.setLayout(new BorderLayout());
		inforrightpanel.add(inforrighttoppanel, BorderLayout.NORTH);
		inforrightpanel.add(inforrightdetailpanel, BorderLayout.CENTER);

	}

	private void initDetailPanel() {
		// TODO Auto-generated method stub

		// inforrightdetaillabel.setText("<html><body><p>详细步骤：</p><br><p>（1）建立模型</p><br><p>（2）转换时间自动机</p><br><p>（3）进行抽象测试用例</p><br><p>（4）执行测试用例，获取测试报告</p><br><p>（5）结束</p></body></html>");
		String text = "<html><body><p>正常情况:</p><br>1.启动无人机。<br>2.解锁无人机。<br>3.切换飞行模式为引导模式。<br>4.在地图上点击takeoff选项，然后输入飞行高度值。<br>5.在地图上其他位置点击Fly to选项。<br>6.无人机按照固定的速度向目的地飞行。<br>7.飞行到达位置后无人机悬停。<br>8.切换飞行模式为降落模式。<br><p>可选情况:</p><br>1.起飞速度操作员可以设定。<br>2.无人机悬停时间操作员可以设定。<br>3.引起故障保护后的动作操作员可以设定。<br>4.从源点到目的点的飞行速度可以设定。<br>5.降落速度可以设定。<br><p>异常情况:</p><br>1.电池电量不足，引起电池故障保护。<br>2.地面站连接异常，引起故障保护。<br>3.飞到目标位置后没有切换到降落模式。<br></body></html>";
		inforrightdetaillabel.setText(text);
		inforrightdetaillabel.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		inforrightdetaillabel.setOpaque(false);

		imagepanel = new ImageCarouselPanel();

		inforrightdetailpanel.setLayout(new BorderLayout());
		inforrightdetailpanel.add(inforrightdetaillabel, BorderLayout.NORTH);
		inforrightdetailpanel.add(imagepanel, BorderLayout.SOUTH);
		inforrightdetailpanel.setBackground(new Color(255, 255, 255));
		inforrightdetailpanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

	}

	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}

}

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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;

public class HomeTimeTestTabbedPanel extends JPanel{

	private MainFrame mainFrame;
	
	private JPanel inforpanel;
	
	private JPanel inforleftpanel;
	private JPanel inforrightpanel;
	
	private JPanel inforleftheadlabelpanel;
	private JPanel inforleftallsituationlabelpanel;
	private JPanel inforleftlabelpanel1;
	private JPanel inforleftlabelpanel2;

	private JLabel inforleftheadlabel;
	private JLabel inforleftnamelabel1;
	private JLabel inforleftdescribelabel1;
	private JLabel inforleftnamelabel2;
	private JLabel inforleftdescribelabel2;
	
	private JPanel emptypanel;

	private JPanel startbuttonpanel;
	private JButton startbutton;
	
	private JPanel exitbuttonpanel;
	private JButton exitbutton;
	
	private JPanel inforrighttoppanel;
	private JPanel inforrightdetailpanel;
	
	private int detailindex=1;
	private TimeTestSituationDetailPanel detailpanel1;
	
	public HomeTimeTestTabbedPanel(MainFrame mainFrame){
		
		this.mainFrame=mainFrame;
		
		inforpanel = new JPanel();
		
		initAllInforRightDetailPanel();
		
		initInforPanel();
		
    	GridBagLayout layout=new GridBagLayout();
		this.setLayout(layout);
		this.add(inforpanel);
		layout.setConstraints(inforpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
	}
	
	private void initAllInforRightDetailPanel() {
		// TODO Auto-generated method stub
		
		String normaltext1="<html><body>1.&nbsp;&nbsp;模拟某校五层教学楼的电梯系统。该楼有一个自动电梯，能在每层停留。五个楼层由下至上依次称为地下层、第一层、第二层、第三层和第四层，其中第一层是大楼的进出层，即是电梯的“本垒层”，电梯“空闲”时，将来到该层候命。<br>2.&nbsp;&nbsp;乘客可随机地进出于任何层。对于每个人来说，他有一个能容忍的最长等待时间，一旦等候电梯时间过长，他将放弃。<br>3.&nbsp;&nbsp;模拟时钟从0开始，时间单位为0.1秒。人和电梯的各种动作均要耗费一定的时间单位（简记为t）。<br>4.&nbsp;&nbsp;按时序显示系统状态的变化过程：发生的全部人和电梯的动作序列。</body></html>";
		String abnormaltext1="<html><body>模拟时钟Time的初值为0，终值可在500~10000范围内逐步增加。</body></html>";
		
		String absolutePath=System.getProperty("user.dir");
		String imagepath1 = absolutePath+"\\src\\site\\resources\\icons\\HomePage\\时间约束\\";
		
		detailpanel1=new TimeTestSituationDetailPanel(normaltext1, abnormaltext1, imagepath1);
		
	}

	private void initInforPanel() {
		// TODO Auto-generated method stub
		
		inforleftpanel = new JPanel();
    	inforrightpanel = new JPanel();
    	
    	initInforLeftPanel();
    	
    	initInforrightPanel();
		
		inforpanel.setLayout(new BorderLayout());
		inforpanel.add(inforleftpanel,BorderLayout.WEST);
		inforpanel.add(inforrightpanel,BorderLayout.CENTER);
		
	}

	private void initInforLeftPanel() {
		// TODO Auto-generated method stub
		
		inforleftheadlabelpanel=new JPanel();
		inforleftallsituationlabelpanel=new JPanel();
		
		inforleftheadlabel=new JLabel();

		emptypanel = new JPanel();

		startbuttonpanel = new JPanel();
		startbutton = new JButton();

		inforleftheadlabel.setText("时间约束测试");
		inforleftheadlabel.setBorder(BorderFactory.createEmptyBorder(30, 30, 0, 0));
		inforleftheadlabel.setFont(new Font("微软雅黑", Font.PLAIN, 27));
		
		inforleftheadlabelpanel.add(inforleftheadlabel);
		inforleftheadlabelpanel.setLayout(new GridLayout());
		inforleftheadlabelpanel.setOpaque(false);
		
		initInforLeftAllSituationLabelPanel();

		startbutton.setText("开始");
		startbutton.setPreferredSize(new Dimension(80, 30));
//		startbutton.setMaximumSize(new Dimension(80, 30));
//		startbutton.setMinimumSize(new Dimension(80, 30));
		
		startbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				mainFrame.getHomeAllTabbedPanel().initAllUIPanelAndData();
				
				mainFrame.getTestCaseGenerationPanel().getCoverpanel().setVisible(true);
//				mainFrame.getStepFiveCenterTabbedPane().getTestCaseReportTabbedPane().getToolbuttonpanel7().setVisible(true);
				
				mainFrame.getHomeAllTabbedPanel().setStarttype(3);
				mainFrame.getStepButton().getStep1button().doClick();
				
			}
		});
		
		startbuttonpanel.add(startbutton);
		startbuttonpanel.setLayout(new FlowLayout(0, 0, 0));
		startbuttonpanel.setOpaque(false);
		startbuttonpanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 0));
		
		emptypanel.setOpaque(false);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final int screenWidth = (int) screenSize.getWidth();
		final int screenHeight = (int) screenSize.getHeight();
		
		inforleftpanel.setPreferredSize(new Dimension(screenWidth/5, screenHeight));
		inforleftpanel.setBackground(Color.WHITE);
		inforleftpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(214,214,214)));
//		inforleftpanel.setLayout(new FlowLayout(FlowLayout.LEADING,0,0));
//		inforleftpanel.add(inforleftlabelpanel1);
//		inforleftpanel.add(inforleftlabelpanel2);
//		inforleftpanel.add(startbuttonpanel);
		
		GridBagLayout layout = new GridBagLayout();
		inforleftpanel.setLayout(layout);
		inforleftpanel.add(inforleftheadlabelpanel);
		inforleftpanel.add(inforleftallsituationlabelpanel);
		inforleftpanel.add(startbuttonpanel);
		inforleftpanel.add(emptypanel);
		layout.setConstraints(inforleftheadlabelpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(inforleftallsituationlabelpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(startbuttonpanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
		layout.setConstraints(emptypanel, new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));

	}
	
	private void initInforLeftAllSituationLabelPanel() {
		// TODO Auto-generated method stub
		
		inforleftlabelpanel1 = new JPanel();
		inforleftlabelpanel2 = new JPanel();
		
		inforleftnamelabel1=new JLabel();
		inforleftnamelabel2=new JLabel();
		
		inforleftdescribelabel1=new JLabel();
		inforleftdescribelabel2=new JLabel();
		
		inforleftnamelabel1.setText("<html><body><u>时间约束场景1：电梯模拟</u></body></html>");
		inforleftnamelabel1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		inforleftnamelabel1.setForeground(new Color(104, 33, 122));
		inforleftnamelabel1.setBorder(BorderFactory.createEmptyBorder(10, 0, 8, 0));
		inforleftnamelabel1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				ChangeInforLeftNameLabelColor();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				inforleftnamelabel1.setForeground(new Color(104, 33, 122));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				detailindex=1;
				
				inforrightdetailpanel.removeAll();
				inforrightdetailpanel.add(detailpanel1);
				
				ChangeRepaint();
				
			}
		});
		inforleftdescribelabel1.setText("<html><body>场景描述：这是一个离散的模拟程序，因为电梯系统是乘客和电梯等“活动体”构成的集合，虽然他们彼此交互作用，但他们的行为是基本独立的。在离散的模拟中，以模拟时钟决定每个活动体的动作发生的时刻和顺序，系统在某个模拟瞬间处理有待完成的各种事情，然后把模拟时钟推进到某个动作预定要发生的下一个时刻。</body></html>");
		inforleftdescribelabel1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		inforleftdescribelabel1.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		
//		inforleftnamelabel2.setText("<html><body><u>功能场景2：绕圈飞行</u></body></html>");
//		inforleftnamelabel2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
//		inforleftnamelabel2.setBorder(BorderFactory.createEmptyBorder(10, 0, 8, 0));
//		inforleftnamelabel2.addMouseListener(new MouseListener() {
//			
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mousePressed(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mouseExited(MouseEvent e) {
//				// TODO Auto-generated method stub
//				ChangeInforLeftNameLabelColor();
//			}
//			
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				// TODO Auto-generated method stub
//				inforleftnamelabel2.setForeground(new Color(104, 33, 122));
//			}
//			
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				// TODO Auto-generated method stub
//				detailindex=2;
//				
//				inforrightdetailpanel.removeAll();
//				inforrightdetailpanel.add(detailpanel2);
//				
//				ChangeRepaint();
//			}
//		});
//		inforleftdescribelabel2.setText("<html><body>场景描述：操作员启动无人机后使其飞行到固定高度，然后切换绕圈飞行模式，无人机以起飞点为圆心开始绕圈飞行。在飞行过程中操作员可以让无人机返航到起飞点降落。</body></html>");
//		inforleftdescribelabel2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
//		inforleftdescribelabel2.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		
		inforleftallsituationlabelpanel.setOpaque(false);
		inforleftallsituationlabelpanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 20));
		
		GridBagLayout layout = new GridBagLayout();
		inforleftallsituationlabelpanel.setLayout(layout);
		inforleftallsituationlabelpanel.add(inforleftnamelabel1);
		inforleftallsituationlabelpanel.add(inforleftdescribelabel1);
//		inforleftallsituationlabelpanel.add(inforleftnamelabel2);
//		inforleftallsituationlabelpanel.add(inforleftdescribelabel2);
		layout.setConstraints(inforleftnamelabel1, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(inforleftdescribelabel1, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//		layout.setConstraints(inforleftnamelabel2, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//		layout.setConstraints(inforleftdescribelabel2, new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		
//		inforleftlabel2.setText("<html><body>了解新功能<br>查看新增功能<br>了解有关的更多信息<body></html>");
//		inforleftlabel2.setText("<html><body>功能场景1<br>名称：从地图一个位置飞向另一个位置<br>参与者：地面站，操作员</body></html>");
//		inforleftlabel2.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 0));
//		inforleftlabel2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
	}
	
	protected void ChangeInforLeftNameLabelColor() {
		// TODO Auto-generated method stub
		inforleftnamelabel1.setForeground(new Color(0, 0, 0));
		inforleftnamelabel2.setForeground(new Color(0, 0, 0));
		if(detailindex==1){
			inforleftnamelabel1.setForeground(new Color(104, 33, 122));
		}
		else if(detailindex==2){
			inforleftnamelabel2.setForeground(new Color(104, 33, 122));
		}
	}

	private void initInforrightPanel() {
		// TODO Auto-generated method stub
		
		inforrighttoppanel=new JPanel();
		inforrightdetailpanel=new JPanel();
		
		exitbuttonpanel=new JPanel();
		exitbutton=new JButton();
		
		exitbutton.setText("返回");
		exitbutton.setPreferredSize(new Dimension(80, 30));
		exitbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				mainFrame.getHomeAllTabbedPanel().getInforresultpanel().removeAll();
				mainFrame.getHomeAllTabbedPanel().getInforresultpanel().add(mainFrame.getHomeAllTabbedPanel().getInforpanel());
				mainFrame.getHomeAllTabbedPanel().ChangeRepaint();
				
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
//		String text = "<html><body><p>正常情况:</p><br>1.启动无人机。<br>2.解锁无人机。<br>3.切换飞行模式为引导模式。<br>4.在地图上点击takeoff选项，然后输入飞行高度值。<br>5.在地图上其他位置点击Fly to选项。<br>6.无人机按照固定的速度向目的地飞行。<br>7.飞行到达位置后无人机悬停。<br>8.切换飞行模式为降落模式。<br><p>可选情况:</p><br>1.起飞速度操作员可以设定。<br>2.无人机悬停时间操作员可以设定。<br>3.引起故障保护后的动作操作员可以设定。<br>4.从源点到目的点的飞行速度可以设定。<br>5.降落速度可以设定。<br><p>异常情况:</p><br>1.电池电量不足，引起电池故障保护。<br>2.地面站连接异常，引起故障保护。<br>3.飞到目标位置后没有切换到降落模式。<br></body></html>";
//		inforrightdetaillabel.setText(text);
//		inforrightdetaillabel.setFont(new Font("微软雅黑", Font.PLAIN, 13));
//		inforrightdetaillabel.setOpaque(false);
//
//		imagepanel = new ImageCarouselPanel();
//
//		inforrightdetailpanel.setLayout(new BorderLayout());
//		inforrightdetailpanel.add(inforrightdetaillabel, BorderLayout.NORTH);
//		inforrightdetailpanel.add(imagepanel, BorderLayout.SOUTH);
//		inforrightdetailpanel.setBackground(new Color(255, 255, 255));
//		inforrightdetailpanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		
		inforrightdetailpanel.setLayout(new GridLayout());
		inforrightdetailpanel.add(detailpanel1);

	}
	
	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}
	
	
}

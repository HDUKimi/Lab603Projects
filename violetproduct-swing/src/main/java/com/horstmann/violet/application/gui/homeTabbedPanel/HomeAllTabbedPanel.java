package com.horstmann.violet.application.gui.homeTabbedPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;

public class HomeAllTabbedPanel extends JPanel {

	private MainFrame mainFrame;

	private HomeFunctionalTestTabbedPanel functionalTestTabbedPanel;
	private HomePerformanceTestTabbedPanel performanceTestTabbedPanel;
	private HomeTimeTestTabbedPanel timeTestTabbedPanel;

	private InforGeneralizePanel inforGeneralizePanel1;
	private InforGeneralizePanel inforGeneralizePanel2;
	private InforGeneralizePanel inforGeneralizePanel3;
	private InforGeneralizePanel inforGeneralizePanel4;

	private JLabel linklabel1;
	private JLabel linklabel2;
	private JLabel linklabel3;
	private JLabel linklabel4;

	private JPanel optiontabpanel;
	private JPanel inforresultpanel;

	private JPanel inforpanel;

	private JPanel titlepanel;
	private JLabel titlelabel;

	private JPanel titleiconlabelpanel;
	private JLabel titleiconlabel1;
	private JLabel titleiconlabel2;

	private JPanel inforleftpanel;
	private JPanel inforrightpanel;

	private JPanel emptypanel;

	private JPanel inforleftlabelpanel1;
	private JPanel inforleftlabelpanel2;

	private JLabel inforleftlabel1;
	private JLabel inforleftlabel2;

	private static int starttype = 0;// 1是功能测试，2是性能测试，3是时间测试

	private JPanel imagelabelpanel;
	private JLabel imagelabel;

	public HomeAllTabbedPanel(MainFrame mainFrame) {

		this.mainFrame = mainFrame;

		functionalTestTabbedPanel = new HomeFunctionalTestTabbedPanel(mainFrame);
		performanceTestTabbedPanel = new HomePerformanceTestTabbedPanel(mainFrame);
		timeTestTabbedPanel = new HomeTimeTestTabbedPanel(mainFrame);

		optiontabpanel = new JPanel();
		inforresultpanel = new JPanel();

		initOptionTabPanel();

		initInforResultPanel();

		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(optiontabpanel);
		this.add(inforresultpanel);
		layout.setConstraints(optiontabpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(inforresultpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));

	}

	public void initAllUIPanelAndData() {

		// mainFrame.getModelTransformationPanel().getModelSequenceTreePanel().updateFileList();
		// mainFrame.getTestCaseGenerationPanel().updateFileList();
		// mainFrame.getTestCaseConfirmationPanel().updateFileList();

		if (starttype != 0) {
			mainFrame.getStepOneCenterTabbedPane().initUIPanelData();
			mainFrame.getStepTwoCenterTabbedPane().initUIPanelData();
			mainFrame.getStepThreeCenterTabbedPane().initUIPanelData();
			mainFrame.getStepFourCenterTabbedPane().initUIPanelData();
			mainFrame.getStepFiveCenterTabbedPane().initUIPanelData();
			mainFrame.getStepSixCenterTabbedPane().initUIPanelData();

		}
	}

	private void initInforResultPanel() {
		// TODO Auto-generated method stub

		inforpanel = new JPanel();

		initInforPanel();

		inforresultpanel.setLayout(new GridLayout());
		inforresultpanel.add(inforpanel);

	}

	private void initOptionTabPanel() {
		// TODO Auto-generated method stub

		titlepanel = new JPanel();
		titlelabel = new JLabel();

		titleiconlabelpanel = new JPanel();
		titleiconlabel1 = new JLabel();
		titleiconlabel2 = new JLabel();

		titlelabel.setText("起始页");
		titlelabel.setFont(new Font("System", Font.PLAIN, 12));
		titlelabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

		String absolutePath = System.getProperty("user.dir");
		String path = absolutePath + "\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "yleftarrow.png");
		icon1.setImage(icon1.getImage().getScaledInstance(8, 7, Image.SCALE_DEFAULT));
		titleiconlabel1.setIcon(icon1);
		titleiconlabel1.setBorder(BorderFactory.createEmptyBorder(5, 4, 4, 4));
		ImageIcon icon2 = new ImageIcon(path + "yfork.png");
		icon2.setImage(icon2.getImage().getScaledInstance(10, 8, Image.SCALE_DEFAULT));
		titleiconlabel2.setIcon(icon2);
		titleiconlabel2.setBorder(BorderFactory.createEmptyBorder(5, 4, 4, 6));

		titleiconlabelpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		titleiconlabelpanel.setOpaque(false);
		titleiconlabelpanel.add(titleiconlabel1);
		titleiconlabelpanel.add(titleiconlabel2);

		titlepanel.setBackground(new Color(255, 242, 157));
		titlepanel.setPreferredSize(new Dimension(90, 23));
		titlepanel.setBorder(BorderFactory.createEmptyBorder(2, 0, 3, 0));
		titlepanel.setLayout(new BorderLayout());
		titlepanel.add(titlelabel, BorderLayout.WEST);
		titlepanel.add(titleiconlabelpanel, BorderLayout.EAST);

		optiontabpanel.setBackground(new Color(41, 57, 85));
		optiontabpanel.setLayout(new BorderLayout());
		optiontabpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(255, 242, 157)));
		optiontabpanel.add(titlepanel, BorderLayout.WEST);

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

		inforleftlabel1.setText("系统工作流程");
		inforleftlabel1.setBorder(BorderFactory.createEmptyBorder(30, 30, 0, 0));
		inforleftlabel1.setFont(new Font("微软雅黑", Font.PLAIN, 27));
		inforleftlabel2.setText(
				"<html><body>"
				+ "<p>第一步：系统建模<br>"
				+ "用户给定被测软件的系统设计模型。</p><br>"
				+ "<p>第二步：模型转换<br>"
				+ "通过模型转换引擎将系统设计模型转换为统一的时间自动机网络模型。</p><br>"
				+ "<p>第三步：模型评估<br>"
				+ "通过存在一致性评估、顺序一致性评估、实时一致性评估三种评估方式来进行评估模型是否一致。</p><br>"
				+ "<p>第四步：抽象测试用例生成<br>"
				+ "将转换后的时间自动机网络模型在测试用例生成引擎的驱动下，生成抽象测试用例集。</p><br>"
				+ "<p>第五步：测试用例实例化<br>"
				+ "通过测试用例实例化生成工具，将抽象测试用例转换成可执行的测试用例。</p><br>"
				+ "<p>第六步：测试执行<br>"
				+ "将实例化测试用例通过测试用例自动执行脚本虚拟执行，得到测试结果，生成测试报告。</p>"
				+ "</body></html>");
		inforleftlabel2.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 30));
		inforleftlabel2.setFont(new Font("微软雅黑", Font.PLAIN, 15));

		inforleftlabelpanel1.add(inforleftlabel1);
		inforleftlabelpanel1.setLayout(new GridLayout());
		inforleftlabelpanel1.setOpaque(false);
		inforleftlabelpanel2.add(inforleftlabel2);
		inforleftlabelpanel2.setLayout(new GridLayout());
		inforleftlabelpanel2.setOpaque(false);

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

		GridBagLayout layout = new GridBagLayout();
		inforleftpanel.setLayout(layout);
		inforleftpanel.add(inforleftlabelpanel1);
		inforleftpanel.add(inforleftlabelpanel2);
		inforleftpanel.add(emptypanel);
		layout.setConstraints(inforleftlabelpanel1, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(inforleftlabelpanel2, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(emptypanel, new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));

	}

	private void initInforrightPanel() {
		// TODO Auto-generated method stub

		// imagelabelpanel1=new JPanel();
		// imagelabelpanel2=new JPanel();
		// imagelabelpanel3=new JPanel();
		// imagelabelpanel4=new JPanel();
		//
		// imagelabel1=new JLabel();
		// imagelabel2=new JLabel();
		// imagelabel3=new JLabel();
		// imagelabel4=new JLabel();
		//
		// String absolutePath=System.getProperty("user.dir");
		// String path =
		// absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";
		//
		// ImageIcon icon = new ImageIcon(path + "homepanelright.png");
		// icon.setImage(icon.getImage().getScaledInstance(227,377,
		// Image.SCALE_DEFAULT));
		//
		// imagelabel1.setIcon(icon);
		// imagelabel2.setIcon(icon);
		// imagelabel3.setIcon(icon);
		// imagelabel4.setIcon(icon);
		//
		// imagelabel1.setBorder(BorderFactory.createEmptyBorder(75, 30, 0,
		// 20));
		// imagelabel2.setBorder(BorderFactory.createEmptyBorder(75, 30, 0,
		// 20));
		// imagelabel3.setBorder(BorderFactory.createEmptyBorder(75, 30, 0,
		// 20));
		// imagelabel4.setBorder(BorderFactory.createEmptyBorder(75, 30, 0,
		// 20));
		//
		// imagelabel1.addMouseListener(new MouseListener() {
		//
		// @Override
		// public void mouseReleased(MouseEvent arg0) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// @Override
		// public void mousePressed(MouseEvent arg0) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// @Override
		// public void mouseExited(MouseEvent arg0) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// @Override
		// public void mouseEntered(MouseEvent arg0) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// @Override
		// public void mouseClicked(MouseEvent arg0) {
		// // TODO Auto-generated method stub
		//
		// inforresultpanel.removeAll();
		// inforresultpanel.add(performanceTestTabbedPanel);
		// ChangeRepaint();
		//
		// }
		// });
		//
		// imagelabelpanel1.setLayout(new BorderLayout());
		// imagelabelpanel1.setOpaque(false);
		// imagelabelpanel1.add(imagelabel1,BorderLayout.NORTH);
		// imagelabelpanel2.setLayout(new BorderLayout());
		// imagelabelpanel2.setOpaque(false);
		// imagelabelpanel2.add(imagelabel2,BorderLayout.NORTH);
		// imagelabelpanel3.setLayout(new BorderLayout());
		// imagelabelpanel3.setOpaque(false);
		// imagelabelpanel3.add(imagelabel3,BorderLayout.NORTH);
		// imagelabelpanel4.setLayout(new BorderLayout());
		// imagelabelpanel4.setOpaque(false);
		// imagelabelpanel4.add(imagelabel4,BorderLayout.NORTH);
		//
		// inforrightpanel.setBackground(Color.WHITE);
		// inforrightpanel.setLayout(new GridLayout(1, 4));
		// inforrightpanel.add(imagelabelpanel1);
		// inforrightpanel.add(imagelabelpanel2);
		// inforrightpanel.add(imagelabelpanel3);
		// inforrightpanel.add(imagelabelpanel4);

		// inforGeneralizePanel1=new InforGeneralizePanel(mainFrame, "性能测试");
		// inforGeneralizePanel2=new InforGeneralizePanel(mainFrame, "功能测试");
		// inforGeneralizePanel3=new InforGeneralizePanel(mainFrame, "时间约束测试");
		// inforGeneralizePanel4=new InforGeneralizePanel(mainFrame, "功能测试");
		//
		// linklabel1=inforGeneralizePanel1.getLinklabel();
		// linklabel2=inforGeneralizePanel2.getLinklabel();
		// linklabel3=inforGeneralizePanel3.getLinklabel();
		// linklabel4=inforGeneralizePanel4.getLinklabel();
		//
		// initLinkAction();

		initImagePanel();

		inforrightpanel.setBackground(Color.WHITE);
		// inforrightpanel.setLayout(new GridLayout(1, 4));
		// inforrightpanel.add(inforGeneralizePanel1);
		// inforrightpanel.add(inforGeneralizePanel2);
		// inforrightpanel.add(inforGeneralizePanel3);
		// inforrightpanel.add(inforGeneralizePanel4);

		JPanel emptypanel = new JPanel();
		emptypanel.setOpaque(false);

		// GridBagLayout layout=new GridBagLayout();
		// inforrightpanel.setLayout(layout);
		// inforrightpanel.add(inforGeneralizePanel1);
		// inforrightpanel.add(inforGeneralizePanel2);
		// inforrightpanel.add(inforGeneralizePanel3);
		// inforrightpanel.add(emptypanel);
		// layout.setConstraints(inforGeneralizePanel1, new GBC(0, 0, 1,
		// 1).setFill(GBC.BOTH).setWeight(1, 0));
		// layout.setConstraints(inforGeneralizePanel2, new GBC(1, 0, 1,
		// 1).setFill(GBC.BOTH).setWeight(1, 0));
		// layout.setConstraints(inforGeneralizePanel3, new GBC(2, 0, 1,
		// 1).setFill(GBC.BOTH).setWeight(1, 0));
		// layout.setConstraints(emptypanel, new GBC(3, 0, 1,
		// 1).setFill(GBC.BOTH).setWeight(1, 0));

		GridBagLayout layout = new GridBagLayout();
		inforrightpanel.setLayout(layout);
		inforrightpanel.add(imagelabelpanel);
		layout.setConstraints(imagelabelpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));

	}

	private void initImagePanel() {
		// TODO Auto-generated method stub

		imagelabel = new JLabel();
		imagelabelpanel = new JPanel();

		String absolutePath = System.getProperty("user.dir");
		String path = absolutePath + "\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon = new ImageIcon(path + "home.png");
		icon.setImage(icon.getImage().getScaledInstance(935, 377, Image.SCALE_DEFAULT));

		imagelabel.setIcon(icon);
//		imagelabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));
		imagelabel.setBorder(BorderFactory.createMatteBorder(0, 0, 250, 0, new Color(255, 255, 255)));

		imagelabelpanel.setLayout(new BorderLayout());
		// imagelabelpanel.setOpaque(false);
		imagelabelpanel.add(imagelabel, BorderLayout.NORTH);

	}

	private void initLinkAction() {
		// TODO Auto-generated method stub

		linklabel1.addMouseListener(new MouseListener() {

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
				linklabel1.setText("连接");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				linklabel1.setText("<html><body><u>连接</u></body></html>");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

				inforresultpanel.removeAll();
				inforresultpanel.add(performanceTestTabbedPanel);
				ChangeRepaint();
			}
		});

		linklabel2.addMouseListener(new MouseListener() {

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
				linklabel2.setText("连接");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				linklabel2.setText("<html><body><u>连接</u></body></html>");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

				inforresultpanel.removeAll();
				inforresultpanel.add(functionalTestTabbedPanel);
				ChangeRepaint();
			}
		});

		linklabel3.addMouseListener(new MouseListener() {

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
				linklabel3.setText("连接");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				linklabel3.setText("<html><body><u>连接</u></body></html>");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

				inforresultpanel.removeAll();
				inforresultpanel.add(timeTestTabbedPanel);
				ChangeRepaint();
			}
		});

		linklabel4.addMouseListener(new MouseListener() {

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
				linklabel4.setText("连接");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				linklabel4.setText("<html><body><u>连接</u></body></html>");
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

				inforresultpanel.removeAll();
				inforresultpanel.add(performanceTestTabbedPanel);
				ChangeRepaint();
			}
		});

	}

	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}

	public JPanel getInforresultpanel() {
		return inforresultpanel;
	}

	public JPanel getInforpanel() {
		return inforpanel;
	}

	public static int getStarttype() {
		return starttype;
	}

	@SuppressWarnings("static-access")
	public void setStarttype(int starttype) {
		this.starttype = starttype;
	}

}

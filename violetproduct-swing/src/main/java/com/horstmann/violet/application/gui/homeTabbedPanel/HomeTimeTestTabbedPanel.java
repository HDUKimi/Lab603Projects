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

public class HomeTimeTestTabbedPanel extends JPanel{

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
	
	public HomeTimeTestTabbedPanel(MainFrame mainFrame){
		
		this.mainFrame=mainFrame;
		
		inforpanel = new JPanel();
		
		initInforPanel();
		
    	GridBagLayout layout=new GridBagLayout();
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
		inforpanel.add(inforleftpanel,BorderLayout.WEST);
		inforpanel.add(inforrightpanel,BorderLayout.CENTER);
		
	}

	private void initInforLeftPanel() {
		// TODO Auto-generated method stub
		
		inforleftlabelpanel1=new JPanel();
    	inforleftlabelpanel2=new JPanel();
    	
    	emptypanel=new JPanel();
    	
    	inforleftlabel1=new JLabel();
    	inforleftlabel2=new JLabel();
    	
    	startbuttonpanel=new JPanel();
    	startbutton=new JButton();
		
		inforleftlabel1.setText("ʱ��Լ������");
		inforleftlabel1.setBorder(BorderFactory.createEmptyBorder(30, 30, 0, 0));
		inforleftlabel1.setFont(new Font("΢���ź�", Font.PLAIN, 27));
		inforleftlabel2.setText("<html><body>�˽��¹���<br>�鿴��������<br>�˽��йصĸ�����Ϣ<body></html>");
		inforleftlabel2.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 0));
		inforleftlabel2.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		
		inforleftlabelpanel1.add(inforleftlabel1);
		inforleftlabelpanel1.setLayout(new GridLayout());
		inforleftlabelpanel1.setOpaque(false);
		inforleftlabelpanel2.add(inforleftlabel2);
		inforleftlabelpanel2.setLayout(new GridLayout());
		inforleftlabelpanel2.setOpaque(false);
		
		startbutton.setText("��ʼ");
		startbutton.setPreferredSize(new Dimension(80, 30));
//		startbutton.setMaximumSize(new Dimension(80, 30));
//		startbutton.setMinimumSize(new Dimension(80, 30));
		
		startbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				mainFrame.getHomeAllTabbedPanel().initAllUIPanelAndData();
				
				mainFrame.getTestCaseGenerationPanel().getCoverpanel().setVisible(true);
				mainFrame.getStepFiveCenterTabbedPane().getTestCaseReportTabbedPane().getToolbuttonpanel7().setVisible(true);
				
				mainFrame.getHomeAllTabbedPanel().setStarttype(3);
				mainFrame.getStepButton().getStep1button().doClick();
				
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
		
		inforleftpanel.setPreferredSize(new Dimension(screenWidth/5, screenHeight));
		inforleftpanel.setBackground(Color.WHITE);
		inforleftpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(214,214,214)));
//		inforleftpanel.setLayout(new FlowLayout(FlowLayout.LEADING,0,0));
//		inforleftpanel.add(inforleftlabelpanel1);
//		inforleftpanel.add(inforleftlabelpanel2);
//		inforleftpanel.add(startbuttonpanel);
		
		GridBagLayout layout=new GridBagLayout();
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
		
		inforrighttoppanel=new JPanel();
		inforrightdetailpanel=new JPanel();
		
		exitbuttonpanel=new JPanel();
		exitbutton=new JButton();
		
		inforrightdetaillabel=new JLabel();
		
		exitbutton.setText("����");
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
		
		inforrightdetaillabel.setText("<html><body><p>��ϸ���裺</p><br><p>��1������ģ��</p><br><p>��2��ת��ʱ���Զ���</p><br><p>��3�����г����������</p><br><p>��4��ִ�в�����������ȡ���Ա���</p><br><p>��5������</p></body></html>");
		inforrightdetaillabel.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		inforrightdetaillabel.setOpaque(false);
		inforrightdetailpanel.setLayout(new BorderLayout());
		inforrightdetailpanel.add(inforrightdetaillabel, BorderLayout.NORTH);
		inforrightdetailpanel.setBackground(new Color(255, 255, 255));
		inforrightdetailpanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 0));
		
		inforrightpanel.setLayout(new BorderLayout());
		inforrightpanel.add(inforrighttoppanel, BorderLayout.NORTH);
		inforrightpanel.add(inforrightdetailpanel, BorderLayout.CENTER);
		
	}
	
	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}
	
	
}

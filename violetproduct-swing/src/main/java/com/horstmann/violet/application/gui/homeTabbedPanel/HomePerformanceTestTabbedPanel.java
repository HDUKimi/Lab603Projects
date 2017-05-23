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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;

public class HomePerformanceTestTabbedPanel extends JPanel{

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
	private PerformanceTestSituationDetailPanel detailpanel1;
	private PerformanceTestSituationDetailPanel detailpanel2;
	
	public HomePerformanceTestTabbedPanel(MainFrame mainFrame){
		
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

		String normaltext1 = "<html><body>1.&nbsp;&nbsp;�����趨Ϊ0ʱ,��ɸ߶�870m������ʱ��394s��<br>"
				+ "2.&nbsp;&nbsp;�����趨Ϊ2ʱ,��ɸ߶�863m������ʱ��392s��<br>"
				+ "3.&nbsp;&nbsp;�����趨Ϊ4ʱ,��ɸ߶�850m������ʱ��387s��<br>"
				+ "4.&nbsp;&nbsp;�����趨Ϊ6ʱ,��ɸ߶�830m������ʱ��377s��<br>"
				+ "5.&nbsp;&nbsp;�����趨Ϊ8ʱ,��ɸ߶�800m������ʱ��365s��<br>"
				+ "6.&nbsp;&nbsp;�����趨Ϊ10ʱ,��ɸ߶�770m������ʱ��351s��<br>"
				+ "7.&nbsp;&nbsp;�����趨Ϊ12ʱ,��ɸ߶�736m������ʱ��336s��<br>"
				+ "8.&nbsp;&nbsp;�����趨Ϊ14ʱ,��ɸ߶�700m������ʱ��320s��<br>"
				+ "9.&nbsp;&nbsp;�����趨Ϊ16ʱ,��ɸ߶�667m������ʱ��306s��<br>"
				+ "10.&nbsp;&nbsp;�����趨Ϊ18ʱ,��ɸ߶�75m������ʱ��333s��<br>"
				+ "11.&nbsp;&nbsp;�����趨Ϊ20ʱ,��ɸ߶�62m������ʱ��333s��</body></html>";
		String abnormaltext1 = "";
		
		String normaltext2="";
		String abnormaltext2="";
		
		String absolutePath = System.getProperty("user.dir");
		String imagepath1 = absolutePath + "\\src\\site\\resources\\icons\\HomePage\\���ܲ���1\\";
		String imagepath2 = absolutePath+"\\src\\site\\resources\\icons\\HomePage\\���ܲ���2\\";

		detailpanel1 = new PerformanceTestSituationDetailPanel(normaltext1, abnormaltext1, imagepath1);
		detailpanel2 = new PerformanceTestSituationDetailPanel(normaltext2, abnormaltext2, imagepath2);

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
		
		inforleftheadlabel.setText("���ܲ���");
		inforleftheadlabel.setBorder(BorderFactory.createEmptyBorder(30, 30, 0, 0));
		inforleftheadlabel.setFont(new Font("΢���ź�", Font.PLAIN, 27));
		
		inforleftheadlabelpanel.add(inforleftheadlabel);
		inforleftheadlabelpanel.setLayout(new GridLayout());
		inforleftheadlabelpanel.setOpaque(false);
		
		initInforLeftAllSituationLabelPanel();
		
		startbutton.setText("��ʼ");
		startbutton.setPreferredSize(new Dimension(80, 30));
//		startbutton.setMaximumSize(new Dimension(80, 30));
//		startbutton.setMinimumSize(new Dimension(80, 30));
		
		startbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				mainFrame.getHomeAllTabbedPanel().initAllUIPanelAndData();
				
				mainFrame.getTestCaseGenerationPanel().getCoverpanel().setVisible(false);
				
				mainFrame.getHomeAllTabbedPanel().setStarttype(2);
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
		
		inforleftnamelabel1.setText("<html><body><u>���ܲ���1��������ܲ���</u></body></html>");
		inforleftnamelabel1.setFont(new Font("΢���ź�", Font.PLAIN, 18));
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
		inforleftdescribelabel1.setText("<html><body>����˵�����ֱ��ڲ�ͬ�����²�����ɸ߶�ÿ����10m�����ĵ�ʱ�䡣</body></html>");
		inforleftdescribelabel1.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		inforleftdescribelabel1.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		
		inforleftnamelabel2.setText("<html><body><u>���ܲ���2����ͣ���ܲ���</u></body></html>");
		inforleftnamelabel2.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		inforleftnamelabel2.setBorder(BorderFactory.createEmptyBorder(10, 0, 8, 0));
		inforleftnamelabel2.addMouseListener(new MouseListener() {
			
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
				inforleftnamelabel2.setForeground(new Color(104, 33, 122));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				detailindex=2;
				
				inforrightdetailpanel.removeAll();
				inforrightdetailpanel.add(detailpanel2);
				
				ChangeRepaint();
			}
		});
		inforleftdescribelabel2.setText("<html><body>����˵�����ֱ����趨��ͬ���ٵ�����´�����ͣ״̬ʱ���������ĺͶ�Ӧ��ʱ�䡣�����趨Ϊ0��2��4��������20����¼���˻���������10%ʱ�����ĵ�ʱ�䡣</body></html>");
		inforleftdescribelabel2.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		inforleftdescribelabel2.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		
		inforleftallsituationlabelpanel.setOpaque(false);
		inforleftallsituationlabelpanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 20));
		
		GridBagLayout layout = new GridBagLayout();
		inforleftallsituationlabelpanel.setLayout(layout);
		inforleftallsituationlabelpanel.add(inforleftnamelabel1);
		inforleftallsituationlabelpanel.add(inforleftdescribelabel1);
		inforleftallsituationlabelpanel.add(inforleftnamelabel2);
		inforleftallsituationlabelpanel.add(inforleftdescribelabel2);
		layout.setConstraints(inforleftnamelabel1, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(inforleftdescribelabel1, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(inforleftnamelabel2, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(inforleftdescribelabel2, new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		
//		inforleftlabel2.setText("<html><body>�˽��¹���<br>�鿴��������<br>�˽��йصĸ�����Ϣ<body></html>");
//		inforleftlabel2.setText("<html><body>���ܳ���1<br>���ƣ��ӵ�ͼһ��λ�÷�����һ��λ��<br>�����ߣ�����վ������Ա</body></html>");
//		inforleftlabel2.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 0));
//		inforleftlabel2.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		
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
		
		initDetailPanel();
		
		inforrightpanel.setLayout(new BorderLayout());
		inforrightpanel.add(inforrighttoppanel, BorderLayout.NORTH);
		inforrightpanel.add(inforrightdetailpanel, BorderLayout.CENTER);
		
	}
	
	private void initDetailPanel() {
		// TODO Auto-generated method stub
		
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

//package com.horstmann.violet.application.gui.homeTabbedPanel;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.Font;
//import java.awt.GridBagLayout;
//import java.awt.GridLayout;
//import java.awt.Image;
//import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//
//import javax.swing.BorderFactory;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//
//import com.horstmann.violet.application.gui.GBC;
//import com.horstmann.violet.application.gui.MainFrame;
//
//public class HomePerformanceTestTabbedPanel extends JPanel{
//
//	private MainFrame mainFrame;
//	
//	private JPanel inforpanel;
//	
//	private JPanel inforleftpanel;
//	private JPanel inforrightpanel;
//	
//	private JPanel inforleftlabelpanel1;
//	private JPanel inforleftlabelpanel2;
//	
//	private JLabel inforleftlabel1;
//	private JLabel inforleftlabel2;
//	
//	private JPanel emptypanel;
//
//	private JPanel startbuttonpanel;
//	private JButton startbutton;
//	
//	private JPanel exitbuttonpanel;
//	private JButton exitbutton;
//	
//	private JPanel inforrighttoppanel;
//	private JPanel inforrightdetailpanel;
//	
//	private JLabel inforrightdetaillabel;
//	
//	public HomePerformanceTestTabbedPanel(MainFrame mainFrame){
//		
//		this.mainFrame=mainFrame;
//		
//		inforpanel = new JPanel();
//		
//		initInforPanel();
//		
//    	GridBagLayout layout=new GridBagLayout();
//		this.setLayout(layout);
//		this.add(inforpanel);
//		layout.setConstraints(inforpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		
//	}
//
//	private void initInforPanel() {
//		// TODO Auto-generated method stub
//		
//		inforleftpanel = new JPanel();
//    	inforrightpanel = new JPanel();
//    	
//    	initInforLeftPanel();
//    	
//    	initInforrightPanel();
//		
//		inforpanel.setLayout(new BorderLayout());
//		inforpanel.add(inforleftpanel,BorderLayout.WEST);
//		inforpanel.add(inforrightpanel,BorderLayout.CENTER);
//		
//	}
//
//	private void initInforLeftPanel() {
//		// TODO Auto-generated method stub
//		
//		inforleftlabelpanel1=new JPanel();
//    	inforleftlabelpanel2=new JPanel();
//    	
//    	emptypanel=new JPanel();
//    	
//    	inforleftlabel1=new JLabel();
//    	inforleftlabel2=new JLabel();
//    	
//    	startbuttonpanel=new JPanel();
//    	startbutton=new JButton();
//		
//		inforleftlabel1.setText("���ܲ���");
//		inforleftlabel1.setBorder(BorderFactory.createEmptyBorder(30, 30, 0, 0));
//		inforleftlabel1.setFont(new Font("΢���ź�", Font.PLAIN, 27));
//		inforleftlabel2.setText("<html><body>�˽��¹���<br>�鿴��������<br>�˽��йصĸ�����Ϣ<body></html>");
//		inforleftlabel2.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 0));
//		inforleftlabel2.setFont(new Font("΢���ź�", Font.PLAIN, 15));
//		
//		inforleftlabelpanel1.add(inforleftlabel1);
//		inforleftlabelpanel1.setLayout(new GridLayout());
//		inforleftlabelpanel1.setOpaque(false);
//		inforleftlabelpanel2.add(inforleftlabel2);
//		inforleftlabelpanel2.setLayout(new GridLayout());
//		inforleftlabelpanel2.setOpaque(false);
//		
//		startbutton.setText("��ʼ");
//		startbutton.setPreferredSize(new Dimension(80, 30));
////		startbutton.setMaximumSize(new Dimension(80, 30));
////		startbutton.setMinimumSize(new Dimension(80, 30));
//		
//		startbutton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				
//				mainFrame.getHomeAllTabbedPanel().setStarttype(2);
//				mainFrame.getStepButton().getStep2button().doClick();
//				
//				mainFrame.getModelTransformationPanel().getModelSequenceTreePanel().updateFileList();
//				mainFrame.getTestCaseGenerationPanel().updateFileList();
//				mainFrame.getTestCaseConfirmationPanel().updateFileList();
//				
//				mainFrame.getTestCaseGenerationPanel().getCoverpanel().setVisible(false);
//				
//			}
//		});
//		
//		startbuttonpanel.add(startbutton);
//		startbuttonpanel.setLayout(new FlowLayout(0, 0, 0));
//		startbuttonpanel.setOpaque(false);
//		startbuttonpanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 0));
//		
//		emptypanel.setOpaque(false);
//		
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		final int screenWidth = (int) screenSize.getWidth();
//		final int screenHeight = (int) screenSize.getHeight();
//		
//		inforleftpanel.setPreferredSize(new Dimension(screenWidth/5, screenHeight));
//		inforleftpanel.setBackground(Color.WHITE);
//		inforleftpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(214,214,214)));
////		inforleftpanel.setLayout(new FlowLayout(FlowLayout.LEADING,0,0));
////		inforleftpanel.add(inforleftlabelpanel1);
////		inforleftpanel.add(inforleftlabelpanel2);
////		inforleftpanel.add(startbuttonpanel);
//		
//		GridBagLayout layout=new GridBagLayout();
//		inforleftpanel.setLayout(layout);
//		inforleftpanel.add(inforleftlabelpanel1);
//		inforleftpanel.add(inforleftlabelpanel2);
//		inforleftpanel.add(startbuttonpanel);
//		inforleftpanel.add(emptypanel);
//		layout.setConstraints(inforleftlabelpanel1, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//		layout.setConstraints(inforleftlabelpanel2, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//		layout.setConstraints(startbuttonpanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
//		layout.setConstraints(emptypanel, new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		
//	}
//
//	private void initInforrightPanel() {
//		// TODO Auto-generated method stub
//		
//		inforrighttoppanel=new JPanel();
//		inforrightdetailpanel=new JPanel();
//		
//		exitbuttonpanel=new JPanel();
//		exitbutton=new JButton();
//		
//		inforrightdetaillabel=new JLabel();
//		
//		exitbutton.setText("����");
//		exitbutton.setPreferredSize(new Dimension(80, 30));
//		exitbutton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				
//				mainFrame.getHomeAllTabbedPanel().getInforresultpanel().removeAll();
//				mainFrame.getHomeAllTabbedPanel().getInforresultpanel().add(mainFrame.getHomeAllTabbedPanel().getInforpanel());
//				mainFrame.getHomeAllTabbedPanel().ChangeRepaint();
//				
//				mainFrame.getHomeAllTabbedPanel().setStarttype(0);
//				
//			}
//		});
//		
//		exitbuttonpanel.add(exitbutton);
//		exitbuttonpanel.setLayout(new GridLayout());
//		exitbuttonpanel.setOpaque(false);
//		exitbuttonpanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 20));
//		
//		inforrighttoppanel.setLayout(new BorderLayout());
//		inforrighttoppanel.add(exitbuttonpanel, BorderLayout.EAST);
//		inforrighttoppanel.setBackground(new Color(255, 255, 255));
//		
//		inforrightdetaillabel.setText("<html><body><p>��ϸ���裺</p><br><p>��1������ģ��</p><br><p>��2��ת��ʱ���Զ���</p><br><p>��3�����г����������</p><br><p>��4��ִ�в�����������ȡ���Ա���</p><br><p>��5������</p></body></html>");
//		inforrightdetaillabel.setFont(new Font("΢���ź�", Font.PLAIN, 13));
//		inforrightdetaillabel.setOpaque(false);
//		inforrightdetailpanel.setLayout(new BorderLayout());
//		inforrightdetailpanel.add(inforrightdetaillabel, BorderLayout.NORTH);
//		inforrightdetailpanel.setBackground(new Color(255, 255, 255));
//		inforrightdetailpanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 0));
//		
//		inforrightpanel.setLayout(new BorderLayout());
//		inforrightpanel.add(inforrighttoppanel, BorderLayout.NORTH);
//		inforrightpanel.add(inforrightdetailpanel, BorderLayout.CENTER);
//		
//	}
//	
//	public void ChangeRepaint() {
//		// TODO Auto-generated method stub
//		this.setVisible(false);
//		this.getRootPane().repaint();
//		this.setVisible(true);
//	}
//	
//	
//}
//

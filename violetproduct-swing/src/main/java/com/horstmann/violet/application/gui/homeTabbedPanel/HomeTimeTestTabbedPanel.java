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
		
		String normaltext1="<html><body>1.&nbsp;&nbsp;ģ��ĳУ����ѧ¥�ĵ���ϵͳ����¥��һ���Զ����ݣ�����ÿ��ͣ�������¥�������������γ�Ϊ���²㡢��һ�㡢�ڶ��㡢������͵��Ĳ㣬���е�һ���Ǵ�¥�Ľ����㣬���ǵ��ݵġ����ݲ㡱�����ݡ����С�ʱ���������ò������<br>2.&nbsp;&nbsp;�˿Ϳ�����ؽ������κβ㡣����ÿ������˵������һ�������̵���ȴ�ʱ�䣬һ���Ⱥ����ʱ�����������������<br>3.&nbsp;&nbsp;ģ��ʱ�Ӵ�0��ʼ��ʱ�䵥λΪ0.1�롣�˺͵��ݵĸ��ֶ�����Ҫ�ķ�һ����ʱ�䵥λ�����Ϊt����<br>4.&nbsp;&nbsp;��ʱ����ʾϵͳ״̬�ı仯���̣�������ȫ���˺͵��ݵĶ������С�</body></html>";
		String abnormaltext1="<html><body>ģ��ʱ��Time�ĳ�ֵΪ0����ֵ����500~10000��Χ�������ӡ�</body></html>";
		
		String absolutePath=System.getProperty("user.dir");
		String imagepath1 = absolutePath+"\\src\\site\\resources\\icons\\HomePage\\ʱ��Լ��\\";
		
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

		inforleftheadlabel.setText("ʱ��Լ������");
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
		
		inforleftnamelabel1.setText("<html><body><u>ʱ��Լ������1������ģ��</u></body></html>");
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
		inforleftdescribelabel1.setText("<html><body>��������������һ����ɢ��ģ�������Ϊ����ϵͳ�ǳ˿ͺ͵��ݵȡ���塱���ɵļ��ϣ���Ȼ���Ǳ˴˽������ã������ǵ���Ϊ�ǻ��������ġ�����ɢ��ģ���У���ģ��ʱ�Ӿ���ÿ�����Ķ���������ʱ�̺�˳��ϵͳ��ĳ��ģ��˲�䴦���д���ɵĸ������飬Ȼ���ģ��ʱ���ƽ���ĳ������Ԥ��Ҫ��������һ��ʱ�̡�</body></html>");
		inforleftdescribelabel1.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		inforleftdescribelabel1.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		
//		inforleftnamelabel2.setText("<html><body><u>���ܳ���2����Ȧ����</u></body></html>");
//		inforleftnamelabel2.setFont(new Font("΢���ź�", Font.PLAIN, 18));
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
//		inforleftdescribelabel2.setText("<html><body>��������������Ա�������˻���ʹ����е��̶��߶ȣ�Ȼ���л���Ȧ����ģʽ�����˻�����ɵ�ΪԲ�Ŀ�ʼ��Ȧ���С��ڷ��й����в���Ա���������˻���������ɵ㽵�䡣</body></html>");
//		inforleftdescribelabel2.setFont(new Font("΢���ź�", Font.PLAIN, 15));
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

		// inforrightdetaillabel.setText("<html><body><p>��ϸ���裺</p><br><p>��1������ģ��</p><br><p>��2��ת��ʱ���Զ���</p><br><p>��3�����г����������</p><br><p>��4��ִ�в�����������ȡ���Ա���</p><br><p>��5������</p></body></html>");
//		String text = "<html><body><p>�������:</p><br>1.�������˻���<br>2.�������˻���<br>3.�л�����ģʽΪ����ģʽ��<br>4.�ڵ�ͼ�ϵ��takeoffѡ�Ȼ��������и߶�ֵ��<br>5.�ڵ�ͼ������λ�õ��Fly toѡ�<br>6.���˻����չ̶����ٶ���Ŀ�ĵط��С�<br>7.���е���λ�ú����˻���ͣ��<br>8.�л�����ģʽΪ����ģʽ��<br><p>��ѡ���:</p><br>1.����ٶȲ���Ա�����趨��<br>2.���˻���ͣʱ�����Ա�����趨��<br>3.������ϱ�����Ķ�������Ա�����趨��<br>4.��Դ�㵽Ŀ�ĵ�ķ����ٶȿ����趨��<br>5.�����ٶȿ����趨��<br><p>�쳣���:</p><br>1.��ص������㣬�����ع��ϱ�����<br>2.����վ�����쳣��������ϱ�����<br>3.�ɵ�Ŀ��λ�ú�û���л�������ģʽ��<br></body></html>";
//		inforrightdetaillabel.setText(text);
//		inforrightdetaillabel.setFont(new Font("΢���ź�", Font.PLAIN, 13));
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

package com.horstmann.violet.application.consolepart;

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
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;

public class ValidationResultPanel extends JPanel{

	private MainFrame mainFrame;
	
	private JPanel titlepanel;
	
	private JPanel resultpanel;
	
	private JLabel titlelabel;
	private JPanel titleiconlabelpanel;
	private JLabel titleiconlabel1;
	private JLabel titleiconlabel2;
	
	private JPanel validationlabeltabpanel;
	private JPanel validationlabeltabpanel1;
	private JPanel validationlabeltabpanel2;
	private JPanel validationlabeltabpanel3;
	private JPanel validationlabeltabpanel4;
	private JButton validationlabeltab1;
	private JButton validationlabeltab2;
	private JButton validationlabeltab3;
	private JButton validationlabeltab4;
	
	private int validationlabeltabindex=1;
	
	private JPanel onevalidationresultpanel;
	private JPanel onenamepanel;
	private JLabel onenamelabel;
	private JPanel oneresultpanel;
	private JScrollPane oneresultscrollpanel;
	
	private JPanel twovalidationresultpanel;
	private JPanel twonamepanel;
	private JLabel twonamelabel;
	private JPanel tworesultpanel;
	private JScrollPane tworesultscrollpanel;
	
	private JPanel threevalidationresultpanel;
	private JPanel threenamepanel;
	private JLabel threenamelabel;
	private JPanel threeresultpanel;
	private JScrollPane threeresultscrollpanel;
	
	private JPanel fourvalidationresultpanel;
	private JPanel fournamepanel;
	private JLabel fournamelabel;
	private JPanel fourresultpanel;
	private JScrollPane fourresultscrollpanel;
	
	private JPanel statelocationpanel;
	private JPanel statelocationlabelpanel;
	private JLabel statelocationlabel;
	private JPanel statelocationtoolpanel;
	private JButton statelocationtoolbutton1;
	private JPanel statelocationresultpanel;
	private JScrollPane statelocationscrollpanel;
	
	private JPanel messagetransitionpanel;
	private JPanel messagetransitionlabelpanel;
	private JLabel messagetransitionlabel;
	private JPanel messagetransitiontoolpanel;
	private JButton messagetransitiontoolbutton1;
	private JPanel messagetransitionresultpanel;
	private JScrollPane messagetransitionscrollpanel;
	
	
	public ValidationResultPanel(MainFrame mainFrame){
		
		this.mainFrame = mainFrame;
		
		this.setBackground(new Color(255, 255, 255));
		
		init();
		
	}

	private void init() {
		// TODO Auto-generated method stub
		
		titlepanel=new JPanel();
		resultpanel=new JPanel();
		
		titlelabel = new JLabel();
		titleiconlabelpanel=new JPanel();
		titleiconlabel1 = new JLabel();
		titleiconlabel2 = new JLabel();
		
		initOneValidationResultPanel();
		
		initTwoValidationResultPanel();
		
		initThreeValidationResultPanel();
		
		initFourValidationResultPanel();
		
		initTitlePanel();
		
		initTabPanel();
		
		initResulePanel();
		
		titlepanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(142, 155, 188)));
		validationlabeltabpanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, new Color(142, 155, 188)));
		resultpanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(142, 155, 188)));
		
		GridBagLayout layout=new GridBagLayout();
		this.setLayout(layout);
		this.add(titlepanel);
		this.add(validationlabeltabpanel);
		this.add(resultpanel);
		layout.setConstraints(titlepanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(validationlabeltabpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(resultpanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();
		
		this.setMinimumSize(new Dimension(screenWidth/7, screenHeight-150));
		this.setMaximumSize(new Dimension(screenWidth/6, screenHeight-150));
		
	}

	private void initFourValidationResultPanel() {
		// TODO Auto-generated method stub
		
		fourvalidationresultpanel=new JPanel();
		
		fournamepanel=new JPanel();
		fournamelabel=new JLabel();
		fourresultpanel=new JPanel();
		
		fournamepanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(142, 155, 188)));
		
		initFourNamePanel();
		
		initFourResulePanel();
		
		GridBagLayout layout=new GridBagLayout();
		fourvalidationresultpanel.setLayout(layout);
		fourvalidationresultpanel.add(fournamepanel);
		fourvalidationresultpanel.add(fourresultscrollpanel);
		layout.setConstraints(fournamepanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(fourresultscrollpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
	}

	private void initFourNamePanel() {
		// TODO Auto-generated method stub
		
		fournamelabel.setText("时间刻度显示： ");
		fournamelabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		fournamelabel.setForeground(new Color(0, 102, 204));
		fournamelabel.setBorder(BorderFactory.createEmptyBorder(2, 6, 2, 0));
		
		fournamepanel.setLayout(new BorderLayout());
		fournamepanel.setBackground(new Color(255,255,255));
		fournamepanel.setPreferredSize(new Dimension(200, 60));
		fournamepanel.setMinimumSize(new Dimension(100, 60));
		fournamepanel.add(fournamelabel,BorderLayout.WEST);
		
	}

	private void initFourResulePanel() {
		// TODO Auto-generated method stub
		
		fourresultpanel.setLayout(new GridLayout());

		fourresultpanel.setBackground(new Color(255, 255, 255));
		
		fourresultscrollpanel=new JScrollPane(fourresultpanel);
		fourresultscrollpanel.setBorder(null);
		
	}

	private void initThreeValidationResultPanel() {
		// TODO Auto-generated method stub
		
		threevalidationresultpanel=new JPanel();
		
		threenamepanel=new JPanel();
		threenamelabel=new JLabel();
		threeresultpanel=new JPanel();
		
		threenamepanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(142, 155, 188)));
		
		initThreeNamePanel();
		
		initThreeResulePanel();
		
		GridBagLayout layout=new GridBagLayout();
		threevalidationresultpanel.setLayout(layout);
		threevalidationresultpanel.add(threenamepanel);
		threevalidationresultpanel.add(threeresultpanel);
		layout.setConstraints(threenamepanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(threeresultpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
	}

	private void initThreeNamePanel() {
		// TODO Auto-generated method stub
		
		threenamelabel.setText(" 测试对比结果显示： ");
		threenamelabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		threenamelabel.setForeground(new Color(0, 102, 204));
		threenamelabel.setBorder(BorderFactory.createEmptyBorder(2, 6, 2, 0));
		
		threenamepanel.setLayout(new BorderLayout());
		threenamepanel.setBackground(new Color(255,255,255));
		threenamepanel.setPreferredSize(new Dimension(200, 45));
		threenamepanel.setMinimumSize(new Dimension(100, 45));
		threenamepanel.add(threenamelabel,BorderLayout.WEST);
		
	}

	private void initThreeResulePanel() {
		// TODO Auto-generated method stub
		
//		threeresultpanel.setLayout(new GridLayout());
//
//		threeresultpanel.setBackground(new Color(255, 255, 255));
//		
//		threeresultscrollpanel=new JScrollPane(threeresultpanel);
//		threeresultscrollpanel.setBorder(null);
		
		statelocationpanel=new JPanel();
		statelocationlabelpanel=new JPanel();
		statelocationlabel=new JLabel();
		statelocationtoolpanel=new JPanel();
		statelocationtoolbutton1=new JButton();
		statelocationresultpanel=new JPanel();
		
		messagetransitionpanel=new JPanel();
		messagetransitionlabelpanel=new JPanel();
		messagetransitionlabel=new JLabel();
		messagetransitiontoolpanel=new JPanel();
		messagetransitiontoolbutton1=new JButton();
		messagetransitionresultpanel=new JPanel();
		
		initStateLocationPanel();
		
		initMessageTransitionPanel();
		
		GridBagLayout layout=new GridBagLayout();
		threeresultpanel.setLayout(layout);
		threeresultpanel.add(statelocationlabelpanel);
		threeresultpanel.add(statelocationscrollpanel);
		threeresultpanel.add(messagetransitionpanel);
		layout.setConstraints(statelocationlabelpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(statelocationscrollpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(messagetransitionpanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		
	}

	private void initStateLocationPanel() {
		// TODO Auto-generated method stub
		
		statelocationlabel.setText("时序图的状态与时间自动机的节点比较");
		statelocationlabel.setForeground(new Color(0,0,0));
		statelocationlabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		statelocationlabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "dropdown.png");
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));

		statelocationtoolbutton1.setIcon(icon1);
		statelocationtoolbutton1.setFocusable(false);
		statelocationtoolbutton1.setContentAreaFilled(false);
		statelocationtoolbutton1.setBorderPainted(false);
		statelocationtoolbutton1.addMouseListener(new ButtonMouseListener());
		statelocationtoolbutton1.setPreferredSize(new Dimension(21,21));
		statelocationtoolbutton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if(statelocationscrollpanel.isVisible()){
					statelocationscrollpanel.setVisible(false);
					ChangeRepaint();
				}
				else {
					statelocationscrollpanel.setVisible(true);
					ChangeRepaint();
				}
				
			}
		});
		
		statelocationtoolpanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,4));
		statelocationtoolpanel.setOpaque(false);
		statelocationtoolpanel.add(statelocationtoolbutton1);
		
		statelocationlabelpanel.setBackground(new Color(207, 214, 229));
		statelocationlabelpanel.setLayout(new BorderLayout());
		statelocationlabelpanel.add(statelocationlabel, BorderLayout.WEST);
		statelocationlabelpanel.add(statelocationtoolpanel, BorderLayout.EAST);
		statelocationlabelpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(142, 155, 188)));
		statelocationlabelpanel.setPreferredSize(new Dimension(100, 29));
		statelocationlabelpanel.setMaximumSize(new Dimension(100, 29));
		statelocationlabelpanel.setMinimumSize(new Dimension(100, 29));
		
		statelocationresultpanel.setLayout(new GridLayout());
		statelocationresultpanel.setBackground(new Color(255, 255, 255));
		statelocationscrollpanel=new JScrollPane(statelocationresultpanel);
		statelocationscrollpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(142, 155, 188)));
		statelocationscrollpanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		statelocationscrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		
	}

	private void initMessageTransitionPanel() {
		// TODO Auto-generated method stub
		
		messagetransitionlabel.setText("时序图的消息与时间自动机的消息比较");
		messagetransitionlabel.setForeground(new Color(0,0,0));
		messagetransitionlabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		messagetransitionlabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "dropdown.png");
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));

		messagetransitiontoolbutton1.setIcon(icon1);
		messagetransitiontoolbutton1.setFocusable(false);
		messagetransitiontoolbutton1.setContentAreaFilled(false);
		messagetransitiontoolbutton1.setBorderPainted(false);
		messagetransitiontoolbutton1.addMouseListener(new ButtonMouseListener());
		messagetransitiontoolbutton1.setPreferredSize(new Dimension(21,21));
		messagetransitiontoolbutton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if(messagetransitionscrollpanel.isVisible()){
					messagetransitionscrollpanel.setVisible(false);
					ChangeRepaint();
				}
				else {
					messagetransitionscrollpanel.setVisible(true);
					ChangeRepaint();
				}
				
			}
		});
		
		messagetransitiontoolpanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,4));
		messagetransitiontoolpanel.setOpaque(false);
		messagetransitiontoolpanel.add(messagetransitiontoolbutton1);
		
		messagetransitionlabelpanel.setBackground(new Color(207, 214, 229));
		messagetransitionlabelpanel.setLayout(new BorderLayout());
		messagetransitionlabelpanel.add(messagetransitionlabel, BorderLayout.WEST);
		messagetransitionlabelpanel.add(messagetransitiontoolpanel, BorderLayout.EAST);
		messagetransitionlabelpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(142, 155, 188)));
		messagetransitionlabelpanel.setPreferredSize(new Dimension(100, 29));
		messagetransitionlabelpanel.setMaximumSize(new Dimension(100, 29));
		messagetransitionlabelpanel.setMinimumSize(new Dimension(100, 29));
		
		messagetransitionresultpanel.setLayout(new GridLayout());
		messagetransitionresultpanel.setBackground(new Color(255, 255, 255));
		messagetransitionscrollpanel=new JScrollPane(messagetransitionresultpanel);
		messagetransitionscrollpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(142, 155, 188)));
		messagetransitionscrollpanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		messagetransitionscrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		
		messagetransitionpanel.setLayout(new BorderLayout());
		messagetransitionpanel.add(messagetransitionlabelpanel,BorderLayout.NORTH);
		messagetransitionpanel.add(messagetransitionscrollpanel,BorderLayout.CENTER);

		
	}

	private void initOneValidationResultPanel() {
		// TODO Auto-generated method stub
		
		onevalidationresultpanel=new JPanel();
		
		onenamepanel=new JPanel();
		onenamelabel=new JLabel();
		oneresultpanel=new JPanel();
		
		onenamepanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(142, 155, 188)));
		
		initOneNamePanel();
		
		initOneResulePanel();
		
		GridBagLayout layout=new GridBagLayout();
		onevalidationresultpanel.setLayout(layout);
		onevalidationresultpanel.add(onenamepanel);
		onevalidationresultpanel.add(oneresultscrollpanel);
		layout.setConstraints(onenamepanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(oneresultscrollpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		
	}

	private void initOneResulePanel() {
		// TODO Auto-generated method stub
		
		oneresultpanel.setLayout(new GridLayout());

		oneresultpanel.setBackground(new Color(255, 255, 255));
		
		oneresultscrollpanel=new JScrollPane(oneresultpanel);
		oneresultscrollpanel.setBorder(null);
		
	}

	private void initOneNamePanel() {
		// TODO Auto-generated method stub
		
		onenamelabel.setText("  ");
		onenamelabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		onenamelabel.setForeground(new Color(0, 102, 204));
		onenamelabel.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 0));
		
		onenamepanel.setLayout(new BorderLayout());
		onenamepanel.setBackground(new Color(255,255,255));
		onenamepanel.setPreferredSize(new Dimension(200, 25));
		onenamepanel.setMinimumSize(new Dimension(100, 25));
		onenamepanel.add(onenamelabel,BorderLayout.WEST);
		
	}
	
	private void initTwoValidationResultPanel() {
		// TODO Auto-generated method stub
		
		twovalidationresultpanel=new JPanel();
		
		twonamepanel=new JPanel();
		twonamelabel=new JLabel();
		tworesultpanel=new JPanel();
		
		twonamepanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(142, 155, 188)));
		
		initTwoNamePanel();
		
		initTwoResulePanel();
		
		GridBagLayout layout=new GridBagLayout();
		twovalidationresultpanel.setLayout(layout);
		twovalidationresultpanel.add(twonamepanel);
		twovalidationresultpanel.add(tworesultscrollpanel);
		layout.setConstraints(twonamepanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(tworesultscrollpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		
	}

	private void initTwoResulePanel() {
		// TODO Auto-generated method stub
		
		tworesultpanel.setLayout(new GridLayout());

		tworesultpanel.setBackground(new Color(255, 255, 255));
		
		tworesultscrollpanel=new JScrollPane(tworesultpanel);
		tworesultscrollpanel.setBorder(null);
		
	}

	private void initTwoNamePanel() {
		// TODO Auto-generated method stub
		
		twonamelabel.setText("  ");
		twonamelabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		twonamelabel.setForeground(new Color(0, 102, 204));
		twonamelabel.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 0));
		
		twonamepanel.setLayout(new BorderLayout());
		twonamepanel.setBackground(new Color(255,255,255));
		twonamepanel.setPreferredSize(new Dimension(200, 25));
		twonamepanel.setMinimumSize(new Dimension(100, 25));
		twonamepanel.add(twonamelabel,BorderLayout.WEST);
		
	}

	private void initTitlePanel() {
		// TODO Auto-generated method stub
		
		titlelabel.setText("验证结果");
		titlelabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		titlelabel.setForeground(new Color(255, 255, 255));
		titlelabel.setBorder(BorderFactory.createEmptyBorder(1, 8, 1, 0));
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "downarrow.png");
		icon1.setImage(icon1.getImage().getScaledInstance(7,11, Image.SCALE_DEFAULT));
		titleiconlabel1.setIcon(icon1);
		titleiconlabel1.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 6));
		ImageIcon icon2 = new ImageIcon(path + "fork.png");
		icon2.setImage(icon2.getImage().getScaledInstance(10,8, Image.SCALE_DEFAULT));
		titleiconlabel2.setIcon(icon2);
		titleiconlabel2.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		
		titleiconlabel2.addMouseListener(new MouseListener() {
			
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
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				mainFrame.getJs2().setDividerSize(0);
				mainFrame.getAttributePart().setVisible(false);
				mainFrame.getOneTouchExpandablePanel().setFlag3(0);
				mainFrame.getOneTouchExpandablePanel().setLocation3(mainFrame.getJs2().getDividerLocation());
				
			}
		});
		
		titleiconlabelpanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		titleiconlabelpanel.setOpaque(false);
		titleiconlabelpanel.add(titleiconlabel1);
		titleiconlabelpanel.add(titleiconlabel2);

		titlepanel.setLayout(new BorderLayout());
		titlepanel.setBackground(new Color(77, 96, 130));
		titlepanel.setPreferredSize(new Dimension(100, 24));
		titlepanel.setMinimumSize(new Dimension(100, 24));
		titlepanel.add(titlelabel,BorderLayout.WEST);
		titlepanel.add(titleiconlabelpanel,BorderLayout.EAST);
		
	}
	
	private void initTabPanel() {
		// TODO Auto-generated method stub
		
		validationlabeltabpanel=new JPanel();
		validationlabeltabpanel1=new JPanel();
		validationlabeltabpanel2=new JPanel();
		validationlabeltabpanel3=new JPanel();
		validationlabeltabpanel4=new JPanel();
		
		validationlabeltab1=new JButton();
		validationlabeltab2=new JButton();
		validationlabeltab3=new JButton();
		validationlabeltab4=new JButton();
		
		validationlabeltab1.setText("存在结果");
		validationlabeltab1.setForeground(new Color(0,0,0));
		validationlabeltab1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		validationlabeltab1.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));
		validationlabeltab1.setFocusable(false);
		validationlabeltab1.setContentAreaFilled(false);
		validationlabeltab1.setBorderPainted(false);
		validationlabeltab1.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				validationlabeltab1.setForeground(new Color(0, 0, 0));
				validationlabeltabpanel1.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (validationlabeltabindex != 1) {
					validationlabeltab1.setForeground(new Color(255, 255, 255));
					validationlabeltabpanel1.setBackground(new Color(77, 96, 130));
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (validationlabeltabindex != 1) {
					validationlabeltabpanel1.setBackground(new Color(134, 161, 209));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				setvalidationlabeltabpanelrepait();
				validationlabeltab1.setForeground(new Color(0, 0, 0));
				validationlabeltabpanel1.setBackground(new Color(255, 255, 255));
				validationlabeltabpanel1.setBorder(BorderFactory.createMatteBorder(0,0,0,1, new Color(142, 155, 188)));
				validationlabeltabindex = 1;
				
				resultpanel.removeAll();
				resultpanel.add(onevalidationresultpanel);
				if(mainFrame.getModelExistValidationPanel().getValidationlabeltabindex()!=1){
					mainFrame.getModelExistValidationPanel().getValidationlabeltab1().doClick();
				}
				
				ChangeRepaint();
				
			}
		});
		
		validationlabeltab1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				setvalidationlabeltabpanelrepait();
				validationlabeltab1.setForeground(new Color(0, 0, 0));
				validationlabeltabpanel1.setBackground(new Color(255, 255, 255));
				validationlabeltabpanel1.setBorder(BorderFactory.createMatteBorder(0,0,0,1, new Color(142, 155, 188)));
				validationlabeltabindex = 1;
				
				resultpanel.removeAll();
				resultpanel.add(onevalidationresultpanel);
				if(mainFrame.getModelExistValidationPanel().getValidationlabeltabindex()!=1){
					mainFrame.getModelExistValidationPanel().getValidationlabeltab1().doClick();
				}
				
				ChangeRepaint();
				
			}
		});
		
		validationlabeltab2.setText("顺序结果");
		validationlabeltab2.setForeground(new Color(255, 255, 255));
		validationlabeltab2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		validationlabeltab2.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));
		validationlabeltab2.setFocusable(false);
		validationlabeltab2.setContentAreaFilled(false);
		validationlabeltab2.setBorderPainted(false);
		validationlabeltab2.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				validationlabeltab2.setForeground(new Color(0, 0, 0));
				validationlabeltabpanel2.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (validationlabeltabindex != 2) {
					validationlabeltab2.setForeground(new Color(255, 255, 255));
					validationlabeltabpanel2.setBackground(new Color(77, 96, 130));
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (validationlabeltabindex != 2) {
					validationlabeltabpanel2.setBackground(new Color(134, 161, 209));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				setvalidationlabeltabpanelrepait();
				validationlabeltab2.setForeground(new Color(0, 0, 0));
				validationlabeltabpanel2.setBackground(new Color(255, 255, 255));
				validationlabeltabpanel2.setBorder(BorderFactory.createMatteBorder(0,0,0,1, new Color(142, 155, 188)));
				validationlabeltabindex = 2;
				
				resultpanel.removeAll();
				resultpanel.add(twovalidationresultpanel);
				if(mainFrame.getModelExistValidationPanel().getValidationlabeltabindex()!=2){
					mainFrame.getModelExistValidationPanel().getValidationlabeltab2().doClick();
				}
				
				ChangeRepaint();
				
			}
		});
		
		validationlabeltab2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				setvalidationlabeltabpanelrepait();
				validationlabeltab2.setForeground(new Color(0, 0, 0));
				validationlabeltabpanel2.setBackground(new Color(255, 255, 255));
				validationlabeltabpanel2.setBorder(BorderFactory.createMatteBorder(0,0,0,1, new Color(142, 155, 188)));
				validationlabeltabindex = 2;
				
				resultpanel.removeAll();
				resultpanel.add(twovalidationresultpanel);
				if(mainFrame.getModelExistValidationPanel().getValidationlabeltabindex()!=2){
					mainFrame.getModelExistValidationPanel().getValidationlabeltab2().doClick();
				}
				
				ChangeRepaint();
				
			}
		});
		
		validationlabeltab3.setText("对比结果");
		validationlabeltab3.setForeground(new Color(255, 255, 255));
		validationlabeltab3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		validationlabeltab3.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));
		validationlabeltab3.setFocusable(false);
		validationlabeltab3.setContentAreaFilled(false);
		validationlabeltab3.setBorderPainted(false);
		validationlabeltab3.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				validationlabeltab3.setForeground(new Color(0, 0, 0));
				validationlabeltabpanel3.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (validationlabeltabindex != 3) {
					validationlabeltab3.setForeground(new Color(255, 255, 255));
					validationlabeltabpanel3.setBackground(new Color(77, 96, 130));
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (validationlabeltabindex != 3) {
					validationlabeltabpanel3.setBackground(new Color(134, 161, 209));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				setvalidationlabeltabpanelrepait();
				validationlabeltab3.setForeground(new Color(0, 0, 0));
				validationlabeltabpanel3.setBackground(new Color(255, 255, 255));
				validationlabeltabpanel3.setBorder(BorderFactory.createMatteBorder(0,1,0,1, new Color(142, 155, 188)));
				validationlabeltabindex = 3;
				
				resultpanel.removeAll();
				resultpanel.add(threevalidationresultpanel);
				if(mainFrame.getModelExistValidationPanel().getValidationlabeltabindex()!=3){
					mainFrame.getModelExistValidationPanel().getValidationlabeltab3().doClick();
				}
				
				ChangeRepaint();
			}
		});
		
		validationlabeltab3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				setvalidationlabeltabpanelrepait();
				validationlabeltab3.setForeground(new Color(0, 0, 0));
				validationlabeltabpanel3.setBackground(new Color(255, 255, 255));
				validationlabeltabpanel3.setBorder(BorderFactory.createMatteBorder(0,1,0,1, new Color(142, 155, 188)));
				validationlabeltabindex = 3;
				
				resultpanel.removeAll();
				resultpanel.add(threevalidationresultpanel);
				if(mainFrame.getModelExistValidationPanel().getValidationlabeltabindex()!=3){
					mainFrame.getModelExistValidationPanel().getValidationlabeltab3().doClick();
				}
				
				ChangeRepaint();
				
			}
		});
		
		validationlabeltab4.setText("时间刻度");
		validationlabeltab4.setForeground(new Color(255, 255, 255));
		validationlabeltab4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		validationlabeltab4.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));
		validationlabeltab4.setFocusable(false);
		validationlabeltab4.setContentAreaFilled(false);
		validationlabeltab4.setBorderPainted(false);
		validationlabeltab4.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				validationlabeltab4.setForeground(new Color(0, 0, 0));
				validationlabeltabpanel4.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (validationlabeltabindex != 4) {
					validationlabeltab4.setForeground(new Color(255, 255, 255));
					validationlabeltabpanel4.setBackground(new Color(77, 96, 130));
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (validationlabeltabindex != 4) {
					validationlabeltabpanel4.setBackground(new Color(134, 161, 209));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				setvalidationlabeltabpanelrepait();
				validationlabeltab4.setForeground(new Color(0, 0, 0));
				validationlabeltabpanel4.setBackground(new Color(255, 255, 255));
				validationlabeltabpanel4.setBorder(BorderFactory.createMatteBorder(0,1,0,1, new Color(142, 155, 188)));
				validationlabeltabindex = 4;
				
				resultpanel.removeAll();
				resultpanel.add(fourvalidationresultpanel);
				if(mainFrame.getModelExistValidationPanel().getValidationlabeltabindex()!=4){
					mainFrame.getModelExistValidationPanel().getValidationlabeltab4().doClick();
				}
				
				ChangeRepaint();
			}
		});
		
		validationlabeltab4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				setvalidationlabeltabpanelrepait();
				validationlabeltab4.setForeground(new Color(0, 0, 0));
				validationlabeltabpanel4.setBackground(new Color(255, 255, 255));
				validationlabeltabpanel4.setBorder(BorderFactory.createMatteBorder(0,1,0,1, new Color(142, 155, 188)));
				validationlabeltabindex = 4;
				
				resultpanel.removeAll();
				resultpanel.add(fourvalidationresultpanel);
				if(mainFrame.getModelExistValidationPanel().getValidationlabeltabindex()!=4){
					mainFrame.getModelExistValidationPanel().getValidationlabeltab4().doClick();
				}
				
				ChangeRepaint();
				
			}
		});
		
		validationlabeltabpanel1.setLayout(new GridLayout());
		validationlabeltabpanel1.setBackground(new Color(255,255,255));
		validationlabeltabpanel1.setBorder(BorderFactory.createMatteBorder(0,0,0,1, new Color(142, 155, 188)));
		validationlabeltabpanel1.setPreferredSize(new Dimension(60, 30));
		validationlabeltabpanel1.add(validationlabeltab1);
		validationlabeltabpanel2.setLayout(new GridLayout());
		validationlabeltabpanel2.setBackground(new Color(77, 96, 130));
		validationlabeltabpanel2.setPreferredSize(new Dimension(60, 30));
		validationlabeltabpanel2.add(validationlabeltab2);
		validationlabeltabpanel3.setLayout(new GridLayout());
		validationlabeltabpanel3.setBackground(new Color(77, 96, 130));
		validationlabeltabpanel3.setPreferredSize(new Dimension(60, 30));
		validationlabeltabpanel3.add(validationlabeltab3);
		validationlabeltabpanel4.setLayout(new GridLayout());
		validationlabeltabpanel4.setBackground(new Color(77, 96, 130));
		validationlabeltabpanel4.setPreferredSize(new Dimension(60, 30));
		validationlabeltabpanel4.add(validationlabeltab4);

		validationlabeltabpanel.setBackground(new Color(41, 57, 85));
		validationlabeltabpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		validationlabeltabpanel.add(validationlabeltabpanel1);
		validationlabeltabpanel.add(validationlabeltabpanel2);
		validationlabeltabpanel.add(validationlabeltabpanel3);
		validationlabeltabpanel.add(validationlabeltabpanel4);
		
		validationlabeltabpanel.setPreferredSize(new Dimension(100, 30));
		validationlabeltabpanel.setMinimumSize(new Dimension(100, 30));
		
	}
	
	protected void setvalidationlabeltabpanelrepait() {
		// TODO Auto-generated method stub
		
		validationlabeltab1.setForeground(new Color(255, 255, 255));
		validationlabeltabpanel1.setBackground(new Color(77, 96, 130));
		validationlabeltabpanel1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(77, 96, 130)));
		validationlabeltab2.setForeground(new Color(255, 255, 255));
		validationlabeltabpanel2.setBackground(new Color(77, 96, 130));
		validationlabeltabpanel2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(77, 96, 130)));
		validationlabeltab3.setForeground(new Color(255, 255, 255));
		validationlabeltabpanel3.setBackground(new Color(77, 96, 130));
		validationlabeltabpanel3.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(77, 96, 130)));
		validationlabeltab4.setForeground(new Color(255, 255, 255));
		validationlabeltabpanel4.setBackground(new Color(77, 96, 130));
		validationlabeltabpanel4.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(77, 96, 130)));

	}

	private void initResulePanel() {
		// TODO Auto-generated method stub
		
		resultpanel.setLayout(new GridLayout());
		resultpanel.add(onevalidationresultpanel);
		
	}
	
	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}

	
	public JLabel getOnenamelabel() {
		return onenamelabel;
	}

	public JPanel getOneresultpanel() {
		return oneresultpanel;
	}
	
	public JLabel getTwonamelabel() {
		return twonamelabel;
	}

	public JPanel getTworesultpanel() {
		return tworesultpanel;
	}

	public JLabel getThreenamelabel() {
		return threenamelabel;
	}

	public JPanel getThreeresultpanel() {
		return threeresultpanel;
	}

	public JLabel getFournamelabel() {
		return fournamelabel;
	}

	public JPanel getFourresultpanel() {
		return fourresultpanel;
	}
	
	public JPanel getStatelocationresultpanel() {
		return statelocationresultpanel;
	}

	public JPanel getMessagetransitionresultpanel() {
		return messagetransitionresultpanel;
	}

	public int getValidationlabeltabindex() {
		return validationlabeltabindex;
	}

	public void setValidationlabeltabindex(int validationlabeltabindex) {
		this.validationlabeltabindex = validationlabeltabindex;
	}

	public JButton getValidationlabeltab1() {
		return validationlabeltab1;
	}

	public JButton getValidationlabeltab2() {
		return validationlabeltab2;
	}
	
	public JButton getValidationlabeltab3() {
		return validationlabeltab3;
	}

	public JButton getValidationlabeltab4() {
		return validationlabeltab4;
	}
	
	
	
}


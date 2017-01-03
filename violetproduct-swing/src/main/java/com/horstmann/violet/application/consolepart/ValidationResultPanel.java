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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
	private JLabel validationlabeltab1;
	private JLabel validationlabeltab2;
	
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
		
		this.setMinimumSize(new Dimension(screenWidth/8, screenHeight-150));
		this.setMaximumSize(new Dimension(screenWidth/7, screenHeight-150));
		
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

	private void initTwoNamePanel() {
		// TODO Auto-generated method stub
		
		twonamelabel.setText(" 测试对比结果显示： ");
		twonamelabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		twonamelabel.setForeground(new Color(0, 102, 204));
		twonamelabel.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 0));
		
		twonamepanel.setLayout(new BorderLayout());
		twonamepanel.setBackground(new Color(255,255,255));
		twonamepanel.setPreferredSize(new Dimension(200, 25));
		twonamepanel.setMinimumSize(new Dimension(100, 25));
		twonamepanel.add(twonamelabel,BorderLayout.WEST);
		
	}

	private void initTwoResulePanel() {
		// TODO Auto-generated method stub
		
		tworesultpanel.setLayout(new GridLayout());

//		tworesultpanel.setBackground(new Color(255, 255, 255));
		tworesultpanel.setBackground(new Color(5, 255, 255));
		
		tworesultscrollpanel=new JScrollPane(tworesultpanel);
		tworesultscrollpanel.setBorder(null);
		
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
		
		validationlabeltab1=new JLabel();
		validationlabeltab2=new JLabel();
		
		validationlabeltab1.setText("属性结果");
		validationlabeltab1.setForeground(new Color(0,0,0));
		validationlabeltab1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		validationlabeltab1.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		validationlabeltab1.setFocusable(false);
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
				validationlabeltabpanel1.setBorder(BorderFactory.createMatteBorder(0,0,1,1, new Color(142, 155, 188)));
				validationlabeltabindex = 1;
				
				resultpanel.removeAll();
				resultpanel.add(onevalidationresultpanel);
				
				ChangeRepaint();
				
//				mainFrame.setVisible(false);
//				mainFrame.getContentPane().repaint();
//				mainFrame.setVisible(true);
				
			}
		});
		
		validationlabeltab2.setText("对比结果");
		validationlabeltab2.setForeground(new Color(255, 255, 255));
		validationlabeltab2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		validationlabeltab2.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		validationlabeltab2.setFocusable(false);
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
				validationlabeltabpanel2.setBorder(BorderFactory.createMatteBorder(0,1,1,1, new Color(142, 155, 188)));
				validationlabeltabindex = 2;
				
				resultpanel.removeAll();
				resultpanel.add(twovalidationresultpanel);
				
				ChangeRepaint();
			}
		});
		
		validationlabeltabpanel1.setLayout(new GridLayout());
		validationlabeltabpanel1.setBackground(new Color(255,255,255));
		validationlabeltabpanel1.setBorder(BorderFactory.createMatteBorder(0,0,1,1, new Color(142, 155, 188)));
		validationlabeltabpanel1.setPreferredSize(new Dimension(60, 30));
		validationlabeltabpanel1.add(validationlabeltab1);
		validationlabeltabpanel2.setLayout(new GridLayout());
		validationlabeltabpanel2.setBackground(new Color(77, 96, 130));
		validationlabeltabpanel2.setPreferredSize(new Dimension(60, 30));
		validationlabeltabpanel2.add(validationlabeltab2);

		validationlabeltabpanel.setBackground(new Color(41, 57, 85));
		validationlabeltabpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		validationlabeltabpanel.add(validationlabeltabpanel1);
		validationlabeltabpanel.add(validationlabeltabpanel2);
		
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
		validationlabeltabpanel2.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(77, 96, 130)));

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

	
	
	public JLabel getOneNamelabel() {
		return onenamelabel;
	}

	public JPanel getOneResultpanel() {
		return oneresultpanel;
	}

//	public JTextArea getResultarea() {
//		return resultarea;
//	}
	
	
	
}


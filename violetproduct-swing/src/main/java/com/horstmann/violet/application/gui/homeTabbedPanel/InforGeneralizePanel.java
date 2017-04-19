package com.horstmann.violet.application.gui.homeTabbedPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.SwingConstants;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;

public class InforGeneralizePanel extends JPanel{

	private MainFrame mainFrame;
	
	private String name;
	
	private JPanel imagelabelpanel;
	private JLabel imagelabel;
	
	private JPanel inforpanel;
	private JLabel inforlabel;
	
	private JPanel namepanel;
	private JLabel namelabel;
	
	private JPanel linepanel;
	
	private JPanel introducepanel;
	private JLabel introducelabel;
	
	private JPanel linkpanel;
	private JLabel linklabel;

	
	public InforGeneralizePanel(MainFrame mainFrame, String name){
		
		this.mainFrame=mainFrame;
		
		this.name=name;
		
		imagelabelpanel=new JPanel();
		inforpanel=new JPanel();
		
		initImagePanel();
		
		initInforPanel();
		
		GridBagLayout layout=new GridBagLayout();
		this.setLayout(layout);
		this.add(imagelabelpanel);
		this.add(inforpanel);
		layout.setConstraints(imagelabelpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
		layout.setConstraints(inforpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final int screenWidth = (int) screenSize.getWidth();
		final int screenHeight = (int) screenSize.getHeight();
		System.out.println(screenWidth+" - - "+screenHeight);
		
		this.setBorder(BorderFactory.createEmptyBorder(screenHeight/6, 10, screenHeight/2, 10));
		
		this.setBackground(new Color(255, 255, 255));
		
	}

	private void initImagePanel() {
		// TODO Auto-generated method stub
		
		imagelabel=new JLabel();
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon = new ImageIcon(path + "inforgeneralize.png");
		icon.setImage(icon.getImage().getScaledInstance(203,157, Image.SCALE_DEFAULT));
		
		imagelabel.setIcon(icon);
//		imagelabel.setBorder(BorderFactory.createEmptyBorder(75, 30, 0, 20));
		
		imagelabelpanel.setLayout(new BorderLayout());
//		imagelabelpanel.setOpaque(false);
		imagelabelpanel.add(imagelabel,BorderLayout.NORTH);
		
	}

	private void initInforPanel() {
		// TODO Auto-generated method stub
		
		inforlabel=new JLabel();
		
		namepanel=new JPanel();
		namelabel=new JLabel();
		
		linepanel=new JPanel();
		
		introducepanel=new JPanel();
		introducelabel=new JLabel();
		
		linkpanel=new JPanel();
		linklabel=new JLabel();
		
		namelabel.setText(name);
		namelabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		namepanel.setLayout(new GridLayout());
		namepanel.add(namelabel);
		namepanel.setOpaque(false);
		namepanel.setBorder(BorderFactory.createEmptyBorder(3, 10, 6, 0));
		
		linepanel.setPreferredSize(new Dimension(200, 1));
		linepanel.setBackground(new Color(221, 220, 220));
		linepanel.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 5, new Color(255, 255, 255)));
		
		introducelabel.setText("<html><body>了解新功能<br>查看新增功能<br>了解有关的更多信息<body></html>");
		introducelabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		introducelabel.setForeground(new Color(0, 122, 204));
		introducelabel.setOpaque(false);
		introducepanel.setLayout(new GridLayout());
		introducepanel.add(introducelabel);
		introducepanel.setBackground(new Color(255, 255, 255));
		introducepanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 5));
		
		initLinkPanel();
		
//		inforlabel.setText("<html><body>"+name+"<br>了解详细功能，请点击</body></html>");
//		
//		inforpanel.setLayout(new GridLayout());
//		inforpanel.setOpaque(false);
//		inforpanel.add(inforlabel);
		
		GridBagLayout layout=new GridBagLayout();
		inforpanel.setLayout(layout);
		inforpanel.add(namepanel);
		inforpanel.add(linepanel);
		inforpanel.add(introducepanel);
		inforpanel.add(linkpanel);
		layout.setConstraints(namepanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
		layout.setConstraints(linepanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
		layout.setConstraints(introducepanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
		layout.setConstraints(linkpanel, new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
		inforpanel.setBackground(new Color(255, 255, 255));
		
	}

	private void initLinkPanel() {
		// TODO Auto-generated method stub
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon = new ImageIcon(path + "homelink.png");
		icon.setImage(icon.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		
		linklabel.setText("连接");
		linklabel.setIcon(icon);
		linklabel.setHorizontalTextPosition(SwingConstants.LEFT);
		linklabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		linklabel.setForeground(new Color(104, 33, 122));
//		linklabel.setBackground(new Color(255, 255, 255));
		linklabel.setOpaque(false);
		
		linkpanel.setLayout(new GridLayout());
		linkpanel.add(linklabel);
		linkpanel.setBackground(new Color(255, 255, 255));
		linkpanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
		
	}

	public JLabel getLinklabel() {
		return linklabel;
	}

	public void setLinklabel(JLabel linklabel) {
		this.linklabel = linklabel;
	}
	
	
	
}

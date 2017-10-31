package com.horstmann.violet.application.gui.homeTabbedPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.horstmann.violet.application.gui.GBC;

public class ImageCarouselPanel extends JPanel{
	
	private String path;

	private JScrollPane imagescrollpanel;
	private JPanel imagepanel;
	
	private JLabel imagelabel1;
	private JLabel imagelabel2;
	private JLabel imagelabel3;
	private JLabel imagelabel4;
	
	private JPanel leftbuttonpanel;
	private JPanel rightbuttonpanel;
	
	private JButton leftbutton;
	private JButton rightbutton;
	
	public ImageCarouselPanel(String path) {
		// TODO Auto-generated constructor stub
		
		this.path=path;
		
		initImagePanel();
		
		initLeftRightButtonPanel();
		
//		GridBagLayout layout=new GridBagLayout();
//		this.setLayout(layout);
//		this.add(leftbuttonpanel);
//		this.add(imagescrollpanel);
//		this.add(rightbuttonpanel);
//		layout.setConstraints(leftbuttonpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		layout.setConstraints(imagescrollpanel, new GBC(1, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		layout.setConstraints(rightbuttonpanel, new GBC(2, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		this.setLayout(new BorderLayout());
		this.add(leftbuttonpanel, BorderLayout.WEST);
		this.add(imagescrollpanel, BorderLayout.CENTER);
		this.add(rightbuttonpanel, BorderLayout.EAST);
		
		this.setBackground(new Color(255, 255, 255));
		
		
	}

	private void initImagePanel() {
		// TODO Auto-generated method stub
		
		imagescrollpanel=new JScrollPane();
		imagepanel=new JPanel();
		
//		String path = "D:\\image\\";
		
		File f=new File(path);
		int count=f.list().length;
		
		System.out.println(path+" - - - "+count);
		
//		imagepanel.setLayout(new GridLayout(1, 4));
		imagepanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		for(int i=1;i<=count;i++){
			
			ImageIcon icon = new ImageIcon(path + i + ".jpg");
			icon.setImage(icon.getImage().getScaledInstance(500, 400, Image.SCALE_DEFAULT));
			
			JLabel imagelabel=new JLabel();
			imagelabel.setIcon(icon);
			imagelabel.setOpaque(false);
			imagelabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
			
			imagepanel.add(imagelabel);
			
		}

//		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("ImagePart/1.jpg");
//		icon1.setImage(icon1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT));
//		ImageIcon icon2 = new ImageIcon(this.getClass().getResource("ImagePart/2.jpg");
//		icon2.setImage(icon2.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT));
//		ImageIcon icon3 = new ImageIcon(this.getClass().getResource("ImagePart/3.jpg");
//		icon3.setImage(icon3.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT));
//		ImageIcon icon4 = new ImageIcon(this.getClass().getResource("ImagePart/4.jpg");
//		icon4.setImage(icon4.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT));
//		
//		imagelabel1=new JLabel();
//		imagelabel1.setIcon(icon1);
//		imagelabel1.setOpaque(false);
//		imagelabel1.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
//		
//		imagelabel2=new JLabel();
//		imagelabel2.setIcon(icon2);
//		imagelabel2.setOpaque(false);
//		imagelabel2.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
//		
//		imagelabel3=new JLabel();
//		imagelabel3.setIcon(icon3);
//		imagelabel3.setOpaque(false);
//		imagelabel3.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
//		
//		imagelabel4=new JLabel();
//		imagelabel4.setIcon(icon4);
//		imagelabel4.setOpaque(false);
//		imagelabel4.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
//		
//		imagepanel.setLayout(new GridLayout(1, 4));
//		imagepanel.add(imagelabel1);
//		imagepanel.add(imagelabel2);
//		imagepanel.add(imagelabel3);
//		imagepanel.add(imagelabel4);
		
		imagepanel.setBackground(new Color(255, 255, 255));
		
		imagescrollpanel=new JScrollPane(imagepanel);
		imagescrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		imagescrollpanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		imagescrollpanel.setBorder(null);
		
		
	}

	private void initLeftRightButtonPanel() {
		// TODO Auto-generated method stub
		
		leftbuttonpanel=new JPanel();
		rightbuttonpanel=new JPanel();
		
		leftbutton=new JButton();
		rightbutton=new JButton();
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		final ImageIcon icon1 = new ImageIcon(this.getClass().getResource("ImagePart/image_left1.png"));
		icon1.setImage(icon1.getImage().getScaledInstance(50, 60, Image.SCALE_DEFAULT));
		final ImageIcon icon2 = new ImageIcon(this.getClass().getResource("ImagePart/image_left2.png"));
		icon2.setImage(icon2.getImage().getScaledInstance(50, 60, Image.SCALE_DEFAULT));
		final ImageIcon icon3 = new ImageIcon(this.getClass().getResource("ImagePart/image_right1.png"));
		icon3.setImage(icon3.getImage().getScaledInstance(50, 60, Image.SCALE_DEFAULT));
		final ImageIcon icon4 = new ImageIcon(this.getClass().getResource("ImagePart/image_right2.png"));
		icon4.setImage(icon4.getImage().getScaledInstance(50, 60, Image.SCALE_DEFAULT));
		
		leftbutton.setIcon(icon1);
		leftbutton.setFocusable(false);
		leftbutton.setContentAreaFilled(false);
		leftbutton.setBorderPainted(false);
		leftbutton.setPreferredSize(new Dimension(50,60));
		leftbutton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				leftbutton.setIcon(icon2);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				leftbutton.setIcon(icon1);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				leftbutton.setIcon(icon2);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		leftbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				JScrollBar bar=imagescrollpanel.getHorizontalScrollBar();
				bar.setValue(bar.getValue()-100);
				
			}
		});
		
		rightbutton.setIcon(icon3);
		rightbutton.setFocusable(false);
		rightbutton.setContentAreaFilled(false);
		rightbutton.setBorderPainted(false);
		rightbutton.setPreferredSize(new Dimension(50,60));
		rightbutton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				rightbutton.setIcon(icon4);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				rightbutton.setIcon(icon3);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				rightbutton.setIcon(icon4);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		rightbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				JScrollBar bar=imagescrollpanel.getHorizontalScrollBar();
				bar.setValue(bar.getValue()+100);
				
			}
		});
		
		leftbuttonpanel.setLayout(new BorderLayout());
		leftbuttonpanel.add(leftbutton, BorderLayout.EAST);
		leftbuttonpanel.setOpaque(false);
		leftbuttonpanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		
		rightbuttonpanel.setLayout(new BorderLayout());
		rightbuttonpanel.add(rightbutton, BorderLayout.WEST);
		rightbuttonpanel.setOpaque(false);
		rightbuttonpanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		
	}
	
}

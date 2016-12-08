package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MoviePanel extends JPanel{

	
	private JLabel movieLabel;
	
	public MoviePanel(){
		
		movieLabel=new JLabel();
		
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon = new ImageIcon(path + "movie.png");
		icon.setImage(icon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
		
		movieLabel.setIcon(icon);
		movieLabel.setText("流视频:通过代码图理解复杂代码--------");
		movieLabel.setForeground(new Color(0, 102, 204));
		movieLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		movieLabel.setBorder(BorderFactory.createEmptyBorder(3,8,4,0));
		
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(255,255,255));
		this.setPreferredSize(new Dimension(500, 27));
		this.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(142, 155, 188)));
		
		this.add(movieLabel, BorderLayout.WEST);
	}
	
	public JLabel getMovieLabel() {
		return movieLabel;
	}
	
}

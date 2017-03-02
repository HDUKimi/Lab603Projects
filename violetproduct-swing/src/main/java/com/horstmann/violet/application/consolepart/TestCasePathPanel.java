package com.horstmann.violet.application.consolepart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;

public class TestCasePathPanel extends JPanel{

	private JPanel titlepanel;
	private JPanel linepanel;
	private JPanel attributepanel;
	
	private JPanel titlelabelpanel;
	private JLabel iconlabel;
	private JLabel titlelabel;
	private JButton toolbutton;
	
	private JLabel linelabel;
	
	public TestCasePathPanel(){
		
		init();
		
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		this.setBackground(new Color(255, 255, 255));
		
	}

	private void init() {
		// TODO Auto-generated method stub
		
		titlepanel=new JPanel();
		linepanel=new JPanel();
		attributepanel=new JPanel();
		
		titlelabelpanel=new JPanel();
		iconlabel=new JLabel();
		titlelabel=new JLabel();
		toolbutton=new JButton();
		
		linelabel=new JLabel();
		
		initTitlePanel();
		
		initLinePanel();
		
		initAttributePanel();
		
		attributepanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(titlepanel);
		this.add(linepanel);
		this.add(attributepanel);
		
	}
	
	private void initTitlePanel() {
		// TODO Auto-generated method stub
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "test17.png");
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(path + "dropdown1.png");
		icon2.setImage(icon2.getImage().getScaledInstance(11,11, Image.SCALE_DEFAULT));
		
		titlelabel.setText("Â·¾¶1");
		titlelabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 12));
//		titlelabel.setForeground(new Color(250,0,60));
		titlelabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		
		iconlabel.setIcon(icon1);
		
		titlelabelpanel.setLayout(new FlowLayout(0, 0, FlowLayout.LEFT));
		titlelabelpanel.add(iconlabel);
		titlelabelpanel.add(titlelabel);
		titlelabelpanel.setOpaque(false);
		
		
		toolbutton.setIcon(icon2);
		toolbutton.setFocusable(false);
		toolbutton.setContentAreaFilled(false);
		toolbutton.setBorderPainted(false);
		toolbutton.addMouseListener(new ButtonMouseListener());
		toolbutton.setPreferredSize(new Dimension(21,21));
		toolbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(attributepanel.isVisible()){
					attributepanel.setVisible(false);
				}
				else{
					attributepanel.setVisible(true);
				}
			}
		});
		
		titlepanel.setLayout(new BorderLayout());
		titlepanel.add(titlelabelpanel,BorderLayout.WEST);
		titlepanel.add(toolbutton,BorderLayout.EAST);
//		titlepanel.setPreferredSize(new Dimension(100, 30));
		
		titlepanel.setOpaque(false);
		
	}

	private void initLinePanel() {
		// TODO Auto-generated method stub
		
		linelabel.setText("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
		linelabel.setPreferredSize(new Dimension(100, 3));
		linelabel.setForeground(new Color(223, 204, 221));
		
		linepanel.setLayout(new GridLayout());
		linepanel.add(linelabel);
		linepanel.setOpaque(false);
		
	}

	private void initAttributePanel() {
		// TODO Auto-generated method stub
		
//		attributepanel.setLayout(new GridLayout());
		attributepanel.setOpaque(false);
		
		GridBagLayout layout = new GridBagLayout();
		attributepanel.setLayout(layout);
		int i=0;
		for(int k=0;k<5;k++){
			
			TestCasePathStateInforPanel tcpsipanel=new TestCasePathStateInforPanel();
			attributepanel.add(tcpsipanel);
			layout.setConstraints(tcpsipanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
			
			TestCasePathTransitionInforPanel tcptipanel=new TestCasePathTransitionInforPanel();
			attributepanel.add(tcptipanel);
			layout.setConstraints(tcptipanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
			
		}
		
	}

	public JPanel getAttributepanel() {
		return attributepanel;
	}
	
}

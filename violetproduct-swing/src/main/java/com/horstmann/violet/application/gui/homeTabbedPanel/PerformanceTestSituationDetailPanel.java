package com.horstmann.violet.application.gui.homeTabbedPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PerformanceTestSituationDetailPanel extends JPanel{

	private String normaltext;
	private String abnormaltext;
	private String imagepath;
	
	private JPanel alldetaillabelpanel;
	private ImageCarouselPanel imagepanel;
	
	private JPanel detaillabelpanel1;
	private JPanel detaillabelpanel2;
	
	private JLabel detailheadlabel1;
	private JLabel detailheadlabel2;
	private JLabel detaillabel1;
	private JLabel detaillabel2;

	public PerformanceTestSituationDetailPanel(String normaltext, String abnormaltext, String imagepath) {
		// TODO Auto-generated constructor stub
		
		this.normaltext=normaltext;
		this.abnormaltext=abnormaltext;
		this.imagepath=imagepath;
		
		alldetaillabelpanel=new JPanel();
		imagepanel=new ImageCarouselPanel(imagepath);
		
		initAllDetailLabelPanel();
		
		initImagePanel();

		this.setLayout(new BorderLayout());
		this.add(alldetaillabelpanel, BorderLayout.NORTH);
		this.add(imagepanel, BorderLayout.SOUTH);
		this.setBackground(new Color(255, 255, 255));
		this.setBorder(BorderFactory.createEmptyBorder(10, 20, 60, 20));
		
	}

	private void initAllDetailLabelPanel() {
		// TODO Auto-generated method stub
		
		detaillabelpanel1=new JPanel();
		detaillabelpanel2=new JPanel();
		
		detailheadlabel1=new JLabel();
		detailheadlabel2=new JLabel();
		detaillabel1=new JLabel();
		detaillabel2=new JLabel();
		
		detailheadlabel1.setText("Õý³£Çé¿ö£º");
		detailheadlabel1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 17));
		detailheadlabel1.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		detaillabel1.setText(normaltext);
		detaillabel1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
//		detaillabel1.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		
		detailheadlabel2.setText("Òì³£Çé¿ö£º");
		detailheadlabel2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 17));
		detailheadlabel2.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		detaillabel2.setText(abnormaltext);
		detaillabel2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		
//		detaillabelpanel1.setLayout(new BorderLayout());
//		detaillabelpanel1.add(detailheadlabel1, BorderLayout.NORTH);
//		detaillabelpanel1.add(detaillabel1, BorderLayout.CENTER);
//		detaillabelpanel1.setOpaque(false);
//		
//		detaillabelpanel2.setLayout(new BorderLayout());
//		detaillabelpanel2.add(detailheadlabel2, BorderLayout.NORTH);
//		detaillabelpanel2.add(detaillabel2, BorderLayout.CENTER);
//		detaillabelpanel2.setOpaque(false);
		
		detaillabelpanel1.setLayout(new BoxLayout(detaillabelpanel1, BoxLayout.Y_AXIS));
//		detaillabelpanel1.add(detailheadlabel1);
		detaillabelpanel1.add(detaillabel1);
		detaillabelpanel1.setOpaque(false);
		
		detaillabelpanel2.setLayout(new BoxLayout(detaillabelpanel2, BoxLayout.Y_AXIS));
//		detaillabelpanel2.add(detailheadlabel2);
		detaillabelpanel2.add(detaillabel2);
		detaillabelpanel2.setOpaque(false);
		
		JPanel emptypanel=new JPanel();
		emptypanel.setOpaque(false);
		
//		alldetaillabelpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		alldetaillabelpanel.setLayout(new GridLayout(1, 3));
		alldetaillabelpanel.add(detaillabelpanel1);
		alldetaillabelpanel.add(detaillabelpanel2);
		alldetaillabelpanel.add(emptypanel);
		alldetaillabelpanel.setBackground(new Color(255, 255, 255));
		alldetaillabelpanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 0));
		
	}

	private void initImagePanel() {
		// TODO Auto-generated method stub
		
	}
	
}

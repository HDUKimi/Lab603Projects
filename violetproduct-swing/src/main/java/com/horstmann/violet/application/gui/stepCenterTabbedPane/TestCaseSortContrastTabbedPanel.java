package com.horstmann.violet.application.gui.stepCenterTabbedPane;

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
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;

public class TestCaseSortContrastTabbedPanel extends JPanel{

	private MainFrame mainFrame;
	
	private JPanel toolpanel;
	private MoviePanel moviepanel;
	private JSplitPane resultpanel;
	private JPanel oldtablepanel;
	private JPanel newtablepanel;
	
	private JPanel toolbuttonpanel1;
	private JPanel toolbuttonpanel2;
	private JButton toolbutton1;
	private JButton toolbutton2;
	
	private JPanel oldtableinforpanel;
	private JLabel oldtableinforlabel;
	private JPanel newtableinforpanel;
	private JLabel newtableinforlabel;
	
	private JScrollPane oldtablescrollpanel;
	private JPanel oldtableresultpanel;
	private JScrollPane newtablescrollpanel;
	private JPanel newtableresultpanel;
	
	private List<TestCaseSortContrastPartPanel> oldTestCaseSortContrastPartPanelList=new ArrayList<>();
	private List<TestCaseSortContrastPartPanel> newTestCaseSortContrastPartPanelList=new ArrayList<>();		
	
	public TestCaseSortContrastTabbedPanel(MainFrame mainFrame){
		
		this.mainFrame=mainFrame;
		
		toolpanel=new JPanel();
		moviepanel=new MoviePanel();
		oldtablepanel=new JPanel();
		newtablepanel=new JPanel();
		
		initToolPanel();
		
		initMoviePanel();
		
		initResultPanel();
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(toolpanel);
		this.add(moviepanel);
		this.add(resultpanel);
		layout.setConstraints(toolpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(moviepanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(resultpanel,new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		this.setBackground(new Color(255, 255, 255));
		
	}
	
	private void initResultPanel() {
		// TODO Auto-generated method stub
		
		initOldTablePanel();
		
		initNewTablePanel();

//		oldtablepanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(142, 155, 188)));
//		newtablepanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(142, 155, 188)));

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = (int) screenSize.getHeight();
		
		resultpanel=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, oldtablepanel, newtablepanel);
		resultpanel.setDividerSize(6);
		resultpanel.setDividerLocation(0.5);
		resultpanel.setResizeWeight(0.5);
		resultpanel.setContinuousLayout(true);
		
	}

	private void initOldTablePanel() {
		// TODO Auto-generated method stub
		
		initOldTableInforPanel();
		
		oldtableresultpanel=new JPanel();
		oldtableresultpanel.setLayout(new GridLayout());
		oldtableresultpanel.setBorder(null);
		oldtableresultpanel.setBackground(new Color(255, 255, 255));
		
		oldtablescrollpanel=new JScrollPane(oldtableresultpanel);
		oldtablescrollpanel.setBorder(null);
		oldtablescrollpanel.setBackground(new Color(255, 255, 255));
		
//		oldtablepanel.setLayout(new BorderLayout());
//		oldtablepanel.add(oldtableinforpanel, BorderLayout.NORTH);
//		oldtablepanel.add(oldtablescrollpanel,BorderLayout.CENTER);
		
		GridBagLayout layout = new GridBagLayout();
		oldtablepanel.setLayout(layout);
		oldtablepanel.add(oldtableinforpanel);
		oldtablepanel.add(oldtablescrollpanel);
		layout.setConstraints(oldtableinforpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(oldtablescrollpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		
	}

	private void initOldTableInforPanel() {
		// TODO Auto-generated method stub
		
		oldtableinforlabel=new JLabel();
		oldtableinforlabel.setText("≈≈–Ú«∞");
		oldtableinforlabel.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 16));
		oldtableinforlabel.setOpaque(false);
		
		oldtableinforlabel.setMaximumSize(new Dimension(60, 50));
		oldtableinforlabel.setPreferredSize(new Dimension(60, 50));
		oldtableinforlabel.setMinimumSize(new Dimension(60, 50));
		
		oldtableinforpanel=new JPanel();
		oldtableinforpanel.setBackground(new Color(255, 255, 255));
		oldtableinforpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(142, 155, 188)));
		oldtableinforpanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		oldtableinforpanel.add(oldtableinforlabel);
//		oldtableinforpanel.setLayout(new BorderLayout());
//		oldtableinforpanel.add(oldtableinforlabel,BorderLayout.CENTER);
		
	}

	private void initNewTablePanel() {
		// TODO Auto-generated method stub
		
		initNewTableInforPanel();
		
		newtableresultpanel=new JPanel();
		newtableresultpanel.setLayout(new GridLayout());
		newtableresultpanel.setBorder(null);
		newtableresultpanel.setBackground(new Color(255, 255, 255));
		
		newtablescrollpanel=new JScrollPane(newtableresultpanel);
		newtablescrollpanel.setBorder(null);
		newtablescrollpanel.setBackground(new Color(255, 255, 255));
		
		GridBagLayout layout = new GridBagLayout();
		newtablepanel.setLayout(layout);
		newtablepanel.add(newtableinforpanel);
		newtablepanel.add(newtablescrollpanel);
		layout.setConstraints(newtableinforpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(newtablescrollpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		
	}

	private void initNewTableInforPanel() {
		// TODO Auto-generated method stub
		
		newtableinforlabel=new JLabel();
		newtableinforlabel.setText("≈≈–Ú∫Û");
		newtableinforlabel.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 16));
		newtableinforlabel.setOpaque(false);
		
		newtableinforlabel.setMaximumSize(new Dimension(60, 50));
		newtableinforlabel.setPreferredSize(new Dimension(60, 50));
		newtableinforlabel.setMinimumSize(new Dimension(60, 50));
		
		newtableinforpanel=new JPanel();
		newtableinforpanel.setBackground(new Color(255, 255, 255));
		newtableinforpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(142, 155, 188)));
		newtableinforpanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		newtableinforpanel.add(newtableinforlabel);
		
	}

	private void initToolPanel() {
		// TODO Auto-generated method stub
		
		toolbuttonpanel1 = new JPanel();
		toolbuttonpanel2 = new JPanel();

		toolbutton1 = new JButton();
		toolbutton2 = new JButton();
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "up_arrow.png");
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(path + "down_arrow.png");
		icon2.setImage(icon2.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
	
		toolbutton1.setIcon(icon1);
		toolbutton1.setFocusable(false);
		toolbutton1.setContentAreaFilled(false);
		toolbutton1.setBorderPainted(false);
		toolbutton1.addMouseListener(new ButtonMouseListener());
		toolbutton1.setPreferredSize(new Dimension(21,21));
		toolbutton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				for(TestCaseSortContrastPartPanel tcscppanel:oldTestCaseSortContrastPartPanelList){
					tcscppanel.getAttributepanel().setVisible(false);
				}
				for(TestCaseSortContrastPartPanel tcscppanel:newTestCaseSortContrastPartPanelList){
					tcscppanel.getAttributepanel().setVisible(false);
				}
				
			}
		});
		
		toolbutton2.setIcon(icon2);
		toolbutton2.setFocusable(false);
		toolbutton2.setContentAreaFilled(false);
		toolbutton2.setBorderPainted(false);
		toolbutton2.addMouseListener(new ButtonMouseListener());
		toolbutton2.setPreferredSize(new Dimension(21,21));
		toolbutton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				for(TestCaseSortContrastPartPanel tcscppanel:oldTestCaseSortContrastPartPanelList){
					tcscppanel.getAttributepanel().setVisible(true);
				}
				for(TestCaseSortContrastPartPanel tcscppanel:newTestCaseSortContrastPartPanelList){
					tcscppanel.getAttributepanel().setVisible(true);
				}
				
			}
		});

		toolbuttonpanel1.setLayout(new GridLayout());
		toolbuttonpanel1.setBackground(new Color(207, 214, 229));
		toolbuttonpanel1.add(toolbutton1);
		toolbuttonpanel2.setLayout(new GridLayout());
		toolbuttonpanel2.setBackground(new Color(207, 214, 229));
		toolbuttonpanel2.add(toolbutton2);

		toolpanel.setBackground(new Color(207, 214, 229));
		toolpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5,3));
		
		toolpanel.add(toolbuttonpanel1);
		toolpanel.add(toolbuttonpanel2);
		
		toolpanel.setPreferredSize(new Dimension(100, 29));
		toolpanel.setMaximumSize(new Dimension(100, 29));
		toolpanel.setMinimumSize(new Dimension(100, 29));
		
	}

	private void initMoviePanel() {
		// TODO Auto-generated method stub
		
		moviepanel.getMovieLabel().setText("’˝‘⁄Ω¯––≤‚ ‘”√¿˝÷ÿ“™∂»≈≈–Ú");
		
	}
	
	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}

	public List<TestCaseSortContrastPartPanel> getOldTestCaseSortContrastPartPanelList() {
		return oldTestCaseSortContrastPartPanelList;
	}

	public void setOldTestCaseSortContrastPartPanelList(
			List<TestCaseSortContrastPartPanel> oldTestCaseSortContrastPartPanelList) {
		this.oldTestCaseSortContrastPartPanelList = oldTestCaseSortContrastPartPanelList;
	}

	public List<TestCaseSortContrastPartPanel> getNewTestCaseSortContrastPartPanelList() {
		return newTestCaseSortContrastPartPanelList;
	}

	public void setNewTestCaseSortContrastPartPanelList(
			List<TestCaseSortContrastPartPanel> newTestCaseSortContrastPartPanelList) {
		this.newTestCaseSortContrastPartPanelList = newTestCaseSortContrastPartPanelList;
	}

	public MoviePanel getMoviepanel() {
		return moviepanel;
	}

	public JSplitPane getResultpanel() {
		return resultpanel;
	}

	public JPanel getOldtablepanel() {
		return oldtablepanel;
	}

	public JPanel getNewtablepanel() {
		return newtablepanel;
	}

	public JScrollPane getOldtablescrollpanel() {
		return oldtablescrollpanel;
	}

	public JPanel getOldtableresultpanel() {
		return oldtableresultpanel;
	}

	public JScrollPane getNewtablescrollpanel() {
		return newtablescrollpanel;
	}

	public JPanel getNewtableresultpanel() {
		return newtableresultpanel;
	}

}

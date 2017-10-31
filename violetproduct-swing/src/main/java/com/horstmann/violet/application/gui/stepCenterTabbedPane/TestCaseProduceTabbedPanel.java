package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.plaf.ProgressBarUI;

import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;

public class TestCaseProduceTabbedPanel extends JPanel{

	private MainFrame mainFrame;

	private JPanel toolpanel;
	private MoviePanel moviepanel;
	private JPanel tabelpanel;

	private JPanel toolbuttonpanel6;
	private JPanel toolbuttonpanel7;
	private JButton toolbutton6;
	private JButton toolbutton7;

	private JScrollPane tabelscrollpanel;
	private JPanel tableresultpanel;
	
	private List<TestCaseProducePartPanel> testCaseProducePartPanelList=new ArrayList<TestCaseProducePartPanel>();
	
	public TestCaseProduceTabbedPanel(MainFrame mainframe){
		
		this.mainFrame = mainframe;

		toolpanel = new JPanel();
		moviepanel = new MoviePanel();
		tabelpanel = new JPanel();

		initToolPanel();

		initMoviePanel();

		initTablePanel();

		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(toolpanel);
		this.add(moviepanel);
		this.add(tabelpanel);
		layout.setConstraints(toolpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(moviepanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(tabelpanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));

		this.setBackground(new Color(255, 255, 255));

	}
	
	private void initToolPanel() {
		// TODO Auto-generated method stub
		
		toolbuttonpanel6 = new JPanel();
		toolbuttonpanel7 = new JPanel();

		toolbutton6 = new JButton();
		toolbutton7 = new JButton();
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon6 = new ImageIcon(this.getClass().getResource("ImagePart/up_arrow.png"));
		icon6.setImage(icon6.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon7 = new ImageIcon(this.getClass().getResource("ImagePart/down_arrow.png"));
		icon7.setImage(icon7.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
	
		
		toolbutton6.setIcon(icon6);
		toolbutton6.setFocusable(false);
		toolbutton6.setContentAreaFilled(false);
		toolbutton6.setBorderPainted(false);
		toolbutton6.addMouseListener(new ButtonMouseListener());
		toolbutton6.setPreferredSize(new Dimension(21,21));
		toolbutton6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				for(TestCaseProducePartPanel tcpppanel:testCaseProducePartPanelList){
					tcpppanel.getAttributepanel().setVisible(false);
				}
			}
		});
		
		toolbutton7.setIcon(icon7);
		toolbutton7.setFocusable(false);
		toolbutton7.setContentAreaFilled(false);
		toolbutton7.setBorderPainted(false);
		toolbutton7.addMouseListener(new ButtonMouseListener());
		toolbutton7.setPreferredSize(new Dimension(21,21));
		toolbutton7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				for(TestCaseProducePartPanel tcpppanel:testCaseProducePartPanelList){
					tcpppanel.getAttributepanel().setVisible(true);
				}
			}
		});

		toolbuttonpanel6.setLayout(new GridLayout());
		toolbuttonpanel6.setBackground(new Color(207, 214, 229));
		toolbuttonpanel6.add(toolbutton6);
		toolbuttonpanel7.setLayout(new GridLayout());
		toolbuttonpanel7.setBackground(new Color(207, 214, 229));
		toolbuttonpanel7.add(toolbutton7);

		toolpanel.setBackground(new Color(207, 214, 229));
		toolpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5,3));
		
		toolpanel.add(toolbuttonpanel6);
		toolpanel.add(toolbuttonpanel7);
		
		toolpanel.setPreferredSize(new Dimension(100, 29));
		toolpanel.setMaximumSize(new Dimension(100, 29));
		toolpanel.setMinimumSize(new Dimension(100, 29));
		
	}

	private void initMoviePanel() {
		// TODO Auto-generated method stub
		
		moviepanel.getMovieLabel().setText("正在筛选、添加、组合实例化约束条件，生成测试序列");
		
	}

	private void initTablePanel() {
		// TODO Auto-generated method stub
		
		tableresultpanel=new JPanel();
		tableresultpanel.setLayout(new GridLayout());
		tableresultpanel.setBorder(null);
		tableresultpanel.setBackground(new Color(255, 255, 255));
		
		tabelscrollpanel=new JScrollPane(tableresultpanel);
//		tabelscrollpanel=new JScrollPane();
        tabelscrollpanel.setBorder(null);
        tabelscrollpanel.setBackground(new Color(255, 255, 255));
		
		tabelpanel.setLayout(new GridLayout());
		tabelpanel.add(tabelscrollpanel);
		
//		JPanel resultpanel=new JPanel();
//		JPanel emptypanel=new JPanel();
//		resultpanel.setOpaque(false);
//		emptypanel.setOpaque(false);
//		
//		GridBagLayout layout = new GridBagLayout();
//		resultpanel.setLayout(layout);
//		int i=0;
//		for(int j=0;j<30;j++){
//			
//			TestCaseProducePartPanel tcpppanel=new TestCaseProducePartPanel(mainFrame);
//			resultpanel.add(tcpppanel);
//			layout.setConstraints(tcpppanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//		}
//		resultpanel.add(emptypanel);
//		layout.setConstraints(emptypanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		
//		tableresultpanel.removeAll();
//		tableresultpanel.add(resultpanel);
		
	}
	
	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}

	public JScrollPane getTabelscrollpanel() {
		return tabelscrollpanel;
	}

	public JPanel getTableresultpanel() {
		return tableresultpanel;
	}

	public JPanel getTabelpanel() {
		return tabelpanel;
	}

	public List<TestCaseProducePartPanel> getTestCaseProducePartPanelList() {
		return testCaseProducePartPanelList;
	}

	public void setTestCaseProducePartPanelList(List<TestCaseProducePartPanel> testCaseProducePartPanelList) {
		this.testCaseProducePartPanelList = testCaseProducePartPanelList;
	}

	public MoviePanel getMoviepanel() {
		return moviepanel;
	}
	
	
	
}

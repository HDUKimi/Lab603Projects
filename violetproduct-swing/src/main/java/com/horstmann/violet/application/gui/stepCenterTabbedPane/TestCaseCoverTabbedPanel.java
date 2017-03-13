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

import com.horstmann.violet.application.consolepart.TestCasePathPanel;
import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.TestCase;
import com.horstmann.violet.workspace.IWorkspace;

public class TestCaseCoverTabbedPanel extends JPanel{

	private MainFrame mainFrame;
	private IWorkspace workspace;

	private JPanel toolpanel;
	private MoviePanel moviepanel;
	private JPanel resultpanel;
	private JPanel diagrampanel;
	private JPanel tabelpanel;
	
	private JPanel toolbuttonpanel1;
	private JPanel toolbuttonpanel2;
	private JPanel toolbuttonpanel6;
	private JPanel toolbuttonpanel7;
	private JButton toolbutton1;
	private JButton toolbutton2;
	private JButton toolbutton6;
	private JButton toolbutton7;
	private JPanel emptypanel1;
	
	private JScrollPane tabelscrollpanel;
	private JPanel tableresultpanel;
	
	private List<TestCaseCoverPartPanel> testCaseCoverPartPanelList=new ArrayList<>();
	
	public TestCaseCoverTabbedPanel(MainFrame mainframe,IWorkspace workspace) {

		this.mainFrame = mainframe;
		this.workspace=workspace;
		
		toolpanel=new JPanel();
		moviepanel=new MoviePanel();
		resultpanel=new JPanel();
		diagrampanel=new JPanel();
		tabelpanel=new JPanel();
		
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
		
		initDiagramPanel();
		
		initTablePanel();

		diagrampanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(142, 155, 188)));

		GridBagLayout layout = new GridBagLayout();
		resultpanel.setLayout(layout);
		resultpanel.add(diagrampanel);
		resultpanel.add(tabelpanel);
		layout.setConstraints(diagrampanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(tabelpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
	}

	private void initDiagramPanel() {
		// TODO Auto-generated method stub
		
		diagrampanel.setLayout(new GridLayout());
		
		if(workspace!=null){
			diagrampanel.add(workspace.getAWTComponent());
		}
		
		
	}

	private void initToolPanel() {
		// TODO Auto-generated method stub
		
		toolbuttonpanel1 = new JPanel();
		toolbuttonpanel2 = new JPanel();
		toolbuttonpanel6 = new JPanel();
		toolbuttonpanel7 = new JPanel();

		toolbutton1 = new JButton();
		toolbutton2 = new JButton();
		toolbutton6 = new JButton();
		toolbutton7 = new JButton();
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "zoomin1.png");
		icon1.setImage(icon1.getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(path + "zoomout1.png");
		icon2.setImage(icon2.getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));
		ImageIcon icon6 = new ImageIcon(path + "up_arrow.png");
		icon6.setImage(icon6.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon7 = new ImageIcon(path + "down_arrow.png");
		icon7.setImage(icon7.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
	
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
				workspace.getSideBar().getEditorToolsBar().getZoomInButton().doClick();
				mainFrame.getConsolePartPanel().getTextarea().append("放大一倍视图\n");
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
				workspace.getSideBar().getEditorToolsBar().getZoomOutButton().doClick();
				mainFrame.getConsolePartPanel().getTextarea().append("缩小一倍视图\n");
			}
		});
		
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
				
				for(TestCaseCoverPartPanel tccppanel:testCaseCoverPartPanelList){
					tccppanel.getAttributepanel().setVisible(false);
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
				
				for(TestCaseCoverPartPanel tccppanel:testCaseCoverPartPanelList){
					tccppanel.getAttributepanel().setVisible(true);
				}
				
			}
		});

		toolbuttonpanel1.setLayout(new GridLayout());
		toolbuttonpanel1.setBackground(new Color(207, 214, 229));
		toolbuttonpanel1.add(toolbutton1);
		toolbuttonpanel2.setLayout(new GridLayout());
		toolbuttonpanel2.setBackground(new Color(207, 214, 229));
		toolbuttonpanel2.add(toolbutton2);
		toolbuttonpanel6.setLayout(new GridLayout());
		toolbuttonpanel6.setBackground(new Color(207, 214, 229));
		toolbuttonpanel6.add(toolbutton6);
		toolbuttonpanel7.setLayout(new GridLayout());
		toolbuttonpanel7.setBackground(new Color(207, 214, 229));
		toolbuttonpanel7.add(toolbutton7);
		
		emptypanel1=new JPanel();
		emptypanel1.setPreferredSize(new Dimension(11, 23));
		emptypanel1.setBackground(new Color(133,145,162));
		emptypanel1.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 5, new Color(207, 214, 229)));
		

		toolpanel.setBackground(new Color(207, 214, 229));
		toolpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5,3));
		
		toolpanel.add(toolbuttonpanel1);
		toolpanel.add(toolbuttonpanel2);
		toolpanel.add(emptypanel1);
		toolpanel.add(toolbuttonpanel6);
		toolpanel.add(toolbuttonpanel7);
		
		toolpanel.setPreferredSize(new Dimension(100, 29));
		toolpanel.setMaximumSize(new Dimension(100, 29));
		toolpanel.setMinimumSize(new Dimension(100, 29));
		
		
	}

	private void initMoviePanel() {
		// TODO Auto-generated method stub
		
		moviepanel.getMovieLabel().setText("正在进行路径覆盖");
		
	}

	private void initTablePanel() {
		// TODO Auto-generated method stub
		
		tableresultpanel=new JPanel();
		tableresultpanel.setLayout(new GridLayout());
		tableresultpanel.setBorder(null);
		tableresultpanel.setBackground(new Color(255, 255, 255));
		
		tabelscrollpanel=new JScrollPane(tableresultpanel);
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
//			TestCaseCoverPartPanel tccppanel=new TestCaseCoverPartPanel(mainFrame);
//			resultpanel.add(tccppanel);
//			layout.setConstraints(tccppanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
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

	public JPanel getResultpanel() {
		return resultpanel;
	}

	public JPanel getDiagrampanel() {
		return diagrampanel;
	}

	public JPanel getTabelpanel() {
		return tabelpanel;
	}

	public JPanel getTableresultpanel() {
		return tableresultpanel;
	}

	public List<TestCaseCoverPartPanel> getTestCaseCoverPartPanelList() {
		return testCaseCoverPartPanelList;
	}

	public void setTestCaseCoverPartPanelList(List<TestCaseCoverPartPanel> testCaseCoverPartPanelList) {
		this.testCaseCoverPartPanelList = testCaseCoverPartPanelList;
	}
	
	
}

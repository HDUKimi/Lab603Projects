package com.horstmann.violet.application.gui.stepCenterTabbedPane;

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
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.plaf.ProgressBarUI;

import com.horstmann.violet.application.consolepart.TestCasePathPanel;
import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.TestCase;
import com.horstmann.violet.application.gui.util.tanchao.TranMessageColorize;
import com.horstmann.violet.application.gui.util.tanchao.TranMessageText;
import com.horstmann.violet.workspace.IWorkspace;

public class TestCaseCoverTabbedPanel extends JPanel{

	private MainFrame mainFrame;
	private IWorkspace workspace;

	private JPanel toolpanel;
	private MoviePanel moviepanel;
	private JSplitPane resultpanel;
	private JPanel diagrampanel;
	private JPanel tablepanel;
	
	private JPanel toolbuttonpanel1;
	private JPanel toolbuttonpanel2;
	private JPanel toolbuttonpanel3;
	private JPanel toolbuttonpanel4;
	private JPanel toolbuttonpanel5;
	private JPanel toolbuttonpanel6;
	private JPanel toolbuttonpanel7;
	private JPanel toolbuttonpanel8;
	private JButton toolbutton1;
	private JButton toolbutton2;
	private JButton toolbutton3;
	private JButton toolbutton4;
	private JButton toolbutton5;
	private JButton toolbutton6;
	private JButton toolbutton7;
	private JButton toolbutton8;
	private JPanel emptypanel1;
	private JPanel emptypanel2;
	private JPanel emptypanel3;
	private JPanel emptypanel4;
	
	private JScrollPane tablescrollpanel;
	private JPanel tableresultpanel;
	
	private List<TestCaseCoverPartPanel> testCaseCoverPartPanelList=new ArrayList<>();
	
	private int location;
	private int tablestate=1;
	private int trantextstate=1;//1是id，0是name
	
	public TestCaseCoverTabbedPanel(MainFrame mainframe,IWorkspace workspace) {

		this.mainFrame = mainframe;
		this.workspace=workspace;
		
		toolpanel=new JPanel();
		moviepanel=new MoviePanel();
//		resultpanel=new JPanel();
		diagrampanel=new JPanel();
		tablepanel=new JPanel();
		
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
		tablepanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(142, 155, 188)));

//		GridBagLayout layout = new GridBagLayout();
//		resultpanel.setLayout(layout);
//		resultpanel.add(diagrampanel);
//		resultpanel.add(tablepanel);
//		layout.setConstraints(diagrampanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		layout.setConstraints(tablepanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = (int) screenSize.getHeight();
		
		resultpanel=new JSplitPane(JSplitPane.VERTICAL_SPLIT, diagrampanel, tablepanel);
		resultpanel.setDividerSize(6);
		resultpanel.setDividerLocation(screenHeight*5/12);
		resultpanel.setResizeWeight(1);
		resultpanel.setContinuousLayout(true);
		
		
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
		toolbuttonpanel3 = new JPanel();
		toolbuttonpanel4 = new JPanel();
		toolbuttonpanel5 = new JPanel();
		toolbuttonpanel6 = new JPanel();
		toolbuttonpanel7 = new JPanel();
		toolbuttonpanel8 = new JPanel();

		toolbutton1 = new JButton();
		toolbutton2 = new JButton();
		toolbutton3 = new JButton();
		toolbutton4 = new JButton();
		toolbutton5 = new JButton();
		toolbutton6 = new JButton();
		toolbutton7 = new JButton();
		toolbutton8 = new JButton();
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "zoomin1.png");
		icon1.setImage(icon1.getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(path + "zoomout1.png");
		icon2.setImage(icon2.getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));
		final ImageIcon icon3 = new ImageIcon(path + "test12.png");
		icon3.setImage(icon3.getImage().getScaledInstance(18,18, Image.SCALE_DEFAULT));
		final ImageIcon icon4 = new ImageIcon(path + "test11.png");
		icon4.setImage(icon4.getImage().getScaledInstance(18,18, Image.SCALE_DEFAULT));
		ImageIcon icon5 = new ImageIcon(path + "test13.png");
		icon5.setImage(icon5.getImage().getScaledInstance(18,18, Image.SCALE_DEFAULT));
		ImageIcon icon6 = new ImageIcon(path + "up_arrow.png");
		icon6.setImage(icon6.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon7 = new ImageIcon(path + "down_arrow.png");
		icon7.setImage(icon7.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon8 = new ImageIcon(path + "eye.png");
		icon8.setImage(icon8.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
	
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
				mainFrame.getConsolePartPanel().getTextarea3().append("放大一倍视图\n");
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
				mainFrame.getConsolePartPanel().getTextarea3().append("缩小一倍视图\n");
			}
		});
		
		toolbutton3.setIcon(icon3);
		toolbutton3.setFocusable(false);
		toolbutton3.setContentAreaFilled(false);
		toolbutton3.setBorderPainted(false);
		toolbutton3.addMouseListener(new ButtonMouseListener());
		toolbutton3.setPreferredSize(new Dimension(21,21));
		toolbutton3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(trantextstate==0){
					TranMessageText tmt=new TranMessageText();
					tmt.TranTextToId(mainFrame, workspace);
					trantextstate=1;
					
					toolbutton3.setIcon(icon3);
					
					ChangeRepaint();
				}
				else{
					TranMessageText tmt=new TranMessageText();
					tmt.TranTextToName(mainFrame, workspace);
					trantextstate=0;
					
					toolbutton3.setIcon(icon4);
					
					ChangeRepaint();
				}
				
//				TranMessageText tmt=new TranMessageText();
//				tmt.TranTextToId(mainFrame, workspace);
//				trantextstate=1;
//				ChangeRepaint();
				
			}
		});

		toolbutton4.setIcon(icon4);
		toolbutton4.setFocusable(false);
		toolbutton4.setContentAreaFilled(false);
		toolbutton4.setBorderPainted(false);
		toolbutton4.addMouseListener(new ButtonMouseListener());
		toolbutton4.setPreferredSize(new Dimension(21,21));
		toolbutton4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				TranMessageText tmt=new TranMessageText();
				tmt.TranTextToName(mainFrame, workspace);
				trantextstate=0;
				ChangeRepaint();
			}
		});

		toolbutton5.setIcon(icon5);
		toolbutton5.setFocusable(false);
		toolbutton5.setContentAreaFilled(false);
		toolbutton5.setBorderPainted(false);
		toolbutton5.addMouseListener(new ButtonMouseListener());
		toolbutton5.setPreferredSize(new Dimension(21,21));
		toolbutton5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(tablestate==1){
					location=resultpanel.getDividerLocation();
					resultpanel.setDividerSize(0);
					tablepanel.setVisible(false);
					tablestate=0;
				}
				else{
					tablepanel.setVisible(true);
					resultpanel.setDividerLocation(location);
					resultpanel.setDividerSize(6);
					tablestate=1;
				}
				
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
		
		toolbutton8.setIcon(icon8);
		toolbutton8.setFocusable(false);
		toolbutton8.setContentAreaFilled(false);
		toolbutton8.setBorderPainted(false);
		toolbutton8.addMouseListener(new ButtonMouseListener());
		toolbutton8.setPreferredSize(new Dimension(21,21));
		toolbutton8.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				System.err.println("1233333333333333333333");
				
				Thread t=new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						
						int index=0;
						
						for(TestCaseCoverPartPanel tccppanel:testCaseCoverPartPanelList){
							
							System.out.println(tccppanel.getAutomatic().getName());
							
							TranMessageColorize tmc=new TranMessageColorize();
							tmc.ColorizeDFSPath(tccppanel.getAutomatic(), tccppanel.getMainFrame(), tccppanel.getWorkspace());
							
							if(mainFrame.getStepThreeCenterTabbedPane().getFixButtonTabbedPanelSelectedIndex()==4){
								mainFrame.getStepThreeCenterTabbedPane().getTestCaseCoverTabbedPanel().ChangeRepaint();
							}
							else if(mainFrame.getStepThreeCenterTabbedPane().getFixButtonTabbedPanelSelectedIndex()==0){
								mainFrame.getStepThreeCenterTabbedPane().getTestCaseProcessTabbedPanel().ChangeRepaint();
							}
							
							index++;
							
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							if(index==testCaseCoverPartPanelList.size()){
								tmc.CleanColorize(tccppanel.getWorkspace());
								if(mainFrame.getStepThreeCenterTabbedPane().getFixButtonTabbedPanelSelectedIndex()==4){
									mainFrame.getStepThreeCenterTabbedPane().getTestCaseCoverTabbedPanel().ChangeRepaint();
								}
								else if(mainFrame.getStepThreeCenterTabbedPane().getFixButtonTabbedPanelSelectedIndex()==0){
									mainFrame.getStepThreeCenterTabbedPane().getTestCaseProcessTabbedPanel().ChangeRepaint();
								}
							}
							
						}
						
					}
				});
				t.start();
				
			}
		});

		toolbuttonpanel1.setLayout(new GridLayout());
		toolbuttonpanel1.setBackground(new Color(207, 214, 229));
		toolbuttonpanel1.add(toolbutton1);
		toolbuttonpanel2.setLayout(new GridLayout());
		toolbuttonpanel2.setBackground(new Color(207, 214, 229));
		toolbuttonpanel2.add(toolbutton2);
		toolbuttonpanel3.setLayout(new GridLayout());
		toolbuttonpanel3.setBackground(new Color(207, 214, 229));
		toolbuttonpanel3.add(toolbutton3);
		toolbuttonpanel4.setLayout(new GridLayout());
		toolbuttonpanel4.setBackground(new Color(207, 214, 229));
		toolbuttonpanel4.add(toolbutton4);
		toolbuttonpanel5.setLayout(new GridLayout());
		toolbuttonpanel5.setBackground(new Color(207, 214, 229));
		toolbuttonpanel5.add(toolbutton5);
		toolbuttonpanel6.setLayout(new GridLayout());
		toolbuttonpanel6.setBackground(new Color(207, 214, 229));
		toolbuttonpanel6.add(toolbutton6);
		toolbuttonpanel7.setLayout(new GridLayout());
		toolbuttonpanel7.setBackground(new Color(207, 214, 229));
		toolbuttonpanel7.add(toolbutton7);
		toolbuttonpanel8.setLayout(new GridLayout());
		toolbuttonpanel8.setBackground(new Color(207, 214, 229));
		toolbuttonpanel8.add(toolbutton8);
		
		emptypanel1=new JPanel();
		emptypanel1.setPreferredSize(new Dimension(11, 23));
		emptypanel1.setBackground(new Color(133,145,162));
		emptypanel1.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 5, new Color(207, 214, 229)));

		emptypanel2=new JPanel();
		emptypanel2.setPreferredSize(new Dimension(11, 23));
		emptypanel2.setBackground(new Color(133,145,162));
		emptypanel2.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 5, new Color(207, 214, 229)));
		
		emptypanel3=new JPanel();
		emptypanel3.setPreferredSize(new Dimension(11, 23));
		emptypanel3.setBackground(new Color(133,145,162));
		emptypanel3.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 5, new Color(207, 214, 229)));
		
		emptypanel4=new JPanel();
		emptypanel4.setPreferredSize(new Dimension(11, 23));
		emptypanel4.setBackground(new Color(133,145,162));
		emptypanel4.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 5, new Color(207, 214, 229)));
		

		toolpanel.setBackground(new Color(207, 214, 229));
		toolpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5,3));
		
		toolpanel.add(toolbuttonpanel1);
		toolpanel.add(toolbuttonpanel2);
		toolpanel.add(emptypanel1);
		toolpanel.add(toolbuttonpanel3);
//		toolpanel.add(toolbuttonpanel4);
		toolpanel.add(emptypanel2);
		toolpanel.add(toolbuttonpanel5);
		toolpanel.add(emptypanel3);
		toolpanel.add(toolbuttonpanel6);
		toolpanel.add(toolbuttonpanel7);
		toolpanel.add(emptypanel4);
		toolpanel.add(toolbuttonpanel8);
		
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
		
		tablescrollpanel=new JScrollPane(tableresultpanel);
        tablescrollpanel.setBorder(null);
        tablescrollpanel.setBackground(new Color(255, 255, 255));
		
		tablepanel.setLayout(new GridLayout());
		tablepanel.add(tablescrollpanel);
		
		
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

	public JSplitPane getResultpanel() {
		return resultpanel;
	}

	public JPanel getDiagrampanel() {
		return diagrampanel;
	}

	public JPanel getTablepanel() {
		return tablepanel;
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

	public int getTrantextstate() {
		return trantextstate;
	}

	public MoviePanel getMoviepanel() {
		return moviepanel;
	}
	
}

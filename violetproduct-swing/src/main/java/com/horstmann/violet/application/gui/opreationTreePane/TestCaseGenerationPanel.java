package com.horstmann.violet.application.gui.opreationTreePane;

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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;

public class TestCaseGenerationPanel extends JPanel {
	
	
	public  MainFrame mainFrame;
	
    private JPanel titlepanel;
    private JPanel treepanel;
	private JPanel treeinforpanel;
	
	private JLabel titlelabel;
	private JPanel titleiconlabelpanel;
	private JLabel titleiconlabel1;
	private JLabel titleiconlabel2;
	private JLabel titleiconlabel3;
	
//	private JPanel uppaalpanel;
	private JPanel uppaallabelpanel;
	private JLabel uppaallabel;
	private JPanel uppaaltoolpanel;
	private JButton uppaaltoolbutton1;
	private JButton uppaaltoolbutton2;
	private JButton uppaaltoolbutton3;
	private JButton uppaaltoolbutton4;
	private JPanel uppaalcheckboxpanel;
	private JScrollPane uppaalscrollpanel;
	
	private JPanel coverpanel;
	private JPanel coverlabelpanel;
	private JLabel coverlabel;
	private JPanel covertoolpanel;
	private JButton covertoolbutton1;
	private JPanel covercheckboxpanel;
	private JScrollPane coverscrollpanel;
	
	private JCheckBox selectUppaalCheckBox;
	private JCheckBox[] uppaalCheckBoxList;
	
	private List<String> uppaallists=new ArrayList<String>();
	
	private JCheckBox selectCoverCheckBox;
	private JCheckBox[] coverCheckBoxList;
	
	private List<String> coverlists=new ArrayList<String>();
	
	public TestCaseGenerationPanel(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub			
		
		this.mainFrame = mainFrame;
		
		titlepanel = new JPanel();
		treepanel = new JPanel();
		treeinforpanel=new JPanel();
		
		titlelabel = new JLabel();
		titleiconlabelpanel=new JPanel();
		titleiconlabel1 = new JLabel();
		titleiconlabel2 = new JLabel();
		titleiconlabel3 = new JLabel();
		
		titlepanel.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(142, 155, 188)));
		treepanel.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(142, 155, 188)));
		
		initTitlePanel();
		
		initTreePanel();
		
		GridBagLayout layout=new GridBagLayout();
		this.setLayout(layout);
		this.add(titlepanel);
		this.add(treepanel);
		layout.setConstraints(titlepanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(treepanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();
		
		this.setMinimumSize(new Dimension(screenWidth/8, screenHeight-150));
		
	}
	

	private void initTitlePanel() {
		// TODO Auto-generated method stub
		
		titlelabel.setText("测试用例");
		titlelabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		titlelabel.setForeground(new Color(255, 255, 255));
		titlelabel.setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 0));
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "triangulararrow.png");
		icon1.setImage(icon1.getImage().getScaledInstance(8,4, Image.SCALE_DEFAULT));
		titleiconlabel1.setIcon(icon1);
		titleiconlabel1.setBorder(BorderFactory.createEmptyBorder(4, 4,4, 6));
		ImageIcon icon2 = new ImageIcon(path + "downarrow.png");
		icon2.setImage(icon2.getImage().getScaledInstance(7,11, Image.SCALE_DEFAULT));
		titleiconlabel2.setIcon(icon2);
		titleiconlabel2.setBorder(BorderFactory.createEmptyBorder(4, 4,4, 4));
		ImageIcon icon3 = new ImageIcon(path + "fork.png");
		icon3.setImage(icon3.getImage().getScaledInstance(10,8, Image.SCALE_DEFAULT));
		titleiconlabel3.setIcon(icon3);
		titleiconlabel3.setBorder(BorderFactory.createEmptyBorder(4, 4,4, 4));
		
		titleiconlabel3.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				mainFrame.getJs1().setDividerSize(0);
				mainFrame.getOpreationPart().setVisible(false);
				mainFrame.getOneTouchExpandablePanel().setFlag1(0);
				mainFrame.getOneTouchExpandablePanel().setLocation1(mainFrame.getJs1().getDividerLocation());
				
			}
		});
		
		titleiconlabelpanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		titleiconlabelpanel.setOpaque(false);
		titleiconlabelpanel.add(titleiconlabel1);
		titleiconlabelpanel.add(titleiconlabel2);
		titleiconlabelpanel.add(titleiconlabel3);

		titlepanel.setLayout(new BorderLayout());
		titlepanel.setBackground(new Color(77, 96, 130));
		titlepanel.setPreferredSize(new Dimension(100, 23));
		titlepanel.setMinimumSize(new Dimension(100, 23));
		titlepanel.add(titlelabel,BorderLayout.WEST);
		titlepanel.add(titleiconlabelpanel,BorderLayout.EAST);
		
		
	}


	private void initTreePanel() {
		// TODO Auto-generated method stub
		
//		uppaalpanel=new JPanel();
		uppaallabelpanel=new JPanel();
		uppaallabel=new JLabel();
		uppaaltoolpanel=new JPanel();
		uppaaltoolbutton1=new JButton();
		uppaaltoolbutton2=new JButton();
		uppaaltoolbutton3=new JButton();
		uppaaltoolbutton4=new JButton();
		uppaalcheckboxpanel=new JPanel();
		
		coverpanel=new JPanel();
		coverlabelpanel=new JPanel();
		coverlabel=new JLabel();
		covertoolpanel=new JPanel();
		covertoolbutton1=new JButton();
		covercheckboxpanel=new JPanel();
		
		initFileList();
		
		initUppaalPanel();
		
		initCoverPanel();
		
		GridBagLayout layout=new GridBagLayout();
		treeinforpanel.setLayout(layout);
		layout.setConstraints(uppaallabelpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(uppaalscrollpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(coverpanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		treeinforpanel.add(uppaallabelpanel);
		treeinforpanel.add(uppaalscrollpanel);
		treeinforpanel.add(coverpanel);
		treeinforpanel.setBackground(new Color(255, 255, 255));
		
		treepanel.removeAll();
		treepanel.setLayout(new GridLayout());
		treepanel.add(treeinforpanel);
//		treepanel.add(TestCaseFileTree);
		
	}


	private void initUppaalPanel() {
		// TODO Auto-generated method stub
		
		uppaallabel.setText("时间自动机");
		uppaallabel.setForeground(new Color(0,0,0));
		uppaallabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		uppaallabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		
		initUppaalToolPanel();
		
		uppaallabelpanel.setBackground(new Color(207, 214, 229));
		uppaallabelpanel.setLayout(new BorderLayout());
		uppaallabelpanel.add(uppaallabel, BorderLayout.WEST);
		uppaallabelpanel.add(uppaaltoolpanel, BorderLayout.EAST);
		uppaallabelpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(142, 155, 188)));
		uppaallabelpanel.setPreferredSize(new Dimension(100, 29));
		uppaallabelpanel.setMaximumSize(new Dimension(100, 29));
		uppaallabelpanel.setMinimumSize(new Dimension(100, 29));
		
		uppaalcheckboxpanel.setLayout(new BoxLayout(uppaalcheckboxpanel, BoxLayout.Y_AXIS));
		uppaalcheckboxpanel.setBorder(BorderFactory.createEmptyBorder(0, 7, 0, 0));
		uppaalcheckboxpanel.setBackground(new Color(255, 255, 255));
		
		addCheckBoxToUppaalcheckboxpanel();
		
		uppaalscrollpanel=new JScrollPane(uppaalcheckboxpanel);
		uppaalscrollpanel.setBorder(null);
		uppaalscrollpanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		uppaalscrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		
	}


	private void initUppaalToolPanel() {
		// TODO Auto-generated method stub
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "allselect.png");
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(path + "cancelselect.png");
		icon2.setImage(icon2.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon3 = new ImageIcon(path + "refresh.png");
		icon3.setImage(icon3.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon4 = new ImageIcon(path + "dropdown.png");
		icon4.setImage(icon4.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));

		uppaaltoolbutton1.setIcon(icon1);
		uppaaltoolbutton1.setFocusable(false);
		uppaaltoolbutton1.setContentAreaFilled(false);
		uppaaltoolbutton1.setBorderPainted(false);
		uppaaltoolbutton1.addMouseListener(new ButtonMouseListener());
		uppaaltoolbutton1.setPreferredSize(new Dimension(21,21));
		uppaaltoolbutton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				for(JCheckBox jcb:uppaalCheckBoxList){
					jcb.setSelected(true);
				}
				
			}
		});
		
		uppaaltoolbutton2.setIcon(icon2);
		uppaaltoolbutton2.setFocusable(false);
		uppaaltoolbutton2.setContentAreaFilled(false);
		uppaaltoolbutton2.setBorderPainted(false);
		uppaaltoolbutton2.addMouseListener(new ButtonMouseListener());
		uppaaltoolbutton2.setPreferredSize(new Dimension(21,21));
		uppaaltoolbutton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				for(JCheckBox jcb:uppaalCheckBoxList){
					jcb.setSelected(false);
				}
				
			}
		});
		
		uppaaltoolbutton3.setIcon(icon3);
		uppaaltoolbutton3.setFocusable(false);
		uppaaltoolbutton3.setContentAreaFilled(false);
		uppaaltoolbutton3.setBorderPainted(false);
		uppaaltoolbutton3.addMouseListener(new ButtonMouseListener());
		uppaaltoolbutton3.setPreferredSize(new Dimension(21,21));
		uppaaltoolbutton3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				updateFileList();
				
//				uppaallists.clear();
//				initFileList();
//				addCheckBoxToUppaalcheckboxpanel();
				
			}
		});
		
		uppaaltoolbutton4.setIcon(icon4);
		uppaaltoolbutton4.setFocusable(false);
		uppaaltoolbutton4.setContentAreaFilled(false);
		uppaaltoolbutton4.setBorderPainted(false);
		uppaaltoolbutton4.addMouseListener(new ButtonMouseListener());
		uppaaltoolbutton4.setPreferredSize(new Dimension(21,21));
		uppaaltoolbutton4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(uppaalscrollpanel.isVisible()){
					uppaalscrollpanel.setVisible(false);
					ChangeRepaint();
				}
				else{
					uppaalscrollpanel.setVisible(true);
					ChangeRepaint();
				}
			}
		});
		
		uppaaltoolpanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,4));
		uppaaltoolpanel.setOpaque(false);
//		uppaaltoolpanel.add(uppaaltoolbutton1);
//		uppaaltoolpanel.add(uppaaltoolbutton2);
		uppaaltoolpanel.add(uppaaltoolbutton3);
		uppaaltoolpanel.add(uppaaltoolbutton4);
		
		
	}


	public void updateFileList() {
		// TODO Auto-generated method stub
		
		uppaallists.clear();
		initFileList();
		addCheckBoxToUppaalcheckboxpanel();
		
	}


	private void addCheckBoxToUppaalcheckboxpanel() {
		// TODO Auto-generated method stub
		
		uppaalcheckboxpanel.removeAll();
		uppaalCheckBoxList=new JCheckBox[uppaallists.size()];
		for(int i=0;i<uppaallists.size();i++){
			uppaalCheckBoxList[i]=new JCheckBox(uppaallists.get(i)){
				
			};
			uppaalCheckBoxList[i].setOpaque(false);
			uppaalcheckboxpanel.add(Box.createVerticalStrut(7));
			uppaalcheckboxpanel.add(uppaalCheckBoxList[i]);
			if(i==0){
				uppaalCheckBoxList[i].setSelected(true);
			}
		}
		
		if(uppaalCheckBoxList.length>0){
			selectUppaalCheckBox=uppaalCheckBoxList[0];
		}
		else{
			selectUppaalCheckBox=new JCheckBox();
		}
		
		for(final JCheckBox jcb:uppaalCheckBoxList){
			
			jcb.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					if(jcb.isSelected()){
						selectUppaalCheckBox.setSelected(false);
						selectUppaalCheckBox=jcb;
					}
					else{
						selectUppaalCheckBox=new JCheckBox();
					}
				}
			});
			
		}
		
	}


	private void initCoverPanel() {
		// TODO Auto-generated method stub
		
		coverlabel.setText("覆盖条件");
		coverlabel.setForeground(new Color(0,0,0));
		coverlabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		coverlabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "dropdown.png");
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		
		covertoolbutton1.setIcon(icon1);
		covertoolbutton1.setFocusable(false);
		covertoolbutton1.setContentAreaFilled(false);
		covertoolbutton1.setBorderPainted(false);
		covertoolbutton1.addMouseListener(new ButtonMouseListener());
		covertoolbutton1.setPreferredSize(new Dimension(21,21));
		covertoolbutton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if(coverscrollpanel.isVisible()){
					coverscrollpanel.setVisible(false);
				}
				else{
					coverscrollpanel.setVisible(true);
				}
			}
		});
		
		covertoolpanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,4));
		covertoolpanel.setOpaque(false);
		covertoolpanel.add(covertoolbutton1);
		
		coverlabelpanel.setBackground(new Color(207, 214, 229));
		coverlabelpanel.setLayout(new BorderLayout());
		coverlabelpanel.add(coverlabel, BorderLayout.WEST);
		coverlabelpanel.add(covertoolpanel, BorderLayout.EAST);
		coverlabelpanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(142, 155, 188)));
		coverlabelpanel.setPreferredSize(new Dimension(100, 29));
		coverlabelpanel.setMaximumSize(new Dimension(100, 29));
		coverlabelpanel.setMinimumSize(new Dimension(100, 29));
		
		covercheckboxpanel.setLayout(new BoxLayout(covercheckboxpanel, BoxLayout.Y_AXIS));
		covercheckboxpanel.setBorder(BorderFactory.createEmptyBorder(0, 7, 0, 0));
		covercheckboxpanel.setBackground(new Color(255, 255, 255));

		coverlists.add("状态覆盖");
		coverlists.add("路径覆盖");
//		coverlists.add("性能测试");
		
		addCheckBoxToCovercheckboxpanel();
		
		coverscrollpanel=new JScrollPane(covercheckboxpanel);
		coverscrollpanel.setBorder(null);
		coverscrollpanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		coverscrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		coverpanel.setLayout(new BorderLayout());
		coverpanel.add(coverlabelpanel, BorderLayout.NORTH);
		coverpanel.add(coverscrollpanel,BorderLayout.CENTER);
		coverpanel.setBackground(new Color(255, 255, 255));
		
	}


	private void addCheckBoxToCovercheckboxpanel() {
		// TODO Auto-generated method stub
		
		covercheckboxpanel.removeAll();
		coverCheckBoxList=new JCheckBox[coverlists.size()];
		for(int i=0;i<coverlists.size();i++){
			coverCheckBoxList[i]=new JCheckBox(coverlists.get(i));
			coverCheckBoxList[i].setOpaque(false);
			covercheckboxpanel.add(Box.createVerticalStrut(7));
			covercheckboxpanel.add(coverCheckBoxList[i]);
		}
		
		selectCoverCheckBox=new JCheckBox();
		
		for(final JCheckBox jcb:coverCheckBoxList){
			
			jcb.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					if(jcb.isSelected()){
						selectCoverCheckBox.setSelected(false);
						selectCoverCheckBox=jcb;
					}
					else{
						selectCoverCheckBox=new JCheckBox();
					}
					
				}
			});
			
		}
		
		coverCheckBoxList[0].setSelected(true);
		selectCoverCheckBox=coverCheckBoxList[0];//默认选中
		
	}


	public void initFileList() {
		
		int starttype=mainFrame.getHomeAllTabbedPanel().getStarttype();
		
		File[] filelists=getAllFileByDiagramType(starttype);
	    for(File file : filelists)
	    {
	    	String fileName=file.getName();
	    	if(fileName.lastIndexOf(".xml")>0){
	    		uppaallists.add(fileName.substring(0, fileName.lastIndexOf(".xml")));
	    	}
	    	
	    }
	}
	/**
	  * 根据类型获取文件夹下的所有文件
	  * @param type
	  * @return
	  */
	public File[] getAllFileByDiagramType(int starttype) {
		String baseUrl = "D:\\ModelDriverProjectFile\\UPPAL\\3.Abstract_TestCase";

		File[] fList = null;
		File file = null;

		if (starttype == 1) {
			file = new File(baseUrl + "\\FunctionalTest");
			fList = file.listFiles();
		} else if (starttype == 2) {
			file = new File(baseUrl + "\\PerformanceTest");
			fList = file.listFiles();
		} else if (starttype == 3) {
			file = new File(baseUrl + "\\TimeTest");
			fList = file.listFiles();
		} else {
			file = new File(baseUrl);
			fList = file.listFiles();
		}

		return fList;
	}
	
	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}


	public JCheckBox getSelectUppaalCheckBox() {
		return selectUppaalCheckBox;
	}


	public JCheckBox[] getUppaalCheckBoxList() {
		return uppaalCheckBoxList;
	}


	public JCheckBox getSelectCoverCheckBox() {
		return selectCoverCheckBox;
	}


	public JCheckBox[] getCoverCheckBoxList() {
		return coverCheckBoxList;
	}


	public JPanel getCoverpanel() {
		return coverpanel;
	}

	
	
}

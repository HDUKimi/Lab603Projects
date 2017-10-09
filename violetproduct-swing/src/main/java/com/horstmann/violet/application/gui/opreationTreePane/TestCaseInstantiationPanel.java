package com.horstmann.violet.application.gui.opreationTreePane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.StepFourCenterTabbedPane;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.ButtonTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.MyLabelCellEditor;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.MyUppaalLabelRender;
import com.horstmann.violet.application.gui.util.ckt.handle.Automatic;
import com.horstmann.violet.application.gui.util.ckt.handle.Transition;
import com.l2fprod.common.swing.JTaskPane;
import com.l2fprod.common.swing.JTaskPaneGroup;

public class TestCaseInstantiationPanel extends JPanel{
  
	public  MainFrame mainFrame;
	
    private JPanel titlepanel;
	private JPanel treepanel;
	
	private JLabel titlelabel;
	private JPanel titleiconlabelpanel;
	private JLabel titleiconlabel1;
	private JLabel titleiconlabel2;
	private JLabel titleiconlabel3;
	
	private JPanel treeinfopanel;
	
//	private JPanel abstractpanel;
	private JPanel abstractlabelpanel;
	private JLabel abstractlabel;
	private JPanel abstracttoolpanel;
	private JButton abstracttoolbutton1;
	private JButton abstracttoolbutton2;
	private JButton abstracttoolbutton3;
	private JButton abstracttoolbutton4;
	private JPanel abstractcheckboxpanel;
	private JScrollPane abstractscrollpanel;
	
	private JPanel instantiatepanel;
	private JPanel instantiatelabelpanel;
	private JLabel instantiatelabel;
	private JPanel instantiatetoolpanel;
	private JButton instantiatetoolbutton1;
	private JPanel instantiatetablepanel;
	private JScrollPane instantiatescrollpanel;
	
	private DefaultTableModel instantiatetablemodel;
	private JTable instantiatetable;
	
	private JCheckBox selectAbstractCheckBox;
	private List<List<JCheckBox>> abstractCheckBoxList=new ArrayList<>();
	
	private List<String> testlists=new ArrayList<String>();
	private List<List<String>> abstractlists=new ArrayList<>();
	
	private JRadioButton selectTestRadioButton;
	private JRadioButton[] testRadioButtonList;
	
	public TestCaseInstantiationPanel(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub			
		
		this.mainFrame = mainFrame;
		
		titlepanel = new JPanel();
		treepanel = new JPanel();
		
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
		
		titlelabel.setText("实例化");
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
		
//		treepanel.removeAll();
//		treepanel.setLayout(new GridLayout());
//		treepanel.add(TestCaseFileTree);
		
		treeinfopanel=new JPanel();
		
//		abstractpanel=new JPanel();
		abstractlabelpanel=new JPanel();
		abstractlabel=new JLabel();
		abstracttoolpanel=new JPanel();
		abstracttoolbutton1=new JButton();
		abstracttoolbutton2=new JButton();
		abstracttoolbutton3=new JButton();
		abstracttoolbutton4=new JButton();
		abstractcheckboxpanel=new JPanel();
		
		instantiatepanel=new JPanel();
		instantiatelabelpanel=new JPanel();
		instantiatelabel=new JLabel();
		instantiatetoolpanel=new JPanel();
		instantiatetoolbutton1=new JButton();
		instantiatetablepanel=new JPanel();
		
		testlists.add("功能测试");
		testlists.add("性能测试");
		testlists.add("时间约束测试");
		
		initFileList();
		
		initAbstractPanel();
		
		initInstantiatePanel();
		
		JPanel emptypanel=new JPanel();
		emptypanel.setLayout(new GridLayout());
		emptypanel.setOpaque(false);
		
		GridBagLayout layout=new GridBagLayout();
		treeinfopanel.setLayout(layout);
		layout.setConstraints(abstractlabelpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(abstractscrollpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(emptypanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		layout.setConstraints(instantiatepanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		treeinfopanel.add(abstractlabelpanel);
		treeinfopanel.add(abstractscrollpanel);
		treeinfopanel.add(emptypanel);
//		treeinfopanel.add(instantiatepanel);
		treeinfopanel.setBackground(new Color(255, 255, 255));
		
		treepanel.removeAll();
		treepanel.setLayout(new GridLayout());
		treepanel.add(treeinfopanel);
//		treepanel.add(TestCaseFileTree);
		
	}
	
	private void initAbstractPanel() {
		// TODO Auto-generated method stub
		
		abstractlabel.setText("测试序列");
		abstractlabel.setForeground(new Color(0,0,0));
		abstractlabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		abstractlabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		
		initAbstractToolPanel();
		
		abstractlabelpanel.setBackground(new Color(207, 214, 229));
		abstractlabelpanel.setLayout(new BorderLayout());
		abstractlabelpanel.add(abstractlabel, BorderLayout.WEST);
		abstractlabelpanel.add(abstracttoolpanel, BorderLayout.EAST);
		abstractlabelpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(142, 155, 188)));
		abstractlabelpanel.setPreferredSize(new Dimension(100, 29));
		abstractlabelpanel.setMaximumSize(new Dimension(100, 29));
		abstractlabelpanel.setMinimumSize(new Dimension(100, 29));
		
		abstractcheckboxpanel.setLayout(new BoxLayout(abstractcheckboxpanel, BoxLayout.Y_AXIS));
		abstractcheckboxpanel.setBorder(BorderFactory.createEmptyBorder(0, 7, 0, 0));
		abstractcheckboxpanel.setBackground(new Color(255, 255, 255));
		
		addCheckBoxToAbstractcheckboxpanel();
		
		abstractscrollpanel=new JScrollPane(abstractcheckboxpanel);
		abstractscrollpanel.setBorder(null);
		abstractscrollpanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		abstractscrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
	}
	
	private void initAbstractToolPanel() {
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

		abstracttoolbutton1.setIcon(icon1);
		abstracttoolbutton1.setFocusable(false);
		abstracttoolbutton1.setContentAreaFilled(false);
		abstracttoolbutton1.setBorderPainted(false);
		abstracttoolbutton1.addMouseListener(new ButtonMouseListener());
		abstracttoolbutton1.setPreferredSize(new Dimension(21,21));
		abstracttoolbutton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
//				for(JCheckBox jcb:abstractCheckBoxList){
//					jcb.setSelected(true);
//				}
				
				
				readAbstractTestCaseSerialFile();
				
			}
		});
		
		abstracttoolbutton2.setIcon(icon2);
		abstracttoolbutton2.setFocusable(false);
		abstracttoolbutton2.setContentAreaFilled(false);
		abstracttoolbutton2.setBorderPainted(false);
		abstracttoolbutton2.addMouseListener(new ButtonMouseListener());
		abstracttoolbutton2.setPreferredSize(new Dimension(21,21));
		abstracttoolbutton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
//				for(JCheckBox jcb:abstractCheckBoxList){
//					jcb.setSelected(false);
//				}
				
			}
		});
		
		abstracttoolbutton3.setIcon(icon3);
		abstracttoolbutton3.setFocusable(false);
		abstracttoolbutton3.setContentAreaFilled(false);
		abstracttoolbutton3.setBorderPainted(false);
		abstracttoolbutton3.addMouseListener(new ButtonMouseListener());
		abstracttoolbutton3.setPreferredSize(new Dimension(21,21));
		abstracttoolbutton3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				updateFileList();
				
//				abstractlists.clear();
//				initFileList();
//				addCheckBoxToAbstractcheckboxpanel();
				
			}
		});
		
		abstracttoolbutton4.setIcon(icon4);
		abstracttoolbutton4.setFocusable(false);
		abstracttoolbutton4.setContentAreaFilled(false);
		abstracttoolbutton4.setBorderPainted(false);
		abstracttoolbutton4.addMouseListener(new ButtonMouseListener());
		abstracttoolbutton4.setPreferredSize(new Dimension(21,21));
		abstracttoolbutton4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(abstractscrollpanel.isVisible()){
					abstractscrollpanel.setVisible(false);
					ChangeRepaint();
				}
				else{
					abstractscrollpanel.setVisible(true);
					ChangeRepaint();
				}
				
			}
		});
		
		abstracttoolpanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,4));
		abstracttoolpanel.setOpaque(false);
//		abstracttoolpanel.add(abstracttoolbutton1);
//		abstracttoolpanel.add(abstracttoolbutton2);
		abstracttoolpanel.add(abstracttoolbutton3);
		abstracttoolpanel.add(abstracttoolbutton4);
		
	}

	public void readAbstractTestCaseSerialFile() {
		// TODO Auto-generated method stub
		
		String name=selectAbstractCheckBox.getText();
		System.out.println(name);
		
		List<Automatic> abstractAutomatic=new ArrayList<>();
		try {
			String serialpath = "D:\\ModelDriverProjectFile\\UPPAL\\3.Abstract_TestCase\\FunctionalTest\\"+name+".txt";
			FileInputStream fis = new FileInputStream(serialpath);
			ObjectInputStream ois = new ObjectInputStream(fis);

			while(true){//使用处理异常的方式来判断文件是否结束
				try {
					Automatic auto=(Automatic) ois.readObject();//文件读取完毕后，会抛异常
					abstractAutomatic.add(auto);
				} catch (Exception  e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
					System.out.println("文件读取完毕!");  
	                break;  
				}
			}

			ois.close();
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.err.println("----------------------------------------");
		System.out.println(abstractAutomatic.size());
		for(Automatic auto:abstractAutomatic){
			System.out.println("auto.getTransitionSet().size() : "+auto.getTransitionSet().size());
//			for(Transition t:auto.getTransitionSet()){
//				System.out.println(t.toString1());
//			}
		}
		
	}


	public void updateFileList() {
		// TODO Auto-generated method stub
		
		abstractlists.clear();
		initFileList();
		addCheckBoxToAbstractcheckboxpanel();
		
		ChangeRepaint();
		
	}


	private void addCheckBoxToAbstractcheckboxpanel() {
		// TODO Auto-generated method stub
		
		abstractcheckboxpanel.removeAll();
		
//		abstractCheckBoxList=new JCheckBox[abstractlists.get(2).size()];
//		for(int i=0;i<abstractlists.get(2).size();i++){
//			abstractCheckBoxList[i]=new JCheckBox(abstractlists.get(2).get(i));
//			abstractCheckBoxList[i].setOpaque(false);
//			abstractcheckboxpanel.add(Box.createVerticalStrut(7));
//			abstractcheckboxpanel.add(abstractCheckBoxList[i]);
//			if(i==0){
//				abstractCheckBoxList[i].setSelected(true);
//			}
//		}
		
		testRadioButtonList=new JRadioButton[testlists.size()];
		abstractCheckBoxList=new ArrayList<>();
		ButtonGroup buttonGroup=new ButtonGroup();
		
		for(int i=0;i<testlists.size();i++){
			
			testRadioButtonList[i]=new JRadioButton(testlists.get(i));
			testRadioButtonList[i].setOpaque(false);
			buttonGroup.add(testRadioButtonList[i]);
			abstractcheckboxpanel.add(Box.createVerticalStrut(7));
			abstractcheckboxpanel.add(testRadioButtonList[i]);
			
			List<JCheckBox> checkBoxs=new ArrayList<>();
			for(int j=0;j<abstractlists.get(i).size();j++){
				JCheckBox checkBox=new JCheckBox(abstractlists.get(i).get(j));
				checkBox.setOpaque(false);
				checkBox.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
				abstractcheckboxpanel.add(Box.createVerticalStrut(7));
				abstractcheckboxpanel.add(checkBox);
				checkBoxs.add(checkBox);
			}
			abstractCheckBoxList.add(checkBoxs);			
		}
		
		selectTestRadioButton=new JRadioButton();
		for(final JRadioButton radioButton:testRadioButtonList){
			radioButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(radioButton.equals(selectTestRadioButton)){
						
					}
					else{
						selectTestRadioButton.setSelected(false);
						selectTestRadioButton=radioButton;
						selectTestRadioButton.setSelected(true);
						
						selectAbstractCheckBox.setSelected(false);
						selectAbstractCheckBox=abstractCheckBoxList.get(FindRadioButtonIndex(radioButton)).get(0);
						selectAbstractCheckBox.setSelected(true);
					}
					
				}
			});
		}
		
		selectAbstractCheckBox=new JCheckBox();
		for(int i=0;i<abstractCheckBoxList.size();i++){
			for(int j=0;j<abstractCheckBoxList.get(i).size();j++){
				final JCheckBox checkBox=abstractCheckBoxList.get(i).get(j);
				checkBox.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(checkBox.equals(selectAbstractCheckBox)){
							checkBox.setSelected(true);
						}
						else{
							selectAbstractCheckBox.setSelected(false);
							selectAbstractCheckBox=checkBox;
							selectAbstractCheckBox.setSelected(true);
							
							selectTestRadioButton.setSelected(false);
							selectTestRadioButton=testRadioButtonList[FindCoverCheckBoxFrontRadioButtonIndex(checkBox)];
							selectTestRadioButton.setSelected(true);
						}
					}
				});
			}
		}
		
		
//		if(abstractCheckBoxList.length>0){
//			selectAbstractCheckBox=abstractCheckBoxList[0][0];
//		}
//		else{
//			selectAbstractCheckBox=new JCheckBox();
//		}
//		
//		for(final JCheckBox jcb:abstractCheckBoxList[0]){
//			
//			jcb.addActionListener(new ActionListener() {
//				
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					// TODO Auto-generated method stub
//					
//					if(jcb.isSelected()){
//						selectAbstractCheckBox.setSelected(false);
//						selectAbstractCheckBox=jcb;
//					}
//					else{
//						selectAbstractCheckBox=new JCheckBox();
//					}
//				}
//			});
//			
//		}
		
		//默认选中
		int becomeRunFileNameType=StepFourCenterTabbedPane.getBecomeRunFileNameType();
		if(becomeRunFileNameType!=-1){
			for(JCheckBox checkBox:abstractCheckBoxList.get(becomeRunFileNameType-1)){
				if(StepFourCenterTabbedPane.getBecomeRunFileName().equals(checkBox.getText())){
					checkBox.setSelected(true);
					selectAbstractCheckBox=checkBox;
					testRadioButtonList[becomeRunFileNameType-1].setSelected(true);
					selectTestRadioButton=testRadioButtonList[becomeRunFileNameType-1];
				}
			}
		}
//		testRadioButtonList[0].setSelected(true);
//		abstractCheckBoxList.get(0).get(0).setSelected(true);
//		selectTestRadioButton=testRadioButtonList[0];
//		selectAbstractCheckBox=abstractCheckBoxList.get(0).get(0);
		
	}
	
	public int FindCoverCheckBoxFrontRadioButtonIndex(JCheckBox checkBox) {
		// TODO Auto-generated method stub
		
		for(int i=0;i<abstractCheckBoxList.size();i++){
			for(int j=0;j<abstractCheckBoxList.get(i).size();j++){
				if(checkBox.equals(abstractCheckBoxList.get(i).get(j))){
					return i;
				}
			}
		}
		
		return -1;
	}
	
//	public int FindCoverCheckBoxIndex(JCheckBox checkBox) {
//		// TODO Auto-generated method stub
//		
//		for(int i=0;i<coverCheckBoxList.length;i++){
//			if(i==1){
//				continue;
//			}
//			for(int j=0;j<coverCheckBoxList[i].length;j++){
//				if(checkBox.equals(coverCheckBoxList[i][j])){
//					return j;
//				}
//			}
//		}
//		
//		return -1;
//	}


	public int FindRadioButtonIndex(JRadioButton radioButton) {
		// TODO Auto-generated method stub
		
		for(int i=0;i<testRadioButtonList.length;i++){
			if(radioButton.equals(testRadioButtonList[i])){
				return i;
			}
		}
		return -1;
	}
	
	private void initInstantiatePanel() {
		// TODO Auto-generated method stub
		
		instantiatelabel.setText("实例化测试用例");
		instantiatelabel.setForeground(new Color(0,0,0));
		instantiatelabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		instantiatelabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "dropdown.png");
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		
		instantiatetoolbutton1.setIcon(icon1);
		instantiatetoolbutton1.setFocusable(false);
		instantiatetoolbutton1.setContentAreaFilled(false);
		instantiatetoolbutton1.setBorderPainted(false);
		instantiatetoolbutton1.addMouseListener(new ButtonMouseListener());
		instantiatetoolbutton1.setPreferredSize(new Dimension(21,21));
		instantiatetoolbutton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if(instantiatescrollpanel.isVisible()){
					instantiatescrollpanel.setVisible(false);
				}
				else{
					instantiatescrollpanel.setVisible(true);
				}
			}
		});
		
		instantiatetoolpanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,4));
		instantiatetoolpanel.setOpaque(false);
		instantiatetoolpanel.add(instantiatetoolbutton1);
		
		instantiatelabelpanel.setBackground(new Color(207, 214, 229));
		instantiatelabelpanel.setLayout(new BorderLayout());
		instantiatelabelpanel.add(instantiatelabel, BorderLayout.WEST);
		instantiatelabelpanel.add(instantiatetoolpanel, BorderLayout.EAST);
		instantiatelabelpanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(142, 155, 188)));
		instantiatelabelpanel.setPreferredSize(new Dimension(100, 29));
		instantiatelabelpanel.setMaximumSize(new Dimension(100, 29));
		instantiatelabelpanel.setMinimumSize(new Dimension(100, 29));
		
		instantiatetablemodel=new DefaultTableModel(0, 1){
			@Override  
            public boolean isCellEditable(int row,int column){  
                return false;  
            } 
		};
		
		instantiatetable=new JTable(instantiatetablemodel);
		
		instantiatetable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		instantiatetable.setGridColor(Color.BLACK);
		instantiatetable.setShowGrid(true);
		instantiatetable.setShowHorizontalLines(true);
		instantiatetable.setShowVerticalLines(false);
		instantiatetable.setFillsViewportHeight(true);
		instantiatetable.setRowHeight(24);
		instantiatetable.doLayout();
		
		instantiatetable.getColumnModel().getColumn(0).setCellEditor(new MyLabelCellEditor());
		instantiatetable.getColumnModel().getColumn(0).setCellRenderer(new MyUppaalLabelRender());
		
		instantiatetable.getTableHeader().setVisible(false);  
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();  
        renderer.setPreferredSize(new Dimension(0, 0));  
        instantiatetable.getTableHeader().setDefaultRenderer(renderer); 
        
        instantiatetable.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount()==2){
					
//					ButtonTabbedPanel buttonTabbedPanel=mainFrame.getStepTwoCenterTabbedPane().getSequenceToUppaalDiagramButtonTabbedPanelLists().get(instantiatetable.getSelectedRow());
//					if(!buttonTabbedPanel.isVisible()){
//						buttonTabbedPanel.setVisible(true);
//					}
//					buttonTabbedPanel.getTabbedbutton().doClick();

				}
			}
        	
		});
		
		instantiatetablepanel.setLayout(new GridLayout());
		instantiatetablepanel.add(instantiatetable);
		instantiatetable.setBackground(new Color(238, 238, 242));
		instantiatetable.setBorder(null);
		instantiatetablepanel.setBorder(null);
		
		instantiatescrollpanel=new JScrollPane(instantiatetablepanel);
		instantiatescrollpanel.setBorder(null);
		instantiatescrollpanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		instantiatescrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		instantiatepanel.setLayout(new BorderLayout());
		instantiatepanel.add(instantiatelabelpanel, BorderLayout.NORTH);
		instantiatepanel.add(instantiatescrollpanel,BorderLayout.CENTER);
		instantiatepanel.setBackground(new Color(255, 255, 255));
		
	}

	public void initFileList() {
		
		for(int i=1;i<=3;i++){
			File[] filelists=getAllFileByDiagramType(i);
			List<String> filenames=new ArrayList<>();
		    for(File file : filelists)
		    {
		    	String fileName=file.getName();
		    	if(fileName.lastIndexOf("Abstract.txt")>0){
		    		filenames.add(fileName.substring(0, fileName.lastIndexOf(".txt")));
		    	}
		    }
		    abstractlists.add(filenames);
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


	public JPanel getAbstractcheckboxpanel() {
		return abstractcheckboxpanel;
	}


	public JCheckBox getSelectAbstractCheckBox() {
		return selectAbstractCheckBox;
	}


	public JRadioButton getSelectTestRadioButton() {
		return selectTestRadioButton;
	}


//	public JCheckBox[] getAbstractCheckBoxList() {
//		return abstractCheckBoxList;
//	}
	
	

}

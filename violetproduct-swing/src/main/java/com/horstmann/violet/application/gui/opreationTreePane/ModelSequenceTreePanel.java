package com.horstmann.violet.application.gui.opreationTreePane;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.dom4j.DocumentException;

import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.ButtonTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.MyLabelCellEditor;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.MyUppaalLabelRender;
import com.horstmann.violet.application.gui.util.tanchao.XMLToTree;
import com.horstmann.violet.application.gui.util.xiaole.UppaalTransfrom.ImportByDoubleClick;
import com.horstmann.violet.application.gui.util.xiaole.UppaalTransfrom.TransToVioletUppaal;
import com.horstmann.violet.framework.file.GraphFile;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;
import com.l2fprod.common.swing.JTaskPane;

public class ModelSequenceTreePanel extends JPanel{
	
	private MainFrame mainFrame;
	
//	private JTaskPane taskpane;
	
//	public JTree modelsequencetree;
//	private DefaultTreeModel modelsequencetreemodel;
//	private CheckBoxTreeNode modelsequencetreerootnode;
	
	private List<String> sequencelists=new ArrayList<String>();
	
	private JPanel sequencepanel;
	private JPanel sequencelabelpanel;
	private JLabel sequencelabel;
	private JPanel sequencetoolpanel;
	private JButton sequencetoolbutton1;
	private JButton sequencetoolbutton2;
	private JButton sequencetoolbutton3;
	private JButton sequencetoolbutton4;
	private JPanel sequencecheckboxpanel;
	private JScrollPane sequencescrollpanel;
	
	private JPanel uppaalpanel;
	private JPanel uppaallabelpanel;
	private JLabel uppaallabel;
	private JPanel uppaaltoolpanel;
	private JButton uppaaltoolbutton1;
	private JPanel uppaaltablepanel;
	private JScrollPane uppaalscrollpanel;
	
	private DefaultTableModel uppaaltablemodel;
	private JTable uppaaltable;
	
	private JCheckBox[] sequenceCheckBoxList;
	
//	private JTable sequencetable;
	

	public ModelSequenceTreePanel(MainFrame mainFrame){
		
		this.mainFrame=mainFrame;
		
//		taskpane=new JTaskPane();
		
		sequencepanel=new JPanel();
		sequencelabelpanel=new JPanel();
		sequencelabel=new JLabel();
		sequencetoolpanel=new JPanel();
		sequencetoolbutton1=new JButton();
		sequencetoolbutton2=new JButton();
		sequencetoolbutton3=new JButton();
		sequencetoolbutton4=new JButton();
		sequencecheckboxpanel=new JPanel();
		
		uppaalpanel=new JPanel();
		uppaallabelpanel=new JPanel();
		uppaallabel=new JLabel();
		uppaaltoolpanel=new JPanel();
		uppaaltoolbutton1=new JButton();
		uppaaltablepanel=new JPanel();
		
		initFileList();
		
		initSequencePanel();
		
		initUppaalPanel();
		
//		init();
		
//		modelsequencetree.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
//		taskpane.setLayout(new GridLayout());
//		taskpane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
//		this.setLayout(new GridLayout(2,1));
//		this.add(sequencepanel);
//		this.add(uppaalpanel);
		
//		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		GridBagLayout layout=new GridBagLayout();
		this.setLayout(layout);
//		layout.setConstraints(sequencepanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(sequencelabelpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(sequencescrollpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(uppaalpanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		this.add(sequencepanel);
		this.add(sequencelabelpanel);
		this.add(sequencescrollpanel);
		this.add(uppaalpanel);
		this.setBackground(new Color(255, 255, 255));
			
	}

	private void initUppaalPanel() {
		// TODO Auto-generated method stub
		
		uppaallabel.setText("时间自动机");
		uppaallabel.setForeground(new Color(0,0,0));
		uppaallabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		uppaallabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "dropdown.png");
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		
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
				
				if(uppaalscrollpanel.isVisible()){
					uppaalscrollpanel.setVisible(false);
//					sequencepanel.setVisible(false);
					
				}
				else{
					uppaalscrollpanel.setVisible(true);
//					sequencepanel.setVisible(true);
				}
			}
		});
		
		uppaaltoolpanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,4));
		uppaaltoolpanel.setOpaque(false);
		uppaaltoolpanel.add(uppaaltoolbutton1);
		
		uppaallabelpanel.setBackground(new Color(207, 214, 229));
		uppaallabelpanel.setLayout(new BorderLayout());
		uppaallabelpanel.add(uppaallabel, BorderLayout.WEST);
		uppaallabelpanel.add(uppaaltoolpanel, BorderLayout.EAST);
		uppaallabelpanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(142, 155, 188)));
		uppaallabelpanel.setPreferredSize(new Dimension(100, 29));
		uppaallabelpanel.setMaximumSize(new Dimension(100, 29));
		uppaallabelpanel.setMinimumSize(new Dimension(100, 29));
		
//		inituppaalTree();
		
		uppaaltablemodel=new DefaultTableModel(0, 1){
			@Override  
            public boolean isCellEditable(int row,int column){  
                return false;  
            } 
		};
		
		uppaaltable=new JTable(uppaaltablemodel);
		
		uppaaltable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		uppaaltable.setGridColor(Color.BLACK);
		uppaaltable.setShowGrid(true);
		uppaaltable.setShowHorizontalLines(true);
		uppaaltable.setShowVerticalLines(false);
		uppaaltable.setFillsViewportHeight(true);
		uppaaltable.setRowHeight(24);
		uppaaltable.doLayout();
		
		uppaaltable.getColumnModel().getColumn(0).setCellEditor(new MyLabelCellEditor());
		uppaaltable.getColumnModel().getColumn(0).setCellRenderer(new MyUppaalLabelRender());
		
		uppaaltable.getTableHeader().setVisible(false);  
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();  
        renderer.setPreferredSize(new Dimension(0, 0));  
        uppaaltable.getTableHeader().setDefaultRenderer(renderer); 
        
//        DefaultTableCellRenderer renderer1=new DefaultTableCellRenderer();
//        renderer1.setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
//        renderer1.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
//        uppaaltable.setDefaultRenderer(Object.class, renderer1);
        
        uppaaltable.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount()==2){
					
					ButtonTabbedPanel buttonTabbedPanel=mainFrame.getStepTwoCenterTabbedPane().getSequenceToUppaalDiagramButtonTabbedPanelLists().get(uppaaltable.getSelectedRow());
					if(!buttonTabbedPanel.isVisible()){
						buttonTabbedPanel.setVisible(true);
					}
					buttonTabbedPanel.getTabbedbutton().doClick();

				}
			}
        	
		});
		
		uppaaltablepanel.setLayout(new GridLayout());
		uppaaltablepanel.add(uppaaltable);
		uppaaltable.setBackground(new Color(238, 238, 242));
		uppaaltable.setBorder(null);
		uppaaltablepanel.setBorder(null);
		
		uppaalscrollpanel=new JScrollPane(uppaaltablepanel);
		uppaalscrollpanel.setBorder(null);
		uppaalscrollpanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		uppaalscrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		uppaalscrollpanel.getViewport().setBackground(Color.red);
		
		uppaalpanel.setLayout(new BorderLayout());
		uppaalpanel.add(uppaallabelpanel, BorderLayout.NORTH);
		uppaalpanel.add(uppaalscrollpanel,BorderLayout.CENTER);
		uppaalpanel.setBackground(new Color(255, 255, 255));
		
	}

	private void initSequencePanel() {
		// TODO Auto-generated method stub
		
		sequencelabel.setText("顺序图");
		sequencelabel.setForeground(new Color(0,0,0));
		sequencelabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		sequencelabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		
		initSequenceToolPanel();
		
		sequencelabelpanel.setBackground(new Color(207, 214, 229));
		sequencelabelpanel.setLayout(new BorderLayout());
		sequencelabelpanel.add(sequencelabel, BorderLayout.WEST);
		sequencelabelpanel.add(sequencetoolpanel, BorderLayout.EAST);
		sequencelabelpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(142, 155, 188)));
		sequencelabelpanel.setPreferredSize(new Dimension(100, 29));
		sequencelabelpanel.setMaximumSize(new Dimension(100, 29));
		sequencelabelpanel.setMinimumSize(new Dimension(100, 29));
		
		
		
//		initSequenceTree();
//		
//		sequencetreepanel.setLayout(new GridLayout());
//		sequencetreepanel.add(modelsequencetree);
		
		
		
//		JTable demoTable= new JTable(); 
//		DefaultTableModel dtmDemo = (DefaultTableModel) demoTable.getModel();
//		String[] tableHeads = { "jCheckBox"};
//		dtmDemo.setColumnIdentifiers(tableHeads);
//		demoTable.getColumnModel().getColumn(0).setCellRenderer(new CheckBoxCellRender());
//		demoTable.getColumnModel().getColumn(0).setCellEditor(new CheckBoxCellEditor());
		
		sequencecheckboxpanel.setLayout(new BoxLayout(sequencecheckboxpanel, BoxLayout.Y_AXIS));
		sequencecheckboxpanel.setBorder(BorderFactory.createEmptyBorder(0, 7, 0, 0));
		sequencecheckboxpanel.setBackground(new Color(255, 255, 255));
		
		addCheckBoxToSequencecheckboxpanel();
		
		sequencescrollpanel=new JScrollPane(sequencecheckboxpanel);
		sequencescrollpanel.setBorder(null);
		sequencescrollpanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		sequencescrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
//		sequencepanel.setLayout(new BorderLayout());
//		sequencepanel.add(sequencelabelpanel, BorderLayout.NORTH);
//		sequencepanel.add(sequencescrollpanel,BorderLayout.CENTER);
		
		
	}

	private void addCheckBoxToSequencecheckboxpanel() {
		// TODO Auto-generated method stub
		
		sequencecheckboxpanel.removeAll();
		sequenceCheckBoxList=new JCheckBox[sequencelists.size()];
		for(int i=0;i<sequencelists.size();i++){
			sequenceCheckBoxList[i]=new JCheckBox(sequencelists.get(i));
			sequenceCheckBoxList[i].setOpaque(false);
//			Object[]data={new JCheckBox(sequencelists.get(i))};
//			Object[]data={sequencelists.get(i)};
//			dtmDemo.addRow(data);
			sequencecheckboxpanel.add(Box.createVerticalStrut(7));
			sequencecheckboxpanel.add(sequenceCheckBoxList[i]);
		}
		
	}

	private void initSequenceToolPanel() {
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

		sequencetoolbutton1.setIcon(icon1);
		sequencetoolbutton1.setFocusable(false);
		sequencetoolbutton1.setContentAreaFilled(false);
		sequencetoolbutton1.setBorderPainted(false);
		sequencetoolbutton1.addMouseListener(new ButtonMouseListener());
		sequencetoolbutton1.setPreferredSize(new Dimension(21,21));
		sequencetoolbutton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				for(JCheckBox jcb:sequenceCheckBoxList){
					jcb.setSelected(true);
				}
				
			}
		});
		
		sequencetoolbutton2.setIcon(icon2);
		sequencetoolbutton2.setFocusable(false);
		sequencetoolbutton2.setContentAreaFilled(false);
		sequencetoolbutton2.setBorderPainted(false);
		sequencetoolbutton2.addMouseListener(new ButtonMouseListener());
		sequencetoolbutton2.setPreferredSize(new Dimension(21,21));
		sequencetoolbutton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				for(JCheckBox jcb:sequenceCheckBoxList){
					jcb.setSelected(false);
				}
				
			}
		});
		
		sequencetoolbutton3.setIcon(icon3);
		sequencetoolbutton3.setFocusable(false);
		sequencetoolbutton3.setContentAreaFilled(false);
		sequencetoolbutton3.setBorderPainted(false);
		sequencetoolbutton3.addMouseListener(new ButtonMouseListener());
		sequencetoolbutton3.setPreferredSize(new Dimension(21,21));
		sequencetoolbutton3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				updateFileList();
				
//				sequencelists.clear();
//				initFileList();
//				addCheckBoxToSequencecheckboxpanel();
				
			}
		});
		
		sequencetoolbutton4.setIcon(icon4);
		sequencetoolbutton4.setFocusable(false);
		sequencetoolbutton4.setContentAreaFilled(false);
		sequencetoolbutton4.setBorderPainted(false);
		sequencetoolbutton4.addMouseListener(new ButtonMouseListener());
		sequencetoolbutton4.setPreferredSize(new Dimension(21,21));
		sequencetoolbutton4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(sequencescrollpanel.isVisible()){
					sequencescrollpanel.setVisible(false);
					ChangeRepaint();
//					uppaalpanel.setVisible(false);
//					sequencecheckboxpanel.setVisible(false);
				}
				else{
					sequencescrollpanel.setVisible(true);
					ChangeRepaint();
//					uppaalpanel.setVisible(true);
//					sequencecheckboxpanel.setVisible(true);
				}
			}
		});
		
		sequencetoolpanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,4));
		sequencetoolpanel.setOpaque(false);
		sequencetoolpanel.add(sequencetoolbutton1);
		sequencetoolpanel.add(sequencetoolbutton2);
		sequencetoolpanel.add(sequencetoolbutton3);
		sequencetoolpanel.add(sequencetoolbutton4);
		
	}

	public void updateFileList() {
		// TODO Auto-generated method stub
		
		sequencelists.clear();
		initFileList();
		addCheckBoxToSequencecheckboxpanel();

	}

//	private void initSequenceTree() {
//		// TODO Auto-generated method stub
//		
//		modelsequencetreerootnode=new CheckBoxTreeNode("模型-顺序图列表");
//        
//		for(String str:sequencelists){
//			CheckBoxTreeNode node=new CheckBoxTreeNode(str);
//			modelsequencetreerootnode.add(node);
//		}
		
//        CheckBoxTreeNode node1 = new CheckBoxTreeNode("node_1");  
//        CheckBoxTreeNode node1_1 = new CheckBoxTreeNode("node_1_1");  
//        CheckBoxTreeNode node1_2 = new CheckBoxTreeNode("node_1_2");  
//        CheckBoxTreeNode node1_3 = new CheckBoxTreeNode("node_1_3");  
//        node1.add(node1_1);  
//        node1.add(node1_2);  
//        node1.add(node1_3);  
//        CheckBoxTreeNode node2 = new CheckBoxTreeNode("node_2");  
//        CheckBoxTreeNode node2_1 = new CheckBoxTreeNode("node_2_1");  
//        CheckBoxTreeNode node2_2 = new CheckBoxTreeNode("node_2_2");  
//        node2.add(node2_1);  
//        node2.add(node2_2);  
//        modelsequencetreerootnode.add(node1);  
//        modelsequencetreerootnode.add(node2); 
        
//        modelsequencetreemodel=new DefaultTreeModel(modelsequencetreerootnode);
//		modelsequencetree=new JTree(modelsequencetreemodel);
//		modelsequencetree.addMouseListener(new CheckBoxTreeNodeSelectionListener());  
//        tree.setModel(model);  
//        modelsequencetree.setCellRenderer(new CheckBoxTreeCellRenderer());
		
//	}
	
	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}

	public void initFileList() {
		
		int starttype=mainFrame.getHomeAllTabbedPanel().getStarttype();
		
		File[] sequenceFilelists = getAllFileByDiagramType(starttype);
	//	File[] tdFilelists= getAllFileByDiagramType("timing");	
	   // File[] uppaalFilelists=getAllFileByDiagramType("UPPAAL2");
	    for(File sequenceFile : sequenceFilelists)
	    {
	    	String fileName=sequenceFile.getName();
//	    	fileName.substring(0, fileName.lastIndexOf(".xml"));
	    	if(fileName.lastIndexOf(".seq.violet.xml")>0){
	    		sequencelists.add(fileName.substring(0, fileName.lastIndexOf(".seq.violet.xml")));
	    	}
	    	
	    }
//	    for(File tdFile : tdFilelists)
//	    {
//	    	String fileName=tdFile.getName();
//	    	tdlists.add(fileName);
//	    }
//	    for(File uppaalFile:uppaalFilelists)
//	    {
//	    	String fileName=uppaalFile.getName();
//	    	uppaallists.add(fileName);
//	    }
	}
	/**
	  * 根据类型获取文件夹下的所有文件
	  * @param type
	  * @return
	  */
	 public   File[] getAllFileByDiagramType(int starttype){
		 String baseUrl ="D:\\ModelDriverProjectFile\\SequenceDiagram\\Violet";
		 
		 File[] fList =null;
		 File file=null;
		 
		 if(starttype==1){
			 file =new File(baseUrl+"\\FunctionalTest");
			 fList= file.listFiles();
		 }
		 else if(starttype==2){
			 file =new File(baseUrl+"\\PerformanceTest");
			 fList= file.listFiles();
		 }
		 else if(starttype==3){
			 file =new File(baseUrl+"\\TimeTest");
			 fList= file.listFiles();
		 }
		 else{
			 file =new File(baseUrl);
			 fList= file.listFiles();
		 }
		 
		 return fList;
	}

	public DefaultTableModel getUppaaltablemodel() {
		return uppaaltablemodel;
	}

	public JTable getUppaaltable() {
		return uppaaltable;
	}

	public JCheckBox[] getSequenceCheckBoxList() {
		return sequenceCheckBoxList;
	}
	
	
	
}

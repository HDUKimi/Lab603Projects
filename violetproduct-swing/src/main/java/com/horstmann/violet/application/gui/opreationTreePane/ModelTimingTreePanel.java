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
import java.util.Enumeration;
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
import javax.swing.tree.TreePath;

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

public class ModelTimingTreePanel extends JPanel{
	
	private MainFrame mainFrame;
	
//	private JTaskPane taskpane;
	
//	public JTree modeltimingtree;
//	private DefaultTreeModel modeltimingtreemodel;
//	private CheckBoxTreeNode modeltimingtreerootnode;
	
	private List<String> timinglists=new ArrayList<String>();
	
	private JPanel timingpanel;
	private JPanel timinglabelpanel;
	private JLabel timinglabel;
	private JPanel timingtoolpanel;
	private JButton timingtoolbutton1;
	private JButton timingtoolbutton2;
	private JButton timingtoolbutton3;
	private JButton timingtoolbutton4;
	private JPanel timingcheckboxpanel;
	private JScrollPane timingscrollpanel;
	
	private JPanel uppaalpanel;
	private JPanel uppaallabelpanel;
	private JLabel uppaallabel;
	private JPanel uppaaltoolpanel;
	private JButton uppaaltoolbutton1;
	private JPanel uppaaltablepanel;
	private JScrollPane uppaalscrollpanel;
	
	private DefaultTableModel uppaaltablemodel;
	private JTable uppaaltable;
	
	public JTree uppaaltree;
	private DefaultTreeModel uppaaltreemodel;
	private DefaultMutableTreeNode uppaaltreerootnode;
	
	private JCheckBox[] timingCheckBoxList;
	
//	private JTable timingtable;
	

	public ModelTimingTreePanel(MainFrame mainFrame){
		
		this.mainFrame=mainFrame;
		
//		taskpane=new JTaskPane();
		
		timingpanel=new JPanel();
		timinglabelpanel=new JPanel();
		timinglabel=new JLabel();
		timingtoolpanel=new JPanel();
		timingtoolbutton1=new JButton();
		timingtoolbutton2=new JButton();
		timingtoolbutton3=new JButton();
		timingtoolbutton4=new JButton();
		timingcheckboxpanel=new JPanel();
		
		uppaalpanel=new JPanel();
		uppaallabelpanel=new JPanel();
		uppaallabel=new JLabel();
		uppaaltoolpanel=new JPanel();
		uppaaltoolbutton1=new JButton();
		uppaaltablepanel=new JPanel();
		
		initFileList();
		
		initTimingPanel();
		
		initUppaalPanel();
		
//		init();
		
//		modeltimingtree.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
//		taskpane.setLayout(new GridLayout());
//		taskpane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
//		this.setLayout(new GridLayout(2,1));
//		this.add(timingpanel);
//		this.add(uppaalpanel);
		
//		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		GridBagLayout layout=new GridBagLayout();
		this.setLayout(layout);
//		layout.setConstraints(timingpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(timinglabelpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(timingscrollpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(uppaalpanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		this.add(timingpanel);
		this.add(timinglabelpanel);
		this.add(timingscrollpanel);
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
					ChangeRepaint();
//					timingpanel.setVisible(false);
					
				}
				else{
					uppaalscrollpanel.setVisible(true);
					ChangeRepaint();
//					timingpanel.setVisible(true);
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
					
					ButtonTabbedPanel buttonTabbedPanel=mainFrame.getStepTwoCenterTabbedPane().getTimingToUppaalDiagramButtonTabbedPanelLists().get(uppaaltable.getSelectedRow());
					if(!buttonTabbedPanel.isVisible()){
						buttonTabbedPanel.setVisible(true);
					}
					buttonTabbedPanel.getTabbedbutton().doClick();

				}
			}
        	
		});
        
        uppaaltreerootnode=new DefaultMutableTreeNode("转换后时间自动机列表");
        uppaaltreemodel=new DefaultTreeModel(uppaaltreerootnode);
        uppaaltree=new JTree(uppaaltreemodel);
        
        uppaaltree.setRootVisible(false);
        
        uppaaltree.addMouseListener(new MouseAdapter() {
        	
        	@Override
        	public void mousePressed(MouseEvent e) {
        		// TODO Auto-generated method stub
        		if(e.getClickCount()==2){
        			
        			TreePath selectionpath=uppaaltree.getSelectionPath();
					DefaultMutableTreeNode parentNode=(DefaultMutableTreeNode) selectionpath.getLastPathComponent();
//					System.out.println(parentNode.toString());
					
					int i=0;
					Enumeration<?> en1=uppaaltreerootnode.children();
					while(en1.hasMoreElements()){
						DefaultMutableTreeNode node1;
						node1=(DefaultMutableTreeNode) en1.nextElement();
						i++;
						if(node1.equals(parentNode)){
							
							System.out.println(i+"  "+node1.toString());
							
							ButtonTabbedPanel buttonTabbedPanel=mainFrame.getStepTwoCenterTabbedPane().getTimingToUppaalDiagramButtonTabbedPanelLists().get(i-1);
							if(!buttonTabbedPanel.isVisible()){
								buttonTabbedPanel.setVisible(true);
							}
							buttonTabbedPanel.getTabbedbutton().doClick();
							
						}
						else{
							Enumeration<?> en2=node1.children();
							while(en2.hasMoreElements()){
								DefaultMutableTreeNode node2;
								node2=(DefaultMutableTreeNode) en2.nextElement();
								i++;
								if(node2.equals(parentNode)){
									
									System.out.println(i+"  "+node2.toString());
									
									ButtonTabbedPanel buttonTabbedPanel=mainFrame.getStepTwoCenterTabbedPane().getTimingToUppaalDiagramButtonTabbedPanelLists().get(i-1);
									if(!buttonTabbedPanel.isVisible()){
										buttonTabbedPanel.setVisible(true);
									}
									buttonTabbedPanel.getTabbedbutton().doClick();
									
								}
								
							}
						}
					}
        			
            	}
        	}
        	
		});
		
		uppaaltablepanel.setLayout(new GridLayout());
//		uppaaltablepanel.add(uppaaltable);
		uppaaltablepanel.add(uppaaltree);
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

	private void initTimingPanel() {
		// TODO Auto-generated method stub
		
		timinglabel.setText("时序图");
		timinglabel.setForeground(new Color(0,0,0));
		timinglabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		timinglabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		
		initTimingToolPanel();
		
		timinglabelpanel.setBackground(new Color(207, 214, 229));
		timinglabelpanel.setLayout(new BorderLayout());
		timinglabelpanel.add(timinglabel, BorderLayout.WEST);
		timinglabelpanel.add(timingtoolpanel, BorderLayout.EAST);
		timinglabelpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(142, 155, 188)));
		timinglabelpanel.setPreferredSize(new Dimension(100, 29));
		timinglabelpanel.setMaximumSize(new Dimension(100, 29));
		timinglabelpanel.setMinimumSize(new Dimension(100, 29));
		
		
		
//		initTimingTree();
//		
//		timingtreepanel.setLayout(new GridLayout());
//		timingtreepanel.add(modeltimingtree);
		
		
		
//		JTable demoTable= new JTable(); 
//		DefaultTableModel dtmDemo = (DefaultTableModel) demoTable.getModel();
//		String[] tableHeads = { "jCheckBox"};
//		dtmDemo.setColumnIdentifiers(tableHeads);
//		demoTable.getColumnModel().getColumn(0).setCellRenderer(new CheckBoxCellRender());
//		demoTable.getColumnModel().getColumn(0).setCellEditor(new CheckBoxCellEditor());
		
		timingcheckboxpanel.setLayout(new BoxLayout(timingcheckboxpanel, BoxLayout.Y_AXIS));
		timingcheckboxpanel.setBorder(BorderFactory.createEmptyBorder(0, 7, 0, 0));
		timingcheckboxpanel.setBackground(new Color(255, 255, 255));
		
		addCheckBoxToTimingcheckboxpanel();
		
		timingscrollpanel=new JScrollPane(timingcheckboxpanel);
		timingscrollpanel.setBorder(null);
		timingscrollpanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		timingscrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
//		timingpanel.setLayout(new BorderLayout());
//		timingpanel.add(timinglabelpanel, BorderLayout.NORTH);
//		timingpanel.add(timingscrollpanel,BorderLayout.CENTER);
		
		
	}

	private void addCheckBoxToTimingcheckboxpanel() {
		// TODO Auto-generated method stub
		
		timingcheckboxpanel.removeAll();
		timingCheckBoxList=new JCheckBox[timinglists.size()];
		for(int i=0;i<timinglists.size();i++){
			timingCheckBoxList[i]=new JCheckBox(timinglists.get(i));
			timingCheckBoxList[i].setOpaque(false);
//			Object[]data={new JCheckBox(timinglists.get(i))};
//			Object[]data={timinglists.get(i)};
//			dtmDemo.addRow(data);
			timingcheckboxpanel.add(Box.createVerticalStrut(7));
			timingcheckboxpanel.add(timingCheckBoxList[i]);
			if(i==0){
				timingCheckBoxList[i].setSelected(true);
			}
		}
		
	}

	private void initTimingToolPanel() {
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

		timingtoolbutton1.setIcon(icon1);
		timingtoolbutton1.setFocusable(false);
		timingtoolbutton1.setContentAreaFilled(false);
		timingtoolbutton1.setBorderPainted(false);
		timingtoolbutton1.addMouseListener(new ButtonMouseListener());
		timingtoolbutton1.setPreferredSize(new Dimension(21,21));
		timingtoolbutton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				for(JCheckBox jcb:timingCheckBoxList){
					jcb.setSelected(true);
				}
				
			}
		});
		
		timingtoolbutton2.setIcon(icon2);
		timingtoolbutton2.setFocusable(false);
		timingtoolbutton2.setContentAreaFilled(false);
		timingtoolbutton2.setBorderPainted(false);
		timingtoolbutton2.addMouseListener(new ButtonMouseListener());
		timingtoolbutton2.setPreferredSize(new Dimension(21,21));
		timingtoolbutton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				for(JCheckBox jcb:timingCheckBoxList){
					jcb.setSelected(false);
				}
				
			}
		});
		
		timingtoolbutton3.setIcon(icon3);
		timingtoolbutton3.setFocusable(false);
		timingtoolbutton3.setContentAreaFilled(false);
		timingtoolbutton3.setBorderPainted(false);
		timingtoolbutton3.addMouseListener(new ButtonMouseListener());
		timingtoolbutton3.setPreferredSize(new Dimension(21,21));
		timingtoolbutton3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				updateFileList();
//				timinglists.clear();
//				initFileList();
//				addCheckBoxToTimingcheckboxpanel();
				
			}
		});
		
		timingtoolbutton4.setIcon(icon4);
		timingtoolbutton4.setFocusable(false);
		timingtoolbutton4.setContentAreaFilled(false);
		timingtoolbutton4.setBorderPainted(false);
		timingtoolbutton4.addMouseListener(new ButtonMouseListener());
		timingtoolbutton4.setPreferredSize(new Dimension(21,21));
		timingtoolbutton4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(timingscrollpanel.isVisible()){
					timingscrollpanel.setVisible(false);
					ChangeRepaint();
//					uppaalpanel.setVisible(false);
//					timingcheckboxpanel.setVisible(false);
				}
				else{
					timingscrollpanel.setVisible(true);
					ChangeRepaint();
//					uppaalpanel.setVisible(true);
//					timingcheckboxpanel.setVisible(true);
				}
			}
		});
		
		timingtoolpanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,4));
		timingtoolpanel.setOpaque(false);
		timingtoolpanel.add(timingtoolbutton1);
		timingtoolpanel.add(timingtoolbutton2);
		timingtoolpanel.add(timingtoolbutton3);
		timingtoolpanel.add(timingtoolbutton4);
		
	}

	public void updateFileList() {
		// TODO Auto-generated method stub
		
		timinglists.clear();
		initFileList();
		addCheckBoxToTimingcheckboxpanel();

	}
//	private void initTimingTree() {
//		// TODO Auto-generated method stub
//		
//		modeltimingtreerootnode=new CheckBoxTreeNode("模型-顺序图列表");
//        
//		for(String str:timinglists){
//			CheckBoxTreeNode node=new CheckBoxTreeNode(str);
//			modeltimingtreerootnode.add(node);
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
//        modeltimingtreerootnode.add(node1);  
//        modeltimingtreerootnode.add(node2); 
        
//        modeltimingtreemodel=new DefaultTreeModel(modeltimingtreerootnode);
//		modeltimingtree=new JTree(modeltimingtreemodel);
//		modeltimingtree.addMouseListener(new CheckBoxTreeNodeSelectionListener());  
//        tree.setModel(model);  
//        modeltimingtree.setCellRenderer(new CheckBoxTreeCellRenderer());
		
//	}

	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}
	
	public void initFileList() {
		File[] timingFilelists = getAllFileByDiagramType("timing");
	//	File[] tdFilelists= getAllFileByDiagramType("timing");	
	   // File[] uppaalFilelists=getAllFileByDiagramType("UPPAAL2");
	    for(File timingFile : timingFilelists)
	    {
	    	String fileName=timingFile.getName();
//	    	fileName.substring(0, fileName.lastIndexOf(".xml"));
	    	if(fileName.lastIndexOf(".timing.violet.xml")>0){
	    		timinglists.add(fileName.substring(0,fileName.lastIndexOf(".timing.violet.xml") ));
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
	 public   File[] getAllFileByDiagramType(String type){
//		 File f =FileSystemView.getFileSystemView().getHomeDirectory();
//		String s =f .getAbsolutePath();
		 String baseUrl ="D://ModelDriverProjectFile";
//		String baseUrl =s+"//ModelDriverProjectFile";
//		File bFile = new File(baseUrl);
//		if(!bFile.exists()){
//			bFile.mkdirs();
//		}
		 File[] fList =null;
		 File file=null;
		 if("senquence".equals(type)){
			 file =new File(baseUrl+"\\SequenceDiagram\\Violet");
			 fList= file.listFiles();
		 }else if("timing".equals(type)){
			file =new File(baseUrl+"\\TimingDiagram\\Violet");
			 fList= file.listFiles();
		 }else if("UPPAAL2".equals(type)){
			 //第二步的UPPAAL涉及的自动机
			 file =new File(baseUrl+"\\UPPAAL\\2.UML Model Transfer");
			 fList=file.listFiles();
		 }else if("UPPAAL3".equals(type)){
			 //第三步的UPPAAL涉及的自动机
			 file =new File(baseUrl+"\\UPPAAL\\3.Abstract TestCase");
			 fList= file.listFiles();
		 }else if("UPPAAL4".equals(type)){
			 //第四步的UPPAAL涉及的自动机
			 file =new File(baseUrl+"\\UPPAAL\\4.Real TestCase");
			 fList=file.listFiles();
		 }else if("state".equals(type)){
			 file =new File(baseUrl+"\\StateDiagram\\Violet");
			 fList=file.listFiles();
		 }else if("usecase".equals(type)){
			 file =new File(baseUrl+"\\UsecaseDiagram\\Violet");
			 fList= file.listFiles();
		 }else if("class".equals(type)){
			 file =new File(baseUrl+"\\ClassDiagram\\Violet");
			 fList= file.listFiles();
		 }else if("activity".equals(type)){
			 file =new File(baseUrl+"\\ActivityDiagram\\Violet");
			 fList=file.listFiles();
		 }
		 return fList;
	}

	public DefaultTableModel getUppaaltablemodel() {
		return uppaaltablemodel;
	}

	public JTable getUppaaltable() {
		return uppaaltable;
	}

	public JCheckBox[] getTimingCheckBoxList() {
		return timingCheckBoxList;
	}

	public JTree getUppaaltree() {
		return uppaaltree;
	}

	public DefaultTreeModel getUppaaltreemodel() {
		return uppaaltreemodel;
	}

	public DefaultMutableTreeNode getUppaaltreerootnode() {
		return uppaaltreerootnode;
	}
	
}

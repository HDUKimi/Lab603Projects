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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
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
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.ButtonTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.MyLabelCellEditor;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.MyUppaalLabelRender;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.CompareEAtoAutomata;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.ExistVerification;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.RowStringsForDisplay;
import com.horstmann.violet.application.gui.util.wujun.TimingTransfrom.TimingEAtoUppaal;

public class AbstractUppaalPanel extends JPanel {

	private MainFrame mainFrame;

	private JPanel timingpanel;
	private JPanel timinglabelpanel;
	private JLabel timinglabel;
	private JPanel timingtoolpanel;
	private JButton timingtoolbutton1;
	private JButton timingtoolbutton2;
	private JPanel timingtablepanel;
	private JPanel timingcheckboxpanel;
	private JScrollPane timingscrollpanel;

	private DefaultTableModel timingtablemodel;
	private JTable timingtable;

	private List<String> timinglists = new ArrayList<String>();
	
	private JCheckBox[] timingCheckBoxList;

	private JPanel abstractpanel;
	private JPanel abstractlabelpanel;
	private JLabel abstractlabel;
	private JPanel abstracttoolpanel;
	private JButton abstracttoolbutton1;
	private JPanel abstracttablepanel;
	private JScrollPane abstractscrollpanel;

	private DefaultTableModel abstracttablemodel;
	private JTable abstracttable;

	public AbstractUppaalPanel(MainFrame mainFrame) {

		this.mainFrame = mainFrame;

		timingpanel = new JPanel();
		timinglabelpanel = new JPanel();
		timinglabel = new JLabel();
		timingtoolpanel = new JPanel();
		timingtoolbutton1 = new JButton();
		timingtoolbutton2 = new JButton();
		timingtablepanel = new JPanel();
		timingcheckboxpanel=new JPanel();

		abstractpanel = new JPanel();
		abstractlabelpanel = new JPanel();
		abstractlabel = new JLabel();
		abstracttoolpanel = new JPanel();
		abstracttoolbutton1 = new JButton();
		abstracttablepanel = new JPanel();

		initFileList();

		initTimingPanel();

		initAbstractPanel();

		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(timinglabelpanel);
		this.add(timingscrollpanel);
		this.add(abstractpanel);
		layout.setConstraints(timinglabelpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(timingscrollpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(abstractpanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));

		// Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// int screenWidth = (int) screenSize.getWidth();
		// int screenHeight = (int) screenSize.getHeight();
		// this.setMinimumSize(new Dimension(screenWidth/8, screenHeight-100));

	}

	private void initTimingPanel() {
		// TODO Auto-generated method stub

		timinglabel.setText("时间自动机");
		timinglabel.setForeground(new Color(0, 0, 0));
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

//		initTimingTablePanel();

//		addDataToTimingTable();
		
		timingcheckboxpanel.setLayout(new BoxLayout(timingcheckboxpanel, BoxLayout.Y_AXIS));
		timingcheckboxpanel.setBorder(BorderFactory.createEmptyBorder(0, 7, 0, 0));
		timingcheckboxpanel.setBackground(new Color(255, 255, 255));
		
		addCheckBoxToTimingcheckboxpanel();
		
		timingscrollpanel=new JScrollPane(timingcheckboxpanel);
		timingscrollpanel.setBorder(null);
		timingscrollpanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		timingscrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		

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
		}
	}

	private void initTimingToolPanel() {
		// TODO Auto-generated method stub

		String absolutePath = System.getProperty("user.dir");
		String path = absolutePath + "\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("ImagePart/dropdown.png"));
		icon1.setImage(icon1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(this.getClass().getResource("ImagePart/refresh.png"));
		icon2.setImage(icon2.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));

		timingtoolbutton1.setIcon(icon1);
		timingtoolbutton1.setFocusable(false);
		timingtoolbutton1.setContentAreaFilled(false);
		timingtoolbutton1.setBorderPainted(false);
		timingtoolbutton1.addMouseListener(new ButtonMouseListener());
		timingtoolbutton1.setPreferredSize(new Dimension(21, 21));
		timingtoolbutton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				if (timingscrollpanel.isVisible()) {
					timingscrollpanel.setVisible(false);
					ChangeRepaint();
				} else {
					timingscrollpanel.setVisible(true);
					ChangeRepaint();
				}

			}
		});

		timingtoolbutton2.setIcon(icon2);
		timingtoolbutton2.setFocusable(false);
		timingtoolbutton2.setContentAreaFilled(false);
		timingtoolbutton2.setBorderPainted(false);
		timingtoolbutton2.addMouseListener(new ButtonMouseListener());
		timingtoolbutton2.setPreferredSize(new Dimension(21, 21));
		timingtoolbutton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				timinglists.clear();
				initFileList();
				addDataToTimingTable();

			}
		});

		timingtoolpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 4));
		timingtoolpanel.setOpaque(false);
		timingtoolpanel.add(timingtoolbutton2);
		timingtoolpanel.add(timingtoolbutton1);

	}

	private void initTimingTablePanel() {
		// TODO Auto-generated method stub

		timingtablemodel=new DefaultTableModel(0, 1){
			@Override  
            public boolean isCellEditable(int row,int column){  
                return false;  
            } 
		};
		
		timingtable=new JTable(timingtablemodel);
		
		timingtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		timingtable.setGridColor(Color.BLACK);
		timingtable.setShowGrid(true);
		timingtable.setShowHorizontalLines(true);
		timingtable.setShowVerticalLines(false);
		timingtable.setFillsViewportHeight(true);
		timingtable.setRowHeight(24);
		timingtable.doLayout();
		
		timingtable.getColumnModel().getColumn(0).setCellEditor(new MyLabelCellEditor());
		timingtable.getColumnModel().getColumn(0).setCellRenderer(new MyGeneralLabelRenderer());
		
		timingtable.getTableHeader().setVisible(false);  
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();  
        renderer.setPreferredSize(new Dimension(0, 0));  
        timingtable.getTableHeader().setDefaultRenderer(renderer);
        
        timingtable.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount()==2){

					
				}
			}
        	
		});
		
		timingtablepanel.setLayout(new GridLayout());
		timingtablepanel.add(timingtable);
		timingtable.setBackground(new Color(255,255,255));
		timingtable.setBorder(null);
		timingtablepanel.setBorder(null);
		
		timingscrollpanel=new JScrollPane(timingtablepanel);
		timingscrollpanel.setBorder(null);
//		timingscrollpanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, new Color(142, 155, 188)));
		timingscrollpanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		timingscrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
	}

	private void addDataToTimingTable() {
		// TODO Auto-generated method stub

		while(timingtablemodel.getRowCount()>0){
			timingtablemodel.removeRow(timingtablemodel.getRowCount()-1);
		}
		
		
		for(String str:timinglists){
			Object[] rowData={str};
			timingtablemodel.addRow(rowData);
		}
		
	}

	private void initAbstractPanel() {
		// TODO Auto-generated method stub

		abstractlabel.setText("抽象时间迁移自动机");
		abstractlabel.setForeground(new Color(0,0,0));
		abstractlabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		abstractlabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("ImagePart/dropdown.png"));
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		
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
				
				if(abstractscrollpanel.isVisible()){
					abstractscrollpanel.setVisible(false);
					ChangeRepaint();
//					timingpanel.setVisible(false);
					
				}
				else{
					abstractscrollpanel.setVisible(true);
					ChangeRepaint();
//					timingpanel.setVisible(true);
				}
			}
		});
		
		abstracttoolpanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,4));
		abstracttoolpanel.setOpaque(false);
		abstracttoolpanel.add(abstracttoolbutton1);
		
		abstractlabelpanel.setBackground(new Color(207, 214, 229));
		abstractlabelpanel.setLayout(new BorderLayout());
		abstractlabelpanel.add(abstractlabel, BorderLayout.WEST);
		abstractlabelpanel.add(abstracttoolpanel, BorderLayout.EAST);
		abstractlabelpanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(142, 155, 188)));
		abstractlabelpanel.setPreferredSize(new Dimension(100, 29));
		abstractlabelpanel.setMaximumSize(new Dimension(100, 29));
		abstractlabelpanel.setMinimumSize(new Dimension(100, 29));
		
//		initabstractTree();
		
		abstracttablemodel=new DefaultTableModel(0, 1){
			@Override  
            public boolean isCellEditable(int row,int column){  
                return false;  
            } 
		};
		
		abstracttable=new JTable(abstracttablemodel);
		
		abstracttable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		abstracttable.setGridColor(Color.BLACK);
		abstracttable.setShowGrid(true);
		abstracttable.setShowHorizontalLines(true);
		abstracttable.setShowVerticalLines(false);
		abstracttable.setFillsViewportHeight(true);
		abstracttable.setRowHeight(24);
		abstracttable.doLayout();
		
		abstracttable.getColumnModel().getColumn(0).setCellEditor(new MyLabelCellEditor());
		abstracttable.getColumnModel().getColumn(0).setCellRenderer(new MyUppaalLabelRender());
		
		abstracttable.getTableHeader().setVisible(false);  
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();  
        renderer.setPreferredSize(new Dimension(0, 0));  
        abstracttable.getTableHeader().setDefaultRenderer(renderer); 
        
//        DefaultTableCellRenderer renderer1=new DefaultTableCellRenderer();
//        renderer1.setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
//        renderer1.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
//        abstracttable.setDefaultRenderer(Object.class, renderer1);
        
        abstracttable.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount()==2){
					

				}
			}
        	
		});
		
		abstracttablepanel.setLayout(new GridLayout());
		abstracttablepanel.add(abstracttable);
//		abstracttable.setBackground(new Color(238, 238, 242));
		abstracttable.setBackground(new Color(255,255,255));
		abstracttable.setBorder(null);
		abstracttablepanel.setBorder(null);
		
		abstractscrollpanel=new JScrollPane(abstracttablepanel);
		abstractscrollpanel.setBorder(null);
		abstractscrollpanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		abstractscrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		abstractscrollpanel.getViewport().setBackground(Color.red);
		
		abstractpanel.setLayout(new BorderLayout());
		abstractpanel.add(abstractlabelpanel, BorderLayout.NORTH);
		abstractpanel.add(abstractscrollpanel,BorderLayout.CENTER);
		abstractpanel.setBackground(new Color(255, 255, 255));
		
	}

	public void initFileList() {
		File[] timingFilelists = getAllFileByDiagramType("timing");
		// File[] tdFilelists= getAllFileByDiagramType("timing");
		// File[] abstractFilelists=getAllFileByDiagramType("UPPAAL2");
		for (File timingFile : timingFilelists) {
			String fileName = timingFile.getName();
			// fileName.substring(0, fileName.lastIndexOf(".xml"));
			if (fileName.lastIndexOf(".timing.violet.xml") > 0) {
				timinglists.add(fileName.substring(0, fileName.lastIndexOf(".timing.violet.xml")));
			}
		}
		// for(File tdFile : tdFilelists)
		// {
		// String fileName=tdFile.getName();
		// tdlists.add(fileName);
		// }
		// for(File abstractFile:abstractFilelists)
		// {
		// String fileName=abstractFile.getName();
		// abstractlists.add(fileName);
		// }
	}

	/**
	 * 根据类型获取文件夹下的所有文件
	 * 
	 * @param type
	 * @return
	 */
	public File[] getAllFileByDiagramType(String type) {
		// File f =FileSystemView.getFileSystemView().getHomeDirectory();
		// String s =f .getAbsolutePath();
		String baseUrl = "D://ModelDriverProjectFile";
		// String baseUrl =s+"//ModelDriverProjectFile";
		// File bFile = new File(baseUrl);
		// if(!bFile.exists()){
		// bFile.mkdirs();
		// }
		File[] fList = null;
		File file = null;
		if ("senquence".equals(type)) {
			file = new File(baseUrl + "\\SequenceDiagram\\Violet");
			fList = file.listFiles();
		} else if ("timing".equals(type)) {
			file = new File(baseUrl + "\\TimingDiagram\\Violet");
			fList = file.listFiles();
		} else if ("UPPAAL2".equals(type)) {
			// 第二步的UPPAAL涉及的自动机
			file = new File(baseUrl + "\\UPPAAL\\2.UML Model Transfer");
			fList = file.listFiles();
		} else if ("UPPAAL3".equals(type)) {
			// 第三步的UPPAAL涉及的自动机
			file = new File(baseUrl + "\\UPPAAL\\3.Abstract TestCase");
			fList = file.listFiles();
		} else if ("UPPAAL4".equals(type)) {
			// 第四步的UPPAAL涉及的自动机
			file = new File(baseUrl + "\\UPPAAL\\4.Real TestCase");
			fList = file.listFiles();
		} else if ("state".equals(type)) {
			file = new File(baseUrl + "\\StateDiagram\\Violet");
			fList = file.listFiles();
		} else if ("usecase".equals(type)) {
			file = new File(baseUrl + "\\UsecaseDiagram\\Violet");
			fList = file.listFiles();
		} else if ("class".equals(type)) {
			file = new File(baseUrl + "\\ClassDiagram\\Violet");
			fList = file.listFiles();
		} else if ("activity".equals(type)) {
			file = new File(baseUrl + "\\ActivityDiagram\\Violet");
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
}

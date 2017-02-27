package com.horstmann.violet.application.gui.stepCenterTabbedPane;

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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.horstmann.violet.application.consolepart.LocationTransitionLabelRenderer;
import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;

public class UppaalParseInforTabbedPanel extends JPanel{

	private MainFrame mainFrame;

	private JPanel toolpanel;
	private MoviePanel moviepanel;
	private JPanel inforpanel;

	private JPanel toolbuttonpanel6;
	private JPanel toolbuttonpanel7;
	private JButton toolbutton6;
	private JButton toolbutton7;

	private JScrollPane inforscrollpanel;
	private JPanel inforresultpanel;
	
//	private UppaalParseGeneralInforPanel generalinforpanel;
	private UppaalParseStateInforPartPanel stateinforpanel;
	private UppaalParseMigrateInforPartPanel migrateinforpanel;
	
	private JPanel generalinforpanel;
//	private JPanel stateinforpanel;
//	private JPanel migrateinforpanel;
	private JPanel emptypanel;
	
	private JLabel generalinforlabel1;
	private JLabel generalinforlabel2;
	private JLabel generalinforlabel3;
	private JLabel generalinforlabel4;
	
	private JTable stateinfortable;
	private DefaultTableModel stateinfortablemodel;
	
	private JTable migrateinfortable;
	private DefaultTableModel migrateinfortablemodel;
	
	public UppaalParseInforTabbedPanel(MainFrame mainframe) {

		this.mainFrame = mainframe;

		toolpanel = new JPanel();
		moviepanel = new MoviePanel();
		inforpanel = new JPanel();

		initToolPanel();

		initMoviePanel();

		initInforPanel();

		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(toolpanel);
		this.add(moviepanel);
		this.add(inforpanel);
		layout.setConstraints(toolpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(moviepanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(inforpanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));

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

		ImageIcon icon6 = new ImageIcon(path + "up_arrow.png");
		icon6.setImage(icon6.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon7 = new ImageIcon(path + "down_arrow.png");
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
		
		moviepanel.getMovieLabel().setText("正在解析时间自动机");
		
	}

	private void initInforPanel() {
		// TODO Auto-generated method stub
		
//		generalinforpanel=new UppaalParseGeneralInforPanel();
		stateinforpanel=new UppaalParseStateInforPartPanel(mainFrame);
		migrateinforpanel=new UppaalParseMigrateInforPartPanel(mainFrame);
		
		generalinforpanel=new JPanel();
//		stateinforpanel=new JPanel();
//		migrateinforpanel=new JPanel();
		emptypanel=new JPanel();
		
		emptypanel.setBackground(new Color(255, 255, 255));
		emptypanel.setMinimumSize(new Dimension(1, 1));
		emptypanel.setMaximumSize(new Dimension(1, 1));
		emptypanel.setPreferredSize(new Dimension(1, 1));
		
		initGeneralInforPanel();
		
//		initStateInforPanel();
		
//		initMigrateInforPanel();
		
//		initDataToDate();
		
		
		inforresultpanel=new JPanel();
//		inforresultpanel.setLayout(new GridLayout());
		inforresultpanel.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
		inforresultpanel.setBackground(new Color(255, 255, 255));
		
		GridBagLayout layout = new GridBagLayout();
		inforresultpanel.setLayout(layout);
		inforresultpanel.add(generalinforpanel);
//		inforresultpanel.add(stateinforpanel);
//		inforresultpanel.add(migrateinforpanel);
		inforresultpanel.add(stateinforpanel.getTitlelinepanel());
//		inforresultpanel.add(stateinforpanel.getLinepanel());
		inforresultpanel.add(stateinforpanel.getAttributepanel());
		inforresultpanel.add(migrateinforpanel);
//		inforresultpanel.add(migrateinforpanel.getTitlepanel());
//		inforresultpanel.add(migrateinforpanel.getLinepanel());
//		inforresultpanel.add(migrateinforpanel.getAttributepanel());
//		inforresultpanel.add(emptypanel);
		layout.setConstraints(generalinforpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//		layout.setConstraints(stateinforpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		layout.setConstraints(migrateinforpanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(stateinforpanel.getTitlelinepanel(), new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//		layout.setConstraints(stateinforpanel.getLinepanel(), new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(stateinforpanel.getAttributepanel(), new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(migrateinforpanel, new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		layout.setConstraints(migrateinforpanel.getTitlepanel(), new GBC(0, 4, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//		layout.setConstraints(migrateinforpanel.getLinepanel(), new GBC(0, 5, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//		layout.setConstraints(migrateinforpanel.getAttributepanel(), new GBC(0, 6, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		layout.setConstraints(emptypanel, new GBC(0, 7, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		
		inforscrollpanel=new JScrollPane(inforresultpanel);
//		inforscrollpanel=new JScrollPane();
        inforscrollpanel.setBorder(null);
        inforscrollpanel.setBackground(new Color(255, 255, 255));
		
		inforpanel.setLayout(new GridLayout());
		inforpanel.add(inforresultpanel);
		
		inforpanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		inforpanel.setOpaque(false);
		
	}
	
	private void initDataToDate() {
		// TODO Auto-generated method stub
		
		for(int i=0;i<50;i++){
			Object[] rowData={"1","loc_id_29C2E776_04D4_47f3_8F70_D9F4DD7BEE72_14","loc_id_29C2E776_04D4_47f3_8F70_D9F4DD7BEE72_14","false","CircularNode"};
			stateinfortablemodel.addRow(rowData);
		}
		
		for(int i=0;i<50;i++){
			Object[] rowData={"13","set_throttle_out_unstabilizedfloat, bool, float","g.throttle_filt#g.throttle_filt:float","cycle=2.5ms--control_mode==0#control_mode:int8_t--motor_state==False || ap.throttle_zero==True#motor_state:bool,ap.throttle_zero:bool","null","不空，但是没有内容"};
			migrateinfortablemodel.addRow(rowData);
		}
		
	}

	private void initGeneralInforPanel() {
		// TODO Auto-generated method stub
		
		generalinforlabel1=new JLabel();
		generalinforlabel2=new JLabel();
		generalinforlabel3=new JLabel();
		generalinforlabel4=new JLabel();
		
		generalinforlabel1.setText("时间自动机名字：template_");
		generalinforlabel2.setText("时间自动机时钟集合： t");
		generalinforlabel3.setText("模型中总状态个数：119");
		generalinforlabel4.setText("模型中总迁移个数：220");
		
		generalinforlabel1.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		generalinforlabel2.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		generalinforlabel3.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		generalinforlabel4.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		
		generalinforpanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 0));
		generalinforpanel.setBackground(new Color(255, 255, 255));
		generalinforpanel.setLayout(new GridLayout(2, 2));
		generalinforpanel.add(generalinforlabel1);
		generalinforpanel.add(generalinforlabel2);
		generalinforpanel.add(generalinforlabel3);
		generalinforpanel.add(generalinforlabel4);
		
	}

	private void initStateInforPanel() {
		// TODO Auto-generated method stub
		
		String[] columnNames={"序号","名称","位置","是否为终止状态","类型"};
		String[][] tabelValues={};
		
		stateinfortablemodel=new DefaultTableModel(tabelValues, columnNames){
			@Override  
            public boolean isCellEditable(int row,int column){  
                return false;  
            } 
		};
		
		stateinfortable=new JTable(stateinfortablemodel);
		
		stateinfortable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        stateinfortable.setSelectionBackground(new Color(250, 248, 236));
        stateinfortable.setGridColor(new Color(224, 226, 220));
		stateinfortable.setShowGrid(true);
		stateinfortable.setShowHorizontalLines(true);
		stateinfortable.setShowVerticalLines(false);
		stateinfortable.setFillsViewportHeight(true);
		stateinfortable.setRowHeight(27);
		stateinfortable.doLayout();
		stateinfortable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		stateinfortable.getColumn("序号").setPreferredWidth(10);
		stateinfortable.getColumn("序号").setMinWidth(10);
		stateinfortable.getColumn("名称").setPreferredWidth(300);
		stateinfortable.getColumn("名称").setMinWidth(300);
		stateinfortable.getColumn("位置").setPreferredWidth(300);
		stateinfortable.getColumn("位置").setMinWidth(300);
		stateinfortable.getColumn("是否为终止状态").setPreferredWidth(50);
		stateinfortable.getColumn("是否为终止状态").setMinWidth(50);
		stateinfortable.getColumn("类型").setPreferredWidth(50);
		stateinfortable.getColumn("类型").setMinWidth(50);
        
        DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
        renderer.setBackground(new Color(71, 80, 93));
        renderer.setForeground(new Color(255, 255, 255));
        renderer.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        stateinfortable.getTableHeader().setDefaultRenderer(renderer); 
        
        stateinfortable.getTableHeader().setPreferredSize(new Dimension(100, 27));
        
        DefaultTableCellRenderer renderer1=new DefaultTableCellRenderer();
        renderer1.setForeground(new Color(115, 110, 102));
        renderer1.setBackground(new Color(255, 255, 255));
        renderer1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        renderer1.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        stateinfortable.setDefaultRenderer(Object.class, renderer1); 
        
        stateinfortable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(224, 225, 220)));
		
        stateinfortable.setBackground(new Color(255, 255, 255));
        
        stateinforpanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        stateinforpanel.setBackground(new Color(255, 255, 255));
        
		stateinforpanel.setLayout(new BorderLayout());
		stateinforpanel.add(stateinfortable.getTableHeader(),BorderLayout.NORTH);
		stateinforpanel.add(stateinfortable, BorderLayout.CENTER);
		
	}

	private void initMigrateInforPanel() {
		// TODO Auto-generated method stub
		
		String[] columnNames={"序号","名称","in(约束条件)","conditions(约束条件)","out(输出信息)","重置时钟"};
		String[][] tabelValues={};
		
		migrateinfortablemodel=new DefaultTableModel(tabelValues, columnNames){
			@Override  
            public boolean isCellEditable(int row,int column){  
                return false;  
            } 
		};
		
		migrateinfortable=new JTable(migrateinfortablemodel);
		
		migrateinfortable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        migrateinfortable.setSelectionBackground(new Color(250, 248, 236));
        migrateinfortable.setGridColor(new Color(224, 226, 220));
		migrateinfortable.setShowGrid(true);
		migrateinfortable.setShowHorizontalLines(true);
		migrateinfortable.setShowVerticalLines(false);
		migrateinfortable.setFillsViewportHeight(true);
		migrateinfortable.setRowHeight(27);
		migrateinfortable.doLayout();
		migrateinfortable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		migrateinfortable.getColumn("序号").setPreferredWidth(10);
		migrateinfortable.getColumn("序号").setMinWidth(10);
		migrateinfortable.getColumn("名称").setPreferredWidth(50);
		migrateinfortable.getColumn("名称").setMinWidth(50);
		migrateinfortable.getColumn("in(约束条件)").setPreferredWidth(50);
		migrateinfortable.getColumn("in(约束条件)").setMinWidth(50);
		migrateinfortable.getColumn("conditions(约束条件)").setPreferredWidth(300);
		migrateinfortable.getColumn("conditions(约束条件)").setMinWidth(300);
		migrateinfortable.getColumn("out(输出信息)").setPreferredWidth(50);
		migrateinfortable.getColumn("out(输出信息)").setMinWidth(50);
		migrateinfortable.getColumn("重置时钟").setPreferredWidth(50);
		migrateinfortable.getColumn("重置时钟").setMinWidth(50);
        
        DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
        renderer.setBackground(new Color(71, 80, 93));
        renderer.setForeground(new Color(255, 255, 255));
        renderer.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        migrateinfortable.getTableHeader().setDefaultRenderer(renderer); 
        
        migrateinfortable.getTableHeader().setPreferredSize(new Dimension(100, 27));
        
        DefaultTableCellRenderer renderer1=new DefaultTableCellRenderer();
        renderer1.setForeground(new Color(115, 110, 102));
        renderer1.setBackground(new Color(255, 255, 255));
        renderer1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        renderer1.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        migrateinfortable.setDefaultRenderer(Object.class, renderer1); 
        
        migrateinfortable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(224, 225, 220)));
		
        migrateinfortable.setBackground(new Color(255, 255, 255));
        
        migrateinforpanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        migrateinforpanel.setBackground(new Color(255, 255, 255));
        
        migrateinforpanel.setLayout(new BorderLayout());
        migrateinforpanel.add(migrateinfortable.getTableHeader(),BorderLayout.NORTH);
        migrateinforpanel.add(migrateinfortable, BorderLayout.CENTER);
		
        
	}

	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}
	
}

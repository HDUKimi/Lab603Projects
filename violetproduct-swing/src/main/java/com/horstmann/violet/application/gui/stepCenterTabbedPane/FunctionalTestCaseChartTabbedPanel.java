package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartPanel;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.BarChart;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.LineChart;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.PieChart;

public class FunctionalTestCaseChartTabbedPanel extends JPanel{
	
	private MainFrame mainFrame;
	
	private JPanel toolpanel;
	private MoviePanel moviepanel;
	private JPanel reportpanel;
	
//	private JPanel toolbuttonpanel1;
//	private JButton toolbutton1;
	
	private JCheckBox checkbox1;
	private JCheckBox checkbox2;
	private JCheckBox checkbox3;
	private JCheckBox checkbox4;
	private JCheckBox checkbox5;
	private JCheckBox checkbox6;
	
	private JScrollPane reportscrollpanel;
	private JPanel reportresultpanel;
	
	private JPanel tablepanel;
	private JTable attributetable;
	private DefaultTableModel attributetablemodel;
	
	private JPanel successfailedtablepanel;
	private JTable successfailedattributetable;
	private DefaultTableModel successfailedattributetablemodel;
	private JPanel failedstatisticstablepanel;
	private JTable failedstatisticsattributetable;
	private DefaultTableModel failedstatisticsattributetablemodel;
	
	private JPanel successfailedpiepanel;
	private JPanel failedstatisticspiepanel;
	
	private JPanel chartpanel;
	private JPanel barpanel1;
	private JPanel linepanel1;
	private JPanel piepanel1;
	private JPanel barpanel2;
	private JPanel linepanel2;
	private JPanel piepanel2;

	private BarChart barchart;
	private LineChart linechart;
	private PieChart piechart;
	private ChartPanel barchartpanel1;
	private ChartPanel linechartpanel1;
	private ChartPanel piechartpanel1;
	private ChartPanel barchartpanel2;
	private ChartPanel linechartpanel2;
	private ChartPanel piechartpanel2;
	
	private JPanel leftemptypanel1;
	private JPanel leftemptypanel2;
	private JPanel leftemptypanel3;
	private JPanel leftemptypanel4;
	private JPanel leftemptypanel5;
	private JPanel leftemptypanel6;
	private JPanel rightemptypanel1;
	private JPanel rightemptypanel2;
	private JPanel rightemptypanel3;
	private JPanel rightemptypanel4;
	private JPanel rightemptypanel5;
	private JPanel rightemptypanel6;
	
	private JPanel emptypanel;

	public FunctionalTestCaseChartTabbedPanel(MainFrame mainFrame){ 
		
		this.mainFrame=mainFrame;
		
		toolpanel=new JPanel();
		moviepanel=new MoviePanel();
		reportpanel=new JPanel();
		
		initToolPanel();
		
		initMoviePanel();
		
		initReportPanel();
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(toolpanel);
		this.add(moviepanel);
		this.add(reportpanel);
		layout.setConstraints(toolpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(moviepanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(reportpanel,new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		this.setBackground(new Color(255, 255, 255));
		
	}

	private void initToolPanel() {
		// TODO Auto-generated method stub
		
//		toolbuttonpanel1 = new JPanel();
//		toolbutton1 = new JButton();
//		
//		String absolutePath=System.getProperty("user.dir");
//		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";
//
//		ImageIcon icon1 = new ImageIcon(path + "start.png");
//		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
//		
//		toolbutton1.setIcon(icon1);
//		toolbutton1.setFocusable(false);
//		toolbutton1.setContentAreaFilled(false);
//		toolbutton1.setBorderPainted(false);
//		toolbutton1.addMouseListener(new ButtonMouseListener());
//		toolbutton1.setPreferredSize(new Dimension(21,21));
//		
//		toolbuttonpanel1.setLayout(new GridLayout());
//		toolbuttonpanel1.setBackground(new Color(207, 214, 229));
//		toolbuttonpanel1.add(toolbutton1);
		
		checkbox1=new JCheckBox();
		checkbox2=new JCheckBox();
		checkbox3=new JCheckBox();
		checkbox4=new JCheckBox();
		checkbox5=new JCheckBox();
		checkbox6=new JCheckBox();
		
		checkbox1.setText("统计表格");
		checkbox1.setOpaque(false);
		checkbox1.setSelected(true);
		checkbox1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(checkbox1.isSelected()){
					tablepanel.setVisible(true);
					ChangeRepaint();
				}
				else{
					tablepanel.setVisible(false);
					ChangeRepaint();
				}
			}
		});
		
		checkbox2.setText("柱状图");
		checkbox2.setOpaque(false);
		checkbox2.setSelected(true);
		checkbox2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		checkbox3.setText("折线图");
		checkbox3.setOpaque(false);
		checkbox3.setSelected(true);
		checkbox3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		checkbox4.setText("折线图");
		checkbox4.setOpaque(false);
		checkbox4.setSelected(true);
		checkbox4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		checkbox5.setText("测试结果饼状图");
		checkbox5.setOpaque(false);
		checkbox5.setSelected(true);
		checkbox5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(checkbox5.isSelected()){
					successfailedpiepanel.setVisible(true);
					ChangeRepaint();
				}
				else{
					successfailedpiepanel.setVisible(false);
					ChangeRepaint();
				}
			}
		});
		
		checkbox6.setText("出错类型饼状图");
		checkbox6.setOpaque(false);
		checkbox6.setSelected(true);
		checkbox6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(checkbox6.isSelected()){
					failedstatisticspiepanel.setVisible(true);
					ChangeRepaint();
				}
				else{
					failedstatisticspiepanel.setVisible(false);
					ChangeRepaint();
				}
			}
		});
		
		toolpanel.setBackground(new Color(207, 214, 229));
		toolpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5,3));
		
//		toolpanel.add(toolbuttonpanel1);
		
		toolpanel.add(checkbox1);
//		toolpanel.add(checkbox2);
//		toolpanel.add(checkbox3);
//		toolpanel.add(checkbox4);
		toolpanel.add(checkbox5);
		toolpanel.add(checkbox6);
		
		toolpanel.setPreferredSize(new Dimension(100, 29));
		toolpanel.setMaximumSize(new Dimension(100, 29));
		toolpanel.setMinimumSize(new Dimension(100, 29));
		
	}

	private void initMoviePanel() {
		// TODO Auto-generated method stub
		
		moviepanel.getMovieLabel().setText("正在生成测试报告");
		
	}

	private void initReportPanel() {
		// TODO Auto-generated method stub
		
		tablepanel=new JPanel();
		chartpanel=new JPanel();
		emptypanel=new JPanel();
		emptypanel.setOpaque(false);
		
		initTablePanel();
		
		initChartPanel();
		
		reportresultpanel=new JPanel();
//		reportresultpanel.setLayout(new GridLayout());
		
		GridBagLayout layout = new GridBagLayout();
		reportresultpanel.setLayout(layout);
		reportresultpanel.add(tablepanel);
		reportresultpanel.add(chartpanel);
		reportresultpanel.add(emptypanel);
		layout.setConstraints(tablepanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(chartpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(emptypanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		reportresultpanel.setBorder(null);
		reportresultpanel.setBackground(new Color(255, 255, 255));
		
		reportscrollpanel=new JScrollPane(reportresultpanel);
        reportscrollpanel.setBorder(null);
        reportscrollpanel.setBackground(new Color(255, 255, 255));
		
//		reportpanel.setBackground(new Color(255, 255, 255));
		reportpanel.setLayout(new GridLayout());
		reportpanel.add(reportscrollpanel);
//		reportpanel.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
		
		
	}

	private void initTablePanel() {
		// TODO Auto-generated method stub
		
		successfailedtablepanel=new JPanel();
		failedstatisticstablepanel=new JPanel();
		
		initSuccessFailedTablePanel();
		
		initFailedStatisticsTablePanel();
		
		GridBagLayout layout = new GridBagLayout();
		tablepanel.setLayout(layout);
		tablepanel.add(successfailedtablepanel);
		tablepanel.add(failedstatisticstablepanel);
		layout.setConstraints(successfailedtablepanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(failedstatisticstablepanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		
		tablepanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		tablepanel.setOpaque(false);
		
//		String[] columnNames = { "模块名称", "通过数", "不通过数", "测试用例总数"};
//		String[][] tabelValues = {};
//
//		attributetablemodel = new DefaultTableModel(tabelValues, columnNames) {
//			@Override
//			public boolean isCellEditable(int row, int column) {
//				return false;
//			}
//		};
//
//		attributetable = new JTable(attributetablemodel);
//
//		attributetable.setName("FunctionalTestCaseChartTabbedPanel");
//
//		attributetable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
////		attributetable.setSelectionBackground(new Color(250, 248, 236));
//		attributetable.setGridColor(new Color(224, 226, 220));
//		attributetable.setShowGrid(false);
//		attributetable.setShowHorizontalLines(true);
//		attributetable.setShowVerticalLines(false);
//		attributetable.setFillsViewportHeight(true);
//		attributetable.setRowHeight(22);
//		attributetable.doLayout();
//		attributetable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//
//		attributetable.getColumnModel().getColumn(0).setCellRenderer(new MyAllLabelRenderer());
//		attributetable.getColumnModel().getColumn(1).setCellRenderer(new MyAllLabelRenderer());
//		attributetable.getColumnModel().getColumn(2).setCellRenderer(new MyAllLabelRenderer());
//		attributetable.getColumnModel().getColumn(3).setCellRenderer(new MyAllLabelRenderer());
//		
//		attributetable.getColumn("模块名称").setPreferredWidth(100);
//		attributetable.getColumn("模块名称").setMinWidth(100);
//		attributetable.getColumn("模块名称").setMaxWidth(100);
//		attributetable.getColumn("通过数").setPreferredWidth(50);
//		attributetable.getColumn("通过数").setMinWidth(50);
//		attributetable.getColumn("不通过数").setPreferredWidth(50);
//		attributetable.getColumn("不通过数").setMinWidth(50);
//		attributetable.getColumn("测试用例总数").setPreferredWidth(50);
//		attributetable.getColumn("测试用例总数").setMinWidth(50);
//
//		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
//		renderer.setBackground(new Color(71, 80, 93));
//		renderer.setForeground(new Color(255, 255, 255));
//		renderer.setFont(new Font("微软雅黑", Font.PLAIN, 13));
//		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
//		attributetable.getTableHeader().setDefaultRenderer(renderer);
//
//		attributetable.getTableHeader().setPreferredSize(new Dimension(100, 27));
//
//		attributetable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(224, 225, 220)));
//
//		tablepanel.setLayout(new BorderLayout());
//		tablepanel.add(attributetable.getTableHeader(), BorderLayout.NORTH);
//		tablepanel.add(attributetable, BorderLayout.CENTER);
//
//		tablepanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
//		tablepanel.setOpaque(false);
//		
//		String[] strname={"setup","init","loop","update","set_range","run"};
//		Random rand=new Random();
//		for(int i=0;i<6;i++){
//			int index=rand.nextInt(6);
//			Object[] rowData={strname[index],rand.nextInt(100),rand.nextInt(100),rand.nextInt(100)};
//			attributetablemodel.addRow(rowData);
//		}
//		Object[] rowData1={"合计:",rand.nextInt(10000),rand.nextInt(10000),rand.nextInt(10000)};
//		attributetablemodel.addRow(rowData1);
//		Object[] rowData2={"百分比:",rand.nextInt(100)+"%",rand.nextInt(100)+"%",rand.nextInt(100)+"%"};
//		attributetablemodel.addRow(rowData2);
		
	}

	private void initSuccessFailedTablePanel() {
		// TODO Auto-generated method stub
		
		String[] columnNames = { " ","成功", "失败", "测试用例总数"};
		String[][] tabelValues = {};

		successfailedattributetablemodel = new DefaultTableModel(tabelValues, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		successfailedattributetable = new JTable(successfailedattributetablemodel);

		successfailedattributetable.setName("FunctionalTestCaseChartTabbedPanel");

		successfailedattributetable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		successfailedattributetable.setSelectionBackground(new Color(250, 248, 236));
		successfailedattributetable.setGridColor(new Color(224, 226, 220));
		successfailedattributetable.setShowGrid(false);
		successfailedattributetable.setShowHorizontalLines(true);
		successfailedattributetable.setShowVerticalLines(false);
		successfailedattributetable.setFillsViewportHeight(true);
		successfailedattributetable.setRowHeight(22);
		successfailedattributetable.doLayout();
		successfailedattributetable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		successfailedattributetable.getColumnModel().getColumn(0).setCellRenderer(new MyAllLabelRenderer());
		successfailedattributetable.getColumnModel().getColumn(1).setCellRenderer(new MyAllLabelRenderer());
		successfailedattributetable.getColumnModel().getColumn(2).setCellRenderer(new MyAllLabelRenderer());
		successfailedattributetable.getColumnModel().getColumn(3).setCellRenderer(new MyAllLabelRenderer());
		
		successfailedattributetable.getColumn(" ").setPreferredWidth(100);
		successfailedattributetable.getColumn(" ").setMinWidth(100);
		successfailedattributetable.getColumn(" ").setMaxWidth(100);
		successfailedattributetable.getColumn("成功").setPreferredWidth(100);
		successfailedattributetable.getColumn("成功").setMinWidth(100);
		successfailedattributetable.getColumn("失败").setPreferredWidth(100);
		successfailedattributetable.getColumn("失败").setMinWidth(100);
		successfailedattributetable.getColumn("测试用例总数").setPreferredWidth(100);
		successfailedattributetable.getColumn("测试用例总数").setMinWidth(100);

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setBackground(new Color(71, 80, 93));
		renderer.setForeground(new Color(255, 255, 255));
		renderer.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		successfailedattributetable.getTableHeader().setDefaultRenderer(renderer);

		successfailedattributetable.getTableHeader().setPreferredSize(new Dimension(100, 27));

		successfailedattributetable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(224, 225, 220)));

		successfailedtablepanel.setLayout(new BorderLayout());
		successfailedtablepanel.add(successfailedattributetable.getTableHeader(), BorderLayout.NORTH);
		successfailedtablepanel.add(successfailedattributetable, BorderLayout.CENTER);

		successfailedtablepanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		successfailedtablepanel.setOpaque(false);
		
	}

	private void initFailedStatisticsTablePanel() {
		// TODO Auto-generated method stub
		
		String[] columnNames = { " ","测试用例有误", "程序出现死循环或者抛出异常", "失败测试用例总数"};
		String[][] tabelValues = {};

		failedstatisticsattributetablemodel = new DefaultTableModel(tabelValues, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		failedstatisticsattributetable = new JTable(failedstatisticsattributetablemodel);

		failedstatisticsattributetable.setName("FunctionalTestCaseChartTabbedPanel");

		failedstatisticsattributetable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		failedstatisticsattributetable.setSelectionBackground(new Color(250, 248, 236));
		failedstatisticsattributetable.setGridColor(new Color(224, 226, 220));
		failedstatisticsattributetable.setShowGrid(false);
		failedstatisticsattributetable.setShowHorizontalLines(true);
		failedstatisticsattributetable.setShowVerticalLines(false);
		failedstatisticsattributetable.setFillsViewportHeight(true);
		failedstatisticsattributetable.setRowHeight(22);
		failedstatisticsattributetable.doLayout();
		failedstatisticsattributetable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		failedstatisticsattributetable.getColumnModel().getColumn(0).setCellRenderer(new MyAllLabelRenderer());
		failedstatisticsattributetable.getColumnModel().getColumn(1).setCellRenderer(new MyAllLabelRenderer());
		failedstatisticsattributetable.getColumnModel().getColumn(2).setCellRenderer(new MyAllLabelRenderer());
		failedstatisticsattributetable.getColumnModel().getColumn(3).setCellRenderer(new MyAllLabelRenderer());
		
		failedstatisticsattributetable.getColumn(" ").setPreferredWidth(100);
		failedstatisticsattributetable.getColumn(" ").setMinWidth(100);
		failedstatisticsattributetable.getColumn(" ").setMaxWidth(100);
		failedstatisticsattributetable.getColumn("测试用例有误").setPreferredWidth(100);
		failedstatisticsattributetable.getColumn("测试用例有误").setMinWidth(100);
		failedstatisticsattributetable.getColumn("程序出现死循环或者抛出异常").setPreferredWidth(100);
		failedstatisticsattributetable.getColumn("程序出现死循环或者抛出异常").setMinWidth(100);
		failedstatisticsattributetable.getColumn("失败测试用例总数").setPreferredWidth(100);
		failedstatisticsattributetable.getColumn("失败测试用例总数").setMinWidth(100);

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setBackground(new Color(71, 80, 93));
		renderer.setForeground(new Color(255, 255, 255));
		renderer.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		failedstatisticsattributetable.getTableHeader().setDefaultRenderer(renderer);

		failedstatisticsattributetable.getTableHeader().setPreferredSize(new Dimension(100, 27));

		failedstatisticsattributetable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(224, 225, 220)));

		failedstatisticstablepanel.setLayout(new BorderLayout());
		failedstatisticstablepanel.add(failedstatisticsattributetable.getTableHeader(), BorderLayout.NORTH);
		failedstatisticstablepanel.add(failedstatisticsattributetable, BorderLayout.CENTER);

		failedstatisticstablepanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		failedstatisticstablepanel.setOpaque(false);
		
	}

	private void initChartPanel() {
		// TODO Auto-generated method stub
		
//		barchart=new BarChart();
//		linechart=new LineChart();
//		piechart=new PieChart();
//		
//		barchartpanel=barchart.createChart();
//		linechartpanel=linechart.createChart();
//		piechartpanel=piechart.createChart();
		
		successfailedpiepanel=new JPanel();
		failedstatisticspiepanel=new JPanel();
		
		barpanel1=new JPanel();
		linepanel1=new JPanel();
		piepanel1=new JPanel();
		barpanel2=new JPanel();
		linepanel2=new JPanel();
		piepanel2=new JPanel();
		
		leftemptypanel1=new JPanel();
		leftemptypanel2=new JPanel();
		leftemptypanel3=new JPanel();
		leftemptypanel4=new JPanel();
		leftemptypanel5=new JPanel();
		leftemptypanel6=new JPanel();
		
		rightemptypanel1=new JPanel();
		rightemptypanel2=new JPanel();
		rightemptypanel3=new JPanel();
		rightemptypanel4=new JPanel();
		rightemptypanel5=new JPanel();
		rightemptypanel6=new JPanel();
		
		leftemptypanel1.setOpaque(false);
		leftemptypanel2.setOpaque(false);
		leftemptypanel3.setOpaque(false);
		leftemptypanel4.setOpaque(false);
		leftemptypanel5.setOpaque(false);
		leftemptypanel6.setOpaque(false);
		
		rightemptypanel1.setOpaque(false);
		rightemptypanel2.setOpaque(false);
		rightemptypanel3.setOpaque(false);
		rightemptypanel4.setOpaque(false);
		rightemptypanel5.setOpaque(false);
		rightemptypanel6.setOpaque(false);
		
		barchartpanel1=new BarChart().createChart();
		linechartpanel1=new LineChart().createChart();
		piechartpanel1=new PieChart().createChart();
		barchartpanel2=new BarChart().createChart();
		linechartpanel2=new LineChart().createChart();
		piechartpanel2=new PieChart().createChart();
		
		successfailedpiepanel.setPreferredSize(new Dimension(300, 300));
		successfailedpiepanel.setMaximumSize(new Dimension(300, 300));
		successfailedpiepanel.setMinimumSize(new Dimension(300, 300));
		failedstatisticspiepanel.setPreferredSize(new Dimension(300, 300));
		failedstatisticspiepanel.setMaximumSize(new Dimension(300, 300));
		failedstatisticspiepanel.setMinimumSize(new Dimension(300, 300));
		
		barchartpanel1.setPreferredSize(new Dimension(300, 300));
		barchartpanel1.setMaximumSize(new Dimension(300, 300));
		barchartpanel1.setMinimumSize(new Dimension(300, 300));
		linechartpanel1.setPreferredSize(new Dimension(300, 300));
		linechartpanel1.setMaximumSize(new Dimension(300, 300));
		linechartpanel1.setMinimumSize(new Dimension(300, 300));
		piechartpanel1.setPreferredSize(new Dimension(300, 300));
		piechartpanel1.setMaximumSize(new Dimension(300, 300));
		piechartpanel1.setMinimumSize(new Dimension(300, 300));
		
		barchartpanel2.setPreferredSize(new Dimension(300, 300));
		barchartpanel2.setMaximumSize(new Dimension(300, 300));
		barchartpanel2.setMinimumSize(new Dimension(300, 300));
		linechartpanel2.setPreferredSize(new Dimension(300, 300));
		linechartpanel2.setMaximumSize(new Dimension(300, 300));
		linechartpanel2.setMinimumSize(new Dimension(300, 300));
		piechartpanel2.setPreferredSize(new Dimension(300, 300));
		piechartpanel2.setMaximumSize(new Dimension(300, 300));
		piechartpanel2.setMinimumSize(new Dimension(300, 300));
		
//		piepanel.setPreferredSize(new Dimension(300, 300));
//		piepanel.setMaximumSize(new Dimension(300, 300));
//		piepanel.setMinimumSize(new Dimension(300, 300));
		
		
		
		barpanel1.setLayout(new GridLayout());
		barpanel1.add(barchartpanel1);
		linepanel1.setLayout(new GridLayout());
		linepanel1.add(linechartpanel1);
		piepanel1.setLayout(new GridLayout());
		piepanel1.add(piechartpanel1);
		
		barpanel2.setLayout(new GridLayout());
		barpanel2.add(barchartpanel2);
		linepanel2.setLayout(new GridLayout());
		linepanel2.add(linechartpanel2);
		piepanel2.setLayout(new GridLayout());
		piepanel2.add(piechartpanel2);
		
		successfailedpiepanel.setLayout(new GridLayout());
		failedstatisticspiepanel.setLayout(new GridLayout());
		
		GridBagLayout layout = new GridBagLayout();
		chartpanel.setLayout(layout);
//		chartpanel.add(barpanel1);
//		chartpanel.add(linepanel1);
//		chartpanel.add(piepanel1);
//		chartpanel.add(barpanel2);
//		chartpanel.add(linepanel2);
//		chartpanel.add(piepanel2);
		chartpanel.add(successfailedpiepanel);
		chartpanel.add(failedstatisticspiepanel);
		
//		chartpanel.add(leftemptypanel1);
//		chartpanel.add(leftemptypanel2);
//		chartpanel.add(leftemptypanel3);
//		chartpanel.add(leftemptypanel4);
//		chartpanel.add(leftemptypanel5);
//		chartpanel.add(rightemptypanel1);
//		chartpanel.add(rightemptypanel2);
//		chartpanel.add(rightemptypanel3);
//		chartpanel.add(rightemptypanel4);
//		chartpanel.add(rightemptypanel5);
		
		layout.setConstraints(successfailedpiepanel, new GBC(1, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(failedstatisticspiepanel, new GBC(2, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
//		layout.setConstraints(barpanel1, new GBC(1, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		layout.setConstraints(linepanel1, new GBC(1, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		layout.setConstraints(piepanel1, new GBC(1, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));

//		layout.setConstraints(barpanel2, new GBC(2, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		layout.setConstraints(linepanel2, new GBC(2, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		layout.setConstraints(piepanel2, new GBC(2, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));

//		layout.setConstraints(leftemptypanel1, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(0.3, 1));
//		layout.setConstraints(leftemptypanel2, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(0.3, 1));
//		layout.setConstraints(leftemptypanel3, new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(0.3, 1));
//		layout.setConstraints(leftemptypanel4, new GBC(0, 4, 1, 1).setFill(GBC.BOTH).setWeight(0.3, 1));
//		layout.setConstraints(leftemptypanel5, new GBC(0, 5, 1, 1).setFill(GBC.BOTH).setWeight(0.3, 1));
//		layout.setConstraints(rightemptypanel1, new GBC(3, 1, 1, 1).setFill(GBC.BOTH).setWeight(0.3, 1));
//		layout.setConstraints(rightemptypanel2, new GBC(3, 2, 1, 1).setFill(GBC.BOTH).setWeight(0.3, 1));
//		layout.setConstraints(rightemptypanel3, new GBC(3, 3, 1, 1).setFill(GBC.BOTH).setWeight(0.3, 1));
//		layout.setConstraints(rightemptypanel4, new GBC(2, 4, 1, 1).setFill(GBC.BOTH).setWeight(0.3, 1));
//		layout.setConstraints(rightemptypanel5, new GBC(2, 5, 1, 1).setFill(GBC.BOTH).setWeight(0.3, 1));
		
		chartpanel.setBackground(new Color(255, 255, 255));
		
	}
	
	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}

	public JTable getAttributetable() {
		return attributetable;
	}

	public DefaultTableModel getAttributetablemodel() {
		return attributetablemodel;
	}

	public JPanel getSuccessfailedpiepanel() {
		return successfailedpiepanel;
	}

	public JPanel getFailedstatisticspiepanel() {
		return failedstatisticspiepanel;
	}

	public JTable getSuccessfailedattributetable() {
		return successfailedattributetable;
	}

	public DefaultTableModel getSuccessfailedattributetablemodel() {
		return successfailedattributetablemodel;
	}

	public JTable getFailedstatisticsattributetable() {
		return failedstatisticsattributetable;
	}

	public DefaultTableModel getFailedstatisticsattributetablemodel() {
		return failedstatisticsattributetablemodel;
	}

}

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
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartPanel;

import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.BarChart;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.LineChart;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.PieChart;

public class TestCaseChartTabbedPanel extends JPanel{
	
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
	
	private JPanel chartpanel;
	private JPanel barpanel;
	private JPanel linepanel;
	private JPanel piepanel;
	
	private JPanel highspeedbarpanel;
	private JPanel timespeedbarpanel;
	
	private JPanel highbatterylinepanel;
	private JPanel hightimelinepanel;
	
	private BarChart barchart;
	private LineChart linechart;
	private PieChart piechart;
	private ChartPanel barchartpanel;
	private ChartPanel linechartpanel;
	private ChartPanel piechartpanel;
	
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

	public TestCaseChartTabbedPanel(MainFrame mainFrame){ 
		
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
		
		checkbox1.setText("表格");
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
		
		checkbox2.setText("风速-高度柱状图");
		checkbox2.setOpaque(false);
		checkbox2.setSelected(true);
		checkbox2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(checkbox2.isSelected()){
					highspeedbarpanel.setVisible(true);
					ChangeRepaint();
				}
				else{
					highspeedbarpanel.setVisible(false);
					ChangeRepaint();
				}
			}
		});
		
		checkbox3.setText("风速-时间柱状图");
		checkbox3.setOpaque(false);
		checkbox3.setSelected(true);
		checkbox3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(checkbox3.isSelected()){
					timespeedbarpanel.setVisible(true);
					ChangeRepaint();
				}
				else{
					timespeedbarpanel.setVisible(false);
					ChangeRepaint();
				}
			}
		});
		
		checkbox4.setText("高度-电量折线图");
		checkbox4.setOpaque(false);
		checkbox4.setSelected(true);
		checkbox4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(checkbox4.isSelected()){
					highbatterylinepanel.setVisible(true);
					ChangeRepaint();
				}
				else{
					highbatterylinepanel.setVisible(false);
					ChangeRepaint();
				}
			}
		});
		
		checkbox5.setText("高度-时间折线图");
		checkbox5.setOpaque(false);
		checkbox5.setSelected(true);
		checkbox5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(checkbox5.isSelected()){
					hightimelinepanel.setVisible(true);
					ChangeRepaint();
				}
				else{
					hightimelinepanel.setVisible(false);
					ChangeRepaint();
				}
			}
		});
		
		checkbox6.setText("饼状图");
		checkbox6.setOpaque(false);
		checkbox6.setSelected(true);
		checkbox6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(checkbox6.isSelected()){
					piechartpanel.setVisible(true);
					ChangeRepaint();
				}
				else{
					piechartpanel.setVisible(false);
					ChangeRepaint();
				}
			}
		});
		
		toolpanel.setBackground(new Color(207, 214, 229));
		toolpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5,3));
		
//		toolpanel.add(toolbuttonpanel1);
		
		toolpanel.add(checkbox1);
		toolpanel.add(checkbox2);
		toolpanel.add(checkbox3);
		toolpanel.add(checkbox4);
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
		
//		String[] columnNames = { "模块名称", "通过数", "不通过数", "首轮命中用例数", "执行用例数", "未执行用例数", "变更用例数", "测试用例总数"};
		String[] columnNames = { "测试ID", "风速","起飞高度", "剩余电量", "所用时间"};
		String[][] tabelValues = {};

		attributetablemodel = new DefaultTableModel(tabelValues, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		attributetable = new JTable(attributetablemodel);

		attributetable.setName("TestCaseChartTabbedPanel");

		attributetable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		attributetable.setSelectionBackground(new Color(250, 248, 236));
		attributetable.setGridColor(new Color(224, 226, 220));
		attributetable.setShowGrid(false);
		attributetable.setShowHorizontalLines(true);
		attributetable.setShowVerticalLines(false);
		attributetable.setFillsViewportHeight(true);
		attributetable.setRowHeight(22);
		attributetable.doLayout();
		attributetable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		attributetable.getColumnModel().getColumn(0).setCellRenderer(new MyAllLabelRenderer());
		attributetable.getColumnModel().getColumn(1).setCellRenderer(new MyAllLabelRenderer());
		attributetable.getColumnModel().getColumn(2).setCellRenderer(new MyAllLabelRenderer());
		attributetable.getColumnModel().getColumn(3).setCellRenderer(new MyAllLabelRenderer());
		attributetable.getColumnModel().getColumn(4).setCellRenderer(new MyAllLabelRenderer());
//		attributetable.getColumnModel().getColumn(4).setCellRenderer(new MyAllLabelRenderer());
//		attributetable.getColumnModel().getColumn(5).setCellRenderer(new MyAllLabelRenderer());
//		attributetable.getColumnModel().getColumn(6).setCellRenderer(new MyAllLabelRenderer());
//		attributetable.getColumnModel().getColumn(7).setCellRenderer(new MyAllLabelRenderer());
		
//		attributetable.getColumn("模块名称").setPreferredWidth(100);
//		attributetable.getColumn("模块名称").setMinWidth(100);
//		attributetable.getColumn("模块名称").setMaxWidth(100);
//		attributetable.getColumn("通过数").setPreferredWidth(50);
//		attributetable.getColumn("通过数").setMinWidth(50);
//		attributetable.getColumn("不通过数").setPreferredWidth(50);
//		attributetable.getColumn("不通过数").setMinWidth(50);
//		attributetable.getColumn("首轮命中用例数").setPreferredWidth(80);
//		attributetable.getColumn("首轮命中用例数").setMinWidth(80);
//		attributetable.getColumn("执行用例数").setPreferredWidth(50);
//		attributetable.getColumn("执行用例数").setMinWidth(50);
//		attributetable.getColumn("未执行用例数").setPreferredWidth(50);
//		attributetable.getColumn("未执行用例数").setMinWidth(50);
//		attributetable.getColumn("变更用例数").setPreferredWidth(50);
//		attributetable.getColumn("变更用例数").setMinWidth(50);
//		attributetable.getColumn("测试用例总数").setPreferredWidth(50);
//		attributetable.getColumn("测试用例总数").setMinWidth(50);

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setBackground(new Color(71, 80, 93));
		renderer.setForeground(new Color(255, 255, 255));
		renderer.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		attributetable.getTableHeader().setDefaultRenderer(renderer);

		attributetable.getTableHeader().setPreferredSize(new Dimension(100, 27));

		attributetable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(224, 225, 220)));

		tablepanel.setLayout(new BorderLayout());
		tablepanel.add(attributetable.getTableHeader(), BorderLayout.NORTH);
		tablepanel.add(attributetable, BorderLayout.CENTER);

		tablepanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		tablepanel.setOpaque(false);
		
//		String[] strname={"setup","init","loop","update","set_range","run"};
//		Random rand=new Random();
//		for(int i=0;i<10;i++){
//			int index=rand.nextInt(6);
//			Object[] rowData={strname[index],rand.nextInt(100),rand.nextInt(100),rand.nextInt(100),rand.nextInt(100),rand.nextInt(100),rand.nextInt(100),rand.nextInt(100)};
//			attributetablemodel.addRow(rowData);
//		}
//		Object[] rowData1={"合计:",rand.nextInt(10000),rand.nextInt(10000),rand.nextInt(10000),rand.nextInt(10000),rand.nextInt(10000),rand.nextInt(10000),rand.nextInt(10000)};
//		attributetablemodel.addRow(rowData1);
//		Object[] rowData2={"百分比:",rand.nextInt(100)+"%",rand.nextInt(100)+"%",rand.nextInt(100)+"%",rand.nextInt(100)+"%",rand.nextInt(100)+"%",rand.nextInt(100)+"%",rand.nextInt(100)+"%"};
//		attributetablemodel.addRow(rowData2);
		
	}

	private void initChartPanel() {
		// TODO Auto-generated method stub
		
//		barchart=new BarChart();
//		linechart=new LineChart();
//		piechart=new PieChart();
//		
//		barchartpanel=barchart.getChartpanel();
//		linechartpanel=linechart.getChartpanel();
//		piechartpanel=piechart.getChartpanel();
		
		barpanel=new JPanel();
		linepanel=new JPanel();
		piepanel=new JPanel();
		
		highspeedbarpanel=new JPanel();
		timespeedbarpanel=new JPanel();
		
		highbatterylinepanel=new JPanel();
		hightimelinepanel=new JPanel();
		
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
		
		barchartpanel=new BarChart().createChart();
		linechartpanel=new LineChart().createChart();
		piechartpanel=new PieChart().createChart();
		
//		barchartpanel.setPreferredSize(new Dimension(300, 300));
//		barchartpanel.setMaximumSize(new Dimension(300, 300));
//		barchartpanel.setMinimumSize(new Dimension(300, 300));
//		linechartpanel.setPreferredSize(new Dimension(300, 300));
//		linechartpanel.setMaximumSize(new Dimension(300, 300));
//		linechartpanel.setMinimumSize(new Dimension(300, 300));
//		piechartpanel.setPreferredSize(new Dimension(300, 300));
//		piechartpanel.setMaximumSize(new Dimension(300, 300));
//		piechartpanel.setMinimumSize(new Dimension(300, 300));
		
		highspeedbarpanel.setPreferredSize(new Dimension(300, 300));
		highspeedbarpanel.setMaximumSize(new Dimension(300, 300));
		highspeedbarpanel.setMinimumSize(new Dimension(300, 300));
		timespeedbarpanel.setPreferredSize(new Dimension(300, 300));
		timespeedbarpanel.setMaximumSize(new Dimension(300, 300));
		timespeedbarpanel.setMinimumSize(new Dimension(300, 300));
		highbatterylinepanel.setPreferredSize(new Dimension(300, 300));
		highbatterylinepanel.setMaximumSize(new Dimension(300, 300));
		highbatterylinepanel.setMinimumSize(new Dimension(300, 300));
		hightimelinepanel.setPreferredSize(new Dimension(300, 300));
		hightimelinepanel.setMaximumSize(new Dimension(300, 300));
		hightimelinepanel.setMinimumSize(new Dimension(300, 300));
		piepanel.setPreferredSize(new Dimension(300, 300));
		piepanel.setMaximumSize(new Dimension(300, 300));
		piepanel.setMinimumSize(new Dimension(300, 300));
		
		
		
//		barpanel.setLayout(new GridLayout());
//		barpanel.add(barchartpanel);
//		
//		linepanel.setLayout(new GridLayout());
//		linepanel.add(linechartpanel);
		
		piepanel.setLayout(new GridLayout());
		piepanel.add(piechartpanel);
		
		highspeedbarpanel.setLayout(new GridLayout());
		timespeedbarpanel.setLayout(new GridLayout());
		
		highbatterylinepanel.setLayout(new GridLayout());
		hightimelinepanel.setLayout(new GridLayout());
		
		
		GridBagLayout layout = new GridBagLayout();
		chartpanel.setLayout(layout);
//		chartpanel.add(barpanel);
//		chartpanel.add(linepanel);
		chartpanel.add(highspeedbarpanel);
		chartpanel.add(timespeedbarpanel);
		chartpanel.add(highbatterylinepanel);
		chartpanel.add(hightimelinepanel);
		chartpanel.add(piepanel);
		
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
		
//		layout.setConstraints(barpanel, new GBC(1, 1, 1, 1).setFill(GBC.BOTH).setWeight(0.4, 1));
//		layout.setConstraints(linepanel, new GBC(1, 2, 1, 1).setFill(GBC.BOTH).setWeight(0.4, 1));
		
//		layout.setConstraints(highspeedbarpanel, new GBC(1, 1, 1, 1).setFill(GBC.BOTH).setWeight(0.4, 1));
//		layout.setConstraints(timespeedbarpanel, new GBC(1, 2, 1, 1).setFill(GBC.BOTH).setWeight(0.4, 1));
//		layout.setConstraints(highbatterylinepanel, new GBC(1, 3, 1, 1).setFill(GBC.BOTH).setWeight(0.4, 1));
//		layout.setConstraints(hightimelinepanel, new GBC(1, 4, 1, 1).setFill(GBC.BOTH).setWeight(0.4, 1));
//		layout.setConstraints(piepanel, new GBC(1, 5, 1, 1).setFill(GBC.BOTH).setWeight(0.4, 1));
//		
//		layout.setConstraints(leftemptypanel1, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(0.3, 1));
//		layout.setConstraints(leftemptypanel2, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(0.3, 1));
//		layout.setConstraints(leftemptypanel3, new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(0.3, 1));
//		layout.setConstraints(leftemptypanel4, new GBC(0, 4, 1, 1).setFill(GBC.BOTH).setWeight(0.3, 1));
//		layout.setConstraints(leftemptypanel5, new GBC(0, 5, 1, 1).setFill(GBC.BOTH).setWeight(0.3, 1));
//		layout.setConstraints(rightemptypanel1, new GBC(2, 1, 1, 1).setFill(GBC.BOTH).setWeight(0.3, 1));
//		layout.setConstraints(rightemptypanel2, new GBC(2, 2, 1, 1).setFill(GBC.BOTH).setWeight(0.3, 1));
//		layout.setConstraints(rightemptypanel3, new GBC(2, 3, 1, 1).setFill(GBC.BOTH).setWeight(0.3, 1));
//		layout.setConstraints(rightemptypanel4, new GBC(2, 4, 1, 1).setFill(GBC.BOTH).setWeight(0.3, 1));
//		layout.setConstraints(rightemptypanel5, new GBC(2, 5, 1, 1).setFill(GBC.BOTH).setWeight(0.3, 1));
		
		layout.setConstraints(highspeedbarpanel, new GBC(1, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(timespeedbarpanel, new GBC(2, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(highbatterylinepanel, new GBC(1, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(hightimelinepanel, new GBC(2, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(piepanel, new GBC(1, 3, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
//		layout.setConstraints(leftemptypanel1, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(0.1, 1));
//		layout.setConstraints(leftemptypanel2, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(0.1, 1));
//		layout.setConstraints(leftemptypanel3, new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(0.1, 1));
//		layout.setConstraints(leftemptypanel4, new GBC(0, 4, 1, 1).setFill(GBC.BOTH).setWeight(0.3, 1));
//		layout.setConstraints(leftemptypanel5, new GBC(0, 5, 1, 1).setFill(GBC.BOTH).setWeight(0.3, 1));
//		layout.setConstraints(rightemptypanel1, new GBC(3, 1, 1, 1).setFill(GBC.BOTH).setWeight(0.1, 1));
//		layout.setConstraints(rightemptypanel2, new GBC(3, 2, 1, 1).setFill(GBC.BOTH).setWeight(0.1, 1));
//		layout.setConstraints(rightemptypanel3, new GBC(3, 3, 1, 1).setFill(GBC.BOTH).setWeight(0.1, 1));
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

	public JPanel getBarpanel() {
		return barpanel;
	}

	public JPanel getLinepanel() {
		return linepanel;
	}

	public JPanel getPiepanel() {
		return piepanel;
	}

	public JTable getAttributetable() {
		return attributetable;
	}

	public DefaultTableModel getAttributetablemodel() {
		return attributetablemodel;
	}

	public JPanel getHighbatterylinepanel() {
		return highbatterylinepanel;
	}

	public JPanel getHightimelinepanel() {
		return hightimelinepanel;
	}

	public JPanel getHighspeedbarpanel() {
		return highspeedbarpanel;
	}

	public JPanel getTimespeedbarpanel() {
		return timespeedbarpanel;
	}

	
	
	
}

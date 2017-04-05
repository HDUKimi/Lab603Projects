package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.title.Title;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;

public class TestCaseProcessEndPanel extends JPanel{
	
	private MainFrame mainFrame;
	
	private JScrollPane scrollpanel;
	
	private JPanel panel;
//	
//	private JLabel label;
	
	private JPanel attributepanel;
	private JPanel chartresultpanel;
	
	private JPanel emptypanel;
	
	private JTable attributetable;
	private DefaultTableModel attributetablemodel;
	
	private List<String> stepAllProcessList=new ArrayList<>();
	private List<String> timeAllProcessList=new ArrayList<>();
	private List<String> resultAllProcessList=new ArrayList<>();
	
	
	public TestCaseProcessEndPanel(MainFrame mainFrame,List<String> stepAllProcessList,List<String> timeAllProcessList,List<String> resultAllProcessList){
		
		this.mainFrame = mainFrame;
		
		this.stepAllProcessList=stepAllProcessList;
		
		this.timeAllProcessList=timeAllProcessList;
		
		this.resultAllProcessList=resultAllProcessList;
		
		panel=new JPanel();
		panel.setBackground(new Color(255, 255, 255));
//		
//		initPanel();
		
//		GridBagLayout layout = new GridBagLayout();
//		this.setLayout(layout);
//		this.add(panel);
//		layout.setConstraints(panel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));

		
		attributepanel=new JPanel();
		chartresultpanel=new JPanel();
		
		emptypanel=new JPanel();
		emptypanel.setOpaque(false);
		
		initTablePanel();
		
		initChartPanel();
		
		initTableData();
		
//		GridBagLayout layout = new GridBagLayout();
//		panel.setLayout(layout);
//		panel.add(attributepanel);
//		panel.add(chartresultpanel);
////		panel.add(emptypanel);
//		layout.setConstraints(attributepanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//		layout.setConstraints(chartresultpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
////		layout.setConstraints(emptypanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		panel.setLayout(new BorderLayout());
		panel.add(attributepanel, BorderLayout.NORTH);
		panel.add(chartresultpanel, BorderLayout.CENTER);
		
//		scrollpanel=new JScrollPane(panel);
//		scrollpanel.setBorder(null);
		
		this.setLayout(new GridLayout());
		this.add(panel);
		
		
		this.setBackground(new Color(255, 255, 255));
		
	}


	public TestCaseProcessEndPanel(MainFrame mainFrame2) {
		// TODO Auto-generated constructor stub
		
		this.mainFrame = mainFrame2;
		
		panel=new JPanel();
		panel.setBackground(new Color(255, 255, 255));
//		
//		initPanel();
		
//		GridBagLayout layout = new GridBagLayout();
//		this.setLayout(layout);
//		this.add(panel);
//		layout.setConstraints(panel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));

		
		attributepanel=new JPanel();
		chartresultpanel=new JPanel();
		
		emptypanel=new JPanel();
		emptypanel.setOpaque(false);
		
		initTablePanel();
		
		initChartPanel();
		
		initTableData();
		
//		GridBagLayout layout = new GridBagLayout();
//		panel.setLayout(layout);
//		panel.add(attributepanel);
//		panel.add(chartresultpanel);
////		panel.add(emptypanel);
//		layout.setConstraints(attributepanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//		layout.setConstraints(chartresultpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
////		layout.setConstraints(emptypanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		panel.setLayout(new BorderLayout());
		panel.add(attributepanel, BorderLayout.NORTH);
		panel.add(chartresultpanel, BorderLayout.CENTER);
		
//		scrollpanel=new JScrollPane(panel);
//		scrollpanel.setBorder(null);
		
		this.setLayout(new GridLayout());
		this.add(panel);
		
		
		this.setBackground(new Color(255, 255, 255));
		
	}


	private void initTableData() {
		// TODO Auto-generated method stub
		
//		Object[] rowData1 = { "第一步：解析时间自动机", "20ms", "共解析出119个状态，220个迁移" };
//		attributetablemodel.addRow(rowData1);
//		Object[] rowData2 = { "第二步：优化约简", "50ms", "得到200个状态，220个迁移，其中状态增加了50个，减少了20个，迁移增加了20个，减少了30个" };
//		attributetablemodel.addRow(rowData2);
//		Object[] rowData3 = { "第三步：生成深度优先生成树", "60ms", "得到深度优先生成树" };
//		attributetablemodel.addRow(rowData3);
//		Object[] rowData4 = { "第四步：路径覆盖", "40ms", "生成100条路径，得到100条测试序列" };
//		attributetablemodel.addRow(rowData4);
//		Object[] rowData5 = { "第五步：添加实例化约束条件", "40ms", "得到100条含有约束条件的抽象测试用例" };
//		attributetablemodel.addRow(rowData5);
//		Object[] rowData6 = { "第六步：实例化", "50ms", "得到100条测试用例" };
//		attributetablemodel.addRow(rowData6);
//		Object[] rowData7 = { "第七步：存储测试用例", "10ms", "生成test.xml，保存路径：D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\test.xml" };
//		attributetablemodel.addRow(rowData7);
		
		for(int i=0;i<stepAllProcessList.size();i++){
			
			Object[] rowData={stepAllProcessList.get(i),timeAllProcessList.get(i),resultAllProcessList.get(i)};
			attributetablemodel.addRow(rowData);
			
		}
		
		
	}


	private void initTablePanel() {
		// TODO Auto-generated method stub
		
		final String[] columnNames={"步骤","耗时","运行结果"};
		String[][] tabelValues={};
		
		attributetablemodel=new DefaultTableModel(tabelValues, columnNames){
			@Override  
            public boolean isCellEditable(int row,int column){  
                return false;  
            } 
		};
		
		attributetable=new JTable(attributetablemodel);
		
		attributetable.setName("TestCaseProcessEndPanel");
		
		attributetable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        attributetable.setSelectionBackground(new Color(250, 248, 236));
        attributetable.setGridColor(new Color(224, 226, 220));
		attributetable.setShowGrid(true);
		attributetable.setShowHorizontalLines(true);
		attributetable.setShowVerticalLines(false);
		attributetable.setFillsViewportHeight(true);
		attributetable.setRowHeight(27);
		attributetable.doLayout();
		attributetable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		attributetable.getColumnModel().getColumn(0).setCellRenderer(new MyAllLabelRenderer());
		attributetable.getColumnModel().getColumn(1).setCellRenderer(new MyAllLabelRenderer());
		attributetable.getColumnModel().getColumn(2).setCellRenderer(new MyAllLabelRenderer());
		
		attributetable.getColumn("步骤").setPreferredWidth(200);
		attributetable.getColumn("步骤").setMinWidth(200);
		attributetable.getColumn("步骤").setMaxWidth(200);
		attributetable.getColumn("耗时").setPreferredWidth(90);
		attributetable.getColumn("耗时").setMinWidth(90);
		attributetable.getColumn("耗时").setMaxWidth(90);
		attributetable.getColumn("运行结果").setPreferredWidth(600);
		attributetable.getColumn("运行结果").setMinWidth(600);
        
        DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
        renderer.setBackground(new Color(71, 80, 93));
        renderer.setForeground(new Color(255, 255, 255));
        renderer.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        attributetable.getTableHeader().setDefaultRenderer(renderer); 
        
        attributetable.getTableHeader().setPreferredSize(new Dimension(100, 27));
        
        
//        DefaultTableCellRenderer renderer1=new DefaultTableCellRenderer(){
//
//			@Override
//			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
//					boolean hasFocus, int row, int column) {
//				// TODO Auto-generated method stub
//
//				this.setForeground(new Color(115, 110, 102));
//				this.setBackground(new Color(255, 255, 255));
//				this.setFont(new Font("微软雅黑", Font.PLAIN, 12));
//				this.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
//				if (column == 0) {
//					this.setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
//					this.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.RED));
//				}
//
//				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//			}
//
//        };
//        attributetable.setDefaultRenderer(Object.class, renderer1);
        
//        DefaultTableCellRenderer renderer1=new DefaultTableCellRenderer();
//        renderer1.setForeground(new Color(115, 110, 102));
//        renderer1.setBackground(new Color(255, 255, 255));
//        renderer1.setFont(new Font("微软雅黑", Font.PLAIN, 10));
////        renderer1.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
//        attributetable.setDefaultRenderer(Object.class, renderer1); 
        
        attributetable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(224, 225, 220)));
		
        attributetable.setBackground(new Color(255, 255, 255));
        
        
        attributepanel.setLayout(new BorderLayout());
        attributepanel.add(attributetable.getTableHeader(),BorderLayout.NORTH);
        attributepanel.add(attributetable, BorderLayout.CENTER);
        attributepanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        attributepanel.setOpaque(false);
		
	}


	private void initChartPanel() {
		// TODO Auto-generated method stub
		
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();

//		defaultcategorydataset.addValue(20, "", "第一步");
//		defaultcategorydataset.addValue(50, "", "第二步");
//		defaultcategorydataset.addValue(60, "", "第三步");
//		defaultcategorydataset.addValue(40, "", "第四步");
//		defaultcategorydataset.addValue(40, "", "第五步");
//		defaultcategorydataset.addValue(50, "", "第六步");
//		defaultcategorydataset.addValue(10, "", "第七步");
		
		for(int i=0;i<stepAllProcessList.size();i++){
			System.out.println(stepAllProcessList.get(i)+"  -  "+timeAllProcessList.get(i)+"  -  "+resultAllProcessList.get(i));
			
			double time=Double.parseDouble(timeAllProcessList.get(i).replaceAll("ms", ""));
			String step=stepAllProcessList.get(i).substring(0, 3);
			
			defaultcategorydataset.addValue(time, "", step);
		}
		
		
		JFreeChart chart = ChartFactory.createLineChart("各步骤耗时折线图", null, "耗时 (ms)", defaultcategorydataset, PlotOrientation.VERTICAL, false, true, false);
        chart.setBackgroundPaint(Color.white);
        chart.setBorderVisible(false);
        

        Font xfont = new Font("微软雅黑",Font.PLAIN,12) ;// X轴  
        Font yfont = new Font("微软雅黑",Font.PLAIN,12) ;// Y轴  
        Font kfont = new Font("微软雅黑",Font.PLAIN,12) ;// 底部  
        Font titleFont = new Font("微软雅黑", Font.PLAIN , 13) ; // 图片标题  
		CategoryPlot plot = chart.getCategoryPlot();// 图形的绘制结构对象

		// 图片标题
		TextTitle title=new TextTitle(chart.getTitle().getText());
		title.setFont(titleFont);
		title.setPaint(new Color(115, 110, 102));
		chart.setTitle(title);

		// 底部
		// chart.getLegend().setItemFont(kfont);

		// X 轴
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setLabelFont(xfont);// 轴标题
		domainAxis.setTickLabelFont(xfont);// 轴数值
		domainAxis.setTickLabelPaint(new Color(115, 110, 102)); // 字体颜色
		// domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		// // 横轴上的label斜显示

		// Y 轴
		ValueAxis rangeAxis = plot.getRangeAxis();
		rangeAxis.setLabelFont(yfont);
		rangeAxis.setLabelPaint(new Color(115, 110, 102)); // 字体颜色
		rangeAxis.setTickLabelFont(yfont);
		rangeAxis.setTickLabelPaint(new Color(115, 110, 102)); // 字体颜色

		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
		// categoryplot.setBackgroundPaint(Color.lightGray);
		categoryplot.setBackgroundPaint(Color.WHITE);
		categoryplot.setRangeGridlinesVisible(false);
		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot.getRenderer();
		lineandshaperenderer.setBaseShapesVisible(true);
		lineandshaperenderer.setDrawOutlines(true);
		lineandshaperenderer.setUseFillPaint(true);
		lineandshaperenderer.setBaseFillPaint(Color.white);
		lineandshaperenderer.setSeriesStroke(0, new BasicStroke(2F));
		lineandshaperenderer.setSeriesOutlineStroke(0, new BasicStroke(2.0F));
		lineandshaperenderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-5D, -5D, 10D, 10D));

		ChartPanel chartpanel = new ChartPanel(chart);
		
		System.out.println(chartpanel.getMinimumDrawHeight()+"  "+chartpanel.getMinimumDrawWidth());
		System.out.println(chartpanel.getMaximumDrawHeight()+"  "+chartpanel.getMaximumDrawWidth());
//		chartpanel.setDomainZoomable(true);
		
//		chartpanel.setSize(new Dimension(chartpanel.getMinimumDrawWidth(), chartpanel.getMinimumDrawHeight()));
//		chartpanel.setSize(new Dimension(100, 100));
//		chartpanel.setPreferredSize(new Dimension(100, 100));
//		chartpanel.setMinimumSize(new Dimension(100, 100));
//		chartpanel.setMaximumSize(new Dimension(100, 100));
		
//		chartresultpanel.setLayout(new BorderLayout());
//		chartresultpanel.add(chartpanel,BorderLayout.CENTER);
		
		chartresultpanel.setLayout(new GridLayout());
		chartresultpanel.add(chartpanel);
		
//		chartresultpanel.setLayout(new FlowLayout());
//		chartresultpanel.add(chartpanel);
		
		chartresultpanel.setBackground(new Color(255, 255, 255));
//		chartresultpanel.setBorder(BorderFactory.createEmptyBorder(15, 5, 5, 5));
		
		
	}


	public List<String> getStepAllProcessList() {
		return stepAllProcessList;
	}


	public void setStepAllProcessList(List<String> stepAllProcessList) {
		this.stepAllProcessList = stepAllProcessList;
	}


	public List<String> getTimeAllProcessList() {
		return timeAllProcessList;
	}


	public void setTimeAllProcessList(List<String> timeAllProcessList) {
		this.timeAllProcessList = timeAllProcessList;
	}


	public List<String> getResultAllProcessList() {
		return resultAllProcessList;
	}


	public void setResultAllProcessList(List<String> resultAllProcessList) {
		this.resultAllProcessList = resultAllProcessList;
	}

//	private void initPanel() {
//		// TODO Auto-generated method stub
//		
//		label=new JLabel();
//		label.setFont(new Font("微软雅黑", Font.PLAIN, 13));
//		
//		label.setText("<html><body>"
//				+ "测试用例成功生成，总耗时183ms<br>"
//				+ "整个进程可概括为：解析时间自动机==>符号状态拆分，去除抽象时间迁移==>路径覆盖==>添加实例化约束条件==>实例化==>生成测试用例xml<br>"
//				+ "<br>"
//				+ "解析时间自动机，执行成功，耗时10ms<br>"
//				+ "时间自动机名字：template_<br>"
//				+ "时间自动机时钟集合：t<br>"
//				+ "模型中共有119个状态，220个迁移<br>"
//				+ "<br>"
//				+ "去除抽象时间迁移，执行成功，耗时30ms<br>"
//				+ "共产生了20个不等式组<br>"
//				+ "<br>"
//				+ "路径覆盖，执行成功，耗时60ms<br>"
//				+ "共产生了100个测试用例序列<br>"
//				+ "<br>"
//				+ "添加实例化约束条件，执行成功，耗时50ms<br>"
//				+ "共产生了100个测试用例序列<br>"
//				+ "<br>"
//				+ "实例化，执行成功，耗时40ms<br>"
//				+ "共产生了100个测试用例序列<br>"
//				+ "<br>"
//				+ "生成测试用例xml，执行成功，耗时10ms<br>"
//				+ "testcase.xml保存路径：D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\test.xml<br>"
//				+ "<br>"
//				+ "</html></body>");
//		
//		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//		panel.setBackground(new Color(255, 255, 255));
//		panel.setLayout(new BorderLayout());
//		panel.add(label,BorderLayout.NORTH);
//		
//	}

	
	
}

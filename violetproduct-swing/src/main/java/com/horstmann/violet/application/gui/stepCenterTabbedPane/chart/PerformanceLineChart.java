package com.horstmann.violet.application.gui.stepCenterTabbedPane.chart;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.ChartUtils;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.Serie;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.TestCase;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.TestCaseResult;


public class PerformanceLineChart {
	
	private List<TestCase> resultlist=new ArrayList<>();
	
	public PerformanceLineChart(List<TestCase> resultlist) {
		
		this.resultlist=resultlist;
	}

	public DefaultCategoryDataset createDataset() {
		
		int length=resultlist.size()+1;
		String[] categories = new String[length];
		Double[] exeTime = new Double[length];
		Double[] takeoff_alt = new Double[length];
		Double[] battery_remaining = new Double[length];
		Double[] time = new Double[length];
		
		int index=1;
		for(TestCase tc:resultlist){
			TestCaseResult tcr=tc.getResult();
			categories[index]=index+"";
//			exeTime[index]=tcr.getExeTime();
//			takeoff_alt[index]=tcr.getTakeoff_alt();
//			battery_remaining[index]=tcr.getBattery_remaining();
//			time[index]=tcr.getTime();
			index++;
		}
		
		Vector<Serie> series = new Vector<Serie>();
		series.add(new Serie("起飞高度", takeoff_alt));
		series.add(new Serie("剩余电量", battery_remaining));
		series.add(new Serie("所用时间", time));
		
		
//		// 标注类别
//		String[] categories = { "V1.0", "V1.1", "V1.2", "V1.3", "V1.4" };
//		Vector<Serie> series = new Vector<Serie>();
//		// 柱子名称：柱子所有的值集合
//		series.add(new Serie("本次发现数", new Integer[] { 30, 23, 37, 25, 19 }));
//		series.add(new Serie("本次解决缺陷数", new Integer[] { 15, 20, 23, 21, 15 }));
////		series.add(new Serie("London", new Double[] { 48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2 }));
////		series.add(new Serie("Berlin", new Double[] { 42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1 }));
		// 1：创建数据集合
		DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
		return dataset;
	}

	public ChartPanel createChart() {
		// 2：创建Chart[创建不同图形]
		JFreeChart chart = ChartFactory.createLineChart("", "", "数量 (个)", createDataset());
		// 3:设置抗锯齿，防止字体显示不清楚
		ChartUtils.setAntiAlias(chart);// 抗锯齿
		// 4:对柱子进行渲染[[采用不同渲染]]
		ChartUtils.setLineRender(chart.getCategoryPlot(), false,true);//
		// 5:对其他部分进行渲染
		ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
		ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
		// 设置标注无边框
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		// 标注位于右侧
		chart.getLegend().setPosition(RectangleEdge.RIGHT);
		
		
		CategoryAxis domainAxis = chart.getCategoryPlot().getDomainAxis();
		domainAxis.setTickLabelPaint(new Color(255, 255, 255)); // 字体颜色
		
		// 6:使用chartPanel接收
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}


}

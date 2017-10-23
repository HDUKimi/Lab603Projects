package com.horstmann.violet.application.gui.stepCenterTabbedPane.chart;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleEdge;

public class TimeFailedStatisticsPieChart {
	
	Map<String,List<Map<Integer,List<Integer>>>> failedStatistics;
	
	private int f1;
	private int f2;
	
	public TimeFailedStatisticsPieChart(Map<String,List<Map<Integer,List<Integer>>>> failedStatistics) {
		this.failedStatistics=failedStatistics;
	}
	
	public TimeFailedStatisticsPieChart(int f1, int f2) {
		this.f1=f1;
		this.f2=f2;
	}

	public DefaultPieDataset createDataset() {
//		String[] categories = { "Active", "fixed", "postponed", "won't fix", "Not repro", "By design", "duplicate", "externa" };
//		Object[] datas = { 16, 12, 13, 10, 15, 8, 9, 10 };
//		String[] categories = new String[failedStatistics.size()];
//		Object[] datas = new Object[failedStatistics.size()];
		
		String[] categories = { "测试用例有误", "不满足时间约束" };
		Object[] datas = { f2, f1 };
		
		DefaultPieDataset dataset = ChartUtils.createDefaultPieDataset(categories, datas);
		return dataset;
	}

	public ChartPanel createChart() {
		// 2：创建Chart[创建不同图形]
		JFreeChart chart = ChartFactory.createPieChart3D("", createDataset());
		// 3:设置抗锯齿，防止字体显示不清楚
		ChartUtils.setAntiAlias(chart);// 抗锯齿
		// 4:对柱子进行渲染[创建不同图形]
		ChartUtils.setPieRender(chart.getPlot());
		
		/**
		 * 可以注释测试
		 */
//		 ((PiePlot)chart.getPlot()).setSimpleLabels(true);//简单标签,不绘制线条
//		 ((PiePlot)chart.getPlot()).setLabelGenerator(null);//不显示数字
		// 设置标注无边框
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		// 标注位于右侧
		chart.getLegend().setPosition(RectangleEdge.RIGHT);
		// 6:使用chartPanel接收
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}
	
}

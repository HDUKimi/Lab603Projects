package com.horstmann.violet.application.gui.stepCenterTabbedPane.chart;

import java.awt.Color;
import java.util.List;
import java.util.Vector;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

import com.horstmann.violet.application.gui.util.chengzuo.Bean.Pair;

public class PerformanceHighSpeedBarChart {

	private List<Pair> resultlist;
	
	public PerformanceHighSpeedBarChart(List<Pair> resultlist){
		
		this.resultlist=resultlist;
	}
	
	public DefaultCategoryDataset createDataset() {
		
//		System.out.println(resultlist.size());
		
		String[] categories=new String[resultlist.size()];
		Integer[] highdata=new Integer[resultlist.size()];
		
		int index=0;
		for(Pair p:resultlist){
			System.out.println(p.getFirst()+" - - "+p.getSecond());
			categories[index]=p.getFirst();
			highdata[index]=Integer.parseInt(p.getSecond());
			index++;
		}
		
		// 标注类别
//		String[] categories = { "模块A", "模块B", "模块C", "模块D" };
		Vector<Serie> series = new Vector<Serie>();
		// 柱子名称：柱子所有的值集合
		series.add(new Serie("高度", highdata));
//		series.add(new Serie("缺陷数", new Integer[] { 4, 10, 10, 6 }));
//		series.add(new Serie("New York", new Double[] { 83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3 }));
//		series.add(new Serie("London", new Double[] { 48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2 }));
//		series.add(new Serie("Berlin", new Double[] { 42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1 }));
		// 1：创建数据集合
		DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
		return dataset;
	}
	
	public ChartPanel createChart() {
		// 2：创建Chart
		JFreeChart chart = ChartFactory.createBarChart("", "风速", "时间", createDataset());
		// 3:设置抗锯齿，防止字体显示不清楚
		ChartUtils.setAntiAlias(chart);// 抗锯齿
		// 4:对柱子进行渲染
		ChartUtils.setBarRenderer(chart.getCategoryPlot(), false);//
		// 5:对其他部分进行渲染
		ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
		ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
		// 设置标注无边框
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		// 标注位于右侧
		chart.getLegend().setPosition(RectangleEdge.RIGHT);
		// 6:使用chartPanel接收
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}
	
}

package com.horstmann.violet.application.gui.stepCenterTabbedPane.chart;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleEdge;

import com.horstmann.violet.application.gui.util.chengzuo.Bean.Pair;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.TestCase;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.TestCaseResult;

public class PerformanceHighBatteryLineChart {

	private Map<String, List<Pair>> resultmap;

	public PerformanceHighBatteryLineChart(Map<String, List<Pair>> resultmap) {

		this.resultmap = resultmap;
	}

//	public DefaultCategoryDataset createDataset() {
//		// 1：创建数据集合
//		DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
//		return dataset;
//	}
	
	private XYSeriesCollection createDataset() {
		// TODO Auto-generated method stub
		
		XYSeriesCollection xyseriescollection=new XYSeriesCollection();
		
		System.out.println(resultmap.size());
		for(Map.Entry<String, List<Pair>> entry:resultmap.entrySet()){
			System.out.println(entry.getKey()+"  "+entry.getValue().size());
			XYSeries xyseries=new XYSeries(entry.getKey());
			for(Pair p:entry.getValue()){
//				System.out.println(p.getFirst()+" - - "+p.getSecond());
				xyseries.add(Double.parseDouble(((String) p.getFirst()).replaceAll("%", "")), Double.parseDouble(((String) p.getSecond()).replaceAll("%", "")));
			}
			xyseriescollection.addSeries(xyseries);
		}
		
		
		return xyseriescollection;
	}

	public ChartPanel createChart() {
		// 2：创建Chart[创建不同图形]
//		JFreeChart chart = ChartFactory.createLineChart("", "", "数量 (个)", createDataset());
		JFreeChart chart = ChartFactory.createXYLineChart("某风速的高度-剩余电量折线图", "高度", "剩余电量( % )", createDataset(), PlotOrientation.VERTICAL, true, true, false);
		// 3:设置抗锯齿，防止字体显示不清楚
		ChartUtils.setAntiAlias(chart);// 抗锯齿
		// 4:对柱子进行渲染[[采用不同渲染]]
//		ChartUtils.setLineRender(chart.getCategoryPlot(), false, true);//
//		// 5:对其他部分进行渲染
//		ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
//		ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
		// 设置标注无边框
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		// 标注位于右侧
		chart.getLegend().setPosition(RectangleEdge.RIGHT);

//		CategoryAxis domainAxis = chart.getCategoryPlot().getDomainAxis();
//		domainAxis.setTickLabelPaint(new Color(255, 255, 255)); // 字体颜色
		
		XYPlot plot=(XYPlot) chart.getPlot();
		plot.setDomainGridlinesVisible(false);

		// 6:使用chartPanel接收
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}

}

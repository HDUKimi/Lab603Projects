package com.horstmann.violet.application.gui.stepCenterTabbedPane.chart;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleEdge;

public class FunctionSuccessFailedPieChart {
	
	private List<Integer> caseSuccess;
	private List<Integer> caseFailed;
	
	private int caseSuccessSize;
	private int caseFailedSize;
	
	public FunctionSuccessFailedPieChart(List<Integer> caseSuccess, List<Integer> caseFailed) {
		this.caseSuccess=caseSuccess;
		this.caseFailed=caseFailed;
	}
	
	public FunctionSuccessFailedPieChart(int caseSuccessSize, int caseFailedSize) {
		this.caseSuccessSize=caseSuccessSize;
		this.caseFailedSize=caseFailedSize;
	}

	public DefaultPieDataset createDataset() {
//		String[] categories = { "Active", "fixed", "postponed", "won't fix", "Not repro", "By design", "duplicate", "externa" };
//		Object[] datas = { 16, 12, 13, 10, 15, 8, 9, 10 };
		String[] categories = { "成功", "失败" };
//		Object[] datas = { caseSuccess.size(), caseFailed.size() };
		Object[] datas = { caseSuccessSize, caseFailedSize };
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

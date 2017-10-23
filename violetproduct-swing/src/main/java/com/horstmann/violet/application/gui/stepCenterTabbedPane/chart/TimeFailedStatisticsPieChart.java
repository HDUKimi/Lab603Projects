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
		
		String[] categories = { "������������", "������ʱ��Լ��" };
		Object[] datas = { f2, f1 };
		
		DefaultPieDataset dataset = ChartUtils.createDefaultPieDataset(categories, datas);
		return dataset;
	}

	public ChartPanel createChart() {
		// 2������Chart[������ͬͼ��]
		JFreeChart chart = ChartFactory.createPieChart3D("", createDataset());
		// 3:���ÿ���ݣ���ֹ������ʾ�����
		ChartUtils.setAntiAlias(chart);// �����
		// 4:�����ӽ�����Ⱦ[������ͬͼ��]
		ChartUtils.setPieRender(chart.getPlot());
		
		/**
		 * ����ע�Ͳ���
		 */
//		 ((PiePlot)chart.getPlot()).setSimpleLabels(true);//�򵥱�ǩ,����������
//		 ((PiePlot)chart.getPlot()).setLabelGenerator(null);//����ʾ����
		// ���ñ�ע�ޱ߿�
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		// ��עλ���Ҳ�
		chart.getLegend().setPosition(RectangleEdge.RIGHT);
		// 6:ʹ��chartPanel����
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}
	
}

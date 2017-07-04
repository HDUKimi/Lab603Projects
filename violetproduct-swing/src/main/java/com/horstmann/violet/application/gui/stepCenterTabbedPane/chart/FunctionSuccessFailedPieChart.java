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
		String[] categories = { "�ɹ�", "ʧ��" };
//		Object[] datas = { caseSuccess.size(), caseFailed.size() };
		Object[] datas = { caseSuccessSize, caseFailedSize };
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

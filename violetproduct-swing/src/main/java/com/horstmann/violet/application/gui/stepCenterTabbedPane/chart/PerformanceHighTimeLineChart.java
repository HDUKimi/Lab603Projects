package com.horstmann.violet.application.gui.stepCenterTabbedPane.chart;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleEdge;

import com.horstmann.violet.application.gui.util.chengzuo.Bean.Pair;

public class PerformanceHighTimeLineChart {

	private Map<String, List<Pair>> resultmap;

	public PerformanceHighTimeLineChart(Map<String, List<Pair>> resultmap) {

		this.resultmap = resultmap;
	}
	
	private XYSeriesCollection createDataset() {
		// TODO Auto-generated method stub
		
		XYSeriesCollection xyseriescollection=new XYSeriesCollection();
		
		System.out.println(resultmap.size());
		for(Map.Entry<String, List<Pair>> entry:resultmap.entrySet()){
//			System.out.println(entry.getKey()+"  "+entry.getValue().size());
			XYSeries xyseries=new XYSeries(entry.getKey());
			for(Pair p:entry.getValue()){
//				System.out.println(p.getFirst()+" - - "+p.getSecond());
				xyseries.add(Double.parseDouble(p.getFirst()), Double.parseDouble(p.getSecond()));
			}
			xyseriescollection.addSeries(xyseries);
		}
		
		
		return xyseriescollection;
	}
	
	public ChartPanel createChart() {
		// 2������Chart[������ͬͼ��]
//		JFreeChart chart = ChartFactory.createLineChart("", "", "���� (��)", createDataset());
		JFreeChart chart = ChartFactory.createXYLineChart("ĳ���ٵĸ߶�-ʱ������ͼ", "�߶�", "���ʱ��", createDataset(), PlotOrientation.VERTICAL, true, true, false);
		// 3:���ÿ���ݣ���ֹ������ʾ�����
		ChartUtils.setAntiAlias(chart);// �����
		// 4:�����ӽ�����Ⱦ[[���ò�ͬ��Ⱦ]]
//		ChartUtils.setLineRender(chart.getCategoryPlot(), false, true);//
//		// 5:���������ֽ�����Ⱦ
//		ChartUtils.setXAixs(chart.getCategoryPlot());// X��������Ⱦ
//		ChartUtils.setYAixs(chart.getCategoryPlot());// Y��������Ⱦ
		// ���ñ�ע�ޱ߿�
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		// ��עλ���Ҳ�
		chart.getLegend().setPosition(RectangleEdge.RIGHT);

//		CategoryAxis domainAxis = chart.getCategoryPlot().getDomainAxis();
//		domainAxis.setTickLabelPaint(new Color(255, 255, 255)); // ������ɫ

		XYPlot plot=(XYPlot) chart.getPlot();
		plot.setDomainGridlinesVisible(false);
		
		// 6:ʹ��chartPanel����
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}
	
}

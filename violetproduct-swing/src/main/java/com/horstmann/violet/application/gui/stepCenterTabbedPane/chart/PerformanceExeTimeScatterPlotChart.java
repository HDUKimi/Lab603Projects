package com.horstmann.violet.application.gui.stepCenterTabbedPane.chart;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleEdge;

import com.horstmann.violet.application.gui.util.chenzuo.Bean.Pair;

public class PerformanceExeTimeScatterPlotChart {

	private List<Double> exetimelist;

	public PerformanceExeTimeScatterPlotChart(List<Double> exetimelist) {

		this.exetimelist = exetimelist;
	}
	
	private XYSeriesCollection createDataset() {
		// TODO Auto-generated method stub
		
		XYSeriesCollection xyseriescollection=new XYSeriesCollection();
		
		XYSeries xyseries = new XYSeries("耗时");
		XYSeries xyseries1 = new XYSeries("平均");
		
		int count;
		double value,sum,average;
		
		sum=0;
		count=exetimelist.size();
		
		for(int i=1;i<=count;i++){
			value=exetimelist.get(i-1);
			sum+=value;
			xyseries.add(i,value);
		}
		average=sum/count;
		for(int i=1;i<=count;i++){
			xyseries1.add(i,average);
		}
		
		xyseriescollection.addSeries(xyseries);
		xyseriescollection.addSeries(xyseries1);
		
		return xyseriescollection;
	}
	
	public ChartPanel createChart() {
		// 2：创建Chart[创建不同图形]
//		JFreeChart chart = ChartFactory.createLineChart("", "", "数量 (个)", createDataset());
		JFreeChart chart = ChartFactory.createScatterPlot("性能-耗时折线图", "", "耗时(ms)", createDataset(), PlotOrientation.VERTICAL, true, true, false);
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
		
		XYDotRenderer xydotrenderer = new XYDotRenderer();
        xydotrenderer.setDotWidth(2);
        xydotrenderer.setDotHeight(2);
        plot.setRenderer(xydotrenderer);
        
        plot.setDomainZeroBaselineVisible(true);  
        plot.setRangeZeroBaselineVisible(true);
        
     // x axis  
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setAutoRangeIncludesZero(true);
        domainAxis.setAutoRange(false);  
          
        // Y axis  
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setAutoRangeIncludesZero(true);
        rangeAxis.setAutoRange(false); 
		
		// 6:使用chartPanel接收
		ChartPanel chartPanel = new ChartPanel(chart);
		
		chartPanel.setVerticalAxisTrace(true);
		chartPanel.setHorizontalAxisTrace(true);
		chartPanel.setPopupMenu(null);
		chartPanel.setDomainZoomable(true);
		chartPanel.setRangeZoomable(true);
		
		return chartPanel;
	}
	
}

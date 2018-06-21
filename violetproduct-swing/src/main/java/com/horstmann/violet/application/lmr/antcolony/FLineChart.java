package com.horstmann.violet.application.lmr.antcolony;

import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleEdge;

import com.horstmann.violet.application.gui.common.ChartUtils;

/**
 * 
 * @author ccw
 * @date 2014-6-11
 *       <p>
 *       创建图表步骤：<br/>
 *       1：创建数据集合<br/>
 *       2：创建Chart：<br/>
 *       3:设置抗锯齿，防止字体显示不清楚<br/>
 *       4:对柱子进行渲染，<br/>
 *       5:对其他部分进行渲染<br/>
 *       6:使用chartPanel接收<br/>
 * 
 *       </p>
 */
public class FLineChart {
	
	public static XYSeriesCollection seriescollection=new XYSeriesCollection();
	
	public FLineChart() {
	}

	public static void AddSeriesCollection(Ant[] ants,String str){
		XYSeries xyseries = new XYSeries(str);
		for(int i=0;i<ants.length;i++){
//			xyseries.add((i+1),ants[i].m_nCurFunVal);
			xyseries.add((i+1),ants[i].m_nCurXVal);
		}
		seriescollection.addSeries(xyseries);
	}
	
	public static void AddSeriesCollection2(Ant2[] ants,String str){
//		XYSeries xyseries = new XYSeries(str);
//		for(int i=0;i<ants.length;i++){
//			xyseries.add((i+1),ants[i].m_nCurFunVal);
//		}
//		seriescollection.addSeries(xyseries);
		
		XYSeries xyseries0 = new XYSeries("β0");
		XYSeries xyseries1 = new XYSeries("β1");
		for(int i=0;i<ants.length;i++){
			xyseries0.add((i+1),ants[i].m_nCurVarVal[0]);
			xyseries1.add((i+1),ants[i].m_nCurVarVal[1]);
		}
		seriescollection.addSeries(xyseries0);
		seriescollection.addSeries(xyseries1);
		
	}
	
	public static void AddSeriesCollection3(Ant3[] ants,String str){
//		XYSeries xyseries = new XYSeries(str);
//		for(int i=0;i<ants.length;i++){
//			xyseries.add((i+1),ants[i].m_nCurFunVal);
//		}
//		seriescollection.addSeries(xyseries);
		
		XYSeries xyseries0 = new XYSeries("β0");
		XYSeries xyseries1 = new XYSeries("β1");
		for(int i=0;i<ants.length;i++){
			xyseries0.add((i+1),ants[i].m_nCurVarVal[0]);
			xyseries1.add((i+1),ants[i].m_nCurVarVal[1]);
		}
		seriescollection.addSeries(xyseries0);
		seriescollection.addSeries(xyseries1);
		
	}
	
	
//	public DefaultCategoryDataset createDataset() {
//		// 标注类别
//		String[] categories = { "V1.0", "V1.1", "V1.2", "V1.3", "V1.4" };
//		Vector<Serie> series = new Vector<Serie>();
//		// 柱子名称：柱子所有的值集合
//		series.add(new Serie("本次发现数", new Integer[] { 30, 23, 37, 25, 19 }));
//		series.add(new Serie("本次解决缺陷数", new Integer[] { 15, 20, 23, 21, 15 }));
////		series.add(new Serie("London", new Double[] { 48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2 }));
////		series.add(new Serie("Berlin", new Double[] { 42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1 }));
//		// 1：创建数据集合
//		DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
//		return dataset;
//	}

	public ChartPanel createChart() {
		// 2：创建Chart[创建不同图形]
		JFreeChart chart = ChartFactory.createXYLineChart("", "", "", seriescollection);
		// 3:设置抗锯齿，防止字体显示不清楚
		ChartUtils.setAntiAlias(chart);// 抗锯齿
		// 4:对柱子进行渲染[[采用不同渲染]]
//		ChartUtils.setLineRender(chart.getCategoryPlot(), false,true);//
//		// 5:对其他部分进行渲染
//		ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
//		ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
		// 设置标注无边框
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		// 标注位于右侧
		chart.getLegend().setPosition(RectangleEdge.RIGHT);
		
		XYPlot plot=(XYPlot) chart.getPlot();
		plot.setDomainGridlinesVisible(false);
		
		// 6:使用chartPanel接收
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}

	/**
	 * 初始化数据组
	 */
	public static void initSeriesCollection() {
		seriescollection=new XYSeriesCollection();
	}

}

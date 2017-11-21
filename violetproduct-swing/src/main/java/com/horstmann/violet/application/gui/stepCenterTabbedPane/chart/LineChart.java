package com.horstmann.violet.application.gui.stepCenterTabbedPane.chart;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleEdge;

import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.ChartUtils;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.Serie;
import com.horstmann.violet.application.gui.util.chenzuo.Bean.Pair;

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
public class LineChart {
	public LineChart() {
	}

	private XYSeriesCollection createDataset() {
		// TODO Auto-generated method stub
		
		XYSeriesCollection xyseriescollection=new XYSeriesCollection();
		
		XYSeries xyseries = new XYSeries("耗时");
		XYSeries xyseries1 = new XYSeries("平均");
		
		Random rand = new Random();
		int min,max,count;
		double value,sum,average;
		
		min=6010;
		max=6020;
		sum=0;
		count=10;
		
		for(int i=1;i<=count;i++){
//			System.out.println(min + ((max - min) * new Random().nextDouble()));
			value=min + ((max - min) * new Random().nextDouble());
			sum+=value;
			xyseries.add(i,value);
		}
		average=sum/count;
		for(int i=0;i<count;i++){
			xyseries1.add(i,average);
		}
		
		xyseriescollection.addSeries(xyseries);
//		xyseriescollection.addSeries(xyseries1);
		
		return xyseriescollection;
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
//		JFreeChart chart = ChartFactory.createLineChart("", "", "数量 (个)", createDataset());
		JFreeChart chart = ChartFactory.createXYLineChart("", "", "数量 (个)", createDataset());
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

	public static void main(String[] args) {
		final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024, 420);
		frame.setLocationRelativeTo(null);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// 创建图形
				ChartPanel chartPanel = new LineChart().createChart();
				frame.getContentPane().add(chartPanel);
				frame.setVisible(true);
			}
		});

	}

}

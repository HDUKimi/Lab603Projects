package com.horstmann.violet.application.gui.stepCenterTabbedPane.chart;

import java.awt.Color;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleEdge;

import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.ChartUtils;

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
public class PieChart {
	public PieChart() {
	}

	public DefaultPieDataset createDataset() {
//		String[] categories = { "Active", "fixed", "postponed", "won't fix", "Not repro", "By design", "duplicate", "externa" };
//		Object[] datas = { 16, 12, 13, 10, 15, 8, 9, 10 };
		String[] categories = { "成功", "失败" };
		Object[] datas = { 17, 2 };
		DefaultPieDataset dataset = ChartUtils.createDefaultPieDataset(categories, datas);
		
		double x;
		x=2*1.00/19;
		NumberFormat ddf1=NumberFormat.getNumberInstance() ; 
		ddf1.setMaximumFractionDigits(2); 
		String s= ddf1.format(x) ; 
		System.out.println(s); 
		System.out.println(ddf1.format(x*100));
		System.out.println(x);
		
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

	public static void main(String[] args) {
		final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024, 420);
		frame.setLocationRelativeTo(null);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// 创建图形
				ChartPanel chartPanel = new PieChart().createChart();
				frame.getContentPane().add(chartPanel);
				frame.setVisible(true);
			}
		});

	}

}

package com.horstmann.violet.application.gui.stepCenterTabbedPane.chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Paint;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class TestCaseBarChartPanel extends JPanel {

	private int[] count = new int[3];

	public TestCaseBarChartPanel(int[] count) {

		this.count = count;

		init();

	}

	private void init() {
		// TODO Auto-generated method stub

		DefaultCategoryDataset dcd = new DefaultCategoryDataset(); // 建立一个默认的饼图

		dcd.setValue(count[0], "测试用例", "成功");
		dcd.setValue(count[1], "测试用例", "死循环&抛出异常");
		dcd.setValue(count[2], "测试用例", "用例有误");

		JFreeChart chart = ChartFactory.createBarChart("测试报告数据图", "", "数量", dcd, PlotOrientation.VERTICAL, false, true,
				false); // 创建一个JFreeChart
		chart.setBackgroundPaint(Color.white);
		// chart.setTitle(new TextTitle("测试报告数据图", new Font("宋体", Font.BOLD +
		// Font.ITALIC, 20)));// 可以重新设置标题，替换“hi”标题

		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
		categoryplot.setDomainGridlinesVisible(false);
		categoryplot.setRangeGridlinesVisible(false);

//		BarRenderer barrenderer = (BarRenderer) categoryplot.getRenderer();
//		barrenderer.setBaseItemLabelsVisible(true);
//		barrenderer.setDrawBarOutline(true);
//
//		barrenderer.setIncludeBaseInRange(true);
//		barrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
//		barrenderer.setBaseItemLabelsVisible(true);

		// barrenderer.setSeriesPaint(0, Color.blue);
		// barrenderer.setSeriesPaint(1, Color.gray);
		// barrenderer.setSeriesPaint(2, Color.yellow);

//		barrenderer.setMaximumBarWidth(0.1);
//		barrenderer.setMinimumBarLength(0.1);
//		categoryplot.setRenderer(barrenderer);

		// CategoryItemRenderer categoryitemrenderer =
		// categoryplot.getRenderer();
		// categoryitemrenderer.setBaseItemLabelGenerator(new
		// StandardCategoryItemLabelGenerator());
		// categoryitemrenderer.setSeriesItemLabelsVisible(0, Boolean.TRUE);

		Paint paint[] = { new Color(144,237,125), new Color(128,133,233), new Color(67,67,72) };
		
		CustomRenderer customrenderer = new CustomRenderer(paint);
		customrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		customrenderer.setBaseItemLabelsVisible(true);

		customrenderer.setBaseItemLabelsVisible(true);
		customrenderer.setDrawBarOutline(true);
		
		customrenderer.setMaximumBarWidth(0.1);
		customrenderer.setMinimumBarLength(0.1);
		

		categoryplot.setRenderer(customrenderer);

		CategoryAxis categoryaxis = categoryplot.getDomainAxis();
		categoryaxis.setCategoryMargin(0.01D);
		categoryaxis.setUpperMargin(0.02D);
		categoryaxis.setLowerMargin(0.02D);

		ChartPanel chartpanel = new ChartPanel(chart);

		this.setLayout(new GridLayout());
		this.add(chartpanel);

	}

	static class CustomRenderer extends BarRenderer {

		private Paint colors[];

		public Paint getItemPaint(int i, int j) {
			return colors[j % colors.length];
		}

		public CustomRenderer(Paint apaint[]) {
			colors = apaint;
		}
	}

}

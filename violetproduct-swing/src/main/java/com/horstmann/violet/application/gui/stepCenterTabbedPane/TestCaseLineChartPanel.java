package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

public class TestCaseLineChartPanel extends JPanel {

	private List<Integer> countlist = new ArrayList<Integer>();

	public TestCaseLineChartPanel(List<Integer> countlist) {

		this.countlist = countlist;

		init();

	}

	private void init() {
		// TODO Auto-generated method stub

		XYSeries xyseries = new XYSeries("³É¹¦¼¤Àø");
		XYSeries xyseries1 = new XYSeries("Ê§°Ü¼¤Àø");
		
//		System.out.println(countlist.size());

		for (int i = 0; i < countlist.size(); i += 2) {

//			System.out.println(countlist.get(i) + "   " + countlist.get(i + 1));
			
			xyseries.add(i/2+1,countlist.get(i).intValue());
			xyseries1.add(i/2+1,countlist.get(i+1).intValue());

		}
		
//		XYSeries xyseries2 = new XYSeries("Third");
//		xyseries2.add(3D, 4D);
//		xyseries2.add(4D, 3D);
//		xyseries2.add(5D, 2D);
//		xyseries2.add(6D, 3D);
//		xyseries2.add(7D, 6D);
//		xyseries2.add(8D, 3D);
//		xyseries2.add(9D, 4D);
//		xyseries2.add(10D, 3D);
//		xyseries2.add(12D, 3D);
		XYSeriesCollection xyseriescollection = new XYSeriesCollection();
		xyseriescollection.addSeries(xyseries);
		xyseriescollection.addSeries(xyseries1);
//		xyseriescollection.addSeries(xyseries2);

		JFreeChart chart = ChartFactory.createXYLineChart("²âÊÔ±¨¸æÊý¾ÝÍ¼", "²âÊÔÓÃÀý", "¼¤Àø", xyseriescollection,
				PlotOrientation.VERTICAL, true, true, false);
		chart.setBackgroundPaint(Color.white);
		
//		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
//		categoryplot.setDomainGridlinesVisible(false);
//		categoryplot.setRangeGridlinesVisible(false);
		
		XYPlot categoryplot = (XYPlot) chart.getPlot();
		categoryplot.setDomainGridlinesVisible(false);
		categoryplot.setRangeGridlinesVisible(false);
		
		XYLineAndShapeRenderer lasp = (XYLineAndShapeRenderer) categoryplot.getRenderer();
		
		lasp.setSeriesPaint(0, new Color(26,179,148));
		lasp.setSeriesPaint(1, new Color(241,92,128));
		
//		XYPlot xyplot = (XYPlot) chart.getPlot();
//		xyplot.setBackgroundPaint(Color.lightGray);
//		xyplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
//		xyplot.setDomainGridlinePaint(Color.white);
//		xyplot.setRangeGridlinePaint(Color.white);
//		XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) xyplot.getRenderer();
//		xylineandshaperenderer.setBaseShapesVisible(true);
//		xylineandshaperenderer.setBaseShapesFilled(true);
//		NumberAxis numberaxis = (NumberAxis) xyplot.getRangeAxis();
//		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		ChartPanel chartpanel = new ChartPanel(chart);

		this.setLayout(new GridLayout());
		this.add(chartpanel);
		this.setPreferredSize(new Dimension(1000, (int) this.getSize().getHeight()));
		
	}

}

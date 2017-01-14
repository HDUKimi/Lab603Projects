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
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class TestCaseStackedBarChartPanel extends JPanel {

	private List<Integer> countlist = new ArrayList<Integer>();

	public TestCaseStackedBarChartPanel(List<Integer> countlist) {

		this.countlist = countlist;

		init();

	}

	private void init() {
		// TODO Auto-generated method stub

		DefaultCategoryDataset dcd = new DefaultCategoryDataset();
		for (int i = 0; i < countlist.size(); i += 2) {

			dcd.addValue(countlist.get(i).intValue(), "³É¹¦¼¤Àø", String.valueOf(i/2+1));
			dcd.addValue(countlist.get(i+1).intValue(), "Ê§°Ü¼¤Àø", String.valueOf(i/2+1));
			
		}
		
		JFreeChart chart = ChartFactory.createStackedBarChart("Stacked Bar Chart Demo 1", "Category", "Value", dcd, PlotOrientation.VERTICAL, true, true, false);
		chart.setBackgroundPaint(Color.white);
	    CategoryPlot categoryplot = (CategoryPlot)chart.getPlot();
	    categoryplot.setBackgroundPaint(Color.lightGray);
	    categoryplot.setRangeGridlinePaint(Color.white);
	    StackedBarRenderer stackedbarrenderer = (StackedBarRenderer)categoryplot.getRenderer();
	    stackedbarrenderer.setDrawBarOutline(false);
	    stackedbarrenderer.setBaseItemLabelsVisible(true);
	    stackedbarrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		
		ChartPanel chartpanel = new ChartPanel(chart);

		this.setLayout(new GridLayout());
		this.add(chartpanel);
		this.setPreferredSize(new Dimension(1000, (int) this.getSize().getHeight()));
		
	}

}

package com.horstmann.violet.application.lmr.deeplearn;

import java.awt.Color;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleEdge;

import com.horstmann.violet.application.gui.common.ChartUtils;

public class DataLineChart {
	
	public static XYSeriesCollection seriescollection=new XYSeriesCollection();
	
	public DataLineChart() {
	}

	public void createDataset() {
		
		XYSeries xyseries = new XYSeries("xx");
		
		List<Double> list=TestRegression6.scorelist;
		
		for(int i=0;i<list.size();i++){
			xyseries.add((i+1),list.get(i).doubleValue());
		}
		
		seriescollection.addSeries(xyseries);
	}
	
	public ChartPanel createChart() {
		
		createDataset();
		
		JFreeChart chart = ChartFactory.createXYLineChart("", "", "", seriescollection);
		ChartUtils.setAntiAlias(chart);
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		chart.getLegend().setPosition(RectangleEdge.RIGHT);
		
		XYPlot plot=(XYPlot) chart.getPlot();
		plot.setDomainGridlinesVisible(false);
		
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}

	public static void initSeriesCollection() {
		seriescollection=new XYSeriesCollection();
	}
	
	public static void main(String[] args) {
		final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024, 420);
		frame.setLocationRelativeTo(null);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				TestRegression6.Start();
				new DataLineChart().createChart();
				initSeriesCollection();
				ChartPanel chartPanel = new DataLineChart().createChart();
				frame.getContentPane().add(chartPanel);
				frame.setVisible(true);
			}
		});

	}

}

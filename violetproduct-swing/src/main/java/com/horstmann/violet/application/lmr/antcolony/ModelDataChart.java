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

public class ModelDataChart {
	
	public static XYSeriesCollection seriescollection=new XYSeriesCollection();
	
	public ModelDataChart() {
	}

	public static void AddSeriesCollection(){
		XYSeries xyseries0 = new XYSeries("Data");
		XYSeries xyseries1 = new XYSeries("JM");
		XYSeries xyseries2 = new XYSeries("GO");
		XYSeries xyseries3 = new XYSeries("Musa");
		XYSeries xyseries4 = new XYSeries("LV");
		
		for(int i=1;i<Model.sumTime.length;i++){
			xyseries0.add(Model.sumTime[i],i);
		}
		
		for(int i=0;i<JMModel.PredictFailureTimeList.size();i++){
			xyseries1.add(JMModel.PredictFailureTimeList.get(i).doubleValue(), i);
		}
		
		for(int i=0;i<GOModel.PredictFailureTimeList.size();i++){
			xyseries2.add(GOModel.PredictFailureTimeList.get(i).doubleValue(), i);
		}
		
		for(int i=0;i<MusaModel.PredictFailureTimeList.size();i++){
			xyseries3.add(MusaModel.PredictFailureTimeList.get(i).doubleValue(), i);
		}
		
		for(int i=0;i<LVModel.PredictFailureTimeList.size();i++){
			xyseries4.add(LVModel.PredictFailureTimeList.get(i).doubleValue(), i);
		}
		
		seriescollection.addSeries(xyseries0);
		seriescollection.addSeries(xyseries1);
		seriescollection.addSeries(xyseries2);
		seriescollection.addSeries(xyseries3);
		seriescollection.addSeries(xyseries4);
		
	}
	
	
	public ChartPanel createChart() {
		JFreeChart chart = ChartFactory.createXYLineChart("", "", "", seriescollection);
		ChartUtils.setAntiAlias(chart);
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		chart.getLegend().setPosition(RectangleEdge.RIGHT);
		
		XYPlot plot=(XYPlot) chart.getPlot();
		plot.setDomainGridlinesVisible(false);
		
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}

	/**
	 */
	public static void initSeriesCollection() {
		seriescollection=new XYSeriesCollection();
	}

}

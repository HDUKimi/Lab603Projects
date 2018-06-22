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

public class ModelYChart {
	public static XYSeriesCollection seriescollection=new XYSeriesCollection();
	
	public ModelYChart() {
	}

	public static void AddSeriesCollection(){
		XYSeries xyseries0 = new XYSeries("Data");
		XYSeries xyseries1 = new XYSeries("JM");
//		XYSeries xyseries2 = new XYSeries("GO");
		XYSeries xyseries3 = new XYSeries("Musa");
		XYSeries xyseries4 = new XYSeries("LV");
		
		xyseries0.add(0,0);
		xyseries0.add(1,1);
		
		xyseries1.add(0,0);
		
////		for(int i=0;i<JMModel.YList.size();i++){
////			xyseries1.add(JMModel.YList.get(i).doubleValue(),i*1.0/(JMModel.YList.size()+1));
////			xyseries1.add(JMModel.YList.get(i).doubleValue(),(i+1)*1.0/(JMModel.YList.size()+1));
////		}
////		xyseries1.add(1,JMModel.YList.size()*1.0/(JMModel.YList.size()+1));
//		
////		for(int i=0;i<JMModel.YList.size();i++){
////			xyseries1.add(i*1.0/(JMModel.YList.size()+1),JMModel.YList.get(i).doubleValue());
////			xyseries1.add((i+1)*1.0/(JMModel.YList.size()+1),JMModel.YList.get(i).doubleValue());
////		}
////		xyseries1.add(JMModel.YList.size()*1.0/(JMModel.YList.size()+1),1);
		
		xyseries1.add(0,0);
		xyseries1.add(1.0/(JMModel.YList.size()+1),0);
		for(int i=0;i<JMModel.YList.size();i++){
			xyseries1.add((i+1)*1.0/(JMModel.YList.size()+1),JMModel.YList.get(i).doubleValue());
			xyseries1.add((i+2)*1.0/(JMModel.YList.size()+1),JMModel.YList.get(i).doubleValue());
		}
		
//		xyseries2.add(0,0);
//		xyseries2.add(1.0/(GOModel.YList.size()+1),0);
//		for(int i=0;i<GOModel.YList.size();i++){
//			xyseries2.add((i+1)*1.0/(GOModel.YList.size()+1),GOModel.YList.get(i).doubleValue());
//			xyseries2.add((i+2)*1.0/(GOModel.YList.size()+1),GOModel.YList.get(i).doubleValue());
//		}
		
		xyseries3.add(0,0);
		xyseries3.add(1.0/(MusaModel.YList.size()+1),0);
		for(int i=0;i<MusaModel.YList.size();i++){
			xyseries3.add((i+1)*1.0/(MusaModel.YList.size()+1),MusaModel.YList.get(i).doubleValue());
			xyseries3.add((i+2)*1.0/(MusaModel.YList.size()+1),MusaModel.YList.get(i).doubleValue());
		}
		
		xyseries4.add(0,0);
		xyseries4.add(1.0/(LVModel.YList.size()+1),0);
		for(int i=0;i<LVModel.YList.size();i++){
			xyseries4.add((i+1)*1.0/(LVModel.YList.size()+1),LVModel.YList.get(i).doubleValue());
			xyseries4.add((i+2)*1.0/(LVModel.YList.size()+1),LVModel.YList.get(i).doubleValue());
		}
		
		seriescollection.addSeries(xyseries0);
		seriescollection.addSeries(xyseries1);
//		seriescollection.addSeries(xyseries2);
		seriescollection.addSeries(xyseries3);
		seriescollection.addSeries(xyseries4);
		
	}
	
	
	public ChartPanel createChart() {
		// 2������Chart[������ͬͼ��]
		JFreeChart chart = ChartFactory.createXYLineChart("", "", "", seriescollection);
		// 3:���ÿ���ݣ���ֹ������ʾ�����
		ChartUtils.setAntiAlias(chart);// �����?
		// 4:�����ӽ�����Ⱦ[[���ò�ͬ��Ⱦ]]
//		ChartUtils.setLineRender(chart.getCategoryPlot(), false,true);//
//		// 5:���������ֽ�����Ⱦ
//		ChartUtils.setXAixs(chart.getCategoryPlot());// X��������Ⱦ
//		ChartUtils.setYAixs(chart.getCategoryPlot());// Y��������Ⱦ
		// ���ñ�ע�ޱ߿�
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		// ��עλ���Ҳ�
		chart.getLegend().setPosition(RectangleEdge.RIGHT);
		
		XYPlot plot=(XYPlot) chart.getPlot();
		plot.setDomainGridlinesVisible(false);
		
		// 6:ʹ��chartPanel����
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}

	/**
	 * ��ʼ��������
	 */
	public static void initSeriesCollection() {
		seriescollection=new XYSeriesCollection();
	}
}

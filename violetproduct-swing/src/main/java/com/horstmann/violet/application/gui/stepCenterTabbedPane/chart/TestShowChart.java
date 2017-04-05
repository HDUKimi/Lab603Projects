package com.horstmann.violet.application.gui.stepCenterTabbedPane.chart;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;

public class TestShowChart {

	JFrame jf;

	JPanel jp;
	
	private BarChart barchart;
	private LineChart linechart;
	private PieChart piechart;
	private ChartPanel barchartpanel;
	private ChartPanel linechartpanel;
	private ChartPanel piechartpanel;
	
	public TestShowChart() {
		// TODO Auto-generated constructor stub
		
		jf = new JFrame();
		jp = new JPanel();
		
		initJPanel();
		
		jf.add(jp);

		jf.setVisible(true);

		jf.setSize(500, 500);
		
	}
	
	private void initJPanel() {
		// TODO Auto-generated method stub
		
//		barchart=new BarChart();
//		linechart=new LineChart();
//		piechart=new PieChart();
//		
//		barchartpanel=barchart.getChartpanel();
//		linechartpanel=linechart.getChartpanel();
//		piechartpanel=piechart.getChartpanel();
		
		barchartpanel=new BarChart().createChart();
		linechartpanel=new LineChart().createChart();
		piechartpanel=new PieChart().createChart();
		
		
		jp.setLayout(new GridLayout(2, 2));
		
		jp.add(barchartpanel);
		jp.add(linechartpanel);
		jp.add(piechartpanel);
		
		jp.setBackground(new Color(255, 255, 255));
		
	}

	public static void main(String[] args) {
		TestShowChart tsc=new TestShowChart();
	}
	
}

package com.horstmann.violet.application.gui.stepCenterTabbedPane.chart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

import com.horstmann.violet.application.gui.GBC;

public class TestCasePieChartPanel extends JPanel{

	private int[] count=new int[3];
	
	private JPanel webBrowserPanel;  
	  
	public TestCasePieChartPanel(int[] count){
		
		this.count=count;
		
		init();
		
	}
	
	private void init() {
		// TODO Auto-generated method stub
		
		DefaultPieDataset dpd=new DefaultPieDataset(); //����һ��Ĭ�ϵı�ͼ
		
		dpd.setValue("�ɹ�", count[0]);
		dpd.setValue("����", count[1]+count[2]);
        
        JFreeChart chart=ChartFactory.createPieChart("���Ա�������ͼ",dpd,true,true,false); 
        //���Բ�����API�ĵ�,��һ�������Ǳ��⣬�ڶ���������һ�����ݼ���������������ʾ�Ƿ���ʾLegend�����ĸ�������ʾ�Ƿ���ʾ��ʾ�������������ʾͼ���Ƿ����URL
        chart.setBackgroundPaint(Color.white);
        
        
//        ChartFrame chartFrame=new ChartFrame("ĳ��˾��Ա��֯����ͼ",chart); 
//        //chartҪ����Java��������У�ChartFrame�̳���java��Jframe�ࡣ�õ�һ�������������Ƿ��ڴ������Ͻǵģ��������м�ı��⡣
//        chartFrame.pack(); //�Ժ��ʵĴ�Сչ��ͼ��
//        chartFrame.setVisible(true);//ͼ���Ƿ�ɼ�
		
        PiePlot mPiePlot = (PiePlot)chart.getPlot();
        mPiePlot.setLabelFont(new Font("΢���ź�", Font.PLAIN, 15));
        mPiePlot.setCircular(true); 
        mPiePlot.setSectionPaint("�ɹ�", new Color(144,237,125));
        mPiePlot.setSectionPaint("����", new Color(255, 95, 95));
        
        mPiePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}:{1}��({2})", NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));  
        // �ײ�ͼ����ʾ�ٷֱ�:�Զ��巽ʽ�� {0} ��ʾѡ� {1} ��ʾ��ֵ�� {2} ��ʾ��ռ����   
//        mPiePlot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}:{1}��({2})"));  
        
        DefaultPieDataset dpd1=new DefaultPieDataset(); //����һ��Ĭ�ϵı�ͼ
		
//		dpd1.setValue("�ɹ�", count[0]);
		dpd1.setValue("��ѭ��&�׳��쳣", count[1]);
		dpd1.setValue("��������", count[2]);
		
        
        JFreeChart chart1=ChartFactory.createPieChart("���Ա�������ͼ",dpd1,true,true,false); 
        chart1.setBackgroundPaint(Color.white);
		
        PiePlot mPiePlot1 = (PiePlot)chart1.getPlot();
        mPiePlot1.setLabelFont(new Font("΢���ź�", Font.PLAIN, 15));
        mPiePlot1.setCircular(true); 
//        mPiePlot1.setSectionPaint("�ɹ�", new Color(144,237,125));
        mPiePlot1.setSectionPaint("��ѭ��&�׳��쳣", new Color(128,133,233));
        mPiePlot1.setSectionPaint("��������", new Color(67,67,72));
        
        mPiePlot1.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}:{1}��({2})", NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));  
        
        
        
        ChartPanel chartpanel=new ChartPanel(chart);
        ChartPanel chartpanel1=new ChartPanel(chart1);
        
        GridBagLayout layout=new GridBagLayout();
		this.setLayout(layout);
		layout.setConstraints(chartpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(chartpanel1, new GBC(1, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		this.add(chartpanel);
		this.add(chartpanel1);
		
	}
	
}

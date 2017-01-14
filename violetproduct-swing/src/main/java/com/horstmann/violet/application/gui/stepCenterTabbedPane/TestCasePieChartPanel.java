package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

public class TestCasePieChartPanel extends JPanel{

	private int[] count=new int[3];
	
	public TestCasePieChartPanel(int[] count){
		
		this.count=count;
		
		init();
		
	}

	private void init() {
		// TODO Auto-generated method stub
		
		DefaultPieDataset dpd=new DefaultPieDataset(); //����һ��Ĭ�ϵı�ͼ
//        dpd.setValue("ʧ��", 25);  //��������
//        dpd.setValue("�ɹ�", 65);
//        dpd.setValue("������Ա", 45);
//        dpd.setValue("������Ա", 10);
		
		dpd.setValue("�ɹ�", count[0]);
		dpd.setValue("��ѭ��&�׳��쳣", count[1]);
		dpd.setValue("��������", count[2]);
		
        
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
        mPiePlot.setSectionPaint("��ѭ��&�׳��쳣", new Color(128,133,233));
        mPiePlot.setSectionPaint("��������", new Color(67,67,72));
        
        mPiePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}:{1}��({2})", NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));  
        // �ײ�ͼ����ʾ�ٷֱ�:�Զ��巽ʽ�� {0} ��ʾѡ� {1} ��ʾ��ֵ�� {2} ��ʾ��ռ����   
//        mPiePlot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}:{1}��({2})"));  
        
        
        ChartPanel chartpanel=new ChartPanel(chart);
        
        this.setLayout(new GridLayout());
        this.add(chartpanel);
		
	}
	
}

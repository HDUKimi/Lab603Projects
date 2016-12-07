package com.horstmann.violet.application.gui.util.chengzuo.Verfication;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis3D;
import org.jfree.chart.axis.NumberAxis3D;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.DefaultCategoryDataset;
//��״ͼ
public class JFreeChartHistogram {
	//��ʱ�����һ��list�ļ��ϣ�����������ݽ��д��������µ�list��Ϊ���������ȥ
	//public static JPanel getHistogram(list)
    public static JPanel getHistogram(){

    			DefaultCategoryDataset dataset=new DefaultCategoryDataset();
    			//�������
    			dataset.addValue(98, "��ѧ", "����");
    			dataset.addValue(87, "����", "����");
    			dataset.addValue(68, "��ѧ", "����");
    			dataset.addValue(89, "����", "����");
    			dataset.addValue(56, "��ѧ", "����");
    			dataset.addValue(96, "����", "����");
    			
    			JFreeChart chart=ChartFactory.createBarChart3D(
    					"�ɼ�ͳ�Ʊ�",
    					"ѧ������",//X��ı�ǩ 
    	                "����",//Y��ı�ǩ 
    	                dataset, //ͼ����ʾ�����ݼ���
    	                PlotOrientation.VERTICAL, //ͼ�����ʾ��ʽ��ˮƽ���ߴ�ֱ��
    	                true,//�Ƿ���ʾ�ӱ��� 
    	                true,//�Ƿ�������ʾ�ı�ǩ 
    	                true); //�Ƿ�����URL����	
    			       JPanel jp=new JPanel();
    			        ChartPanel cp=new ChartPanel(chart);
    			        jp.add(cp);
    			        
    			       //����ͼ���ϵ�����
    					//���������������
    					chart.getTitle().setFont(new Font("����",Font.BOLD,18));
    					//�����ӱ�������
    					chart.getLegend().setItemFont(new Font("����",Font.BOLD,15));
    					//��ȡͼ���������
    					CategoryPlot categoryPlot = (CategoryPlot)chart.getPlot();
    					//��ȡX��Ķ���
    					CategoryAxis3D categoryAxis3D = (CategoryAxis3D)categoryPlot.getDomainAxis();
    					//��ȡY��Ķ���
    					NumberAxis3D numberAxis3D = (NumberAxis3D)categoryPlot.getRangeAxis();
    					//����X���ϵ�����
    					categoryAxis3D.setTickLabelFont(new Font("����",Font.BOLD,15));
    					//����X���������
    					categoryAxis3D.setLabelFont(new Font("����",Font.BOLD,15));
    					//����Y���ϵ�����
    					numberAxis3D.setTickLabelFont(new Font("����",Font.BOLD,15));
    					//����Y���������
    					numberAxis3D.setLabelFont(new Font("����",Font.BOLD,15));
    					//����Y������ʾ�Ŀ̶ȣ���10��Ϊ1��
    					numberAxis3D.setAutoTickUnitSelection(false);
    					NumberTickUnit unit = new NumberTickUnit(10);
    					numberAxis3D.setTickUnit(unit);
    					//��ȡ��ͼ�������
    					BarRenderer3D barRenderer3D = (BarRenderer3D)categoryPlot.getRenderer();
    					//��������ͼ�Ŀ��
    					barRenderer3D.setMaximumBarWidth(0.07);
    					//��ͼ������ʾ����
    					barRenderer3D.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
    					barRenderer3D.setBaseItemLabelsVisible(true);
    					barRenderer3D.setBaseItemLabelFont(new Font("����",Font.BOLD,15));
    					
    					//��D��Ŀ¼������ͼƬ
    					File file = new File("chart.jpeg");
    					try {
    						ChartUtilities.saveChartAsJPEG(file, chart, 800, 600);
    					} catch (IOException e) {
    						e.printStackTrace();
    					}
    					
    return jp; 
    }
     public static void main(String[] args) {
		 JFrame jf=new JFrame();
		 JPanel jp=JFreeChartHistogram.getHistogram();
		 jf.add(jp);
		 jf.pack();
		 jf.setVisible(true);
	}
    
}

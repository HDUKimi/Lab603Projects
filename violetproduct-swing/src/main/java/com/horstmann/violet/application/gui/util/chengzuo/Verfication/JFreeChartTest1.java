package com.horstmann.violet.application.gui.util.chengzuo.Verfication;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

//����ͼ
public class JFreeChartTest1 {
	public static List<TestCase> list=new ArrayList<TestCase>();
	//�Լ�������д��һ��list����
	public static List getList(){
		
		for(int i=1;i<4;i++){
			TestCase tc=new TestCase();
			if(i==1){
				tc.setTestCaseID(String.valueOf(1));
				tc.setContent("content"+1);
				tc.setState("state"+1);
				tc.setResult("ʧ��");
			}
			if(i==2){
				tc.setTestCaseID(String.valueOf(2));
				tc.setContent("content"+2);
				tc.setState("state"+2);
				tc.setResult("�ɹ�");
			}
			if(i==3){
				tc.setTestCaseID(String.valueOf(3));
				tc.setContent("content"+3);
				tc.setState("state"+3);
				tc.setResult("�ɹ�");
			}
			list.add(tc);
		}
		return list;
	}
	public static JPanel getJfreeLineChart(List<TestCase> list){
		  DefaultCategoryDataset dataSet = new DefaultCategoryDataset();  
//	        for (TestCase t : list) {  
//	          //  Map<String, Object> map = (Map) obj;  
//	          //  dataSet.setValue((Long) map.get("num"), (String) map.get("mn"), map.get("ym").toString());  
//	              dataSet.setValue(Integer.parseInt(t.getTestCaseID()), t.getState(), t.getResult());
//	        }  
		    dataSet.addValue(98, "��ѧ", "����");
			dataSet.addValue(68, "��ѧ", "����");
			dataSet.addValue(56, "��ѧ", "����");
	        //�����createLineChart��ΪcreateLineChart3D�ͱ�Ϊ��3DЧ��������ͼ       
	        JFreeChart  chart = ChartFactory.createLineChart3D(
	        		"ͼ�����",
	        		"X�����", 
	        		"Y�����", 
	        		dataSet,  
	                PlotOrientation.VERTICAL, // ���Ʒ���  
	                true, // ��ʾͼ��  
	                true, // ���ñ�׼������  
	                false // �Ƿ����ɳ�����  
	                ); 
	        //����JPanel���ڼ���chart
	        JPanel jp=new JPanel();
	        ChartPanel cp=new ChartPanel(chart);
	        jp.add(cp);
	        
	        chart.getTitle().setFont(new Font("", 10, 20)); // ���ñ�������  
	        chart.getLegend().setItemFont(new Font("", 10, 20));// ����ͼ���������  
	        chart.setBackgroundPaint(new Color(188,188,188));// ���ñ���ɫ   
	        //��ȡ��ͼ������  
	        CategoryPlot plot = chart.getCategoryPlot();  
	        plot.setBackgroundPaint(Color.LIGHT_GRAY); // ���û�ͼ������ɫ  
	        plot.setRangeGridlinePaint(Color.WHITE); // ����ˮƽ���򱳾�����ɫ  
	        plot.setRangeGridlinesVisible(true);// �����Ƿ���ʾˮƽ���򱳾���,Ĭ��ֵΪtrue  
	        plot.setDomainGridlinePaint(Color.WHITE); // ���ô�ֱ���򱳾�����ɫ  
	        plot.setDomainGridlinesVisible(true); // �����Ƿ���ʾ��ֱ���򱳾���,Ĭ��ֵΪfalse  
	          
	          
	        CategoryAxis domainAxis = plot.getDomainAxis();     
	        domainAxis.setLabelFont(new Font("", 10, 20)); // ���ú�������  
	        domainAxis.setTickLabelFont(new Font("", 10, 20));// ������������ֵ����  
	        domainAxis.setLowerMargin(0.01);// ��߾� �߿����  
	        domainAxis.setUpperMargin(0.06);// �ұ߾� �߿����,��ֹ���ߵ�һ�����ݿ����������ᡣ  
	        domainAxis.setMaximumCategoryLabelLines(2);  
	          
	        ValueAxis rangeAxis = plot.getRangeAxis();  
	        rangeAxis.setLabelFont(new Font("", 10, 20));   
	        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());//Y����ʾ����  
	        rangeAxis.setAutoRangeMinimumSize(1);   //��С���  
	        rangeAxis.setUpperMargin(0.18);//�ϱ߾�,��ֹ����һ�����ݿ����������ᡣ     
	        rangeAxis.setLowerBound(0);   //��Сֵ��ʾ0  
	        rangeAxis.setAutoRange(false);   //���Զ�����Y������  
	        rangeAxis.setTickMarkStroke(new BasicStroke(1.6f));     // ���������Ǵ�С  
	        rangeAxis.setTickMarkPaint(Color.BLACK);     // ������������ɫ  
	  
	          
	          
	     // ��ȡ���߶���  
	        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();  
	        BasicStroke realLine = new BasicStroke(1.8f); // ����ʵ��  
	        // ��������  
	        float dashes[] = { 5.0f };   
	        BasicStroke brokenLine = new BasicStroke(2.2f, // ������ϸ  
	                BasicStroke.CAP_ROUND, // �˵���  
	                BasicStroke.JOIN_ROUND, // �۵���  
	                8f, dashes, 0.6f);   
	        for (int i = 0; i < dataSet.getRowCount(); i++) {  
	            if (i % 2 == 0)  
	                renderer.setSeriesStroke(i, realLine); // ����ʵ�߻���  
	            else  
	                renderer.setSeriesStroke(i, brokenLine); // �������߻���  
	        }  
	          
	        plot.setNoDataMessage("�޶�Ӧ�����ݣ������²�ѯ��");  
	        plot.setNoDataMessageFont(new Font("", 10, 20));//����Ĵ�С  
	        plot.setNoDataMessagePaint(Color.RED);//������ɫ    
		return jp;
	}
	public static void main(String[] args) {
		JFrame jf=new JFrame("����ͼ");
		List<TestCase> list=JFreeChartTest1.getList();
		JPanel jp=JFreeChartTest1.getJfreeLineChart(list);
		jf.add(jp);
		jf.pack();
		jf.setVisible(true);
		
	}
}

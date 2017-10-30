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
		
		DefaultPieDataset dpd=new DefaultPieDataset(); //建立一个默认的饼图
		
		dpd.setValue("成功", count[0]);
		dpd.setValue("错误", count[1]+count[2]);
        
        JFreeChart chart=ChartFactory.createPieChart("测试报告数据图",dpd,true,true,false); 
        //可以查具体的API文档,第一个参数是标题，第二个参数是一个数据集，第三个参数表示是否显示Legend，第四个参数表示是否显示提示，第五个参数表示图中是否存在URL
        chart.setBackgroundPaint(Color.white);
        
        
//        ChartFrame chartFrame=new ChartFrame("某公司人员组织数据图",chart); 
//        //chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
//        chartFrame.pack(); //以合适的大小展现图形
//        chartFrame.setVisible(true);//图形是否可见
		
        PiePlot mPiePlot = (PiePlot)chart.getPlot();
        mPiePlot.setLabelFont(new Font("微软雅黑", Font.PLAIN, 15));
        mPiePlot.setCircular(true); 
        mPiePlot.setSectionPaint("成功", new Color(144,237,125));
        mPiePlot.setSectionPaint("错误", new Color(255, 95, 95));
        
        mPiePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}:{1}个({2})", NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));  
        // 底部图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比例   
//        mPiePlot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}:{1}个({2})"));  
        
        DefaultPieDataset dpd1=new DefaultPieDataset(); //建立一个默认的饼图
		
//		dpd1.setValue("成功", count[0]);
		dpd1.setValue("死循环&抛出异常", count[1]);
		dpd1.setValue("用例有误", count[2]);
		
        
        JFreeChart chart1=ChartFactory.createPieChart("测试报告数据图",dpd1,true,true,false); 
        chart1.setBackgroundPaint(Color.white);
		
        PiePlot mPiePlot1 = (PiePlot)chart1.getPlot();
        mPiePlot1.setLabelFont(new Font("微软雅黑", Font.PLAIN, 15));
        mPiePlot1.setCircular(true); 
//        mPiePlot1.setSectionPaint("成功", new Color(144,237,125));
        mPiePlot1.setSectionPaint("死循环&抛出异常", new Color(128,133,233));
        mPiePlot1.setSectionPaint("用例有误", new Color(67,67,72));
        
        mPiePlot1.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}:{1}个({2})", NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));  
        
        
        
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

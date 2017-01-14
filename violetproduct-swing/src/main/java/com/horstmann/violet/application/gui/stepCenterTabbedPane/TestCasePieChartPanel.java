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
		
		DefaultPieDataset dpd=new DefaultPieDataset(); //建立一个默认的饼图
//        dpd.setValue("失败", 25);  //输入数据
//        dpd.setValue("成功", 65);
//        dpd.setValue("开发人员", 45);
//        dpd.setValue("其他人员", 10);
		
		dpd.setValue("成功", count[0]);
		dpd.setValue("死循环&抛出异常", count[1]);
		dpd.setValue("用例有误", count[2]);
		
        
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
        mPiePlot.setSectionPaint("死循环&抛出异常", new Color(128,133,233));
        mPiePlot.setSectionPaint("用例有误", new Color(67,67,72));
        
        mPiePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}:{1}个({2})", NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));  
        // 底部图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比例   
//        mPiePlot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}:{1}个({2})"));  
        
        
        ChartPanel chartpanel=new ChartPanel(chart);
        
        this.setLayout(new GridLayout());
        this.add(chartpanel);
		
	}
	
}

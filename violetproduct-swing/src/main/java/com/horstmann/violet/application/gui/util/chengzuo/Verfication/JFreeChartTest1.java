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

//折线图
public class JFreeChartTest1 {
	public static List<TestCase> list=new ArrayList<TestCase>();
	//自己创造了写了一个list集合
	public static List getList(){
		
		for(int i=1;i<4;i++){
			TestCase tc=new TestCase();
			if(i==1){
				tc.setTestCaseID(String.valueOf(1));
				tc.setContent("content"+1);
				tc.setState("state"+1);
				tc.setResult("失败");
			}
			if(i==2){
				tc.setTestCaseID(String.valueOf(2));
				tc.setContent("content"+2);
				tc.setState("state"+2);
				tc.setResult("成功");
			}
			if(i==3){
				tc.setTestCaseID(String.valueOf(3));
				tc.setContent("content"+3);
				tc.setState("state"+3);
				tc.setResult("成功");
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
		    dataSet.addValue(98, "数学", "张三");
			dataSet.addValue(68, "数学", "李四");
			dataSet.addValue(56, "数学", "王五");
	        //如果把createLineChart改为createLineChart3D就变为了3D效果的折线图       
	        JFreeChart  chart = ChartFactory.createLineChart3D(
	        		"图表标题",
	        		"X轴标题", 
	        		"Y轴标题", 
	        		dataSet,  
	                PlotOrientation.VERTICAL, // 绘制方向  
	                true, // 显示图例  
	                true, // 采用标准生成器  
	                false // 是否生成超链接  
	                ); 
	        //生成JPanel用于加入chart
	        JPanel jp=new JPanel();
	        ChartPanel cp=new ChartPanel(chart);
	        jp.add(cp);
	        
	        chart.getTitle().setFont(new Font("", 10, 20)); // 设置标题字体  
	        chart.getLegend().setItemFont(new Font("", 10, 20));// 设置图例类别字体  
	        chart.setBackgroundPaint(new Color(188,188,188));// 设置背景色   
	        //获取绘图区对象  
	        CategoryPlot plot = chart.getCategoryPlot();  
	        plot.setBackgroundPaint(Color.LIGHT_GRAY); // 设置绘图区背景色  
	        plot.setRangeGridlinePaint(Color.WHITE); // 设置水平方向背景线颜色  
	        plot.setRangeGridlinesVisible(true);// 设置是否显示水平方向背景线,默认值为true  
	        plot.setDomainGridlinePaint(Color.WHITE); // 设置垂直方向背景线颜色  
	        plot.setDomainGridlinesVisible(true); // 设置是否显示垂直方向背景线,默认值为false  
	          
	          
	        CategoryAxis domainAxis = plot.getDomainAxis();     
	        domainAxis.setLabelFont(new Font("", 10, 20)); // 设置横轴字体  
	        domainAxis.setTickLabelFont(new Font("", 10, 20));// 设置坐标轴标尺值字体  
	        domainAxis.setLowerMargin(0.01);// 左边距 边框距离  
	        domainAxis.setUpperMargin(0.06);// 右边距 边框距离,防止最后边的一个数据靠近了坐标轴。  
	        domainAxis.setMaximumCategoryLabelLines(2);  
	          
	        ValueAxis rangeAxis = plot.getRangeAxis();  
	        rangeAxis.setLabelFont(new Font("", 10, 20));   
	        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());//Y轴显示整数  
	        rangeAxis.setAutoRangeMinimumSize(1);   //最小跨度  
	        rangeAxis.setUpperMargin(0.18);//上边距,防止最大的一个数据靠近了坐标轴。     
	        rangeAxis.setLowerBound(0);   //最小值显示0  
	        rangeAxis.setAutoRange(false);   //不自动分配Y轴数据  
	        rangeAxis.setTickMarkStroke(new BasicStroke(1.6f));     // 设置坐标标记大小  
	        rangeAxis.setTickMarkPaint(Color.BLACK);     // 设置坐标标记颜色  
	  
	          
	          
	     // 获取折线对象  
	        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();  
	        BasicStroke realLine = new BasicStroke(1.8f); // 设置实线  
	        // 设置虚线  
	        float dashes[] = { 5.0f };   
	        BasicStroke brokenLine = new BasicStroke(2.2f, // 线条粗细  
	                BasicStroke.CAP_ROUND, // 端点风格  
	                BasicStroke.JOIN_ROUND, // 折点风格  
	                8f, dashes, 0.6f);   
	        for (int i = 0; i < dataSet.getRowCount(); i++) {  
	            if (i % 2 == 0)  
	                renderer.setSeriesStroke(i, realLine); // 利用实线绘制  
	            else  
	                renderer.setSeriesStroke(i, brokenLine); // 利用虚线绘制  
	        }  
	          
	        plot.setNoDataMessage("无对应的数据，请重新查询。");  
	        plot.setNoDataMessageFont(new Font("", 10, 20));//字体的大小  
	        plot.setNoDataMessagePaint(Color.RED);//字体颜色    
		return jp;
	}
	public static void main(String[] args) {
		JFrame jf=new JFrame("折线图");
		List<TestCase> list=JFreeChartTest1.getList();
		JPanel jp=JFreeChartTest1.getJfreeLineChart(list);
		jf.add(jp);
		jf.pack();
		jf.setVisible(true);
		
	}
}

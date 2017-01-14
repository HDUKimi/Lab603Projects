package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.VerticalAlignment;
import org.jfree.util.UnitType;

public class TestCaseAreaChartPanel extends JPanel{

	private List<Integer> countlist = new ArrayList<Integer>();

	public TestCaseAreaChartPanel(List<Integer> countlist) {

		this.countlist = countlist;

		init();

	}

	private void init() {
		// TODO Auto-generated method stub
		
		DefaultCategoryDataset dcd = new DefaultCategoryDataset();
		
//		System.out.println(countlist.size());

		for (int i = 0; i < countlist.size(); i += 2) {

//			System.out.println(countlist.get(i) + "   " + countlist.get(i + 1));
			
//			dcd.addValue(countlist.get(i).intValue()+countlist.get(i+1).intValue(), "¼¤Àø×ÜÊý", String.valueOf(i/2+1));
			dcd.addValue(countlist.get(i).intValue(), "³É¹¦¼¤Àø", String.valueOf(i/2+1));
			dcd.addValue(countlist.get(i+1).intValue(), "Ê§°Ü¼¤Àø", String.valueOf(i/2+1));
			

		}
		
		JFreeChart chart = ChartFactory.createAreaChart("Area Chart", "Category", "Value", dcd, PlotOrientation.VERTICAL, true, true, false);
		chart.setBackgroundPaint(Color.white);
//        TextTitle texttitle = new TextTitle("An area chart demonstration.  We use this subtitle as an example of what happens when you get a really long title or subtitle.");
//        texttitle.setFont(new Font("SansSerif", 0, 12));
//        texttitle.setPosition(RectangleEdge.TOP);
//        texttitle.setPadding(new RectangleInsets(UnitType.RELATIVE, 0.050000000000000003D, 0.050000000000000003D, 0.050000000000000003D, 0.050000000000000003D));
//        texttitle.setVerticalAlignment(VerticalAlignment.BOTTOM);
//        chart.addSubtitle(texttitle);
//        CategoryPlot categoryplot = (CategoryPlot)chart.getPlot();
//        categoryplot.setForegroundAlpha(0.5F);
//        categoryplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
//        categoryplot.setBackgroundPaint(Color.lightGray);
//        categoryplot.setDomainGridlinesVisible(true);
//        categoryplot.setDomainGridlinePaint(Color.white);
//        categoryplot.setRangeGridlinesVisible(true);
//        categoryplot.setRangeGridlinePaint(Color.white);
//        CategoryAxis categoryaxis = categoryplot.getDomainAxis();
//        categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
//        categoryaxis.setLowerMargin(0.0D);
//        categoryaxis.setUpperMargin(0.0D);
//        categoryaxis.addCategoryLabelToolTip("Type 1", "The first type.");
//        categoryaxis.addCategoryLabelToolTip("Type 2", "The second type.");
//        categoryaxis.addCategoryLabelToolTip("Type 3", "The third type.");
//        NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
//        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
//        numberaxis.setLabelAngle(0.0D);
		
		ChartPanel chartpanel = new ChartPanel(chart);

		this.setLayout(new GridLayout());
		this.add(chartpanel);
		this.setPreferredSize(new Dimension(1000, (int) this.getSize().getHeight()));
		
	}
	
}

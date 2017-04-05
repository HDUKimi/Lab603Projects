package com.horstmann.violet.application.gui.stepCenterTabbedPane.chart;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.ChartUtils;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.chart.Serie;

/**
 * 
 * @author ccw
 * @date 2014-6-11
 *       <p>
 *       ����ͼ���裺<br/>
 *       1���������ݼ���<br/>
 *       2������Chart��<br/>
 *       3:���ÿ���ݣ���ֹ������ʾ�����<br/>
 *       4:�����ӽ�����Ⱦ��<br/>
 *       5:���������ֽ�����Ⱦ<br/>
 *       6:ʹ��chartPanel����<br/>
 * 
 *       </p>
 */
public class LineChart {
	public LineChart() {
	}

	public DefaultCategoryDataset createDataset() {
		// ��ע���
		String[] categories = { "V1.0", "V1.1", "V1.2", "V1.3", "V1.4" };
		Vector<Serie> series = new Vector<Serie>();
		// �������ƣ��������е�ֵ����
		series.add(new Serie("���η�����", new Integer[] { 30, 23, 37, 25, 19 }));
		series.add(new Serie("���ν��ȱ����", new Integer[] { 15, 20, 23, 21, 15 }));
//		series.add(new Serie("London", new Double[] { 48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2 }));
//		series.add(new Serie("Berlin", new Double[] { 42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1 }));
		// 1���������ݼ���
		DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
		return dataset;
	}

	public ChartPanel createChart() {
		// 2������Chart[������ͬͼ��]
		JFreeChart chart = ChartFactory.createLineChart("", "", "���� (��)", createDataset());
		// 3:���ÿ���ݣ���ֹ������ʾ�����
		ChartUtils.setAntiAlias(chart);// �����
		// 4:�����ӽ�����Ⱦ[[���ò�ͬ��Ⱦ]]
		ChartUtils.setLineRender(chart.getCategoryPlot(), false,true);//
		// 5:���������ֽ�����Ⱦ
		ChartUtils.setXAixs(chart.getCategoryPlot());// X��������Ⱦ
		ChartUtils.setYAixs(chart.getCategoryPlot());// Y��������Ⱦ
		// ���ñ�ע�ޱ߿�
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		// ��עλ���Ҳ�
		chart.getLegend().setPosition(RectangleEdge.RIGHT);
		// 6:ʹ��chartPanel����
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}

	public static void main(String[] args) {
		final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024, 420);
		frame.setLocationRelativeTo(null);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// ����ͼ��
				ChartPanel chartPanel = new LineChart().createChart();
				frame.getContentPane().add(chartPanel);
				frame.setVisible(true);
			}
		});

	}

}

package com.horstmann.violet.application.gui.stepFour;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.common.ColorData;
import com.horstmann.violet.application.lmr.antcolony.GOModel;
import com.horstmann.violet.application.lmr.antcolony.JMModel;
import com.horstmann.violet.application.lmr.antcolony.LVModel;
import com.horstmann.violet.application.lmr.antcolony.ModelUChart;
import com.horstmann.violet.application.lmr.antcolony.ModelYChart;
import com.horstmann.violet.application.lmr.antcolony.MusaModel;

public class ModelEvaluatePanel extends JPanel {

	private MainFrame mainFrame;

	private JPanel evaluatePanel;
	private JPanel modelPanel;

	private JPanel evaluateTablePanel;
	private JTable evaluateTable;
	private DefaultTableModel evaluateTableModel;

	private JPanel uPanel;
	private JPanel yPanel;

	public ModelEvaluatePanel(MainFrame mainFrame) {

		this.mainFrame = mainFrame;

		evaluatePanel = new JPanel();
		modelPanel = new JPanel();

		initEvaluatePanel();
		initModelPanel();

		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(evaluatePanel);
		this.add(modelPanel);
		layout.setConstraints(evaluatePanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(modelPanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));

		this.setBackground(ColorData.white);

	}

	private void initEvaluatePanel() {

		evaluatePanel.setOpaque(false);
		evaluatePanel.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10),
				BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorData.gray), "模型评价值",
						TitledBorder.LEFT, TitledBorder.TOP, new Font("微软雅黑", Font.PLAIN, 13), ColorData.black)));

		evaluateTablePanel = new JPanel();

		initEvaluateTablePanel();

		evaluatePanel.setLayout(new GridLayout());
		evaluatePanel.add(evaluateTablePanel);

	}

	private void initEvaluateTablePanel() {

		final String[] columnNames = { "", "1", "2", "3", "4" };
		String[][] tabelValues = {};

		evaluateTableModel = new DefaultTableModel(tabelValues, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		evaluateTable = new JTable(evaluateTableModel);

		evaluateTable.setName("TestCaseProcessEndPanel");

		evaluateTable.setSelectionBackground(ColorData.white);
		evaluateTable.setSelectionForeground(ColorData.black);
		evaluateTable.setGridColor(ColorData.gray);
		evaluateTable.setShowGrid(true);
		evaluateTable.setShowHorizontalLines(true);
		evaluateTable.setShowVerticalLines(true);
		evaluateTable.setFillsViewportHeight(true);
		evaluateTable.setRowHeight(27);
		evaluateTable.doLayout();
		evaluateTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		evaluateTable.setDefaultRenderer(Object.class, renderer);

		evaluateTable.setBackground(new Color(255, 255, 255));
		evaluateTable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorData.gray));

		evaluateTablePanel.setLayout(new BorderLayout());
		evaluateTablePanel.add(evaluateTable, BorderLayout.NORTH);
		evaluateTablePanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		evaluateTablePanel.setOpaque(false);

		Object[] rowData1 = { "评价标准", "JM模型", "GO模型", "Musa模型", "LV模型" };
		evaluateTableModel.addRow(rowData1);
		String[] str = new String[] { "模型拟合度", "模型精确性", "模型偏差", "模型偏差趋势", "模型噪声" };
		for (int i = 0; i < str.length; i++) {
			Object[] rowData = { str[i], "", "", "", "", };
			evaluateTableModel.addRow(rowData);
		}

	}

	private void initModelPanel() {

		modelPanel.setOpaque(false);

		uPanel = new JPanel();
		yPanel = new JPanel();

		uPanel.setOpaque(false);
		yPanel.setOpaque(false);
		
		uPanel.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5),
				BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorData.gray), "U-结构图",
						TitledBorder.LEFT, TitledBorder.TOP, new Font("微软雅黑", Font.PLAIN, 13), ColorData.black)));
		yPanel.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10),
				BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorData.gray), "Y-结构图",
						TitledBorder.LEFT, TitledBorder.TOP, new Font("微软雅黑", Font.PLAIN, 13), ColorData.black)));

		GridBagLayout layout = new GridBagLayout();
		modelPanel.setLayout(layout);
		modelPanel.add(uPanel);
		modelPanel.add(yPanel);
		layout.setConstraints(uPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(yPanel, new GBC(1, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));

	}
	
	public void dealAndShow() {
		
		JProgressBar progressBar=mainFrame.getStepFourCenterPanel().getProgressPanel().getProgressBar();
		progressBar.setValue(0);
		while(progressBar.getValue()<99){
			
			progressBar.setValue(progressBar.getValue()+1);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		showEvaluateData();
		
		ModelUChart.AddSeriesCollection();
		ModelYChart.AddSeriesCollection();
		
		uPanel.removeAll();
		uPanel.setLayout(new GridLayout());
		uPanel.add(new ModelUChart().createChart());
		
		yPanel.removeAll();
		yPanel.setLayout(new GridLayout());
		yPanel.add(new ModelYChart().createChart());
		
		progressBar.setValue(100);
		
		mainFrame.ChangeRepaint(this);
		
	}

	private void showEvaluateData() {
		
		String[] str = new String[] { "模型拟合度", "模型精确性", "模型偏差", "模型偏差趋势", "模型噪声" };
		double[] JM=new double[]{JMModel.FD,JMModel.PL,JMModel.KS_U,JMModel.KS_Y,JMModel.MN};
		double[] GO=new double[]{GOModel.FD,GOModel.PL,GOModel.KS_U,GOModel.KS_Y,GOModel.MN};
		double[] Musa=new double[]{MusaModel.FD,MusaModel.PL,MusaModel.KS_U,MusaModel.KS_Y,MusaModel.MN};
		double[] LV=new double[]{LVModel.FD,LVModel.PL,LVModel.KS_U,LVModel.KS_Y,LVModel.MN};
		for (int i = 1; i <= str.length; i++) {
			evaluateTable.setValueAt(JM[i-1], i, 1);
			evaluateTable.setValueAt(GO[i-1], i, 2);
			evaluateTable.setValueAt(Musa[i-1], i, 3);
			evaluateTable.setValueAt(LV[i-1], i, 4);
		}
		
	}

}

package com.horstmann.violet.application.gui.stepFive;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.common.ColorData;
import com.horstmann.violet.application.gui.common.DottedLabel;
import com.horstmann.violet.application.gui.common.DottedPanel;
import com.horstmann.violet.application.lmr.antcolony.GOModel;
import com.horstmann.violet.application.lmr.antcolony.JMModel;
import com.horstmann.violet.application.lmr.antcolony.LVModel;
import com.horstmann.violet.application.lmr.antcolony.MusaModel;

public class ReliabilityEvaluatePanel extends JPanel {

	private MainFrame mainFrame;

	private JPanel evaluatePanel;

	private JPanel evaluateTablePanel;
	private JTable evaluateTable;
	private DefaultTableModel evaluateTableModel;

	private JPanel labelPanel;
	private JLabel label;
	
	private DottedPanel dottedPanel;
	private JPanel resultPanel;

	public ReliabilityEvaluatePanel(MainFrame mainFrame) {

		this.mainFrame = mainFrame;

		evaluatePanel = new JPanel();
		labelPanel = new JPanel();

		initLabelPanel();
		initEvaluatePanel();
		
		dottedPanel=new DottedPanel();
		resultPanel=new JPanel();

		dottedPanel.setOpaque(false);
		resultPanel.setOpaque(false);
		
		GridBagLayout layout = new GridBagLayout();
		dottedPanel.setLayout(layout);
		dottedPanel.add(labelPanel);
		dottedPanel.add(evaluatePanel);
		layout.setConstraints(labelPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
		layout.setConstraints(evaluatePanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		resultPanel.setLayout(new BorderLayout());
		resultPanel.add(dottedPanel, BorderLayout.NORTH);
		resultPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		this.setLayout(new BorderLayout());
		this.add(resultPanel, BorderLayout.NORTH);

		this.setBackground(ColorData.white);

	}

	private void initLabelPanel() {

		label = new JLabel();
		label.setText("<html><body><p>根据上述推导出的估计值，该系统的可靠性指标的估计值为</p></body></html>");
		label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

		labelPanel.setLayout(new GridLayout());
		labelPanel.add(label);
		labelPanel.setBorder(BorderFactory.createEmptyBorder(20, 15, 10, 15));
		labelPanel.setOpaque(false);

	}

	private void initEvaluatePanel() {

		evaluateTablePanel = new JPanel();

		initEvaluateTablePanel();

		evaluatePanel.setLayout(new GridLayout());
		evaluatePanel.add(evaluateTablePanel);

		evaluatePanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 20, 15));
		evaluatePanel.setBackground(ColorData.white);

	}

	private void initEvaluateTablePanel() {

		final String[] columnNames = { "", "" };
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

		evaluateTable.setBackground(ColorData.white);
		evaluateTable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorData.gray));

		evaluateTablePanel.setLayout(new BorderLayout());
		evaluateTablePanel.add(evaluateTable, BorderLayout.NORTH);
		evaluateTablePanel.setOpaque(false);

		String[] str = new String[] { "可靠度", "不可靠度", "失效率", "剩余故障数", "MTTF" };
		Object[] rowData1 = { "名称", "结果" };
		evaluateTableModel.addRow(rowData1);
		for (int i = 0; i < str.length; i++) {
			Object[] rowData = { str[i], Math.random() };
			evaluateTableModel.addRow(rowData);
		}

	}
	
	public void dealAndShow() {
		
		JProgressBar progressBar=mainFrame.getStepFiveCenterPanel().getProgressPanel().getProgressBar();
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
		
		int selectModel=mainFrame.getStepFiveCenterPanel().getSelectModel();
		
		String[] str;
		double[] result;
		
		if(selectModel!=4){
			str= new String[] { "可靠度", "不可靠度", "失效率", "剩余故障数", "MTTF" };
			if(selectModel==1){
				result=new double[]{JMModel.Reliable,JMModel.NoReliable,JMModel.FailureRate,JMModel.ResidualFaults,JMModel.MTTF};
			}
			else if(selectModel==2){
				result=new double[]{GOModel.Reliable,GOModel.NoReliable,GOModel.FailureRate,GOModel.ResidualFaults,GOModel.MTTF};
			}
			else{
				result=new double[]{MusaModel.Reliable,MusaModel.NoReliable,MusaModel.FailureRate,MusaModel.ResidualFaults,MusaModel.MTTF};
			}
		}
		else{
			str= new String[] { "可靠度", "不可靠度", "失效率", "MTTF" };
			result=new double[]{LVModel.Reliable,LVModel.NoReliable,LVModel.FailureRate,LVModel.MTTF};
		}
		
		while(evaluateTableModel.getRowCount()>0){
			evaluateTableModel.removeRow(evaluateTableModel.getRowCount()-1);
		}
		
		Object[] rowData1 = { "名称", "结果" };
		evaluateTableModel.addRow(rowData1);
		for (int i = 0; i < str.length; i++) {
			Object[] rowData = { str[i], result[i] };
			evaluateTableModel.addRow(rowData);
		}
		
		progressBar.setValue(100);
		
		mainFrame.ChangeRepaint(this);
		
	}

}

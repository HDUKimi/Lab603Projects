package com.horstmann.violet.application.gui.stepFour;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.common.ColorData;

public class DealFailureDataPanel extends JPanel{

	private MainFrame mainFrame;
	
	private JScrollPane scrollPane;
	private JPanel resultPanel;

	private JPanel failureDataPanel;

	private JPanel failureDataTablePanel;
	private JTable failureDataTable;
	private DefaultTableModel failureDataTableModel;
	
	private JPanel labelPanel;
	private JLabel label;
	
	public DealFailureDataPanel(MainFrame mainFrame) {
	
		this.mainFrame = mainFrame;

		failureDataPanel = new JPanel();
		labelPanel = new JPanel();

		initLabelPanel();
		initFailureDataPanel();
		
		resultPanel=new JPanel();
		resultPanel.setBackground(ColorData.white);
		
		GridBagLayout layout = new GridBagLayout();
		resultPanel.setLayout(layout);
		resultPanel.add(labelPanel);
		resultPanel.add(failureDataPanel);
		layout.setConstraints(labelPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
		layout.setConstraints(failureDataPanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		resultPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		scrollPane=new JScrollPane(resultPanel);
		scrollPane.setBorder(null);

		this.setLayout(new GridLayout());
		this.add(scrollPane);
		
		this.setBackground(ColorData.white);
		
	}
	
	private void initLabelPanel() {

		label = new JLabel();
		label.setText("<html><body><p>根据测试用例执行结果，计算失效数据</p></body></html>");
		label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

		labelPanel.setLayout(new GridLayout());
		labelPanel.add(label);
		labelPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		labelPanel.setOpaque(false);

	}
	
	private void initFailureDataPanel() {

		failureDataTablePanel = new JPanel();

		initFailureDataTablePanel();

		failureDataPanel.setLayout(new GridLayout());
		failureDataPanel.add(failureDataTablePanel);

		failureDataPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		failureDataPanel.setBackground(ColorData.white);

	}

	private void initFailureDataTablePanel() {

		final String[] columnNames = { "", "", "" };
		String[][] tabelValues = {};

		failureDataTableModel = new DefaultTableModel(tabelValues, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		failureDataTable = new JTable(failureDataTableModel);

		failureDataTable.setSelectionBackground(ColorData.white);
		failureDataTable.setSelectionForeground(ColorData.black);
		failureDataTable.setGridColor(ColorData.gray);
		failureDataTable.setShowGrid(true);
		failureDataTable.setShowHorizontalLines(true);
		failureDataTable.setShowVerticalLines(true);
		failureDataTable.setFillsViewportHeight(true);
		failureDataTable.setRowHeight(27);
		failureDataTable.doLayout();
		failureDataTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		failureDataTable.setDefaultRenderer(Object.class, renderer);

		failureDataTable.setBackground(ColorData.white);
		failureDataTable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorData.gray));

		failureDataTablePanel.setLayout(new BorderLayout());
		failureDataTablePanel.add(failureDataTable, BorderLayout.NORTH);
		failureDataTablePanel.setOpaque(false);

		Object[] rowData1 = { "序号", "失效间隔时间", "累积失效发生时刻" };
		failureDataTableModel.addRow(rowData1);
		for (int i = 0; i < 100; i++) {
			Object[] rowData = { (i+1), (int)(Math.random()*100), (int)(Math.random()*100) };
			failureDataTableModel.addRow(rowData);
		}

	}
	
	public void dealAndShow() {
		
	}
	
}

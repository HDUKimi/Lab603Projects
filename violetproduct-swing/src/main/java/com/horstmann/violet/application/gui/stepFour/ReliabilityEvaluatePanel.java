package com.horstmann.violet.application.gui.stepFour;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.common.ColorData;
import com.horstmann.violet.application.gui.common.DottedLabel;

public class ReliabilityEvaluatePanel extends JPanel {

	private MainFrame mainFrame;

	private JPanel evaluatePanel;

	private JPanel evaluateTablePanel;
	private JTable evaluateTable;
	private DefaultTableModel evaluateTableModel;

	private JPanel labelPanel;
	private DottedLabel label;

	public ReliabilityEvaluatePanel(MainFrame mainFrame) {

		this.mainFrame = mainFrame;

		evaluatePanel = new JPanel();
		labelPanel = new JPanel();

		initLabelPanel();
		initEvaluatePanel();

		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(labelPanel);
		this.add(evaluatePanel);
		layout.setConstraints(labelPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
		layout.setConstraints(evaluatePanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));

		this.setBackground(ColorData.white);

	}

	private void initLabelPanel() {

		label = new DottedLabel();
		label.setText("<html><body><p>���������Ƶ����Ĺ���ֵ����ϵͳ�Ŀɿ���ָ��Ĺ���ֵΪ</p></body></html>");
		label.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));

		labelPanel.setLayout(new GridLayout());
		labelPanel.add(label);
		labelPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
		labelPanel.setOpaque(false);

	}

	private void initEvaluatePanel() {

		evaluateTablePanel = new JPanel();

		initEvaluateTablePanel();

		evaluatePanel.setLayout(new GridLayout());
		evaluatePanel.add(evaluateTablePanel);

		evaluatePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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

		String[] str = new String[] { "�ɿ���", "���ɿ���", "ʧЧ��", "ʣ�������", "MTTF" };
		Object[] rowData1 = { "����", "���" };
		evaluateTableModel.addRow(rowData1);
		for (int i = 0; i < str.length; i++) {
			Object[] rowData = { str[i], Math.random(), Math.random(), Math.random(), Math.random() };
			evaluateTableModel.addRow(rowData);
		}

	}

}

package com.horstmann.violet.application.gui;
import java.awt.Color;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class ConsolePartDetailInfoTable extends JTable {

	private static final long serialVersionUID = -8389977798357867875L;
	private DefaultTableModel defaultTableModel;
	private final Object[] columnNames = { "���", "��Ϣ","��Ϣ��ϸ����","����","��ע"};

	public ConsolePartDetailInfoTable() {

		defaultTableModel = new DefaultTableModel(columnNames, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		this.setModel(defaultTableModel);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setRowSelectionAllowed(true);
		this.setGridColor(Color.BLACK);
		this.setShowGrid(true);
		this.setShowHorizontalLines(true);
		this.setShowVerticalLines(true);
		this.setFillsViewportHeight(true);
		this.setDefaultEditor(Object.class, cellEditor);
		this.setColumnWidth(0, 50);
		this.doLayout();
		initRowsData();
	}

	public void initRowsData() {
		this.removeRowsData();

		List<ConsolePartDetailInfo> list = ConsolePartDataTestDao.getDetailInfoList();
		Object[] rowData = new Object[columnNames.length];
		int index = 1;// ������ʾ��ŵ�

		if (list != null && !list.isEmpty()) {
			for (ConsolePartDetailInfo info : list) {
				rowData[0] = index++;// ������ʾ��ŵ�
				rowData[1] = info.getMessage();// ��Ϣ
				
				rowData[2] = info.getMessagedetail();
				rowData[3] = info.getDate();// ����
				rowData[4] =info.getNote();
				defaultTableModel.addRow(rowData);
			}
		}
		this.revalidate();
	}

	private void removeRowsData() {
		int count = defaultTableModel.getRowCount();
		for (count -= 1; count > -1; count--) {
			defaultTableModel.removeRow(count);
		}
	}

	private void setColumnWidth(int columnIndex, int width) {
		TableColumn column = this.getColumnModel().getColumn(columnIndex);
		column.setPreferredWidth(width);
		column.setMaxWidth(width);
		column.setMinWidth(width);
	}
}

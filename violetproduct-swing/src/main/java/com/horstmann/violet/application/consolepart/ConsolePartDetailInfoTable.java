package com.horstmann.violet.application.consolepart;
import java.awt.Color;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.horstmann.violet.application.menu.util.dataBase.RealTestCaseVO;

public class ConsolePartDetailInfoTable extends JTable {

	private static final long serialVersionUID = -8389977798357867875L;
	private DefaultTableModel defaultTableModel;
	private final Object[] columnNames = { "���", "������������","��������·��","��ע"};

	public ConsolePartDetailInfoTable(int index) {

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
		initRowsData(index);
	}    
	public void initRowsData(int i) {
		
		this.removeRowsData();
		if(i==1){//ʵ����
		List<RealTestCaseVO> list = ConsolePartDataTestDao.getRealTestCaseList();
		Object[] rowData = new Object[columnNames.length];
		int index = 1;// ������ʾ��ŵ�
  
		if (list != null && !list.isEmpty()) {
			for (RealTestCaseVO info : list) {
				rowData[0] = index++;// ������ʾ��ŵ�
				rowData[1] = info.getName();//������������
				
				rowData[2] = info.getProcessList();//·��
				rowData[3] = info.getRemark();// ��ע
				
				defaultTableModel.addRow(rowData);
			}
		}
		}
		if(i==0)//����
		{
			List<RealTestCaseVO> list = ConsolePartDataTestDao.getRealTestCaseList();
			Object[] rowData = new Object[columnNames.length];
			int index = 1;// ������ʾ��ŵ�
	  
			if (list != null && !list.isEmpty()) {
				for (RealTestCaseVO info : list) {
					rowData[0] = index++;// ������ʾ��ŵ�
					rowData[1] = info.getName();//������������
					
					rowData[2] = info.getProcessList();//·��
					rowData[3] = info.getRemark();// ��ע
					
					defaultTableModel.addRow(rowData);
				}
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

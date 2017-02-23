package com.horstmann.violet.application.consolepart;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import java.util.Timer;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.horstmann.violet.application.menu.util.zhangjian.Database.AbstractTestCaseVO;
import com.horstmann.violet.application.menu.util.zhangjian.Database.RealTestCaseVO;

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
		this.setRowSelectionAllowed(true);
		
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setSelectionBackground(new Color(250, 248, 236));
		this.setGridColor(new Color(224, 226, 220));
		this.setShowGrid(true);
		this.setShowHorizontalLines(true);
		this.setShowVerticalLines(false);
		this.setFillsViewportHeight(true);
		this.setRowHeight(27);
		this.doLayout();
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		this.getColumn("���").setPreferredWidth(10);
		this.getColumn("���").setMinWidth(10);
		this.getColumn("������������").setPreferredWidth(50);
		this.getColumn("������������").setMinWidth(50);
		this.getColumn("��������·��").setPreferredWidth(500);
		this.getColumn("��������·��").setMinWidth(500);
		this.getColumn("��ע").setPreferredWidth(10);
		this.getColumn("��ע").setMinWidth(10);
		
		DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
        renderer.setBackground(new Color(71, 80, 93));
        renderer.setForeground(new Color(255, 255, 255));
        renderer.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        this.getTableHeader().setDefaultRenderer(renderer); 
        
        this.getTableHeader().setPreferredSize(new Dimension(1, 27));
        
        DefaultTableCellRenderer renderer1=new DefaultTableCellRenderer();
        renderer1.setForeground(new Color(115, 110, 102));
        renderer1.setBackground(new Color(255, 255, 255));
        renderer1.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        renderer1.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        this.setDefaultRenderer(Object.class, renderer1); 
        
        this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(224, 225, 220)));
		
        this.setBackground(new Color(255, 255, 255));
        
		
		initRowsData(index);
	}    
	public void initRowsData(int i) {
		
		this.removeRowsData();
		if(i==1){//ʵ����
	

			long time1 = System.currentTimeMillis();
			System.out.println(time1);
			
			
				List<RealTestCaseVO> list = ConsolePartDataTestDao.getRealTestCaseList();
		        Object[] rowData = new Object[columnNames.length];
		        int index = 1;// ������ʾ��ŵ�
		        int Index=0;
		        
		        long time2 = System.currentTimeMillis();
				System.out.println(time2);
				
				System.out.println(time2-time1);
		        
		        System.out.println("++++++++++++"+list.size());
		        
				// TODO Auto-generated method stub
			while (Index + 47 < list.size()) {

				if (list != null && !list.isEmpty()) {
					for (RealTestCaseVO info : list.subList(Index, Index + 47)) {
						rowData[0] = index++;// ������ʾ��ŵ�
						rowData[1] = info.getName();// ������������
						rowData[2] = info.getProcessList();// ·��
						rowData[3] = info.getRemark();// ��ע
						defaultTableModel.addRow(rowData);
						defaultTableModel.fireTableDataChanged();
					}
				}
				Index += 47;
			}																					        		
		}
		if(i==0)//����
		{
			List<AbstractTestCaseVO> list = ConsolePartDataTestDao.getAbsTestCaseList();
			Object[] rowData = new Object[columnNames.length];
			int index = 1;// ������ʾ��ŵ�
	  
			if (list != null && !list.isEmpty()) {
				for (AbstractTestCaseVO info : list) {
					rowData[0] = index++;// ������ʾ��ŵ�
					rowData[1] = info.getName();//������������
					
					rowData[2] = info.getTextRouter();//·��
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

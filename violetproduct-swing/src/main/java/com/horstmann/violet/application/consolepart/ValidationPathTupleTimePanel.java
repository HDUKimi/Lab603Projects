package com.horstmann.violet.application.consolepart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.horstmann.violet.application.gui.stepCenterTabbedPane.MyLabelCellEditor;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.MyLabelRenderer;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.MyProgressRenderer;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.PathTuple;

public class ValidationPathTupleTimePanel extends JPanel{

	private ArrayList<PathTuple> pathtuple;
	
	private ArrayList<Integer> times;
	
	private JTable timetable;
	private DefaultTableModel timetablemodel;
	
	private int sumtimes=0;
	
	public ValidationPathTupleTimePanel(ArrayList<PathTuple> pathtuple,ArrayList<Integer> times){
		
		this.pathtuple=pathtuple;
		
		this.times=times;
		
		init();
		
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		this.setBackground(new Color(255, 255, 255));
		
	}

	private void init() {
		// TODO Auto-generated method stub
		
		initTimeTablePanel();
		
//		this.setLayout(new GridLayout());
//		this.add(timetable);
//		this.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		
		this.setLayout(new BorderLayout());
		this.add(timetable.getTableHeader(),BorderLayout.NORTH);
		this.add(timetable, BorderLayout.CENTER);
		
	}

	private void initTimeTablePanel() {
		// TODO Auto-generated method stub
		
		String[] columnNames={"类别","名字","时间刻度"};
		String[][] tabelValues={};
		
		timetablemodel=new DefaultTableModel(tabelValues, columnNames){
			@Override  
            public boolean isCellEditable(int row,int column){  
                return false;  
            } 
		};
		
		timetable=new JTable(timetablemodel);
		
		timetable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        timetable.setSelectionBackground(new Color(250, 248, 236));
        timetable.setGridColor(new Color(224, 226, 220));
		timetable.setShowGrid(true);
		timetable.setShowHorizontalLines(true);
		timetable.setShowVerticalLines(false);
		timetable.setFillsViewportHeight(true);
		timetable.setRowHeight(27);
		timetable.doLayout();
		timetable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
//		timetable.getColumnModel().getColumn(1).setCellEditor(new MyLabelCellEditor());
		timetable.getColumnModel().getColumn(0).setCellRenderer(new LocationTransitionLabelRenderer());
		
		timetable.getColumn("类别").setPreferredWidth(30);
		timetable.getColumn("类别").setMinWidth(30);
		timetable.getColumn("名字").setPreferredWidth(200);
		timetable.getColumn("名字").setMinWidth(200);
		timetable.getColumn("时间刻度").setPreferredWidth(60);
		timetable.getColumn("时间刻度").setMinWidth(60);
        
        DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
        renderer.setBackground(new Color(71, 80, 93));
        renderer.setForeground(new Color(255, 255, 255));
        renderer.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        timetable.getTableHeader().setDefaultRenderer(renderer); 
        
        timetable.getTableHeader().setPreferredSize(new Dimension(100, 27));
        
        DefaultTableCellRenderer renderer1=new DefaultTableCellRenderer();
        renderer1.setForeground(new Color(115, 110, 102));
        renderer1.setBackground(new Color(255, 255, 255));
        renderer1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        renderer1.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        timetable.setDefaultRenderer(Object.class, renderer1); 
        
        timetable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(224, 225, 220)));
		
        timetable.setBackground(new Color(255, 255, 255));
        
        sumtimes=0;
        for(int i=0;i<times.size();i++){
			
        	String pathtuplename;
        	int state;
			if(i%2==0){
				pathtuplename=pathtuple.get(i/2).getLocation().getName();
				state=0;
			}
			else{
				pathtuplename=pathtuple.get(i/2).getTransition().getName();
				state=1;
			}
			
			Object[] rowData={state,pathtuplename,times.get(i)};
            timetablemodel.addRow(rowData);
			
            sumtimes+=times.get(i);
            
		}
        
		
	}

	public int getSumtimes() {
		return sumtimes;
	}
	
	
	
}

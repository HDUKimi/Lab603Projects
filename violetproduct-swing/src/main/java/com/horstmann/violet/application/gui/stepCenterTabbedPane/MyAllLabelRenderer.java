package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class MyAllLabelRenderer extends JLabel implements TableCellRenderer{

	String tablename;
	
	public void fillColor(JTable t,JLabel l,boolean isSelected ){
        //setting the background and foreground when JLabel is selected
		
        if(isSelected){
            l.setBackground(t.getSelectionBackground());
//            l.setForeground(l.getForeground());
            l.setForeground(new Color(0, 0, 0));
        }

        else{
            l.setBackground(t.getBackground());
            l.setForeground(l.getForeground());
        }
    }
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO Auto-generated method stub
		
		
		setForeground(new Color(115, 110, 102));
		setBackground(new Color(255, 255, 255));
		setText(value.toString());
		
		tablename=table.getName();
		
		if(tablename.equals("TestCaseProcessEndPanel")){
//			final String[] columnNames={"����","��ʱ","���н��"};
			setFont(new Font("΢���ź�", Font.PLAIN, 12));
			if(table.getColumnName(column).equals("��ʱ")){
				setHorizontalAlignment(JLabel.CENTER);
				setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			}
			else{
				setHorizontalAlignment(JLabel.LEFT);
				setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
			}
			
		}
		else if(tablename.equals("UppaalParseStateInforPartPanel")){
//			final String[] columnNames={"���","����","λ��","�Ƿ�Ϊ��ֹ״̬","����"};
//			setFont(new Font("΢���ź�", Font.PLAIN, 10));
			if(table.getColumnName(column).equals("���")||table.getColumnName(column).equals("�Ƿ�Ϊ��ֹ״̬")){
				setHorizontalAlignment(JLabel.CENTER);
				setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			}
			else{
				setHorizontalAlignment(JLabel.LEFT);
				setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
			}
		}
		else if(tablename.equals("UppaalParseMigrateInforPartPanel")){
//			final String[] columnNames={"���","����","Դ״̬����","Ŀ��״̬����","in(Լ������)","out(�����Ϣ)","conditions(Լ������)"};
//			setFont(new Font("΢���ź�", Font.PLAIN, 10));
			if(table.getColumnName(column).equals("���")){
				setHorizontalAlignment(JLabel.CENTER);
				setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			}
			else{
				setHorizontalAlignment(JLabel.LEFT);
				setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
			}
		}
		else if(tablename.equals("UppaalOptimizationStateInforPartPanel")){
//			final String[] columnNames={"״̬","���","����","λ��","�Ƿ�Ϊ��ֹ״̬","����"};
//			setFont(new Font("΢���ź�", Font.PLAIN, 10));
			setForeground(new Color(0,0,0));
			setBackground(new Color(255, 255, 255));
			if(Integer.parseInt(table.getValueAt(row, 0).toString())==-1){
				setForeground(new Color(177, 177, 177));
				setBackground(new Color(255, 255, 255));
			}
			if(table.getColumnName(column).equals("���")||table.getColumnName(column).equals("�Ƿ�Ϊ��ֹ״̬")){
				setHorizontalAlignment(JLabel.CENTER);
				setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			}
			else{
				setHorizontalAlignment(JLabel.LEFT);
				setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
			}
		}
		else if(tablename.equals("UppaalOptimizationMigrateInforPartPanel")){
//			final String[] columnNames={"״̬","���","����","in(Լ������)","conditions(Լ������)","out(�����Ϣ)","����ʱ��"};
//			setFont(new Font("΢���ź�", Font.PLAIN, 12));
			setForeground(new Color(0,0,0));
			setBackground(new Color(255, 255, 255));
			if(Integer.parseInt(table.getValueAt(row, 0).toString())==-1){
				setForeground(new Color(177, 177, 177));
				setBackground(new Color(255, 255, 255));
			}
			if(table.getColumnName(column).equals("���")){
				setHorizontalAlignment(JLabel.CENTER);
				setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			}
			else{
				setHorizontalAlignment(JLabel.LEFT);
				setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
			}
		}
		else if(tablename.equals("TestCaseCoverPartPanel")){
//			final String[] columnNames={"Ǩ��Id","Ǩ������","Դ״̬����","Ŀ��״̬����","in(Լ������)","out(�����Ϣ)","conditions(Լ������)"};
//			setFont(new Font("΢���ź�", Font.PLAIN, 12));
			if(table.getColumnName(column).equals("Ǩ��Id")){
				setHorizontalAlignment(JLabel.CENTER);
				setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			}
			else{
				setHorizontalAlignment(JLabel.LEFT);
				setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
			}
		}
		else if(tablename.equals("TestCaseProducePartPanel")){
//			final String[] columnNames={"Ǩ��Id","Ǩ������","Դ״̬����","Ŀ��״̬����","ʵ����Լ������"};
//			setFont(new Font("΢���ź�", Font.PLAIN, 12));
			if(table.getColumnName(column).equals("Ǩ��Id")){
				setHorizontalAlignment(JLabel.CENTER);
				setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			}
			else{
				setHorizontalAlignment(JLabel.LEFT);
				setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
			}
		}
		else if(tablename.equals("TestCaseInstantiationPartPanel")){
//			final String[] columnNames={"Ǩ��Id","Ǩ������","Դ״̬����","ʵ�������"};
//			setFont(new Font("΢���ź�", Font.PLAIN, 12));
			if(table.getColumnName(column).equals("Ǩ��Id")){
				setHorizontalAlignment(JLabel.CENTER);
				setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			}
			else{
				setHorizontalAlignment(JLabel.LEFT);
				setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
			}
		}
		
        setOpaque(true);
		
		fillColor(table,this,isSelected);
		
		return this;
	}

}

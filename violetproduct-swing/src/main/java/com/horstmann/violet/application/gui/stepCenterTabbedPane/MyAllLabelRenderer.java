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
//			final String[] columnNames={"步骤","耗时","运行结果"};
			setFont(new Font("微软雅黑", Font.PLAIN, 12));
			if(table.getColumnName(column).equals("耗时")){
				setHorizontalAlignment(JLabel.CENTER);
				setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			}
			else{
				setHorizontalAlignment(JLabel.LEFT);
				setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
			}
			
		}
		else if(tablename.equals("UppaalParseStateInforPartPanel")){
//			final String[] columnNames={"序号","名称","位置","是否为终止状态","类型"};
//			setFont(new Font("微软雅黑", Font.PLAIN, 10));
			if(table.getColumnName(column).equals("序号")||table.getColumnName(column).equals("是否为终止状态")){
				setHorizontalAlignment(JLabel.CENTER);
				setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			}
			else{
				setHorizontalAlignment(JLabel.LEFT);
				setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
			}
		}
		else if(tablename.equals("UppaalParseMigrateInforPartPanel")){
//			final String[] columnNames={"序号","名称","源状态名称","目的状态名称","in(约束条件)","out(输出信息)","conditions(约束条件)"};
//			setFont(new Font("微软雅黑", Font.PLAIN, 10));
			if(table.getColumnName(column).equals("序号")){
				setHorizontalAlignment(JLabel.CENTER);
				setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			}
			else{
				setHorizontalAlignment(JLabel.LEFT);
				setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
			}
		}
		else if(tablename.equals("UppaalOptimizationStateInforPartPanel")){
//			final String[] columnNames={"状态","序号","名称","位置","是否为终止状态","类型"};
//			setFont(new Font("微软雅黑", Font.PLAIN, 10));
			setForeground(new Color(0,0,0));
			setBackground(new Color(255, 255, 255));
			if(Integer.parseInt(table.getValueAt(row, 0).toString())==-1){
				setForeground(new Color(177, 177, 177));
				setBackground(new Color(255, 255, 255));
			}
			if(table.getColumnName(column).equals("序号")||table.getColumnName(column).equals("是否为终止状态")){
				setHorizontalAlignment(JLabel.CENTER);
				setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			}
			else{
				setHorizontalAlignment(JLabel.LEFT);
				setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
			}
		}
		else if(tablename.equals("UppaalOptimizationMigrateInforPartPanel")){
//			final String[] columnNames={"状态","序号","名称","in(约束条件)","conditions(约束条件)","out(输出信息)","重置时钟"};
//			setFont(new Font("微软雅黑", Font.PLAIN, 12));
			setForeground(new Color(0,0,0));
			setBackground(new Color(255, 255, 255));
			if(Integer.parseInt(table.getValueAt(row, 0).toString())==-1){
				setForeground(new Color(177, 177, 177));
				setBackground(new Color(255, 255, 255));
			}
			if(table.getColumnName(column).equals("序号")){
				setHorizontalAlignment(JLabel.CENTER);
				setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			}
			else{
				setHorizontalAlignment(JLabel.LEFT);
				setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
			}
		}
		else if(tablename.equals("TestCaseCoverPartPanel")){
//			final String[] columnNames={"迁移Id","迁移名称","源状态名称","目的状态名称","in(约束条件)","out(输出信息)","conditions(约束条件)"};
//			setFont(new Font("微软雅黑", Font.PLAIN, 12));
			if(table.getColumnName(column).equals("迁移Id")){
				setHorizontalAlignment(JLabel.CENTER);
				setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			}
			else{
				setHorizontalAlignment(JLabel.LEFT);
				setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
			}
		}
		else if(tablename.equals("TestCaseProducePartPanel")){
//			final String[] columnNames={"迁移Id","迁移名称","源状态名称","目的状态名称","实例化约束条件"};
//			setFont(new Font("微软雅黑", Font.PLAIN, 12));
			if(table.getColumnName(column).equals("迁移Id")){
				setHorizontalAlignment(JLabel.CENTER);
				setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			}
			else{
				setHorizontalAlignment(JLabel.LEFT);
				setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
			}
		}
		else if(tablename.equals("TestCaseInstantiationPartPanel")){
//			final String[] columnNames={"迁移Id","迁移名称","源状态名称","实例化结果"};
//			setFont(new Font("微软雅黑", Font.PLAIN, 12));
			if(table.getColumnName(column).equals("迁移Id")){
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

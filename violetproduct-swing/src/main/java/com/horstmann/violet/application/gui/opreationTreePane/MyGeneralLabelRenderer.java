package com.horstmann.violet.application.gui.opreationTreePane;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class MyGeneralLabelRenderer extends JLabel implements TableCellRenderer{

	public void fillColor(JTable t,JLabel l,boolean isSelected ){
        //setting the background and foreground when JLabel is selected
        if(isSelected){
            l.setBackground(t.getSelectionBackground());
//            l.setForeground(t.getSelectionForeground());
        }

        else{
            l.setBackground(t.getBackground());
//            l.setForeground(t.getForeground());
        }
    }

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO Auto-generated method stub
		
		setText(value.toString());
		
		setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		
		setOpaque(true);
        
        fillColor(table,this,isSelected);
		
		return this;
	}
	
	
	
}

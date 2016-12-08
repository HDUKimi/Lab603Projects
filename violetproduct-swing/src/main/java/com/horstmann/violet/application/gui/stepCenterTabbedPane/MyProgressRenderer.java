package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.table.TableCellRenderer;

public class MyProgressRenderer extends JProgressBar implements TableCellRenderer{

	int progressvalue;
	
	public void fillColor(JTable t,JProgressBar l,boolean isSelected ){
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
		
		progressvalue=Integer.parseInt(value.toString());
		
		setBorderPainted(false);
//		setBorderPainted(true);
		
		setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
//		setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.ORANGE));
		
		setUI(new ProgressUI(this,new Color(58,145,159)));
		
		setValue(progressvalue);
		
		setOpaque(true);
        
        fillColor(table,this,isSelected);
		
//        setPreferredSize(new Dimension(50, 20));
//        setMaximumSize(new Dimension(50, 20));
//        setMinimumSize(new Dimension(50, 20));
        
		return this;
	}

}

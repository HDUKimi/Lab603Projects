package com.horstmann.violet.application.consolepart;

import java.awt.Component;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class TestCaseInforLabelRenderer extends JLabel implements TableCellRenderer{

	private String text;
	
	public void fillColor(JTable t,JLabel l,boolean isSelected ){
        //setting the background and foreground when JLabel is selected
        if(isSelected){
            l.setBackground(t.getSelectionBackground());
            l.setForeground(t.getSelectionForeground());
        }

        else{
            l.setBackground(t.getBackground());
            l.setForeground(t.getForeground());
        }
    }
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO Auto-generated method stub

		text=value.toString();
		
		if(text.startsWith("+-+")){
			setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 12));
			setText("<html><u>"+text.replace("+-+", "")+"</u><html>");
		}
		else{
			setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
			setText(text);
		}
		
        setHorizontalAlignment(JLabel.LEFT);
        
        setOpaque(true);
        
        fillColor(table,this,isSelected);
        
        setBorder(BorderFactory.createEmptyBorder(0,8,0,0));
		
		return this;
	}

}

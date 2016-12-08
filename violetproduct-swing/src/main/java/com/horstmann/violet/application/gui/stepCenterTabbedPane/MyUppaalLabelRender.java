package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class MyUppaalLabelRender extends JLabel implements TableCellRenderer{

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

		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";
		
		
		
		ImageIcon icon = new ImageIcon(path + "table_uppaal.png");
		icon.setImage(icon.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		
        setIcon(icon);
        setText(value.toString());
//        setHorizontalAlignment(JLabel.CENTER);
        
        setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        setOpaque(true);
        
        fillColor(table,this,isSelected);
		
		return this;
	}

}

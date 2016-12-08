package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class MyLabelRenderer extends JLabel implements TableCellRenderer{

	String pngname;
	int pngstate;
	
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

		pngstate=Integer.parseInt(value.toString());
		
		if(pngstate==0){
			pngname="table_start";
		}
		else if(pngstate==1){
			pngname="table_end";
		}
//		else if(pngstate==2){
//			pngname="table_uppaal";
//		}
		
		ImageIcon icon = new ImageIcon(path + pngname+".png");
		icon.setImage(icon.getImage().getScaledInstance(22,22, Image.SCALE_DEFAULT));
		
        setIcon(icon);
//        setBackground(Color.RED);
//        setOpaque(false);

        setHorizontalAlignment(JLabel.CENTER);
        
        setOpaque(true);
        
        fillColor(table,this,isSelected);
        
//        setBorder(BorderFactory.createEmptyBorder(3,10,3,10));
		
		return this;
	}

}

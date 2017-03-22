package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class MyAddDeleteLabelRenderer extends JLabel implements TableCellRenderer{

	String pngname="";
	int pngstate;
	
	public void fillColor(JTable t,JLabel l,boolean isSelected ){
        //setting the background and foreground when JLabel is selected
        if(isSelected){
            l.setBackground(t.getSelectionBackground());
            l.setForeground(t.getSelectionForeground());
        }

        else{
//        	if(pngstate==1){
//        		l.setForeground(new Color(0,0,0));
//				l.setBackground(new Color(205,255,185));
//        	}
//        	else if(pngstate==-1){
//        		l.setForeground(new Color(177,177,177));
//        		l.setBackground(new Color(200,200,200));
//        	}
//        	else{
//        		l.setForeground(new Color(115, 110, 102));
//        		l.setBackground(new Color(255, 255, 255));
//        	}
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
		
		if(pngstate==1){
			pngname="add3";
//			setForeground(new Color(115, 110, 102));
//			setBackground(new Color(255, 135, 135));
			
		}
		else if(pngstate==-1){
			pngname="delete3";
//			setForeground(new Color(5, 110, 2));
//			setBackground(new Color(5, 135, 5));
		}
		else{
			pngname="empty";
		}

		ImageIcon icon = new ImageIcon(path + pngname + ".png");
		icon.setImage(icon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));

		setIcon(icon);

        setHorizontalAlignment(JLabel.CENTER);
        
        setOpaque(true);
        
        fillColor(table,this,isSelected);
        
		return this;
	}

}

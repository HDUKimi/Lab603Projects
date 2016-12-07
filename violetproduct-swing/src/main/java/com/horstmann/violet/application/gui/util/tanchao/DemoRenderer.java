package com.horstmann.violet.application.gui.util.tanchao;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class DemoRenderer extends DefaultTreeCellRenderer {  
	  
    @Override  
    public Component getTreeCellRendererComponent(JTree tree, Object value,  
            boolean sel, boolean expanded, boolean leaf, int row,  
            boolean hasFocus) {  
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;  
        // ���ڵ��0��ʼ����������  
        // ����   
            if(!node.isLeaf())
            {
            if (expanded) {  
                this.setIcon(new ImageIcon("src/site/resources/icons/jianhao.png"));  
            } else {  
                this.setIcon(new ImageIcon("src/site/resources/icons/jiahao.png"));  
            }   
            }
        this.setText(value.toString());  
        return this;  
    }
}

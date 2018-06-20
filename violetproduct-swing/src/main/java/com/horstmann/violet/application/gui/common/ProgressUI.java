package com.horstmann.violet.application.gui.common;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class ProgressUI extends BasicProgressBarUI {

    private JProgressBar jProgressBar;
    private Color forecolor;

    public ProgressUI(JProgressBar jProgressBar,Color forecolor) {
        this.jProgressBar = jProgressBar;
        this.forecolor=forecolor;
    }

    @Override
    protected void paintDeterminate(Graphics g, JComponent c) {

        this.jProgressBar.setBackground(ColorData.white);
        this.jProgressBar.setForeground(forecolor);
        
        super.paintDeterminate(g, c);
    }

}

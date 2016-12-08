package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.colorpicker.ColorPickerDemo;
import com.horstmann.violet.workspace.IWorkspace;

public class ToolPanel extends JPanel{
	
	private MainFrame mainFrame;
	private IWorkspace workspace;
	
//	private JLabel toolLabel;
	
	private JPanel editortoolpanel;
	private JPanel graphtoolpanel;
	private JPanel optionaltoolpanel;
	private JPanel colorpanel;
	
	private JPanel emptypanel1;
	private JPanel emptypanel2;
	private JPanel emptypanel3;
	
	
	public ToolPanel(MainFrame mainFrame,IWorkspace workspace){
		
		this.mainFrame=mainFrame;
		this.workspace=workspace;
		
//		toolLabel=new JLabel();
		
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
//		toolLabel.setText("显示相关内容(R)");
//		toolLabel.setForeground(new Color(0, 0,0));
//		toolLabel.setFont(new Font("System", Font.PLAIN, 12));
//		toolLabel.setBorder(BorderFactory.createEmptyBorder(4,8,4,0));
		
		editortoolpanel=new EditorToolPanel(mainFrame,workspace);
		editortoolpanel.setOpaque(false);
		editortoolpanel.setBorder(BorderFactory.createEmptyBorder(1, 0, 0, 0));
		
		graphtoolpanel=new GraphToolPanel(mainFrame,workspace);
		graphtoolpanel.setOpaque(false);
		
		colorpanel=new ColorPickerDemo(mainFrame,workspace);
		colorpanel.setOpaque(false);
		
		optionaltoolpanel=new OptionalToolPanel(mainFrame,workspace);
		optionaltoolpanel.setOpaque(false);
		
		initemptypanel();
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT,3,0));
		this.setBackground(new Color(207,214,229));
//		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(142, 155, 188)));
		this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		this.setPreferredSize(new Dimension(700, 29));
		this.setMaximumSize(new Dimension(700, 29));
		this.setMinimumSize(new Dimension(700, 29));
		
//		this.add(toolLabel);	
		this.add(editortoolpanel);
		this.add(emptypanel1);
		this.add(graphtoolpanel);
		this.add(emptypanel2);
		this.add(optionaltoolpanel);
		this.add(emptypanel3);
		this.add(colorpanel);
	}

	private void initemptypanel() {
		// TODO Auto-generated method stub
		
		emptypanel1=new JPanel();
		emptypanel1.setPreferredSize(new Dimension(1, 23));
		emptypanel1.setBackground(new Color(133,145,162));
		emptypanel2=new JPanel();
		emptypanel2.setPreferredSize(new Dimension(1, 23));
		emptypanel2.setBackground(new Color(133,145,162));
		emptypanel3=new JPanel();
		emptypanel3.setPreferredSize(new Dimension(1, 23));
		emptypanel3.setBackground(new Color(133,145,162));
		
	}

}

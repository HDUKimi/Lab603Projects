package com.horstmann.violet.application.gui.stepFour;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Stroke;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.common.ColorData;
import com.horstmann.violet.application.gui.common.DottedLabel;

public class ModelSelectPanel extends JPanel{

	private MainFrame mainFrame;
	
	private JPanel labelPanel1;
	private JPanel labelPanel2;
	private JPanel labelPanel3;
	
	private DottedLabel label1;
	private JLabel label2;
	private DottedLabel label3;
	
	private JPanel emptyPanel;
	private JPanel emptyPanel1;
	private JPanel emptyPanel2;
	
	
	public ModelSelectPanel(MainFrame mainFrame) {
		
		this.mainFrame=mainFrame;
		
		labelPanel1= new JPanel();
		labelPanel2=new JPanel();
		labelPanel3=new JPanel();
		
		emptyPanel=new JPanel();
		
		labelPanel1.setOpaque(false);
		labelPanel2.setOpaque(false);
		labelPanel3.setOpaque(false);
		emptyPanel.setOpaque(false);
		
		initLabelPanel1();
		initLabelPanel2();
		initLabelPanel3();
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(labelPanel1);
		this.add(labelPanel2);
		this.add(labelPanel3);
		this.add(emptyPanel);
		layout.setConstraints(labelPanel1, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
		layout.setConstraints(labelPanel2, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
		layout.setConstraints(labelPanel3, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
		layout.setConstraints(emptyPanel, new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		this.setBackground(ColorData.white);
		
	}


	private void initLabelPanel1() {
		
		label1=new DottedLabel();
		label1.setText("<html><body><p>根据模型评价标准分别计算各个待选模型的5项评价值，并进行分级编码。</p><br><p>编码结果如表9所示，括号内为级别，其编码后最终的结果为{4,4,1,1,4}</p></body></html>");
		label1.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		
		
		labelPanel1.setLayout(new GridLayout());
		labelPanel1.add(label1);
		labelPanel1.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));
		
	}


	private void initLabelPanel2() {
		
		ImageIcon icon = new ImageIcon(this.getClass().getResource("1.png"));
		icon.setImage(icon.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT));
		
		label2=new JLabel();
		label2.setIcon(icon);
		
		emptyPanel1=new JPanel();
		emptyPanel2=new JPanel();
		emptyPanel1.setOpaque(false);
		emptyPanel2.setOpaque(false);
		
		GridBagLayout layout = new GridBagLayout();
		labelPanel2.setLayout(layout);
		labelPanel2.add(emptyPanel1);
		labelPanel2.add(label2);
		labelPanel2.add(emptyPanel2);
		layout.setConstraints(emptyPanel1, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(label2, new GBC(1, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
		layout.setConstraints(emptyPanel2, new GBC(2, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		
		labelPanel2.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		
	}


	private void initLabelPanel3() {
		
		label3=new DottedLabel();
		label3.setText("<html><body><p>将编码后的结果输入到稳定的BP神经网络中进行计算</p><br><p>输出结果为4，即LV模型为最佳的模型选择</p></body></html>");
		label3.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		
		labelPanel3.setLayout(new GridLayout());
		labelPanel3.add(label3);
		labelPanel3.setBorder(BorderFactory.createEmptyBorder(10, 10, 30, 10));
		
	}
	
}

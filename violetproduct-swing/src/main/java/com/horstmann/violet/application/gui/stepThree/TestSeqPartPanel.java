package com.horstmann.violet.application.gui.stepThree;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.common.ColorData;

public class TestSeqPartPanel extends JPanel{

	private JPanel titlePanel;
	private JPanel linePanel;
	private JPanel attributePanel;
	
	private JLabel titleLabel;
	private JLabel iconLabel;
	private ImageIcon upImageIcon;
	private ImageIcon downImageIcon;
	
	private JLabel lineLabel;
	
	private JPanel attributeTablePanel;
	private JTable attributeTable;
	private DefaultTableModel attributeTableModel;
	
	private int isShow=0;
	
	public TestSeqPartPanel() {
		
		titlePanel=new JPanel();
		linePanel=new JPanel();
		attributePanel=new JPanel();
		
		initTitlePanel();
		initLinePanel();
		initAttributePanel();
		
		linePanel.setVisible(false);
		attributePanel.setVisible(false);
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(titlePanel);
		this.add(linePanel);
		this.add(attributePanel);
		layout.setConstraints(titlePanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(linePanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
		layout.setConstraints(attributePanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		
		this.setBackground(ColorData.white);
		this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, ColorData.gray));
		
	}

	private void initTitlePanel() {
		
		titleLabel=new JLabel();
		
		titleLabel.setText("≤‚ ‘–Ú¡–1");
		titleLabel.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 13));
		
		initIconLabel();
		
		titlePanel.setLayout(new BorderLayout());
		titlePanel.add(titleLabel, BorderLayout.WEST);
		titlePanel.add(iconLabel, BorderLayout.EAST);
		
		titlePanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		
		titlePanel.setBackground(ColorData.white);
		
	}

	private void initIconLabel() {
		
		upImageIcon = new ImageIcon(this.getClass().getResource("up.png"));
		upImageIcon.setImage(upImageIcon.getImage().getScaledInstance(15,15, Image.SCALE_DEFAULT));
		
		downImageIcon = new ImageIcon(this.getClass().getResource("down.png"));
		downImageIcon.setImage(downImageIcon.getImage().getScaledInstance(15,15, Image.SCALE_DEFAULT));
		
		iconLabel=new JLabel();
		iconLabel.setIcon(downImageIcon);
		
		iconLabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isShow==0){
					linePanel.setVisible(true);
					attributePanel.setVisible(true);
					iconLabel.setIcon(upImageIcon);
					isShow=1;
				}
				else{
					linePanel.setVisible(false);
					attributePanel.setVisible(false);
					iconLabel.setIcon(downImageIcon);
					isShow=0;
				}
			}
		});
		
	}

	private void initLinePanel() {
		
		lineLabel=new JLabel();
		
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<50;i++){
			sb.append(" -");
		}
		
		lineLabel.setText(sb.toString());
		lineLabel.setForeground(ColorData.gray);
		lineLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		
		linePanel.setLayout(new BorderLayout());
		linePanel.add(lineLabel, BorderLayout.WEST);
		linePanel.setBackground(ColorData.white);
		
	}

	private void initAttributePanel() {
		
		attributeTablePanel = new JPanel();

		initAttributeTablePanel();
		
		attributePanel.setLayout(new GridLayout());
		attributePanel.add(attributeTablePanel);
		
	}

	private void initAttributeTablePanel() {
		
		final String[] columnNames = { "–Ú∫≈","√˚≥∆","ID"," «∑ÒŒ™÷’÷π◊¥Ã¨","¿‡–Õ" };
		String[][] tabelValues = {};

		attributeTableModel = new DefaultTableModel(tabelValues, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		attributeTable = new JTable(attributeTableModel);

		attributeTable.setSelectionBackground(ColorData.white);
		attributeTable.setSelectionForeground(ColorData.black);
		attributeTable.setGridColor(ColorData.gray);
		attributeTable.setShowGrid(true);
		attributeTable.setShowHorizontalLines(true);
		attributeTable.setShowVerticalLines(true);
		attributeTable.setFillsViewportHeight(true);
		attributeTable.setRowHeight(27);
		attributeTable.doLayout();
		attributeTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		attributeTable.setDefaultRenderer(Object.class, renderer);
		
		attributeTable.setBackground(ColorData.white);
		attributeTable.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, ColorData.gray));

		attributeTablePanel.setLayout(new BorderLayout());
		attributeTablePanel.add(attributeTable, BorderLayout.CENTER);
		attributeTablePanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
		attributeTablePanel.setBackground(ColorData.white);

		Object[] rowData1 = { "–Ú∫≈","√˚≥∆","ID"," «∑ÒŒ™÷’÷π◊¥Ã¨","¿‡–Õ" };
		attributeTableModel.addRow(rowData1);
		for (int i = 0; i < 50; i++) {
			Object[] rowData = { (i+1), "dfaf", "13213", "eee", "false" };
			attributeTableModel.addRow(rowData);
		}
		
	}
	
}

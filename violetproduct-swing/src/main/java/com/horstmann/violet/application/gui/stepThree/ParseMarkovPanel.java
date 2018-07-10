package com.horstmann.violet.application.gui.stepThree;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.common.ColorData;

public class ParseMarkovPanel extends JPanel{
	
	private MainFrame mainFrame;
	
	private JPanel statePanel;
	private JPanel transitionPanel;
	
	private JScrollPane stateScrollPane;
	private JPanel stateTablePanel;
	private JTable stateTable;
	private DefaultTableModel stateTableModel;
	
	private JScrollPane transitionScrollPane;
	private JPanel transitionTablePanel;
	private JTable transitionTable;
	private DefaultTableModel transitionTableModel;
	
	public ParseMarkovPanel(MainFrame mainFrame) {
		
		this.mainFrame=mainFrame;
		
		statePanel=new JPanel();
		transitionPanel=new JPanel();
		
		initStatePanel();
		initTransitionPanel();
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(statePanel);
		this.add(transitionPanel);
		layout.setConstraints(statePanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(transitionPanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		this.setBackground(ColorData.white);
		
	}

	private void initStatePanel() {
		
		statePanel.setOpaque(false);
		statePanel.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10),
				BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorData.gray), "×´Ì¬½Úµã",
						TitledBorder.LEFT, TitledBorder.TOP, new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13), ColorData.black)));
		
		stateTablePanel = new JPanel();

		initStateTablePanel();
		
		stateScrollPane=new JScrollPane(stateTablePanel);
		stateScrollPane.setBorder(null);
		
		statePanel.setLayout(new GridLayout());
		statePanel.add(stateScrollPane);
		
	}

	private void initStateTablePanel() {

		final String[] columnNames = { "ÐòºÅ","Ãû³Æ","ID","ÊÇ·ñÎªÖÕÖ¹×´Ì¬","ÀàÐÍ" };
		String[][] tabelValues = {};

		stateTableModel = new DefaultTableModel(tabelValues, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		stateTable = new JTable(stateTableModel);

		stateTable.setSelectionBackground(ColorData.white);
		stateTable.setSelectionForeground(ColorData.black);
		stateTable.setGridColor(ColorData.gray);
		stateTable.setShowGrid(true);
		stateTable.setShowHorizontalLines(true);
		stateTable.setShowVerticalLines(true);
		stateTable.setFillsViewportHeight(true);
		stateTable.setRowHeight(27);
		stateTable.doLayout();
		stateTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		stateTable.setDefaultRenderer(Object.class, renderer);
		
		stateTable.setBackground(ColorData.white);
		stateTable.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, ColorData.gray));

		stateTablePanel.setLayout(new BorderLayout());
		stateTablePanel.add(stateTable, BorderLayout.CENTER);
		stateTablePanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		stateTablePanel.setBackground(ColorData.white);

		Object[] rowData1 = { "ÐòºÅ","Ãû³Æ","ID","ÊÇ·ñÎªÖÕÖ¹×´Ì¬","ÀàÐÍ" };
		stateTableModel.addRow(rowData1);
		for (int i = 0; i < 50; i++) {
			Object[] rowData = { (i+1), "dfaf", "13213", "eee", "false" };
			stateTableModel.addRow(rowData);
		}

	}

	private void initTransitionPanel() {
		transitionPanel.setOpaque(false);
		transitionPanel.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10),
				BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorData.gray), "ÏûÏ¢Ç¨ÒÆ",
						TitledBorder.LEFT, TitledBorder.TOP, new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13), ColorData.black)));
		
		transitionTablePanel = new JPanel();

		initTransitionTablePanel();
		
		transitionScrollPane=new JScrollPane(transitionTablePanel);
		transitionScrollPane.setBorder(null);
		
		transitionPanel.setLayout(new GridLayout());
		transitionPanel.add(transitionScrollPane);
		
	}

	private void initTransitionTablePanel() {

		final String[] columnNames = { "ÐòºÅ","Ãû³Æ","ID","ÊÇ·ñÎªÖÕÖ¹×´Ì¬","ÀàÐÍ" };
		String[][] tabelValues = {};

		transitionTableModel = new DefaultTableModel(tabelValues, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		transitionTable = new JTable(transitionTableModel);

		transitionTable.setSelectionBackground(ColorData.white);
		transitionTable.setSelectionForeground(ColorData.black);
		transitionTable.setGridColor(ColorData.gray);
		transitionTable.setShowGrid(true);
		transitionTable.setShowHorizontalLines(true);
		transitionTable.setShowVerticalLines(true);
		transitionTable.setFillsViewportHeight(true);
		transitionTable.setRowHeight(27);
		transitionTable.doLayout();
		transitionTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		transitionTable.setDefaultRenderer(Object.class, renderer);
		
		transitionTable.setBackground(ColorData.white);
		transitionTable.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, ColorData.gray));

		transitionTablePanel.setLayout(new BorderLayout());
		transitionTablePanel.add(transitionTable, BorderLayout.CENTER);
		transitionTablePanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		transitionTablePanel.setBackground(ColorData.white);

		Object[] rowData1 = { "ÐòºÅ","Ãû³Æ","ID","ÊÇ·ñÎªÖÕÖ¹×´Ì¬","ÀàÐÍ" };
		transitionTableModel.addRow(rowData1);
		for (int i = 0; i < 50; i++) {
			Object[] rowData = { (i+1), "dfaf", "13213", "eee", "false" };
			transitionTableModel.addRow(rowData);
		}

	}
	
	public void dealAndShow() {
		
	}
	
}

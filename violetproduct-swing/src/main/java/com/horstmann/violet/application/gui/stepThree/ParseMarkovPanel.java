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

import com.horstmann.violet.application.ckt.entity.Markov;
import com.horstmann.violet.application.ckt.entity.State;
import com.horstmann.violet.application.ckt.entity.Transition;
import com.horstmann.violet.application.ckt.random.ReadMarkov;
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
				BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorData.gray), "״̬�ڵ�",
						TitledBorder.LEFT, TitledBorder.TOP, new Font("΢���ź�", Font.PLAIN, 13), ColorData.black)));
		
		stateTablePanel = new JPanel();

		initStateTablePanel();
		
		stateScrollPane=new JScrollPane(stateTablePanel);
		stateScrollPane.setBorder(null);
		
		statePanel.setLayout(new GridLayout());
		statePanel.add(stateScrollPane);
		
	}

	private void initStateTablePanel() {

		final String[] columnNames = { "���","����","�Ƿ�Ϊ��ֹ״̬"};
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
		
		stateTable.getColumn("���").setPreferredWidth(50);
		stateTable.getColumn("���").setMinWidth(50);
		stateTable.getColumn("���").setMaxWidth(50);

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		stateTable.setDefaultRenderer(Object.class, renderer);
		
		stateTable.setBackground(ColorData.white);
		stateTable.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, ColorData.gray));

		stateTablePanel.setLayout(new BorderLayout());
		stateTablePanel.add(stateTable, BorderLayout.CENTER);
		stateTablePanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		stateTablePanel.setBackground(ColorData.white);

	}

	private void initTransitionPanel() {
		transitionPanel.setOpaque(false);
		transitionPanel.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10),
				BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorData.gray), "��ϢǨ��",
						TitledBorder.LEFT, TitledBorder.TOP, new Font("΢���ź�", Font.PLAIN, 13), ColorData.black)));
		
		transitionTablePanel = new JPanel();

		initTransitionTablePanel();
		
		transitionScrollPane=new JScrollPane(transitionTablePanel);
		transitionScrollPane.setBorder(null);
		
		transitionPanel.setLayout(new GridLayout());
		transitionPanel.add(transitionScrollPane);
		
	}

	private void initTransitionTablePanel() {

		final String[] columnNames = { "���","����","����","����","Դ״̬����","Ŀ��״̬����","Ԥ�ڽ��" };
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
		
		transitionTable.getColumn("���").setPreferredWidth(50);
		transitionTable.getColumn("���").setMinWidth(50);
		transitionTable.getColumn("���").setMaxWidth(50);
		transitionTable.getColumn("����").setPreferredWidth(50);
		transitionTable.getColumn("����").setMinWidth(50);
		transitionTable.getColumn("����").setMaxWidth(50);
		transitionTable.getColumn("����").setPreferredWidth(60);
		transitionTable.getColumn("����").setMinWidth(60);
		transitionTable.getColumn("����").setMaxWidth(60);
		transitionTable.getColumn("����").setPreferredWidth(50);
		transitionTable.getColumn("����").setMinWidth(50);
		transitionTable.getColumn("Դ״̬����").setPreferredWidth(100);
		transitionTable.getColumn("Դ״̬����").setMinWidth(100);
		transitionTable.getColumn("Դ״̬����").setMaxWidth(100);
		transitionTable.getColumn("Ŀ��״̬����").setPreferredWidth(100);
		transitionTable.getColumn("Ŀ��״̬����").setMinWidth(100);
		transitionTable.getColumn("Ŀ��״̬����").setMaxWidth(100);
		transitionTable.getColumn("Ԥ�ڽ��").setPreferredWidth(100);
		transitionTable.getColumn("Ԥ�ڽ��").setMinWidth(100);
		transitionTable.getColumn("Ԥ�ڽ��").setMaxWidth(100);

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		transitionTable.setDefaultRenderer(Object.class, renderer);
		
		transitionTable.setBackground(ColorData.white);
		transitionTable.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, ColorData.gray));

		transitionTablePanel.setLayout(new BorderLayout());
		transitionTablePanel.add(transitionTable, BorderLayout.CENTER);
		transitionTablePanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		transitionTablePanel.setBackground(ColorData.white);

	}
	
	public void dealAndShow() {
		
		dealData();
		
		showData();
		
	}

	private void showData() {
		
		Markov markov = mainFrame.getStepThreeCenterPanel().getMarkov();
		
		for(int i=stateTableModel.getRowCount()-1;i>=0;i--){
			stateTableModel.removeRow(i);
		}
		
		for(int i=transitionTableModel.getRowCount()-1;i>=0;i--){
			transitionTableModel.removeRow(i);
		}
		
		Object[] rowData10 = { "���","����","�Ƿ�Ϊ��ֹ״̬" };
		stateTableModel.addRow(rowData10);
		for(State state:markov.getStates()){
			Object[] rowData11 = { state.getId(),state.getStateName(),state.isFinalState() };
			stateTableModel.addRow(rowData11);
		}
		
		Object[] rowData20 = { "���","����","����","����","Դ״̬����","Ŀ��״̬����","Ԥ�ڽ��" };
		transitionTableModel.addRow(rowData20);
		for (Transition transition:markov.getTransitions()) {
			Object[] rowData21 = { transition.getId(), transition.getName(), transition.getProbability(), transition.getCondition(),transition.getStartState().getStateName(),transition.getEndState().getStateName(), transition.getExpectResult() };
			transitionTableModel.addRow(rowData21);
		}
		
	}

	private void dealData() {
		String path=mainFrame.getStepThreeCenterPanel().getMarkovPath();

		try {
			Markov markov = ReadMarkov.readMarkov(path);
			mainFrame.getStepThreeCenterPanel().setMarkov(markov);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

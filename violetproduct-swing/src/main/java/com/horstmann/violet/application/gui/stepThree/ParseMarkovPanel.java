package com.horstmann.violet.application.gui.stepThree;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
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
	
	private Callable<Integer> callable;
	private FutureTask<Integer> task;
	private Thread thread;
	private Callable<Integer> processCallable;
	private FutureTask<Integer> processTask;
	private Thread processThread;
	
	private int state;
	
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
				BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorData.gray), "状态节点",
						TitledBorder.LEFT, TitledBorder.TOP, new Font("微软雅黑", Font.PLAIN, 13), ColorData.black)));
		
		stateTablePanel = new JPanel();

		initStateTablePanel();
		
		stateScrollPane=new JScrollPane(stateTablePanel);
		stateScrollPane.setBorder(null);
		
		statePanel.setLayout(new GridLayout());
		statePanel.add(stateScrollPane);
		
	}

	private void initStateTablePanel() {

		final String[] columnNames = { "序号","名称","是否为终止状态"};
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
		
		stateTable.getColumn("序号").setPreferredWidth(50);
		stateTable.getColumn("序号").setMinWidth(50);
		stateTable.getColumn("序号").setMaxWidth(50);

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		stateTable.setDefaultRenderer(Object.class, renderer);
		
		stateTable.setBackground(ColorData.white);
		stateTable.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, ColorData.gray));

//		stateTablePanel.setLayout(new BorderLayout());
//		stateTablePanel.add(stateTable, BorderLayout.CENTER);
		
		JPanel emptyPanel=new JPanel();
		emptyPanel.setOpaque(false);
		
		GridBagLayout layout = new GridBagLayout();
		stateTablePanel.setLayout(layout);
		stateTablePanel.add(stateTable);
		stateTablePanel.add(emptyPanel);
		layout.setConstraints(stateTable, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(emptyPanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		
		stateTablePanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		stateTablePanel.setBackground(ColorData.white);

	}

	private void initTransitionPanel() {
		transitionPanel.setOpaque(false);
		transitionPanel.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10),
				BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorData.gray), "消息迁移",
						TitledBorder.LEFT, TitledBorder.TOP, new Font("微软雅黑", Font.PLAIN, 13), ColorData.black)));
		
		transitionTablePanel = new JPanel();

		initTransitionTablePanel();
		
		transitionScrollPane=new JScrollPane(transitionTablePanel);
		transitionScrollPane.setBorder(null);
		
		transitionPanel.setLayout(new GridLayout());
		transitionPanel.add(transitionScrollPane);
		
	}

	private void initTransitionTablePanel() {

		final String[] columnNames = { "序号","名称","概率","条件","源状态名称","目标状态名称","预期结果" };
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
		
		transitionTable.getColumn("序号").setPreferredWidth(50);
		transitionTable.getColumn("序号").setMinWidth(50);
		transitionTable.getColumn("序号").setMaxWidth(50);
		transitionTable.getColumn("名称").setPreferredWidth(50);
		transitionTable.getColumn("名称").setMinWidth(50);
		transitionTable.getColumn("名称").setMaxWidth(50);
		transitionTable.getColumn("概率").setPreferredWidth(60);
		transitionTable.getColumn("概率").setMinWidth(60);
		transitionTable.getColumn("概率").setMaxWidth(60);
		transitionTable.getColumn("条件").setPreferredWidth(50);
		transitionTable.getColumn("条件").setMinWidth(50);
		transitionTable.getColumn("源状态名称").setPreferredWidth(100);
		transitionTable.getColumn("源状态名称").setMinWidth(100);
		transitionTable.getColumn("源状态名称").setMaxWidth(100);
		transitionTable.getColumn("目标状态名称").setPreferredWidth(100);
		transitionTable.getColumn("目标状态名称").setMinWidth(100);
		transitionTable.getColumn("目标状态名称").setMaxWidth(100);
		transitionTable.getColumn("预期结果").setPreferredWidth(100);
		transitionTable.getColumn("预期结果").setMinWidth(100);
		transitionTable.getColumn("预期结果").setMaxWidth(100);

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		transitionTable.setDefaultRenderer(Object.class, renderer);
		
		transitionTable.setBackground(ColorData.white);
		transitionTable.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, ColorData.gray));

//		transitionTablePanel.setLayout(new BorderLayout());
//		transitionTablePanel.add(transitionTable, BorderLayout.CENTER);
		
		JPanel emptyPanel=new JPanel();
		emptyPanel.setOpaque(false);
		
		GridBagLayout layout = new GridBagLayout();
		transitionTablePanel.setLayout(layout);
		transitionTablePanel.add(transitionTable);
		transitionTablePanel.add(emptyPanel);
		layout.setConstraints(transitionTable, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(emptyPanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		transitionTablePanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		transitionTablePanel.setBackground(ColorData.white);

	}
	
	public void dealAndShow() {
		
		processCallable=new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				
				JProgressBar progressBar=mainFrame.getStepThreeCenterPanel().getProgressPanel().getProgressBar();
				progressBar.setValue(0);
				while(progressBar.getValue()<30){
					
					progressBar.setValue(progressBar.getValue()+1);
					if(state==0){
						Thread.sleep(100);
					}
					else{
						Thread.sleep(10);
					}
					
				}
				
				return 1;
			}
		};
		processTask=new FutureTask<>(processCallable);
		processThread=new Thread(processTask);
		
		callable=new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {

				state=0;
				
				dealData();
				
				state=1;
				
				while(processTask.get() == null){
					Thread.sleep(10);
				}
				
				showData();
				
				mainFrame.getStepThreeCenterPanel().getProgressPanel().getProgressBar().setValue(30);
				mainFrame.getStepThreeCenterPanel().setStep(2);
				
				return 1;
			}

		};
		task=new FutureTask<>(callable);
		thread=new Thread(task);
		
		processThread.start();
		thread.start();
		
		
	}

	private void showData() {
		
		Markov markov = mainFrame.getStepThreeCenterPanel().getMarkov();
		
		for(int i=stateTableModel.getRowCount()-1;i>=0;i--){
			stateTableModel.removeRow(i);
		}
		
		for(int i=transitionTableModel.getRowCount()-1;i>=0;i--){
			transitionTableModel.removeRow(i);
		}
		
		Object[] rowData10 = { "序号","名称","是否为终止状态" };
		stateTableModel.addRow(rowData10);
		for(State state:markov.getStates()){
			Object[] rowData11 = { state.getId(),state.getStateName(),state.isFinalState() };
			stateTableModel.addRow(rowData11);
		}
		
		mainFrame.ChangeRepaint(this);
		
		Object[] rowData20 = { "序号","名称","概率","条件","源状态名称","目标状态名称","预期结果" };
		transitionTableModel.addRow(rowData20);
		for (Transition transition:markov.getTransitions()) {
			Object[] rowData21 = { transition.getId(), transition.getName(), transition.getProbability(), transition.getCondition(),transition.getStartState().getStateName(),transition.getEndState().getStateName(), transition.getExpectResult() };
			transitionTableModel.addRow(rowData21);
		}
		
		mainFrame.ChangeRepaint(this);
		
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

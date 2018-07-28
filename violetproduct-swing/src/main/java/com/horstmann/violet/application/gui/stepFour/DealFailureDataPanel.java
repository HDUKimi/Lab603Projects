package com.horstmann.violet.application.gui.stepFour;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.horstmann.violet.application.ckt.entity.Route;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.common.ColorData;
import com.horstmann.violet.application.gui.common.FileUtil;

public class DealFailureDataPanel extends JPanel {

	private MainFrame mainFrame;

	private JScrollPane scrollPane;
	private JPanel resultPanel;

	private JPanel failureDataPanel;

	private JPanel failureDataTablePanel;
	private JTable failureDataTable;
	private DefaultTableModel failureDataTableModel;

	private JPanel labelPanel;
	private JLabel label;

	private Callable<Integer> callable;
	private FutureTask<Integer> task;
	private Thread thread;
	private Callable<Integer> processCallable;
	private FutureTask<Integer> processTask;
	private Thread processThread;

	private int state;

	private List<Integer> failureDataList = new ArrayList<>();

	public DealFailureDataPanel(MainFrame mainFrame) {

		this.mainFrame = mainFrame;

		failureDataPanel = new JPanel();
		labelPanel = new JPanel();

		initLabelPanel();
		initFailureDataPanel();

		resultPanel = new JPanel();
		resultPanel.setBackground(ColorData.white);

		GridBagLayout layout = new GridBagLayout();
		resultPanel.setLayout(layout);
		resultPanel.add(labelPanel);
		resultPanel.add(failureDataPanel);
		layout.setConstraints(labelPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
		layout.setConstraints(failureDataPanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));

		resultPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		scrollPane = new JScrollPane(resultPanel);
		scrollPane.setBorder(null);

		this.setLayout(new GridLayout());
		this.add(scrollPane);

		this.setBackground(ColorData.white);

	}

	private void initLabelPanel() {

		label = new JLabel();
		label.setText("<html><body><p>根据测试用例执行结果，计算失效数据</p></body></html>");
		label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

		labelPanel.setLayout(new GridLayout());
		labelPanel.add(label);
		labelPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		labelPanel.setOpaque(false);

	}

	private void initFailureDataPanel() {

		failureDataTablePanel = new JPanel();

		initFailureDataTablePanel();

		failureDataPanel.setLayout(new GridLayout());
		failureDataPanel.add(failureDataTablePanel);

		failureDataPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		failureDataPanel.setBackground(ColorData.white);

	}

	private void initFailureDataTablePanel() {

		final String[] columnNames = { "", "", "" };
		String[][] tabelValues = {};

		failureDataTableModel = new DefaultTableModel(tabelValues, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		failureDataTable = new JTable(failureDataTableModel);

		failureDataTable.setSelectionBackground(ColorData.white);
		failureDataTable.setSelectionForeground(ColorData.black);
		failureDataTable.setGridColor(ColorData.gray);
		failureDataTable.setShowGrid(true);
		failureDataTable.setShowHorizontalLines(true);
		failureDataTable.setShowVerticalLines(true);
		failureDataTable.setFillsViewportHeight(true);
		failureDataTable.setRowHeight(27);
		failureDataTable.doLayout();
		failureDataTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		failureDataTable.setDefaultRenderer(Object.class, renderer);

		failureDataTable.setBackground(ColorData.white);
		failureDataTable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorData.gray));

		failureDataTablePanel.setLayout(new BorderLayout());
		failureDataTablePanel.add(failureDataTable, BorderLayout.NORTH);
		failureDataTablePanel.setOpaque(false);


	}

	public void dealAndShow() {

		processCallable = new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {

				JProgressBar progressBar = mainFrame.getStepFourCenterPanel().getProgressPanel().getProgressBar();
				
				progressBar.setValue(0);
				
				while (progressBar.getValue() < 100) {

					progressBar.setValue(progressBar.getValue() + 1);
					if (state == 0) {
						Thread.sleep(100);
					} else {
						Thread.sleep(10);
					}

				}

				return 1;
			}
		};
		processTask = new FutureTask<>(processCallable);
		processThread = new Thread(processTask);

		callable = new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {

				try {
					state = 0;

					dealData();
					
					Thread.sleep(2000);

					state = 1;

					while (processTask.get() == null) {
						Thread.sleep(10);
					}

					showData();

					mainFrame.getStepFourCenterPanel().getProgressPanel().getProgressBar().setValue(100);

				} catch (Exception e) {
					e.printStackTrace();
				}

				return 1;
			}

		};
		task = new FutureTask<>(callable);
		thread = new Thread(task);

		processThread.start();
		thread.start();

	}

	protected void dealData() {

		List<Route> routes = mainFrame.getStepFourCenterPanel().getRoutes();

		failureDataList = new ArrayList<>();

		for (Route route : routes) {
			if (!route.getRouteResult().equals(route.getTestResult())) {
				failureDataList.add(route.getId());
			}
		}
		
		String name=mainFrame.getStepFourCenterPanel().getTestCaseName().replace("testcase", "failure");
		try {
			FileUtil.WriteFailureData(failureDataList,name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void showData() {

		while (failureDataTableModel.getRowCount() > 0) {
			failureDataTableModel.removeRow(failureDataTableModel.getRowCount() - 1);
		}

		Object[] rowData1 = { "序号", "失效间隔时间", "累积失效发生时刻" };
		failureDataTableModel.addRow(rowData1);

		int index = 1;
		int pre = 0;
		for (Integer time : failureDataList) {
			Object[] rowData = { index++, time - pre, time };
			pre = time;
			failureDataTableModel.addRow(rowData);
		}

		mainFrame.ChangeRepaint(this);

	}

}

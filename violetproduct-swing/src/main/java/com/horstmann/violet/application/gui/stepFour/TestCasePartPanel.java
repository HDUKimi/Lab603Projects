package com.horstmann.violet.application.gui.stepFour;

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

import com.horstmann.violet.application.ckt.entity.Route;
import com.horstmann.violet.application.ckt.entity.Transition;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.common.ColorData;
import com.horstmann.violet.application.gui.common.DottedLinePanel;

public class TestCasePartPanel extends JPanel {

	private Route route;

	private JPanel titlePanel;
	private JPanel linePanel;
	private JPanel attributePanel;

	private JLabel titleLabel;
	private JLabel iconLabel;
	private ImageIcon upImageIcon;
	private ImageIcon downImageIcon;

	private DottedLinePanel dottedLinePanel;

	private JPanel attributeTablePanel;
	private JTable attributeTable;
	private DefaultTableModel attributeTableModel;

	private int isShow = 0;

	public TestCasePartPanel(Route route) {

		this.route = route;

		titlePanel = new JPanel();
		linePanel = new JPanel();
		attributePanel = new JPanel();

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

		showData();

	}

	private void showData() {

		String text = "测试用例" + route.getId() + "     预期结果: " + route.getRouteResult();
		titleLabel.setText(text);

		Object[] rowData1 = { "名称", "条件", "预期结果" };
		attributeTableModel.addRow(rowData1);
		for (Transition transition : route.getTransitionList()) {
			Object[] rowData = { transition.getName(), transition.getResultOfCondition(), transition.getExpectResult() };
			attributeTableModel.addRow(rowData);
		}
		
	}

	private void initTitlePanel() {

		titleLabel = new JLabel();

		titleLabel.setFont(new Font("微软雅黑", Font.PLAIN, 13));

		initIconLabel();

		titlePanel.setLayout(new BorderLayout());
		titlePanel.add(titleLabel, BorderLayout.WEST);
		titlePanel.add(iconLabel, BorderLayout.EAST);

		titlePanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

		titlePanel.setBackground(ColorData.white);

	}

	private void initIconLabel() {

		upImageIcon = new ImageIcon(this.getClass().getResource("up.png"));
		upImageIcon.setImage(upImageIcon.getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));

		downImageIcon = new ImageIcon(this.getClass().getResource("down.png"));
		downImageIcon.setImage(downImageIcon.getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));

		iconLabel = new JLabel();
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
				if (isShow == 0) {
					linePanel.setVisible(true);
					attributePanel.setVisible(true);
					iconLabel.setIcon(upImageIcon);
					isShow = 1;
				} else {
					linePanel.setVisible(false);
					attributePanel.setVisible(false);
					iconLabel.setIcon(downImageIcon);
					isShow = 0;
				}
			}
		});

	}

	private void initLinePanel() {

		dottedLinePanel = new DottedLinePanel();

		linePanel.setLayout(new GridLayout());
		linePanel.add(dottedLinePanel);
		linePanel.setBackground(ColorData.white);

	}

	private void initAttributePanel() {

		attributeTablePanel = new JPanel();

		initAttributeTablePanel();

		attributePanel.setLayout(new GridLayout());
		attributePanel.add(attributeTablePanel);

	}

	private void initAttributeTablePanel() {

		final String[] columnNames = { "名称", "条件", "预期结果" };
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
		
		attributeTable.getColumn("名称").setPreferredWidth(50);
		attributeTable.getColumn("名称").setMinWidth(50);
		attributeTable.getColumn("名称").setMaxWidth(50);
		attributeTable.getColumn("条件").setPreferredWidth(50);
		attributeTable.getColumn("条件").setMinWidth(50);
		attributeTable.getColumn("预期结果").setPreferredWidth(100);
		attributeTable.getColumn("预期结果").setMinWidth(100);
		attributeTable.getColumn("预期结果").setMaxWidth(100);

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		attributeTable.setDefaultRenderer(Object.class, renderer);

		attributeTable.setBackground(ColorData.white);
		attributeTable.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, ColorData.gray));

		attributeTablePanel.setLayout(new BorderLayout());
		attributeTablePanel.add(attributeTable, BorderLayout.CENTER);
		attributeTablePanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
		attributeTablePanel.setBackground(ColorData.white);

	}

}

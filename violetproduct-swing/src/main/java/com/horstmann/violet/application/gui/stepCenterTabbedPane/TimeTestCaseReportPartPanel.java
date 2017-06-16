package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.TestCase;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.myProcess;

public class TimeTestCaseReportPartPanel extends JPanel {

	private JPanel titlepanel;
	private JPanel linepanel;
	private JPanel attributepanel;
	private JPanel limitpanel;

	private JPanel titlelabelpanel;
	private JCheckBox toolcheckbox;
	private JLabel iconlabel;
	private JLabel titlelabel;
	private JButton toolbutton;

	private JLabel linelabel;

	private JTable attributetable;
	private DefaultTableModel attributetablemodel;
	
	private JTable limittable;
	private DefaultTableModel limittablemodel;
	
	private TestCase testcase;
	private List<String> limit;

	public TimeTestCaseReportPartPanel(TestCase testcase) {

		this.testcase=testcase;
		this.limit=testcase.getLimit();
		
		init();

		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		this.setBackground(new Color(255, 255, 255));

	}

	private void init() {
		// TODO Auto-generated method stub

		titlepanel = new JPanel();
		linepanel = new JPanel();
		attributepanel = new JPanel();
		limitpanel=new JPanel();

		titlelabelpanel = new JPanel();
		iconlabel = new JLabel();
		titlelabel = new JLabel();
		toolbutton = new JButton();
		toolcheckbox=new JCheckBox();

		linelabel = new JLabel();

		initTitlePanel();

		initLinePanel();

		initAttributePanel();
		
		initLimitPanel();

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(titlepanel);
		this.add(linepanel);
		this.add(attributepanel);
		this.add(limitpanel);

	}

	private void initTitlePanel() {
		// TODO Auto-generated method stub

		String absolutePath = System.getProperty("user.dir");
		String path = absolutePath + "\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "tick.png");
		icon1.setImage(icon1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
		ImageIcon icon3 = new ImageIcon(path + "dropdown1.png");
		icon3.setImage(icon3.getImage().getScaledInstance(11, 11, Image.SCALE_DEFAULT));

		String title = "";
		title+="测试用例ID:"+testcase.getTestCaseID()+"     ";
//		if(testcase.getState()!=null){
//			title+=testcase.getState()+"     ";
//		}
//		else{
//			title+="测试耗时:     ";
//		}
		title+="执行结果:";
//		if(testcase.getResult()!=null){
//			title+=testcase.getResult().substring(0, testcase.getResult().indexOf("耗时"));
//		}
		
		titlelabel.setText(title);
		titlelabel.setFont(new Font("微软雅黑", Font.BOLD, 12));
		titlelabel.setForeground(new Color(60,0,255));
		titlelabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

//		iconlabel.setIcon(icon1);
		iconlabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

		toolcheckbox.setSelected(false);
		toolcheckbox.setOpaque(false);
		
		
		titlelabelpanel.setLayout(new FlowLayout(0, 0, FlowLayout.LEFT));
		titlelabelpanel.add(toolcheckbox);
		titlelabelpanel.add(titlelabel);
		titlelabelpanel.add(iconlabel);
		titlelabelpanel.setOpaque(false);

		toolbutton.setIcon(icon3);
		toolbutton.setFocusable(false);
		toolbutton.setContentAreaFilled(false);
		toolbutton.setBorderPainted(false);
		toolbutton.addMouseListener(new ButtonMouseListener());
		toolbutton.setPreferredSize(new Dimension(21, 21));
		toolbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (attributepanel.isVisible()) {
					attributepanel.setVisible(false);
					limitpanel.setVisible(false);
				} else {
					attributepanel.setVisible(true);
					limitpanel.setVisible(true);
				}
			}
		});

		titlepanel.setLayout(new BorderLayout());
		titlepanel.add(titlelabelpanel, BorderLayout.WEST);
		titlepanel.add(toolbutton, BorderLayout.EAST);
		// titlepanel.setPreferredSize(new Dimension(100, 30));

		titlepanel.setOpaque(false);

	}

	private void initLinePanel() {
		// TODO Auto-generated method stub

		linelabel.setText(
				"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
		linelabel.setPreferredSize(new Dimension(100, 3));
		linelabel.setForeground(new Color(223, 204, 221));

		linepanel.setLayout(new GridLayout());
		linepanel.add(linelabel);
		linepanel.setOpaque(false);

	}

	private void initAttributePanel() {
		// TODO Auto-generated method stub

		String[] columnNames = { "激励ID", "激励名称", "激励参数", "激励状态", "激励执行情况" };
		String[][] tabelValues = {};

		attributetablemodel = new DefaultTableModel(tabelValues, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		attributetable = new JTable(attributetablemodel);
		
		attributetable.setName("TimeTestCaseReportPartPanel");

		attributetable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		attributetable.setSelectionBackground(new Color(250, 248, 236));
		attributetable.setGridColor(new Color(224, 226, 220));
		attributetable.setShowGrid(false);
		attributetable.setShowHorizontalLines(true);
		attributetable.setShowVerticalLines(false);
		attributetable.setFillsViewportHeight(true);
		attributetable.setRowHeight(27);
		attributetable.doLayout();
		attributetable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		attributetable.getColumnModel().getColumn(0).setCellRenderer(new MyAllLabelRenderer());
		attributetable.getColumnModel().getColumn(1).setCellRenderer(new MyAllLabelRenderer());
		attributetable.getColumnModel().getColumn(2).setCellRenderer(new MyAllLabelRenderer());
		attributetable.getColumnModel().getColumn(3).setCellRenderer(new MyAllLabelRenderer());
		attributetable.getColumnModel().getColumn(4).setCellRenderer(new MyAllLabelRenderer());

		attributetable.getColumn("激励ID").setPreferredWidth(50);
		attributetable.getColumn("激励ID").setMinWidth(50);
		attributetable.getColumn("激励ID").setMaxWidth(50);
		attributetable.getColumn("激励名称").setPreferredWidth(100);
		attributetable.getColumn("激励名称").setMinWidth(100);
		attributetable.getColumn("激励参数").setPreferredWidth(500);
		attributetable.getColumn("激励参数").setMinWidth(500);
		attributetable.getColumn("激励状态").setPreferredWidth(60);
		attributetable.getColumn("激励状态").setMinWidth(60);
		attributetable.getColumn("激励执行情况").setPreferredWidth(60);
		attributetable.getColumn("激励执行情况").setMinWidth(60);

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setBackground(new Color(71, 80, 93));
		renderer.setForeground(new Color(255, 255, 255));
		renderer.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		attributetable.getTableHeader().setDefaultRenderer(renderer);

		attributetable.getTableHeader().setPreferredSize(new Dimension(100, 27));

		attributetable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(224, 225, 220)));

//		attributetable.setBackground(new Color(255, 255, 255));

		attributepanel.setLayout(new BorderLayout());
		attributepanel.add(attributetable.getTableHeader(), BorderLayout.NORTH);
		attributepanel.add(attributetable, BorderLayout.CENTER);

		attributepanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		attributepanel.setOpaque(false);

		for(myProcess p:testcase.getProcessList()){
			
			Object[] rowData={p.getProcessID(),p.getProcessName(),p.getProcessParam(),p.getProcessStatus(),""};
			attributetablemodel.addRow(rowData);
			
		}

	}
	
	private void initLimitPanel() {
		// TODO Auto-generated method stub
		
		String[] columnNames = { "不等式组", "详细属性", "结果" };
		String[][] tabelValues = {};

		limittablemodel = new DefaultTableModel(tabelValues, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		limittable = new JTable(limittablemodel);
		
		limittable.setName("TimeTestCaseReportLimitPartPanel");

		limittable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		limittable.setSelectionBackground(new Color(250, 248, 236));
		limittable.setGridColor(new Color(224, 226, 220));
		limittable.setShowGrid(false);
		limittable.setShowHorizontalLines(true);
		limittable.setShowVerticalLines(false);
		limittable.setFillsViewportHeight(true);
		limittable.setRowHeight(27);
		limittable.doLayout();
		limittable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		limittable.getColumnModel().getColumn(0).setCellRenderer(new MyAllLabelRenderer());
		limittable.getColumnModel().getColumn(1).setCellRenderer(new MyAllLabelRenderer());
		limittable.getColumnModel().getColumn(2).setCellRenderer(new MyLabelRenderer());
//		limittable.getColumnModel().getColumn(3).setCellRenderer(new MyAllLabelRenderer());
//		limittable.getColumnModel().getColumn(4).setCellRenderer(new MyAllLabelRenderer());
//		limittable.getColumnModel().getColumn(5).setCellRenderer(new MyAllLabelRenderer());
//		limittable.getColumnModel().getColumn(6).setCellRenderer(new MyAllLabelRenderer());

		limittable.getColumn("不等式组").setPreferredWidth(150);
		limittable.getColumn("不等式组").setMinWidth(150);
		limittable.getColumn("不等式组").setMaxWidth(150);
		limittable.getColumn("详细属性").setPreferredWidth(600);
		limittable.getColumn("详细属性").setMinWidth(600);
		limittable.getColumn("结果").setPreferredWidth(50);
		limittable.getColumn("结果").setMinWidth(50);
		limittable.getColumn("结果").setMaxWidth(50);

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setBackground(new Color(71, 80, 93));
		renderer.setForeground(new Color(255, 255, 255));
		renderer.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		limittable.getTableHeader().setDefaultRenderer(renderer);

		limittable.getTableHeader().setPreferredSize(new Dimension(100, 27));

		limittable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(224, 225, 220)));

//		limittable.setBackground(new Color(255, 255, 255));

		limitpanel.setLayout(new BorderLayout());
		limitpanel.add(limittable.getTableHeader(), BorderLayout.NORTH);
		limitpanel.add(limittable, BorderLayout.CENTER);

		limitpanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		limitpanel.setOpaque(false);

//		int size=limit.size();
//		int index=0;
//		for(int i=0;i<size/7;i++){
//			Object[] rowData={limit.get(index),limit.get(index+1),limit.get(index+2),limit.get(index+3),limit.get(index+4),limit.get(index+5),limit.get(index+6)};
//			limittablemodel.addRow(rowData);
//			index+=7;
//		}
//		Object[] rowData={"","","","","","",""};
//		for(int obindex=0;index<size;){
//			rowData[obindex++]=limit.get(index++);
//		}
//		limittablemodel.addRow(rowData);
		
//		for(String l:limit){
//			Random rand=new Random();
//			int index=rand.nextInt(2);
//			Object[] rowData={l,"t1=1,t2=2,t1+t2=3",index};
//			limittablemodel.addRow(rowData);
//		}
		
		for(String l:limit){
			Object[] rowData={l,"",-1};
			limittablemodel.addRow(rowData);
		}
		
	}

	public JPanel getAttributepanel() {
		return attributepanel;
	}

	public JPanel getLimitpanel() {
		return limitpanel;
	}

	public JButton getToolbutton() {
		return toolbutton;
	}

	public JCheckBox getToolcheckbox() {
		return toolcheckbox;
	}

	public void setToolcheckbox(JCheckBox toolcheckbox) {
		this.toolcheckbox = toolcheckbox;
	}

	public TestCase getTestcase() {
		return testcase;
	}

	public JLabel getIconlabel() {
		return iconlabel;
	}

	public JLabel getTitlelabel() {
		return titlelabel;
	}

	public JTable getAttributetable() {
		return attributetable;
	}

	public DefaultTableModel getAttributetablemodel() {
		return attributetablemodel;
	}

	public void setAttributetable(JTable attributetable) {
		this.attributetable = attributetable;
	}

	public void setAttributetablemodel(DefaultTableModel attributetablemodel) {
		this.attributetablemodel = attributetablemodel;
	}

	public JLabel getLinelabel() {
		return linelabel;
	}

	public JTable getLimittable() {
		return limittable;
	}

	public DefaultTableModel getLimittablemodel() {
		return limittablemodel;
	}

	
}


package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.horstmann.violet.application.consolepart.LocationTransitionLabelRenderer;
import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.util.chenzuo.Bean.TestCase;
import com.horstmann.violet.application.gui.util.chenzuo.Bean.myProcess;

public class PerformanceTestCaseReportPartPanel extends JPanel {

	private MainFrame mainFrame;
	
	private JPanel titlepanel;
	private JPanel linepanel;
	private JPanel attributepanel;

	private JPanel titlelabelpanel;
	private JCheckBox toolcheckbox;
	private JLabel iconlabel;
	private JLabel titlelabel;
	private JButton toolbutton;
	
	private JTable titletable;
	private DefaultTableModel titletablemodel;

	private JLabel linelabel;

	private JTable attributetable;
	private DefaultTableModel attributetablemodel;
	
	private TestCase testcase;

	public PerformanceTestCaseReportPartPanel(MainFrame mainFrame,TestCase testcase) {

		this.mainFrame=mainFrame;
		this.testcase=testcase;
		
		init();

		this.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

		this.setBackground(new Color(255, 255, 255));

	}

	private void init() {
		// TODO Auto-generated method stub

		titlepanel = new JPanel();
		linepanel = new JPanel();
		attributepanel = new JPanel();

		titlelabelpanel = new JPanel();
		iconlabel = new JLabel();
		titlelabel = new JLabel();
		toolbutton = new JButton();
		toolcheckbox=new JCheckBox();

		linelabel = new JLabel();
		
		attributepanel.setVisible(false);

		initTitlePanel();

//		initLinePanel();

		initAttributePanel();

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(titlepanel);
//		this.add(linepanel);
		this.add(attributepanel);

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
			title+="测试耗时:     ";
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
				} else {
					attributepanel.setVisible(true);
				}
			}
		});
		
		initTitleTable();

		titlepanel.setLayout(new BorderLayout());
//		titlepanel.add(titlelabelpanel, BorderLayout.WEST);
		titlepanel.add(titletable, BorderLayout.CENTER);
		titlepanel.add(toolbutton, BorderLayout.EAST);
		// titlepanel.setPreferredSize(new Dimension(100, 30));

		titlepanel.setOpaque(false);
		titlepanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(224, 225, 220)));

	}

	private void initTitleTable() {
		// TODO Auto-generated method stub
		
		String[] columnNames = { "测试ID", "风速", "起飞高度", "剩余电量", "所用时间"};
		String[][] tabelValues = {};

		titletablemodel = new DefaultTableModel(tabelValues, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		titletable = new JTable(titletablemodel);
		
		titletable.setName("PerformanceTestCaseReportPartPanelTitleTable");

		titletable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		titletable.setSelectionBackground(new Color(250, 248, 236));
		titletable.setSelectionBackground(new Color(255, 255, 255));
		titletable.setSelectionForeground(new Color(115, 110, 102));
		titletable.setGridColor(new Color(224, 226, 220));
		titletable.setShowGrid(false);
		titletable.setShowHorizontalLines(false);
		titletable.setShowVerticalLines(false);
		titletable.setFillsViewportHeight(true);
		titletable.setRowHeight(22);
		titletable.doLayout();
		titletable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		titletable.getColumnModel().getColumn(0).setCellRenderer(new MyAllLabelRenderer());
		titletable.getColumnModel().getColumn(1).setCellRenderer(new MyAllLabelRenderer());
		titletable.getColumnModel().getColumn(2).setCellRenderer(new MyAllLabelRenderer());
		titletable.getColumnModel().getColumn(3).setCellRenderer(new MyAllLabelRenderer());
		titletable.getColumnModel().getColumn(4).setCellRenderer(new MyAllLabelRenderer());

		titletable.getColumn("测试ID").setPreferredWidth(50);
		titletable.getColumn("测试ID").setMinWidth(50);
		titletable.getColumn("测试ID").setMaxWidth(50);
		titletable.getColumn("风速").setPreferredWidth(100);
		titletable.getColumn("风速").setMinWidth(100);
		titletable.getColumn("起飞高度").setPreferredWidth(100);
		titletable.getColumn("起飞高度").setMinWidth(100);
		titletable.getColumn("剩余电量").setPreferredWidth(100);
		titletable.getColumn("剩余电量").setMinWidth(100);
		titletable.getColumn("所用时间").setPreferredWidth(100);
		titletable.getColumn("所用时间").setMinWidth(100);

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setBackground(new Color(71, 80, 93));
		renderer.setForeground(new Color(255, 255, 255));
		renderer.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		titletable.getTableHeader().setDefaultRenderer(renderer);

		titletable.getTableHeader().setPreferredSize(new Dimension(100, 27));


//		titletable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(224, 225, 220)));
		
		Object[] rowData={testcase.getTestCaseID(),testcase.getResult().getWind_speed(),testcase.getResult().getTakeoff_alt(),"",""};
		titletablemodel.addRow(rowData);

		
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

		final String[] columnNames = { "激励ID", "激励名称", "激励参数", "激励状态", "激励执行情况" };
		String[][] tabelValues = {};

		attributetablemodel = new DefaultTableModel(tabelValues, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		attributetable = new JTable(attributetablemodel);
		
		attributetable.setName("PerformanceTestCaseReportPartPanel");

		attributetable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		attributetable.setSelectionBackground(new Color(250, 248, 236));
		attributetable.setGridColor(new Color(224, 226, 220));
		attributetable.setShowGrid(false);
		attributetable.setShowHorizontalLines(true);
		attributetable.setShowVerticalLines(false);
		attributetable.setFillsViewportHeight(true);
		attributetable.setRowHeight(19);
		attributetable.doLayout();
		attributetable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
//		Dimension intercellspacing=attributetable.getIntercellSpacing();
//		intercellspacing.setSize(0, 1);//列间距，行间距
//		attributetable.setIntercellSpacing(intercellspacing);

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
//		renderer.setBackground(new Color(71, 80, 93));
//		renderer.setForeground(new Color(255, 255, 255));
		renderer.setForeground(new Color(71, 80, 93));
		renderer.setBackground(new Color(250, 248, 236));
		renderer.setFont(new Font("微软雅黑", Font.BOLD, 13));
		renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		attributetable.getTableHeader().setDefaultRenderer(renderer);

		attributetable.getTableHeader().setPreferredSize(new Dimension(100, 23));
		
		attributetable.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount()==2){
					
					int stepindex=mainFrame.getStepindex();
					
					if(stepindex==4){
						mainFrame.getTestCaseInstantiationResultPanel().getOnenamelabel().setText(titlelabel.getText().split(" ")[0]);
						
						JTable jt=mainFrame.getTestCaseInstantiationResultPanel().getTestcaseinfortable();
						DefaultTableModel dtm=mainFrame.getTestCaseInstantiationResultPanel().getTestcaseinfortablemodel();
						
						int index=attributetable.getSelectedRow();
						
						final int[] columnindex=new int[columnNames.length];
						int k=0;
						int count=0;
						
						List<String> rowDataList=new ArrayList<String>();
						
						for(int i=0;i<columnNames.length;i++){
							rowDataList.add("+-+"+columnNames[i]+":");
							columnindex[k++]=count++;
							
							String str=attributetablemodel.getValueAt(index, i)+"";
							String[] strdata=str.split(",|--");
							
							for(String s:strdata){
								rowDataList.add(s);
								count++;
							}
							
						}
						
						while(dtm.getRowCount()>0){
							dtm.removeRow(dtm.getRowCount()-1);
						}
						
						for(String s:rowDataList){
							Object[] rowData={s};
							dtm.addRow(rowData);
						}
						
						dtm.fireTableDataChanged();
						
						mainFrame.getTestCaseInstantiationResultPanel().getTestcaselabeltab1().doClick();
						
					}
					else if(stepindex==5){
						mainFrame.getTestCaseConfirmResultPanel().getOnenamelabel().setText(titlelabel.getText().split(" ")[0]);
						
						JTable jt=mainFrame.getTestCaseConfirmResultPanel().getTestcaseinfortable();
						DefaultTableModel dtm=mainFrame.getTestCaseConfirmResultPanel().getTestcaseinfortablemodel();
						
						int index=attributetable.getSelectedRow();
						
						final int[] columnindex=new int[columnNames.length];
						int k=0;
						int count=0;
						
						List<String> rowDataList=new ArrayList<String>();
						
						for(int i=0;i<columnNames.length;i++){
							rowDataList.add("+-+"+columnNames[i]+":");
							columnindex[k++]=count++;
							
							String str=attributetablemodel.getValueAt(index, i)+"";
							String[] strdata=str.split(",|--");
							
							for(String s:strdata){
								rowDataList.add(s);
								count++;
							}
							
						}
						
						while(dtm.getRowCount()>0){
							dtm.removeRow(dtm.getRowCount()-1);
						}
						
						for(String s:rowDataList){
							Object[] rowData={s};
							dtm.addRow(rowData);
						}
						
						dtm.fireTableDataChanged();
						
						mainFrame.getTestCaseConfirmResultPanel().getTestcaselabeltab1().doClick();
						
					}
					
				}
			}

		});

//		DefaultTableCellRenderer renderer1 = new DefaultTableCellRenderer(){
//
//			@Override
//			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
//					boolean hasFocus, int row, int column) {
//				// TODO Auto-generated method stub
//				
//				setForeground(new Color(115, 110, 102));
//				setBackground(new Color(255, 255, 255));
//				setFont(new Font("微软雅黑", Font.PLAIN, 12));
//				setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
//				
//				if(value.toString().equals("false")){
//					setForeground(Color.RED);
//					setBackground(Color.RED);
//					
////					table.getR
//				}
//				
//				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//			}
//			
//		};
		
//		DefaultTableCellRenderer renderer1 = new DefaultTableCellRenderer();
//		renderer1.setForeground(new Color(115, 110, 102));
//		renderer1.setBackground(new Color(255, 255, 255));
//		renderer1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
//		renderer1.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
////		renderer1.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));
//		attributetable.setDefaultRenderer(Object.class, renderer1);
		
//		for (int i = 0; i < attributetable.getColumnCount(); i++) {  
//			attributetable.getColumn(attributetable.getColumnName(i)).setCellRenderer(renderer1);  
//        }

		attributetable.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(224, 225, 220)));

//		attributetable.setBackground(new Color(255, 255, 255));

		// attributepanel.setLayout(new GridLayout());
		// attributepanel.add(attributetable);

		attributepanel.setLayout(new BorderLayout());
		attributepanel.add(attributetable.getTableHeader(), BorderLayout.NORTH);
		attributepanel.add(attributetable, BorderLayout.CENTER);

		attributepanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		attributepanel.setOpaque(false);

		for(myProcess p:testcase.getProcessList()){
			
			Object[] rowData={p.getProcessID(),p.getProcessName(),p.getProcessParam(),"",""};
			attributetablemodel.addRow(rowData);
			
		}

	}

	public JPanel getAttributepanel() {
		return attributepanel;
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

	public JTable getTitletable() {
		return titletable;
	}

	public DefaultTableModel getTitletablemodel() {
		return titletablemodel;
	}

	
	
	
}

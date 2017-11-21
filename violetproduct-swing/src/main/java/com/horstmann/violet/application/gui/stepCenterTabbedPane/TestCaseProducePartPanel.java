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
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.util.chenzuo.Bean.Pair;
import com.horstmann.violet.application.gui.util.ckt.handle.Automatic;
import com.horstmann.violet.application.gui.util.ckt.handle.Get_inequality__1;
import com.horstmann.violet.application.gui.util.ckt.handle.Transition;

public class TestCaseProducePartPanel extends JPanel{

	private MainFrame mainFrame;
	private Automatic automatic;
	
	private JPanel titlepanel;
	private JPanel linepanel;
	private JPanel attributepanel;
	private JPanel limitpanel;

	private JPanel titlelabelpanel;
	private JLabel iconlabel;
	private JLabel titlelabel;
	private JButton toolbutton;

	private JLabel linelabel;

	private JTable attributetable;
	private DefaultTableModel attributetablemodel;
	
	private JTable limittable;
	private DefaultTableModel limittablemodel;
	
	private List<String> limit;
	private int hastime=0; 
	
	public TestCaseProducePartPanel(MainFrame mainFrame, Automatic automatic){
		
		this.mainFrame=mainFrame;
		
		this.automatic=automatic;

		if(automatic.getInequalitySet()!=null&&automatic.getInequalitySet().size()>0){
			hastime=1;
			limit=automatic.getInequalitySet();
		}
		
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
		iconlabel=new JLabel();
		titlelabel = new JLabel();
		toolbutton = new JButton();

		linelabel = new JLabel();
		
		attributepanel.setVisible(false);
		limitpanel.setVisible(false);

		initTitlePanel();

		initLinePanel();

		initAttributePanel();
		
		if(hastime==1){
			initLimitPanel();
		}
		else{
			limitpanel.setOpaque(false);
		}

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

		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("ImagePart/test16.png"));
		icon1.setImage(icon1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
		ImageIcon icon3 = new ImageIcon(this.getClass().getResource("ImagePart/dropdown1.png"));
		icon3.setImage(icon3.getImage().getScaledInstance(11, 11, Image.SCALE_DEFAULT));

		titlelabel.setText(automatic.getName().replace("用例", "序列"));
		titlelabel.setFont(new Font("微软雅黑", Font.BOLD, 12));
//		titlelabel.setForeground(new Color(60,0,255));
		titlelabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		
		iconlabel.setIcon(icon1);
		
		titlelabelpanel.setLayout(new FlowLayout(0, 0, FlowLayout.LEFT));
		titlelabelpanel.add(iconlabel);
		titlelabelpanel.add(titlelabel);
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
		
		final String[] columnNames={"迁移Id","迁移名称","源状态名称","目的状态名称","实例化约束条件"};
		String[][] tabelValues={};
		
		attributetablemodel=new DefaultTableModel(tabelValues, columnNames){
			@Override  
            public boolean isCellEditable(int row,int column){  
                return false;  
            } 
		};
		
		attributetable=new JTable(attributetablemodel);
		
		attributetable.setName("TestCaseProducePartPanel");
		
		attributetable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        attributetable.setSelectionBackground(new Color(250, 248, 236));
        attributetable.setGridColor(new Color(224, 226, 220));
		attributetable.setShowGrid(true);
		attributetable.setShowHorizontalLines(true);
		attributetable.setShowVerticalLines(false);
		attributetable.setFillsViewportHeight(true);
		attributetable.setRowHeight(21);
		attributetable.doLayout();
		attributetable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		attributetable.getColumnModel().getColumn(0).setCellRenderer(new MyAllLabelRenderer());
		attributetable.getColumnModel().getColumn(1).setCellRenderer(new MyAllLabelRenderer());
		attributetable.getColumnModel().getColumn(2).setCellRenderer(new MyAllLabelRenderer());
		attributetable.getColumnModel().getColumn(3).setCellRenderer(new MyAllLabelRenderer());
		attributetable.getColumnModel().getColumn(4).setCellRenderer(new MyAllLabelRenderer());

		attributetable.getColumn("迁移Id").setPreferredWidth(50);
		attributetable.getColumn("迁移Id").setMinWidth(50);
		attributetable.getColumn("迁移Id").setMaxWidth(50);
		attributetable.getColumn("迁移名称").setPreferredWidth(50);
		attributetable.getColumn("迁移名称").setMinWidth(50);
		attributetable.getColumn("源状态名称").setPreferredWidth(80);
		attributetable.getColumn("源状态名称").setMinWidth(80);
		attributetable.getColumn("目的状态名称").setPreferredWidth(80);
		attributetable.getColumn("目的状态名称").setMinWidth(80);
		attributetable.getColumn("实例化约束条件").setPreferredWidth(270);
		attributetable.getColumn("实例化约束条件").setMinWidth(270);
        
        DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
        renderer.setBackground(new Color(71, 80, 93));
        renderer.setForeground(new Color(255, 255, 255));
        renderer.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        attributetable.getTableHeader().setDefaultRenderer(renderer); 
        
        attributetable.getTableHeader().setPreferredSize(new Dimension(100, 27));
        
//        DefaultTableCellRenderer renderer1=new DefaultTableCellRenderer();
//        renderer1.setForeground(new Color(115, 110, 102));
//        renderer1.setBackground(new Color(255, 255, 255));
//        renderer1.setFont(new Font("微软雅黑", Font.PLAIN, 10));
//        renderer1.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
//        attributetable.setDefaultRenderer(Object.class, renderer1); 
        
        
		attributetable.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount()==2){
					
					int index=attributetable.getSelectedRow();
					
					final int[] columnindex=new int[columnNames.length];
					int k=0;
					int count=0;
					
					List<String> rowDataList=new ArrayList<String>();
					
					for(int i=0;i<columnNames.length;i++){
						rowDataList.add("+-+"+columnNames[i]+":");
						columnindex[k++]=count++;
						
						String str=(String) attributetablemodel.getValueAt(index, i);
						String[] strdata=str.split(",|--");
						
						for(String s:strdata){
							rowDataList.add(s);
							count++;
						}
						
					}
					
					if(mainFrame.getStepindex()==3){
						
						mainFrame.getAbstractTestCaseResultPanel().getOnenamelabel().setText(titlelabel.getText());
						
						DefaultTableModel dtm=mainFrame.getAbstractTestCaseResultPanel().getTestcaseinfortablemodel();
						
						
						while(dtm.getRowCount()>0){
							dtm.removeRow(dtm.getRowCount()-1);
						}
						
						for(String s:rowDataList){
							Object[] rowData={s};
							dtm.addRow(rowData);
						}
						
						dtm.fireTableDataChanged();
						
						mainFrame.getAbstractTestCaseResultPanel().getTestcaselabeltab1().doClick();
						
					}
					else if(mainFrame.getStepindex()==4){
						
						mainFrame.getTestCaseInstantiationResultPanel().getOnenamelabel().setText(titlelabel.getText());
						
						DefaultTableModel dtm=mainFrame.getTestCaseInstantiationResultPanel().getTestcaseinfortablemodel();
						
						
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
					
				}
			}

		});
        
        
        attributetable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(224, 225, 220)));
		
        attributetable.setBackground(new Color(255, 255, 255));
        
        
        attributepanel.setLayout(new BorderLayout());
        attributepanel.add(attributetable.getTableHeader(),BorderLayout.NORTH);
        attributepanel.add(attributetable, BorderLayout.CENTER);
        attributepanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        attributepanel.setOpaque(false);

        for(Transition t:automatic.getTransitionSet()){
//			Object[] rowData={"266","loop","loc_id_0D871D83_6090_4623_852D_16B5323BFC94_14","loc_id_9A3C08E8_ECB5_4e57_86B0_723B3E6DBF39_15","0<=g.failsafe_throttle<=1,925<=g.failsafe_throttle_value<=1100,-128<=g.failsafe_throttle<=127,-32768<=g.failsafe_throttle_value<=32767,ap.pre_arm_rc_check==1,0<=ap.pre_arm_rc_check<=255"};
			Object[] rowData={t.getId()+"",t.getName(),t.getSource(),t.getTarget(),t.getLimit()};
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

		for (String l : limit) {
			Object[] rowData = { l, "", -1 };
			limittablemodel.addRow(rowData);
		}
		
	}

	public JPanel getAttributepanel() {
		return attributepanel;
	}

	public JPanel getLimitpanel() {
		return limitpanel;
	}
	
}

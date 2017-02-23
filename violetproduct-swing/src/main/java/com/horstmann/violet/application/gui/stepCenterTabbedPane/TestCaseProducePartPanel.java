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

public class TestCaseProducePartPanel extends JPanel{

	private MainFrame mainFrame;
	
	private JPanel titlepanel;
	private JPanel linepanel;
	private JPanel attributepanel;

	private JPanel titlelabelpanel;
	private JLabel titlelabel;
	private JButton toolbutton;

	private JLabel linelabel;

	private JTable attributetable;
	private DefaultTableModel attributetablemodel;
	
	
	public TestCaseProducePartPanel(MainFrame mainFrame){
		
		this.mainFrame=mainFrame;
		
		init();
		
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		this.setBackground(new Color(255, 255, 255));

		
	}


	private void init() {
		// TODO Auto-generated method stub

		titlepanel = new JPanel();
		linepanel = new JPanel();
		attributepanel = new JPanel();

		titlelabelpanel = new JPanel();
		titlelabel = new JLabel();
		toolbutton = new JButton();

		linelabel = new JLabel();

		initTitlePanel();

		initLinePanel();

		initAttributePanel();

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(titlepanel);
		this.add(linepanel);
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

		titlelabel.setText("测试用例  23");
		titlelabel.setFont(new Font("微软雅黑", Font.BOLD, 12));
//		titlelabel.setForeground(new Color(60,0,255));
		titlelabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

		
		titlelabelpanel.setLayout(new FlowLayout(0, 0, FlowLayout.LEFT));
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
				} else {
					attributepanel.setVisible(true);
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
		
		String[] columnNames={"迁移Id","迁移名称","源状态名称","目的状态名称","实例化约束条件"};
		String[][] tabelValues={};
		
		attributetablemodel=new DefaultTableModel(tabelValues, columnNames){
			@Override  
            public boolean isCellEditable(int row,int column){  
                return false;  
            } 
		};
		
		attributetable=new JTable(attributetablemodel);
		
		attributetable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        attributetable.setSelectionBackground(new Color(250, 248, 236));
        attributetable.setGridColor(new Color(224, 226, 220));
		attributetable.setShowGrid(true);
		attributetable.setShowHorizontalLines(true);
		attributetable.setShowVerticalLines(false);
		attributetable.setFillsViewportHeight(true);
		attributetable.setRowHeight(27);
		attributetable.doLayout();
		attributetable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		attributetable.getColumn("迁移Id").setPreferredWidth(5);
		attributetable.getColumn("迁移Id").setMinWidth(5);
		attributetable.getColumn("迁移名称").setPreferredWidth(10);
		attributetable.getColumn("迁移名称").setMinWidth(10);
		attributetable.getColumn("源状态名称").setPreferredWidth(100);
		attributetable.getColumn("源状态名称").setMinWidth(100);
		attributetable.getColumn("目的状态名称").setPreferredWidth(100);
		attributetable.getColumn("目的状态名称").setMinWidth(100);
		attributetable.getColumn("实例化约束条件").setPreferredWidth(500);
		attributetable.getColumn("实例化约束条件").setMinWidth(500);
        
        DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
        renderer.setBackground(new Color(71, 80, 93));
        renderer.setForeground(new Color(255, 255, 255));
        renderer.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        attributetable.getTableHeader().setDefaultRenderer(renderer); 
        
        attributetable.getTableHeader().setPreferredSize(new Dimension(100, 27));
        
        DefaultTableCellRenderer renderer1=new DefaultTableCellRenderer();
        renderer1.setForeground(new Color(115, 110, 102));
        renderer1.setBackground(new Color(255, 255, 255));
        renderer1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        renderer1.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        attributetable.setDefaultRenderer(Object.class, renderer1); 
        
        
		attributetable.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount()==2){
					
					mainFrame.getAbstractTestCaseResultPanel().getNamelabel().setText("测试用例  23");
					
					DefaultTableModel dtm=mainFrame.getAbstractTestCaseResultPanel().getTestcaseproducetablemodel();
					
					int index=attributetable.getSelectedRow();
					
					List<String> rowDataList=new ArrayList<String>();
					rowDataList.add("迁移Id");
					rowDataList.add((String) attributetablemodel.getValueAt(index, 0));
					rowDataList.add("迁移名称");
					rowDataList.add((String) attributetablemodel.getValueAt(index, 1));
					rowDataList.add("源状态名称");
					rowDataList.add((String) attributetablemodel.getValueAt(index, 2));
					rowDataList.add("目的状态名称");
					rowDataList.add((String) attributetablemodel.getValueAt(index, 3));
					rowDataList.add("实例化约束条件");
					
					String str=(String) attributetablemodel.getValueAt(index, 4);
					String[] strdata=str.split(",");
					
					for(String s:strdata){
						rowDataList.add(s);
					}
					
					for(String s:rowDataList){
						Object[] rowData={"   "+s};
						dtm.addRow(rowData);
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

        for(int i=0;i<8;i++){
			Object[] rowData={"266","loop","loc_id_0D871D83_6090_4623_852D_16B5323BFC94_14","loc_id_9A3C08E8_ECB5_4e57_86B0_723B3E6DBF39_15","0<=g.failsafe_throttle<=1,925<=g.failsafe_throttle_value<=1100,-128<=g.failsafe_throttle<=127,-32768<=g.failsafe_throttle_value<=32767,ap.pre_arm_rc_check==1,0<=ap.pre_arm_rc_check<=255"};
			attributetablemodel.addRow(rowData);
		}
		
	}
	
}

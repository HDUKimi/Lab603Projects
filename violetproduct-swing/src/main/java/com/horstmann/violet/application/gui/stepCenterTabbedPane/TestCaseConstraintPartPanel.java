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

public class TestCaseConstraintPartPanel extends JPanel{

	private JPanel titlepanel;
	private JPanel linepanel;
	private JPanel attributepanel;

	private JPanel titlelabelpanel;
	private JLabel titlelabel;
	private JButton toolbutton;

	private JLabel linelabel;

	private JTable attributetable;
	private DefaultTableModel attributetablemodel;
	
	
	public TestCaseConstraintPartPanel(){
		
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

		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("ImagePart/tick.png"));
		icon1.setImage(icon1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
		ImageIcon icon3 = new ImageIcon(this.getClass().getResource("ImagePart/dropdown1.png"));
		icon3.setImage(icon3.getImage().getScaledInstance(11, 11, Image.SCALE_DEFAULT));

		titlelabel.setText("��1������ʽ��");
		titlelabel.setFont(new Font("΢���ź�", Font.BOLD, 12));
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
		
		String[] columnNames={""};
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

//		attributetable.getColumn("Ǩ��Id").setPreferredWidth(5);
//		attributetable.getColumn("Ǩ��Id").setMinWidth(5);
//		attributetable.getColumn("Ǩ������").setPreferredWidth(30);
//		attributetable.getColumn("Ǩ������").setMinWidth(30);
//		attributetable.getColumn("Դ״̬����").setPreferredWidth(100);
//		attributetable.getColumn("Դ״̬����").setMinWidth(100);
//		attributetable.getColumn("Ŀ��״̬����").setPreferredWidth(100);
//		attributetable.getColumn("Ŀ��״̬����").setMinWidth(100);
//		attributetable.getColumn("in(Լ������)").setPreferredWidth(100);
//		attributetable.getColumn("in(Լ������)").setMinWidth(100);
//		attributetable.getColumn("out(�����Ϣ)").setPreferredWidth(20);
//		attributetable.getColumn("out(�����Ϣ)").setMinWidth(20);
//		attributetable.getColumn("conditions(Լ������)").setPreferredWidth(100);
//		attributetable.getColumn("conditions(Լ������)").setMinWidth(100);
        
        DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
        renderer.setBackground(new Color(71, 80, 93));
        renderer.setForeground(new Color(255, 255, 255));
        renderer.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        attributetable.getTableHeader().setDefaultRenderer(renderer); 
        
        attributetable.getTableHeader().setPreferredSize(new Dimension(100, 27));
        
        DefaultTableCellRenderer renderer1=new DefaultTableCellRenderer();
        renderer1.setForeground(new Color(115, 110, 102));
        renderer1.setBackground(new Color(255, 255, 255));
        renderer1.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        renderer1.setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
        attributetable.setDefaultRenderer(Object.class, renderer1); 
        
        attributetable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(224, 225, 220)));
		
        attributetable.setBackground(new Color(255, 255, 255));
        
        
        attributepanel.setLayout(new BorderLayout());
//        attributepanel.add(attributetable.getTableHeader(),BorderLayout.NORTH);
        attributepanel.add(attributetable, BorderLayout.CENTER);
        attributepanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        attributepanel.setOpaque(false);

        String []allrowdata={
        		"t1<=2",
        		"t1>=0",
        		"t1+t2<=2",
        		"t1+t2>=0",
        		"t1+t2+t4<5",
        		"t1+t2+t4>=0",
        		"t1+t2+t4+t5<5",
        		"t1+t2+t4+t5>=0",
        		"t1+t2+t4+t5+t27<=120",
        		"t1+t2+t4+t5+t27>=0",
        		"t1+t2+t4+t5+t27+t9<=120",
        		"t1+t2+t4+t5+t27+t9>=0",
        		"t1+t2+t4+t5+t27+t9+t11<=120",
        		"t1+t2+t4+t5+t27+t9+t11>=0",
        		"t1+t2+t4+t5+t27+t9+t11+t12<=120",
        		"t1+t2+t4+t5+t27+t9+t11+t12>=0",
        		"t16<120",
        		"t16>=0",
        		"t16+t17<120",
        		"t16+t17>=0",
        		"t16+t17+t18>=0",
        		"t16+t17+t18+t19>=0",
        		"t16+t17+t18+t19+t22>=0"
        		};
        
        for(int i=0;i<allrowdata.length;i++){
			Object[] rowData={"   "+allrowdata[i]};
			attributetablemodel.addRow(rowData);
		}
		
	}
}

package com.horstmann.violet.application.consolepart;

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

import com.horstmann.violet.application.gui.util.ckt.handle.Transition;

public class TestCasePathTransitionInforPanel extends JPanel{

	private Transition transition;
	
	private JPanel titlepanel;
	private JPanel linepanel;
	private JPanel attributepanel;
	
	private JPanel titlelabelpanel;
	private JLabel iconlabel;
	private JLabel titlelabel;
	private JButton toolbutton;
	
	private JLabel linelabel;
	
	private JTable attributetable;
	private DefaultTableModel attributetablemodel;
	
	public TestCasePathTransitionInforPanel(Transition transition){
		
		this.transition=transition;
		
		init();
		
//		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		
		this.setBackground(new Color(255, 255, 255));
		
	}
	
	private void init() {
		// TODO Auto-generated method stub
		
		titlepanel=new JPanel();
		linepanel=new JPanel();
		attributepanel=new JPanel();
		
		titlelabelpanel=new JPanel();
		iconlabel=new JLabel();
		titlelabel=new JLabel();
		toolbutton=new JButton();
		
		linelabel=new JLabel();
		
		initTitlePanel();
		
		initLinePanel();
		
		initAttributePanel();
		
//		this.setLayout(new BorderLayout());
//		this.add(titlepanel, BorderLayout.NORTH);
//		this.add(attributepanel,BorderLayout.CENTER);
		
//		GridBagLayout layout = new GridBagLayout();
//		this.setLayout(layout);
//		this.add(titlepanel);
//		this.add(attributepanel);
//		layout.setConstraints(titlepanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//		layout.setConstraints(attributepanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(titlepanel);
		this.add(linepanel);
		this.add(attributepanel);
//		this.add(Box.createVerticalGlue());
		
		
	}

	private void initLinePanel() {
		// TODO Auto-generated method stub
		
		linelabel.setText("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
		linelabel.setPreferredSize(new Dimension(100, 3));
		linelabel.setForeground(new Color(223, 204, 221));
		
		linepanel.setLayout(new GridLayout());
		linepanel.add(linelabel);
		linepanel.setOpaque(false);
		
	}

	private void initTitlePanel() {
		// TODO Auto-generated method stub

		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("ImagePart/event_ation.png"));
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(this.getClass().getResource("ImagePart/dropdown1.png"));
		icon2.setImage(icon2.getImage().getScaledInstance(11,11, Image.SCALE_DEFAULT));
		
		titlelabel.setText("Ç¨ÒÆ"+transition.getId());
		titlelabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 12));
//		titlelabel.setForeground(new Color(60,0,255));
		titlelabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		
		iconlabel.setIcon(icon1);
		
		titlelabelpanel.setLayout(new FlowLayout(0, 0, FlowLayout.LEFT));
		titlelabelpanel.add(iconlabel);
		titlelabelpanel.add(titlelabel);
		titlelabelpanel.setOpaque(false);
		
		
		toolbutton.setIcon(icon2);
		toolbutton.setFocusable(false);
		toolbutton.setContentAreaFilled(false);
		toolbutton.setBorderPainted(false);
		toolbutton.addMouseListener(new ButtonMouseListener());
		toolbutton.setPreferredSize(new Dimension(21,21));
		toolbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(attributepanel.isVisible()){
					attributepanel.setVisible(false);
				}
				else{
					attributepanel.setVisible(true);
				}
			}
		});
		
		titlepanel.setLayout(new BorderLayout());
		titlepanel.add(titlelabelpanel,BorderLayout.WEST);
		titlepanel.add(toolbutton,BorderLayout.EAST);
//		titlepanel.setPreferredSize(new Dimension(100, 30));
		
		titlepanel.setOpaque(false);
		
	}

	private void initAttributePanel() {
		// TODO Auto-generated method stub
		
		attributetablemodel=new DefaultTableModel(0, 1){
			@Override  
            public boolean isCellEditable(int row,int column){  
                return false;  
            } 
		};
		
		attributetable=new JTable(attributetablemodel);
		
		attributetable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		attributetable.setGridColor(Color.BLACK);
		attributetable.setShowGrid(false);
		attributetable.setShowHorizontalLines(false);
		attributetable.setShowVerticalLines(false);
		attributetable.setFillsViewportHeight(true);
		attributetable.setRowHeight(20);
		attributetable.doLayout();
		
		attributetable.getTableHeader().setVisible(false);  
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();  
        renderer.setPreferredSize(new Dimension(0, 0));  
        attributetable.getTableHeader().setDefaultRenderer(renderer); 
        
        DefaultTableCellRenderer renderer1 = new DefaultTableCellRenderer();  
        renderer1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 11));
        attributetable.setDefaultRenderer(Object.class, renderer1);
        
        Object[] rowData1={"id="+transition.getId()};
        attributetablemodel.addRow(rowData1);
        Object[] rowData2={"name="+transition.getName()};
        attributetablemodel.addRow(rowData2);
        Object[] rowData3={"source="+transition.getSource()};
        attributetablemodel.addRow(rowData3);
        Object[] rowData4={"target="+transition.getTarget()};
        attributetablemodel.addRow(rowData4);
        Object[] rowData5={"in="+transition.getIn()};
        attributetablemodel.addRow(rowData5);
        Object[] rowData6={"out="+transition.getOut()};
        attributetablemodel.addRow(rowData6);
        Object[] rowData7={"condition="+transition.getCondition()};
        attributetablemodel.addRow(rowData7);
        
        attributepanel.setLayout(new GridLayout());
        attributepanel.add(attributetable);
        attributepanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        attributepanel.setOpaque(false);
        
	}
}

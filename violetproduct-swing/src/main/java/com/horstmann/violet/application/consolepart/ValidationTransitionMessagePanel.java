package com.horstmann.violet.application.consolepart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.UppaalTransition;

public class ValidationTransitionMessagePanel extends JPanel{

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
	
	private UppaalTransition transistion;
	
	public ValidationTransitionMessagePanel(UppaalTransition transistion){
		
		this.transistion=transistion;
		
		init();
		
		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
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
		
		linepanel.setLayout(new GridLayout());
		linepanel.add(linelabel);
		linepanel.setOpaque(false);
		
	}

	private void initTitlePanel() {
		// TODO Auto-generated method stub

		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "write.png");
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(path + "dropdown.png");
		icon2.setImage(icon2.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		
		titlelabel.setText(transistion.getName());
		titlelabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 12));
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
        
        Object[] rowData={"name="+transistion.getName()};
        attributetablemodel.addRow(rowData);
        Object[] rowData1={"fromName="+transistion.getFromName()};
        attributetablemodel.addRow(rowData1);
        Object[] rowData2={"toName="+transistion.getToName()};
        attributetablemodel.addRow(rowData2);
        Object[] rowData3={"target="+transistion.getTarget()};
        attributetablemodel.addRow(rowData3);
        Object[] rowData4={"source="+transistion.getSource()};
        attributetablemodel.addRow(rowData4);
        Object[] rowData5={"x="+transistion.getX()};
        attributetablemodel.addRow(rowData5);
        Object[] rowData6={"time="+transistion.getTime()};
        attributetablemodel.addRow(rowData6);
        Object[] rowData7={"out="+transistion.isOut()};
        attributetablemodel.addRow(rowData7);
        Object[] rowData8={"duration="+transistion.getDuration()};
        attributetablemodel.addRow(rowData8);
        Object[] rowData9={"label="+transistion.getLabel()};
        attributetablemodel.addRow(rowData9);
        
        attributepanel.setLayout(new GridLayout());
        attributepanel.add(attributetable);
        attributepanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        attributepanel.setOpaque(false);
        
	}
	
}

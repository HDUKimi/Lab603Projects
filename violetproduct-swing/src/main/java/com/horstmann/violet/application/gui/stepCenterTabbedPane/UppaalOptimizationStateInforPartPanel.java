package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;

public class UppaalOptimizationStateInforPartPanel extends JPanel{

	private MainFrame mainFrame;

	private JPanel titlepanel;
	private JPanel linepanel;
	private JPanel attributepanel;
	
	private JPanel titlelinepanel;
	
	private JPanel titlelabelpanel;
	private JLabel iconlabel;
	private JLabel titlelabel;
	private JButton toolbutton;
	
	private JLabel linelabel;
	
	private JScrollPane tablepanel;
	private JTable attributetable;
	private DefaultTableModel attributetablemodel;
	
	public UppaalOptimizationStateInforPartPanel(MainFrame mainFrame){
		
		this.mainFrame=mainFrame;
		
		init();
		
//		this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		this.setBackground(new Color(255, 255, 255));
		
	}

	private void init() {
		// TODO Auto-generated method stub
		
		titlepanel=new JPanel();
		linepanel=new JPanel();
		attributepanel=new JPanel();
		
		titlelinepanel=new JPanel();
		
		titlelabelpanel=new JPanel();
		iconlabel=new JLabel();
		titlelabel=new JLabel();
		toolbutton=new JButton();
		
		linelabel=new JLabel();
		
		titlepanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
		linepanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
//		attributepanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
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
		
//		titlelinepanel.setLayout(new BoxLayout(titlelinepanel, BoxLayout.Y_AXIS));
//		titlelinepanel.add(titlepanel);
//		titlelinepanel.add(linepanel);
		titlelinepanel.setOpaque(false);
		
		GridBagLayout layout = new GridBagLayout();
		titlelinepanel.setLayout(layout);
		titlelinepanel.add(titlepanel);
		titlelinepanel.add(linepanel);
		layout.setConstraints(titlepanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(linepanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		
		this.setLayout(new BorderLayout());
		this.add(titlelinepanel,BorderLayout.NORTH);
		this.add(attributepanel,BorderLayout.CENTER);
//		this.add(Box.createVerticalGlue());
		
	}

	private void initTitlePanel() {
		// TODO Auto-generated method stub
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "scenario_end.png");
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(path + "dropdown1.png");
		icon2.setImage(icon2.getImage().getScaledInstance(11,11, Image.SCALE_DEFAULT));
		
		titlelabel.setText("ËùÓÐ×´Ì¬ÐÅÏ¢");
		titlelabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 12));
//		titlelabel.setForeground(new Color(250,0,60));
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
		titlepanel.setPreferredSize(new Dimension(100, 25));
		titlepanel.setMinimumSize(new Dimension(100, 25));
		titlepanel.setMaximumSize(new Dimension(100, 25));
		
		titlepanel.setOpaque(false);
		
	}

	private void initLinePanel() {
		// TODO Auto-generated method stub
		
		linelabel.setText("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
		linelabel.setPreferredSize(new Dimension(100, 3));
		linelabel.setMaximumSize(new Dimension(100, 3));
		linelabel.setMinimumSize(new Dimension(100, 3));
		linelabel.setForeground(new Color(223, 204, 221));
		
		linepanel.setLayout(new GridLayout());
		linepanel.add(linelabel);
		linepanel.setOpaque(false);
		
	}

	private void initAttributePanel() {
		// TODO Auto-generated method stub
		
		final String[] columnNames={"×´Ì¬","ÐòºÅ","Ãû³Æ","Î»ÖÃ","ÊÇ·ñÎªÖÕÖ¹×´Ì¬","ÀàÐÍ"};
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

		attributetable.getColumnModel().getColumn(0).setCellRenderer(new MyAddDeleteLabelRenderer());
		
		attributetable.getColumn("×´Ì¬").setPreferredWidth(20);
		attributetable.getColumn("×´Ì¬").setMinWidth(20);
		attributetable.getColumn("ÐòºÅ").setPreferredWidth(20);
		attributetable.getColumn("ÐòºÅ").setMinWidth(20);
		attributetable.getColumn("Ãû³Æ").setPreferredWidth(300);
		attributetable.getColumn("Ãû³Æ").setMinWidth(300);
		attributetable.getColumn("Î»ÖÃ").setPreferredWidth(300);
		attributetable.getColumn("Î»ÖÃ").setMinWidth(300);
		attributetable.getColumn("ÊÇ·ñÎªÖÕÖ¹×´Ì¬").setPreferredWidth(50);
		attributetable.getColumn("ÊÇ·ñÎªÖÕÖ¹×´Ì¬").setMinWidth(50);
		attributetable.getColumn("ÀàÐÍ").setPreferredWidth(50);
		attributetable.getColumn("ÀàÐÍ").setMinWidth(50);
        
        DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
        renderer.setBackground(new Color(71, 80, 93));
        renderer.setForeground(new Color(255, 255, 255));
        renderer.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        attributetable.getTableHeader().setDefaultRenderer(renderer); 
        
        attributetable.getTableHeader().setPreferredSize(new Dimension(100, 27));
        
//        DefaultTableCellRenderer renderer1=new DefaultTableCellRenderer();
//        renderer1.setForeground(new Color(115, 110, 102));
//        renderer1.setBackground(new Color(255, 255, 255));
//        renderer1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
//        renderer1.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
//        DefaultTableCellRenderer renderer1 = new DefaultTableCellRenderer() {
//
//			@Override
//			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
//					boolean hasFocus, int row, int column) {
//				// TODO Auto-generated method stub
//
//				setForeground(new Color(115, 110, 102));
//				setBackground(new Color(255, 255, 255));
//				setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
//				setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
//
//				if(table.getValueAt(row, 0)=="-1"){
//					setForeground(new Color(5, 110, 2));
//					setBackground(new Color(5, 135, 5));
//				}
//				else if(table.getValueAt(row, 0)=="1"){
//					setForeground(new Color(115, 110, 102));
//					setBackground(new Color(255, 135, 135));
//				}
//
//				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//			}
//
//		};
//        attributetable.setDefaultRenderer(Object.class, renderer1); 
        
        attributetable.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount()==2){
					
					mainFrame.getAbstractTestCaseResultPanel().getOnenamelabel().setText(titlelabel.getText());
					
					JTable jt=mainFrame.getAbstractTestCaseResultPanel().getTestcaseinfortable();
					DefaultTableModel dtm=mainFrame.getAbstractTestCaseResultPanel().getTestcaseinfortablemodel();
					
					int index=attributetable.getSelectedRow();
					
					final int[] columnindex=new int[columnNames.length-1];
					int k=0;
					int count=0;
					
					List<String> rowDataList=new ArrayList<String>();
					
					for(int i=1;i<columnNames.length;i++){
						rowDataList.add("+-+"+columnNames[i]+":");
						columnindex[k++]=count++;
						
						String str=(String) attributetablemodel.getValueAt(index, i);
						String[] strdata=str.split("--");
						
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
					
				}
			}

		});
        
        attributetable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(224, 225, 220)));
		
        attributetable.setBackground(new Color(255, 255, 255));
        
        
//        attributepanel.setLayout(new BorderLayout());
//        attributepanel.add(attributetable.getTableHeader(),BorderLayout.NORTH);
//        attributepanel.add(attributetable, BorderLayout.CENTER);
////        attributepanel.setLayout(new GridLayout());
////        attributepanel.add(attributetable);
////        attributepanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
//        attributepanel.setOpaque(false);
        
        tablepanel=new JScrollPane(attributetable);
        
        attributepanel.setLayout(new GridLayout());
        attributepanel.add(tablepanel);
//        attributepanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        attributepanel.setOpaque(false);
        
	}

	public JPanel getTitlepanel() {
		return titlepanel;
	}

	public JPanel getLinepanel() {
		return linepanel;
	}

	public JPanel getAttributepanel() {
		return attributepanel;
	}

	public JPanel getTitlelinepanel() {
		return titlelinepanel;
	}

	public JTable getAttributetable() {
		return attributetable;
	}

	public DefaultTableModel getAttributetablemodel() {
		return attributetablemodel;
	}
	
}

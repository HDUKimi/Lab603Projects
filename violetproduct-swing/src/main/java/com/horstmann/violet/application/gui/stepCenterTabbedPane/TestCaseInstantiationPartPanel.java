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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.util.ckt.handle.Automatic;
import com.horstmann.violet.application.gui.util.ckt.handle.Transition;

public class TestCaseInstantiationPartPanel extends JPanel{

	private MainFrame mainFrame;
	private Automatic automatic;
	
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
	
	
	public TestCaseInstantiationPartPanel(MainFrame mainFrame, Automatic automatic){
		
		this.mainFrame=mainFrame;
		
		this.automatic=automatic;
		
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
		iconlabel=new JLabel();
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

		ImageIcon icon1 = new ImageIcon(path + "test15.png");
		icon1.setImage(icon1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
		ImageIcon icon3 = new ImageIcon(path + "dropdown1.png");
		icon3.setImage(icon3.getImage().getScaledInstance(11, 11, Image.SCALE_DEFAULT));

		titlelabel.setText(automatic.getName());
		titlelabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 12));
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
		
		final String[] columnNames={"Ç¨ÒÆId","Ç¨ÒÆÃû³Æ","Ô´×´Ì¬Ãû³Æ","Ä¿µÄ×´Ì¬Ãû³Æ","ÊµÀý»¯½á¹û"};
		String[][] tabelValues={};
		
		attributetablemodel=new DefaultTableModel(tabelValues, columnNames){
			@Override  
            public boolean isCellEditable(int row,int column){  
                return false;  
            } 
		};
		
		attributetable=new JTable(attributetablemodel);
		
		attributetable.setName("TestCaseInstantiationPartPanel");
		
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

		attributetable.getColumn("Ç¨ÒÆId").setPreferredWidth(50);
		attributetable.getColumn("Ç¨ÒÆId").setMinWidth(50);
		attributetable.getColumn("Ç¨ÒÆId").setMaxWidth(50);
		attributetable.getColumn("Ç¨ÒÆÃû³Æ").setPreferredWidth(50);
		attributetable.getColumn("Ç¨ÒÆÃû³Æ").setMinWidth(50);
		attributetable.getColumn("Ô´×´Ì¬Ãû³Æ").setPreferredWidth(80);
		attributetable.getColumn("Ô´×´Ì¬Ãû³Æ").setMinWidth(80);
		attributetable.getColumn("Ä¿µÄ×´Ì¬Ãû³Æ").setPreferredWidth(80);
		attributetable.getColumn("Ä¿µÄ×´Ì¬Ãû³Æ").setMinWidth(80);
		attributetable.getColumn("ÊµÀý»¯½á¹û").setPreferredWidth(270);
		attributetable.getColumn("ÊµÀý»¯½á¹û").setMinWidth(270);
        
        DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
        renderer.setBackground(new Color(71, 80, 93));
        renderer.setForeground(new Color(255, 255, 255));
        renderer.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        attributetable.getTableHeader().setDefaultRenderer(renderer); 
        
        attributetable.getTableHeader().setPreferredSize(new Dimension(100, 27));
        
//        DefaultTableCellRenderer renderer1=new DefaultTableCellRenderer();
//        renderer1.setForeground(new Color(115, 110, 102));
//        renderer1.setBackground(new Color(255, 255, 255));
//        renderer1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 10));
//        renderer1.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
//        attributetable.setDefaultRenderer(Object.class, renderer1); 
        
        attributetable.addMouseListener(new MouseAdapter(){
        	
        	@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount()==2){
					
					mainFrame.getAbstractTestCaseResultPanel().getOnenamelabel().setText(titlelabel.getText());
					
					JTable jt=mainFrame.getAbstractTestCaseResultPanel().getTestcaseinfortable();
					DefaultTableModel dtm=mainFrame.getAbstractTestCaseResultPanel().getTestcaseinfortablemodel();
					
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
//			Object[] rowData={"48","set_pwm","loc_id_41F2D344_CCE6_4e2a_A417_9245889CE58C_4_5","yaw_pwm=2200,_num_tasks=255,dt=65535,_task_time_allowed=4294967295,i=0,time_available=4294967295,interval_ticks=65535,has_new_input=True"};
        	
//        	String result=t.getResult().toString().replaceAll("\\[|]", "").split("%")[1];
        	
        	String result=null;
        	String[] resultsplit=t.getResult().toString().replaceAll("\\[|]", "").split("%");
        	if(resultsplit.length>1){
        		result=resultsplit[1];
        	}
        	else{
        		result=resultsplit[0];
        	}
        	
//        	if(mainFrame.getStepThreeCenterTabbedPane().getTestCaseProcessTabbedPanel().getSelectCoverState()==2){//ÐÔÄÜ²âÊÔ
//        		String name;
//    			name=t.getName();
//    			if(name.indexOf("(")>0){
//    				name=name.substring(0, name.indexOf("("));
//    			}
//    			result=result.replaceAll("\\[|]", "");
//    			result=result.replace("%null", "");
//    			result=result.replace(name, "");
//    			result=result.replace("%", "");
//    			if(result.equals("")){
//    				result=null;
//    			}
//        	}
        	
			Object[] rowData={t.getId()+"",t.getName(),t.getSource(),t.getTarget(),result+""};
			attributetablemodel.addRow(rowData);
		}
		
	}


	public JPanel getAttributepanel() {
		return attributepanel;
	}
	
}

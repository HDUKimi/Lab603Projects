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
import java.util.ArrayList;
import java.util.Arrays;
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
import com.horstmann.violet.application.gui.util.ckt.handle.Transition;

public class TestCaseInequalitySolveInforPanel extends JPanel{

	private MainFrame mainFrame;
	private Transition limittransition;
	private Transition resulttransition;
	
	private String id;
	private List<String> limit=new ArrayList<>();
	private String result;
	
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
	
//	public TestCaseInequalitySolveInforPanel(String id,List<String> limit, List<String> result){
	public TestCaseInequalitySolveInforPanel(MainFrame mainFrame, Transition limittransition, Transition resulttransition){
		
//		this.id=id;
//		
//		this.limit=limit;
//		
//		this.result=result;
		
		this.mainFrame=mainFrame;
		this.limittransition=limittransition;
		this.resulttransition=resulttransition;
		
		init();
		
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
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(titlepanel);
		this.add(linepanel);
		this.add(attributepanel);
		
	}

	private void initTitlePanel() {
		// TODO Auto-generated method stub
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "event_ation.png");
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(path + "dropdown1.png");
		icon2.setImage(icon2.getImage().getScaledInstance(11,11, Image.SCALE_DEFAULT));
		
		titlelabel.setText("迁移"+id);
		titlelabel.setFont(new Font("微软雅黑", Font.BOLD, 12));
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
		
		titlepanel.setOpaque(false);
		
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
        attributetable.setSelectionBackground(new Color(250, 248, 236));
        attributetable.setGridColor(new Color(224, 226, 220));
		attributetable.setShowGrid(true);
		attributetable.setShowHorizontalLines(true);
		attributetable.setShowVerticalLines(false);
		attributetable.setFillsViewportHeight(true);
		attributetable.setRowHeight(27);
		attributetable.doLayout();
		attributetable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		attributetable.getColumnModel().getColumn(0).setCellRenderer(new TestCaseInforLabelRenderer());
		
		attributetable.getTableHeader().setVisible(false);  
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();  
        renderer.setPreferredSize(new Dimension(0, 0));  
        attributetable.getTableHeader().setDefaultRenderer(renderer); 
        
        DefaultTableCellRenderer renderer1=new DefaultTableCellRenderer();
        renderer1.setForeground(new Color(115, 110, 102));
        renderer1.setBackground(new Color(255, 255, 255));
        renderer1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        renderer1.setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
        attributetable.setDefaultRenderer(Object.class, renderer1); 
        
        
//        for(int i=0;i<4;i++){
//        	Object[] rowData1={" 925<=g.failsafe_throttle_value<=1100,-128<=g.failsafe_throttle<=127"};
//			attributetablemodel.addRow(rowData1);
//			Object[] rowData2={"求解： g.failsafe_throttle_value=1100"};
//			attributetablemodel.addRow(rowData2);
//        }
        
        limit=Arrays.asList(limittransition.getLimit().split(","));
        result=resulttransition.getResult().toString();
        
        if(mainFrame.getStepThreeCenterTabbedPane().getTestCaseProcessTabbedPanel().getSelectCoverState()==2){//性能测试
    		String name;
			name=resulttransition.getName();
			if(name.indexOf("(")>0){
				name=name.substring(0, name.indexOf("("));
			}
			result=result.replaceAll("\\[|]", "");
			result=result.replace("%null", "");
			result=result.replace(name, "");
			result=result.replace("%", "");
//			if(result.equals("")){
//				result=null;
//			}
    	}
        
        Object[] rowData={"+-+不等式："};
    	attributetablemodel.addRow(rowData);
        for(String l:limit){
        	if(l.equals("")||l==null){
        		Object[] rowData1={"null"};
            	attributetablemodel.addRow(rowData1);
        	}
        	else{
        		Object[] rowData1={l};
            	attributetablemodel.addRow(rowData1);
        	}
        }
        Object[] rowData2={"+-+求解："};
    	attributetablemodel.addRow(rowData2);
    	
    	if(result.equals("")){
    		Object[] rowData3={"null"};
        	attributetablemodel.addRow(rowData3);
    	}
    	else{
				String[] rs = result.split(",");
				for (int i = 0; i < rs.length; i++) {
					Object[] rowData3 = { rs[i] };
					attributetablemodel.addRow(rowData3);
				}
    	}
    	
//        for(String r:result){
//        	if(r.equals(null)){
//        		Object[] rowData3={"null"};
//            	attributetablemodel.addRow(rowData3);
//        	}
//        	else{
////        		String []rs=r.split(",");
////            	for(int i=0;i<rs.length;i++){
////            		Object[] rowData3={rs[i]};
////                	attributetablemodel.addRow(rowData3);
////            	}
//        		Object[] rowData3={"+"+r+"+"};
//            	attributetablemodel.addRow(rowData3);
//        	}
//        	
//        }
        
        attributetable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(224, 225, 220)));
		
        attributetable.setBackground(new Color(255, 255, 255));
        
        attributepanel.setLayout(new GridLayout());
        attributepanel.add(attributetable);
//        attributepanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        attributepanel.setOpaque(false);
		
	}
	
}

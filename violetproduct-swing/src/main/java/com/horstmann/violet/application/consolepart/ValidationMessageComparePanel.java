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
import com.horstmann.violet.application.gui.util.wujun.TDVerification.UppaalTransition;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.RowStringsForDisplay.MessageCompare;
import com.horstmann.violet.application.gui.util.wujun.TimingTransfrom.EAMessage;

public class ValidationMessageComparePanel extends JPanel{

	private JPanel titlepanel;
	private JPanel linepanel;
	private JPanel attributepanel;

	private JPanel titlelabelpanel;
//	private JLabel iconlabel;
//	private JLabel titlelabel;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JButton toolbutton;

	private JLabel linelabel;

	private JTable attributetable;
	private DefaultTableModel attributetablemodel;

	private MessageCompare messagecompare;
	
	private EAMessage message;
	private UppaalTransition transition;
	private String result;
	
	public ValidationMessageComparePanel(MessageCompare messagecompare){
		
		this.messagecompare=messagecompare;
		
		message=messagecompare.getMessage();
		
		transition=messagecompare.getTransition();
		
		result=messagecompare.getResult();

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
//		iconlabel=new JLabel();
//		titlelabel=new JLabel();
		label1=new JLabel();
		label2=new JLabel();
		label3=new JLabel();
		toolbutton=new JButton();
		
		linelabel=new JLabel();
		
		initTitlePanel();
		
		initLinePanel();
		
		initAttributePanel();
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(titlepanel);
		this.add(linepanel);
		this.add(attributepanel);
		
		attributepanel.setVisible(false);
		
	}
	
	private void initTitlePanel() {
		// TODO Auto-generated method stub
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "scenario_end.png");
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(path + "dropdown1.png");
		icon2.setImage(icon2.getImage().getScaledInstance(11,11, Image.SCALE_DEFAULT));
		
//		titlelabel.setText(stateInfo.getName()+" "+location.getName()+" "+result);
//		titlelabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
//		titlelabel.setForeground(new Color(250,0,60));
//		titlelabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		
		label1.setText(message.getName());
		label2.setText(transition.getName());
		
		if(result.equals("ok")){
			label3.setText("³É¹¦");
		}
		else{
			label3.setText("Ê§°Ü");
		}
		
		label1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		label1.setPreferredSize(new Dimension(150, 23));
		
		label2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		label2.setPreferredSize(new Dimension(150, 23));
		
		label3.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		label3.setPreferredSize(new Dimension(60, 23));
		
//		iconlabel.setIcon(icon1);
		
		titlelabelpanel.setLayout(new FlowLayout(0, 0, FlowLayout.LEFT));
//		titlelabelpanel.add(iconlabel);
//		titlelabelpanel.add(titlelabel);
		titlelabelpanel.add(label1);
		titlelabelpanel.add(label2);
		titlelabelpanel.add(label3);
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
		
		attributetablemodel=new DefaultTableModel(12, 3){
			@Override  
            public boolean isCellEditable(int row,int column){  
                return false;  
            } 
		};
		
		attributetable=new JTable(attributetablemodel);
		
		attributetable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		attributetable.setGridColor(Color.BLACK);
		attributetable.setShowGrid(true);
		attributetable.setShowHorizontalLines(true);
		attributetable.setShowVerticalLines(true);
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
        
        attributetable.getColumnModel().getColumn(0).setMinWidth(150);
        attributetable.getColumnModel().getColumn(0).setMaxWidth(150);
        attributetable.getColumnModel().getColumn(1).setMinWidth(150);
        attributetable.getColumnModel().getColumn(1).setMaxWidth(150);
        attributetable.getColumnModel().getColumn(2).setMinWidth(60);
        attributetable.getColumnModel().getColumn(2).setMaxWidth(60);
        
        Object[] columnData1={"name="+message.getName(),"duration="+message.getDuration(),"sendTime="+message.getSendTime(),"receiveTime="+message.getReceiveTime(),"sourceId="+message.getSourceId(),"tragetId="+message.getTragetId(),"sourceName="+message.getSourceName(),"tragetName="+message.getTragetName(),"connectorId="+message.getConnectorId(),"inner="+message.getInner()};
        
        for(int i=0;i<columnData1.length;i++){
        	attributetablemodel.setValueAt(columnData1[i], i, 0);
        }
        
        Object[] columnData2={"name="+transition.getName(),"fromName="+transition.getFromName(),"toName="+transition.getToName(),"target="+transition.getTarget(),"source="+transition.getSource(),"x="+transition.getX(),"time="+transition.getTime(),"startTime="+transition.getStartTime(),"endTime="+transition.getEndTime(),"out="+transition.isOut(),"timeDuration="+transition.getTimeDuration(),"label="+transition.getLabel()};
        
        for(int i=0;i<columnData2.length;i++){
        	attributetablemodel.setValueAt(columnData2[i], i, 1);
        }
        
        Object[] columnData3={"result="+result};
        
        for(int i=0;i<columnData3.length;i++){
        	attributetablemodel.setValueAt(columnData3[i], i, 2);
        }
        
        attributetable.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 0, new Color(0, 0, 0)));
        
//        attributepanel.setLayout(new GridLayout());
        attributepanel.setLayout(new FlowLayout(0, 0, FlowLayout.LEFT));
        attributepanel.add(attributetable);
//        attributepanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        attributepanel.setOpaque(false);
        
	}
	
}

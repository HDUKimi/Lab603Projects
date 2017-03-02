package com.horstmann.violet.application.gui.stepCenterTabbedPane;

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
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;

public class UppaalOptimizationTabbedPanel extends JPanel{

	private MainFrame mainFrame;

	private JPanel toolpanel;
	private MoviePanel moviepanel;
	private JPanel inforpanel;

	private JPanel toolbuttonpanel6;
	private JPanel toolbuttonpanel7;
	private JButton toolbutton6;
	private JButton toolbutton7;

	private JScrollPane inforscrollpanel;
	private JPanel inforresultpanel;
	
	private UppaalOptimizationStateInforPartPanel stateinforpanel;
	private UppaalOptimizationMigrateInforPartPanel migrateinforpanel;
	
	private JPanel generalinforpanel;
	private JPanel emptypanel;
	
	private JLabel generalinforlabel1;
	private JLabel generalinforlabel2;
	private JLabel generalinforlabel3;
	private JLabel generalinforlabel4;
	
	private JTable stateinfortable;
	private DefaultTableModel stateinfortablemodel;
	
	private JTable migrateinfortable;
	private DefaultTableModel migrateinfortablemodel;
	
	public UppaalOptimizationTabbedPanel(MainFrame mainframe) {

		this.mainFrame = mainframe;

		toolpanel = new JPanel();
		moviepanel = new MoviePanel();
		inforpanel = new JPanel();

		initToolPanel();

		initMoviePanel();

		initInforPanel();

		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(toolpanel);
		this.add(moviepanel);
		this.add(inforpanel);
		layout.setConstraints(toolpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(moviepanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(inforpanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));

		this.setBackground(new Color(255, 255, 255));

	}
	
	private void initToolPanel() {
		// TODO Auto-generated method stub
		
		toolbuttonpanel6 = new JPanel();
		toolbuttonpanel7 = new JPanel();

		toolbutton6 = new JButton();
		toolbutton7 = new JButton();
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon6 = new ImageIcon(path + "up_arrow.png");
		icon6.setImage(icon6.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon7 = new ImageIcon(path + "down_arrow.png");
		icon7.setImage(icon7.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
	
		
		toolbutton6.setIcon(icon6);
		toolbutton6.setFocusable(false);
		toolbutton6.setContentAreaFilled(false);
		toolbutton6.setBorderPainted(false);
		toolbutton6.addMouseListener(new ButtonMouseListener());
		toolbutton6.setPreferredSize(new Dimension(21,21));
		toolbutton6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
			}
		});
		
		toolbutton7.setIcon(icon7);
		toolbutton7.setFocusable(false);
		toolbutton7.setContentAreaFilled(false);
		toolbutton7.setBorderPainted(false);
		toolbutton7.addMouseListener(new ButtonMouseListener());
		toolbutton7.setPreferredSize(new Dimension(21,21));
		toolbutton7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
			}
		});

		toolbuttonpanel6.setLayout(new GridLayout());
		toolbuttonpanel6.setBackground(new Color(207, 214, 229));
		toolbuttonpanel6.add(toolbutton6);
		toolbuttonpanel7.setLayout(new GridLayout());
		toolbuttonpanel7.setBackground(new Color(207, 214, 229));
		toolbuttonpanel7.add(toolbutton7);

		toolpanel.setBackground(new Color(207, 214, 229));
		toolpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5,3));
		
		toolpanel.add(toolbuttonpanel6);
		toolpanel.add(toolbuttonpanel7);
		
		toolpanel.setPreferredSize(new Dimension(100, 29));
		toolpanel.setMaximumSize(new Dimension(100, 29));
		toolpanel.setMinimumSize(new Dimension(100, 29));
		
		
	}

	private void initMoviePanel() {
		// TODO Auto-generated method stub
		
		moviepanel.getMovieLabel().setText("正在进行符号状态拆分,去除抽象时间迁移");
		
	}
	
	private void initInforPanel() {
		// TODO Auto-generated method stub
		
		stateinforpanel=new UppaalOptimizationStateInforPartPanel(mainFrame);
		migrateinforpanel=new UppaalOptimizationMigrateInforPartPanel(mainFrame);
		
		generalinforpanel=new JPanel();
		emptypanel=new JPanel();
		
		emptypanel.setBackground(new Color(255, 255, 255));
		emptypanel.setMinimumSize(new Dimension(1, 1));
		emptypanel.setMaximumSize(new Dimension(1, 1));
		emptypanel.setPreferredSize(new Dimension(1, 1));
		
		initGeneralInforPanel();
		
		initDataToDate();
		
		inforresultpanel=new JPanel();
		inforresultpanel.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
		inforresultpanel.setBackground(new Color(255, 255, 255));
		
		GridBagLayout layout = new GridBagLayout();
		inforresultpanel.setLayout(layout);
		inforresultpanel.add(generalinforpanel);
		inforresultpanel.add(stateinforpanel.getTitlelinepanel());
		inforresultpanel.add(stateinforpanel.getAttributepanel());
		inforresultpanel.add(migrateinforpanel);

		layout.setConstraints(generalinforpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(stateinforpanel.getTitlelinepanel(), new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(stateinforpanel.getAttributepanel(), new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(migrateinforpanel, new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		
		inforscrollpanel=new JScrollPane(inforresultpanel);
        inforscrollpanel.setBorder(null);
        inforscrollpanel.setBackground(new Color(255, 255, 255));
		
		inforpanel.setLayout(new GridLayout());
		inforpanel.add(inforresultpanel);
		
		inforpanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		inforpanel.setOpaque(false);
		
	}
	
	private void initDataToDate() {
		// TODO Auto-generated method stub
		
		stateinfortable=stateinforpanel.getAttributetable();
		stateinfortablemodel=stateinforpanel.getAttributetablemodel();
		
		migrateinfortable=migrateinforpanel.getAttributetable();
		migrateinfortablemodel=migrateinforpanel.getAttributetablemodel();
		
		while(stateinfortablemodel.getRowCount()>0){
			stateinfortablemodel.removeRow(stateinfortablemodel.getRowCount()-1);
		}
		
		while(migrateinfortablemodel.getRowCount()>0){
			migrateinfortablemodel.removeRow(migrateinfortablemodel.getRowCount()-1);
		}
		
		DefaultTableCellRenderer renderer1 = new DefaultTableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				// TODO Auto-generated method stub

				setForeground(new Color(115, 110, 102));
				setBackground(new Color(255, 255, 255));
				setFont(new Font("微软雅黑", Font.PLAIN, 12));
				setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

				if(Integer.parseInt(table.getValueAt(row, 0).toString())==-1){
					setForeground(new Color(177,177,177));
					setBackground(new Color(200,200,200));
				}
				else if(Integer.parseInt(table.getValueAt(row, 0).toString())==1){
					setForeground(new Color(0,0,0));
					setBackground(new Color(205,255,185));
				}

				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}

		};
		stateinfortable.setDefaultRenderer(Object.class, renderer1);
		migrateinfortable.setDefaultRenderer(Object.class, renderer1);
		
		Random rand=new Random();
		int index;
		for(int i=0;i<50;i++){
			index=rand.nextInt(3);
			if(index==2){
				index=-1;
			}
			Object[] rowData1={index,"1","loc_id_29C2E776_04D4_47f3_8F70_D9F4DD7BEE72_14","loc_id_29C2E776_04D4_47f3_8F70_D9F4DD7BEE72_14","false","CircularNode"};
			stateinfortablemodel.addRow(rowData1);
		}
		
		for(int i=0;i<50;i++){
			index=rand.nextInt(3);
			if(index==2){
				index=-1;
			}
			Object[] rowData1={index,"13","set_throttle_out_unstabilizedfloat, bool, float","g.throttle_filt#g.throttle_filt:float","cycle=2.5ms--control_mode==0#control_mode:int8_t--motor_state==False || ap.throttle_zero==True#motor_state:bool,ap.throttle_zero:bool","null","不空，但是没有内容"};
			migrateinfortablemodel.addRow(rowData1);
		}
		
	}

	private void initGeneralInforPanel() {
		// TODO Auto-generated method stub
		
		generalinforlabel1=new JLabel();
		generalinforlabel2=new JLabel();
		generalinforlabel3=new JLabel();
		generalinforlabel4=new JLabel();
		
		generalinforlabel1.setText("时间自动机名字：template_");
		generalinforlabel2.setText("时间自动机时钟集合： t");
		generalinforlabel3.setText("模型中总状态个数：200 ，  增加了50个 ， 减少了10个");
		generalinforlabel4.setText("模型中总迁移个数：220 ，  增加了20个 ， 减少了30个");
		
		generalinforlabel1.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		generalinforlabel2.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		generalinforlabel3.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		generalinforlabel4.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		
		generalinforpanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 0));
		generalinforpanel.setBackground(new Color(255, 255, 255));
		generalinforpanel.setLayout(new GridLayout(2, 2));
		generalinforpanel.add(generalinforlabel1);
		generalinforpanel.add(generalinforlabel2);
		generalinforpanel.add(generalinforlabel3);
		generalinforpanel.add(generalinforlabel4);
		
	}
	
	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}
	
}

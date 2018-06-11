package com.horstmann.violet.application.gui.stepCenterTabbedPane;

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

import com.horstmann.violet.application.consolepart.TestCasePathPanel;
import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.util.ckt.handle.Automatic;
import com.horstmann.violet.application.gui.util.ckt.handle.Transition;
import com.horstmann.violet.application.gui.util.tanchao.TranMessageColorize;
import com.horstmann.violet.workspace.IWorkspace;

public class TestCaseSortContrastPartPanel extends JPanel{

	private MainFrame mainFrame;
	private Automatic automatic;
	private IWorkspace workspace;
	
	private int oldindex;

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
	
	
	public TestCaseSortContrastPartPanel(int oldindex,MainFrame mainFrame,Automatic automatic,IWorkspace workspace){
		
		
		this.oldindex=oldindex;
		this.mainFrame=mainFrame;
		this.automatic=automatic;
		this.workspace=workspace;
		
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
		
		attributepanel.setVisible(false);

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

		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("ImagePart/test17.png"));
		icon1.setImage(icon1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
		ImageIcon icon3 = new ImageIcon(this.getClass().getResource("ImagePart/dropdown1.png"));
		icon3.setImage(icon3.getImage().getScaledInstance(11, 11, Image.SCALE_DEFAULT));

		titlelabel.setText(automatic.getName().replace("用例", "路径")+"   即为排序前的测试路径"+oldindex+"   优先级为： "+automatic.getPathOfAllImpPercentage());
		titlelabel.setFont(new Font("微软雅黑", Font.BOLD, 12));
//		titlelabel.setForeground(new Color(60,0,255));
		titlelabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		
		titlelabel.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount()==2){
					
					System.out.println("---------------------------------");
					for(Transition t:automatic.getTransitionSet()){
						System.out.println(t.getId()+" + "+t.getName());
					}
					System.out.println("---------------------------------");

					TranMessageColorize tmc=new TranMessageColorize();
					tmc.ColorizeDFSPath(automatic,mainFrame,workspace,mainFrame.getStepThreeCenterTabbedPane().getTestCaseSortContrastTabbedPanel().getTrantextstate());
					
					if(mainFrame.getStepThreeCenterTabbedPane().getFixButtonTabbedPanelSelectedIndex()==5){
						mainFrame.getStepThreeCenterTabbedPane().getTestCaseSortContrastTabbedPanel().ChangeRepaint();
					}
					else if(mainFrame.getStepThreeCenterTabbedPane().getFixButtonTabbedPanelSelectedIndex()==0){
						mainFrame.getStepThreeCenterTabbedPane().getTestCaseProcessTabbedPanel().ChangeRepaint();
					}
					else if(mainFrame.getStepThreeCenterTabbedPane().getFixButtonTabbedPanelSelectedIndex()==7){
						mainFrame.getStepThreeCenterTabbedPane().getTestCaseOptimizationTabbedPanel().ChangeRepaint();
					}
					
					JPanel resultpanel = new JPanel();
					JPanel emptypanel = new JPanel();
					resultpanel.setOpaque(false);
					emptypanel.setOpaque(false);

					GridBagLayout layout = new GridBagLayout();
					resultpanel.setLayout(layout);
					int i = 0;

					TestCasePathPanel tcppanel = new TestCasePathPanel(automatic);
					resultpanel.add(tcppanel);
					layout.setConstraints(tcppanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
					resultpanel.add(emptypanel);
					layout.setConstraints(emptypanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));

					mainFrame.getAbstractTestCaseResultPanel().getThreeresultpanel().removeAll();
					mainFrame.getAbstractTestCaseResultPanel().getThreeresultpanel().add(resultpanel);
					
					mainFrame.getAbstractTestCaseResultPanel().getThreenamelabel().setText(automatic.getName().replace("用例", "路径")+"，包含"+automatic.getStateSet().size()+"个状态节点和"+automatic.getTransitionSet().size()+"条消息迁移");
					
					mainFrame.getAbstractTestCaseResultPanel().getTestcaselabeltab3().doClick();
					
				}
			}
			
		});

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
				"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
		linelabel.setPreferredSize(new Dimension(100, 3));
		linelabel.setForeground(new Color(223, 204, 221));

		linepanel.setLayout(new GridLayout());
		linepanel.add(linelabel);
		linepanel.setOpaque(false);
		
	}


	private void initAttributePanel() {
		// TODO Auto-generated method stub
		
		final String[] columnNames={"迁移ID","迁移名称","源状态ID","目的状态ID","in(约束条件)","out(输出信息)","conditions(约束条件)"};
		String[][] tabelValues={};
		
		attributetablemodel=new DefaultTableModel(tabelValues, columnNames){
			@Override  
            public boolean isCellEditable(int row,int column){  
                return false;  
            } 
		};
		
		attributetable=new JTable(attributetablemodel);
		
		attributetable.setName("TestCaseCoverPartPanel");
		
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
		attributetable.getColumnModel().getColumn(5).setCellRenderer(new MyAllLabelRenderer());
		attributetable.getColumnModel().getColumn(6).setCellRenderer(new MyAllLabelRenderer());

		attributetable.getColumn("迁移ID").setPreferredWidth(50);
		attributetable.getColumn("迁移ID").setMinWidth(50);
		attributetable.getColumn("迁移ID").setMaxWidth(50);
		attributetable.getColumn("迁移名称").setPreferredWidth(50);
		attributetable.getColumn("迁移名称").setMinWidth(50);
		attributetable.getColumn("源状态ID").setPreferredWidth(80);
		attributetable.getColumn("源状态ID").setMinWidth(80);
		attributetable.getColumn("目的状态ID").setPreferredWidth(80);
		attributetable.getColumn("目的状态ID").setMinWidth(80);
		attributetable.getColumn("in(约束条件)").setPreferredWidth(90);
		attributetable.getColumn("in(约束条件)").setMinWidth(90);
		attributetable.getColumn("out(输出信息)").setPreferredWidth(90);
		attributetable.getColumn("out(输出信息)").setMinWidth(90);
		attributetable.getColumn("conditions(约束条件)").setPreferredWidth(90);
		attributetable.getColumn("conditions(约束条件)").setMinWidth(90);
        
        DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
        renderer.setBackground(new Color(71, 80, 93));
        renderer.setForeground(new Color(255, 255, 255));
        renderer.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        attributetable.getTableHeader().setDefaultRenderer(renderer); 
        
        attributetable.getTableHeader().setPreferredSize(new Dimension(100, 27));
        

        
        attributetable.addMouseListener(new MouseAdapter() {

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
        	Object[] rowData={t.getId()+"",t.getName(),t.getSource(),t.getTarget(),t.getIn(),t.getOut()+"",t.getCondition()};
			attributetablemodel.addRow(rowData);
        }
		
	}


	public JPanel getAttributepanel() {
		return attributepanel;
	}
	
	public MainFrame getMainFrame() {
		return mainFrame;
	}


	public Automatic getAutomatic() {
		return automatic;
	}


	public IWorkspace getWorkspace() {
		return workspace;
	}
	
}

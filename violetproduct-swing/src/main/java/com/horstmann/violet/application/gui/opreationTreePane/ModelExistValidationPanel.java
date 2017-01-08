package com.horstmann.violet.application.gui.opreationTreePane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.horstmann.violet.application.consolepart.TableHeadPanel;
import com.horstmann.violet.application.consolepart.ValidationMessageComparePanel;
import com.horstmann.violet.application.consolepart.ValidationPathTupleTimePanel;
import com.horstmann.violet.application.consolepart.ValidationStateComparePanel;
import com.horstmann.violet.application.consolepart.ValidationTransitionMessagePanel;
import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.ButtonTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.MoviePanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.MyLabelCellEditor;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.MyUppaalLabelRender;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.ToolPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.UppaalToolPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.ValidationToolPanel;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.CompareEAtoAutomata;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.ExistVerification;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.LocationVerificationDisplay;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.PathTuple;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.RowStringsForDisplay;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.TransitionVerificationDisplay;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.UppaalTransition;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.RowStringsForDisplay.MessageCompare;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.RowStringsForDisplay.StateCompare;
import com.horstmann.violet.application.gui.util.wujun.TimingTransfrom.TimingEAtoUppaal;
import com.horstmann.violet.application.gui.util.xiaole.GraghLayout.LayoutUppaal;
import com.horstmann.violet.application.gui.util.xiaole.UppaalTransfrom.ImportByDoubleClick;
import com.horstmann.violet.application.gui.util.xiaole.UppaalTransfrom.TransToVioletUppaal;
import com.horstmann.violet.application.menu.FileMenu;
import com.horstmann.violet.framework.file.GraphFile;
import com.horstmann.violet.framework.file.IFile;
import com.horstmann.violet.framework.file.IGraphFile;
import com.horstmann.violet.framework.file.LocalFile;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;

public class ModelExistValidationPanel extends JPanel{

	private static MainFrame mainFrame;
	
	private List<String> timinglists=new ArrayList<String>();
	
	private JPanel titlepanel;
	private JPanel treepanel;
	
	private JLabel titlelabel;
	private JPanel titleiconlabelpanel;
	private JLabel titleiconlabel1;
	private JLabel titleiconlabel2;
	private JLabel titleiconlabel3;
	
	private JPanel timingpanel;
	private JPanel timinglabelpanel;
	private JLabel timinglabel;
	private JPanel timingtoolpanel;
	private JButton timingtoolbutton1;
	private JButton timingtoolbutton2;
	private JPanel timingtablepanel;
	private JScrollPane timingscrollpanel;
	
	private DefaultTableModel timingtablemodel;
	private JTable timingtable;
	
	private JPanel validationpanel;
	private JPanel validationalllabelpanel;
	private JPanel validationlabelpanel;
	private JLabel validationlabel;
	private JPanel validationtoolpanel;
	private JButton validationtoolbutton1;
	private JButton validationtoolbutton2;
	
	private JPanel validationlabeltabpanel;
	private JPanel validationlabeltabpanel1;
	private JPanel validationlabeltabpanel2;
	private JPanel validationlabeltabpanel3;
	private JPanel validationlabeltabpanel4;
	private JLabel validationlabeltab1;
	private JLabel validationlabeltab2;
	private JLabel validationlabeltab3;
	private JLabel validationlabeltab4;
	
	private int validationlabeltabindex=1;
	
	private JPanel validationcheckboxpanel;
	private JScrollPane validationscrollpanel;
	
	private List<UppaalTransition> uppaalmessagelist=new ArrayList<UppaalTransition>();
	private List<UppaalTransition> selecteduppaalmessagelist=new ArrayList<UppaalTransition>();
//	private JCheckBox[] uppaalMessageCheckBoxList;
	private List<JCheckBox> uppaalMessageCheckBoxList;
	
	private static ExistVerification ev;
	
	IWorkspace uppaalworkspace=null;
	
	
	
	
	public ModelExistValidationPanel(MainFrame mainFrame){
		
		this.mainFrame=mainFrame;
		
		initFileList();
		
		titlepanel=new JPanel();
		treepanel=new JPanel();
		
		titlelabel = new JLabel();
		titleiconlabelpanel=new JPanel();
		titleiconlabel1 = new JLabel();
		titleiconlabel2 = new JLabel();
		titleiconlabel3 = new JLabel();
		
		timingpanel=new JPanel();
		timinglabelpanel=new JPanel();
		timinglabel=new JLabel();
		timingtoolpanel=new JPanel();
		timingtoolbutton1=new JButton();
		timingtoolbutton2=new JButton();
		timingtablepanel=new JPanel();
		
		validationpanel=new JPanel();
		validationlabelpanel=new JPanel();
		validationlabel=new JLabel();
		validationtoolpanel=new JPanel();
		validationtoolbutton1=new JButton();
		validationtoolbutton2=new JButton();
		
		titlepanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(142, 155, 188)));
//		treepanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(142, 155, 188)));
		validationpanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, new Color(142, 155, 188)));
		
		initTitlePanel();
		
		initTreePanel();
		
//		GridBagLayout layout=new GridBagLayout();
//		this.setLayout(layout);
//		this.add(titlepanel);
//		this.add(treepanel);
//		layout.setConstraints(titlepanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//		layout.setConstraints(treepanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		GridBagLayout layout=new GridBagLayout();
		this.setLayout(layout);
		this.add(titlepanel);
		this.add(timinglabelpanel);
		this.add(timingscrollpanel);
		this.add(validationpanel);
		layout.setConstraints(titlepanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(timinglabelpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(timingscrollpanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(validationpanel, new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();
		
		this.setMinimumSize(new Dimension(screenWidth/8, screenHeight-100));
	
		
	}

	private void initTitlePanel() {
		// TODO Auto-generated method stub
		
		titlelabel.setText("模型转换验证");
		titlelabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		titlelabel.setForeground(new Color(255, 255, 255));
		titlelabel.setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 0));
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "triangulararrow.png");
		icon1.setImage(icon1.getImage().getScaledInstance(8,4, Image.SCALE_DEFAULT));
		titleiconlabel1.setIcon(icon1);
		titleiconlabel1.setBorder(BorderFactory.createEmptyBorder(4, 4,4, 6));
		ImageIcon icon2 = new ImageIcon(path + "downarrow.png");
		icon2.setImage(icon2.getImage().getScaledInstance(7,11, Image.SCALE_DEFAULT));
		titleiconlabel2.setIcon(icon2);
		titleiconlabel2.setBorder(BorderFactory.createEmptyBorder(4, 4,4, 4));
		ImageIcon icon3 = new ImageIcon(path + "fork.png");
		icon3.setImage(icon3.getImage().getScaledInstance(10,8, Image.SCALE_DEFAULT));
		titleiconlabel3.setIcon(icon3);
		titleiconlabel3.setBorder(BorderFactory.createEmptyBorder(4, 4,4, 4));
		
		titleiconlabel3.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				mainFrame.getJs1().setDividerSize(0);
				mainFrame.getOpreationPart().setVisible(false);
				mainFrame.getOneTouchExpandablePanel().setFlag1(0);
				mainFrame.getOneTouchExpandablePanel().setLocation1(mainFrame.getJs1().getDividerLocation());
				
			}
		});
		
		titleiconlabelpanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		titleiconlabelpanel.setOpaque(false);
		titleiconlabelpanel.add(titleiconlabel1);
		titleiconlabelpanel.add(titleiconlabel2);
		titleiconlabelpanel.add(titleiconlabel3);

		titlepanel.setLayout(new BorderLayout());
		titlepanel.setBackground(new Color(77, 96, 130));
		titlepanel.setPreferredSize(new Dimension(100, 24));
		titlepanel.setMinimumSize(new Dimension(100, 24));
		titlepanel.add(titlelabel,BorderLayout.WEST);
		titlepanel.add(titleiconlabelpanel,BorderLayout.EAST);

	}

	private void initTreePanel() {
		// TODO Auto-generated method stub
		
		initTimingPanel();
		
		initValidationPanel();
		
//		GridBagLayout layout=new GridBagLayout();
//		treepanel.setLayout(layout);
//		treepanel.add(timingpanel);
//		treepanel.add(validationpanel);
//		layout.setConstraints(timingpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		layout.setConstraints(validationpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
	}

	private void initTimingPanel() {
		// TODO Auto-generated method stub
		
		timinglabel.setText("时序图");
		timinglabel.setForeground(new Color(0,0,0));
		timinglabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		timinglabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		
		initTimingToolPanel();
		
		timinglabelpanel.setBackground(new Color(207, 214, 229));
		timinglabelpanel.setLayout(new BorderLayout());
		timinglabelpanel.add(timinglabel, BorderLayout.WEST);
		timinglabelpanel.add(timingtoolpanel, BorderLayout.EAST);
		timinglabelpanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(142, 155, 188)));
		timinglabelpanel.setPreferredSize(new Dimension(100, 29));
		timinglabelpanel.setMaximumSize(new Dimension(100, 29));
		timinglabelpanel.setMinimumSize(new Dimension(100, 29));
		
		initTimingTablePanel();
		
		addDataToTimingTable();
		
//		timingpanel.setLayout(new BorderLayout());
//		timingpanel.add(timinglabelpanel, BorderLayout.NORTH);
//		timingpanel.add(timingscrollpanel,BorderLayout.CENTER);
		
//		GridBagLayout layout=new GridBagLayout();
//		timingpanel.setLayout(layout);
//		timingpanel.add(timinglabelpanel);
//		timingpanel.add(timingscrollpanel);
//		layout.setConstraints(timinglabelpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//		layout.setConstraints(timingscrollpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
	}

	private void addDataToTimingTable() {
		// TODO Auto-generated method stub
		
		while(timingtablemodel.getRowCount()>0){
			timingtablemodel.removeRow(timingtablemodel.getRowCount()-1);
		}
		
		
		for(String str:timinglists){
			Object[] rowData={str};
			timingtablemodel.addRow(rowData);
		}
		
		
	}

	private void initTimingTablePanel() {
		// TODO Auto-generated method stub
		timingtablemodel=new DefaultTableModel(0, 1){
			@Override  
            public boolean isCellEditable(int row,int column){  
                return false;  
            } 
		};
		
		timingtable=new JTable(timingtablemodel);
		
		timingtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		timingtable.setGridColor(Color.BLACK);
		timingtable.setShowGrid(true);
		timingtable.setShowHorizontalLines(true);
		timingtable.setShowVerticalLines(false);
		timingtable.setFillsViewportHeight(true);
		timingtable.setRowHeight(24);
		timingtable.doLayout();
		
		timingtable.getColumnModel().getColumn(0).setCellEditor(new MyLabelCellEditor());
		timingtable.getColumnModel().getColumn(0).setCellRenderer(new MyGeneralLabelRenderer());
		
		timingtable.getTableHeader().setVisible(false);  
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();  
        renderer.setPreferredSize(new Dimension(0, 0));  
        timingtable.getTableHeader().setDefaultRenderer(renderer);
        
        timingtable.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount()==2){

					String baseUrl = "D:\\ModelDriverProjectFile\\TimingDiagram\\Violet\\";
					String filename=(String) timingtablemodel.getValueAt(timingtable.getSelectedRow(), timingtable.getSelectedColumn());
					String path = baseUrl + filename + ".timing.violet.xml";
//					System.out.println("-----path:-----"+path);
					
					showTimingDiagram(path);
					
					showUppaalDiagram(filename);
					
//					String filePath="D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\uppaalTest1.uppaal.violet.xml";
					
					try {
						
						System.out.println("--------------------------TimingEAtoUppaal.getDiagramDataName()"+TimingEAtoUppaal.getDiagramDataName()+".xml");
						
						ev=new ExistVerification(TimingEAtoUppaal.getDiagramDataName()+".xml");
						
						uppaalmessagelist=ev.getMessages();
						System.out.println("-------------------");
						for(UppaalTransition u:uppaalmessagelist){
							System.out.println(u.toString());
						}
						System.out.println("-------------------");
						
						
						RowStringsForDisplay row=CompareEAtoAutomata.compareFromXMLPath(path, TimingEAtoUppaal.getDiagramDataName()+".xml");
						
						System.out.println("##################################");
						
						ArrayList<PathTuple> pathtuple=ev.getPath();
						
						System.out.println(pathtuple.size());
						
						for(PathTuple pt:pathtuple){
							
							System.out.println(pt.getLocation().toString());
							if(pt.getTransition()!=null){
								System.out.println(pt.getTransition().toString());
							}
							
							
						}
						
						
						ArrayList<Integer> times = CompareEAtoAutomata.verificationPathTupleTime(ev.getPath());
						System.out.println("##################################");
						System.out.println(times.size());
						
						for(Integer i:times){
							System.out.println(i);
						}
						
						System.out.println("##################################");
						
						ValidationPathTupleTimePanel vpttp=new ValidationPathTupleTimePanel(pathtuple, times);
						mainFrame.getValidationResultPanel().getThreeresultpanel().removeAll();
						mainFrame.getValidationResultPanel().getThreeresultpanel().add(vpttp);
						
						mainFrame.getValidationResultPanel().getThreenamelabel().setText("自动机路径累加的时间和:"+vpttp.getSumtimes());
						
						
						
						System.out.println("##################################");
						
						for(int i=0;i<times.size();i++){
							
							if(i%2==0){
								System.out.println("location:"+pathtuple.get(i/2).getLocation().getName()+" times:"+times.get(i));
							}
							else{
								System.out.println("transition:"+pathtuple.get(i/2).getTransition().getName()+" times:"+times.get(i));
							}
							
							
							
						}
						
//						System.out.println("##################################");
//						List<LocationVerificationDisplay> locationVerificationDisplays = ev.verificationLocationTimeDuration();
//				    	List<TransitionVerificationDisplay> transitionVerificationDisplays = ev.verificationTransitionTimeDuration();
//				    	
//				    	System.out.println(locationVerificationDisplays.size());
//				    	for(LocationVerificationDisplay lvd:locationVerificationDisplays){
//				    		System.out.println(lvd.toString());
//				    	}
//				    	System.out.println("##################################");
//				    	System.out.println(transitionVerificationDisplays.size());
//				    	for(TransitionVerificationDisplay tvd:transitionVerificationDisplays){
//				    		System.out.println(tvd.toString());
//				    	}
				    	
				    	System.out.println("##################################");
				    	System.out.println(ev.getVerificationResult());
						
						
						System.out.println("##################################");
						
						
						
						
						ArrayList<StateCompare> stateCompareList =row.getStateCompareList();
						ArrayList<MessageCompare> messageCompareList = row.getMessageCompareList();
						
						mainFrame.getValidationResultPanel().getTwonamelabel().setText("共找到"+stateCompareList.size()+"条状态和"+messageCompareList.size()+"条消息");
						
						System.out.println("***************************");
						System.out.println(stateCompareList.size());
						System.out.println("---------------------------");
						
						mainFrame.getValidationResultPanel().getStatelocationresultpanel().removeAll();
						
						System.out.println("++++++++++++++++++++");
						
						JPanel resultpanel1=new JPanel();
						JPanel emptypanel1=new JPanel();
						resultpanel1.setOpaque(false);
						emptypanel1.setOpaque(false);
						
						GridBagLayout layout1 = new GridBagLayout();
						resultpanel1.setLayout(layout1);
						int i=0;
						
						ArrayList<String> headlist1=new ArrayList<String>();
//						headlist1.add("EAStateInfo");
//						headlist1.add("UppaalLocation");
//						headlist1.add("Result");
						headlist1.add("时序图");
						headlist1.add("时间自动机");
						headlist1.add("结果");
						
						TableHeadPanel thpanel1=new TableHeadPanel(headlist1);
						resultpanel1.add(thpanel1);
						layout1.setConstraints(thpanel1, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
						
						
						for(StateCompare s:stateCompareList){
							System.out.println(s.getStateInfo().toString());
							System.out.println(s.getLocation().toString());
							System.out.println(s.getResult());
							
							ValidationStateComparePanel vscpanel=new ValidationStateComparePanel(s);
							resultpanel1.add(vscpanel);
							layout1.setConstraints(vscpanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
							
							System.out.println("---------------------------");
						}
						
						resultpanel1.add(emptypanel1);
						layout1.setConstraints(emptypanel1, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
						mainFrame.getValidationResultPanel().getStatelocationresultpanel().add(resultpanel1);
						
						
						System.out.println("***************************");
						System.out.println(messageCompareList.size());
						System.out.println("---------------------------");
						
						mainFrame.getValidationResultPanel().getMessagetransitionresultpanel().removeAll();
						
						System.out.println("++++++++++++++++++++");
						
						JPanel resultpanel2=new JPanel();
						JPanel emptypanel2=new JPanel();
						resultpanel2.setOpaque(false);
						emptypanel2.setOpaque(false);
						
						GridBagLayout layout2 = new GridBagLayout();
						resultpanel2.setLayout(layout2);
						int j=0;
						
						ArrayList<String> headlist2=new ArrayList<String>();
//						headlist2.add("EAMessage");
//						headlist2.add("UppaalTransition");
//						headlist2.add("Result");
						headlist2.add("时序图");
						headlist2.add("时间自动机");
						headlist2.add("结果");
						
						TableHeadPanel thpanel2=new TableHeadPanel(headlist2);
						resultpanel2.add(thpanel2);
						layout2.setConstraints(thpanel2, new GBC(0, j++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
						
						for(MessageCompare m:messageCompareList){
							System.out.println(m.getMessage().toString());
							System.out.println(m.getTransition().toString());
							System.out.println(m.getResult());
							
							ValidationMessageComparePanel vmcpanel=new ValidationMessageComparePanel(m);
							resultpanel2.add(vmcpanel);
							layout2.setConstraints(vmcpanel, new GBC(0, j++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
							
							System.out.println("---------------------------");
						}
						
						resultpanel2.add(emptypanel2);
						layout2.setConstraints(emptypanel2, new GBC(0, j++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
						mainFrame.getValidationResultPanel().getMessagetransitionresultpanel().add(resultpanel2);
						
						System.out.println("***************************");
						
						
						mainFrame.getValidationResultPanel().ChangeRepaint();
						
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
					
					addCheckBoxToValidationCheckboxPanel();
					
//					mainFrame.getStepSixCenterTabbedPane().getTimingDiagramButton().doClick();
					mainFrame.getStepSixCenterTabbedPane().getUppaalDiagramButton().doClick();
					
					ChangeRepaint();
					
					mainFrame.getStepSixCenterTabbedPane().ChangeRepaint();
					
				}
			}
        	
		});
		
		timingtablepanel.setLayout(new GridLayout());
		timingtablepanel.add(timingtable);
		timingtable.setBackground(new Color(238, 238, 242));
		timingtable.setBorder(null);
		timingtablepanel.setBorder(null);
		
		timingscrollpanel=new JScrollPane(timingtablepanel);
		timingscrollpanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(142, 155, 188)));
		timingscrollpanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		timingscrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
	}

	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
		
	}

	protected void addCheckBoxToValidationCheckboxPanel() {
		// TODO Auto-generated method stub

		validationcheckboxpanel.removeAll();
		
//		ItemListener itemListener = new ItemListener() {
//			
//            JCheckBox jCheckBox;
//
//			@Override
//			public void itemStateChanged(ItemEvent e) {
//				// TODO Auto-generated method stub
//				
//				jCheckBox = (JCheckBox) e.getSource();
//				 
//                if (jCheckBox.isSelected()) {
//                	selecteduppaalmessagelist.add(uppaalmessagelist.get(uppaalMessageCheckBoxList.indexOf(jCheckBox)));
//                } else {
//                	selecteduppaalmessagelist.remove(selecteduppaalmessagelist.indexOf(uppaalmessagelist.get(uppaalMessageCheckBoxList.indexOf(jCheckBox))));
//                }
//				
//			}
//        };
        
        		
        uppaalMessageCheckBoxList=new ArrayList<JCheckBox>();
        validationcheckboxpanel.add(Box.createVerticalStrut(5));
		for(int i=0;i<uppaalmessagelist.size();i++){
			JCheckBox jcb=new JCheckBox(uppaalmessagelist.get(i).getName());
//			jcb.addItemListener(itemListener);
			jcb.setOpaque(false);
			uppaalMessageCheckBoxList.add(i, jcb);
//			Object[]data={new JCheckBox(validationlists.get(i))};
//			Object[]data={validationlists.get(i)};
//			dtmDemo.addRow(data);
			validationcheckboxpanel.add(Box.createVerticalStrut(0));
			validationcheckboxpanel.add(jcb);
		}		
        
//		uppaalMessageCheckBoxList=new JCheckBox[uppaalmessagelist.size()];
//		for(int i=0;i<uppaalmessagelist.size();i++){
//			uppaalMessageCheckBoxList[i]=new JCheckBox(uppaalmessagelist.get(i).getName());
//			uppaalMessageCheckBoxList[i].addItemListener(itemListener);
//			uppaalMessageCheckBoxList[i].setOpaque(false);
////			Object[]data={new JCheckBox(validationlists.get(i))};
////			Object[]data={validationlists.get(i)};
////			dtmDemo.addRow(data);
//			validationcheckboxpanel.add(Box.createVerticalStrut(7));
//			validationcheckboxpanel.add(uppaalMessageCheckBoxList[i]);
//		}
		
	}

	protected void showUppaalDiagram(String filename) {
		// TODO Auto-generated method stub
		
//		IWorkspace workspace=null;
		uppaalworkspace=mainFrame.getStepTwoCenterTabbedPane().getTimingToUppaalTabbedPane().getTiminganduppaalmap().get(filename);
		
		
			
		String baseUrl = "D:\\ModelDriverProjectFile\\TimingDiagram\\Violet\\";
		String path = baseUrl + filename + ".timing.violet.xml";

		try {

			TimingEAtoUppaal.transEA(path);
			LayoutUppaal.layout(TimingEAtoUppaal.getDiagramDataName() + ".xml");
			String filename1 = TransToVioletUppaal.TransToViolet();

			System.out.println("filename1:" + filename1 + " TimingEAtoUppaal.getDiagramDataName():"
					+ TimingEAtoUppaal.getDiagramDataName());

			if (uppaalworkspace == null) {
				GraphFile fGraphFile1 = ImportByDoubleClick.importFileByDoubleClick("UPPAAL", filename1);
				uppaalworkspace = new Workspace(fGraphFile1);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		
		mainFrame.getStepSixCenterTabbedPane().getDiagramPanel().removeAll();
		mainFrame.getStepSixCenterTabbedPane().getUppaalDiagramTabbedPane().removeAll();
		
		ValidationToolPanel toolPanel = new ValidationToolPanel(mainFrame,uppaalworkspace);

		MoviePanel moviePanel = new MoviePanel();

		GridBagLayout layout = new GridBagLayout();
		mainFrame.getStepSixCenterTabbedPane().getUppaalDiagramTabbedPane().setLayout(layout);
		mainFrame.getStepSixCenterTabbedPane().getUppaalDiagramTabbedPane().add(toolPanel);
		mainFrame.getStepSixCenterTabbedPane().getUppaalDiagramTabbedPane().add(moviePanel);
		mainFrame.getStepSixCenterTabbedPane().getUppaalDiagramTabbedPane().add(uppaalworkspace.getAWTComponent());
		layout.setConstraints(toolPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(moviePanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(uppaalworkspace.getAWTComponent(),new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		
	}

	protected void showTimingDiagram(String path) {
		// TODO Auto-generated method stub
		
		File file=new File(path);
		IFile selectedFile=null;
		try {
			selectedFile = new LocalFile(file);
			boolean flag = !(selectedFile.getFilename().contains("EA"));// 是EA格式的文件

			// 如果是平台保存的XML文件
			IGraphFile graphFile = null;
			// //增加转换的方法11

			selectedFile = FileMenu.exchangeFile(selectedFile, graphFile, flag);

			graphFile = new GraphFile(selectedFile);

			// 显示文件图形
			IWorkspace workspace = new Workspace(graphFile);
			
			mainFrame.getStepSixCenterTabbedPane().getDiagramPanel().removeAll();
			mainFrame.getStepSixCenterTabbedPane().getTimingDiagramTabbedPane().removeAll();
			
			ToolPanel toolPanel = new ToolPanel(mainFrame,workspace);
			
			MoviePanel moviePanel = new MoviePanel();

			GridBagLayout layout = new GridBagLayout();
			mainFrame.getStepSixCenterTabbedPane().getTimingDiagramTabbedPane().setLayout(layout);
			mainFrame.getStepSixCenterTabbedPane().getTimingDiagramTabbedPane().add(toolPanel);
			mainFrame.getStepSixCenterTabbedPane().getTimingDiagramTabbedPane().add(moviePanel);
			mainFrame.getStepSixCenterTabbedPane().getTimingDiagramTabbedPane().add(workspace.getAWTComponent());
			layout.setConstraints(toolPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
			layout.setConstraints(moviePanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
			layout.setConstraints(workspace.getAWTComponent(),new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
			
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	private void initTimingToolPanel() {
		// TODO Auto-generated method stub
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "dropdown.png");
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(path + "refresh.png");
		icon2.setImage(icon2.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));

		timingtoolbutton1.setIcon(icon1);
		timingtoolbutton1.setFocusable(false);
		timingtoolbutton1.setContentAreaFilled(false);
		timingtoolbutton1.setBorderPainted(false);
		timingtoolbutton1.addMouseListener(new ButtonMouseListener());
		timingtoolbutton1.setPreferredSize(new Dimension(21,21));
		timingtoolbutton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if(timingscrollpanel.isVisible()){
					timingscrollpanel.setVisible(false);
					ChangeRepaint();
				}
				else {
					timingscrollpanel.setVisible(true);
					ChangeRepaint();
				}
				
			}
		});
		
		timingtoolbutton2.setIcon(icon2);
		timingtoolbutton2.setFocusable(false);
		timingtoolbutton2.setContentAreaFilled(false);
		timingtoolbutton2.setBorderPainted(false);
		timingtoolbutton2.addMouseListener(new ButtonMouseListener());
		timingtoolbutton2.setPreferredSize(new Dimension(21,21));
		timingtoolbutton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				timinglists.clear();
				initFileList();
				addDataToTimingTable();
				
			}
		});

		timingtoolpanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,4));
		timingtoolpanel.setOpaque(false);
		timingtoolpanel.add(timingtoolbutton2);
		timingtoolpanel.add(timingtoolbutton1);
		
	}

	private void initValidationPanel() {
		// TODO Auto-generated method stub
		
		validationlabel.setText("存在性验证");
		validationlabel.setForeground(new Color(0,0,0));
		validationlabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		validationlabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		
		initValidationToolPanel();
		
		validationlabelpanel.setBackground(new Color(207, 214, 229));
		validationlabelpanel.setLayout(new BorderLayout());
		validationlabelpanel.add(validationlabel, BorderLayout.WEST);
		validationlabelpanel.add(validationtoolpanel, BorderLayout.EAST);
		validationlabelpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(142, 155, 188)));
		validationlabelpanel.setPreferredSize(new Dimension(100, 29));
		validationlabelpanel.setMaximumSize(new Dimension(100, 29));
		validationlabelpanel.setMinimumSize(new Dimension(100, 29));
		
		initValidationLabelTabPanel();
		
		initValidationCheckboxPanel();
		
//		validationpanel.setLayout(new BorderLayout());
//		validationpanel.add(validationlabelpanel, BorderLayout.NORTH);
		
		validationalllabelpanel=new JPanel();
		
		GridBagLayout layout=new GridBagLayout();
		validationalllabelpanel.setLayout(layout);
		validationalllabelpanel.add(validationlabelpanel);
		validationalllabelpanel.add(validationlabeltabpanel);
		layout.setConstraints(validationlabelpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(validationlabeltabpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		
		validationpanel.setLayout(new BorderLayout());
		validationpanel.add(validationalllabelpanel,BorderLayout.NORTH);
		validationpanel.add(validationscrollpanel,BorderLayout.CENTER);

		
	}

	private void initValidationLabelTabPanel() {
		// TODO Auto-generated method stub
		
		validationlabeltabpanel=new JPanel();
		validationlabeltabpanel1=new JPanel();
		validationlabeltabpanel2=new JPanel();
		validationlabeltabpanel3=new JPanel();
		validationlabeltabpanel4=new JPanel();
		
		validationlabeltab1=new JLabel();
		validationlabeltab2=new JLabel();
		validationlabeltab3=new JLabel();
		validationlabeltab4=new JLabel();
		
		validationlabeltab1.setText("存在");
		validationlabeltab1.setForeground(new Color(0,0,0));
		validationlabeltab1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		validationlabeltab1.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		validationlabeltab1.setFocusable(false);
		validationlabeltab1.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				validationlabeltab1.setForeground(new Color(0, 0, 0));
				validationlabeltabpanel1.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (validationlabeltabindex != 1) {
					validationlabeltab1.setForeground(new Color(255, 255, 255));
					validationlabeltabpanel1.setBackground(new Color(77, 96, 130));
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (validationlabeltabindex != 1) {
					validationlabeltabpanel1.setBackground(new Color(134, 161, 209));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				setvalidationlabeltabpanelrepait();
				validationlabeltab1.setForeground(new Color(0, 0, 0));
				validationlabeltabpanel1.setBackground(new Color(255, 255, 255));
				validationlabeltabpanel1.setBorder(BorderFactory.createMatteBorder(0,0,1,1, new Color(142, 155, 188)));
				validationlabeltabindex = 1;
				
				validationlabel.setText("存在性验证");

			}
		});
		
		validationlabeltab2.setText("顺序");
		validationlabeltab2.setForeground(new Color(255, 255, 255));
		validationlabeltab2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		validationlabeltab2.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		validationlabeltab2.setFocusable(false);
		validationlabeltab2.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				validationlabeltab2.setForeground(new Color(0, 0, 0));
				validationlabeltabpanel2.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (validationlabeltabindex != 2) {
					validationlabeltab2.setForeground(new Color(255, 255, 255));
					validationlabeltabpanel2.setBackground(new Color(77, 96, 130));
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (validationlabeltabindex != 2) {
					validationlabeltabpanel2.setBackground(new Color(134, 161, 209));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				setvalidationlabeltabpanelrepait();
				validationlabeltab2.setForeground(new Color(0, 0, 0));
				validationlabeltabpanel2.setBackground(new Color(255, 255, 255));
				validationlabeltabpanel2.setBorder(BorderFactory.createMatteBorder(0,1,1,1, new Color(142, 155, 188)));
				validationlabeltabindex = 2;
				
				validationlabel.setText("顺序性验证");

			}
		});
		
		validationlabeltab3.setText("逆向");
		validationlabeltab3.setForeground(new Color(255, 255, 255));
		validationlabeltab3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		validationlabeltab3.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		validationlabeltab3.setFocusable(false);
		validationlabeltab3.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				validationlabeltab3.setForeground(new Color(0, 0, 0));
				validationlabeltabpanel3.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (validationlabeltabindex != 3) {
					validationlabeltab3.setForeground(new Color(255, 255, 255));
					validationlabeltabpanel3.setBackground(new Color(77, 96, 130));
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (validationlabeltabindex != 3) {
					validationlabeltabpanel3.setBackground(new Color(134, 161, 209));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				setvalidationlabeltabpanelrepait();
				validationlabeltab3.setForeground(new Color(0, 0, 0));
				validationlabeltabpanel3.setBackground(new Color(255, 255, 255));
				validationlabeltabpanel3.setBorder(BorderFactory.createMatteBorder(0,1,1,1, new Color(142, 155, 188)));
				validationlabeltabindex = 3;

			}
		});
		
		validationlabeltab4.setText("双向");
		validationlabeltab4.setForeground(new Color(255, 255, 255));
		validationlabeltab4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		validationlabeltab4.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		validationlabeltab4.setFocusable(false);
		validationlabeltab4.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				validationlabeltab4.setForeground(new Color(0, 0, 0));
				validationlabeltabpanel4.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (validationlabeltabindex != 4) {
					validationlabeltab4.setForeground(new Color(255, 255, 255));
					validationlabeltabpanel4.setBackground(new Color(77, 96, 130));
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (validationlabeltabindex != 4) {
					validationlabeltabpanel4.setBackground(new Color(134, 161, 209));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				setvalidationlabeltabpanelrepait();
				validationlabeltab4.setForeground(new Color(0, 0, 0));
				validationlabeltabpanel4.setBackground(new Color(255, 255, 255));
				validationlabeltabpanel4.setBorder(BorderFactory.createMatteBorder(0,1,1,1, new Color(142, 155, 188)));
				validationlabeltabindex = 4;

			}
		});
		
		validationlabeltabpanel1.setLayout(new GridLayout());
		validationlabeltabpanel1.setBackground(new Color(255,255,255));
		validationlabeltabpanel1.setBorder(BorderFactory.createMatteBorder(0,0,1,1, new Color(142, 155, 188)));
		validationlabeltabpanel1.setPreferredSize(new Dimension(40, 23));
		validationlabeltabpanel1.add(validationlabeltab1);
		validationlabeltabpanel2.setLayout(new GridLayout());
		validationlabeltabpanel2.setBackground(new Color(77, 96, 130));
		validationlabeltabpanel2.setPreferredSize(new Dimension(40, 23));
		validationlabeltabpanel2.add(validationlabeltab2);
		validationlabeltabpanel3.setLayout(new GridLayout());
		validationlabeltabpanel3.setBackground(new Color(77, 96, 130));
		validationlabeltabpanel3.setPreferredSize(new Dimension(40, 23));
		validationlabeltabpanel3.add(validationlabeltab3);
		validationlabeltabpanel4.setLayout(new GridLayout());
		validationlabeltabpanel4.setBackground(new Color(77, 96, 130));
		validationlabeltabpanel4.setPreferredSize(new Dimension(40, 23));
		validationlabeltabpanel4.add(validationlabeltab4);

		validationlabeltabpanel.setBackground(new Color(41, 57, 85));
		validationlabeltabpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		validationlabeltabpanel.add(validationlabeltabpanel1);
		validationlabeltabpanel.add(validationlabeltabpanel2);
//		validationlabeltabpanel.add(validationlabeltabpanel3);
//		validationlabeltabpanel.add(validationlabeltabpanel4);
		
		validationlabeltabpanel.setPreferredSize(new Dimension(100, 22));
		validationlabeltabpanel.setMinimumSize(new Dimension(100, 22));
		
		
	}

	protected void setvalidationlabeltabpanelrepait() {
		// TODO Auto-generated method stub
		
		validationlabeltab1.setForeground(new Color(255, 255, 255));
		validationlabeltabpanel1.setBackground(new Color(77, 96, 130));
		validationlabeltabpanel1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(77, 96, 130)));
		validationlabeltab2.setForeground(new Color(255, 255, 255));
		validationlabeltabpanel2.setBackground(new Color(77, 96, 130));
		validationlabeltabpanel2.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(77, 96, 130)));
		validationlabeltab3.setForeground(new Color(255, 255, 255));
		validationlabeltabpanel3.setBackground(new Color(77, 96, 130));
		validationlabeltabpanel3.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(77, 96, 130)));
		validationlabeltab4.setForeground(new Color(255, 255, 255));
		validationlabeltabpanel4.setBackground(new Color(77, 96, 130));
		validationlabeltabpanel4.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(77, 96, 130)));
		
	}

	private void initValidationCheckboxPanel() {
		// TODO Auto-generated method stub
		
		validationcheckboxpanel=new JPanel();
		
		validationcheckboxpanel.setLayout(new BoxLayout(validationcheckboxpanel, BoxLayout.Y_AXIS));
		validationcheckboxpanel.setBorder(BorderFactory.createEmptyBorder(0, 7, 0, 0));
		validationcheckboxpanel.setBackground(new Color(255, 255, 255));
		
		validationscrollpanel=new JScrollPane(validationcheckboxpanel);
		validationscrollpanel.setBorder(null);
		validationscrollpanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		validationscrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
	}

	private void initValidationToolPanel() {
		// TODO Auto-generated method stub
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "start.png");
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(path + "dropdown.png");
		icon2.setImage(icon2.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));

		validationtoolbutton1.setIcon(icon1);
		validationtoolbutton1.setFocusable(false);
		validationtoolbutton1.setContentAreaFilled(false);
		validationtoolbutton1.setBorderPainted(false);
		validationtoolbutton1.addMouseListener(new ButtonMouseListener());
		validationtoolbutton1.setPreferredSize(new Dimension(21,21));
		validationtoolbutton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				selecteduppaalmessagelist.clear();
				for(JCheckBox jcb:uppaalMessageCheckBoxList){
					if(jcb.isSelected()){
						selecteduppaalmessagelist.add(uppaalmessagelist.get(uppaalMessageCheckBoxList.indexOf(jcb)));
					}
				}
				
				
				if(validationlabeltabindex==1){
					
					List<UppaalTransition> l = null;
					l=ev.getSelectedTransitionsIfExist(selecteduppaalmessagelist);
					
					if(l==null){
						System.out.println("list is null");
					}
					else{
						System.out.println("   ----------   ");
						
						mainFrame.getValidationResultPanel().getOnenamelabel().setText("共找到"+l.size()+"条消息：");
						
						mainFrame.getValidationResultPanel().getOneresultpanel().removeAll();
//						mainFrame.getValidationResultPanel().getResultpanel().setLayout(new BoxLayout(mainFrame.getValidationResultPanel().getResultpanel(), BoxLayout.Y_AXIS));
						
						System.out.println(mainFrame.getValidationResultPanel().getOneresultpanel().size());
						System.out.println("++++++++++++++++++++");
						
						JPanel resultpanel=new JPanel();
						JPanel emptypanel=new JPanel();
						resultpanel.setOpaque(false);
						emptypanel.setOpaque(false);
						
						GridBagLayout layout = new GridBagLayout();
						resultpanel.setLayout(layout);
						int i=0;
						for(UppaalTransition u:l){
							System.out.println(u);
							
							ValidationTransitionMessagePanel vtmpanel=new ValidationTransitionMessagePanel(u);
							resultpanel.add(vtmpanel);
							layout.setConstraints(vtmpanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
							
						}
						resultpanel.add(emptypanel);
						layout.setConstraints(emptypanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//						mainFrame.getValidationResultPanel().getResultpanel().add(Box.createVerticalGlue());
						mainFrame.getValidationResultPanel().getOneresultpanel().add(resultpanel);
						mainFrame.getValidationResultPanel().ChangeRepaint();
						System.out.println("++++++++++++++++++++");
						
					}
					
				}else if(validationlabeltabindex==2){
					
					List<PathTuple> l=null;
					l=ev.getPathOfSelectedTransitions(selecteduppaalmessagelist);
					
					if(l==null){
						System.out.println("list is null");
					}
					else{
						System.out.println("   ----------   ");
						
						System.out.println("++++++++++++++++++++");
						for(PathTuple u:l){
							System.out.println(u.getLocation().toString()+ " --- "+u.getTransition().toString());
						}
						System.out.println("++++++++++++++++++++");
						
						
					}
				}
				
				
				
//				System.out.println("++++++++++++++++++++");
//				System.out.println(validationlabeltabindex);
//				for(UppaalTransition u:selecteduppaalmessagelist){
//					System.out.println(u);
//				}
//				System.out.println("++++++++++++++++++++");
			}
		});
		
		validationtoolbutton2.setIcon(icon2);
		validationtoolbutton2.setFocusable(false);
		validationtoolbutton2.setContentAreaFilled(false);
		validationtoolbutton2.setBorderPainted(false);
		validationtoolbutton2.addMouseListener(new ButtonMouseListener());
		validationtoolbutton2.setPreferredSize(new Dimension(21,21));
		validationtoolbutton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if(validationscrollpanel.isVisible()){
					validationlabeltabpanel.setVisible(false);
					validationscrollpanel.setVisible(false);
					ChangeRepaint();
				}
				else{
					validationlabeltabpanel.setVisible(true);
					validationscrollpanel.setVisible(true);
					ChangeRepaint();
				}
				
			}
		});
		
		validationtoolpanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,4));
		validationtoolpanel.setOpaque(false);
//		validationtoolpanel.add(validationtoolbutton1);
		validationtoolpanel.add(validationtoolbutton2);

		
	}
	
	public void initFileList() {
	
		File[] timingFilelists = getAllFileByDiagramType("timing");
	    for(File timingFile : timingFilelists)
	    {
	    	String fileName=timingFile.getName();
//	    	fileName.substring(0, fileName.lastIndexOf(".xml"));
	    	if(fileName.lastIndexOf(".timing.violet.xml")>0){
	    		timinglists.add(fileName.substring(0,fileName.lastIndexOf(".timing.violet.xml") ));
	    	}
	    	
//	    	timinglists.add(fileName);
	    }
//	    for(File tdFile : tdFilelists)
//	    {
//	    	String fileName=tdFile.getName();
//	    	tdlists.add(fileName);
//	    }
//	    for(File uppaalFile:uppaalFilelists)
//	    {
//	    	String fileName=uppaalFile.getName();
//	    	uppaallists.add(fileName);
//	    }
	}
	/**
	  * 根据类型获取文件夹下的所有文件
	  * @param type
	  * @return
	  */
	 public   File[] getAllFileByDiagramType(String type){
//		 File f =FileSystemView.getFileSystemView().getHomeDirectory();
//		String s =f .getAbsolutePath();
		 String baseUrl ="D://ModelDriverProjectFile";
//		String baseUrl =s+"//ModelDriverProjectFile";
//		File bFile = new File(baseUrl);
//		if(!bFile.exists()){
//			bFile.mkdirs();
//		}
		 File[] fList =null;
		 File file=null;
		 if("senquence".equals(type)){
			 file =new File(baseUrl+"\\SequenceDiagram\\Violet");
			 fList= file.listFiles();
		 }else if("timing".equals(type)){
			file =new File(baseUrl+"\\TimingDiagram\\Violet");
			 fList= file.listFiles();
		 }else if("UPPAAL2".equals(type)){
			 //第二步的UPPAAL涉及的自动机
			 file =new File(baseUrl+"\\UPPAAL\\2.UML Model Transfer");
			 fList=file.listFiles();
		 }else if("UPPAAL3".equals(type)){
			 //第三步的UPPAAL涉及的自动机
			 file =new File(baseUrl+"\\UPPAAL\\3.Abstract TestCase");
			 fList= file.listFiles();
		 }else if("UPPAAL4".equals(type)){
			 //第四步的UPPAAL涉及的自动机
			 file =new File(baseUrl+"\\UPPAAL\\4.Real TestCase");
			 fList=file.listFiles();
		 }else if("state".equals(type)){
			 file =new File(baseUrl+"\\StateDiagram\\Violet");
			 fList=file.listFiles();
		 }else if("usecase".equals(type)){
			 file =new File(baseUrl+"\\UsecaseDiagram\\Violet");
			 fList= file.listFiles();
		 }else if("class".equals(type)){
			 file =new File(baseUrl+"\\ClassDiagram\\Violet");
			 fList= file.listFiles();
		 }else if("activity".equals(type)){
			 file =new File(baseUrl+"\\ActivityDiagram\\Violet");
			 fList=file.listFiles();
		 }
		 return fList;
	}

	public static MainFrame getMainFrame() {
		return mainFrame;
	}

	public int getValidationlabeltabindex() {
		return validationlabeltabindex;
	}

	public List<UppaalTransition> getUppaalmessagelist() {
		return uppaalmessagelist;
	}

	public List<JCheckBox> getUppaalMessageCheckBoxList() {
		return uppaalMessageCheckBoxList;
	}

	public static ExistVerification getEv() {
		return ev;
	}

	public IWorkspace getUppaalworkspace() {
		return uppaalworkspace;
	}
	
	

	
	
}

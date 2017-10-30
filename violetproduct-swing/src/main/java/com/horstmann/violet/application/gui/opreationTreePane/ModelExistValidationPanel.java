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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.plaf.basic.BasicScrollPaneUI.VSBChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.dom4j.DocumentException;

import com.horstmann.violet.application.consolepart.TableHeadPanel;
import com.horstmann.violet.application.consolepart.ValidationLocationMessagePanel;
import com.horstmann.violet.application.consolepart.ValidationMessageComparePanel;
import com.horstmann.violet.application.consolepart.ValidationPathTupleTimePanel;
import com.horstmann.violet.application.consolepart.ValidationStateComparePanel;
import com.horstmann.violet.application.consolepart.ValidationTransitionMessagePanel;
import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.StepSixCenterTabbedPane;
import com.horstmann.violet.application.gui.StepThreeCenterTabbedPane;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.ButtonTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.MoviePanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.MyLabelCellEditor;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.MyUppaalLabelRender;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.ToolPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.UppaalToolPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.ValidationToolPanel;
import com.horstmann.violet.application.gui.util.lmr.Evaluation.Evaluation;
import com.horstmann.violet.application.gui.util.tanchao.TranMessageColorize;
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
	private JButton timingtoolbutton3;
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
	private JButton validationlabeltab1;
	private JButton validationlabeltab2;
	private JButton validationlabeltab3;
	private JButton validationlabeltab4;
	
	private int validationlabeltabindex=1;
	
	private JPanel validationcheckboxpanel;
	private JScrollPane validationscrollpanel;
	private JPanel validationinfopanel;
	private JPanel emptypanel;
	
	private JPanel assesspanel;
	private JPanel assesslabelpanel;
	private JLabel assesslabel;
	private JPanel assesstoolpanel;
	private JButton assesstoolbutton1;
	private JPanel assessdealpanel;
	private JScrollPane assessscrollpanel;
	
	private JPanel assessdealpanel1;
	private JPanel assessdealpanel2;
	private JPanel assessdealpanel3;
	private JPanel assessdeallabelpanel1;
	private JPanel assessdeallabelpanel11;
	private JLabel assessdeallabel1;
	private JLabel assessdeallabel11;
	private JTextField assessdealtext11;
	private JButton assessdealbutton1;
	private JPanel assessdeallinepanel1;
	private JLabel assessdeallinelabel1;
	private JPanel assessdeallabelpanel2;
	private JPanel assessdeallabelpanel21;
	private JPanel assessdeallabelpanel22;
	private JLabel assessdeallabel2;
	private JLabel assessdeallabel21;
	private JLabel assessdeallabel22;
	private JTextField assessdealtext21;
	private JTextField assessdealtext22;
	private JButton assessdealbutton2;
	private JPanel assessdeallinepanel2;
	private JLabel assessdeallinelabel2;
	private JPanel assessdeallabelpanel3;
	private JPanel assessdeallabelpanel31;
	private JLabel assessdeallabel3;
	private JLabel assessdeallabel31;
	private JTextField assessdealtext31;
	private JButton assessdealbutton3;
	
	private ButtonGroup buttonGroup;
	private JRadioButton radioButton1;
	private JRadioButton radioButton2;
	private JRadioButton radioButton3;
	
	private List<UppaalTransition> uppaalmessagelist=new ArrayList<UppaalTransition>();
	private List<UppaalTransition> selecteduppaalmessagelist=new ArrayList<UppaalTransition>();
//	private JCheckBox[] uppaalMessageCheckBoxList;
	private List<JCheckBox> uppaalMessageCheckBoxList;
	
	private static ExistVerification ev;
	
	IWorkspace uppaalworkspace=null;
	
	private int statesuccesssum=0,statefailsum=0,messagesuccesssum=0,messagefailsum=0;
	
	private ArrayList<StateCompare> stateCompareList =new ArrayList<StateCompare>();
	private ArrayList<MessageCompare> messageCompareList = new ArrayList<MessageCompare>();
	
	private ArrayList<PathTuple> pathtuple=new ArrayList<PathTuple>();
	private ArrayList<Integer> times = new ArrayList<Integer>();
	
	
	private int uppaalType=0;//0: 1:顺序图 2:时序图
	private Evaluation evaluation;
	
	private	MoviePanel moviePanel;
	
	private List<String> uppaallists=new ArrayList<String>();
	
	private JCheckBox selectUppaalCheckBox;
	private JCheckBox[] uppaalCheckBoxList;
	
	
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
		timingtoolbutton3=new JButton();
		timingtablepanel=new JPanel();
		
		validationpanel=new JPanel();
		validationlabelpanel=new JPanel();
		validationlabel=new JLabel();
		validationtoolpanel=new JPanel();
		validationtoolbutton1=new JButton();
		validationtoolbutton2=new JButton();
		
		assesspanel=new JPanel();
		assesslabelpanel=new JPanel();
		assesslabel=new JLabel();
		assesstoolpanel=new JPanel();
		assesstoolbutton1=new JButton();
		assessdealpanel=new JPanel();
		
		titlepanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(142, 155, 188)));
//		treepanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(142, 155, 188)));
		validationpanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(142, 155, 188)));
		
		initTitlePanel();
		
		initTreePanel();
		
		GridBagLayout layout=new GridBagLayout();
		this.setLayout(layout);
		this.add(titlepanel);
		this.add(treepanel);
		layout.setConstraints(titlepanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(treepanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		JPanel emptypanel=new JPanel();
		emptypanel.setLayout(new GridLayout());
		emptypanel.setBackground(new Color(255, 255, 255));
		emptypanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(142, 155, 188)));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();
		
		this.setMinimumSize(new Dimension(screenWidth/8, screenHeight-100));
	
		
	}

	private void initTitlePanel() {
		// TODO Auto-generated method stub
		
		titlelabel.setText("模型评估");
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
		
		initAssessPanel();
		
		GridBagLayout layout=new GridBagLayout();
		treepanel.setLayout(layout);
		treepanel.add(timinglabelpanel);
		treepanel.add(timingscrollpanel);
		treepanel.add(assesspanel);
//		this.add(assesslabelpanel);
//		this.add(assessscrollpanel);
//		this.add(validationpanel);
//		this.add(emptypanel);
		layout.setConstraints(timinglabelpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(timingscrollpanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(assesspanel, new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		layout.setConstraints(assesslabelpanel, new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//		layout.setConstraints(assessscrollpanel, new GBC(0, 4, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		layout.setConstraints(validationpanel, new GBC(0, 5, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//		layout.setConstraints(emptypanel, new GBC(0, 6, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		
	}

	private void initAssessPanel() {
		// TODO Auto-generated method stub
		
		assesslabel.setText("模型评估");
		assesslabel.setForeground(new Color(0,0,0));
		assesslabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		assesslabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		
		initAssessToolPanel();
		
		assesslabelpanel.setBackground(new Color(207, 214, 229));
		assesslabelpanel.setLayout(new BorderLayout());
		assesslabelpanel.add(assesslabel, BorderLayout.WEST);
		assesslabelpanel.add(assesstoolpanel, BorderLayout.EAST);
		assesslabelpanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(142, 155, 188)));
		assesslabelpanel.setPreferredSize(new Dimension(100, 29));
		assesslabelpanel.setMaximumSize(new Dimension(100, 29));
		assesslabelpanel.setMinimumSize(new Dimension(100, 29));
		
		initAssessDealPanel();
		
		assesspanel.setLayout(new BorderLayout());
		assesspanel.add(assesslabelpanel,BorderLayout.NORTH);
		assesspanel.add(assessscrollpanel,BorderLayout.CENTER);
		
	}

	private void initAssessToolPanel() {
		// TODO Auto-generated method stub
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "dropdown.png");
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));

		assesstoolbutton1.setIcon(icon1);
		assesstoolbutton1.setFocusable(false);
		assesstoolbutton1.setContentAreaFilled(false);
		assesstoolbutton1.setBorderPainted(false);
		assesstoolbutton1.addMouseListener(new ButtonMouseListener());
		assesstoolbutton1.setPreferredSize(new Dimension(21,21));
		assesstoolbutton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if(assessscrollpanel.isVisible()){
					assessscrollpanel.setVisible(false);
					ChangeRepaint();
				}
				else {
					assessscrollpanel.setVisible(true);
					ChangeRepaint();
				}
				
			}
		});

		assesstoolpanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,4));
		assesstoolpanel.setOpaque(false);
		assesstoolpanel.add(assesstoolbutton1);
		
	}

	private void initAssessDealPanel() {
		// TODO Auto-generated method stub
		
		assessdealpanel1=new JPanel();
		assessdealpanel2=new JPanel();
		assessdealpanel3=new JPanel();
		assessdeallinepanel1=new JPanel();
		assessdeallinepanel2=new JPanel();
		
		initAssessDealPanelOne();
		
		initAssessDealPanelTwo();
		
		initAssessDealPanelThree();
		
		buttonGroup=new ButtonGroup();
		buttonGroup.add(radioButton1);
		buttonGroup.add(radioButton2);
		buttonGroup.add(radioButton3);
		radioButton1.setSelected(true);
		
		initAssessDealLinePanel();
		
		assessdealpanel1.setBackground(new Color(255, 255, 255));
		assessdealpanel2.setBackground(new Color(255, 255, 255));
		assessdealpanel3.setBackground(new Color(255, 255, 255));
		assessdeallinepanel1.setBackground(new Color(255, 255, 255));
		assessdeallinepanel2.setBackground(new Color(255, 255, 255));
		
		JPanel emptypanel=new JPanel();
		emptypanel.setLayout(new GridLayout());
		emptypanel.setBackground(new Color(255, 255, 255));
		
		assessdealpanel.setBackground(new Color(255, 255, 255));
		GridBagLayout layout=new GridBagLayout();
		assessdealpanel.setLayout(layout);
		assessdealpanel.add(assessdealpanel1);
		assessdealpanel.add(assessdeallinepanel1);
		assessdealpanel.add(assessdealpanel2);
		assessdealpanel.add(assessdeallinepanel2);
		assessdealpanel.add(assessdealpanel3);
//		assessdealpanel.add(emptypanel);
		layout.setConstraints(assessdealpanel1, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(assessdeallinepanel1, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(assessdealpanel2, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(assessdeallinepanel2, new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(assessdealpanel3, new GBC(0, 4, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//		layout.setConstraints(emptypanel, new GBC(0, 5, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		JPanel borderpanel=new JPanel();
		borderpanel.setBackground(new Color(255, 255, 255));
		borderpanel.setLayout(new BorderLayout());
		borderpanel.add(assessdealpanel, BorderLayout.NORTH);
		
		assessscrollpanel=new JScrollPane(borderpanel);
		assessscrollpanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(142, 155, 188)));
		assessscrollpanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		assessscrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
	}

	private void initAssessDealLinePanel() {
		// TODO Auto-generated method stub
		
		assessdeallinelabel1=new JLabel();
		
		assessdeallinelabel1.setText("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
		assessdeallinelabel1.setPreferredSize(new Dimension(100, 3));
		assessdeallinelabel1.setForeground(new Color(223, 204, 221));
		
		assessdeallinepanel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
		assessdeallinepanel1.setLayout(new GridLayout());
		assessdeallinepanel1.add(assessdeallinelabel1);
		
		assessdeallinelabel2=new JLabel();
		
		assessdeallinelabel2.setText("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
		assessdeallinelabel2.setPreferredSize(new Dimension(100, 3));
		assessdeallinelabel2.setForeground(new Color(223, 204, 221));
		
		assessdeallinepanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
		assessdeallinepanel2.setLayout(new GridLayout());
		assessdeallinepanel2.add(assessdeallinelabel2);
		
	}
	
	public static void initAssessUIPanel() {
		// TODO Auto-generated method stub
		
		mainFrame.getModelExistValidationPanel().getMoviePanel().getMovieLabel().setText("等待进行模型评估");
		
		mainFrame.getValidationResultPanel().getFivenamelabel().setText("评估结果： ");
		mainFrame.getValidationResultPanel().getFiveresultpanel().removeAll();
		mainFrame.getValidationResultPanel().ChangeRepaint();
		
		mainFrame.getStepSixCenterTabbedPane().getValidationToolPanel().initValidationProgressbar();
	}

	private void initAssessDealPanelOne() {
		// TODO Auto-generated method stub
		
		assessdeallabelpanel1=new JPanel();
		assessdeallabelpanel11=new JPanel();
		assessdeallabel1=new JLabel();
		assessdeallabel11=new JLabel();
		assessdealtext11=new JTextField();
		assessdealbutton1=new JButton();
		radioButton1=new JRadioButton();
		
		radioButton1.setText("存在一致性评估");
		radioButton1.setForeground(new Color(0,0,0));
		radioButton1.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		radioButton1.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		radioButton1.setOpaque(false);
		
//		assessdeallabel1.setText("存在一致性评估");
//		assessdeallabel1.setForeground(new Color(0,0,0));
//		assessdeallabel1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
//		assessdeallabel1.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		
		assessdeallabel11.setText("消息：");
		assessdeallabel11.setForeground(new Color(0,0,0));
		assessdeallabel11.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		assessdeallabel11.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 18));
		
		assessdealtext11.setForeground(new Color(0,0,0));
		assessdealtext11.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		assessdealtext11.setPreferredSize(new Dimension(100, 20));
		assessdealtext11.setMaximumSize(new Dimension(100, 20));
		assessdealtext11.setMinimumSize(new Dimension(100, 20));
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "resultset_next.png");
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));

		assessdealbutton1.setIcon(icon1);
		assessdealbutton1.setFocusable(false);
		assessdealbutton1.setContentAreaFilled(false);
		assessdealbutton1.setBorderPainted(false);
		assessdealbutton1.addMouseListener(new ButtonMouseListener());
		assessdealbutton1.setPreferredSize(new Dimension(21,21));
		assessdealbutton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				initAssessUIPanel();
				
				Thread t=new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						
						mainFrame.getValidationResultPanel().getValidationlabeltab5().doClick();
						
						TranMessageColorize tmc=new TranMessageColorize();
						tmc.CleanColorize(mainFrame.getModelExistValidationPanel().getUppaalworkspace());
						
						String message=assessdealtext11.getText();
						
						if(message==null||message.trim().equals("")){
							JOptionPane.showMessageDialog(null, "消息不能为空！", "存在一致性评估" , JOptionPane.WARNING_MESSAGE);
							System.out.println("message is null");
						}
						else{
							
							List<UppaalTransition> uppaalMessageTransitionList = new ArrayList<UppaalTransition>();
							UppaalTransition umt=new UppaalTransition();
							umt.setName(message);
							uppaalMessageTransitionList.add(umt);

							List<UppaalTransition> uppaalTransitionList = new ArrayList<UppaalTransition>();
							
							if(uppaalType==1){
								uppaalTransitionList=evaluation.FindUppaalTransitionByMessage(message);
							}
							else{
								uppaalTransitionList=mainFrame.getModelExistValidationPanel().getEv().getSelectedTransitionsIfExist(uppaalMessageTransitionList);
							}

							if(uppaalTransitionList==null||uppaalTransitionList.size()==0){
								JOptionPane.showMessageDialog(null, "消息 "+message+" 不存在！", "存在一致性评估" , JOptionPane.ERROR_MESSAGE);
								System.out.println("message is not exist ");
							}
							else{
								tmc.ColorizeTran(uppaalTransitionList,mainFrame.getModelExistValidationPanel().getUppaalworkspace());
								
								mainFrame.getValidationResultPanel().getFivenamelabel().setText("共找到"+uppaalTransitionList.size()+"条消息：");
								
								mainFrame.getValidationResultPanel().getFiveresultpanel().removeAll();
								
								System.out.println("++++++++++++++++++++");
								
								JPanel resultpanel=new JPanel();
								JPanel emptypanel=new JPanel();
								resultpanel.setOpaque(false);
								emptypanel.setOpaque(false);
								
								GridBagLayout layout = new GridBagLayout();
								resultpanel.setLayout(layout);
								int i=0;
								for(UppaalTransition ut:uppaalTransitionList){
									System.out.println(ut);
									
									ValidationTransitionMessagePanel vtmpanel=new ValidationTransitionMessagePanel(ut);
									resultpanel.add(vtmpanel);
									layout.setConstraints(vtmpanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
									
								}
								resultpanel.add(emptypanel);
								layout.setConstraints(emptypanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
								mainFrame.getValidationResultPanel().getFiveresultpanel().add(resultpanel);
								mainFrame.getValidationResultPanel().ChangeRepaint();
								System.out.println("++++++++++++++++++++");
								
								JOptionPane.showMessageDialog(null, "消息 "+message+" 存在！", "存在一致性评估" , JOptionPane.INFORMATION_MESSAGE);
								System.out.println("message is exist ");
							}
							
						}
						
					}
				});
				t.start();
				
			}
		});
		
		assessdeallabelpanel1.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		assessdeallabelpanel1.setOpaque(false);
		assessdeallabelpanel1.setLayout(new BorderLayout());
		assessdeallabelpanel1.add(radioButton1,BorderLayout.WEST);
//		assessdeallabelpanel1.add(assessdealbutton1, BorderLayout.EAST);
		
		assessdeallabelpanel11.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		assessdeallabelpanel11.setOpaque(false);
		assessdeallabelpanel11.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		assessdeallabelpanel11.add(assessdeallabel11);
		assessdeallabelpanel11.add(assessdealtext11);

		GridBagLayout layout=new GridBagLayout();
		assessdealpanel1.setLayout(layout);
		assessdealpanel1.add(assessdeallabelpanel1);
		assessdealpanel1.add(assessdeallabelpanel11);
		layout.setConstraints(assessdeallabelpanel1, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(assessdeallabelpanel11, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		
	}

	private void initAssessDealPanelTwo() {
		// TODO Auto-generated method stub
		
		assessdeallabelpanel2=new JPanel();
		assessdeallabelpanel21=new JPanel();
		assessdeallabelpanel22=new JPanel();
		assessdeallabel2=new JLabel();
		assessdeallabel21=new JLabel();
		assessdeallabel22=new JLabel();
		assessdealtext21=new JTextField();
		assessdealtext22=new JTextField();
		assessdealbutton2=new JButton();
		
		radioButton2=new JRadioButton();
		
		radioButton2.setText("顺序一致性评估");
		radioButton2.setForeground(new Color(0,0,0));
		radioButton2.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		radioButton2.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		radioButton2.setOpaque(false);
		
		assessdeallabel2.setText("顺序一致性评估");
		assessdeallabel2.setForeground(new Color(0,0,0));
		assessdeallabel2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		assessdeallabel2.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		
		assessdeallabel21.setText("消息A：");
		assessdeallabel21.setForeground(new Color(0,0,0));
		assessdeallabel21.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		assessdeallabel21.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		
		assessdeallabel22.setText("消息B：");
		assessdeallabel22.setForeground(new Color(0,0,0));
		assessdeallabel22.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		assessdeallabel22.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		
		assessdealtext21.setForeground(new Color(0,0,0));
		assessdealtext21.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		assessdealtext21.setPreferredSize(new Dimension(100, 20));
		assessdealtext21.setMaximumSize(new Dimension(100, 20));
		assessdealtext21.setMinimumSize(new Dimension(100, 20));
		
		assessdealtext22.setForeground(new Color(0,0,0));
		assessdealtext22.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		assessdealtext22.setPreferredSize(new Dimension(100, 20));
		assessdealtext22.setMaximumSize(new Dimension(100, 20));
		assessdealtext22.setMinimumSize(new Dimension(100, 20));
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "resultset_next.png");
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));

		assessdealbutton2.setIcon(icon1);
		assessdealbutton2.setFocusable(false);
		assessdealbutton2.setContentAreaFilled(false);
		assessdealbutton2.setBorderPainted(false);
		assessdealbutton2.addMouseListener(new ButtonMouseListener());
		assessdealbutton2.setPreferredSize(new Dimension(21,21));
		assessdealbutton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				initAssessUIPanel();
				
				Thread t=new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						
						mainFrame.getValidationResultPanel().getValidationlabeltab5().doClick();
						
						TranMessageColorize tmc=new TranMessageColorize();
						tmc.CleanColorize(mainFrame.getModelExistValidationPanel().getUppaalworkspace());
						
						String message1=assessdealtext21.getText();
						String message2=assessdealtext22.getText();
						
						if(message1==null||message1.trim().equals("")||message2==null||message2.trim().equals("")){
							JOptionPane.showMessageDialog(null, "消息A或消息B不能为空！", "顺序一致性评估" , JOptionPane.WARNING_MESSAGE);
							System.out.println("message1 or message2 is null");
						}
						else{
							
							List<UppaalTransition> uppaalMessageTransitionList = new ArrayList<UppaalTransition>();
							UppaalTransition umt1=new UppaalTransition();
							umt1.setName(message1);
							uppaalMessageTransitionList.add(umt1);
							UppaalTransition umt2=new UppaalTransition();
							umt2.setName(message2);
							uppaalMessageTransitionList.add(umt2);

							List<PathTuple> pathTupleList = new ArrayList<PathTuple>();
							
							if(uppaalType==1){
								pathTupleList=evaluation.FindUppaalPathTupleByMessages(message1, message2);
							}
							else{
								pathTupleList=mainFrame.getModelExistValidationPanel().getEv().getPathOfSelectedTransitions(uppaalMessageTransitionList);
							}
							
							if(pathTupleList==null||pathTupleList.size()==0){
								JOptionPane.showMessageDialog(null, "顺序一致性评估失败，找不到路径！", "顺序一致性评估" , JOptionPane.ERROR_MESSAGE);
								System.out.println("message is not exist ");
							}
							else{
								System.out.println("pathTupleList.size() "+pathTupleList.size());
								if(uppaalType==1){
									tmc.ColorizeTranAndStateByDFS(pathTupleList, mainFrame.getModelExistValidationPanel().getUppaalworkspace());
								}
								else{
									tmc.ColorizeTranAndState(pathTupleList, mainFrame.getModelExistValidationPanel().getUppaalworkspace());
								}
								
								mainFrame.getValidationResultPanel().getFivenamelabel().setText("共找到一条路径，包含"+pathTupleList.size()+"个节点和"+pathTupleList.size()+"条消息：");
								
								mainFrame.getValidationResultPanel().getFiveresultpanel().removeAll();
								
								System.out.println("++++++++++++++++++++");
								
								JPanel resultpanel=new JPanel();
								JPanel emptypanel=new JPanel();
								resultpanel.setOpaque(false);
								emptypanel.setOpaque(false);
								
								GridBagLayout layout = new GridBagLayout();
								resultpanel.setLayout(layout);
								int i=0;
								
								for(PathTuple pt:pathTupleList){
//									System.out.println(pt.getLocation().toString()+ " --- "+pt.getTransition().toString());
									
									ValidationLocationMessagePanel vlmpanel=new ValidationLocationMessagePanel(pt.getLocation());
									resultpanel.add(vlmpanel);
									layout.setConstraints(vlmpanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
									
									ValidationTransitionMessagePanel vtmpanel=new ValidationTransitionMessagePanel(pt.getTransition());
									resultpanel.add(vtmpanel);
									layout.setConstraints(vtmpanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
									
								}
								
								resultpanel.add(emptypanel);
								layout.setConstraints(emptypanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//								mainFrame.getValidationResultPanel().getResultpanel().add(Box.createVerticalGlue());
								mainFrame.getValidationResultPanel().getFiveresultpanel().add(resultpanel);
								mainFrame.getValidationResultPanel().ChangeRepaint();
								
								System.out.println("++++++++++++++++++++");
								
								JOptionPane.showMessageDialog(null, "顺序一致性评估成功，找到一组路径！", "顺序一致性评估" , JOptionPane.INFORMATION_MESSAGE);
								System.out.println("message is exist ");
							}
							
						}
						
					}
				});
				t.start();
				
			}
		});
		
		assessdeallabelpanel2.setOpaque(false);
		assessdeallabelpanel2.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		assessdeallabelpanel2.setLayout(new BorderLayout());
		assessdeallabelpanel2.add(radioButton2,BorderLayout.WEST);
//		assessdeallabelpanel2.add(assessdealbutton2, BorderLayout.EAST);
		
		assessdeallabelpanel21.setOpaque(false);
		assessdeallabelpanel21.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		assessdeallabelpanel21.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		assessdeallabelpanel21.add(assessdeallabel21);
		assessdeallabelpanel21.add(assessdealtext21);
		
		assessdeallabelpanel22.setOpaque(false);
		assessdeallabelpanel22.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		assessdeallabelpanel22.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		assessdeallabelpanel22.add(assessdeallabel22);
		assessdeallabelpanel22.add(assessdealtext22);

		GridBagLayout layout=new GridBagLayout();
		assessdealpanel2.setLayout(layout);
		assessdealpanel2.add(assessdeallabelpanel2);
		assessdealpanel2.add(assessdeallabelpanel21);
		assessdealpanel2.add(assessdeallabelpanel22);
		layout.setConstraints(assessdeallabelpanel2, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(assessdeallabelpanel21, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(assessdeallabelpanel22, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		
	}
	
	private void initAssessDealPanelThree() {
		// TODO Auto-generated method stub
		
		assessdeallabelpanel3=new JPanel();
		assessdeallabelpanel31=new JPanel();
		assessdeallabel3=new JLabel();
		assessdeallabel31=new JLabel();
		assessdealtext31=new JTextField();
		assessdealbutton3=new JButton();
		
		radioButton3=new JRadioButton();
		
		radioButton3.setText("实时一致性评估");
		radioButton3.setForeground(new Color(0,0,0));
		radioButton3.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		radioButton3.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		radioButton3.setOpaque(false);
		
		assessdeallabel3.setText("实时一致性评估");
		assessdeallabel3.setForeground(new Color(0,0,0));
		assessdeallabel3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		assessdeallabel3.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		
		assessdeallabel31.setText("时间：");
		assessdeallabel31.setForeground(new Color(0,0,0));
		assessdeallabel31.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		assessdeallabel31.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 18));
		
		assessdealtext31.setForeground(new Color(0,0,0));
		assessdealtext31.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		assessdealtext31.setPreferredSize(new Dimension(100, 20));
		assessdealtext31.setMaximumSize(new Dimension(100, 20));
		assessdealtext31.setMinimumSize(new Dimension(100, 20));
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "resultset_next.png");
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));

		assessdealbutton3.setIcon(icon1);
		assessdealbutton3.setFocusable(false);
		assessdealbutton3.setContentAreaFilled(false);
		assessdealbutton3.setBorderPainted(false);
		assessdealbutton3.addMouseListener(new ButtonMouseListener());
		assessdealbutton3.setPreferredSize(new Dimension(21,21));
		assessdealbutton3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				initAssessUIPanel();
				
				Thread t=new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						
						mainFrame.getValidationResultPanel().getValidationlabeltab5().doClick();
						
						String message=assessdealtext31.getText();
						
						if(message==null||message.trim().equals("")){
							JOptionPane.showMessageDialog(null, "时间不能为空！", "实时一致性评估" , JOptionPane.WARNING_MESSAGE);
							System.out.println("message is null");
						}
						else{
							
							if(isInequality(message)){
								
								Boolean result=false;
								
								if(uppaalType==1){
									result=evaluation.CheckTimeByInput(message);
								}
								else{
									result=mainFrame.getModelExistValidationPanel().getEv().getPathByInput(message);
								}
								
								System.out.println("result is "+result);
								
								if(result){
									JOptionPane.showMessageDialog(null, "实时一致性评估成功！", "实时一致性评估" , JOptionPane.INFORMATION_MESSAGE);
								}
								else{
									JOptionPane.showMessageDialog(null, "实时一致性评估失败！", "实时一致性评估" , JOptionPane.ERROR_MESSAGE);
								}
								
							}
							else{
								JOptionPane.showMessageDialog(null, "时间参数格式不符合要求！", "实时一致性评估" , JOptionPane.WARNING_MESSAGE);
								System.out.println("message is not conform format");
							}
							
						}
						
					}
				});
				t.start();
				
			}
		});
		
		assessdeallabelpanel3.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		assessdeallabelpanel3.setOpaque(false);
		assessdeallabelpanel3.setLayout(new BorderLayout());
		assessdeallabelpanel3.add(radioButton3,BorderLayout.WEST);
//		assessdeallabelpanel3.add(assessdealbutton3, BorderLayout.EAST);
		
		assessdeallabelpanel31.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		assessdeallabelpanel31.setOpaque(false);
		assessdeallabelpanel31.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		assessdeallabelpanel31.add(assessdeallabel31);
		assessdeallabelpanel31.add(assessdealtext31);

		GridBagLayout layout=new GridBagLayout();
		assessdealpanel3.setLayout(layout);
		assessdealpanel3.add(assessdeallabelpanel3);
		assessdealpanel3.add(assessdeallabelpanel31);
		layout.setConstraints(assessdeallabelpanel3, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(assessdeallabelpanel31, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		
	}
	
	public static Boolean isInequality(String str){
		
		Boolean result=false;
		
		if(str.startsWith("t>=")||str.startsWith("t<=")){
			result=str.substring(3).matches("[0-9]+");
		}
		else if(str.startsWith("t>")||str.startsWith("t<")){
			result=str.substring(2).matches("[0-9]+");
		}
		
		return result;
		
	}

	private void initTimingPanel() {
		// TODO Auto-generated method stub
		
		timinglabel.setText("时间自动机");
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
		
//		initTimingTablePanel();
//		
//		addDataToTimingTable();
//		
//		timingtablepanel.setLayout(new GridLayout());
//		timingtablepanel.add(timingtable);
//		timingtablepanel.setBorder(null);
		
		timingtablepanel.setLayout(new BoxLayout(timingtablepanel, BoxLayout.Y_AXIS));
		timingtablepanel.setBorder(BorderFactory.createEmptyBorder(0, 7, 0, 0));
		timingtablepanel.setBackground(new Color(255, 255, 255));
		
		addCheckBoxToUppaalcheckboxpanel();
		
		timingscrollpanel=new JScrollPane(timingtablepanel);
		timingscrollpanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(142, 155, 188)));
		timingscrollpanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		timingscrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
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

	private void addCheckBoxToUppaalcheckboxpanel() {
		// TODO Auto-generated method stub
		
		timingtablepanel.removeAll();
		uppaalCheckBoxList=new JCheckBox[uppaallists.size()];
		for(int i=0;i<uppaallists.size();i++){
			uppaalCheckBoxList[i]=new JCheckBox(uppaallists.get(i)){
				
			};
			uppaalCheckBoxList[i].setOpaque(false);
			timingtablepanel.add(Box.createVerticalStrut(7));
			timingtablepanel.add(uppaalCheckBoxList[i]);
		}
		
		if(uppaalCheckBoxList.length>0&&StepThreeCenterTabbedPane.getBecomeRunFileName()!=null){
			for(JCheckBox checkBox:uppaalCheckBoxList){
				if(StepSixCenterTabbedPane.getBecomeRunFileName().equals(checkBox.getText())){
					checkBox.setSelected(true);
					selectUppaalCheckBox=checkBox;
				}
			}
		}
		else{
			selectUppaalCheckBox=new JCheckBox();
		}
		
		for(final JCheckBox jcb:uppaalCheckBoxList){
			
			jcb.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					if(jcb.isSelected()){
						selectUppaalCheckBox.setSelected(false);
						selectUppaalCheckBox=jcb;
					}
					else{
						selectUppaalCheckBox=new JCheckBox();
					}
				}
			});
			
		}
		
	}

	// TODO Auto-generated method stub
	private void addDataToTimingTable() {
		
		while(timingtablemodel.getRowCount()>0){
			timingtablemodel.removeRow(timingtablemodel.getRowCount()-1);
		}
		
		
		for(String str:uppaallists){
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
		
		timingtable.setBackground(new Color(255,255,255));
		timingtable.setBorder(null);
		
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
					String uppaalname=filename.substring(0, filename.indexOf("Uppaal"));
//					System.out.println("-----path:-----"+path);
					
//					showTimingDiagram(path);
					
					showUppaalDiagram(uppaalname);
					
					uppaalType=checkUppaalType(uppaalname);
					
					if(uppaalType==1){
						evaluation=new Evaluation(uppaalname, uppaalType);
						evaluation.Ready();
					}
					else{
						try {
							ev=new ExistVerification("D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\TimingToUppal\\"+uppaalname+"ForXStream.xml");
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
//					String filePath="D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\uppaalTest1.uppaal.violet.xml";
					
//					try {
//						System.out.println("+-+-+-++++++++++++++++********************"+"D:\\ModelDriverProjectFile\\WJXML\\"+filename+"\\"+TimingEAtoUppaal.getDiagramDataName()+".xml");
//						ev=new ExistVerification("D:\\ModelDriverProjectFile\\WJXML\\"+filename+"\\"+TimingEAtoUppaal.getDiagramDataName()+".xml");
////						ev=new ExistVerification("D:\\ModelDriverProjectFile\\WJXML\\EA4.1.0 功能场景1\\UAV.xml");
//						
//						uppaalmessagelist=ev.getMessages();
//						
//						RowStringsForDisplay row=CompareEAtoAutomata.compareFromXMLPath(path, "D:\\ModelDriverProjectFile\\WJXML\\"+filename+"\\"+TimingEAtoUppaal.getDiagramDataName()+".xml");
//						
//						stateCompareList =row.getStateCompareList();
//						messageCompareList = row.getMessageCompareList();
//						
////						showStateCompare(stateCompareList);
////
////						showMessageCompare(messageCompareList);
////						
//////						mainFrame.getValidationResultPanel().getTwonamelabel().setText("共找到"+stateCompareList.size()+"条状态和"+messageCompareList.size()+"条消息"+"状态和节点比较中,成功"+statesuccesssum+"条,失败"+statefailsum+"条"+"消息比较中,成功"+messagesuccesssum+"条,失败"+messagefailsum+"条");
////						mainFrame.getValidationResultPanel().getTwonamelabel().setText("<html><body>在状态比较中,共找到"+stateCompareList.size()+"条状态,成功"+statesuccesssum+"条,失败"+statefailsum+"条<br>在消息比较中,共找到"+messageCompareList.size()+"条消息,成功"+messagesuccesssum+"条,失败"+messagefailsum+"条</body></html>");
//						
//						pathtuple=ev.getPath();
//						times = CompareEAtoAutomata.verificationPathTupleTime(ev.getPath());
//						
////						showPathTupleTime(pathtuple, times);
////						
////						mainFrame.getValidationResultPanel().ChangeRepaint();
//						
//						
//					} catch (Exception e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					
//					addCheckBoxToValidationCheckboxPanel();
					
//					mainFrame.getStepSixCenterTabbedPane().getTimingDiagramButton().doClick();
					mainFrame.getStepSixCenterTabbedPane().getUppaalDiagramButton().doClick();
					
					ChangeRepaint();
					
					mainFrame.getStepSixCenterTabbedPane().ChangeRepaint();
					
				}
			}
        	
		});
		
	}

	protected int checkUppaalType(String filename) {
		
		String baseUrl = "D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\";
	    
	    File SeFile=new File(baseUrl+"SequenceToUppal\\"+filename+"ForXStream.xml");
	    if(SeFile.exists()){
	    	return 1;
	    }
	    
	    File TiFile=new File(baseUrl+"TimingToUppal\\"+filename+"ForXStream.xml");
	    if(TiFile.exists()){
	    	return 2;
	    }
		
		return 0;
	}

	protected void showMessageCompare(ArrayList<MessageCompare> messageCompareList) {
		// TODO Auto-generated method stub
		
		mainFrame.getValidationResultPanel().getMessagetransitionresultpanel().removeAll();
		
		JPanel resultpanel2=new JPanel();
		JPanel emptypanel2=new JPanel();
		resultpanel2.setOpaque(false);
		emptypanel2.setOpaque(false);
		
		GridBagLayout layout2 = new GridBagLayout();
		resultpanel2.setLayout(layout2);
		int j=0;
		
		ArrayList<String> headlist2=new ArrayList<String>();
		headlist2.add("时序图");
		headlist2.add("时间自动机");
		headlist2.add("结果");
		
		TableHeadPanel thpanel2=new TableHeadPanel(headlist2);
		resultpanel2.add(thpanel2);
		layout2.setConstraints(thpanel2, new GBC(0, j++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		
		messagesuccesssum=0;
		messagefailsum=0;
		
		for(MessageCompare m:messageCompareList){

			ValidationMessageComparePanel vmcpanel=new ValidationMessageComparePanel(m);
			resultpanel2.add(vmcpanel);
			layout2.setConstraints(vmcpanel, new GBC(0, j++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
			
			if(m.getResult().equals("ok")){
				messagesuccesssum++;
			}
			else{
				messagefailsum++;
			}

		}
		
		resultpanel2.add(emptypanel2);
		layout2.setConstraints(emptypanel2, new GBC(0, j++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		mainFrame.getValidationResultPanel().getMessagetransitionresultpanel().add(resultpanel2);
		
	}

	protected void showStateCompare(ArrayList<StateCompare> stateCompareList) {
		// TODO Auto-generated method stub
		
		mainFrame.getValidationResultPanel().getStatelocationresultpanel().removeAll();
		
		JPanel resultpanel1=new JPanel();
		JPanel emptypanel1=new JPanel();
		resultpanel1.setOpaque(false);
		emptypanel1.setOpaque(false);
		
		GridBagLayout layout1 = new GridBagLayout();
		resultpanel1.setLayout(layout1);
		int i=0;
		
		ArrayList<String> headlist1=new ArrayList<String>();
		headlist1.add("时序图");
		headlist1.add("时间自动机");
		headlist1.add("结果");
		
		TableHeadPanel thpanel1=new TableHeadPanel(headlist1);
		resultpanel1.add(thpanel1);
		layout1.setConstraints(thpanel1, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		
		statesuccesssum=0;
		statefailsum=0;
		
		for(StateCompare s:stateCompareList){
			
			ValidationStateComparePanel vscpanel=new ValidationStateComparePanel(s);
			resultpanel1.add(vscpanel);
			layout1.setConstraints(vscpanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
			
			if(s.getResult().equals("ok")){
				statesuccesssum++;
			}
			else{
				statefailsum++;
			}
			
		}
		
		resultpanel1.add(emptypanel1);
		layout1.setConstraints(emptypanel1, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		mainFrame.getValidationResultPanel().getStatelocationresultpanel().add(resultpanel1);
		
	}

	protected void showPathTupleTime(ArrayList<PathTuple> pathtuple, ArrayList<Integer> times) {
		// TODO Auto-generated method stub
		
		ValidationPathTupleTimePanel vpttp=new ValidationPathTupleTimePanel(pathtuple, times);
		mainFrame.getValidationResultPanel().getFourresultpanel().removeAll();
		mainFrame.getValidationResultPanel().getFourresultpanel().add(vpttp);
		
		if(times.get(times.size()-1)==CompareEAtoAutomata.findTimingDiagramLastStateStartTime()){
			mainFrame.getValidationResultPanel().getFournamelabel().setText("自动机路径累加的时间和为"+times.get(times.size()-1)+"s,最后一个状态的开始时间为"+CompareEAtoAutomata.findTimingDiagramLastStateStartTime()+"s,验证成功");
		}
		else{
			mainFrame.getValidationResultPanel().getFournamelabel().setText("自动机路径累加的时间和为"+times.get(times.size()-1)+"s,最后一个状态的开始时间为"+CompareEAtoAutomata.findTimingDiagramLastStateStartTime()+"s,验证失败");
		}
		
		
	}

	protected void showPathTupleTime() {
		// TODO Auto-generated method stub
		
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
//		uppaalworkspace=mainFrame.getStepTwoCenterTabbedPane().getTimingToUppaalTabbedPane().getTiminganduppaalmap().get(filename);
		
		
			
		String baseUrl = "D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\";
		String path = baseUrl + "\\"+filename + "\\"+filename+"Uppaal.uppaal.violet.xml";

		try {

//			TimingEAtoUppaal.transEA(filename,path,mainFrame,0);
//			LayoutUppaal.layout("D:\\ModelDriverProjectFile\\WJXML\\"+filename+"\\"+TimingEAtoUppaal.getDiagramDataName() + ".xml");
//			String filename1 = TransToVioletUppaal.TransToViolet(filename,2,2);
//
//			System.out.println("filename1:" + filename1 + " TimingEAtoUppaal.getDiagramDataName():"
//					+ TimingEAtoUppaal.getDiagramDataName());
//
//			if (uppaalworkspace == null) {
//				GraphFile fGraphFile1 = ImportByDoubleClick.importFileByDoubleClick("UPPAAL", filename1);
//				uppaalworkspace = new Workspace(fGraphFile1);
//			}
			
			GraphFile fGraphFile1=new GraphFile(new LocalFile(new File(path)));
			uppaalworkspace = new Workspace(fGraphFile1);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		
		mainFrame.getStepSixCenterTabbedPane().getDiagramPanel().removeAll();
		mainFrame.getStepSixCenterTabbedPane().getUppaalDiagramTabbedPane().removeAll();
		
		ValidationToolPanel toolPanel = new ValidationToolPanel(mainFrame,uppaalworkspace);
		mainFrame.getStepSixCenterTabbedPane().setValidationToolPanel(toolPanel);
		
		moviePanel = new MoviePanel();
		moviePanel.getMovieLabel().setText("等待进行模型评估");
		
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
		ImageIcon icon3 = new ImageIcon(path + "resultset_next.png");
		icon3.setImage(icon3.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		
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
				
				updateFileList();
				
//				uppaallists.clear();
//				initFileList();
////				addDataToTimingTable();
//				addCheckBoxToUppaalcheckboxpanel();
				
			}
		});
		
		timingtoolbutton3.setIcon(icon3);
		timingtoolbutton3.setFocusable(false);
		timingtoolbutton3.setContentAreaFilled(false);
		timingtoolbutton3.setBorderPainted(false);
		timingtoolbutton3.addMouseListener(new ButtonMouseListener());
		timingtoolbutton3.setPreferredSize(new Dimension(21,21));
		timingtoolbutton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String baseUrl = "D:\\ModelDriverProjectFile\\TimingDiagram\\Violet\\";
				String filename=selectUppaalCheckBox.getText();
				String uppaalname=filename.substring(0, filename.indexOf("Uppaal"));
				
				showUppaalDiagram(uppaalname);
				
				uppaalType=checkUppaalType(uppaalname);
				
				if(uppaalType==1){
					evaluation=new Evaluation(uppaalname, uppaalType);
					evaluation.Ready();
				}
				else{
					try {
						ev=new ExistVerification("D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\TimingToUppal\\"+uppaalname+"ForXStream.xml");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				mainFrame.getStepSixCenterTabbedPane().getUppaalDiagramButton().doClick();
				
				ChangeRepaint();
				
				mainFrame.getStepSixCenterTabbedPane().ChangeRepaint();
				
				
			}
			
		});

		timingtoolpanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,4));
		timingtoolpanel.setOpaque(false);
		timingtoolpanel.add(timingtoolbutton3);
		timingtoolpanel.add(timingtoolbutton2);
		timingtoolpanel.add(timingtoolbutton1);
		
	}

	public void updateFileList() {
		// TODO Auto-generated method stub
		uppaallists.clear();
		initFileList();
		addCheckBoxToUppaalcheckboxpanel();
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
		
		emptypanel=new JPanel();
		emptypanel.setBackground(new Color(255, 255, 255));
		
		validationinfopanel=new JPanel();
		validationinfopanel.setLayout(new GridLayout());
		validationinfopanel.add(validationscrollpanel);
		
		validationpanel.setLayout(new BorderLayout());
		validationpanel.add(validationalllabelpanel,BorderLayout.NORTH);
		validationpanel.add(validationinfopanel,BorderLayout.CENTER);

		
	}

	private void initValidationLabelTabPanel() {
		// TODO Auto-generated method stub
		
		validationlabeltabpanel=new JPanel();
		validationlabeltabpanel1=new JPanel();
		validationlabeltabpanel2=new JPanel();
		validationlabeltabpanel3=new JPanel();
		validationlabeltabpanel4=new JPanel();
		
		validationlabeltab1=new JButton();
		validationlabeltab2=new JButton();
		validationlabeltab3=new JButton();
		validationlabeltab4=new JButton();
		
		validationlabeltab1.setText("存在");
		validationlabeltab1.setForeground(new Color(0,0,0));
		validationlabeltab1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		validationlabeltab1.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));
		validationlabeltab1.setFocusable(false);
		validationlabeltab1.setContentAreaFilled(false);
		validationlabeltab1.setBorderPainted(false);
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
				
				validationinfopanel.removeAll();
				validationinfopanel.add(validationscrollpanel);
				if(mainFrame.getValidationResultPanel().getValidationlabeltabindex()!=1){
					mainFrame.getValidationResultPanel().getValidationlabeltab1().doClick();
				}
				
				ChangeRepaint();

			}
		});
		
		validationlabeltab1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setvalidationlabeltabpanelrepait();
				validationlabeltab1.setForeground(new Color(0, 0, 0));
				validationlabeltabpanel1.setBackground(new Color(255, 255, 255));
				validationlabeltabpanel1.setBorder(BorderFactory.createMatteBorder(0,0,1,1, new Color(142, 155, 188)));
				validationlabeltabindex = 1;
				
				validationlabel.setText("存在性验证");
				
				validationinfopanel.removeAll();
				validationinfopanel.add(validationscrollpanel);
				if(mainFrame.getValidationResultPanel().getValidationlabeltabindex()!=1){
					mainFrame.getValidationResultPanel().getValidationlabeltab1().doClick();
				}
				
				ChangeRepaint();
			}
		});
		
		validationlabeltab2.setText("顺序");
		validationlabeltab2.setForeground(new Color(255, 255, 255));
		validationlabeltab2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		validationlabeltab2.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));
		validationlabeltab2.setFocusable(false);
		validationlabeltab2.setContentAreaFilled(false);
		validationlabeltab2.setBorderPainted(false);
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
				
				validationinfopanel.removeAll();
				validationinfopanel.add(validationscrollpanel);
				if(mainFrame.getValidationResultPanel().getValidationlabeltabindex()!=2){
					mainFrame.getValidationResultPanel().getValidationlabeltab2().doClick();
				}
				
				ChangeRepaint();

			}
		});

		validationlabeltab2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setvalidationlabeltabpanelrepait();
				validationlabeltab2.setForeground(new Color(0, 0, 0));
				validationlabeltabpanel2.setBackground(new Color(255, 255, 255));
				validationlabeltabpanel2.setBorder(BorderFactory.createMatteBorder(0,1,1,1, new Color(142, 155, 188)));
				validationlabeltabindex = 2;
				
				validationlabel.setText("顺序性验证");
				
				validationinfopanel.removeAll();
				validationinfopanel.add(validationscrollpanel);
				if(mainFrame.getValidationResultPanel().getValidationlabeltabindex()!=2){
					mainFrame.getValidationResultPanel().getValidationlabeltab2().doClick();
				}
				
				ChangeRepaint();
			}
		});
		
		validationlabeltab3.setText("对比");
		validationlabeltab3.setForeground(new Color(255, 255, 255));
		validationlabeltab3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		validationlabeltab3.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));
		validationlabeltab3.setFocusable(false);
		validationlabeltab3.setContentAreaFilled(false);
		validationlabeltab3.setBorderPainted(false);
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
				
				validationlabel.setText("对比性验证");
				
				validationinfopanel.removeAll();
				validationinfopanel.add(emptypanel);
				if(mainFrame.getValidationResultPanel().getValidationlabeltabindex()!=3){
					mainFrame.getValidationResultPanel().getValidationlabeltab3().doClick();
				}
				
				ChangeRepaint();

			}
		});
		
		validationlabeltab3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setvalidationlabeltabpanelrepait();
				validationlabeltab3.setForeground(new Color(0, 0, 0));
				validationlabeltabpanel3.setBackground(new Color(255, 255, 255));
				validationlabeltabpanel3.setBorder(BorderFactory.createMatteBorder(0,1,1,1, new Color(142, 155, 188)));
				validationlabeltabindex = 3;
				
				validationlabel.setText("对比性验证");
				
				validationinfopanel.removeAll();
				validationinfopanel.add(emptypanel);
				if(mainFrame.getValidationResultPanel().getValidationlabeltabindex()!=3){
					mainFrame.getValidationResultPanel().getValidationlabeltab3().doClick();
				}
				
				ChangeRepaint();

			}
		});
		
		validationlabeltab4.setText("时间");
		validationlabeltab4.setForeground(new Color(255, 255, 255));
		validationlabeltab4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		validationlabeltab4.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));
		validationlabeltab4.setFocusable(false);
		validationlabeltab4.setContentAreaFilled(false);
		validationlabeltab4.setBorderPainted(false);
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
				
				validationlabel.setText("时间性验证");
				
				validationinfopanel.removeAll();
				validationinfopanel.add(emptypanel);
				if(mainFrame.getValidationResultPanel().getValidationlabeltabindex()!=4){
					mainFrame.getValidationResultPanel().getValidationlabeltab4().doClick();
				}
				
				ChangeRepaint();

			}
		});
		
		validationlabeltab4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setvalidationlabeltabpanelrepait();
				validationlabeltab4.setForeground(new Color(0, 0, 0));
				validationlabeltabpanel4.setBackground(new Color(255, 255, 255));
				validationlabeltabpanel4.setBorder(BorderFactory.createMatteBorder(0,1,1,1, new Color(142, 155, 188)));
				validationlabeltabindex = 4;
				
				validationlabel.setText("时间性验证");
				
				validationinfopanel.removeAll();
				validationinfopanel.add(emptypanel);
				if(mainFrame.getValidationResultPanel().getValidationlabeltabindex()!=4){
					mainFrame.getValidationResultPanel().getValidationlabeltab4().doClick();
				}
				
				ChangeRepaint();
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
		validationlabeltabpanel.add(validationlabeltabpanel3);
		validationlabeltabpanel.add(validationlabeltabpanel4);
		
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

//		validationtoolbutton1.setIcon(icon1);
//		validationtoolbutton1.setFocusable(false);
//		validationtoolbutton1.setContentAreaFilled(false);
//		validationtoolbutton1.setBorderPainted(false);
//		validationtoolbutton1.addMouseListener(new ButtonMouseListener());
//		validationtoolbutton1.setPreferredSize(new Dimension(21,21));
//		validationtoolbutton1.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				
//				selecteduppaalmessagelist.clear();
//				for(JCheckBox jcb:uppaalMessageCheckBoxList){
//					if(jcb.isSelected()){
//						selecteduppaalmessagelist.add(uppaalmessagelist.get(uppaalMessageCheckBoxList.indexOf(jcb)));
//					}
//				}
//				
//				
//				if(validationlabeltabindex==1){
//					
//					List<UppaalTransition> l = null;
//					l=ev.getSelectedTransitionsIfExist(selecteduppaalmessagelist);
//					
//					if(l==null){
//						System.out.println("list is null");
//					}
//					else{
//						System.out.println("   ----------   ");
//						
//						mainFrame.getValidationResultPanel().getOnenamelabel().setText("共找到"+l.size()+"条消息：");
//						
//						mainFrame.getValidationResultPanel().getOneresultpanel().removeAll();
////						mainFrame.getValidationResultPanel().getResultpanel().setLayout(new BoxLayout(mainFrame.getValidationResultPanel().getResultpanel(), BoxLayout.Y_AXIS));
//						
//						System.out.println(mainFrame.getValidationResultPanel().getOneresultpanel().size());
//						System.out.println("++++++++++++++++++++");
//						
//						JPanel resultpanel=new JPanel();
//						JPanel emptypanel=new JPanel();
//						resultpanel.setOpaque(false);
//						emptypanel.setOpaque(false);
//						
//						GridBagLayout layout = new GridBagLayout();
//						resultpanel.setLayout(layout);
//						int i=0;
//						for(UppaalTransition u:l){
//							System.out.println(u);
//							
//							ValidationTransitionMessagePanel vtmpanel=new ValidationTransitionMessagePanel(u);
//							resultpanel.add(vtmpanel);
//							layout.setConstraints(vtmpanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//							
//						}
//						resultpanel.add(emptypanel);
//						layout.setConstraints(emptypanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
////						mainFrame.getValidationResultPanel().getResultpanel().add(Box.createVerticalGlue());
//						mainFrame.getValidationResultPanel().getOneresultpanel().add(resultpanel);
//						mainFrame.getValidationResultPanel().ChangeRepaint();
//						System.out.println("++++++++++++++++++++");
//						
//					}
//					
//				}else if(validationlabeltabindex==2){
//					
//					List<PathTuple> l=null;
//					l=ev.getPathOfSelectedTransitions(selecteduppaalmessagelist);
//					
//					if(l==null){
//						System.out.println("list is null");
//					}
//					else{
//						System.out.println("   ----------   ");
//						
//						System.out.println("++++++++++++++++++++");
//						for(PathTuple u:l){
//							System.out.println(u.getLocation().toString()+ " --- "+u.getTransition().toString());
//						}
//						System.out.println("++++++++++++++++++++");
//						
//						
//					}
//				}
//				
//				
//				
////				System.out.println("++++++++++++++++++++");
////				System.out.println(validationlabeltabindex);
////				for(UppaalTransition u:selecteduppaalmessagelist){
////					System.out.println(u);
////				}
////				System.out.println("++++++++++++++++++++");
//			}
//		});
		
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
	
	    String baseUrl = "D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\";
	    
	    File SeFile=new File(baseUrl+"SequenceToUppal\\");
	    for(String fileName:SeFile.list()){
	    	if(fileName.lastIndexOf("ForXStream.xml")>0){
	    		uppaallists.add(fileName.substring(0,fileName.lastIndexOf("ForXStream.xml"))+"Uppaal");
	    	}
	    }
	    
	    File TiFile=new File(baseUrl+"TimingToUppal\\");
	    for(String fileName:TiFile.list()){
	    	if(fileName.lastIndexOf("ForXStream.xml")>0){
	    		uppaallists.add(fileName.substring(0,fileName.lastIndexOf("ForXStream.xml"))+"Uppaal");
	    	}
	    }
	    
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

	public ExistVerification getEv() {
		return ev;
	}

	public Evaluation getEvaluation() {
		return evaluation;
	}

	public int getUppaalType() {
		return uppaalType;
	}

	public IWorkspace getUppaalworkspace() {
		return uppaalworkspace;
	}

	public ArrayList<StateCompare> getStateCompareList() {
		return stateCompareList;
	}

	public ArrayList<MessageCompare> getMessageCompareList() {
		return messageCompareList;
	}

	public ArrayList<PathTuple> getPathtuple() {
		return pathtuple;
	}

	public ArrayList<Integer> getTimes() {
		return times;
	}

	public JButton getValidationlabeltab1() {
		return validationlabeltab1;
	}

	public JButton getValidationlabeltab2() {
		return validationlabeltab2;
	}

	public JButton getValidationlabeltab3() {
		return validationlabeltab3;
	}

	public JButton getValidationlabeltab4() {
		return validationlabeltab4;
	}

	public JPanel getValidationcheckboxpanel() {
		return validationcheckboxpanel;
	}

	public JPanel getValidationinfopanel() {
		return validationinfopanel;
	}

	public JTextField getAssessdealtext11() {
		return assessdealtext11;
	}

	public JTextField getAssessdealtext21() {
		return assessdealtext21;
	}

	public JTextField getAssessdealtext22() {
		return assessdealtext22;
	}

	public JTextField getAssessdealtext31() {
		return assessdealtext31;
	}

	public JRadioButton getRadioButton1() {
		return radioButton1;
	}

	public JRadioButton getRadioButton2() {
		return radioButton2;
	}

	public JRadioButton getRadioButton3() {
		return radioButton3;
	}

	public MoviePanel getMoviePanel() {
		return moviePanel;
	}
	
}

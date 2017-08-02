package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.table.DefaultTableModel;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.horstmann.violet.application.consolepart.TestCaseInequalitySolveInforPanel;
import com.horstmann.violet.application.consolepart.TestCaseInequalitySolvePanel;
import com.horstmann.violet.application.consolepart.TestCasePathPanel;
import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.opreationTreePane.TestCaseConfirmationPanel;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.TestCase;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.myProcess;
import com.horstmann.violet.application.gui.util.ckt.handle.ATDTR__1;
import com.horstmann.violet.application.gui.util.ckt.handle.AddType;
import com.horstmann.violet.application.gui.util.ckt.handle.GetAutomatic;
import com.horstmann.violet.application.gui.util.ckt.handle.IPR__1;
import com.horstmann.violet.application.gui.util.xiaole.GraghLayout.LayoutUppaal;
import com.horstmann.violet.application.gui.util.xiaole.UppaalTransfrom.ImportByDoubleClick;
import com.horstmann.violet.application.menu.util.zhangjian.Database.AbstractState;
import com.horstmann.violet.application.menu.util.zhangjian.Database.AbstractTransition;
import com.horstmann.violet.application.menu.util.zhangjian.UMLTransfrom.CreateAbstractUppaalXML;
import com.horstmann.violet.framework.file.GraphFile;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;

import com.horstmann.violet.application.gui.util.ckt.handle.Automatic;
import com.horstmann.violet.application.gui.util.ckt.handle.State;
import com.horstmann.violet.application.gui.util.ckt.handle.StateCoverage__1;
import com.horstmann.violet.application.gui.util.ckt.handle.Transition;
import com.horstmann.violet.application.gui.util.ckt.output.TestAutoDiagram;
import com.horstmann.violet.application.gui.util.ckt.output.forPlatform;
import com.horstmann.violet.application.gui.util.ckt.testcase.PathCoverage_new;
import com.horstmann.violet.application.gui.util.ckt.testcase.PerformanceXML;
import com.horstmann.violet.application.gui.util.ckt.testcase.PerformanceXML2;
import com.horstmann.violet.application.gui.util.ckt.xml.GetTimeXML;
import com.horstmann.violet.application.gui.util.ckt.xml.XMLGet;
import com.horstmann.violet.application.gui.util.ckt.xml.XmlOfTime;
import com.horstmann.violet.application.gui.util.tanchao.SaveText;
import com.horstmann.violet.application.gui.util.tanchao.TranMessageColorize;
import com.horstmann.violet.application.gui.util.wj.util.GeneratePath;

public class TestCaseProcessTabbedPanel extends JPanel{
	
	private static MainFrame mainFrame;
	
	private JPanel toolpanel;
	private MoviePanel moviepanel;
	private JPanel tablepanel;
	
	private JPanel toolbuttonpanel1;
	private JPanel toolbuttonpanel2;
	private JPanel toolbuttonpanel3;
	private JButton toolbutton1;
	private JButton toolbutton2;
	private JButton toolbutton3;
	private JPanel emptypanel1;

	private JLabel progressbarlabel;

	private JProgressBar progressbar;
	private int progressbarindex;
	
	private Callable<Integer> maincallable;
	private FutureTask<Integer> maintask;
	private Thread mainthread;
	
	private Callable<Integer> callable1;
	private FutureTask<Integer> task1;
	private Thread thread1;
	private Callable<Integer> callable2;
	private FutureTask<Integer> task2;
	private Thread thread2;
	private Callable<Integer> callable3;
	private FutureTask<Integer> task3;
	private Thread thread3;
	private Callable<Integer> callable4;
	private FutureTask<Integer> task4;
	private Thread thread4;
	private Callable<Integer> callable5;
	private FutureTask<Integer> task5;
	private Thread thread5;
	private Callable<Integer> callable6;
	private FutureTask<Integer> task6;
	private Thread thread6;
	private Callable<Integer> callable7;
	private FutureTask<Integer> task7;
	private Thread thread7;
	
	private List<FutureTask<Integer>> futuretasklist=new ArrayList<FutureTask<Integer>>();
	private List<Thread> threadlist=new ArrayList<Thread>();
	
	private int stepsum=7;
	private int threadstate=0;
	private int step=1;
	
	private String selectUppaal;
	private String selectUppaalPath;
	private String selectCover;
	private int selectCoverState=0;//0-״̬���ǣ�1-Ǩ�Ƹ��ǣ�2-���ܲ���
	private int starttype;
	
//	private Automatic automatic;
	private List<AbstractState> abStateList =new ArrayList<AbstractState>();
	private List<AbstractTransition> abTransList =new ArrayList<AbstractTransition>();
	
	private Map<String, String> stateIdToNameMap=new HashMap<String, String>();
	private Map<String, String> stateNameToIdMap=new HashMap<String, String>();
	private Map<String, String> transitionIdToNameMap=new HashMap<String, String>();
	private Map<String, String> transitionNameToIdMap=new HashMap<String, String>();
	
	
	private Automatic a;
	private Automatic type_a;
	private Automatic iPARAutomatic;
	private Automatic aTDRTAutomatic;
	private Automatic type_aTDRTAutomatic;
	private Automatic DFStree;
	private ArrayList<Automatic> testCase;
	private ArrayList<Automatic> collectLimit;
//	private ArrayList<Automatic> collectResult;
	
//	private Automatic PerAutomaticResult;
//	private List<List<String>> listcases=new ArrayList<>();
	
	private String clockstate="��";
	private int automatictimestate=0;//0-����ʱ��Լ����1-��ʱ��Լ��
	
	long time1;
	long time2;
	
	private List<String> stepAllProcessList=new ArrayList<>();
	private List<String> timeAllProcessList=new ArrayList<>();
	private List<String> resultAllProcessList=new ArrayList<>();
	

	public TestCaseProcessTabbedPanel(MainFrame mainframe) {

		this.mainFrame = mainframe;
		
		toolpanel = new JPanel();
		moviepanel = new MoviePanel();
		tablepanel = new JPanel();

		initToolPanel();

		initMoviePanel();

		initTablePanel();

		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(toolpanel);
		this.add(moviepanel);
		this.add(tablepanel);
		layout.setConstraints(toolpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(moviepanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(tablepanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));

		this.setBackground(new Color(255, 255, 255));

	}

	private void initToolPanel() {
		// TODO Auto-generated method stub
		
		toolbuttonpanel1 = new JPanel();
		toolbuttonpanel2 = new JPanel();
		toolbuttonpanel3 = new JPanel();

		toolbutton1 = new JButton();
		toolbutton2 = new JButton();
		toolbutton3 = new JButton();
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "start.png");
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(path + "suspend.png");
		icon2.setImage(icon2.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon3 = new ImageIcon(path + "stop.png");
		icon3.setImage(icon3.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon4 = new ImageIcon(path + "up_arrow.png");
		icon4.setImage(icon4.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon5 = new ImageIcon(path + "down_arrow.png");
		icon5.setImage(icon5.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon6 = new ImageIcon(path + "allselect.png");
		icon6.setImage(icon6.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon7 = new ImageIcon(path + "cancelselect.png");
		icon7.setImage(icon7.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
	
		toolbutton1.setIcon(icon1);
		toolbutton1.setFocusable(false);
		toolbutton1.setContentAreaFilled(false);
		toolbutton1.setBorderPainted(false);
		toolbutton1.addMouseListener(new ButtonMouseListener());
		toolbutton1.setPreferredSize(new Dimension(21,21));
		toolbutton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if(threadstate==0){
					initThread();
					step=1;
					mainthread.start();
					thread1.start();
					threadstate=1;
				}
				if(threadstate==-1){
					mainthread.resume();
					threadlist.get(step-1).resume();
					threadstate=1;
				}
				
			}
		});
		
		toolbutton2.setIcon(icon2);
		toolbutton2.setFocusable(false);
		toolbutton2.setContentAreaFilled(false);
		toolbutton2.setBorderPainted(false);
		toolbutton2.addMouseListener(new ButtonMouseListener());
		toolbutton2.setPreferredSize(new Dimension(21,21));
		toolbutton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(threadstate==1){
					mainthread.suspend();
					threadlist.get(step-1).suspend();
					threadstate=-1;
				}
				
			}
		});
		
		
		toolbutton3.setIcon(icon3);
		toolbutton3.setFocusable(false);
		toolbutton3.setContentAreaFilled(false);
		toolbutton3.setBorderPainted(false);
		toolbutton3.addMouseListener(new ButtonMouseListener());
		toolbutton3.setPreferredSize(new Dimension(21,21));
		toolbutton3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(threadstate!=0){
					if(threadstate==-1){
						mainthread.resume();
						threadlist.get(step-1).resume();
						threadstate=1;
					}
					mainthread.stop();
					for(Thread t:threadlist){
						t.stop();
					}
				}
				threadstate=0;
				step=1;
				
				initUIPanel();
				
			}
		});
		
		progressbar=new JProgressBar(){

			@Override
			public void setUI(ProgressBarUI ui) {
				// TODO Auto-generated method stub
				super.setUI(new ProgressUI(this,new Color(255, 201, 14)));
			}
			
		};
		
		progressbar.setMinimum(0);  
        progressbar.setMaximum(100);  
        progressbar.setValue(0);  
        progressbar.setStringPainted(false);  
        progressbar.setPreferredSize(new Dimension(400, 23)); 
        
        progressbarlabel=new JLabel();
        progressbarlabel.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        progressbarlabel.setText("0%");
		

		toolbuttonpanel1.setLayout(new GridLayout());
		toolbuttonpanel1.setBackground(new Color(207, 214, 229));
		toolbuttonpanel1.add(toolbutton1);
		toolbuttonpanel2.setLayout(new GridLayout());
		toolbuttonpanel2.setBackground(new Color(207, 214, 229));
		toolbuttonpanel2.add(toolbutton2);
		toolbuttonpanel3.setLayout(new GridLayout());
		toolbuttonpanel3.setBackground(new Color(207, 214, 229));
		toolbuttonpanel3.add(toolbutton3);

		emptypanel1=new JPanel();
		emptypanel1.setPreferredSize(new Dimension(16, 23));
		emptypanel1.setBackground(new Color(133,145,162));
		emptypanel1.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 10, new Color(207, 214, 229)));
		
		toolpanel.setBackground(new Color(207, 214, 229));
		toolpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5,3));
		
		toolpanel.add(toolbuttonpanel1);
		toolpanel.add(toolbuttonpanel2);
		toolpanel.add(toolbuttonpanel3);
		toolpanel.add(emptypanel1);
		toolpanel.add(progressbar);
		toolpanel.add(progressbarlabel);
		
		toolpanel.setPreferredSize(new Dimension(100, 29));
		toolpanel.setMaximumSize(new Dimension(100, 29));
		toolpanel.setMinimumSize(new Dimension(100, 29));
		
	}

	public void initUIPanel() {
		// TODO Auto-generated method stub
		
		progressbarindex=0;
		progressbar.setValue(0);
		progressbarlabel.setText("");
		
		for(FixedButtonTabbedPanel fbtpanel:mainFrame.getStepThreeCenterTabbedPane().getFixButtonTabbedPanelList()){
			fbtpanel.setVisible(false);
		}
		mainFrame.getStepThreeCenterTabbedPane().getTestCaseProcessButtonPanel().setVisible(true);
		mainFrame.getStepThreeCenterTabbedPane().setFixButtonTabbedPanelSelectedIndex(0);
		tablepanel.removeAll();
		
		moviepanel.getMovieLabel().setText("�ȴ����в�����������");
		
		DefaultTableModel dtm=mainFrame.getAbstractTestCaseResultPanel().getTestcaseinfortablemodel();
		while(dtm.getRowCount()>0){
			dtm.removeRow(dtm.getRowCount()-1);
		}
		dtm.fireTableDataChanged();
		mainFrame.getAbstractTestCaseResultPanel().getOnenamelabel().setText("");
		mainFrame.getAbstractTestCaseResultPanel().getTworesultpanel().removeAll();
		mainFrame.getAbstractTestCaseResultPanel().getThreeresultpanel().removeAll();
		mainFrame.getAbstractTestCaseResultPanel().getThreenamelabel().setText("");
		
		mainFrame.getConsolePartPanel().getTextarea3().setText("");
		ChangeRepaint();
	}

	protected void initThread() {
		// TODO Auto-generated method stub
		
		//��ʼ���̣߳�����
		initUIPanel();
//		mainFrame.getStepFiveCenterTabbedPane().getTestCaseReportTabbedPane().initUIPanel();
//		mainFrame.getStepFiveCenterTabbedPane().getTestCaseReportDiagramButton().doClick();
		
		selectUppaal=mainFrame.getTestCaseGenerationPanel().getSelectUppaalCheckBox().getText();
		selectCover=mainFrame.getTestCaseGenerationPanel().getSelectCoverCheckBox().getText();
		System.out.println(selectUppaal+"  ------  "+selectCover);
		
		selectCoverState=0;
		if(selectCover.equals("״̬����")){
			selectCoverState=0;
		}
		else if(selectCover.equals("Ǩ�Ƹ���")){
			selectCoverState=1;
		}
//		else if(selectCover.equals("���ܲ���")){
//			selectCoverState=2;
//		}
		
		String baseUrl = "D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\";
		
		starttype=mainFrame.getHomeAllTabbedPanel().getStarttype();
		if(starttype == 1){
			baseUrl += "\\FunctionalTest\\";
		} else if (starttype == 2) {
			baseUrl += "\\PerformanceTest\\";
		} else if (starttype == 3) {
			baseUrl += "\\TimeTest\\";
		}
		
		selectUppaalPath = baseUrl + selectUppaal + ".xml";
		
		System.out.println(selectUppaalPath+"  ----------  "+selectCoverState);
		
		automatictimestate=0;
		
		a=null;
		type_a=null;
		
		stepAllProcessList=new ArrayList<>();
		timeAllProcessList=new ArrayList<>();
		resultAllProcessList=new ArrayList<>();
		
		testCase=new ArrayList<>();
		
		maincallable=new Callable<Integer>() {
			
			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				
				while(progressbarindex<=100){
					System.out.println(progressbarindex+"  "+(int)(((double)100/stepsum)*step+1));
					if(progressbarindex==(int)(((double)100/stepsum)*step+1)){
						//������һ���̣߳�������list
						
						if(futuretasklist.get(step-1).isDone()){
							System.out.println("123456789-----------------123456789--------------------123456789");

							System.out.println(step);
							
							if(step<stepsum){
								threadlist.get(step).start();
							}
							
							step++;
							progressbarindex++;
							progressbar.setValue(progressbar.getValue()+1);
							progressbarlabel.setText(progressbar.getValue()+"%");
						}
						
						
					}
					else{
						progressbarindex++;
						progressbar.setValue(progressbar.getValue()+1);
						progressbarlabel.setText(progressbar.getValue()+"%");
					}
//					if(step==stepsum-1&&!futuretasklist.get(step-1).isDone()){//����ʵ��������ʱ����������ʱ��
//						Thread.sleep(5000);
//					}
//					else if (step==stepsum&&!futuretasklist.get(step-1).isDone()){
//						Thread.sleep(10000);
//					}
					if(!futuretasklist.get(step-1).isDone()){
						Thread.sleep(500);
					}
					else{
						Thread.sleep(100);
					}
				}
				
				initTestCaseProcessEndUIData();
				
				threadstate=0;
				
				return progressbarindex;
			}
		};
		maintask=new FutureTask<>(maincallable);
		mainthread=new Thread(maintask);
		
		callable1=new Callable<Integer>() {
			
			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				
				time1=System.currentTimeMillis();
				
				moviepanel.getMovieLabel().setText("���ڽ���xml����ȡʱ���Զ���");
				TextAreaPrint("���ڽ���xml����ȡʱ���Զ���");
				
				UppaalParseInforTabbedPanel copyupitpanel=new UppaalParseInforTabbedPanel(mainFrame);
				
				tablepanel.removeAll();
				tablepanel.add(copyupitpanel.getInforpanel());
				
				//��ȡ����

				a = GetAutomatic.getAutomatic(selectUppaalPath);
				type_a=AddType.addType(a);
				
//				if(a.getClockSet().get(0).equals("t")){
//					automatictimestate=1;
//					clockstate="��";
//				}
				if(a.getClockSet().size()!=0){
					automatictimestate=1;
					clockstate="��";
				}
				
				Thread.sleep(1000);
				
				mainFrame.getStepThreeCenterTabbedPane().getUppaalParseInforTabbedPanel().getGeneralinforlabel1().setText("ʱ���Զ������֣�"+a.getName());
				mainFrame.getStepThreeCenterTabbedPane().getUppaalParseInforTabbedPanel().getGeneralinforlabel2().setText("�Ƿ���ʱ��Լ����"+clockstate);
				mainFrame.getStepThreeCenterTabbedPane().getUppaalParseInforTabbedPanel().getGeneralinforlabel3().setText("ģ������״̬������"+a.getStateSet().size());
				mainFrame.getStepThreeCenterTabbedPane().getUppaalParseInforTabbedPanel().getGeneralinforlabel4().setText("ģ������Ǩ�Ƹ�����"+a.getTransitionSet().size());
				
				copyupitpanel.getGeneralinforlabel1().setText("ʱ���Զ������֣�"+a.getName());
				copyupitpanel.getGeneralinforlabel2().setText("�Ƿ���ʱ��Լ����"+clockstate);
				copyupitpanel.getGeneralinforlabel3().setText("ģ������״̬������"+a.getStateSet().size());
				copyupitpanel.getGeneralinforlabel4().setText("ģ������Ǩ�Ƹ�����"+a.getTransitionSet().size());
				
				TextAreaPrint("ʱ���Զ������֣�"+a.getName());
				TextAreaPrint("�Ƿ���ʱ��Լ����"+clockstate);
				TextAreaPrint("ģ������״̬������"+a.getStateSet().size());
				TextAreaPrint("ģ������Ǩ�Ƹ�����"+a.getTransitionSet().size());
				
				DefaultTableModel copystatetablemodel=copyupitpanel.getStateinforpanel().getAttributetablemodel();
				DefaultTableModel copymigratetablemodel=copyupitpanel.getMigrateinforpanel().getAttributetablemodel();
				
				DefaultTableModel stateinfortablemodel=mainFrame.getStepThreeCenterTabbedPane().getUppaalParseInforTabbedPanel().getStateinforpanel().getAttributetablemodel();
				DefaultTableModel migrateinfortablemodel=mainFrame.getStepThreeCenterTabbedPane().getUppaalParseInforTabbedPanel().getMigrateinforpanel().getAttributetablemodel();
				
				while(stateinfortablemodel.getRowCount()>0){
					stateinfortablemodel.removeRow(stateinfortablemodel.getRowCount()-1);
				}
				
				while(migrateinfortablemodel.getRowCount()>0){
					migrateinfortablemodel.removeRow(migrateinfortablemodel.getRowCount()-1);
				}

				for(State s:type_a.getStateSet()){
//					Object[] rowData={"1","loc_id_29C2E776_04D4_47f3_8F70_D9F4DD7BEE72_14","loc_id_29C2E776_04D4_47f3_8F70_D9F4DD7BEE72_14","false","CircularNode"};
					Object[] rowData={s.getId()+"",s.getName(),s.getPosition(),s.isFinalState()+"",s.getType()};
					copystatetablemodel.addRow(rowData);
					stateinfortablemodel.addRow(rowData);
					TextAreaPrint(s.toString());
				}
				
				for(Transition t:type_a.getTransitionSet()){
//					Object[] rowData={"13","set_throttle_out_unstabilizedfloat, bool, float","g.throttle_filt#g.throttle_filt:float","cycle=2.5ms--control_mode==0#control_mode:int8_t--motor_state==False || ap.throttle_zero==True#motor_state:bool,ap.throttle_zero:bool","null","���գ�����û������"};
					Object[] rowData={t.getId()+"",t.getName(),t.getSource(),t.getTarget(),t.getIn(),t.getOut(),t.getCondition()};
					copymigratetablemodel.addRow(rowData);
					migrateinfortablemodel.addRow(rowData);
					TextAreaPrint(t.toString());
				}
				
				mainFrame.getStepThreeCenterTabbedPane().getUppaalParseInforButtonPanel().setVisible(true);
				
				time2=System.currentTimeMillis();
				
				stepAllProcessList.add("��һ������ȡʱ���Զ���");
				timeAllProcessList.add(time2-time1+"ms");
				resultAllProcessList.add("ͨ������xml����������"+a.getStateSet().size()+"��״̬��"+a.getTransitionSet().size()+"��Ǩ��");
				
				return 1;
			}
		};
		task1=new FutureTask<>(callable1);
		thread1=new Thread(task1);
		
		callable2=new Callable<Integer>() {
			
			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				
				time1=System.currentTimeMillis();
				
				moviepanel.getMovieLabel().setText("���ڽ��з���״̬���,ȥ������ʱ��Ǩ��");
				TextAreaPrint("���ڽ��з���״̬���,ȥ������ʱ��Ǩ��");
				
				Thread.sleep(1000);
				
				UppaalOptimizationTabbedPanel copyuotpanel=new UppaalOptimizationTabbedPanel(mainFrame);
				
				tablepanel.removeAll();
				tablepanel.add(copyuotpanel.getInforpanel());
				
				//��ȡ����
				iPARAutomatic=IPR__1.iPR(a);//״̬���
				
				aTDRTAutomatic=ATDTR__1.aTDRT(iPARAutomatic,a);//ȥ��Ǩ��
				
				XmlOfTime.findEndStateList(aTDRTAutomatic);
				
				List<State> oldstatelists=aTDRTAutomatic.getStateSet();//���״̬ID
				ArrayList<State> newstatelists=new ArrayList<>();
				int stateindex=1;
				for(State s:oldstatelists){
					s.setId(stateindex++);
					newstatelists.add(s);
				}
				aTDRTAutomatic.setStateSet(newstatelists);
				
				type_aTDRTAutomatic=AddType.addType(aTDRTAutomatic);
				
				Thread.sleep(1000);
				
				mainFrame.getStepThreeCenterTabbedPane().getUppaalOptimizationTabbedPanel().getGeneralinforlabel1().setText("ʱ���Զ������֣�"+aTDRTAutomatic.getName());
				mainFrame.getStepThreeCenterTabbedPane().getUppaalOptimizationTabbedPanel().getGeneralinforlabel2().setText("�Ƿ���ʱ��Լ����"+clockstate);
				mainFrame.getStepThreeCenterTabbedPane().getUppaalOptimizationTabbedPanel().getGeneralinforlabel3().setText("ģ������״̬������"+aTDRTAutomatic.getStateSet().size());
				mainFrame.getStepThreeCenterTabbedPane().getUppaalOptimizationTabbedPanel().getGeneralinforlabel4().setText("ģ������Ǩ�Ƹ�����"+aTDRTAutomatic.getTransitionSet().size());
				
				copyuotpanel.getGeneralinforlabel1().setText("ʱ���Զ������֣�"+aTDRTAutomatic.getName());
				copyuotpanel.getGeneralinforlabel2().setText("�Ƿ���ʱ��Լ����"+clockstate);
				copyuotpanel.getGeneralinforlabel3().setText("ģ������״̬������"+aTDRTAutomatic.getStateSet().size());
				copyuotpanel.getGeneralinforlabel4().setText("ģ������Ǩ�Ƹ�����"+aTDRTAutomatic.getTransitionSet().size());
				
				TextAreaPrint("ʱ���Զ������֣�"+aTDRTAutomatic.getName());
				TextAreaPrint("�Ƿ���ʱ��Լ����"+clockstate);
				TextAreaPrint("ģ������״̬������"+aTDRTAutomatic.getStateSet().size());
				TextAreaPrint("ģ������Ǩ�Ƹ�����"+aTDRTAutomatic.getTransitionSet().size());
				
				DefaultTableModel copystatetablemodel=copyuotpanel.getStateinforpanel().getAttributetablemodel();
				DefaultTableModel copymigratetablemodel=copyuotpanel.getMigrateinforpanel().getAttributetablemodel();
				
				DefaultTableModel stateinfortablemodel=mainFrame.getStepThreeCenterTabbedPane().getUppaalOptimizationTabbedPanel().getStateinforpanel().getAttributetablemodel();
				DefaultTableModel migrateinfortablemodel=mainFrame.getStepThreeCenterTabbedPane().getUppaalOptimizationTabbedPanel().getMigrateinforpanel().getAttributetablemodel();
				
				while(stateinfortablemodel.getRowCount()>0){
					stateinfortablemodel.removeRow(stateinfortablemodel.getRowCount()-1);
				}
				
				while(migrateinfortablemodel.getRowCount()>0){
					migrateinfortablemodel.removeRow(migrateinfortablemodel.getRowCount()-1);
				}
				
				Random rand=new Random();
				int index;
				
				for(State s:type_aTDRTAutomatic.getStateSet()){
					index=rand.nextInt(3);
					if(index==2){
						index=-1;
					}
					index=0;
//					Object[] rowData={index,"1","loc_id_29C2E776_04D4_47f3_8F70_D9F4DD7BEE72_14","loc_id_29C2E776_04D4_47f3_8F70_D9F4DD7BEE72_14","false","CircularNode"};
					Object[] rowData={index,s.getId()+"",s.getName(),s.getPosition(),s.isFinalState()+"",s.getType()};
					copystatetablemodel.addRow(rowData);
					stateinfortablemodel.addRow(rowData);
					TextAreaPrint(s.toString());
				}
				
//				for(Transition t:type_aTDRTAutomatic.getTransitionSet()){
//					index=rand.nextInt(3);
//					if(index==2){
//						index=-1;
//					}
////					Object[] rowData={index,"13","set_throttle_out_unstabilizedfloat, bool, float","g.throttle_filt#g.throttle_filt:float","cycle=2.5ms--control_mode==0#control_mode:int8_t--motor_state==False || ap.throttle_zero==True#motor_state:bool,ap.throttle_zero:bool","null","���գ�����û������"};
//					Object[] rowData={index,t.getId()+"",t.getName(),t.getSource(),t.getTarget(),t.getIn(),t.getOut(),t.getCondition()};
//					copymigratetablemodel.addRow(rowData);
//					migrateinfortablemodel.addRow(rowData);
//				}
				System.out.println("-----------------------------------------");
				for(Transition t:type_aTDRTAutomatic.getTransitionSet()){
					System.out.println(t.getId()+""+" - "+t.getName()+" - "+t.getSource()+" - "+t.getTarget()+" - "+t.getIn()+" - "+t.getOut()+" - "+t.getCondition());
					index=rand.nextInt(3);
					if(index==2){
						index=-1;
					}
					index=0;
					t.setId(Integer.valueOf(t.getTranTimeName().replace("t", "")));
//					index,t.getId()+"",t.getName(),t.getSource(),t.getTarget(),t.getIn(),t.getOut(),t.getCondition()};
					Object[] rowData={index,t.getId()+"",t.getName(),t.getSource(),t.getTarget(),t.getIn(),t.getOut()+"",t.getCondition()};
					copymigratetablemodel.addRow(rowData);
					migrateinfortablemodel.addRow(rowData);
					TextAreaPrint(t.toString());
				}
				System.out.println("-----------------------------------------");
				
				mainFrame.getStepThreeCenterTabbedPane().getUppaalOptimizationButtonPanel().setVisible(true);
				
				time2=System.currentTimeMillis();
				
				stepAllProcessList.add("�ڶ�����Լ��״̬�ռ�");
				timeAllProcessList.add(time2-time1+"ms");
				resultAllProcessList.add("�õ�"+aTDRTAutomatic.getStateSet().size()+"��״̬��"+aTDRTAutomatic.getTransitionSet().size()+"��Ǩ��");
				
				return 1;
			}
		};
		task2=new FutureTask<>(callable2);
		thread2=new Thread(task2);
		
		callable3=new Callable<Integer>() {
			
			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				
				time1=System.currentTimeMillis();
				
				moviepanel.getMovieLabel().setText("���ڽ�����ȱ���ʱ���Զ����������������������");
				TextAreaPrint("���ڽ�����ȱ���ʱ���Զ����������������������");

				if(starttype==1){//���ܲ���
					if(selectCoverState==0){//״̬����
						//��ȡ����
						DFStree=StateCoverage__1.DFSTree(type_a);
						//Automate����ת��Ϊxml
//						AutomateTransformXml(DFStree);
						AutomateTransformXml(type_a);
						
					}
					else if(selectCoverState==1){//Ǩ�Ƹ���
						AutomateTransformXml(type_a);
					}
				}
				else if(starttype==2){//���ܲ���
					System.out.println("+++++++++++-------------------");
					DFStree=GeneratePath.getPerformPathFromAutomatic(type_a);
					System.out.println("//////////++++++++++++++++++++");
					//Automate����ת��Ϊxml
					AutomateTransformXml(type_a);
				}
				else if(starttype==3){//ʱ��Լ������
					AutomateTransformXml(type_aTDRTAutomatic);
				}
				
				System.out.println("/////////////////********************");
				
//				String xmlname="EA���ܲ���-��ɸ߶�V9Uppaal.uppaal.violet.xml";
//				AutomateTransformXml(type_a);
				
				Thread.sleep(1000);

				GraphFile absfGraphFile=ImportByDoubleClick.importFileByDoubleClick("UPPAAL","abs.uppaal.violet.xml");
//				GraphFile absfGraphFile=ImportByDoubleClick.importFileByDoubleClick("UPPAAL",xmlname);
				IWorkspace workspace=new Workspace(absfGraphFile);
				TestCaseUppaalTabbedPanel tcutpanel=new TestCaseUppaalTabbedPanel(mainFrame, workspace);
				
				IWorkspace copyworkspace=new Workspace(absfGraphFile);
				TestCaseUppaalTabbedPanel copytcutpanel=new TestCaseUppaalTabbedPanel(mainFrame, copyworkspace);
				
				TranMessageColorize tmc=new TranMessageColorize();
				if(starttype==1){//���ܲ���
					if(selectCoverState==0){//״̬����
						tmc.ColorizeDFSTree(DFStree,mainFrame,workspace);
						tmc.ColorizeDFSTree(DFStree,mainFrame,copyworkspace);
					}
					else if(selectCoverState==1){//Ǩ�Ƹ���
						
					}
				}
				else if(starttype==2){//���ܲ���
					tmc.ColorizeDFSTree(DFStree,mainFrame,workspace);
					tmc.ColorizeDFSTree(DFStree,mainFrame,copyworkspace);
				}
				else if(starttype==3){//ʱ��Լ������
					if(selectCoverState==0){//״̬����
						
					}
					else if(selectCoverState==1){//Ǩ�Ƹ���
						
					}
				}
				
//				if(selectCoverState!=1){
//					TranMessageColorize tmc=new TranMessageColorize();
//					tmc.ColorizeDFSTree(DFStree,mainFrame,workspace);
//					tmc.ColorizeDFSTree(DFStree,mainFrame,copyworkspace);
//				}
				
				
				mainFrame.getStepThreeCenterTabbedPane().setTestCaseUppaalTabbedPanel(tcutpanel);
				
				tablepanel.removeAll();
				tablepanel.add(copytcutpanel.getDiagramPanel());
				
				System.out.println("122222222222222222222222222223333333333333333333");
				
				//·����Ϣ
//				List<Automatic> autopathlist=new ArrayList<>();
//				autopathlist=TestAutoDiagram.PathAuto();
//				
//				JPanel resultpanel=new JPanel();
//				JPanel emptypanel=new JPanel();
//				resultpanel.setOpaque(false);
//				emptypanel.setOpaque(false);
//				
//				GridBagLayout layout = new GridBagLayout();
//				resultpanel.setLayout(layout);
//				int i=0;
//				
//				for(Automatic am:autopathlist){
//					TestCasePathPanel tcppanel=new TestCasePathPanel(am);
//					resultpanel.add(tcppanel);
//					layout.setConstraints(tcppanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//				}
//				
////				for(int j=0;j<30;j++){
////					TestCasePathPanel tcppanel=new TestCasePathPanel();
////					resultpanel.add(tcppanel);
////					layout.setConstraints(tcppanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
////				}
//				resultpanel.add(emptypanel);
//				layout.setConstraints(emptypanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//				
//				mainFrame.getAbstractTestCaseResultPanel().getThreeresultpanel().removeAll();
//				mainFrame.getAbstractTestCaseResultPanel().getThreeresultpanel().add(resultpanel);
//				mainFrame.getAbstractTestCaseResultPanel().getTestcaselabeltab3().doClick();
				
				mainFrame.getStepThreeCenterTabbedPane().getTestCaseUppaalButtonPanel().setVisible(true);

				time2=System.currentTimeMillis();
				
				if(starttype==1){//���ܲ���
					if(selectCoverState==0){//״̬����
							stepAllProcessList.add("�ڶ����������������������");
					}
					else if(selectCoverState==1){//Ǩ�Ƹ���
							stepAllProcessList.add("�ڶ����������������������");
					}
				}
				else if(starttype==2){//���ܲ���
					stepAllProcessList.add("�ڶ����������������������");
				}
				else if(starttype==3){//ʱ��Լ������
					stepAllProcessList.add("�������������������������");
				}
				
				timeAllProcessList.add(time2-time1+"ms");
				resultAllProcessList.add("ͨ����ȱ���ʱ���Զ������õ��������������");
				
				return 1;
			}
		};
		task3=new FutureTask<>(callable3);
		thread3=new Thread(task3);
		
		callable4 = new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				
				time1=System.currentTimeMillis();
				
				if(starttype==1){//���ܲ���
					if(selectCoverState==0){//״̬����
						//��ȡ����
						testCase=StateCoverage__1.testCase(DFStree);
						AutomateTransformXml(DFStree);
						
					}
					else if(selectCoverState==1){//Ǩ�Ƹ���
						testCase=GeneratePath.getFormatPathFromAutomatic(a, 100);
					}
				}
				else if(starttype==2){//���ܲ���
					DFStree.setName("��������1");
					testCase.add(DFStree);
				}
				else if(starttype==3){//ʱ��Լ������
					if(selectCoverState==0){//״̬����
						testCase=StateCoverage__1.testCase(aTDRTAutomatic);						
					}
					else if(selectCoverState==1){//Ǩ�Ƹ���
						testCase=PathCoverage_new.testCase(aTDRTAutomatic);
					}
				}
				
				System.err.println("------------------ "+testCase.size()+" ------------------");
				
				Thread.sleep(1000);
				
				//��һ����xml
				GraphFile absfGraphFile=ImportByDoubleClick.importFileByDoubleClick("UPPAAL","abs.uppaal.violet.xml");
				IWorkspace workspace=new Workspace(absfGraphFile);
				TestCaseCoverTabbedPanel tcctpanel=new TestCaseCoverTabbedPanel(mainFrame, workspace);
				
				IWorkspace copyworkspace=new Workspace(absfGraphFile);
				TestCaseCoverTabbedPanel copytcctpanel=new TestCaseCoverTabbedPanel(mainFrame, copyworkspace);
				
				mainFrame.getStepThreeCenterTabbedPane().setTestCaseCoverTabbedPanel(tcctpanel);
				
				TranMessageColorize tmc=new TranMessageColorize();
				tmc.CleanColorize(workspace);
				tmc.CleanColorize(copyworkspace);
				
				tablepanel.removeAll();
				tablepanel.add(copytcctpanel.getResultpanel());
				
				if(starttype==1){//���ܲ���
					if(selectCoverState==0){//״̬����
						moviepanel.getMovieLabel().setText("���ڽ���״̬���ǣ����ɲ���·��");
						mainFrame.getStepThreeCenterTabbedPane().getTestCaseCoverTabbedPanel().getMoviepanel().getMovieLabel().setText("���ڽ���״̬���ǣ����ɲ�������");
						mainFrame.getStepThreeCenterTabbedPane().getTestCaseCoverButton().setText("״̬����");
						TextAreaPrint("���ڽ���״̬���ǣ����ɲ���·��");
					}
					else if(selectCoverState==1){//Ǩ�Ƹ���
						moviepanel.getMovieLabel().setText("���ڽ���Ǩ�Ƹ��ǣ����ɲ���·��");
						mainFrame.getStepThreeCenterTabbedPane().getTestCaseCoverTabbedPanel().getMoviepanel().getMovieLabel().setText("���ڽ���Ǩ�Ƹ��ǣ����ɲ�������");
						mainFrame.getStepThreeCenterTabbedPane().getTestCaseCoverButton().setText("Ǩ�Ƹ���");
						TextAreaPrint("���ڽ���Ǩ�Ƹ��ǣ����ɲ���·��");
					}
				}
				else if(starttype==2){//���ܲ���
					moviepanel.getMovieLabel().setText("���ڽ������ܲ��ԣ����ɲ���·��");
					mainFrame.getStepThreeCenterTabbedPane().getTestCaseCoverTabbedPanel().getMoviepanel().getMovieLabel().setText("���ڽ������ܲ��ԣ����ɲ�������");
					mainFrame.getStepThreeCenterTabbedPane().getTestCaseCoverButton().setText("���ܲ���");
					TextAreaPrint("���ڽ������ܲ��ԣ����ɲ���·��");
				}
				else if(starttype==3){//ʱ��Լ������
					if(selectCoverState==0){//״̬����
						moviepanel.getMovieLabel().setText("���ڽ���״̬���ǣ����ɲ���·��");
						mainFrame.getStepThreeCenterTabbedPane().getTestCaseCoverTabbedPanel().getMoviepanel().getMovieLabel().setText("���ڽ���״̬���ǣ����ɲ�������");
						mainFrame.getStepThreeCenterTabbedPane().getTestCaseCoverButton().setText("״̬����");
						TextAreaPrint("���ڽ���״̬���ǣ����ɲ���·��");
					}
					else if(selectCoverState==1){//Ǩ�Ƹ���
						moviepanel.getMovieLabel().setText("���ڽ���Ǩ�Ƹ��ǣ����ɲ���·��");
						mainFrame.getStepThreeCenterTabbedPane().getTestCaseCoverTabbedPanel().getMoviepanel().getMovieLabel().setText("���ڽ���Ǩ�Ƹ��ǣ����ɲ�������");
						mainFrame.getStepThreeCenterTabbedPane().getTestCaseCoverButton().setText("Ǩ�Ƹ���");
						TextAreaPrint("���ڽ���Ǩ�Ƹ��ǣ����ɲ���·��");
					}
				}

				
				int k=1;
				for (Automatic am : testCase) {

					SaveText.init("D:\\Text\\testfugai.txt");
					SaveText.SaveWord("��������ID: " + k);
					SaveText.SaveWord("Ǩ���б�: ");
					for (Transition t : am.getTransitionSet()) {
						SaveText.SaveWord(t.toString());
					}
					SaveText.SaveFenGe();
					SaveText.End();

					SaveText.init("D:\\Text\\testlujing.txt");
					SaveText.SaveWord("·��: " + k);
					SaveText.SaveWord(
							"���� " + am.getStateSet().size() + " ��״̬�ڵ� " + am.getTransitionSet().size() + " ����ϢǨ�� ");
					SaveText.SaveWord("·������: ");
					for (int index = 0; index < am.getTransitionSet().size(); index++) {
						SaveText.SaveWord(am.getTransitionSet().get(index).toString());
						if(index<am.getStateSet().size()){
							SaveText.SaveWord(am.getStateSet().get(index).toString());
						}
					}
					SaveText.SaveFenGe();
					SaveText.End();
					
					k++;
				}
				
				//��������
				
//				List<Automatic> autopathlist=new ArrayList<>();
//				autopathlist=TestAutoDiagram.PathAuto();
				
				List<TestCaseCoverPartPanel> coverpartlist=new ArrayList<>();
				
				JPanel resultpanel=new JPanel();
				JPanel emptypanel=new JPanel();
				resultpanel.setOpaque(false);
				emptypanel.setOpaque(false);
				
				JPanel copyresultpanel=new JPanel();
				JPanel copyemptypanel=new JPanel();
				copyresultpanel.setOpaque(false);
				copyemptypanel.setOpaque(false);
				
				GridBagLayout layout = new GridBagLayout();
				resultpanel.setLayout(layout);
				
				GridBagLayout copylayout = new GridBagLayout();
				copyresultpanel.setLayout(copylayout);
				
				int i=0;
				int copyi=0;
				
				for(Automatic am:testCase){

					TestCaseCoverPartPanel tccppanel=new TestCaseCoverPartPanel(mainFrame,am,workspace);//����������С�����·����Ϣ���Լ�workspace
					resultpanel.add(tccppanel);
					layout.setConstraints(tccppanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
					
					coverpartlist.add(tccppanel);
					
					TestCaseCoverPartPanel copytccppanel=new TestCaseCoverPartPanel(mainFrame,am,copyworkspace);//����������С�����·����Ϣ���Լ�workspace
					copyresultpanel.add(copytccppanel);
					copylayout.setConstraints(copytccppanel, new GBC(0, copyi++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
				}
				
				resultpanel.add(emptypanel);
				layout.setConstraints(emptypanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
				
				mainFrame.getStepThreeCenterTabbedPane().getTestCaseCoverTabbedPanel().setTestCaseCoverPartPanelList(coverpartlist);
				
				copyresultpanel.add(copyemptypanel);
				copylayout.setConstraints(copyemptypanel, new GBC(0, copyi++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
				
				mainFrame.getStepThreeCenterTabbedPane().getTestCaseCoverTabbedPanel().getTableresultpanel().removeAll();
				mainFrame.getStepThreeCenterTabbedPane().getTestCaseCoverTabbedPanel().getTableresultpanel().add(resultpanel);
				
				copytcctpanel.getTableresultpanel().removeAll();
				copytcctpanel.getTableresultpanel().add(copyresultpanel);
				
				mainFrame.getStepThreeCenterTabbedPane().getTestCaseCoverButtonPanel().setVisible(true);

				time2=System.currentTimeMillis();
				
				if(starttype==1){//���ܲ���
					if(selectCoverState==0){//״̬����
							stepAllProcessList.add("��������״̬����");
							resultAllProcessList.add("����"+testCase.size()+"������·������������״̬������");
					}
					else if(selectCoverState==1){//Ǩ�Ƹ���
							stepAllProcessList.add("��������Ǩ�Ƹ���");
							resultAllProcessList.add("����"+testCase.size()+"������·������������Ǩ�Ʊ�����");
					}
				}
				else if(starttype==2){//���ܲ���
					stepAllProcessList.add("�����������ܲ���");
					resultAllProcessList.add("����"+testCase.size()+"������·��");
				}
				else if(starttype==3){//ʱ��Լ������
					if(selectCoverState==0){//״̬����
						stepAllProcessList.add("���Ĳ���״̬����");
						resultAllProcessList.add("����"+testCase.size()+"������·������������״̬������");
					}
					else if(selectCoverState==1){//Ǩ�Ƹ���
						stepAllProcessList.add("���Ĳ���Ǩ�Ƹ���");
						resultAllProcessList.add("����"+testCase.size()+"������·������������Ǩ�Ʊ�����");
					}
				}
				
				timeAllProcessList.add(time2-time1+"ms");
				
				return 1;
			}
		};
		task4 = new FutureTask<>(callable4);
		thread4 = new Thread(task4);

		callable5 = new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				
				time1=System.currentTimeMillis();
				
				moviepanel.getMovieLabel().setText("����ɸѡ����ӡ����ʵ����Լ�����������ɲ�������");
				TextAreaPrint("����ɸѡ����ӡ����ʵ����Լ�����������ɲ�������");
				
				//��ȡ����
				collectLimit = forPlatform.collectLimit(testCase);
				
				
				int k=1;
				for (Automatic am : collectLimit) {

					SaveText.init("D:\\Text\\testyueshu.txt");
					SaveText.SaveWord("��������ID: " + k);
					SaveText.SaveWord("Ǩ���б�: ");
					for (Transition t : am.getTransitionSet()) {
						SaveText.SaveWord(t.toString());
					}
					SaveText.SaveFenGe();
					SaveText.End();

					k++;
				}
				
				
				Thread.sleep(1000);
				
				TestCaseProduceTabbedPanel copytcptpanel=new TestCaseProduceTabbedPanel(mainFrame);
				
				tablepanel.removeAll();
				tablepanel.add(copytcptpanel.getTabelpanel());
				
				//�����������
				List<TestCaseProducePartPanel> producepartlist=new ArrayList<>();
				
				JPanel resultpanel=new JPanel();
				JPanel emptypanel=new JPanel();
				resultpanel.setOpaque(false);
				emptypanel.setOpaque(false);
				
				JPanel copyresultpanel=new JPanel();
				JPanel copyemptypanel=new JPanel();
				copyresultpanel.setOpaque(false);
				copyemptypanel.setOpaque(false);
				
				GridBagLayout layout = new GridBagLayout();
				resultpanel.setLayout(layout);
				
				GridBagLayout copylayout = new GridBagLayout();
				copyresultpanel.setLayout(copylayout);
				
				int i=0;
				int copyi=0;
				for(Automatic am:collectLimit){
					
					TestCaseProducePartPanel tcpppanel=new TestCaseProducePartPanel(mainFrame,am);//���뵥������������Ϣ
					resultpanel.add(tcpppanel);
					layout.setConstraints(tcpppanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
					
					producepartlist.add(tcpppanel);
					
					TestCaseProducePartPanel copytcpppanel=new TestCaseProducePartPanel(mainFrame,am);//���뵥������������Ϣ
					copyresultpanel.add(copytcpppanel);
					copylayout.setConstraints(copytcpppanel, new GBC(0, copyi++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
				}
				resultpanel.add(emptypanel);
				layout.setConstraints(emptypanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
				
				mainFrame.getStepThreeCenterTabbedPane().getTestCaseProduceTabbedPanel().setTestCaseProducePartPanelList(producepartlist);
				
				copyresultpanel.add(copyemptypanel);
				copylayout.setConstraints(copyemptypanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
				
				mainFrame.getStepThreeCenterTabbedPane().getTestCaseProduceTabbedPanel().getTableresultpanel().removeAll();
				mainFrame.getStepThreeCenterTabbedPane().getTestCaseProduceTabbedPanel().getTableresultpanel().add(resultpanel);
				
				copytcptpanel.getTableresultpanel().removeAll();
				copytcptpanel.getTableresultpanel().add(copyresultpanel);
				
				mainFrame.getStepThreeCenterTabbedPane().getTestCaseProduceButtonPanel().setVisible(true);
				
				time2=System.currentTimeMillis();
				
				if(starttype==1){//���ܲ���
					stepAllProcessList.add("���Ĳ�����ȡ��������");
				}
				else if(starttype==2){//���ܲ���
					stepAllProcessList.add("���Ĳ�����ȡ��������");
				}
				else if(starttype==3){//ʱ�����
					stepAllProcessList.add("���岽����ȡ��������");
				}
				
				timeAllProcessList.add(time2-time1+"ms");
				resultAllProcessList.add("��ÿ������·����Լ���������д����õ�"+collectLimit.size()+"����������");
				
				return 1;
			}
		};
		task5 = new FutureTask<>(callable5);
		thread5 = new Thread(task5);
		
		callable6=new Callable<Integer>() {
			
			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				
				time1=System.currentTimeMillis();
				
				moviepanel.getMovieLabel().setText("�������ɲ��������ļ�");
				TextAreaPrint("�������ɲ��������ļ�");
				
				String abstractName=selectUppaal.substring(0, selectUppaal.indexOf("ForXStream"));
				String baseUrl = "D:\\ModelDriverProjectFile\\UPPAL\\3.Abstract_TestCase\\";
				
				if(starttype == 1){
					baseUrl += "\\FunctionalTest\\";
				} else if (starttype == 2) {
					baseUrl += "\\PerformanceTest\\";
				} else if (starttype == 3) {
					baseUrl += "\\TimeTest\\";
				}
				
				if(starttype==1){//���ܲ���
					if(selectCoverState==0){//״̬����
						abstractName+="״̬����";
					}
					else if(selectCoverState==1){//Ǩ�Ƹ���
						abstractName+="Ǩ�Ƹ���";
					}
				}
				else if(starttype==2){//���ܲ���
					
				}
				else if(starttype==3){//ʱ��Լ������
					if(selectCoverState==0){//״̬����
						abstractName+="״̬����";
					}
					else if(selectCoverState==1){//Ǩ�Ƹ���
						abstractName+="Ǩ�Ƹ���";
					}
				}
				
				String path = baseUrl + abstractName + "Abstract.txt";
				
				writeAbstractTestCaseSerialFile(collectLimit, path);
				
				Thread.sleep(1000);
				
				time2=System.currentTimeMillis();
				
				if(starttype==1){//���ܲ���
					stepAllProcessList.add("���岽���洢��������");
				}
				else if(starttype==2){//���ܲ���
					stepAllProcessList.add("���岽���洢��������");
				}
				else if(starttype==3){//ʱ�����
					stepAllProcessList.add("���������洢��������");
				}
				
				timeAllProcessList.add(time2-time1+"ms");
				resultAllProcessList.add("����"+ abstractName + "Abstract.txt������·����"+path);
				
				
				return 1;
			}
		};
		task6=new FutureTask<>(callable6);
		thread6=new Thread(task6);
		
		callable7=new Callable<Integer>() {
			
			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				
				time1=System.currentTimeMillis();
				
				moviepanel.getMovieLabel().setText("���ڶԲ������н�������");
				TextAreaPrint("���ڶԲ������н�������");
				
				List<TestCaseSortContrastPartPanel> oldsortcontrastpartlist=new ArrayList<>();
				
				JPanel oldresultpanel=new JPanel();
				JPanel oldemptypanel=new JPanel();
				oldresultpanel.setOpaque(false);
				oldemptypanel.setOpaque(false);
				
				GridBagLayout oldlayout = new GridBagLayout();
				oldresultpanel.setLayout(oldlayout);
				
				int oldi=0;
				
				ArrayList<Automatic> oldTestCase=XMLGet.testcaseNew(testCase);
				
				Thread.sleep(1000);
				
				for(Automatic am:oldTestCase){

					TestCaseSortContrastPartPanel tcscppanel=new TestCaseSortContrastPartPanel(mainFrame,am);//����������С�����·����Ϣ���Լ�workspace
					oldresultpanel.add(tcscppanel);
					oldlayout.setConstraints(tcscppanel, new GBC(0, oldi++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
					
					oldsortcontrastpartlist.add(tcscppanel);
					
				}
				
				oldresultpanel.add(oldemptypanel);
				oldlayout.setConstraints(oldemptypanel, new GBC(0, oldi++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
				
				mainFrame.getStepThreeCenterTabbedPane().getTestCaseSortContrastTabbedPanel().setOldTestCaseSortContrastPartPanelList(oldsortcontrastpartlist);
				mainFrame.getStepThreeCenterTabbedPane().getTestCaseSortContrastTabbedPanel().getOldtableresultpanel().removeAll();
				mainFrame.getStepThreeCenterTabbedPane().getTestCaseSortContrastTabbedPanel().getOldtableresultpanel().add(oldresultpanel);
				
				List<TestCaseSortContrastPartPanel> newsortcontrastpartlist=new ArrayList<>();
				
				JPanel newresultpanel=new JPanel();
				JPanel newemptypanel=new JPanel();
				newresultpanel.setOpaque(false);
				newemptypanel.setOpaque(false);
				
				GridBagLayout newlayout = new GridBagLayout();
				newresultpanel.setLayout(newlayout);
				
				int newi=0;
				
				for(Automatic am:testCase){

					TestCaseSortContrastPartPanel tcscppanel=new TestCaseSortContrastPartPanel(mainFrame,am);//����������С�����·����Ϣ���Լ�workspace
					newresultpanel.add(tcscppanel);
					newlayout.setConstraints(tcscppanel, new GBC(0, newi++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
					
					newsortcontrastpartlist.add(tcscppanel);
					
				}
				
				newresultpanel.add(newemptypanel);
				newlayout.setConstraints(newemptypanel, new GBC(0, newi++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
				
				mainFrame.getStepThreeCenterTabbedPane().getTestCaseSortContrastTabbedPanel().setNewTestCaseSortContrastPartPanelList(newsortcontrastpartlist);
				mainFrame.getStepThreeCenterTabbedPane().getTestCaseSortContrastTabbedPanel().getNewtableresultpanel().removeAll();
				mainFrame.getStepThreeCenterTabbedPane().getTestCaseSortContrastTabbedPanel().getNewtableresultpanel().add(newresultpanel);
				
				
				mainFrame.getStepThreeCenterTabbedPane().getTestCaseSortContrastButtonPanel().setVisible(true);
				
				Thread.sleep(1000);
				
				time2=System.currentTimeMillis();
				
				if(starttype==1){//���ܲ���
					stepAllProcessList.add("���岽������Ա�");
				}
				else if(starttype==2){//���ܲ���
					stepAllProcessList.add("���岽������Ա�");
				}
				else if(starttype==3){//ʱ�����
					stepAllProcessList.add("������������Ա�");
				}
				
				timeAllProcessList.add(time2-time1+"ms");
				resultAllProcessList.add("��ɶԲ������е����򣬲�������ǰ��Ĳ������н��жԱ�");
				
				
				return 1;
			}
		};
		task7=new FutureTask<>(callable7);
		thread7=new Thread(task7);
		
		futuretasklist=new ArrayList<>();
		threadlist=new ArrayList<>();
		
		if(starttype==1){//���ܲ���
			futuretasklist.add(task1);
			futuretasklist.add(task3);
			futuretasklist.add(task4);
			futuretasklist.add(task5);
			futuretasklist.add(task6);
			futuretasklist.add(task7);
			
			threadlist.add(thread1);
			threadlist.add(thread3);
			threadlist.add(thread4);
			threadlist.add(thread5);
			threadlist.add(thread6);
			threadlist.add(thread7);
			
			stepsum=6;
		}
		else if(starttype==2){//���ܲ���
			futuretasklist.add(task1);
			futuretasklist.add(task3);
			futuretasklist.add(task4);
			futuretasklist.add(task5);
			futuretasklist.add(task6);
			
			threadlist.add(thread1);
			threadlist.add(thread3);
			threadlist.add(thread4);
			threadlist.add(thread5);
			threadlist.add(thread6);
			
			stepsum=5;
		}
		else if(starttype==3){//ʱ��Լ������
			futuretasklist.add(task1);
			futuretasklist.add(task2);
			futuretasklist.add(task3);
			futuretasklist.add(task4);
			futuretasklist.add(task5);
			futuretasklist.add(task6);
			
			threadlist.add(thread1);
			threadlist.add(thread2);
			threadlist.add(thread3);
			threadlist.add(thread4);
			threadlist.add(thread5);
			threadlist.add(thread6);
			
			stepsum=6;
		}
		
	}

	protected void writeAbstractTestCaseSerialFile(ArrayList<Automatic> abstractAutomatic, String path) {
		// TODO Auto-generated method stub
		
		try {
//			String path="D:\\ModelDriverProjectFile\\UPPAL\\3.Abstract_TestCase\\"+testcasename+"serialtestcase.txt";
			FileOutputStream fos = new FileOutputStream(path);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			
			for(Automatic auto:abstractAutomatic){
				oos.writeObject(auto);
			}
			
			oos.close();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void initTestCaseProcessEndUIData() {
		// TODO Auto-generated method stub
		
		moviepanel.getMovieLabel().setText("���������������");
		TextAreaPrint("���������������");
		TestCaseProcessEndPanel tcpepanel=new TestCaseProcessEndPanel(mainFrame,stepAllProcessList,timeAllProcessList,resultAllProcessList);
		
//		tcpepanel.setStepAllProcessList(stepAllProcessList);
//		tcpepanel.setTimeAllProcessList(timeAllProcessList);
//		tcpepanel.setResultAllProcessList(resultAllProcessList);
		
		
		tablepanel.removeAll();
		tablepanel.add(tcpepanel);
		
		mainFrame.getStepThreeCenterTabbedPane().getTestCaseProcessButtonPanel().setVisible(true);
		mainFrame.getStepThreeCenterTabbedPane().getTestCaseProcessButton().doClick();
		
		
//		String path;
//		PerformanceXML.createXML(collectResult, path);
		
		
		ChangeRepaint();
		
	}

	protected void AutomateTransformXml(Automatic automatic) {
		// TODO Auto-generated method stub
		
//		automatic=TestAutoDiagram.DFSAuto();
		
		try {
			TestAutoDiagram.createSequenceXML(automatic);
			
			LayoutUppaal.layout("sequence.xml");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		abStateList=new ArrayList<>();
		abTransList=new ArrayList<>();
		
		stateIdToNameMap=new HashMap<>();
		stateNameToIdMap=new HashMap<>();
		transitionIdToNameMap=new HashMap<>();
		transitionNameToIdMap=new HashMap<>();
		
		System.out.println("ABCABCABCABCABCABCABCABCABCABCABCABC");
		
		for(State s :automatic.getStateSet()){
			//��wqq����ص���Ϣ--->ת��Ϊzhangjian����ص���Ϣ(state)
			AbstractState abState =new AbstractState();
			abState.setSid(s.getId());//��ѯ���ݿ�����״̬�ڵ�ĸ���
			abState.setSname(s.getName());
			abState.setPosition(s.getPosition());
			
//			abState.setType(s.getType());
			System.out.println("************************  "+s.getType());
			if(s.getType().equals("CircularNode")){
				System.out.println("---------------");
				abState.setType("CircularNode");
			}
			else if(s.getType().equals("Start")||s.getType().equals("start")){
				System.out.println("++++++++++++++++++");
				abState.setType("Start");
			}
			
			abState.setInvariantDBM(s.getInvariantDBM().toString());
			abStateList.add(abState);
			
			stateIdToNameMap.put(s.getId()+"", s.getId()+" "+s.getName());
			stateNameToIdMap.put(s.getId()+" "+s.getName(), s.getId()+"");
			
		}
		
		for(Transition t :automatic.getTransitionSet()){
			//��wqq����ص���Ϣ--->ת��Ϊzhangjian����ص���Ϣ(transition)
			
			int tid;
//			if(starttype==3){//ʱ��Լ����ֺ�Ǩ��id������ظ�������ʱ������t1,t2
//				tid=Integer.valueOf(t.getTranTimeName().replace("t", ""));
//			}
//			else{
				tid=t.getId();
//			}
			
			AbstractTransition abTrans =new AbstractTransition();
//			abTrans.setTid(t.getId());
			abTrans.setTid(tid);
			abTrans.setTname(t.getName());
			abTrans.setSourceID(TestAutoDiagram.getStateIdByName(abStateList, t.getSource())+"");
			
			abTrans.setSource(t.getSource());
			abTrans.setTargetID(TestAutoDiagram.getStateIdByName(abStateList, t.getTarget())+"");
			abTrans.setTarget(t.getTarget());
			
			abTrans.setType(t.getTypes().toString());
//			StringBuilder sb =new StringBuilder();
//			for(int i=0;i<t.getResetClockSet().size();i++){
//				sb.append(t.getResetClockSet().get(i));
//				if(i!=t.getResetClockSet().size()-1){
//					sb.append(";");
//				}
//			}
//			abTrans.setResetClockSet(sb.toString());
//			abTrans.setConstraintDBM(t.getConstraintDBM().toString());
			//System.out.println(t.getTypes()+"**"+t.getSource()+"**"+t.getTarget()+"**"+t.getResetClockSet()+"**"+t.getConstraintDBM());
			abTransList.add(abTrans);
			
			transitionIdToNameMap.put(tid+"", tid+"<br>"+t.getName());
			transitionNameToIdMap.put(tid+"<br>"+t.getName(), tid+"");
		}
		
		System.out.println(stateIdToNameMap);
		System.out.println(transitionIdToNameMap);
		
		System.out.println("--------------------***********************************-----------------------------");
		System.out.println(abStateList.size()+"  -  -  "+abTransList.size());
		
		CreateAbstractUppaalXML c =new CreateAbstractUppaalXML(abStateList, abTransList);
		try {
			 c.create("D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\abs.uppaal.violet.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void AtutomaticProduceXML(List<Automatic> listauto, String path){
		Document doc = DocumentHelper.createDocument();
		Element TCS=doc.addElement("TCS");
		
		for(Automatic am:listauto){
			Element testcase = TCS.addElement("testcase");
			for(Transition t:am.getTransitionSet()){
				Element process = testcase.addElement("process");
				Element operation = process.addElement("operation");
				Element input = process.addElement("input");

				operation.setText(t.getName());
				input.setText(t.getResult().toString().replaceAll("\\[|]", ""));
			}
		}

		try {
			// �����������Ŀ�ĵ�
			
			FileWriter fw = new FileWriter(path);

			// ���������ʽ���ַ���
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");

			// �����������xml�ļ���XMLWriter����
			XMLWriter xmlWriter = new XMLWriter(fw, format);
			xmlWriter.write(doc);// *****
			xmlWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initMoviePanel() {
		// TODO Auto-generated method stub
		
		moviepanel.getMovieLabel().setText("�ȴ����в�����������");
		
	}

	private void initTablePanel() {
		// TODO Auto-generated method stub
		
		tablepanel.setLayout(new GridLayout());
		tablepanel.setOpaque(false);
		
//		TestCaseProcessEndPanel tcpepanel=new TestCaseProcessEndPanel(mainFrame);
//		tablepanel.add(tcpepanel);
		
	}
	
	public static void TextAreaPrint(String word){
		JTextArea textarea=mainFrame.getConsolePartPanel().getTextarea3();
		textarea.append(word+"\n");
		textarea.setCaretPosition(textarea.getDocument().getLength());
	}
	
	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		if(mainFrame.getStepindex()==3){
			this.setVisible(false);
			this.getRootPane().repaint();
			this.setVisible(true);
		}
	}

	public Map<String, String> getStateIdToNameMap() {
		return stateIdToNameMap;
	}

	public Map<String, String> getStateNameToIdMap() {
		return stateNameToIdMap;
	}

	public Map<String, String> getTransitionIdToNameMap() {
		return transitionIdToNameMap;
	}

	public Map<String, String> getTransitionNameToIdMap() {
		return transitionNameToIdMap;
	}

	public int getSelectCoverState() {
		return selectCoverState;
	}
	
	
	
}

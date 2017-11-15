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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;

import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.StepFiveCenterTabbedPane;
import com.horstmann.violet.application.gui.StepFourCenterTabbedPane;
import com.horstmann.violet.application.gui.opreationTreePane.TestCaseConfirmationPanel;
import com.horstmann.violet.application.gui.util.chenzuo.Bean.TestCase;
import com.horstmann.violet.application.gui.util.ckt.handle.Automatic;
import com.horstmann.violet.application.gui.util.ckt.handle.Transition;
import com.horstmann.violet.application.gui.util.ckt.output.forPlatform;
import com.horstmann.violet.application.gui.util.ckt.testcase.PerformanceXML2;
import com.horstmann.violet.application.gui.util.ckt.xml.XmlOfTime;
import com.horstmann.violet.application.gui.util.ckt.xml.borderTestXML;
import com.horstmann.violet.application.gui.util.ckt.xml.outXMLofTime;
import com.horstmann.violet.application.gui.util.tanchao.SaveText;

public class TestCaseInstantiationProcessTabbedPanel extends JPanel{
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
	
	
	private List<FutureTask<Integer>> futuretasklist=new ArrayList<FutureTask<Integer>>();
	private List<Thread> threadlist=new ArrayList<Thread>();
	
	private int stepsum=4;
	private int threadstate=0;
	private int step=1;
	
	private String selectAbstract;
	private String selectAbstractPath;
	private int starttype;//1-功能，2-性能
	private int hastime=0;//0-不含时间，1-含有时间
	
	private ArrayList<Automatic> collectLimit;
	private ArrayList<Automatic> collectResult;
	private ArrayList<Automatic> bordercollectLimit;
	private ArrayList<Automatic> bordercollectResult;
	
	private Automatic PerAutomaticResult;
	
	long time1;
	long time2;
	
	private List<String> stepAllProcessList=new ArrayList<String>();
	private List<String> timeAllProcessList=new ArrayList<String>();
	private List<String> resultAllProcessList=new ArrayList<String>();
	

	public TestCaseInstantiationProcessTabbedPanel(MainFrame mainframe) {

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

		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("ImagePart/start.png"));
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(this.getClass().getResource("ImagePart/suspend.png"));
		icon2.setImage(icon2.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon3 = new ImageIcon(this.getClass().getResource("ImagePart/stop.png"));
		icon3.setImage(icon3.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon4 = new ImageIcon(this.getClass().getResource("ImagePart/up_arrow.png"));
		icon4.setImage(icon4.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon5 = new ImageIcon(this.getClass().getResource("ImagePart/down_arrow.png"));
		icon5.setImage(icon5.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon6 = new ImageIcon(this.getClass().getResource("ImagePart/allselect.png"));
		icon6.setImage(icon6.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon7 = new ImageIcon(this.getClass().getResource("ImagePart/cancelselect.png"));
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
				
				if(mainFrame.getTestCaseInstantiationPanel().getSelectAbstractCheckBox().getText()==null||mainFrame.getTestCaseInstantiationPanel().getSelectAbstractCheckBox().getText().equals("")){
					JOptionPane.showMessageDialog(mainFrame, "请选择要进行实例化的测试序列！", "提示" , JOptionPane.ERROR_MESSAGE);
					return ;
				}
				
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
        progressbarlabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
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
		
		for(FixedButtonTabbedPanel fbtpanel:mainFrame.getStepFourCenterTabbedPane().getFixButtonTabbedPanelList()){
			fbtpanel.setVisible(false);
		}
		mainFrame.getStepFourCenterTabbedPane().getTestCaseInstantiationProcessButtonPanel().setVisible(true);
		mainFrame.getStepFourCenterTabbedPane().setFixButtonTabbedPanelSelectedIndex(0);
		tablepanel.removeAll();
		
		moviepanel.getMovieLabel().setText("等待进行测试用例实例化");
		
		DefaultTableModel dtm=mainFrame.getTestCaseInstantiationResultPanel().getTestcaseinfortablemodel();
		while(dtm.getRowCount()>0){
			dtm.removeRow(dtm.getRowCount()-1);
		}
		dtm.fireTableDataChanged();
		mainFrame.getTestCaseInstantiationResultPanel().getOnenamelabel().setText("");
		mainFrame.getTestCaseInstantiationResultPanel().getTworesultpanel().removeAll();
		
		mainFrame.getConsolePartPanel().getTextarea4().setText("");
		ChangeRepaint();
	}

	protected void initThread() {
		// TODO Auto-generated method stub
		
		//初始化线程，数据
		initUIPanel();
		
		selectAbstract=mainFrame.getTestCaseInstantiationPanel().getSelectAbstractCheckBox().getText();
		
		String baseUrl = "D:\\ModelDriverProjectFile\\UPPAL\\3.Abstract_TestCase\\";
		
		starttype=mainFrame.getTestCaseInstantiationPanel().FindRadioButtonIndex(mainFrame.getTestCaseInstantiationPanel().getSelectTestRadioButton())+1;
		
		if(selectAbstract.contains("TimeAbstract")){
			hastime=1;
		}
		else{
			hastime=0;
		}
		
		if(starttype == 1){
			baseUrl += "\\FunctionalTest\\";
		} else if (starttype == 2) {
			baseUrl += "\\PerformanceTest\\";
		} 
//		else if (starttype == 3) {
//			baseUrl += "\\TimeTest\\";
//		}
		
		mainFrame.getStepFourCenterTabbedPane().getTestCaseShowTabbedPanel().setStarttype(starttype);
		
		selectAbstractPath = baseUrl + selectAbstract + ".txt";
		
		System.out.println(selectAbstractPath+"  ------  ");
		
		stepAllProcessList=new ArrayList<String>();
		timeAllProcessList=new ArrayList<String>();
		resultAllProcessList=new ArrayList<String>();
		
		collectResult=new ArrayList<Automatic>();
		
		maincallable=new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
					

				while(progressbarindex<=100){
					System.out.println(progressbarindex+"  "+(int)(((double)100/stepsum)*step+1));
					if(progressbarindex==(int)(((double)100/stepsum)*step+1)){
						//开启下一个线程，并存入list
						
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
					if(step==2&&!futuretasklist.get(step-1).isDone()){//处于实例化步骤时，增大休眠时间
						Thread.sleep(3000);
					}
//					else if (step==stepsum&&!futuretasklist.get(step-1).isDone()){
//						Thread.sleep(10000);
//					}
					else{
						Thread.sleep(300);
					}
				}
				
				initTestCaseProcessEndUIData();
				
				threadstate=0;
				
				return progressbarindex;
				
			}
		};
		maintask=new FutureTask<Integer>(maincallable);
		mainthread=new Thread(maintask);
		
		callable1=new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				
				time1=System.currentTimeMillis();
				
				moviepanel.getMovieLabel().setText("正在读取测试序列");
				TextAreaPrint("正在读取测试序列");
				
				//获取数据
				collectLimit=(ArrayList<Automatic>) readAbstractTestCaseSerialFile(selectAbstractPath);
				//获取数据
				bordercollectLimit=(ArrayList<Automatic>) readAbstractTestCaseSerialFile(selectAbstractPath);

				Thread.sleep(1000);
				
				TestCaseProduceTabbedPanel copytcptpanel=new TestCaseProduceTabbedPanel(mainFrame);
				
				tablepanel.removeAll();
				tablepanel.add(copytcptpanel.getTabelpanel());
				
				//抽象测试用例
				List<TestCaseProducePartPanel> producepartlist=new ArrayList<TestCaseProducePartPanel>();
				
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
					
					TextAreaPrint(am.getName());
					for(Transition t:am.getTransitionSet()){
						TextAreaPrint(t.getId()+"  -  -  "+t.getName());
						TextAreaPrint(t.getLimit());
					}
					
					TestCaseProducePartPanel tcpppanel=new TestCaseProducePartPanel(mainFrame,am);//传入单个测试用例信息
					resultpanel.add(tcpppanel);
					layout.setConstraints(tcpppanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
					
					producepartlist.add(tcpppanel);
					
					TestCaseProducePartPanel copytcpppanel=new TestCaseProducePartPanel(mainFrame,am);//传入单个测试用例信息
					copyresultpanel.add(copytcpppanel);
					copylayout.setConstraints(copytcpppanel, new GBC(0, copyi++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
				}
				resultpanel.add(emptypanel);
				layout.setConstraints(emptypanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
				
				mainFrame.getStepFourCenterTabbedPane().getTestCaseProduceTabbedPanel().setTestCaseProducePartPanelList(producepartlist);
				
				copyresultpanel.add(copyemptypanel);
				copylayout.setConstraints(copyemptypanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
				
				mainFrame.getStepFourCenterTabbedPane().getTestCaseProduceTabbedPanel().getMoviepanel().getMovieLabel().setText("正在读取测试序列");
				mainFrame.getStepFourCenterTabbedPane().getTestCaseProduceTabbedPanel().getTableresultpanel().removeAll();
				mainFrame.getStepFourCenterTabbedPane().getTestCaseProduceTabbedPanel().getTableresultpanel().add(resultpanel);
				
				copytcptpanel.getTableresultpanel().removeAll();
				copytcptpanel.getTableresultpanel().add(copyresultpanel);
				
				mainFrame.getStepFourCenterTabbedPane().getTestCaseProduceButtonPanel().setVisible(true);
				
				time2=System.currentTimeMillis();
				
				stepAllProcessList.add("第一步：读取测试序列");
				timeAllProcessList.add(time2-time1+"ms");
				resultAllProcessList.add("得到"+collectLimit.size()+"条含有约束条件的抽象测试用例");
				
				return 1;
			}
		};
		task1=new FutureTask<Integer>(callable1);
		thread1=new Thread(task1);
		
		callable2=new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub

//				collectLimit=(ArrayList<Automatic>) readAbstractTestCaseSerialFile(selectAbstractPath);

				time1=System.currentTimeMillis();
				
				moviepanel.getMovieLabel().setText("正在对测试序列进行实例化求解");
				TextAreaPrint("正在对测试序列进行实例化求解");
				
				if(starttype==1){//功能测试
					if(hastime==1){
						collectResult=XmlOfTime.collectResult(collectLimit);
					}
					else{
						//获取数据
						collectResult = forPlatform.collectResult(collectLimit);
						//获取边界值数据
						bordercollectResult = borderTestXML.collectResult(bordercollectLimit);
					}
				}
				else if(starttype==2){//性能测试
					if(hastime==1){
						collectResult=XmlOfTime.collectResult(collectLimit);
					}
					else{
						PerAutomaticResult=PerformanceXML2.getPerformResultFromAutomatic(collectLimit.get(0));
						collectResult.add(PerAutomaticResult);
					}
				}
//				else if(starttype==3){
//					collectResult=XmlOfTime.collectResult(collectLimit);
//				}
				
//				int k=1;
//				for (Automatic am : collectResult) {
//
//					SaveText.init("D:\\Text\\testshilihua.txt");
//					SaveText.SaveWord("测试用例ID: " + k);
//					SaveText.SaveWord("迁移列表: ");
//					for (Transition t : am.getTransitionSet()) {
//						SaveText.SaveWord(t.toString2());
//					}
//					SaveText.SaveFenGe();
//					SaveText.End();
//
//					k++;
//				}
				
				
				Thread.sleep(1000);
				
				TestCaseInstantiationTabbedPanel copytcitpanel=new TestCaseInstantiationTabbedPanel(mainFrame);
				
				tablepanel.removeAll();
				tablepanel.add(copytcitpanel.getTabelpanel());
				
				//实例化
				List<TestCaseInstantiationPartPanel> instantiationpartlist=new ArrayList<TestCaseInstantiationPartPanel>();
				
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
				for(Automatic am:collectResult){
					
					TestCaseInstantiationPartPanel tcippanel=new TestCaseInstantiationPartPanel(mainFrame,am);//传入单个实例化信息
					resultpanel.add(tcippanel);
					layout.setConstraints(tcippanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
					
					instantiationpartlist.add(tcippanel);
					
					TestCaseInstantiationPartPanel copytcippanel=new TestCaseInstantiationPartPanel(mainFrame,am);//传入单个实例化信息
					copyresultpanel.add(copytcippanel);
					copylayout.setConstraints(copytcippanel, new GBC(0, copyi++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
				}
				resultpanel.add(emptypanel);
				layout.setConstraints(emptypanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
				
				mainFrame.getStepFourCenterTabbedPane().getTestCaseInstantiationTabbedPanel().setTestCaseInstantiationPartPanelList(instantiationpartlist);
				
				copyresultpanel.add(copyemptypanel);
				copylayout.setConstraints(copyemptypanel, new GBC(0, copyi++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
				
				mainFrame.getStepFourCenterTabbedPane().getTestCaseInstantiationTabbedPanel().getTableresultpanel().removeAll();
				mainFrame.getStepFourCenterTabbedPane().getTestCaseInstantiationTabbedPanel().getTableresultpanel().add(resultpanel);
				
				copytcitpanel.getTableresultpanel().removeAll();
				copytcitpanel.getTableresultpanel().add(copyresultpanel);
				
				Thread.sleep(1000);
				
				//求解信息,collectLimit,collectResult
//				JPanel resultpanel1=new JPanel();
//				JPanel emptypanel1=new JPanel();
//				resultpanel1.setOpaque(false);
//				emptypanel1.setOpaque(false);
//				
//				GridBagLayout layout1 = new GridBagLayout();
//				resultpanel1.setLayout(layout1);
////				int i=0;
//				i=0;
////				for(int j=0;j<30;j++){
//				for(int j=0;j<collectLimit.size();j++){
//					
//					Automatic alimit=collectLimit.get(j);
//					Automatic aresult=collectResult.get(j);
//					
//					TestCaseInequalitySolvePanel tcispanel=new TestCaseInequalitySolvePanel(alimit.getName());
//					
//					JPanel processpanel=tcispanel.getAttributepanel();
//					GridBagLayout layout2 = new GridBagLayout();
//					processpanel.setLayout(layout2);
//					
////					for(int k=0;k<10;k++){
//					for(int k=0;k<alimit.getTransitionSet().size();k++){
//						
//						List<String> slimit=Arrays.asList(alimit.getTransitionSet().get(k).getLimit().split(","));
//						List<String> sresult=aresult.getTransitionSet().get(k).getResult();
//						
////						TestCaseInequalitySolveInforPanel tcisipanel=new TestCaseInequalitySolveInforPanel(alimit.getTransitionSet().get(k).getId()+"",slimit,sresult);
//						TestCaseInequalitySolveInforPanel tcisipanel=new TestCaseInequalitySolveInforPanel(mainFrame, alimit.getTransitionSet().get(k),aresult.getTransitionSet().get(k));
//						processpanel.add(tcisipanel);
//						layout2.setConstraints(tcisipanel, new GBC(0, k, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//						
//					}
//					
//					resultpanel1.add(tcispanel);
//					layout1.setConstraints(tcispanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//				}
//				resultpanel1.add(emptypanel1);
//				layout1.setConstraints(emptypanel1, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//				
//				mainFrame.getAbstractTestCaseResultPanel().getTworesultpanel().removeAll();
//				mainFrame.getAbstractTestCaseResultPanel().getTworesultpanel().add(resultpanel1);
//				mainFrame.getAbstractTestCaseResultPanel().getTestcaselabeltab2().doClick();
				
				mainFrame.getStepFourCenterTabbedPane().getTestCaseInstantiationButtonPanel().setVisible(true);

				time2=System.currentTimeMillis();
				
				stepAllProcessList.add("第二步：实例化");
				timeAllProcessList.add(time2-time1+"ms");
				resultAllProcessList.add("通过对"+collectResult.size()+"条测试序列进行实例化求解，每条测试序列得到多组解");
				System.out.println("-------------------213546879213");
				return 1;
				
			}
		};
		task2=new FutureTask<Integer>(callable2);
		thread2=new Thread(task2);
		
		callable3=new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub

				time1=System.currentTimeMillis();
				
				moviepanel.getMovieLabel().setText("正在生成测试用例");
				TextAreaPrint("正在生成测试用例");
				
				String name=selectAbstract.substring(0, selectAbstract.indexOf("Abstract"));
				String baseUrl = "D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\";
				
				if(starttype == 1){
					baseUrl += "\\FunctionalTest\\";
				} else if (starttype == 2) {
					baseUrl += "\\PerformanceTest\\";
				} 
//				else if (starttype == 3) {
//					baseUrl += "\\TimeTest\\";
//				}
				
//				String path=baseUrl+name+"TestCase.xml";
//				
//				System.err.println(path);
				
				List<TestCase> testcaselist=new ArrayList<TestCase>();
				List<TestCase> bordertestcaselist=new ArrayList<TestCase>();
				if(starttype==1&&hastime==0){//功能测试
					
					String path=baseUrl+name+"TestCase.xml";
					
//					AtutomaticProduceXML(collectResult, path);
					forPlatform.produceXML(path,collectResult);
					
					List<FunctionalTestCaseReportPartPanel> functionaltestcasereportlist=new ArrayList<FunctionalTestCaseReportPartPanel>();
					
					testcaselist=TestCaseConfirmationPanel.extractFunctionalTestDataFromXml(path);
					
					JPanel resultpanel=new JPanel();
					JPanel emptypanel=new JPanel();
					resultpanel.setOpaque(false);
					emptypanel.setOpaque(false);
					
					GridBagLayout layout = new GridBagLayout();
					resultpanel.setLayout(layout);
					int i=0;

					for(TestCase tc:testcaselist){
						FunctionalTestCaseReportPartPanel ftcrppanel=new FunctionalTestCaseReportPartPanel(mainFrame, tc,0);
						resultpanel.add(ftcrppanel);
						layout.setConstraints(ftcrppanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
						functionaltestcasereportlist.add(ftcrppanel);
					}
					resultpanel.add(emptypanel);
					layout.setConstraints(emptypanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
					
					mainFrame.getStepFourCenterTabbedPane().getTestCaseShowTabbedPanel().setStarttype(1);
					mainFrame.getStepFourCenterTabbedPane().getTestCaseShowTabbedPanel().setFunctionaltestcasereportlist(functionaltestcasereportlist);
					
					mainFrame.getStepFourCenterTabbedPane().getTestCaseShowTabbedPanel().getTableresultpanel().removeAll();
					mainFrame.getStepFourCenterTabbedPane().getTestCaseShowTabbedPanel().getTableresultpanel().add(resultpanel);
					
					mainFrame.getStepFourCenterTabbedPane().getTestCaseShowButtonPanel().setVisible(true);
					
					//边界值测试用例生成
					
					String borderpath=baseUrl+name+"BorderTestCase.xml";
					
					System.err.println(borderpath);
					
					borderTestXML.produceXML(borderpath,bordercollectResult);
					
					List<FunctionalTestCaseReportPartPanel> borderfunctionaltestcasereportlist=new ArrayList<FunctionalTestCaseReportPartPanel>();
					
					bordertestcaselist=TestCaseConfirmationPanel.extractFunctionalTestDataFromXml(borderpath);
					
					JPanel borderresultpanel=new JPanel();
					JPanel borderemptypanel=new JPanel();
					borderresultpanel.setOpaque(false);
					borderemptypanel.setOpaque(false);
					
					GridBagLayout borderlayout = new GridBagLayout();
					borderresultpanel.setLayout(borderlayout);
					int borderi=0;

					for(TestCase tc:bordertestcaselist){
						FunctionalTestCaseReportPartPanel ftcrppanel=new FunctionalTestCaseReportPartPanel(mainFrame,tc,0);
						borderresultpanel.add(ftcrppanel);
						borderlayout.setConstraints(ftcrppanel, new GBC(0, borderi++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
						borderfunctionaltestcasereportlist.add(ftcrppanel);
					}
					borderresultpanel.add(borderemptypanel);
					borderlayout.setConstraints(borderemptypanel, new GBC(0, borderi++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
					
					mainFrame.getStepFourCenterTabbedPane().getBorderTestCaseShowTabbedPanel().setStarttype(1);
					mainFrame.getStepFourCenterTabbedPane().getBorderTestCaseShowTabbedPanel().setFunctionaltestcasereportlist(borderfunctionaltestcasereportlist);
					
					mainFrame.getStepFourCenterTabbedPane().getBorderTestCaseShowTabbedPanel().getTableresultpanel().removeAll();
					mainFrame.getStepFourCenterTabbedPane().getBorderTestCaseShowTabbedPanel().getTableresultpanel().add(borderresultpanel);
					
					mainFrame.getStepFourCenterTabbedPane().getBorderTestCaseShowButtonPanel().setVisible(true);
					
				}
				else if(starttype==2&&hastime==0){//性能测试
//					for(Transition t:PerAutomaticResult.getTransitionSet()){
//						listcases.add(t.getResult());
//					}
//					PerformanceXML.produceXML(listcases, path);
					
					String path=baseUrl+name+"TestCase.xml";
					PerformanceXML2.produceXML(path);
					
					List<PerformanceTestCaseReportPartPanel> performancetestcasereportlist=new ArrayList<PerformanceTestCaseReportPartPanel>();
					
					testcaselist=TestCaseConfirmationPanel.extractPerformanceTestDataFromXml(path);
					
					JPanel resultpanel=new JPanel();
					JPanel emptypanel=new JPanel();
					resultpanel.setOpaque(false);
					emptypanel.setOpaque(false);
					
					GridBagLayout layout = new GridBagLayout();
					resultpanel.setLayout(layout);
					int i=0;
					
					PerformanceTestCaseReportTableHeaderPanel ptcrthpanel=new PerformanceTestCaseReportTableHeaderPanel();
					resultpanel.add(ptcrthpanel);
					layout.setConstraints(ptcrthpanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
					
					for(TestCase tc:testcaselist){
						PerformanceTestCaseReportPartPanel tcrppanel=new PerformanceTestCaseReportPartPanel(mainFrame, tc,0);
						resultpanel.add(tcrppanel);
						layout.setConstraints(tcrppanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
						performancetestcasereportlist.add(tcrppanel);
					}
					resultpanel.add(emptypanel);
					layout.setConstraints(emptypanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
					
					mainFrame.getStepFourCenterTabbedPane().getTestCaseShowTabbedPanel().setStarttype(2);
					mainFrame.getStepFourCenterTabbedPane().getTestCaseShowTabbedPanel().setPerformancetestcasereportlist(performancetestcasereportlist);
					
					mainFrame.getStepFourCenterTabbedPane().getTestCaseShowTabbedPanel().getTableresultpanel().removeAll();
					mainFrame.getStepFourCenterTabbedPane().getTestCaseShowTabbedPanel().getTableresultpanel().add(resultpanel);
					
					mainFrame.getStepFourCenterTabbedPane().getTestCaseShowButtonPanel().setVisible(true);
					
					
				}
				else if(hastime==1){//时间约束测试

					String path=baseUrl+name+"TestCase.xml";
//					GetTimeXML.produceXML(path,testCase);
//					XmlOfTime.produceXML(path, collectResult, collectLimit);
					XmlOfTime.produceXML(path, collectLimit);
					
//					String path1=baseUrl+name+"TestCase1.xml";
//					String path2=baseUrl+name+"TestCase2.xml";
//					
//					XmlOfTime.produceXML(path1, collectLimit);
//					outXMLofTime.produceXML(path2, collectLimit);
					
					List<List<String>> limitlist=new ArrayList<List<String>>();
					List<TimeTestCaseReportPartPanel> timetestcasereportlist=new ArrayList<TimeTestCaseReportPartPanel>();
					
					testcaselist=TestCaseConfirmationPanel.extractTimeTestDataFromXml(path);
//					Map resultmap=TestCaseConfirmationPanel.extractTimeTestDataFromXml(path);
//					testcaselist=(List<TestCase>) resultmap.get("testcase");
//					limitlist=(List<List<String>>) resultmap.get("limit");
					
					JPanel resultpanel=new JPanel();
					JPanel emptypanel=new JPanel();
					resultpanel.setOpaque(false);
					emptypanel.setOpaque(false);
					
					GridBagLayout layout = new GridBagLayout();
					resultpanel.setLayout(layout);
					int i=0;

					int index=0;
					for(TestCase tc:testcaselist){
						TimeTestCaseReportPartPanel ttcrppanel=new TimeTestCaseReportPartPanel(mainFrame, tc,0);
						resultpanel.add(ttcrppanel);
						layout.setConstraints(ttcrppanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
						timetestcasereportlist.add(ttcrppanel);
						index++;
					}
					resultpanel.add(emptypanel);
					layout.setConstraints(emptypanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
					
					mainFrame.getStepFourCenterTabbedPane().getTestCaseShowTabbedPanel().setStarttype(3);
					mainFrame.getStepFourCenterTabbedPane().getTestCaseShowTabbedPanel().setTimetestcasereportlist(timetestcasereportlist);
					
					mainFrame.getStepFourCenterTabbedPane().getTestCaseShowTabbedPanel().getTableresultpanel().removeAll();
					mainFrame.getStepFourCenterTabbedPane().getTestCaseShowTabbedPanel().getTableresultpanel().add(resultpanel);
					
					mainFrame.getStepFourCenterTabbedPane().getTestCaseShowButtonPanel().setVisible(true);
					
				}
				
				Thread.sleep(new Random().nextInt(1000)+1000);

				time2=System.currentTimeMillis();
				
				stepAllProcessList.add("第三步：生成测试用例");
				timeAllProcessList.add(time2-time1+"ms");
				if(starttype==1){
					if(hastime==1){
						resultAllProcessList.add("对实例化后求到的多组解进行随机组合，生成"+testcaselist.size()+"条测试用例");
					}
					else{
						resultAllProcessList.add("对实例化后求到的多组解进行随机组合，生成"+testcaselist.size()+"条不含边界值的测试用例和"+bordertestcaselist.size()+"条含边界值的测试用例");						
					}
				}
				else{
					resultAllProcessList.add("对实例化后求到的多组解进行随机组合，生成"+testcaselist.size()+"条测试用例");
				}
				
				return 1;
				
			}
		};
		task3=new FutureTask<Integer>(callable3);
		thread3=new Thread(task3);
		
		callable4=new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				
				time1=System.currentTimeMillis();
				
				moviepanel.getMovieLabel().setText("正在生成测试用例xml");
				
				TextAreaPrint("正在生成测试用例xml");
				
				String name=selectAbstract.substring(0, selectAbstract.indexOf("Abstract"));
				String baseUrl = "D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\";
				
				if(starttype == 1){
					baseUrl += "FunctionalTest\\";
				} else if (starttype == 2) {
					baseUrl += "PerformanceTest\\";
				} 
//				else if (starttype == 3) {
//					baseUrl += "TimeTest\\";
//				}
				
				String path=baseUrl+name+"TestCase.xml";

				Thread.sleep(new Random().nextInt(1000)+1000);
				
				time2=System.currentTimeMillis();
				
				stepAllProcessList.add("第四步：存储测试用例");
				timeAllProcessList.add(time2-time1+"ms");
				
				if(starttype == 1){
					if(hastime==1){
						resultAllProcessList.add("生成"+name+"TestCase.xml");
						TextAreaPrint("生成"+name+"TestCase.xml，保存路径："+path);
					}
					else{
						String borderpath=baseUrl+name+"BorderTestCase.xml";
						resultAllProcessList.add("生成"+name+"TestCase.xml 和 "+name+"BorderTestCase.xml");
						
						TextAreaPrint("生成"+name+"TestCase.xml，保存路径："+path);
						TextAreaPrint("生成"+name+"BorderTestCase.xml，保存路径："+borderpath);
					}
				}
				else if(starttype==2){
					resultAllProcessList.add("生成"+name+"TestCase.xml");
					TextAreaPrint("生成"+name+"TestCase.xml，保存路径："+path);
				}
//				else if(starttype==3){
//					resultAllProcessList.add("生成"+name+"TimeTestCase.xml");
//					TextAreaPrint("生成"+name+"TestCase.xml，保存路径："+path);
//				}
				
				StepFiveCenterTabbedPane.setBecomeRunFileName(name+"TestCase");
				StepFiveCenterTabbedPane.setBecomeRunFileNameType(starttype);
				
				
				return 1;
			}
		};
		task4=new FutureTask<Integer>(callable4);
		thread4=new Thread(task4);
		
		callable5=new Callable<Integer>() {
			
			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				
				time1=System.currentTimeMillis();
				
				String name=selectAbstract.substring(0, selectAbstract.indexOf("Abstract"));
				String baseUrl = "D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\";

				baseUrl += "FunctionalTest\\";
				
				String path=baseUrl+name+"BorderTestCase.xml";
				
				System.err.println(path);
				
				borderTestXML.produceXML(path,bordercollectResult);
				
				return 1;
			}
		};
		task5=new FutureTask<Integer>(callable5);
		thread5=new Thread(task5);
		
		futuretasklist=new ArrayList<FutureTask<Integer>>();
		threadlist=new ArrayList<Thread>();
	
		futuretasklist.add(task1);
		futuretasklist.add(task2);
		futuretasklist.add(task3);
		futuretasklist.add(task4);
		
		threadlist.add(thread1);
		threadlist.add(thread2);
		threadlist.add(thread3);
		threadlist.add(thread4);
		
		stepsum=4;
		
	}
	
	protected void initTestCaseProcessEndUIData() {
		// TODO Auto-generated method stub
		
		moviepanel.getMovieLabel().setText("测试用例生成完毕");
		TextAreaPrint("测试用例生成完毕");
		TestCaseProcessEndPanel tcpepanel=new TestCaseProcessEndPanel(mainFrame,stepAllProcessList,timeAllProcessList,resultAllProcessList);
		
//		tcpepanel.setStepAllProcessList(stepAllProcessList);
//		tcpepanel.setTimeAllProcessList(timeAllProcessList);
//		tcpepanel.setResultAllProcessList(resultAllProcessList);
		
		
		tablepanel.removeAll();
		tablepanel.add(tcpepanel);
		
		mainFrame.getStepFourCenterTabbedPane().getTestCaseInstantiationProcessButtonPanel().setVisible(true);
		mainFrame.getStepFourCenterTabbedPane().getTestCaseInstantiationProcessButton().doClick();
		
		
//		String path;
//		PerformanceXML.createXML(collectResult, path);
		
		
		ChangeRepaint();
		
	}


	private void initMoviePanel() {
		// TODO Auto-generated method stub
		
		moviepanel.getMovieLabel().setText("等待进行测试用例实例化");
		
	}

	private void initTablePanel() {
		// TODO Auto-generated method stub
		
		tablepanel.setLayout(new GridLayout());
		tablepanel.setOpaque(false);
		
	}
	
	public static void TextAreaPrint(String word){
		JTextArea textarea=mainFrame.getConsolePartPanel().getTextarea4();
		textarea.append(word+"\n");
		textarea.setCaretPosition(textarea.getDocument().getLength());
		
//		int startindex;
//		try {
//			startindex = textarea.getLineEndOffset(textarea.getLineCount()-1);
////			int endindex=StepTwoArea.getLineEndOffset(index);
//			textarea.requestFocus();
//			textarea.setSelectionStart(startindex);
//			textarea.setSelectionEnd(startindex);
//		} catch (BadLocationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
	public List<Automatic> readAbstractTestCaseSerialFile(String path) {
		// TODO Auto-generated method stub
		
		List<Automatic> abstractAutomatic=new ArrayList<Automatic>();
		try {
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);

			while(fis.available()>0){//代表文件还有内容
				try {
					Automatic auto=(Automatic) ois.readObject();//文件读取完毕后，会抛异常
					abstractAutomatic.add(auto);
				} catch (Exception  e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
					System.out.println("文件读取完毕!");  
	                break;  
				}
			}

			ois.close();
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return abstractAutomatic;
	}
	
	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		if(mainFrame.getStepindex()==4){
			this.setVisible(false);
			this.getRootPane().repaint();
			this.setVisible(true);
		}
	}

}

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
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
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
import javax.swing.plaf.ProgressBarUI;
import javax.swing.table.DefaultTableModel;

import org.dom4j.DocumentException;

import com.horstmann.violet.application.consolepart.TestCaseInequalitySolveInforPanel;
import com.horstmann.violet.application.consolepart.TestCaseInequalitySolvePanel;
import com.horstmann.violet.application.consolepart.TestCasePathPanel;
import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.util.xiaole.GraghLayout.LayoutUppaal;
import com.horstmann.violet.application.gui.util.xiaole.UppaalTransfrom.ImportByDoubleClick;
import com.horstmann.violet.application.menu.util.zhangjian.Database.AbstractState;
import com.horstmann.violet.application.menu.util.zhangjian.Database.AbstractTransition;
import com.horstmann.violet.application.menu.util.zhangjian.UMLTransfrom.CreateAbstractUppaalXML;
import com.horstmann.violet.framework.file.GraphFile;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;

import cn.edu.hdu.ckt.handle.Automatic;
import cn.edu.hdu.ckt.handle.State;
import cn.edu.hdu.ckt.handle.TestAutoDiagram;
import cn.edu.hdu.ckt.handle.Transition;

public class TestCaseProcessTabbedPanel extends JPanel{
	
	private MainFrame mainFrame;
	
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
	
	
	private List<FutureTask<Integer>> futuretasklist=new ArrayList<FutureTask<Integer>>();
	private List<Thread> threadlist=new ArrayList<Thread>();
	
	private int threadstate=0;
	private int stepsum=6;
	private int step=1;
	
	private Automatic automatic;
	private List<AbstractState> abStateList =new ArrayList<AbstractState>();
	private List<AbstractTransition> abTransList =new ArrayList<AbstractTransition>();
	
	private Map<String, String> stateIdToNameMap=new HashMap<String, String>();
	private Map<String, String> stateNameToIdMap=new HashMap<String, String>();
	private Map<String, String> transitionIdToNameMap=new HashMap<String, String>();
	private Map<String, String> transitionNameToIdMap=new HashMap<String, String>();
	

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
				mainthread.stop();
				for(Thread t:threadlist){
					t.stop();
				}
				threadstate=0;
				step=1;
				
				progressbarindex=0;
				progressbar.setValue(0);
				progressbarlabel.setText("");
				
				for(FixedButtonTabbedPanel fbtpanel:mainFrame.getStepThreeCenterTabbedPane().getFixButtonTabbedPanelList()){
					fbtpanel.setVisible(false);
				}
				mainFrame.getStepThreeCenterTabbedPane().getTestCaseProcessButtonPanel().setVisible(true);
				mainFrame.getStepThreeCenterTabbedPane().setFixButtonTabbedPanelStartIndex(0);
				tablepanel.removeAll();
				
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

	protected void initThread() {
		// TODO Auto-generated method stub
		
		progressbarindex=0;
		progressbar.setValue(0);
		progressbarlabel.setText(" ");
		
		for(FixedButtonTabbedPanel fbtpanel:mainFrame.getStepThreeCenterTabbedPane().getFixButtonTabbedPanelList()){
			fbtpanel.setVisible(false);
		}
		mainFrame.getStepThreeCenterTabbedPane().getTestCaseProcessButtonPanel().setVisible(true);
		mainFrame.getStepThreeCenterTabbedPane().setFixButtonTabbedPanelStartIndex(0);
		tablepanel.removeAll();
		
		maincallable=new Callable<Integer>() {
			
			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				
				while(progressbarindex<=100){
					System.out.println(progressbarindex+"  "+(int)((double)100/stepsum)*step);
					if(progressbarindex==(int)((double)100/stepsum)*step+1){
						//开启下一个线程，并存入list
						
						if(futuretasklist.get(step-1).isDone()){
							if(step==1){
//								stepsum=7;
//								step=3;
							}
							System.out.println(step);
							if(step<6){
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
					Thread.sleep(1000);
				}
				moviepanel.getMovieLabel().setText("测试用例生成完毕");
				TestCaseProcessEndPanel tcpepanel=new TestCaseProcessEndPanel(mainFrame);
				tablepanel.removeAll();
				tablepanel.add(tcpepanel);
				ChangeRepaint();
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
				
				moviepanel.getMovieLabel().setText("正在解析时间自动机");
				
				UppaalParseInforTabbedPanel copyupitpanel=new UppaalParseInforTabbedPanel(mainFrame);
				
				tablepanel.removeAll();
				tablepanel.add(copyupitpanel.getInforpanel());
				
				//获取数据
				Thread.sleep(1000);
				
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
				
				for(int i=0;i<50;i++){
					Object[] rowData={"1","loc_id_29C2E776_04D4_47f3_8F70_D9F4DD7BEE72_14","loc_id_29C2E776_04D4_47f3_8F70_D9F4DD7BEE72_14","false","CircularNode"};
					copystatetablemodel.addRow(rowData);
					stateinfortablemodel.addRow(rowData);
				}
				
				for(int i=0;i<50;i++){
					Object[] rowData={"13","set_throttle_out_unstabilizedfloat, bool, float","g.throttle_filt#g.throttle_filt:float","cycle=2.5ms--control_mode==0#control_mode:int8_t--motor_state==False || ap.throttle_zero==True#motor_state:bool,ap.throttle_zero:bool","null","不空，但是没有内容"};
					copymigratetablemodel.addRow(rowData);
					migrateinfortablemodel.addRow(rowData);
				}
				
				mainFrame.getStepThreeCenterTabbedPane().getUppaalParseInforButtonPanel().setVisible(true);
				
				return 1;
			}
		};
		task1=new FutureTask<>(callable1);
		thread1=new Thread(task1);
		
		callable2=new Callable<Integer>() {
			
			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				
				moviepanel.getMovieLabel().setText("正在进行优化约简");
				
				Thread.sleep(1000);
				
				UppaalOptimizationTabbedPanel copyuotpanel=new UppaalOptimizationTabbedPanel(mainFrame);
				
				tablepanel.removeAll();
				tablepanel.add(copyuotpanel.getInforpanel());
				
				//获取数据
				Thread.sleep(1000);
				
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
				for(int i=0;i<50;i++){
					index=rand.nextInt(3);
					if(index==2){
						index=-1;
					}
					Object[] rowData={index,"1","loc_id_29C2E776_04D4_47f3_8F70_D9F4DD7BEE72_14","loc_id_29C2E776_04D4_47f3_8F70_D9F4DD7BEE72_14","false","CircularNode"};
					copystatetablemodel.addRow(rowData);
					stateinfortablemodel.addRow(rowData);
				}
				
				for(int i=0;i<50;i++){
					index=rand.nextInt(3);
					if(index==2){
						index=-1;
					}
					Object[] rowData={index,"13","set_throttle_out_unstabilizedfloat, bool, float","g.throttle_filt#g.throttle_filt:float","cycle=2.5ms--control_mode==0#control_mode:int8_t--motor_state==False || ap.throttle_zero==True#motor_state:bool,ap.throttle_zero:bool","null","不空，但是没有内容"};
					copymigratetablemodel.addRow(rowData);
					migrateinfortablemodel.addRow(rowData);
				}
				
				mainFrame.getStepThreeCenterTabbedPane().getUppaalOptimizationButtonPanel().setVisible(true);
				
				return 1;
			}
		};
		task2=new FutureTask<>(callable2);
		thread2=new Thread(task2);
		
		callable3=new Callable<Integer>() {
			
			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				
				moviepanel.getMovieLabel().setText("正在根据时间自动机生成深度优先生成树");
				
				//获取数据
				Thread.sleep(1000);
				
				//Automate数据转换为xml
				AutomateTransformXml();
				
				GraphFile absfGraphFile=ImportByDoubleClick.importFileByDoubleClick("UPPAAL","abs.uppaal.violet.xml");
				IWorkspace workspace=new Workspace(absfGraphFile);
				TestCaseUppaalTabbedPanel tcutpanel=new TestCaseUppaalTabbedPanel(mainFrame, workspace);
				
				IWorkspace copyworkspace=new Workspace(absfGraphFile);
				TestCaseUppaalTabbedPanel copytcutpanel=new TestCaseUppaalTabbedPanel(mainFrame, copyworkspace);
				
				mainFrame.getStepThreeCenterTabbedPane().setTestCaseUppaalTabbedPanel(tcutpanel);
				
				tablepanel.removeAll();
				tablepanel.add(copytcutpanel.getDiagramPanel());
				
				Thread.sleep(1000);

				//路径信息
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
				
				return 1;
			}
		};
		task3=new FutureTask<>(callable3);
		thread3=new Thread(task3);
		
		callable4 = new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				
				moviepanel.getMovieLabel().setText("正在进行路径覆盖，生成测试序列");
				
				//获取数据
				Thread.sleep(1000);
				
				//上一步的xml
				GraphFile absfGraphFile=ImportByDoubleClick.importFileByDoubleClick("UPPAAL","abs.uppaal.violet.xml");
				IWorkspace workspace=new Workspace(absfGraphFile);
				TestCaseCoverTabbedPanel tcctpanel=new TestCaseCoverTabbedPanel(mainFrame, workspace);
				
				IWorkspace copyworkspace=new Workspace(absfGraphFile);
				TestCaseCoverTabbedPanel copytcctpanel=new TestCaseCoverTabbedPanel(mainFrame, copyworkspace);
				
				mainFrame.getStepThreeCenterTabbedPane().setTestCaseCoverTabbedPanel(tcctpanel);
				
				tablepanel.removeAll();
				tablepanel.add(copytcctpanel.getResultpanel());
				
				
				//测试序列
				
				List<Automatic> autopathlist=new ArrayList<>();
				autopathlist=TestAutoDiagram.PathAuto();
				
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
				
				for(Automatic am:autopathlist){
					
					TestCaseCoverPartPanel tccppanel=new TestCaseCoverPartPanel(mainFrame,am,workspace);//传入测试序列。包括路径信息，以及workspace
					resultpanel.add(tccppanel);
					layout.setConstraints(tccppanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
					
					coverpartlist.add(tccppanel);
					
					TestCaseCoverPartPanel copytccppanel=new TestCaseCoverPartPanel(mainFrame,am,workspace);//传入测试序列。包括路径信息，以及workspace
					copyresultpanel.add(copytccppanel);
					copylayout.setConstraints(copytccppanel, new GBC(0, copyi++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
				}
				
//				for(int j=0;j<30;j++){
//					
//					TestCaseCoverPartPanel tccppanel=new TestCaseCoverPartPanel(mainFrame);//传入测试序列。包括路径信息，以及workspace
//					resultpanel.add(tccppanel);
//					layout.setConstraints(tccppanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//					
//					coverpartlist.add(tccppanel);
//					
//					TestCaseCoverPartPanel copytccppanel=new TestCaseCoverPartPanel(mainFrame);//传入测试序列。包括路径信息，以及workspace
//					copyresultpanel.add(copytccppanel);
//					copylayout.setConstraints(copytccppanel, new GBC(0, copyi++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//				}
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
				
				return 1;
			}
		};
		task4 = new FutureTask<>(callable4);
		thread4 = new Thread(task4);

		callable5 = new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				
				moviepanel.getMovieLabel().setText("正在抽象测试用例");
				
				//获取数据
				Thread.sleep(1000);
				
				TestCaseProduceTabbedPanel copytcptpanel=new TestCaseProduceTabbedPanel(mainFrame);
				
				tablepanel.removeAll();
				tablepanel.add(copytcptpanel.getTabelpanel());
				
				//抽象测试用例
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
				for(int j=0;j<30;j++){
					
					TestCaseProducePartPanel tcpppanel=new TestCaseProducePartPanel(mainFrame);//传入单个测试用例信息
					resultpanel.add(tcpppanel);
					layout.setConstraints(tcpppanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
					
					producepartlist.add(tcpppanel);
					
					TestCaseProducePartPanel copytcpppanel=new TestCaseProducePartPanel(mainFrame);//传入单个测试用例信息
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
				
				return 1;
			}
		};
		task5 = new FutureTask<>(callable5);
		thread5 = new Thread(task5);

		callable6 = new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				
				moviepanel.getMovieLabel().setText("正在进行实例化测试用例");
				
				//获取数据
				Thread.sleep(1000);
				
				TestCaseInstantiationTabbedPanel copytcitpanel=new TestCaseInstantiationTabbedPanel(mainFrame);
				
				tablepanel.removeAll();
				tablepanel.add(copytcitpanel.getTabelpanel());
				
				//实例化
				List<TestCaseInstantiationPartPanel> instantiationpartlist=new ArrayList<>();
				
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
				for(int j=0;j<30;j++){
					
					TestCaseInstantiationPartPanel tcippanel=new TestCaseInstantiationPartPanel(mainFrame);//传入单个实例化信息
					resultpanel.add(tcippanel);
					layout.setConstraints(tcippanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
					
					instantiationpartlist.add(tcippanel);
					
					TestCaseInstantiationPartPanel copytcippanel=new TestCaseInstantiationPartPanel(mainFrame);//传入单个实例化信息
					copyresultpanel.add(copytcippanel);
					copylayout.setConstraints(copytcippanel, new GBC(0, copyi++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
				}
				resultpanel.add(emptypanel);
				layout.setConstraints(emptypanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
				
				mainFrame.getStepThreeCenterTabbedPane().getTestCaseInstantiationTabbedPanel().setTestCaseInstantiationPartPanelList(instantiationpartlist);
				
				copyresultpanel.add(copyemptypanel);
				copylayout.setConstraints(copyemptypanel, new GBC(0, copyi++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
				
				mainFrame.getStepThreeCenterTabbedPane().getTestCaseInstantiationTabbedPanel().getTableresultpanel().removeAll();
				mainFrame.getStepThreeCenterTabbedPane().getTestCaseInstantiationTabbedPanel().getTableresultpanel().add(resultpanel);
				
				copytcitpanel.getTableresultpanel().removeAll();
				copytcitpanel.getTableresultpanel().add(copyresultpanel);
				
				Thread.sleep(1000);
				
				//求解信息
				JPanel resultpanel1=new JPanel();
				JPanel emptypanel1=new JPanel();
				resultpanel1.setOpaque(false);
				emptypanel1.setOpaque(false);
				
				GridBagLayout layout1 = new GridBagLayout();
				resultpanel1.setLayout(layout1);
//				int i=0;
				i=0;
				for(int j=0;j<30;j++){
					
					TestCaseInequalitySolvePanel tcispanel=new TestCaseInequalitySolvePanel();
					
					JPanel processpanel=tcispanel.getAttributepanel();
					GridBagLayout layout2 = new GridBagLayout();
					processpanel.setLayout(layout2);
					for(int k=0;k<10;k++){
						
						TestCaseInequalitySolveInforPanel tcisipanel=new TestCaseInequalitySolveInforPanel();
						processpanel.add(tcisipanel);
						layout2.setConstraints(tcisipanel, new GBC(0, k, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
						
					}
					
					resultpanel1.add(tcispanel);
					layout1.setConstraints(tcispanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
				}
				resultpanel1.add(emptypanel1);
				layout1.setConstraints(emptypanel1, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
				
				mainFrame.getAbstractTestCaseResultPanel().getTworesultpanel().removeAll();
				mainFrame.getAbstractTestCaseResultPanel().getTworesultpanel().add(resultpanel1);
				mainFrame.getAbstractTestCaseResultPanel().getTestcaselabeltab2().doClick();
				
				mainFrame.getStepThreeCenterTabbedPane().getTestCaseInstantiationButtonPanel().setVisible(true);
				
				return 1;
			}
		};
		task6 = new FutureTask<>(callable6);
		thread6 = new Thread(task6);
		
		futuretasklist=new ArrayList<>();
		futuretasklist.add(task1);
		futuretasklist.add(task2);
		futuretasklist.add(task3);
		futuretasklist.add(task4);
		futuretasklist.add(task5);
		futuretasklist.add(task6);
		
		threadlist=new ArrayList<>();
		threadlist.add(thread1);
		threadlist.add(thread2);
		threadlist.add(thread3);
		threadlist.add(thread4);
		threadlist.add(thread5);
		threadlist.add(thread6);
		
	}

	protected void AutomateTransformXml() {
		// TODO Auto-generated method stub
		
		automatic=TestAutoDiagram.DFSAuto();
		
		try {
			TestAutoDiagram.createSequenceXML(automatic);
			
			LayoutUppaal.layout("D:\\sequence.xml");
			
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
		
		for(State s :automatic.getStateSet()){
			//将wqq的相关的信息--->转换为zhangjian的相关的信息(state)
			AbstractState abState =new AbstractState();
			abState.setSid(s.getId());//查询数据库里面状态节点的个数
			abState.setSname(s.getName());
			abState.setPosition(s.getPosition());
			
			if(s.getType()==null){
				abState.setType("CircularNode");
			}
			else if(s.getType().equals("start")){
				abState.setType("Start");
			}
			
			abState.setInvariantDBM(s.getInvariantDBM().toString());
			abStateList.add(abState);
			
			stateIdToNameMap.put(s.getId()+"", s.getId()+" "+s.getName());
			stateNameToIdMap.put(s.getId()+" "+s.getName(), s.getId()+"");
			
		}
		
		for(Transition t :automatic.getTransitionSet()){
			//将wqq的相关的信息--->转换为zhangjian的相关的信息(transition)
			AbstractTransition abTrans =new AbstractTransition();
			abTrans.setTid(t.getId());
			abTrans.setTname(t.getName());
			abTrans.setSourceID(TestAutoDiagram.getStateIdByName(abStateList, t.getSource())+"");
			
			abTrans.setSource(t.getSource());
			abTrans.setTargetID(TestAutoDiagram.getStateIdByName(abStateList, t.getTarget())+"");
			abTrans.setTarget(t.getTarget());
			
			abTrans.setType(t.getTypes().toString());
			StringBuilder sb =new StringBuilder();
			for(int i=0;i<t.getResetClockSet().size();i++){
				sb.append(t.getResetClockSet().get(i));
				if(i!=t.getResetClockSet().size()-1){
					sb.append(";");
				}
			}
			abTrans.setResetClockSet(sb.toString());
			abTrans.setConstraintDBM(t.getConstraintDBM().toString());
			//System.out.println(t.getTypes()+"**"+t.getSource()+"**"+t.getTarget()+"**"+t.getResetClockSet()+"**"+t.getConstraintDBM());
			abTransList.add(abTrans);
			
			transitionIdToNameMap.put(t.getId()+"", t.getId()+"<br>"+t.getName());
			transitionNameToIdMap.put(t.getId()+"<br>"+t.getName(), t.getId()+"");
		}
		
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

	private void initMoviePanel() {
		// TODO Auto-generated method stub
		
		moviepanel.getMovieLabel().setText("正在进行测试用例生成");
		
	}

	private void initTablePanel() {
		// TODO Auto-generated method stub
		
		tablepanel.setLayout(new GridLayout());
		tablepanel.setOpaque(false);
		
		TestCaseProcessEndPanel tcpepanel=new TestCaseProcessEndPanel(mainFrame);
		tablepanel.add(tcpepanel);
		
	}
	
	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
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
	
	
	
}

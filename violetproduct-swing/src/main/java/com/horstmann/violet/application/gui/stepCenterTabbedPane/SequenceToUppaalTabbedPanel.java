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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.StepTwoCenterTabbedPane;
import com.horstmann.violet.application.gui.util.tanchao.ShowOnTableAndConsole;
import com.horstmann.violet.application.gui.util.tanchao.XMLCopy;
import com.horstmann.violet.application.gui.util.wujun.SequenceTransfrom.SD2UppaalMain;
import com.horstmann.violet.application.gui.util.xiaole.GraghLayout.LayoutUppaal;
import com.horstmann.violet.application.gui.util.xiaole.UppaalTransfrom.ImportByDoubleClick;
import com.horstmann.violet.application.gui.util.xiaole.UppaalTransfrom.TransToVioletUppaal;
import com.horstmann.violet.application.menu.FileMenu;
import com.horstmann.violet.framework.file.GraphFile;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;

public class SequenceToUppaalTabbedPanel extends JPanel{

	private MainFrame mainFrame;
	
	private JPanel toolpanel;
	private MoviePanel moviepanel;
	private JPanel tabelpanel;
	
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
	private int smallprogressbarindex;
	
	private DefaultTableModel sequencetouppaaltablemodel;
	private JTable sequencetouppaaltable;
	private JScrollPane tabelscrollpanel;
	
	private Thread t;
	private Thread progreseethread;
	private int threadstate=0;
	
	private int successcount;
	
	private int sequencelistindex;
	private int oldsequencelistindex;
	private List<String> sequencelists = new ArrayList<String>();
	
//	private List<String> uppaallists = new ArrayList<String>();
	
	private static Map<String,String> sequencetouppaalmap=new LinkedHashMap<String,String>();
	
	private Set<String> sequencetouppaalset;
	
	private List<UppaalProcessModel> uppaalprocesslists=new ArrayList<UppaalProcessModel>();
	
	private Callable<Integer> maincallable;
	private FutureTask<Integer> maintask;
	private Thread mainthread;
	
	private Callable<Integer> trancallable;
	private FutureTask<Integer> trantask;
	private Thread tranthread;
	
	private List<String> tranprocesslist=new ArrayList<>();
	private int tranprocesslistindex;
	private int tranprocessstate;
	private String tranxmlname=null;
	
	private IWorkspace workspace1;
	
	public SequenceToUppaalTabbedPanel(MainFrame mainframe){
		
		this.mainFrame=mainframe;
		
		toolpanel=new JPanel();
		moviepanel=new MoviePanel();
		tabelpanel=new JPanel();
		
//		addDataToUppaalDataList();
		
		initToolPanel();
		
		initMoviePanel();
		
		initTablePanel();
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(toolpanel);
		this.add(moviepanel);
		this.add(tabelpanel);
		layout.setConstraints(toolpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(moviepanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(tabelpanel,new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
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
				
//				if(t==null){
//					startSequenceToUppaal();
//					threadstate=true;
//				}
				
				if(threadstate==0){
//					startSequenceToUppaal();
					initThread();
					mainthread.start();
					tranthread.start();
					threadstate=1;
					System.out.println("t is alive");
				}
				else if(threadstate==1){
					
				}
				else if(threadstate==-1){
					threadstate=1;
//					t.resume();
//					progreseethread.resume();
					mainthread.resume();
					tranthread.resume();
					System.out.println("t is not alive");
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
				
//				t.suspend();
//				progreseethread.suspend();
				mainthread.suspend();
//				tranthread.suspend();
				if(tranprocessstate==1){
					tranthread.suspend();
				}
				threadstate=-1;
				
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
				
//				t.stop();
//				progreseethread.stop();
				mainthread.stop();
				tranthread.stop();
				threadstate=0;
				
				progressbar.setValue(0);
				progressbarlabel.setText("0%");
				
				while(sequencetouppaaltablemodel.getRowCount()>0){
					sequencetouppaaltablemodel.removeRow(sequencetouppaaltablemodel.getRowCount()-1);
				}
				
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
		
		tranprocesslist=new ArrayList<>();
		tranprocesslist.add("正在获取顺序图信息");
		tranprocesslist.add("初始化数据");
		tranprocesslist.add("创建状态");
		tranprocesslist.add("根据消息所在的组合片段，构建组合片段嵌套表");
		tranprocesslist.add("获取跳出loop的条件");
		tranprocesslist.add("获取组合片段的取反条件");
		tranprocesslist.add("得到邻接矩阵");
		tranprocesslist.add("根据邻接矩阵连接状态");
		tranprocesslist.add("完成顺序图到自动机的转换，正在写入xml");
		tranprocesslist.add("写入完成");
		
		tranprocesslistindex=0;
		
		tranprocessstate=0;
		
		progressbarindex=0;
		progressbar.setValue(0);
		progressbarlabel.setText(" ");
		
		smallprogressbarindex=0;
		
		sequencelistindex=0;
		oldsequencelistindex=-1;
		
		JCheckBox[] cb=mainFrame.getModelTransformationPanel().getModelSequenceTreePanel().getSequenceCheckBoxList();
		sequencelists.clear();
		
		for(JCheckBox jcb:cb){
			if(jcb.isSelected()){
				sequencelists.add(jcb.getText());
			}
		}
		
		while(sequencetouppaaltablemodel.getRowCount()>0){
			sequencetouppaaltablemodel.removeRow(sequencetouppaaltablemodel.getRowCount()-1);
		}
		
		final JTextArea StepTwoArea= mainFrame.getConsolePartPanel().getTextarea();
		
		StepTwoArea.append("UML模型正在转换中......\n");	
		
		maincallable=new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				
				while(progressbarindex<=100){
//				while(!trantask.isDone()&&progressbarindex<=100){
					System.err.println(trantask.isDone()+" - - "+progressbarindex+" + + "+(!trantask.isDone()||progressbarindex<=100));
					
					if(smallprogressbarindex==0){
						Object[] tableRowData = { sequencelistindex+1, 0, sequencelists.get(sequencelistindex), tranprocesslist.get(tranprocesslistindex), smallprogressbarindex, smallprogressbarindex, "" };
						sequencetouppaaltablemodel.addRow(tableRowData);
						sequencetouppaaltablemodel.fireTableDataChanged();
					}
					System.out.println(progressbarindex + " - " + (int) (((double) 100 / sequencelists.size()) * (sequencelistindex + 1) + 0.5));
					if (progressbarindex == (int) (((double) 100 / sequencelists.size()) * (sequencelistindex + 1) + 0.5)) {

						sequencetouppaaltablemodel.setValueAt(1, sequencelistindex, 1);
						sequencetouppaaltablemodel.setValueAt(100 + "%", sequencelistindex, 4);
						sequencetouppaaltablemodel.setValueAt(100, sequencelistindex, 5);
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						sequencetouppaaltablemodel.setValueAt(df.format(new Date()), sequencelistindex, 6);

						sequencetouppaaltablemodel.fireTableDataChanged();
						
						mainFrame.addTabbedPane(workspace1, 21);

						mainFrame.getStepTwoCenterTabbedPane().getSequenceToUppaalDiagramButtonTabbedPanelLists()
								.get(mainFrame.getModelTransformationPanel().getModelSequenceTreePanel()
										.getUppaaltablemodel().getRowCount())
								.setVisible(false);
						System.err.println(tranxmlname);
						Object[] rowData = { tranxmlname.substring(0, tranxmlname.lastIndexOf(".uppaal.violet.xml")) };
						mainFrame.getModelTransformationPanel().getModelSequenceTreePanel().getUppaaltablemodel()
								.addRow(rowData);

						sequencelistindex++;
						smallprogressbarindex = 0;
						tranprocesslistindex = 0;

						while (progressbarindex == 100) {
							progressbarindex++;
						}
					}
					else{
						for(int k=0;k<sequencelists.size();k++){
							if(smallprogressbarindex==100){
								break;
							}
							if(smallprogressbarindex/tranprocesslist.size()!=tranprocesslistindex){
								tranprocesslistindex=smallprogressbarindex/tranprocesslist.size();
								sequencetouppaaltablemodel.setValueAt(tranprocesslist.get(tranprocesslistindex), sequencelistindex, 3);
							}
							
							sequencetouppaaltablemodel.setValueAt(smallprogressbarindex + "%", sequencelistindex, 4);
							sequencetouppaaltablemodel.setValueAt(smallprogressbarindex, sequencelistindex, 5);
							smallprogressbarindex++;
							
							Random rand=new Random();
//							int sleeptime=(rand.nextInt(10)+1)*10;
							int sleeptime;
							if(SD2UppaalMain.diagramslistsize<10){
								sleeptime=100;
							}
							else{
								sleeptime=SD2UppaalMain.diagramslistsize*4;
							}
							if(tranprocessstate==1){
								sleeptime=100;
							}
							Thread.sleep(sleeptime);
						}
						
						progressbarindex++;
						progressbar.setValue(progressbar.getValue()+1);
						progressbarlabel.setText(progressbar.getValue()+"%");
						
						JTextArea textarea=mainFrame.getConsolePartPanel().getTextarea();
						textarea.setCaretPosition(textarea.getDocument().getLength()*progressbar.getValue()/100);
						
//						JScrollBar bar=mainFrame.getConsolePartPanel().getTextscrollpanel().getVerticalScrollBar();
//						System.out.println(bar.getValue()+" -******- "+bar.getMaximum()+"   "+bar.getMaximum()*progressbar.getValue()/100);
//						bar.setValue(bar.getMaximum()*progressbar.getValue()/100);
//						
//						ChangeRepaint();
						
					}
				}
				System.err.println(trantask.isDone()+" - - "+progressbarindex+" + + "+(!trantask.isDone()||progressbarindex<=100));
				moviepanel.getMovieLabel().setText("所有顺序图全部转换完成，总共有"+sequencelists.size()+"张顺序图，转换成功了"+successcount+"张顺序图，转换率为："+(double)successcount/sequencelists.size()*100+"%");
				StepTwoArea.append("UML模型转换完成......\n");
				threadstate=0;
				
				return 1;
			}
		};
		maintask=new FutureTask<>(maincallable);
		mainthread=new Thread(maintask);
		
		trancallable=new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				
//				String filename1 = null;
				
				successcount=0;
				
//				for (String filename : sequencelists) {
				
				while(sequencelistindex<sequencelists.size()){
					if(oldsequencelistindex==sequencelistindex){
						Thread.sleep(100);
						tranprocessstate=1;
					}
					else{
						tranprocessstate=0;
						oldsequencelistindex=sequencelistindex;
						String filename=sequencelists.get(oldsequencelistindex);
//						sequencelistindex=sequencelists.indexOf(filename);
						
						String baseUrl = "D:\\ModelDriverProjectFile\\SequenceDiagram\\Violet\\";
						String baseUrl2 = "D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\";
						String baseUrl3 = "D:\\ModelDriverProjectFile\\UPPAL\\3.Abstract_TestCase\\";
						
						int starttype=mainFrame.getHomeAllTabbedPanel().getStarttype();
						if(starttype == 1){
							baseUrl += "\\FunctionalTest\\";
							baseUrl2 += "\\FunctionalTest\\";
							baseUrl3 += "\\FunctionalTest\\";
						} else if (starttype == 2) {
							baseUrl += "\\PerformanceTest\\";
							baseUrl2 += "\\PerformanceTest\\";
							baseUrl3 += "\\PerformanceTest\\";
						} else if (starttype == 3) {
							baseUrl += "\\TimeTest\\";
							baseUrl2 += "\\TimeTest\\";
							baseUrl3 += "\\TimeTest\\";
						}
						
						System.out.println(sequencelistindex+"   "+sequencelists.size()+"   "+baseUrl + filename);
						String path = baseUrl + filename + ".seq.violet.xml";

						if (filename.contains("EA")) {// 打开ea平台的xml文件
							
							moviepanel.getMovieLabel().setText("正在转换顺序图 "+filename+"...");
							
//							path="D:\\ModelDriverProjectFile\\TimingDiagram\\Violet\\EATiming2.timing.violet.xml";
							
							SD2UppaalMain.transEA(path, mainFrame);// 主要是将ea的xml转换成我们的wujun的xml(里面有他的路径)
							
//							tranprocessstate=1;
							
							System.out.println("*************************+++++++++++++++++++++++"+SD2UppaalMain.diagramslistsize);
							
							if(SD2UppaalMain.diagramslistsize==1){
								System.out.println("-------------------------123");
								XMLCopy.SourceCopyToTarget("D:\\ModelDriverProjectFile\\WJXML\\"+SD2UppaalMain.getDiagramDataName()+"ForXStream.xml", baseUrl3+filename+"ForXStream.xml");
								LayoutUppaal.layout("D:\\ModelDriverProjectFile\\WJXML\\"+SD2UppaalMain.getDiagramDataName()+".xml");
							}
							else{
								System.out.println("*************************456");
								XMLCopy.SourceCopyToTarget("D:\\ModelDriverProjectFile\\WJXML\\UAVForXStream.xml", baseUrl3+filename+"ForXStream.xml");
								LayoutUppaal.layout("D:\\ModelDriverProjectFile\\WJXML\\UAV.xml");
							}
							
							System.out.println("SD2UppaalMain.getDiagramDataName():+++++++++"+SD2UppaalMain.getDiagramDataName());
							
							tranxmlname = TransToVioletUppaal.TransToViolet(filename);
//							uppaallists.add(filename1);
							
							GraphFile fGraphFile1 = ImportByDoubleClick.importFileByDoubleClick("UPPAAL", tranxmlname);
							workspace1 = new Workspace(fGraphFile1);
//							mainFrame.addTabbedPane(workspace1, 21);
//
//							mainFrame.getStepTwoCenterTabbedPane().getSequenceToUppaalDiagramButtonTabbedPanelLists()
//									.get(mainFrame.getModelTransformationPanel().getModelSequenceTreePanel()
//											.getUppaaltablemodel().getRowCount())
//									.setVisible(false);
							
							successcount++;

						} else {// 打开我们平台的xml文件

						}
					}
				}
				System.out.println("----------------"+sequencelistindex);
				
				return 1;
			}
		};
		trantask=new FutureTask<>(trancallable);
		tranthread=new Thread(trantask);
		
	}

	protected void startSequenceToUppaal() {
		// TODO Auto-generated method stub
		
		JCheckBox[] cb=mainFrame.getModelTransformationPanel().getModelSequenceTreePanel().getSequenceCheckBoxList();
		sequencelists.clear();
//		uppaallists.clear();
		progressbar.setValue(0);
		for(JCheckBox jcb:cb){
			if(jcb.isSelected()){
				sequencelists.add(jcb.getText());
			}
		}
		while(sequencetouppaaltablemodel.getRowCount()>0){
			sequencetouppaaltablemodel.removeRow(sequencetouppaaltablemodel.getRowCount()-1);
		}
		
		final JTextArea StepTwoArea= mainFrame.getConsolePartPanel().getTextarea();
		
		StepTwoArea.append("UML模型正在转换中......\n");	
		// TODO Auto-generated method stub
	   	try {
	   		//事件分发线程(gum处理事件和画图的时候)
//	   		SwingUtilities.invokeLater(new Runnable() {
	   		t = new Thread(new Runnable(){
				
				@Override
				public void run() {
					String filename1 = null;
					try {
						//uml转化成事件自动机
                         //用于获得当前工作的sequence

						successcount=0;
						
						for (String filename : sequencelists) {
							
							sequencelistindex=sequencelists.indexOf(filename);
							
							String baseUrl = "D:\\ModelDriverProjectFile\\SequenceDiagram\\Violet\\";
							System.out.println(sequencelistindex+"   "+sequencelists.size()+"   "+baseUrl + filename);
							String path = baseUrl + filename + ".seq.violet.xml";

							if (filename.contains("EA")) {// 打开ea平台的xml文件
								
								moviepanel.getMovieLabel().setText("正在转换顺序图 "+filename+"...");
								
//								path="D:\\ModelDriverProjectFile\\TimingDiagram\\Violet\\EATiming2.timing.violet.xml";
								
								SD2UppaalMain.transEA(path, mainFrame);// 主要是将ea的xml转换成我们的wujun的xml(里面有他的路径)
								
								ShowData();
								
								System.out.println("*************************+++++++++++++++++++++++");
								// 以下d盘中写的文件是死的路径，但是上面是动态生成的需要修改D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\
//								LayoutUppaal.layout(
//										"UseCase4-Sequence1-Normal.xml");// ("sequence.xml");
								
//								System.out.println("SD2UppaalMain.getDiagramDataName():+++++++++"+SD2UppaalMain.getDiagramDataName()+" - - "+SD2UppaalMain.getDiagramDataName()+"ForXStream.xml");//前者文件为时间自动机需要，后者文件为测试用例生成需要
								
//								XMLCopy.SourceCopyToTarget("D:\\ModelDriverProjectFile\\WJXML\\"+SD2UppaalMain.getDiagramDataName()+"ForXStream.xml", "D:\\ModelDriverProjectFile\\UPPAL\\3.Abstract_TestCase\\"+filename+"ForXStream.xml");
								XMLCopy.SourceCopyToTarget("D:\\ModelDriverProjectFile\\WJXML\\UAVForXStream.xml", "D:\\ModelDriverProjectFile\\UPPAL\\3.Abstract_TestCase\\"+filename+"ForXStream.xml");
								
//								LayoutUppaal.layout("D:\\ModelDriverProjectFile\\WJXML\\"+SD2UppaalMain.getDiagramDataName()+".xml");
								LayoutUppaal.layout("D:\\ModelDriverProjectFile\\WJXML\\UAV.xml");
								
								filename1 = TransToVioletUppaal.TransToViolet(filename);//wujun的xml转换为平台显示的时间自动机xml
								// String
								// filename1="uppaalTest1.uppaal.violet.xml";
								// GraphFile
								// fGraphFile1=ImportByDoubleClick.importFileByDoubleClick("UPPAAL",filename1);
								// IWorkspace workspace1=new
								// Workspace(fGraphFile1);
								// mainFrame.addTabbedPane(workspace1,2);
								// mainFrame.repaint();
								// Thread.sleep(5000);
								// String
								// filename2=TransToVioletUppaal.TransToViolet();

								// GraphFile
								// fGraphFile2=ImportByDoubleClick.importFileByDoubleClick("UPPAAL",filename2);
								// IWorkspace workspace2=new
								// Workspace(fGraphFile2);
								// StepTwoArea.append("UML模型到时间自动机模型已经转换完成!\n");

//								uppaallists.add(filename1);
								
								GraphFile fGraphFile1 = ImportByDoubleClick.importFileByDoubleClick("UPPAAL", filename1);
								IWorkspace workspace1 = new Workspace(fGraphFile1);
								mainFrame.addTabbedPane(workspace1, 21);

								mainFrame.getStepTwoCenterTabbedPane().getSequenceToUppaalDiagramButtonTabbedPanelLists()
										.get(mainFrame.getModelTransformationPanel().getModelSequenceTreePanel()
												.getUppaaltablemodel().getRowCount())
										.setVisible(false);
								
								Object[] rowData = { filename1.substring(0, filename1.lastIndexOf(".uppaal.violet.xml")) };
								mainFrame.getModelTransformationPanel().getModelSequenceTreePanel().getUppaaltablemodel()
										.addRow(rowData);
								
								successcount++;

							} else {// 打开我们平台的xml文件

							}
							// SD2UppaalMain.transEA(path);//主要是将ea的xml转换成我们的wujun的xml(里面有他的路径)
							// String
							// filename1=TransToVioletUppaal.TransToViolet();
							// GraphFile
							// fGraphFile1=ImportByDoubleClick.importFileByDoubleClick("UPPAAL",filename1);
							// IWorkspace workspace1=new Workspace(fGraphFile1);
							// mainFrame.addTabbedPane(workspace1,2);
							// mainFrame.repaint();
							// Thread.sleep(5000);
							// 先进行布局
							// 将时间自动机展示在我们的平台上
							// LayoutUppaal.layout
							// ("C:\\Users\\Admin\\Desktop\\项目最新代码\\violetumleditor-master\\violetproduct-swing\\sequence.xml");//("stabilize_run.xml");
							// String
							// filename2=TransToVioletUppaal.TransToViolet();
							// GraphFile
							// fGraphFile2=ImportByDoubleClick.importFileByDoubleClick("UPPAAL",filename2);
							// IWorkspace workspace2=new Workspace(fGraphFile2);
							// StepTwoArea.append("UML模型到时间自动机模型已经转换完成!\n");
							// mainFrame.addTabbedPane(workspace1,2);
						}
						
						
//						for (String s : uppaallists) {
//							Object[] rowData = { s.substring(0, s.lastIndexOf(".uppaal.violet.xml")) };
//							mainFrame.getModelTransformationPanel().getModelSequenceTreePanel().getUppaaltablemodel()
//									.addRow(rowData);
//
//							GraphFile fGraphFile1 = ImportByDoubleClick.importFileByDoubleClick("UPPAAL", s);
//							IWorkspace workspace1 = new Workspace(fGraphFile1);
//							mainFrame.addTabbedPane(workspace1, 2);
//
//							mainFrame.getStepTwoCenterTabbedPane().getUppaalDiagramButtonTabbedPanelLists()
//									.get(mainFrame.getModelTransformationPanel().getModelSequenceTreePanel()
//											.getUppaaltablemodel().getRowCount() - 1)
//									.setVisible(false);
//						}
						
						System.out.println(
								"-------------------------------------------------------------------------------------");

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
//					int count=mainFrame.getModelTransformationPanel().getModelSequenceTreePanel().getUppaaltablemodel().getRowCount();
					
					moviepanel.getMovieLabel().setText("所有顺序图全部转换完成，总共有"+sequencelists.size()+"张顺序图，转换成功了"+successcount+"张顺序图，转换率为："+(double)successcount/sequencelists.size()*100+"%");
					StepTwoArea.append("UML模型转换完成......\n");
					threadstate=0;

				}

			});   	
	   		
	   		t.start();
	   		
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	protected void ShowData() {
		// TODO Auto-generated method stub
		
		sequencetouppaalmap=ShowOnTableAndConsole.getSequencetouppaalmap();
		
		sequencetouppaalset=sequencetouppaalmap.keySet();
		
		uppaalprocesslists.clear();
		
		for(String str:sequencetouppaalset){
			
			UppaalProcessModel upmodel=new UppaalProcessModel();
			
			upmodel.setId(sequencelistindex+1);
			upmodel.setState(0);
			upmodel.setName(sequencelists.get(sequencelistindex));
			upmodel.setOperation(str);
			upmodel.setProgress(0);
			upmodel.setTime("");
			
			uppaalprocesslists.add(upmodel);
			
		}

		int id = -1;
		for (final UppaalProcessModel upm : uppaalprocesslists) {

			Object[] tableRowData = { upm.getId(), upm.getState(), upm.getName(), upm.getOperation(), upm.getProgress(),
					upm.getProgress(), upm.getTime() };

			if (id == -1) {
				id = upm.getId();
				sequencetouppaaltablemodel.addRow(tableRowData);

			} else if (id == upm.getId()) {
				sequencetouppaaltablemodel.setValueAt(upm.getState(), id - 1, 1);
				sequencetouppaaltablemodel.setValueAt(upm.getOperation(), id - 1, 3);
				sequencetouppaaltablemodel.setValueAt(upm.getTime(), id - 1, 6);

			}
			if(uppaalprocesslists.indexOf(upm)==uppaalprocesslists.size()-1){
				
				sequencetouppaaltablemodel.setValueAt(1, id - 1, 1);
				sequencetouppaaltablemodel.setValueAt(upm.getOperation(), id - 1, 3);
				
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				sequencetouppaaltablemodel.setValueAt(df.format(new Date()), id - 1, 6);
				
				sequencetouppaaltablemodel.fireTableDataChanged();
				
				break;
				
			}

			sequencetouppaaltablemodel.fireTableDataChanged();

			mainFrame.getConsolePartPanel().getTextarea().append(upm.getOperation() + "\n");

			mainFrame.getConsolePartPanel().getTextarea().append(sequencetouppaalmap.get(upm.getOperation()));

			try {
				progreseethread = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated
						// method stub
						while (true) {

							int uppaalprocesslistindex = uppaalprocesslists.indexOf(upm);
							int uppaalprocesslistsize = uppaalprocesslists.size()-1;

							System.out.println("sequencelistindex:" + sequencelistindex + " uppaalprocesslistindex:"
									+ uppaalprocesslistindex + " uppaalprocesslistsize:" + uppaalprocesslistsize
									+ " uppaalprocesslists.size():" + uppaalprocesslists.size()
									+ " sequencelists.size():" + sequencelists.size());

							int startprogressbar = (int) ((double) 100 / sequencelists.size() * sequencelistindex);
							int endprogressbar = (int) ((double) 100 / sequencelists.size() * (sequencelistindex + 1));
							int totalprogressbar = endprogressbar - startprogressbar;

							System.out.println("startprogressbar:" + startprogressbar + " endprogressbar:"
									+ endprogressbar + " totalprogressbar:" + totalprogressbar);

							int startprogressbarvalue = (int) ((double) totalprogressbar / uppaalprocesslistsize
									* uppaalprocesslistindex) + 1 + startprogressbar;
							int endprogressbarvalue = (int) ((double) totalprogressbar / uppaalprocesslistsize
									* (uppaalprocesslistindex + 1)) + startprogressbar;// 每个小步骤所要的进度条值区间

							// System.out.println("---------------------------------------------------------------");
							System.out.println("startprogressbarvalue:" + startprogressbarvalue
									+ " endprogressbarvalue:" + endprogressbarvalue);

							for (int i = startprogressbarvalue, j = 0; i <= endprogressbarvalue; i++, j++) {
								// System.out.println("++++"+i);
								progressbar.setValue(i);
								progressbarlabel.setText(i + "%");

								double modelprocess = endprogressbarvalue - startprogressbarvalue + 1;// 进度条值区间
								double avemodelprocess = 100 / (double) uppaalprocesslistsize;// 小步骤的总数值区间
								int modelprocessindex = uppaalprocesslistindex % uppaalprocesslistsize;
								// System.out.println("j:"+j+"
								// modelprocess:"+modelprocess+"
								// modelprocessindex:"+modelprocessindex+"
								// avemodelprocess:"+avemodelprocess);
								int startmodelprocess = (int) ((double) avemodelprocess * modelprocessindex
										+ avemodelprocess / modelprocess * j) + 1;
								int endmodelprocess = (int) ((double) avemodelprocess * modelprocessindex
										+ avemodelprocess / modelprocess * (j + 1));// 通过小步骤的总数值区间，来计算进度条加1时，小步骤的数值须加多少
								// System.out.println("startmodelprocess:"+startmodelprocess+"
								// avemodelprocess/modelprocess*(j+1):"+avemodelprocess/modelprocess*(j+1)+"
								// endmodelprocess:"+endmodelprocess);
								for (int k = startmodelprocess; k <= endmodelprocess; k++) {
									// System.out.println("k:"+k+"
									// startmodelprocess:"+startmodelprocess+"
									// endmodelprocess:"+endmodelprocess);
									sequencetouppaaltablemodel.setValueAt(k + "%", upm.getId() - 1, 4);
									sequencetouppaaltablemodel.setValueAt(k, upm.getId() - 1, 5);
									sequencetouppaaltablemodel.fireTableDataChanged();
									try {
										Thread.sleep(100);
									} catch (InterruptedException e) {
										// TODO
										// Auto-generated
										// catch block
										e.printStackTrace();
									}
								}

							}

							break;

						}
					}
				});
				progreseethread.start();
				progreseethread.join();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		
	}

	private void initMoviePanel() {
		// TODO Auto-generated method stub
		
		moviepanel.getMovieLabel().setText("正在读取导出的所有顺序图");
		
	}

	private void initTablePanel() {
		// TODO Auto-generated method stub
		
		String[] columnNames={"序号","状态","顺序图","操作","进度","","完成时间"};
//		String[][] tabelValues={{"1","1","3","4","5","6"}};
		String[][] tabelValues={};
		sequencetouppaaltablemodel=new DefaultTableModel(tabelValues, columnNames){
			@Override  
            public boolean isCellEditable(int row,int column){  
                return false;  
            } 
		};
		
		sequencetouppaaltable=new JTable(sequencetouppaaltablemodel);
		
        sequencetouppaaltable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sequencetouppaaltable.setSelectionBackground(new Color(250, 248, 236));
        sequencetouppaaltable.setGridColor(new Color(224, 226, 220));
		sequencetouppaaltable.setShowGrid(true);
		sequencetouppaaltable.setShowHorizontalLines(true);
		sequencetouppaaltable.setShowVerticalLines(false);
		sequencetouppaaltable.setFillsViewportHeight(true);
		sequencetouppaaltable.setRowHeight(27);
		sequencetouppaaltable.doLayout();
		sequencetouppaaltable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		sequencetouppaaltable.getColumnModel().getColumn(1).setCellEditor(new MyLabelCellEditor());
		sequencetouppaaltable.getColumnModel().getColumn(1).setCellRenderer(new MyLabelRenderer());
//		sequencetouppaaltable.getColumnModel().getColumn(4).setCellEditor(new MyLabelCellEditor());
		sequencetouppaaltable.getColumnModel().getColumn(5).setCellRenderer(new MyProgressRenderer());
		
		
//		sequencetouppaaltable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		sequencetouppaaltable.getColumn("序号").setPreferredWidth(50);
		sequencetouppaaltable.getColumn("序号").setMinWidth(50);
		sequencetouppaaltable.getColumn("序号").setMaxWidth(50);
		sequencetouppaaltable.getColumn("状态").setPreferredWidth(50);
		sequencetouppaaltable.getColumn("状态").setMinWidth(50);
		sequencetouppaaltable.getColumn("状态").setMaxWidth(50);
		sequencetouppaaltable.getColumn("顺序图").setPreferredWidth(150);
		sequencetouppaaltable.getColumn("顺序图").setMinWidth(150);
		sequencetouppaaltable.getColumn("操作").setPreferredWidth(450);
		sequencetouppaaltable.getColumn("操作").setMinWidth(450);
		sequencetouppaaltable.getColumn("进度").setPreferredWidth(50);
		sequencetouppaaltable.getColumn("进度").setMinWidth(50);
		sequencetouppaaltable.getColumn("进度").setMaxWidth(50);
		sequencetouppaaltable.getColumn("").setPreferredWidth(100);
		sequencetouppaaltable.getColumn("").setMinWidth(100);
		sequencetouppaaltable.getColumn("").setMaxWidth(100);
		sequencetouppaaltable.getColumn("完成时间").setPreferredWidth(150);
		sequencetouppaaltable.getColumn("完成时间").setMinWidth(150);
        
        DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
        renderer.setBackground(new Color(71, 80, 93));
        renderer.setForeground(new Color(255, 255, 255));
        renderer.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        sequencetouppaaltable.getTableHeader().setDefaultRenderer(renderer); 
        
        sequencetouppaaltable.getTableHeader().setPreferredSize(new Dimension(1, 27));
        
        DefaultTableCellRenderer renderer1=new DefaultTableCellRenderer();
        renderer1.setForeground(new Color(115, 110, 102));
        renderer1.setBackground(new Color(255, 255, 255));
        renderer1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        renderer1.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        sequencetouppaaltable.setDefaultRenderer(Object.class, renderer1); 
        
        sequencetouppaaltable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(224, 225, 220)));
		
        sequencetouppaaltable.setBackground(new Color(255, 255, 255));
        
        tabelscrollpanel=new JScrollPane(sequencetouppaaltable);
        tabelscrollpanel.setBorder(null);
        tabelscrollpanel.setBackground(new Color(255, 255, 255));
		
		tabelpanel.setBackground(new Color(255, 255, 255));
		tabelpanel.setLayout(new GridLayout());
		tabelpanel.add(tabelscrollpanel);
		tabelpanel.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
	}
	
	public DefaultTableModel getSequencetouppaaltablemodel() {
		return sequencetouppaaltablemodel;
	}

	public JTable getSequencetouppaaltable() {
		return sequencetouppaaltable;
	}

	public JLabel getProgressbarlabel() {
		return progressbarlabel;
	}

	public JProgressBar getProgressbar() {
		return progressbar;
	}

	public List<String> getSequencelists() {
		return sequencelists;
	}

	public int getSequencelistindex() {
		return sequencelistindex;
	}

	public void setSequencelistindex(int sequencelistindex) {
		this.sequencelistindex = sequencelistindex;
	}
	
	
	
}
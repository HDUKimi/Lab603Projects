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
import com.horstmann.violet.application.gui.util.wujun.TimingTransfrom.TimingEAtoUppaal;
import com.horstmann.violet.application.gui.util.xiaole.GraghLayout.LayoutUppaal;
import com.horstmann.violet.application.gui.util.xiaole.UppaalTransfrom.ImportByDoubleClick;
import com.horstmann.violet.application.gui.util.xiaole.UppaalTransfrom.TransToVioletUppaal;
import com.horstmann.violet.application.menu.FileMenu;
import com.horstmann.violet.framework.file.GraphFile;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;

public class TimingToUppaalTabbedPanel extends JPanel{

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
	
	private DefaultTableModel timingtouppaaltablemodel;
	private JTable timingtouppaaltable;
	private JScrollPane tabelscrollpanel;
	
	private Thread t;
	private Thread progreseethread;
	private int threadstate=0;
	
	private int successcount;
	
	private int timinglistindex;
	private int oldtiminglistindex;
	private List<String> timinglists = new ArrayList<String>();
	
//	private List<String> uppaallists = new ArrayList<String>();
	
//	private static Map<String,String> timingtouppaalmap=new LinkedHashMap<String,String>();
//	
//	private Set<String> timingtouppaalset;
//	
//	private List<UppaalProcessModel> uppaalprocesslists=new ArrayList<UppaalProcessModel>();
//	
	public Map<String, IWorkspace> timinganduppaalmap=new LinkedHashMap<String, IWorkspace>();
	
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
	
	public TimingToUppaalTabbedPanel(MainFrame mainframe){
		
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
//					startTimingToUppaal();
//					threadstate=true;
//				}
				
				if(threadstate==0){
//					startTimingToUppaal();
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
				
				progressbarindex=0;
				progressbar.setValue(0);
				progressbarlabel.setText("0%");
				
				while(timingtouppaaltablemodel.getRowCount()>0){
					timingtouppaaltablemodel.removeRow(timingtouppaaltablemodel.getRowCount()-1);
				}
				
				moviepanel.getMovieLabel().setText("���ڶ�ȡ����������˳��ͼ");
				
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
	
	protected void initThread() {
		// TODO Auto-generated method stub
		
//		initUIPanel();
		mainFrame.getStepThreeCenterTabbedPane().getTestCaseProcessTabbedPanel().initUIPanel();
		mainFrame.getStepFiveCenterTabbedPane().getTestCaseReportTabbedPane().initUIPanel();
		
		mainFrame.getStepThreeCenterTabbedPane().getTestCaseProcessButton().doClick();
		mainFrame.getStepFiveCenterTabbedPane().getTestCaseReportDiagramButton().doClick();
		
		tranprocesslist=new ArrayList<>();
		tranprocesslist.add("���ڻ�ȡʱ��ͼ��Ϣ");
		tranprocesslist.add("��ʼ������");
		tranprocesslist.add("��ȡ��������Ϣ");
		tranprocesslist.add("�ϲ�template");
		tranprocesslist.add("���ʱ��ͼ���Զ�����ת��������д��xml");
		tranprocesslist.add("д�����");
		
		tranprocesslistindex=0;
		
		tranprocessstate=0;
		
		progressbarindex=0;
		progressbar.setValue(0);
		progressbarlabel.setText(" ");
		
		smallprogressbarindex=0;
		
		timinglistindex=0;
		oldtiminglistindex=-1;
		
		JCheckBox[] cb=mainFrame.getModelTransformationPanel().getModelTimingTreePanel().getTimingCheckBoxList();
		timinglists.clear();
		
		for(JCheckBox jcb:cb){
			if(jcb.isSelected()){
				timinglists.add(jcb.getText());
			}
		}
		
		while(timingtouppaaltablemodel.getRowCount()>0){
			timingtouppaaltablemodel.removeRow(timingtouppaaltablemodel.getRowCount()-1);
		}
		
		final JTextArea StepTwoArea= mainFrame.getConsolePartPanel().getTextarea2();
		
		StepTwoArea.append("UMLģ������ת����......\n");	
		
		maincallable=new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				
				while(progressbarindex<=100){
//				while(!trantask.isDone()&&progressbarindex<=100){
					System.err.println(trantask.isDone()+" - - "+progressbarindex+" + + "+(!trantask.isDone()||progressbarindex<=100));
					
					if(smallprogressbarindex==0){
						Object[] tableRowData = { timinglistindex+1, 0, timinglists.get(timinglistindex), tranprocesslist.get(tranprocesslistindex), smallprogressbarindex, smallprogressbarindex, "" };
						timingtouppaaltablemodel.addRow(tableRowData);
						timingtouppaaltablemodel.fireTableDataChanged();
					}
					System.out.println(progressbarindex + " - " + (int) (((double) 100 / timinglists.size()) * (timinglistindex + 1) + 0.5));
					if (progressbarindex == (int) (((double) 100 / timinglists.size()) * (timinglistindex + 1) + 0.5)) {

						timingtouppaaltablemodel.setValueAt(1, timinglistindex, 1);
						timingtouppaaltablemodel.setValueAt(100 + "%", timinglistindex, 4);
						timingtouppaaltablemodel.setValueAt(100, timinglistindex, 5);
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						timingtouppaaltablemodel.setValueAt(df.format(new Date()), timinglistindex, 6);

						timingtouppaaltablemodel.fireTableDataChanged();
						
						mainFrame.addTabbedPane(workspace1, 22);
						
						timinganduppaalmap.put(tranxmlname.substring(0, tranxmlname.lastIndexOf(".uppaal.violet.xml")), workspace1);
						
						DefaultTableModel dtm=mainFrame.getModelTransformationPanel().getModelTimingTreePanel().getUppaaltablemodel();
						for(int i=0;i<dtm.getRowCount();i++){
							if(dtm.getValueAt(i, 0).toString().equals(tranxmlname.substring(0, tranxmlname.lastIndexOf(".uppaal.violet.xml")))){
								dtm.removeRow(i);
								break;
							}
						}
						dtm.fireTableDataChanged();

						mainFrame.getStepTwoCenterTabbedPane().getTimingToUppaalDiagramButtonTabbedPanelLists()
								.get(mainFrame.getModelTransformationPanel().getModelTimingTreePanel()
										.getUppaaltablemodel().getRowCount())
								.setVisible(false);

						Object[] rowData = { tranxmlname.substring(0, tranxmlname.lastIndexOf(".uppaal.violet.xml")) };
						mainFrame.getModelTransformationPanel().getModelTimingTreePanel().getUppaaltablemodel()
								.addRow(rowData);
						
						timinglistindex++;
						smallprogressbarindex = 0;
						tranprocesslistindex = 0;

						while (progressbarindex == 100) {
							progressbarindex++;
						}
					}
					else{
						for(int k=0;k<timinglists.size();k++){
							if(smallprogressbarindex==100){
								break;
							}
							if(smallprogressbarindex/(double)(100.0/tranprocesslist.size())!=tranprocesslistindex){
								tranprocesslistindex=(int) (smallprogressbarindex/(double)(100.0/tranprocesslist.size()));
								timingtouppaaltablemodel.setValueAt(tranprocesslist.get(tranprocesslistindex), timinglistindex, 3);
							}
							
							timingtouppaaltablemodel.setValueAt(smallprogressbarindex + "%", timinglistindex, 4);
							timingtouppaaltablemodel.setValueAt(smallprogressbarindex, timinglistindex, 5);
							smallprogressbarindex++;
							
//							Random rand=new Random();
//							int sleeptime=(rand.nextInt(10)+1)*10;
							int sleeptime;
							sleeptime=100;
//							if(tranprocessstate==1){
//								sleeptime=100;
//							}
							Thread.sleep(sleeptime);
						}
						
						progressbarindex++;
						progressbar.setValue(progressbar.getValue()+1);
						progressbarlabel.setText(progressbar.getValue()+"%");
						
						int count=StepTwoArea.getLineCount();
						int index=count*progressbar.getValue()/100;
						if(index==count){
							index-=1;
						}
						int startindex=StepTwoArea.getLineStartOffset(index);
//						int endindex=StepTwoArea.getLineEndOffset(index);
						StepTwoArea.requestFocus();
						StepTwoArea.setSelectionStart(startindex);
						StepTwoArea.setSelectionEnd(startindex);
//						System.err.println("---"+count+"--"+index+"--"+startindex+"--"+endindex+"--");
						
						ChangeRepaint();
						
					}
				}
				System.err.println(trantask.isDone()+" - - "+progressbarindex+" + + "+(!trantask.isDone()||progressbarindex<=100));
				moviepanel.getMovieLabel().setText("����ʱ��ͼȫ��ת����ɣ��ܹ���"+timinglists.size()+"��ʱ��ͼ��ת���ɹ���"+successcount+"��ʱ��ͼ��ת����Ϊ��"+(double)successcount/timinglists.size()*100+"%");
				StepTwoArea.append("UMLģ��ת�����......\n");
				StepTwoArea.setCaretPosition(StepTwoArea.getDocument().getLength()*progressbar.getValue()/100);

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
				
//				for (String filename : timinglists) {
				
				while(timinglistindex<timinglists.size()){
					if(oldtiminglistindex==timinglistindex){
						Thread.sleep(100);
						tranprocessstate=1;
					}
					else{
						tranprocessstate=0;
						oldtiminglistindex=timinglistindex;
						String filename=timinglists.get(oldtiminglistindex);
//						timinglistindex=timinglists.indexOf(filename);
						
						String baseUrl = "D:\\ModelDriverProjectFile\\TimingDiagram\\Violet\\";
						String baseUrl2 = "D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\";
						String baseUrl3 = "D:\\ModelDriverProjectFile\\UPPAL\\3.Abstract_TestCase\\";
						
//						int starttype=mainFrame.getHomeAllTabbedPanel().getStarttype();
//						if(starttype == 1){
//							baseUrl += "\\FunctionalTest\\";
//							baseUrl2 += "\\FunctionalTest\\";
//							baseUrl3 += "\\FunctionalTest\\";
//						} else if (starttype == 2) {
//							baseUrl += "\\PerformanceTest\\";
//							baseUrl2 += "\\PerformanceTest\\";
//							baseUrl3 += "\\PerformanceTest\\";
//						} else if (starttype == 3) {
//							baseUrl += "\\TimeTest\\";
//							baseUrl2 += "\\TimeTest\\";
//							baseUrl3 += "\\TimeTest\\";
//						}
						
						System.out.println(timinglistindex+"   "+timinglists.size()+"   "+baseUrl + filename);
						String path = baseUrl + filename + ".timing.violet.xml";

						if (filename.contains("EA")) {// ��eaƽ̨��xml�ļ�
							
							moviepanel.getMovieLabel().setText("����ת��˳��ͼ "+filename+"...");
							
//							path="D:\\ModelDriverProjectFile\\TimingDiagram\\Violet\\EATiming2.timing.violet.xml";
							
//							SD2UppaalMain.transEA(path, mainFrame);// ��Ҫ�ǽ�ea��xmlת�������ǵ�wujun��xml(����������·��)
							TimingEAtoUppaal.transEA(path, mainFrame, 1);
							
//							tranprocessstate=1;
							
							System.out.println("-------------------------123");
//							XMLCopy.SourceCopyToTarget("D:\\ModelDriverProjectFile\\WJXML\\"+TimingEAtoUppaal.getDiagramDataName()+"ForXStream.xml", baseUrl3+filename+"ForXStream.xml");
							System.out.println(TimingEAtoUppaal.getDiagramDataName());
							LayoutUppaal.layout("D:\\ModelDriverProjectFile\\WJXML\\"+TimingEAtoUppaal.getDiagramDataName()+".xml");
						
							System.out.println("TimingEAtoUppaal.getDiagramDataName():+++++++++"+TimingEAtoUppaal.getDiagramDataName());
							
							tranxmlname = TransToVioletUppaal.TransToViolet(filename,1);
//							uppaallists.add(filename1);
							
							GraphFile fGraphFile1 = ImportByDoubleClick.importFileByDoubleClick("UPPAAL", tranxmlname);
							workspace1 = new Workspace(fGraphFile1);
//							mainFrame.addTabbedPane(workspace1, 21);
//
//							mainFrame.getStepTwoCenterTabbedPane().getTimingToUppaalDiagramButtonTabbedPanelLists()
//									.get(mainFrame.getModelTransformationPanel().getModelTimingTreePanel()
//											.getUppaaltablemodel().getRowCount())
//									.setVisible(false);
							
							successcount++;

						} else {// ������ƽ̨��xml�ļ�

						}
					}
				}
				System.out.println("----------------"+timinglistindex);
				
				return 1;
			}
		};
		trantask=new FutureTask<>(trancallable);
		tranthread=new Thread(trantask);
		
	}
	
	public void initUIPanel() {
		// TODO Auto-generated method stub
		
		progressbarindex=0;
		progressbar.setValue(0);
		progressbarlabel.setText("0%");
		
		while(timingtouppaaltablemodel.getRowCount()>0){
			timingtouppaaltablemodel.removeRow(timingtouppaaltablemodel.getRowCount()-1);
		}
		
		moviepanel.getMovieLabel().setText("���ڶ�ȡ����������˳��ͼ");
		
		DefaultTableModel dtm=mainFrame.getModelTransformationPanel().getModelTimingTreePanel().getUppaaltablemodel();
		for (int i = 0; i < dtm.getRowCount(); i++) {
			dtm.removeRow(i);
		}
		dtm.fireTableDataChanged();
		
		for(ButtonTabbedPanel btp:mainFrame.getStepTwoCenterTabbedPane().getTimingToUppaalDiagramButtonTabbedPanelLists()){
			btp.setVisible(false);
		}
		
		mainFrame.getAttributePartTwoPanel().getNamelabel().setText("");
		mainFrame.getAttributePartTwoPanel().getAttributepanel().removeAll();
		
		mainFrame.getConsolePartPanel().getTextarea2().setText("");
	}

//	protected void startTimingToUppaal() {
//		// TODO Auto-generated method stub
//		
//		JCheckBox[] cb=mainFrame.getModelTransformationPanel().getModelTimingTreePanel().getTimingCheckBoxList();
//		timinglists.clear();
////		uppaallists.clear();
//		progressbar.setValue(0);
//		for(JCheckBox jcb:cb){
//			if(jcb.isSelected()){
//				timinglists.add(jcb.getText());
//			}
//		}
//		while(timingtouppaaltablemodel.getRowCount()>0){
//			timingtouppaaltablemodel.removeRow(timingtouppaaltablemodel.getRowCount()-1);
//		}
//		
//		final JTextArea StepTwoArea= mainFrame.getConsolePartPanel().getTextarea2();
//		
//		StepTwoArea.append("UMLģ������ת����......\n");	
//		// TODO Auto-generated method stub
//	   	try {
//	   		//�¼��ַ��߳�(gum�����¼��ͻ�ͼ��ʱ��)
////	   		SwingUtilities.invokeLater(new Runnable() {
//	   		t = new Thread(new Runnable(){
//				
//				@Override
//				public void run() {
//					String filename1 = null;
//					try {
//						//umlת�����¼��Զ���
//                         //���ڻ�õ�ǰ������timing
//						
//						successcount=0;
//
//						for (String filename : timinglists) {
//							
//							timinglistindex=timinglists.indexOf(filename);
//							
//							String baseUrl = "D:\\ModelDriverProjectFile\\TimingDiagram\\Violet\\";
//							System.out.println(timinglistindex+"   "+timinglists.size()+"   "+baseUrl + filename);
//							String path = baseUrl + filename + ".timing.violet.xml";
//
//							if (filename.contains("EA")) {// ��eaƽ̨��xml�ļ�
//								
//								moviepanel.getMovieLabel().setText("����ת��ʱ��ͼ "+filename+"...");
//								
////								path="D:\\ModelDriverProjectFile\\TimingDiagram\\Violet\\EATiming2.timing.violet.xml";
//								
//								TimingEAtoUppaal.transEA(path);
//								
//								ShowData();
//								
//								System.out.println("*************************+++++++++++++++++++++++");
//								// ����d����д���ļ�������·�������������Ƕ�̬���ɵ���Ҫ�޸�
////								LayoutUppaal.layout(
////										"D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\UseCase1-Sequence1-Normal.xml");// timingce.xml");
//								
////								XMLCopy.SourceCopyToTarget(SD2UppaalMain.getDiagramDataName()+"ForXStream.xml", "D:\\ModelDriverProjectFile\\UPPAL\\3.Abstract_TestCase\\"+filename+"ForXStream.xml");
//								
//								LayoutUppaal.layout(TimingEAtoUppaal.getDiagramDataName()+".xml");
//								
//								filename1 = TransToVioletUppaal.TransToViolet(filename,1);
//								// String
//								// filename1="uppaalTest1.uppaal.violet.xml";
//								// GraphFile
//								// fGraphFile1=ImportByDoubleClick.importFileByDoubleClick("UPPAAL",filename1);
//								// IWorkspace workspace1=new
//								// Workspace(fGraphFile1);
//								// mainFrame.addTabbedPane(workspace1,2);
//								// mainFrame.repaint();
//								// Thread.sleep(5000);
//								// String
//								// filename2=TransToVioletUppaal.TransToViolet();
//
//								// GraphFile
//								// fGraphFile2=ImportByDoubleClick.importFileByDoubleClick("UPPAAL",filename2);
//								// IWorkspace workspace2=new
//								// Workspace(fGraphFile2);
//								// StepTwoArea.append("UMLģ�͵�ʱ���Զ���ģ���Ѿ�ת�����!\n");
//
////								uppaallists.add(filename1);
//								
//								GraphFile fGraphFile1 = ImportByDoubleClick.importFileByDoubleClick("UPPAAL", filename1);
//								IWorkspace workspace1 = new Workspace(fGraphFile1);
//								mainFrame.addTabbedPane(workspace1, 22);
//								
//								timinganduppaalmap.put(filename, workspace1);
//
//								mainFrame.getStepTwoCenterTabbedPane().getTimingToUppaalDiagramButtonTabbedPanelLists()
//										.get(mainFrame.getModelTransformationPanel().getModelTimingTreePanel()
//												.getUppaaltablemodel().getRowCount())
//										.setVisible(false);
//								
//								Object[] rowData = { filename1.substring(0, filename1.lastIndexOf(".uppaal.violet.xml")) };
//								mainFrame.getModelTransformationPanel().getModelTimingTreePanel().getUppaaltablemodel()
//										.addRow(rowData);
//								
//								successcount++;
//								
//
//							} else {// ������ƽ̨��xml�ļ�
//
//							}
//							// SD2UppaalMain.transEA(path);//��Ҫ�ǽ�ea��xmlת�������ǵ�wujun��xml(����������·��)
//							// String
//							// filename1=TransToVioletUppaal.TransToViolet();
//							// GraphFile
//							// fGraphFile1=ImportByDoubleClick.importFileByDoubleClick("UPPAAL",filename1);
//							// IWorkspace workspace1=new Workspace(fGraphFile1);
//							// mainFrame.addTabbedPane(workspace1,2);
//							// mainFrame.repaint();
//							// Thread.sleep(5000);
//							// �Ƚ��в���
//							// ��ʱ���Զ���չʾ�����ǵ�ƽ̨��
//							// LayoutUppaal.layout
//							// ("C:\\Users\\Admin\\Desktop\\��Ŀ���´���\\violetumleditor-master\\violetproduct-swing\\sequence.xml");//("stabilize_run.xml");
//							// String
//							// filename2=TransToVioletUppaal.TransToViolet();
//							// GraphFile
//							// fGraphFile2=ImportByDoubleClick.importFileByDoubleClick("UPPAAL",filename2);
//							// IWorkspace workspace2=new Workspace(fGraphFile2);
//							// StepTwoArea.append("UMLģ�͵�ʱ���Զ���ģ���Ѿ�ת�����!\n");
//							// mainFrame.addTabbedPane(workspace1,2);
//						}
//						
//						
////						for (String s : uppaallists) {
////							Object[] rowData = { s.substring(0, s.lastIndexOf(".uppaal.violet.xml")) };
////							mainFrame.getModelTransformationPanel().getModelTimingTreePanel().getUppaaltablemodel()
////									.addRow(rowData);
////
////							GraphFile fGraphFile1 = ImportByDoubleClick.importFileByDoubleClick("UPPAAL", s);
////							IWorkspace workspace1 = new Workspace(fGraphFile1);
////							mainFrame.addTabbedPane(workspace1, 2);
////
////							mainFrame.getStepTwoCenterTabbedPane().getUppaalDiagramButtonTabbedPanelLists()
////									.get(mainFrame.getModelTransformationPanel().getModelTimingTreePanel()
////											.getUppaaltablemodel().getRowCount() - 1)
////									.setVisible(false);
////						}
//						
//						System.out.println(
//								"-------------------------------------------------------------------------------------");
//
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//					moviepanel.getMovieLabel().setText("����ʱ��ͼȫ��ת����ɣ��ܹ���"+timinglists.size()+"��ʱ��ͼ��ת���ɹ���"+successcount+"��ʱ��ͼ��ת����Ϊ��"+(double)successcount/timinglists.size()*100+"%");
//					StepTwoArea.append("UMLģ��ת�����......\n");
//					threadstate=0;
//
//				}
//
//			});   	
//	   		
//	   		t.start();
//	   		
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//	}
//
//	protected void ShowData() {
//		// TODO Auto-generated method stub
//		
//		timingtouppaalmap=ShowOnTableAndConsole.getTimingtouppaalmap();
//		
//		timingtouppaalset=timingtouppaalmap.keySet();
//		
//		uppaalprocesslists.clear();
//		
//		for(String str:timingtouppaalset){
//			
//			UppaalProcessModel upmodel=new UppaalProcessModel();
//			
//			upmodel.setId(timinglistindex+1);
//			upmodel.setState(0);
//			upmodel.setName(timinglists.get(timinglistindex));
//			upmodel.setOperation(str);
//			upmodel.setProgress(0);
//			upmodel.setTime("");
//			
//			uppaalprocesslists.add(upmodel);
//			
//		}
//
//		int id = -1;
//		for (final UppaalProcessModel upm : uppaalprocesslists) {
//
//			Object[] tableRowData = { upm.getId(), upm.getState(), upm.getName(), upm.getOperation(), upm.getProgress(),
//					upm.getProgress(), upm.getTime() };
//
//			if (id == -1) {
//				id = upm.getId();
//				timingtouppaaltablemodel.addRow(tableRowData);
//
//			} else if (id == upm.getId()) {
//				timingtouppaaltablemodel.setValueAt(upm.getState(), id - 1, 1);
//				timingtouppaaltablemodel.setValueAt(upm.getOperation(), id - 1, 3);
//				timingtouppaaltablemodel.setValueAt(upm.getTime(), id - 1, 6);
//
//			}
//			if(uppaalprocesslists.indexOf(upm)==uppaalprocesslists.size()-1){
//				
//				timingtouppaaltablemodel.setValueAt(1, id - 1, 1);
//				timingtouppaaltablemodel.setValueAt(upm.getOperation(), id - 1, 3);
//				
//				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				timingtouppaaltablemodel.setValueAt(df.format(new Date()), id - 1, 6);
//				
//				timingtouppaaltablemodel.fireTableDataChanged();
//				
//				break;
//				
//			}
//
//			timingtouppaaltablemodel.fireTableDataChanged();
//
//			mainFrame.getConsolePartPanel().getTextarea2().append(upm.getOperation() + "\n");
//
//			mainFrame.getConsolePartPanel().getTextarea2().append(timingtouppaalmap.get(upm.getOperation()));
//
//			try {
//				progreseethread = new Thread(new Runnable() {
//
//					@Override
//					public void run() {
//						// TODO Auto-generated
//						// method stub
//						while (true) {
//
//							int uppaalprocesslistindex = uppaalprocesslists.indexOf(upm);
//							int uppaalprocesslistsize = uppaalprocesslists.size()-1;
//
//							System.out.println("timinglistindex:" + timinglistindex + " uppaalprocesslistindex:"
//									+ uppaalprocesslistindex + " uppaalprocesslistsize:" + uppaalprocesslistsize
//									+ " uppaalprocesslists.size():" + uppaalprocesslists.size()
//									+ " timinglists.size():" + timinglists.size());
//
//							int startprogressbar = (int) ((double) 100 / timinglists.size() * timinglistindex);
//							int endprogressbar = (int) ((double) 100 / timinglists.size() * (timinglistindex + 1));
//							int totalprogressbar = endprogressbar - startprogressbar;
//
//							System.out.println("startprogressbar:" + startprogressbar + " endprogressbar:"
//									+ endprogressbar + " totalprogressbar:" + totalprogressbar);
//
//							int startprogressbarvalue = (int) ((double) totalprogressbar / uppaalprocesslistsize
//									* uppaalprocesslistindex) + 1 + startprogressbar;
//							int endprogressbarvalue = (int) ((double) totalprogressbar / uppaalprocesslistsize
//									* (uppaalprocesslistindex + 1)) + startprogressbar;// ÿ��С������Ҫ�Ľ�����ֵ����
//
//							// System.out.println("---------------------------------------------------------------");
//							System.out.println("startprogressbarvalue:" + startprogressbarvalue
//									+ " endprogressbarvalue:" + endprogressbarvalue);
//
//							for (int i = startprogressbarvalue, j = 0; i <= endprogressbarvalue; i++, j++) {
//								// System.out.println("++++"+i);
//								progressbar.setValue(i);
//								progressbarlabel.setText(i + "%");
//
//								double modelprocess = endprogressbarvalue - startprogressbarvalue + 1;// ������ֵ����
//								double avemodelprocess = 100 / (double) uppaalprocesslistsize;// С���������ֵ����
//								int modelprocessindex = uppaalprocesslistindex % uppaalprocesslistsize;
//								// System.out.println("j:"+j+"
//								// modelprocess:"+modelprocess+"
//								// modelprocessindex:"+modelprocessindex+"
//								// avemodelprocess:"+avemodelprocess);
//								int startmodelprocess = (int) ((double) avemodelprocess * modelprocessindex
//										+ avemodelprocess / modelprocess * j) + 1;
//								int endmodelprocess = (int) ((double) avemodelprocess * modelprocessindex
//										+ avemodelprocess / modelprocess * (j + 1));// ͨ��С���������ֵ���䣬�������������1ʱ��С�������ֵ��Ӷ���
//								// System.out.println("startmodelprocess:"+startmodelprocess+"
//								// avemodelprocess/modelprocess*(j+1):"+avemodelprocess/modelprocess*(j+1)+"
//								// endmodelprocess:"+endmodelprocess);
//								for (int k = startmodelprocess; k <= endmodelprocess; k++) {
//									// System.out.println("k:"+k+"
//									// startmodelprocess:"+startmodelprocess+"
//									// endmodelprocess:"+endmodelprocess);
//									timingtouppaaltablemodel.setValueAt(k + "%", upm.getId() - 1, 4);
//									timingtouppaaltablemodel.setValueAt(k, upm.getId() - 1, 5);
//									timingtouppaaltablemodel.fireTableDataChanged();
//									try {
//										Thread.sleep(100);
//									} catch (InterruptedException e) {
//										// TODO
//										// Auto-generated
//										// catch block
//										e.printStackTrace();
//									}
//								}
//
//							}
//
//							break;
//
//						}
//					}
//				});
//				progreseethread.start();
//				progreseethread.join();
//
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//
//		}
//		
//	}

	private void initMoviePanel() {
		// TODO Auto-generated method stub
		
		moviepanel.getMovieLabel().setText("���ڶ�ȡ����������ʱ��ͼ");
		
	}

	private void initTablePanel() {
		// TODO Auto-generated method stub
		
		String[] columnNames={"���","״̬","ʱ��ͼ","����","����","","���ʱ��"};
//		String[][] tabelValues={{"1","1","3","4","5","6"}};
		String[][] tabelValues={};
		timingtouppaaltablemodel=new DefaultTableModel(tabelValues, columnNames){
			@Override  
            public boolean isCellEditable(int row,int column){  
                return false;  
            } 
		};
		
		timingtouppaaltable=new JTable(timingtouppaaltablemodel);
		timingtouppaaltable.setName("TimingToUppaalTabbedPanel");
		
        timingtouppaaltable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        timingtouppaaltable.setSelectionBackground(new Color(250, 248, 236));
        timingtouppaaltable.setGridColor(new Color(224, 226, 220));
		timingtouppaaltable.setShowGrid(true);
		timingtouppaaltable.setShowHorizontalLines(true);
		timingtouppaaltable.setShowVerticalLines(false);
		timingtouppaaltable.setFillsViewportHeight(true);
		timingtouppaaltable.setRowHeight(27);
		timingtouppaaltable.doLayout();
		timingtouppaaltable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		timingtouppaaltable.getColumnModel().getColumn(1).setCellEditor(new MyLabelCellEditor());
		timingtouppaaltable.getColumnModel().getColumn(1).setCellRenderer(new MyLabelRenderer());
//		timingtouppaaltable.getColumnModel().getColumn(4).setCellEditor(new MyLabelCellEditor());
		timingtouppaaltable.getColumnModel().getColumn(5).setCellRenderer(new MyProgressRenderer());
		
		
//		timingtouppaaltable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		timingtouppaaltable.getColumn("���").setPreferredWidth(50);
		timingtouppaaltable.getColumn("���").setMinWidth(50);
		timingtouppaaltable.getColumn("���").setMaxWidth(50);
		timingtouppaaltable.getColumn("״̬").setPreferredWidth(50);
		timingtouppaaltable.getColumn("״̬").setMinWidth(50);
		timingtouppaaltable.getColumn("״̬").setMaxWidth(50);
		timingtouppaaltable.getColumn("ʱ��ͼ").setPreferredWidth(150);
		timingtouppaaltable.getColumn("ʱ��ͼ").setMinWidth(150);
		timingtouppaaltable.getColumn("����").setPreferredWidth(450);
		timingtouppaaltable.getColumn("����").setMinWidth(450);
		timingtouppaaltable.getColumn("����").setPreferredWidth(50);
		timingtouppaaltable.getColumn("����").setMinWidth(50);
		timingtouppaaltable.getColumn("����").setMaxWidth(50);
		timingtouppaaltable.getColumn("").setPreferredWidth(100);
		timingtouppaaltable.getColumn("").setMinWidth(100);
		timingtouppaaltable.getColumn("").setMaxWidth(100);
		timingtouppaaltable.getColumn("���ʱ��").setPreferredWidth(150);
		timingtouppaaltable.getColumn("���ʱ��").setMinWidth(150);
        
        DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
        renderer.setBackground(new Color(71, 80, 93));
        renderer.setForeground(new Color(255, 255, 255));
        renderer.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        timingtouppaaltable.getTableHeader().setDefaultRenderer(renderer); 
        
        timingtouppaaltable.getTableHeader().setPreferredSize(new Dimension(1, 27));
        
        DefaultTableCellRenderer renderer1=new DefaultTableCellRenderer();
        renderer1.setForeground(new Color(115, 110, 102));
        renderer1.setBackground(new Color(255, 255, 255));
        renderer1.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        renderer1.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        timingtouppaaltable.setDefaultRenderer(Object.class, renderer1); 
        
        timingtouppaaltable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(224, 225, 220)));
		
        timingtouppaaltable.setBackground(new Color(255, 255, 255));
        
        tabelscrollpanel=new JScrollPane(timingtouppaaltable);
        tabelscrollpanel.setBorder(null);
        tabelscrollpanel.setBackground(new Color(255, 255, 255));
		
		tabelpanel.setBackground(new Color(255, 255, 255));
		tabelpanel.setLayout(new GridLayout());
		tabelpanel.add(tabelscrollpanel);
		tabelpanel.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
	}
	
	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		if(mainFrame.getStepindex()==2){
			this.setVisible(false);
			this.getRootPane().repaint();
			this.setVisible(true);
		}
	}
	
	public DefaultTableModel getTimingtouppaaltablemodel() {
		return timingtouppaaltablemodel;
	}

	public JTable getTimingtouppaaltable() {
		return timingtouppaaltable;
	}

	public JLabel getProgressbarlabel() {
		return progressbarlabel;
	}

	public JProgressBar getProgressbar() {
		return progressbar;
	}

	public List<String> getTiminglists() {
		return timinglists;
	}

	public int getTiminglistindex() {
		return timinglistindex;
	}

	public void setTiminglistindex(int timinglistindex) {
		this.timinglistindex = timinglistindex;
	}

	public Map<String, IWorkspace> getTiminganduppaalmap() {
		return timinganduppaalmap;
	}

	
}
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
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.StepSixCenterTabbedPane;
import com.horstmann.violet.application.gui.StepThreeCenterTabbedPane;
import com.horstmann.violet.application.gui.util.tanchao.RefreshTool;
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
	
//	private Thread t;
//	private Thread progreseethread;
	private int threadstate=0;
	
	private int successcount;
	
	private int sequencelistindex;
	private int oldsequencelistindex;
	private List<String> sequencelists = new ArrayList<String>();
	
	private List<String> uppaallists =new ArrayList<String>();
	private List<IWorkspace> uppaalworkspacelists=new ArrayList<IWorkspace>();
	
//	private List<String> uppaallists = new ArrayList<String>();
	
//	private static Map<String,String> sequencetouppaalmap=new LinkedHashMap<String,String>();
//	
//	private Set<String> sequencetouppaalset;
//	
//	private List<UppaalProcessModel> uppaalprocesslists=new ArrayList<UppaalProcessModel>();
	
	private Callable<Integer> maincallable;
	private FutureTask<Integer> maintask;
	private Thread mainthread;
	
	private Callable<Integer> trancallable;
	private FutureTask<Integer> trantask;
	private Thread tranthread;
	
	private List<String> tranprocesslist=new ArrayList<String>();
	private int tranprocesslistindex;
	private int tranprocessstate;
	private String tranxmlname=null;
	
	private IWorkspace workspace1;
	private IWorkspace currentworkspace;
	
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

		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("ImagePart/start.png"));
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(this.getClass().getResource("ImagePart/suspend.png"));
		icon2.setImage(icon2.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon3 = new ImageIcon(this.getClass().getResource("ImagePart/stop.png"));
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
				
				JCheckBox[] cb=mainFrame.getModelTransformationPanel().getModelSequenceTreePanel().getSequenceCheckBoxList();
				int selectcount=0;
				for(JCheckBox checkBox:cb){
					if(checkBox.isSelected()){
						selectcount++;
					}
				}
				if(selectcount==0){
					JOptionPane.showMessageDialog(mainFrame, "��ѡ��Ҫ����ģ��ת����˳��ͼ��", "��ʾ" , JOptionPane.ERROR_MESSAGE);
					return ;
				}
				
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
				
				progressbarindex=0;
				progressbar.setValue(0);
				progressbarlabel.setText(" ");
				
				while(sequencetouppaaltablemodel.getRowCount()>0){
					sequencetouppaaltablemodel.removeRow(sequencetouppaaltablemodel.getRowCount()-1);
				}

				moviepanel.getMovieLabel().setText("�ȴ�����ģ��ת��");
				
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
        progressbarlabel.setText(" ");
		

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
		
		initUIPanel();
//		mainFrame.getStepThreeCenterTabbedPane().getTestCaseProcessTabbedPanel().initUIPanel();
//		mainFrame.getStepFiveCenterTabbedPane().getTestCaseReportTabbedPane().initUIPanel();
		
//		mainFrame.getStepThreeCenterTabbedPane().getTestCaseProcessButton().doClick();
//		mainFrame.getStepFiveCenterTabbedPane().getTestCaseReportDiagramButton().doClick();
		
		tranprocesslist=new ArrayList<String>();
		tranprocesslist.add("���ڻ�ȡ˳��ͼ��Ϣ");
		tranprocesslist.add("��ʼ������");
		tranprocesslist.add("����״̬");
		tranprocesslist.add("������Ϣ���ڵ����Ƭ�Σ��������Ƭ��Ƕ�ױ�");
		tranprocesslist.add("��ȡ����loop������");
		tranprocesslist.add("��ȡ���Ƭ�ε�ȡ������");
		tranprocesslist.add("�õ��ڽӾ���");
		tranprocesslist.add("�����ڽӾ�������״̬");
		tranprocesslist.add("���˳��ͼ���Զ�����ת��������д��xml");
		tranprocesslist.add("д�����");
		
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
						
//						mainFrame.addTabbedPane(workspace1, 21);
//						
//						DefaultTableModel dtm=mainFrame.getModelTransformationPanel().getModelSequenceTreePanel().getUppaaltablemodel();
//						for(int i=0;i<dtm.getRowCount();i++){
//							if(dtm.getValueAt(i, 0).toString().equals(tranxmlname.substring(0, tranxmlname.lastIndexOf(".uppaal.violet.xml")))){
//								dtm.removeRow(i);
//								break;
//							}
//						}
//						dtm.fireTableDataChanged();
//
//						mainFrame.getStepTwoCenterTabbedPane().getSequenceToUppaalDiagramButtonTabbedPanelLists()
//								.get(mainFrame.getModelTransformationPanel().getModelSequenceTreePanel()
//										.getUppaaltablemodel().getRowCount())
//								.setVisible(false);
//
//						Object[] rowData = { tranxmlname.substring(0, tranxmlname.lastIndexOf(".uppaal.violet.xml")) };
//						mainFrame.getModelTransformationPanel().getModelSequenceTreePanel().getUppaaltablemodel()
//								.addRow(rowData);
						
						JTree uppaaltree=mainFrame.getModelTransformationPanel().getModelSequenceTreePanel().getUppaaltree();
                    	DefaultTreeModel uppaaltreemodel=mainFrame.getModelTransformationPanel().getModelSequenceTreePanel().getUppaaltreemodel();
                    	DefaultMutableTreeNode uppaaltreerootnode=mainFrame.getModelTransformationPanel().getModelSequenceTreePanel().getUppaaltreerootnode();
						
                    	mainFrame.addTabbedPane(currentworkspace, 21);
						
						String currentnodename=currentworkspace.getTitle().toString().replace(".uppaal.violet.xml", "");
						DefaultMutableTreeNode currentnode=new DefaultMutableTreeNode(currentnodename);
						
                    	uppaaltreemodel.insertNodeInto(currentnode, uppaaltreerootnode, uppaaltreerootnode.getChildCount());
                    	
						for(IWorkspace uppaalworkspace:uppaalworkspacelists){
							
							mainFrame.addTabbedPane(uppaalworkspace, 21);
							
							String nodename=uppaalworkspace.getTitle().toString().replace(".uppaal.violet.xml", "");
							DefaultMutableTreeNode node=new DefaultMutableTreeNode(nodename);
							
							uppaaltreemodel.insertNodeInto(node, currentnode, currentnode.getChildCount());
							
							TreePath path=new TreePath(uppaaltreerootnode.getPath());
							if(!uppaaltree.isVisible(path)){
								uppaaltree.makeVisible(path);
							}
							uppaaltree.getSelectionModel().setSelectionPath(new TreePath(node.getPath()));

						}
						
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
							if(smallprogressbarindex/(double)(100.0/tranprocesslist.size())!=tranprocesslistindex){
								tranprocesslistindex=(int) (smallprogressbarindex/(double)(100.0/tranprocesslist.size()));
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
								sleeptime=SD2UppaalMain.diagramslistsize*5;
							}
							if(tranprocessstate==1){
								sleeptime=100;
							}
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
						
//						ChangeRepaint();
						
					}
				}
				System.err.println(trantask.isDone()+" - - "+progressbarindex+" + + "+(!trantask.isDone()||progressbarindex<=100));
				moviepanel.getMovieLabel().setText("����˳��ͼȫ��ת����ɣ��ܹ���"+sequencelists.size()+"��˳��ͼ��ת���ɹ���"+successcount+"��˳��ͼ��ת����Ϊ��"+(double)successcount/sequencelists.size()*100+"%");
				StepTwoArea.append("UMLģ��ת�����......\n");
				StepTwoArea.setCaretPosition(StepTwoArea.getDocument().getLength()*progressbar.getValue()/100);
				StepTwoArea.append("����˳��ͼȫ��ת����ɣ��ܹ���"+sequencelists.size()+"��˳��ͼ��ת���ɹ���"+successcount+"��˳��ͼ��ת����Ϊ��"+(double)successcount/sequencelists.size()*100+"%\n");
				StepTwoArea.setCaretPosition(StepTwoArea.getDocument().getLength()*progressbar.getValue()/100);

				System.out.println(uppaallists.size());
				for(String s:uppaallists){
					System.out.println(s);
				}
				
				threadstate=0;
				
				JOptionPane.showMessageDialog(mainFrame, "ģ��ת����ɣ�", "��Ϣ" , JOptionPane.INFORMATION_MESSAGE);
				
				return 1;
			}
		};
		maintask=new FutureTask<Integer>(maincallable);
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
						String baseUrl2 = "D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\SequenceToUppal\\";
						String baseUrl3 = "D:\\ModelDriverProjectFile\\UPPAL\\3.Abstract_TestCase\\";
						
//						int starttype=mainFrame.getHomeAllTabbedPanel().getStarttype();
//						if(starttype == 1){
//							baseUrl += "FunctionalTest\\";
//							baseUrl2 += "FunctionalTest\\";
//							baseUrl3 += "FunctionalTest\\";
//						} else if (starttype == 2) {
//							baseUrl += "PerformanceTest\\";
//							baseUrl2 += "PerformanceTest\\";
//							baseUrl3 += "PerformanceTest\\";
//						} else if (starttype == 3) {
//							baseUrl += "TimeTest\\";
//							baseUrl2 += "TimeTest\\";
//							baseUrl3 += "TimeTest\\";
//						}
						
						System.out.println(sequencelistindex+"   "+sequencelists.size()+"   "+baseUrl + filename);
						String path = baseUrl + filename + ".seq.violet.xml";
						
//						StepThreeCenterTabbedPane.setBecomeRunFileName(filename+"ForXStream");
						StepThreeCenterTabbedPane.setBecomeRunFileName(filename+"Uppaal");
//						StepThreeCenterTabbedPane.setNeedRefresh(true);
						StepSixCenterTabbedPane.setBecomeRunFileName(filename+"Uppaal");
//						StepSixCenterTabbedPane.setNeedRefresh(true);
						RefreshTool.RefreshThreeAndEnd();

						if (!FileMenu.isVioletXML(path)) {// ��eaƽ̨��xml�ļ�
							
							moviepanel.getMovieLabel().setText("����ת��˳��ͼ "+filename+"...");
							
//							path="D:\\ModelDriverProjectFile\\TimingDiagram\\Violet\\EATiming2.timing.violet.xml";
							
							SD2UppaalMain.transEA(filename, path, mainFrame);// ��Ҫ�ǽ�ea��xmlת�������ǵ�wujun��xml(����������·��)
							
//							tranprocessstate=1;
							
							System.out.println("*************************+++++++++++++++++++++++"+SD2UppaalMain.diagramslistsize);
							
							String originaluppaalbaseurl="D:\\ModelDriverProjectFile\\WJXML\\"+filename+"\\";
							String uppaalbaseurl="D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\"+filename+"\\";
							
							File originaluppaalbasefile=new File(originaluppaalbaseurl);
							File[] originaluppaalbasefilelist=originaluppaalbasefile.listFiles();
							uppaallists.clear();
							System.out.println("originaluppaalbasefilelist "+originaluppaalbasefilelist.length);
							for(File f:originaluppaalbasefilelist){
								
								String fname=f.getName();
								if(fname.endsWith(".xml")&&!fname.contains("ForXStream")&&!fname.contains("-----")){
//								if(fname.endsWith(".xml")&&!fname.contains("ForXStream")&&!fname.contains("11")){
									uppaallists.add(fname.substring(0, fname.lastIndexOf(".xml")));
								}
								
							}
							
							File uppaalbasefile=new File(uppaalbaseurl);
						    if(!uppaalbasefile.exists()){
						    	while(!uppaalbasefile.mkdirs()){
						    	
						    	}
						    }
						    
						    System.out.println("originaluppaalbasefilelist "+originaluppaalbasefilelist.length);
							System.out.println("--------------------------");
							
							if(SD2UppaalMain.diagramslistsize==1){
								System.out.println("-------------------------123");
								XMLCopy.SourceCopyToTarget(originaluppaalbaseurl+SD2UppaalMain.getDiagramDataName()+"ForXStream.xml", baseUrl2+filename+"ForXStream.xml");
								LayoutUppaal.layout(originaluppaalbaseurl+SD2UppaalMain.getDiagramDataName()+".xml");
							}
							else{
								System.out.println("*************************456");
								
								File sourcefile;
								
								sourcefile=new File(originaluppaalbaseurl+"UAVForXStream.xml");
								if(sourcefile.exists()){
									XMLCopy.SourceCopyToTarget(originaluppaalbaseurl+"UAVForXStream.xml", baseUrl2+filename+"ForXStream.xml");
									LayoutUppaal.layout(originaluppaalbaseurl+"UAV.xml");
								}
								else{
									XMLCopy.SourceCopyToTarget(originaluppaalbaseurl+filename+"ForXStream.xml", baseUrl2+filename+"ForXStream.xml");
									LayoutUppaal.layout(originaluppaalbaseurl+filename+".xml");
								}
								
							}
							
							System.out.println("SD2UppaalMain.getDiagramDataName():+++++++++"+SD2UppaalMain.getDiagramDataName());
							
//							tranxmlname = TransToVioletUppaal.TransToViolet(filename,0);
							tranxmlname = TransToVioletUppaal.TransToVioletAddPath(uppaalbaseurl,filename,1,1);
							GraphFile fGraphFile1 = ImportByDoubleClick.importFileByPath(uppaalbaseurl, tranxmlname);
							currentworkspace = new Workspace(fGraphFile1);
							uppaalworkspacelists.clear();
							for(String uppaalname:uppaallists){
								LayoutUppaal.layout(originaluppaalbaseurl+uppaalname+".xml");
								tranxmlname = TransToVioletUppaal.TransToVioletAddPath(uppaalbaseurl,uppaalname,1,1);
								GraphFile fGraphFile2 = ImportByDoubleClick.importFileByPath(uppaalbaseurl, tranxmlname);
								workspace1 = new Workspace(fGraphFile2);
								uppaalworkspacelists.add(workspace1);
							}
							System.out.println("--------------------------");
							
//							GraphFile fGraphFile1 = ImportByDoubleClick.importFileByDoubleClick("UPPAAL", tranxmlname);
//							workspace1 = new Workspace(fGraphFile1);
//							mainFrame.addTabbedPane(workspace1, 21);
//
//							mainFrame.getStepTwoCenterTabbedPane().getSequenceToUppaalDiagramButtonTabbedPanelLists()
//									.get(mainFrame.getModelTransformationPanel().getModelSequenceTreePanel()
//											.getUppaaltablemodel().getRowCount())
//									.setVisible(false);
							
							successcount++;

						} else {// ������ƽ̨��xml�ļ�

						}
					}
				}
				System.out.println("----------------"+sequencelistindex);
				
				return 1;
			}
		};
		trantask=new FutureTask<Integer>(trancallable);
		tranthread=new Thread(trantask);
		
	}

	public void initUIPanel() {
		// TODO Auto-generated method stub
		
		progressbarindex=0;
		progressbar.setValue(0);
		progressbarlabel.setText("");
		
		while(sequencetouppaaltablemodel.getRowCount()>0){
			sequencetouppaaltablemodel.removeRow(sequencetouppaaltablemodel.getRowCount()-1);
		}
		
		moviepanel.getMovieLabel().setText("�ȴ�����ģ��ת��");
		
//		DefaultTableModel dtm=mainFrame.getModelTransformationPanel().getModelSequenceTreePanel().getUppaaltablemodel();
//		for (int i = 0; i < dtm.getRowCount(); i++) {
//			dtm.removeRow(i);
//		}
//		dtm.fireTableDataChanged();
		
		for(ButtonTabbedPanel btp:mainFrame.getStepTwoCenterTabbedPane().getSequenceToUppaalDiagramButtonTabbedPanelLists()){
			btp.setVisible(false);
		}
		for(ButtonTabbedPanel btp:mainFrame.getStepTwoCenterTabbedPane().getTimingToUppaalDiagramButtonTabbedPanelLists()){
			btp.setVisible(false);
		}
		
		mainFrame.getModelTransformationPanel().getModelSequenceTreePanel().initUIPanel();
		
		mainFrame.getAttributePartTwoPanel().getNamelabel().setText("");
		mainFrame.getAttributePartTwoPanel().getAttributepanel().removeAll();
		
		mainFrame.getConsolePartPanel().getTextarea2().setText("");
	}

//	protected void startSequenceToUppaal() {
//		// TODO Auto-generated method stub
//		
//		JCheckBox[] cb=mainFrame.getModelTransformationPanel().getModelSequenceTreePanel().getSequenceCheckBoxList();
//		sequencelists.clear();
////		uppaallists.clear();
//		progressbar.setValue(0);
//		for(JCheckBox jcb:cb){
//			if(jcb.isSelected()){
//				sequencelists.add(jcb.getText());
//			}
//		}
//		while(sequencetouppaaltablemodel.getRowCount()>0){
//			sequencetouppaaltablemodel.removeRow(sequencetouppaaltablemodel.getRowCount()-1);
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
//                         //���ڻ�õ�ǰ������sequence
//
//						successcount=0;
//						
//						for (String filename : sequencelists) {
//							
//							sequencelistindex=sequencelists.indexOf(filename);
//							
//							String baseUrl = "D:\\ModelDriverProjectFile\\SequenceDiagram\\Violet\\";
//							System.out.println(sequencelistindex+"   "+sequencelists.size()+"   "+baseUrl + filename);
//							String path = baseUrl + filename + ".seq.violet.xml";
//
//							if (filename.contains("EA")) {// ��eaƽ̨��xml�ļ�
//								
//								moviepanel.getMovieLabel().setText("����ת��˳��ͼ "+filename+"...");
//								
////								path="D:\\ModelDriverProjectFile\\TimingDiagram\\Violet\\EATiming2.timing.violet.xml";
//								
//								SD2UppaalMain.transEA(path, mainFrame);// ��Ҫ�ǽ�ea��xmlת�������ǵ�wujun��xml(����������·��)
//								
//								ShowData();
//								
//								System.out.println("*************************+++++++++++++++++++++++");
//								// ����d����д���ļ�������·�������������Ƕ�̬���ɵ���Ҫ�޸�D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\
////								LayoutUppaal.layout(
////										"UseCase4-Sequence1-Normal.xml");// ("sequence.xml");
//								
////								System.out.println("SD2UppaalMain.getDiagramDataName():+++++++++"+SD2UppaalMain.getDiagramDataName()+" - - "+SD2UppaalMain.getDiagramDataName()+"ForXStream.xml");//ǰ���ļ�Ϊʱ���Զ�����Ҫ�������ļ�Ϊ��������������Ҫ
//								
////								XMLCopy.SourceCopyToTarget("D:\\ModelDriverProjectFile\\WJXML\\"+SD2UppaalMain.getDiagramDataName()+"ForXStream.xml", "D:\\ModelDriverProjectFile\\UPPAL\\3.Abstract_TestCase\\"+filename+"ForXStream.xml");
//								XMLCopy.SourceCopyToTarget("D:\\ModelDriverProjectFile\\WJXML\\UAVForXStream.xml", "D:\\ModelDriverProjectFile\\UPPAL\\3.Abstract_TestCase\\"+filename+"ForXStream.xml");
//								
////								LayoutUppaal.layout("D:\\ModelDriverProjectFile\\WJXML\\"+SD2UppaalMain.getDiagramDataName()+".xml");
//								LayoutUppaal.layout("D:\\ModelDriverProjectFile\\WJXML\\UAV.xml");
//								
//								filename1 = TransToVioletUppaal.TransToViolet(filename);//wujun��xmlת��Ϊƽ̨��ʾ��ʱ���Զ���xml
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
//								mainFrame.addTabbedPane(workspace1, 21);
//
//								mainFrame.getStepTwoCenterTabbedPane().getSequenceToUppaalDiagramButtonTabbedPanelLists()
//										.get(mainFrame.getModelTransformationPanel().getModelSequenceTreePanel()
//												.getUppaaltablemodel().getRowCount())
//										.setVisible(false);
//								
//								Object[] rowData = { filename1.substring(0, filename1.lastIndexOf(".uppaal.violet.xml")) };
//								mainFrame.getModelTransformationPanel().getModelSequenceTreePanel().getUppaaltablemodel()
//										.addRow(rowData);
//								
//								successcount++;
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
////							mainFrame.getModelTransformationPanel().getModelSequenceTreePanel().getUppaaltablemodel()
////									.addRow(rowData);
////
////							GraphFile fGraphFile1 = ImportByDoubleClick.importFileByDoubleClick("UPPAAL", s);
////							IWorkspace workspace1 = new Workspace(fGraphFile1);
////							mainFrame.addTabbedPane(workspace1, 2);
////
////							mainFrame.getStepTwoCenterTabbedPane().getUppaalDiagramButtonTabbedPanelLists()
////									.get(mainFrame.getModelTransformationPanel().getModelSequenceTreePanel()
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
////					int count=mainFrame.getModelTransformationPanel().getModelSequenceTreePanel().getUppaaltablemodel().getRowCount();
//					
//					moviepanel.getMovieLabel().setText("����˳��ͼȫ��ת����ɣ��ܹ���"+sequencelists.size()+"��˳��ͼ��ת���ɹ���"+successcount+"��˳��ͼ��ת����Ϊ��"+(double)successcount/sequencelists.size()*100+"%");
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
//		sequencetouppaalmap=ShowOnTableAndConsole.getSequencetouppaalmap();
//		
//		sequencetouppaalset=sequencetouppaalmap.keySet();
//		
//		uppaalprocesslists.clear();
//		
//		for(String str:sequencetouppaalset){
//			
//			UppaalProcessModel upmodel=new UppaalProcessModel();
//			
//			upmodel.setId(sequencelistindex+1);
//			upmodel.setState(0);
//			upmodel.setName(sequencelists.get(sequencelistindex));
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
//				sequencetouppaaltablemodel.addRow(tableRowData);
//
//			} else if (id == upm.getId()) {
//				sequencetouppaaltablemodel.setValueAt(upm.getState(), id - 1, 1);
//				sequencetouppaaltablemodel.setValueAt(upm.getOperation(), id - 1, 3);
//				sequencetouppaaltablemodel.setValueAt(upm.getTime(), id - 1, 6);
//
//			}
//			if(uppaalprocesslists.indexOf(upm)==uppaalprocesslists.size()-1){
//				
//				sequencetouppaaltablemodel.setValueAt(1, id - 1, 1);
//				sequencetouppaaltablemodel.setValueAt(upm.getOperation(), id - 1, 3);
//				
//				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				sequencetouppaaltablemodel.setValueAt(df.format(new Date()), id - 1, 6);
//				
//				sequencetouppaaltablemodel.fireTableDataChanged();
//				
//				break;
//				
//			}
//
//			sequencetouppaaltablemodel.fireTableDataChanged();
//
//			mainFrame.getConsolePartPanel().getTextarea2().append(upm.getOperation() + "\n");
//
//			mainFrame.getConsolePartPanel().getTextarea2().append(sequencetouppaalmap.get(upm.getOperation()));
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
//							System.out.println("sequencelistindex:" + sequencelistindex + " uppaalprocesslistindex:"
//									+ uppaalprocesslistindex + " uppaalprocesslistsize:" + uppaalprocesslistsize
//									+ " uppaalprocesslists.size():" + uppaalprocesslists.size()
//									+ " sequencelists.size():" + sequencelists.size());
//
//							int startprogressbar = (int) ((double) 100 / sequencelists.size() * sequencelistindex);
//							int endprogressbar = (int) ((double) 100 / sequencelists.size() * (sequencelistindex + 1));
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
//									sequencetouppaaltablemodel.setValueAt(k + "%", upm.getId() - 1, 4);
//									sequencetouppaaltablemodel.setValueAt(k, upm.getId() - 1, 5);
//									sequencetouppaaltablemodel.fireTableDataChanged();
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
		
		moviepanel.getMovieLabel().setText("�ȴ�����ģ��ת��");
		
	}

	private void initTablePanel() {
		// TODO Auto-generated method stub
		
		String[] columnNames={"���","״̬","˳��ͼ","����","����","","���ʱ��"};
//		String[][] tabelValues={{"1","1","3","4","5","6"}};
		String[][] tabelValues={};
		sequencetouppaaltablemodel=new DefaultTableModel(tabelValues, columnNames){
			@Override  
            public boolean isCellEditable(int row,int column){  
                return false;  
            } 
		};
		
		sequencetouppaaltable=new JTable(sequencetouppaaltablemodel);
		sequencetouppaaltable.setName("SequenceToUppaalTabbedPanel");
		
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
		
		sequencetouppaaltable.getColumn("���").setPreferredWidth(50);
		sequencetouppaaltable.getColumn("���").setMinWidth(50);
		sequencetouppaaltable.getColumn("���").setMaxWidth(50);
		sequencetouppaaltable.getColumn("״̬").setPreferredWidth(50);
		sequencetouppaaltable.getColumn("״̬").setMinWidth(50);
		sequencetouppaaltable.getColumn("״̬").setMaxWidth(50);
		sequencetouppaaltable.getColumn("˳��ͼ").setPreferredWidth(150);
		sequencetouppaaltable.getColumn("˳��ͼ").setMinWidth(150);
		sequencetouppaaltable.getColumn("����").setPreferredWidth(450);
		sequencetouppaaltable.getColumn("����").setMinWidth(450);
		sequencetouppaaltable.getColumn("����").setPreferredWidth(50);
		sequencetouppaaltable.getColumn("����").setMinWidth(50);
		sequencetouppaaltable.getColumn("����").setMaxWidth(50);
		sequencetouppaaltable.getColumn("").setPreferredWidth(100);
		sequencetouppaaltable.getColumn("").setMinWidth(100);
		sequencetouppaaltable.getColumn("").setMaxWidth(100);
		sequencetouppaaltable.getColumn("���ʱ��").setPreferredWidth(150);
		sequencetouppaaltable.getColumn("���ʱ��").setMinWidth(150);
        
        DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
        renderer.setBackground(new Color(71, 80, 93));
        renderer.setForeground(new Color(255, 255, 255));
        renderer.setFont(new Font("΢���ź�", Font.PLAIN, 13));
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        sequencetouppaaltable.getTableHeader().setDefaultRenderer(renderer); 
        
        sequencetouppaaltable.getTableHeader().setPreferredSize(new Dimension(1, 27));
        
        DefaultTableCellRenderer renderer1=new DefaultTableCellRenderer();
        renderer1.setForeground(new Color(115, 110, 102));
        renderer1.setBackground(new Color(255, 255, 255));
        renderer1.setFont(new Font("΢���ź�", Font.PLAIN, 12));
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
	
	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		if(mainFrame.getStepindex()==2){
			this.setVisible(false);
			this.getRootPane().repaint();
			this.setVisible(true);
		}
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
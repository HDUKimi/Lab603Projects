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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.plaf.ProgressBarUI;

import com.horstmann.violet.application.consolepart.TableHeadPanel;
import com.horstmann.violet.application.consolepart.ValidationLocationMessagePanel;
import com.horstmann.violet.application.consolepart.ValidationMessageComparePanel;
import com.horstmann.violet.application.consolepart.ValidationPathTupleTimePanel;
import com.horstmann.violet.application.consolepart.ValidationStateComparePanel;
import com.horstmann.violet.application.consolepart.ValidationTransitionMessagePanel;
import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.util.tanchao.TranMessageColorize;
import com.horstmann.violet.application.gui.util.tanchao.XMLVerificationTranMessage;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.CompareEAtoAutomata;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.PathTuple;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.UppaalTransition;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.RowStringsForDisplay.MessageCompare;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.RowStringsForDisplay.StateCompare;
import com.horstmann.violet.workspace.IWorkspace;

public class ValidationToolPanel extends JPanel{

	private MainFrame mainFrame;
	private IWorkspace workspace;
	
	private JPanel toolbuttonpanel1;
	private JPanel toolbuttonpanel2;
	private JPanel toolbuttonpanel3;
	private JPanel toolbuttonpanel4;
	private JPanel toolbuttonpanel5;
	private JButton toolbutton1;
	private JButton toolbutton2;
	private JButton toolbutton3;
	private JButton toolbutton4;
	private JButton toolbutton5;
	private JPanel emptypanel1;
	private JPanel emptypanel2;
	private static JLabel progressbarlabel;
	
	private static JProgressBar progressbar;
	private static int progressbarindex;
	
	private Thread t;
	private static Thread progreseethread;
	private int threadstate=0;
	
	private static List<UppaalTransition> selecteduppaalmessagelist=new ArrayList<UppaalTransition>();
	
	private int statesuccesssum=0,statefailsum=0,messagesuccesssum=0,messagefailsum=0;
	
	public ValidationToolPanel(MainFrame mainFrame,IWorkspace workspace) {
		
		this.mainFrame=mainFrame;
		this.workspace = workspace;
		
		toolbuttonpanel1 = new JPanel();
		toolbuttonpanel2 = new JPanel();
		toolbuttonpanel3 = new JPanel();
		toolbuttonpanel4 = new JPanel();
		toolbuttonpanel5 = new JPanel();

		toolbutton1 = new JButton();
		toolbutton2 = new JButton();
		toolbutton3 = new JButton();
		toolbutton4 = new JButton();
		toolbutton5 = new JButton();
		
		init();
		
		this.setBackground(new Color(207, 214, 229));
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 5,3));
		
		this.add(toolbuttonpanel1);
		this.add(toolbuttonpanel2);
		this.add(emptypanel1);
		this.add(toolbuttonpanel3);
		this.add(toolbuttonpanel4);
		this.add(toolbuttonpanel5);
		this.add(emptypanel2);
		this.add(progressbar);
		this.add(progressbarlabel);
		
		this.setPreferredSize(new Dimension(100, 29));
		this.setMaximumSize(new Dimension(100, 29));
		this.setMinimumSize(new Dimension(100, 29));
		
	}

	private void init() {
		// TODO Auto-generated method stub
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "zoomin1.png");
		icon1.setImage(icon1.getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(path + "zoomout1.png");
		icon2.setImage(icon2.getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));
		ImageIcon icon3 = new ImageIcon(path + "start.png");
		icon3.setImage(icon3.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon4 = new ImageIcon(path + "suspend.png");
		icon4.setImage(icon4.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon5 = new ImageIcon(path + "stop.png");
		icon5.setImage(icon5.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
	
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
				workspace.getSideBar().getEditorToolsBar().getZoomInButton().doClick();
				mainFrame.getConsolePartPanel().getTextarea6().append("放大一倍视图\n");
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
				workspace.getSideBar().getEditorToolsBar().getZoomOutButton().doClick();
				mainFrame.getConsolePartPanel().getTextarea6().append("缩小一倍视图\n");
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
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			   	
				if(threadstate==0){
					
					initValidationProgressbar();
					
					int validationlabeltabindex;
					validationlabeltabindex=mainFrame.getModelExistValidationPanel().getValidationlabeltabindex();
					
					if(validationlabeltabindex==1){

						startValidation();
					} else if (validationlabeltabindex == 2) {
						startValidation();
					} else if (validationlabeltabindex == 3) {
						startValidation3();
						
					} else if (validationlabeltabindex == 4) {
						startValidation4();
						
					}
					
					
					threadstate=1;
					
				}
				else if(threadstate==1){
					
				}
				else if(threadstate==-1){
					threadstate=1;
					t.resume();
					progreseethread.resume();
				}
				
			}
		});
		
		toolbutton4.setIcon(icon4);
		toolbutton4.setFocusable(false);
		toolbutton4.setContentAreaFilled(false);
		toolbutton4.setBorderPainted(false);
		toolbutton4.addMouseListener(new ButtonMouseListener());
		toolbutton4.setPreferredSize(new Dimension(21,21));
		toolbutton4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				t.suspend();
				progreseethread.suspend();
				threadstate=-1;
				
			}
		});
		
		
		toolbutton5.setIcon(icon5);
		toolbutton5.setFocusable(false);
		toolbutton5.setContentAreaFilled(false);
		toolbutton5.setBorderPainted(false);
		toolbutton5.addMouseListener(new ButtonMouseListener());
		toolbutton5.setPreferredSize(new Dimension(21,21));
		toolbutton5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				t.stop();
				progreseethread.stop();
				threadstate=0;
				
				initValidationProgressbar();
				
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
		toolbuttonpanel4.setLayout(new GridLayout());
		toolbuttonpanel4.setBackground(new Color(207, 214, 229));
		toolbuttonpanel4.add(toolbutton4);
		toolbuttonpanel5.setLayout(new GridLayout());
		toolbuttonpanel5.setBackground(new Color(207, 214, 229));
		toolbuttonpanel5.add(toolbutton5);

		emptypanel1=new JPanel();
		emptypanel1.setPreferredSize(new Dimension(16, 23));
		emptypanel1.setBackground(new Color(133,145,162));
		emptypanel1.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 10, new Color(207, 214, 229)));
		
		emptypanel2=new JPanel();
		emptypanel2.setPreferredSize(new Dimension(16, 23));
		emptypanel2.setBackground(new Color(133,145,162));
		emptypanel2.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 10, new Color(207, 214, 229)));
		
	}

	public void initValidationProgressbar() {
		// TODO Auto-generated method stub
		
		progressbar.setValue(0);
		progressbarlabel.setText(" ");
		
		progressbarindex=0;
		
	}

	private  void startValidation3() {
		// TODO Auto-generated method stub
		
		t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				startRunProgressbar(mainFrame.getModelExistValidationPanel().getStateCompareList().size()+mainFrame.getModelExistValidationPanel().getMessageCompareList().size());
				
				showStateCompare(mainFrame.getModelExistValidationPanel().getStateCompareList());

				showMessageCompare(mainFrame.getModelExistValidationPanel().getMessageCompareList());
				
				mainFrame.getValidationResultPanel().getThreenamelabel().setText("<html><body>在状态比较中,共找到"+mainFrame.getModelExistValidationPanel().getStateCompareList().size()+"条状态,成功"+statesuccesssum+"条,失败"+statefailsum+"条<br>在消息比较中,共找到"+mainFrame.getModelExistValidationPanel().getMessageCompareList().size()+"条消息,成功"+messagesuccesssum+"条,失败"+messagefailsum+"条</body></html>");
				
				
				threadstate = 0;

			}
		});

		t.start();
		
	}
	
	private  void startValidation4() {
		// TODO Auto-generated method stub
		
		t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				startRunProgressbar(mainFrame.getModelExistValidationPanel().getTimes().size());
				
				showPathTupleTime(mainFrame.getModelExistValidationPanel().getPathtuple(), mainFrame.getModelExistValidationPanel().getTimes());
				
				mainFrame.getValidationResultPanel().ChangeRepaint();
				
				threadstate = 0;

			}
		});

		t.start();
		
	}

	protected void startRunProgressbar(final int listsize) {
		// TODO Auto-generated method stub
		try {
			progreseethread=new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					for(int index=0;index<listsize;index++){
						int startprogressbar = (int) ((double) 100 / listsize * progressbarindex);
						int endprogressbar = (int) ((double) 100 / listsize * (progressbarindex + 1));
						
						progressbarindex++;
						
						for(int i=startprogressbar;i<endprogressbar;i++){
							progressbar.setValue(progressbar.getValue()+1);
							progressbarlabel.setText(progressbar.getValue()+"%");
							
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					}
					
				}
				
			});
			
			progreseethread.start();
			progreseethread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void startValidation() {
		// TODO Auto-generated method stub
		
		
		selecteduppaalmessagelist.clear();
		for(JCheckBox jcb:mainFrame.getModelExistValidationPanel().getUppaalMessageCheckBoxList()){
			if(jcb.isSelected()){
				selecteduppaalmessagelist.add(mainFrame.getModelExistValidationPanel().getUppaalmessagelist().get(mainFrame.getModelExistValidationPanel().getUppaalMessageCheckBoxList().indexOf(jcb)));
			}
		}
		
		t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				if(mainFrame.getModelExistValidationPanel().getValidationlabeltabindex()==1){
					
					List<UppaalTransition> uppaalTransitionList = null;
					uppaalTransitionList=mainFrame.getModelExistValidationPanel().getEv().getSelectedTransitionsIfExist(selecteduppaalmessagelist);
					
					if(uppaalTransitionList==null){
						System.out.println("list is null");
					}
					else{
						//------------
//						String path = "D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\uppaalTest1.uppaal.violet.xml";
	                    //得到k---name v-----id的map
//						HashMap<String,String> mapCopy=XMLVerificationTranMessage.getTranMessage(path);
						TranMessageColorize tmc=new TranMessageColorize();
						tmc.ColorizeTran(uppaalTransitionList,mainFrame.getModelExistValidationPanel().getUppaalworkspace());
						
						//------------
						
						mainFrame.getValidationResultPanel().getOnenamelabel().setText("共找到"+uppaalTransitionList.size()+"条消息：");
						
						mainFrame.getValidationResultPanel().getOneresultpanel().removeAll();
						
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
//						mainFrame.getValidationResultPanel().getResultpanel().add(Box.createVerticalGlue());
						mainFrame.getValidationResultPanel().getOneresultpanel().add(resultpanel);
						mainFrame.getValidationResultPanel().ChangeRepaint();
						System.out.println("++++++++++++++++++++");
						
					}
					
				}else if(mainFrame.getModelExistValidationPanel().getValidationlabeltabindex()==2){
					
					List<PathTuple> pathTupleList=null;//给我的集合
					pathTupleList=mainFrame.getModelExistValidationPanel().getEv().getPathOfSelectedTransitions(selecteduppaalmessagelist);
					
					if(pathTupleList==null){
						System.out.println("list is null");
					}
					else{
						//xie
						TranMessageColorize tmc=new TranMessageColorize();
						tmc.ColorizeTranAndState(pathTupleList, mainFrame.getModelExistValidationPanel().getUppaalworkspace());
						mainFrame.getValidationResultPanel().getTwonamelabel().setText("共找到一条路径，包含"+pathTupleList.size()+"个节点和"+pathTupleList.size()+"条消息：");
						
						mainFrame.getValidationResultPanel().getTworesultpanel().removeAll();
						
						System.out.println("++++++++++++++++++++");
						
						JPanel resultpanel=new JPanel();
						JPanel emptypanel=new JPanel();
						resultpanel.setOpaque(false);
						emptypanel.setOpaque(false);
						
						GridBagLayout layout = new GridBagLayout();
						resultpanel.setLayout(layout);
						int i=0;
						
						for(PathTuple pt:pathTupleList){
							System.out.println(pt.getLocation().toString()+ " --- "+pt.getTransition().toString());
							
							ValidationLocationMessagePanel vlmpanel=new ValidationLocationMessagePanel(pt.getLocation());
							resultpanel.add(vlmpanel);
							layout.setConstraints(vlmpanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
							
							ValidationTransitionMessagePanel vtmpanel=new ValidationTransitionMessagePanel(pt.getTransition());
							resultpanel.add(vtmpanel);
							layout.setConstraints(vtmpanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
							
						}
						
						resultpanel.add(emptypanel);
						layout.setConstraints(emptypanel, new GBC(0, i++, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
//						mainFrame.getValidationResultPanel().getResultpanel().add(Box.createVerticalGlue());
						mainFrame.getValidationResultPanel().getTworesultpanel().add(resultpanel);
						mainFrame.getValidationResultPanel().ChangeRepaint();
						
						System.out.println("++++++++++++++++++++");
						
					}
				}

				threadstate=0;
				
			}
		});
		
		t.start();
		
	}
	
	public static void startProcessCount(final int listsize){
		
		try {
			progreseethread=new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					
//					int listsize=selecteduppaalmessagelist.size();
					int startprogressbar = (int) ((double) 100 / listsize * progressbarindex);
					int endprogressbar = (int) ((double) 100 / listsize * (progressbarindex + 1));
					
					progressbarindex++;
					
					for(int i=startprogressbar;i<endprogressbar;i++){
						progressbar.setValue(progressbar.getValue()+1);
						progressbarlabel.setText(progressbar.getValue()+"%");
						
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
				}
				
			});
			
			progreseethread.start();
			progreseethread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
			mainFrame.getValidationResultPanel().getFournamelabel().setText("<html><body>自动机路径累加的时间和为"+times.get(times.size()-1)+"s<br>最后一个状态的开始时间为"+CompareEAtoAutomata.findTimingDiagramLastStateStartTime()+"s<br>验证成功</html></body>");
		}
		else{
			mainFrame.getValidationResultPanel().getFournamelabel().setText("<html><body>自动机路径累加的时间和为"+times.get(times.size()-1)+"s<br>最后一个状态的开始时间为"+CompareEAtoAutomata.findTimingDiagramLastStateStartTime()+"s<br>验证失败</html></body>");
		}
		
		
	}
	
}

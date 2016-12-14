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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.plaf.ProgressBarUI;

import com.horstmann.violet.application.consolepart.ValidationTransitionMessagePanel;
import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.PathTuple;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.UppaalTransition;
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
				mainFrame.getConsolePartPanel().getTextarea().append("放大一倍视图\n");
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
				mainFrame.getConsolePartPanel().getTextarea().append("缩小一倍视图\n");
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
					
					progressbar.setValue(0);
					progressbarlabel.setText(" ");
					
					progressbarindex=0;
					
					startValidation();
					
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
				
				progressbar.setValue(0);
				progressbarlabel.setText("");
				
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
						
						mainFrame.getValidationResultPanel().getNamelabel().setText("共找到"+uppaalTransitionList.size()+"条消息：");
						
						mainFrame.getValidationResultPanel().getResultpanel().removeAll();
//						mainFrame.getValidationResultPanel().getResultpanel().setLayout(new BoxLayout(mainFrame.getValidationResultPanel().getResultpanel(), BoxLayout.Y_AXIS));
						
						System.out.println(mainFrame.getValidationResultPanel().getResultpanel().size());
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
						mainFrame.getValidationResultPanel().getResultpanel().add(resultpanel);
						mainFrame.getValidationResultPanel().ChangeRepaint();
						System.out.println("++++++++++++++++++++");
						
					}
					
				}else if(mainFrame.getModelExistValidationPanel().getValidationlabeltabindex()==2){
					
					List<PathTuple> pathTupleList=null;
					pathTupleList=mainFrame.getModelExistValidationPanel().getEv().getPathOfSelectedTransitions(selecteduppaalmessagelist);
					
					if(pathTupleList==null){
						System.out.println("list is null");
					}
					else{
						
						System.out.println("++++++++++++++++++++");
						for(PathTuple pt:pathTupleList){
							System.out.println(pt.getLocation().toString()+ " --- "+pt.getTransition().toString());
						}
						System.out.println("++++++++++++++++++++");
						
					}
				}

				threadstate=0;
				
			}
		});
		
		t.start();
		
	}
	
	public static void startProcessCount(){
		
		try {
			progreseethread=new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					
					int startprogressbar = (int) ((double) 100 / selecteduppaalmessagelist.size() * progressbarindex);
					int endprogressbar = (int) ((double) 100 / selecteduppaalmessagelist.size() * (progressbarindex + 1));
					
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
	
}

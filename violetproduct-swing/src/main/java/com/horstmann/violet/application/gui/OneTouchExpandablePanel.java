package com.horstmann.violet.application.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.horstmann.violet.application.gui.stepCenterTabbedPane.ButtonTabbedPanel;
import com.horstmann.violet.framework.file.GraphFile;
import com.horstmann.violet.framework.file.IFile;
import com.horstmann.violet.framework.file.IGraphFile;
import com.horstmann.violet.framework.file.LocalFile;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;
import com.horstmann.violet.workspace.WorkspacePanel;

public class OneTouchExpandablePanel extends JPanel{

	private MainFrame mainFrame;
	private WorkspacePanel workspacePanel;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
//	private JButton button5;
//	private JButton button6;
	
	private JPanel labelpanel1;
	private JPanel labelpanel2;
	private JPanel labelpanel3;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private int flag1=1;
	private int flag2=1;
	private int flag3=1;
	
	private int location1;
	private int location2;
	private int location3;

	public OneTouchExpandablePanel(MainFrame mainFrame){
		this.setBackground(new Color(41,57,85));
		this.mainFrame=mainFrame;
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
		
//		location1=mainFrame.getJs3().getMaximumDividerLocation();
//		location1=200;
//		location2=screenHeight*2/3;
		
		labelpanel1=new JPanel();
		labelpanel2=new JPanel();
		labelpanel3=new JPanel();
		
		label1=new JLabel();
		label2=new JLabel();
		label3=new JLabel();
		
		initlabelpanel();
		
//		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
//		this.setBorder(BorderFactory.createEtchedBorder());
		initButton();
		
//		this.setLayout(new GridLayout(5,1));
//		this.add(label1);
//		this.add(button1);
//		this.add(button2);
//		this.add(button3);
//		this.add(button4);
		
//		System.out.println(mainFrame.getCenterPanel().getSize().getHeight());
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//		this.setAlignmentX(0.0f);
		this.add(Box.createVerticalStrut(6));
		this.add(labelpanel1);
//		this.add(Box.createHorizontalStrut(20));
		this.add(Box.createVerticalStrut(10));
		this.add(labelpanel2);
		this.add(Box.createVerticalStrut(10));
		this.add(labelpanel3);
		this.add(Box.createVerticalStrut(screenHeight-300));
		this.add(Box.createVerticalGlue());
//		this.add(Box.createGlue());
		
		
//		this.add(button5);
//		this.add(button6);
		SetButtonListener();
	}

	private void initlabelpanel() {
		// TODO Auto-generated method stub
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final int screenWidth = (int) screenSize.getWidth();
		final int screenHeight = (int) screenSize.getHeight();
		
		
		label1.setText("<html><body>模<br>型<br>列<br>表</body></html>");
		label2.setText("<html><body>消<br>息<br>控<br>制<br>台</body></html>");
		label3.setText("<html><body>属<br>性<br>信<br>息</body></html>");
		
		label1.setFont(new Font("System", Font.PLAIN, 12));
		label1.setForeground(new Color(255, 255, 255));
		label1.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 2));
		
		label1.addMouseListener(new MouseListener() {
			
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
				if(flag1==1){
					
					if(mainFrame.getJs1().getDividerLocation()>1){
						location1=mainFrame.getJs1().getDividerLocation();
					}
					mainFrame.getJs1().setDividerSize(0);
					mainFrame.getOpreationPart().setVisible(false);
					
					flag1=0;
				}
				else if(flag1==0){
					
					if(mainFrame.getJs1().getDividerLocation()>1){
						location1=mainFrame.getJs1().getDividerLocation();
					}
					mainFrame.getJs1().setDividerLocation(location1);
					mainFrame.getJs1().setDividerSize(6);
					mainFrame.getOpreationPart().setVisible(true);
					
					flag1=1;
				}
				
				//**********************************************************
//				Callable<Integer> callable1=new Callable<Integer>() {
//					
//					@Override
//					public Integer call() throws Exception {
//						// TODO Auto-generated method stub
//						
////						stateDiagramButtonTabbedPanelLists.get(0).getTabbedbutton().doClick();
////						Thread.sleep(2000);
//						
//						IFile file =  new LocalFile(new File("D:\\ModelDriverProjectFile\\StateDiagram\\Violet\\1.state.violet.xml"));
//						IGraphFile graphFile = new GraphFile(file);
//						IWorkspace workspace = new Workspace(graphFile);
//						mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().removeAll();
//						mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().setLayout(new GridLayout());
//						mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().add(workspace.getAWTComponent());
//						mainFrame.getStepOneCenterTabbedPane().ChangeRepaint();
//						
//						
//						return 1;
//					}
//				};
//				final FutureTask<Integer> task1=new FutureTask<>(callable1);
//				final Thread thread1=new Thread(task1);
//				
//				Callable<Integer> callable2=new Callable<Integer>() {
//					
//					@Override
//					public Integer call() throws Exception {
//						// TODO Auto-generated method stub
//						
////						stateDiagramButtonTabbedPanelLists.get(1).getTabbedbutton().doClick();
////						Thread.sleep(2000);
//						
//						IFile file =  new LocalFile(new File("D:\\ModelDriverProjectFile\\StateDiagram\\Violet\\2.state.violet.xml"));
//						IGraphFile graphFile = new GraphFile(file);
//						IWorkspace workspace = new Workspace(graphFile);
//						mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().removeAll();
//						mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().setLayout(new GridLayout());
//						mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().add(workspace.getAWTComponent());
//						mainFrame.getStepOneCenterTabbedPane().ChangeRepaint();
//						
//						return 1;
//					}
//				};
//				final FutureTask<Integer> task2=new FutureTask<>(callable2);
//				final Thread thread2=new Thread(task2);
//				
//				Callable<Integer> callable3=new Callable<Integer>() {
//					
//					@Override
//					public Integer call() throws Exception {
//						// TODO Auto-generated method stub
//						
////						stateDiagramButtonTabbedPanelLists.get(2).getTabbedbutton().doClick();
////						Thread.sleep(000);
//						IFile file =  new LocalFile(new File("D:\\ModelDriverProjectFile\\StateDiagram\\Violet\\3.state.violet.xml"));
//						IGraphFile graphFile = new GraphFile(file);
//						IWorkspace workspace = new Workspace(graphFile);
//						mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().removeAll();
//						mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().setLayout(new GridLayout());
//						mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().add(workspace.getAWTComponent());
//						mainFrame.getStepOneCenterTabbedPane().ChangeRepaint();
//						
//						return 1;
//					}
//				};
//				final FutureTask<Integer> task3=new FutureTask<>(callable3);
//				final Thread thread3=new Thread(task3);
//				
//				Callable<Integer> callable4=new Callable<Integer>() {
//					
//					@Override
//					public Integer call() throws Exception {
//						// TODO Auto-generated method stub
//						
////						stateDiagramButtonTabbedPanelLists.get(3).getTabbedbutton().doClick();
////						Thread.sleep(000);
//						IFile file =  new LocalFile(new File("D:\\ModelDriverProjectFile\\StateDiagram\\Violet\\1.state.violet.xml"));
//						IGraphFile graphFile = new GraphFile(file);
//						IWorkspace workspace = new Workspace(graphFile);
//						mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().removeAll();
//						mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().setLayout(new GridLayout());
//						mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().add(workspace.getAWTComponent());
//						mainFrame.getStepOneCenterTabbedPane().ChangeRepaint();
//						
//						return 1;
//					}
//				};
//				final FutureTask<Integer> task4=new FutureTask<>(callable4);
//				final Thread thread4=new Thread(task4);
//				
//				Callable<Integer> callable=new Callable<Integer>() {
//					
//					@Override
//					public Integer call() throws Exception {
//						// TODO Auto-generated method stub
//						int i=1;
//						while(true){
//							if(i==1&&task1.isDone()){
//								thread2.start();
//								i++;
//							}
//							else if(i==2&&task2.isDone()){
//								System.out.println("------////------");
//								thread3.start();
//								i++;
//							}
//							else if(i==3&&task3.isDone()){
//								System.out.println("++++++////++++++");
//								thread4.start();
//								i++;
//								break;
//							}
//							Thread.sleep(1000);
//						}
//						return 1;
//					}
//				};
//				FutureTask<Integer> task=new FutureTask<>(callable);
//				Thread thread=new Thread(task);
//				
//				thread1.start();
//				thread.start();
				
				//*********************************************
				
				
			}
		});
		
		label2.setFont(new Font("System", Font.PLAIN, 12));
		label2.setForeground(new Color(255, 255, 255));
		label2.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 2));
		
		label2.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(flag2==1){
					
					location2=mainFrame.getJs3().getDividerLocation();
					mainFrame.getJs3().setDividerSize(0);
					mainFrame.getConsolePartPanel().setVisible(false);
					flag2=0;
				}
				else if(flag2==0){
					
					if(mainFrame.getJs3().getSize().getHeight()<mainFrame.getConsolePartPanel().getSize().getHeight()){
						location2=(int) (mainFrame.getJs3().getSize().getHeight()/2);
					}
					else{
						location2=(int) (mainFrame.getJs3().getSize().getHeight()-mainFrame.getConsolePartPanel().getSize().getHeight());
					}
					
					mainFrame.getJs3().setDividerLocation(location2);
					mainFrame.getJs3().setDividerSize(6);
					mainFrame.getConsolePartPanel().setVisible(true);
					flag2=1;
				}
			}
		});
		
		label3.setFont(new Font("System", Font.PLAIN, 12));
		label3.setForeground(new Color(255, 255, 255));
		label3.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 2));
		
		label3.addMouseListener(new MouseListener() {
			
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
				if(flag3==1){
					
					location3=mainFrame.getJs2().getDividerLocation();
					mainFrame.getJs2().setDividerSize(0);
					mainFrame.getAttributePart().setVisible(false);
					
					flag3=0;
				}
				else if(flag3==0){
					
					if(mainFrame.getJs2().getSize().getWidth()<mainFrame.getAttributePart().getSize().getWidth()){
						location3=(int) (mainFrame.getJs2().getSize().getWidth()/2);
					}
					else{
						location3=(int) (mainFrame.getJs2().getSize().getWidth()-mainFrame.getAttributePart().getSize().getWidth());
					}
					
					mainFrame.getJs2().setDividerLocation(location3);
					mainFrame.getJs2().setDividerSize(6);
					mainFrame.getAttributePart().setVisible(true);
					
					flag3=1;
				}
			}
		});
		
		labelpanel1.setLayout(new GridLayout());
		labelpanel1.setOpaque(false);
		labelpanel1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 6, new Color(70, 90, 125)));
//		labelpanel1.setPreferredSize(new Dimension(23, 0));
		labelpanel1.add(label1);
		labelpanel2.setLayout(new GridLayout());
		labelpanel2.setOpaque(false);
		labelpanel2.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 6, new Color(70, 90, 125)));
//		labelpanel2.setPreferredSize(new Dimension(23, 0));
		labelpanel2.add(label2);
		labelpanel3.setLayout(new GridLayout());
		labelpanel3.setOpaque(false);
		labelpanel3.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 6, new Color(70, 90, 125)));
//		labelpanel3.setPreferredSize(new Dimension(23, 0));
		labelpanel3.add(label3);
		
		
		
	}

	private void SetButtonListener() {
		// TODO Auto-generated method stub
		
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if(mainFrame.getJs1().getDividerLocation()>1){
					location1=mainFrame.getJs1().getDividerLocation();
				}
//				mainFrame.getJs1().setDividerLocation(0);
				
				mainFrame.getJs1().setDividerSize(0);
				mainFrame.getOpreationPart().setVisible(false);
				
				
//				System.out.println("----"+mainFrame.getJs3().getDividerLocation()+"++"+location1);
				
			}
		});
		
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(mainFrame.getJs1().getDividerLocation()>1){
					location1=mainFrame.getJs1().getDividerLocation();
				}
				mainFrame.getJs1().setDividerLocation(location1);
				mainFrame.getJs1().setDividerSize(6);
				mainFrame.getOpreationPart().setVisible(true);
//				System.out.println("++++"+mainFrame.getJs3().getDividerLocation()+"++"+location1);
			}
		});
		
		button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(mainFrame.getJs3().getDividerLocation()<mainFrame.getJs4().getMaximumDividerLocation()){
					location2=mainFrame.getJs3().getDividerLocation();
				}
				mainFrame.getJs3().setDividerLocation(1.0);
//				System.out.println("----"+mainFrame.getJs4().getDividerLocation()+"++"+location2);
			}
		});
		
		button4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(mainFrame.getJs3().getDividerLocation()<mainFrame.getJs3().getMaximumDividerLocation()){
					location2=mainFrame.getJs3().getDividerLocation();
				}
				mainFrame.getJs3().setDividerLocation(location2);
//				System.out.println("----"+mainFrame.getJs4().getDividerLocation()+"++"+location2);
			}
		});
		
//		button5.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				workspacePanel.getWorkspacejs().setDividerLocation(1.0);
//			}
//		});
		
	}

	private void initButton() {
		// TODO Auto-generated method stub
		button1=new JButton();
		button2=new JButton();
		button3=new JButton();
		button4=new JButton();
//		button5=new JButton();
//		button6=new JButton();
		
		URL urlin=this.getClass().getResource("testin.png");
        ImageIcon iconin=new ImageIcon(urlin);
        iconin.setImage(iconin.getImage().getScaledInstance(22, 22, Image.SCALE_DEFAULT));
		
        URL urlout=this.getClass().getResource("testout.png");
        ImageIcon iconout=new ImageIcon(urlout);
        iconout.setImage(iconout.getImage().getScaledInstance(22, 22, Image.SCALE_DEFAULT));
		
        button1.setIcon(iconin);
        button1.setPreferredSize(new Dimension(30, 30));
        button3.setIcon(iconin);
        button3.setPreferredSize(new Dimension(30, 30));
//        button5.setIcon(iconin);
//        button5.setPreferredSize(new Dimension(30, 30));
        button2.setIcon(iconout);
        button2.setPreferredSize(new Dimension(30, 30));
        button4.setIcon(iconout);
        button4.setPreferredSize(new Dimension(30, 30));
//        button6.setIcon(iconout);
//        button6.setPreferredSize(new Dimension(30, 30));
        
        button1.setFocusable(false);
        button1.setContentAreaFilled(false);
        button1.setBorderPainted(false);
        button1.addMouseListener(new ButtonMouseListener());
        button2.setFocusable(false);
        button2.setContentAreaFilled(false);
        button2.setBorderPainted(false);
        button2.addMouseListener(new ButtonMouseListener());
        button3.setFocusable(false);
        button3.setContentAreaFilled(false);
        button3.setBorderPainted(false);
        button3.addMouseListener(new ButtonMouseListener());
        button4.setFocusable(false);
        button4.setContentAreaFilled(false);
        button4.setBorderPainted(false);
        button4.addMouseListener(new ButtonMouseListener());
//        button5.setFocusable(false);
//        button6.setFocusable(false);
        
	}

	public int getFlag1() {
		return flag1;
	}

	public void setFlag1(int flag1) {
		this.flag1 = flag1;
	}

	public int getFlag2() {
		return flag2;
	}

	public void setFlag2(int flag2) {
		this.flag2 = flag2;
	}

	public int getFlag3() {
		return flag3;
	}

	public void setFlag3(int flag3) {
		this.flag3 = flag3;
	}

	public int getLocation1() {
		return location1;
	}

	public void setLocation1(int location1) {
		this.location1 = location1;
	}

	public int getLocation2() {
		return location2;
	}

	public void setLocation2(int location2) {
		this.location2 = location2;
	}

	public int getLocation3() {
		return location3;
	}

	public void setLocation3(int location3) {
		this.location3 = location3;
	}

	
	
}


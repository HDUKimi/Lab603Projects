package com.horstmann.violet.application.gui.stepCenterTabbedPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.util.tanchao.XMLToTree;
import com.horstmann.violet.application.gui.util.wqq.AutoMataTransfrom2.C;
import com.horstmann.violet.framework.file.IGraphFile;
import com.horstmann.violet.workspace.IWorkspace;

public class ButtonTabbedPanel extends JPanel{

	private MainFrame mainFrame;
	private IWorkspace workspace;
	
	private int buttontabbedpanelindex;//当前选中的button在其对应图的list中的下标位置
	private String tabbedbuttontext;
	
	private JButton tabbedbutton;
	private JLabel fixedlabel;
	private JLabel delectlabel;
	
	private JPanel labelpanel;
	
	
//	private List<ButtonTabbedPanel> buttonTabbedPanelLists;
	
	public ButtonTabbedPanel(MainFrame mainFrame, IWorkspace workspace, int buttontabbedpanelindex, String tabbedbuttontext){
		
		this.mainFrame=mainFrame;
		this.workspace=workspace;
		this.buttontabbedpanelindex=buttontabbedpanelindex;
		this.tabbedbuttontext=tabbedbuttontext;
		
		tabbedbutton=new JButton();
		fixedlabel=new JLabel();
		delectlabel=new JLabel();
		labelpanel=new JPanel();
		
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		
		tabbedbutton.setText(tabbedbuttontext);
		tabbedbutton.setForeground(Color.WHITE);
		tabbedbutton.setBackground(new Color(77, 96, 130));
		tabbedbutton.setContentAreaFilled(false);
		tabbedbutton.setFocusable(false);
		tabbedbutton.setBorderPainted(false);
		setTabbedButtonActionListener();
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "leftarrow.png");
		icon1.setImage(icon1.getImage().getScaledInstance(8, 7, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(path + "fork.png");
		icon2.setImage(icon2.getImage().getScaledInstance(10, 8, Image.SCALE_DEFAULT));

		fixedlabel.setIcon(icon1);
		fixedlabel.setBorder(BorderFactory.createEmptyBorder(5, 3, 0, 6));
		delectlabel.setIcon(icon2);
		delectlabel.setBorder(BorderFactory.createEmptyBorder(6, 3, 0, 6));
		setDelectLabelMouseListener();
		
		labelpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		labelpanel.setOpaque(false);
		labelpanel.add(fixedlabel);
		labelpanel.add(delectlabel);
		
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(77, 96, 130));
		this.setPreferredSize(new Dimension(150, 23));
		this.setBorder(BorderFactory.createEmptyBorder(2, 0, 1, 0));
		this.add(tabbedbutton, BorderLayout.WEST);
		this.add(labelpanel, BorderLayout.EAST);
		
		
	}

	private void setDelectLabelMouseListener() {
		// TODO Auto-generated method stub
		
		delectlabel.addMouseListener(new MouseListener() {
			
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
//				delectlabel.getParent().getParent().setVisible(false);
//				mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().removeAll();
//				mainFrame.setVisible(false);
//				mainFrame.getContentPane().repaint();
//				mainFrame.setVisible(true);
				
				((ButtonTabbedPanel)delectlabel.getParent().getParent()).getTabbedbutton().doClick();
				
				if(mainFrame.getStepindex()==1){
					mainFrame.getMenuFactory().getFileMenu(mainFrame).fileCloseItem.doClick();
				}
				else if(mainFrame.getStepindex()==2){
					((ButtonTabbedPanel)delectlabel.getParent().getParent()).setVisible(false);
//					this.getStepOneCenterTabbedPane().getButtonPanel().remove(this.getStepOneCenterTabbedPane().getSelectedButtonTabbedPanel());
					mainFrame.getStepTwoCenterTabbedPane().getDiagramPanel().removeAll();
					mainFrame.setVisible(false);
					mainFrame.getContentPane().repaint();
					mainFrame.setVisible(true);
				}
				
				
				
			}
		});
		
	}

	private void setTabbedButtonActionListener() {
		// TODO Auto-generated method stub
		
		tabbedbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(mainFrame.getStepindex()==1){
//					buttonTabbedPanelLists=mainFrame.getStepOneCenterTabbedPane().getSequenceDiagramButtonTabbedPanelLists();
					
					for(JPanel jp:mainFrame.getStepOneCenterTabbedPane().getSequenceDiagramButtonTabbedPanelLists()){
						jp.setBackground(new Color(77, 96, 130));
					}
					for(JPanel jp:mainFrame.getStepOneCenterTabbedPane().getStateDiagramButtonTabbedPanelLists()){
						jp.setBackground(new Color(77, 96, 130));
					}
					for(JPanel jp:mainFrame.getStepOneCenterTabbedPane().getTimingDiagramButtonTabbedPanelLists()){
						jp.setBackground(new Color(77, 96, 130));
					}
					for(JPanel jp:mainFrame.getStepOneCenterTabbedPane().getUsecaseDiagramButtonTabbedPanelLists()){
						jp.setBackground(new Color(77, 96, 130));
					}
					
					setBackground(new Color(58, 105, 190));
					mainFrame.getStepOneCenterTabbedPane().setSelectedButtonTabbedPanel(((ButtonTabbedPanel)tabbedbutton.getParent()));
					
					System.out.println("------------------------");
					System.out.println("xuanzhongl "+((ButtonTabbedPanel)tabbedbutton.getParent()).getButtontabbedpanelindex());
					
					workspace=((ButtonTabbedPanel) tabbedbutton.getParent()).getWorkspace();
					
					System.out.println(workspace.getGraphFile().isSaveRequired()+"  "+workspace.getGraphFile().getDirectory()+"  "+workspace.getGraphFile().getFilename());
					
					// 这里分两种情况
					// 1、新建:判断是不是类似于N.XXX类型
					// 2、导入:判断文件名后缀是不是.XXX.violet.xml
					if (workspace.getTitle().toString().endsWith(".seq.violet.xml")
							|| workspace.getTitle().toString().substring(2, 4).equals("Se")) {
						
						mainFrame.getStepOneCenterTabbedPane().setSelectedIndex(0);
						mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().removeAll();
						mainFrame.getStepOneCenterTabbedPane().getSequenceDiagramTabbedPane().removeAll();

						if(mainFrame.getActiveWorkspace()!=null){
							
							mainFrame.getStepOneCenterTabbedPane().getSequenceDiagramTabbedPane().add(workspace.getAWTComponent(),
									new GBC(0, 0).setWeight(1, 1).setFill(GBC.BOTH));
							
							ToolPanel toolPanel = new ToolPanel(mainFrame,workspace);

							System.out.println(workspace.getTitle().toString());
							
							MoviePanel moviePanel = new MoviePanel();
							moviePanel.getMovieLabel().setText("顺序图"+tabbedbutton.getText()+"正在编辑 ...");

							GridBagLayout layout = new GridBagLayout();
							mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().setLayout(layout);
							mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().add(toolPanel);
							mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().add(moviePanel);
							mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().add(mainFrame.getStepOneCenterTabbedPane().getSequenceDiagramTabbedPane());
							layout.setConstraints(toolPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
							layout.setConstraints(moviePanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
							layout.setConstraints(mainFrame.getStepOneCenterTabbedPane().getSequenceDiagramTabbedPane(),
									new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
						}
						
						mainFrame.getStepOneCenterTabbedPane().ChangeRepaint();
						
						mainFrame.getConsolePartPanel().getTextarea().append("打开顺序图 "+tabbedbutton.getText()+" 的绘图面板\n");
						
//						mainFrame.getAttributePartPanel().getTreepanel().removeAll();
//						if(workspace.getGraphFile().getDirectory()!=null){
//							mainFrame.getAttributePartPanel().getTreepanel().add(XMLToTree.getTree(workspace.getGraphFile().getDirectory()+"\\"+workspace.getGraphFile().getFilename()),BorderLayout.WEST);
//						}
//						mainFrame.getAttributePartPanel().ChangeRepaint();
						
					}
					else if (workspace.getTitle().toString().endsWith(".ucase.violet.xml")
							|| workspace.getTitle().toString().substring(2, 4).equals("Us")) {
						
						mainFrame.getStepOneCenterTabbedPane().setSelectedIndex(3);
						mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().removeAll();
						mainFrame.getStepOneCenterTabbedPane().getUsecaseDiagramTabbedPane().removeAll();

						if(mainFrame.getActiveWorkspace()!=null){

							mainFrame.getStepOneCenterTabbedPane().getUsecaseDiagramTabbedPane().add(workspace.getAWTComponent(),
									new GBC(0, 0).setWeight(1, 1).setFill(GBC.BOTH));
							
							ToolPanel toolPanel = new ToolPanel(mainFrame,workspace);

							System.out.println(workspace.getTitle().toString());
							
							MoviePanel moviePanel = new MoviePanel();
							moviePanel.getMovieLabel().setText("用例图"+tabbedbutton.getText()+"正在编辑 ...");

							GridBagLayout layout = new GridBagLayout();
							mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().setLayout(layout);
							mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().add(toolPanel);
							mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().add(moviePanel);
							mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().add(mainFrame.getStepOneCenterTabbedPane().getUsecaseDiagramTabbedPane());
							layout.setConstraints(toolPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
							layout.setConstraints(moviePanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
							layout.setConstraints(mainFrame.getStepOneCenterTabbedPane().getUsecaseDiagramTabbedPane(),
									new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
							
							
//							IGraphFile graphFile = workspace.getGraphFile();
//							graphFile.getFilename()+" 到 "+graphFile.getDirectory()
							
							
						}
						
						mainFrame.getStepOneCenterTabbedPane().ChangeRepaint();
						
						mainFrame.getConsolePartPanel().getTextarea().append("打开用例图 "+tabbedbutton.getText()+" 的绘图面板\n");
						
					}
					else if (workspace.getTitle().toString().endsWith(".timing.violet.xml")
							|| workspace.getTitle().toString().substring(2, 4).equals("Ti")) {
						
						mainFrame.getStepOneCenterTabbedPane().setSelectedIndex(1);
						mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().removeAll();
						mainFrame.getStepOneCenterTabbedPane().getTimingDiagramTabbedPane().removeAll();

						if(mainFrame.getActiveWorkspace()!=null){
							
							mainFrame.getStepOneCenterTabbedPane().getTimingDiagramTabbedPane().add(workspace.getAWTComponent(),
									new GBC(0, 0).setWeight(1, 1).setFill(GBC.BOTH));
							
							ToolPanel toolPanel = new ToolPanel(mainFrame,workspace);

							System.out.println(workspace.getTitle().toString());
							
							MoviePanel moviePanel = new MoviePanel();
							moviePanel.getMovieLabel().setText("时序图"+tabbedbutton.getText()+"正在编辑 ...");

							GridBagLayout layout = new GridBagLayout();
							mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().setLayout(layout);
							mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().add(toolPanel);
							mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().add(moviePanel);
							mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().add(mainFrame.getStepOneCenterTabbedPane().getTimingDiagramTabbedPane());
							layout.setConstraints(toolPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
							layout.setConstraints(moviePanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
							layout.setConstraints(mainFrame.getStepOneCenterTabbedPane().getTimingDiagramTabbedPane(),
									new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
						}
						
						mainFrame.getStepOneCenterTabbedPane().ChangeRepaint();
						
						mainFrame.getConsolePartPanel().getTextarea().append("打开时序图 "+tabbedbutton.getText()+" 的绘图面板\n");
						
					}
					else if (workspace.getTitle().toString().endsWith(".state.violet.xml")
							|| workspace.getTitle().toString().substring(2, 4).equals("St")) {
						
						mainFrame.getStepOneCenterTabbedPane().setSelectedIndex(2);
						mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().removeAll();
						mainFrame.getStepOneCenterTabbedPane().getStateDiagramTabbedPane().removeAll();

						if(mainFrame.getActiveWorkspace()!=null){
							
							mainFrame.getStepOneCenterTabbedPane().getStateDiagramTabbedPane().add(workspace.getAWTComponent(),
									new GBC(0, 0).setWeight(1, 1).setFill(GBC.BOTH));
							
							ToolPanel toolPanel = new ToolPanel(mainFrame,workspace);

							System.out.println(workspace.getTitle().toString());
							
							MoviePanel moviePanel = new MoviePanel();
							moviePanel.getMovieLabel().setText("状态图"+tabbedbutton.getText()+"正在编辑 ...");

							GridBagLayout layout = new GridBagLayout();
							mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().setLayout(layout);
							mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().add(toolPanel);
							mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().add(moviePanel);
							mainFrame.getStepOneCenterTabbedPane().getDiagramPanel().add(mainFrame.getStepOneCenterTabbedPane().getStateDiagramTabbedPane());
							layout.setConstraints(toolPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
							layout.setConstraints(moviePanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
							layout.setConstraints(mainFrame.getStepOneCenterTabbedPane().getStateDiagramTabbedPane(),
									new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
						}
						
						mainFrame.getStepOneCenterTabbedPane().ChangeRepaint();
						
						mainFrame.getConsolePartPanel().getTextarea().append("打开状态图 "+tabbedbutton.getText()+" 的绘图面板\n");
						
					}
					
					mainFrame.getAttributePartPanel().getSteponenamelabel().setText(tabbedbutton.getText());

					mainFrame.getAttributePartPanel().getAttributepanel().removeAll();
					if(workspace.getGraphFile().getDirectory()!=null){
						mainFrame.getAttributePartPanel().setSteponeattributetree(XMLToTree.getTree(workspace.getGraphFile().getDirectory()+"\\"+workspace.getGraphFile().getFilename()));
					}
					JScrollPane StepOneScrollTree = new JScrollPane(
							mainFrame.getAttributePartPanel().getSteponeattributetree());
					StepOneScrollTree.setBorder(null);
					StepOneScrollTree.setBackground(new Color(255, 255, 255));

					mainFrame.getAttributePartPanel().getAttributepanel().add(StepOneScrollTree);
					
					mainFrame.getAttributePartPanel().ChangeRepaint();
					
				}
				else if(mainFrame.getStepindex()==2){
					
					for(JPanel jp:mainFrame.getStepTwoCenterTabbedPane().getSequenceToUppaalDiagramButtonTabbedPanelLists()){
						jp.setBackground(new Color(77, 96, 130));
					}
					for(JPanel jp:mainFrame.getStepTwoCenterTabbedPane().getTimingToUppaalDiagramButtonTabbedPanelLists()){
						jp.setBackground(new Color(77, 96, 130));
					}
					mainFrame.getStepTwoCenterTabbedPane().getSequenceToUppaalDiagramButtonPanel().setBackground(new Color(77, 96, 130));
					mainFrame.getStepTwoCenterTabbedPane().getTimingToUppaalDiagramButtonPanel().setBackground(new Color(77, 96, 130));
					
					setBackground(new Color(58, 105, 190));
					mainFrame.getStepTwoCenterTabbedPane().setSelectedButtonTabbedPanel(((ButtonTabbedPanel)tabbedbutton.getParent()));
					
					System.out.println("------------------------");
					System.out.println("xuanzhongl "+((ButtonTabbedPanel)tabbedbutton.getParent()).getButtontabbedpanelindex());
					System.out.println(workspace.getGraphFile().isSaveRequired()+"  "+workspace.getGraphFile().getDirectory()+"  "+workspace.getGraphFile().getFilename());
					
					workspace=((ButtonTabbedPanel) tabbedbutton.getParent()).getWorkspace();
					
					
					mainFrame.getStepTwoCenterTabbedPane().getDiagramPanel().removeAll();
					mainFrame.getStepTwoCenterTabbedPane().getUppaalDiagramTabbedPane().removeAll();

					mainFrame.getStepTwoCenterTabbedPane().getUppaalDiagramTabbedPane().add(workspace.getAWTComponent(),
							new GBC(0, 0).setWeight(1, 1).setFill(GBC.BOTH));
					
					UppaalToolPanel toolPanel = new UppaalToolPanel(mainFrame,workspace);

					System.out.println(workspace.getTitle().toString());

					MoviePanel moviePanel = new MoviePanel();
					moviePanel.getMovieLabel().setText("时间机" + tabbedbutton.getText() + "正在编辑 ...");

					GridBagLayout layout = new GridBagLayout();
					mainFrame.getStepTwoCenterTabbedPane().getDiagramPanel().setLayout(layout);
					mainFrame.getStepTwoCenterTabbedPane().getDiagramPanel().add(toolPanel);
					mainFrame.getStepTwoCenterTabbedPane().getDiagramPanel().add(moviePanel);
					mainFrame.getStepTwoCenterTabbedPane().getDiagramPanel()
							.add(mainFrame.getStepTwoCenterTabbedPane().getUppaalDiagramTabbedPane());
					layout.setConstraints(toolPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
					layout.setConstraints(moviePanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
					layout.setConstraints(mainFrame.getStepTwoCenterTabbedPane().getUppaalDiagramTabbedPane(),
							new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
					
					mainFrame.getStepTwoCenterTabbedPane().ChangeRepaint();
					
					mainFrame.getConsolePartPanel().getTextarea().append("打开时间自动机"+tabbedbutton.getText()+"\n");
					
					mainFrame.getAttributePartPanel().getSteptwonamelabel().setText(tabbedbutton.getText());

					mainFrame.getAttributePartPanel().getAttributepanel().removeAll();
					mainFrame.getAttributePartPanel().setSteptwoattributetree(XMLToTree.getTree(workspace.getGraphFile().getDirectory()+"\\"+workspace.getGraphFile().getFilename()));
					
					JScrollPane StepTwoScrollTree = new JScrollPane(
							mainFrame.getAttributePartPanel().getSteptwoattributetree());
					StepTwoScrollTree.setBorder(null);
					StepTwoScrollTree.setBackground(new Color(255, 255, 255));

					mainFrame.getAttributePartPanel().getAttributepanel().add(StepTwoScrollTree);
					
					mainFrame.getAttributePartPanel().ChangeRepaint();
					
//					mainFrame.setVisible(false);
//					mainFrame.getContentPane().repaint();
//					mainFrame.setVisible(true);
					
				}
			}
		});
		
	}

	public JButton getTabbedbutton() {
		return tabbedbutton;
	}

	public void setTabbedbutton(JButton tabbedbutton) {
		this.tabbedbutton = tabbedbutton;
	}

	public IWorkspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(IWorkspace workspace) {
		this.workspace = workspace;
	}

	public int getButtontabbedpanelindex() {
		return buttontabbedpanelindex;
	}

	public void setButtontabbedpanelindex(int buttontabbedpanelindex) {
		this.buttontabbedpanelindex = buttontabbedpanelindex;
	}
	
	
	
}

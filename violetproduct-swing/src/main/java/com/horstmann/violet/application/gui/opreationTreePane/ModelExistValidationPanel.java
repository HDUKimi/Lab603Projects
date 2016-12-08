package com.horstmann.violet.application.gui.opreationTreePane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.ButtonTabbedPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.MoviePanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.ToolPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.UppaalToolPanel;
import com.horstmann.violet.application.menu.FileMenu;
import com.horstmann.violet.framework.file.GraphFile;
import com.horstmann.violet.framework.file.IFile;
import com.horstmann.violet.framework.file.IGraphFile;
import com.horstmann.violet.framework.file.LocalFile;
import com.horstmann.violet.workspace.IWorkspace;
import com.horstmann.violet.workspace.Workspace;

public class ModelExistValidationPanel extends JPanel{

	private MainFrame mainFrame;
	
	private List<String> timinglists=new ArrayList<String>();
	
	private JPanel titlepanel;
	private JPanel treepanel;
	
	private JLabel titlelabel;
	private JPanel titleiconlabelpanel;
	private JLabel titleiconlabel1;
	private JLabel titleiconlabel2;
	private JLabel titleiconlabel3;
	
	private JPanel timingpanel;
	private JPanel timinglabelpanel;
	private JLabel timinglabel;
	private JPanel timingtoolpanel;
	private JButton timingtoolbutton1;
	private JPanel timingtablepanel;
	private JScrollPane timingscrollpanel;
	
	private DefaultTableModel timingtablemodel;
	private JTable timingtable;
	
	private JPanel validationpanel;
	private JPanel validationlabelpanel;
	private JLabel validationlabel;
	private JPanel validationtoolpanel;
	private JButton validationtoolbutton1;
	private JButton validationtoolbutton2;
	
	public ModelExistValidationPanel(MainFrame mainFrame){
		
		this.mainFrame=mainFrame;
		
		initFileList();
		
		titlepanel=new JPanel();
		treepanel=new JPanel();
		
		titlelabel = new JLabel();
		titleiconlabelpanel=new JPanel();
		titleiconlabel1 = new JLabel();
		titleiconlabel2 = new JLabel();
		titleiconlabel3 = new JLabel();
		
		timingpanel=new JPanel();
		timinglabelpanel=new JPanel();
		timinglabel=new JLabel();
		timingtoolpanel=new JPanel();
		timingtoolbutton1=new JButton();
		timingtablepanel=new JPanel();
		
		validationpanel=new JPanel();
		validationlabelpanel=new JPanel();
		validationlabel=new JLabel();
		validationtoolpanel=new JPanel();
		validationtoolbutton1=new JButton();
		validationtoolbutton2=new JButton();
		
		titlepanel.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(142, 155, 188)));
		treepanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(142, 155, 188)));
		
		initTitlePanel();
		
		initTreePanel();
		
		GridBagLayout layout=new GridBagLayout();
		this.setLayout(layout);
		this.add(titlepanel);
		this.add(treepanel);
		layout.setConstraints(titlepanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(treepanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();
		
		this.setMinimumSize(new Dimension(screenWidth/8, screenHeight-150));
	
		
	}

	private void initTitlePanel() {
		// TODO Auto-generated method stub
		
		titlelabel.setText("ģ��ת����֤");
		titlelabel.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		titlelabel.setForeground(new Color(255, 255, 255));
		titlelabel.setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 0));
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "triangulararrow.png");
		icon1.setImage(icon1.getImage().getScaledInstance(8,4, Image.SCALE_DEFAULT));
		titleiconlabel1.setIcon(icon1);
		titleiconlabel1.setBorder(BorderFactory.createEmptyBorder(4, 4,4, 6));
		ImageIcon icon2 = new ImageIcon(path + "downarrow.png");
		icon2.setImage(icon2.getImage().getScaledInstance(7,11, Image.SCALE_DEFAULT));
		titleiconlabel2.setIcon(icon2);
		titleiconlabel2.setBorder(BorderFactory.createEmptyBorder(4, 4,4, 4));
		ImageIcon icon3 = new ImageIcon(path + "fork.png");
		icon3.setImage(icon3.getImage().getScaledInstance(10,8, Image.SCALE_DEFAULT));
		titleiconlabel3.setIcon(icon3);
		titleiconlabel3.setBorder(BorderFactory.createEmptyBorder(4, 4,4, 4));
		
		titleiconlabel3.addMouseListener(new MouseListener() {
			
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
				
				mainFrame.getJs1().setDividerSize(0);
				mainFrame.getOpreationPart().setVisible(false);
				mainFrame.getOneTouchExpandablePanel().setFlag1(0);
				mainFrame.getOneTouchExpandablePanel().setLocation1(mainFrame.getJs1().getDividerLocation());
				
			}
		});
		
		titleiconlabelpanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		titleiconlabelpanel.setOpaque(false);
		titleiconlabelpanel.add(titleiconlabel1);
		titleiconlabelpanel.add(titleiconlabel2);
		titleiconlabelpanel.add(titleiconlabel3);

		titlepanel.setLayout(new BorderLayout());
		titlepanel.setBackground(new Color(77, 96, 130));
		titlepanel.setPreferredSize(new Dimension(100, 23));
		titlepanel.setMinimumSize(new Dimension(100, 23));
		titlepanel.add(titlelabel,BorderLayout.WEST);
		titlepanel.add(titleiconlabelpanel,BorderLayout.EAST);

	}

	private void initTreePanel() {
		// TODO Auto-generated method stub
		
		initTimingPanel();
		
		initValidationPanel();
		
		GridBagLayout layout=new GridBagLayout();
		treepanel.setLayout(layout);
		treepanel.add(timingpanel);
		treepanel.add(validationpanel);
		layout.setConstraints(timingpanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(validationpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
	}

	private void initTimingPanel() {
		// TODO Auto-generated method stub
		
		timinglabel.setText("ʱ��ͼ");
		timinglabel.setForeground(new Color(0,0,0));
		timinglabel.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		timinglabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		
		initTimingToolPanel();
		
		timinglabelpanel.setBackground(new Color(207, 214, 229));
		timinglabelpanel.setLayout(new BorderLayout());
		timinglabelpanel.add(timinglabel, BorderLayout.WEST);
		timinglabelpanel.add(timingtoolpanel, BorderLayout.EAST);
		timinglabelpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(142, 155, 188)));
		timinglabelpanel.setPreferredSize(new Dimension(100, 29));
		timinglabelpanel.setMaximumSize(new Dimension(100, 29));
		timinglabelpanel.setMinimumSize(new Dimension(100, 29));
		
		initTimingTablePanel();
		
		for(String str:timinglists){
			Object[] rowData={str};
			timingtablemodel.addRow(rowData);
		}
		
		
		timingpanel.setLayout(new BorderLayout());
		timingpanel.add(timinglabelpanel, BorderLayout.NORTH);
		timingpanel.add(timingscrollpanel,BorderLayout.CENTER);
		
	}

	private void initTimingTablePanel() {
		// TODO Auto-generated method stub
		
		timingtablemodel=new DefaultTableModel(0, 1){
			@Override  
            public boolean isCellEditable(int row,int column){  
                return false;  
            } 
		};
		
		timingtable=new JTable(timingtablemodel);
		
		timingtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		timingtable.setGridColor(Color.BLACK);
		timingtable.setShowGrid(true);
		timingtable.setShowHorizontalLines(true);
		timingtable.setShowVerticalLines(false);
		timingtable.setFillsViewportHeight(true);
		timingtable.setRowHeight(27);
		timingtable.doLayout();
		
		timingtable.getTableHeader().setVisible(false);  
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();  
        renderer.setPreferredSize(new Dimension(0, 0));  
        timingtable.getTableHeader().setDefaultRenderer(renderer);
		
        timingtable.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount()==2){

					String baseUrl = "D:\\ModelDriverProjectFile\\TimingDiagram\\Violet\\";
					String filename=(String) timingtablemodel.getValueAt(timingtable.getSelectedRow(), timingtable.getSelectedColumn());
					String path = baseUrl + filename + ".timing.violet.xml";
					System.out.println("-----path:-----"+path);
					
					showTimingDiagram(path);
					
					showUppaalDiagram(filename);
					
					mainFrame.getStepSixCenterTabbedPane().ChangeRepaint();
					
				}
			}
        	
		});
		
		timingtablepanel.setLayout(new GridLayout());
		timingtablepanel.add(timingtable);
		timingtable.setBackground(new Color(238, 238, 242));
		timingtable.setBorder(null);
		timingtablepanel.setBorder(null);
		
		timingscrollpanel=new JScrollPane(timingtablepanel);
		timingscrollpanel.setBorder(null);
		timingscrollpanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		timingscrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
	}

	protected void showUppaalDiagram(String filename) {
		// TODO Auto-generated method stub
		
		IWorkspace workspace=mainFrame.getStepTwoCenterTabbedPane().getTimingToUppaalTabbedPane().getTiminganduppaalmap().get(filename);
		
		mainFrame.getStepSixCenterTabbedPane().getDiagramPanel().removeAll();
		mainFrame.getStepSixCenterTabbedPane().getUppaalDiagramTabbedPane().removeAll();
		
		UppaalToolPanel toolPanel = new UppaalToolPanel(mainFrame,workspace);

		MoviePanel moviePanel = new MoviePanel();

		GridBagLayout layout = new GridBagLayout();
		mainFrame.getStepSixCenterTabbedPane().getUppaalDiagramTabbedPane().setLayout(layout);
		mainFrame.getStepSixCenterTabbedPane().getUppaalDiagramTabbedPane().add(toolPanel);
		mainFrame.getStepSixCenterTabbedPane().getUppaalDiagramTabbedPane().add(moviePanel);
		mainFrame.getStepSixCenterTabbedPane().getUppaalDiagramTabbedPane().add(workspace.getAWTComponent());
		layout.setConstraints(toolPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(moviePanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(workspace.getAWTComponent(),new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		
	}

	protected void showTimingDiagram(String path) {
		// TODO Auto-generated method stub
		
		File file=new File(path);
		IFile selectedFile=null;
		try {
			selectedFile = new LocalFile(file);
			boolean flag = !(selectedFile.getFilename().contains("EA"));// ��EA��ʽ���ļ�

			// �����ƽ̨�����XML�ļ�
			IGraphFile graphFile = null;
			// //����ת���ķ���11

			selectedFile = FileMenu.exchangeFile(selectedFile, graphFile, flag);

			graphFile = new GraphFile(selectedFile);

			// ��ʾ�ļ�ͼ��
			IWorkspace workspace = new Workspace(graphFile);
			
			mainFrame.getStepSixCenterTabbedPane().getDiagramPanel().removeAll();
			mainFrame.getStepSixCenterTabbedPane().getTimingDiagramTabbedPane().removeAll();
			
			ToolPanel toolPanel = new ToolPanel(mainFrame,workspace);
			
			MoviePanel moviePanel = new MoviePanel();

			GridBagLayout layout = new GridBagLayout();
			mainFrame.getStepSixCenterTabbedPane().getTimingDiagramTabbedPane().setLayout(layout);
			mainFrame.getStepSixCenterTabbedPane().getTimingDiagramTabbedPane().add(toolPanel);
			mainFrame.getStepSixCenterTabbedPane().getTimingDiagramTabbedPane().add(moviePanel);
			mainFrame.getStepSixCenterTabbedPane().getTimingDiagramTabbedPane().add(workspace.getAWTComponent());
			layout.setConstraints(toolPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
			layout.setConstraints(moviePanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
			layout.setConstraints(workspace.getAWTComponent(),new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
			
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	private void initTimingToolPanel() {
		// TODO Auto-generated method stub
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "dropdown.png");
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));

		timingtoolbutton1.setIcon(icon1);
		timingtoolbutton1.setFocusable(false);
		timingtoolbutton1.setContentAreaFilled(false);
		timingtoolbutton1.setBorderPainted(false);
		timingtoolbutton1.addMouseListener(new ButtonMouseListener());
		timingtoolbutton1.setPreferredSize(new Dimension(21,21));
		timingtoolbutton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});

		timingtoolpanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,4));
		timingtoolpanel.setOpaque(false);
		timingtoolpanel.add(timingtoolbutton1);
		
	}

	private void initValidationPanel() {
		// TODO Auto-generated method stub
		
		validationlabel.setText("һ������֤");
		validationlabel.setForeground(new Color(0,0,0));
		validationlabel.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		validationlabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		
		initValidationToolPanel();
		
		validationlabelpanel.setBackground(new Color(207, 214, 229));
		validationlabelpanel.setLayout(new BorderLayout());
		validationlabelpanel.add(validationlabel, BorderLayout.WEST);
		validationlabelpanel.add(validationtoolpanel, BorderLayout.EAST);
		validationlabelpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(142, 155, 188)));
		validationlabelpanel.setPreferredSize(new Dimension(100, 29));
		validationlabelpanel.setMaximumSize(new Dimension(100, 29));
		validationlabelpanel.setMinimumSize(new Dimension(100, 29));
		
		validationpanel.setLayout(new BorderLayout());
		validationpanel.add(validationlabelpanel, BorderLayout.NORTH);

		
	}

	private void initValidationToolPanel() {
		// TODO Auto-generated method stub
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "allselect.png");
		icon1.setImage(icon1.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(path + "dropdown.png");
		icon2.setImage(icon2.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));

		validationtoolbutton1.setIcon(icon1);
		validationtoolbutton1.setFocusable(false);
		validationtoolbutton1.setContentAreaFilled(false);
		validationtoolbutton1.setBorderPainted(false);
		validationtoolbutton1.addMouseListener(new ButtonMouseListener());
		validationtoolbutton1.setPreferredSize(new Dimension(21,21));
		validationtoolbutton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				
			}
		});
		
		validationtoolbutton2.setIcon(icon2);
		validationtoolbutton2.setFocusable(false);
		validationtoolbutton2.setContentAreaFilled(false);
		validationtoolbutton2.setBorderPainted(false);
		validationtoolbutton2.addMouseListener(new ButtonMouseListener());
		validationtoolbutton2.setPreferredSize(new Dimension(21,21));
		validationtoolbutton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				
			}
		});
		
		validationtoolpanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,4));
		validationtoolpanel.setOpaque(false);
		validationtoolpanel.add(validationtoolbutton1);
		validationtoolpanel.add(validationtoolbutton2);

		
	}
	
	
	public void initFileList() {
		File[] timingFilelists = getAllFileByDiagramType("timing");
	    for(File timingFile : timingFilelists)
	    {
	    	String fileName=timingFile.getName();
//	    	fileName.substring(0, fileName.lastIndexOf(".xml"));
	    	timinglists.add(fileName.substring(0, fileName.lastIndexOf(".timing.violet.xml")));
	    }
//	    for(File tdFile : tdFilelists)
//	    {
//	    	String fileName=tdFile.getName();
//	    	tdlists.add(fileName);
//	    }
//	    for(File uppaalFile:uppaalFilelists)
//	    {
//	    	String fileName=uppaalFile.getName();
//	    	uppaallists.add(fileName);
//	    }
	}
	/**
	  * �������ͻ�ȡ�ļ����µ������ļ�
	  * @param type
	  * @return
	  */
	 public   File[] getAllFileByDiagramType(String type){
//		 File f =FileSystemView.getFileSystemView().getHomeDirectory();
//		String s =f .getAbsolutePath();
		 String baseUrl ="D://ModelDriverProjectFile";
//		String baseUrl =s+"//ModelDriverProjectFile";
//		File bFile = new File(baseUrl);
//		if(!bFile.exists()){
//			bFile.mkdirs();
//		}
		 File[] fList =null;
		 File file=null;
		 if("senquence".equals(type)){
			 file =new File(baseUrl+"\\SequenceDiagram\\Violet");
			 fList= file.listFiles();
		 }else if("timing".equals(type)){
			file =new File(baseUrl+"\\TimingDiagram\\Violet");
			 fList= file.listFiles();
		 }else if("UPPAAL2".equals(type)){
			 //�ڶ�����UPPAAL�漰���Զ���
			 file =new File(baseUrl+"\\UPPAAL\\2.UML Model Transfer");
			 fList=file.listFiles();
		 }else if("UPPAAL3".equals(type)){
			 //��������UPPAAL�漰���Զ���
			 file =new File(baseUrl+"\\UPPAAL\\3.Abstract TestCase");
			 fList= file.listFiles();
		 }else if("UPPAAL4".equals(type)){
			 //���Ĳ���UPPAAL�漰���Զ���
			 file =new File(baseUrl+"\\UPPAAL\\4.Real TestCase");
			 fList=file.listFiles();
		 }else if("state".equals(type)){
			 file =new File(baseUrl+"\\StateDiagram\\Violet");
			 fList=file.listFiles();
		 }else if("usecase".equals(type)){
			 file =new File(baseUrl+"\\UsecaseDiagram\\Violet");
			 fList= file.listFiles();
		 }else if("class".equals(type)){
			 file =new File(baseUrl+"\\ClassDiagram\\Violet");
			 fList= file.listFiles();
		 }else if("activity".equals(type)){
			 file =new File(baseUrl+"\\ActivityDiagram\\Violet");
			 fList=file.listFiles();
		 }
		 return fList;
	}

	
	
}

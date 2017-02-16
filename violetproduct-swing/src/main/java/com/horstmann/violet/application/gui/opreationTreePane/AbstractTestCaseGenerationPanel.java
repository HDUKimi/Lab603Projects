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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;

public class AbstractTestCaseGenerationPanel extends JPanel {
	
	public  MainFrame mainFrame;

	public DefaultMutableTreeNode abstractuppaalDiagram;
	public DefaultMutableTreeNode uppaalDiagram;
	public DefaultMutableTreeNode alluppaalDiagram;
	public JTree UppaalDiagramTree;
	private String[] abuppaallists;
	private String[] uppaallists;
	
	private JPanel titlepanel;
	private JPanel toolpanel;
	private JPanel treepanel;
	private JPanel diagrampanel;
	
	private JLabel titlelabel;
	private JPanel titleiconlabelpanel;
	private JLabel titleiconlabel1;
	private JLabel titleiconlabel2;
	private JLabel titleiconlabel3;
	
	private JPanel diagrambuttonpanel1;
	private JPanel diagrambuttonpanel2;
	private JPanel diagrambuttonpanel3;
	private JButton diagrambutton1;
	private JButton diagrambutton2;
	private JButton diagrambutton3;
	
	private int index = 1;
	
	private AbstractUppaalPanel abstractUppaalPanel; 
	
//	private JPanel titlepanel;
//	private JPanel treepanel;
//	private JLabel titlelabel;
//	private JSplitPane js;

	public AbstractTestCaseGenerationPanel(MainFrame mainFrame) {
		
		this.mainFrame = mainFrame;
		
		initFileList();
		initUI();
		
//		this.setLayout(new GridLayout(1, 1));
//		
//		titlepanel=new JPanel();
//		treepanel=new JPanel();
//		titlelabel=new JLabel();
//		
//		titlelabel.setText("建立时间自动机文件");
//		titlelabel.setForeground(new Color(255,255,255));
//		
//		titlepanel.setLayout(new FlowLayout(FlowLayout.LEFT));
//		titlepanel.setBackground(new Color(64,66, 68));
//		titlepanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
//		titlepanel.add(titlelabel);
//		
//		treepanel.setLayout(new GridLayout());
//		UppaalDiagramTree.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//		treepanel.add(UppaalDiagramTree);
//		
//		js=new JSplitPane(JSplitPane.VERTICAL_SPLIT,titlepanel,treepanel);
//		js.setDividerLocation(30);
//		js.setDividerSize(1);
//		js.setEnabled(false);
//		
//		this.add(js);
		
		titlepanel = new JPanel();
		toolpanel = new JPanel();
		treepanel = new JPanel();
		diagrampanel = new JPanel();
		
		titlelabel = new JLabel();
		titleiconlabelpanel=new JPanel();
		titleiconlabel1 = new JLabel();
		titleiconlabel2 = new JLabel();
		titleiconlabel3 = new JLabel();
		
		abstractUppaalPanel=new AbstractUppaalPanel(mainFrame);
		
		titlepanel.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(142, 155, 188)));
		toolpanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, new Color(142, 155, 188)));
		treepanel.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(142, 155, 188)));
		
		initTitlePanel();
		
		initToolButton();

		initDiagramButton();
		
		initTreePanel();
		
		GridBagLayout layout=new GridBagLayout();
		this.setLayout(layout);
		this.add(titlepanel);
//		this.add(toolpanel);
		this.add(treepanel);
		this.add(diagrampanel);
		layout.setConstraints(titlepanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
//		layout.setConstraints(toolpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(treepanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(diagrampanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();
		
		this.setMinimumSize(new Dimension(screenWidth/8, screenHeight-150));
		
	}

	private void initTitlePanel() {
		// TODO Auto-generated method stub
		
		titlelabel.setText("模型转换");
		titlelabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
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

	private void initToolButton() {
		// TODO Auto-generated method stub
		
	}

	private void initDiagramButton() {
		// TODO Auto-generated method stub
		
		diagrambuttonpanel1 = new JPanel();
		diagrambuttonpanel2 = new JPanel();
		diagrambuttonpanel3 = new JPanel();
		diagrambutton1 = new JButton();
		diagrambutton2 = new JButton();
		diagrambutton3 = new JButton();

		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "diagrambutton1.png");
		icon1.setImage(icon1.getImage().getScaledInstance(16, 15, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(path + "diagrambutton2.png");
		icon2.setImage(icon2.getImage().getScaledInstance(16,15, Image.SCALE_DEFAULT));
		ImageIcon icon3 = new ImageIcon(path + "diagrambutton3.png");
		icon3.setImage(icon3.getImage().getScaledInstance(16,15, Image.SCALE_DEFAULT));

		diagrambutton1.setIcon(icon1);
		diagrambutton1.setFocusable(false);
		diagrambutton1.setContentAreaFilled(false);
		diagrambutton1.setBorderPainted(false);
		diagrambutton1.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				diagrambuttonpanel1.setBackground(new Color(255,255,255));
//				diagrambuttonpanel1.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(142, 155, 188)));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (index != 1) {
					diagrambuttonpanel1.setBackground(new Color(77, 96, 130));
//					diagrambuttonpanel1.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(142, 155, 188)));
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (index != 1) {
					diagrambuttonpanel1.setBackground(new Color(134, 161, 209));
//					diagrambuttonpanel1.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(142, 155, 188)));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				setdiagrambuttonpanelrepaint();
				diagrambuttonpanel1.setBackground(new Color(255,255,255));
				diagrambuttonpanel1.setBorder(BorderFactory.createMatteBorder(0,1,1,1, new Color(142, 155, 188)));
				diagrambuttonpanel2.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
				diagrambuttonpanel3.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
				index = 1;
				
				treepanel.removeAll();
				treepanel.add(abstractUppaalPanel);
				
				mainFrame.setVisible(false);
				mainFrame.getContentPane().repaint();
				mainFrame.setVisible(true);
				
			}
		});
		diagrambutton1.setPreferredSize(new Dimension(22,22));
		diagrambutton2.setIcon(icon2);
		diagrambutton2.setFocusable(false);
		diagrambutton2.setContentAreaFilled(false);
		diagrambutton2.setBorderPainted(false);
		diagrambutton2.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				diagrambuttonpanel2.setBackground(new Color(255,255,255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (index != 2) {
					diagrambuttonpanel2.setBackground(new Color(77, 96, 130));
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (index != 2) {
					diagrambuttonpanel2.setBackground(new Color(134, 161, 209));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				setdiagrambuttonpanelrepaint();
				diagrambuttonpanel2.setBackground(new Color(255,255,255));
				diagrambuttonpanel2.setBorder(BorderFactory.createMatteBorder(0,1,1,1, new Color(142, 155, 188)));
				diagrambuttonpanel1.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
				diagrambuttonpanel3.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
				index = 2;
				
				mainFrame.setVisible(false);
				mainFrame.getContentPane().repaint();
				mainFrame.setVisible(true);
				
			}
		});
		diagrambutton2.setPreferredSize(new Dimension(22,22));
		diagrambutton3.setIcon(icon3);
		diagrambutton3.setFocusable(false);
		diagrambutton3.setContentAreaFilled(false);
		diagrambutton3.setBorderPainted(false);
		diagrambutton3.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				diagrambuttonpanel3.setBackground(new Color(255,255,255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (index != 3) {
					diagrambuttonpanel3.setBackground(new Color(77, 96, 130));
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (index != 3) {
					diagrambuttonpanel3.setBackground(new Color(134, 161, 209));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				setdiagrambuttonpanelrepaint();
				diagrambuttonpanel3.setBackground(new Color(255,255,255));
				diagrambuttonpanel3.setBorder(BorderFactory.createMatteBorder(0,1,1,1, new Color(142, 155, 188)));
				diagrambuttonpanel1.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
				diagrambuttonpanel2.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
				index = 3;
				
				treepanel.removeAll();
				treepanel.add(UppaalDiagramTree);

				mainFrame.setVisible(false);
				mainFrame.getContentPane().repaint();
				mainFrame.setVisible(true);
				
			}
		});
		diagrambutton3.setPreferredSize(new Dimension(22,22));

		diagrambuttonpanel1.setLayout(new GridLayout());
		diagrambuttonpanel1.setBackground(new Color(255,255,255));
//		diagrambuttonpanel1.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		diagrambuttonpanel1.setBorder(BorderFactory.createMatteBorder(0,1,1,1, new Color(142, 155, 188)));
		diagrambuttonpanel1.add(diagrambutton1);
		diagrambuttonpanel2.setLayout(new GridLayout());
		diagrambuttonpanel2.setBackground(new Color(77, 96, 130));
//		diagrambuttonpanel2.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		diagrambuttonpanel2.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
		diagrambuttonpanel2.add(diagrambutton2);
		diagrambuttonpanel3.setLayout(new GridLayout());
		diagrambuttonpanel3.setBackground(new Color(77, 96, 130));
//		diagrambuttonpanel3.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		diagrambuttonpanel3.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
		diagrambuttonpanel3.add(diagrambutton3);

		diagrampanel.setLayout(new GridLayout(1, 3));
		diagrampanel.setBackground(new Color(77, 96, 130));
		// diagrampanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
		diagrampanel.add(diagrambuttonpanel1);
		diagrampanel.add(diagrambuttonpanel2);
		diagrampanel.add(diagrambuttonpanel3);
		
		diagrampanel.setPreferredSize(new Dimension(100, 22));
		diagrampanel.setMinimumSize(new Dimension(100, 22));
		
	}

	protected void setdiagrambuttonpanelrepaint() {
		// TODO Auto-generated method stub
		
		diagrambuttonpanel1.setBackground(new Color(77, 96, 130));
		diagrambuttonpanel2.setBackground(new Color(77, 96, 130));
		diagrambuttonpanel3.setBackground(new Color(77, 96, 130));
		
	}

	private void initTreePanel() {
		// TODO Auto-generated method stub
		
		setdiagrambuttonpanelrepaint();
		diagrambuttonpanel1.setBackground(new Color(255,255,255));
		diagrambuttonpanel1.setBorder(BorderFactory.createMatteBorder(0,1,1,1, new Color(142, 155, 188)));
		diagrambuttonpanel2.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
		diagrambuttonpanel3.setBorder(BorderFactory.createMatteBorder(1,0,0,0, new Color(142, 155, 188)));
		index = 1;
		treepanel.removeAll();
		treepanel.setLayout(new GridLayout());
		treepanel.add(abstractUppaalPanel);
		
	}

	private void initUI() {
		// TODO Auto-generated method stub

		alluppaalDiagram = new DefaultMutableTreeNode("时间自动机文件");
		uppaalDiagram = new DefaultMutableTreeNode("含有时间迁移的时间自动机文件");
		abstractuppaalDiagram = new DefaultMutableTreeNode("不含有时间迁移的自动机文件");

		for (String ud : uppaallists) {
			uppaalDiagram.add(new DefaultMutableTreeNode(ud));
		}
		for (String ab : abuppaallists) {
			abstractuppaalDiagram.add(new DefaultMutableTreeNode(ab));
		}
		alluppaalDiagram.add(uppaalDiagram);
		alluppaalDiagram.add(abstractuppaalDiagram);
		UppaalDiagramTree = new JTree(alluppaalDiagram);
	}

	private void initFileList() {
		abuppaallists = new String[] { "1.uppaaldiagram", "2.uppaaldiagram", "3.uppaaldiagram" };
		uppaallists = new String[] { "4.uppaaldiagram", "5.uppaaldiagram", "6.uppaaldiagram" };
	}
	
	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}

}

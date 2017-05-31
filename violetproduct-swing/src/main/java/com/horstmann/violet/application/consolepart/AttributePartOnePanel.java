package com.horstmann.violet.application.consolepart;

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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;

public class AttributePartOnePanel  extends JPanel {
	
	private MainFrame mainFrame;
	
	private JPanel titlepanel;
	private JPanel namepanel;
	private JPanel toolpanel;
	private JPanel attributepanel;
//	private JPanel treepanel;
	
	private JLabel titlelabel;
	private JPanel titleiconlabelpanel;
	private JLabel titleiconlabel1;
	private JLabel titleiconlabel2;
	
	private JLabel namelabel;
	
	private JButton toolbutton1;
	private JButton toolbutton2;
	private JPanel emptypanel;
	
	private JTree attributetree=null;
	
	public AttributePartOnePanel(MainFrame mainFrame){
		
		this.mainFrame=mainFrame;
		
		this.setBackground(new Color(255, 255, 255));
		
		init();
		
	}

	private void init() {
		// TODO Auto-generated method stub
		
		titlepanel = new JPanel();
		namepanel = new JPanel();
		toolpanel = new JPanel();
		attributepanel = new JPanel();
		
		titlelabel = new JLabel();
		titleiconlabelpanel=new JPanel();
		titleiconlabel1 = new JLabel();
		titleiconlabel2 = new JLabel();
		
		namelabel = new JLabel();
		
		toolbutton1=new JButton();
		toolbutton2=new JButton();
		
		initTitlePanel();
		
		initNamePanel();
		
		initToolPanel();
		
		attributepanel.setLayout(new GridLayout());
		JScrollPane jspanel=new JScrollPane();
		attributepanel.add(jspanel);
//		textarea.setEditable(false);
//		textarea.setBorder(null);
		jspanel.setBorder(null);
		
		attributepanel.setBackground(new Color(255, 255, 255));
		jspanel.setBackground(new Color(255, 255, 255));
		
//		this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(142, 155, 188)));
//		this.setLayout(new BorderLayout());
//		this.add(titlepanel,BorderLayout.NORTH);
//		this.add(attributepanel,BorderLayout.CENTER);
		
		titlepanel.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(142, 155, 188)));
		namepanel.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(142, 155, 188)));
		toolpanel.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(142, 155, 188)));
		attributepanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(142, 155, 188)));
		
		
		GridBagLayout layout=new GridBagLayout();
		this.setLayout(layout);
		this.add(titlepanel);
		this.add(namepanel);
		this.add(toolpanel);
		this.add(attributepanel);
		layout.setConstraints(titlepanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(namepanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(toolpanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(attributepanel, new GBC(0,3, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();
		
		this.setMinimumSize(new Dimension(screenWidth/7, screenHeight-150));
		this.setMaximumSize(new Dimension(screenWidth/6, screenHeight-150));
		
	}

	private void initNamePanel() {
		// TODO Auto-generated method stub
		
		namelabel.setText("  ");
		namelabel.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 12));
		namelabel.setForeground(new Color(0, 102, 204));
		namelabel.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 0));
		
		namepanel.setLayout(new BorderLayout());
		namepanel.setBackground(new Color(255,255,255));
		namepanel.setPreferredSize(new Dimension(200, 29));
		namepanel.setMinimumSize(new Dimension(100, 29));
		namepanel.add(namelabel,BorderLayout.WEST);
		
	}

	private void initToolPanel() {
		// TODO Auto-generated method stub
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "allexpanded.png");
		icon1.setImage(icon1.getImage().getScaledInstance(9,9, Image.SCALE_DEFAULT));
		ImageIcon icon2 = new ImageIcon(path + "allcollapsed.png");
		icon2.setImage(icon2.getImage().getScaledInstance(9,9, Image.SCALE_DEFAULT));

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
				
				if(attributetree!=null){
					TreeNode node = (TreeNode) attributetree.getModel().getRoot();
			        expandAll(attributetree, new TreePath(node), true);
			        ChangeRepaint();
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
				
				if(attributetree!=null){
					TreeNode node = (TreeNode) attributetree.getModel().getRoot();
					TreePath tp=new TreePath(node);
			        expandAll(attributetree, tp.pathByAddingChild(node.getChildAt(0)), false);
			        ChangeRepaint();
				}
		        
			}
		});

		emptypanel=new JPanel();
		emptypanel.setPreferredSize(new Dimension(16, 23));
		emptypanel.setBackground(new Color(133,145,162));
		emptypanel.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 10, new Color(207, 214, 229)));
		
		toolpanel.setBackground(new Color(207, 214, 229));
		toolpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 2,2));
		
		toolpanel.add(toolbutton1);
		toolpanel.add(toolbutton2);
		toolpanel.add(emptypanel);
		
		toolpanel.setPreferredSize(new Dimension(100, 27));
		toolpanel.setMaximumSize(new Dimension(100, 27));
		toolpanel.setMinimumSize(new Dimension(100, 27));
		
	}

	protected void expandAll(JTree tree, TreePath parent, boolean expand) {
		// TODO Auto-generated method stub
		
		TreeNode node = (TreeNode) parent.getLastPathComponent();

        if (node.getChildCount() > 0) {
            for (Enumeration e = node.children(); e.hasMoreElements();) {
                TreeNode n = (TreeNode) e.nextElement();
                TreePath path = parent.pathByAddingChild(n);
                expandAll(tree, path, expand);
            }
        }
        if (expand) {
            tree.expandPath(parent);
        } else {
            tree.collapsePath(parent);
        }
		
	}

	private void initTitlePanel() {
		// TODO Auto-generated method stub
		titlelabel.setText(" Ù–‘-xml");
		titlelabel.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 12));
		titlelabel.setForeground(new Color(255, 255, 255));
		titlelabel.setBorder(BorderFactory.createEmptyBorder(1, 8, 1, 0));
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "downarrow.png");
		icon1.setImage(icon1.getImage().getScaledInstance(7,11, Image.SCALE_DEFAULT));
		titleiconlabel1.setIcon(icon1);
		titleiconlabel1.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 6));
		ImageIcon icon2 = new ImageIcon(path + "fork.png");
		icon2.setImage(icon2.getImage().getScaledInstance(10,8, Image.SCALE_DEFAULT));
		titleiconlabel2.setIcon(icon2);
		titleiconlabel2.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		
		titleiconlabel2.addMouseListener(new MouseListener() {
			
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
				
				mainFrame.getJs2().setDividerSize(0);
				mainFrame.getAttributePart().setVisible(false);
				mainFrame.getOneTouchExpandablePanel().setFlag3(0);
				mainFrame.getOneTouchExpandablePanel().setLocation3(mainFrame.getJs2().getDividerLocation());
				
			}
		});
		
		titleiconlabelpanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		titleiconlabelpanel.setOpaque(false);
		titleiconlabelpanel.add(titleiconlabel1);
		titleiconlabelpanel.add(titleiconlabel2);

		titlepanel.setLayout(new BorderLayout());
		titlepanel.setBackground(new Color(77, 96, 130));
//		titlepanel.setBorder(BorderFactory.createEmptyBorder(1, 10, 0, 0));
		titlepanel.setPreferredSize(new Dimension(100, 23));
		titlepanel.setMinimumSize(new Dimension(100, 23));
		titlepanel.add(titlelabel,BorderLayout.WEST);
		titlepanel.add(titleiconlabelpanel,BorderLayout.EAST);
	}
	
	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}

	public JPanel getAttributepanel() {
		return attributepanel;
	}

	public void setAttributepanel(JPanel attributepanel) {
		this.attributepanel = attributepanel;
	}

	public JTree getAttributetree() {
		return attributetree;
	}

	public void setAttributetree(JTree attributetree) {
		this.attributetree = attributetree;
	}

	public JPanel getNamepanel() {
		return namepanel;
	}
	
	public void setNamelabel(JLabel namelabel) {
		this.namelabel = namelabel;
	}
	
	public JLabel getNamelabel() {
		return namelabel;
	}

}

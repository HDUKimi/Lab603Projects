package com.horstmann.violet.application.consolepart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;

public class TestCaseInstantiationResultPanel extends JPanel {

	private MainFrame mainFrame;

	private JPanel titlepanel;

	private JPanel resultpanel;

	private JLabel titlelabel;
	private JPanel titleiconlabelpanel;
	private JLabel titleiconlabel1;
	private JLabel titleiconlabel2;

	private JPanel testcaselabeltabpanel;
	private JPanel testcaselabeltabpanel1;
	private JPanel testcaselabeltabpanel2;
	private JButton testcaselabeltab1;
	private JButton testcaselabeltab2;
	
	private int testcaselabeltabindex=1;
	
	
	private JPanel onetestcaseresultpanel;
	private JPanel onenamepanel;
	private JLabel onenamelabel;
	private JPanel oneresultpanel;
	private JScrollPane oneresultscrollpanel;
	
	private JTable testcaseinfortable;
	private DefaultTableModel testcaseinfortablemodel;
	
	private JPanel twotestcaseresultpanel;
	private JPanel twonamepanel;
	private JLabel twonamelabel;
	private JPanel tworesultpanel;
	private JScrollPane tworesultscrollpanel;
	
	public TestCaseInstantiationResultPanel(MainFrame mainFrame){
		
		this.mainFrame=mainFrame;
		
		this.setBackground(new Color(255, 255, 255));
		
		init();
		
	}

	private void init() {
		// TODO Auto-generated method stub

		titlepanel = new JPanel();
		resultpanel = new JPanel();

		titlelabel = new JLabel();
		titleiconlabelpanel = new JPanel();
		titleiconlabel1 = new JLabel();
		titleiconlabel2 = new JLabel();
		
		initOneTestCaseResultPanel();

		initTwoTestCaseResultPanel();
		
		initTitlePanel();

		initTabPanel();
		
		initResulePanel();

		titlepanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(142, 155, 188)));
		testcaselabeltabpanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, new Color(142, 155, 188)));
		resultpanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(142, 155, 188)));

		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(titlepanel);
		this.add(testcaselabeltabpanel);
		this.add(resultpanel);
		layout.setConstraints(titlepanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(testcaselabeltabpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(resultpanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();

		this.setMinimumSize(new Dimension(screenWidth / 7, screenHeight - 150));
		this.setMaximumSize(new Dimension(screenWidth / 6, screenHeight - 150));
		
		
	}
	
	private void initOneTestCaseResultPanel() {
		// TODO Auto-generated method stub
		
		onetestcaseresultpanel=new JPanel();
		
		onenamepanel=new JPanel();
		onenamelabel=new JLabel();
		oneresultpanel=new JPanel();
		
		onenamepanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(142, 155, 188)));
		
		initOneNamePanel();
		
		initOneResulePanel();
		
		GridBagLayout layout=new GridBagLayout();
		onetestcaseresultpanel.setLayout(layout);
		onetestcaseresultpanel.add(onenamepanel);
		onetestcaseresultpanel.add(oneresultscrollpanel);
		layout.setConstraints(onenamepanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(oneresultscrollpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		
	}

	private void initOneResulePanel() {
		// TODO Auto-generated method stub
		
		initTestCaseInforTable();
		
		oneresultpanel.setLayout(new GridLayout());
		oneresultpanel.add(testcaseinfortable);

		oneresultpanel.setBackground(new Color(255, 255, 255));
		
		oneresultscrollpanel=new JScrollPane(oneresultpanel);
		oneresultscrollpanel.setBorder(null);
		
	}

	private void initTestCaseInforTable() {
		// TODO Auto-generated method stub
		
		String[] columnNames={""};
		String[][] tabelValues={};
		
		testcaseinfortablemodel=new DefaultTableModel(tabelValues, columnNames){
			@Override  
            public boolean isCellEditable(int row,int column){  
                return false;  
            } 
		};
		
		testcaseinfortable=new JTable(testcaseinfortablemodel);
		
		testcaseinfortable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        testcaseinfortable.setSelectionBackground(new Color(250, 248, 236));
        testcaseinfortable.setGridColor(new Color(224, 226, 220));
		testcaseinfortable.setShowGrid(true);
		testcaseinfortable.setShowHorizontalLines(true);
		testcaseinfortable.setShowVerticalLines(false);
		testcaseinfortable.setFillsViewportHeight(true);
		testcaseinfortable.setRowHeight(27);
		testcaseinfortable.doLayout();
		testcaseinfortable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		testcaseinfortable.getColumnModel().getColumn(0).setCellRenderer(new TestCaseInforLabelRenderer());
		
//		DefaultTableCellRenderer renderer1 = new DefaultTableCellRenderer() {
//
//			@Override
//			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
//					boolean hasFocus, int row, int column) {
//				// TODO Auto-generated method stub
//
//				if(row==0||row==2||row==4||row==6||row==8){
//					setBackground(new Color(71, 80, 93));
//			        setForeground(new Color(255, 255, 255));
//				}
//				else{
//					setForeground(new Color(115, 110, 102));
//			        setBackground(new Color(255, 255, 255));
//				}
//				
//				setFont(new Font("微软雅黑", Font.PLAIN, 12));
//				setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
//
//				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//			}
//
//		};
//		testcaseinfortable.setDefaultRenderer(Object.class, renderer1);
		
		testcaseinfortable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(224, 225, 220)));
        testcaseinfortable.setBackground(new Color(255, 255, 255));
		
	}

	private void initOneNamePanel() {
		// TODO Auto-generated method stub
		
		onenamelabel.setText("  ");
		onenamelabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		onenamelabel.setForeground(new Color(0, 102, 204));
		onenamelabel.setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 0));
		
		onenamepanel.setLayout(new BorderLayout());
		onenamepanel.setBackground(new Color(255,255,255));
		onenamepanel.setPreferredSize(new Dimension(200, 25));
		onenamepanel.setMinimumSize(new Dimension(100, 25));
		onenamepanel.add(onenamelabel,BorderLayout.WEST);
		
	}
	
	private void initTwoTestCaseResultPanel() {
		// TODO Auto-generated method stub
		
		twotestcaseresultpanel=new JPanel();
		
		twonamepanel=new JPanel();
		twonamelabel=new JLabel();
		tworesultpanel=new JPanel();
		
		twonamepanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(142, 155, 188)));
		
		initTwoNamePanel();
		
		initTwoResulePanel();
		
		GridBagLayout layout=new GridBagLayout();
		twotestcaseresultpanel.setLayout(layout);
		twotestcaseresultpanel.add(twonamepanel);
		twotestcaseresultpanel.add(tworesultscrollpanel);
		layout.setConstraints(twonamepanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(tworesultscrollpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		
	}

	private void initTwoResulePanel() {
		// TODO Auto-generated method stub
		
//		initTestCaseInequalitySolveProcess();
		
		tworesultpanel.setLayout(new GridLayout());

		tworesultpanel.setBackground(new Color(255, 255, 255));
		
		tworesultscrollpanel=new JScrollPane(tworesultpanel);
		tworesultscrollpanel.setBorder(null);
		
	}


	private void initTwoNamePanel() {
		// TODO Auto-generated method stub
		
		twonamelabel.setText("实例化中不等式求解过程");
		twonamelabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		twonamelabel.setForeground(new Color(0, 102, 204));
		twonamelabel.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 0));
		
		twonamepanel.setLayout(new BorderLayout());
		twonamepanel.setBackground(new Color(255,255,255));
		twonamepanel.setPreferredSize(new Dimension(200, 25));
		twonamepanel.setMinimumSize(new Dimension(100, 25));
		twonamepanel.add(twonamelabel,BorderLayout.WEST);
		
	}
	
	private void initTitlePanel() {
		// TODO Auto-generated method stub

		titlelabel.setText("实例化结果");
		titlelabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		titlelabel.setForeground(new Color(255, 255, 255));
		titlelabel.setBorder(BorderFactory.createEmptyBorder(1, 8, 1, 0));

		String absolutePath = System.getProperty("user.dir");
		String path = absolutePath + "\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("ImagePart/downarrow.png"));
		icon1.setImage(icon1.getImage().getScaledInstance(7, 11, Image.SCALE_DEFAULT));
		titleiconlabel1.setIcon(icon1);
		titleiconlabel1.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 6));
		ImageIcon icon2 = new ImageIcon(this.getClass().getResource("ImagePart/fork.png"));
		icon2.setImage(icon2.getImage().getScaledInstance(10, 8, Image.SCALE_DEFAULT));
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

		titleiconlabelpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		titleiconlabelpanel.setOpaque(false);
		titleiconlabelpanel.add(titleiconlabel1);
		titleiconlabelpanel.add(titleiconlabel2);

		titlepanel.setLayout(new BorderLayout());
		titlepanel.setBackground(new Color(77, 96, 130));
		titlepanel.setPreferredSize(new Dimension(100, 24));
		titlepanel.setMinimumSize(new Dimension(100, 24));
		titlepanel.add(titlelabel, BorderLayout.WEST);
		titlepanel.add(titleiconlabelpanel, BorderLayout.EAST);
		
	}
	
	private void initTabPanel() {
		// TODO Auto-generated method stub
		
		testcaselabeltabpanel=new JPanel();
		testcaselabeltabpanel1=new JPanel();
		testcaselabeltabpanel2=new JPanel();
		
		testcaselabeltab1=new JButton();
		testcaselabeltab2=new JButton();
		
		testcaselabeltab1.setText("详细信息");
		testcaselabeltab1.setForeground(new Color(0,0,0));
		testcaselabeltab1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		testcaselabeltab1.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));
		testcaselabeltab1.setFocusable(false);
		testcaselabeltab1.setContentAreaFilled(false);
		testcaselabeltab1.setBorderPainted(false);
		testcaselabeltab1.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				testcaselabeltab1.setForeground(new Color(0, 0, 0));
				testcaselabeltabpanel1.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (testcaselabeltabindex != 1) {
					testcaselabeltab1.setForeground(new Color(255, 255, 255));
					testcaselabeltabpanel1.setBackground(new Color(77, 96, 130));
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (testcaselabeltabindex != 1) {
					testcaselabeltabpanel1.setBackground(new Color(134, 161, 209));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				settestcaselabeltabpanelrepait();
				testcaselabeltab1.setForeground(new Color(0, 0, 0));
				testcaselabeltabpanel1.setBackground(new Color(255, 255, 255));
				testcaselabeltabpanel1.setBorder(BorderFactory.createMatteBorder(0,0,0,1, new Color(142, 155, 188)));
				testcaselabeltabindex = 1;
				
				resultpanel.removeAll();
				resultpanel.add(onetestcaseresultpanel);
				
				ChangeRepaint();
				
			}
		});
		testcaselabeltab1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				settestcaselabeltabpanelrepait();
				testcaselabeltab1.setForeground(new Color(0, 0, 0));
				testcaselabeltabpanel1.setBackground(new Color(255, 255, 255));
				testcaselabeltabpanel1.setBorder(BorderFactory.createMatteBorder(0,0,0,1, new Color(142, 155, 188)));
				testcaselabeltabindex = 1;
				
				resultpanel.removeAll();
				resultpanel.add(onetestcaseresultpanel);
				
				ChangeRepaint();
			}
		});
		
		
		testcaselabeltab2.setText("求解信息");
		testcaselabeltab2.setForeground(new Color(255, 255, 255));
		testcaselabeltab2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		testcaselabeltab2.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));
		testcaselabeltab2.setFocusable(false);
		testcaselabeltab2.setContentAreaFilled(false);
		testcaselabeltab2.setBorderPainted(false);
		testcaselabeltab2.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				testcaselabeltab2.setForeground(new Color(0, 0, 0));
				testcaselabeltabpanel2.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (testcaselabeltabindex != 2) {
					testcaselabeltab2.setForeground(new Color(255, 255, 255));
					testcaselabeltabpanel2.setBackground(new Color(77, 96, 130));
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (testcaselabeltabindex != 2) {
					testcaselabeltabpanel2.setBackground(new Color(134, 161, 209));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				settestcaselabeltabpanelrepait();
				testcaselabeltab2.setForeground(new Color(0, 0, 0));
				testcaselabeltabpanel2.setBackground(new Color(255, 255, 255));
				testcaselabeltabpanel2.setBorder(BorderFactory.createMatteBorder(0,0,0,1, new Color(142, 155, 188)));
				testcaselabeltabindex = 2;
				
				resultpanel.removeAll();
				resultpanel.add(twotestcaseresultpanel);
				
				ChangeRepaint();
				
			}
		});
		testcaselabeltab2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				settestcaselabeltabpanelrepait();
				testcaselabeltab2.setForeground(new Color(0, 0, 0));
				testcaselabeltabpanel2.setBackground(new Color(255, 255, 255));
				testcaselabeltabpanel2.setBorder(BorderFactory.createMatteBorder(0,0,0,1, new Color(142, 155, 188)));
				testcaselabeltabindex = 2;
				
				resultpanel.removeAll();
				resultpanel.add(twotestcaseresultpanel);
				
				ChangeRepaint();
			}
		});
		
		testcaselabeltabpanel1.setLayout(new GridLayout());
		testcaselabeltabpanel1.setBackground(new Color(255,255,255));
		testcaselabeltabpanel1.setBorder(BorderFactory.createMatteBorder(0,0,0,1, new Color(142, 155, 188)));
		testcaselabeltabpanel1.setPreferredSize(new Dimension(60, 30));
		testcaselabeltabpanel1.add(testcaselabeltab1);
		testcaselabeltabpanel2.setLayout(new GridLayout());
		testcaselabeltabpanel2.setBackground(new Color(77, 96, 130));
		testcaselabeltabpanel2.setPreferredSize(new Dimension(60, 30));
		testcaselabeltabpanel2.add(testcaselabeltab2);

		testcaselabeltabpanel.setBackground(new Color(41, 57, 85));
		testcaselabeltabpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		testcaselabeltabpanel.add(testcaselabeltabpanel1);
//		testcaselabeltabpanel.add(testcaselabeltabpanel2);
		
		testcaselabeltabpanel.setPreferredSize(new Dimension(100, 30));
		testcaselabeltabpanel.setMinimumSize(new Dimension(100, 30));
		
	}
	
	protected void settestcaselabeltabpanelrepait() {
		// TODO Auto-generated method stub
		
		testcaselabeltab1.setForeground(new Color(255, 255, 255));
		testcaselabeltabpanel1.setBackground(new Color(77, 96, 130));
		testcaselabeltabpanel1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(77, 96, 130)));
		testcaselabeltab2.setForeground(new Color(255, 255, 255));
		testcaselabeltabpanel2.setBackground(new Color(77, 96, 130));
		testcaselabeltabpanel2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(77, 96, 130)));

	}

	private void initResulePanel() {
		// TODO Auto-generated method stub

		resultpanel.setLayout(new GridLayout());
		resultpanel.setOpaque(false);

		resultpanel.add(onetestcaseresultpanel);
		
	}

	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}

	public int getTestcaselabeltabindex() {
		return testcaselabeltabindex;
	}

	public JLabel getOnenamelabel() {
		return onenamelabel;
	}

	public JTable getTestcaseinfortable() {
		return testcaseinfortable;
	}

	public DefaultTableModel getTestcaseinfortablemodel() {
		return testcaseinfortablemodel;
	}

	public JPanel getTwotestcaseresultpanel() {
		return twotestcaseresultpanel;
	}

	public JLabel getTwonamelabel() {
		return twonamelabel;
	}

	public JPanel getTworesultpanel() {
		return tworesultpanel;
	}

	public JButton getTestcaselabeltab1() {
		return testcaselabeltab1;
	}

	public JButton getTestcaselabeltab2() {
		return testcaselabeltab2;
	}
	
}

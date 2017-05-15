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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.horstmann.violet.application.gui.ButtonMouseListener;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.framework.dialog.DialogFactory;
import com.horstmann.violet.framework.file.chooser.JFileChooserService;
import com.horstmann.violet.framework.file.naming.ExtensionFilter;
import com.horstmann.violet.framework.file.persistence.IFileWriter;
import com.horstmann.violet.framework.file.persistence.JFileWriter;

public class ConsolePartPanel extends JPanel {

	private MainFrame mainFrame;
	
	private JPanel titlepanel;
	private JPanel textpanel;
	private JPanel toolpanel;
	private JLabel titlelabel;
	private JTextArea textarea;
	
	private JScrollPane textscrollpanel;
	
	private JPanel titleiconlabelpanel;
	private JLabel titleiconlabel1;
	private JLabel titleiconlabel2;
	private JLabel titleiconlabel3;
	private JLabel titleiconlabel4;
	private JLabel titleiconlabel5;
	private JButton titletoolbutton1;
	private JButton titletoolbutton2;

	private JPanel toollabelpanel1;
	private JPanel toollabelpanel2;
	private JPanel toollabelpanel3;
	private JLabel toollabel1;
	private JLabel toollabel2;
	private JLabel toollabel3;

	private JSplitPane js;

	private int index = 1;
	
	private DialogFactory dialogFactory;
	private File currentDirectory;

	public ConsolePartPanel(MainFrame mainFrame) {

		this.mainFrame=mainFrame;
		
		init();

	}

	private void init() {
		// TODO Auto-generated method stub
		titlepanel = new JPanel();
		textpanel = new JPanel();
		toolpanel = new JPanel();
		titlelabel = new JLabel();
		textarea = new JTextArea();
		titleiconlabelpanel=new JPanel();
		titleiconlabel1 = new JLabel();
		titleiconlabel2 = new JLabel();
		titleiconlabel3 = new JLabel();
		titleiconlabel4 = new JLabel();
		titleiconlabel5 = new JLabel();
		
		titletoolbutton1=new JButton();
		titletoolbutton2=new JButton();

		initTitlePanel();
		
		textpanel.setLayout(new GridLayout());
		textscrollpanel=new JScrollPane(textarea);
		textpanel.add(textscrollpanel);
		textarea.setEditable(false);
		textarea.setBorder(null);
		textscrollpanel.setBorder(null);

		initToolButton();

//		this.setLayout(new BorderLayout());
//
//		js = new JSplitPane(JSplitPane.VERTICAL_SPLIT, titlepanel, textpanel);
//		js.setDividerLocation(23);
//		js.setDividerSize(1);
//		js.setEnabled(false);
//		js.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(142, 155, 188)));
//
//		this.add(js, BorderLayout.CENTER);
//		this.add(toolpanel, BorderLayout.SOUTH);
		
		titlepanel.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(142, 155, 188)));
		textpanel.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, new Color(142, 155, 188)));
		
		
		GridBagLayout layout=new GridBagLayout();
		this.setLayout(layout);
		this.add(titlepanel);
		this.add(textpanel);
		this.add(toolpanel);
		layout.setConstraints(titlepanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(textpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(toolpanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();
		
		this.setMinimumSize(new Dimension(screenWidth*2/5, screenHeight/5));
		
	}

	private void initTitlePanel() {
		// TODO Auto-generated method stub
		
		// titlelabel.setText("UML模型建立过程信息");
		titlelabel.setForeground(new Color(0, 0, 0));
		titlelabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		titlelabel.setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 0));

		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "ytriangulararrow.png");
		icon1.setImage(icon1.getImage().getScaledInstance(8, 4, Image.SCALE_DEFAULT));
		titleiconlabel1.setIcon(icon1);
		titleiconlabel1.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 6));
		ImageIcon icon2 = new ImageIcon(path + "ydownarrow.png");
		icon2.setImage(icon2.getImage().getScaledInstance(7, 11, Image.SCALE_DEFAULT));
		titleiconlabel2.setIcon(icon2);
		titleiconlabel2.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		ImageIcon icon3 = new ImageIcon(path + "yfork.png");
		icon3.setImage(icon3.getImage().getScaledInstance(10, 8, Image.SCALE_DEFAULT));
		titleiconlabel3.setIcon(icon3);
		titleiconlabel3.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		ImageIcon icon4 = new ImageIcon(path + "remove.png");
		icon4.setImage(icon4.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		titleiconlabel4.setIcon(icon4);
		ImageIcon icon5 = new ImageIcon(path + "export.png");
		icon5.setImage(icon5.getImage().getScaledInstance(16,16, Image.SCALE_DEFAULT));
		titleiconlabel5.setIcon(icon5);
		
		titleiconlabel3.addMouseListener(new MouseListener() {
			
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
				
				mainFrame.getJs3().setDividerSize(0);
				mainFrame.getConsolePartPanel().setVisible(false);
				mainFrame.getOneTouchExpandablePanel().setFlag2(0);
				mainFrame.getOneTouchExpandablePanel().setLocation2(mainFrame.getJs3().getDividerLocation());
				
			}
		});

		titleiconlabel4.addMouseListener(new MouseListener() {
			
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
				
				getTextarea().setText("");
				
			}
		});
		
		titleiconlabel5.addMouseListener(new MouseListener() {
			
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
				
//				System.out.println("---------");
//				System.out.println(getTextarea().getText());
				
//				JFileChooser fc = new JFileChooser();
//				fc.setDialogType(JFileChooser.FILES_ONLY);
//				fc.setDialogTitle("选择文件");
//				fc.setMultiSelectionEnabled(false);
//				fc.showSaveDialog(fc);
//
//				System.out.println(fc.getSelectedFile().getAbsolutePath());
				
//				JFileChooserService jf=new JFileChooserService();
//				jf.chooseAndGetFileWriter(filters);
				
				saveFile();
				
				
//				System.out.println("---------");
				
			}
		});
		
		
		titletoolbutton1.setIcon(icon4);
		titletoolbutton1.setFocusable(false);
		titletoolbutton1.setContentAreaFilled(false);
		titletoolbutton1.setBorderPainted(false);
		titletoolbutton1.addMouseListener(new ButtonMouseListener());
		titletoolbutton1.setPreferredSize(new Dimension(21,21));
		titletoolbutton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				getTextarea().setText("");
			}
		});
		
		titletoolbutton2.setIcon(icon5);
		titletoolbutton2.setFocusable(false);
		titletoolbutton2.setContentAreaFilled(false);
		titletoolbutton2.setBorderPainted(false);
		titletoolbutton2.addMouseListener(new ButtonMouseListener());
		titletoolbutton2.setPreferredSize(new Dimension(21,21));
		titletoolbutton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				saveFile();
			}
		});
		
		
		titleiconlabelpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		titleiconlabelpanel.setOpaque(false);
		titleiconlabelpanel.add(titletoolbutton1);
		titleiconlabelpanel.add(titletoolbutton2);
		titleiconlabelpanel.add(titleiconlabel1);
		titleiconlabelpanel.add(titleiconlabel2);
		titleiconlabelpanel.add(titleiconlabel3);

		titlepanel.setLayout(new BorderLayout());
		titlepanel.setBackground(new Color(255, 242, 157));
		titlepanel.setPreferredSize(new Dimension(200, 23));
		titlepanel.setMinimumSize(new Dimension(200, 23));
//		titlepanel.setBorder(BorderFactory.createEmptyBorder(2, 10, 0, 0));
		titlepanel.add(titlelabel, BorderLayout.WEST);
		titlepanel.add(titleiconlabelpanel, BorderLayout.EAST);

	}

	protected void saveFile() {
		// TODO Auto-generated method stub
		
		File localfile = new File("D://ModelDriverProjectFile");
		JFileChooser fileChooser = new JFileChooser(localfile);// 初始化一个文件路劲选择框
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setCurrentDirectory(localfile);
//		fileChooser.setAcceptAllFileFilterUsed(false);
//		fileChooser.showSaveDialog(fileChooser);
		
		FileFilter filter=new FileNameExtensionFilter("文本文件(txt)", "TXT");
		fileChooser.setFileFilter(filter);
		
		int i=fileChooser.showOpenDialog(fileChooser);
		if(i==JFileChooser.APPROVE_OPTION){
			
//			System.out.println(fileChooser.getSelectedFile().getAbsolutePath());
	        
			String filepath=fileChooser.getSelectedFile().getAbsolutePath();
			
			if(!filepath.endsWith(".txt")&&!filepath.endsWith(".TXT")){
				filepath+=".txt";
			}
			
//			System.out.println(filepath);
			
			File file=new File(filepath);
			
			try {
				FileWriter out=new FileWriter(file);
				out.write(getTextarea().getText());
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
//		JFileChooser fc = new JFileChooser();
//		fc.setDialogType(JFileChooser.FILES_ONLY);
//		fc.setDialogTitle("选择文件");
//		fc.setMultiSelectionEnabled(false);
//		fc.showSaveDialog(fc);
//
//		System.out.println(fc.getSelectedFile().getAbsolutePath());
		
	}

	private void initToolButton() {
		// TODO Auto-generated method stub

		toollabelpanel1 = new JPanel();
		toollabelpanel2 = new JPanel();
		toollabelpanel3 = new JPanel();

		toollabel1 = new JLabel();
		toollabel2 = new JLabel();
		toollabel3 = new JLabel();

		toollabel1.setText("消息");
		toollabel1.setForeground(new Color(0,0,0));
		toollabel1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		toollabel1.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		toollabel1.setFocusable(false);
		toollabel1.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				toollabel1.setForeground(new Color(0, 0, 0));
				toollabelpanel1.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (index != 1) {
					toollabel1.setForeground(new Color(255, 255, 255));
					toollabelpanel1.setBackground(new Color(77, 96, 130));
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (index != 1) {
					toollabelpanel1.setBackground(new Color(134, 161, 209));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				settoollabelpanelrepait();
				toollabel1.setForeground(new Color(0, 0, 0));
				toollabelpanel1.setBackground(new Color(255, 255, 255));
				toollabelpanel1.setBorder(BorderFactory.createMatteBorder(0,1,1,1, new Color(142, 155, 188)));
				index = 1;

			}
		});
		toollabel2.setText("断点");
		toollabel2.setForeground(new Color(255, 255, 255));
		toollabel2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		toollabel2.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		toollabel2.setFocusable(false);
		toollabel2.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				toollabel2.setForeground(new Color(0, 0, 0));
				toollabelpanel2.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (index != 2) {
					toollabel2.setForeground(new Color(255, 255, 255));
					toollabelpanel2.setBackground(new Color(77, 96, 130));
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (index != 2) {
					toollabelpanel2.setBackground(new Color(134, 161, 209));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				settoollabelpanelrepait();
				toollabel2.setForeground(new Color(0, 0, 0));
				toollabelpanel2.setBackground(new Color(255, 255, 255));
				toollabelpanel2.setBorder(BorderFactory.createMatteBorder(0,1,1,1, new Color(142, 155, 188)));
				index = 2;

			}
		});
		toollabel3.setText("输出");
		toollabel3.setForeground(new Color(255, 255, 255));
		toollabel3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		toollabel3.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		toollabel3.setFocusable(false);
		toollabel3.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				toollabel3.setForeground(new Color(0, 0, 0));
				toollabelpanel3.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (index != 3) {
					toollabel3.setForeground(new Color(255, 255, 255));
					toollabelpanel3.setBackground(new Color(77, 96, 130));
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (index != 3) {
					toollabelpanel3.setBackground(new Color(134, 161, 209));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				settoollabelpanelrepait();
				toollabel3.setForeground(new Color(0, 0, 0));
				toollabelpanel3.setBackground(new Color(255, 255, 255));
				toollabelpanel3.setBorder(BorderFactory.createMatteBorder(0,1,1,1, new Color(142, 155, 188)));
				index = 3;

			}
		});

		toollabelpanel1.setLayout(new GridLayout());
		toollabelpanel1.setBackground(new Color(255,255,255));
		toollabelpanel1.setBorder(BorderFactory.createMatteBorder(0,1,1,1, new Color(142, 155, 188)));
		toollabelpanel1.setPreferredSize(new Dimension(40, 23));
		toollabelpanel1.add(toollabel1);
		toollabelpanel2.setLayout(new GridLayout());
		toollabelpanel2.setBackground(new Color(77, 96, 130));
		toollabelpanel2.setPreferredSize(new Dimension(40, 23));
		toollabelpanel2.add(toollabel2);
		toollabelpanel3.setLayout(new GridLayout());
		toollabelpanel3.setBackground(new Color(77, 96, 130));
		toollabelpanel3.setPreferredSize(new Dimension(40, 23));
		toollabelpanel3.add(toollabel3);

		toolpanel.setBackground(new Color(41, 57, 85));
		toolpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		toolpanel.add(toollabelpanel1);
		toolpanel.add(toollabelpanel2);
		toolpanel.add(toollabelpanel3);
		
		toolpanel.setPreferredSize(new Dimension(150, 22));
		toolpanel.setMinimumSize(new Dimension(150, 22));

	}

	protected void settoollabelpanelrepait() {
		// TODO Auto-generated method stub
		toollabel1.setForeground(new Color(255, 255, 255));
		toollabelpanel1.setBackground(new Color(77, 96, 130));
		toollabelpanel1.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(77, 96, 130)));
		toollabel2.setForeground(new Color(255, 255, 255));
		toollabelpanel2.setBackground(new Color(77, 96, 130));
		toollabelpanel2.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(77, 96, 130)));
		toollabel3.setForeground(new Color(255, 255, 255));
		toollabelpanel3.setBackground(new Color(77, 96, 130));
		toollabelpanel3.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(77, 96, 130)));
	}

	public JLabel getTitlelabel() {
		return titlelabel;
	}

	public void setTitlelabel(JLabel titlelabel) {
		this.titlelabel = titlelabel;
	}

	public JTextArea getTextarea() {
		return textarea;
	}

	public void setTextarea(JTextArea textarea) {
		this.textarea = textarea;
	}
	
	public void setTextscrollpanel(JScrollPane textscrollpanel) {
		this.textscrollpanel = textscrollpanel;
	}

	public JScrollPane getTextscrollpanel() {
		return textscrollpanel;
	}

	public JPanel getTextpanel() {
		return textpanel;
	}

	public void setTextpanel(JPanel textpanel) {
		this.textpanel = textpanel;
	}

}

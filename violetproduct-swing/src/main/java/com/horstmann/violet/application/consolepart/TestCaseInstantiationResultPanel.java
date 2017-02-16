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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

		initTitlePanel();

		initResulePanel();

		titlepanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(142, 155, 188)));
		resultpanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(142, 155, 188)));

		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(titlepanel);
		this.add(resultpanel);
		layout.setConstraints(titlepanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(resultpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int) screenSize.getWidth();
		int screenHeight = (int) screenSize.getHeight();

		this.setMinimumSize(new Dimension(screenWidth / 7, screenHeight - 150));
		this.setMaximumSize(new Dimension(screenWidth / 6, screenHeight - 150));

	}

	private void initTitlePanel() {
		// TODO Auto-generated method stub

		titlelabel.setText("实例化结果");
		titlelabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		titlelabel.setForeground(new Color(255, 255, 255));
		titlelabel.setBorder(BorderFactory.createEmptyBorder(1, 8, 1, 0));

		String absolutePath = System.getProperty("user.dir");
		String path = absolutePath + "\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "downarrow.png");
		icon1.setImage(icon1.getImage().getScaledInstance(7, 11, Image.SCALE_DEFAULT));
		titleiconlabel1.setIcon(icon1);
		titleiconlabel1.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 6));
		ImageIcon icon2 = new ImageIcon(path + "fork.png");
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

	private void initResulePanel() {
		// TODO Auto-generated method stub

		resultpanel.setLayout(new GridLayout());
		// resultpanel.add(onevalidationresultpanel);

	}

	public void ChangeRepaint() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.getRootPane().repaint();
		this.setVisible(true);
	}

}


package com.horstmann.violet.application.gui.homeTabbedPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.menu.FileMenu;
import com.horstmann.violet.application.swingextension.WelcomeButtonUI;
import com.horstmann.violet.framework.injection.bean.ManiocFramework.BeanInjector;
import com.horstmann.violet.framework.injection.bean.ManiocFramework.InjectedBean;
import com.horstmann.violet.framework.injection.resources.ResourceBundleInjector;
import com.horstmann.violet.framework.injection.resources.annotation.ResourceBundleBean;
import com.horstmann.violet.framework.theme.ITheme;
import com.horstmann.violet.framework.theme.ThemeManager;

public class HomePanel extends JPanel
{
	
	private JPanel toppanel;
	private JPanel mainpanel;
	private JPanel leftpanel;
	private JPanel rightpanel;
	private JPanel titlepanel;
	private JLabel titlelabel;
	
	private JPanel titleiconlabelpanel;
	private JLabel titleiconlabel1;
	private JLabel titleiconlabel2;
	
	private JPanel leftlabelpanel1;
	private JPanel leftlabelpanel2;
	
	private JLabel leftlabel1;
	private JLabel leftlabel2;
	
	private JPanel imagelabelpanel1;
	private JPanel imagelabelpanel2;
	private JPanel imagelabelpanel3;
	private JPanel imagelabelpanel4;
	
	private JLabel imagelabel1;
	private JLabel imagelabel2;
	private JLabel imagelabel3;
	private JLabel imagelabel4;
	

    public HomePanel()
    {
        
//        setOpaque(false);//设置背景色为透明
//        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        add(getflowchartPanel());

    	toppanel = new JPanel();
    	mainpanel = new JPanel();
    	leftpanel = new JPanel();
    	rightpanel = new JPanel();
    	titlepanel = new JPanel();
    	titlelabel=new JLabel();
    	
    	titleiconlabelpanel=new JPanel();
    	titleiconlabel1=new JLabel();
    	titleiconlabel2=new JLabel();
    	
    	leftlabelpanel1=new JPanel();
    	leftlabelpanel2=new JPanel();
    	
    	leftlabel1=new JLabel();
    	leftlabel2=new JLabel();
    	
    	imagelabelpanel1=new JPanel();
    	imagelabelpanel2=new JPanel();
    	imagelabelpanel3=new JPanel();
    	imagelabelpanel4=new JPanel();
    	
    	imagelabel1=new JLabel();
    	imagelabel2=new JLabel();
    	imagelabel3=new JLabel();
    	imagelabel4=new JLabel();
    	
    	
    	GridBagLayout layout=new GridBagLayout();
		this.setLayout(layout);
		this.add(toppanel);
		this.add(mainpanel);
		layout.setConstraints(toppanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(mainpanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		inittoppanel();
		
		initmainpanel();
    	
    }

//    public void paint(Graphics g)
//    {
//        Graphics2D g2 = (Graphics2D) g;
//        Paint currentPaint = g2.getPaint();
//        ITheme cLAF = ThemeManager.getInstance().getTheme();
//        GradientPaint paint = new GradientPaint(getWidth() / 2, -getHeight() / 4, cLAF.getWelcomeBackgroundStartColor(),
//                getWidth() / 2, getHeight() + getHeight() / 4, cLAF.getWelcomeBackgroundEndColor());
//        g2.setPaint(paint);
//        g2.fillRect(0, 0, getWidth(), getHeight());
//        g2.setPaint(currentPaint);
//        super.paint(g);
//    }
// 
//
//    private JPanel getflowchartPanel()
//    {
//        if (this.flowchartPanel== null)
//        {
//        	this.flowchartPanel=new JPanel(){
//        	 protected void paintComponent(Graphics g) {
//        		  super.paintComponent(g);
//        		    ImageIcon icon = new ImageIcon("D:"
//        		    		+ "\\项目代码\\violetumleditor-master\\"
//        		    		+ "violetproduct-swing\\src\\site\\"
//        		    		+ "resources\\icons\\flowchart.PNG");
//        		    Image img = icon.getImage();
//        	        setBackground(Color.WHITE);
//        	        if (img != null) {
//        	            int height = img.getHeight(this);
//        	            int width = img.getWidth(this);
//        	  
//        	            if (height != -1 && height > getHeight())
//        	                height = getHeight();
//        	  
//        	            if (width != -1 && width > getWidth())
//        	                width = getWidth();
//        	            int x = (int) (((double) (getWidth() - width)) / 2.0);
//        	            int y = (int) (((double) (getHeight() - height)) / 2.0);
//        	            g.drawImage(img, x, y, width, height, this);
//        	        }        		          		
//        		   }
//        		  };
//        }
//        return this.flowchartPanel;
//    }
//
//    private JPanel getFootTextPanel()
//    {
//        if (this.footTextPanel == null)
//        {
//            this.footTextPanel = new JPanel();
//            this.footTextPanel.setOpaque(false);
//            this.footTextPanel.setBorder(new EmptyBorder(0, 0, 10, 0));
//            this.footTextPanel.setLayout(new BoxLayout(this.footTextPanel, BoxLayout.Y_AXIS));
//            this.footTextPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//            JLabel text = new JLabel(this.footText);
//            ITheme cLAF = ThemeManager.getInstance().getTheme();
//            text.setFont(cLAF.getWelcomeSmallFont());
//            text.setForeground(cLAF.getWelcomeBigForegroundColor());
//            text.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//            this.footTextPanel.add(text);
//        }
//
//        return this.footTextPanel;
//    }
//
//
//
//    private JPanel flowchartPanel;
//    
//    private JPanel introducPanel;
//
//    private JPanel footTextPanel;

    
    private void initmainpanel() {
		// TODO Auto-generated method stub
		mainpanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(142,155,188)));
		mainpanel.setLayout(new BorderLayout());
		mainpanel.add(leftpanel,BorderLayout.WEST);
		mainpanel.add(rightpanel,BorderLayout.CENTER);
		
		leftpanel.setPreferredSize(new Dimension(270, 720));
		leftpanel.setBackground(Color.WHITE);
		leftpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(214,214,214)));
		leftpanel.setLayout(new FlowLayout(FlowLayout.LEADING,0,0));
		leftpanel.add(leftlabelpanel1);
		leftpanel.add(leftlabelpanel2);
		
		leftlabel1.setText("产品视频");
		leftlabel1.setBorder(BorderFactory.createEmptyBorder(30, 30, 0, 0));
		leftlabel1.setFont(new Font("微软雅黑", Font.PLAIN, 27));
		leftlabel2.setText("通过以下视频短片了解更多信息");
		leftlabel2.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 0));
		leftlabel2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		leftlabelpanel1.add(leftlabel1);
		leftlabelpanel1.setLayout(new GridLayout());
		leftlabelpanel1.setOpaque(false);
		leftlabelpanel1.setPreferredSize(new Dimension(270, 60));
		leftlabelpanel2.add(leftlabel2);
		leftlabelpanel2.setLayout(new GridLayout());
		leftlabelpanel2.setOpaque(false);
		leftlabelpanel2.setPreferredSize(new Dimension(270, 60));
		
		
		
		rightpanel.setBackground(Color.WHITE);
		rightpanel.setLayout(new GridLayout(1, 4));
		rightpanel.add(imagelabelpanel1);
		rightpanel.add(imagelabelpanel2);
		rightpanel.add(imagelabelpanel3);
		rightpanel.add(imagelabelpanel4);
		
		String absolutePath=System.getProperty("user.dir");
		String path = absolutePath+"\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon = new ImageIcon(path + "homepanelright.png");
		icon.setImage(icon.getImage().getScaledInstance(227,377, Image.SCALE_DEFAULT));
		
		imagelabel1.setIcon(icon);
		imagelabel2.setIcon(icon);
		imagelabel3.setIcon(icon);
		imagelabel4.setIcon(icon);
		
		imagelabel1.setBorder(BorderFactory.createEmptyBorder(75, 30, 0, 20));
		imagelabel2.setBorder(BorderFactory.createEmptyBorder(75, 30, 0, 20));
		imagelabel3.setBorder(BorderFactory.createEmptyBorder(75, 30, 0, 20));
		imagelabel4.setBorder(BorderFactory.createEmptyBorder(75, 30, 0, 20));
		
		imagelabelpanel1.setLayout(new BorderLayout());
		imagelabelpanel1.setOpaque(false);
		imagelabelpanel1.add(imagelabel1,BorderLayout.NORTH);
		imagelabelpanel2.setLayout(new BorderLayout());
		imagelabelpanel2.setOpaque(false);
		imagelabelpanel2.add(imagelabel2,BorderLayout.NORTH);
		imagelabelpanel3.setLayout(new BorderLayout());
		imagelabelpanel3.setOpaque(false);
		imagelabelpanel3.add(imagelabel3,BorderLayout.NORTH);
		imagelabelpanel4.setLayout(new BorderLayout());
		imagelabelpanel4.setOpaque(false);
		imagelabelpanel4.add(imagelabel4,BorderLayout.NORTH);
		
	}

	private void inittoppanel() {
		// TODO Auto-generated method stub
		titlelabel.setText("起始页");
		titlelabel.setFont(new Font("System", Font.PLAIN, 12));
		titlelabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		titlepanel.setLayout(new BorderLayout());
		titlepanel.add(titlelabel,BorderLayout.WEST);
		titlepanel.setBackground(new Color(255, 242, 157));
		titlepanel.setPreferredSize(new Dimension(90, 23));
		
		String path = "D:\\项目代码\\violetumleditor-master\\violetproduct-swing\\src\\site\\resources\\icons\\OpreationPart\\";

		ImageIcon icon1 = new ImageIcon(path + "yleftarrow.png");
		icon1.setImage(icon1.getImage().getScaledInstance(8,7, Image.SCALE_DEFAULT));
		titleiconlabel1.setIcon(icon1);
		titleiconlabel1.setBorder(BorderFactory.createEmptyBorder(4, 4,4, 6));
		ImageIcon icon2 = new ImageIcon(path + "yfork.png");
		icon2.setImage(icon2.getImage().getScaledInstance(10,8, Image.SCALE_DEFAULT));
		titleiconlabel2.setIcon(icon2);
		titleiconlabel2.setBorder(BorderFactory.createEmptyBorder(4, 4,4, 4));
		
		titleiconlabelpanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		titleiconlabelpanel.setOpaque(false);
		titleiconlabelpanel.add(titleiconlabel1);
		titleiconlabelpanel.add(titleiconlabel2);
		
		toppanel.setBackground(new Color(41,57,85));
		toppanel.setLayout(new BorderLayout());
		toppanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(255, 242, 157)));
		toppanel.add(titlepanel,BorderLayout.WEST);
		titlepanel.add(titleiconlabelpanel,BorderLayout.EAST);
	}



    private JPanel flowchartPanel;
    
    private JPanel introducPanel;

    private JPanel footTextPanel;

    
    private String footText="Automated test platform .\n made by Le.Xiao & Jian.Zhang";

    
 
}

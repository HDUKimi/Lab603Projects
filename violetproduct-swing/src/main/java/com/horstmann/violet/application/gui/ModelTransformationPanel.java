package com.horstmann.violet.application.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.horstmann.violet.application.consolepart.ConsolePartTextArea;
import com.l2fprod.common.swing.JTaskPane;
import com.l2fprod.common.swing.JTaskPaneGroup;

public class ModelTransformationPanel extends JPanel{
	 //ת���������ı���ʾ
	private JTaskPane selectpanel;
	private JTaskPane sdpanel;
	private JTaskPane timingpanel;
	private JTaskPane uppaalpanel;
	private JList<String> sdlist;  //��һ����ģ������SDͼ
	private JList<String> tdlist;  //��һ����ģ������TDͼ
	private JList<String> uppaallist; 	
	private JButton transformationButton;
	private MainFrame mainFrame;
	private JDialog messageDialog;
		
	public ModelTransformationPanel(MainFrame mainFram){	
		this.mainFrame=mainFram;
		initFileList();
		initUI();		
	}

	private void initFileList() {
		// TODO Auto-generated method stub
		String[] sdlists={"1.sequencediagram",
				"2.sequencediagram",
				"3.sequencediagram"};
		sdlist=new JList<String>(sdlists);
		String[] tdlists={"1.timingdiagram",
				"2.timingdiagram",
				"3.timingdiagram"};	
		tdlist=new JList<String>(tdlists);
		String[] uppaallists={"1.uppaal","2.uppaal","3.uppaal","4.uppaal"};
		uppaallist=new JList<String>(uppaallists);
		
		
	}

	private void initUI() {
		// TODO Auto-generated method stub
		
		//˳��ͼ���
		this.sdpanel=new JTaskPane();
		this.sdpanel.setLayout(new BoxLayout(sdpanel,BoxLayout.Y_AXIS));				
		JTaskPaneGroup group1 = new JTaskPaneGroup();
        Font font1 = group1.getFont().deriveFont(Font.PLAIN);
        group1.setFont(font1);
        group1.setTitle("˳��ͼ�ļ�");
        group1.setLayout(new BorderLayout());  
        group1.add(sdlist,BorderLayout.CENTER);   
        sdpanel.add(group1);
		
		
	   
		//ʱ��ͼ���
        this.timingpanel=new JTaskPane();
		this.timingpanel.setLayout(new BoxLayout(timingpanel,BoxLayout.Y_AXIS));				
		JTaskPaneGroup group2 = new JTaskPaneGroup();
        Font font2 = group2.getFont().deriveFont(Font.PLAIN);
        group2.setFont(font2);
        group2.setTitle("ʱ��ͼ�ļ�");
        group2.setLayout(new BorderLayout());  
        group2.add(tdlist,BorderLayout.CENTER);   
        timingpanel.add(group2);
        
        //�����ļ����
		this.selectpanel=new JTaskPane();
		selectpanel.setLayout(new BoxLayout(selectpanel,BoxLayout.Y_AXIS));
		JTaskPaneGroup group3 = new JTaskPaneGroup();
        Font font3 = group3.getFont().deriveFont(Font.PLAIN);
        group3.setFont(font3);
        group3.setTitle("Ҫת����UMLͼ�ļ�");
        group3.setLayout(new GridBagLayout());     
		group3.add(sdpanel,new GBC(0,0,1,1).setFill(GBC.BOTH).setWeight(1, 1));
		group3.add(timingpanel,new GBC(0,1,1,1).setFill(GBC.BOTH).setWeight(1, 1));
		selectpanel.add(group3);
		
		//���ɵ�ʱ���Զ������
		this.uppaalpanel=new JTaskPane();
		this.uppaalpanel.setLayout(new BoxLayout(uppaalpanel,BoxLayout.Y_AXIS));				
		JTaskPaneGroup group4 = new JTaskPaneGroup();
	    Font font4 = group4.getFont().deriveFont(Font.PLAIN);
	    group4.setFont(font4);
	    group4.setTitle("���ɵ�ʱ���Զ����ļ�");
	    group4.setLayout(new BorderLayout());  
	    group4.add(uppaallist,BorderLayout.CENTER);   
	    uppaalpanel.add(group4);
	    
	    
		//ת����ť
		this.transformationButton=new JButton("��UMLͼת����ʱ���Զ���");
		//ѡ����Ϣ		
		transformationButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				messageDialog=new JDialog(mainFrame, "UMLģ��ת��",false);
				messageDialog.getContentPane().setLayout(new BoxLayout(messageDialog.getContentPane(), BoxLayout.Y_AXIS));
				JTextArea messageInfo=new JTextArea("ģ��ת���ɹ�");
				messageInfo.setEditable(false);
				messageDialog.getContentPane().add(messageInfo);
				JButton okButton=new JButton("ȷ��");
				messageDialog.getContentPane().add(okButton);
				centerDialog(messageDialog, mainFrame);
				messageDialog.setSize(200, 200);
				messageDialog.setVisible(true);
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						messageDialog.dispose();
						
					}
				});
				
			}		
		});
		JPanel operationPanel=new JPanel();
		JPanel transformationPanel=new JPanel();
		transformationPanel.setLayout(new BoxLayout(transformationPanel,BoxLayout.X_AXIS));
		transformationPanel.add(transformationButton);
		operationPanel.setLayout(new BoxLayout(operationPanel,BoxLayout.Y_AXIS));
		operationPanel.add(selectpanel);
		operationPanel.add(transformationPanel);
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.add(operationPanel);				
	    this.add(uppaalpanel);	  				
		}
	private void centerDialog(JDialog dialog, Frame owner)
    {
        Rectangle b = owner.getBounds();

        double x = b.getX() + b.getWidth() / 2 - dialog.getWidth() / 2;
        double y = b.getY() + b.getHeight() / 2 - dialog.getHeight() / 2;
        Dimension screenSize = owner.getToolkit().getScreenSize();
        if (x + dialog.getWidth() > screenSize.getWidth())
        {
            x = screenSize.getWidth() - dialog.getWidth();
        }
        if (y + dialog.getHeight() > screenSize.getHeight())
        {
            y = screenSize.getHeight() - dialog.getHeight();
        }
        Point newLocation = new Point(Math.max((int) x, 0), Math.max((int) y, 0));
        dialog.setLocation(newLocation);
    }
	}
	



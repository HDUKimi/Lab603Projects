package com.horstmann.violet.application.gui.stepThree;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.common.ColorData;

public class TestSeqProducePanel extends JPanel {

	private MainFrame mainFrame;
	
	private JScrollPane seqScrollPane;
	private JPanel seqPanel;

	public TestSeqProducePanel(MainFrame mainFrame) {

		this.mainFrame = mainFrame;
		
		initSeqPanel();
		
		this.setLayout(new GridLayout());
		this.add(seqScrollPane);
		
	}

	private void initSeqPanel() {
		
		seqPanel=new JPanel();
		seqPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		seqPanel.setBackground(ColorData.white);
		
		seqScrollPane=new JScrollPane(seqPanel);
		seqScrollPane.setBorder(null);
		
	}

	public void dealAndShow() {
		
		showSeqData();
		
	}

	private void showSeqData() {
		
		seqPanel.setLayout(new BoxLayout(seqPanel, BoxLayout.Y_AXIS));
		for(int i=0;i<50;i++){
			TestSeqPartPanel seqPartPanel=new TestSeqPartPanel();
			if(i==0){
				seqPartPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorData.gray));
			}
			seqPanel.add(seqPartPanel);
		}
		
	}
	
}

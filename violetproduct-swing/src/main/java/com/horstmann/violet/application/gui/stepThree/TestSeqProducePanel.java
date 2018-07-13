package com.horstmann.violet.application.gui.stepThree;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.horstmann.violet.application.ckt.entity.Markov;
import com.horstmann.violet.application.ckt.entity.Route;
import com.horstmann.violet.application.ckt.random.RouletteSelection;
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
		
		dealData();
		
		showData();
		
	}

	private void dealData() {
		
		Markov markov=mainFrame.getStepThreeCenterPanel().getMarkov();
		
		List<Route> routes = RouletteSelection.randomRoute(markov, 100);
		
		mainFrame.getStepThreeCenterPanel().setRoutes(routes);
		
	}

	private void showData() {
		
		List<Route> routes=mainFrame.getStepThreeCenterPanel().getRoutes();
		
		seqPanel.setLayout(new BoxLayout(seqPanel, BoxLayout.Y_AXIS));
		for(int i=0;i<routes.size();i++){
			Route route=routes.get(i);
			TestSeqPartPanel seqPartPanel=new TestSeqPartPanel(route);
			if(i==0){
				seqPartPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorData.gray));
			}
			seqPanel.add(seqPartPanel);
		}
		
	}
	
}

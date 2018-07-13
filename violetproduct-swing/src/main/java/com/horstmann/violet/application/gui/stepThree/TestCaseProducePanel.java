package com.horstmann.violet.application.gui.stepThree;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.horstmann.violet.application.ckt.entity.Route;
import com.horstmann.violet.application.ckt.xml.RouteInstantiate;
import com.horstmann.violet.application.ckt.xml.SaveTestCase;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.common.ColorData;

public class TestCaseProducePanel extends JPanel {

	private MainFrame mainFrame;

	private JScrollPane caseScrollPane;
	private JPanel casePanel;

	public TestCaseProducePanel(MainFrame mainFrame) {

		this.mainFrame = mainFrame;
		
		initCasePanel();
		
		this.setLayout(new GridLayout());
		this.add(caseScrollPane);
		
	}

	private void initCasePanel() {
		
		casePanel=new JPanel();
		casePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		casePanel.setBackground(ColorData.white);
		
		caseScrollPane=new JScrollPane(casePanel);
		caseScrollPane.setBorder(null);
		
	}

	public void dealAndShow() {
		
		dealData();
		
		showData();
		
	}

	private void dealData() {

		List<Route> routes=mainFrame.getStepThreeCenterPanel().getRoutes();
		
		RouteInstantiate.instantiation(routes);
		
		SaveTestCase.ToXML("E:\\XML\\connect.markov.violet.xml", routes);
		
	}

	private void showData() {
		
		List<Route> routes=mainFrame.getStepThreeCenterPanel().getRoutes();
		
		casePanel.setLayout(new BoxLayout(casePanel, BoxLayout.Y_AXIS));
		for(int i=0;i<routes.size();i++){
			Route route=routes.get(i);
			TestCasePartPanel casePartPanel=new TestCasePartPanel(route);
			if(i==0){
				casePartPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorData.gray));
			}
			casePanel.add(casePartPanel);
		}
		
	}

}

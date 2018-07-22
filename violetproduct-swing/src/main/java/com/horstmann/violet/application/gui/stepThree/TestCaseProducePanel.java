package com.horstmann.violet.application.gui.stepThree;

import java.awt.GridLayout;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
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
	
	private Callable<Integer> callable;
	private FutureTask<Integer> task;
	private Thread thread;
	private Callable<Integer> processCallable;
	private FutureTask<Integer> processTask;
	private Thread processThread;
	
	private int state;

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
		
		processCallable=new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				
				JProgressBar progressBar=mainFrame.getStepThreeCenterPanel().getProgressPanel().getProgressBar();
				while(progressBar.getValue()<100){
					
					progressBar.setValue(progressBar.getValue()+1);
					if(state==0){
						Thread.sleep(1000);
					}
					else{
						Thread.sleep(10);
					}
					
				}
				
				return 1;
			}
		};
		processTask=new FutureTask<>(processCallable);
		processThread=new Thread(processTask);
		
		callable=new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {

				state=0;
				
				dealData();
				
				state=1;
				
				while(processTask.get() == null){
					Thread.sleep(10);
				}
				
				showData();
				
				mainFrame.getStepThreeCenterPanel().getProgressPanel().getProgressBar().setValue(100);
				mainFrame.getStepThreeCenterPanel().setStep(4);
				
				return 1;
			}

		};
		task=new FutureTask<>(callable);
		thread=new Thread(task);
		
		processThread.start();
		thread.start();
		
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
		
		mainFrame.ChangeRepaint(this);
		
	}

}

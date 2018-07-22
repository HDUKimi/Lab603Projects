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

import com.horstmann.violet.application.ckt.entity.Markov;
import com.horstmann.violet.application.ckt.entity.Route;
import com.horstmann.violet.application.ckt.random.RouletteSelection;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.common.ColorData;

public class TestSeqProducePanel extends JPanel {

	private MainFrame mainFrame;
	
	private JScrollPane seqScrollPane;
	private JPanel seqPanel;
	
	private Callable<Integer> callable;
	private FutureTask<Integer> task;
	private Thread thread;
	private Callable<Integer> processCallable;
	private FutureTask<Integer> processTask;
	private Thread processThread;
	
	private int state;

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
		
		processCallable=new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				
				JProgressBar progressBar=mainFrame.getStepThreeCenterPanel().getProgressPanel().getProgressBar();
				while(progressBar.getValue()<60){
					
					progressBar.setValue(progressBar.getValue()+1);
					if(state==0){
						Thread.sleep(100);
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
				
				mainFrame.getStepThreeCenterPanel().getProgressPanel().getProgressBar().setValue(60);
				mainFrame.getStepThreeCenterPanel().setStep(3);
				
				return 1;
			}

		};
		task=new FutureTask<>(callable);
		thread=new Thread(task);
		
		processThread.start();
		thread.start();
		
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
		
		mainFrame.ChangeRepaint(this);
		
	}
	
}

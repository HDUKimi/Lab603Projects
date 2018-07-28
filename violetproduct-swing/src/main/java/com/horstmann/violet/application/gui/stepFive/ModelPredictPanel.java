package com.horstmann.violet.application.gui.stepFive;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

import com.horstmann.violet.application.ckt.entity.Route;
import com.horstmann.violet.application.ckt.xml.TestCaseUtil;
import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.common.ColorData;
import com.horstmann.violet.application.gui.common.FileUtil;
import com.horstmann.violet.application.lmr.antcolony.AcoMainFun;
import com.horstmann.violet.application.lmr.antcolony.GOModel;
import com.horstmann.violet.application.lmr.antcolony.JMModel;
import com.horstmann.violet.application.lmr.antcolony.LVModel;
import com.horstmann.violet.application.lmr.antcolony.ModelDataChart;
import com.horstmann.violet.application.lmr.antcolony.MusaModel;

public class ModelPredictPanel extends JPanel{

	private MainFrame mainFrame;
	
	private JPanel predictPanel;
	private JPanel modelPanel;
	
	private JPanel JMModelPanel;
	private JPanel GOModelPanel;
	private JPanel MusaModelPanel;
	private JPanel LVModelPanel;
	
	private JPanel JMLabelPanel;
	private JPanel GOLabelPanel;
	private JPanel MusaLabelPanel;
	private JPanel LVLabelPanel;
	
	private JLabel JMLabel1;
	private JLabel JMLabel2;
	private JLabel GOLabel1;
	private JLabel GOLabel2;
	private JLabel MusaLabel1;
	private JLabel MusaLabel2;
	private JLabel LVLabel1;
	private JLabel LVLabel2;
	private JLabel LVLabel3;
	
	private Callable<Integer> callable;
	private FutureTask<Integer> task;
	private Thread thread;
	private Callable<Integer> processCallable;
	private FutureTask<Integer> processTask;
	private Thread processThread;
	
	private int state;
	
	public ModelPredictPanel(MainFrame mainFrame) {
		
		this.mainFrame=mainFrame;
		
		predictPanel=new JPanel();
		modelPanel=new JPanel();
		
		initPredictPanel();
		initModelPanel();
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(predictPanel);
		this.add(modelPanel);
		layout.setConstraints(predictPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(2, 1));
		layout.setConstraints(modelPanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		this.setBackground(ColorData.white);
		
	}

	private void initPredictPanel() {
		
		predictPanel.setOpaque(false);
		predictPanel.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10),
				BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorData.gray), "Ä£ÐÍÔ¤²âÇ÷ÊÆÍ¼",
						TitledBorder.LEFT, TitledBorder.TOP, new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13), ColorData.black)));

	}

	private void initModelPanel() {
		
		modelPanel.setOpaque(false);

		JMModelPanel=new JPanel();
		GOModelPanel=new JPanel();
		MusaModelPanel=new JPanel();
		LVModelPanel=new JPanel();
		
		JMModelPanel.setOpaque(false);
		GOModelPanel.setOpaque(false);
		MusaModelPanel.setOpaque(false);
		LVModelPanel.setOpaque(false);
		
		initJMModelPanel();
		initGOModelPanel();
		initMusaModelPanel();
		initLVModelPanel();
		
		JMModelPanel.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5),
				BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorData.gray), "JMÄ£ÐÍ",
						TitledBorder.LEFT, TitledBorder.TOP, new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13), ColorData.black)));
		GOModelPanel.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5),
				BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorData.gray), "GOÄ£ÐÍ",
						TitledBorder.LEFT, TitledBorder.TOP, new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13), ColorData.black)));
		MusaModelPanel.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5),
				BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorData.gray), "MusaÄ£ÐÍ",
						TitledBorder.LEFT, TitledBorder.TOP, new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13), ColorData.black)));
		LVModelPanel.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10),
				BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorData.gray), "LVÄ£ÐÍ",
						TitledBorder.LEFT, TitledBorder.TOP, new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13), ColorData.black)));

		
		GridBagLayout layout = new GridBagLayout();
		modelPanel.setLayout(layout);
		modelPanel.add(JMModelPanel);
		modelPanel.add(GOModelPanel);
		modelPanel.add(MusaModelPanel);
		modelPanel.add(LVModelPanel);
		layout.setConstraints(JMModelPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(GOModelPanel, new GBC(1, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(MusaModelPanel, new GBC(2, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		layout.setConstraints(LVModelPanel, new GBC(3, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
//		modelPanel.setLayout(new GridLayout(1, 4));
//		modelPanel.add(JMModelPanel);
//		modelPanel.add(GOModelPanel);
//		modelPanel.add(MusaModelPanel);
//		modelPanel.add(LVModelPanel);
		
	}
	
	private void initJMModelPanel() {
		
		JMLabelPanel=new JPanel();
		
		JMLabel1=new JLabel();
		JMLabel2=new JLabel();
		
		JMLabel1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		JMLabel1.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
		JMLabel2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		JMLabel2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		JMLabelPanel.setOpaque(false);
		JMLabelPanel.setLayout(new BoxLayout(JMLabelPanel, BoxLayout.Y_AXIS));
		JMLabelPanel.add(JMLabel1);
		JMLabelPanel.add(JMLabel2);
		
		JMModelPanel.setLayout(new BorderLayout());
		JMModelPanel.add(JMLabelPanel, BorderLayout.NORTH);
		
	}

	private void initGOModelPanel() {
		
		GOLabelPanel=new JPanel();
		
		GOLabel1=new JLabel();
		GOLabel2=new JLabel();
		
		GOLabel1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		GOLabel1.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
		GOLabel2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		GOLabel2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		GOLabelPanel.setOpaque(false);
		GOLabelPanel.setLayout(new BoxLayout(GOLabelPanel, BoxLayout.Y_AXIS));
		GOLabelPanel.add(GOLabel1);
		GOLabelPanel.add(GOLabel2);
		
		GOModelPanel.setLayout(new BorderLayout());
		GOModelPanel.add(GOLabelPanel, BorderLayout.NORTH);
		
	}

	private void initMusaModelPanel() {
		
		MusaLabelPanel=new JPanel();
		
		MusaLabel1=new JLabel();
		MusaLabel2=new JLabel();
		
		MusaLabel1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		MusaLabel1.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
		MusaLabel2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		MusaLabel2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		MusaLabelPanel.setOpaque(false);
		MusaLabelPanel.setLayout(new BoxLayout(MusaLabelPanel, BoxLayout.Y_AXIS));
		MusaLabelPanel.add(MusaLabel1);
		MusaLabelPanel.add(MusaLabel2);
		
		MusaModelPanel.setLayout(new BorderLayout());
		MusaModelPanel.add(MusaLabelPanel, BorderLayout.NORTH);
		
	}

	private void initLVModelPanel() {
		
		LVLabelPanel=new JPanel();
		
		LVLabel1=new JLabel();
		LVLabel2=new JLabel();
		LVLabel3=new JLabel();
		
		LVLabel1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		LVLabel1.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
		LVLabel2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		LVLabel2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		LVLabel3.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		LVLabel3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		LVLabelPanel.setOpaque(false);
		LVLabelPanel.setLayout(new BoxLayout(LVLabelPanel, BoxLayout.Y_AXIS));
		LVLabelPanel.add(LVLabel1);
		LVLabelPanel.add(LVLabel2);
		LVLabelPanel.add(LVLabel3);
		
		LVModelPanel.setLayout(new BorderLayout());
		LVModelPanel.add(LVLabelPanel, BorderLayout.NORTH);
		
	}

	public void dealAndShow() {
		
		processCallable=new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				
				JProgressBar progressBar=mainFrame.getStepFiveCenterPanel().getProgressPanel().getProgressBar();
				progressBar.setValue(0);
				while(progressBar.getValue()<99){
					
					progressBar.setValue(progressBar.getValue()+1);
					if(state==0){
						Thread.sleep(600);
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
				
				try{
					state=0;
					
					DealData();
					
					state=1;
					
					while(processTask.get() == null){
						Thread.sleep(10);
					}
					
					ShowData();
					
					mainFrame.getStepFiveCenterPanel().getProgressPanel().getProgressBar().setValue(100);
					
				}catch (Exception e) {
					e.printStackTrace();
				}

				return 1;
			}

		};
		task=new FutureTask<>(callable);
		thread=new Thread(task);
		
		processThread.start();
		thread.start();
		
	}
	
	private void DealData() {
		
		String name=mainFrame.getStepFiveCenterPanel().getFailureDataName();
		String path=FileUtil.pathlist.get(3)+name+".violet.txt";
		
		List<Integer> failureDataList=new ArrayList<>();
		try {
			failureDataList=FileUtil.ReadFailureData(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AcoMainFun aco=new AcoMainFun();
		
		aco.InitData(failureDataList);
	}

	private void ShowData() {
		
		predictPanel.removeAll();
		predictPanel.setLayout(new GridLayout());
		predictPanel.add(new ModelDataChart().createChart());
		
		JMLabel1.setText("ÐÎ²Îa: "+JMModel.a);
		JMLabel2.setText("±ÈÀý³£Êýb: "+JMModel.b);
		
		GOLabel1.setText("ÐÎ²Îa: "+GOModel.a);
		GOLabel2.setText("±ÈÀý³£Êýb: "+GOModel.b);
		
		MusaLabel1.setText("ÐÎ²Îa: "+MusaModel.a);
		MusaLabel2.setText("±ÈÀý³£Êýb: "+MusaModel.b);
		
		LVLabel1.setText("ÐÎ²Îa: "+LVModel.a);
		LVLabel2.setText("±ÈÀý³£Êý¦Â0: "+LVModel.b0);
		LVLabel3.setText("±ÈÀý³£Êý¦Â1: "+LVModel.b1);
		
		mainFrame.ChangeRepaint(this);
		
	}
	
	
	
}

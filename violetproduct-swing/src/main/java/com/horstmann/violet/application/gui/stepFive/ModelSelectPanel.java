package com.horstmann.violet.application.gui.stepFive;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.common.ColorData;
import com.horstmann.violet.application.gui.common.DottedLabel;
import com.horstmann.violet.application.lmr.antcolony.GOModel;
import com.horstmann.violet.application.lmr.antcolony.JMModel;
import com.horstmann.violet.application.lmr.antcolony.LVModel;
import com.horstmann.violet.application.lmr.antcolony.MusaModel;
import com.horstmann.violet.application.lmr.deeplearn.BpRegression;

public class ModelSelectPanel extends JPanel{

	private MainFrame mainFrame;
	
	private JPanel labelPanel1;
	private JPanel labelPanel2;
	private JPanel labelPanel3;
	private JPanel labelPanel4;
	
	private DottedLabel label1;
	private JLabel label2;
	private DottedLabel label3;
	private JLabel label4;
	
	private JPanel emptyPanel;
	private JPanel emptyPanel1;
	private JPanel emptyPanel2;
	private JPanel emptyPanel3;
	private JPanel emptyPanel4;
	
	
	private Callable<Integer> callable;
	private FutureTask<Integer> task;
	private Thread thread;
	private Callable<Integer> processCallable;
	private FutureTask<Integer> processTask;
	private Thread processThread;
	
	private int state;
	
	
	public ModelSelectPanel(MainFrame mainFrame) {
		
		this.mainFrame=mainFrame;
		
		labelPanel1= new JPanel();
		labelPanel2=new JPanel();
		labelPanel3=new JPanel();
		labelPanel4=new JPanel();
		
		emptyPanel=new JPanel();
		
		labelPanel1.setOpaque(false);
		labelPanel2.setOpaque(false);
		labelPanel3.setOpaque(false);
		labelPanel4.setOpaque(false);
		emptyPanel.setOpaque(false);
		
		initLabelPanel1();
		initLabelPanel2();
		initLabelPanel3();
		initLabelPanel4();
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(labelPanel1);
		this.add(labelPanel2);
		this.add(labelPanel3);
		this.add(labelPanel4);
		this.add(emptyPanel);
		layout.setConstraints(labelPanel1, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
		layout.setConstraints(labelPanel2, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
		layout.setConstraints(labelPanel3, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
		layout.setConstraints(labelPanel4, new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
		layout.setConstraints(emptyPanel, new GBC(0, 4, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
		
		this.setBackground(ColorData.white);
		
	}


	private void initLabelPanel1() {
		
		label1=new DottedLabel();
		label1.setText("<html><body><p>根据模型评价标准分别计算各个待选模型的5项评价值，并进行分级编码。</p></body></html>");
		label1.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		
		
		labelPanel1.setLayout(new GridLayout());
		labelPanel1.add(label1);
		labelPanel1.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));
		
	}


	private void initLabelPanel2() {
		
		ImageIcon icon = new ImageIcon(this.getClass().getResource("1.png"));
		icon.setImage(icon.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT));
		
		label2=new JLabel();
		label2.setIcon(icon);
		
		emptyPanel1=new JPanel();
		emptyPanel2=new JPanel();
		emptyPanel1.setOpaque(false);
		emptyPanel2.setOpaque(false);
		
		GridBagLayout layout = new GridBagLayout();
		labelPanel2.setLayout(layout);
		labelPanel2.add(emptyPanel1);
		labelPanel2.add(label2);
		labelPanel2.add(emptyPanel2);
		layout.setConstraints(emptyPanel1, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(label2, new GBC(1, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
		layout.setConstraints(emptyPanel2, new GBC(2, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		
		labelPanel2.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		
	}


	private void initLabelPanel3() {
		
		label3=new DottedLabel();
		label3.setText("<html><body><p>将编码后的结果输入到稳定的BP神经网络中进行计算...</p></body></html>");
		label3.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		
		labelPanel3.setLayout(new GridLayout());
		labelPanel3.add(label3);
		labelPanel3.setBorder(BorderFactory.createEmptyBorder(10, 10, 30, 10));
		
	}
	
	private void initLabelPanel4() {
		
		ImageIcon icon = new ImageIcon(this.getClass().getResource("bp.jpg"));
		icon.setImage(icon.getImage().getScaledInstance(500,300, Image.SCALE_DEFAULT));
		
		label4=new JLabel();
		label4.setIcon(icon);
		
		emptyPanel3=new JPanel();
		emptyPanel4=new JPanel();
		emptyPanel3.setOpaque(false);
		emptyPanel4.setOpaque(false);
		
		GridBagLayout layout = new GridBagLayout();
		labelPanel4.setLayout(layout);
		labelPanel4.add(emptyPanel3);
		labelPanel4.add(label4);
		labelPanel4.add(emptyPanel4);
		layout.setConstraints(emptyPanel3, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		layout.setConstraints(label4, new GBC(1, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
		layout.setConstraints(emptyPanel4, new GBC(2, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 0));
		
		labelPanel4.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		
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
						Thread.sleep(500);
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
				
				int[] input=dealEvaluate();
				
				Thread.sleep(1000);
				
				label1.setText("<html><body><p>根据模型评价标准分别计算各个待选模型的5项评价值，并进行分级编码。</p><br><p>其编码后最终的结果为"+Arrays.toString(input)+"</p></body></html>");
				mainFrame.ChangeRepaint(mainFrame.getStepFiveCenterPanel().getModelSelectPanel());
				
				BpRegression bp=new BpRegression();
				int result=bp.Start(input);
				
				mainFrame.getStepFiveCenterPanel().setSelectModel(result);
				
				state=1;
				
				while(processTask.get() == null){
					Thread.sleep(10);
				}
				
				String[] modelStr=new String[]{"","JM","GO","Musa","LV"};
				
				label3.setText("<html><body><p>输出结果为"+result+"，即"+modelStr[result]+"模型为最佳的模型选择</p></body></html>");
				
				mainFrame.ChangeRepaint(mainFrame.getStepFiveCenterPanel().getModelSelectPanel());
				
				mainFrame.getStepFiveCenterPanel().getProgressPanel().getProgressBar().setValue(100);
				
				return 1;
			}

		};
		task=new FutureTask<>(callable);
		thread=new Thread(task);
		
		processThread.start();
		thread.start();
		
	}


	private int[] dealEvaluate() {
		
		int[] input=new int[5];
		
		double[] FD=new double[]{0,JMModel.FD,GOModel.FD,MusaModel.FD,LVModel.FD};
		double[] PL=new double[]{0,JMModel.PL,GOModel.PL,MusaModel.PL,LVModel.PL};
		double[] KS_U=new double[]{0,JMModel.KS_U,GOModel.KS_U,MusaModel.KS_U,LVModel.KS_U};
		double[] KS_Y=new double[]{0,JMModel.KS_Y,GOModel.KS_Y,MusaModel.KS_Y,LVModel.KS_Y};
		double[] MN=new double[]{0,JMModel.MN,GOModel.MN,MusaModel.MN,LVModel.MN};
		
		input[0]=1;
		for(int i=1;i<FD.length;i++){
			if(FD[i]<FD[input[0]]){
				input[0]=i;
			}
		}
		
		input[1]=1;
		for(int i=1;i<PL.length;i++){
			if(PL[i]>PL[input[1]]){
				input[1]=i;
			}
		}
		
		input[2]=1;
		for(int i=1;i<KS_U.length;i++){
			if(KS_U[i]<KS_U[input[2]]){
				input[2]=i;
			}
		}
		
		input[3]=1;
		for(int i=1;i<KS_Y.length;i++){
			if(KS_Y[i]<KS_Y[input[3]]){
				input[3]=i;
			}
		}
		
		input[4]=1;
		for(int i=1;i<MN.length;i++){
			if(MN[i]<MN[input[4]]){
				input[4]=i;
			}
		}
		
		return input;
	}
	
}

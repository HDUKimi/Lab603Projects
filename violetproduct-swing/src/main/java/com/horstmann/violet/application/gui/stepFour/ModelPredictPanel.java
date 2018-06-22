package com.horstmann.violet.application.gui.stepFour;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

import com.horstmann.violet.application.gui.GBC;
import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.common.ColorData;
import com.horstmann.violet.application.lmr.antcolony.AcoMainFun;
import com.horstmann.violet.application.lmr.antcolony.ModelDataChart;

public class ModelPredictPanel extends JPanel{

	private MainFrame mainFrame;
	
	private JPanel predictPanel;
	private JPanel modelPanel;
	
	private JPanel JMModelPanel;
	private JPanel GOModelPanel;
	private JPanel MusaModelPanel;
	private JPanel LVModelPanel;
	
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
		layout.setConstraints(predictPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(1, 1));
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
		
	}
	
	public void dealAndShow() {
		
		AcoMainFun aco=new AcoMainFun();
		
		aco.InitData();
		
		predictPanel.removeAll();
		predictPanel.setLayout(new GridLayout());
		predictPanel.add(new ModelDataChart().createChart());
		
		mainFrame.ChangeRepaint(this);
		
	}
	
}

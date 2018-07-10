package com.horstmann.violet.application.gui.stepThree;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.plaf.ProgressBarUI;

import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.common.ColorData;
import com.horstmann.violet.application.gui.common.ProgressUI;

public class ProgressPanel extends JPanel {

	private MainFrame mainFrame;

	private JPanel progressBarPanel;

	private JProgressBar progressBar;

	public ProgressPanel(MainFrame mainFrame) {

		this.mainFrame = mainFrame;

		progressBarPanel = new JPanel();

		initProgressBarPanel();

		this.setLayout(new BorderLayout());
		this.add(progressBarPanel, BorderLayout.CENTER);

		this.setBackground(ColorData.white);
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, ColorData.gray));

	}

	private void initProgressBarPanel() {

		progressBar = new JProgressBar() {

			@Override
			public void setUI(ProgressBarUI ui) {
				// TODO Auto-generated method stub
				super.setUI(new ProgressUI(this, ColorData.yellow_light));
			}

		};

		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setValue(0);
		// progressBar.setStringPainted(false);
		progressBar.setPreferredSize(new Dimension(400, 23));
		progressBar.setMaximumSize(new Dimension(400, 23));
		progressBar.setMinimumSize(new Dimension(400, 23));
		
		progressBar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, ColorData.gray));

		progressBarPanel.setLayout(new GridLayout());
		progressBarPanel.add(progressBar);
		progressBarPanel.setBorder(BorderFactory.createEmptyBorder(7, 10, 7, 10));

		progressBarPanel.setOpaque(false);

	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}
	
}

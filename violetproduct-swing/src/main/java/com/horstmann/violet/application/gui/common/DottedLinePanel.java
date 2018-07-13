package com.horstmann.violet.application.gui.common;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class DottedLinePanel extends JPanel {

	private Rectangle2D mfRect = new Rectangle2D.Float();
	// ��ɫ
	private Color mfColor = ColorData.gray;

	private float[] dash1 = { 5.0f };

	private BasicStroke s = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);

	public DottedLinePanel() {
		super();
		this.setOpaque(false);
	}

	/**
	 * ��дpaint����
	 */
	public void paint(Graphics g) {

		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;
		// ���ñ߿���ɫ
		g2d.setColor(mfColor);
		// ���ñ߿�Χ
//		mfRect.setRect(0, 0, getWidth() - 1, getHeight() - 1);
		// ���ñ߿�����
		g2d.setStroke(s);

//		g2d.draw(mfRect);
		g2d.drawLine(10, (getHeight()-1)/2, getWidth()-10, (getHeight()-1)/2);

	}
}
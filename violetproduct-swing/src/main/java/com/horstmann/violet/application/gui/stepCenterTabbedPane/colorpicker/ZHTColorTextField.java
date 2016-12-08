package com.horstmann.violet.application.gui.stepCenterTabbedPane.colorpicker;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.Rectangle2D;

import javax.swing.JTextField;
public class ZHTColorTextField extends JTextField {
	private boolean showWithRGB = false;
	private Color color = null;
	private int leftGap = 30;

	public ZHTColorTextField() {
		this.setEditable(false);
	}

	public Insets getInsets() {
		Insets insets = super.getInsets();
		insets.left += leftGap;
		insets.right += 2;
		return insets;
	}

	public Dimension getPreferredSize() {
		return new Dimension(100, 20);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		int gap = 2;
		double h = 16;
		double w = leftGap - gap * 2;
		if (h >= this.getHeight()) {
			h = this.getHeight() - 4;
		}
		if (color != null) {
			g2d.setColor(color);
			Rectangle2D rect = new Rectangle2D.Double(gap, this.getHeight() / 2.0 - h / 2, w, h);
			g2d.fill(rect);
		} else {
			Rectangle2D borderRect = new Rectangle2D.Double(gap, this.getHeight() / 2.0 - h / 2, w, h);
			int rCount = 3;
			int cCount = 7;
			double pw = h / rCount;
			double ph = w / cCount;
			int size = rCount * cCount;
			for (int i = 0; i < size; i++) {
				int row = i / rCount;
				int col = i % rCount;
				if (i % 2 == 0) {
					g2d.setColor(Color.WHITE);
				} else {
					g2d.setColor(Color.GRAY);
				}
				Rectangle2D rect = new Rectangle2D.Double(gap + row * ph, borderRect.getY() + col * pw, ph, pw);
				g2d.fill(rect);
			}
			borderRect.setFrame(borderRect.getX() - 1, borderRect.getY() - 1, borderRect.getWidth() + 1, borderRect.getHeight() + 1);
			g2d.setColor(Color.BLACK);
			g2d.draw(borderRect);
		}
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		if (this.color != color) {
			this.color = color;
//			seText();
			this.repaint();
		}
	}

	private void seText() {
		if (color != null) {
			if (showWithRGB) {
				this.setText(color.getRed() + "," + color.getGreen() + "," + color.getBlue());
			} else {
//				this.setText(ZHTUtils.getColorHexString(color, false));
				this.setText(Integer.toHexString(color.getRGB() & 0xffffff).toUpperCase());
			}
		} else {
			this.setText("None");
		}
		this.repaint();
	}

	public boolean isShowWithRGB() {
		return showWithRGB;
	}

	public void setShowWithRGB(boolean showWithRGB) {
		this.showWithRGB = showWithRGB;
		seText();
	};

}
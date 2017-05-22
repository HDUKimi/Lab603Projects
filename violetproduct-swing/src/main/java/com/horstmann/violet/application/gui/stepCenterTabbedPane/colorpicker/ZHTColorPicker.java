package com.horstmann.violet.application.gui.stepCenterTabbedPane.colorpicker;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.horstmann.violet.workspace.sidebar.colortools.ColorChoice;
import com.horstmann.violet.workspace.sidebar.colortools.ColorToolsBarPanel;
public class ZHTColorPicker extends JComponent {

	
	public static final String SELECTEDCOLORCHANGE = "selectedcolorchange";
	public static final String OVERCOLORCHANGE = "overcolorchange";
	public static final String MORECOLORSELECTION = "morecolorselection";
	
	private int red;
	private int green;
	private int blue;
	
	private Color backgroundColor;
	private Color borderColor;
	private Color textColor;

//	private JButton noneButton = new ColorButton("None");
//	private JButton otherButton = new ColorButton("more Colors");
	private ZHTColorPanel colorPanel = new ZHTColorPanel();
//	private ZHTColorTextField colorField = new ZHTColorTextField();
	private Color selectedColor = null;

	public ZHTColorPicker() {
		
		initGUI();
		installListener();
	}

	public Dimension getPreferredSize() {
		return new Dimension(160,160);
	}

	public Insets getInsets() {
		return new Insets(1, 1, 1, 1);
	}

	private void installListener() {
//		otherButton.addMouseListener(new MouseAdapter() {
//			public void mousePressed(MouseEvent e) {
//				firePropertyChange(MORECOLORSELECTION, true, false);
//				Color selectedColor = JColorChooser.showDialog(ZHTColorPicker.this, "Colors", null);
//				setSelectedColor(selectedColor);
//
//			}
//		});

//		noneButton.addMouseListener(new MouseAdapter() {
//			public void mousePressed(MouseEvent e) {
//				setSelectedColor(null);
//			}
//		});

		colorPanel.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName() == SELECTEDCOLORCHANGE) {
					Object newValue = evt.getNewValue();
					if (newValue == null) {
						setSelectedColor(null);
					} else {
						setSelectedColor((Color) newValue);
					}
				}
				if (evt.getPropertyName() == OVERCOLORCHANGE) {
					Object newValue = evt.getNewValue();
					if (newValue == null) {
//						colorField.setColor(null);
					} else {
//						colorField.setColor((Color) newValue);
					}
				}
			}
		});
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setOpaque(false);
//		topPanel.add(colorField, BorderLayout.WEST);
//		topPanel.add(noneButton, BorderLayout.CENTER);

		this.add(topPanel, BorderLayout.NORTH);
//		this.add(otherButton, BorderLayout.SOUTH);
		this.add(colorPanel, BorderLayout.CENTER);
	}

	public Color getSelectedColor() {
		return selectedColor;
	}

	public void setSelectedColor(Color selectedColor) {
		Color old = this.selectedColor;
		this.selectedColor = selectedColor;
		this.firePropertyChange(SELECTEDCOLORCHANGE, old, selectedColor);
//		System.out.println("---------------");
//		System.out.println(selectedColor+" + "+selectedColor.getRed()+" + "+selectedColor.getGreen()+" + "+selectedColor.getBlue());
		red=selectedColor.getRed();
		green=selectedColor.getGreen();
		blue=selectedColor.getBlue();
		backgroundColor=new Color(red, green, blue);
		if(red>=30){
			red-=30;
		}
		if(green>=30){
			green-=30;
		}
		if(blue>=30){
			blue-=30;
		}
		if(red==250 && green==250 && blue==250){
			borderColor=new Color(191,191,191);
		}
		else{
			borderColor=new Color(red, green, blue);
		}
		textColor=new Color(51,51,51);
//		System.out.println(old+" + "+old.getRed()+" + "+old.getGreen()+" + "+old.getBlue());
		ColorChoice c=new ColorChoice(backgroundColor,borderColor,textColor);
		ColorPickerDemo.getMainFrame().getConsolePartPanel().getTextarea1().append("选中了颜色: "+backgroundColor.toString()+"\n");
		((ColorToolsBarPanel)ColorPickerDemo.getWorkspace().getSideBar().getColorChoiceBar().getAWTComponent()).fireColorChoiceChanged(c);
//		System.out.println("---------------");
//		System.out.println("---"+selectedColor);
	}

//	public void setTextColor(Color color) {
//		colorField.setColor(color);
//	}

	protected void paintChildren(Graphics g) {
		super.paintChildren(g);
//		JLabel label = new JLabel("Powered By zht");
//		label.setForeground(Color.BLUE);
//		label.setFont(new Font("Dialog", Font.PLAIN, 8));
//		int width = this.getWidth();
//		int height = this.getHeight();
//		double w = label.getPreferredSize().getWidth();
//		double h = label.getPreferredSize().getHeight();
//		double x = width - w - 5;
//		double y = height - h - 5;
//		SwingUtilities.paintComponent(g, label, this, new Rectangle((int) x, (int) y, (int) w, (int) h));
	}

//	public JButton getNoneButton() {
//		return noneButton;
//	}
//
//	public JButton getOtherButton() {
//		return otherButton;
//	}
//
//	public ZHTColorTextField getColorField() {
//		return colorField;
//	}
}

class ColorButton extends JButton {
	public ColorButton(String text) {
		super(text);
		this.setContentAreaFilled(false);
	}

	protected void paintComponent(Graphics g) {
		if (this.getModel().isRollover()) {
			Graphics2D g2 = (Graphics2D) g;
			GradientPaint p = new GradientPaint(0, 0, new Color(0xFFFFFF), 0, getHeight(), new Color(0xC8D2DE));
			Paint oldPaint = g2.getPaint();
			g2.setPaint(p);
			g2.fillRect(0, 0, getWidth(), getHeight());
			g2.setPaint(oldPaint);
		}
		super.paintComponent(g);

	}

}
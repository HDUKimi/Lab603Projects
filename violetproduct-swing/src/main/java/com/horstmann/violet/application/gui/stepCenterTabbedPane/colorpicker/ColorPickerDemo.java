package com.horstmann.violet.application.gui.stepCenterTabbedPane.colorpicker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.workspace.IWorkspace;

public class ColorPickerDemo extends JPanel {

	private static MainFrame mainFrame;
	private static IWorkspace workspace;

	// public static void main(String[] args) {
	// JFrame frame = new JFrame();
	//// frame.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
	// frame.setContentPane(new ColorPickerDemo());
	// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// frame.setSize(800, 600);
	// frame.setLocationRelativeTo(null);
	// frame.setVisible(true);
	// }

	private ZHTColorPicker picker;
	private JPopupMenu menu = new JPopupMenu();

	public ColorPickerDemo(MainFrame mainFrame,IWorkspace workspace) {
		this.mainFrame=mainFrame;
		this.workspace = workspace;
		setWorkspace(this.workspace);
		picker = new ZHTColorPicker();

		menu.setLayout(new BorderLayout());
		menu.add(picker);
		this.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					menu.show(ColorPickerDemo.this, e.getX(), e.getY());
				}
			}
		});
		picker.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName() == ZHTColorPicker.SELECTEDCOLORCHANGE) {
					menu.setVisible(false);
					Color color = picker.getSelectedColor();
					System.out.println(color);
					setBackground(color);
				}
				if (evt.getPropertyName() == ZHTColorPicker.MORECOLORSELECTION) {
					menu.setVisible(false);
				}
			}
		});
		JComboBox comboBox = new ColorPickerCombobox();
		comboBox.setBorder(null);
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 4, 3));
		this.add(comboBox);
	}

	
	
	public static MainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public static IWorkspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(IWorkspace workspace) {
		this.workspace = workspace;
	}

}

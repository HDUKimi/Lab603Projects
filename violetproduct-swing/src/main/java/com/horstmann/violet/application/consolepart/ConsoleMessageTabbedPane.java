package com.horstmann.violet.application.consolepart;

import java.awt.Component;

import javax.swing.JTabbedPane;

public class ConsoleMessageTabbedPane extends JTabbedPane {
	private Component component;
	
	public ConsoleMessageTabbedPane(String title,Component component) {
		// TODO Auto-generated constructor stub	
		component = component;
	      this.add(title,component);
	}	
	public Component getComponent() {
		return component;
	}
}

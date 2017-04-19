package com.horstmann.violet.application.gui.util.wujun.SD2AutomataForPlatform.util;

import com.horstmann.violet.application.gui.util.wujun.SequenceTransfrom.SD2UppaalMain;

public class Display {
	public static void println(String value) {

		System.out.println(value);
		SD2UppaalMain.getMainFrame().getConsolePartPanel().getTextarea().append(value+"\n");
		
	}
	
	public static void println() {
		println("");
		SD2UppaalMain.getMainFrame().getConsolePartPanel().getTextarea().append("\n");
	}
	
	public static void print(String value) {
		System.out.print(value);
		SD2UppaalMain.getMainFrame().getConsolePartPanel().getTextarea().append(value);
	}
}

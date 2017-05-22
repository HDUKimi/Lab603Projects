package com.horstmann.violet.application.gui.util.wujun.TDVerification;

import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.opreationTreePane.ModelExistValidationPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.ValidationToolPanel;

public class Display {
	public static void println(String value) {

		System.out.println(value);
		
		MainFrame mainFrame=ModelExistValidationPanel.getMainFrame();
		
		if(value.contains("∆•≈‰µΩœ˚œ¢")){
			ValidationToolPanel.startProcessCount();
		}
		
		mainFrame.getConsolePartPanel().getTextarea6().append(value+"\n");
		
	}
	
	public static void println() {
		println(" ");
	}
}


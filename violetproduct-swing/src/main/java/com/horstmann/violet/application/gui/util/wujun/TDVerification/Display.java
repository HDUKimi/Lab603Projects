package com.horstmann.violet.application.gui.util.wujun.TDVerification;

import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.opreationTreePane.ModelExistValidationPanel;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.ValidationToolPanel;

public class Display {
	public static void println(String value) {

		System.out.println(value);
		
		MainFrame mainFrame=ModelExistValidationPanel.getMainFrame();
		
//		if(value.contains("∆•≈‰µΩœ˚œ¢")){
//			ValidationToolPanel.startProcessCount();
//		}
		
		mainFrame.getConsolePartPanel().getTextarea6().append(value+"\n");
		mainFrame.getConsolePartPanel().getTextarea6().setCaretPosition(mainFrame.getConsolePartPanel().getTextarea6().getDocument().getLength());
		
	}
	
	public static void process(int messagesize) {

//		ValidationToolPanel.startProcessCount(messagesize);
		
	}
	
	public static void println() {
		println(" ");
	}
}


package com.horstmann.violet.application.gui.util.wujun.TimingTransfrom;

public class Display {
	public static void println(String value) {

		System.out.println(value);
		
		if(TimingEAtoUppaal.getState()==1){
			TimingEAtoUppaal.getMainFrame().getConsolePartPanel().getTextarea2().append(value+"\n");
		}
		
	}
	
	public static void println() {
		println(" ");
		
		if (TimingEAtoUppaal.getState() == 1) {
			TimingEAtoUppaal.getMainFrame().getConsolePartPanel().getTextarea2().append("\n");
		}
	}
}

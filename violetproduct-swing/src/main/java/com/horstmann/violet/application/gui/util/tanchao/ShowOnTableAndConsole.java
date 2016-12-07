package com.horstmann.violet.application.gui.util.tanchao;

import java.util.Map;
import java.util.Set;

import javax.swing.JTextArea;

import com.horstmann.violet.application.gui.MainFrame;

public class ShowOnTableAndConsole {
     //顺序图的中间过程
	private static MainFrame mainFrame;
	public static void seqShow(Map<String,String> m1,MainFrame mainframe){
		mainFrame = mainframe;
		Set<String> set=m1.keySet();
		System.out.println("start11111111111111111111");
		for(String str:set){
			System.out.println(str);
			System.out.println(m1.get(str));
		}
		System.out.println("end11111111111111111111");
		JTextArea jTextArea=(JTextArea)mainFrame.getConsolePart().getConsoleMessageTabbedPane().getComponent();

		System.out.println(mainframe.getStepButton().getStepTwoArea().getText());
	}
	
	
	//时序图
	public static void timShow(){
		
	}
	//.....
}

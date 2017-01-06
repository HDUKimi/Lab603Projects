package com.horstmann.violet.application.gui.util.tanchao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.stepCenterTabbedPane.UppaalProcessModel;

public class ShowOnTableAndConsole {
     //顺序图的中间过程
	private static MainFrame mainFrame;
	
	private static Map<String,String> sequencetouppaalmap=new LinkedHashMap<String,String>();
	private static Set<String> sequencetouppaalset;
	
	private static Map<String,String> timingtouppaalmap=new LinkedHashMap<String,String>();
	private static Set<String> timingtouppaalset;
	
	
	public static void seqShow(Map<String,String> m1,MainFrame mainframe){
		
		mainFrame = mainframe;
		
		sequencetouppaalmap=m1;
		
		sequencetouppaalset=sequencetouppaalmap.keySet();
		System.out.println(sequencetouppaalset.size());
		for(String s:sequencetouppaalset){
			System.out.println(s);
		}
		
	}
	
	//时序图
	public static void timShow(Map<String,String> m1){

		sequencetouppaalmap = m1;

		sequencetouppaalset=sequencetouppaalmap.keySet();
		
	}
	//.....

	public static Set<String> getSequencetouppaalset() {
		return sequencetouppaalset;
	}

	public static Map<String, String> getSequencetouppaalmap() {
		return sequencetouppaalmap;
	}

	public static Map<String, String> getTimingtouppaalmap() {
		return timingtouppaalmap;
	}

	public static Set<String> getTimingtouppaalset() {
		return timingtouppaalset;
	}

	
	
	
}

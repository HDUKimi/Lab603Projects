package com.horstmann.violet.application.menu.xiaole.TimingTransfrom;

import org.dom4j.DocumentException;
//Ê±ÐòÍ¼
public class MainTransVioletTiming {

	public static void CreateTimCaseDiagramVioletXml(String source,String target) throws DocumentException {
		WriteVioletTimingDiagram writeVioletTimingGraph=new WriteVioletTimingDiagram();
		System.out.println("source start-------");
	    writeVioletTimingGraph.readEATimingDiagram(source);
	    System.out.println("source------");
		writeVioletTimingGraph.WriteVioletTiming(target);	
		System.out.println("target-------");

	}

}

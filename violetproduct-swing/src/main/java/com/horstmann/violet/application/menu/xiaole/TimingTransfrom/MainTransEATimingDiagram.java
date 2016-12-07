package com.horstmann.violet.application.menu.xiaole.TimingTransfrom;

import org.dom4j.DocumentException;

public class MainTransEATimingDiagram {

	public static void main(String[] args) throws DocumentException
	{
		
		WriteEATimingGraph writeEATimingGraph=new WriteEATimingGraph();
		writeEATimingGraph.ReadVioletXml();
		writeEATimingGraph.WriteEATimingDiagram("e:/TimingDiagram.xml");		
	}
	
}

package com.horstmann.violet.application.gui.util.wujun.SequenceTransfrom;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.xml.transform.Templates;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/*
 * 用于XStream转换成类
 */
public class WriteForXStream
{
	public static void creatXML(String Path,ArrayList<UppaalTemplate> temPlates,HashSet<String> template_instantiations ) throws IOException 
	{
		int x=30,y=30;
		Document document = DocumentHelper.createDocument();  //创建文档   
		Element automata = document.addElement("automata");
		Element templateList = automata.addElement("templateList");  
		
		Iterator<UppaalTemplate> templateIterator=temPlates.iterator();
		while(templateIterator.hasNext())
		{
			UppaalTemplate uppaalTemplate=templateIterator.next();
		    Element template=templateList.addElement("template");// 添加template节点
		    template.addElement("name").setText(uppaalTemplate.getName()); // template.name节点
		    Element locationList = template.addElement("locationList");
		    Element transitionList = template.addElement("transitionList");
		    
		    // 添加所有location节点
		    for(UppaalLocation locationData : uppaalTemplate.locations) {
		    	Element location = locationList.addElement("location");
		    	location.addElement("id").setText("loc_id"+locationData.getId());
		    	location.addElement("name").setText(""+locationData.getName());
		    	location.addElement("timeDuration").setText(""+locationData.getTimeDuration());
		    	location.addElement("init").setText(""+locationData.getInit().toString());
		    	location.addElement("final").setText(""+locationData.getFnal().toString());
		    	location.addElement("R1").setText(""+locationData.getR1());
		    	location.addElement("R2").setText(""+locationData.getR2());
		    	location.addElement("Energe").setText(""+locationData.getEnerge());
		    	location.addElement("T1").setText(""+locationData.getT1());
		    	location.addElement("T2").setText(""+locationData.getT2());
		    	
		    }
		    // 添加所有transition节点
		    for(UppaalTransition transitionData : uppaalTemplate.transitions) {
		    	Element transition = transitionList.addElement("transition");
		    	transition.addElement("id").setText("tran_id"+transitionData.getSourceId()+transitionData.getTargetId());
		    	if (transitionData.getNameText().contains("->")) {
					transitionData.setNameText(transitionData.getNameText().replaceAll("->", "."));
				}
		    	transition.addElement("name").setText("" + transitionData.getNameText());
		    	transition.addElement("source").setText("loc_id"+transitionData.getSourceId());
		    	transition.addElement("target").setText("loc_id"+transitionData.getTargetId());
		    	transition.addElement("timeDuration").setText(""+transitionData.getSEQDO());
		    	if (transitionData.getTypeAndCondition().contains("->")) {
					transitionData.setTypeAndCondition(transitionData.getTypeAndCondition().replaceAll("->", "."));
				}
		    	transition.addElement("typeAndCondition").setText(""+transitionData.getTypeAndCondition());
		    	
		    	transition.addElement("in").setText(""+transitionData.getInString());
		    	transition.addElement("out").setText(""+transitionData.getOutString());
		    	transition.addElement("RESET").setText(""+transitionData.getRESET());
		    	transition.addElement("isEndOfPath").setText(""+transitionData.isEndOfPath);
		    	if (transitionData.getTypeId() == null) {
		    		transition.addElement("typeId").setText("null");
				} else {
					transition.addElement("typeId").setText(""+transitionData.getTypeId());
				}
		    }
		    
		}	
		String doString=document.asXML();
		FileOutputStream outputStream = new FileOutputStream(Path);
		outputStream.write(doString.getBytes());
		outputStream.close();
	}  
}  


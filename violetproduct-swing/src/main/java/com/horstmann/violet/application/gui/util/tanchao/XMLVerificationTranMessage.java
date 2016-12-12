package com.horstmann.violet.application.gui.util.tanchao;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

public class XMLVerificationTranMessage {
	public static SAXReader reader;
	public static Document document;
	public static Element root;
	public static HashMap<String,String> map=new HashMap<String,String>();
    public static HashMap<String,String> getTranMessage(String path){
    	try {
			reader=new SAXReader();
			document=reader.read(path);
			root=document.getRootElement();
			Element edges=root.element("edges");
			List<Element> edgeList=edges.elements();
			for(Element ele:edgeList){
				Attribute idAttri=ele.attribute("id");
				//获得属性id的文字
				String id=idAttri.getText();
				Element label=ele.element("labelText");
				String name=label.getText();
				map.put(name, id);//k---name,value----id
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return map;
    }
    
    public static void main(String[] args) {
		HashMap<String,String> mapCopy=XMLVerificationTranMessage.getTranMessage("C:\\Users\\Admin\\Desktop\\uppaalTest1.uppaal.violet.xml");
		Set<String> sets=mapCopy.keySet();
		for(String s:sets){
			System.out.println(s);
		}
	}
}

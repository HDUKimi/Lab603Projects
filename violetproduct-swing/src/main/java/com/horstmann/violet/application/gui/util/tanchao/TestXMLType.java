package com.horstmann.violet.application.gui.util.tanchao;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class TestXMLType {

	public static void main(String[] args) {
		
		String path="D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\FunctionalTest\\EA4.1.0 功能场景1迁移覆盖TestCase.xml";
//		String path="D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\PerformanceTest\\EA性能测试-起飞高度V9TestCase.xml";
//		String path="D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\TimeTest\\EAElevator迁移覆盖TestCase.xml";
		
		try {
			TestCaseXMLType(path);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void TestCaseXMLType(String path) throws DocumentException{
		
		int type=1;
		
		SAXReader reader=new SAXReader();
		File file=new File(path);
		
		Document document=reader.read(file);
		
		Element root=document.getRootElement();
		
//		List<Element> testcaseElements=root.elements("testcase");
		Element testcaseElement=root.element("testcase");
		
		Element limitElement=null;
		limitElement=testcaseElement.element("limit");
		if(limitElement!=null){
			type=3;
		}
		else{
			
			Element outputElement=null;
			outputElement=testcaseElement.element("process").element("output");
			if(outputElement!=null){
				type=2;
			}
			
		}
		
		System.out.println(type);
		
	}
	
}

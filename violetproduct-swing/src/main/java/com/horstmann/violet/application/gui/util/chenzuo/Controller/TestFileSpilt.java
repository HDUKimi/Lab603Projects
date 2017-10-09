package com.horstmann.violet.application.gui.util.chenzuo.Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class TestFileSpilt {

	public static void main(String[] args) {
		
		TestFileSpilt testFileSpilt=new TestFileSpilt();
		
		File file=new File(System.getProperty("user.dir")+"//src//xx#1.xml");
		
		testFileSpilt.FileSpilt(file);
		
	}
	
	public File[] FileSpilt(File file){
		
		int spiltindex=0;
		String filename=file.getName().replaceAll(".xml", "");
		File[] files=new File[2];
		
		SAXReader reader = new SAXReader();
		
		try {
			
			Document dom1 = reader.read(file);
			Document dom2 = reader.read(file);
			
			Element TCS1=dom1.getRootElement();
			List<Element> testcaseElements1=TCS1.elements("testcase");
			Element TCS2=dom2.getRootElement();
			List<Element> testcaseElements2=TCS2.elements("testcase");
			
			spiltindex=testcaseElements1.size()/2;
			
			for(int i=0;i<testcaseElements1.size()-spiltindex;i++){
				testcaseElements2.remove(0);
			}
			
			for(int i=0;i<spiltindex;i++){
				testcaseElements1.remove(testcaseElements1.size()-1);
			}
			
			String baseUrl = "D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\";
			
			DomWriteFile(dom1, baseUrl+filename+"_1.xml");
			DomWriteFile(dom2, baseUrl+filename+"_2.xml");
			
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return files;
		
	}
	
	public void DomWriteFile(Document dom, String path){
		
		try {
			// 定义输出流的目的地
			FileWriter fw = new FileWriter(path);

			// 定义输出格式和字符集
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");

			// 定义用于输出xml文件的XMLWriter对象
			XMLWriter xmlWriter = new XMLWriter(fw, format);
			xmlWriter.write(dom);// *****
			xmlWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}

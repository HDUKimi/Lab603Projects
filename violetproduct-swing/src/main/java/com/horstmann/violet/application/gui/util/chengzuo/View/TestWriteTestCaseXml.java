package com.horstmann.violet.application.gui.util.chengzuo.View;

import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class TestWriteTestCaseXml {

	public static void main(String[] args) {
		
		Document doc = DocumentHelper.createDocument();
		Element TCS=doc.addElement("TCS");
		
		for (int i = 0; i < 10; i++) {
			Element testcase = TCS.addElement("testcase");
			for (int j = 0; j < 5; j++) {
				Element process = testcase.addElement("process");
				Element operation = process.addElement("operation");
				Element input = process.addElement("input");

				operation.setText("set_pwm");
				input.setText("pitch_pwm=1462,has_new_input=True");
			}

		}

		try {
			// �����������Ŀ�ĵ�
			String path="D:\\789.xml";
			FileWriter fw = new FileWriter(path);

			// ���������ʽ���ַ���
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");

			// �����������xml�ļ���XMLWriter����
			XMLWriter xmlWriter = new XMLWriter(fw, format);
			xmlWriter.write(doc);// *****
			xmlWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void outputXml(Document doc, String filename) {
	    try {
	      //�����������Ŀ�ĵ�
	      FileWriter fw = new FileWriter(filename);
	       
	      //���������ʽ���ַ���
	      OutputFormat format 
	        = OutputFormat.createPrettyPrint();
	      format.setEncoding("UTF-8");
	       
	      //�����������xml�ļ���XMLWriter����
	      XMLWriter xmlWriter 
	        = new XMLWriter(fw, format);
	      xmlWriter.write(doc);//*****	      
	      xmlWriter.close(); 
	    } catch (IOException e) {
	      e.printStackTrace();
	    }   
	  }
	
}

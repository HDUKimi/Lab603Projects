package com.horstmann.violet.application.gui.util.ckt.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.horstmann.violet.application.gui.util.ckt.handle.ATDTR__1;
import com.horstmann.violet.application.gui.util.ckt.handle.Automatic;
import com.horstmann.violet.application.gui.util.ckt.handle.GetAutomatic;
import com.horstmann.violet.application.gui.util.ckt.handle.Get_inequality__1;
import com.horstmann.violet.application.gui.util.ckt.handle.IPR__1;
import com.horstmann.violet.application.gui.util.ckt.handle.StateCoverage__1;

public class GetTimeXML {

	public static void main(String[] args) throws Exception {
		//String xml = "EASmallTime4ForXStream.xml";
		String xml = "EASmallTime5ForXStream.xml";
		Automatic automatic = GetAutomatic.getAutomatic(xml);// ���ԭʼ��ʱ���Զ���
		Automatic new_automatic = IPR__1.iPR(automatic);// ��ò�ֺ��ʱ���Զ���
		Automatic aTDRTAutomatic = ATDTR__1.aTDRT(new_automatic, automatic);// ���ȥ������ʱ��Ǩ�ƺ��ʱ���Զ���
		ArrayList<Automatic> testCase = StateCoverage__1.testCase(aTDRTAutomatic);// �������״̬���ǵĳ����������
		ArrayList<ArrayList<String>> all_inequalitys = Get_inequality__1.get_AllInequalitys(testCase);// ÿ���������������һ������ʽ��

		System.out.println("�ܹ�" + all_inequalitys.size() + "������ʽ��");
		int e = 1;
		for (ArrayList<String> inequalitys : all_inequalitys) {
			System.out.println("��" + e + "������ʽ��");
			for (String s : inequalitys) {
				System.out.println(s);
			}
			System.out.println("***************");
			e++;
		}
		// 1������document���󣬴�������xml�ĵ�
		Document dom = DocumentHelper.createDocument();
		// 2���������ڵ�TCS
		org.dom4j.Element tcs = dom.addElement("TCS");
		// 3����TCS�ڵ������version����
		
		for(int i=0;i<testCase.size();i++){
			// 4�������ӽڵ㼰�ڵ�����
			Element testcase = tcs.addElement("testcase");
			for(int j=0;j<testCase.get(i).getTransitionSet().size();j++){
				//��ӽڵ�
				Element process = testcase.addElement("process");				
				Element operation = process.addElement("operation");
				operation.setText(testCase.get(i).getTransitionSet().get(j).getName());				
				Element input = process.addElement("input");
				input.setText("null");
				Element time = process.addElement("time");
				time.setText(testCase.get(i).getTransitionSet().get(j).getTranTimeName());
			}	
			Element limit = testcase.addElement("limit");
			Element operation = limit.addElement("operation");
			String s = null;
			for(int k=0;k<all_inequalitys.get(i).size();k++){
				s = all_inequalitys.get(i).get(0).toString();
				if((all_inequalitys.get(i).size()>1)&&(k>0)){
					s = s + "," + all_inequalitys.get(i).get(k).toString();
				}
				s = s.replace("&lt;", "<").replace("&gt;", ">").replace("&lt;=", "<=").replace("&gt;=", ">=");
				//String s = all_inequalitys.get(i).get(k);
				
			}
			operation.setText(s);			
		}	
		
		
		OutputFormat format = OutputFormat.createPrettyPrint();
        //6������xml�ļ�
		File file = new File("E:\\XML\\EASmallTime5ForXStream-time.xml");
		//formatXML(file);
		XMLWriter writer;
		
		try {
			writer = new XMLWriter(new FileOutputStream(file), format);
			writer.write(dom);
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		  
	}
	/*public static String formatXML(File file) throws Exception {  
        SAXReader reader = new SAXReader();  
        Document document = reader.read(new FileReader(file));  
        String requestXML = null;  
        XMLWriter xw = null;  
        if (document != null) {  
          try {  
              OutputFormat format = OutputFormat.createPrettyPrint();   
              format.setEncoding("UTF-8");   
              StringWriter sw = new StringWriter();   
              xw = new XMLWriter(sw, format);   
              xw.setEscapeText(false);  
              xw.write(document);  
              requestXML = sw.toString();  
              xw.flush();   
          } finally {  
            if (xw != null) {  
              try {  
                xw.close();  
              } catch (IOException e) {  
              }  
            }  
          }  
        }  
        return requestXML;  
    }*/
	
	public static void produceXML(String path, ArrayList<Automatic> testcaselist) {

		ArrayList<ArrayList<String>> all_inequalitys = Get_inequality__1.get_AllInequalitys(testcaselist);// ÿ���������������һ������ʽ��

		// 1������document���󣬴�������xml�ĵ�
		Document dom = DocumentHelper.createDocument();
		// 2���������ڵ�TCS
		org.dom4j.Element tcs = dom.addElement("TCS");
		// 3����TCS�ڵ������version����

		for (int i = 0; i < testcaselist.size(); i++) {
			// 4�������ӽڵ㼰�ڵ�����
			Element testcase = tcs.addElement("testcase");
			for (int j = 0; j < testcaselist.get(i).getTransitionSet().size(); j++) {
				// ��ӽڵ�
				Element process = testcase.addElement("process");
				Element operation = process.addElement("operation");
				operation.setText(testcaselist.get(i).getTransitionSet().get(j).getName());
				Element input = process.addElement("input");
				input.setText("null");
				Element time = process.addElement("time");
				time.setText(testcaselist.get(i).getTransitionSet().get(j).getTranTimeName());
			}
			Element limit = testcase.addElement("limit");
			Element operation = limit.addElement("operation");
			String s = null;
			for (int k = 0; k < all_inequalitys.get(i).size(); k++) {
				s = all_inequalitys.get(i).get(0).toString();
				if ((all_inequalitys.get(i).size() > 1) && (k > 0)) {
					s = s + "," + all_inequalitys.get(i).get(k).toString();
				}
				s = s.replace("&lt;", "<").replace("&gt;", ">").replace("&lt;=", "<=").replace("&gt;=", ">=");
				// String s = all_inequalitys.get(i).get(k);

			}
			operation.setText(s);
		}

		OutputFormat format = OutputFormat.createPrettyPrint();
		// 6������xml�ļ�
		File file = new File(path);
		// formatXML(file);
		XMLWriter writer;

		try {
			writer = new XMLWriter(new FileOutputStream(file), format);
			writer.write(dom);
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}

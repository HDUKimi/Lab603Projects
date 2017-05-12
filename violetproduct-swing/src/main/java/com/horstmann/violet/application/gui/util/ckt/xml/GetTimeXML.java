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
		Automatic automatic = GetAutomatic.getAutomatic(xml);// 获得原始的时间自动机
		Automatic new_automatic = IPR__1.iPR(automatic);// 获得拆分后的时间自动机
		Automatic aTDRTAutomatic = ATDTR__1.aTDRT(new_automatic, automatic);// 获得去除抽象时间迁移后的时间自动机
		ArrayList<Automatic> testCase = StateCoverage__1.testCase(aTDRTAutomatic);// 获得满足状态覆盖的抽象测试序列
		ArrayList<ArrayList<String>> all_inequalitys = Get_inequality__1.get_AllInequalitys(testCase);// 每个抽象测试序列有一个不等式组

		System.out.println("总共" + all_inequalitys.size() + "个不等式组");
		int e = 1;
		for (ArrayList<String> inequalitys : all_inequalitys) {
			System.out.println("第" + e + "个不等式组");
			for (String s : inequalitys) {
				System.out.println(s);
			}
			System.out.println("***************");
			e++;
		}
		// 1、创建document对象，代表整个xml文档
		Document dom = DocumentHelper.createDocument();
		// 2、创建根节点TCS
		org.dom4j.Element tcs = dom.addElement("TCS");
		// 3、向TCS节点中添加version属性
		
		for(int i=0;i<testCase.size();i++){
			// 4、生成子节点及节点内容
			Element testcase = tcs.addElement("testcase");
			for(int j=0;j<testCase.get(i).getTransitionSet().size();j++){
				//添加节点
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
        //6、生成xml文件
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

		ArrayList<ArrayList<String>> all_inequalitys = Get_inequality__1.get_AllInequalitys(testcaselist);// 每个抽象测试序列有一个不等式组

		// 1、创建document对象，代表整个xml文档
		Document dom = DocumentHelper.createDocument();
		// 2、创建根节点TCS
		org.dom4j.Element tcs = dom.addElement("TCS");
		// 3、向TCS节点中添加version属性

		for (int i = 0; i < testcaselist.size(); i++) {
			// 4、生成子节点及节点内容
			Element testcase = tcs.addElement("testcase");
			for (int j = 0; j < testcaselist.get(i).getTransitionSet().size(); j++) {
				// 添加节点
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
		// 6、生成xml文件
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

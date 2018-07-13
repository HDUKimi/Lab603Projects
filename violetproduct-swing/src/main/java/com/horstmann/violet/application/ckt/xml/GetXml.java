package com.horstmann.violet.application.ckt.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.horstmann.violet.application.ckt.entity.*;
import com.horstmann.violet.application.ckt.random.TestCases;

public class GetXml {

	public static void main(String[] args) throws Exception {
		//String fileOfStart = "writeWP.markov.violet2.xml";
		String fileOfStart = "writeWP.markov.violet.1.xml";
		
		getXML(fileOfStart);
	}
	public static void getXML(String fileOfStart) throws Exception{
		//String fileOfStart = "writeWP.markov.violet2.xml";
		List<Route> testcases = TestCases.getTestCase(fileOfStart);
		// 1������document���󣬴�������xml�ĵ�
		Document dom = DocumentHelper.createDocument();
		// 2���������ڵ�TCS
		Element tcs = dom.addElement("TCS");
		// 3����TCS�ڵ������version����
		for(Route tc : testcases){
			// 4�������ӽڵ㼰�ڵ�����
			Element testcase = tcs.addElement("testcase");
			String id = tc.getId()+"";//id����ڼ�����������
			testcase.addAttribute("id", id);
			testcase.addAttribute("RouteResult", tc.getRouteResult());
			for(Transition t : tc.getTransitionList()){
				//��ӽڵ�
				Element process = testcase.addElement("process");
				process.addAttribute("expectResult", t.getExpectResult());//������ʶǨ�Ƶ��������
				Element operation = process.addElement("operation");
				operation.setText(t.getName());
				Element input = process.addElement("input");
				if(t.getResultOfCondition().length()>0){
					input.setText(t.getResultOfCondition());
				}else{
					input.setText("null");
				}
				
			}
		}
		OutputFormat format = OutputFormat.createPrettyPrint();
        //6������xml�ļ�
		File file = new File("E:\\XML\\writeWP.markov.violet.xml");
		XMLWriter writer;
		try {
			writer = new XMLWriter(new FileOutputStream(file), format);
			writer.write(dom);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

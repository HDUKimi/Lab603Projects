package com.horstmann.violet.application.ckt.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.horstmann.violet.application.ckt.entity.Route;
import com.horstmann.violet.application.ckt.entity.Transition;

public class SaveTestCase {

	public static void ToXML(String path, List<Route> testcases) {
		// 1������document���󣬴�������xml�ĵ�
		Document dom = DocumentHelper.createDocument();
		// 2���������ڵ�TCS
		Element tcs = dom.addElement("TCS");
		// 3����TCS�ڵ������version����
		for (Route tc : testcases) {
			// 4�������ӽڵ㼰�ڵ�����
			Element testcase = tcs.addElement("testcase");
			String id = tc.getId() + "";// id����ڼ�����������
			testcase.addAttribute("id", id);
			testcase.addAttribute("RouteResult",
					tc.getTransitionList().get(tc.getTransitionList().size() - 1).getExpectResult());
			Element data = testcase.addElement("data");
			data.setText(getAllData(tc));

			for (Transition t : tc.getTransitionList()) {
				// ��ӽڵ�
				Element process = testcase.addElement("process");
				process.addAttribute("expectResult", t.getExpectResult());// ������ʶǨ�Ƶ��������
				Element operation = process.addElement("operation");
				operation.setText(t.getName());
				Element input = process.addElement("input");
				if (t.getResultOfCondition().length() > 0) {
					input.setText(t.getResultOfCondition());
				} else {
					input.setText("null");
				}

			}
		}
		OutputFormat format = OutputFormat.createPrettyPrint();
		// 6������xml�ļ�
//		File file = new File("E:\\XML\\connect.markov.violet.xml");
		File file = new File(path);
		XMLWriter writer;
		try {
			writer = new XMLWriter(new FileOutputStream(file), format);
			writer.write(dom);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getAllData(Route tc){
		String allData = new String();
		ArrayList<String> names = new ArrayList<String>();
		for(Transition t : tc.getTransitionList()){
			if(!(names.contains(t.getName().trim()))){
				if(t.getResultOfCondition().length()>0){
					allData = allData + "," + t.getResultOfCondition();	
				}
				names.add(t.getName().trim());
			}	
		}
		if(allData.length()>1){
			allData = allData.trim().substring(1, allData.length());
		}else{
			allData = "null";
		}
		return allData;
	}

}

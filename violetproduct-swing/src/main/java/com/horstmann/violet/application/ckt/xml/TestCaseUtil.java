package com.horstmann.violet.application.ckt.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.horstmann.violet.application.ckt.entity.Route;
import com.horstmann.violet.application.ckt.entity.Transition;

public class TestCaseUtil {
	
	public static void main(String[] args) {
		
		String path="C:\\ModelDriverProjectFile\\TestCase\\readWP.testcase.violet.xml";
		
		List<Route> routes=ToRoute(path);
		
		System.out.println(routes.size());
		
	}
	
	public static List<Route> ToRoute(String path){
		
		List<Route> testcases=new ArrayList<>();
		
		File file=new File(path);
		
		SAXReader reader = new SAXReader();   
		Document doc = null;
		try {
			doc = reader.read(file);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		Element root = doc.getRootElement();  
		
		List<Element> nodes = root.elements("testcase");
		for(Element node:nodes){
			Route route=new Route();
			route.setId(Integer.parseInt(node.attribute("id").getValue().trim()));
			route.setRouteResult(node.attribute("RouteResult").getValue().trim());
			
			List<Transition> transitions=new ArrayList<>();
			
			List<Element> tnodes=node.elements("process");
			for(Element tnode:tnodes){
				Transition transition=new Transition();
				
				transition.setExpectResult(tnode.attribute("expectResult").getValue().trim());
				transition.setName(tnode.element("operation").getText());
				transition.setResultOfCondition(tnode.element("input").getText());
				
				transitions.add(transition);
			}
			
			route.setTransitionList(transitions);
			
			testcases.add(route);
		}
		
		return testcases;
		
	}

	public static void ToXML(String path, List<Route> testcases) {
		// 1、创建document对象，代表整个xml文档
		Document dom = DocumentHelper.createDocument();
		// 2、创建根节点TCS
		Element tcs = dom.addElement("TCS");
		// 3、向TCS节点中添加version属性
		for (Route tc : testcases) {
			// 4、生成子节点及节点内容
			Element testcase = tcs.addElement("testcase");
			String id = tc.getId() + "";// id代表第几个测试用例
			testcase.addAttribute("id", id);
			testcase.addAttribute("RouteResult",
					tc.getTransitionList().get(tc.getTransitionList().size() - 1).getExpectResult());
			Element data = testcase.addElement("data");
			data.setText(getAllData(tc));

			for (Transition t : tc.getTransitionList()) {
				// 添加节点
				Element process = testcase.addElement("process");
				process.addAttribute("expectResult", t.getExpectResult());// 用来标识迁移的期望结果
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
		// 6、生成xml文件
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

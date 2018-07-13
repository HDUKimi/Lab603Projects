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

import com.horstmann.violet.application.ckt.entity.*;
import com.horstmann.violet.application.ckt.random.GetResult;
import com.horstmann.violet.application.ckt.random.ReadMarkov;
import com.horstmann.violet.application.ckt.random.RouletteSelection;
import com.horstmann.violet.application.ckt.random.TestCases;

/**
 * 获得的XML格式为
 * <TCS>
  <testcase id="1" RouteResult="2">
    <data>baseStream=true,use_int=true</data>
    <process expectResult="true">
      <operation>m1</operation>
      <input>baseStream=true</input>
    </process>
    <process expectResult="true">
      <operation>m3</operation>
      <input>use_int=true</input>
    </process>
   </testcase>
   </TCS>
 * 生成最终XML所用类
 * @author ckt
 *
 */
public class GetXML1 {

	public static void main(String[] args) throws Exception {
		//String fileOfStart = "writeWP.markov.violet2.xml";
		//String fileOfStart = "writeWP.markov.violet3.xml";//第1个成功模型
		//String fileOfStart = "readWP.markov.violet.xml";//第2个模型
		String fileOfStart = "connect.markov.violet.xml";//第3个模型
//		getXML(fileOfStart);
		
		
		//第1步：将xml文件转换为Markov模型
		Markov markov = ReadMarkov.readMarkov(fileOfStart);
		
//		for(State state:markov.getStates()){
//			System.out.println(state.toString());
//		}
//		for(Transition transition:markov.getTransitions()){
//			System.out.println(transition.toString());
//		}
		
		//第2步：利用轮盘赌生成随机路径
		List<Route> routes = RouletteSelection.randomRoute(markov, 100);
		
		for(Route route:routes){
			System.out.println(route.toString());
			for(Transition transition:route.getTransitionList()){
				System.out.println(transition.toString());
			}
		}
		
		//第3步：为路径上的每条迁移实例化
		RouteInstantiate.instantiation(routes);
		
		SaveTestCase.ToXML("E:\\XML\\connect.markov.violet.xml", routes);
		
	}
	public static void getXML(String fileOfStart) throws Exception{
		//String fileOfStart = "writeWP.markov.violet2.xml";
		List<Route> testcases = TestCases.getTestCase(fileOfStart);
		
		/*//第3步：为路径上的每条迁移实例化
				System.out.println("============实例化迁移==========");
				for(Route route : testcases){
					for(Transition t : route.getTransitionList()){
						//System.out.println("=====" + t.getCondition());
						String resultOfTransition = GetResult.getResult(t.getCondition());
						//System.out.println("==========迁移上解为：" + resultOfTransition);
						t.setResultOfCondition(resultOfTransition);
					}
					TestCases.systemRoute(route);//打印路径上的信息
				}*/
		
		
		// 1、创建document对象，代表整个xml文档
		Document dom = DocumentHelper.createDocument();
		// 2、创建根节点TCS
		Element tcs = dom.addElement("TCS");
		// 3、向TCS节点中添加version属性
		for(Route tc : testcases){
			// 4、生成子节点及节点内容
			Element testcase = tcs.addElement("testcase");
			String id = tc.getId()+"";//id代表第几个测试用例
			testcase.addAttribute("id", id);
			testcase.addAttribute("RouteResult", tc.getTransitionList().get(tc.getTransitionList().size()-1).getExpectResult());
			Element data = testcase.addElement("data");
			data.setText(getAllData(tc));
			
			for(Transition t : tc.getTransitionList()){
				//添加节点
				Element process = testcase.addElement("process");
				process.addAttribute("expectResult", t.getExpectResult());//用来标识迁移的期望结果
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
        //6、生成xml文件
		File file = new File("E:\\XML\\connect.markov.violet.xml");
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

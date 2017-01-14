package com.horstmann.violet.application.gui.util.chengzuo.View;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.horstmann.violet.application.gui.util.chengzuo.Bean.TestCase;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.myProcess;

public class TestReadTestCaseXml {

	public static void main(String[] args) {
		
		int i=1,j=1;
		
		List<TestCase> testcaseList = new ArrayList<TestCase>();
		List<myProcess> processList = new ArrayList<myProcess>();
		
		SAXReader reader = new SAXReader();
		
		String path="D:\\rc_loopForXStream1.0.1.xml";
		
		File file=new File(path);
		
		try {
			
			Document dom = reader.read(file);
			
			Element TCS=dom.getRootElement();
			List<Element> testcaseElements=TCS.elements("testcase");
			for(Element testcase:testcaseElements){
				
//				System.out.println(i++);
				
				List<Element> processElements=testcase.elements("process");
				
				for(Element process:processElements){
					
//					System.out.println(j++);
					
					Element operation=process.element("operation");
//					System.out.println(operation.getData());
					
					Element input=process.element("input");
//					System.out.println(input.getData());
					
					myProcess p = new myProcess();
					p.setProcessID(j++);
					p.setProcessName(operation.getData().toString());
					p.setProcessParam(input.getData().toString());
//					p.setProcessStatus(processStatus);
//					p.setProcessExec(processExec);

					processList.add(p);
					
				}
				
				j=1;
				
				TestCase tc = new TestCase();
				tc.setTestCaseID(String.valueOf(i++));
				tc.setProcessList(processList);
//				tc.setState(state);
//				tc.setResult(result);

				testcaseList.add(tc);
				
				processList = new ArrayList<myProcess>();
				
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(testcaseList.size());
		
		for(TestCase tc:testcaseList){
			
			System.out.println(tc);
			
		}
		
	}
	
}

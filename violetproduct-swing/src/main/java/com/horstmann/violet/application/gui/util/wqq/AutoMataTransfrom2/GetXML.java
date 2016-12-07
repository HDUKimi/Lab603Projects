package com.horstmann.violet.application.gui.util.wqq.AutoMataTransfrom2;
//??????????? xml???
import java.util.ArrayList;
import org.junit.Test;

import com.horstmann.violet.application.gui.util.yangjie.Mathematica2;
import com.wolfram.jlink.KernelLink;
import com.wolfram.jlink.MathLinkException;
import com.wolfram.jlink.MathLinkFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class GetXML {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String xml="UAV2ForXStream.xml";
		//String xml="read_radioForXStream.xml";
		String xml="read_radio3ForXStream.xml";//tcs1.xml
		//String xml="loop2ForXStream.xml";//tcs2.xml
		
		Automatic auto=GetAutomatic.getAutomatic(xml);//???????????????

		ArrayList<Automatic> testCase=StateCoverage__1.testCase(auto);//????????????????????????
		
		System.out.println("????????????????"+testCase.size());
		int i = 1;
		String s=null;
		String s1=null;
		// 1??????document???????????xml???
		Document dom = DocumentHelper.createDocument();
		// 2???????????TCS
		org.dom4j.Element tcs = dom.addElement("TCS");
		// 3????TCS????????version????
		for(Automatic a:testCase){		
			System.out.println();
			System.out.println("????????????:"+a.getName());
			System.out.println("============??"+i+"?????????????============");
			
			// 4?????????????????
			Element testcase = tcs.addElement("testcase");
	
			for(Transition tran:a.getTransitionSet()){
				//System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

				//System.out.println("--------->????????--------> "+tran.getName());
				//System.out.println(tran.getSource()+"---->"+tran.getTarget()+"????? "+tran.getEventSet());
			//?????????????	
			//System.out.println("?????"+tran.getEventSet());//????????
			//???????????????
				String bds1=Get_str.get_bds(tran.getEventSet().toString());
				//System.out.println("????????:"+bds1);  //????????????????
				String cs1=Get_str.get_cs(bds1);
				//System.out.println("???????:"+cs1);//??????????????????????
				//System.out.println("bds---------->"+bds);
          //???????????????
				String boolbds=Get_str.get_bool_bds(tran.getEventSet().toString());
				//System.out.println("?????????:"+boolbds);//????????????????
				String boolcs=Get_str.get_bool_cs(boolbds);
				//System.out.println("????????"+boolcs);//??????????????????????
		  //==0??????????? ==???=
				String bds0=Get_str.get_bds_0(tran.getEventSet().toString().replace("(", "").replace(")", ""));
				//System.out.println("==0????????"+bds0);
				//
				/*if(bds0!=null){
					System.out.println("==0???????????"+bds0);
				}*/
				
				//?????
				Element process = testcase.addElement("process");
				Element operation = process.addElement("operation");
				if(tran.getName().contains("(")){
					int index11=tran.getName().indexOf("(");
					String sss=tran.getName().substring(0,index11);
					operation.setText(sss);
				}
				else{
					operation.setText(tran.getName());
				}
				
				Element input = process.addElement("input");
				//
			//????mma??????????
				if(((bds1==null)&&(cs1==null))&&((boolbds==null)&&(boolcs==null))){
					System.out.println("???????????null");
					input.setText("null");
				}
				if((bds1!=null)&&(cs1!=null)){
					System.out.println("*******????????*******"+bds1);
					System.out.println("*******???????*******"+cs1);
					String solution1 = Mathematica2.getSolution2(bds1, cs1);
					s=solution1.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
					System.out.println("?????????????"+solution1);
					//System.out.println("?????????????");
					//System.out.println(solution1);
					//System.out.println("##########"+s);
				}
				if((boolbds!=null)&&(boolcs!=null)){
					String solution2 = Mathematica2.getSolution3(boolbds, boolcs);
					s1=solution2.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
					System.out.println("????????????"+solution2);
					//System.out.println("????????????");
					//System.out.println(solution2);
					//System.out.println("##########"+s1);
				}
				//==0??????????? ==???=	
				if(bds0!=null){
					System.out.println("==0???????????"+bds0);
					if((bds1!=null)&&(cs1!=null)&&(boolbds!=null)&&(boolcs!=null)){
						input.setText(s+","+s1+","+bds0);
					}
					if((bds1!=null)&&(cs1!=null)&&(boolbds==null)&&(boolcs==null)){
						input.setText(s+","+bds0);
					}
					if((bds1==null)&&(cs1==null)&&(boolbds!=null)&&(boolcs!=null)){
						input.setText(s1+","+bds0);
					}
				}
				else{
					if((bds1!=null)&&(cs1!=null)&&(boolbds!=null)&&(boolcs!=null)){
						input.setText(s+","+s1);
					}
					if((bds1!=null)&&(cs1!=null)&&(boolbds==null)&&(boolcs==null)){
						input.setText(s);
					}
					if((bds1==null)&&(cs1==null)&&(boolbds!=null)&&(boolcs!=null)){
						input.setText(s1);
					}
				}
				
			//????mma??????????
				//String solution1 = Mathematica2.getSolution2(bds1, cs1);
				//String solution2 = Mathematica2.getSolution3(boolbds, boolcs);
				
				
				
				//System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
				
			}
			System.out.println("============??"+i+"??????????????============");
			i++;
		}
		OutputFormat format = OutputFormat.createPrettyPrint();
		// 6??????xml???
	    File file = new File("C:\\Users\\Administrator\\Desktop\\read_radio.xml");
		//File file = new File("E:\\xml\\tcs4.xml");
		//File file = new File("E:\\xml\\tcs2.xml");
		
	    XMLWriter writer;
		try {
			writer = new XMLWriter(new FileOutputStream(file), format);
			writer.write(dom);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}

}


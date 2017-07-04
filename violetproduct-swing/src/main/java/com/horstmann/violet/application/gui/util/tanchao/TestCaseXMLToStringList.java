package com.horstmann.violet.application.gui.util.tanchao;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class TestCaseXMLToStringList {
   private List<String> list=new ArrayList<String>();
   private BufferedReader in=null;
   private BufferedWriter out=null;
   
   //��xml �ļ� �����ַ����ļ���
	public List<String> getStrings(String name){
		try {
			in=new BufferedReader(new FileReader(name));
			String lineS="";//��ǰ�е��ַ���
			String s="";
			while((lineS=in.readLine())!=null){
				if(!lineS.contains("?")&&!lineS.contains("TCS")){
					if(lineS.contains("</testcase>")){
						s+=lineS;
						s+="\n";
						list.add(s);
						s="";
					}
					else{
					   s+=lineS;
					   s+="\n";
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	//����list�ļ�������xml�ļ�
	public void createXml(List<String> listS,String path){
		try {
//		    out=new DataOutputStream(new FileOutputStream("C:\\Users\\Admin\\Desktop\\Case.xml"));
			out=new BufferedWriter(new FileWriter(path));
			out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+"\n");
			out.write("<TCS>");
			System.out.println(listS.size());
			for(String s:listS){
				out.write(s);
				out.flush();
			}
			out.write("</TCS>");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		TestCaseXMLToStringList t=new TestCaseXMLToStringList();
	    List<String> list=t.getStrings("C:\\Users\\Admin\\Desktop\\EA4.1.0 ���ܳ���1״̬����TestCase.xml");
	    System.out.println(list.get(0));
//	    t.createXml(list);
		  
	  
	}
}

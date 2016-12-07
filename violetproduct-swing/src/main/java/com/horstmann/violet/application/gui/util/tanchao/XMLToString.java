package com.horstmann.violet.application.gui.util.tanchao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//将xml文件的内容读取,并且输出
public class XMLToString {
     @SuppressWarnings("null")
	public static StringBuilder getXmlStringContent(String path){
    	 String s=null;
    	 BufferedReader in=null;
     	 StringBuilder sb=new StringBuilder();
    	 try {
			in=new BufferedReader(new FileReader(new File(path)));
		    while((s=in.readLine())!=null){
		    	sb.append(s);
		    	sb.append(System.getProperty("line.separator"));
		    }
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
    	 return sb;
     }
    public static void main(String[] args) {
		String path="C:/Users/Admin/Desktop/111.timing.violet.xml";
		StringBuilder sb=getXmlStringContent(path);
		System.out.println(sb);
	}
}

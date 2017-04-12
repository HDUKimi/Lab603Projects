package com.horstmann.violet.application.gui.util.tanchao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XMLCopy {

	public static void SourceCopyToTarget(String sourcepath, String targetpath) {
		// TODO Auto-generated method stub
		
		File sourcefile=new File(sourcepath);
		File targetfile=new File(targetpath);
		
		try {
			SAXReader saxReader = new SAXReader(); // 用来读取xml文档
			Document document = saxReader.read(sourcefile);
			
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("utf-8");//指定编码
			
			XMLWriter writer = new XMLWriter(new FileOutputStream(targetfile), format);
			writer.write(document);
			writer.flush();
			writer.close();
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

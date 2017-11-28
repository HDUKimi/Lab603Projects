package com.horstmann.violet.application.gui.util.tanchao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class SaveText {
	
	private static File file;
	private static Writer writer;
	
	public static void init(String path){
		file=new File(path);
		try {
			writer=new FileWriter(file,false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void End(){
		try {
			writer.flush();//刷新输出流，强制清空缓存区
			writer.close();
			file.exists();
			
			file=null;
			writer=null;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void SaveWord(String word){
		
		try {
			writer.write(word);
			writer.write("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void SaveFenGe(){
		try {
			writer.write("----------------------------------------------------------------------");
			writer.write("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

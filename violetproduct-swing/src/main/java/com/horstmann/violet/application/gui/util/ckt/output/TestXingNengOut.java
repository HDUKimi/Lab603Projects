package com.horstmann.violet.application.gui.util.ckt.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.horstmann.violet.application.gui.util.ckt.handle.AddType;
import com.horstmann.violet.application.gui.util.ckt.handle.Automatic;
import com.horstmann.violet.application.gui.util.ckt.handle.GetAutomatic;
import com.horstmann.violet.application.gui.util.ckt.handle.State;
import com.horstmann.violet.application.gui.util.ckt.handle.Transition;
import com.horstmann.violet.application.gui.util.ckt.testcase.PerformanceXML;
import com.horstmann.violet.application.gui.util.wj.util.GeneratePath;

public class TestXingNengOut {
	
	public static int index=1;

	public static void main(String[] args) throws Exception {
		
		File f=new File("D:\\test.txt");
		Writer w=new FileWriter(f,false);
		
//		String xml = "D:\\xml\\UAVForXStreamGAODU.xml";//性能测试，测高度，从Excel中读取高度值
		
		String xml="D:\\ModelDriverProjectFile\\UPPAL\\3.Abstract_TestCase\\EA性能测试-起飞高度V2ForXStream.xml";
		
		Automatic auto=GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
		
		Automatic type_auto=AddType.addType(auto);
		
		System.out.println(type_auto.getClockSet().size());
		
		writeStr(w,type_auto.getTransitionSet().size()+" - - "+type_auto.getStateSet().size());
		
		for(Transition t:type_auto.getTransitionSet()){
			writeStr(w,t.toString());
		}
		
		for(State s:type_auto.getStateSet()){
			writeStr(w,s.toString());
		}
		
		writeStr(w,"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		writeStr(w,"1---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		writeStr(w,"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		Automatic a=GeneratePath.getPerformPathFromAutomatic(type_auto);//获得满足状态覆盖的抽象测试序列
//		Automatic type_a=AddType.addType(a);
		
		writeStr(w,a.getTransitionSet().size()+" - - "+a.getStateSet().size());
		
		index=1;
		for(Transition t:a.getTransitionSet()){
			writeStr(w,(index++)+"  "+t.toString());
		}
		
		for(State s:a.getStateSet()){
			writeStr(w,s.toString());
		}
		
		writeStr(w,"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		writeStr(w,"2---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		writeStr(w,"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		ArrayList<Automatic> listauto=new ArrayList<>();
		listauto.add(a);
		ArrayList<Automatic> collectLimit = forPlatform.collectLimit(listauto);
		
		writeStr(w,collectLimit.size()+"");
		
		for(Automatic aut:collectLimit){
			index=1;
			writeStr(w,aut.getTransitionSet().size()+"");
			for(Transition t:aut.getTransitionSet()){
				writeStr(w,(index++)+"  "+t.toString1());
			}
		}
		
		writeStr(w,"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		writeStr(w,"3---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		writeStr(w,"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
//		Automatic perauto=PerformanceXML.getPerformResultFromAutomatic(a);
////		List<List<String>> result=PerformanceXML.cases;
//		
//		writeStr(w,perauto.getTransitionSet().size()+"");
//		
//		index=1;
//		
//		for(Transition t:perauto.getTransitionSet()){
//			
//			String name;
//			name=t.getName();
//			if(name.indexOf("(")>0){
//				name=name.substring(0, name.indexOf("("));
//			}
//			
//					
//			String result=t.getResult().toString();
//			result=result.replaceAll("\\[|]", "");
//			result=result.replace("%null", "");
//			result=result.replace(name, "");
//			result=result.replace("%", "");
//			if(result.equals("")){
//				result=null;
//			}
//			
//			
//			writeStr(w,(index++)+"  "+result+" "+t.getResult().size()+" "+t.getName());
//		}
//		
//		writeStr(w,"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//		writeStr(w,"4---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//		writeStr(w,"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//		
//		List<List<String>> listcases=new ArrayList<>();
//		for(Transition t:perauto.getTransitionSet()){
//			listcases.add(t.getResult());
//		}
//		
//		writeStr(w,listcases.size()+"");
//		
//		String path="E:\\XML\\UAVForXStreamGAODU+border+path+perform.xml";
//		
//		PerformanceXML.produceXML(listcases, path);
//		
//		writeStr(w,"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//		writeStr(w,"5---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//		writeStr(w,"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		
		System.out.println("END");
		w.flush();//刷新输出流，强制清空缓存区
		w.close();
		
		String str="setup%null%null";
		System.out.println(str.replace("null", ""));
		
	}
	
	public static void writeStr(Writer w,String str){
		try {
			w.write(str);
			w.write("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

package com.horstmann.violet.application.gui.util.ckt.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.horstmann.violet.application.gui.util.ckt.handle.ATDTR__1;
import com.horstmann.violet.application.gui.util.ckt.handle.AddType;
import com.horstmann.violet.application.gui.util.ckt.handle.Automatic;
import com.horstmann.violet.application.gui.util.ckt.handle.GetAutomatic;
import com.horstmann.violet.application.gui.util.ckt.handle.IPR__1;
import com.horstmann.violet.application.gui.util.ckt.handle.State;
import com.horstmann.violet.application.gui.util.ckt.handle.StateCoverage__1;
import com.horstmann.violet.application.gui.util.ckt.handle.Transition;
import com.horstmann.violet.application.gui.util.wj.util.GeneratePath;

public class TestOut {

	public static void main(String[] args) throws IOException {
		
		File f=new File("D:\\test.txt");
		Writer w=new FileWriter(f,false);
		
//		String xml="D:\\xml\\UAVForXStream3.1.6.xml";//有时间约束
//		String xml = "D:\\xml\\UAVForXStream3.8.2.0.xml";
		//String xml="UAVForXStreamPerformTestV6.xml";
		String xml="D:\\xml\\UAVForXStream3.8.2.xml";
		

//		String xml="UAVForXStreamXuanTing.xml";
//		String xml="";
//		String xml="";
//		String xml="";
//		String xml="";
//		
//		String xml="";
//		String xml="";
//		String xml="";
		
		Automatic a = GetAutomatic.getAutomatic(xml);
//		a=AddType.addType(a);
		
		System.out.println(a.getClockSet());
		
		writeStr(w,a.getTransitionSet().size()+" - - "+a.getStateSet().size());
		
		for(Transition t:a.getTransitionSet()){
			writeStr(w,t.toString());
		}
		
		for(State s:a.getStateSet()){
			writeStr(w,s.toString());
		}
		
		writeStr(w,"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		writeStr(w,"1---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		writeStr(w,"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		Automatic automatic=IPR__1.iPR(a);
		
		writeStr(w,automatic.getTransitionSet().size()+" - - "+automatic.getStateSet().size());
		
		for(Transition t:automatic.getTransitionSet()){
			writeStr(w,t.toString());
		}
		
		for(State s:automatic.getStateSet()){
			writeStr(w,s.toString());
		}
		
		writeStr(w,"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		writeStr(w,"2---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		writeStr(w,"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		Automatic aTDRTAutomatic=ATDTR__1.aTDRT(automatic,a);
		
		writeStr(w,aTDRTAutomatic.getTransitionSet().size()+" - - "+aTDRTAutomatic.getStateSet().size());
		
		for(Transition t:aTDRTAutomatic.getTransitionSet()){
			writeStr(w,t.toString());
		}
		
		for(State s:aTDRTAutomatic.getStateSet()){
			writeStr(w,s.toString());
		}
		
		List<State> statelists=aTDRTAutomatic.getStateSet();
		ArrayList<State> newstatelists=new ArrayList<>();
		int i=1;
		for(State s:statelists){
			s.setId(i++);
			newstatelists.add(s);
		}
		aTDRTAutomatic.setStateSet(newstatelists);
		
		writeStr(w,"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		writeStr(w,"3---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		writeStr(w,"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		//状态覆盖
		Automatic DFStree=StateCoverage__1.DFSTree(aTDRTAutomatic);
		
		writeStr(w,DFStree.getTransitionSet().size()+" - - "+DFStree.getStateSet().size());
		
		for(Transition t:DFStree.getTransitionSet()){
			writeStr(w,t.toString());
		}
		
		for(State s:DFStree.getStateSet()){
			writeStr(w,s.toString());
		}
		
		writeStr(w,"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		writeStr(w,"4---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		writeStr(w,"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		
		
		ArrayList<Automatic> testCase=StateCoverage__1.testCase(DFStree);
		
		//路径覆盖
//		ArrayList<Automatic> testCase=GeneratePath.getFormatPathFromAutomatic(a, 2000);
		
		writeStr(w,testCase.size()+"");
		
		for(Automatic auto:testCase){
			writeStr(w,auto.getTransitionSet().size()+"");
			for(Transition t:auto.getTransitionSet()){
				writeStr(w,t.toString());
			}
		}
		
		writeStr(w,"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		writeStr(w,"5---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		writeStr(w,"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		ArrayList<Automatic> collectLimit = forPlatform.collectLimit(testCase);
		
		writeStr(w,collectLimit.size()+"");
		
		for(Automatic auto:collectLimit){
			writeStr(w,auto.getTransitionSet().size()+"");
			for(Transition t:auto.getTransitionSet()){
				writeStr(w,t.toString1());
			}
		}
		
		writeStr(w,"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		writeStr(w,"6---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		writeStr(w,"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		ArrayList<Automatic> collectResult = forPlatform.collectResult(collectLimit);
		
		writeStr(w,collectResult.size()+"");
		
		for(Automatic auto:collectResult){
			writeStr(w,auto.getTransitionSet().size()+"");
			for(Transition t:auto.getTransitionSet()){
				writeStr(w,t.toString2());
			}
		}
		
//		package cn.edu.hdu.ckt.testcase.PerformanceXML;//xml
		System.out.println("END");
		w.flush();//刷新输出流，强制清空缓存区
		w.close();
		
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

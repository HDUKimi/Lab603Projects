package com.horstmann.violet.application.gui.util.ckt.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.horstmann.violet.application.gui.util.ckt.handle.Automatic;
import com.horstmann.violet.application.gui.util.ckt.handle.GetAutomatic;
import com.horstmann.violet.application.gui.util.ckt.handle.StateCoverage__1;
import com.horstmann.violet.application.gui.util.ckt.testcase.*;

public class testDFSs {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String xml="UAVForXStream3.8.2.0.xml";
		Automatic auto=GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
		Automatic aaa = StateCoverage__1.DFSTree(auto);
//		System.out.println(aaa.toString());
//		System.out.println(aaa.getStateSet().size());
//		System.out.println(aaa.getTransitionSet().size());
		
		FileOutputStream fos=new FileOutputStream("E:\\项目\\111.txt");
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		oos.writeObject(aaa);
		
		
		ArrayList<Automatic> bb=StateCoverage__1.testCase(auto);
		System.out.println(bb.size());
//		for(Automatic c:bb){
//			System.out.println("-------------------");
//			System.out.println(c.toString());
//			System.out.println(c.getStateSet().size());
//			System.out.println(c.getTransitionSet().size());
//		}
		
		FileOutputStream afos=new FileOutputStream("E:\\项目\\222.txt");
		ObjectOutputStream aoos=new ObjectOutputStream(afos);
		aoos.writeObject(bb);
		
	}

}

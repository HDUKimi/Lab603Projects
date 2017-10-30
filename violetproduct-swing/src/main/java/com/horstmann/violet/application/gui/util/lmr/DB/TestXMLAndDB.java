package com.horstmann.violet.application.gui.util.lmr.DB;

import com.horstmann.violet.application.gui.util.tanchao.TestCaseXMLToStringList;

public class TestXMLAndDB {

	public void XMLSaveDB(){
		
		String basepath="D:\\ModelDriverProjectFile\\UPPAL\\4.Real_TestCase\\FunctionalTest\\";
		String name="EA4.1.0 ¹¦ÄÜ³¡¾°2Ç¨ÒÆ¸²¸ÇBorderTestCase";
		String path=basepath+name+".xml";
		int type=2;
		TestCaseXMLToStringList tcxmltsl=new TestCaseXMLToStringList();
		DataBaseUtil.insertTestCaseStringList(type, tcxmltsl.getStrings(path), name);
		
	}
	
	public void DBSaveXML(){
		
		String name="EA4.1.0 ¹¦ÄÜ³¡¾°2Ç¨ÒÆ¸²¸ÇBorderTestCase";
		TestCaseXMLToStringList tcxmltsl=new TestCaseXMLToStringList();
		tcxmltsl.createXml(DataBaseUtil.queryTestCaseStringList(name), "D:\\Text\\123.xml");
		
	}
	
	public static void main(String[] args) {
		
		TestXMLAndDB test=new TestXMLAndDB();
		
		System.out.println("------START------");
//		test.XMLSaveDB();
		test.DBSaveXML();
		System.out.println("-------END-------");
		
	}
	
}

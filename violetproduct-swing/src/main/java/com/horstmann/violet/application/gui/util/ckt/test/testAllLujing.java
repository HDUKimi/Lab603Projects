package com.horstmann.violet.application.gui.util.ckt.test;

import java.util.ArrayList;
import com.horstmann.violet.application.gui.util.ckt.handle.*;


public class testAllLujing {
	public static void main(String[] args) {
		String xml = "EAElevatorV1ForXStream.xml";
		Automatic automatic = GetAutomatic.getAutomatic(xml);// ���ԭʼ��ʱ���Զ���
		//Automatic new_automatic = IPR__1.iPR(automatic);// ��ò�ֺ��ʱ���Զ���
		//Automatic aTDRTAutomatic = ATDTR__1.aTDRT(new_automatic, automatic);// ���ȥ������ʱ��Ǩ�ƺ��ʱ���Զ���
		//ArrayList<Automatic> testCase = StateCoverage__1.testCase(aTDRTAutomatic);// �������״̬���ǵĳ����������
		ArrayList<Automatic> testCase = path.testcase(automatic);
		System.out.println(testCase.size()+"-----����·���ĸ���");
	}	
	
}

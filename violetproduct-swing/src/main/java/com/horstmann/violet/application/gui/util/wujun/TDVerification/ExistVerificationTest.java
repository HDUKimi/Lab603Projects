package com.horstmann.violet.application.gui.util.wujun.TDVerification;

import java.io.File;

/**
 * Created by jun on 16/12/7.
 */
public class ExistVerificationTest {
    public static final String automataPath = "1.xml";
    public static final String eaPath = "tdtest.xml";

    public static void main(String[] args) throws Exception {
    	
    	// 1 �Ա�
    	CompareEAtoAutomata.compareFromXMLPath(eaPath, automataPath);
    	
    	
    	
    	// 2 ��֤
        ExistVerification ev = new ExistVerification(automataPath);
        // 2.1 ... ��ƽ̨���� ��֤����һ���ԣ�
  
        // 2.2ʵʱһ����
        ev.verificationTimeDuration();
        
    	
    }
}

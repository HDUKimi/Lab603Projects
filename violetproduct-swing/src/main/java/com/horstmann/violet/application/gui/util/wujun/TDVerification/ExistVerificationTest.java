package com.horstmann.violet.application.gui.util.wujun.TDVerification;

import java.io.File;

/**
 * Created by jun on 16/12/7.
 */
public class ExistVerificationTest {
    public static final String automataPath = "1.xml";
    public static final String eaPath = "tdtest.xml";

    public static void main(String[] args) throws Exception {
    	
    	// 1 对比
    	CompareEAtoAutomata.compareFromXMLPath(eaPath, automataPath);
    	
    	
    	
    	// 2 验证
        ExistVerification ev = new ExistVerification(automataPath);
        // 2.1 ... 由平台调用 验证存在一致性，
  
        // 2.2实时一致性
        ev.verificationTimeDuration();
        
    	
    }
}

package com.horstmann.violet.application.gui.util.wujun.TDVerification;

import java.io.File;

/**
 * Created by jun on 16/12/7.
 */
public class ExistVerificationTest {
    public static final String automataPath = "1.xml";
    public static final String eaPath = "tdtest.xml";

    public static void main(String[] args) throws Exception {
    	
    	
    	
    	// 2 一致性验证
        ExistVerification ev = new ExistVerification(automataPath);
        
        
        // 对比
    	CompareEAtoAutomata.compareFromXMLPath(eaPath, automataPath);
        // 自动机路径累加时间是否等于时序图中最后一个状态的开始时间
    	CompareEAtoAutomata.verificationPathTupleTime(ev.getPath());
        
        // 2.2实时一致性
        ev.verificationTimeDuration();
        
    	
    }
}

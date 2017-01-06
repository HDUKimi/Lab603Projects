package com.horstmann.violet.application.gui.util.wujun.TDVerification;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jun on 16/12/7.
 */
public class ExistVerificationTest {
    public static final String automataPath = "1.xml";
    public static final String eaPath = "tdtest.xml";

    public static void main(String[] args) throws Exception {
    	// 1.对比 <前提1>
        RowStringsForDisplay rowStringsForDisplay = CompareEAtoAutomata.compareFromXMLPath(eaPath, automataPath);
        
    	// 2.一致性验证 <前提2>
        ExistVerification ev = new ExistVerification(automataPath);
        
        // 3.自动机路径累加时间是否等于时序图中最后一个状态的开始时间  如果不相等则返回null 需要<前提1>与<前提2>
    	ArrayList<Integer> times = CompareEAtoAutomata.verificationPathTupleTime(ev.getPath());
        
        // 4.实时一致性验证 显示 需要<前提1>
    	List<LocationVerificationDisplay> locationVerificationDisplays = ev.verificationLocationTimeDuration();
    	List<TransitionVerificationDisplay> transitionVerificationDisplays = ev.verificationTransitionTimeDuration();
    	
    	// 5.实时一致性验证 结果 需要完成第四步
    	ev.getVerificationResult();
    	
    }
}

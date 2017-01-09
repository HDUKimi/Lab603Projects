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
    	// 1.�Ա� <ǰ��1>
        RowStringsForDisplay rowStringsForDisplay = CompareEAtoAutomata.compareFromXMLPath(eaPath, automataPath);
        
    	// 2.һ������֤ <ǰ��2>
        ExistVerification ev = new ExistVerification(automataPath);
        
        // 3.�Զ���·���ۼ�ʱ���Ƿ����ʱ��ͼ�����һ��״̬�Ŀ�ʼʱ��  ���������򷵻�null ��Ҫ<ǰ��1>��<ǰ��2>
    	ArrayList<Integer> times = CompareEAtoAutomata.verificationPathTupleTime(ev.getPath());
        
        // 4.ʵʱһ������֤ ��ʾ ��Ҫ<ǰ��1>
    	List<LocationVerificationDisplay> locationVerificationDisplays = ev.verificationLocationTimeDuration();
    	List<TransitionVerificationDisplay> transitionVerificationDisplays = ev.verificationTransitionTimeDuration();
    	
    	// 5.ʵʱһ������֤ ��� ��Ҫ��ɵ��Ĳ�
    	ev.getVerificationResult();
    	
    }
}

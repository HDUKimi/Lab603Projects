package com.horstmann.violet.application.gui.util.wujun.TDVerification;

import java.io.File;

/**
 * Created by jun on 16/12/7.
 */
public class ExistVerificationTest {
    public static final String automataPath = "1.xml";
    public static final String eaPath = "tdtest.xml";

    public static void main(String[] args) throws Exception {
    	
    	
    	
    	// 2 һ������֤
        ExistVerification ev = new ExistVerification(automataPath);
        
        
        // �Ա�
    	CompareEAtoAutomata.compareFromXMLPath(eaPath, automataPath);
        // �Զ���·���ۼ�ʱ���Ƿ����ʱ��ͼ�����һ��״̬�Ŀ�ʼʱ��
    	CompareEAtoAutomata.verificationPathTupleTime(ev.getPath());
        
        // 2.2ʵʱһ����
        ev.verificationTimeDuration();
        
    	
    }
}

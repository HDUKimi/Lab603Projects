package com.horstmann.violet.application.gui.util.wujun.TDVerification;

import java.io.File;

/**
 * Created by jun on 16/12/7.
 */
public class ExistVerificationTest {
    public static final String tempPath = "testMoment.xml";

    public static void main(String[] args) throws Exception {
        ExistVerification ev = new ExistVerification(tempPath);
        
        if(ev.verificationTimeDuration()) {
        	System.out.println("³É¹¦");
        }
    }
}

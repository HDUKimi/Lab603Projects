package com.horstmann.violet.application.gui.util.wujun.TDVerification;

/**
 * Created by jun on 16/12/7.
 */
public class ExistUtil {
	private static ExistVerification existVerification;
    public static ExistVerification getExist(String filePath) throws Exception {
    	return existVerification = new ExistVerification(filePath);
    }
}

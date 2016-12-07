package com.horstmann.violet.application.gui.util.wujun.TDVerification;

/**
 * Created by jun on 16/12/7.
 */
public class ExistUtil {
    static public ExistVerification getExist(String filePath) throws Exception {
        return new ExistVerification(filePath);
    }
}

package com.horstmann.violet.application.gui.util.chenzuo.Util;

import java.io.File;

/**
 * Created by geek on 2017/8/15.
 */

public class FileUtil {

    public static String REMOTE_TC_PATH ="/home/8_13_Finall/Test/testcase/";
    public static String REMOTE_RS_PATH ="/home/8_13_Finall/Test/result/";
    public static String LOCAL_TARGET_PATH =System.getProperty("user.dir")+"\\src\\main\\java\\com\\horstmann\\violet\\application\\gui\\util\\chenzuo\\File\\";

    public void SetLocalPath(String path){
        LOCAL_TARGET_PATH = path;
    }
    /**
     * 鍒犻櫎鏂囦欢锛屽彲浠ユ槸鏂囦欢鎴栨枃浠跺�?
     *
     * @param fileName
     *            瑕佸垹闄ょ殑鏂囦欢鍚�?
     * @return 鍒犻櫎鎴愬姛杩斿洖true锛屽惁鍒欒繑鍥�?�alse
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("鍒犻櫎鏂囦欢澶辫�?:" + fileName + "涓嶅瓨鍦紒");
            return false;
        } else {
            if (file.isFile())
                return deleteFile(fileName);
            else
                return deleteDirectory(fileName);
        }
    }

    /**
     * 鍒犻櫎鍗曚釜鏂囦�?
     *
     * @param fileName
     *            瑕佸垹闄ょ殑鏂囦欢鐨勬枃浠跺�?
     * @return 鍗曚釜鏂囦欢鍒犻櫎鎴愬姛杩斿洖true锛屽惁鍒欒繑鍥�?�alse
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 濡傛灉鏂囦欢璺緞鎵��?�瑰簲鐨勬枃浠跺瓨鍦紝骞朵笖鏄竴涓枃浠讹紝鍒欑洿鎺ュ垹闄�
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("鍒犻櫎鍗曚釜鏂囦�?" + fileName + "鎴愬姛锛�?");
                return true;
            } else {
                System.out.println("鍒犻櫎鍗曚釜鏂囦�?" + fileName + "澶辫触锛�?");
                return false;
            }
        } else {
            System.out.println("鍒犻櫎鍗曚釜鏂囦欢澶辫触锛�" + fileName + "涓嶅瓨鍦紒");
            return false;
        }
    }

    /**
     * 鍒犻櫎鐩綍鍙婄洰褰曚笅鐨勬枃浠�?
     *
     * @param dir
     *            瑕佸垹闄ょ殑鐩綍鐨勬枃浠惰矾寰�?
     * @return 鐩綍鍒犻櫎鎴愬姛杩斿洖true锛屽惁鍒欒繑鍥�?�alse
     */
    public static boolean deleteDirectory(String dir) {
        // 濡傛灉dir涓嶄互鏂囦欢鍒嗛殧绗︾粨灏撅紝鑷姩娣诲姞鏂囦欢鍒嗛殧绗�?
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 濡傛灉dir瀵瑰簲鐨勬枃浠朵笉�?�樺湪锛屾垨鑰呬笉鏄竴涓洰褰曪紝鍒欓��鍑�
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("鍒犻櫎鐩綍澶辫触锛�?" + dir + "涓嶅瓨鍦紒");
            return false;
        }
        boolean flag = true;
        // 鍒犻櫎鏂囦欢澶�?�腑鐨勬墍鏈夋枃浠跺寘鎷�?瓙鐩綍
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 鍒犻櫎�?�愭枃浠�?
            if (files[i].isFile()) {
                flag = FileUtil.deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 鍒犻櫎�?�愮洰褰�?
            else if (files[i].isDirectory()) {
                flag = FileUtil.deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            System.out.println("鍒犻櫎鐩綍澶辫触锛�?");
            return false;
        }
        // 鍒犻櫎褰撳墠鐩�?
        if (dirFile.delete()) {
            System.out.println("鍒犻櫎鐩綍" + dir + "鎴愬姛锛�?");
            return true;
        } else {
            return false;
        }
    }
}

package com.horstmann.violet.application.gui.util.chenzuo.Service;

import com.horstmann.violet.application.gui.util.chenzuo.Bean.Constants;
import com.horstmann.violet.application.gui.util.chenzuo.Bean.TestCase;
import com.horstmann.violet.application.gui.util.chenzuo.Util.FileUtil;
import com.horstmann.violet.application.gui.util.chenzuo.Util.TcConvertUtil;
import com.horstmann.violet.application.gui.util.tanchao.OrderWrapperTool;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Created by geek on 2017/8/14.
 */
public class ResultService {

    private Logger logger = Logger.getLogger(this.getClass());

    private ScheduledThreadPoolExecutor scheduledService = new ScheduledThreadPoolExecutor(1);

    public static List<TestCase> list = Collections.synchronizedList(new ArrayList());

    public ResultService(String type) {
        scheduledService.scheduleAtFixedRate(
                new GetResult(type),
                0,
                Constants.PEROID,
                Constants.TIME_TYPE);
    }

    class GetResult implements Runnable {

        private String type;

        @Override
        public void run() {
            readfile();
        }

        public GetResult(String type) {
            this.type = type;
        }

        public void readfile() {
            File file = new File(FileUtil.LOCAL_TARGET_PATH);
            if (file.isDirectory()) {
//                String[] filelist = file.list();
                
            	String[] filelist=OrderWrapperTool.SortFileNameList(file.list());
                
                for (int i = 0; i < filelist.length; i++) {
                    String fileName = FileUtil.LOCAL_TARGET_PATH + filelist[i];
                        try {
                            list.addAll(TcConvertUtil.buildTestCaseList(type, fileName));
                            FileUtil.delete(fileName);
                            logger.debug("list size:"+list.size());
                            if(Constants.ISFINISH.get()){
                                logger.debug("scheduledService close");
                                scheduledService.shutdown();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
            }

        }
    }

    public static List<TestCase> getResult() {
        return list;
    }

	public static void main(String[] args) {
        PropertyConfigurator.configure("src/log4j.properties");
        ResultService s = new ResultService("Function");
    }

}

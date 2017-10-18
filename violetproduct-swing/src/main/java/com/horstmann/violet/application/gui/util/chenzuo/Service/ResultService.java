package com.horstmann.violet.application.gui.util.chenzuo.Service;

import com.horstmann.violet.application.gui.util.chenzuo.Bean.Constants;
import com.horstmann.violet.application.gui.util.chenzuo.Bean.TestCase;
import com.horstmann.violet.application.gui.util.chenzuo.Controller.Controller;
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
    
    public List<String> writedlist=new ArrayList<>();

    public ResultService(String type) {
    	list = Collections.synchronizedList(new ArrayList());
    	writedlist=new ArrayList<>();
    	DeleteFile();
        scheduledService.scheduleAtFixedRate(
                new GetResult(type),
                0,
                Constants.PEROID,
                Constants.TIME_TYPE);
    }

    private void DeleteFile() {
    	
    	File file = new File(FileUtil.LOCAL_TARGET_PATH);
        if (file.isDirectory()) {
            String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
                String fileName = FileUtil.LOCAL_TARGET_PATH + filelist[i];
                FileUtil.delete(fileName);
            }
        }
    	
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
                String[] filelist = file.list();
                
//            	String[] filelist=OrderWrapperTool.SortFileNameList(file.list());
                System.out.println("readfile()+++++++++++++++++++++"+filelist.length);
                for (int i = 0; i < filelist.length; i++) {
                	System.out.println(filelist[i]);
                }
                System.out.println("readfile()-----------------------"+type+" - "+Controller.offsetTestCaseId);
                
                for (int i = 0; i < filelist.length; i++) {
                	
                	if(!filelist[i].contains("89")&&!filelist[i].contains("93")){
                		continue;
                	}
                	
                	int flag=0;
                	for(String s:writedlist){
                		if(filelist[i].equals(s)){
                			flag=1;
                			break;
                		}
                	}
                	if(flag==1){
                		continue;
                	}
                	else{
                		writedlist.add(filelist[i]);
                	}
                	
                    String fileName = FileUtil.LOCAL_TARGET_PATH + filelist[i];
                        try {
                        	
                        	List<TestCase> testcaselist=TcConvertUtil.buildTestCaseList(type, fileName);
                        	
                        	//IDÆ«ÒÆ
                        	if(Controller.offsetIP!=null&&filelist[i].contains(Controller.offsetIP)){
                        		for(TestCase testCase:testcaselist){
                        			testCase.setTestCaseID(String.valueOf(Integer.parseInt(testCase.getTestCaseID())+Controller.offsetTestCaseId));
                        		}
                        	}
                        	
                            list.addAll(testcaselist);
//                            FileUtil.delete(fileName);
                            logger.debug("list size:"+list.size());
//                            if(Constants.ISFINISH.get()){
//                                logger.debug("scheduledService close");
//                                scheduledService.shutdown();
//                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
                
            	if(Constants.ISFINISH.get()){
					logger.debug("scheduledService close");
					scheduledService.shutdown();
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

	public ScheduledThreadPoolExecutor getScheduledService() {
		return scheduledService;
	}
	
}

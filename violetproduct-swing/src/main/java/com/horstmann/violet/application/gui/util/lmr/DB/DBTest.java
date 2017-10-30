package com.horstmann.violet.application.gui.util.lmr.DB;

import java.util.ArrayList;
import java.util.List;

import com.horstmann.violet.application.gui.util.chenzuo.Bean.TestCase;

public class DBTest {

	public static void main(String[] args) {
		
//		String str="testCaseID:1"
//				+"-->processList:["
//				+"[processID:1	processName:setup	processParam:null	processStatus:76.986	processExec:true]"
//				+"[processID:2	processName:init_ardupilot	processParam:null	processStatus:76.981	processExec:true]"
//				+"[processID:3	processName:init	processParam:null	processStatus:0	processExec:true]"
//				+"[processID:4	processName:init	processParam:null	processStatus:0.001	processExec:true]"
//				+"[processID:5	processName:init_rc_in	processParam:null	processStatus:0.014	processExec:true]"
//				+"[processID:6	processName:set_range	processParam:g.throttle_min=-32768	processStatus:0.007	processExec:true]"
//				+"]"
//				+"-->exetime:[162.28800000000004]"
//				+"-->state:[测试执行成功!]";
//		
//		TestCase testCase=DataBaseUtil.extractTestCaseByString(1, str);
//		System.out.println(testCase.toString());
//		System.out.println(testCase.showTestCase());
		
		
		String str="testCaseID:2"
				+"-->processList:["
				+"[processID:1	processName:peopleComing	processParam:null	processStatus:t1=0.005	processExec:true]"
				+"[processID:2	processName:newPeople	processParam:null	processStatus:t2=0.045	processExec:true]"
				+"[processID:3	processName:pushCallUp	processParam:null	processStatus:t3=0.023	processExec:true]"
				+"[processID:4	processName:response	processParam:null	processStatus:t4=0.02	processExec:true]"
				+"[processID:5	processName:controller	processParam:Floor=2,State=0	processStatus:t5=0.035	processExec:true]"
				+"[processID:6	processName:open	processParam:Floor=1,State=0,callUp 1 !=false,callDown 1 !=false,callCar 1 !=false	processStatus:t6=2005.87	processExec:true]"
				+"[processID:7	processName:open_return	processParam:Floor=1,State=0,callUp 1 !=false,callDown 1 !=false,callCar 1 !=false	processStatus:t8=0.057	processExec:true]"
				+"[processID:8	processName:peopleOutIn	processParam:null	processStatus:t12=2006.19	processExec:true]"
				+"[processID:9	processName:thisFloorPeopleOut	processParam:elevator_thisFloorOut=true	processStatus:t9=0.057	processExec:true]"
				+"[processID:10	processName:thisFloorPeopleOut_return	processParam:elevator_thisFloorOut=true	processStatus:t13=0.022	processExec:true]"
				+"[processID:11	processName:thisFloorPeopleIn	processParam:getCurrentFloor_empty=false,!elevator_thisFloorOut=true,!elevator_thisFloorOut=true	processStatus:t17=0.014	processExec:true]"
				+"[processID:12	processName:thisFloorPeopleIn_return	processParam:getCurrentFloor_empty=false	processStatus:t14=0.016	processExec:true]"
				+"[processID:13	processName:thisFloorPeopleIn	processParam:getCurrentFloor_empty=false	processStatus:t19=0.019	processExec:true]"
				+"[processID:14	processName:thisFloorPeopleIn_return	processParam:getCurrentFloor_empty=false	processStatus:t14=0.012	processExec:true]"
				+"[processID:15	processName:close	processParam:!getCurrentFloor_empty=false,!getCurrentFloor_empty=false	processStatus:t20=2006.32	processExec:true]"
				+"[processID:16	processName:close_return	processParam:null	processStatus:t15=0.058	processExec:true]"
				+"[processID:17	processName:prepareMove	processParam:null	processStatus:t21=1002.31	processExec:true]"
				+"[processID:18	processName:goUpstairs	processParam:null	processStatus:t22=1002.99	processExec:true]"
			+"]"
			+"-->timeLimit:["
				+"[uninquery:t1>=0	timeAttribute:t1=0.005 	isOk:true]"
				+"[uninquery:t1+t2>=0	timeAttribute:t1=0.005 t2=0.045 	isOk:true]"
				+"[uninquery:t1+t2+t3>=0	timeAttribute:t1=0.005 t2=0.045 t3=0.023 	isOk:true]"
				+"[uninquery:t1+t2+t3+t4>=0	timeAttribute:t1=0.005 t2=0.045 t3=0.023 t4=0.02 	isOk:true]"
				+"[uninquery:t5<=2500	timeAttribute:t5=0.035 	isOk:true]"
				+"[uninquery:t5>=0	timeAttribute:t5=0.035 	isOk:true]"
				+"[uninquery:t6<=2500	timeAttribute:t6=2005.87 	isOk:true]"
				+"[uninquery:t6>=0	timeAttribute:t6=2005.87 	isOk:true]"
				+"[uninquery:t6+t8<=30000	timeAttribute:t6=2005.87 t8=0.057 	isOk:true]"
				+"[uninquery:t6+t8>=0	timeAttribute:t6=2005.87 t8=0.057 	isOk:true]"
				+"[uninquery:t6+t8+t12>=0	timeAttribute:t6=2005.87 t8=0.057 t12=2006.19 	isOk:true]"
				+"[uninquery:t9<=3000	timeAttribute:t9=0.057 	isOk:true]"
				+"[uninquery:t9>=0	timeAttribute:t9=0.057 	isOk:true]"
				+"[uninquery:t9+t13>=0	timeAttribute:t9=0.057 t13=0.022 	isOk:true]"
				+"[uninquery:t17<=3000	timeAttribute:t17=0.014 	isOk:true]"
				+"[uninquery:t17>=0	timeAttribute:t17=0.014 	isOk:true]"
				+"[uninquery:t17+t14>=0	timeAttribute:t17=0.014 t14=0.012 	isOk:true]"
				+"[uninquery:t19<=3000	timeAttribute:t19=0.019 	isOk:true]"
				+"[uninquery:t19>=0	timeAttribute:t19=0.019 	isOk:true]"
				+"[uninquery:t17+t14>=0	timeAttribute:t17=0.014 t14=0.012 	isOk:true]"
				+"[uninquery:t20<=2500	timeAttribute:t20=2006.32 	isOk:true]"
				+"[uninquery:t20>=0	timeAttribute:t20=2006.32 	isOk:true]"
				+"[uninquery:t20+t15>=0	timeAttribute:t20=2006.32 t15=0.058 	isOk:true]"
				+"[uninquery:t21<=1500	timeAttribute:t21=1002.31 	isOk:true]"
				+"[uninquery:t21>=0	timeAttribute:t21=1002.31 	isOk:true]"
				+"[uninquery:t21+t22>=0	timeAttribute:t21=1002.31 t22=1002.99 	isOk:true]"
			+"]"
			+"-->exetime:[8024.063]"
			+"-->state:[测试用例正确执行,且满足时间约束]";
		
//		TestCase testCase=DataBaseUtil.extractTestCaseByString(3, str);
//		
//		System.out.println(testCase.toString());
//		System.out.println(testCase.getLimit());
//		System.out.println(testCase.getResult().getTimeLimit().getShowMap().size());
		
		
//		int type=3;
		List<String> testcasestringlist=new ArrayList<String>();
//		testcasestringlist.add(str);
		String testcasedataname="XXTimeTestCase";
		
//		DataBaseUtil.insertTestCaseStringList(type, testcasestringlist, testcasedataname);
		
		testcasestringlist=DataBaseUtil.queryTestCaseStringList(testcasedataname);
		System.out.println(testcasestringlist.toString());
		
	}
	
}

package com.horstmann.violet.application.gui.util.lmr.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.plaf.synth.SynthSpinnerUI;

import com.horstmann.violet.application.gui.util.chenzuo.Bean.Pair;
import com.horstmann.violet.application.gui.util.chenzuo.Bean.TestCase;
import com.horstmann.violet.application.gui.util.chenzuo.Bean.TestCaseResult;
import com.horstmann.violet.application.gui.util.chenzuo.Bean.Time;
import com.horstmann.violet.application.gui.util.chenzuo.Bean.myProcess;

public class DataBaseUtil {

	static ResultSet ret = null;
	public static final String url = "jdbc:mysql://localhost:3306/violetproduct_testcase";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "123456";
	public static Connection conn = null;
	public static PreparedStatement pst = null;
	public static String sql;
	
	/**
	 * 初始化数据库连接
	 */
	public static void init() {
		try {
			Class.forName(name);
			conn = DriverManager.getConnection(url, user, password);// 获取连接
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭数据库资源
	 */
	public static void close() {
		try {
			if(ret!=null){
				ret.close();
			}
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteTestCaseStringList(int type){
		
		try {

			init();
			
			if (type == 1) {
				sql = "delete from functionalcase";
			} else if (type == 2) {
				sql = "delete from performancecase";
			} else if (type == 3) {
				sql = "delete from bordercase";
			} else if (type == 4) {
				sql = "delete from timecase";
			}

			pst = conn.prepareStatement(sql);
			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
	}
	
	public static void deleteTestCaseByIdAndType(int caseid, int type) {
		try {

			init();
			
			if (type == 1) {
				sql = "delete from functionalcase where casedataid="+caseid;
			} else if (type == 2) {
				sql = "delete from performancecase where casedataid="+caseid;
			} else if (type == 3) {
				sql = "delete from bordercase where casedataid="+caseid;
			} else if (type == 4) {
				sql = "delete from timecase where casedataid="+caseid;
			}

			pst = conn.prepareStatement(sql);
			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public static ResultSet query(String sql) {
		try {
			// init();
			pst = conn.prepareStatement(sql);// 准备执行语句
			ret = pst.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close();
		}
		return ret;
	}
	
	public static List<String> queryCaseDataList(){
		
		List<String> casedatalist=new ArrayList<String>();
		
		try {

			init();
			
			casedatalist=new ArrayList<String>();
			
			sql = "SELECT id, name, type FROM casedata ";
			
			pst = conn.prepareStatement(sql);// 准备执行语句
			ret = pst.executeQuery();
			while(ret.next()){
				casedatalist.add(ret.getString(2));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return casedatalist;
		
	}
	
	public static int queryTestCaseDataType(String testcasedataname){
		
		int type=1;
		
		try {

			init();
			
			sql = "SELECT id, name, type FROM casedata WHERE name=?";
			
			pst = conn.prepareStatement(sql);// 准备执行语句
			pst.setString(1, testcasedataname);
			ret = pst.executeQuery();
			if(ret.next()){
				type=ret.getInt(3);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return type;
		
	}
	
	public static List<String> queryTestCaseStringList(String testcasedataname){

		List<String> testcasestringlist=new ArrayList<String>();
		
		int type=queryTestCaseDataType(testcasedataname);
		
		try {

			init();
			
			testcasestringlist=new ArrayList<String>();
			
			if (type == 1) {
				sql = "SELECT b.id, b.content FROM casedata a left join functionalcase b on a.id=b.casedataid where a.name=? ";
			} else if (type == 2) {
				sql = "SELECT b.id, b.content FROM casedata a left join performancecase b on a.id=b.casedataid where a.name=? ";
			} else if (type == 3) {
				sql = "SELECT b.id, b.content FROM casedata a left join bordercase b on a.id=b.casedataid where a.name=? ";
			} else if (type == 4) {
				sql = "SELECT b.id, b.content FROM casedata a left join timecase b on a.id=b.casedataid where a.name=? ";
			} 
			
			
			pst = conn.prepareStatement(sql);// 准备执行语句
			pst.setString(1, testcasedataname);
			ret = pst.executeQuery();
			while(ret.next()){
				testcasestringlist.add(ret.getString(2));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return testcasestringlist;
	}
	
	public static int queryMaxTestCaseDataId(){
		
		int id=0;
		
		try {

			init();
			
			sql="select max(id) from casedata order by id";
			
			ret = query(sql);
			
			if(ret.next()){
				id=ret.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return id;
	}
	
	public static int queryByTestCaseDataName(String testcasedataname){
		
		int id=0;
		
		try {

			init();
			
			sql="select id from casedata where name='"+testcasedataname+"'";
			
			ret = query(sql);
			
			if(ret.next()){
				id=ret.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return id;
		
	}
	
	public static void insertTestCaseData(int type, String testcasedataname){
		
		int id=queryMaxTestCaseDataId()+1;
		
		try {

			init();
			
			sql = "insert into casedata(id,name,type) values(?,?,?)";

			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setString(2, testcasedataname);
			pst.setInt(3, type);
			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
	}

	public static void insertTestCaseStringList(int type, List<String> testcasestringlist, String testcasedataname) {

		int caseid=queryByTestCaseDataName(testcasedataname);
		if(caseid==0){
			insertTestCaseData(type, testcasedataname);
			caseid=queryByTestCaseDataName(testcasedataname);
		}
		else{
			deleteTestCaseByIdAndType(caseid,type);
		}
		
		try {

			init();
			
			if (type == 1) {
				sql = "insert into functionalcase(id,content,casedataid) values(?,?,?)";
			} else if (type == 2) {
				sql = "insert into performancecase(id,content,casedataid) values(?,?,?)";
			} else if (type == 3) {
				sql = "insert into bordercase(id,content,casedataid) values(?,?,?)";
			} else if (type == 4) {
				sql = "insert into timecase(id,content,casedataid) values(?,?,?)";
			} 

			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			int id = 1;
			for (String str : testcasestringlist) {
				
				if(id%100==0){
					pst.executeBatch();
					conn.commit();
					
					conn.setAutoCommit(false);
				}
				
				pst.setInt(1, id++);
				pst.setString(2, str);
				pst.setInt(3, caseid);
//				pst.executeUpdate();
				pst.addBatch();
			}

			pst.executeBatch();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

	}
	
	public static TestCase extractTestCaseByString(int type, String str){
		
		str = str.replace("\n", "");
		TestCase testCase=new TestCase();

		if(type==1){
			testCase.setTestCaseID(stringRegEx(str, "testCaseID:([\\s|\\S]*?)-->processList:").get(0));
			testCase.setExetime(stringRegEx(str, "exetime:\\[([\\s|\\S]*?)\\]-->expectResult:").get(0));
			testCase.setExpectResult(stringRegEx(str, "expectResult:\\[([\\s|\\S]*?)\\]-->state:").get(0));
			testCase.setState(stringRegEx(str, "state:\\[([\\s|\\S]*?)\\]-->programExeResult:").get(0));
			testCase.setProgramExeResult(stringRegEx(str, "programExeResult:\\[([\\s|\\S]*?)\\]").get(0));
			String processstr=stringRegEx(str, "processList:\\[([\\s|\\S]*?)\\]-->exetime:").get(0);
			testCase.setProcessList(extractProcessByString(processstr));
		}
		else if(type==2){
			testCase.setTestCaseID(stringRegEx(str, "testCaseID:([\\s|\\S]*?)-->processList:").get(0));
			testCase.setExetime(stringRegEx(str, "exetime:\\[([\\s|\\S]*?)\\]-->expectResult:").get(0));
			testCase.setExpectResult(stringRegEx(str, "expectResult:\\[([\\s|\\S]*?)\\]-->state:").get(0));
			testCase.setState(stringRegEx(str, "state:\\[([\\s|\\S]*?)\\]").get(0));
			String processstr=stringRegEx(str, "processList:\\[([\\s|\\S]*?)\\]-->performanceParam:").get(0);
			testCase.setProcessList(extractProcessByString(processstr));
			String paramstr=stringRegEx(str, "performanceParam:\\[([\\s|\\S]*?)\\]-->exetime:").get(0);
			extractPerformanceParamByString(testCase,paramstr);
		}
		else if(type==3){
			testCase.setTestCaseID(stringRegEx(str, "testCaseID:([\\s|\\S]*?)-->processList:").get(0));
			testCase.setExetime(stringRegEx(str, "exetime:\\[([\\s|\\S]*?)\\]-->expectResult:").get(0));
			testCase.setExpectResult(stringRegEx(str, "expectResult:\\[([\\s|\\S]*?)\\]-->state:").get(0));
			testCase.setState(stringRegEx(str, "state:\\[([\\s|\\S]*?)\\]-->programExeResult:").get(0));
			testCase.setProgramExeResult(stringRegEx(str, "programExeResult:\\[([\\s|\\S]*?)\\]").get(0));
			String processstr=stringRegEx(str, "processList:\\[([\\s|\\S]*?)\\]-->timeLimit:").get(0);
			testCase.setProcessList(extractProcessByString(processstr));
			String timelimitstr=stringRegEx(str, "timeLimit:\\[([\\s|\\S]*?)\\]-->exetime:").get(0);
			extractTimeLimitByString(testCase,timelimitstr);
		}
		
		return testCase;
		
	}


	private static void extractPerformanceParamByString(TestCase testCase, String str) {
		
		double wind_speed = 0,takeoff_alt = 0,battery_remaining = 0,time = 0;
		
		String regex="\\d+";
		
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(str);
		
		int i=1;
		while (mat.find()) {
			if(i==1){
				wind_speed=Double.parseDouble(mat.group(0).trim());
			}
			else if(i==2){
				takeoff_alt=Double.parseDouble(mat.group(0).trim());
			}
			else if(i==3){
				battery_remaining=Double.parseDouble(mat.group(0).trim());
			}
			else if(i==4){
				time=Double.parseDouble(mat.group(0).trim());
			}
			i++;
		}
		
		TestCaseResult testCaseResult=new TestCaseResult();
		testCaseResult.setWind_speed(wind_speed);
		testCaseResult.setTakeoff_alt(takeoff_alt);
		testCaseResult.setBattery_remaining(battery_remaining);
		testCaseResult.setTime(time);
		testCase.setResult(testCaseResult);
		
	}

	private static void extractTimeLimitByString(TestCase testCase, String str) {

		List<String> timeLimitList=new ArrayList<String>();
		Map<String, Pair<String, String>> timeLimitMap = new HashMap<String, Pair<String, String>>();
		
		List<String> timeLimitStringList=stringRegEx(str, "\\[([\\s|\\S]*?)\\]");
		
		for(String timeLimit:timeLimitStringList){
			String uninquery=stringRegEx(timeLimit, "uninquery:([\\s|\\S]*?)timeAttribute:").get(0);
			String timeAttribute=stringRegEx(timeLimit, "timeAttribute:([\\s|\\S]*?)isOk:").get(0);
			String isOk=timeLimit.split("isOk:")[1];
			
			timeLimitList.add(uninquery);
			timeLimitMap.put(uninquery, new Pair<String, String>(timeAttribute, isOk));
		}
		
		testCase.setLimit(timeLimitList);
		Time time=new Time();
		time.setShowMap(timeLimitMap);
		TestCaseResult testCaseResult=new TestCaseResult();
		testCaseResult.setTimeLimit(time);
		testCase.setResult(testCaseResult);
		
	}

	public static List<myProcess> extractProcessByString(String str) {
		
		List<myProcess> list=new ArrayList<myProcess>();
		
		List<String> processStringlist=stringRegEx(str, "\\[processID:([\\s|\\S]*?)\\]");
		
		for(String processStr:processStringlist){
			myProcess process=new myProcess();
			processStr="processID:"+processStr;
			process.setProcessID(Integer.parseInt(stringRegEx(processStr, "processID:([\\s|\\S]*?)processName:").get(0)));
			process.setProcessName(stringRegEx(processStr, "processName:([\\s|\\S]*?)processParam:").get(0));
			process.setProcessParam(stringRegEx(processStr, "processParam:([\\s|\\S]*?)processStatus:").get(0));
			process.setProcessStatus(stringRegEx(processStr, "processStatus:([\\s|\\S]*?)processExec:").get(0));
//			process.setProcessExec(Boolean.parseBoolean(stringRegEx(processStr, "processExec:([\\s|\\S]*?)").get(0)));
			process.setProcessExec(Boolean.parseBoolean(processStr.split("processExec:")[1]));
			list.add(process);
		}
		
		return list;
	}

	/**
	 * 对字符串进行 正则匹配，获取结果
	 * 
	 * @param current
	 *            匹配串
	 * @param regEx
	 *            正则表达式
	 * @return
	 */
	public static List<String> stringRegEx(String current, String regEx) {
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(current);
		List<String> result = new ArrayList<String>();
		while (mat.find()) {
//			result.add(mat.group(1).replace("[", " ").replace("]", " ").trim());
			result.add(mat.group(1).trim());
		}
		return result;
	}

}

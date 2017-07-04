package com.horstmann.violet.application.gui.opreationTreePane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSpinnerUI;

import com.horstmann.violet.application.gui.util.chengzuo.Bean.TestCase;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.myProcess;

public class DataBaseUtil {

	static ResultSet ret = null;
	public static final String url = "jdbc:mysql://localhost:3306/violetproduct_testcase";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "123456";
	public static Connection conn = null;
	public static PreparedStatement pst = null;
	public static String sql;
	
	public static List<String> testcasestringlist=new ArrayList<>();

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
	
	public static List<String> queryTestCaseStringList(int type){
		
		try {

			init();
			
			testcasestringlist=new ArrayList<>();
			
			if (type == 1) {
				sql = "select * from functionalcase order by id";
			} else if (type == 2) {
				sql = "select * from performancecase order by id";
			} else if (type == 3) {
				sql = "select * from timecase order by id";
			}
			
			ret = query(sql);
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

	public static void insertTestCaseStringList(int type, List<String> testcasestringlist) {

		try {

			deleteTestCaseStringList(type);
			
			init();
			
			if (type == 1) {
				sql = "insert into functionalcase(id,content) values(?,?)";
			} else if (type == 2) {
				sql = "insert into performancecase(id,content) values(?,?)";
			} else if (type == 3) {
				sql = "insert into timecase(id,content) values(?,?)";
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

	public static void insertFunctional(String filename, List<TestCase> testcaselist) {
		try {

			init();

			int id = 0;
			sql = "select id from CaseData order by id desc";
			ret = query(sql);
			if (ret.next()) {
				id = ret.getInt(1) + 1;
			} else {
				id = 1;
			}

			sql = "insert into CaseData(id,name) values(?,?)";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setString(2, filename);
			pst.executeUpdate();

			conn.setAutoCommit(false);

			sql = "insert into TestCase(id,caseid,tcid) values(?,?,?)";
			pst = conn.prepareStatement(sql);
			for (TestCase tc : testcaselist) {
				pst.setString(1, id + "-" + tc.getTestCaseID());
				pst.setString(2, id + "");
				pst.setString(3, tc.getTestCaseID());
				// pst.executeUpdate();
				pst.addBatch();

			}

			pst.executeBatch();
			conn.commit();

			conn.setAutoCommit(false);
			sql = "insert into Process(id,testcaseid,pid,pname,pparam) values(?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			for (TestCase tc : testcaselist) {
				System.out.println("------------------" + tc.getTestCaseID() + "------------------");
				for (myProcess p : tc.getProcessList()) {

					pst.setString(1, id + "-" + tc.getTestCaseID() + "-" + p.getProcessID());
					pst.setString(2, id + "-" + tc.getTestCaseID());
					pst.setInt(3, p.getProcessID());
					pst.setString(4, p.getProcessName());
					pst.setString(5, p.getProcessParam());
					// pst.executeUpdate();
					pst.addBatch();
				}
			}
			pst.executeBatch();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

}

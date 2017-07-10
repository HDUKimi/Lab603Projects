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
	
	public static List<String> queryCaseDataList(){
		
		List<String> casedatalist=new ArrayList<>();
		
		try {

			init();
			
			casedatalist=new ArrayList<>();
			
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
	
	public static List<String> queryTestCaseStringList(int type, String testcasedataname){

		List<String> testcasestringlist=new ArrayList<>();
		
		try {

			init();
			
			testcasestringlist=new ArrayList<>();
			
			if (type == 1) {
				sql = "SELECT b.id, b.content FROM casedata a left join functionalcase b on a.id=b.casedataid where a.name=? ";
			} else if (type == 2) {
				sql = "SELECT b.id, b.content FROM casedata a left join performancecase b on a.id=b.casedataid where a.name=? ";
			} else if (type == 3) {
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

		insertTestCaseData(type, testcasedataname);
		int caseid=queryMaxTestCaseDataId();
		
		try {

			init();
			
			if (type == 1) {
				sql = "insert into functionalcase(id,content,casedataid) values(?,?,?)";
			} else if (type == 2) {
				sql = "insert into performancecase(id,content,casedataid) values(?,?,?)";
			} else if (type == 3) {
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

	

}

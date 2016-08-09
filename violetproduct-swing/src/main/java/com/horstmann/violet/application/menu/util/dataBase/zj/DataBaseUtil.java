package com.horstmann.violet.application.menu.util.dataBase.zj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public  class DataBaseUtil {
	static ResultSet ret = null;  
 	public static final String url ="jdbc:mysql://localhost:3306/mydb"; 
    public static final String name ="com.mysql.jdbc.Driver";  
    public static final String user ="root";  
    public static final String password ="123198";  
    public static Connection conn = null;  
    public static PreparedStatement pst = null;  
    /**
     * ɾ�����е���������
     * @param sql ɾ����䣺 delete from ����  where 1=1;
     */
   public static void deleteAll(String sql) {
		try {
			init();
         	pst = conn.prepareStatement(sql);//׼��ִ�����   
         	pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
    
    /**
     * ��ѯ���ݿ��б�ļ�¼�ĸ���
     * @param sql select count(*) from ����
     * @return
     */
    public static int getObjNum(String sql) {
    	int i=0;
		try {
			init();
         	pst = conn.prepareStatement(sql);//׼��ִ�����   
			ret =pst.executeQuery();
			while(ret.next()){
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return i;
	}
   /**
    *��ѯ���ݿ�����е�RealProcess����
    * @param sql ��ѯRealProcess��sql��� select * from real_process
    * @return
    */
	public static List<RealProcess> getAllProcess(String sql) {
		List<RealProcess>  rpList =new ArrayList<RealProcess>();
		try {
			init();
         	pst = conn.prepareStatement(sql);//׼��ִ�����   
			ret =pst.executeQuery();
			while(ret.next()){
			RealProcess rp =new RealProcess();
			int id =Integer.parseInt(ret.getString(1));
			rp.setPid(id);
			String operation =ret.getString(2);
			rp.setOperation(operation);
			String inputInfo=ret.getString(3);
			rp.setInputInfo(inputInfo);	
			rpList.add(rp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return rpList;
	}
	/**
	 * ����RealProcess�������ݿ�	 
	 * @param rpList RealProcess����ļ��� insert into real_process values(?,?,?);
	 * @param sql �������
	 */
	public static void insert2RealProcess(List<RealProcess> rpList,String sql) {
			try {
				init();
	         	for (RealProcess p :rpList) {
	         		pst = conn.prepareStatement(sql);//׼��ִ�����   
	         		pst.setInt(1,p.getPid());
	         		pst.setString(2, p.getOperation());
	         		pst.setString(3, p.getInputInfo());
					pst.executeUpdate();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close();
			}
	}
	/**
	 * ��ѯ���е�RealTestCaseʵ����¼ 
	 * @param sql 
	 * @return
	 */
	public static List<RealTestCase> getAllRealTestCase(String sql) {
		List<RealTestCase> rtcList =new ArrayList<RealTestCase>();
		try {
			init();
         	pst = conn.prepareStatement(sql);//׼��ִ�����   
			ret =pst.executeQuery();
			while(ret.next()){
			RealTestCase rtc =new RealTestCase();
			int id =Integer.parseInt(ret.getString(1));
			rtc.setId(id);
			String name =ret.getString(2);
			rtc.setName(name);
			String router=ret.getString(3);
			rtc.setRealTestRouter(router);
			rtcList.add(rtc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return rtcList;
	}
	/**
	 * ����RealTestCase�����ݿ�
	 * @param rtcList RealTestCase�Ķ��󼯺�
	 * @param sql
	 */
	public static void insert2RealTestCase(List<RealTestCase> rtcList,String sql) {
		try {
			init();
         	for (RealTestCase p :rtcList) {
         		pst = conn.prepareStatement(sql);//׼��ִ�����   
         		pst.setInt(1,p.getId());
         		pst.setString(2, p.getName());
         		pst.setString(3, p.getRealTestRouter());
				pst.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}

	/**
	  * ��ʼ�����ݿ�����
	  */
	public static void init(){
	    try {
			Class.forName(name);
			conn = DriverManager.getConnection(url, user, password);//��ȡ����  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	  * �ر����ݿ���Դ
	  */
	public static void  close(){
		try {
			ret.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ������������л�ȡ���е�״̬�ڵ�
	 * @param sql
	 * @return
	 */
	public static List<AbstractState> getAllState(String sql) {
		List<AbstractState> asList =new ArrayList<AbstractState>();
		try {
			init();
         	pst = conn.prepareStatement(sql);//׼��ִ�����   
			ret =pst.executeQuery();
			while(ret.next()){
			AbstractState as=new AbstractState();
			int id =Integer.parseInt(ret.getString(1));
			as.setSid(id);
			String sname=ret.getString(2);
			as.setSname(sname);
			String innerDBM=ret.getString(3);
			as.setInvariantDBM(innerDBM);
			String position =ret.getString(4);
			as.setPosition(position);
			String type =ret.getString(5);
			as.setType(type);
			asList.add(as);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return asList;
	}
	/**
	 * ���������������е�State�ڵ�
	 * @param asList
	 * @param sql
	 */
	public static void insert2State(List<AbstractState> asList,String sql) {
		try {
			init();
         	for (AbstractState p :asList) {
         		pst = conn.prepareStatement(sql);//׼��ִ�����   
         		pst.setInt(1,p.getSid());
         		pst.setString(2, p.getSname());
         		pst.setString(3, p.getInvariantDBM());
         		pst.setString(4, p.getPosition());
         		pst.setString(5, p.getType());
				pst.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	/**
	 * ������������л�ȡ���е�Transition�ڵ�
	 * @param sql
	 * @return
	 */
	public static List<AbstractTransition> getAllTranstion(String sql) {
		List<AbstractTransition> atList =new ArrayList<AbstractTransition>();
		try {
			init();
         	pst = conn.prepareStatement(sql);//׼��ִ�����   
			ret =pst.executeQuery();
			while(ret.next()){
			AbstractTransition at=new AbstractTransition();
			int id =Integer.parseInt(ret.getString(1));
			at.setTid(id);
			String target=ret.getString(2);
			at.setTarget(target);
			String source=ret.getString(3);
			at.setSource(source);
			String constraintDBM =ret.getString(4);
			at.setConstraintDBM(constraintDBM);
			atList.add(at);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return atList;
	}
	/**
	 * ���������������е�Transition�ڵ�
	 * @param asList
	 * @param sql
	 */
	public static void insert2Transition(List<AbstractTransition> atList,String sql) {
		try {
			init();
         	for (AbstractTransition p :atList) {
         		pst = conn.prepareStatement(sql);//׼��ִ�����   
         		pst.setInt(1,p.getTid());
         		pst.setString(2, p.getTarget());
         		pst.setString(3, p.getSource());
         		pst.setString(4, p.getConstraintDBM());
				pst.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	/**
	 * ��ѯ���е�AbstractTestCaseʵ����¼ 
	 * @param sql 
	 * @return
	 */
	public static List<AbstractTestCase> getAllAbstractTestCase(String sql) {
		List<AbstractTestCase> rtcList =new ArrayList<AbstractTestCase>();
		try {
			init();
         	pst = conn.prepareStatement(sql);//׼��ִ�����   
			ret =pst.executeQuery();
			while(ret.next()){
			AbstractTestCase atc =new AbstractTestCase();
			int id =Integer.parseInt(ret.getString(1));
			atc.setTid(id);
			String name =ret.getString(2);
			atc.setTname(name);
			String router=ret.getString(3);
			atc.setTestRouter(router);
			rtcList.add(atc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return rtcList;
	}
	/**
	 * ����AbstractTestCase�����ݿ�
	 * @param rtcList AbstractTestCase�Ķ��󼯺�
	 * @param sql
	 */
	public static void insert2AbstractTestCase(List<AbstractTestCase> rtcList,String sql) {
		try {
			init();
         	for (AbstractTestCase p :rtcList) {
         		pst = conn.prepareStatement(sql);//׼��ִ�����   
         		pst.setInt(1,p.getTid());
         		pst.setString(2, p.getTname());
         		pst.setString(3, p.getTestRouter());
				pst.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	/**
	 * ͨ��id��ȡ��Ӧ��abstract_state�ڵ�
	 * @param ids id�����飬���ж��id
	 * @param sql ��ѯ��sql���
	 * @return List<AbstractState> abstract_state�ڵ�ļ���
	 */
	public static List<AbstractState> getStateByID(int[] ids, String sql) {
		List<AbstractState> asList =new ArrayList<AbstractState>();
		try {
			init();
         	pst = conn.prepareStatement(sql);//׼��ִ�����   
         	for (int i = 0; i < ids.length; i++) {
         		ret =pst.executeQuery();
         		while(ret.next()){
        			AbstractState as=new AbstractState();
        			int id =Integer.parseInt(ret.getString(1));
        			as.setSid(id);
        			String sname=ret.getString(2);
        			as.setSname(sname);
        			String innerDBM=ret.getString(3);
        			as.setInvariantDBM(innerDBM);
        			String position =ret.getString(4);
        			as.setPosition(position);
        			String type =ret.getString(5);
        			as.setType(type);
        			asList.add(as);
         		}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return asList;
	}
	/**
	 * ͨ��id��ѯprocess�ڵ�
	 * @param ids id�����飬���id����
	 * @param sql ��ѯ���
	 * @return List<RealProcess> process�ڵ㼯��
	 */
	public static List<RealProcess> getProcessByID(int[] ids,String sql) {
		List<RealProcess>  rpList =new ArrayList<RealProcess>();
		try {
			init();
				pst = conn.prepareStatement(sql);//׼��ִ�����   
	         	for (int i = 0; i < ids.length; i++) {
	         		pst.setInt(1, ids[i]);
	         		ret =pst.executeQuery();
	    			while(ret.next()){
	    			RealProcess rp =new RealProcess();
	    			int id =Integer.parseInt(ret.getString(1));
	    			rp.setPid(id);
	    			String operation =ret.getString(2);
	    			rp.setOperation(operation);
	    			String inputInfo=ret.getString(3);
	    			rp.setInputInfo(inputInfo);	
	    			rpList.add(rp);
    			}
         	}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return rpList;
	}
	/**
	 * ��ȡʵ������������չʾ����
	 * @param sql1  ��ѯ���е�RealTestCase
	 * @param sql2  ��ѯ���е�Process
	 * @return
	 */
	public static  List<RealTestCaseVO> getTestCaseVO(String sql1, String sql2) {
		List<RealTestCaseVO> list=new ArrayList<RealTestCaseVO>();
    	List<RealTestCase>rl =DataBaseUtil.getAllRealTestCase(sql1);
    	for(RealTestCase r :rl){
    		RealTestCaseVO rv =new RealTestCaseVO();
    		rv.setId(r.getId()+"");
    		rv.setName(r.getName());
    		String routerid=r.getRealTestRouter();
    		String[] sids=routerid.split(",");
    		int[] ids =new int[sids.length] ;
    		for (int i = 0; i < sids.length; i++) {
				int id= Integer.parseInt(sids[i]);
				ids[i]=id;
			}
    		List<RealProcess> rp=DataBaseUtil.getProcessByID(ids,sql2);
    		StringBuffer sb =new StringBuffer();
    		for(RealProcess p :rp){
    			if(!(p.equals(rp.get(rp.size()-1)))){
    				sb.append(p.getOperation()+"("+p.getInputInfo()+"),");
    			}else{
    				sb.append(p.getOperation()+"("+p.getInputInfo()+")");
    			}
    			
    		}
    		rv.setProcessList(sb.toString());
    		list.add(rv);	
    	}
		return list;
	}
	
	
	/**
	 * չʾ��ѯ���е�AbstractTestCaseʵ����¼ 
	 * @return AbstractTestCaseVO�ļ���
	 */
	public static List<AbstractTestCaseVO> ShowAllAbstractTestCase() {
		List<AbstractTestCaseVO> list =new ArrayList<AbstractTestCaseVO>();
		String sql3 ="select * from abstract_testcase";
		List<AbstractTestCase> abList=DataBaseUtil.getAllAbstractTestCase(sql3);
		for(AbstractTestCase state:abList){
			AbstractTestCaseVO s =new AbstractTestCaseVO();
			s.setId(state.getTid()+"");
			s.setName(state.getTname());
			String router=state.getTestRouter();
//			System.out.println("router��"+router);
			//����router����ȡ��ϸ��state��transition��Ϣ
			String[] strs =router.split(",");
			Pattern p =Pattern.compile("(\\d+)");//�ƶ�������ʽ����
			StringBuffer sb =new StringBuffer();
//			System.out.println("���ȣ�"+strs.length);
			try {
				init();
				for(String str:strs){
					Matcher m =p.matcher(str);//ƥ��������ʽ
					String id="";
					if(m.find())
						id =m.group(1);
//					System.out.println("ÿ������"+str);
//					System.out.println("id��"+id);
					if(str.contains("s")){
						pst =conn.prepareStatement("select * from abstract_state where sid =?");
						pst.setInt(1,Integer.parseInt(id));
						ret= pst.executeQuery();
						while(ret.next()){
							sb.append("State"+"(name:"+ret.getString(2)+";DBM:"+ret.getString(3)+";Position:"+ret.getString(4)+")");
							
						}
					}else if(str.contains("t")){
						pst =conn.prepareStatement("select * from abstract_transition where tid =?");
						pst.setInt(1,Integer.parseInt(id));
						ret= pst.executeQuery();
						while(ret.next()){
							sb.append("Transition"+"(id:"+ret.getString(1)+" ;DBM:"+ret.getString(7));
						}
					}
					if(!(str.equals(strs[strs.length-1]))){
						sb.append(" --> ");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close();
			}
			s.setTextRouter(sb.toString());
			list.add(s);
		}
		
		//�����ѯ����·����Ϣ
		for(AbstractTestCaseVO v :list){
			System.out.println(v);
		}
		return list;
	}
	

	
}

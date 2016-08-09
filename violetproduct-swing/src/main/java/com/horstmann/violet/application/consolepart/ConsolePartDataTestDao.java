package com.horstmann.violet.application.consolepart;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.horstmann.violet.application.menu.util.dataBase.zj.AbstractTestCase;
import com.horstmann.violet.application.menu.util.dataBase.zj.AbstractTestCaseVO;
import com.horstmann.violet.application.menu.util.dataBase.zj.DataBaseUtil;
import com.horstmann.violet.application.menu.util.dataBase.zj.RealProcess;
import com.horstmann.violet.application.menu.util.dataBase.zj.RealTestCase;
import com.horstmann.violet.application.menu.util.dataBase.zj.RealTestCaseVO;

public class ConsolePartDataTestDao {
	/**
	 * ��ȡʵ������������
	 * @return
	 */
	public static List<RealTestCaseVO> getRealTestCaseList() {
		List<RealTestCaseVO> list=new ArrayList<RealTestCaseVO>();
		//����ֻ��ѯ������¼������Ҫ�޸�
    	List<RealTestCase>rl =DataBaseUtil.getAllRealTestCase("select * from real_testcase ");
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
    		List<RealProcess> rp=DataBaseUtil.getProcessByID(ids, "select * from real_process where pid =?");
    		StringBuffer sb =new StringBuffer();
    		for(RealProcess p :rp){
    			if(!(p.equals(rp.get(rp.size()-1)))){
    				sb.append(p.getOperation()+"("+p.getInputInfo()+")---->");
    			}else{
    				sb.append(p.getOperation()+"("+p.getInputInfo()+")");
    			}
    			
    		}
    		rv.setProcessList(sb.toString());
    		list.add(rv);	
    	}
    	return list;
		
	}
	public static List<AbstractTestCaseVO> getAbsTestCaseList() {
	
		//����ֻ��ѯ������¼������Ҫ�޸�
    	List<AbstractTestCaseVO> rl =DataBaseUtil.ShowAllAbstractTestCase();
  
    	return rl;
		
	}
	
	
	

}

package com.horstmann.violet.application.consolepart;

import java.util.ArrayList;
import java.util.List;

import com.horstmann.violet.application.menu.util.dataBase.DataBaseUtil;
import com.horstmann.violet.application.menu.util.dataBase.RealProcess;
import com.horstmann.violet.application.menu.util.dataBase.RealTestCase;
import com.horstmann.violet.application.menu.util.dataBase.RealTestCaseVO;

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
	
	

}

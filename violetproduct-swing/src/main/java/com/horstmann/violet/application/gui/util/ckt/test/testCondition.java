package com.horstmann.violet.application.gui.util.ckt.test;

import java.util.ArrayList;
import java.util.List;

import com.horstmann.violet.application.gui.util.ckt.testcase.GetMap;
import com.horstmann.violet.application.gui.util.ckt.testcase.Result1;

public class testCondition {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String tran = "cycle=2.5ms--I<_num_tasks#I:uint8_t,_num_tasks:uint8_t--dt>=interval_ticks#dt:uint16_t,interval_ticks:uint16_t--_task_time_allowed<=time_available#_task_time_allowed:uint32_t,time_available:uint32_t--I==3#I:uint8_t--ap.usb_connected==0 && failsafe.battery==0 && has_exhausted==true,0<=ap.usb_connected<=1,0<=failsafe.battery<=1#failsafe.battery:uint8_t,ap.usb_connected:uint8_t,has_exhausted:bool--failsafe.battery==0,0<=failsafe.battery<=1#failsafe.battery:uint8_t--g.failsafe_battery_enabled != 0 && has_armed==true#g.failsafe_battery_enabled:int,has_armed:bool--control_mode==0 || control_mode==1#control_mode:int8_t--ap.throttle_zero==1 || ap.land_complete==1,0<=ap.throttle_zero<=1,0<=ap.land_complete<=1#ap.land_complete:uint8_t,ap.throttle_zero:uint8_t";
		System.out.println("condition---->"+tran);
		List<String> result2=new ArrayList<String>();//存放condition里面最终实例化结果
		if(tran.equals("null")){	
			result2.add(null);
		}else{
			if(!tran.equals("null")){
				if(tran.contains("--")){
					List<List<String>> con_result = new ArrayList<List<String>>();
					List<String> con_result1;
					String getcon[] = tran.split("--");
					for(int ii=0;ii<getcon.length;ii++){
						if(!(GetMap.get_condMap(getcon[ii])==null)){
							String inn = getcon[ii].replace("false", "False").replace("true", "True").replace("->", "$");
							con_result1 = Result1.getResult(inn);
							if((con_result1.size()>0)&&!(con_result1.get(0).equals(null))){
								con_result.add(con_result1);
							}
						}
					}
					if((con_result.size()>0)&&!(con_result.get(0).equals(null))){
						System.out.println("in_result.size()--->"+con_result.size());
						dis(0,con_result);
						result2 = re;
					}else{
						if(!(GetMap.get_condMap(tran)==null)){//map里面为空，即没有参数
							String inn = tran.replace("false", "False").replace("true", "True").replace("->", "$");
							result2 = Result1.getResult(inn);
						}else{
							if((GetMap.get_inMap(tran)==null)){
								result2.add(null);
							}
						}
					}
				}
			}
		}
		for(int i=0;i<result2.size();i++){
			System.out.println("result1---->"+result2.get(i));
		}
	}





	/*if(!(GetMap.get_condMap(tran)==null)){
						String tra = tran.replace("false", "False").replace("true", "True").replace("->", "$");
                        //result2 = Result.getResult(tra);
						System.out.println("tra----"+tra);
						result2 = Result1.getResult(tra);

						//result2 = testbdscs.getResult(tra);						
						for(int ii=0;ii<result2.size();ii++){
							//System.out.println("condition里解"+ii+"为:"+result2.get(ii));
						}
					}*/

	public static String s="";
	public static List<String> re = new ArrayList<String>();
	public static void dis(int n, List<List<String>> l) {
		if (n == l.size()) {
			re.add(s);
			return;
		} else {
			List<String> sCollection = l.get(n);
			for (int i = 0; i < sCollection.size(); i++) {
				String s1 = s;
				if(s==""){
					s  = sCollection.get(i);
				}else{
					s =s + ","+ sCollection.get(i);
				}				
				dis(n+1,l);
				s = s1;
			}
		}
	}
}

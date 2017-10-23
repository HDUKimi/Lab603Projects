package com.horstmann.violet.application.gui.util.ckt.testcase;

import java.util.ArrayList;
import java.util.List;

public class OutSide {

	public static void main(String[] args) {
		String s = "i<_num_tasks,dt>=interval_ticks,_task_time_allowed<=time_available,i==10,failsafe.gcs!=0,g.failsafe_gcs!=0,failsafe.last_heartbeat_ms!=0,failsafe.rc_override_active!=0,control_mode==4,0<=failsafe.gcs<=1,0<=g.failsafe_gcs<=1,0<=failsafe.rc_override_active<=1";
		//String s = "1<=x1<=7,1<=x2<7,1<x3<=7,1<x4<7,7>=x5>=1,7>=x6>1,7>x7>=1,7>x8>1,x9>=1,7>=x10,x11<=7,1<=x12,x13<7,x14>1,1<x15,x==True";
		//String s = "1<=x1<=2,1<=x2<2,1<x3<=2,1<x4<2,2>=x5>=1,2>=x6>1,2>x7>=1,2>x8>1,x9>=1,7>=x10,x11<=7,1<=x12,x13<7,x14>1,1<x15,x==True";
		//String s = "x1<-7";
		//String s = "i<_num_tasks,dt>=interval_ticks,_task_time_allowed<=time_available,0<=i<=16,0<=_num_tasks<=256,0<=dt<=65536,0<=_task_time_allowed<=65536,0<=i<=256,0<=time_available<=65536,0<=interval_ticks<=65536";
		//getBorder(s);
		String[] ss1 = s.split("--");
		for(int i=0;i<ss1.length;i++){
			System.out.println("最终结果："+getOutSide(ss1[i].split("#")[0]));
		}
		//System.out.println("最终结果："+getBorder(s));
		//System.out.println(getDoubleBorder(s));
		/*for(int i=0;i<getBorder(s).size();i++){
			System.out.println(getBorder(s).get(i));
		}*/
	}
	
	
	public static String getOutSide(String domain1) {
		List<String> bdsSet = new ArrayList<String>();
		List<String> Set = new ArrayList<String>();
		String[] bds = domain1.split(",");
		for(int i=0;i<bds.length;i++){
			System.out.println(bds[i]+"=====");
			if(getNumber(bds[i])){
				System.out.println("=====" + bds[i]+"=====");
				if(bds[i].contains("!")){
					String s = bds[i].replace("!", "=");
					bdsSet.add(s);
				}else{
					String domain = bds[i];
					List<String> border = new ArrayList<String>();
					String cs = null;
					if (domain.contains("<=")) {
						String[] strs = domain.split("<=");				
						System.out.println("需处理（有<=）："+domain);
						if (strs.length == 3) {	
							//System.out.println("例如1<=x<=7");//strs[0]=1;strs[2]=7
							cs = strs[1];
							String s = cs + "<" + strs[0] + "||" + cs + ">" + strs[2];
							bdsSet.add(s);
						} else{ 
							if (strs[0].contains("<")||strs[1].contains("<")) {
								//System.out.println("     例如1<x<=7或1<=x<7");//
								if (strs[1].contains("<")) {  //1<=x<7
									//System.out.println("     例如1<=x<7");// strs[0]=1; 
									int integer = Integer.parseInt(strs[1].split("<")[1]);//integer=7
									cs = strs[1].split("<")[0];
									String s = cs + "<" + strs[0] + "||" + cs + ">=" + integer;
									bdsSet.add(s);
								} else {
									if (strs[0].contains("<")) {
										//System.out.println("     例如1<x<=7");//strs[1]=7
										int integer = Integer.parseInt(strs[0].split("<")[0]);
										cs = strs[0].split("<")[1];
										String s = cs + "<=" + integer + "||" + cs + ">" + strs[1];
										bdsSet.add(s);
									}						
								}
							} else {
								//System.out.println("     例如1<=x或x<=7");//
								int s1=domain.substring(0,1).toCharArray()[0];
								if(!((s1>=48&&s1<=57)||s1==45)){//第一个为参数
									//System.out.println("     例如x<=7");//strs[1]=7
									cs = strs[0];
									String s =  cs + ">" + strs[1];
									bdsSet.add(s);
								}else{
									//System.out.println("     例如1<=x");//strs[0]=1
									cs = strs[1];
									String s =  cs + "<" + strs[0];
									bdsSet.add(s);
								}					
							}
						}
					} else{ 
						if (domain.contains(">=")) {
							System.out.println("需处理（有>=）："+domain);
							String[] strs = domain.split(">=");
							if (strs.length == 3) {	
								//System.out.println("例如7>=x>=1");//strs[0]=7;strs[2]=1
								cs = strs[1];
								String s = cs + ">" + strs[0] + "||" + cs + "<" + strs[2];
								bdsSet.add(s);
							} else{ 
								if (strs[0].contains(">")||strs[1].contains(">")) {
									//System.out.println("     例如7>=x>1或7>x>=1");//
									if (strs[1].contains(">")) {  //1<=x<7
										//System.out.println("     例如7>=x>1");//strs[0]=7;integer=1
										int integer = Integer.parseInt(strs[1].split(">")[1]);
										cs = strs[1].split(">")[0];
										String s = cs + ">" + strs[0] + "||" + cs + "<=" + integer;
										bdsSet.add(s);
									} else {
										if (strs[0].contains(">")) {
											//System.out.println("     例如7>x>=1");//strs[1]=1;integer=7										
											int integer = Integer.parseInt(strs[0].split(">")[0]);
											cs = strs[0].split(">")[1];
											String s = cs + ">=" + integer + "||" + cs + "<" + strs[1];
											bdsSet.add(s);
										}						
									}
								}else{
									//System.out.println("     例如x>=1或7>=x");//
									int s1=domain.substring(0,1).toCharArray()[0];
									if(!((s1>=48&&s1<=57)||s1==45)){//第一个为参数
										//System.out.println("     例如x>=1");//domain.split(">=")[1]=1
										cs = domain.split(">=")[0];
										String s =  cs + "<" + domain.split(">=")[1];
										bdsSet.add(s);
									}else{
										//System.out.println("     例如7>=x");//domain.split(">=")[0]=7
										cs = domain.split(">=")[1];
										String s = cs + ">" + domain.split(">=")[0];
										bdsSet.add(s);
									}	
								}
							}		
						} else{ 					
							if (domain.contains("<")) { //不包含<=也不包含>=
								System.out.println("需处理（有<）："+domain);
								String[] strs = domain.split("<");
								//System.out.println("     例如1<x<7或x<7或1<x");//
								if (strs.length == 3) {
									//System.out.println("     例如1<x<7");//Integer.parseInt(strs[0])=1;Integer.parseInt(strs[2])=7
									cs = strs[1];
									String s = cs + "<=" + Integer.parseInt(strs[0]) + "||" + cs + ">=" + Integer.parseInt(strs[2]);
									bdsSet.add(s);
								} else {
									int s1=domain.substring(0,1).toCharArray()[0];
									if(!((s1>=48&&s1<=57)||s1==45)){//第一个为参数
										//System.out.println("     例如x<7");//Integer.parseInt(strs[1])=7
										cs = strs[0];
										String s = cs + ">=" + Integer.parseInt(strs[1]);
										bdsSet.add(s);
									}else{
										//System.out.println("     例如1<x");//Integer.parseInt(strs[0])=1
										cs = strs[1]; 
										String s = cs + "<=" + Integer.parseInt(strs[0]);
										bdsSet.add(s);
									}					
								}
							} else{ 
								if (domain.contains(">")) { //不包含<=也不包含>=也不包含<
									System.out.println("需处理（有>）："+domain);
									//System.out.println("     例如7>x>1或x>1或7>x");//
									String[] strs = domain.split(">");
									if (strs.length == 3) {
										//System.out.println("     例如7>x>1");//Integer.parseInt(strs[0])=7;Integer.parseInt(strs[2])=1
										cs = strs[1];
										String s = cs + ">=" + Integer.parseInt(strs[0]) + "||" + cs + "<=" + Integer.parseInt(strs[2]);
										bdsSet.add(s);
									} else {
										int s1=domain.substring(0,1).toCharArray()[0];
										if(!((s1>=48&&s1<=57)||s1==45)){//第一个为参数
											//System.out.println("     例如x>1");//Integer.parseInt(strs[1])=1
											cs = strs[0]; 
											String s = cs + "<=" + Integer.parseInt(strs[1]);
											bdsSet.add(s);
										}else{
											//System.out.println("     例如7>x");//Integer.parseInt(strs[0])=7
											cs = strs[1];  
											String s = cs + ">=" + Integer.parseInt(strs[0]);
											bdsSet.add(s);
										}
									}
								}
							}
						}
					}			
				}
				}else{
				Set.add(bds[i]);
			}
		}
		String outside = null;
		String outside1 = null;
		String outside2 = null;
		//一个参数的求出所有边界 只需要满足一组解就可以
		if(bdsSet.size()!=0){
			for(int i=0; i<bdsSet.size(); i++){
				if(i==0){
					outside1 = bdsSet.get(0);
				}else{
					if(null == outside1){
						outside1 = bdsSet.get(i);
					}else{
						outside1 = outside1 + "||" + bdsSet.get(i);
					}
				}
			}					
		}
		//两个参数的原样输出
		if(null != outside1){
			outside = outside1;
			if(Set.size()>0){
				for(int j=0; j<Set.size(); j++){
					outside = outside + "," + Set.get(j);
				}
			}
		}else{
			if((null == outside1) && (Set.size()>0)){
				
			}
		}
		return outside;
	}
/**
 * 判断每个不等式里参数是否只有一个
 * @param domain1
 * @return
 */
	public static boolean getNumber(String bds) {
		/*if(bds.contains("!")){
			return false;
		}else{*/
			int number=0;
			String[] css = bds.trim().split("[!-+<>=]=?");
			for(String cs:css){
				if(!cs.equals("")){
					//System.out.println("cs:"+cs);
					int s1=cs.trim().substring(0,1).toCharArray()[0];
					//System.out.println("css:"+s1);
					if(!((s1>=48&&s1<=57)||s1==45)){//第一个为参数
						number++;
					}
				}				
			}
			if(number<=1){
				//System.out.println("一个参数");
				return true;
			}else{
				//System.out.println("多个参数");
				return false;
			}
		//}		
	}

}

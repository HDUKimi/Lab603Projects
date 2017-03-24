package com.horstmann.violet.application.gui.util.ckt.testcase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 求解连续型参数变量定义域的边界值，返回边界值集合。
 * 
 */
public class Border {
	public static void main(String[] args) {
		String s = "i<_num_tasks,dt>=interval_ticks,_task_time_allowed<=time_available,i==10,failsafe.gcs!=0,g.failsafe_gcs!=0,failsafe.last_heartbeat_ms!=0,failsafe.rc_override_active!=0,control_mode==4,0<=failsafe.gcs<=1,0<=g.failsafe_gcs<=1,0<=failsafe.rc_override_active<=1";
		//String s = "1<=x1<=7,1<=x2<7,1<x3<=7,1<x4<7,7>=x5>=1,7>=x6>1,7>x7>=1,7>x8>1,x9>=1,7>=x10,x11<=7,1<=x12,x13<7,x14>1,1<x15,x==True";
		//String s = "1<=x1<=2,1<=x2<2,1<x3<=2,1<x4<2,2>=x5>=1,2>=x6>1,2>x7>=1,2>x8>1,x9>=1,7>=x10,x11<=7,1<=x12,x13<7,x14>1,1<x15,x==True";
		//String s = "x1<-7";
		//String s = "i<_num_tasks,dt>=interval_ticks,_task_time_allowed<=time_available,0<=i<=16,0<=_num_tasks<=256,0<=dt<=65536,0<=_task_time_allowed<=65536,0<=i<=256,0<=time_available<=65536,0<=interval_ticks<=65536";
		//getBorder(s);
		String[] ss1 = s.split("--");
		for(int i=0;i<ss1.length;i++){
			System.out.println("最终结果："+getBorder(ss1[i].split("#")[0]));
		}
		//System.out.println("最终结果："+getBorder(s));
		//System.out.println(getDoubleBorder(s));
		/*for(int i=0;i<getBorder(s).size();i++){
			System.out.println(getBorder(s).get(i));
		}*/
	}
/**
 * 得到整数不等式的边界组合等式
 * @param domain1
 * @return
 */
	public static String getBorder(String domain1) {
		System.out.println("---整数边界---");
		System.out.println("传入的不等式："+domain1);
		String[] bds = domain1.split(",");
		List<String> borderBds2 = new ArrayList<String>();
		String borderBds=null;
		//System.out.println("长度为"+bds.length);
		/*for(int i=0;i<bds.length;i++){			
			System.out.print("     "+bds[i]);
		}*/
		//System.out.println();
		for(int i=0;i<bds.length;i++){
			//System.out.println("bds[i]: "+bds[i]);//
			if(getNumber(bds[i])){				
				//System.out.println("-----------------------");
				String borderBds1 = null;
				String domain = bds[i];
				List<String> border = new ArrayList<String>();
				String cs = null;
				if (domain.contains("<=")) {
					String[] strs = domain.split("<=");				
					System.out.println("需处理（有<=）："+domain);
					if (strs.length == 3) {	
						//System.out.println("例如1<=x<=7");//
						border.add(strs[0]);
						border.add(strs[2]);
						cs = strs[1];
					} else{ 
						if (strs[0].contains("<")||strs[1].contains("<")) {
							//System.out.println("     例如1<x<=7或1<=x<7");//
							if (strs[1].contains("<")) {  //1<=x<7
								//System.out.println("     例如1<=x<7");//
								border.add(strs[0]);						
								//System.out.println("border"+border);
								int integer = Integer.parseInt(strs[1].split("<")[1]);
								if(integer-1>Integer.parseInt(strs[0])){
									border.add(Integer.parseInt(strs[1].split("<")[1]) - 1
											+ "");
								}
								cs = strs[1].split("<")[0];
							} else {
								if (strs[0].contains("<")) {
									//System.out.println("     例如1<x<=7");//
									border.add(strs[1]);
									int integer = Integer.parseInt(strs[0].split("<")[0]);
									if(integer+1<Integer.parseInt(strs[1])){
									border.add(Integer.parseInt(strs[0].split("<")[0]) + 1
											+ "");
									}
									cs = strs[0].split("<")[1];
								}						
							}
						} else {
							//System.out.println("     例如1<=x或x<=7");//
							int s1=domain.substring(0,1).toCharArray()[0];
							if(!((s1>=48&&s1<=57)||s1==45)){//第一个为参数
								//System.out.println("     例如x<=7");//
								border.add(strs[1]);
								cs = strs[0];
							}else{
								//System.out.println("     例如1<=x");//
								border.add(strs[0]);
								cs = strs[1];
							}					
						}
					}
				} else{ 
					if (domain.contains(">=")) {
						System.out.println("需处理（有>=）："+domain);
						String[] strs = domain.split(">=");
						if (strs.length == 3) {	
							//System.out.println("例如7>=x>=1");//
							border.add(strs[0]);
							border.add(strs[2]);
							cs = strs[1];
						} else{ 
							if (strs[0].contains(">")||strs[1].contains(">")) {
								//System.out.println("     例如7>=x>1或7>x>=1");//
								if (strs[1].contains(">")) {  //1<=x<7
									//System.out.println("     例如7>=x>1");//
									border.add(strs[0]);						
									//System.out.println("border"+border);
									int integer = Integer.parseInt(strs[1].split(">")[1]);
									if(integer+1<Integer.parseInt(strs[0])){
										border.add(Integer.parseInt(strs[1].split(">")[1]) + 1
												+ "");
									}
									cs = strs[1].split(">")[0];
								} else {
									if (strs[0].contains(">")) {
										//System.out.println("     例如7>x>=1");//
										border.add(strs[1]);
										int integer = Integer.parseInt(strs[0].split(">")[0]);
										if(integer-1>Integer.parseInt(strs[1])){
										border.add(Integer.parseInt(strs[0].split(">")[0]) - 1
												+ "");
										}
										cs = strs[0].split(">")[1];
									}						
								}
							}else{
								//System.out.println("     例如x>=1或7>=x");//
								int s1=domain.substring(0,1).toCharArray()[0];
								if(!((s1>=48&&s1<=57)||s1==45)){//第一个为参数
									//System.out.println("     例如x>=1");//
									border.add(domain.split(">=")[1]);
									cs = domain.split(">=")[0];
								}else{
									//System.out.println("     例如7>=x");//
									border.add(domain.split(">=")[0]);
									cs = domain.split(">=")[1];
								}	
							}
						}		
					} else{ 					
						if (domain.contains("<")) { //不包含<=也不包含>=
							System.out.println("需处理（有<）："+domain);
							String[] strs = domain.split("<");
							//System.out.println("     例如1<x<7或x<7或1<x");//
							if (strs.length == 3) {
								//System.out.println("     例如1<x<7");//
								if(Integer.parseInt(strs[0])+1<Integer.parseInt(strs[2])-1){
								border.add(Integer.parseInt(strs[0]) + 1 + "");					
								border.add(Integer.parseInt(strs[2]) - 1 + "");
								}
								cs = strs[1];
							} else {
								int s1=domain.substring(0,1).toCharArray()[0];
								if(!((s1>=48&&s1<=57)||s1==45)){//第一个为参数
									//System.out.println("     例如x<7");//
									border.add(Integer.parseInt(strs[1]) - 1 + "");
									cs = strs[0];
								}else{
									//System.out.println("     例如1<x");//
									border.add(Integer.parseInt(strs[0]) + 1 + "");
									cs = strs[1]; 
								}					
							}
						} else{ 
							if (domain.contains(">")) { //不包含<=也不包含>=也不包含<
								System.out.println("需处理（有>）："+domain);
								//System.out.println("     例如7>x>1或x>1或7>x");//
								String[] strs = domain.split(">");
								if (strs.length == 3) {
									//System.out.println("     例如7>x>1");//
									if(Integer.parseInt(strs[0])-1>Integer.parseInt(strs[2])+1){
									border.add(Integer.parseInt(strs[0]) - 1 + "");					
									border.add(Integer.parseInt(strs[2]) + 1 + "");
									}
									cs = strs[1];
								} else {
									int s1=domain.substring(0,1).toCharArray()[0];
									if(!((s1>=48&&s1<=57)||s1==45)){//第一个为参数
										//System.out.println("     例如x>1");//
										border.add(Integer.parseInt(strs[1]) + 1 + "");
										cs = strs[0]; 
									}else{
										//System.out.println("     例如7>x");//
										border.add(Integer.parseInt(strs[0]) - 1 + "");
										cs = strs[1];  
									}
								}
							}
						}
					}
				}
				if(cs!=null&&border.size()>0){
					for(int j=0;j<border.size();j++){
						if(j==0){
							borderBds1 = cs+"=="+border.get(0);
						}else{
							borderBds1 = borderBds1+"||"+cs+"=="+border.get(j);
						}				
					}
					System.out.println("处理结果："+borderBds1);
				}
				borderBds2.add(borderBds1);
			
			}
		}
		/*System.out.println("----------------------------");
		System.out.println("----------------------------");
		for(int n=0;n<borderBds2.size();n++){
			System.out.println("输出结果："+borderBds2.get(n));
		}
		System.out.println("----------------------------");
		System.out.println("----------------------------");*/
		for(int n=0;n<borderBds2.size();n++){
			if(borderBds2.get(n)!=null){
				if(n==0){
					borderBds = borderBds2.get(0);
				}else{
					//System.out.println("borderBds::"+borderBds);
					if((null==borderBds)){
						borderBds = borderBds2.get(n);
					}
					else{
						borderBds = borderBds+"||"+borderBds2.get(n);
					}
					
				}
			}

		}
		System.out.println("整数最终边界："+borderBds);
		return borderBds;

	}

/**
 * 得到小数不等式的边界组合等式
 * @param domain1
 * @return
 */
	public static String getDoubleBorder(String domain1) {
		System.out.println("---小数边界---");
		System.out.println("传入的不等式："+domain1);
		String[] bds = domain1.split(",");
		List<String> borderBds2 = new ArrayList<String>();
		String borderBds=null;
		System.out.println("长度为"+bds.length);
		for(int i=0;i<bds.length;i++){			
			System.out.print("     "+bds[i]);
		}
		System.out.println();
		for(int i=0;i<bds.length;i++){
			if(getNumber(bds[i])){	///////			
				System.out.println("-----------------------");
				String borderBds1 = null;
				String domain = bds[i];
				List<String> border = new ArrayList<String>();
				String cs = null;
				if (domain.contains("<=")) {
					String[] strs = domain.split("<=");				
					System.out.println("需处理（有<=）："+domain);
					if (strs.length == 3) {	
						System.out.println("例如1<=x<=7");
						border.add(strs[0]);
						border.add(strs[2]);
						cs = strs[1];
					} else{ 
						if (strs[0].contains("<")||strs[1].contains("<")) {
							System.out.println("     例如1<x<=7或1<=x<7");
							if (strs[1].contains("<")) {  //1<=x<7
								System.out.println("     例如1<=x<7");
								border.add(strs[0]);						
								//System.out.println("border"+border);
								int integer = Integer.parseInt(strs[1].split("<")[1]);
								if(integer-0.1>Integer.parseInt(strs[0])){
									border.add(Integer.parseInt(strs[1].split("<")[1]) - 0.1
											+ "");
								}
								cs = strs[1].split("<")[0];
							} else {
								if (strs[0].contains("<")) {
									System.out.println("     例如1<x<=7");
									border.add(strs[1]);
									int integer = Integer.parseInt(strs[0].split("<")[0]);
									if(integer+0.1<Integer.parseInt(strs[1])){
									border.add(Integer.parseInt(strs[0].split("<")[0]) + 0.1
											+ "");
									}
									cs = strs[0].split("<")[1];
								}						
							}
						} else {
							System.out.println("     例如1<=x或x<=7");
							int s1=domain.substring(0,1).toCharArray()[0];
							if(!((s1>=48&&s1<=57)||s1==45)){//第一个为参数
								System.out.println("     例如x<=7");
								border.add(strs[1]);
								cs = strs[0];
							}else{
								System.out.println("     例如1<=x");
								border.add(strs[0]);
								cs = strs[1];
							}					
						}
					}
				} else{ 
					if (domain.contains(">=")) {
						System.out.println("需处理（有>=）："+domain);
						String[] strs = domain.split(">=");
						if (strs.length == 3) {	
							System.out.println("例如7>=x>=1");
							border.add(strs[0]);
							border.add(strs[2]);
							cs = strs[1];
						} else{ 
							if (strs[0].contains(">")||strs[1].contains(">")) {
								System.out.println("     例如7>=x>1或7>x>=1");
								if (strs[1].contains(">")) {  //1<=x<7
									System.out.println("     例如7>=x>1");
									border.add(strs[0]);						
									//System.out.println("border"+border);
									int integer = Integer.parseInt(strs[1].split(">")[1]);
									if(integer+0.1<Integer.parseInt(strs[0])){
										border.add(Integer.parseInt(strs[1].split(">")[1]) + 0.1
												+ "");
									}
									cs = strs[1].split(">")[0];
								} else {
									if (strs[0].contains(">")) {
										System.out.println("     例如7>x>=1");
										border.add(strs[1]);
										int integer = Integer.parseInt(strs[0].split(">")[0]);
										if(integer-0.1>Integer.parseInt(strs[1])){
										border.add(Integer.parseInt(strs[0].split(">")[0]) - 0.1
												+ "");
										}
										cs = strs[0].split(">")[1];
									}						
								}
							}else{
								System.out.println("     例如x>=1或7>=x");
								int s1=domain.substring(0,1).toCharArray()[0];
								if(!((s1>=48&&s1<=57)||s1==45)){//第一个为参数
									System.out.println("     例如x>=1");
									border.add(domain.split(">=")[1]);
									cs = domain.split(">=")[0];
								}else{
									System.out.println("     例如7>=x");
									border.add(domain.split(">=")[0]);
									cs = domain.split(">=")[1];
								}	
							}
						}		
					} else{ 					
						if (domain.contains("<")) { //不包含<=也不包含>=
							System.out.println("需处理（有<）："+domain);
							String[] strs = domain.split("<");
							System.out.println("     例如1<x<7或x<7或1<x");
							if (strs.length == 3) {
								System.out.println("     例如1<x<7");
								if(Integer.parseInt(strs[0])+0.1<Integer.parseInt(strs[2])-0.1){
								border.add(Integer.parseInt(strs[0]) + 0.1 + "");					
								border.add(Integer.parseInt(strs[2]) - 0.1 + "");
								}
								cs = strs[1];
							} else {
								int s1=domain.substring(0,1).toCharArray()[0];
								if(!((s1>=48&&s1<=57)||s1==45)){//第一个为参数
									System.out.println("     例如x<7");
									border.add(Integer.parseInt(strs[1]) - 0.1 + "");
									cs = strs[0];
								}else{
									System.out.println("     例如1<x");
									border.add(Integer.parseInt(strs[0]) + 0.1 + "");
									cs = strs[1]; 
								}					
							}
						} else{ 
							if (domain.contains(">")) { //不包含<=也不包含>=也不包含<
								System.out.println("需处理（有>）："+domain);
								System.out.println("     例如7>x>1或x>1或7>x");
								String[] strs = domain.split(">");
								if (strs.length == 3) {
									System.out.println("     例如7>x>1");
									if(Integer.parseInt(strs[0])-0.1>Integer.parseInt(strs[2])+0.1){
									border.add(Integer.parseInt(strs[0]) - 0.1 + "");					
									border.add(Integer.parseInt(strs[2]) + 0.1 + "");
									}
									cs = strs[1];
								} else {
									int s1=domain.substring(0,1).toCharArray()[0];
									if(!((s1>=48&&s1<=57)||s1==45)){//第一个为参数
										System.out.println("     例如x>1");
										border.add(Integer.parseInt(strs[1]) + 0.1 + "");
										cs = strs[0]; 
									}else{
										System.out.println("     例如7>x");
										border.add(Integer.parseInt(strs[0]) - 0.1 + "");
										cs = strs[1];  
									}
								}
							}
						}
					}
				}
				if(cs!=null&&border.size()>0){
					for(int j=0;j<border.size();j++){
						if(j==0){
							borderBds1 = cs+"=="+border.get(0);
						}else{
							borderBds1 = borderBds1+"||"+cs+"=="+border.get(j);
						}				
					}
					System.out.println("处理结果："+borderBds1);
				}
				borderBds2.add(borderBds1);
			
			}
		}
/*		System.out.println("----------------------------");
		System.out.println("----------------------------");
		for(int n=0;n<borderBds2.size();n++){
			System.out.println("输出结果："+borderBds2.get(n));
		}
		System.out.println("----------------------------");
		System.out.println("----------------------------");*/
		for(int n=0;n<borderBds2.size();n++){
			if(borderBds2.get(n)!=null){
				if(n==0){
					borderBds = borderBds2.get(0);
				}else{
					if(null==borderBds){
						borderBds = borderBds2.get(n);							
					}else{
						borderBds = borderBds+"||"+borderBds2.get(n);
					}
					
				}
			}

		}
		System.out.println("小数最终边界："+borderBds);
		return borderBds;

	}
/**
 * 判断每个不等式里参数是否只有一个
 * @param domain1
 * @return
 */
	public static boolean getNumber(String bds) {
		if(bds.contains("!")){
			return false;
		}else{
			int number=0;
			String[] css = bds.trim().split("[-+<>=]=?");
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
		}		
	}
}


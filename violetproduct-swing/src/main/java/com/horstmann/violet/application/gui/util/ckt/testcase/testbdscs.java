package com.horstmann.violet.application.gui.util.ckt.testcase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class testbdscs {

	public static void main(String[] args) {
//		List<String> list=new ArrayList<String>();//存放整数不等式
//		List<String> list1=new ArrayList<String>();//存放小数不等式
//		List<String> list2=new ArrayList<String>();//存放=0小数不等式
//		List<String> list3=new ArrayList<String>();//存放=0小数不等式的参数
//		Map<String,String> map = new HashMap<String,String>();
//		Map<String,String> map1 = new HashMap<String,String>();
//		Map<String,String> map3 = new HashMap<String,String>();
//		String ttt=null;
//		String ttt1=null;
//		String ttt2=null;
//		//String ttt3[]=null;
//		List<String> result=new ArrayList<String>();//存放最终实例化结果
//		List<String> tt3=new ArrayList<String>();//存放除了=0的小数不等式外的实例化结果


		List<String> con = new ArrayList<String>();
		/*String con1 = "cycle=2.5ms--ap.motor_test==0#ap.motor_test:uint8_t--_flags.armed>2#_flags.armed:uint8_t";
		String con2 = "cycle=2.5ms--ap.motor_test==0#ap.motor_test:uint8_t--_flags.armed>2#_flags.armed:uint8_t--sssss_1=0#sssss_1:float--!(sssss_2<sssss_3,sssss_4<88)#sssss_2:uint8_t,sssss_3:uint8_t,sssss_4:uint8_t";
		String con3 ="cycle=2.5ms--i<_num_tasks#i:uint8_t,_num_tasks:uint8_t--dt>=interval_ticks#dt:uint16_t,interval_ticks:uint16_t--dt>=interval_ticks_product_two && _debug>1#dt:uint16_t,interval_ticks_product_two:uint16_t,_debug:int8_t--!(time_taken>_task_time_allowed && _debug>2)#time_taken:uint32_t,_task_time_allowed:uint32_t,_debug:int8_t";
		String con4 = "cycle=2.5ms--i<_num_tasks#i:uint8_t,_num_tasks:uint8_t--dt>=interval_ticks#dt:uint16_t,interval_ticks:uint16_t--_task_time_allowed<=time_available#_task_time_allowed:uint32_t,time_available:uint32_t--i==0#i:uint8_t";
		String con5 = "cycle=2.5ms--i<_num_tasks#i:uint8_t,_num_tasks:uint8_t--dt>=interval_ticks#dt:uint16_t,interval_ticks:uint16_t--_task_time_allowed<=time_available#_task_time_allowed:uint32_t,time_available:uint32_t--channel_throttle$control_in<=0#channel_throttle$control_in:int16_t--tmp>4000#tmp:int16_t--arming_counter==20&&is_armed==false#arming_counter:int16_t,is_armed:bool";
		String con6 = "cycle=2.5ms--i<_num_tasks#i:uint8_t,_num_tasks:uint8_t--dt>=interval_ticks#dt:uint16_t,interval_ticks:uint16_t--_task_time_allowed<=time_available#_task_time_allowed:uint32_t,time_available:uint32_t--_hil_mode==false#_hil_mode:bool--i<_num_drivers#i:uint8_t,_num_drivers:uint8_t";
		String con7 = "i$t#i$t:uint8_t";*/
		/*con.add(con1);
		con.add(con2);
		con.add(con3);
		con.add(con4);
		con.add(con5);
		con.add(con6);
		con.add(con7);*/
		//String condition = "has_new_input==True#has_new_input:bool";
		//String condition = "cycle=2.5ms--contr--ol_mode==0#control_mode:int8_t#0<=control_mode<=14--motor_state==False || ap.throttle_zero==True#motor_state:bool,ap.throttle_zero:bool";
		//String condition = "cycle=2.5ms--control_mode==0#control_mode:int8_t#0<=control_mode<=14--motor_state==False || ap.throttle_zero==True#motor_state:bool,ap.throttle_zero:bool";
		//String condition = "cycle=2.5ms--control_mode==4#control_mode:int8_t#0<=control_mode<=14--guided_mode==2#guided_mode:uint8_t#guided_mode=0,guided_mode=1,guided_mode=2,guided_mode=3,guided_mode=4--ap.auto_armed != 0 && get_interlock==true && ap.land_complete!=1#ap.auto_armed:uint8_t,get_interlock:bool,ap.land_complete:uint8_t--tnow - vel_update_time_ms > 3000 && is_zero==false#tnow:uint32_t,vel_update_time_ms:uint32_t,is_zero:bool";
		//String condition = "cycle=2.5ms--i<_num_tasks#i:uint8_t,_num_tasks:uint8_t--dt>=interval_ticks#dt:uint16_t,interval_ticks:uint16_t--_task_time_allowed<=time_available#_task_time_allowed:uint32_t,time_available:uint32_t--i==18#i:uint8_t--0<=i<=16#i:uint8_t--gcs[i].initialised==true#gcs[i].initialised:bool";
		//String condition = "_accel.x,_accel.y,_accel.z,_delta_velocity.x,_delta_velocity.y,_delta_velocity.z,_delta_velocity_dt,_delta_velocity_valid,_gyro.x,_gyro.y,_gyro.z,_delta_angle.x,_delta_angle.y,_delta_angle.z,_delta_angle_valid#_accel.x:float,_accel.y:float,_accel.z:float,_delta_velocity.x:float,_delta_velocity.y:float,_delta_velocity.z:float,_delta_velocity_dt:float,_delta_velocity_valid:bool,_gyro.x:float,_gyro.y:float,_gyro.z:float,_delta_angle.x:float,_delta_angle.y:float,_delta_angle.z:float,_delta_angle_valid:bool";
		String condition = "pos_vector.x,pos_vector.y,pos_vector.z,vel_vector.x,vel_vector.y,vel_vector.z#pos_vector.x:float,pos_vector.y:float,pos_vector.z:float,vel_vector.x:float,vel_vector.y:float,vel_vector.z:float";
		int ee=0;
		//for(String condition : con){
			List<String> list=new ArrayList<String>();//存放整数不等式
			List<String> list1=new ArrayList<String>();//存放小数不等式
			List<String> list2=new ArrayList<String>();//存放=0小数不等式
			List<String> list3=new ArrayList<String>();//存放=0小数不等式的参数
			Map<String,String> map = new HashMap<String,String>();
			Map<String,String> map1 = new HashMap<String,String>();
			Map<String,String> map3 = new HashMap<String,String>();
			String ttt=null;
			String ttt1=null;
			String ttt2=null;
			//String ttt3[]=null;
			List<String> result=new ArrayList<String>();//存放最终实例化结果
			List<String> tt3=new ArrayList<String>();//存放除了=0的小数不等式外的实例化结果

			
			System.out.println("condition"+ ee +"----->"+condition);
			System.out.println("keySet集合3："+GetMap.get_condMap(condition));
			if(GetMap.get_condMap(condition)==null){
				result.add(null);
				//System.out.println("keySet集合3："+GetMap.get_condMap(condition));
			}else{

				if(!(GetMap.get_condMap(condition)==null)){
					map1 = GetMap.get_condMap(condition);//必须有，要不结果错误
					//System.out.println("==================================");
					Set<String> set =map1.keySet();

					String cs1 = AddBdsType.getcs(map1);
					String cs2 = AddBdsType.getDoubleCs(map1);
					String cs3 = AddBdsType.getBoolCs(map1);			
					String s1 = AddBdsType.add_bds(map1);
					String s20 = AddBdsType.add_doublebds(map1);
					String bds2=null;
					String bds00=null;

					//==0的不等式即为解 ==换为=
					//String bds0=GetBds.get_bds_0(condition.toString());
					//数字型不等式和参数
					String bds1=GetBds.get_bds(condition.toString());	
					System.out.println("bds1:"+bds1);


					/////////////////输出
					System.out.println("=================================");
					System.out.println("整数------>不等式，即："+bds1);  //condition上数字不等式				
					System.out.println("整数------>参数，即："+cs1);
					System.out.println("小数=0---->不等式，即："+bds00);
					System.out.println("小数------>不等式，即："+bds2);
					System.out.println("小数------>参数前，即："+cs2);
					System.out.println("add------>整数不等式为："+s1);
					System.out.println("add------>小数不等式前为："+s20);
					System.out.println("=================================");

					///////////////////////////////////
					if(cs2!=null){  //cs2是小数------>参数
						if(bds1!=null){
							if(bds1.contains(",")){
								String[] xbds = bds1.split(",");
								if(cs2.contains(",")){//多个不等式多个参数
									String[] xcs = cs2.split(",");
									for(String x1:xbds){
										int m=0;
										for(String x2:xcs){													
											if(x1.contains(x2)){
												//list1.add(x1);
												m=1;
											}
										}
										////判断是不是小数或者整数不等式或者==0的情况
										if(m==1&&!(x1.contains("==0"))){
											list1.add(x1);//list1里面存放除了==0的小数不等式
										}
										else{
											if(m==1&&(x1.contains("==0"))){
												list2.add(x1);//list2里面存放==0的小数不等式
											}
											if(m==0){
												list.add(x1);//list里面存放整数不等式
											}
										}

									}//for(String x1:xbds) 
								}//if(cs2.contains(","))
								else{
									//!cs2.contains(",")
									////多个不等式一个参数
									for(String x1:xbds){
										int m=0;
										if(x1.contains(cs2)){
											m=1;
										}
										////判断是不是小数或者整数不等式或者==0的情况
										if(m==1&&!(x1.contains("==0"))){
											list1.add(x1);//list1里面存放除了==0的小数不等式
										}
										else{
											if(m==1&&(x1.contains("==0"))){
												list2.add(x1);//list2里面存放==0的小数不等式
											}
											if(m==0){
												list.add(x1);//list里面存放除整数不等式
											}
										}
									}												
								}

							}//if(bds1.contains(","))
							else{
								if(!bds1.contains(",")){
									if(cs2.contains(",")){//一个不等式多个参数
										String[] xcs = cs2.split(",");
										int m=0;
										for(String x2:xcs){													
											if(bds1.contains(x2)){
												//list1.add(x1);
												m=1;
											}
										}
										////判断是不是小数或者整数不等式
										if(m==1&&!(bds1.contains("==0"))){
											list1.add(bds1);//list1里面存放除了==0的小数不等式
										}
										else{
											if(m==1&&(bds1.contains("==0"))){
												list2.add(bds1);//list2里面存放==0的小数不等式													
											}
											if(m==0){
												list.add(bds1);//list里面存放除了==0的整数不等式
											}
										}
									}//if(cs2.contains(","))
									else{
										//!cs2.contains(",")
										//一个不等式一个参数
										int m=0;
										if(bds1.contains(cs2)){
											m=1;
										}
										////判断是不是小数或者整数不等式或者==0的情况
										if(m==1&&!(bds1.contains("==0"))){
											list1.add(bds1);//list1里面存放除了==0的小数不等式
										}
										else{
											if(m==1&&(bds1.contains("==0"))){
												list2.add(bds1);//list2里面存放==0的小数不等式													
											}
											if(m==0){
												list.add(bds1);//list里面存放除了==0的整数不等式
											}
										}
									}
								}
							}

							//整数不等式
							String cs = null;
							if(list.size()>1){
								cs=list.get(0);
								for(int mm=1;mm<list.size();mm++){
									String c1=list.get(mm);
									cs=cs+","+c1;
								}
							}
							if(list.size()==1){
								cs=list.get(0);
							}
							bds1=cs;
							//System.out.println("整数不等式为----->"+cs);

							//除=0的小数不等式
							String css = null;
							if(list1.size()>1){
								css=list1.get(0);
								for(int mm=1;mm<list1.size();mm++){
									String c1=list1.get(mm);
									css=css+","+c1;
								}
							}
							if(list1.size()==1){
								css=list1.get(0);
							}
							bds2=css;
							//								System.out.println("小数不等式为----->"+css);

							//=0的小数不等式
							String csss = null;
							if(list2.size()>1){
								csss=list2.get(0);
								list3.add(list2.get(0).replace("==0", ""));
								for(int mm=1;mm<list2.size();mm++){
									list3.add(list2.get(mm).replace("==0", ""));
									String c1=list2.get(mm);
									csss=csss+","+c1;
								}
							}
							if(list2.size()==1){
								csss=list2.get(0);
								list3.add(list2.get(0).replace("==0", ""));
							}	
							bds00=csss.replace("==", "=");

						}//if(bds1!=null)
						else{
							if(bds1==null){
								//System.out.println("整数不等式为----->"+null);
								//System.out.println("小数不等式为----->"+null);
							}										
						}


					}//if(cs2!=null)
					else{
						//没有小数参数
						//System.out.println("整数不等式为----->"+bds1);
						//System.out.println("小数不等式为----->"+null);

					}




					//////////////////////







					for(int i=0;i<list3.size();i++){
						//处理小数参数
						if(cs2.contains(list3.get(i)+",")){
							cs2 = cs2.replace(list3.get(i)+",", "");
						}else{
							if(cs2.contains(list3.get(i))){
								cs2 = cs2.replace(list3.get(i), "");
							}						
						}
						//处理增加的小数不等式
						map1.remove(list3.get(i));					
					}
					String s2 = AddBdsType.add_doublebds(map1);


					/////////////////输出
					System.out.println("=================================");
					System.out.println("整数------>不等式，即："+bds1);  //condition上数字不等式			
					System.out.println("整数------>参数，即："+cs1);
					System.out.println("小数=0---->不等式，即："+bds00);
					System.out.println("小数------>不等式，即："+bds2);
					System.out.println("小数------>参数后，即："+cs2);
					System.out.println("add------>整数不等式为："+s1);
					System.out.println("add------>小数不等式后为："+s2);
					System.out.println("=================================");



					//布尔型不等式和参数
					String boolbds=GetBds.get_boolbds(condition.toString());
					if(cs3!=null&&boolbds==null){
						boolbds = AddBdsType.add_boolbds(cs3);
					}
					System.out.println("布尔型------>不等式，即："+boolbds);//condition上布尔不等式
					System.out.println("小数=0---->不等式，即："+bds00);
					//			System.out.println("布尔型------>参数，即："+cs3);



					/////////////////////////////////////////
					//调用mma软件求解方程组
					String[] results = null;
					String[] results1 = null;
					String[] ttt3 = null;
					if(((bds1==null)&&(cs1==null))&&(s2==null)){
						//System.out.println("        ===>  condition上没有约束即为：null");
						//input.setText("null");
					}
					if((bds1!=null)&&(cs1!=null)){
						String bbb = bds1+","+s1;
						//System.out.println("        ===>  condition上整数型数值不等式："+bbb);
						//										System.out.println("        ===>  condition上整数型数值参数："+cs1);
						System.out.println("整数不等式:"+bbb);
						System.out.println("整数数参数："+cs1);


						int resultNum = getMathNum(bbb);
						String solution1 = Mathematica.getSolution2(bbb, cs1, resultNum);
						ttt=solution1.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
						//ttt=bbb.toString();
						//
						System.out.println("整数解："+solution1);
						results = solution1.substring(2, solution1.length() - 2).split("\\}, \\{");
						for(int i=0;i<results.length;i++){
							System.out.println("results11----"+results[i]);
						}
						System.out.println("condition整数型约束解11为："+solution1);
					}
					else{
						if(s1!=null){
							//											System.out.println("        ===>  condition上整数型数值不等式："+s1);
							//											System.out.println("        ===>  condition上整数型数值参数："+cs1);
							System.out.println("整数不等式:"+s1);
							System.out.println("整数数参数："+cs1);
							int resultNum = getMathNum(s1);
							String solution1 = Mathematica.getSolution2(s1, cs1, resultNum);
							ttt=solution1.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
							//ttt=s1.toString();
							//							
							results = solution1.trim().substring(2, solution1.length() - 2).split("\\}, \\{");
							for(int i=0;i<results.length;i++){
								System.out.println("results22----"+results[i]);
							}
							System.out.println("condition上整数型约束解22为："+solution1);
						}
					}
					if((bds2!=null)&&(s2!=null)){
						//System.out.println("condition上小数型数值不等式："+s2);
						//System.out.println("condition上小数型数值参数："+cs2);
						String bb = bds2+","+s2;
						System.out.println("小数不等式:"+bb);
						System.out.println("小数参数0："+cs2);
						int resultNum = getMathNum(bb);
						String solution2 = Mathematica.getSolution4(bb, cs2, resultNum);
						ttt1=solution2.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
						//ttt1=s2.toString();
						//						
						results1 = solution2.substring(2, solution2.length() - 2).split("\\}, \\{");
						for(int i=0;i<results1.length;i++){
							System.out.println("results1 33----"+results[i]);
						}
						System.out.println("condition上小数型约束解33为："+solution2);
					}else{
						if((s2!=null)){
							System.out.println("小数不等式:"+s2);
							System.out.println("小数参数===："+cs2);
							int resultNum = getMathNum(s2);
							String solution2 = Mathematica.getSolution4(s2, cs2, resultNum);
							ttt1=solution2.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
							//							
							results1 = solution2.substring(2, solution2.length() - 2).split("\\}, \\{");
							System.out.println(results1.length);
							for(int i=0;i<results1.length;i++){
								System.out.println("results1 44----"+results1[i]);
							}
							System.out.println("condition上小数型约束解44为："+solution2);
						}
					}

					//System.out.println("condition布尔型值解为："+boolbds);//condition上布尔不等式	
					if(boolbds!=null){
						System.out.println("===>condition上布尔型的不等式："+boolbds);
						if((results!=null)&&(results1!=null)){
							//Object results;
							//for(int i=0;;i++){
							for(String tttt:results){
								for(String tttt1:results1){
									//ttt3[i]
									String t3=tttt+","+tttt1+","+boolbds;
									tt3.add(t3);
								}								
							}
							//}

							//ttt3=ttt+","+ttt1+","+boolbds;
						}
						if((results!=null)&&(results1==null)){
							//for(int i=0;;i++){
							for(String tttt:results){

								String t3=tttt+","+boolbds;
								tt3.add(t3);
							}
							//}
							//ttt3=ttt+","+boolbds;
						}
						if((results==null)&&(results1!=null)){
							//for(int i=0;;i++){							
							for(String tttt1:results1){

								String t3=tttt1+","+boolbds;
								tt3.add(t3);
							}															
							//}
							//ttt3=ttt1+","+boolbds;
						}
						//System.out.println("condition上解为："+ttt3);
						if((results==null)&&(results1==null)){
							//for(int i=0;;i++){						
								String t3=boolbds;
								tt3.add(t3);																						
							//}
							//ttt3=ttt1+","+boolbds;
						}
					}
					else{
						//if(results.length>=0)
						if((results!=null)&&(results1!=null)){
							//for(int i=0;;i++){
							for(String tttt:results){
								for(String tttt1:results1){									
									String t3=tttt+","+tttt1;
									tt3.add(t3);
								}								
							}
							//}
							//ttt3=ttt+","+ttt1;
						}
						if((results!=null)&&(results1==null)){
							//for(int i=0;;i++){
							for(String tttt:results){															
								String t3=tttt;
								tt3.add(t3);
							}
							//}
							//ttt3=ttt;
						}
						if((results==null)&&(results1!=null)){
							//for(int i=0;;i++){							
							for(String tttt1:results1){								
								String t3=tttt1;
								tt3.add(t3);
							}															
							//}
							//ttt3=ttt1;
						}


					}
					for(int i=0;i<tt3.size();i++){
						System.out.println("list中解:"+tt3.get(i));
					}
					if(bds00!=null){
						System.out.println("bds00:"+bds00);
						for(int i=0;i<tt3.size();i++){	
							String t = tt3.get(i)+","+bds00;
							t = t.replace(" ", "").replaceAll("->", "=").replace("(", "").replace(")", "").replace("$", "->");
							result.add(t);
							//tt3.remove(tt3.get(i));
						}
					}else{
						for(int i=0;i<tt3.size();i++){
							String t = tt3.get(i).replace(" ", "").replaceAll("->", "=").replace("(", "").replace(")", "").replace("$", "->");
							result.add(t);						
						}
					}
					/*for(int i=0;i<tt3.size();i++){
						System.out.println("解"+i+"为:"+tt3.get(i));
					}*/

					for(int i=0;i<result.size();i++){
						System.out.println("testcase"+i+"为:"+result.get(i));
					}

					//////////////////////////////////////

					System.out.println("---------------");
					System.out.println("result--->"+result);
					System.out.println("---------------");
				}

			}
			ee++;
		}
	//}



	public static List<String> getResult(String condition){

//		List<String> list=new ArrayList<String>();//存放整数不等式
//		List<String> list1=new ArrayList<String>();//存放小数不等式
//		List<String> list2=new ArrayList<String>();//存放=0小数不等式
//		List<String> list3=new ArrayList<String>();//存放=0小数不等式的参数
//		Map<String,String> map = new HashMap<String,String>();
//		Map<String,String> map1 = new HashMap<String,String>();
//		Map<String,String> map3 = new HashMap<String,String>();
//		String ttt=null;
//		String ttt1=null;
//		String ttt2=null;
//		//String ttt3[]=null;
//		List<String> result=new ArrayList<String>();//存放最终实例化结果
//		List<String> tt3=new ArrayList<String>();//存放除了=0的小数不等式外的实例化结果


		List<String> con = new ArrayList<String>();
		/*String con1 = "cycle=2.5ms--ap.motor_test==0#ap.motor_test:uint8_t--_flags.armed>2#_flags.armed:uint8_t";
		String con2 = "cycle=2.5ms--ap.motor_test==0#ap.motor_test:uint8_t--_flags.armed>2#_flags.armed:uint8_t--sssss_1=0#sssss_1:float--!(sssss_2<sssss_3,sssss_4<88)#sssss_2:uint8_t,sssss_3:uint8_t,sssss_4:uint8_t";
		String con3 ="cycle=2.5ms--i<_num_tasks#i:uint8_t,_num_tasks:uint8_t--dt>=interval_ticks#dt:uint16_t,interval_ticks:uint16_t--dt>=interval_ticks_product_two && _debug>1#dt:uint16_t,interval_ticks_product_two:uint16_t,_debug:int8_t--!(time_taken>_task_time_allowed && _debug>2)#time_taken:uint32_t,_task_time_allowed:uint32_t,_debug:int8_t";
		String con4 = "cycle=2.5ms--i<_num_tasks#i:uint8_t,_num_tasks:uint8_t--dt>=interval_ticks#dt:uint16_t,interval_ticks:uint16_t--_task_time_allowed<=time_available#_task_time_allowed:uint32_t,time_available:uint32_t--i==0#i:uint8_t";
		String con5 = "cycle=2.5ms--i<_num_tasks#i:uint8_t,_num_tasks:uint8_t--dt>=interval_ticks#dt:uint16_t,interval_ticks:uint16_t--_task_time_allowed<=time_available#_task_time_allowed:uint32_t,time_available:uint32_t--channel_throttle$control_in<=0#channel_throttle$control_in:int16_t--tmp>4000#tmp:int16_t--arming_counter==20&&is_armed==false#arming_counter:int16_t,is_armed:bool";
		String con6 = "cycle=2.5ms--i<_num_tasks#i:uint8_t,_num_tasks:uint8_t--dt>=interval_ticks#dt:uint16_t,interval_ticks:uint16_t--_task_time_allowed<=time_available#_task_time_allowed:uint32_t,time_available:uint32_t--_hil_mode==false#_hil_mode:bool--i<_num_drivers#i:uint8_t,_num_drivers:uint8_t";
		String con7 = "i$t#i$t:uint8_t";*/
		/*con.add(con1);
		con.add(con2);
		con.add(con3);
		con.add(con4);
		con.add(con5);
		con.add(con6);
		con.add(con7);*/
		//String condition = "has_new_input==True#has_new_input:bool";
		//String condition = "cycle=2.5ms--control_mode==4#control_mode:int8_t#0<=control_mode<=14--guided_mode==1#guided_mode:uint8_t#guided_mode=0,guided_mode=1,guided_mode=2,guided_mode=3,guided_mode=4--ap.auto_armed==0 || get_interlock==false || ap.land_complete==1#ap.auto_armed:uint8_t, get_interlock:bool,ap.land_complete:uint8_t";
		//String condition = "cycle=2.5ms--control_mode==0#control_mode:int8_t#0<=control_mode<=14--motor_state==False || ap.throttle_zero==True#motor_state:bool,ap.throttle_zero:bool";
		int ee=0;
		//for(String condition : con){
			List<String> list=new ArrayList<String>();//存放整数不等式
			List<String> list1=new ArrayList<String>();//存放小数不等式
			List<String> list2=new ArrayList<String>();//存放=0小数不等式
			List<String> list3=new ArrayList<String>();//存放=0小数不等式的参数
			Map<String,String> map = new HashMap<String,String>();
			Map<String,String> map1 = new HashMap<String,String>();
			Map<String,String> map3 = new HashMap<String,String>();
			String ttt=null;
			String ttt1=null;
			String ttt2=null;
			//String ttt3[]=null;
			List<String> result=new ArrayList<String>();//存放最终实例化结果
			List<String> tt3=new ArrayList<String>();//存放除了=0的小数不等式外的实例化结果

			
			System.out.println("condition"+ ee +"----->"+condition);
			//System.out.println("keySet集合3："+GetMap.get_condMap(condition));
			if(GetMap.get_condMap(condition)==null){
				result.add(null);
				//System.out.println("keySet集合3："+GetMap.get_condMap(condition));
			}else{

				if(!(GetMap.get_condMap(condition)==null)){
					map1 = GetMap.get_condMap(condition);//必须有，要不结果错误
					//System.out.println("==================================");
					Set<String> set =map1.keySet();

					String cs1 = AddBdsType.getcs(map1);
					String cs2 = AddBdsType.getDoubleCs(map1);
					String cs3 = AddBdsType.getBoolCs(map1);			
					String s1 = AddBdsType.add_bds(map1);
					String s20 = AddBdsType.add_doublebds(map1);
					String bds2=null;
					String bds00=null;

					//==0的不等式即为解 ==换为=
					//String bds0=GetBds.get_bds_0(condition.toString());
					//数字型不等式和参数
					String bds1=GetBds.get_bds(condition.toString());	
					System.out.println("bds1:"+bds1);


					/////////////////输出
					System.out.println("=================================");
					System.out.println("整数------>不等式，即："+bds1);  //condition上数字不等式				
					System.out.println("整数------>参数，即："+cs1);
					System.out.println("小数=0---->不等式，即："+bds00);
					System.out.println("小数------>不等式，即："+bds2);
					System.out.println("小数------>参数前，即："+cs2);
					System.out.println("add------>整数不等式为："+s1);
					System.out.println("add------>小数不等式前为："+s20);
					System.out.println("=================================");

					///////////////////////////////////
					if(cs2!=null){  //cs2是小数------>参数
						if(bds1!=null){
							if(bds1.contains(",")){
								String[] xbds = bds1.split(",");
								if(cs2.contains(",")){//多个不等式多个参数
									String[] xcs = cs2.split(",");
									for(String x1:xbds){
										int m=0;
										for(String x2:xcs){													
											if(x1.contains(x2)){
												//list1.add(x1);
												m=1;
											}
										}
										////判断是不是小数或者整数不等式或者==0的情况
										if(m==1&&!(x1.contains("==0"))){
											list1.add(x1);//list1里面存放除了==0的小数不等式
										}
										else{
											if(m==1&&(x1.contains("==0"))){
												list2.add(x1);//list2里面存放==0的小数不等式
											}
											if(m==0){
												list.add(x1);//list里面存放整数不等式
											}
										}

									}//for(String x1:xbds) 
								}//if(cs2.contains(","))
								else{
									//!cs2.contains(",")
									////多个不等式一个参数
									for(String x1:xbds){
										int m=0;
										if(x1.contains(cs2)){
											m=1;
										}
										////判断是不是小数或者整数不等式或者==0的情况
										if(m==1&&!(x1.contains("==0"))){
											list1.add(x1);//list1里面存放除了==0的小数不等式
										}
										else{
											if(m==1&&(x1.contains("==0"))){
												list2.add(x1);//list2里面存放==0的小数不等式
											}
											if(m==0){
												list.add(x1);//list里面存放除整数不等式
											}
										}
									}												
								}

							}//if(bds1.contains(","))
							else{
								if(!bds1.contains(",")){
									if(cs2.contains(",")){//一个不等式多个参数
										String[] xcs = cs2.split(",");
										int m=0;
										for(String x2:xcs){													
											if(bds1.contains(x2)){
												//list1.add(x1);
												m=1;
											}
										}
										////判断是不是小数或者整数不等式
										if(m==1&&!(bds1.contains("==0"))){
											list1.add(bds1);//list1里面存放除了==0的小数不等式
										}
										else{
											if(m==1&&(bds1.contains("==0"))){
												list2.add(bds1);//list2里面存放==0的小数不等式													
											}
											if(m==0){
												list.add(bds1);//list里面存放除了==0的整数不等式
											}
										}
									}//if(cs2.contains(","))
									else{
										//!cs2.contains(",")
										//一个不等式一个参数
										int m=0;
										if(bds1.contains(cs2)){
											m=1;
										}
										////判断是不是小数或者整数不等式或者==0的情况
										if(m==1&&!(bds1.contains("==0"))){
											list1.add(bds1);//list1里面存放除了==0的小数不等式
										}
										else{
											if(m==1&&(bds1.contains("==0"))){
												list2.add(bds1);//list2里面存放==0的小数不等式													
											}
											if(m==0){
												list.add(bds1);//list里面存放除了==0的整数不等式
											}
										}
									}
								}
							}

							//整数不等式
							String cs = null;
							if(list.size()>1){
								cs=list.get(0);
								for(int mm=1;mm<list.size();mm++){
									String c1=list.get(mm);
									cs=cs+","+c1;
								}
							}
							if(list.size()==1){
								cs=list.get(0);
							}
							bds1=cs;
							//System.out.println("整数不等式为----->"+cs);

							//除=0的小数不等式
							String css = null;
							if(list1.size()>1){
								css=list1.get(0);
								for(int mm=1;mm<list1.size();mm++){
									String c1=list1.get(mm);
									css=css+","+c1;
								}
							}
							if(list1.size()==1){
								css=list1.get(0);
							}
							bds2=css;
							//								System.out.println("小数不等式为----->"+css);

							//=0的小数不等式
							String csss = null;
							if(list2.size()>1){
								csss=list2.get(0);
								list3.add(list2.get(0).replace("==0", ""));
								for(int mm=1;mm<list2.size();mm++){
									list3.add(list2.get(mm).replace("==0", ""));
									String c1=list2.get(mm);
									csss=csss+","+c1;
								}
							}
							if(list2.size()==1){
								csss=list2.get(0);
								list3.add(list2.get(0).replace("==0", ""));
							}	
							bds00=csss.replace("==", "=");

						}//if(bds1!=null)
						else{
							if(bds1==null){
								//System.out.println("整数不等式为----->"+null);
								//System.out.println("小数不等式为----->"+null);
							}										
						}


					}//if(cs2!=null)
					else{
						//没有小数参数
						//System.out.println("整数不等式为----->"+bds1);
						//System.out.println("小数不等式为----->"+null);

					}




					//////////////////////







					for(int i=0;i<list3.size();i++){
						//处理小数参数
						if(cs2.contains(list3.get(i)+",")){
							cs2 = cs2.replace(list3.get(i)+",", "");
						}else{
							if(cs2.contains(list3.get(i))){
								cs2 = cs2.replace(list3.get(i), "");
							}						
						}
						//处理增加的小数不等式
						map1.remove(list3.get(i));					
					}
					String s2 = AddBdsType.add_doublebds(map1);


					/////////////////输出
					System.out.println("=================================");
					System.out.println("整数------>不等式，即："+bds1);  //condition上数字不等式			
					System.out.println("整数------>参数，即："+cs1);
					System.out.println("小数=0---->不等式，即："+bds00);
					System.out.println("小数------>不等式，即："+bds2);
					System.out.println("小数------>参数后，即："+cs2);
					System.out.println("add------>整数不等式为："+s1);
					System.out.println("add------>小数不等式后为："+s2);
					System.out.println("=================================");



					//布尔型不等式和参数
					String boolbds=GetBds.get_boolbds(condition.toString());
					if(cs3!=null&&boolbds==null){
						boolbds = AddBdsType.add_boolbds(cs3);
					}
					System.out.println("布尔型------>不等式，即："+boolbds);//condition上布尔不等式
					System.out.println("小数=0---->不等式，即："+bds00);
					//			System.out.println("布尔型------>参数，即："+cs3);



					/////////////////////////////////////////
					//调用mma软件求解方程组
					String[] results = null;
					String[] results1 = null;
					String[] ttt3 = null;
					if(((bds1==null)&&(cs1==null))&&(s2==null)){
						//System.out.println("        ===>  condition上没有约束即为：null");
						//input.setText("null");
					}
					if((bds1!=null)&&(cs1!=null)){
						String bbb = bds1+","+s1;
						//System.out.println("        ===>  condition上整数型数值不等式："+bbb);
						//										System.out.println("        ===>  condition上整数型数值参数："+cs1);
						System.out.println("整数不等式:"+bbb);
						System.out.println("整数数参数："+cs1);


						int resultNum = getMathNum(bbb);
						String solution1 = Mathematica.getSolution2(bbb, cs1, resultNum);
						//ttt=solution1.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
						//ttt=bbb.toString();
						System.out.println(solution1);					
						results = solution1.substring(2, solution1.length() - 2).split("\\}, \\{");
						for(int i=0;i<results.length;i++){
							System.out.println("results11----"+results[i]);
						}
						System.out.println("condition整数型约束解11为："+solution1);
					}
					else{
						if(s1!=null){
							//											System.out.println("        ===>  condition上整数型数值不等式："+s1);
							//											System.out.println("        ===>  condition上整数型数值参数："+cs1);
							System.out.println("整数不等式:"+s1);
							System.out.println("整数数参数："+cs1);
							int resultNum = getMathNum(s1);
							String solution1 = Mathematica.getSolution2(s1, cs1, resultNum);
							ttt=solution1.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
							//ttt=s1.toString();
							//							
							results = solution1.trim().substring(2, solution1.length() - 2).split("\\}, \\{");
							for(int i=0;i<results.length;i++){
								System.out.println("results22----"+results[i]);
							}
							System.out.println("condition上整数型约束解22为："+solution1);
						}
					}
					if((bds2!=null)&&(s2!=null)){
						//System.out.println("condition上小数型数值不等式："+s2);
						//System.out.println("condition上小数型数值参数："+cs2);
						String bb = bds2+","+s2;
						System.out.println("小数不等式:"+bb);
						System.out.println("小数参数："+cs2);
						int resultNum = getMathNum(bb);
						String solution2 = Mathematica.getSolution4(bb, cs2,resultNum);
						ttt1=solution2.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
						//ttt1=s2.toString();
						//						
						results1 = solution2.substring(2, solution2.length() - 2).split("\\}, \\{");
						for(int i=0;i<results1.length;i++){
							System.out.println("results1 33----"+results[i]);
						}
						System.out.println("condition上小数型约束解33为："+solution2);
					}else{
						if((s2!=null)){
							System.out.println("小数不等式:"+s2);
							System.out.println("小数参数："+cs2);
							int resultNum = getMathNum(s2);
							String solution2 = Mathematica.getSolution4(s2, cs2, resultNum);
							ttt1=solution2.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
							//							
							results1 = solution2.substring(2, solution2.length() - 2).split("\\}, \\{");
							System.out.println(results1.length);
							for(int i=0;i<results1.length;i++){
								System.out.println("results1 44----"+results1[i]);
							}
							System.out.println("condition上小数型约束解44为："+solution2);
						}
					}

					//System.out.println("condition布尔型值解为："+boolbds);//condition上布尔不等式	
					if(boolbds!=null){
						System.out.println("===>condition上布尔型的不等式："+boolbds);
						if((results!=null)&&(results1!=null)){
							//Object results;
							//for(int i=0;;i++){
							for(String tttt:results){
								for(String tttt1:results1){
									//ttt3[i]
									String t3=tttt+","+tttt1+","+boolbds;
									tt3.add(t3);
								}								
							}
							//}

							//ttt3=ttt+","+ttt1+","+boolbds;
						}
						if((results!=null)&&(results1==null)){
							//for(int i=0;;i++){
							for(String tttt:results){

								String t3=tttt+","+boolbds;
								tt3.add(t3);
							}
							//}
							//ttt3=ttt+","+boolbds;
						}
						if((results==null)&&(results1!=null)){
							//for(int i=0;;i++){							
							for(String tttt1:results1){

								String t3=tttt1+","+boolbds;
								tt3.add(t3);
							}															
							//}
							//ttt3=ttt1+","+boolbds;
						}
						//System.out.println("condition上解为："+ttt3);
						if((results==null)&&(results1==null)){
							//for(int i=0;;i++){						
								String t3=boolbds;
								tt3.add(t3);																						
							//}
							//ttt3=ttt1+","+boolbds;
						}
					}
					else{
						//if(results.length>=0)
						if((results!=null)&&(results1!=null)){
							//for(int i=0;;i++){
							for(String tttt:results){
								for(String tttt1:results1){									
									String t3=tttt+","+tttt1;
									tt3.add(t3);
								}								
							}
							//}
							//ttt3=ttt+","+ttt1;
						}
						if((results!=null)&&(results1==null)){
							//for(int i=0;;i++){
							for(String tttt:results){															
								String t3=tttt;
								tt3.add(t3);
							}
							//}
							//ttt3=ttt;
						}
						if((results==null)&&(results1!=null)){
							//for(int i=0;;i++){							
							for(String tttt1:results1){								
								String t3=tttt1;
								tt3.add(t3);
							}															
							//}
							//ttt3=ttt1;
						}


					}
					for(int i=0;i<tt3.size();i++){
						System.out.println("list中解:"+tt3.get(i));
					}
					if(bds00!=null){
						System.out.println("bds00:"+bds00);
						for(int i=0;i<tt3.size();i++){	
							String t = tt3.get(i)+","+bds00;
							t = t.replace(" ", "").replaceAll("->", "=").replace("(", "").replace(")", "").replace("$", "->");
							result.add(t);
							//tt3.remove(tt3.get(i));
						}
					}else{
						for(int i=0;i<tt3.size();i++){
							String t = tt3.get(i).replace(" ", "").replaceAll("->", "=").replace("(", "").replace(")", "").replace("$", "->");
							result.add(t);						
						}
					}
					/*for(int i=0;i<tt3.size();i++){
						System.out.println("解"+i+"为:"+tt3.get(i));
					}*/

					for(int i=0;i<result.size();i++){
						System.out.println("testcase"+i+"为:"+result.get(i));
					}

					//////////////////////////////////////

					System.out.println("---------------");
					System.out.println("result--->"+result);
					System.out.println("---------------");
				}

			}
			ee++;
			return result;
		
	}
	public static int getMathNum(String bds) {
		String[] bNum = bds.split(",");
		int resultNum;
		if (bNum.length > 8) {
			resultNum = 1;
		} else {
			resultNum = 5;
		}
		return resultNum;
	}


}

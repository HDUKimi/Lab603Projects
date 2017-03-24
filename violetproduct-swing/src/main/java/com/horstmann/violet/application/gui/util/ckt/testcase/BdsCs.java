package com.horstmann.violet.application.gui.util.ckt.testcase;
//为不同参数类型的参数加上约束不等式，以及获得每条迁移中的参数
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.horstmann.violet.application.gui.util.ckt.handle.*;


public class BdsCs {

	public static void main(String[] args) {

		String xml="loop6ForXStream.xml";
		Automatic automatic=GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
		//Automatic auto = AddType.addType(automatic);
		ArrayList<Automatic> testCase=StateCoverage__1.testCase(automatic);//获得满足状态覆盖的抽象测试序列
		System.out.println("抽象测试序列个数："+testCase.size());
		for(Automatic a:testCase){
			System.out.println("测试用例名字:"+a.getName());
			for(Transition tran:a.getTransitionSet()){
				System.out.println("激励名称："+tran.getName());
				System.out.println("激励事件，即约束："+tran.getEventSet());
				Map<String,String> map = new HashMap<String,String>();
				Map<String,String> map1 = new HashMap<String,String>();
				Map<String,String> map3 = new HashMap<String,String>();
//////////////////////////in处理开始///////////////////////////////////////
				//处理in里面的不等式和参数，得到参数类型与参数一一对应的map，进行添加不等式操作
//				System.out.println("--------------------------in------------------------");	
//				System.out.println("in---->"+tran.getIn());	//in里面的内容
				
				
				if(GetMap.get_inMap(tran.getIn())==null){//map里面为空，即没有参数
//					System.out.println("keySet集合："+tran.getIn());
				}
				else{//map里有值，即有参数和参数对应类型
					map3 = GetMap.get_inMap(tran.getIn());
//					Set<String> keySet = map3.keySet();
//					System.out.println("keySet集合："+keySet);
					String cs1 = AddBdsType.getcs(map3);
					String cs2 = AddBdsType.getDoubleCs(map3);
					String cs3 = AddBdsType.getBoolCs(map3);
//					System.out.println("根据in类型得到的整数不等式中的参数，即："+cs1);
//					System.out.println("根据in类型得到的小数不等式中的参数，即："+cs2);
//					System.out.println("根据in类型得到的布尔型不等式中的参数，即："+cs3);
//					
//					System.out.println("-----------");
					
					String s1 = AddBdsType.add_bds(map3);			
					String s2 = AddBdsType.add_doublebds(map3);
//					System.out.println("根据in类型添加整数不等式为："+s1);
//					System.out.println("根据in类型添加小数不等式为："+s2);
					
					/*for (String key : keySet) {
						//System.out.println("key即参数类型："+map.get(key));
						System.out.println("|参数名称|："+key.trim()+"----->"+"|参数类型|："+GetMap.get_inMap(tran.getIn()).get(key));
						
					}*/

				}
				

///////////////////////////in处理结束//////////////////////////////////////
//////////////////////////out处理开始///////////////////////////////////////
				
//				System.out.println("out---->"+tran.getOut());
				
				
				
//////////////////////////out处理结束///////////////////////////////////////
//////////////////////////condition处理开始///////////////////////////////////////
				System.out.println("--------------------------condition------------------------");
				//Map<String,String> map = new HashMap<String,String>();
				//Map<String,String> map1 = new HashMap<String,String>();
				if(!tran.getCondition().equals("null")){
					tran.getCondition().replace("false", "False").replace("true", "True");
					//System.out.println("tran.getCondition():::"+tran.getCondition());
					if(tran.getCondition().contains("/")){
						
						String conditonValue = tran.getCondition().split("/")[1];
						System.out.println("//condition//---->"+conditonValue);
						//System.out.println("==================================");
						if(GetMap.get_condMap(conditonValue)==null){
							System.out.println("keySet集合1："+GetMap.get_condMap(conditonValue));
						}
						else{
							if(!(GetMap.get_condMap(conditonValue)==null)){
								map = GetMap.get_condMap(conditonValue);//必须有，要不结果错误
//								Set<String> set = map.keySet();
//								Collection<String> coll = map.values();
								//System.out.println("==================================");
//								System.out.println("keyset集合2："+set);
//								System.out.println("------------------values集合4："+coll);
								
								String cs1 = AddBdsType.getcs(map);
								String cs2 = AddBdsType.getDoubleCs(map);
								String cs3 = AddBdsType.getBoolCs(map);
//								System.out.println("根据condition类型得到的整数不等式中的参数，即："+cs1);
//								System.out.println("根据condition类型得到的小数不等式中的参数，即："+cs2);
//								System.out.println("根据condition类型得到的布尔型不等式中的参数，即："+cs3);								
								System.out.println("-----------");								
								String s1 = AddBdsType.add_bds(map);
//								String s2 = AddBdsType.add_doublebds(map);
//								System.out.println("根据condition类型添加整数不等式为："+s1);
//								System.out.println("根据condition类型添加小数不等式为："+s2);
								
								/*for (String key : set) {
									//System.out.println("key即参数类型："+map.get(key));
									System.out.println("|参数名称|："+key.trim()+"----->"+"|参数类型|："+GetMap.get_condMap(conditonValue).get(key));
									
								}*/

							}
						}
						
					}
					else{
						if(!tran.getCondition().contains("/")){
							System.out.println("condition---->"+tran.getCondition());
							if(GetMap.get_condMap(tran.getCondition())==null){
								System.out.println("keySet集合3："+GetMap.get_condMap(tran.getCondition()));
							}
							else{
								if(!(GetMap.get_condMap(tran.getCondition())==null)){
									map1 = GetMap.get_condMap(tran.getCondition());//必须有，要不结果错误
									//System.out.println("==================================");
//									Set<String> set =map1.keySet();
//									Collection<String> coll = map1.values();
									//System.out.println("==================================");
//									System.out.println("keyset集合4："+set);
//									System.out.println("------------------values集合："+coll);
									
									String cs1 = AddBdsType.getcs(map1);
									String cs2 = AddBdsType.getDoubleCs(map1);
									String cs3 = AddBdsType.getBoolCs(map1);
//									System.out.println("根据condition类型得到的整数不等式中的参数，即："+cs1);
//									System.out.println("根据condition类型得到的小数不等式中的参数，即："+cs2);
//									System.out.println("根据condition类型得到的布尔型不等式中的参数，即："+cs3);									
//									System.out.println("-----------");									
									String s1 = AddBdsType.add_bds(map1);
//									String s2 = AddBdsType.add_doublebds(map1);
//									System.out.println("根据condition类型添加整数不等式为："+s1);
//									System.out.println("根据condition类型添加小数不等式为："+s2);
									
								/*	for (String key : set) {
										//System.out.println("key即参数类型："+map.get(key));
										System.out.println("|参数名称|："+key.trim()+"----->"+"|参数类型|："+GetMap.get_condMap(tran.getCondition()).get(key));
										
									}*/

								}
							}
							
						}
					}
				}
				else{
					if(tran.getCondition().equals("null")){
//						System.out.println("condition--null-->"+tran.getCondition());	
					}
					
				}
				
				
				
//////////////////////////condition处理结束///////////////////////////////////////
				
				System.out.println("*************************迁移分割符**************************");
			}
			System.out.println("-------------------------------测试用例分割符----------------------------------");
		}
	}
}





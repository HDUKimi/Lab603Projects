package com.horstmann.violet.application.gui.util.ckt.testcase;
//输出测试用例组
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.horstmann.violet.application.gui.util.ckt.handle.*;
import com.wolfram.jlink.KernelLink;
import com.wolfram.jlink.MathLinkException;
import com.wolfram.jlink.MathLinkFactory;

public class Testcase {
	public static void main(String[] args) {
		//String xml="loop6ForXStream.xml";
		//String xml="loop6ForXStream.xml";
		String xml="loop10ForXStream.xml";
		//String xml="loop9ForXStream.xml";
		Automatic auto=GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
		ArrayList<Automatic> testCase=StateCoverage__1.testCase(auto);//获得满足状态覆盖的抽象测试序列		
		System.out.println("抽象测试序列个数："+testCase.size());
		int i = 1;
		for(Automatic a:testCase){	///////////////////////////////////
			System.out.println();
			System.out.println("测试用例名字:"+a.getName());
			List<String> list=new ArrayList<String>();//存放整数不等式
			List<String> list1=new ArrayList<String>();//存放小数不等式
			int j=1;
			System.out.println("==================第"+i+"条测试用例开始==================");
			for(Transition tran:a.getTransitionSet()){/////////////////////
				//System.out.println("             ======第"+j+"条迁移开始======");
				System.out.println("--------->激励名称--------> "+tran.getName());
				//System.out.println(tran.getSource()+"---->"+tran.getTarget()+"约束： "+tran.getEventSet());
				//未处理的约束条件	
				System.out.println("约束："+tran.getEventSet());//约束不等式
				/////////////////////////////////////////////////////////////////
				Map<String,String> map = new HashMap<String,String>();
				Map<String,String> map1 = new HashMap<String,String>();
				Map<String,String> map3 = new HashMap<String,String>();
				Map<String,String> map4 = new HashMap<String,String>();//////
				//////////////////////////in处理开始///////////////////////////////////////
				
						//处理in里面的不等式和参数，得到参数类型与参数一一对应的map，进行添加不等式操作
			//System.out.println("========================in========================");	
			System.out.println("in---->"+tran.getIn());	//in里面的内容			
			if(GetMap.get_inMap(tran.getIn())==null){//map里面为空，即没有参数
//				System.out.println("keySet集合："+tran.getIn());
			}
			else{//map里有值，即有参数和参数对应类型
				map3 = GetMap.get_inMap(tran.getIn());
//				Set<String> keySet = map3.keySet();
//				System.out.println("keySet集合："+keySet);
				String cs1 = AddBdsType.getcs(map3);
				String cs2 = AddBdsType.getDoubleCs(map3);
				String cs3 = AddBdsType.getBoolCs(map3);
//				System.out.println("根据in类型得到的整数不等式中的参数，即："+cs1);
//				System.out.println("根据in类型得到的小数不等式中的参数，即："+cs2);
//				System.out.println("根据in类型得到的布尔型不等式中的参数，即："+cs3);

				System.out.println("-----------");

				String s1 = AddBdsType.add_bds(map3);			
				String s2 = AddBdsType.add_doublebds(map3);
//				System.out.println("根据in类型添加整数不等式为："+s1);
//				System.out.println("根据in类型添加小数不等式为："+s2);
				
			 //==0的不等式即为解 ==换为=
				String bds0=GetBds.get_bds_0(tran.getIn().toString());

			//数字型不等式和参数
				String bds1=GetBds.get_bds(tran.getIn().toString());
//				System.out.println("根据in类型得到的整数不等式中的不等式，即："+bds1);  //in上数字不等式
//				System.out.println("根据in类型得到的整数不等式中的参数，即："+cs1);
//				System.out.println("根据in类型得到的小数不等式中的不等式，即："+bds1);
//				System.out.println("根据in类型得到的小数不等式中的参数，即："+cs2);
//				System.out.println("根据in类型添加整数不等式为："+s1);
//				System.out.println("根据in类型添加小数不等式为："+s2);

				System.out.println("整数------>不等式，即："+bds1);  //in上数字不等式
				System.out.println("==0------>不等式，即："+bds0);
				System.out.println("整数------>参数，即："+cs1);
				System.out.println("小数------>不等式，即：");
				System.out.println("小数------>参数，即："+cs2);
				System.out.println("add------>整数不等式为："+s1);
				System.out.println("add------>小数不等式为："+s2);

            //布尔型不等式和参数
				String boolbds=GetBds.get_boolbds(tran.getIn().toString());
				System.out.println("布尔型------>不等式，即："+boolbds);//in上布尔不等式
				System.out.println("布尔型------>参数，即："+cs3);

//////////////////
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
														if(m==0&&!(x1.contains("==0"))){
															list.add(x1);//list里面存放除了==0的整数不等式
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
														if(m==0&&!(x1.contains("==0"))){
															list.add(x1);//list里面存放除了==0的整数不等式
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
														if(m==0&&!(bds1.contains("==0"))){
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
														if(m==0&&!(bds1.contains("==0"))){
															list.add(bds1);//list里面存放除了==0的整数不等式
														}
													}
												}
											}
										}
									}//if(bds1!=null)
									else{
										if(bds1==null){
											System.out.println("整数不等式为----->"+null);
											System.out.println("小数不等式为----->"+null);
										}										
									}
								}//if(cs2!=null)
								else{
									//没有小数参数
									System.out.println("整数不等式为----->"+bds1);
									System.out.println("小数不等式为----->"+null);

								}
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
								System.out.println("整数不等式为----->"+cs);
								
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
								System.out.println("小数不等式为----->"+css);
								/////////
//////////////////
								//数字型不等式和参数
								//布尔型不等式和参数
								//==0的不等式即为解 ==换为=
								//调用mma软件求解方程组
								if(((bds1==null)&&(cs1==null))&&(s2==null)){
									System.out.println("in没有约束即为：null");
								}
								if((bds1!=null)&&(cs1!=null)){
									String bbb = bds1+","+s1;
									System.out.println("bbb++++++:"+bbb);
									String solution1 = Mathematica.getSolution2(bbb, cs1);
									System.out.println("in整数型约束解为："+solution1);
									//System.out.println("数值型约束解为：");
									//System.out.println(solution1);
								}
								else{
									if(s1!=null){
										String solution1 = Mathematica.getSolution2(s1, cs1);
										System.out.println("in整数型约束解为："+solution1);
									}
								}
								if((boolbds!=null)&&(s2!=null)){
									String solution2 = Mathematica.getSolution4(s2, cs2);
									System.out.println("in小数型约束解为："+solution2);
									//System.out.println("布尔型值解为：");
									//System.out.println(solution2);
								}
								System.out.println("in布尔型值解为："+boolbds);//in上布尔不等式
								/*if((boolbds!=null)&&(boolcs!=null)){
									String solution2 = Mathematica.getSolution3(boolbds, boolcs);
									System.out.println("in布尔型值解为："+solution2);
									//System.out.println("布尔型值解为：");
									//System.out.println(solution2);
								}*/
							//==0的不等式即为解 ==换为=	
								if(bds0!=null){
									System.out.println("==0的不等式解为："+bds0);
								}

								 

								//调用mma软件求解方程组
								//String solution1 = Mathematica2.getSolution2(bds1, cs1);
								//String solution2 = Mathematica2.getSolution3(boolbds, boolcs);
								
								
								
								
								
//////////////////

		  

/*
				for (String key : keySet) {
					//System.out.println("key即参数类型："+map.get(key));
					System.out.println("|参数名称|："+key.trim()+"----->"+"|参数类型|："+GetMap.get_inMap(tran.getIn()).get(key));

				}*/

			}
//			System.out.println("===========================in处理结束===========================");
				 
				///////////////////////////in处理结束//////////////////////////////////////
				//////////////////////////out处理开始///////////////////////////////////////

				//			System.out.println("out---->"+tran.getOut());



				//////////////////////////out处理结束///////////////////////////////////////
				//////////////////////////condition处理开始///////////////////////////////////////
				System.out.println("--------------------------condition------------------------");
				//Map<String,String> map = new HashMap<String,String>();
				//Map<String,String> map1 = new HashMap<String,String>();
				if(!tran.getCondition().equals("null")){
					tran.getCondition().replaceAll("false", "False").replaceAll("true", "True");				
					if(tran.getCondition().contains("/")){//取/后面的

						String conditonValue = tran.getCondition().split("/")[1];//要处理的不等式组
						System.out.println("//condition//---->"+conditonValue);
						//System.out.println("==================================");
						if(GetMap.get_condMap(conditonValue)==null){          //没有约束不等式
							System.out.println("keySet集合1："+GetMap.get_condMap(conditonValue));
						}
						else{                                                 //有约束不等式
							if(!(GetMap.get_condMap(conditonValue)==null)){
								map = GetMap.get_condMap(conditonValue);//必须有，要不结果错误
								//Set<String> set = map.keySet();
								//Collection<String> coll = map.values();
								//System.out.println("==================================");
								//System.out.println("keyset集合2："+set);
								//System.out.println("------------------values集合4："+coll);

								String cs1 = AddBdsType.getcs(map);
								String cs2 = AddBdsType.getDoubleCs(map);
								String cs3 = AddBdsType.getBoolCs(map);
								//							System.out.println("根据condition类型得到的整数不等式中的参数，即："+cs1);
								//							System.out.println("根据condition类型得到的小数不等式中的参数，即："+cs2);
								//							System.out.println("根据condition类型得到的布尔型不等式中的参数，即："+cs3);								
								//							System.out.println("-----------");								
								String s1 = AddBdsType.add_bds(map);
								String s2 = AddBdsType.add_doublebds(map);
								//							System.out.println("根据condition类型添加整数不等式为："+s1);
								//							System.out.println("根据condition类型添加小数不等式为："+s2);




								//							for (String key : set) {
								//								//System.out.println("key即参数类型："+map.get(key));
								//								System.out.println("|参数名称|："+key.trim()+"----->"+"|参数类型|："+GetMap.get_condMap(conditonValue).get(key));
								//								
								//							}

								//==0的不等式即为解 ==换为=
								//(==0的解)
								String bds0=GetBds.get_bds_0(conditonValue.toString());


								//数字型不等式和参数
								String bds1=GetBds.get_bds(conditonValue.toString());
							//	System.out.println("根据condition类型得到的整数不等式中的不等式，即："+bds1);  //condition上数字不等式
						//	System.out.println("根据condition类型得到的整数不等式中的参数，即："+cs1);
						//	System.out.println("根据condition类型得到的小数不等式中的不等式，即：");
						//	System.out.println("根据condition类型得到的小数不等式中的参数，即："+cs2);
						//	System.out.println("根据condition类型添加整数不等式为："+s1);
						//	System.out.println("根据condition类型添加小数不等式为："+s2);

								System.out.println("整数------>不等式，即："+bds1);  //condition上数字不等式
								//System.out.println("==0------>不等式，即："+bds0);
								System.out.println("整数------>参数，即："+cs1);
								System.out.println("小数------>不等式，即：");
								System.out.println("小数------>参数，即："+cs2);
								System.out.println("add------>整数不等式为："+s1);
								System.out.println("add------>小数不等式为："+s2);

								//布尔型不等式和参数
								String boolbds=GetBds.get_boolbds(conditonValue.toString());
								System.out.println("布尔型------>不等式，即："+boolbds);//condition上布尔不等式
								System.out.println("布尔型------>参数，即："+cs3);

								//////////////////
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
														if(m==0&&!(x1.contains("==0"))){
															list.add(x1);//list里面存放除了==0的整数不等式
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
														if(m==0&&!(x1.contains("==0"))){
															list.add(x1);//list里面存放除了==0的整数不等式
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
														if(m==0&&!(bds1.contains("==0"))){
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
														if(m==0&&!(bds1.contains("==0"))){
															list.add(bds1);//list里面存放除了==0的整数不等式
														}
													}
												}
											}
										}
									}//if(bds1!=null)
									else{
										if(bds1==null){
											System.out.println("整数不等式为----->"+null);
											System.out.println("小数不等式为----->"+null);
										}										
									}
								}//if(cs2!=null)
								else{
									//没有小数参数
									System.out.println("整数不等式为----->"+bds1);
									System.out.println("小数不等式为----->"+null);

								}
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
								System.out.println("整数不等式为----->"+cs);
								
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
								System.out.println("小数不等式为----->"+css);
								/////////

//////////////////
								//数字型不等式和参数
								//布尔型不等式和参数
								//==0的不等式即为解 ==换为=
								//调用mma软件求解方程组
								if(((bds1==null)&&(cs1==null))&&(s2==null)){
									System.out.println("condition没有约束即为：null");
								}
								if((bds1!=null)&&(cs1!=null)){
									String bbb = bds1+","+s1;
									System.out.println("bbb++++++:"+bbb);
									String solution1 = Mathematica.getSolution2(bbb, cs1);
									System.out.println("condition整数型约束解为："+solution1);
									//System.out.println("数值型约束解为：");
									//System.out.println(solution1);
								}
								else{
									if(s1!=null){
										String solution1 = Mathematica.getSolution2(s1, cs1);
										System.out.println("condition整数型约束解为："+solution1);
									}
								}
								if(/*(boolbds!=null)&&*/(s2!=null)){
									String solution2 = Mathematica.getSolution4(s2, cs2);
									System.out.println("condition小数型约束解为："+solution2);
									//System.out.println("布尔型值解为：");
									//System.out.println(solution2);
								}
								System.out.println("condition布尔型值解为："+boolbds);//in上布尔不等式
								/*if((boolbds!=null)&&(boolcs!=null)){
									String solution2 = Mathematica.getSolution3(boolbds, boolcs);
									System.out.println("in布尔型值解为："+solution2);
									//System.out.println("布尔型值解为：");
									//System.out.println(solution2);
								}*/
							/*//==0的不等式即为解 ==换为=	
								if(bds0!=null){
									System.out.println("==0的不等式解为："+bds0);
								}*/

								 

								//调用mma软件求解方程组
								//String solution1 = Mathematica2.getSolution2(bds1, cs1);
								//String solution2 = Mathematica2.getSolution3(boolbds, boolcs);
								
								
								
								
								
//////////////////


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
									Set<String> set =map1.keySet();
									//								Collection<String> coll = map1.values();
									//System.out.println("==================================");
									//								System.out.println("keyset集合4："+set);
									//								System.out.println("------------------values集合："+coll);

									String cs1 = AddBdsType.getcs(map1);
									String cs2 = AddBdsType.getDoubleCs(map1);
									String cs3 = AddBdsType.getBoolCs(map1);
									//								System.out.println("根据condition类型得到的整数不等式中的参数，即："+cs1);
									//								System.out.println("根据condition类型得到的小数不等式中的参数，即："+cs2);
									//								System.out.println("根据condition类型得到的布尔型不等式中的参数，即："+cs3);									
									//								System.out.println("-----------");									
									String s1 = AddBdsType.add_bds(map1);
									String s2 = AddBdsType.add_doublebds(map1);
									//								System.out.println("根据condition类型添加整数不等式为："+s1);
									//								System.out.println("根据condition类型添加小数不等式为："+s2);

									//								for (String key : set) {
									//									//System.out.println("key即参数类型："+map.get(key));
									//									System.out.println("|参数名称|："+key.trim()+"----->"+"|参数类型|："+GetMap.get_condMap(tran.getCondition()).get(key));
									//									
									//								}

									//==0的不等式即为解 ==换为=
									String bds0=GetBds.get_bds_0(tran.getCondition().toString());
									//数字型不等式和参数
									String bds1=GetBds.get_bds(tran.getCondition().toString());
						//			System.out.println("根据condition类型得到的整数不等式中的不等式，即："+bds1);  //condition上数字不等式
						//		System.out.println("根据condition类型得到的整数不等式中的参数，即："+cs1);
						//		System.out.println("根据condition类型得到的小数不等式中的不等式，即："+bds1);
						//		System.out.println("根据condition类型得到的小数不等式中的参数，即："+cs2);
						//		System.out.println("根据condition类型添加整数不等式为："+s1);
						//		System.out.println("根据condition类型添加小数不等式为："+s2);

									System.out.println("整数------>不等式，即："+bds1);  //condition上数字不等式
									//System.out.println("==0------>不等式，即："+bds0);
									System.out.println("整数------>参数，即："+cs1);
									System.out.println("小数------>不等式，即：");
									System.out.println("小数------>参数，即："+cs2);
									System.out.println("add------>整数不等式为："+s1);
									System.out.println("add------>小数不等式为："+s2);

									//布尔型不等式和参数
									String boolbds=GetBds.get_boolbds(tran.getCondition().toString());
									System.out.println("布尔型------>不等式，即："+boolbds);//condition上布尔不等式
									System.out.println("布尔型------>参数，即："+cs3);


					//////////////////
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
															if(m==0&&!(x1.contains("==0"))){
																list.add(x1);//list里面存放除了==0的整数不等式
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
															if(m==0&&!(x1.contains("==0"))){
																list.add(x1);//list里面存放除了==0的整数不等式
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
															if(m==0&&!(bds1.contains("==0"))){
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
															if(m==0&&!(bds1.contains("==0"))){
																list.add(bds1);//list里面存放除了==0的整数不等式
															}
														}
													}
												}
											}
										}//if(bds1!=null)
										else{
											if(bds1==null){
												System.out.println("整数不等式为----->"+null);
												System.out.println("小数不等式为----->"+null);
											}										
										}
									}//if(cs2!=null)
									else{
										//没有小数参数
										System.out.println("整数不等式为----->"+bds1);
										System.out.println("小数不等式为----->"+null);

									}
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
									System.out.println("整数不等式为----->"+cs);
									
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
									System.out.println("小数不等式为----->"+css);
									/////////
//////////////////
									//数字型不等式和参数
									//布尔型不等式和参数
									//==0的不等式即为解 ==换为=
									//调用mma软件求解方程组
									if(((bds1==null)&&(cs1==null))&&(s2==null)){
										System.out.println("condition没有约束即为：null");
									}
									if((bds1!=null)&&(cs1!=null)){
										String bbb = bds1+","+s1;
										System.out.println("bbb++++++:"+bbb);
										String solution1 = Mathematica.getSolution2(bbb, cs1);
										System.out.println("condition整数型约束解为："+solution1);
										//System.out.println("数值型约束解为：");
										//System.out.println(solution1);
									}
									else{
										if(s1!=null){
											String solution1 = Mathematica.getSolution2(s1, cs1);
											System.out.println("condition整数型约束解为："+solution1);
										}
									}
									if(/*(boolbds!=null)&&*/(s2!=null)){
										String solution2 = Mathematica.getSolution4(s2, cs2);
										System.out.println("condition小数型约束解为："+solution2);
										//System.out.println("布尔型值解为：");
										//System.out.println(solution2);
									}
									System.out.println("condition布尔型值解为："+boolbds);//in上布尔不等式
									/*if((boolbds!=null)&&(boolcs!=null)){
										String solution2 = Mathematica.getSolution3(boolbds, boolcs);
										System.out.println("in布尔型值解为："+solution2);
										//System.out.println("布尔型值解为：");
										//System.out.println(solution2);
									}*/
								/*//==0的不等式即为解 ==换为=	
									if(bds0!=null){
										System.out.println("==0的不等式解为："+bds0);
									}*/

									 

									//调用mma软件求解方程组
									//String solution1 = Mathematica2.getSolution2(bds1, cs1);
									//String solution2 = Mathematica2.getSolution3(boolbds, boolcs);
									
									
									
									
									
	//////////////////
									
									
								}
							}
						}
					}
				}
				else{
					if(tran.getCondition().equals("null")){
						//					System.out.println("condition--null-->"+tran.getCondition());	
					}

				}

				System.out.println("===========================condition处理结束===========================");

				//////////////////////////condition处理结束///////////////////////////////////////









				/*		

			//数字型不等式和参数
				String bds1=Get_str.get_bds(tran.getEventSet().toString());
				//System.out.println(bds1);  //一条迁移上数字不等式
				String cs1=Get_str.get_cs(bds1);
				//System.out.println(cs1);//一条迁移上数字不等式中的参数
				//System.out.println("bds---------->"+bds);
            //布尔型不等式和参数
				String boolbds=Get_str.get_bool_bds(tran.getEventSet().toString());
				//System.out.println(boolbds);//一条迁移上布尔不等式
				String boolcs=Get_str.get_bool_cs(boolbds);
				//System.out.println(boolcs);//一条迁移上布尔不等式中的参数
		   //==0的不等式即为解 ==换为=
				String bds0=Get_str.get_bds_0(tran.getEventSet().toString());
				//
				if(bds0!=null){
					System.out.println("==0的不等式解为："+bds0);
				}

			//调用mma软件求解方程组
				if(((bds1==null)&&(cs1==null))&&((boolbds==null)&&(boolcs==null))){
					System.out.println("没有约束即为：null");
				}
				if((bds1!=null)&&(cs1!=null)){
					String solution1 = Mathematica.getSolution2(bds1, cs1);
					System.out.println("数值型约束解为："+solution1);
					//System.out.println("数值型约束解为：");
					//System.out.println(solution1);
				}
				if((boolbds!=null)&&(boolcs!=null)){
					String solution2 = Mathematica.getSolution3(boolbds, boolcs);
					System.out.println("布尔型值解为："+solution2);
					//System.out.println("布尔型值解为：");
					//System.out.println(solution2);
				}
			//==0的不等式即为解 ==换为=	
				if(bds0!=null){
					System.out.println("==0的不等式解为："+bds0);
				}

				 */

				//调用mma软件求解方程组
				//String solution1 = Mathematica2.getSolution2(bds1, cs1);
				//String solution2 = Mathematica2.getSolution3(boolbds, boolcs);

				//System.out.println("                 ======第"+j+"条迁移结束======");
				j++;
			}
			//System.out.println("==================第"+i+"条测试用例结束==================");
			i++;
		}



	}

}



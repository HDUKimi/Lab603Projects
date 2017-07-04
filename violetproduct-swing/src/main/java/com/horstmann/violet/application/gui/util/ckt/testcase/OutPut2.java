package com.horstmann.violet.application.gui.util.ckt.testcase;
//实例化输出结果  根据map3和OutPut1改写
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.horstmann.violet.application.gui.util.ckt.handle.Automatic;
import com.horstmann.violet.application.gui.util.ckt.handle.GetAutomatic;
import com.horstmann.violet.application.gui.util.ckt.handle.StateCoverage__1;
import com.horstmann.violet.application.gui.util.ckt.handle.Transition;


public class OutPut2 {

	public static void main(String[] args) {
		//String xml="loop6ForXStream.xml";
		String xml="loopForXStream3.13.xml";
		Automatic auto=GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
		ArrayList<Automatic> testCase=StateCoverage__1.testCase(auto);//获得满足状态覆盖的抽象测试序列
		
		

		//########################################        第四步             ###############################################
		//#########################################################################################################
		//=====================================================================读取满足状态覆盖的抽象测试用例
		//======================================================================根据抽象测试用例，求解约束不等式
		System.out.println("----------------------------读取满足状态覆盖的测试用例----------------------------");
		System.out.println("===========================正在获取测试用例");
		System.out.println("===========================");
		System.out.println("===========================正在处理测试用例约束条件");
		System.out.println("===========================");
		System.out.println("===========================正在求解约束不等式");
		System.out.println("===========================");
		System.out.println("===========================正在进行实例化过程");
		System.out.println("===========================");
		System.out.println();
		System.out.println("  ===>  抽象测试序列个数："+testCase.size());

		int i = 1;
		String ttt=null;
		String ttt1=null;
		String ttt2=null;
		String ttt3=null;

		for(Automatic a:testCase){	///////////////////////////////////
			List<String> list=new ArrayList<String>();//存放整数不等式
			List<String> list1=new ArrayList<String>();//存放小数不等式
			int j=1;
			System.out.println();
			System.out.println("===========================正在读取第"+i+"条测试用例");
			System.out.println("  ===>  测试用例名字:"+a.getName());
			for(Transition tran:a.getTransitionSet()){/////////////////////
				System.out.println("       -----------------------------");
				//System.out.println("      ===>  迁移(激励)名称--->"+tran.getName());
				if(tran.getName().contains("(")){
					int index11=tran.getName().replace("!", "").replace("?", "").indexOf("(");
					String sss=tran.getName().substring(0,index11);
					System.out.println("      ===>  迁移(激励)名称："+sss);
				}
				else{
					System.out.println("      ===>  迁移(激励)名称："+tran.getName().replace("!", "").replace("?", ""));
				}
				
				System.out.println("        ===>  迁移Id："+tran.getId());								
				System.out.println("        ===>  源状态名称："+tran.getSource());
//				System.out.println("        ===>  目的状态名称："+tran.getTarget());
				//System.out.println("             ======第"+j+"条迁移开始======");
				//				System.out.println("--------->激励名称--------> "+tran.getName());
				//System.out.println(tran.getSource()+"---->"+tran.getTarget()+"约束： "+tran.getEventSet());
				//未处理的约束条件	
//				System.out.println("约束："+tran.getEventSet());//约束不等式
				/////////////////////////////////////////////////////////////////
				Map<String,String> map = new HashMap<String,String>();
				Map<String,String> map1 = new HashMap<String,String>();
				Map<String,String> map3 = new HashMap<String,String>();
				Map<String,String> map4 = new HashMap<String,String>();//////
				///////////////////////////////////////////////in处理开始///////////////////////////////////////

				//处理in里面的不等式和参数，得到参数类型与参数一一对应的map，进行添加不等式操作
				//System.out.println("========================in========================");	
                //System.out.println("in---->"+tran.getIn());//in里面的内容			
				if(GetMap.get_inMap(tran.getIn())==null){//map里面为空，即没有参数
					//System.out.println("keySet集合："+tran.getIn());
				}
				else{//map里有值，即有参数和参数对应类型
					map3 = GetMap.get_inMap(tran.getIn());
					String cs1 = AddBdsType.getcs(map3);
					String cs2 = AddBdsType.getDoubleCs(map3);
					String cs3 = AddBdsType.getBoolCs(map3);
					//					System.out.println("-----------");
					String s1 = AddBdsType.add_bds(map3);			
					String s2 = AddBdsType.add_doublebds(map3);		
					String bds2=null;
					//==0的不等式即为解 ==换为=
					String bds0=GetBds.get_bds_0(tran.getIn().toString());

					//数字型不等式和参数
					String bds1=GetBds.get_bds(tran.getIn().toString());
					


					/////////////////////////////////////
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
							//	System.out.println("整数不等式为----->"+cs);

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
                            //	System.out.println("小数不等式为----->"+css);
						}//if(bds1!=null)
						else{
							if(bds1==null){
								//				System.out.println("整数不等式为----->"+null);
								//				System.out.println("小数不等式为----->"+null);
							}										
						}												
					}//if(cs2!=null)
					else{
						//没有小数参数
						//		System.out.println("整数不等式为----->"+bds1);
						//		System.out.println("小数不等式为----->"+null);

					}
										
					
					//////////////////////



					//////////////////////
					//System.out.println("整数------>不等式，即："+bds1);  //in上数字不等式
					//System.out.println("==0------>不等式，即："+bds0);
					//					System.out.println("整数------>参数，即："+cs1);
					//System.out.println("小数------>不等式，即："+bds2);
					//					System.out.println("小数------>参数，即："+cs2);
					//System.out.println("add------>整数不等式为："+s1);
					//System.out.println("add------>小数不等式为："+s2);

					//布尔型不等式和参数
					String boolbds=GetBds.get_boolbds(tran.getIn().toString());
					if(cs3!=null&&boolbds==null){
						boolbds = AddBdsType.add_boolbds(cs3);
					}
                   //System.out.println("布尔型------>不等式，即："+boolbds);//in上布尔不等式
					//System.out.println("布尔型------>参数，即："+cs3);


                     //System.out.println("in上不等式：");

					////////////////////////////////////
					//调用mma软件求解方程组
					if(((bds1==null)&&(cs1==null))&&(bds2==null)&&(s2==null)){
//						System.out.println("        ===>  in上没有约束即为：null");
					}
					if((bds1!=null)&&(cs1!=null)){
						String bbb = bds1+","+s1;
//						System.out.println("        ===>  in上整数型数值不等式："+bbb);
                          //System.out.println("        ===>  in上整数型数值参数："+cs1);
						int resultNum = getMathNum(bbb);
						String solution1 = Mathematica.getSolution2(bbb, cs1, resultNum);
						ttt=solution1.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
						//ttt=bbb.toString();
						//System.out.println("in整数型约束解为："+solution1);
					}
					else{
						if(s1!=null){
//							System.out.println("        ===>  in上整数型数值不等式："+s1);
                             //System.out.println("in上整数型数值参数："+cs1);
							int resultNum = getMathNum(s1);
							String solution1 = Mathematica.getSolution2(s1, cs1, resultNum);
							ttt=solution1.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
							//System.out.println("in上整数型约束解为："+solution1);
						}
					}
					if((bds2!=null)&&(s2!=null)){
//						System.out.println("        ===>  in上小数型数值不等式："+s2);
                        //System.out.println("in上小数型数值参数："+cs2);
						String bb = bds2+","+s2;
						int resultNum = getMathNum(bb);
						String solution2 = Mathematica.getSolution4(bb, cs2, resultNum);
						ttt1=solution2.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
						//ttt1=s2.toString();
						//System.out.println("in上小数型约束解为："+solution2);
					}else{
						if((s2!=null)){
							int resultNum = getMathNum(s2);
							String solution2 = Mathematica.getSolution4(s2, cs2, resultNum);
							ttt1=solution2.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
						}
					}
					if(boolbds!=null){
//						System.out.println("        ===>  in上布尔型的不等式："+boolbds);
						if((bds1!=null)&&(cs1!=null)&&(s2!=null)){
							ttt2=ttt+","+ttt1+","+boolbds;
						}
						if((bds1!=null)&&(cs1!=null)&&(s2==null)){
							ttt2=ttt+","+boolbds;;
						}
						if((bds1==null)&&(cs1==null)&&(s2!=null)){
							ttt2=ttt1+","+boolbds;
						}
					}
					else{
						if((bds1!=null)&&(cs1!=null)&&(s2!=null)){
							ttt2=ttt+","+ttt1;
						}
						if((bds1!=null)&&(cs1!=null)&&(s2==null)){
							ttt2=ttt;
						}
						if((bds1==null)&&(cs1==null)&&(s2!=null)){
							ttt2=ttt1;
						}
					}




					//////////////////


				}
				//			System.out.println("===========================in处理结束===========================");

				//////////////////////////////////////////////in处理结束//////////////////////////////////////
				//////////////////////////////////////////////out处理开始///////////////////////////////////////

				//			System.out.println("out---->"+tran.getOut());



				//////////////////////////////////////////////out处理结束///////////////////////////////////////
				/////////////////////////////////////////////condition处理开始///////////////////////////////////////
//				System.out.println("--------------------------condition------------------------");
				//Map<String,String> map = new HashMap<String,String>();
				//Map<String,String> map1 = new HashMap<String,String>();
				if(!tran.getCondition().equals("null")){
					tran.getCondition().replaceAll("false", "False").replaceAll("true", "True");				
					if(tran.getCondition().contains("/")){//取/后面的

						String conditonValue = tran.getCondition().split("/")[1];//要处理的不等式组
//						System.out.println("          condition/---->"+conditonValue);
						//System.out.println("==================================");
						if(GetMap.get_condMap(conditonValue)==null){          //没有约束不等式
							//							System.out.println("keySet集合1："+GetMap.get_condMap(conditonValue));
						}
						else{                                                 //有约束不等式
							if(!(GetMap.get_condMap(conditonValue)==null)){
								map = GetMap.get_condMap(conditonValue);//必须有，要不结果错误

								String cs1 = AddBdsType.getcs(map);
								String cs2 = AddBdsType.getDoubleCs(map);
								String cs3 = AddBdsType.getBoolCs(map);
								//								System.out.println("-----------");								
								String s1 = AddBdsType.add_bds(map);
								String s2 = AddBdsType.add_doublebds(map);	
								String bds2=null;

								//==0的不等式即为解 ==换为=
								//(==0的解)
								//								String bds0=GetBds.get_bds_0(conditonValue.toString());

								//数字型不等式和参数
								String bds1=GetBds.get_bds(conditonValue.toString());	
								
								
								
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
										//								System.out.println("整数不等式为----->"+cs);

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
									}//if(bds1!=null)
									else{
										if(bds1==null){
											//											System.out.println("整数不等式为----->"+null);
											//											System.out.println("小数不等式为----->"+null);
										}										
									}
									
									
								}//if(cs2!=null)
								else{
									//没有小数参数
									//									System.out.println("整数不等式为----->"+bds1);
									//									System.out.println("小数不等式为----->"+null);

								}
												
							
								//////////////////////
								
								
								
								
								
								
								//////////////////////////////////
//								System.out.println("整数------>不等式，即："+bds1);  //condition上数字不等式
								//System.out.println("==0------>不等式，即："+bds0);
								//								System.out.println("整数------>参数，即："+cs1);
//								System.out.println("小数------>不等式，即："+bds2);
								//								System.out.println("小数------>参数，即："+cs2);
//								System.out.println("add------>整数不等式为："+s1);
//								System.out.println("add------>小数不等式为："+s2);

								//布尔型不等式和参数
								String boolbds=GetBds.get_boolbds(conditonValue.toString());
								if(cs3!=null&&boolbds==null){
									boolbds = AddBdsType.add_boolbds(cs3);
								}
//								System.out.println("布尔型------>不等式，即："+boolbds);//condition上布尔不等式  ==
//								System.out.println("布尔型------>参数，即："+cs3);

								

								///////////////////////////////////
								//调用mma软件求解方程组
								if(((bds1==null)&&(cs1==null))&&(s2==null)){
//									System.out.println("        ===>  condition上没有约束即为：null");
									//input.setText("null");
								}
								if((bds1!=null)&&(cs1!=null)){
									String bbb = bds1+","+s1;
//									System.out.println("        ===>  condition上整数型数值不等式："+bbb);
                                   //System.out.println("        ===>  condition上整数型数值参数："+cs1);
									int resultNum = getMathNum(bbb);
									String solution1 = Mathematica.getSolution2(bbb, cs1, resultNum);
									ttt=solution1.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
									//ttt=bbb.toString();
									//									System.out.println("condition整数型约束解为："+solution1);
								}
								else{
									if(s1!=null){
										System.out.println("        ===>  condition上整数型数值不等式："+s1);
                                      //System.out.println("        ===>  condition上整数型数值参数："+cs1);
										int resultNum = getMathNum(s1);
										String solution1 = Mathematica.getSolution2(s1, cs1, resultNum);
										ttt=solution1.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
										//ttt=s1.toString();
										//System.out.println("condition上整数型约束解为："+solution1);
									}
								}
								if((bds2!=null)&&(s2!=null)){
//									System.out.println("        ===>  condition上小数型数值不等式："+s2);
                                    //System.out.println("        ===>  condition上小数型数值参数："+cs2);
									String bb = bds2+","+s2;
									int resultNum = getMathNum(bb);
									String solution2 = Mathematica.getSolution4(bb, cs2, resultNum);
									ttt1=solution2.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
									//ttt1=s2.toString();
									//									System.out.println("condition上小数型约束解为："+solution2);
								}else{
									if((s2!=null)){
										int resultNum = getMathNum(s2);
										String solution2 = Mathematica.getSolution4(s2, cs2, resultNum);
										ttt1=solution2.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
									}
								}
								//System.out.println("condition布尔型值解为："+boolbds);//condition上布尔不等式	
								if(boolbds!=null){
//									System.out.println("        ===>  condition上布尔型的不等式："+boolbds);
									if((bds1!=null)&&(cs1!=null)&&(s2!=null)){
										ttt3=ttt+","+ttt1+","+boolbds;
									}
									if((bds1!=null)&&(cs1!=null)&&(s2==null)){
										ttt3=ttt+","+boolbds;
									}
									if((bds1==null)&&(cs1==null)&&(s2!=null)){
										ttt3=ttt1+","+boolbds;
									}
								}
								else{
									if((bds1!=null)&&(cs1!=null)&&(s2!=null)){
										ttt3=ttt+","+ttt1;
									}
									if((bds1!=null)&&(cs1!=null)&&(s2==null)){
										ttt3=ttt;
									}
									if((bds1==null)&&(cs1==null)&&(s2!=null)){
										ttt3=ttt1;
									}
								}


								//////////////////


							}
						}

					}
					else{
						if(!tran.getCondition().contains("/")){
//							System.out.println("          condition---->"+tran.getCondition());
							if(GetMap.get_condMap(tran.getCondition())==null){
								//								System.out.println("keySet集合3："+GetMap.get_condMap(tran.getCondition()));
							}
							else{
								if(!(GetMap.get_condMap(tran.getCondition())==null)){
									map1 = GetMap.get_condMap(tran.getCondition());//必须有，要不结果错误
									//System.out.println("==================================");
									Set<String> set =map1.keySet();

									String cs1 = AddBdsType.getcs(map1);
									String cs2 = AddBdsType.getDoubleCs(map1);
									String cs3 = AddBdsType.getBoolCs(map1);			
									String s1 = AddBdsType.add_bds(map1);
									String s2 = AddBdsType.add_doublebds(map1);
									String bds2=null;

									//==0的不等式即为解 ==换为=
									String bds0=GetBds.get_bds_0(tran.getCondition().toString());
									//数字型不等式和参数
									String bds1=GetBds.get_bds(tran.getCondition().toString());					

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
											//									System.out.println("整数不等式为----->"+cs);

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
											//									System.out.println("小数不等式为----->"+css);
										}//if(bds1!=null)
										else{
											if(bds1==null){
												//												System.out.println("整数不等式为----->"+null);
												//												System.out.println("小数不等式为----->"+null);
											}										
										}
										
									
									}//if(cs2!=null)
									else{
										//没有小数参数
										//	System.out.println("整数不等式为----->"+bds1);
										//	System.out.println("小数不等式为----->"+null);

									}
														
								
									/////////
									
									
									
									/////////////////输出
                        //         System.out.println("整数------>不等式，即："+bds1);  //condition上数字不等式
									//System.out.println("==0------>不等式，即："+bds0);
									//System.out.println("整数------>参数，即："+cs1);
                         //		System.out.println("小数------>不等式，即："+bds2);
									//System.out.println("小数------>参数，即："+cs2);
                        //			System.out.println("add------>整数不等式为："+s1);
                           //				System.out.println("add------>小数不等式为："+s2);

									//布尔型不等式和参数
									String boolbds=GetBds.get_boolbds(tran.getCondition().toString());
									if(cs3!=null&&boolbds==null){
										boolbds = AddBdsType.add_boolbds(cs3);
									}
                            //			System.out.println("布尔型------>不等式，即："+boolbds);//condition上布尔不等式
                            //			System.out.println("布尔型------>参数，即："+cs3);


								
									/////////////////////////////////////////
									//调用mma软件求解方程组
									if(((bds1==null)&&(cs1==null))&&(s2==null)){
//										System.out.println("        ===>  condition上没有约束即为：null");
										//input.setText("null");
									}
									if((bds1!=null)&&(cs1!=null)){
										String bbb = bds1+","+s1;
//										System.out.println("        ===>  condition上整数型数值不等式："+bbb);
//										System.out.println("        ===>  condition上整数型数值参数："+cs1);
										int resultNum = getMathNum(bbb);
										String solution1 = Mathematica.getSolution2(bbb, cs1, resultNum);
										ttt=solution1.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
										//ttt=bbb.toString();
										//										System.out.println("condition整数型约束解为："+solution1);
									}
									else{
										if(s1!=null){
//											System.out.println("        ===>  condition上整数型数值不等式："+s1);
//											System.out.println("        ===>  condition上整数型数值参数："+cs1);
											int resultNum = getMathNum(s1);
											String solution1 = Mathematica.getSolution2(s1, cs1, resultNum);
											ttt=solution1.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
											//ttt=s1.toString();
											//											System.out.println("condition上整数型约束解为："+solution1);
										}
									}
									if((bds2!=null)&&(s2!=null)){
//										System.out.println("        ===>  condition上小数型数值不等式："+s2);
//										System.out.println("        ===>  condition上小数型数值参数："+cs2);
										String bb = bds2+","+s2;
										int resultNum = getMathNum(bb);
										String solution2 = Mathematica.getSolution4(bb, cs2, resultNum);
										ttt1=solution2.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
										//ttt1=s2.toString();
										//										System.out.println("condition上小数型约束解为："+solution2);
									}else{
										if((s2!=null)){
											int resultNum = getMathNum(s2);
											String solution2 = Mathematica.getSolution4(s2, cs2, resultNum);
											ttt1=solution2.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
										}
									}
									//System.out.println("condition布尔型值解为："+boolbds);//condition上布尔不等式	
									if(boolbds!=null){
//										System.out.println("        ===>  condition上布尔型的不等式："+boolbds);
										if((bds1!=null)&&(cs1!=null)&&(s2!=null)){
											ttt3=ttt+","+ttt1+","+boolbds;
										}
										if((bds1!=null)&&(cs1!=null)&&(s2==null)){
											ttt3=ttt+","+boolbds;
										}
										if((bds1==null)&&(cs1==null)&&(s2!=null)){
											ttt3=ttt1+","+boolbds;
										}
									}
									else{
										if((bds1!=null)&&(cs1!=null)&&(s2!=null)){
											ttt3=ttt+","+ttt1;
										}
										if((bds1!=null)&&(cs1!=null)&&(s2==null)){
											ttt3=ttt;
										}
										if((bds1==null)&&(cs1==null)&&(s2!=null)){
											ttt3=ttt1;
										}
									}


									//////////////////////////////////////


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

//				System.out.println("===========================condition处理结束===========================");

				////////////////////////////////////////////////condition处理结束///////////////////////////////////////





//				System.out.println("******************************************");
				/////////////////////////////////////////////////////////////////////////////////////
				//添加节点
				
				//
				////////////////////为input添加求出来的解
				if(ttt2==null&&ttt3==null){		
					System.out.println("        ===>  实例化结果：null");
				}
				else{
					if(ttt2!=null&&ttt3!=null){
						ttt3.toString().replaceAll("false", "False").replaceAll("true", "True");
						String x=ttt2+","+ttt3;
						x.toString().replace("false", "False").replace("true", "True");
						System.out.println("        ===>  实例化结果："+x);
					}
					else{
						if(ttt2==null&&ttt3!=null){
							ttt3.toString().replaceAll("false", "False").replaceAll("true", "True");
							String x=ttt3;
							x.toString().replace("false", "False").replace("true", "True");
							System.out.println("        ===>  实例化结果："+x);
						}
						else{
							if(ttt2!=null&&ttt3==null){
								String x=ttt2;
								x.toString().replaceAll("false", "False").replaceAll("true", "True");
								System.out.println("        ===>  实例化结果："+x);
							}
						}
					}
				}

//				System.out.println("******************************************");

				////////////////////////////////////////////////////////////////////////////////////
				//System.out.println("                 ======第"+j+"条迁移结束======");
				j++;
			}
			System.out.println("===========================第"+i+"条测试用例读取完成");
			i++;
		}
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

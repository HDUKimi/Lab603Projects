package com.horstmann.violet.application.gui.util.ckt.testcase;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
//输出测试用例组
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import com.horstmann.violet.application.gui.util.ckt.handle.*;
import com.wolfram.jlink.KernelLink;
import com.wolfram.jlink.MathLinkException;
import com.wolfram.jlink.MathLinkFactory;

public class GetXML3 {
	public static void main(String[] args) {
		//String xml="loop6ForXStream.xml";
		String xml="loopForXStream3.13.xml";
		
		//String xml="loop9ForXStream.xml";
		//String xml="stabilize_runForXStream.xml";
		Automatic auto=GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
		ArrayList<Automatic> testCase=StateCoverage__1.testCase(auto);//获得满足状态覆盖的抽象测试序列		
		System.out.println("抽象测试序列个数："+testCase.size());
		int i = 1;
		String ttt=null;
		String ttt1=null;
		String ttt2=null;
		String ttt3=null;
		// 1、创建document对象，代表整个xml文档
		Document dom = DocumentHelper.createDocument();
		// 2、创建根节点TCS
		org.dom4j.Element tcs = dom.addElement("TCS");
		// 3、向TCS节点中添加version属性
		for(Automatic a:testCase){	///////////////////////////////////
			List<String> list=new ArrayList<String>();//存放整数不等式
			List<String> list1=new ArrayList<String>();//存放小数不等式
			int j=1;
			System.out.println();
			System.out.println("测试用例名字:"+a.getName());
			System.out.println("==================第"+i+"条测试用例开始==================");

			// 4、生成子节点及节点内容
			Element testcase = tcs.addElement("testcase");

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
///////////////////////////////////////////////in处理开始///////////////////////////////////////

				//处理in里面的不等式和参数，得到参数类型与参数一一对应的map，进行添加不等式操作
				//System.out.println("========================in========================");	
				System.out.println("in---->"+tran.getIn());	//in里面的内容			
				if(GetMap.get_inMap(tran.getIn())==null){//map里面为空，即没有参数
					//				System.out.println("keySet集合："+tran.getIn());
				}
				else{//map里有值，即有参数和参数对应类型
					map3 = GetMap.get_inMap(tran.getIn());
					String cs1 = AddBdsType.getcs(map3);
					String cs2 = AddBdsType.getDoubleCs(map3);
					String cs3 = AddBdsType.getBoolCs(map3);
					System.out.println("-----------");
					String s1 = AddBdsType.add_bds(map3);			
					String s2 = AddBdsType.add_doublebds(map3);				
					//==0的不等式即为解 ==换为=
					String bds0=GetBds.get_bds_0(tran.getIn().toString());

					//数字型不等式和参数
					String bds1=GetBds.get_bds(tran.getIn().toString());
					System.out.println("整数------>不等式，即："+bds1);  //in上数字不等式
					//System.out.println("==0------>不等式，即："+bds0);
					System.out.println("整数------>参数，即："+cs1);
					System.out.println("小数------>不等式，即：");
					System.out.println("小数------>参数，即："+cs2);
					System.out.println("add------>整数不等式为："+s1);
					System.out.println("add------>小数不等式为："+s2);

					//布尔型不等式和参数
					String boolbds=GetBds.get_boolbds(tran.getIn().toString());
					System.out.println("布尔型------>不等式，即："+boolbds);//in上布尔不等式
					System.out.println("布尔型------>参数，即："+cs3);

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
					//////////////////////
					////////////////////////////////////
					//调用mma软件求解方程组
					if(((bds1==null)&&(cs1==null))&&(s2==null)){
						System.out.println("没有约束即为：null");
						//input.setText("null");
					}
					if((bds1!=null)&&(cs1!=null)){
						String bbb = bds1+","+s1;
						System.out.println("*******整数型数值不等式*******"+bbb);
						System.out.println("*******整数型数值参数*******"+cs1);
						int resultNum = getMathNum(bbb);
						String solution1 = Mathematica.getSolution2(bbb, cs1, resultNum);
						ttt=solution1.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
						System.out.println("in整数型约束解为："+solution1);
					}
					else{
						if(s1!=null){
							System.out.println("*******整数型数值不等式*******"+s1);
							System.out.println("*******整数型数值参数*******"+cs1);
							int resultNum = getMathNum(s1);
							String solution1 = Mathematica.getSolution2(s1, cs1,resultNum);
							ttt=solution1.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
							System.out.println("in上整数型约束解为："+solution1);
						}
					}
					if((s2!=null)){
						System.out.println("*******小数型数值不等式*******"+s2);
						System.out.println("*******小数型数值参数*******"+cs2);
						int resultNum = getMathNum(s2);
						String solution2 = Mathematica.getSolution4(s2, cs2, resultNum);
						ttt1=solution2.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
						System.out.println("in上小数型约束解为："+solution2);
					}
					//System.out.println("in布尔型值解为："+boolbds);//condition上布尔不等式	
					if(boolbds!=null){
						System.out.println("in上布尔型的不等式解为："+boolbds);
						if((bds1!=null)&&(cs1!=null)&&(s2!=null)){
							ttt2=ttt+","+ttt1+","+boolbds;
							//input.setText(ttt+","+ttt1+","+boolbds);
						}
						if((bds1!=null)&&(cs1!=null)&&(s2==null)){
							ttt2=ttt+","+boolbds;
							//input.setText(ttt+","+boolbds);
						}
						if((bds1==null)&&(cs1==null)&&(s2!=null)){
							ttt2=ttt1+","+boolbds;
							//input.setText(ttt1+","+boolbds);
						}
					}
					else{
						if((bds1!=null)&&(cs1!=null)&&(s2!=null)){
							ttt2=ttt+","+ttt1;
							//input.setText(ttt+","+ttt1);
						}
						if((bds1!=null)&&(cs1!=null)&&(s2==null)){
							ttt2=ttt;
							//input.setText(ttt);
						}
						if((bds1==null)&&(cs1==null)&&(s2!=null)){
							ttt2=ttt1;
							//input.setText(ttt1);
						}
					}




					//////////////////




					//for (String key : keySet) {
					//System.out.println("key即参数类型："+map.get(key));
					//	System.out.println("|参数名称|："+key.trim()+"----->"+"|参数类型|："+GetMap.get_inMap(tran.getIn()).get(key));

					//}

				}
				//			System.out.println("===========================in处理结束===========================");

//////////////////////////////////////////////in处理结束//////////////////////////////////////
//////////////////////////////////////////////out处理开始///////////////////////////////////////

				//			System.out.println("out---->"+tran.getOut());



//////////////////////////////////////////////out处理结束///////////////////////////////////////
/////////////////////////////////////////////condition处理开始///////////////////////////////////////
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
								System.out.println("-----------");								
								String s1 = AddBdsType.add_bds(map);
								String s2 = AddBdsType.add_doublebds(map);												

								//==0的不等式即为解 ==换为=
								//(==0的解)
								String bds0=GetBds.get_bds_0(conditonValue.toString());

								//数字型不等式和参数
								String bds1=GetBds.get_bds(conditonValue.toString());				
								System.out.println("整数------>不等式，即："+bds1);  //condition上数字不等式
								//System.out.println("==0------>不等式，即："+bds0);
								System.out.println("整数------>参数，即："+cs1);
								System.out.println("小数------>不等式，即：");
								System.out.println("小数------>参数，即："+cs2);
								System.out.println("add------>整数不等式为："+s1);
								System.out.println("add------>小数不等式为："+s2);

								//布尔型不等式和参数
								String boolbds=GetBds.get_boolbds(conditonValue.toString());
								System.out.println("布尔型------>不等式，即："+boolbds);//condition上布尔不等式  ==
								System.out.println("布尔型------>参数，即："+cs3);

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
								//////////////////////

								///////////////////////////////////
								//调用mma软件求解方程组
								if(((bds1==null)&&(cs1==null))&&(s2==null)){
									System.out.println("没有约束即为：null");
									//input.setText("null");
								}
								if((bds1!=null)&&(cs1!=null)){
									String bbb = bds1+","+s1;
									System.out.println("*******整数型数值不等式*******"+bbb);
									System.out.println("*******整数型数值参数*******"+cs1);
									int resultNum = getMathNum(bbb);
									String solution1 = Mathematica.getSolution2(bbb, cs1, resultNum);
									ttt=solution1.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
									System.out.println("condition整数型约束解为："+solution1);
								}
								else{
									if(s1!=null){
										System.out.println("*******整数型数值不等式*******"+s1);
										System.out.println("*******整数型数值参数*******"+cs1);
										int resultNum = getMathNum(s1);
										String solution1 = Mathematica.getSolution2(s1, cs1,resultNum);
										ttt=solution1.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
										System.out.println("condition上整数型约束解为："+solution1);
									}
								}
								if((s2!=null)){
									System.out.println("*******小数型数值不等式*******"+s2);
									System.out.println("*******小数型数值参数*******"+cs2);
									int resultNum = getMathNum(s2);
									String solution2 = Mathematica.getSolution4(s2, cs2, resultNum);
									ttt1=solution2.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
									System.out.println("condition上小数型约束解为："+solution2);
								}
								//System.out.println("condition布尔型值解为："+boolbds);//condition上布尔不等式	
								if(boolbds!=null){
									System.out.println("condition上布尔型的不等式解为："+boolbds);
									if((bds1!=null)&&(cs1!=null)&&(s2!=null)){
										ttt3=ttt+","+ttt1+","+boolbds;
										//input.setText(ttt+","+ttt1+","+boolbds);
									}
									if((bds1!=null)&&(cs1!=null)&&(s2==null)){
										ttt3=ttt+","+boolbds;
										//input.setText(ttt+","+boolbds);
									}
									if((bds1==null)&&(cs1==null)&&(s2!=null)){
										ttt3=ttt1+","+boolbds;
										//input.setText(ttt1+","+boolbds);
									}
								}
								else{
									if((bds1!=null)&&(cs1!=null)&&(s2!=null)){
										ttt3=ttt+","+ttt1;
										//input.setText(ttt+","+ttt1);
									}
									if((bds1!=null)&&(cs1!=null)&&(s2==null)){
										ttt3=ttt;
										//input.setText(ttt);
									}
									if((bds1==null)&&(cs1==null)&&(s2!=null)){
										ttt3=ttt1;
										//input.setText(ttt1);
									}
								}


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

									String cs1 = AddBdsType.getcs(map1);
									String cs2 = AddBdsType.getDoubleCs(map1);
									String cs3 = AddBdsType.getBoolCs(map1);			
									String s1 = AddBdsType.add_bds(map1);
									String s2 = AddBdsType.add_doublebds(map1);

									//==0的不等式即为解 ==换为=
									String bds0=GetBds.get_bds_0(tran.getCondition().toString());
									//数字型不等式和参数
									String bds1=GetBds.get_bds(tran.getCondition().toString());					

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
									/////////////////////////////////////////
									//调用mma软件求解方程组
									if(((bds1==null)&&(cs1==null))&&(s2==null)){
										System.out.println("没有约束即为：null");
										//input.setText("null");
									}
									if((bds1!=null)&&(cs1!=null)){
										String bbb = bds1+","+s1;
										System.out.println("*******整数型数值不等式*******"+bbb);
										System.out.println("*******整数型数值参数*******"+cs1);
										int resultNum = getMathNum(bbb);
										String solution1 = Mathematica.getSolution2(bbb, cs1, resultNum);
										ttt=solution1.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
										System.out.println("condition整数型约束解为："+solution1);
									}
									else{
										if(s1!=null){
											System.out.println("*******整数型数值不等式*******"+s1);
											System.out.println("*******整数型数值参数*******"+cs1);
											int resultNum = getMathNum(s1);
											String solution1 = Mathematica.getSolution2(s1, cs1, resultNum);
											ttt=solution1.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
											System.out.println("condition上整数型约束解为："+solution1);
										}
									}
									if((s2!=null)){
										System.out.println("*******小数型数值不等式*******"+s2);
										System.out.println("*******小数型数值参数*******"+cs2);
										int resultNum = getMathNum(s2);
										String solution2 = Mathematica.getSolution4(s2, cs2, resultNum);
										ttt1=solution2.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
										System.out.println("condition上小数型约束解为："+solution2);
									}
									//System.out.println("condition布尔型值解为："+boolbds);//condition上布尔不等式	
									if(boolbds!=null){
										System.out.println("condition上布尔型的不等式解为："+boolbds);
										if((bds1!=null)&&(cs1!=null)&&(s2!=null)){
											ttt3=ttt+","+ttt1+","+boolbds;
											//input.setText(ttt+","+ttt1+","+boolbds);
										}
										if((bds1!=null)&&(cs1!=null)&&(s2==null)){
											ttt3=ttt+","+boolbds;
											//input.setText(ttt+","+boolbds);
										}
										if((bds1==null)&&(cs1==null)&&(s2!=null)){
											ttt3=ttt1+","+boolbds;
											//input.setText(ttt1+","+boolbds);
										}
									}
									else{
										if((bds1!=null)&&(cs1!=null)&&(s2!=null)){
											ttt3=ttt+","+ttt1;
											//input.setText(ttt+","+ttt1);
										}
										if((bds1!=null)&&(cs1!=null)&&(s2==null)){
											ttt3=ttt;
											//input.setText(ttt);
										}
										if((bds1==null)&&(cs1==null)&&(s2!=null)){
											ttt3=ttt1;
											//input.setText(ttt1);
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

				System.out.println("===========================condition处理结束===========================");

////////////////////////////////////////////////condition处理结束///////////////////////////////////////





/////////////////////////////////////////////////////////////////////////////////////
				//添加节点
				Element process = testcase.addElement("process");
				Element operation = process.addElement("operation");
				if(tran.getName().contains("(")){
					int index11=tran.getName().replace("!", "").replace("?", "").indexOf("(");
					String sss=tran.getName().substring(0,index11);
					operation.setText(sss);
				}
				else{
					operation.setText(tran.getName().replace("!", "").replace("?", ""));
				}
				Element input = process.addElement("input");
				//
////////////////////为input添加求出来的解
				if(ttt2==null&&ttt3==null){
					input.setText("null");
				}
				else{
					if(ttt2!=null&&ttt3!=null){
						input.setText(ttt2+","+ttt3);
					}
					else{
						if(ttt2==null&&ttt3!=null){
							input.setText(ttt3);
						}
						else{
							if(ttt2!=null&&ttt3==null){
								input.setText(ttt2);
							}
						}
					}
				}



////////////////////////////////////////////////////////////////////////////////////
				//System.out.println("                 ======第"+j+"条迁移结束======");
				j++;
			}
			//System.out.println("==================第"+i+"条测试用例结束==================");
			i++;
		}

		OutputFormat format = OutputFormat.createPrettyPrint();
		// 6、生成xml文件
		//File file = new File("C:\\Users\\Administrator\\Desktop\\tcss.xml");
		File file = new File("E:\\项目\\xml\\tcs_loop9.xml");
		//File file = new File("E:\\xml\\tcs2.xml");

		XMLWriter writer;
		try {
			writer = new XMLWriter(new FileOutputStream(file), format);
			writer.write(dom);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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



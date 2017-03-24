package com.horstmann.violet.application.gui.util.ckt.testcase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class get_bdscs {


	/**
	 * 取in里面的参数与类型对应的map
	 * @param tran
	 * @return
	 */
	public static Map<String,String> get_inMap(String ins){
		List<String> list1=new ArrayList<String>(); //存放数值型不等式
		List<String> list2=new ArrayList<String>(); //存放布尔型不等式
		List<String> list3=new ArrayList<String>(); //存放==0的不等式
		List<String> cs1=new ArrayList<String>();//存放数值型参数
		List<String> cs2=new ArrayList<String>();//存放布尔型参数
		List<String> cs3=new ArrayList<String>();//存放==0的参数

		Map<String,String> map = new HashMap<String,String>();//映射参数和类型之间关系
		int i=0;
		if(ins==null){  //in里面为空
			return null;
		}
		else{
			if(ins!=null){ //in里面不为空
				if(ins.contains("#")){////////////////////////////////////////////////#区分不等式和参数类型
					String[] s = ins.split("#");//#左边为不等式，#右边为不等式中参数的类型
					String sbds = s[0].replace("&&", ",").replace("||", ",").replace(" ", "")/*.replace("(", "").replace(")", "")*/;//存放in中的不等式
					String stype = s[1];               //存放in中参数的类型

					if(stype!=null&&stype.contains(",")){///////////////////////////////不止一个参数
						String[] intype = stype.split(",");
						if(sbds.contains(",")){  ////////////////////////////不止一个不等式且不止一个参数
							String[] inbds = sbds.split(",");
							for(String str:inbds){     ///拆成一个一个的不等式
								//带有约束范围的不等式 像  x<3，y>z    #int,double,int
								if(str.contains("<")||str.contains(">")||str.contains("=")){
									String cs= getcs(str);
									if(cs!=null){
										if(cs.contains(",")){//一个不等式中不止一个参数
											if(!(str.contains("==0")||str.contains("True")||str.contains("False"))){
												list1.add(str);  // 数值型
												String[] ccs=cs.split(",");
												for(String ss:ccs){
													if(ss!=null){
														cs1.add(ss);
														map.put(ss, intype[i]);
														i++;
													}
												}
											}
											else{
												if(str.contains("True")||str.contains("False")){
													list2.add(str);  // 布尔型
													//cs2.add(cs);
													String[] ccs=cs.split(",");
													for(String ss:ccs){
														if(ss!=null){
															cs2.add(ss);
															map.put(ss, intype[i]);
															i++;
														}
													}
												}
												else{
													if(str.contains("==0")){
														list3.add(str); // ==0型
														//cs3.add(cs);
														String[] ccs=cs.split(",");
														for(String ss:ccs){
															if(ss!=null){
																cs1.add(ss);
																map.put(ss, intype[i]);
																i++;
															}
														}
													}
												}
											}

										}
										else{//一个不等式中只有一个参数
											if(!cs.contains(",")){//一个不等式中只有一个参数
												if(!(str.contains("==0")||str.contains("True")||str.contains("False"))){
													list1.add(str);  // 数值型
													cs1.add(cs);
													map.put(cs, intype[i]);
													i++;
												}
												else{
													if(str.contains("True")||str.contains("False")){
														list2.add(str);  // 布尔型
														cs2.add(cs);
														map.put(cs, intype[i]);
														i++;
													}
													else{
														if(str.contains("==0")){
															list3.add(str); // ==0型
															cs3.add(cs);
															map.put(cs, intype[i]);
															i++;
														}
													}
												}
											}
										}
									}
									/*else{//参数为空，即没有不等式
								                       }	*/						
								}						
								else{//一个不等式中   没有明确约束  像  x#int   
									String cs= str;
									cs1.add(cs);//默认不会存在布尔型参数没具体约束
									map.put(cs, intype[i]);
									i++;
								}
							}//for(String str:inbds)
						}//if(sbds.contains(","))
						else{//////////////////////////////只有一个不等式,不止一个参数
							//带有约束范围的不等式 像  x<y#int,double
							if(!sbds.contains(",")){
								if(sbds.contains("<")||sbds.contains(">")||sbds.contains("=")){
									String cs= getcs(sbds);
									if(cs!=null){
										if(cs.contains(",")){//一个不等式中不止一个参数
											if(!(sbds.contains("==0")||sbds.contains("True")||sbds.contains("False"))){
												list1.add(sbds);  // 数值型
												String[] ccs=cs.split(",");
												for(String ss:ccs){
													if(ss!=null){
														cs1.add(ss);
														map.put(ss, intype[i]);
														i++;
													}
												}
											}
											else{
												if(sbds.contains("True")||sbds.contains("False")){
													list2.add(sbds);  // 布尔型
													//cs2.add(cs);
													String[] ccs=cs.split(",");
													for(String ss:ccs){
														if(ss!=null){
															cs2.add(ss);
															map.put(ss, intype[i]);
															i++;
														}
													}
												}
												else{
													if(sbds.contains("==0")){
														list3.add(sbds); // ==0型
														//cs3.add(cs);
														String[] ccs=cs.split(",");
														for(String ss:ccs){
															if(ss!=null){
																cs1.add(ss);
																map.put(ss, intype[i]);
																i++;
															}
														}
													}
												}
											}

										}
										else{
											if(!cs.contains(",")){//一个不等式中只有一个参数
												if(!(sbds.contains("==0")||sbds.contains("True")||sbds.contains("False"))){
													list1.add(sbds);  // 数值型
													cs1.add(cs);
													map.put(cs, intype[i]);
													i++;
												}
												else{
													if(sbds.contains("True")||sbds.contains("False")){
														list2.add(sbds);  // 布尔型
														cs2.add(cs);
														map.put(cs, intype[i]);
														i++;
													}
													else{
														if(sbds.contains("==0")){
															list3.add(sbds); // ==0型
															cs3.add(cs);
															map.put(cs, intype[i]);
															i++;
														}
													}
												}
											}
										}
									}
									/*else{//参数为空，即没有不等式

								}	*/						
								}						
								else{//没有明确约束  像  x#int
									String cs= sbds;
									cs1.add(cs);//默认不会存在布尔型参数没具体约束
									map.put(cs, intype[i]);
									i++;
								}
							}

							////////////////////////////
						}
					}//if(stype!=null&&stype.contains(","))
					else{
						if(stype!=null&&!stype.contains(",")){//  只有一个参数
							if(sbds.contains(",")){   ////////////////////////////多个不等式，一个参数
								String[] inbds = sbds.split(",");
								for(String str:inbds){     ///拆成一个一个的不等式
									//带有约束范围的不等式 像  x<5,cycle==10ms#int    x,cycle==10ms#int
									if(str.contains("<")||str.contains(">")||str.contains("=")){
										String cs= getcs(str);
										if(cs!=null&&!cs.contains(",")){
											if(!(str.contains("==0")||str.contains("True")||str.contains("False"))){
												list1.add(str);  // 数值型
												cs1.add(cs);
												map.put(cs, stype);
											}
											else{
												if(str.contains("True")||str.contains("False")){
													list2.add(str);  // 布尔型
													cs2.add(cs);
													map.put(cs, stype);
												}
												else{
													if(str.contains("==0")){
														list3.add(str); // ==0型
														cs3.add(cs);
														map.put(cs, stype);
													}
												}
											}
										}
									}//if(sbds.contains("<")||sbds.contains(">")||sbds.contains("="))
									else{//没有具体约束的带一个参数的不等式
										if(!(str.contains("<")||str.contains(">")||str.contains("="))){
											list1.add(str);
											cs1.add(str);
											map.put(str, stype);
										}
									}
								}
							}
							else{
								if(!sbds.contains(",")){////////////一个不等式，一个参数
									//带有约束范围的不等式 像  x<5#int    x#int
									if(sbds.contains("<")||sbds.contains(">")||sbds.contains("=")){
										String cs= getcs(sbds);
										if(cs!=null&&!cs.contains(",")){
											if(!(sbds.contains("==0")||sbds.contains("True")||sbds.contains("False"))){
												list1.add(sbds);  // 数值型
												cs1.add(cs);
												map.put(cs, stype);
											}
											else{
												if(sbds.contains("True")||sbds.contains("False")){
													list2.add(sbds);  // 布尔型
													cs2.add(cs);
													map.put(cs, stype);
												}
												else{
													if(sbds.contains("==0")){
														list3.add(sbds); // ==0型
														cs3.add(cs);
														map.put(cs, stype);
													}
												}
											}
										}
									}//if(sbds.contains("<")||sbds.contains(">")||sbds.contains("="))
									else{//没有具体约束的带一个参数的不等式
										if(!(sbds.contains("<")||sbds.contains(">")||sbds.contains("="))){
											list1.add(sbds);
											cs1.add(sbds);
											map.put(sbds, stype);
										}
									}
								}
							}

						}//if(stype!=null&&!stype.contains(","))												
					}
				}//if(ins.contains("#"))
				/*else{//不带# 即没有参数

				}*/
			}//if(ins!=null)
			return map;


		}
	}

	/**
	 * 取condition里面的参数与类型对应的map
	 * @param tran
	 * @return
	 */

	public static Map<String,String> get_condMap(String condition){
		List<String> list1=new ArrayList<String>(); //存放数值型不等式
		List<String> list2=new ArrayList<String>(); //存放布尔型不等式
		List<String> list3=new ArrayList<String>(); //存放==0的不等式
		List<String> cs1=new ArrayList<String>();//存放数值型参数
		List<String> cs2=new ArrayList<String>();//存放布尔型参数
		List<String> cs3=new ArrayList<String>();//存放==0的参数

		Map<String,String> map = new HashMap<String,String>();//映射参数和类型之间关系
		int i=0;
		if(condition==null){  //condition里面为空
			return null;
		}
		else{
			if(condition.contains("--")&&condition!=null){
				condition.replace("false", "False").replace("true", "True").replace("&&", ",")/*.replace("(", "").replace(")", "")*/;
				String[] condition1=condition.split("--");
				for(String condition2:condition1){
//					
					System.out.println("condition2即--分开后的不等式："+condition2);
					i=0;
					if(condition2!=null){ //condition里面不为空
						if(condition2.contains("#")){////////////////////////////////////////////////#区分不等式和参数类型
							System.out.println("--分开后的condition里含有#");
							String[] s = condition2.split("#");//#左边为不等式，#右边为不等式中参数的类型
							String sbds = s[0].replace("&&", ",")/*.replace("||", ",")*/.replace(" ", "")/*.replace("(", "").replace(")", "")*/;//存放in中的不等式
							String stype = s[1];               //存放condition中参数的类型
//
							System.out.println("#前不等式："+sbds);
//
							System.out.println("#后参数类型："+stype);

							if(stype!=null&&stype.contains(",")){///////////////////////////////不止一个参数
								
								String[] intype = stype.split(",");
								if(sbds.contains(",")){  //////////////////////////////不止一个不等式且不止一个参数
//
									System.out.println("多个不等式，多个参数，总不等式为："+sbds);
									String[] inbds = sbds.split(",");
									for(String str:inbds){     ///拆成一个一个的不等式
										//带有约束范围的不等式 像  x<3，y>z    #int,double,int
										if(str.contains("<")||str.contains(">")||str.contains("=")){
											String cs= getcs(str);
//  //
											System.out.println("多多分开，数值不等式为："+str);
											System.out.println("多多分开，数值参数为："+cs);
											System.out.println("对参数进行分析，有无逗号，存入map里-------------");
											if(cs!=null){
												if(cs.contains(",")){//一个不等式中不止一个参数
													if(!(str.contains("==0")||str.contains("True")||str.contains("False"))){
														list1.add(str);  // 数值型
														String[] ccs=cs.split(",");
														for(String ss:ccs){
															if(ss!=null){
																cs1.add(ss);
																map.put(ss, intype[i]);
																//System.out.println("i1:"+i);
																i++;
															}
														}
													}
													else{
														if(str.contains("True")||str.contains("False")){
															list2.add(str);  // 布尔型
															//cs2.add(cs);
															String[] ccs=cs.split(",");
															for(String ss:ccs){
																if(ss!=null){
																	cs2.add(ss);
																	map.put(ss, intype[i]);
																	//System.out.println("i2:"+i);
																	i++;
																}
															}
														}
														else{
															if(str.contains("==0")){
																list3.add(str); // ==0型
																//cs3.add(cs);
																String[] ccs=cs.split(",");
																for(String ss:ccs){
																	if(ss!=null){
																		cs1.add(ss);
																		map.put(ss, intype[i]);

																		//System.out.println("i3:"+i);
																		i++;
																	}
																}
															}
														}
													}

												}
												else{//一个不等式中只有一个参数
//
													System.out.println("一个不等式一个参数，不等式为："+str);
													System.out.println("一一，参数为："+cs);
													System.out.println("对参数进行分析，有无逗号，存入map里-------------");
													if(!cs.contains(",")){//一个不等式中只有一个参数
														if(!(str.contains("==0")||str.contains("True")||str.contains("False"))){
															list1.add(str);  // 数值型
															cs1.add(cs);
															map.put(cs, intype[i]);
															//System.out.println("i4:"+i);
															i++;
														}
														else{
															if(str.contains("True")||str.contains("False")){
																list2.add(str);  // 布尔型
																cs2.add(cs);
																map.put(cs, intype[i]);
																//System.out.println("i5:"+i);
																i++;
															}
															else{
																if(str.contains("==0")){
																	list3.add(str); // ==0型
																	cs3.add(cs);
																	map.put(cs, intype[i]);
																	//System.out.println("i6:"+i);
																	i++;
																}
															}
														}
													}
												}
											}
											/*else{//参数为空，即没有不等式
										                       }	*/						
										}						
										else{//一个不等式中   没有明确约束  像  x#int
//											
											System.out.println("一个不等式，没有明确约束像x#int---------------");
											
											System.out.println("参数为不等式，存入map里-------------");
											String cs= str;
											cs1.add(cs);//默认不会存在布尔型参数没具体约束
											map.put(cs, intype[i]);
											//System.out.println("i7:"+i);
											i++;
										}
									}//for(String str:inbds)
								}//if(sbds.contains(","))
								else{//////////////////////////////只有一个不等式,不止一个参数
//
									System.out.println("一个不等式，多个参数，总不等式为："+sbds);
									//带有约束范围的不等式 像  x<y#int,double
									if(!sbds.contains(",")){
										if(sbds.contains("<")||sbds.contains(">")||sbds.contains("=")){
											String cs= getcs(sbds);
//
											System.out.println("一个不等式中参数为："+cs+"----------对参数分析是否多参");
											System.out.println("对参数进行分析，有无逗号，存入map里-------------");
											if(cs!=null){
												if(cs.contains(",")){//一个不等式中不止一个参数
//
													System.out.println("一个不等式，多个参数，不等式为："+sbds+"----参数为："+cs);
													if(!(sbds.contains("==0")||sbds.contains("True")||sbds.contains("False"))){
														list1.add(sbds);  // 数值型
														String[] ccs=cs.split(",");
														for(String ss:ccs){
															if(ss!=null){
																cs1.add(ss);
																//System.out.println("ss:"+ss);
																//System.out.println("i8:"+i);
																//System.out.println("intype[i]:"+intype[i]);
																map.put(ss, intype[i]);/////
																i++;
															}
														}
													}
													else{
														if(sbds.contains("True")||sbds.contains("False")){
															list2.add(sbds);  // 布尔型
															//cs2.add(cs);
															String[] ccs=cs.split(",");
															for(String ss:ccs){
																if(ss!=null){
																	cs2.add(ss);
																	map.put(ss, intype[i]);
																	//System.out.println("i9:"+i);
																	i++;
																}
															}
														}
														else{
															if(sbds.contains("==0")){
																list3.add(sbds); // ==0型
																//cs3.add(cs);
																String[] ccs=cs.split(",");
																for(String ss:ccs){
																	if(ss!=null){
																		cs1.add(ss);
																		map.put(ss, intype[i]);
																		//System.out.println("i10:"+i);
																		i++;
																	}
																}
															}
														}
													}

												}
												else{
													if(!cs.contains(",")){//一个不等式中只有一个参数
//
														System.out.println("一个不等式，一个参数，总不等式为："+sbds);
														if(!(sbds.contains("==0")||sbds.contains("True")||sbds.contains("False"))){
															list1.add(sbds);  // 数值型
															cs1.add(cs);
															map.put(cs, intype[i]);
															//System.out.println("i11:"+i);
															i++;
														}
														else{
															if(sbds.contains("True")||sbds.contains("False")){
																list2.add(sbds);  // 布尔型
																cs2.add(cs);
																map.put(cs, intype[i]);
																//System.out.println("i12:"+i);
																i++;
															}
															else{
																if(sbds.contains("==0")){
																	list3.add(sbds); // ==0型
																	cs3.add(cs);
																	map.put(cs, intype[i]);
																	//System.out.println("i13:"+i);
																	i++;
																}
															}
														}
													}
												}
											}
											/*else{//参数为空，即没有不等式

										}	*/						
										}						
										else{//没有明确约束  像  x#int
//
											System.out.println("一个不等式，没有明确约束像x#int---------------");											
											System.out.println("参数为不等式，存入map里-------------");
											String cs= sbds;
											cs1.add(cs);//默认不会存在布尔型参数没具体约束
											map.put(cs, intype[i]);
											//System.out.println("i14:"+i);
											i++;
										}
									}

									////////////////////////////
								}
							}//if(stype!=null&&stype.contains(","))
							else{
								if(stype!=null&&!stype.contains(",")){//  只有一个参数
									if(sbds.contains(",")){   ////////////////////////////多个不等式，一个参数
//
										System.out.println("多个不等式，一个参数，不等式为："+sbds);
										String[] inbds = sbds.split(",");
										for(String str:inbds){     ///拆成一个一个的不等式
											//带有约束范围的不等式 像  x<5,cycle==10ms#int    x,cycle==10ms#int
											if(str.contains("<")||str.contains(">")||str.contains("=")){
												String cs= getcs(str);
//
												System.out.println("多个不等式，一个参数，参数为："+cs);
												if(cs!=null&&!cs.contains(",")){
													if(!(str.contains("==0")||str.contains("True")||str.contains("False"))){
														list1.add(str);  // 数值型
														cs1.add(cs);
														map.put(cs, stype);
													}
													else{
														if(str.contains("True")||str.contains("False")){
															list2.add(str);  // 布尔型
															cs2.add(cs);
															map.put(cs, stype);
														}
														else{
															if(str.contains("==0")){
																list3.add(str); // ==0型
																cs3.add(cs);
																map.put(cs, stype);
															}
														}
													}
												}
											}//if(sbds.contains("<")||sbds.contains(">")||sbds.contains("="))
											else{//没有具体约束的带一个参数的不等式
												if(!(str.contains("<")||str.contains(">")||str.contains("="))){
//
													System.out.println("一个不等式，没有明确约束像x#int---------------");											
													System.out.println("参数为不等式，存入map里-------------");
													list1.add(str);
													cs1.add(str);
													map.put(str, stype);
												}
											}
										}
									}
									else{
										if(!sbds.contains(",")){////////////一个不等式，一个参数
											System.out.println("一个不等式，一个参数,不等式为："+sbds);
											//带有约束范围的不等式 像  x<5#int    x#int
											if(sbds.contains("<")||sbds.contains(">")||sbds.contains("=")){
												String cs= getcs(sbds);
//
												System.out.println("有具体约束的参数为："+cs);
												if(cs!=null&&!cs.contains(",")){
													if(!(sbds.contains("==0")||sbds.contains("True")||sbds.contains("False"))){
														list1.add(sbds);  // 数值型
														cs1.add(cs);
														map.put(cs, stype);
													}
													else{
														if(sbds.contains("True")||sbds.contains("False")){
															list2.add(sbds);  // 布尔型
															cs2.add(cs);
															map.put(cs, stype);
														}
														else{
															if(sbds.contains("==0")){
																list3.add(sbds); // ==0型
																cs3.add(cs);
																map.put(cs, stype);
															}
														}
													}
												}
											}//if(sbds.contains("<")||sbds.contains(">")||sbds.contains("="))
											else{//没有具体约束的带一个参数的不等式
												System.out.println("没有具体约束，不等式为参数"+sbds);
												if(!(sbds.contains("<")||sbds.contains(">")||sbds.contains("="))){
													list1.add(sbds);
													cs1.add(sbds);
													map.put(sbds, stype);
												}
											}
										}
									}

								}//if(stype!=null&&!stype.contains(","))												
							}
						}//if(condition2.contains("#"))
						/*else{//不带# 即没有参数

						}*/
					}//if(condition2!=null)
				}//for(String condition2:condition1)
			}//if(condition.contains("--")&&condition!=null)
			else{
				if(!condition.contains("--")&&condition!=null){//condition里面不为空
					i=0;
					System.out.println("condition里没有--，即只要一个不等式，不等式为"+condition);
					if(condition.contains("#")){////////////////////////////////////////////////#区分不等式和参数类型
						String[] s = condition.split("#");//#左边为不等式，#右边为不等式中参数的类型
						String sbds = s[0].replace("&&", ",").replace("||", ",").replace(" ", "").replace("(", "").replace(")", "");//存放in中的不等式
						String stype = s[1];               //存放condition中参数的类型
						//System.out.println("不等式："+sbds);
						//System.out.println("参数类型："+stype);

						if(stype!=null&&stype.contains(",")){///////////////////////////////不止一个参数
							String[] intype = stype.split(",");
							if(sbds.contains(",")){  ////////////////////////////不止一个不等式且不止一个参数
								String[] inbds = sbds.split(",");
								for(String str:inbds){     ///拆成一个一个的不等式
									//带有约束范围的不等式 像  x<3，y>z    #int,double,int
									if(str.contains("<")||str.contains(">")||str.contains("=")){
										String cs= getcs(str);
										System.out.println("数值参数为："+cs);
										if(cs!=null){
											if(cs.contains(",")){//一个不等式中不止一个参数
												if(!(str.contains("==0")||str.contains("True")||str.contains("False"))){
													list1.add(str);  // 数值型
													String[] ccs=cs.split(",");
													for(String ss:ccs){
														if(ss!=null){
															cs1.add(ss);
															map.put(ss, intype[i]);
															//System.out.println("i1:"+i);
															i++;
														}
													}
												}
												else{
													if(str.contains("True")||str.contains("False")){
														list2.add(str);  // 布尔型
														//cs2.add(cs);
														String[] ccs=cs.split(",");
														for(String ss:ccs){
															if(ss!=null){
																cs2.add(ss);
																map.put(ss, intype[i]);
																//System.out.println("i2:"+i);
																i++;
															}
														}
													}
													else{
														if(str.contains("==0")){
															list3.add(str); // ==0型
															//cs3.add(cs);
															String[] ccs=cs.split(",");
															for(String ss:ccs){
																if(ss!=null){
																	cs1.add(ss);
																	map.put(ss, intype[i]);

																	//System.out.println("i3:"+i);
																	i++;
																}
															}
														}
													}
												}

											}
											else{//一个不等式中只有一个参数
												if(!cs.contains(",")){//一个不等式中只有一个参数
													if(!(str.contains("==0")||str.contains("True")||str.contains("False"))){
														list1.add(str);  // 数值型
														cs1.add(cs);
														map.put(cs, intype[i]);
														//System.out.println("i4:"+i);
														i++;
													}
													else{
														if(str.contains("True")||str.contains("False")){
															list2.add(str);  // 布尔型
															cs2.add(cs);
															map.put(cs, intype[i]);
															//System.out.println("i5:"+i);
															i++;
														}
														else{
															if(str.contains("==0")){
																list3.add(str); // ==0型
																cs3.add(cs);
																map.put(cs, intype[i]);
																//System.out.println("i6:"+i);
																i++;
															}
														}
													}
												}
											}
										}
										else{//参数为空，即没有不等式
										                       }							
									}						
									else{//一个不等式中   没有明确约束  像  x#int   
										String cs= str;
										cs1.add(cs);//默认不会存在布尔型参数没具体约束
										map.put(cs, intype[i]);
										//System.out.println("i7:"+i);
										i++;
									}
								}//for(String str:inbds)
							}//if(sbds.contains(","))
							else{//////////////////////////////只有一个不等式,不止一个参数
								//带有约束范围的不等式 像  x<y#int,double
								if(!sbds.contains(",")){
									if(sbds.contains("<")||sbds.contains(">")||sbds.contains("=")){
										String cs= getcs(sbds);
										if(cs!=null){
											if(cs.contains(",")){//一个不等式中不止一个参数
												if(!(sbds.contains("==0")||sbds.contains("True")||sbds.contains("False"))){
													list1.add(sbds);  // 数值型
													String[] ccs=cs.split(",");
													for(String ss:ccs){
														if(ss!=null){
															cs1.add(ss);
															//System.out.println("ss:"+ss);
															//System.out.println("i8:"+i);
															//System.out.println("intype[i]:"+intype[i]);
															map.put(ss, intype[i]);/////
															i++;
														}
													}
												}
												else{
													if(sbds.contains("True")||sbds.contains("False")){
														list2.add(sbds);  // 布尔型
														//cs2.add(cs);
														String[] ccs=cs.split(",");
														for(String ss:ccs){
															if(ss!=null){
																cs2.add(ss);
																map.put(ss, intype[i]);
																//System.out.println("i9:"+i);
																i++;
															}
														}
													}
													else{
														if(sbds.contains("==0")){
															list3.add(sbds); // ==0型
															//cs3.add(cs);
															String[] ccs=cs.split(",");
															for(String ss:ccs){
																if(ss!=null){
																	cs1.add(ss);
																	map.put(ss, intype[i]);
																	//System.out.println("i10:"+i);
																	i++;
																}
															}
														}
													}
												}

											}
											else{
												if(!cs.contains(",")){//一个不等式中只有一个参数
													if(!(sbds.contains("==0")||sbds.contains("True")||sbds.contains("False"))){
														list1.add(sbds);  // 数值型
														cs1.add(cs);
														map.put(cs, intype[i]);
														//System.out.println("i11:"+i);
														i++;
													}
													else{
														if(sbds.contains("True")||sbds.contains("False")){
															list2.add(sbds);  // 布尔型
															cs2.add(cs);
															map.put(cs, intype[i]);
															//System.out.println("i12:"+i);
															i++;
														}
														else{
															if(sbds.contains("==0")){
																list3.add(sbds); // ==0型
																cs3.add(cs);
																map.put(cs, intype[i]);
																//System.out.println("i13:"+i);
																i++;
															}
														}
													}
												}
											}
										}
										else{//参数为空，即没有不等式

										}							
									}						
									else{//没有明确约束  像  x#int
										String cs= sbds;
										cs1.add(cs);//默认不会存在布尔型参数没具体约束
										map.put(cs, intype[i]);
										//System.out.println("i14:"+i);
										i++;
									}
								}

								////////////////////////////
							}
						}//if(stype!=null&&stype.contains(","))
						else{
							if(stype!=null&&!stype.contains(",")){//  只有一个参数
								if(sbds.contains(",")){   ////////////////////////////多个不等式，一个参数
									String[] inbds = sbds.split(",");
									for(String str:inbds){     ///拆成一个一个的不等式
										//带有约束范围的不等式 像  x<5,cycle==10ms#int    x,cycle==10ms#int
										if(str.contains("<")||str.contains(">")||str.contains("=")){
											String cs= getcs(str);
											if(cs!=null&&!cs.contains(",")){
												if(!(str.contains("==0")||str.contains("True")||str.contains("False"))){
													list1.add(str);  // 数值型
													cs1.add(cs);
													map.put(cs, stype);
												}
												else{
													if(str.contains("True")||str.contains("False")){
														list2.add(str);  // 布尔型
														cs2.add(cs);
														map.put(cs, stype);
													}
													else{
														if(str.contains("==0")){
															list3.add(str); // ==0型
															cs3.add(cs);
															map.put(cs, stype);
														}
													}
												}
											}
										}//if(sbds.contains("<")||sbds.contains(">")||sbds.contains("="))
										else{//没有具体约束的带一个参数的不等式
											if(!(str.contains("<")||str.contains(">")||str.contains("="))){
												list1.add(str);
												cs1.add(str);
												map.put(str, stype);
											}
										}
									}
								}
								else{
									if(!sbds.contains(",")){////////////一个不等式，一个参数
										//带有约束范围的不等式 像  x<5#int    x#int
										if(sbds.contains("<")||sbds.contains(">")||sbds.contains("=")){
											String cs= getcs(sbds);
											if(cs!=null&&!cs.contains(",")){
												if(!(sbds.contains("==0")||sbds.contains("True")||sbds.contains("False"))){
													list1.add(sbds);  // 数值型
													cs1.add(cs);
													map.put(cs, stype);
												}
												else{
													if(sbds.contains("True")||sbds.contains("False")){
														list2.add(sbds);  // 布尔型
														cs2.add(cs);
														map.put(cs, stype);
													}
													else{
														if(sbds.contains("==0")){
															list3.add(sbds); // ==0型
															cs3.add(cs);
															map.put(cs, stype);
														}
													}
												}
											}
										}//if(sbds.contains("<")||sbds.contains(">")||sbds.contains("="))
										else{//没有具体约束的带一个参数的不等式
											if(!(sbds.contains("<")||sbds.contains(">")||sbds.contains("="))){
												list1.add(sbds);
												cs1.add(sbds);
												map.put(sbds, stype);
											}
										}
									}
								}

							}//if(stype!=null&&!stype.contains(","))												
						}
					}//if(condition2.contains("#"))
					else{//不带# 即没有参数

						}






				}
			}

			return map;


		}
	}








	/**
	 * 取一个不等式中的参数	
	 * @param s
	 * @return
	 */
	public static String getcs(String s){
		String c1=new String();
		String cs=new String(); //用于返回参数组
		String s1=new String();//获取第一个不等式首字符
		String s2=new String();
		String s3=new String();
		String s4=new String();
		List<String> list=new ArrayList<String>();   //存放参数
		if((s!=null)&&!(s.contains("cycle"))){
			String a = s.replace("(", "").replace(")", "").replace("true", "True").replace("false", "False");
			//先判断是否含布尔型
			if(a.contains("True")||a.contains("False")){
				if(a.contains("==")){
					int index1=a.indexOf("=");
					s1=a.substring(0,index1);//==前面的
					if(!(s1.contains("True")||s1.contains("False"))){
						list.add(s1);
					}
					s2=a.substring(index1+2,a.length());//==后面的
					if(!(s2.contains("True")||s2.contains("False"))){
						list.add(s2);
					}
				}			 
			}
			//不是布尔型不等式，则处理数值型不等式
			else{
				int ss1=a.substring(0, 1).toCharArray()[0];
				if(!((ss1>=48&&ss1<=57)||ss1==45)){//第一个为参数
					if(a.contains("<=")){  ///////处理两个<=
						int index1=a.indexOf("<");
						s1=a.substring(0,index1);//第一个<=前面的
						list.add(s1);
						s2=a.substring(index1+2,a.length());//第一个<=后面的
						if(s2.contains("<=")){
							int index2=s2.indexOf("<");
							s3=s2.substring(0,index2);//两个<=之间的
							s4=s2.substring(index2+2,s2.length());//第二个<=后面的
							int ss2=s3.substring(0, 1).toCharArray()[0];
							int ss3=s4.substring(0, 1).toCharArray()[0];
							if(!((ss2>=48&&ss2<=57)||ss2==45)){
								list.add(s3);
							}
							if(!((ss3>=48&&ss3<=57)||ss3==45)){
								list.add(s4);
							}
						}	
						else{
							int ss2=s2.substring(0, 1).toCharArray()[0];
							if(!((ss2>=48&&ss2<=57)||ss2==45)){
								list.add(s2);
							}
						}
					}             //////////<=.<=
					else{
						if(a.contains("<")){
							int index1=a.indexOf("<");
							s1=a.substring(0,index1);//<=前面的
							list.add(s1);
							s2=a.substring(index1+1,a.length());//<=后面的
							//int ss2=Integer.valueOf(s2.substring(0, 1));
							int ss2=s2.substring(0, 1).toCharArray()[0];
							if(!((ss2>=48&&ss2<=57)||ss2==45)){
								list.add(s2);
							}
						}
						else{
							if(a.contains(">=")){  ///////处理两个>=
								int index1=a.indexOf(">");
								s1=a.substring(0,index1);//第一个>=前面的
								list.add(s1);
								s2=a.substring(index1+2,a.length());//第一个>=后面的
								if(s2.contains(">=")){
									int index2=s2.indexOf(">");
									s3=s2.substring(0,index2);//两个>=之间的
									s4=s2.substring(index2+2,s2.length());//第二个>=后面的
									int ss2=s3.substring(0, 1).toCharArray()[0];
									int ss3=s4.substring(0, 1).toCharArray()[0];
									if(!((ss2>=48&&ss2<=58)||ss2==45)){
										list.add(s3);
									}
									if(!((ss3>=48&&ss3<=57)||ss3==45)){
										list.add(s4);
									}
								}
								else{
									int ss2=s2.substring(0, 1).toCharArray()[0];
									if(!((ss2>=48&&ss2<=57)||ss2==45)){
										list.add(s2);
									}
								}
							}             //////////>=.>=
							else{
								if(a.contains(">")){
									int index1=a.indexOf(">");
									s1=a.substring(0,index1);//>=前面的
									list.add(s1);
									s2=a.substring(index1+1,a.length());//>=后面的
									//int ss2=Integer.valueOf(s2.substring(0, 1));
									int ss2=s2.substring(0, 1).toCharArray()[0];
									if(!((ss2>=48&&ss2<=57)||ss2==45)){
										list.add(s2);
									}
								}
								else{
									if(a.contains("==")){
										int index1=a.indexOf("=");
										s1=a.substring(0,index1);//==前面的
										list.add(s1);
										s2=a.substring(index1+2,a.length());//==后面的
										//int ss2=Integer.valueOf(s2.substring(0, 1));
										int ss2=s2.substring(0, 1).toCharArray()[0];
										if(!((ss2>=48&&ss2<=57)||ss2==45)){
											list.add(s2);
										}
									}
									else{
										if(a.contains("!=")){
											int index1=a.indexOf("!");
											s1=a.substring(0,index1);//!=前面的
											list.add(s1);
											s2=a.substring(index1+2,a.length());//!=后面的
											//int ss2=Integer.valueOf(s2.substring(0, 1));
											int ss2=s2.substring(0, 1).toCharArray()[0];
											if(!((ss2>=48&&ss2<=57)||ss2==45)){
												list.add(s2);
											} 
										}
									}
								}
								//	 }
								// }
							}
						}	 
					}//不为>=.>=				 
				}
				if((ss1>=48&&ss1<=57)||ss1==45){//第一个为自然数
					if(a.contains("<=")){  ///////处理两个<=
						int index1=a.indexOf("<");
						s2=a.substring(index1+2,a.length());//第一个<=后面的
						if(s2.contains("<=")){
							int index2=s2.indexOf("<");
							s3=s2.substring(0,index2);//两个<=之间的
							s4=s2.substring(index2+2,s2.length());//第二个<=后面的
							//int ss2=Integer.valueOf(s3.substring(0, 1));
							// int ss3=Integer.valueOf(s4.substring(0, 1));
							int ss2=s3.substring(0, 1).toCharArray()[0];
							int ss3=s4.substring(0, 1).toCharArray()[0];
							if(!((ss2>=48&&ss2<=57)||ss2==45)){
								list.add(s3);
							}
							if(!((ss3>=48&&ss3<=57)||ss3==45)){
								list.add(s4);
							}
						} 
						else{
							int ss2=s2.substring(0, 1).toCharArray()[0];
							if(!((ss2>=48&&ss2<=57)||ss2==45)){
								list.add(s2);
							}
						}
					}             //////////<=.<=
					/*else{
						 if(a.contains("<=")){
							 int index1=a.indexOf("<");
							 s2=a.substring(index1+2,a.length());//<=后面的
							 //int ss2=Integer.valueOf(s2.substring(0, 1));
							 int ss2=s2.substring(0, 1).toCharArray()[0];
							 if(!((ss2>=48&&ss2<=57)||ss2==45)){
								 list.add(s2);
							 }
						 }           ///////////    <=
					 */						 else{
						 if(a.contains("<")){
							 int index1=a.indexOf("<");
							 s2=a.substring(index1+1,a.length());//<=后面的
							 //int ss2=Integer.valueOf(s2.substring(0, 1));
							 int ss2=s2.substring(0, 1).toCharArray()[0];
							 if(!((ss2>=48&&ss2<=57)||ss2==45)){
								 list.add(s2);
							 }
						 }
						 else{
							 if(a.contains(">=")){  ///////处理两个>=
								 int index1=a.indexOf(">");
								 s2=a.substring(index1+2,a.length());//第一个>=后面的
								 if(s2.contains(">=")){
									 int index2=s2.indexOf(">");
									 s3=s2.substring(0,index2);//两个>=之间的
									 s4=s2.substring(index2+2,s2.length());//第二个>=后面的
									 //int ss2=Integer.valueOf(s3.substring(0, 1));
									 //int ss3=Integer.valueOf(s4.substring(0, 1));
									 int ss2=s3.substring(0, 1).toCharArray()[0];
									 int ss3=s4.substring(0, 1).toCharArray()[0];
									 if(!((ss2>=48&&ss2<=57)||ss2==45)){
										 list.add(s3);
									 }
									 if(!((ss3>=48&&ss3<=57)||ss3==45)){
										 list.add(s4);
									 }
								 }
								 else{
									 int ss2=s2.substring(0, 1).toCharArray()[0];
									 if(!((ss2>=48&&ss2<=57)||ss2==45)){
										 list.add(s2);
									 }
								 }

							 }             //////////>=.>=
							 /*else{
									 if(a.contains(">=")){
										 int index1=a.indexOf(">");
										 s2=a.substring(index1+2,a.length());//<=后面的
										 //int ss2=Integer.valueOf(s2.substring(0, 1));
										 int ss2=s2.substring(0, 1).toCharArray()[0];
										 if(!((ss2>=48&&ss2<=57)||ss2==45)){
											 list.add(s2);
										 }
									 }     */      ///////////    >=
							 else{
								 if(a.contains(">")){
									 int index1=a.indexOf(">");
									 s2=a.substring(index1+1,a.length());//>=后面的
									 //int ss2=Integer.valueOf(s2.substring(0, 1));
									 int ss2=s2.substring(0, 1).toCharArray()[0];
									 if(!((ss2>=48&&ss2<=57)||ss2==45)){
										 list.add(s2);
									 }
								 }
								 else{
									 if(a.contains("==")){
										 int index1=a.indexOf("=");
										 s2=a.substring(index1+2,a.length());//==后面的
										 //int ss2=Integer.valueOf(s2.substring(0, 1));
										 int ss2=s2.substring(0, 1).toCharArray()[0];
										 if(!((ss2>=48&&ss2<=57)||ss2==45)){
											 list.add(s2);
										 }
									 }
									 else{
										 if(a.contains("!=")){
											 int index1=a.indexOf("!");
											 s2=a.substring(index1+2,a.length());//!=后面的
											 //int ss2=Integer.valueOf(s2.substring(0, 1));
											 int ss2=s2.substring(0, 1).toCharArray()[0];
											 if(!((ss2>=48&&ss2<=57)||ss2==45)){
												 list.add(s2);
											 } 
										 }
									 }
								 }
							 }
						 }	 
					 }				 
				}
			}
		}
		else{
			return null;
		}
		if(list.size()>1){
			cs=list.get(0);
			for(int j=1;j<list.size();j++){
				c1=list.get(j);
				cs=cs+","+c1;
			}
		}
		if(list.size()==1){
			cs=list.get(0);
		}					
		return cs;
	}

}


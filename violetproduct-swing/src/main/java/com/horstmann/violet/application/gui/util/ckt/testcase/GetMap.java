package com.horstmann.violet.application.gui.util.ckt.testcase;
//最终的取出in和condition里的map
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetMap {


	/**
	 * 取in里面的参数与类型对应的map
	 * @param tran
	 * @return
	 */
	public static Map<String,String> get_inMap(String ins){
		Map<String,String> map = new HashMap<String,String>();//映射参数和类型之间关系
		if(ins.equals("null")){  //in里面为空
			return null;
		}
		else{
			if(ins!=null){ //in里面不为空
				if(ins.contains("#")){//  #区分不等式和参数类型
					String[] s = ins.replace(" ", "").split("#");//#左边为不等式，#右边为不等式中参数的类型
					//String sbds = s[0].replace("&&", ",").replace("||", ",").replace(" ", "")/*.replace("(", "").replace(")", "")*/;//存放in中的不等式
					String stype = s[1];  //存放in中参数的类型

					if(stype!=null&&stype.contains(",")){///////////////////////////////不止一个参数
						String[] intype = stype.split(",");
						for(String tt1:intype){//tt1为一个参数对应一个类型
							String[] ss1 = tt1.split(":");
							map.put(ss1[0], ss1[1]);
						}

					}//if(stype!=null&&stype.contains(","))
					else{
						if(stype!=null&&!stype.contains(",")){//  只有一个参数与对应的类型
							String[] ss1 = stype.split(":");
							map.put(ss1[0], ss1[1]);

						}//if(stype!=null&&!stype.contains(","))												
					}
				}//if(ins.contains("#"))
				else{//不带# 即没有参数
					return null; 
				}
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

		Map<String,String> map = new HashMap<String,String>();//映射参数和类型之间关系
		if(condition==null){  //condition里面为空
			return null;
		}
		else{
			if(condition.contains("--")&&condition!=null){
				//condition=condition.replace("false", "False").replace("true", "True").replace("&&", ",")/*.replace("(", "").replace(")", "")*/;
				condition=condition.replace("false", "False").replace("true", "True");///
				String[] condition1=condition.replace(" ", "").split("--");
				for(String condition2:condition1){
					if(condition2!=null){ //condition里面不为空
						if(condition2.contains("#")){
							String[] s = condition2.split("#");//#左边为不等式，#右边为不等式中参数的类型
							String stype = s[1];  //存放in中参数的类型
							if(stype!=null&&stype.contains(",")){//不止一个参数
								String[] contype = stype.split(",");
								for(String tt1:contype){//tt1为一个参数对应一个类型
									String[] ss1 = tt1.split(":");
									map.put(ss1[0], ss1[1]);
								}

							}//if(stype!=null&&stype.contains(","))
							else{
								if(stype!=null&&!stype.contains(",")){//  只有一个参数与对应的类型
									String[] ss1 = stype.split(":");
									map.put(ss1[0], ss1[1]);

								}//if(stype!=null&&!stype.contains(","))												
							}
						}//if(condition2.contains("#"))
					}//if(condition2!=null)
				}//for(String condition2:condition1)
			}//if(condition.contains("--")&&condition!=null)
			else{
				if(!condition.contains("--")&&condition!=null){//condition里面没有--，即只要一个不等式,且不为空
					if(condition.contains("#")){////////////////////////////////////////////////#区分不等式和参数类型
						String[] s = condition.replace(" ", "").split("#");//#左边为不等式，#右边为不等式中参数的类型
						//String sbds = s[0].replace("&&", ",").replace("||", ",").replace(" ", "").replace("(", "").replace(")", "");//存放in中的不等式
						String stype = s[1];   //存放condition中参数和对应的类型
						if(stype!=null&&stype.contains(",")){//不止一个参数
							String[] contype = stype.split(",");
							for(String tt1:contype){//tt1为一个参数对应一个类型
								String[] ss1 = tt1.split(":");
								map.put(ss1[0], ss1[1]);
							}																				
						}//if(stype!=null&&stype.contains(","))
						else{
							if(stype!=null&&!stype.contains(",")){//  只有一个参数
								String[] ss1 = stype.split(":");
								map.put(ss1[0], ss1[1]);																
							}//if(stype!=null&&!stype.contains(","))												
						}
					}//if(condition.contains("#"))
					else{
						if(!condition.contains("#")){ //cycle
							return null;
						}
					}

				}
			}
			return map;
		}
	}



	/**
	 * 取#后不等式里面的参数与类型对应的map
	 * @param tran
	 * @return
	 */
	public static Map<String,String> getMap(String stype){
		Map<String,String> map = new HashMap<String,String>();//映射参数和类型之间关系
		if(stype.equals("null")){  //in里面为空
			return null;
		}
		else{
			if(stype!=null){ //传过来的stype里面不为空
				if(stype!=null&&stype.contains(",")){///////////////////////////////不止一个参数
					String[] intype = stype.split(",");
					for(String tt1:intype){//tt1为一个参数对应一个类型
						String[] ss1 = tt1.split(":");
						map.put(ss1[0], ss1[1]);
					}

				}//if(stype!=null&&stype.contains(","))
				else{
					if(stype!=null&&!stype.contains(",")){//  只有一个参数与对应的类型
						String[] ss1 = stype.split(":");
						map.put(ss1[0], ss1[1]);

					}//if(stype!=null&&!stype.contains(","))												
				}

			}//if(ins!=null)
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



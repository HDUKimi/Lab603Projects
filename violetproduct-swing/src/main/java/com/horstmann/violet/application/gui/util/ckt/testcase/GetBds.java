package com.horstmann.violet.application.gui.util.ckt.testcase;
//////处理in out condition里面的不等式，进行分类处理
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class GetBds {


	/**
	 * 最终的取数值不等式，用于mma
	 * @param tran
	 * @return
	 */
	public static String get_bds(String condition){
		Map<String,String> map1 = new HashMap<String,String>();//接收 映射参数和类型之间关系的map
		List<String> list = new ArrayList<String>();//搜集数值型不等式
		//List<String> list1 = new ArrayList<String>();//搜集==0情况的不等式
		Queue<String> queue = new LinkedList<String>();//拆成一个一个的，存放不等式
		String s1=new String();
		String ss=null;
		int n = 0;//用来标志是否属于！（……）这种格式的不等式
		if(condition==null){
			return null;
		}
		else{
			if(condition!=null && condition.length()>0){				
				if(condition.contains("--")){//处理condition里多个条件
					String[] condition1 = condition.split("--");
					for(String condition2:condition1){
						if(condition2.contains("!(")){
							n = 1;
						}
						if(condition2!=null && !condition2.contains("cycle")){//cycle不处理，处理不带cycle的
							if(condition2.contains("#")){//--与--之间 含有# 说明有不等式和参数
								String[] s = condition2.split("#");
								String sbds = s[0]; //存放未处理过的不等式
								String scs = s[1];  //存放参数 与对应的参数类型
								map1 = GetMap.getMap(scs);//map1里面存放--与--之间参数与参数类型的对应关系
								String bds = sbds.replace("&&", ",").replace("||", ",").replace("(", "").replace(")", "");
								if(bds.contains(",")){//--与--之中又拆 有多个不等式
									String[] ssbds = bds.split(",");
									for(int i=0;i<ssbds.length;i++){
										if(n==1&&i>0){//为每一个不等式前加！
											String bb = "!"+ssbds[i];
											ssbds[i] = bb;
										}
										queue.add(ssbds[i]);
									}
									String a=queue.poll();
									while(a!=null){
										if((a.contains("<")||a.contains(">")||a.contains("="))&&(!a.contains("True"))&&(!a.contains("False"))&&(!a.contains("true"))&&(!a.contains("false"))){
											if(a.contains("=")&&!a.contains("<")&&!a.contains(">")&&!a.contains("==")&&!a.contains("!=")){
												a = a.replace("=", "==");
											}
											list.add(a); //只把数值型的不等式存入list中
										}
										a = queue.poll();
									}

								}
								else{
									if(!(bds.contains(","))){//--之中只有一个不等式	
										if((bds.contains("<")||bds.contains(">")||bds.contains("="))&&(!bds.contains("True"))&&(!bds.contains("False"))&&(!bds.contains("true"))&&(!bds.contains("false"))){
											if(bds.contains("=")&&!bds.contains("<")&&!bds.contains(">")&&!bds.contains("==")&&!bds.contains("!=")){
												bds = bds.replace("=", "==");
											}
											list.add(bds); //只把数值型的不等式存入list中
										}
									}										
								}

							}//if(condition2.contains("#"))

						}//if(condition2!=null)
						n = 0;
					}//for(String condition2:condition1)

				}//if(condition.contains("--"))
				else{
					if(!(condition.contains("--"))){//处理condition里只有一个条件的情况和in里面的情况
						if(condition!=null && !condition.contains("cycle")){//cycle不处理，处理不带cycle的
							if(condition.contains("!(")){
								n = 1;
							}
							if(condition.contains("#")){//--与--之间 含有# 说明有不等式和参数
								String[] s = condition.split("#");
								String sbds = null;
								if(n==1){
									sbds = "!"+s[0]; //存放未处理过的不等式
								}else{
									sbds = s[0]; //存放未处理过的不等式
								}
								
								//String sbds = s[0]; //存放未处理过的不等式
								String scs = s[1];  //存放参数 与对应的参数类型
								map1 = GetMap.getMap(scs);//map1里面存放--与--之间参数与参数类型的对应关系
								String bds = sbds.replace("&&", ",").replace("||", ",").replace("(", "").replace(")", "");
								if(bds.contains(",")){//--与--之中又拆 有多个不等式
									String[] ssbds = bds.split(",");
									for(int i=0;i<ssbds.length;i++){
										queue.add(ssbds[i]);
									}
									String a=queue.poll();
									while(a!=null){
										if((a.contains("<")||a.contains(">")||a.contains("="))&&(!a.contains("True"))&&(!a.contains("False"))&&(!a.contains("true"))&&(!a.contains("false"))){
											if(a.contains("=")&&!a.contains("<")&&!a.contains(">")&&!a.contains("==")&&!a.contains("!=")){
												a = a.replace("=", "==");
											}
											list.add(a); //只把数值型的不等式存入list中
										}
										a = queue.poll();
									}

								}
								else{
									if(!(bds.contains(","))){//--之中只有一个不等式	
										if((bds.contains("<")||bds.contains(">")||bds.contains("="))&&(!bds.contains("True"))&&(!bds.contains("False"))&&(!bds.contains("true"))&&(!bds.contains("false"))){
											if(bds.contains("=")&&!bds.contains("<")&&!bds.contains(">")&&!bds.contains("==")&&!bds.contains("!=")){
												bds = bds.replace("=", "==");
											}
											list.add(bds); //只把数值型的不等式存入list中
										}
									}										
								}

							}//if(condition2.contains("#"))

						}//if(condition2!=null)


					}//if(!(tran.contains("--")))
				}//else
			}//if(tran!=null)
			if(list.size()<=0){
				return null;
			}
			else{
				if(list.size()==1){
//					if(!(list.get(0).contains("==0"))){
						ss=list.get(0);
//					}	
				}	
				if(list.size()>1){
//					if(!list.get(0).contains("==0")){
						ss=list.get(0);
//					}
					for(int j=1;j<list.size();j++){
						s1=list.get(j);
//						if(!(s1.contains("==0"))&&(ss!=null)){
						if(ss!=null){
							ss=ss+","+s1;
						}
//						if(!(s1.contains("==0"))&&(ss==null)){
						if(ss==null){
							ss=s1;
						}
					}
				}
				
				
				if(ss!=null&&ss.contains(" ")){
					ss=ss.replace(" ", "");
				}

				return ss;	
			}
				
		}//else


	}



	/**
	 * 最终的取布尔不等式，用于mma
	 * @param tran
	 * @return
	 */
	public static String get_boolbds(String condition){
		//Map<String,String> map = new HashMap<String,String>();//接收 映射参数和类型之间关系的map
		Map<String,String> map1 = new HashMap<String,String>();
		List<String> list = new ArrayList<String>();//搜索数值型不等式
		List<String> list1 = new ArrayList<String>();//搜集==0情况的不等式
		Queue<String> queue = new LinkedList<String>();//拆成一个一个的，存放不等式
		String s1=new String();
		String ss=null;
		int n = 0;
		if(condition==null){
			return null;
		}
		else{
			if(condition!=null && condition.length()>0){
				if(condition.contains("--")){//处理condition里多个条件
					String[] condition1 = condition.split("--");
					for(String condition2:condition1){				
						if(condition2!=null && !condition2.contains("cycle")){//cycle不处理，处理不带cycle的
							if(condition2.contains("#")){//--与--之间 含有# 说明有不等式和参数
								String[] s = condition2.split("#");
								String sbds = s[0]; //存放未处理过的不等式
								String scs = s[1];  //存放参数 与对应的参数类型
								map1 = GetMap.getMap(scs);//map1里面存放--与--之间参数与参数类型的对应关系
								//Set<String> keyset =map1.keySet();
								//for(String key : keyset){
								//	map.put(key.trim(), map1.get(key));
								//}
								String bds = sbds.replace("&&", ",").replace("||", ",").replace("(", "").replace(")", "");
								if(bds.contains(",")){//--与--之中又拆 有多个不等式
									String[] ssbds = bds.split(",");
									for(int i=0;i<ssbds.length;i++){
										queue.add(ssbds[i]);
									}
									String a=queue.poll();
									while(a!=null){
										if(a.contains("<")||a.contains(">")||a.contains("=")){
											if(a.contains("=")&&!a.contains("<")&&!a.contains(">")&&!a.contains("==")&&!a.contains("!=")){
												a = a.replace("=", "==");
											}
											list.add(a); //只把数值型的不等式存入list中
										}
										a = queue.poll();
									}


								}
								else{
									if(!(bds.contains(","))){//--之中只有一个不等式	
										if(bds.contains("<")||bds.contains(">")||bds.contains("=")){
											if(bds.contains("=")&&!bds.contains("<")&&!bds.contains(">")&&!bds.contains("==")&&!bds.contains("!=")){
												bds = bds.replace("=", "==");
											}
											list.add(bds); //只把数值型的不等式存入list中
										}
									}										
								}

							}//if(condition2.contains("#"))

						}//if(condition2!=null)
					}//for(String condition2:condition1)

				}//if(condition.contains("--"))
				else{
					if(!(condition.contains("--"))){//处理condition里只有一个条件的情况和in里面的情况
						if(condition!=null && !condition.contains("cycle")){//cycle不处理，处理不带cycle的
							if(condition.contains("#")){//--与--之间 含有# 说明有不等式和参数
								String[] s = condition.split("#");
								String sbds = s[0]; //存放未处理过的不等式
								String scs = s[1];  //存放参数 与对应的参数类型
								map1 = GetMap.getMap(scs);//map1里面存放--与--之间参数与参数类型的对应关系
								//Set<String> keyset =map1.keySet();
								//for(String key : keyset){
									//map.put(key.trim(), map1.get(key));
								//}
								String bds = sbds.replace("&&", ",").replace("||", ",").replace("(", "").replace(")", "");
								if(bds.contains(",")){//--与--之中又拆 有多个不等式
									String[] ssbds = bds.split(",");
									for(int i=0;i<ssbds.length;i++){
										queue.add(ssbds[i]);
									}
									String a=queue.poll();
									while(a!=null){
										if(a.contains("<")||a.contains(">")||a.contains("=")){
											if(a.contains("=")&&!a.contains("<")&&!a.contains(">")&&!a.contains("==")&&!a.contains("!=")){
												a = a.replace("=", "==");
											}
											list.add(a); //只把数值型的不等式存入list中
										}
										a = queue.poll();
									}

								}
								else{
									if(!(bds.contains(","))){//--之中只有一个不等式	
										if(bds.contains("<")||bds.contains(">")||bds.contains("=")){
											if(bds.contains("=")&&!bds.contains("<")&&!bds.contains(">")&&!bds.contains("==")&&!bds.contains("!=")){
												bds = bds.replace("=", "==");
											}
											list.add(bds); //只把数值型的不等式存入list中
										}
									}										
								}

							}//if(condition2.contains("#"))

						}//if(condition2!=null)


					}//if(!(tran.contains("--")))
				}//else
			}//if(tran!=null)
			if(list.size()>0){
				for(int j=0;j<list.size();j++){
					if(list.get(j).contains("True")||list.get(j).contains("False")||list.get(j).contains("true")||list.get(j).contains("false")){
						list1.add(list.get(j));
					}
				}
			}
			if(list1.size()>1){
				ss=list1.get(0);
				for(int j=1;j<list1.size();j++){
					s1=list1.get(j);
					ss=ss+","+s1;
				}
			}
			if(list1.size()==1){
				ss=list1.get(0);
			}	
			if(list1.size()<=0){
				return null;
			}
			if(ss!=null&&ss.contains(" ")){
				ss=ss.replace(" ", "");
			}

			ss = ss.replace("==", "=");
			return ss;		
		}//else


	}

	
	
		
	/**
	 * 最终的取==0不等式，用于mma
	 * @param tran
	 * @return
	 */
	public static String get_bds_0(String condition){
		Map<String,String> map1 = new HashMap<String,String>();//接收 映射参数和类型之间关系的map
		List<String> list = new ArrayList<String>();//搜集数值型不等式
		List<String> list1 = new ArrayList<String>();//搜集==0情况的不等式
		Queue<String> queue = new LinkedList<String>();//拆成一个一个的，存放不等式
		String s1=new String();
		String ss=null;
		if(condition==null){
			return null;
		}
		else{
			if(condition!=null && condition.length()>0){
				if(condition.contains("--")){//处理condition里多个条件
					String[] condition1 = condition.split("--");
					for(String condition2:condition1){
						if(condition2!=null && !condition2.contains("cycle")){//cycle不处理，处理不带cycle的
							if(condition2.contains("#")){//--与--之间 含有# 说明有不等式和参数
								String[] s = condition2.split("#");
								String sbds = s[0]; //存放未处理过的不等式
								String scs = s[1];  //存放参数 与对应的参数类型
								map1 = GetMap.getMap(scs);//map1里面存放--与--之间参数与参数类型的对应关系
								String bds = sbds.replace("&&", ",").replace("||", ",").replace("(", "").replace(")", "");
								if(bds.contains(",")){//--与--之中又拆 有多个不等式
									String[] ssbds = bds.split(",");
									for(int i=0;i<ssbds.length;i++){
										queue.add(ssbds[i]);
									}
									String a=queue.poll();
									while(a!=null){
										if(a.contains("<")||a.contains(">")||a.contains("=")){
											if(a.contains("=")&&!a.contains("<")&&!a.contains(">")&&!a.contains("==")&&!a.contains("!=")){
												a = a.replace("=", "==");
											}
											list.add(a); //只把数值型的不等式存入list中
										}
										a = queue.poll();
									}


								}
								else{
									if(!(bds.contains(","))){//--之中只有一个不等式	
										if(bds.contains("<")||bds.contains(">")||bds.contains("=")){
											if(bds.contains("=")&&!bds.contains("<")&&!bds.contains(">")&&!bds.contains("==")&&!bds.contains("!=")){
												bds = bds.replace("=", "==");
											}
											list.add(bds); //只把数值型的不等式存入list中
										}
									}										
								}

							}//if(condition2.contains("#"))

						}//if(condition2!=null)
					}//for(String condition2:condition1)

				}//if(condition.contains("--"))
				else{
					if(!(condition.contains("--"))){//处理condition里只有一个条件的情况和in里面的情况
						if(condition!=null && !condition.contains("cycle")){//cycle不处理，处理不带cycle的
							if(condition.contains("#")){//--与--之间 含有# 说明有不等式和参数
								String[] s = condition.split("#");
								String sbds = s[0]; //存放未处理过的不等式
								String scs = s[1];  //存放参数 与对应的参数类型
								map1 = GetMap.getMap(scs);//map1里面存放--与--之间参数与参数类型的对应关系
								String bds = sbds.replace("&&", ",").replace("||", ",").replace("(", "").replace(")", "");
								if(bds.contains(",")){//--与--之中又拆 有多个不等式
									String[] ssbds = bds.split(",");
									for(int i=0;i<ssbds.length;i++){
										queue.add(ssbds[i]);
									}
									String a=queue.poll();
									while(a!=null){
										if(a.contains("<")||a.contains(">")||a.contains("=")){
											if(a.contains("=")&&!a.contains("<")&&!a.contains(">")&&!a.contains("==")&&!a.contains("!=")){
												a = a.replace("=", "==");
											}
											list.add(a); //只把数值型的不等式存入list中
										}
										a = queue.poll();
									}

								}
								else{
									if(!(bds.contains(","))){//--之中只有一个不等式	
										if(bds.contains("<")||bds.contains(">")||bds.contains("=")){
											if(bds.contains("=")&&!bds.contains("<")&&!bds.contains(">")&&!bds.contains("==")&&!bds.contains("!=")){
												bds = bds.replace("=", "==");
											}
											list.add(bds); //只把数值型的不等式存入list中
										}
									}										
								}

							}//if(condition2.contains("#"))

						}//if(condition2!=null)


					}//if(!(tran.contains("--")))
				}//else
			}//if(tran!=null)
			if(list.size()>0){
				for(int j=0;j<list.size();j++){
					if(list.get(j).contains("==0")){
						list1.add(list.get(j));
					}
				}
			}
			if(list1.size()>1){
				ss=list1.get(0);
				for(int j=1;j<list1.size();j++){
					s1=list1.get(j);
					ss=ss+","+s1;
				}
			}
			if(list1.size()==1){
				ss=list1.get(0);
			}	
			if(list1.size()<=0){
				return null;
			}

			//ss=ss.replace(" ", "").replace("==", "=");
			return ss;		
		}//else


	}

	
	
	
	
}


































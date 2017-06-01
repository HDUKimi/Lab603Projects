package com.horstmann.violet.application.gui.util.ckt.testcase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultOfTime {
	public static void main(String[] args) {
		//String a = "State==0 && Floor==1,-1<=State<=1,0<=Floor<=4#State:int,Floor:int";
		String a = "!(elevator_thisFloorOut == true)#elevator_thisFloorOut:bool--!(getCurrentFloor_empty == false)#getCurrentFloor_empty:bool";
		List<String> list1 = new ArrayList<String>();
		list1 = getResultOfTime(a);
		System.out.println(list1.toString());

	}

	
	
	/**
	 * 处理时间里的条件，得到多组解
	 * @param in or condition
	 * @return
	 */
	public static List<String> getResultOfTime(String condition){
		condition = condition.replace("false", "False").replace("true", "True");
		//int flag = 0;
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
		if(GetMap.get_condMap(condition)==null){
			//System.out.println("keySet集合3："+GetMap.get_condMap(condition));
			return null;
		}else{
			if(!(GetMap.get_condMap(condition)==null)){
				map1 = GetMap.get_condMap(condition);//必须有，要不结果错误
				//System.out.println("==================================");
				//Set<String> set =map1.keySet();

				String cs1 = AddBdsType.getcs(map1);
				String cs2 = AddBdsType.getDoubleCs(map1);
				String cs3 = AddBdsType.getBoolCs(map1);			
				String s1 = AddBdsType.add_bds(map1);
				String s20 = AddBdsType.add_doublebds(map1);
				String bds2=null;
				String bds00=null;

				//数字型不等式和参数
				String bds1=GetBds.get_bds(condition.toString());

				//System.out.println("bds1:"+bds1);

				/////////////////输出
				/*System.out.println("=================================");
				System.out.println("整数------>不等式，即："+bds1);  //condition上数字不等式				
				System.out.println("整数------>参数，即："+cs1);
				System.out.println("小数=0---->不等式，即："+bds00);
				System.out.println("小数------>不等式，即："+bds2);
				System.out.println("小数------>参数前，即："+cs2);
				System.out.println("add------>整数不等式为："+s1);
				System.out.println("add------>小数不等式前为："+s20);
				System.out.println("=================================");*/

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

						//System.out.println("小数不等式为----->"+css);

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
							csss = csss.replace("==", "=");
						}
						if(list2.size()==1){
							csss=list2.get(0);
							list3.add(list2.get(0).replace("==0", ""));
							csss = csss.replace("==", "=");
						}	
						/*if(csss.contains("==")){
							bds00=csss.replace("==", "=");	
						}*/
						bds00=csss;

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
				/*System.out.println("=================================");
				System.out.println("整数------>不等式，即："+bds1);  //condition上数字不等式			
				System.out.println("整数------>参数，即："+cs1);
				System.out.println("小数=0---->不等式，即："+bds00);
				System.out.println("小数------>不等式，即："+bds2);
				System.out.println("小数------>参数后，即："+cs2);
				System.out.println("add------>整数不等式为："+s1);
				System.out.println("add------>小数不等式后为："+s2);
				System.out.println("=================================");*/


				//布尔型不等式和参数
				String boolbds=GetBds.get_boolbds(condition.toString());
				if(cs3!=null&&boolbds==null){
					boolbds = AddBdsType.add_boolbds(cs3);
				}
				//System.out.println("布尔型------>不等式，即："+boolbds);//condition上布尔不等式
				//System.out.println("小数=0---->不等式，即："+bds00);
				//			System.out.println("布尔型------>参数，即："+cs3);


				/////////////////////////////////////////
				//调用mma软件求解方程组
				String[] results = null;
				String[] results1 = null;
				//String[] ttt3 = null;
				if(((bds1==null)&&(cs1==null))&&(s2==null)){
					//System.out.println("        ===>  condition上没有约束即为：null");
					//input.setText("null");
				}
				if((bds1!=null)&&(cs1!=null)){
					/*if(Border.getBorder(bds1)!=null){
						bds1 = bds1+","+Border.getBorder(bds1);//边界等式加入
						flag = 1;
					}	*/
					String bbb = bds1+","+s1;
					//System.out.println("        ===>  condition上整数型数值不等式："+bbb);
					//										System.out.println("        ===>  condition上整数型数值参数："+cs1);
					//System.out.println("整数不等式:"+bbb);
					//System.out.println("整数数参数："+cs1);
					String solution1 = Mathematica.getSolution2(bbb, cs1);					
					if(solution1.equals("{}")){
						System.out.println("原求得矛盾空解"+solution1);
						System.out.println("原求得矛盾不等式"+bds1);
						//bbb = Remove11(bds1);
						/*if(Border.getBorder(bds1)!=null){
							bds1 = Remove11(bds1)+","+Border.getBorder(bds1);//边界等式加入
							flag = 1;
						}*/	
						bbb = Remove11(bds1)+","+s1;


						solution1 = Mathematica.getSolution2(bbb, cs1);
						System.out.println("删除矛盾后不等式"+bbb);
						System.out.println("删除矛盾后解"+solution1);
					}
					/*if(solution1.contains("ndInstance")){
						System.out.println("----------------------求解异常----------------------");
						System.out.println("----------------------求解异常----------------------");
						System.out.println("----------------------求解异常----------------------");
						System.out.println("----------------------求解异常----------------------");
						System.out.println("----------------------求解异常----------------------");
					}*/
					ttt=solution1.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
					//ttt=bbb.toString();
					//					
					System.out.println("整数型解"+solution1);
					results = solution1.substring(2, solution1.length() - 2).split("\\}, \\{");

					//System.out.println("condition整数型约束解为："+solution1);
				}
				else{
					if(s1!=null){
						//System.out.println("        ===>  condition上整数型数值不等式："+s1);
						//System.out.println("        ===>  condition上整数型数值参数："+cs1);
						//System.out.println("整数不等式:"+s1);
						//System.out.println("整数数参数："+cs1);
						String solution1 = Mathematica.getSolution2(s1, cs1);

						if(solution1.contains("ndInstance")){
							System.out.println("----------------------求解异常----------------------");
							System.out.println("----------------------求解异常----------------------");
							System.out.println("----------------------求解异常----------------------");
							System.out.println("----------------------求解异常----------------------");
							System.out.println("----------------------求解异常----------------------");
						}

						ttt=solution1.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
						//ttt=s1.toString();
						//						
						results = solution1.substring(2, solution1.length() - 2).split("\\}, \\{");

						//System.out.println("condition上整数型约束解为："+solution1);
					}
				}
				if((bds2!=null)&&(s2!=null)){
					//System.out.println("condition上小数型数值不等式："+s2);
					//System.out.println("condition上小数型数值参数："+cs2);
					System.out.println("小数不等式："+bds2);
					/*if(Border.getDoubleBorder(bds2)!=null){
						bds2 = bds2+","+Border.getDoubleBorder(bds2);
						flag =1;
					}*/	
					System.out.println("加入边界后小数不等式："+bds2);
					String bb = bds2+","+s2;
					//System.out.println("小数不等式:"+bb);
					//System.out.println("小数参数："+cs2);
					String solution2 = Mathematica.getSolution4(bb, cs2);
					if(solution2.equals("{}")){
						//bb = Remove11(bb);
						/*if(Border.getDoubleBorder(bds2)!=null){
							bds2 = Remove11(bds2)+","+Border.getDoubleBorder(bds2);
							flag = 1;
						}*/	
						bb = Remove11(bds2)+","+s2;
						solution2 = Mathematica.getSolution4(bb, cs2);
					}

					if(solution2.contains("ndInstance")){
						System.out.println("----------------------求解异常----------------------");
						System.out.println("----------------------求解异常----------------------");
						System.out.println("----------------------求解异常----------------------");
						System.out.println("----------------------求解异常----------------------");
						System.out.println("----------------------求解异常----------------------");
					}

					ttt1=solution2.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
					//ttt1=s2.toString();
					//					
					results1 = solution2.substring(2, solution2.length() - 2).split("\\}, \\{");

					//System.out.println("condition上小数型约束解为："+solution2);
				}else{
					if((s2!=null)){
						//System.out.println("小数不等式:"+s2);
						//System.out.println("小数参数："+cs2);
						String solution2 = Mathematica.getSolution4(s2, cs2);
						if(solution2.equals("{}")){
							s2 = Remove11(s2);
							solution2 = Mathematica.getSolution4(s2, cs2);
						}


						if(solution2.contains("ndInstance")){
							System.out.println("----------------------求解异常----------------------");
							System.out.println("----------------------求解异常----------------------");
							System.out.println("----------------------求解异常----------------------");
							System.out.println("----------------------求解异常----------------------");
							System.out.println("----------------------求解异常----------------------");
						}

						ttt1=solution2.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
						//						
						results1 = solution2.substring(2, solution2.length() - 2).split("\\}, \\{");

						//System.out.println("condition上小数型约束解为："+solution2);
					}
				}

				//System.out.println("condition布尔型值解为："+boolbds);//condition上布尔不等式	
				if(boolbds!=null){
					//System.out.println("        ===>  condition上布尔型的不等式："+boolbds);
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
					//System.out.println("list中解:"+tt3.get(i));
				}
				if(bds00!=null){
					//System.out.println("bds00:"+bds00);
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

			}	
			return result;
			/*if((flag==1)&&!(result.toString().equals("[null]"))){
				List<String> results = new ArrayList<String>();
				for(int i=0;i<result.size();i++){
					results.add("flag=1"+result.get(i).replace("{", "").replace("}", ""));
				}
				return results;
			}else{
				return result;
			}*/

		}

	
	}
	
	/**
	 * 利用软件mathematica求解为空，即不等式中有矛盾，两两对比
	 * 移出矛盾的不等式，取后一个不等式
	 */
	public static String Remove11(String bbb){
		System.out.println("原处理不等式："+bbb);
		String cs1 = null;
		String cs2 = null;
		String bds[] = bbb.replace("||", ",").split(",");

		for(int i=0;i<bds.length-1;i++){
			for(int j=i+1;j<bds.length;j++){
				//System.out.println(bds[i]+"------"+bds[j]);
				//System.out.println(getNumber(bds[i]));
				//System.out.println(getNumber(bds[j]));
				if(getNumber(bds[i]) && getNumber(bds[j])){					
					String[] css = bds[i].trim().split("[-!+<>=]=?");
					for(String cs:css){						
						if(!cs.equals("")){
							int s1=cs.trim().substring(0,1).toCharArray()[0];
							if(!((s1>=48&&s1<=57)||s1==45)){//第一个为参数
								cs1 = cs;
								//System.out.println("第一个不等式参数："+cs);
							}
						}				
					}
					String[] css1 = bds[j].trim().split("[-!+<>=]=?");
					for(String cs:css1){

						if(!cs.equals("")){
							int s1=cs.trim().substring(0,1).toCharArray()[0];
							if(!((s1>=48&&s1<=57)||s1==45)){//第一个为参数
								cs2 = cs;
								//System.out.println("第二个不等式参数："+cs);
							}
						}				
					}
					if(cs1.equals(cs2)){
						String bds1 = bds[i]+","+bds[j];
						String cs = cs1;
						//if()
						String solution = Mathematica.getSolution2(bds1, cs);
						//System.out.println("solution---"+solution);
						if(solution.equals("{}")){
							bbb=bbb.replaceAll(bds[i]+",", "");								
						}						
					}					 
				}
			}
		}
		System.out.println("处理后的不等式"+bbb);
		return bbb;
	}

	/**
	 * 判断每个不等式里参数是否只有一个
	 * @param domain1
	 * @return
	 */
	public static boolean getNumber(String bds) {

		int number=0;
		String[] css = bds.trim().split("[!-+<>=]=?");
		for(String cs:css){
			if(!cs.equals("")){
				int s1=cs.trim().substring(0,1).toCharArray()[0];
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

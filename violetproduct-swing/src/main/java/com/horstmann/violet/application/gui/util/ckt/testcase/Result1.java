package com.horstmann.violet.application.gui.util.ckt.testcase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ����:�����������õ�����mathematica�⣬���ص��Ƕ����,border ��ӱ߽��ĵ�ʽ����Ľ��
 * 
 * @author ckt
 *
 */
public class Result1 {
	/**
	 * �����������õ������
	 * @param condition
	 * @return
	 */
	public static List<String> getResult(String condition){ 
		int flag = 0;
		List<String> list=new ArrayList<String>();//�����������ʽ
		List<String> list1=new ArrayList<String>();//���С������ʽ
		List<String> list2=new ArrayList<String>();//���=0С������ʽ
		List<String> list3=new ArrayList<String>();//���=0С������ʽ�Ĳ���
		Map<String,String> map = new HashMap<String,String>();
		Map<String,String> map1 = new HashMap<String,String>();
		Map<String,String> map3 = new HashMap<String,String>();
		String ttt=null;
		String ttt1=null;
		String ttt2=null;
		//String ttt3[]=null;
		List<String> result=new ArrayList<String>();//�������ʵ�������
		List<String> tt3=new ArrayList<String>();//��ų���=0��С������ʽ���ʵ�������
		//System.out.println("condition----->"+condition);
		//System.out.println("keySet����3��"+GetMap.get_condMap(condition));
		if(GetMap.get_condMap(condition)==null){
			//System.out.println("keySet����3��"+GetMap.get_condMap(condition));
			return null;
		}else{
			if(!(GetMap.get_condMap(condition)==null)){
				map1 = GetMap.get_condMap(condition);//�����У�Ҫ���������
				//System.out.println("==================================");
				//Set<String> set =map1.keySet();

				String cs1 = AddBdsType.getcs(map1);
				String cs2 = AddBdsType.getDoubleCs(map1);
				String cs3 = AddBdsType.getBoolCs(map1);			
				String s1 = AddBdsType.add_bds(map1);
				String s20 = AddBdsType.add_doublebds(map1);
				String bds2=null;
				String bds00=null;

				//�����Ͳ���ʽ�Ͳ���
				String bds1=GetBds.get_bds(condition.toString());

				//System.out.println("bds1:"+bds1);

				/////////////////���
				/*System.out.println("=================================");
				System.out.println("����------>����ʽ������"+bds1);  //condition�����ֲ���ʽ				
				System.out.println("����------>����������"+cs1);
				System.out.println("С��=0---->����ʽ������"+bds00);
				System.out.println("С��------>����ʽ������"+bds2);
				System.out.println("С��------>����ǰ������"+cs2);
				System.out.println("add------>��������ʽΪ��"+s1);
				System.out.println("add------>С������ʽǰΪ��"+s20);
				System.out.println("=================================");*/

				///////////////////////////////////
				if(cs2!=null){  //cs2��С��------>����
					if(bds1!=null){
						if(bds1.contains(",")){
							String[] xbds = bds1.split(",");
							if(cs2.contains(",")){//�������ʽ�������
								String[] xcs = cs2.split(",");
								for(String x1:xbds){
									int m=0;
									for(String x2:xcs){													
										if(x1.contains(x2)){
											//list1.add(x1);
											m=1;
										}
									}
									////�ж��ǲ���С��������������ʽ����==0�����
									if(m==1&&!(x1.contains("==0"))){
										list1.add(x1);//list1�����ų���==0��С������ʽ
									}
									else{
										if(m==1&&(x1.contains("==0"))){
											list2.add(x1);//list2������==0��С������ʽ
										}
										if(m==0){
											list.add(x1);//list��������������ʽ
										}
									}

								}//for(String x1:xbds) 
							}//if(cs2.contains(","))
							else{
								//!cs2.contains(",")
								////�������ʽһ������
								for(String x1:xbds){
									int m=0;
									if(x1.contains(cs2)){
										m=1;
									}
									////�ж��ǲ���С��������������ʽ����==0�����
									if(m==1&&!(x1.contains("==0"))){
										list1.add(x1);//list1�����ų���==0��С������ʽ
									}
									else{
										if(m==1&&(x1.contains("==0"))){
											list2.add(x1);//list2������==0��С������ʽ
										}
										if(m==0){
											list.add(x1);//list�����ų���������ʽ
										}
									}
								}												
							}

						}//if(bds1.contains(","))
						else{
							if(!bds1.contains(",")){
								if(cs2.contains(",")){//һ������ʽ�������
									String[] xcs = cs2.split(",");
									int m=0;
									for(String x2:xcs){													
										if(bds1.contains(x2)){
											//list1.add(x1);
											m=1;
										}
									}
									////�ж��ǲ���С��������������ʽ
									if(m==1&&!(bds1.contains("==0"))){
										list1.add(bds1);//list1�����ų���==0��С������ʽ
									}
									else{
										if(m==1&&(bds1.contains("==0"))){
											list2.add(bds1);//list2������==0��С������ʽ													
										}
										if(m==0){
											list.add(bds1);//list�����ų���==0����������ʽ
										}
									}
								}//if(cs2.contains(","))
								else{
									//!cs2.contains(",")
									//һ������ʽһ������
									int m=0;
									if(bds1.contains(cs2)){
										m=1;
									}
									////�ж��ǲ���С��������������ʽ����==0�����
									if(m==1&&!(bds1.contains("==0"))){
										list1.add(bds1);//list1�����ų���==0��С������ʽ
									}
									else{
										if(m==1&&(bds1.contains("==0"))){
											list2.add(bds1);//list2������==0��С������ʽ													
										}
										if(m==0){
											list.add(bds1);//list�����ų���==0����������ʽ
										}
									}
								}
							}
						}

						//��������ʽ
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
						//System.out.println("��������ʽΪ----->"+cs);

						//��=0��С������ʽ
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

						//System.out.println("С������ʽΪ----->"+css);

						//=0��С������ʽ
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
							//System.out.println("��������ʽΪ----->"+null);
							//System.out.println("С������ʽΪ----->"+null);
						}										
					}


				}//if(cs2!=null)
				else{
					//û��С������
					//System.out.println("��������ʽΪ----->"+bds1);
					//System.out.println("С������ʽΪ----->"+null);

				}
				//////////////////////


				for(int i=0;i<list3.size();i++){
					//����С������
					if(cs2.contains(list3.get(i)+",")){
						cs2 = cs2.replace(list3.get(i)+",", "");
					}else{
						if(cs2.contains(list3.get(i))){
							cs2 = cs2.replace(list3.get(i), "");
						}						
					}
					//�������ӵ�С������ʽ
					map1.remove(list3.get(i));					
				}
				String s2 = AddBdsType.add_doublebds(map1);


				/////////////////���
				/*System.out.println("=================================");
				System.out.println("����------>����ʽ������"+bds1);  //condition�����ֲ���ʽ			
				System.out.println("����------>����������"+cs1);
				System.out.println("С��=0---->����ʽ������"+bds00);
				System.out.println("С��------>����ʽ������"+bds2);
				System.out.println("С��------>�����󣬼���"+cs2);
				System.out.println("add------>��������ʽΪ��"+s1);
				System.out.println("add------>С������ʽ��Ϊ��"+s2);
				System.out.println("=================================");*/


				//�����Ͳ���ʽ�Ͳ���
				String boolbds=GetBds.get_boolbds(condition.toString());
				if(cs3!=null&&boolbds==null){
					boolbds = AddBdsType.add_boolbds(cs3);
				}
				//System.out.println("������------>����ʽ������"+boolbds);//condition�ϲ�������ʽ
				//System.out.println("С��=0---->����ʽ������"+bds00);
				//			System.out.println("������------>����������"+cs3);


				/////////////////////////////////////////
				//����mma�����ⷽ����
				String[] results = null;
				String[] results1 = null;
				//String[] ttt3 = null;
				if(((bds1==null)&&(cs1==null))&&(s2==null)){
					//System.out.println("        ===>  condition��û��Լ����Ϊ��null");
					//input.setText("null");
				}
				if((bds1!=null)&&(cs1!=null)){
					if(Border.getBorder(bds1)!=null){
						bds1 = bds1+","+Border.getBorder(bds1);//�߽��ʽ����
						flag = 1;
					}	
					String bbb = bds1+","+s1;
					//System.out.println("        ===>  condition����������ֵ����ʽ��"+bbb);
					//										System.out.println("        ===>  condition����������ֵ������"+cs1);
					//System.out.println("��������ʽ:"+bbb);
					//System.out.println("������������"+cs1);
					String solution1 = Mathematica.getSolution2(bbb, cs1);					
					if(solution1.equals("{}")){
						System.out.println("ԭ���ì�ܿս�"+solution1);
						System.out.println("ԭ���ì�ܲ���ʽ"+bds1);
						//bbb = Remove11(bds1);
						if(Border.getBorder(bds1)!=null){
							bds1 = Remove11(bds1)+","+Border.getBorder(bds1);//�߽��ʽ����
							flag = 1;
						}	
						bbb = Remove11(bds1)+","+s1;


						solution1 = Mathematica.getSolution2(bbb, cs1);
						System.out.println("ɾ��ì�ܺ󲻵�ʽ"+bbb);
						System.out.println("ɾ��ì�ܺ��"+solution1);
					}
					/*if(solution1.contains("ndInstance")){
						System.out.println("----------------------����쳣----------------------");
						System.out.println("----------------------����쳣----------------------");
						System.out.println("----------------------����쳣----------------------");
						System.out.println("----------------------����쳣----------------------");
						System.out.println("----------------------����쳣----------------------");
					}*/
					ttt=solution1.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
					//ttt=bbb.toString();
					//					
					System.out.println("�����ͽ�"+solution1);
					results = solution1.substring(2, solution1.length() - 2).split("\\}, \\{");

					//System.out.println("condition������Լ����Ϊ��"+solution1);
				}
				else{
					if(s1!=null){
						//System.out.println("        ===>  condition����������ֵ����ʽ��"+s1);
						//System.out.println("        ===>  condition����������ֵ������"+cs1);
						//System.out.println("��������ʽ:"+s1);
						//System.out.println("������������"+cs1);
						String solution1 = Mathematica.getSolution2(s1, cs1);

						if(solution1.contains("ndInstance")){
							System.out.println("----------------------����쳣----------------------");
							System.out.println("----------------------����쳣----------------------");
							System.out.println("----------------------����쳣----------------------");
							System.out.println("----------------------����쳣----------------------");
							System.out.println("----------------------����쳣----------------------");
						}

						ttt=solution1.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
						//ttt=s1.toString();
						//						
						results = solution1.substring(2, solution1.length() - 2).split("\\}, \\{");

						//System.out.println("condition��������Լ����Ϊ��"+solution1);
					}
				}
				if((bds2!=null)&&(s2!=null)){
					//System.out.println("condition��С������ֵ����ʽ��"+s2);
					//System.out.println("condition��С������ֵ������"+cs2);
					System.out.println("С������ʽ��"+bds2);
					if(Border.getDoubleBorder(bds2)!=null){
						bds2 = bds2+","+Border.getDoubleBorder(bds2);
						flag =1;
					}	
					System.out.println("����߽��С������ʽ��"+bds2);
					String bb = bds2+","+s2;
					//System.out.println("С������ʽ:"+bb);
					//System.out.println("С��������"+cs2);
					String solution2 = Mathematica.getSolution4(bb, cs2);
					if(solution2.equals("{}")){
						//bb = Remove11(bb);
						if(Border.getDoubleBorder(bds2)!=null){
							bds2 = Remove11(bds2)+","+Border.getDoubleBorder(bds2);
							flag = 1;
						}	
						bb = Remove11(bds2)+","+s2;
						solution2 = Mathematica.getSolution4(bb, cs2);
					}

					if(solution2.contains("ndInstance")){
						System.out.println("----------------------����쳣----------------------");
						System.out.println("----------------------����쳣----------------------");
						System.out.println("----------------------����쳣----------------------");
						System.out.println("----------------------����쳣----------------------");
						System.out.println("----------------------����쳣----------------------");
					}

					ttt1=solution2.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
					//ttt1=s2.toString();
					//					
					results1 = solution2.substring(2, solution2.length() - 2).split("\\}, \\{");

					//System.out.println("condition��С����Լ����Ϊ��"+solution2);
				}else{
					if((s2!=null)){
						//System.out.println("С������ʽ:"+s2);
						//System.out.println("С��������"+cs2);
						String solution2 = Mathematica.getSolution4(s2, cs2);
						if(solution2.equals("{}")){
							s2 = Remove11(s2);
							solution2 = Mathematica.getSolution4(s2, cs2);
						}


						if(solution2.contains("ndInstance")){
							System.out.println("----------------------����쳣----------------------");
							System.out.println("----------------------����쳣----------------------");
							System.out.println("----------------------����쳣----------------------");
							System.out.println("----------------------����쳣----------------------");
							System.out.println("----------------------����쳣----------------------");
						}

						ttt1=solution2.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
						//						
						results1 = solution2.substring(2, solution2.length() - 2).split("\\}, \\{");

						//System.out.println("condition��С����Լ����Ϊ��"+solution2);
					}
				}

				//System.out.println("condition������ֵ��Ϊ��"+boolbds);//condition�ϲ�������ʽ	
				if(boolbds!=null){
					//System.out.println("        ===>  condition�ϲ����͵Ĳ���ʽ��"+boolbds);
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
					//System.out.println("condition�Ͻ�Ϊ��"+ttt3);
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
					//System.out.println("list�н�:"+tt3.get(i));
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
				/*for(int i=0;i<tt3.size();i++){
					System.out.println("��"+i+"Ϊ:"+tt3.get(i));
				}*/

				/*for(int i=0;i<result.size();i++){					
					System.out.println("��"+i+"Ϊ:"+result.get(i));
				}*/

				//////////////////////////////////////


			}	
			if((flag==1)&&!(result.toString().equals("[null]"))){
				List<String> results = new ArrayList<String>();
				for(int i=0;i<result.size();i++){					
					results.add("flag=1"+result.get(i));
				}
				return results;
			}else{
				return result;
			}

		}

	}


	/**
	 * �������mathematica���Ϊ�գ�������ʽ����ì�ܣ������Ա�
	 * �Ƴ�ì�ܵĲ���ʽ��ȡ��һ������ʽ
	 */
	public static String Remove11(String bbb){
		System.out.println("ԭ������ʽ��"+bbb);
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
							if(!((s1>=48&&s1<=57)||s1==45)){//��һ��Ϊ����
								cs1 = cs;
								//System.out.println("��һ������ʽ������"+cs);
							}
						}				
					}
					String[] css1 = bds[j].trim().split("[-!+<>=]=?");
					for(String cs:css1){

						if(!cs.equals("")){
							int s1=cs.trim().substring(0,1).toCharArray()[0];
							if(!((s1>=48&&s1<=57)||s1==45)){//��һ��Ϊ����
								cs2 = cs;
								//System.out.println("�ڶ�������ʽ������"+cs);
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
		System.out.println("�����Ĳ���ʽ"+bbb);
		return bbb;
	}

	/**
	 * �ж�ÿ������ʽ������Ƿ�ֻ��һ��
	 * @param domain1
	 * @return
	 */
	public static boolean getNumber(String bds) {

		int number=0;
		String[] css = bds.trim().split("[!-+<>=]=?");
		for(String cs:css){
			if(!cs.equals("")){
				int s1=cs.trim().substring(0,1).toCharArray()[0];
				if(!((s1>=48&&s1<=57)||s1==45)){//��һ��Ϊ����
					number++;
				}
			}				
		}
		if(number<=1){
			//System.out.println("һ������");
			return true;
		}else{
			//System.out.println("�������");
			return false;
		}

	}



	/**
	 * ���ܲ��ԣ��ض�������⣬��ʹ��������˹��������
	 * @param s
	 * @return
	 */
	public static List<String> preInResult(String s){
		List<String> high = new ArrayList<String>();//��Ÿ߶ȵĽ�
		List<String> speed = new ArrayList<String>();//��ŷ��ٵĽ�
		List<String> direction = new ArrayList<String>();//��ŷ���Ľ�
		List<String> otherResult = new ArrayList<String>();//��ų��˷��١����򡢸߶�֮��Ľ�
		List<String> result1=new ArrayList<String>();//���in��������ʵ�������
		if(s.contains("--")){
			List<List<String>> in_result = new ArrayList<List<String>>();
			//List<String> in_result1;
			String getin[] = s.split("--");
			for(int ii=0;ii<getin.length;ii++){
				if(!(GetMap.get_inMap(getin[ii])==null)){
					String inn = getin[ii].replace("false", "False").replace("true", "True").replace("->", "$");
					String[] in = inn.split("#")[0].split(",");							
					for(String s1:in){								
						if(s1.contains("takeoff_alt_cm")){
							//�߶�,1000���
							//high = Result1.PerformanceResult(s1,1000);
							high = Result1.In_Result(s1, 10);
							if((high.size()>0)&&!(high.get(0).equals(null))){
								in_result.add(high);
							}
							
							
						}else{									
							if(s1.contains("_sitl.wind_speed")){
								//���٣�10���
								//speed = Result1.PerformanceResult(s1,20);
								speed = Result1.In_Result(s1, 10);
								if((speed.size()>0)&&!(speed.get(0).equals(null))){
									in_result.add(speed);
								}
							}else{
								if(s1.contains("_sitl.wind_direction")){
									//����10���
									//direction = Result1.PerformanceResult(s1,60);
									direction = Result1.In_Result(s1, 10);
									if((direction.size()>0)&&!(direction.get(0).equals(null))){
										in_result.add(direction);
									}
								}else{
									if(!(inn.contains("takeoff_alt_cm"))&&!(inn.contains("_sitl.wind_speed"))&&!(inn.contains("_sitl.wind_direction"))){
										otherResult = Result1.getResult(inn);
										if((otherResult.size()>0)&&!(otherResult.get(0).equals(null))){
											in_result.add(otherResult);
										}	
									}												
								}
							}																		
						}																
					}																					
				}
			}
			if((in_result.size()>0)&&!(in_result.get(0).equals(null))){							
				System.out.println("in_result.size()--->"+in_result.size());
				dis(0,in_result);
				result1 = re;
				List<String> ret = new ArrayList<String>();
				re = ret;
			}
		}else{
			List<List<String>> inResult = new ArrayList<List<String>>();
			if(!(GetMap.get_inMap(s)==null)){//map���治Ϊ�գ��в���
				/*List<String> high = new ArrayList<String>();
					List<String> speed = new ArrayList<String>();
					List<String> direction = new ArrayList<String>();
					List<String> otherResult = new ArrayList<String>();*/
				String inn = s.replace("false", "False").replace("true", "True").replace("->", "$");
				String[] in = inn.split("#")[0].split(",");							
				for(String s1:in){								
					if(s1.contains("takeoff_alt_cm")){
						//�߶�,10���
						high = Result1.PerformanceResult(s1,100);
						if((high.size()>0)&&!(high.get(0).equals(null))){
							inResult.add(high);
						}
					}else{									
						if(s1.contains("_sitl.wind_speed")){
							//���٣�10���
							speed = Result1.PerformanceResult(s1,20);
							if((speed.size()>0)&&!(speed.get(0).equals(null))){
								inResult.add(speed);
							}
						}else{
							if(s1.contains("_sitl.wind_direction")){
								//����10���
								direction = Result1.PerformanceResult(s1,60);
								if((direction.size()>0)&&!(direction.get(0).equals(null))){
									inResult.add(direction);
								}
							}else{
								if(!(inn.contains("takeoff_alt_cm"))&&!(inn.contains("_sitl.wind_speed"))&&!(inn.contains("_sitl.wind_direction"))){
									otherResult = Result1.getResult(inn);
									if((otherResult.size()>0)&&!(otherResult.get(0).equals(null))){
										inResult.add(otherResult);
									}
								}									
							}
						}																		
					}																
				}
				if((inResult.size()>0)&&!(inResult.get(0).equals(null))){
					System.out.println("in_result.size()--->"+inResult.size());				
					dis(0,inResult);
					result1 = re;
					List<String> ret = new ArrayList<String>();
					re = ret;
				}
			}else{
				if((GetMap.get_inMap(s)==null)){//map����Ϊ�գ���û�в���
					result1.add(null);
				}
			}
		}
		return result1;
		/*if(result1.equals(null)){
			return null;
		}else  return result1;*/
	}
	
	
	
	
	/**
	 * ���ܲ��ԣ��߶ȡ����١�����������
	 * @param domain  ��Լ���Ĳ���ʽ���ض������߶ȡ����١�����
	 * @param n  ���ؽ�ĸ���
	 * @return
	 */
	public static List<String> PerformanceResult(String domain,int n){
		List<String> result = new ArrayList<String>();
		String cs = null;
		if (domain.contains("<=")){
			String[] strs = domain.split("<=");				
			System.out.println("�账����<=����"+domain);
			if (strs.length == 3) {	
				//System.out.println("����1<=x<=7");//
				cs = strs[1];
				float x = Float.parseFloat(strs[2])-Float.parseFloat(strs[0]);
				float value = x/(n-1);   //��ÿ�ε�����ֵ��n��Ҫ����Ĳ�����������			
				result.add(cs+"="+strs[0]);
				for(int i=1;i<=n-2;i++){//���߽绹��Ҫ�����ĸ���
					float y = Float.parseFloat(strs[0])+value*i;
					result.add(cs+"="+y);				
				}
				result.add(cs+"="+strs[2]);				
			} else{ 
				if (strs[0].contains("<")||strs[1].contains("<")) {
					//System.out.println("     ����1<x<=7��1<=x<7");//
					if (strs[1].contains("<")) {  //1<=x<7
						//System.out.println("     ����1<=x<7");//
						cs = strs[1].split("<")[0];
						float x = Float.parseFloat(strs[1].split("<")[1])-Float.parseFloat(strs[0]);
						float value = x/n;   //��ÿ�ε�����ֵ��n��Ҫ����Ĳ�����������
						result.add(cs+"="+strs[0]);
						for(int i=1;i<=n-1;i++){ //���߽绹��Ҫ�����ĸ���
							float y = Float.parseFloat(strs[0])+value*i;
							result.add(cs+"="+y);				
						}
					} else {
						if (strs[0].contains("<")) {
							//System.out.println("     ����1<x<=7");//
							cs = strs[0].split("<")[1];
							float x = Float.parseFloat(strs[1])-Float.parseFloat(strs[0].split("<")[0]);
							float value = x/n;   //��ÿ�ε�����ֵ��n��Ҫ����Ĳ�����������
							for(int i=1;i<=n-1;i++){ //���߽绹��Ҫ�����ĸ���
								float y = Float.parseFloat(strs[0].split("<")[0])+value*i;
								result.add(cs+"="+y);				
							}
							result.add(cs+"="+strs[1]);
						}						
					}
				} else {
					//System.out.println("     ����1<=x��x<=7");//
					int s1=domain.substring(0,1).toCharArray()[0];
					if(!((s1>=48&&s1<=57)||s1==45)){//��һ��Ϊ����
						//System.out.println("     ����x<=7");//
						cs = strs[0];
						float x = Float.parseFloat(strs[1])-0;
						float value = x/(n-1);   //��ÿ�ε�����ֵ��n��Ҫ����Ĳ�����������
						result.add(cs+"="+0);
						for(int i=1;i<=n-2;i++){ //���߽绹��Ҫ�����ĸ���
							float y = 0+value*i;
							result.add(cs+"="+y);				
						}
						result.add(cs+"="+strs[1]);
					}else{
						//System.out.println("     ����1<=x");//
						cs = strs[1];
						result.add(cs+"="+strs[0]);
						for(int i=1;i<=n-1;i++){ //���߽绹��Ҫ�����ĸ���
							float y = Float.parseFloat(strs[0])+10*i;
							result.add(cs+"="+y);				
						}
					}					
				}
			}
		}else{			 
			if (domain.contains(">=")) {
				System.out.println("�账����>=����"+domain);
				String[] strs = domain.split(">=");
				if (strs.length == 3) {	
					//System.out.println("����7>=x>=1");//		
					cs = strs[1];
					float x = Float.parseFloat(strs[0])-Float.parseFloat(strs[2]);
					float value = x/(n-1);   //��ÿ�ε�����ֵ��n��Ҫ����Ĳ�����������
					result.add(cs+"="+strs[2]);
					for(int i=1;i<=n-2;i++){//���߽绹��Ҫ�����ĸ���
						float y = Float.parseFloat(strs[2])+value*i;
						result.add(cs+"="+y);				
					}
					result.add(cs+"="+strs[0]);	
				} else{ 
					if (strs[0].contains(">")||strs[1].contains(">")) {
						//System.out.println("     ����7>=x>1��7>x>=1");//
						if (strs[1].contains(">")) {  //1<=x<7
							//System.out.println("     ����7>=x>1");//
							cs = strs[1].split(">")[0];
							float x = Float.parseFloat(strs[0])-Float.parseFloat(strs[1].split(">")[1]);
							float value = x/n;   //��ÿ�ε�����ֵ��n��Ҫ����Ĳ�����������
							for(int i=1;i<=n-1;i++){ //���߽绹��Ҫ�����ĸ���
								float y = Float.parseFloat(strs[1].split(">")[1])+value*i;
								result.add(cs+"="+y);				
							}
							result.add(cs+"="+strs[0]);
						} else {
							if (strs[0].contains(">")) {
								//System.out.println("     ����7>x>=1");//
								cs = strs[0].split(">")[1];
								float x = Float.parseFloat(strs[0])-Float.parseFloat(strs[1].split(">")[1]);
								float value = x/n;   //��ÿ�ε�����ֵ��n��Ҫ����Ĳ�����������
								result.add(cs+"="+strs[1]);
								for(int i=1;i<=n-1;i++){ //���߽绹��Ҫ�����ĸ���
									float y = Float.parseFloat(strs[1])+value*i;
									result.add(cs+"="+y);				
								}								
							}						
						}
					}else{
						//System.out.println("     ����x>=1��7>=x");//
						int s1=domain.substring(0,1).toCharArray()[0];
						if(!((s1>=48&&s1<=57)||s1==45)){//��һ��Ϊ����
							//System.out.println("     ����x>=1");//
							cs = domain.split(">=")[0];
							result.add(cs+"="+domain.split(">=")[1]);
							for(int i=1;i<=n-1;i++){ //���߽绹��Ҫ�����ĸ���
								float y = Float.parseFloat(domain.split(">=")[1])+10*i;
								result.add(cs+"="+y);				
							}	
						}else{
							//System.out.println("     ����7>=x");//						
							cs = domain.split(">=")[1];	
							float x = Float.parseFloat(domain.split(">=")[0])-0;
							float value = x/(n-1);   //��ÿ�ε�����ֵ��n��Ҫ����Ĳ�����������
							result.add(cs+"="+0);
							for(int i=1;i<=n-2;i++){ //���߽绹��Ҫ�����ĸ���
								float y = 0+value*i;
								result.add(cs+"="+y);				
							}	
							result.add(cs+"="+domain.split(">=")[0]);
						}	
					}
				}		
			} else{ 					
				if (domain.contains("<")) { //������<=Ҳ������>=
					System.out.println("�账����<����"+domain);
					String[] strs = domain.split("<");
					//System.out.println("     ����1<x<7��x<7��1<x");//
					if (strs.length == 3) {
						//System.out.println("     ����1<x<7");//
						cs = strs[1];
						float x = Float.parseFloat(strs[2])-Float.parseFloat(strs[0]);
						float value = x/(n+1);   //��ÿ�ε�����ֵ��n��Ҫ����Ĳ�����������
						for(int i=1;i<=n;i++){ //���߽绹��Ҫ�����ĸ���
							float y = Float.parseFloat(strs[0])+value*i;
							result.add(cs+"="+y);				
						}	
					} else {
						int s1=domain.substring(0,1).toCharArray()[0];
						if(!((s1>=48&&s1<=57)||s1==45)){//��һ��Ϊ����
							//System.out.println("     ����x<7");//
							cs = strs[0];
							float x = Float.parseFloat(strs[1])-0;
							float value = x/(n+1);   //��ÿ�ε�����ֵ��n��Ҫ����Ĳ�����������
							for(int i=1;i<=n;i++){ //���߽绹��Ҫ�����ĸ���
								float y = 0+value*i;
								result.add(cs+"="+y);				
							}	
						}else{
							//System.out.println("     ����1<x");//					
							cs = strs[1]; 
							for(int i=1;i<=n;i++){ //���߽绹��Ҫ�����ĸ���
								float y = Float.parseFloat(strs[0])+10*i;
								result.add(cs+"="+y);				
							}	
						}					
					}
				} else{ 
					if (domain.contains(">")) { //������<=Ҳ������>=Ҳ������<
						System.out.println("�账����>����"+domain);
						//System.out.println("     ����7>x>1��x>1��7>x");//
						String[] strs = domain.split(">");
						if (strs.length == 3) {
							//System.out.println("     ����7>x>1");//
							cs = strs[1];
							float x = Float.parseFloat(strs[0])-Float.parseFloat(strs[2]);
							float value = x/(n+1);   //��ÿ�ε�����ֵ��n��Ҫ����Ĳ�����������
							for(int i=1;i<=n;i++){ //���߽绹��Ҫ�����ĸ���
								float y = Float.parseFloat(strs[2])+value*i;
								result.add(cs+"="+y);				
							}	
						} else {
							int s1=domain.substring(0,1).toCharArray()[0];
							if(!((s1>=48&&s1<=57)||s1==45)){//��һ��Ϊ����
								//System.out.println("     ����x>1");//								
								cs = strs[0]; 
								for(int i=1;i<=n;i++){ //���߽绹��Ҫ�����ĸ���
									float y = Float.parseFloat(strs[1])+10*i;
									result.add(cs+"="+y);				
								}	
							}else{
								//System.out.println("     ����7>x");//								
								cs = strs[1];  
								float x = Float.parseFloat(strs[0])-0;
								float value = x/n;   //��ÿ�ε�����ֵ��n��Ҫ����Ĳ�����������
								result.add(cs+"="+0);
								for(int i=1;i<=n-1;i++){ //���߽绹��Ҫ�����ĸ���
									float y = 0+value*i;
									result.add(cs+"="+y);				
								}	
							}
						}
					}
				}
			}		
		}
		if(result.equals(null)){
			return null;
		}else  return result;
	}
	
	/**
	 * ���ܲ��ԣ���ʹ�������ͨ�������ض��ĵ�����ֵ���и�ֵ���
	 * @param s
	 * @return
	 */
	public static List<String> In_Result(String domain,int n){
		List<String> result = new ArrayList<String>();
		String cs = null;
		if (domain.contains("<=")){
			String[] strs = domain.split("<=");				
			System.out.println("�账����<=����"+domain);
			if (strs.length == 3) {	
				//System.out.println("����1<=x<=7");//
				cs = strs[1];	
				result.add(cs+"="+strs[0]);
				int y = Integer.parseInt(strs[0]);
				for(;y+n<=Integer.parseInt(strs[2]);){//��nΪ�������֣������
					y = y+n;
					result.add(cs+"="+y);				
				}				
			} else{ 
				if (strs[0].contains("<")||strs[1].contains("<")) {
					//System.out.println("     ����1<x<=7��1<=x<7");//
					if (strs[1].contains("<")) {  //1<=x<7
						//System.out.println("     ����1<=x<7");//
						cs = strs[1].split("<")[0];
						result.add(cs+"="+strs[0]);
						int y = Integer.parseInt(strs[0]);
						for(;y+n<Integer.parseInt(strs[1].split("<")[1]);){//��nΪ�������֣������
							y = y+n;
							result.add(cs+"="+y);				
						}	
					} else {
						if (strs[0].contains("<")) {
							//System.out.println("     ����1<x<=7");//
							cs = strs[0].split("<")[1];
							int y = Integer.parseInt(strs[0].split("<")[0])+1;//Ϊ�˴ﵽ�߽���ԣ��ѱ߽�+1
							result.add(cs+"="+y);
							for(;y+n<=Integer.parseInt(strs[1]);){ //���߽绹��Ҫ�����ĸ���
								y = y+n;
								result.add(cs+"="+y);				
							}
						}						
					}
				} else {
					//System.out.println("     ����1<=x��x<=7");//
					int s1=domain.substring(0,1).toCharArray()[0];
					if(!((s1>=48&&s1<=57)||s1==45)){//��һ��Ϊ����
						//System.out.println("     ����x<=7");//
						cs = strs[0];
						result.add(cs+"="+0);
						int y = 0;
						for(;y+n<=Integer.parseInt(strs[1]);){ //���߽绹��Ҫ�����ĸ���
							y = y+n;
							result.add(cs+"="+y);				
						}
					}else{
						//System.out.println("     ����1<=x");//
						cs = strs[1];
						result.add(cs+"="+strs[0]);
						int y = Integer.parseInt(strs[0]);
						for(;y+n<=5000;){ //1��5000�Ե�����n���ϵ���
							y = y+n;
							result.add(cs+"="+y);				
						}
					}					
				}
			}
		}else{			 
			if (domain.contains(">=")) {
				System.out.println("�账����>=����"+domain);
				String[] strs = domain.split(">=");
				if (strs.length == 3) {	
					//System.out.println("����7>=x>=1");//		
					cs = strs[1];
					int y = Integer.parseInt(strs[2]);
					result.add(cs+"="+y);
					for(;y+n<=Integer.parseInt(strs[0]);){//���߽绹��Ҫ�����ĸ���
						y = y+n;
						result.add(cs+"="+y);				
					}	
				} else{ 
					if (strs[0].contains(">")||strs[1].contains(">")) {
						//System.out.println("     ����7>=x>1��7>x>=1");//
						if (strs[1].contains(">")) {  //1<=x<7
							//System.out.println("     ����7>=x>1");//
							cs = strs[1].split(">")[0];
							int y = Integer.parseInt(strs[1].split(">")[1])+1;
							result.add(cs+"="+y);
							for(;y+n<=Integer.parseInt(strs[0]);){ //���߽绹��Ҫ�����ĸ���
								y = y+n;
								result.add(cs+"="+y);				
							}
						} else {
							if (strs[0].contains(">")) {
								//System.out.println("     ����7>x>=1");//
								cs = strs[0].split(">")[1];
								result.add(cs+"="+strs[1]);
								int y = Integer.parseInt(strs[1]);
								for(;y+n<Integer.parseInt(strs[0].split(">")[0]);){ //���߽绹��Ҫ�����ĸ���
									y = y+n;
									result.add(cs+"="+y);				
								}									
							}						
						}
					}else{
						//System.out.println("     ����x>=1��7>=x");//
						int s1=domain.substring(0,1).toCharArray()[0];
						if(!((s1>=48&&s1<=57)||s1==45)){//��һ��Ϊ����
							//System.out.println("     ����x>=1");//
							cs = domain.split(">=")[0];
							result.add(cs+"="+domain.split(">=")[1]);
							int y = Integer.parseInt(domain.split(">=")[1]);
							for(;y+n<=5000;){ //���߽绹��Ҫ�����ĸ���
								y = y+n;
								result.add(cs+"="+y);				
							}	
						}else{
							//System.out.println("     ����7>=x");//						
							cs = domain.split(">=")[1];								
							result.add(cs+"="+0);
							int y = 0;
							for(;y+n<=Integer.parseInt(domain.split(">=")[0]);){ //���߽绹��Ҫ�����ĸ���
								y = y+n;
								result.add(cs+"="+y);				
							}	
						}	
					}
				}		
			} else{ 					
				if (domain.contains("<")) { //������<=Ҳ������>=
					System.out.println("�账����<����"+domain);
					String[] strs = domain.split("<");
					//System.out.println("     ����1<x<7��x<7��1<x");//
					if (strs.length == 3) {
						//System.out.println("     ����1<x<7");//
						cs = strs[1];
						int y = Integer.parseInt(strs[0])+1;
						result.add(cs+"="+y);
						for(;y+n<Integer.parseInt(strs[2]);){ //���߽绹��Ҫ�����ĸ���
							y=y+n;
							result.add(cs+"="+y);				
						}	
					} else {
						int s1=domain.substring(0,1).toCharArray()[0];
						if(!((s1>=48&&s1<=57)||s1==45)){//��һ��Ϊ����
							//System.out.println("     ����x<7");//
							cs = strs[0];
							int y = 0;
							result.add(cs+"="+y);
							for(;y+n<Integer.parseInt(strs[1]);){ //���߽绹��Ҫ�����ĸ���
								y=y+n;
								result.add(cs+"="+y);				
							}	
						}else{
							//System.out.println("     ����1<x");//					
							cs = strs[1]; 
							int y = Integer.parseInt(strs[0])+1;
							result.add(cs+"="+y);
							for(;y+n<=5000;){ //���߽绹��Ҫ�����ĸ���
								y = y+n;
								result.add(cs+"="+y);				
							}	
						}					
					}
				} else{ 
					if (domain.contains(">")) { //������<=Ҳ������>=Ҳ������<
						System.out.println("�账����>����"+domain);
						//System.out.println("     ����7>x>1��x>1��7>x");//
						String[] strs = domain.split(">");
						if (strs.length == 3) {
							//System.out.println("     ����7>x>1");//
							cs = strs[1];
							int y = Integer.parseInt(strs[2])+1;
							result.add(cs+"="+y);
							for(;y+n<Integer.parseInt(strs[0]);){ //���߽绹��Ҫ�����ĸ���
								y = y+n;
								result.add(cs+"="+y);				
							}	
						} else {
							int s1=domain.substring(0,1).toCharArray()[0];
							if(!((s1>=48&&s1<=57)||s1==45)){//��һ��Ϊ����
								//System.out.println("     ����x>1");//								
								cs = strs[0]; 
								int y = Integer.parseInt(strs[1])+1;
								result.add(cs+"="+y);
								for(;y+n<=5000;){ //���߽绹��Ҫ�����ĸ���
									y = y+n;
									result.add(cs+"="+y);				
								}	
							}else{
								//System.out.println("     ����7>x");//								
								cs = strs[1];  
								int y = 0;
								result.add(cs+"="+y);						
								for(;y+n<Integer.parseInt(strs[0]);){ //���߽绹��Ҫ�����ĸ���
									y=y+n;
									result.add(cs+"="+y);				
								}	
							}
						}
					}
				}
			}		
		}
		if(result.equals(null)){
			return null;
		}else  return result;
	}
	
//	/**
//	 * ����poi����������excel�ļ�
//	 * @param s
//	 * @return
//	 */
//	public static List<String> PoiReadExcel(File file){
//		List<String> string = new ArrayList<String>();
//		//��Ҫ������excel�ļ�
//				//File file = new File("C:\\Users\\Administrator\\Desktop\\v41.xls");		
//				try {
//					//����Excel����ȡ�ļ�����
//					HSSFWorkbook workbook = new HSSFWorkbook(FileUtils.openInputStream(file));
//					//��ȡ��һ��������workbook.getSheet("Sheet1");
////					HSSFSheet sheet = workbook.getSheet("Sheet1");
//					//��ȡĬ�ϵ�һ��������sheet
//					HSSFSheet sheet = workbook.getSheetAt(0);
//					int firstRowNum = 0;
//					//��ȡsheet�����һ���к�
//					int lastRowNum = sheet.getLastRowNum();
//					for (int i = 1; i < lastRowNum; i++) {
//						HSSFRow row = sheet.getRow(i);
//						//��ȡ��ǰ�����Ԫ���к�
//						int laseCellNum = row.getLastCellNum();
//						/*for (int j = 0; j < 2; j++) {
//							HSSFCell cell = row.getCell(j);
//							//String value = cell.getStringCellValue();
//							int value1 = (int) cell.getNumericCellValue();
//							System.out.print(value1 + "    ");
//						}*/
//						HSSFCell cell = row.getCell(1);
//						//String value = cell.getStringCellValue();
//						//int value1 = (int) cell.getNumericCellValue();
//						String value1 = (int) cell.getNumericCellValue() + "";
//						System.out.print(value1 + "    ");
//						string.add(value1);
//						System.out.println();
//					}	
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				return string;
//	}

	
	
	
	
	
	
	public static String s="";
	public static List<String> re = new ArrayList<String>();
	public static void dis(int n, List<List<String>> l) {
		if (n == l.size()) {
			re.add(s);
			return;
		} else {
			List<String> sCollection = l.get(n);
			for (int i = 0; i < sCollection.size(); i++) {
				String s1 = s;
				if(s==""){
					s  = sCollection.get(i);
				}else{
					s =s + ","+ sCollection.get(i);
				}				
				dis(n+1,l);
				s = s1;
			}
		}
	}
}

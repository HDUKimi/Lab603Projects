package com.horstmann.violet.application.gui.util.ckt.testcase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.horstmann.violet.application.gui.util.ckt.handle.*;

public class OutPut {
	public static void main(String[] args) {
		//String xml="loop10ForXStream.xml";//无时间约束
		//String xml="loopForXStream3.1.5.xml";//有时间约束
		//String xml="loopForXStream3.1.5.2.x.xml";//有时间约束
		//String xml="loopForXStream.3.1.5.2.y.xml";//有时间约束
		//String xml="loopForXStream3.13.xml";//有时间约束
		//String xml="loopForXStream3.1.5.2.z.xml";//有时间约束
		//String xml="UAVForXStream3.1.5.3.xml";//有时间约束
		String xml="UAVForXStream3.1.6.xml";//有时间约束
		Automatic auto=GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
		if((auto.getClockSet().toString().equals("[]"))){
			System.out.println();
			System.out.println("此时间自动机无时间约束");
			//System.out.println("时间自动机时钟集合：null");
			//output1(xml);//第一步 读取xml，存入对应数据结构中
			//output11(xml);//第二步   满足状态覆盖的测试序列，存入对应数据结构中
			//output111(xml);//第三步   处理生成抽象测试用例
			output1111(xml);//第四步 抽象测试用例实例化
		}else{
			System.out.println();
			System.out.println("此时间自动机有时间约束");
			System.out.print("时间自动机时钟集合：");
			for(String c:auto.getClockSet()){
				System.out.print(" "+c+"  ");
			}
			System.out.println();
			//output2(xml);
			//output22(xml);
			//output111(xml);
			
			
			//output1(xml);//第一步 读取xml，存入对应数据结构中
			//output11(xml);//第二步   满足状态覆盖的测试序列，存入对应数据结构中
			//output111(xml);//第三步   处理生成抽象测试用例
			output1111(xml);//第四步 抽象测试用例实例化
		}
		//output1(xml);
	}


	/**
	 * 无时间约束的输出格式                     第一步                读取xml，存入对应数据结构中
	 * @param xml
	 */
	public static  void output1(String xml){
		Automatic automatic1=GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
		Automatic auto=AddType.addType(automatic1);
		//	ArrayList<Automatic> testCase=StateCoverage__1.testCase(auto);//获得满足状态覆盖的抽象测试序列		



		//########################################        第一步             ###############################################
		//#########################################################################################################
		//======================================================================读取xml，存入对应数据结构中		
		//======================================================================正在解析xml文档
		
		System.out.println("=============================获取xml文档信息=============================");
		//System.out.println();
		System.out.println("----------------------正在读取获得的xml文档信息----------------------");
	
		System.out.println("===>  时间自动机总体信息");
		System.out.println("时间自动机名字："+auto.getName());	
		System.out.print("时间自动机时钟集合：");
		if((auto.getClockSet().toString().equals("[]"))){
			System.out.println("null");
		}
		else{
			for(String c:auto.getClockSet()){
				System.out.print(" "+c+"  ");
			}
		}
		System.out.println("模型中总状态个数："+auto.getStateSet().size());
		System.out.println("模型中总迁移个数："+auto.getTransitionSet().size());
		System.out.println();
		//=========================================================================xml总体信息结束


		//=========================================================================获取模型初始状态信息
		System.out.println("-------------------------获取初始状态信息-------------------------");
		
		State iniState=auto.getInitState();
		System.out.println("===>  初始状态信息");
		System.out.println("初始状态名字："+iniState.getName());
		System.out.println("状态位置："+iniState.getPosition());
		System.out.println("是否为终止状态 ："+iniState.isFinalState());
		System.out.println("Type类型(是否为初始)："+iniState.getType());
		DBM_element[][] DBM=iniState.getInvariantDBM();
		if(auto.getClockSet().size()>0){
			for(int i=0;i<auto.getClockSet().size()+1;i++){
				for(int j=0;j<auto.getClockSet().size()+1;j++){
					DBM_element cons=DBM[i][j];
					//System.out.println("DBM_i："+cons.getDBM_i());
					//System.out.println("DBM_j："+cons.getDBM_j());
					//System.out.println("value："+cons.getValue());
					//System.out.println("Strictness："+cons.isStrictness());					
				}
			}
		}		
		System.out.println();
		//============================================================================模型初始状态信息获取结束


		//============================================================================获取模型状态的具体信息
		System.out.println("-------------------------获取所有状态信息-------------------------");
		System.out.println("===>  状态总个数："+auto.getStateSet().size());
		System.out.println();
		int k=0;
		for(State state:auto.getStateSet()){
			System.out.println("第"+k+"个状态");
			k++;
			DBM_element[][] dbm=state.getInvariantDBM();
			if(auto.getClockSet().size()>0){
				for(int i=0;i<auto.getClockSet().size()+1;i++){
					for(int j=0;j<auto.getClockSet().size()+1;j++){
						DBM_element cons=dbm[i][j];
						//System.out.println("DBM_i："+cons.getDBM_i());
						//System.out.println("DBM_j："+cons.getDBM_j());
						//System.out.println("value："+cons.getValue());
						//System.out.println("Strictness："+cons.isStrictness());					
					}
				}
			}			
			System.out.println("状态名称："+state.getName());
			System.out.println("状态位置："+state.getPosition());
			System.out.println("是否为终止状态："+state.isFinalState());
			System.out.println("Type类型(是否为初始)："+state.getType());
			DBM_element[][] adddbm=state.getAddClockRelationDBM();
			if(auto.getClockSet().size()>0){
				for(int i=0;i<auto.getClockSet().size()+1;i++){
					for(int j=0;j<auto.getClockSet().size()+1;j++){
						DBM_element cons=adddbm[i][j];
						//System.out.println("DBM_i："+cons.getDBM_i());
						//System.out.println("DBM_j："+cons.getDBM_j());
						//System.out.println("value："+cons.getValue());
						//System.out.println("Strictness："+cons.isStrictness());					
					}
				}
			}			
			//System.out.println("--------------------------");
			System.out.println();
		}
		System.out.println();		
		//=========================================================================模型具体状态信息获取结束




		//=========================================================================获取模型迁移(激励)信息
		System.out.println("-------------------------获取所有迁移(激励)信息-------------------------");
		
		System.out.println("===>  迁移总个数："+auto.getTransitionSet().size());
		int p=0;
		for(Transition tran:auto.getTransitionSet()){
			System.out.println("第"+p+"条迁移(激励)");
			System.out.println("迁移(激励)名称："+tran.getName().replace("(", "").replace(")", "").replace("!", "").replace("?", ""));
			System.out.println("迁移Id："+tran.getId());
			p++;
			if(tran.getConstraintDBM()!=null){
				DBM_element[][] dbm=tran.getConstraintDBM();
				if(auto.getClockSet().size()>0){
					for(int i=0;i<auto.getClockSet().size()+1;i++){
						for(int j=0;j<auto.getClockSet().size()+1;j++){
							DBM_element cons=dbm[i][j];
							//System.out.println("DBM_i："+cons.getDBM_i());
							//System.out.println("DBM_j："+cons.getDBM_j());
							//System.out.println("value："+cons.getValue());
							//System.out.println("Strictness："+cons.isStrictness());					
						}
					}
				}				
			}
			else System.out.println("时钟约束为空");			
//			System.out.println("      ===>  源状态名称："+tran.getSource());
//			System.out.println("      ===>  目的状态名称："+tran.getTarget());						
			if(tran.getEventSet()==null){
				System.out.println("事件为空");
			}
			else if(tran.getEventSet().size()==0){
				System.out.println("事件为不空，但是没有内容");
			}
			else{				
				String in=tran.getIn();		
				String con=tran.getCondition();
				String out=tran.getOut();
				System.out.print("in(约束条件)：");
				if("".equals(in.toString())){
					System.out.println("null");
				}
				else{
					System.out.println(in.toString());
				}
				System.out.println("condition(约束条件)："+con);
				System.out.println("out(输出信息)："+out.toString());	
				////////////////out.toString()为空时输出空
				/*if("".equals(out.toString())){
					System.out.println("null");
				}
				else{
					System.out.println(out.toString());
				}*/
				//ArrayList<String> events=tran.getEventSet();
				//				for(String e：events){
				//					System.out.println("      ===>  事件："+e);
				//				}								
			}			
			if(tran.getResetClockSet()==null){
				System.out.println("重置时钟：null");
			}
			else if(tran.getResetClockSet().size()==0){
				System.out.println("重置时钟：不空，但是没有内容");
			}
			else{
				ArrayList<String> reset=tran.getResetClockSet();
				for(String r:reset){
					System.out.println("重置的时钟："+r);
				}
			}

			if(tran.getTypeIds()==null){
				//System.out.println("      ===>  typeID：null");
			}
			else if(tran.getTypeIds().size()==0){
				//System.out.println("      ===>  typeID：不空，但是没有内容");
			}
			else{
				ArrayList<String> typeid=tran.getTypeIds();
				for(String i:typeid){
					//System.out.println("      ===>  typeId："+i);
				}
			}

			if(tran.getTypes()==null){
				//System.out.println("      ===>  types：null");
			}
			else if(tran.getTypes().size()==0){
				//System.out.println("      ===>  types：不空，但是没有内容");
			}
			else{
				ArrayList<String> type=tran.getTypes();
				for(String t:type){
					//System.out.println("      ===>  types："+t);
				}
			}

			//System.out.println("--------------------------");
			System.out.println();
		}
		System.out.println();
		//=====================================================================模型迁移(激励)信息获取结束	
		//=====================================================================读取xml结束	




	}


	/**
	 * 无时间约束的输出格式                       第二步            满足状态覆盖的测试序列，存入对应数据结构中
	 * @param xml
	 */
	public static  void output11(String xml){
		Automatic automatic1=GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
		Automatic auto=AddType.addType(automatic1);
		ArrayList<Automatic> testCase=StateCoverage__1.testCase(auto);//获得满足状态覆盖的抽象测试序列		

		//########################################        第二步             ###############################################
		//#########################################################################################################
		//=====================================================================读取抽象测试序列
		//======================================================================满足状态覆盖的测试序列，存入对应数据结构中
		System.out.println("===========================获取满足状态覆盖的测试序列信息===========================");
		System.out.println("-------------------------正在遍历时间自动机-------------------------");
		System.out.println();
		System.out.println("-------------------------正在生成深度优先生成树-------------------------");
		System.out.println();
		System.out.println("-------------------------正在遍历深度优先生成树-------------------------");
		System.out.println();
		System.out.println("-------------------------正在生成满足状态覆盖的抽象测试序列-------------------------");
		System.out.println();
		System.out.println("-------------------------获取抽象测试序列详细信息-------------------------");		
		System.out.println();
		System.out.println("===>  抽象测试序列个数："+testCase.size());
		System.out.println();
		for(Automatic a:testCase){	
			System.out.println("---------------");
			System.out.println("--->  测试用例名字："+a.getName());
			for(Transition tran:a.getTransitionSet()){	
				System.out.println("迁移(激励)名称："+tran.getName().replace("(", "").replace(")", "").replace("!", "").replace("?", ""));
				System.out.println("迁移Id："+tran.getId());								
				System.out.println("源状态名称："+tran.getSource());
				System.out.println("目的状态名称："+tran.getTarget());	
				//未处理的约束条件	
				//				System.out.println("        ===>  约束："+tran.getEventSet());
				//输出in里面的约束
				System.out.println("in(约束条件)："+tran.getIn());
				/*if(tran.getIn().equals("")){
					System.out.println("null");
				}else{
					System.out.println(tran.getIn());
				}	*/				
				//输出out里面的约束
				System.out.print("out(输出信息)：");
				if(tran.getOut().equals("")){
					System.out.println("null");
				}else{
					System.out.println(tran.getOut());
				}
				//输出condition里面的约束
				//System.out.println("        ===>  get-condition："+tran.getCondition());
				if(!tran.getCondition().equals("null")){
					tran.getCondition().replaceAll("false", "False").replaceAll("true", "True");				
					if(tran.getCondition().contains("/")){//取/后面的
						String conditonValue = tran.getCondition().split("/")[1];//要处理的不等式组
						System.out.println("condition(约束条件)："+conditonValue);
					}
					else{
						if(!tran.getCondition().contains("/")){
							System.out.println("condition(约束条件)："+tran.getCondition());
						}
					}
				}
				else{
					if(tran.getCondition().equals("null")){
						System.out.println("condition(约束条件)：null"+tran.getCondition());	
					}
				}
				System.out.println();
			}
			//System.out.println();
			//System.out.println("---------------------------------------------------------");

		}
		//=====================================================================读取抽象测试序列结束
	}

	/**
	 * 无时间约束的输出格式                       第三步           处理生成抽象测试用例
	 * @param xml
	 */
	public static  void output111(String xml){
		Automatic auto=GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
		ArrayList<Automatic> testCase=StateCoverage__1.testCase(auto);//获得满足状态覆盖的抽象测试序列



		//########################################        第三步             ###############################################
		//#########################################################################################################
		//=====================================================================读取满足状态覆盖的抽象测试序列
		//======================================================================处理迁移（激励）上的约束，生成抽象测试用例
		System.out.println("===========================生成满足状态覆盖的测试用例===========================");
		System.out.println("----------------------------获取测试用例信息----------------------------");
		System.out.println();
		System.out.println("===>  抽象测试序列个数："+testCase.size());

		int i = 1;
		String ttt=null;
		String ttt1=null;
		String ttt2=null;
		String ttt3=null;

		for(Automatic a:testCase){	///////////////////////////////////
			//List<String> list=new ArrayList<String>();//存放整数不等式
			//List<String> list1=new ArrayList<String>();//存放小数不等式
			//List<String> list2=new ArrayList<String>();//存放小数=0的不等式
			int j=1;
			//System.out.println();
			System.out.println("------------第"+i+"条测试用例------------");
			System.out.println("--->  测试用例名字:"+a.getName());
			for(Transition tran:a.getTransitionSet()){/////////////////////
				//System.out.println();
				//System.out.println("迁移(激励)名称--->"+tran.getName());
				if(tran.getName().contains("(")){
					int index11=tran.getName().replace("!", "").replace("?", "").indexOf("(");
					String sss=tran.getName().substring(0,index11);
					System.out.println("迁移(激励)名称："+sss);
				}
				else{
					System.out.println("迁移(激励)名称："+tran.getName().replace("!", "").replace("?", ""));
				}

				System.out.println("迁移Id："+tran.getId());								
				System.out.println("源状态名称："+tran.getSource());
				System.out.println("目的状态名称："+tran.getTarget());
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
				//System.out.println("          in---->"+tran.getIn());	//in里面的内容			
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
					String bds00=null;
					
					List<String> list=new ArrayList<String>();//存放整数不等式
					List<String> list1=new ArrayList<String>();//存放小数不等式
					List<String> list2=new ArrayList<String>();//存放小数=0的不等式
					
					//==0的不等式即为解 ==换为=
					//String bds0=GetBds.get_bds_0(tran.getIn().toString());

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
							//	System.out.println("整数不等式为----->"+cs);
							
							//=0的小数不等式
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
							
							//=0的小数不等式
							String csss = null;
							if(list2.size()>1){
								csss=list2.get(0);
								for(int mm=1;mm<list2.size();mm++){
									String c1=list2.get(mm);
									csss=csss+","+c1;
								}
							}
							if(list2.size()==1){
								csss=list2.get(0);
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
					if(((bds1==null)&&(cs1==null))&&(s2==null)){
						//						System.out.println("        ===>  in上没有约束即为：null");
					}
					if((bds1!=null)&&(cs1!=null)){
						String bbb = bds1+","+s1;
						//						System.out.println("        ===>  in上整数型数值不等式："+bbb);
						//System.out.println("        ===>  in上整数型数值参数："+cs1);
						//String solution1 = Mathematica.getSolution2(bbb, cs1);
						ttt=bbb.toString();
						//	System.out.println("in整数型约束解为："+solution1);
					}
					else{
						if(s1!=null){
							//							System.out.println("        ===>  in上整数型数值不等式："+s1);
							//System.out.println("in上整数型数值参数："+cs1);
							//String solution1 = Mathematica.getSolution2(s1, cs1);
							ttt=s1.toString();
							//System.out.println("in上整数型约束解为："+solution1);
						}
					}
					if((s2!=null)){
						//						System.out.println("        ===>  in上小数型数值不等式："+s2);
						//System.out.println("in上小数型数值参数："+cs2);
						//						String solution2 = Mathematica.getSolution4(s2, cs2);
						ttt1=s2.toString();
						//						System.out.println("in上小数型约束解为："+solution2);
					}	
					if(boolbds!=null){
						//System.out.println("        ===>  in上布尔型的不等式："+boolbds);
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
					if(ttt2!=null&&bds00!=null){
						ttt2 = ttt2+","+bds00;
					}else{
						if(ttt2==null&&bds00!=null){
							ttt2 = bds00;
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
					List<String> list=new ArrayList<String>();//存放整数不等式
					List<String> list1=new ArrayList<String>();//存放小数不等式
					List<String> list2=new ArrayList<String>();//存放小数=0的不等式
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
								String bds00=null;

								
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
										//								System.out.println("整数不等式为----->"+cs);

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
											for(int mm=1;mm<list2.size();mm++){
												String c1=list2.get(mm);
												csss=csss+","+c1;
											}
										}
										if(list2.size()==1){
											csss=list2.get(0);
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
									//									String solution1 = Mathematica.getSolution2(bbb, cs1);
									ttt=bbb.toString();
									//									System.out.println("condition整数型约束解为："+solution1);
								}
								else{
									if(s1!=null){
										//System.out.println("condition上整数型数值不等式："+s1);
										//System.out.println("        ===>  condition上整数型数值参数："+cs1);
										//String solution1 = Mathematica.getSolution2(s1, cs1);
										ttt=s1.toString();
										//System.out.println("condition上整数型约束解为："+solution1);
									}
								}
								if((s2!=null)){
									//System.out.println("condition上小数型数值不等式："+s2);
									//System.out.println("        ===>  condition上小数型数值参数："+cs2);
									//									String solution2 = Mathematica.getSolution4(s2, cs2);
									ttt1=s2.toString();
									//									System.out.println("condition上小数型约束解为："+solution2);
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
								if(ttt3!=null&&bds00!=null){
									ttt3 = ttt3+","+bds00;
								}else{
									if(ttt3==null&&bds00!=null){
										ttt3 = bds00;
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
									String bds00=null;

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
															if(m==1&&(x1.contains("==0"))){
																list2.add(x1);//list2里面存放==0的小数不等式
															}
															if(m==0){
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
															if(m==1&&!(x1.contains("==0"))){
																list2.add(x1);//list1里面存放==0的小数不等式
															}
															if(m==0){
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
															if(m==1&&!(bds1.contains("==0"))){
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
											//									System.out.println("整数不等式为----->"+cs);

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
											//									System.out.println("小数不等式为----->"+css);
											//=0的小数不等式
											String csss = null;
											if(list2.size()>1){
												csss=list2.get(0);
												for(int mm=1;mm<list2.size();mm++){
													String c1=list2.get(mm);
													csss=csss+","+c1;
												}
											}
											if(list2.size()==1){
												csss=list2.get(0);
											}	
											bds00=csss.replace("==", "=");
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
										//										String solution1 = Mathematica.getSolution2(bbb, cs1);
										ttt=bbb.toString();
										//										System.out.println("condition整数型约束解为："+solution1);
									}
									else{
										if(s1!=null){
											//											System.out.println("        ===>  condition上整数型数值不等式："+s1);
											//											System.out.println("        ===>  condition上整数型数值参数："+cs1);
											//											String solution1 = Mathematica.getSolution2(s1, cs1);
											ttt=s1.toString();
											//											System.out.println("condition上整数型约束解为："+solution1);
										}
									}
									if((s2!=null)){
										//										System.out.println("        ===>  condition上小数型数值不等式："+s2);
										//										System.out.println("        ===>  condition上小数型数值参数："+cs2);
										//										String solution2 = Mathematica.getSolution4(s2, cs2);
										ttt1=s2.toString();
										//										System.out.println("condition上小数型约束解为："+solution2);
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
									if(ttt3!=null&&bds00!=null){
										ttt3 = ttt3+","+bds00;
									}else{
										if(ttt3==null&&bds00!=null){
											ttt3 = bds00;
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
					System.out.println("实例化约束条件：null");
				}
				else{
					if(ttt2!=null&&ttt3!=null){
						String x=ttt2+","+ttt3;
						x.replaceAll("false", "False").replaceAll("true", "True");
						System.out.println("实例化约束条件："+x);
					}
					else{
						if(ttt2==null&&ttt3!=null){
							String x=ttt3;
							x.replaceAll("false", "False").replaceAll("true", "True");
							System.out.println("实例化约束条件："+x);
						}
						else{
							if(ttt2!=null&&ttt3==null){
								String x=ttt2;
								x.replaceAll("false", "False").replaceAll("true", "True");
								System.out.println("实例化约束条件："+x);
							}
						}
					}
				}

				//				System.out.println("******************************************");

				////////////////////////////////////////////////////////////////////////////////////
				//System.out.println("                 ======第"+j+"条迁移结束======");
				j++;
				System.out.println();
			}
			System.out.println();
			//System.out.println("===========================第"+i+"条测试用例读取完成");
			i++;
		}
	}


	/**
	 * 无时间约束的输出格式                       第四步           抽象测试用例实例化
	 * @param xml
	 */
	public static  void output1111(String xml){

		Automatic auto=GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
		ArrayList<Automatic> testCase=StateCoverage__1.testCase(auto);//获得满足状态覆盖的抽象测试序列



		//########################################        第四步             ###############################################
		//#########################################################################################################
		//=====================================================================读取满足状态覆盖的抽象测试用例
		//======================================================================根据抽象测试用例，求解约束不等式
		System.out.println("===========================正在进行实例化过程===========================");
		System.out.println();
		System.out.println("---------------------满足状态覆盖的实例化结果---------------------");
		System.out.println("===>  实例化测试用例个数："+testCase.size());

		int i = 1;
		String ttt=null;
		String ttt1=null;
		String ttt2=null;//in
		String ttt3=null;//condition

		for(Automatic a:testCase){	///////////////////////////////////
			
			int j=1;
			System.out.println();
			System.out.println("-------------正在读取第"+i+"条测试用例------------");
			System.out.println("--->  测试用例名字:"+a.getName());
			for(Transition tran:a.getTransitionSet()){/////////////////////
				//System.out.println("       -----------------------------");
				if(tran.getName().contains("(")){
					int index11=tran.getName().replace("!", "").replace("?", "").indexOf("(");
					String sss=tran.getName().substring(0,index11);
					System.out.println("迁移(激励)名称："+sss);
				}
				else{
					System.out.println("迁移(激励)名称："+tran.getName().replace("!", "").replace("?", ""));
				}

				System.out.println("迁移Id："+tran.getId());								
				System.out.println("源状态名称："+tran.getSource());
				//				System.out.println("        ===>  目的状态名称："+tran.getTarget());
				//System.out.println("             ======第"+j+"条迁移开始======");
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
//				
				System.out.println("in:"+tran.getIn());	//in里面的内容			
				if(GetMap.get_inMap(tran.getIn())==null){//map里面为空，即没有参数
					//System.out.println("keySet集合："+tran.getIn());
					ttt2=null;
				}
				else{//map里有值，即有参数和参数对应类型
					map3 = GetMap.get_inMap(tran.getIn());
					String cs1 = AddBdsType.getcs(map3);
					String cs2 = AddBdsType.getDoubleCs(map3);
					String cs3 = AddBdsType.getBoolCs(map3);
					
					List<String> list=new ArrayList<String>();//存放整数不等式
					List<String> list1=new ArrayList<String>();//存放除=0的小数不等式
					List<String> list2=new ArrayList<String>();//存放=0小数不等式
					
/////					
					//System.out.println("zscs:"+cs1+"    xscs:"+cs2+"    boolcs:"+cs3);
					String s1 = AddBdsType.add_bds(map3);			
					String s2 = AddBdsType.add_doublebds(map3);		
					String bds2=null;
					String bds00=null;
					//==0的不等式即为解 ==换为=
					//String bds0=GetBds.get_bds_0(tran.getIn().toString());

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
											if(m==1&&(x1.contains("==0"))){
												list2.add(x1);//list2里面存放==0的小数不等式
											}
											if(m==0){
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
											if(m==1&&(x1.contains("==0"))){
											list2.add(x1);//list2里面存放==0的小数不等式
										    }
											
											if(m==0){
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
							//	System.out.println("整数不等式为----->"+cs);

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
							//	System.out.println("小数不等式为----->"+css);
							
							//=0的小数不等式
							String csss = null;
							if(list2.size()>1){
								csss=list2.get(0);
								for(int mm=1;mm<list2.size();mm++){
									String c1=list2.get(mm);
									csss=csss+","+c1;
								}
							}
							if(list2.size()==1){
								csss=list2.get(0);
							}	
							bds00=csss.replace("==", "=");
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
//					System.out.println("整数------>不等式，即："+bds1);  //in上数字不等式
//					System.out.println("==0------>不等式，即："+bds00);
//					System.out.println("整数------>参数，即："+cs1);
//					System.out.println("小数------>不等式，即："+bds2);
//					System.out.println("小数------>参数，即："+cs2);
//					System.out.println("add------>整数不等式为："+s1);
//					System.out.println("add------>小数不等式为："+s2);

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
						//System.out.println("        ===>  in上没有约束即为：null");
					}
					if((bds1!=null)&&(cs1!=null)){
						String bbb = bds1+","+s1;
						//System.out.println("        ===>  in上整数型数值不等式："+bbb);
						//System.out.println("        ===>  in上整数型数值参数："+cs1);
						int resultNum = getMathNum(bbb);
						String solution1 = Mathematica.getSolution2(bbb, cs1, resultNum);
						ttt=solution1.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
						//ttt=bbb.toString();
						//System.out.println("in整数型约束解为："+solution1);
					}
					else{
						if(s1!=null){
							//System.out.println("        ===>  in上整数型数值不等式："+s1);
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
						//System.out.println("        ===>  in上布尔型的不等式："+boolbds);
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
					if(ttt2!=null&&bds00!=null){
						ttt2 = ttt2+","+bds00;
					}else{
						if(ttt2==null&&bds00!=null){
							ttt2 = bds00;
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
					List<String> list=new ArrayList<String>();//存放整数不等式
					List<String> list1=new ArrayList<String>();//存放除=0的小数不等式
					List<String> list2=new ArrayList<String>();//存放=0小数不等式
					tran.getCondition().replaceAll("false", "False").replaceAll("true", "True");				
					if(tran.getCondition().contains("/")){//取/后面的

						String conditonValue = tran.getCondition().split("/")[1];//要处理的不等式组
//						
						System.out.println("condition上条件:"+conditonValue);
						//System.out.println("==================================");
						if(GetMap.get_condMap(conditonValue)==null){          //没有约束不等式
							//System.out.println("keySet集合1："+GetMap.get_condMap(conditonValue));
							ttt3=null;
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
								String bds00=null;

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
														if(m==1&&(x1.contains("==0"))){
															list2.add(x1);//list2里面存放==0的小数不等式
														}
														if(m==0){
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
														if(m==1&&(x1.contains("==0"))){
															list2.add(x1);//list2里面存放==0的小数不等式
														}
														if(m==0){
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
										//								System.out.println("整数不等式为----->"+cs);

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
											for(int mm=1;mm<list2.size();mm++){
												String c1=list2.get(mm);
												csss=csss+","+c1;
											}
										}
										if(list2.size()==1){
											csss=list2.get(0);
										}	
										bds00=csss.replace("==", "=");
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
//								//System.out.println("==0------>不等式，即："+bds0);
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
								//System.out.println("布尔型------>不等式，即："+boolbds);//condition上布尔不等式  ==
								//								System.out.println("布尔型------>参数，即："+cs3);



								///////////////////////////////////
								//调用mma软件求解方程组
								if(((bds1==null)&&(cs1==null))&&(s2==null)){
									//									System.out.println("        ===>  condition上没有约束即为：null");
									//input.setText("null");
								}
								if((bds1!=null)&&(cs1!=null)){
									String bbb = bds1+","+s1;
//////////////									
									//System.out.println("condition上整数型数值不等式："+bbb);
									//System.out.println("        ===>  condition上整数型数值参数："+cs1);
									int resultNum = getMathNum(bbb);
									String solution1 = Mathematica.getSolution2(bbb, cs1, resultNum);
									ttt=solution1.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
									//ttt=bbb.toString();
									//									System.out.println("condition整数型约束解为："+solution1);
								}
								else{
									if(s1!=null){
///////////										
										//System.out.println("condition上整数型数值不等式："+s1);
										//System.out.println("        ===>  condition上整数型数值参数："+cs1);
										int resultNum = getMathNum(s1);
										String solution1 = Mathematica.getSolution2(s1, cs1, resultNum);
										ttt=solution1.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
										//ttt=s1.toString();
										//System.out.println("condition上整数型约束解为："+solution1);
									}
								}
								if((bds2!=null)&&(s2!=null)){
//////////////									
									//System.out.println("condition上小数型数值不等式："+s2);
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
/////////////								
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
								if(ttt3!=null&&bds00!=null){
									ttt3 = ttt3+","+bds00;
									System.out.println("condition上解为："+ttt3);
								}else{
									if(ttt3==null&&bds00!=null){
										ttt3 = bds00;
										System.out.println("condition上解为："+ttt3);
									}
								}


								//////////////////


							}
						}

					}
					else{
						if(!tran.getCondition().contains("/")){
//							
							System.out.println("condition条件："+tran.getCondition());
							if(GetMap.get_condMap(tran.getCondition())==null){
								//								System.out.println("keySet集合3："+GetMap.get_condMap(tran.getCondition()));
								ttt3=null;
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
									String bds00=null;

									//==0的不等式即为解 ==换为=
									//String bds0=GetBds.get_bds_0(tran.getCondition().toString());
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
															if(m==1&&(x1.contains("==0"))){
																list2.add(x1);//list2里面存放==0的小数不等式
															}
															if(m==0){
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
															if(m==1&&(x1.contains("==0"))){
																list2.add(x1);//list2里面存放==0的小数不等式
															}
															if(m==0){
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
										
											//=0的小数不等式
											String csss = null;
											if(list2.size()>1){
												csss=list2.get(0);
												for(int mm=1;mm<list2.size();mm++){
													String c1=list2.get(mm);
													csss=csss+","+c1;
												}
											}
											if(list2.size()==1){
												csss=list2.get(0);
											}	
											bds00=csss.replace("==", "=");
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
//									System.out.println("整数------>不等式，即："+bds1);  //condition上数字不等式
//									//System.out.println("==0------>不等式，即："+bds0);
//									System.out.println("整数------>参数，即："+cs1);
//									System.out.println("小数------>不等式，即："+bds2);
//									System.out.println("小数------>参数，即："+cs2);
//									System.out.println("add------>整数不等式为："+s1);
//									System.out.println("add------>小数不等式为："+s2);

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
/////////										
										//System.out.println("condition上整数型数值不等式："+bbb);
										//										System.out.println("        ===>  condition上整数型数值参数："+cs1);

										int resultNum = getMathNum(bbb);
										String solution1 = Mathematica.getSolution2(bbb, cs1, resultNum);
										ttt=solution1.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
										//ttt=bbb.toString();
										//										System.out.println("condition整数型约束解为："+solution1);
									}
									else{
										if(s1!=null){
/////////	
											//System.out.println("condition上整数型数值不等式："+s1);
											//											System.out.println("        ===>  condition上整数型数值参数："+cs1);
											int resultNum = getMathNum(s1);
											String solution1 = Mathematica.getSolution2(s1, cs1, resultNum);
											ttt=solution1.toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
											//ttt=s1.toString();
											//											System.out.println("condition上整数型约束解为："+solution1);
										}
									}
									if((bds2!=null)&&(s2!=null)){
/////////									
										//System.out.println("condition上小数型数值不等式："+s2);
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
/////////
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

									if(ttt3!=null&&bds00!=null){
										ttt3 = ttt3+","+bds00;
										System.out.println("condition上解为："+ttt3);
									}else{
										if(ttt3==null&&bds00!=null){
											ttt3 = bds00;
											System.out.println("condition上解为："+ttt3);
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
						System.out.println("condition上条件："+tran.getCondition());							
						ttt3=null;
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
					System.out.println("实例化结果：null");
				}
				else{
					if(ttt2!=null&&ttt3!=null){
						ttt3.toString().replaceAll("false", "False").replaceAll("true", "True");
						String x=ttt2+","+ttt3;
						x.toString().replace("false", "False").replace("true", "True");
						System.out.println("实例化结果："+x);
					}
					else{
						if(ttt2==null&&ttt3!=null){
							ttt3.toString().replaceAll("false", "False").replaceAll("true", "True");
							String x=ttt3;
							x.toString().replace("false", "False").replace("true", "True");
							System.out.println("实例化结果："+x);
						}
						else{
							if(ttt2!=null&&ttt3==null){
								String x=ttt2;
								x.toString().replaceAll("false", "False").replaceAll("true", "True");
								System.out.println("实例化结果："+x);
							}
						}
					}
				}

				System.out.println();
				//				System.out.println("******************************************");

				////////////////////////////////////////////////////////////////////////////////////
				//System.out.println("                 ======第"+j+"条迁移结束======");
				j++;
			}
			//System.out.println("===========================第"+i+"条测试用例读取完成");
			i++;
		}

	}




	/**
	 * 有时间自动机的输出格式
	 * @param xml
	 */
	public static  void output2(String xml){

		Automatic automatic1=GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
		Automatic auto=AddType.addType(automatic1);
		//ArrayList<State> new_stateSet=Minimization__1.minimization(automatic);
		Automatic new_automatic=IPR__1.iPR(auto);//获得拆分后的时间自动机
		Automatic aTDRTAutomatic=ATDTR__1.aTDRT(new_automatic,auto);//获得去除抽象时间迁移后的时间自动机
		//Automatic DFStree=StateCoverage__1.DFSTree(aTDRTAutomatic);
		ArrayList<Automatic> testCase=StateCoverage__1.testCase(aTDRTAutomatic);//获得满足状态覆盖的抽象测试序列
		ArrayList<ArrayList<String>> all_inequalitys=Get_inequality__1.get_AllInequalitys(testCase);//每个抽象测试序列有一个不等式组

		//########################################        第一步             ###############################################
		//#########################################################################################################
		//======================================================================读取xml，存入对应数据结构中		
		//======================================================================正在解析xml文档
		System.out.println("----------------------------获取xml文档信息----------------------------");
		System.out.println("===========================正在解析xml文档");
		System.out.println("===========================");
		System.out.println();
		System.out.println("===========================正在获取xml文档信息");		
		System.out.println("  ===>  时间自动机名字："+auto.getName());	
		System.out.print("  ===>  时间自动机时钟集合：");
		if((auto.getClockSet().toString().equals("[]"))){
			System.out.println("null");
		}
		else{
			for(String c:auto.getClockSet()){
				System.out.print(" "+c+"  ");
			}
		}
		System.out.println("  ===>  模型中总状态个数："+auto.getStateSet().size());
		System.out.println("  ===>  模型中总迁移个数："+auto.getTransitionSet().size());
		System.out.println();
		//=========================================================================xml总体信息结束


		//=========================================================================获取模型初始状态信息
		System.out.println("===========================正在获取初始状态信息");
		System.out.println("===========================");
		State iniState=auto.getInitState();
		System.out.println("  ===>  初始状态名字："+iniState.getName());
		System.out.println("  ===>  状态位置："+iniState.getPosition());
		System.out.println("  ===>  是否为终止状态 ："+iniState.isFinalState());
		System.out.println("  ===>  Type类型(是否为初始)："+iniState.getType());
		DBM_element[][] DBM=iniState.getInvariantDBM();
		if(auto.getClockSet().size()>0){
			for(int i=0;i<auto.getClockSet().size()+1;i++){
				for(int j=0;j<auto.getClockSet().size()+1;j++){
					DBM_element cons=DBM[i][j];
					//System.out.println("DBM_i："+cons.getDBM_i());
					//System.out.println("DBM_j："+cons.getDBM_j());
					System.out.println("  ===>  value："+cons.getValue());
					System.out.println("  ===>  Strictness："+cons.isStrictness());					
				}
			}
		}		
		System.out.println();
		//============================================================================模型初始状态信息获取结束


		//============================================================================获取模型状态的具体信息
		System.out.println("===========================正在获取所有状态具体信息");
		System.out.println("===========================");
		System.out.println("  ===>  状态总个数："+auto.getStateSet().size());
		int k=0;
		for(State state:auto.getStateSet()){
			System.out.println("    ===>  第"+k+"个状态");
			k++;
			DBM_element[][] dbm=state.getInvariantDBM();
			if(auto.getClockSet().size()>0){
				for(int i=0;i<auto.getClockSet().size()+1;i++){
					for(int j=0;j<auto.getClockSet().size()+1;j++){
						DBM_element cons=dbm[i][j];
						//System.out.println("DBM_i："+cons.getDBM_i());
						//System.out.println("DBM_j："+cons.getDBM_j());
						System.out.println("      ===>  value："+cons.getValue());
						System.out.println("      ===>  Strictness："+cons.isStrictness());					
					}
				}
			}			
			System.out.println("      ===>  状态名称："+state.getName());
			System.out.println("      ===>  状态位置："+state.getPosition());
			System.out.println("      ===>  是否为终止状态："+state.isFinalState());
			System.out.println("      ===>  Type类型(是否为初始)："+state.getType());
			DBM_element[][] adddbm=state.getAddClockRelationDBM();
			if(auto.getClockSet().size()>0){
				for(int i=0;i<auto.getClockSet().size()+1;i++){
					for(int j=0;j<auto.getClockSet().size()+1;j++){
						DBM_element cons=adddbm[i][j];
						//System.out.println("DBM_i："+cons.getDBM_i());
						//System.out.println("DBM_j："+cons.getDBM_j());
						System.out.println("      ===>  value："+cons.getValue());
						System.out.println("      ===>  Strictness："+cons.isStrictness());					
					}
				}
			}			
			System.out.println("--------------------------");
		}
		System.out.println();		
		//=========================================================================模型具体状态信息获取结束




		//=========================================================================获取模型迁移(激励)信息
		System.out.println("===========================正在获取所有迁移(激励)具体信息");
		System.out.println("===========================");
		System.out.println("  ===>  迁移总个数："+auto.getTransitionSet().size());
		int p=0;
		for(Transition tran:auto.getTransitionSet()){
			System.out.println("    ===>  第"+p+"条迁移(激励)");
			System.out.println("      ===>  迁移(激励)名称："+tran.getName()/*.replace("(", "").replace(")", "").replace("!", "").replace("?", "")*/);
			System.out.println("      ===>  迁移Id："+tran.getId());
			p++;
			if(tran.getConstraintDBM()!=null){
				DBM_element[][] dbm=tran.getConstraintDBM();
				if(auto.getClockSet().size()>0){
					for(int i=0;i<auto.getClockSet().size()+1;i++){
						for(int j=0;j<auto.getClockSet().size()+1;j++){
							DBM_element cons=dbm[i][j];
							//System.out.println("DBM_i："+cons.getDBM_i());
							//System.out.println("DBM_j："+cons.getDBM_j());
							System.out.println("      ===>  value："+cons.getValue());
							System.out.println("      ===>  Strictness："+cons.isStrictness());					
						}
					}
				}				
			}
			else System.out.println("时钟约束为空");			
			System.out.println("      ===>  源状态名称："+tran.getSource());
			System.out.println("      ===>  目的状态名称："+tran.getTarget());						
			if(tran.getEventSet()==null){
				System.out.println("事件为空");
			}
			else if(tran.getEventSet().size()==0){
				System.out.println("事件为不空，但是没有内容");
			}
			else{				
				String in=tran.getIn();		
				String con=tran.getCondition();
				String out=tran.getOut();
				System.out.print("      ===>  in(约束条件)：");
				if("".equals(in.toString())){
					System.out.println("null");
				}
				else{
					System.out.println(in.toString());
				}
				System.out.println("      ===>  condition(约束条件)："+con);
				System.out.print("      ===>  out(输出信息)："+out.toString());												
				if("".equals(out.toString())){
					System.out.println("null");
				}
				else{
					System.out.println(out.toString());
				}

				ArrayList<String> events=tran.getEventSet();
				//				for(String e：events){
				//					System.out.println("      ===>  事件："+e);
				//				}								
			}			
			if(tran.getResetClockSet()==null){
				System.out.println("      ===>  重置时钟：null");
			}
			else if(tran.getResetClockSet().size()==0){
				System.out.println("      ===>  重置时钟：不空，但是没有内容");
			}
			else{
				ArrayList<String> reset=tran.getResetClockSet();
				for(String r:reset){
					System.out.println("      ===>  重置的时钟："+r);
				}
			}

			if(tran.getTypeIds()==null){
				//				System.out.println("      ===>  typeID：null");
			}
			else if(tran.getTypeIds().size()==0){
				//				System.out.println("      ===>  typeID：不空，但是没有内容");
			}
			else{
				ArrayList<String> typeid=tran.getTypeIds();
				for(String i:typeid){
					//					System.out.println("      ===>  typeId："+i);
				}
			}

			if(tran.getTypes()==null){
				//				System.out.println("      ===>  types：null");
			}
			else if(tran.getTypes().size()==0){
				//				System.out.println("      ===>  types：不空，但是没有内容");
			}
			else{
				ArrayList<String> type=tran.getTypes();
				for(String t:type){
					//					System.out.println("      ===>  types："+t);
				}
			}

			System.out.println("--------------------------");
		}
		System.out.println();
		//=====================================================================模型迁移(激励)信息获取结束	
		//=====================================================================读取xml结束	

	}


	/**
	 * 有时间自动机的输出格式
	 * @param xml
	 */
	public static  void output22(String xml){

		Automatic automatic1=GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
		Automatic auto=AddType.addType(automatic1);
		//ArrayList<State> new_stateSet=Minimization__1.minimization(automatic);
		Automatic new_automatic=IPR__1.iPR(auto);//获得拆分后的时间自动机
		Automatic aTDRTAutomatic=ATDTR__1.aTDRT(new_automatic,auto);//获得去除抽象时间迁移后的时间自动机
		//Automatic DFStree=StateCoverage__1.DFSTree(aTDRTAutomatic);
		ArrayList<Automatic> testCase=StateCoverage__1.testCase(aTDRTAutomatic);//获得满足状态覆盖的抽象测试序列
		ArrayList<ArrayList<String>> all_inequalitys=Get_inequality__1.get_AllInequalitys(testCase);//每个抽象测试序列有一个不等式组



		//########################################        第二步             ###############################################
		//#########################################################################################################
		//======================================================================进行符号拆分算法，存入对应数据结构中		
		//======================================================================正在进行符号状态拆分
		System.out.println("----------------------------获取符号状态拆分后的时间自动机信息----------------------------");		
		System.out.println("===========================正在进行符号状态拆分");
		System.out.println("===========================");

		//======================================================================进行符号拆分算法结束
		//########################################        第三步             ###############################################		
		//#########################################################################################################		


		//########################################        第四步             ###############################################	
		//#########################################################################################################
		//=====================================================================读取抽象测试序列
		//======================================================================满足状态覆盖的测试序列，存入对应数据结构中结束
		System.out.println("----------------------------获取满足状态覆盖的测试序列信息----------------------------");
		System.out.println("===========================正在遍历时间自动机");
		System.out.println("===========================");
		System.out.println("===========================正在生成深度优先生成树");
		System.out.println("===========================");
		System.out.println("===========================正在遍历深度优先生成树");
		System.out.println("===========================");
		System.out.println("===========================正在生成满足状态覆盖的抽象测试序列");
		System.out.println("===========================");
		System.out.println();
		System.out.println("===========================正在获取抽象测试序列详细信息");		
		System.out.println();
		System.out.println("  ===>  抽象测试序列个数："+testCase.size());
		System.out.println("-------------------------------------------------");
		for(Automatic a:testCase){			
			System.out.println("    ===>  测试用例名字："+a.getName());
			for(Transition tran:a.getTransitionSet()){	
				System.out.println("       -----------------------------");
				System.out.println("      ===>  迁移(激励)名称--->"+tran.getName());
				System.out.println("        ===>  迁移Id："+tran.getId());								
				System.out.println("        ===>  源状态名称："+tran.getSource());
				System.out.println("        ===>  目的状态名称："+tran.getTarget());	
				//未处理的约束条件	
				//				System.out.println("        ===>  约束："+tran.getEventSet());
				//输出in里面的约束
				System.out.print("        ===>  in(约束条件)："+tran.getIn());
				if(tran.getIn().equals("")){
					System.out.println("null");
				}else{
					System.out.println(tran.getIn());
				}					
				//输出out里面的约束
				System.out.print("        ===>  out(输出信息)：");
				if(tran.getOut().equals("")){
					System.out.println("null");
				}else{
					System.out.println(tran.getOut());
				}
				//输出condition里面的约束
				//System.out.println("        ===>  get-condition："+tran.getCondition());
				if(!tran.getCondition().equals("null")){
					tran.getCondition().replaceAll("false", "False").replaceAll("true", "True");				
					if(tran.getCondition().contains("/")){//取/后面的
						String conditonValue = tran.getCondition().split("/")[1];//要处理的不等式组
						System.out.println("        ===>  condition(约束条件)//："+conditonValue);
					}
					else{
						if(!tran.getCondition().contains("/")){
							System.out.println("        ===>  condition(约束条件)："+tran.getCondition());
						}
					}
				}
				else{
					if(tran.getCondition().equals("null")){
						System.out.println("        ===>  condition(约束条件)：null-->"+tran.getCondition());	
					}
				}																																									
			}
			System.out.println("---------------------------------------------------------");
			//System.out.println("---------------------------------------------------------");

		}
		System.out.println();
		//=====================================================================读取抽象测试序列结束
		//########################################        第五步             ###############################################
		//#########################################################################################################






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

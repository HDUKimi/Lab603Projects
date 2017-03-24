package com.horstmann.violet.application.gui.util.ckt.testcase;
//看输出对应map是否正确
import java.util.ArrayList;
import java.util.Set;

import com.horstmann.violet.application.gui.util.ckt.handle.*;


public class get_inout {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String xml="read_radio2ForXStream.xml";
		String xml="loop6ForXStream.xml";
		Automatic automatic=GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
		//Automatic auto = AddType.addType(automatic);

		ArrayList<Automatic> testCase=StateCoverage__1.testCase(automatic);//获得满足状态覆盖的抽象测试序列

		//int i=1;	
		System.out.println("抽象测试序列个数："+testCase.size());
		for(Automatic a:testCase){

			System.out.println("测试用例名字:"+a.getName());
			for(Transition tran:a.getTransitionSet()){
				//System.out.println(/*tran.getSource()+"---->"+tran.getTarget()+*/"约束： "+tran.getEventSet());

				//////////////////////////in处理开始///////////////////////////////////////
				//处理in里面的不等式和参数，使参数类型与参数一一对应
				//				System.out.println("--------------------------in------------------------");	
				//				System.out.println("in---->"+tran.getIn());					
				//				if(GetMap.get_inMap(tran.getIn())==null){
				//					//System.out.println("keySet集合：null，即没有不等式和参数");
				//					System.out.println("keySet集合："+tran.getIn());
				//				}
				//				else{
				//					Set<String> keySet = GetMap.get_inMap(tran.getIn()).keySet();
				//					System.out.println("keySet集合："+keySet);
				//					for (String key : keySet) {
				//						//System.out.println("key即参数类型："+GetMap.get_inMap(tran.getIn()).get(key));
				//						//System.out.println("key.trim()即参数名称："+key.trim());
				//						System.out.println("|参数名称|："+key.trim()+"----->"+"|参数类型|："+GetMap.get_inMap(tran.getIn()).get(key));
				//					}
				//				}
				//				

				/*System.out.println("in_map为："+get_bdscs.get_inbds(tran.getIn()).keySet());*/
				///////////////////////////in处理结束//////////////////////////////////////
				//////////////////////////out处理开始///////////////////////////////////////

				//				System.out.println("out---->"+tran.getOut());

				//////////////////////////out处理结束///////////////////////////////////////
				//////////////////////////condition处理开始///////////////////////////////////////
				/*if(tran.getCondition().contains("--")){
					String[] condition = tran.getCondition().split("--");
					for(int j=0;i<condition.length;i++){
						System.out.println("condition---->"+condition[j]);
					}
				}
				else{
					System.out.println("condition---->"+tran.getCondition());
				}*/
				//System.out.println(tran.getEventSet());
				System.out.println("--------------------------condition------------------------");	
				if(!tran.getCondition().equals("null")){
					tran.getCondition().replace("false", "False").replace("true", "True");
					if(tran.getCondition().contains("/")){
						String conditonValue = tran.getCondition().split("/")[1];
						System.out.println("//condition//---->"+conditonValue);
						///////////////////////////////////
						int m = 1;
						if(conditonValue.contains("--")){
							String[] tt = conditonValue.split("--");
							for(String t:tt){
								System.out.println(m+"： "+t);
							}
						}

						/////////////////////////////////

						//System.out.println("==================================");
						/*if(GetMap.get_condMap(conditonValue)==null){
							System.out.println("keySet集合："+GetMap.get_condMap(conditonValue));
						}
						else{
							if(!(GetMap.get_condMap(conditonValue)==null)){
								Set<String> set = GetMap.get_condMap(conditonValue).keySet();
								//System.out.println("=================================="+set.toString());
								System.out.println("keyset集合："+set);
								for (String key : set) {
									//System.out.println("key即参数类型："+get_bdscs.get_condMap(conditonValue).get(key));
									System.out.println("|参数名称|："+key.trim()+"----->"+"|参数类型|："+GetMap.get_condMap(conditonValue).get(key));
								}
							}
						}*/

					}
					else{
						if(!tran.getCondition().contains("/")){
							System.out.println("condition---->"+tran.getCondition());

							///////////////////////////////////
							int m = 1;
							if(tran.getCondition().contains("--")){
								String[] tt = tran.getCondition().split("--");
								for(String t:tt){
									System.out.println(m+"： "+t);
									m++;
								}
							}

							/////////////////////////////////


							/*	if(GetMap.get_condMap(tran.getCondition())==null){
								System.out.println("keySet集合："+GetMap.get_condMap(tran.getCondition()));
							}
							else{
								if(!(GetMap.get_condMap(tran.getCondition())==null)){
									//System.out.println("==================================");
									Set<String> set = GetMap.get_condMap(tran.getCondition()).keySet();
									//System.out.println("==================================");
									System.out.println("keyset集合："+set);
									for (String key : set) {
										//System.out.println("key即参数类型："+get_bdscs.get_condMap(tran.getCondition()).get(key));
										//System.out.println("key.trim()即参数名称："+key.trim());
										System.out.println("|参数名称|："+key.trim()+"----->"+"|参数类型|："+GetMap.get_condMap(tran.getCondition()).get(key));
									}
								}
							}*/

						}
					}
				}
				else{
					if(tran.getCondition().equals("null")){
						System.out.println("condition--null-->"+tran.getCondition());
						/*						Set<String> keySet = get_bdscs.get_condMap(tran.getIn()).keySet();
						System.out.println("keySet集合："+keySet);
						for (String key : keySet) {
							System.out.println("key即参数类型："+get_bdscs.get_condMap(tran.getIn()).get(key));
							System.out.println("key.trim()即参数名称："+key.trim());
						 */		}	
				}

			}



			//////////////////////////condition处理结束///////////////////////////////////////
			/*if(tran.getCondition().contains("/")){
				String[] t=tran.getCondition().split("/");
				System.out.println("condition---->"+t[1]);
			}
			else{
				System.out.println("condition---->"+tran.getCondition());
			}*/



			/*	for(Transition tran:a.getTransitionSet()){
				System.out.println(tran.getName()+"----********----迁移边名称");
				System.out.println(tran.getSource()+"---->"+tran.getTarget()+"约束： "+tran.getEventSet());

				//System.out.println("第二个输出：i等于"+i+"----"+Get_yueshu.get_yueshu(tran.getEventSet().toString()));
				//System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				//i++;
			}*/
			/*for(Transition tran:a.getTransitionSet()){
				System.out.println(tran.getSource()+"---->"+tran.getTarget()+"第一个输出：约束： "+tran.getEventSet());
				System.out.println(tran.getName()+"----********----迁移边名称");
				System.out.println("第二个输出：i等于"+i+"----"+Get_yueshu.get_yueshu(tran.getEventSet().toString()));
				//System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				//i++;
			}*/
			System.out.println("*********************************");
		}
	}
}




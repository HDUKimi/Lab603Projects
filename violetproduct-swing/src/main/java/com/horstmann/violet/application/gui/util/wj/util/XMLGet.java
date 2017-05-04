package com.horstmann.violet.application.gui.util.wj.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.horstmann.violet.application.gui.util.ckt.handle.Automatic;
import com.horstmann.violet.application.gui.util.ckt.handle.GetAutomatic;
import com.horstmann.violet.application.gui.util.ckt.handle.StateCoverage__1;
import com.horstmann.violet.application.gui.util.ckt.handle.Transition;
import com.horstmann.violet.application.gui.util.ckt.testcase.*;

public class XMLGet {
	public static void main(String[] args) {
		//String xml="UAVForXStream3.8.2.0.xml";
//		String xml = "UAVForXStreamPerfromTestV2.xml";
		String xml = "D:\\ModelDriverProjectFile\\UPPAL\\3.Abstract_TestCase\\FunctionalTest\\EA4.1.0 功能场景1ForXStream.xml";
		//Automatic automatic = GetAutomatic.getAutomatic("UAVForXStream3.8.0.xml");
		//GeneratePath.getFormatPathFromAutomatic(automatic, 8000);
		//
		Automatic auto=GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
		ArrayList<Automatic> testCase=GeneratePath.getFormatPathFromAutomatic(auto, 10);//获得满足状态覆盖的抽象测试序列

		// 1、创建document对象，代表整个xml文档
		Document dom = DocumentHelper.createDocument();
		// 2、创建根节点TCS
		org.dom4j.Element tcs = dom.addElement("TCS");
		// 3、向TCS节点中添加version属性

		int i = 1;				
		for(Automatic a:testCase){	///////////////////////////////////			
			List<List<String>> cases = new ArrayList<List<String>>(); // 测试用例集合
			System.out.println("===========================正在读取第"+i+"条测试用例");
			System.out.println("  ===>  测试用例名字:"+a.getName());
			int j = 1;
			// 4、生成子节点及节点内容
			//Element testcase = tcs.addElement("testcase");

			for(Transition tran:a.getTransitionSet()){
				System.out.println();
				System.out.println("======第"+j+"条迁移开始======");
				System.out.println("迁移内容:"+tran.getIn()+"---"+tran.getCondition());
				//添加节点
				//Element process = testcase.addElement("process");
				//Element operation = process.addElement("operation");
				String sss = new String();
				List<String> result1=new ArrayList<String>();//存放in里面最终实例化结果
				List<String> result2=new ArrayList<String>();//存放condition里面最终实例化结果
				if(tran.getName().contains("(")){
					int index11=tran.getName().replace("!", "").replace("?", "").indexOf("(");
					sss=tran.getName().substring(0,index11);
					System.out.println("迁移(激励)名称："+sss);
					//operation.setText(sss);
				}
				else{
					sss = tran.getName().replace("!", "").replace("?", "");
					System.out.println("迁移(激励)名称："+sss);
					//operation.setText(sss);
				}


				System.out.println("迁移Id："+tran.getId());								
				//System.out.println("源状态名称："+tran.getSource());
				//System.out.println("目的状态名称："+tran.getTarget());

				//未处理的约束条件	
				//System.out.println("约束："+tran.getEventSet());//约束不等式
				////////////////////////////////////////////////////////////////
				///////////////////////////////       in处理开始            /////////////////////////

				//处理in里面的不等式和参数，得到参数类型与参数一一对应的map，进行添加不等式操作
				//System.out.println("========================in========================");	
				System.out.println("in---->"+tran.getIn());	//in里面的内容	
				if(tran.getIn().equals("null")){	
					result1.add(null);
				}else{

					if(tran.getIn().contains("--")){
						List<List<String>> in_result = new ArrayList<List<String>>();
						List<String> in_result1;
						//String rrr = null;
						String getin[] = tran.getIn().split("--");
						for(int ii=0;ii<getin.length;ii++){
							if(!(GetMap.get_inMap(getin[ii])==null)){
								String inn = getin[ii].replace("false", "False").replace("true", "True").replace("->", "$");
								in_result1 = Result1.getResult(inn);
								if((in_result1.size()>0)&&!(in_result1.get(0).equals(null))){
									in_result.add(in_result1);
								}
							}
						}
						if((in_result.size()>0)&&!(in_result.get(0).equals(null))){
							System.out.println("in_result.size()--->"+in_result.size());
							dis(0,in_result);
							result1 = re;
						}
					}else{
						if(!(GetMap.get_inMap(tran.getIn())==null)){//map里面为空，即没有参数
							String inn = tran.getIn().replace("false", "False").replace("true", "True").replace("->", "$");
							result1 = Result1.getResult(inn);
						}else{
							if((GetMap.get_inMap(tran.getIn())==null)){
								result1.add(null);
							}
						}
					}
				
					
					/*
					if(tran.getIn().contains("--")){
						List<List<String>> in_result = new ArrayList<List<String>>();
						List<String> in_result1;
						//String rrr = null;
						String getin[] = tran.getIn().split("--");
						for(int ii=0;ii<getin.length;ii++){
							if(!(GetMap.get_inMap(getin[ii])==null)){
								String inn = getin[ii].replace("false", "False").replace("true", "True").replace("->", "$");
								in_result1 = Result1.getResult(inn);
								if((in_result1.size()>0)&&!(in_result1.get(0).equals(null))){
									in_result.add(in_result1);
								}
							}
						}
						if((in_result.size()>0)&&!(in_result.get(0).equals(null))){
							if(in_result.size()==2){
								for(String rr1:in_result.get(0)){
									for(String rr2:in_result.get(1)){
										if((rr1!=null)&&(rr2!=null)){
											String rr3 = rr1+","+rr2;
											result1.add(rr3);
										}
									}
								}		
							}else{
								if(in_result.size()==3){
									for(String rr1:in_result.get(0)){
										for(String rr2:in_result.get(1)){
											for(String rr3:in_result.get(2))
											if((rr1!=null)&&(rr2!=null)){
												String rr4 = rr1+","+rr2+","+rr3;
												result1.add(rr4);
											}
										}
									}		
								}
							}
							
						}
										
					}else{
						if(!(GetMap.get_inMap(tran.getIn())==null)){//map里面为空，即没有参数
							String inn = tran.getIn().replace("false", "False").replace("true", "True").replace("->", "$");
							result1 = Result1.getResult(inn);
						}else{
							if((GetMap.get_inMap(tran.getIn())==null)){
								result1.add(null);
							}
						}
				}
					
					
					if(GetMap.get_inMap(tran.getIn())==null){//map里面为空，即没有参数
						//System.out.println("keySet集合："+tran.getIn());
						result1.add(null);
					}
					else{//map里有值，即有参数和参数对应类型
						String inn = tran.getIn().replace("false", "False").replace("true", "True").replace("->", "$");
						result1 = Result1.getResult(inn);
						for(int ii=0;ii<result1.size();ii++){
							//System.out.println("in里解"+ii+"为:"+result1.get(ii));
						}
					}
				*/}
				
				//System.out.println("===========================in处理结束===========================");
				/////////////////////////////////////////////condition处理开始///////////////////////////////////////
				//System.out.println("condition---->"+tran.getCondition());
				if(tran.getCondition().equals("null")){	
					result2.add(null);
				}else{
					if(!tran.getCondition().equals("null")){
						if(GetMap.get_condMap(tran.getCondition())==null){
							result2.add(null);
						}else{
							if(!(GetMap.get_condMap(tran.getCondition())==null)){
								String tra = tran.getCondition().replace("false", "False").replace("true", "True").replace("->", "$");
//								result2 = Result.getResult(tra);
								System.out.println("tra----"+tra);
								result2 = Result1.getResult(tra);
								
								//result2 = testbdscs.getResult(tra);						
								for(int ii=0;ii<result2.size();ii++){
									//System.out.println("condition里解"+ii+"为:"+result2.get(ii));
								}
							}
						}

					}					
				}

				////////////////////////////////////////////////condition处理结束///////////////////////////////////////

				//System.out.println("******************************************");
				/////////////////////////////////////////////////////////////////////////////////////



				//
				//System.out.println("result1--->"+result1.size()+"---"+result1);
				//System.out.println("result2--->"+result2.size()+"---"+result2);
				/*if(result1.size()>=0){
					System.out.println("result1--->"+result1.size()+"---"+result1);
					System.out.println("result2--->"+result2.size()+"---"+result2);
				}else{
					//if(result1.size())
						System.out.println("result1===>"+result1.size()+"---"+result1);
					    System.out.println("result2===>"+result2.size()+"---"+result2);
				}*/
				//System.out.println("result1--->"+result1.size()+"---"+result1);
				//System.out.println("result2--->"+result2.size()+"---"+result2);
				////////////////////为input添加求出来的解
				List<String> result=new ArrayList<String>();//存放一条迁移上的结果
				String res = new String();
				if((result1.toString().equals("[null]"))&&(result2.toString().equals("[null]"))){
					res = sss+"%"+null;
					//res = null;
					result.add(res.toString());
				}else{
					if(!(result1.toString().equals("[null]"))&&(result2.toString().equals("[null]"))){
						for(String ttt2:result1){
							if(ttt2!=null){
								if(ttt2.contains("flag=1")){
									res = sss+"flag=1"+"%"+ttt2.replace("flag=1", "");
								}else{
									res = sss+"%"+ttt2;
								}
								
								//res = ttt2;
								result.add(res.toString());
							}
						}
					}
					if((result1.toString().equals("[null]"))&&!(result2.toString().equals("[null]"))){
						for(String ttt3:result2){
							if(ttt3!=null){
								if(ttt3.contains("flag=1")){
									res = sss+"flag=1"+"%"+ttt3.replace("flag=1", "");
								}else{
									res = sss+"%"+ttt3;
								}
								
								//res = sss+"%"+ttt3;
								//res = ttt3;
								result.add(res.toString());
							}
						}
					}
					if(!(result1.toString().equals("[null]"))&&!(result2.toString().equals("[null]"))){
						for(String ttt2:result1){
							for(String ttt3:result2){
								if(ttt2!=null&&ttt3!=null){
									if((ttt2.contains("flag=1"))||(ttt3.contains("flag=1"))){
										res = sss+"flag=1"+"%"+ttt2.replace("flag=1", "")+","+ttt3.replace("flag=1", "");
									}else{
										if(!(ttt2.contains("flag=1"))&&!(ttt3.contains("flag=1"))){
											res = sss+"%"+ttt2+","+ttt3;
										}
									}
									
									
									//res = sss+"%"+ttt2+","+ttt3;
									//res = ttt2+","+ttt3;
									result.add(res.toString());
								}									
							}
						}
					}
				}	
                System.out.println("result--------------"+result);
				if(result.size()==0){
					//System.out.println("-----------------0000000---------------------");
					//Element input = process.addElement("input");
					//input.setText("解1为:"+null);
				}else{
					for(int ii=1;ii<=result.size();ii++){
						System.out.println("解"+ii+"为:"+result.get(ii-1));//输出所有解
						String s = "解"+ii+"为:"+result.get(ii-1);
						//Element input = process.addElement("input");
						//input.setText(s);
					}					
				}


				cases.add(result);
				////////////////////////////////////////////////////////////////////////////////////
				//System.out.println("                 ======第"+j+"条迁移结束======");
				j++;
			}//for(Transition tran:a.getTransitionSet())

			//cases里放的是一条测试用例上上每条迁移上的解，result放的是一条迁移上的多组解
			/////
			int numm=1;
			for(int nn=0;nn<cases.size();nn++){
				int n = cases.get(nn).size();
				numm = numm*n;
				//System.out.println("第"+nn+"条迁移上解个数为："+cases.get(nn).size());
			}
			System.out.println("第"+i+"条测试路径上解个数"+numm);
//			if(num>5000){ //测试用例个数保持5000条以内
//				num = 5000;
//			}
			int num=1;//一条路径100个测试用例///////////////////////////////////////////////////////////////////////////////////
			if(num>numm){   //如果一条路径上解小于100，则选取真实个数
				num = numm;
			}
			for(int n1=0;n1<num;n1++){
				// 4、生成子节点及节点内容
				Element testcase = tcs.addElement("testcase");
				//System.out.println("---------------------testcase"+n1);
				for(int nn=0;nn<cases.size();nn++){//cases.size表示边的个数
					//添加节点
					Element process = testcase.addElement("process");
					Element operation = process.addElement("operation");

					int random = -1;
					if (random == -1) {
						random = new Random().nextInt(cases.get(nn).size());
					}
					//System.out.println("random-->"+random);
					String value = cases.get(nn).get(random);
					//System.out.println("解value-->"+value);
					String[] cs =value.toString().split("%");
					//System.out.println("operation-->"+cs[0]);
					if(cs[0].contains("flag=1")){
						String name = cs[0].replace("flag=1", "");
						operation.setText(name);
					}else{
						if(!cs[0].contains("flag=1")){
							operation.setText(cs[0]);
						}
					}
					
					Element input = process.addElement("input");
					if(cs[0].contains("flag=1")){
						input.addAttribute("border","1");
						input.setText(cs[1]);
					}else{
						input.setText(cs[1]);
					}
					//input.setText(cs[1]);
					//System.out.println("input-->"+cs[1]);
				}
				//System.out.println("---------------------testcase");
				//System.out.println(a.getName());
			}
			////////
			

			//System.out.println("===========================第"+i+"条测试用例读取完成");
			i++;
		
		
		}//for(Automatic a:testCase)

		System.out.println("抽象测试序列个数："+testCase.size());
		System.out.println();


		/////////////////////



		/////////////////////

		OutputFormat format = OutputFormat.createPrettyPrint();
        //6、生成xml文件
//		File file = new File("C:\\Users\\Administrator\\Desktop\\tcss.xml");
//		File file = new File("E:\\项目\\xml\\UAVForXStream3.2.1+.xml");m
//		File file = new File("E:\\项目\\xml\\rc_loopForXStream1.01.xml");
//		File file = new File("E:\\项目\\xml\\arm_motors_checkForXStream1.01.xml");
		File file = new File("E:\\项目\\xml\\UAVForXStreamPerfromTestV2+border+path.xml");
	//	File file = new File("E:\\项目\\xml\\UAVForXStream3.8.2.0.xml+border+path.xml");
		
		//File file = new File("E:\\项目\\xml\\UAVForXStream3.5.0.xml");
		//File file = new File("E:\\项目\\xml\\test.xml");
		//File file = new File("E:\\xml\\tcs2.xml");

		XMLWriter writer;
		try {
			writer = new XMLWriter(new FileOutputStream(file), format);
			writer.write(dom);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
	
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


package com.horstmann.violet.application.gui.util.ckt.testcase;
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

import com.horstmann.violet.application.gui.util.ckt.handle.*;
import com.horstmann.violet.application.gui.util.ckt.output.ShowInfor;
import com.horstmann.violet.application.gui.util.ckt.testcase.*;
import com.horstmann.violet.application.gui.util.wj.util.*;

public class PerformanceXML2 {
	public static void main(String[] args) throws Exception {
		//String xml="UAVForXStreamPerformTestV6.xml";//性能测试，主要测试高度、风速、风向
		//String xml="UAVForXStreamGaoDu-v6.xml"; //性能测试，主要测高度
		//String xml = "UAVForXStreamXuanTing.xml";//性能测试，主要测悬停
		//String xml = "UAVForXStreamGAODU.xml";//性能测试，测高度，从Excel中读取高度值
		String xml = "UAVForXStreamGaoDuV9.xml";
		Automatic auto=GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
		Automatic a=GeneratePath.getPerformPathFromAutomatic(auto);//获得满足状态覆盖的抽象测试序列

		// 1、创建document对象，代表整个xml文档
		Document dom = DocumentHelper.createDocument();
		// 2、创建根节点TCS
		org.dom4j.Element tcs = dom.addElement("TCS");
		// 3、向TCS节点中添加version属性
		for(int Fspeed=0;Fspeed<=16;Fspeed=Fspeed+2){
			int i = 1;					
			List<List<String>> cases = new ArrayList<List<String>>(); // 测试用例集合
			List<String> outtt=new ArrayList<String>();//out输出
			List<List<String>> syso=new ArrayList<List<String>>();
			ShowInfor.print("===========================正在读取第"+i+"条测试用例");
			ShowInfor.print("  ===>  测试用例名字:"+a.getName());
			int j = 1;
			for(Transition tran:a.getTransitionSet()){
				ShowInfor.print();
				ShowInfor.print("======第"+j+"条迁移开始======");
				ShowInfor.print("迁移内容:"+tran.getIn()+"---"+tran.getCondition());
				String sss = new String();
				List<String> result1=new ArrayList<String>();//存放in里面最终实例化结果			
				List<String> result2=new ArrayList<String>();//存放condition里面最终实例化结果
				if(tran.getName().contains("(")){
					int index11=tran.getName().replace("!", "").replace("?", "").indexOf("(");
					sss=tran.getName().substring(0,index11);
					ShowInfor.print("迁移(激励)名称："+sss);
				}
				else{
					sss = tran.getName().replace("!", "").replace("?", "");
					ShowInfor.print("迁移(激励)名称："+sss);
				}
				ShowInfor.print("迁移Id："+tran.getId());								
				//ShowInfor.print("源状态名称："+tran.getSource());
				//ShowInfor.print("目的状态名称："+tran.getTarget());
				//未处理的约束条件	
				//ShowInfor.print("约束："+tran.getEventSet());//约束不等式


				/**
				 * 处理in里面的不等式和参数，实例化in
				 */
				//处理in里面的不等式和参数，得到参数类型与参数一一对应的map，进行添加不等式操作
				//ShowInfor.print("========================in========================");	
				ShowInfor.print("in---->"+tran.getIn());	//in里面的内容
				if(tran.getIn().equals("null")){	
					result1.add(null);
				}else{	
					result1=Result_2.preInResult(tran.getIn(),Fspeed);
					syso.add(result1);
				}
				ShowInfor.print("-------------------"+result1.toString());

				/**
				 * 处理condition里面的不等式和参数，实例化condition
				 */

				//ShowInfor.print("condition---->"+tran.getCondition());
				if(tran.getCondition().equals("null")){	
					result2.add(null);
				}else{
					if(!tran.getCondition().equals("null")){
						if(GetMap.get_condMap(tran.getCondition())==null){
							result2.add(null);
						}else{
							if(!(GetMap.get_condMap(tran.getCondition())==null)){
								String tra = tran.getCondition().replace("false", "False").replace("true", "True").replace("->", "$");
								//result2 = Result.getResult(tra);
								ShowInfor.print("tra----"+tra);
								result2 = Result1.getResult(tra);
								//result2 = testbdscs.getResult(tra);						
								for(int ii=0;ii<result2.size();ii++){
									//ShowInfor.print("condition里解"+ii+"为:"+result2.get(ii));
								}
							}
						}

					}					
				}

				/**
				 * 处理out里面要输出的信息，存储到xml中
				 * ckt要输出信息ckt
				 */
				String outP=new String();
				String s1=new String();
				if(tran.getOut().contains("ckt")){
					String[] out = tran.getOut().split(",");
					List<String> output = new ArrayList<String>();
					for(String s:out){
						if(s.contains("ckt")){
							String ss = s.replace("ckt", "");
							output.add(ss);
						}
					}					
					if(output.size()>1){
						outP=output.get(0);
						for(int j1=1;j1<output.size();j1++){
							s1=output.get(j);
							outP=outP+","+s1;
						}
					}
					if(output.size()==1){
						outP=output.get(0);
					}	
					if(output.size()<=0){
						outP="null";
					}
					outtt.add(outP);
				}else{
					outP="null";
					outtt.add(outP);
				}
				/**
				 * 把in与condition上解组合在一起
				 * 格式：激励名称%in与condition上解%out里要输出的信息
				 * 组合的放在result内
				 */
				List<String> result=new ArrayList<String>();//存放一条迁移上的结果
				String res = new String();
				if((result1.toString().equals("[null]"))&&(result2.toString().equals("[null]"))){
					res = sss+"%"+null+"%"+outP;
					//res = null;
					result.add(res.toString());
				}else{
					if(!(result1.toString().equals("[null]"))&&(result2.toString().equals("[null]"))){
						for(String ttt2:result1){
							if(ttt2!=null){
								if(ttt2.contains("flag=1")){
									res = sss+"flag=1"+"%"+ttt2.replace("flag=1", "")+"%"+outP;
								}else{
									res = sss+"%"+ttt2+"%"+outP;
								}

								//res = ttt2;
								result.add(res.toString());
							}
						}
						
					}
					if((result1.toString().equals("[null]"))&&!(result2.toString().equals("[null]"))){
						int random = -1;
						if (random == -1) {
							random = new Random().nextInt(result2.size());
						}
						String ttt3=result2.get(random);
							if(ttt3!=null){
								if(ttt3.contains("flag=1")){
									res = sss+"flag=1"+"%"+ttt3.replace("flag=1", "")+"%"+outP;
								}else{
									res = sss+"%"+ttt3+"%"+outP;
								}
								//res = sss+"%"+ttt3;
								//res = ttt3;
								result.add(res.toString());
							}
					}
					if(!(result1.toString().equals("[null]"))&&!(result2.toString().equals("[null]"))){
						/*for(String ttt2:result1){
							for(String ttt3:result2){
								if(ttt2!=null&&ttt3!=null){
									if((ttt2.contains("flag=1"))||(ttt3.contains("flag=1"))){
										res = sss+"flag=1"+"%"+ttt2.replace("flag=1", "")+","+ttt3.replace("flag=1", "")+"%"+outP;
									}else{
										if(!(ttt2.contains("flag=1"))&&!(ttt3.contains("flag=1"))){
											res = sss+"%"+ttt2+","+ttt3+"%"+outP;
										}
									}
									//res = sss+"%"+ttt2+","+ttt3;
									//res = ttt2+","+ttt3;
									result.add(res.toString());
								}									
							}
						}*/
						for(String ttt2:result1){
							int random = -1;
							if (random == -1) {
								random = new Random().nextInt(result2.size());
							}
							String ttt3=result2.get(random);
								if(ttt2!=null&&ttt3!=null){
									if((ttt2.contains("flag=1"))||(ttt3.contains("flag=1"))){
										res = sss+"flag=1"+"%"+ttt2.replace("flag=1", "")+","+ttt3.replace("flag=1", "")+"%"+outP;
									}else{
										if(!(ttt2.contains("flag=1"))&&!(ttt3.contains("flag=1"))){
											res = sss+"%"+ttt2+","+ttt3+"%"+outP;
										}
									}
									result.add(res.toString());
								}									
						}
					}
				}	
//				ShowInfor.print("result--------------"+result);
				if(result.size()==0){
					//input.setText("解1为:"+null);
				}else{
					if(result.get(0).contains("takeoff_alt_cm")){
						for(int ii=1;ii<=result.size();ii++){
							ShowInfor.print("解==========="+ii+"为:"+result.get(ii-1));//输出所有解
							String s = "解==========="+ii+"为:"+result.get(ii-1);
						}
					}
										
				}
				cases.add(result);
				////////////////////////////////////////////////////////////////////////////////////
				//ShowInfor.print("                 ======第"+j+"条迁移结束======");
				j++;
			}//for(Transition tran:a.getTransitionSet())
			/**
			 * 数据写进xml文档里
			 */
			/*List<String> gaodu = null;//高度解集合
			String other = new String();//除了高度其他随机取的解
			for(int n = 0;n<cases.size();n++){
				if(cases.get(n).contains("takeoff_alt_cm")){
					gaodu = cases.get(n);
				}else{
					if(!cases.get(n).contains("takeoff_alt_cm")){
						int random = -1;
						if (random == -1) {
							random = new Random().nextInt(cases.get(n).size());
						}					
						cases.get(n).get(random);
					}
				}
			}*/
			//把解拼接在一起
			/*List<String> casess = new ArrayList<String>();
			dis(0,cases);
			casess = re;
			List<String> ret = new ArrayList<String>();
			re = ret;*/
			//cases里放的是一条测试用例上上每条迁移上的解，cases的size是多少条迁移，result放的是一条迁移上的多组解
			/////
			/*int numm=1;
			for(int nn=0;nn<cases.size();nn++){
				int n = cases.get(nn).size();
				numm = numm*n;
				//ShowInfor.print("第"+nn+"条迁移上解个数为："+cases.get(nn).size());
			}*/
			/*int numm = casess.size();
			ShowInfor.print("第"+i+"条测试路径上解个数"+numm);
			int num=1000;//一条路径100个测试用例///////////////////////////////////////////////////////////////////////////////////
			if(num>numm){   //如果一条路径上解小于100，则选取真实个数
				num = numm;
			}*/
			for (int j2 = 0; j2 < syso.size(); j2++) {
				//if(syso.get(j2).get(0).contains("takeoff_alt_cm")){
					ShowInfor.print("============================="+syso.get(j2).size()+"-----"+syso.get(j2).toString());
				//}
				
			}
			
			int num = 0;
			int min;
			//生成测试用例个数是含有高度迁移的组合数
			for(int n = 0;n<cases.size();n++){			
				if(cases.get(n).get(0).contains("takeoff_alt_cm")){	
					ShowInfor.print("里面解个数："+cases.get(n).size());
					min = cases.get(n).size();
					if(num<min){
						num = min;
					}
				}
			}
			//ShowInfor.print("测试用例的个数为："+num);
			//ShowInfor.print("in里面解个数："+cases);
			for(int n1=0;n1<num;n1++){
				// 4、生成子节点及节点内容
				Element testcase = tcs.addElement("testcase");
				int m = n1;
				//ShowInfor.print("---------------------testcase"+n1);
				for(int nn=0;nn<cases.size();nn++){//cases.size表示边的个数
					//添加节点
					Element process = testcase.addElement("process");
					Element operation = process.addElement("operation");
					String value = new String();
					if(!cases.get(nn).get(0).contains("takeoff_alt_cm")){
						int random = -1;
						if (random == -1) {
							random = new Random().nextInt(cases.get(nn).size());
						}
						//ShowInfor.print("random-->"+random);
						value = cases.get(nn).get(random);
					}else{
						//cases.get(nn).get(0).substring(18).contains("takeoff_alt_cm")
						if(cases.get(nn).get(0).contains("takeoff_alt_cm")){
							if(cases.get(nn).size()<10){
								int random = -1;
								if (random == -1) {
									random = new Random().nextInt(cases.get(nn).size());
								}
								value = cases.get(nn).get(random);	
							}else{
								//ShowInfor.print("第"+n1+"个测试用例");
								//ShowInfor.print("======"+n1);
								//ShowInfor.print(cases.get(nn).size());
								value = cases.get(nn).get(m);
							}						
						}					
					}
					//ShowInfor.print("解value-->"+value);
					String[] cs =value.toString().split("%");
					//ShowInfor.print("operation-->"+cs[0]);
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
					//ShowInfor.print("input-->"+cs[1]);
					Element output = process.addElement("output");
					//ShowInfor.print(outtt.get(nn).toString());
					output.setText(cs[2]);

				}
				//ShowInfor.print("---------------------testcase");
				//ShowInfor.print(a.getName());
			}
			////////
			//ShowInfor.print("===========================第"+i+"条测试用例读取完成");
			i++;


			//}//for(Automatic a:testCase)

			//ShowInfor.print("抽象测试序列个数："+testCase.size());
			ShowInfor.print();
			/////////////////////
			/////////////////////
		}
		

		OutputFormat format = OutputFormat.createPrettyPrint();
		//6、生成xml文件
		File file = new File("E:\\项目\\XML\\UAVForXStreamV9-----+16+border+path+perform.xml");   
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
	
	public static List<List<List<String>>> allcases=new ArrayList<>();
	
	public static Automatic getPerformResultFromAutomatic(Automatic automatic){
		
		allcases=new ArrayList<>();
		
		for(int Fspeed=0;Fspeed<=16;Fspeed=Fspeed+2){
			int i = 1;					
			List<List<String>> cases = new ArrayList<List<String>>(); // 测试用例集合
			List<String> outtt=new ArrayList<String>();//out输出
			List<List<String>> syso=new ArrayList<List<String>>();
			ShowInfor.print("===========================正在读取第"+i+"条测试用例");
			ShowInfor.print("  ===>  测试用例名字:"+automatic.getName());
			int j = 1;
			for(Transition tran:automatic.getTransitionSet()){
				ShowInfor.print();
				ShowInfor.print("======第"+j+"条迁移开始======");
				ShowInfor.print("迁移内容:"+tran.getIn()+"---"+tran.getCondition());
				String sss = new String();
				List<String> result1=new ArrayList<String>();//存放in里面最终实例化结果			
				List<String> result2=new ArrayList<String>();//存放condition里面最终实例化结果
				if(tran.getName().contains("(")){
					int index11=tran.getName().replace("!", "").replace("?", "").indexOf("(");
					sss=tran.getName().substring(0,index11);
					ShowInfor.print("迁移(激励)名称："+sss);
				}
				else{
					sss = tran.getName().replace("!", "").replace("?", "");
					ShowInfor.print("迁移(激励)名称："+sss);
				}
				ShowInfor.print("迁移Id："+tran.getId());								
				//ShowInfor.print("源状态名称："+tran.getSource());
				//ShowInfor.print("目的状态名称："+tran.getTarget());
				//未处理的约束条件	
				//ShowInfor.print("约束："+tran.getEventSet());//约束不等式


				/**
				 * 处理in里面的不等式和参数，实例化in
				 */
				//处理in里面的不等式和参数，得到参数类型与参数一一对应的map，进行添加不等式操作
				//ShowInfor.print("========================in========================");	
				ShowInfor.print("in---->"+tran.getIn());	//in里面的内容
				if(tran.getIn().equals("null")){	
					result1.add(null);
				}else{	
					result1=Result_2.preInResult(tran.getIn(),Fspeed);
					syso.add(result1);
				}
				ShowInfor.print("-------------------"+result1.toString());

				/**
				 * 处理condition里面的不等式和参数，实例化condition
				 */

				//ShowInfor.print("condition---->"+tran.getCondition());
				if(tran.getCondition().equals("null")){	
					result2.add(null);
				}else{
					if(!tran.getCondition().equals("null")){
						if(GetMap.get_condMap(tran.getCondition())==null){
							result2.add(null);
						}else{
							if(!(GetMap.get_condMap(tran.getCondition())==null)){
								String tra = tran.getCondition().replace("false", "False").replace("true", "True").replace("->", "$");
								//result2 = Result.getResult(tra);
								ShowInfor.print("tra----"+tra);
								result2 = Result1.getResult(tra);
								//result2 = testbdscs.getResult(tra);						
								for(int ii=0;ii<result2.size();ii++){
									//ShowInfor.print("condition里解"+ii+"为:"+result2.get(ii));
								}
							}
						}

					}					
				}

				/**
				 * 处理out里面要输出的信息，存储到xml中
				 * ckt要输出信息ckt
				 */
				String outP=new String();
				String s1=new String();
				if(tran.getOut().contains("ckt")){
					String[] out = tran.getOut().split(",");
					List<String> output = new ArrayList<String>();
					for(String s:out){
						if(s.contains("ckt")){
							String ss = s.replace("ckt", "");
							output.add(ss);
						}
					}					
					if(output.size()>1){
						outP=output.get(0);
						for(int j1=1;j1<output.size();j1++){
							s1=output.get(j);
							outP=outP+","+s1;
						}
					}
					if(output.size()==1){
						outP=output.get(0);
					}	
					if(output.size()<=0){
						outP="null";
					}
					outtt.add(outP);
				}else{
					outP="null";
					outtt.add(outP);
				}
				/**
				 * 把in与condition上解组合在一起
				 * 格式：激励名称%in与condition上解%out里要输出的信息
				 * 组合的放在result内
				 */
				List<String> result=new ArrayList<String>();//存放一条迁移上的结果
				String res = new String();
				if((result1.toString().equals("[null]"))&&(result2.toString().equals("[null]"))){
					res = sss+"%"+null+"%"+outP;
					//res = null;
					result.add(res.toString());
				}else{
					if(!(result1.toString().equals("[null]"))&&(result2.toString().equals("[null]"))){
						for(String ttt2:result1){
							if(ttt2!=null){
								if(ttt2.contains("flag=1")){
									res = sss+"flag=1"+"%"+ttt2.replace("flag=1", "")+"%"+outP;
								}else{
									res = sss+"%"+ttt2+"%"+outP;
								}

								//res = ttt2;
								result.add(res.toString());
							}
						}
						
					}
					if((result1.toString().equals("[null]"))&&!(result2.toString().equals("[null]"))){
						int random = -1;
						if (random == -1) {
							random = new Random().nextInt(result2.size());
						}
						String ttt3=result2.get(random);
							if(ttt3!=null){
								if(ttt3.contains("flag=1")){
									res = sss+"flag=1"+"%"+ttt3.replace("flag=1", "")+"%"+outP;
								}else{
									res = sss+"%"+ttt3+"%"+outP;
								}
								//res = sss+"%"+ttt3;
								//res = ttt3;
								result.add(res.toString());
							}
					}
					if(!(result1.toString().equals("[null]"))&&!(result2.toString().equals("[null]"))){
						/*for(String ttt2:result1){
							for(String ttt3:result2){
								if(ttt2!=null&&ttt3!=null){
									if((ttt2.contains("flag=1"))||(ttt3.contains("flag=1"))){
										res = sss+"flag=1"+"%"+ttt2.replace("flag=1", "")+","+ttt3.replace("flag=1", "")+"%"+outP;
									}else{
										if(!(ttt2.contains("flag=1"))&&!(ttt3.contains("flag=1"))){
											res = sss+"%"+ttt2+","+ttt3+"%"+outP;
										}
									}
									//res = sss+"%"+ttt2+","+ttt3;
									//res = ttt2+","+ttt3;
									result.add(res.toString());
								}									
							}
						}*/
						for(String ttt2:result1){
							int random = -1;
							if (random == -1) {
								random = new Random().nextInt(result2.size());
							}
							String ttt3=result2.get(random);
								if(ttt2!=null&&ttt3!=null){
									if((ttt2.contains("flag=1"))||(ttt3.contains("flag=1"))){
										res = sss+"flag=1"+"%"+ttt2.replace("flag=1", "")+","+ttt3.replace("flag=1", "")+"%"+outP;
									}else{
										if(!(ttt2.contains("flag=1"))&&!(ttt3.contains("flag=1"))){
											res = sss+"%"+ttt2+","+ttt3+"%"+outP;
										}
									}
									result.add(res.toString());
								}									
						}
					}
				}	
//				ShowInfor.print("result--------------"+result);
				if(result.size()==0){
					//input.setText("解1为:"+null);
				}else{
					if(result.get(0).contains("takeoff_alt_cm")){
						for(int ii=1;ii<=result.size();ii++){
							ShowInfor.print("解==========="+ii+"为:"+result.get(ii-1));//输出所有解
							String s = "解==========="+ii+"为:"+result.get(ii-1);
						}
					}
										
				}
				cases.add(result);
				////////////////////////////////////////////////////////////////////////////////////
				//ShowInfor.print("                 ======第"+j+"条迁移结束======");
				j++;
				
				tran.setResult(result);
			}//for(Transition tran:a.getTransitionSet())
			
			allcases.add(cases);
			i++;
		}
		
		return automatic;
	}
	
	public static void produceXML(String path){
		
		// 1、创建document对象，代表整个xml文档
		Document dom = DocumentHelper.createDocument();
		// 2、创建根节点TCS
		org.dom4j.Element tcs = dom.addElement("TCS");
		// 3、向TCS节点中添加version属性
		
		for(int index=0;index<allcases.size();index++){
			
			List<List<String>> cases = new ArrayList<List<String>>(); // 测试用例集合
			cases=allcases.get(index);
			
			int num = 0;
			int min;
			//生成测试用例个数是含有高度迁移的组合数
			for(int n = 0;n<cases.size();n++){			
				if(cases.get(n).get(0).contains("takeoff_alt_cm")){	
					ShowInfor.print("里面解个数："+cases.get(n).size());
					min = cases.get(n).size();
					if(num<min){
						num = min;
					}
				}
			}
//			ShowInfor.print("测试用例的个数为："+num);
//			ShowInfor.print("in里面解个数："+cases);
			for(int n1=0;n1<num;n1++){
				// 4、生成子节点及节点内容
				Element testcase = tcs.addElement("testcase");
				int m = n1;
//				ShowInfor.print("---------------------testcase"+n1);
				for(int nn=0;nn<cases.size();nn++){//cases.size表示边的个数
					//添加节点
					Element process = testcase.addElement("process");
					Element operation = process.addElement("operation");
					String value = new String();
					if(!cases.get(nn).get(0).contains("takeoff_alt_cm")){
						int random = -1;
						if (random == -1) {
							random = new Random().nextInt(cases.get(nn).size());
						}
//						ShowInfor.print("random-->"+random);
						value = cases.get(nn).get(random);
					}else{
//						cases.get(nn).get(0).substring(18).contains("takeoff_alt_cm")
						if(cases.get(nn).get(0).contains("takeoff_alt_cm")){
							if(cases.get(nn).size()<10){
								int random = -1;
								if (random == -1) {
									random = new Random().nextInt(cases.get(nn).size());
								}
								value = cases.get(nn).get(random);	
							}else{
//								ShowInfor.print("第"+n1+"个测试用例");
//								ShowInfor.print("======"+n1);
//								ShowInfor.print(cases.get(nn).size()+"");
								value = cases.get(nn).get(m);
							}						
						}					
					}
//					ShowInfor.print("解value-->"+value);
					String[] cs =value.toString().split("%");
//					ShowInfor.print("operation-->"+cs[0]);
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
//					ShowInfor.print("input-->"+cs[1]);
					Element output = process.addElement("output");
//					ShowInfor.print(outtt.get(nn).toString());
					output.setText(cs[2]);
//					ShowInfor.print("output-->"+cs[2]);

				}
//				ShowInfor.print("---------------------testcase");
//				ShowInfor.print(a.getName());
			}
			////////
//			ShowInfor.print("===========================第"+i+"条测试用例读取完成");
			
			//}//for(Automatic a:testCase)

//			ShowInfor.print("抽象测试序列个数："+testCase.size());
//			ShowInfor.print();
			/////////////////////
			/////////////////////
			
		}
		
		OutputFormat format = OutputFormat.createPrettyPrint();
		//6、生成xml文件
		File file = new File(path);
   
		XMLWriter writer;
		try {
			writer = new XMLWriter(new FileOutputStream(file), format);
			writer.write(dom);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}


package com.horstmann.violet.application.gui.util.ckt.testcase;
//最终生成xml文档

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.horstmann.violet.application.gui.util.ckt.handle.Automatic;
import com.horstmann.violet.application.gui.util.ckt.handle.GetAutomatic;
import com.horstmann.violet.application.gui.util.ckt.handle.StateCoverage__1;
import com.horstmann.violet.application.gui.util.ckt.handle.Transition;

public class GetXML6 {
	public static void main(String[] args) {
		//String xml="UAVForXStream3.2.1+.xml";//有时间约束
		//String xml="UAVForXStream3.3.0.xml";//有时间约束
		String xml="rc_loopForXStream1.01.xml";//有时间约束
		//String xml="arm_motors_checkForXStream1.01.xml";//有时间约束
		Automatic auto=GetAutomatic.getAutomatic(xml);//获得原始的时间自动机
		ArrayList<Automatic> testCase=StateCoverage__1.testCase(auto);//获得满足状态覆盖的抽象测试序列

		// 1、创建document对象，代表整个xml文档
		Document dom = DocumentHelper.createDocument();
		// 2、创建根节点TCS
		org.dom4j.Element tcs = dom.addElement("TCS");
		// 3、向TCS节点中添加version属性

		int i = 1;		
		List<String> result1=new ArrayList<String>();//存放in里面最终实例化结果
		List<String> result2=new ArrayList<String>();//存放condition里面最终实例化结果



		for(Automatic a:testCase){	///////////////////////////////////
			List<List<String>> cases = new ArrayList<List<String>>(); // 测试用例集合
			System.out.println("===========================正在读取第"+i+"条测试用例");
			System.out.println("  ===>  测试用例名字:"+a.getName());
			int j = 1;
			// 4、生成子节点及节点内容
			Element testcase = tcs.addElement("testcase");

			for(Transition tran:a.getTransitionSet()){
				System.out.println("======第"+j+"条迁移开始======");
				//System.out.println("------------------------------------");
				//添加节点
				Element process = testcase.addElement("process");
				Element operation = process.addElement("operation");
				String sss = new String();
				if(tran.getName().contains("(")){
					int index11=tran.getName().replace("!", "").replace("?", "").indexOf("(");
					sss=tran.getName().substring(0,index11);
					System.out.println("迁移(激励)名称："+sss);
					operation.setText(sss);
				}
				else{
					sss = tran.getName().replace("!", "").replace("?", "");
					System.out.println("迁移(激励)名称："+sss);
					operation.setText(sss);
				}
				
				
				System.out.println("迁移Id："+tran.getId());								
				System.out.println("源状态名称："+tran.getSource());
				System.out.println("目的状态名称："+tran.getTarget());
				
				//未处理的约束条件	
				//System.out.println("约束："+tran.getEventSet());//约束不等式
				////////////////////////////////////////////////////////////////
				///////////////////////////////       in处理开始            /////////////////////////

				//处理in里面的不等式和参数，得到参数类型与参数一一对应的map，进行添加不等式操作
				//System.out.println("========================in========================");	
				//System.out.println("in---->"+tran.getIn());	//in里面的内容			
				if(GetMap.get_inMap(tran.getIn())==null){//map里面为空，即没有参数
					//System.out.println("keySet集合："+tran.getIn());
					result1 = null;
				}
				else{//map里有值，即有参数和参数对应类型
					String inn = tran.getIn().replace("false", "False").replace("true", "True");
					result1 = Result.getResult(inn);
					for(int ii=0;ii<result1.size();ii++){
						System.out.println("in里解"+ii+"为:"+result1.get(ii));
					}
				}
				//System.out.println("===========================in处理结束===========================");
				/////////////////////////////////////////////condition处理开始///////////////////////////////////////
				if(tran.getCondition().equals("null")){	
					result2 = null;
				}else{
					if(!tran.getCondition().equals("null")){
						if(GetMap.get_condMap(tran.getCondition())==null){
							result2 = null;
						}else{
							if(!(GetMap.get_condMap(tran.getCondition())==null)){
								String tra = tran.getCondition().replace("false", "False").replace("true", "True");
								result2 = Result.getResult(tra);
								for(int ii=0;ii<result2.size();ii++){
									System.out.println("condition里解"+ii+"为:"+result2.get(ii));
								}
							}
						}

					}					
				}

				////////////////////////////////////////////////condition处理结束///////////////////////////////////////

				//System.out.println("******************************************");
				/////////////////////////////////////////////////////////////////////////////////////

				

				//
				
				////////////////////为input添加求出来的解
				List<String> result=new ArrayList<String>();//存放一条迁移上的结果
				String res = new String();
				if((result1==null)&&(result2==null)){
					//res = sss+"%"+null;
					res = null;
					result.add(res);
				}else{
					if(!(result1==null)&&(result2==null)){
						for(String ttt2:result1){
							if(ttt2!=null){
								//res = sss+"%"+ttt2;
								res = ttt2;
								result.add(res);
							}
						}
					}
					if((result1==null)&&!(result2==null)){
						for(String ttt3:result2){
							if(ttt3!=null){
								//res = sss+"%"+ttt3;
								res = ttt3;
								result.add(res);
							}
						}
					}
					if(!(result1==null)&&!(result2==null)){
						for(String ttt2:result1){
							for(String ttt3:result2){
								if(ttt2!=null&&ttt3!=null){
									//res = sss+"%"+ttt2+","+ttt3;
									res = ttt2+","+ttt3;
									result.add(res);
								}									
							}
						}
					}
				}					

				if(result.size()==0){
					Element input = process.addElement("input");
					input.setText("解1为:"+null);
				}else{
					for(int ii=1;ii<=result.size();ii++){
						System.out.println("解"+ii+"为:"+result.get(ii-1));
						String s = "解"+ii+"为:"+result.get(ii-1);
						Element input = process.addElement("input");
						input.setText(s);
					}					
				}
				
				
				cases.add(result);
				////////////////////////////////////////////////////////////////////////////////////
				//System.out.println("                 ======第"+j+"条迁移结束======");
				j++;
			}//for(Transition tran:a.getTransitionSet())

			//cases里放的是一条测试用例上上每条迁移上的解，result放的是一条迁移上的多组解
			/*int num=1;
			for(int nn=0;nn<cases.size();nn++){
				int n = cases.get(nn).size();
				num = num*n;
				System.out.println("第"+nn+"条迁移上解个数为："+cases.get(nn).size());
			}*/
			/*for(int n1=0;n1<num;n1++){
				// 4、生成子节点及节点内容
				Element testcase = tcs.addElement("testcase");
				for(int nn1=0;;nn1++){
					for(int nn=0;nn<cases.size();nn++){
						//添加节点
						Element process = testcase.addElement("process");
						Element operation = process.addElement("operation");
						String[] cs =cases.get(nn).get(nn1).split("%");
						operation.setText(cs[0]);
						Element input = process.addElement("input");
						input.setText(cs[1]);
					}
				}
			}*/



			//System.out.println("===========================第"+i+"条测试用例读取完成");
			i++;
		}//for(Automatic a:testCase)


		/////////////////////



		/////////////////////

		OutputFormat format = OutputFormat.createPrettyPrint();
		//6、生成xml文件
		//File file = new File("C:\\Users\\Administrator\\Desktop\\tcss.xml");
		//File file = new File("E:\\项目\\xml\\UAVForXStream3.2.1+.xml");
		File file = new File("E:\\项目\\xml\\rc_loopForXStream1.01.xml");
		//File file = new File("E:\\项目\\xml\\arm_motors_checkForXStream1.01.xml");
		
		//String xml="loopForXStream3.1.5.2.z.xml";//有时间约束
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
}

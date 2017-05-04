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
		String xml = "D:\\ModelDriverProjectFile\\UPPAL\\3.Abstract_TestCase\\FunctionalTest\\EA4.1.0 ���ܳ���1ForXStream.xml";
		//Automatic automatic = GetAutomatic.getAutomatic("UAVForXStream3.8.0.xml");
		//GeneratePath.getFormatPathFromAutomatic(automatic, 8000);
		//
		Automatic auto=GetAutomatic.getAutomatic(xml);//���ԭʼ��ʱ���Զ���
		ArrayList<Automatic> testCase=GeneratePath.getFormatPathFromAutomatic(auto, 10);//�������״̬���ǵĳ����������

		// 1������document���󣬴�������xml�ĵ�
		Document dom = DocumentHelper.createDocument();
		// 2���������ڵ�TCS
		org.dom4j.Element tcs = dom.addElement("TCS");
		// 3����TCS�ڵ������version����

		int i = 1;				
		for(Automatic a:testCase){	///////////////////////////////////			
			List<List<String>> cases = new ArrayList<List<String>>(); // ������������
			System.out.println("===========================���ڶ�ȡ��"+i+"����������");
			System.out.println("  ===>  ������������:"+a.getName());
			int j = 1;
			// 4�������ӽڵ㼰�ڵ�����
			//Element testcase = tcs.addElement("testcase");

			for(Transition tran:a.getTransitionSet()){
				System.out.println();
				System.out.println("======��"+j+"��Ǩ�ƿ�ʼ======");
				System.out.println("Ǩ������:"+tran.getIn()+"---"+tran.getCondition());
				//��ӽڵ�
				//Element process = testcase.addElement("process");
				//Element operation = process.addElement("operation");
				String sss = new String();
				List<String> result1=new ArrayList<String>();//���in��������ʵ�������
				List<String> result2=new ArrayList<String>();//���condition��������ʵ�������
				if(tran.getName().contains("(")){
					int index11=tran.getName().replace("!", "").replace("?", "").indexOf("(");
					sss=tran.getName().substring(0,index11);
					System.out.println("Ǩ��(����)���ƣ�"+sss);
					//operation.setText(sss);
				}
				else{
					sss = tran.getName().replace("!", "").replace("?", "");
					System.out.println("Ǩ��(����)���ƣ�"+sss);
					//operation.setText(sss);
				}


				System.out.println("Ǩ��Id��"+tran.getId());								
				//System.out.println("Դ״̬���ƣ�"+tran.getSource());
				//System.out.println("Ŀ��״̬���ƣ�"+tran.getTarget());

				//δ�����Լ������	
				//System.out.println("Լ����"+tran.getEventSet());//Լ������ʽ
				////////////////////////////////////////////////////////////////
				///////////////////////////////       in����ʼ            /////////////////////////

				//����in����Ĳ���ʽ�Ͳ������õ��������������һһ��Ӧ��map��������Ӳ���ʽ����
				//System.out.println("========================in========================");	
				System.out.println("in---->"+tran.getIn());	//in���������	
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
						if(!(GetMap.get_inMap(tran.getIn())==null)){//map����Ϊ�գ���û�в���
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
						if(!(GetMap.get_inMap(tran.getIn())==null)){//map����Ϊ�գ���û�в���
							String inn = tran.getIn().replace("false", "False").replace("true", "True").replace("->", "$");
							result1 = Result1.getResult(inn);
						}else{
							if((GetMap.get_inMap(tran.getIn())==null)){
								result1.add(null);
							}
						}
				}
					
					
					if(GetMap.get_inMap(tran.getIn())==null){//map����Ϊ�գ���û�в���
						//System.out.println("keySet���ϣ�"+tran.getIn());
						result1.add(null);
					}
					else{//map����ֵ�����в����Ͳ�����Ӧ����
						String inn = tran.getIn().replace("false", "False").replace("true", "True").replace("->", "$");
						result1 = Result1.getResult(inn);
						for(int ii=0;ii<result1.size();ii++){
							//System.out.println("in���"+ii+"Ϊ:"+result1.get(ii));
						}
					}
				*/}
				
				//System.out.println("===========================in�������===========================");
				/////////////////////////////////////////////condition����ʼ///////////////////////////////////////
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
									//System.out.println("condition���"+ii+"Ϊ:"+result2.get(ii));
								}
							}
						}

					}					
				}

				////////////////////////////////////////////////condition�������///////////////////////////////////////

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
				////////////////////Ϊinput���������Ľ�
				List<String> result=new ArrayList<String>();//���һ��Ǩ���ϵĽ��
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
					//input.setText("��1Ϊ:"+null);
				}else{
					for(int ii=1;ii<=result.size();ii++){
						System.out.println("��"+ii+"Ϊ:"+result.get(ii-1));//������н�
						String s = "��"+ii+"Ϊ:"+result.get(ii-1);
						//Element input = process.addElement("input");
						//input.setText(s);
					}					
				}


				cases.add(result);
				////////////////////////////////////////////////////////////////////////////////////
				//System.out.println("                 ======��"+j+"��Ǩ�ƽ���======");
				j++;
			}//for(Transition tran:a.getTransitionSet())

			//cases��ŵ���һ��������������ÿ��Ǩ���ϵĽ⣬result�ŵ���һ��Ǩ���ϵĶ����
			/////
			int numm=1;
			for(int nn=0;nn<cases.size();nn++){
				int n = cases.get(nn).size();
				numm = numm*n;
				//System.out.println("��"+nn+"��Ǩ���Ͻ����Ϊ��"+cases.get(nn).size());
			}
			System.out.println("��"+i+"������·���Ͻ����"+numm);
//			if(num>5000){ //����������������5000������
//				num = 5000;
//			}
			int num=1;//һ��·��100����������///////////////////////////////////////////////////////////////////////////////////
			if(num>numm){   //���һ��·���Ͻ�С��100����ѡȡ��ʵ����
				num = numm;
			}
			for(int n1=0;n1<num;n1++){
				// 4�������ӽڵ㼰�ڵ�����
				Element testcase = tcs.addElement("testcase");
				//System.out.println("---------------------testcase"+n1);
				for(int nn=0;nn<cases.size();nn++){//cases.size��ʾ�ߵĸ���
					//��ӽڵ�
					Element process = testcase.addElement("process");
					Element operation = process.addElement("operation");

					int random = -1;
					if (random == -1) {
						random = new Random().nextInt(cases.get(nn).size());
					}
					//System.out.println("random-->"+random);
					String value = cases.get(nn).get(random);
					//System.out.println("��value-->"+value);
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
			

			//System.out.println("===========================��"+i+"������������ȡ���");
			i++;
		
		
		}//for(Automatic a:testCase)

		System.out.println("����������и�����"+testCase.size());
		System.out.println();


		/////////////////////



		/////////////////////

		OutputFormat format = OutputFormat.createPrettyPrint();
        //6������xml�ļ�
//		File file = new File("C:\\Users\\Administrator\\Desktop\\tcss.xml");
//		File file = new File("E:\\��Ŀ\\xml\\UAVForXStream3.2.1+.xml");m
//		File file = new File("E:\\��Ŀ\\xml\\rc_loopForXStream1.01.xml");
//		File file = new File("E:\\��Ŀ\\xml\\arm_motors_checkForXStream1.01.xml");
		File file = new File("E:\\��Ŀ\\xml\\UAVForXStreamPerfromTestV2+border+path.xml");
	//	File file = new File("E:\\��Ŀ\\xml\\UAVForXStream3.8.2.0.xml+border+path.xml");
		
		//File file = new File("E:\\��Ŀ\\xml\\UAVForXStream3.5.0.xml");
		//File file = new File("E:\\��Ŀ\\xml\\test.xml");
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


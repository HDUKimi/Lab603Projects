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
		//String xml="UAVForXStreamPerformTestV6.xml";//���ܲ��ԣ���Ҫ���Ը߶ȡ����١�����
		//String xml="UAVForXStreamGaoDu-v6.xml"; //���ܲ��ԣ���Ҫ��߶�
		//String xml = "UAVForXStreamXuanTing.xml";//���ܲ��ԣ���Ҫ����ͣ
		//String xml = "UAVForXStreamGAODU.xml";//���ܲ��ԣ���߶ȣ���Excel�ж�ȡ�߶�ֵ
		String xml = "UAVForXStreamGaoDuV9.xml";
		Automatic auto=GetAutomatic.getAutomatic(xml);//���ԭʼ��ʱ���Զ���
		Automatic a=GeneratePath.getPerformPathFromAutomatic(auto);//�������״̬���ǵĳ����������

		// 1������document���󣬴�������xml�ĵ�
		Document dom = DocumentHelper.createDocument();
		// 2���������ڵ�TCS
		org.dom4j.Element tcs = dom.addElement("TCS");
		// 3����TCS�ڵ������version����
		for(int Fspeed=0;Fspeed<=16;Fspeed=Fspeed+2){
			int i = 1;					
			List<List<String>> cases = new ArrayList<List<String>>(); // ������������
			List<String> outtt=new ArrayList<String>();//out���
			List<List<String>> syso=new ArrayList<List<String>>();
			ShowInfor.print("===========================���ڶ�ȡ��"+i+"����������");
			ShowInfor.print("  ===>  ������������:"+a.getName());
			int j = 1;
			for(Transition tran:a.getTransitionSet()){
				ShowInfor.print();
				ShowInfor.print("======��"+j+"��Ǩ�ƿ�ʼ======");
				ShowInfor.print("Ǩ������:"+tran.getIn()+"---"+tran.getCondition());
				String sss = new String();
				List<String> result1=new ArrayList<String>();//���in��������ʵ�������			
				List<String> result2=new ArrayList<String>();//���condition��������ʵ�������
				if(tran.getName().contains("(")){
					int index11=tran.getName().replace("!", "").replace("?", "").indexOf("(");
					sss=tran.getName().substring(0,index11);
					ShowInfor.print("Ǩ��(����)���ƣ�"+sss);
				}
				else{
					sss = tran.getName().replace("!", "").replace("?", "");
					ShowInfor.print("Ǩ��(����)���ƣ�"+sss);
				}
				ShowInfor.print("Ǩ��Id��"+tran.getId());								
				//ShowInfor.print("Դ״̬���ƣ�"+tran.getSource());
				//ShowInfor.print("Ŀ��״̬���ƣ�"+tran.getTarget());
				//δ�����Լ������	
				//ShowInfor.print("Լ����"+tran.getEventSet());//Լ������ʽ


				/**
				 * ����in����Ĳ���ʽ�Ͳ�����ʵ����in
				 */
				//����in����Ĳ���ʽ�Ͳ������õ��������������һһ��Ӧ��map��������Ӳ���ʽ����
				//ShowInfor.print("========================in========================");	
				ShowInfor.print("in---->"+tran.getIn());	//in���������
				if(tran.getIn().equals("null")){	
					result1.add(null);
				}else{	
					result1=Result_2.preInResult(tran.getIn(),Fspeed);
					syso.add(result1);
				}
				ShowInfor.print("-------------------"+result1.toString());

				/**
				 * ����condition����Ĳ���ʽ�Ͳ�����ʵ����condition
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
									//ShowInfor.print("condition���"+ii+"Ϊ:"+result2.get(ii));
								}
							}
						}

					}					
				}

				/**
				 * ����out����Ҫ�������Ϣ���洢��xml��
				 * cktҪ�����Ϣckt
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
				 * ��in��condition�Ͻ������һ��
				 * ��ʽ����������%in��condition�Ͻ�%out��Ҫ�������Ϣ
				 * ��ϵķ���result��
				 */
				List<String> result=new ArrayList<String>();//���һ��Ǩ���ϵĽ��
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
					//input.setText("��1Ϊ:"+null);
				}else{
					if(result.get(0).contains("takeoff_alt_cm")){
						for(int ii=1;ii<=result.size();ii++){
							ShowInfor.print("��==========="+ii+"Ϊ:"+result.get(ii-1));//������н�
							String s = "��==========="+ii+"Ϊ:"+result.get(ii-1);
						}
					}
										
				}
				cases.add(result);
				////////////////////////////////////////////////////////////////////////////////////
				//ShowInfor.print("                 ======��"+j+"��Ǩ�ƽ���======");
				j++;
			}//for(Transition tran:a.getTransitionSet())
			/**
			 * ����д��xml�ĵ���
			 */
			/*List<String> gaodu = null;//�߶Ƚ⼯��
			String other = new String();//���˸߶��������ȡ�Ľ�
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
			//�ѽ�ƴ����һ��
			/*List<String> casess = new ArrayList<String>();
			dis(0,cases);
			casess = re;
			List<String> ret = new ArrayList<String>();
			re = ret;*/
			//cases��ŵ���һ��������������ÿ��Ǩ���ϵĽ⣬cases��size�Ƕ�����Ǩ�ƣ�result�ŵ���һ��Ǩ���ϵĶ����
			/////
			/*int numm=1;
			for(int nn=0;nn<cases.size();nn++){
				int n = cases.get(nn).size();
				numm = numm*n;
				//ShowInfor.print("��"+nn+"��Ǩ���Ͻ����Ϊ��"+cases.get(nn).size());
			}*/
			/*int numm = casess.size();
			ShowInfor.print("��"+i+"������·���Ͻ����"+numm);
			int num=1000;//һ��·��100����������///////////////////////////////////////////////////////////////////////////////////
			if(num>numm){   //���һ��·���Ͻ�С��100����ѡȡ��ʵ����
				num = numm;
			}*/
			for (int j2 = 0; j2 < syso.size(); j2++) {
				//if(syso.get(j2).get(0).contains("takeoff_alt_cm")){
					ShowInfor.print("============================="+syso.get(j2).size()+"-----"+syso.get(j2).toString());
				//}
				
			}
			
			int num = 0;
			int min;
			//���ɲ������������Ǻ��и߶�Ǩ�Ƶ������
			for(int n = 0;n<cases.size();n++){			
				if(cases.get(n).get(0).contains("takeoff_alt_cm")){	
					ShowInfor.print("����������"+cases.get(n).size());
					min = cases.get(n).size();
					if(num<min){
						num = min;
					}
				}
			}
			//ShowInfor.print("���������ĸ���Ϊ��"+num);
			//ShowInfor.print("in����������"+cases);
			for(int n1=0;n1<num;n1++){
				// 4�������ӽڵ㼰�ڵ�����
				Element testcase = tcs.addElement("testcase");
				int m = n1;
				//ShowInfor.print("---------------------testcase"+n1);
				for(int nn=0;nn<cases.size();nn++){//cases.size��ʾ�ߵĸ���
					//��ӽڵ�
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
								//ShowInfor.print("��"+n1+"����������");
								//ShowInfor.print("======"+n1);
								//ShowInfor.print(cases.get(nn).size());
								value = cases.get(nn).get(m);
							}						
						}					
					}
					//ShowInfor.print("��value-->"+value);
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
			//ShowInfor.print("===========================��"+i+"������������ȡ���");
			i++;


			//}//for(Automatic a:testCase)

			//ShowInfor.print("����������и�����"+testCase.size());
			ShowInfor.print();
			/////////////////////
			/////////////////////
		}
		

		OutputFormat format = OutputFormat.createPrettyPrint();
		//6������xml�ļ�
		File file = new File("E:\\��Ŀ\\XML\\UAVForXStreamV9-----+16+border+path+perform.xml");   
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
			List<List<String>> cases = new ArrayList<List<String>>(); // ������������
			List<String> outtt=new ArrayList<String>();//out���
			List<List<String>> syso=new ArrayList<List<String>>();
			ShowInfor.print("===========================���ڶ�ȡ��"+i+"����������");
			ShowInfor.print("  ===>  ������������:"+automatic.getName());
			int j = 1;
			for(Transition tran:automatic.getTransitionSet()){
				ShowInfor.print();
				ShowInfor.print("======��"+j+"��Ǩ�ƿ�ʼ======");
				ShowInfor.print("Ǩ������:"+tran.getIn()+"---"+tran.getCondition());
				String sss = new String();
				List<String> result1=new ArrayList<String>();//���in��������ʵ�������			
				List<String> result2=new ArrayList<String>();//���condition��������ʵ�������
				if(tran.getName().contains("(")){
					int index11=tran.getName().replace("!", "").replace("?", "").indexOf("(");
					sss=tran.getName().substring(0,index11);
					ShowInfor.print("Ǩ��(����)���ƣ�"+sss);
				}
				else{
					sss = tran.getName().replace("!", "").replace("?", "");
					ShowInfor.print("Ǩ��(����)���ƣ�"+sss);
				}
				ShowInfor.print("Ǩ��Id��"+tran.getId());								
				//ShowInfor.print("Դ״̬���ƣ�"+tran.getSource());
				//ShowInfor.print("Ŀ��״̬���ƣ�"+tran.getTarget());
				//δ�����Լ������	
				//ShowInfor.print("Լ����"+tran.getEventSet());//Լ������ʽ


				/**
				 * ����in����Ĳ���ʽ�Ͳ�����ʵ����in
				 */
				//����in����Ĳ���ʽ�Ͳ������õ��������������һһ��Ӧ��map��������Ӳ���ʽ����
				//ShowInfor.print("========================in========================");	
				ShowInfor.print("in---->"+tran.getIn());	//in���������
				if(tran.getIn().equals("null")){	
					result1.add(null);
				}else{	
					result1=Result_2.preInResult(tran.getIn(),Fspeed);
					syso.add(result1);
				}
				ShowInfor.print("-------------------"+result1.toString());

				/**
				 * ����condition����Ĳ���ʽ�Ͳ�����ʵ����condition
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
									//ShowInfor.print("condition���"+ii+"Ϊ:"+result2.get(ii));
								}
							}
						}

					}					
				}

				/**
				 * ����out����Ҫ�������Ϣ���洢��xml��
				 * cktҪ�����Ϣckt
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
				 * ��in��condition�Ͻ������һ��
				 * ��ʽ����������%in��condition�Ͻ�%out��Ҫ�������Ϣ
				 * ��ϵķ���result��
				 */
				List<String> result=new ArrayList<String>();//���һ��Ǩ���ϵĽ��
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
					//input.setText("��1Ϊ:"+null);
				}else{
					if(result.get(0).contains("takeoff_alt_cm")){
						for(int ii=1;ii<=result.size();ii++){
							ShowInfor.print("��==========="+ii+"Ϊ:"+result.get(ii-1));//������н�
							String s = "��==========="+ii+"Ϊ:"+result.get(ii-1);
						}
					}
										
				}
				cases.add(result);
				////////////////////////////////////////////////////////////////////////////////////
				//ShowInfor.print("                 ======��"+j+"��Ǩ�ƽ���======");
				j++;
				
				tran.setResult(result);
			}//for(Transition tran:a.getTransitionSet())
			
			allcases.add(cases);
			i++;
		}
		
		return automatic;
	}
	
	public static void produceXML(String path){
		
		// 1������document���󣬴�������xml�ĵ�
		Document dom = DocumentHelper.createDocument();
		// 2���������ڵ�TCS
		org.dom4j.Element tcs = dom.addElement("TCS");
		// 3����TCS�ڵ������version����
		
		for(int index=0;index<allcases.size();index++){
			
			List<List<String>> cases = new ArrayList<List<String>>(); // ������������
			cases=allcases.get(index);
			
			int num = 0;
			int min;
			//���ɲ������������Ǻ��и߶�Ǩ�Ƶ������
			for(int n = 0;n<cases.size();n++){			
				if(cases.get(n).get(0).contains("takeoff_alt_cm")){	
					ShowInfor.print("����������"+cases.get(n).size());
					min = cases.get(n).size();
					if(num<min){
						num = min;
					}
				}
			}
//			ShowInfor.print("���������ĸ���Ϊ��"+num);
//			ShowInfor.print("in����������"+cases);
			for(int n1=0;n1<num;n1++){
				// 4�������ӽڵ㼰�ڵ�����
				Element testcase = tcs.addElement("testcase");
				int m = n1;
//				ShowInfor.print("---------------------testcase"+n1);
				for(int nn=0;nn<cases.size();nn++){//cases.size��ʾ�ߵĸ���
					//��ӽڵ�
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
//								ShowInfor.print("��"+n1+"����������");
//								ShowInfor.print("======"+n1);
//								ShowInfor.print(cases.get(nn).size()+"");
								value = cases.get(nn).get(m);
							}						
						}					
					}
//					ShowInfor.print("��value-->"+value);
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
//			ShowInfor.print("===========================��"+i+"������������ȡ���");
			
			//}//for(Automatic a:testCase)

//			ShowInfor.print("����������и�����"+testCase.size());
//			ShowInfor.print();
			/////////////////////
			/////////////////////
			
		}
		
		OutputFormat format = OutputFormat.createPrettyPrint();
		//6������xml�ļ�
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


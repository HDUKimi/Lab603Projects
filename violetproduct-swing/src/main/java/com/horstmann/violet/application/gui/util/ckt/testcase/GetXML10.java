package com.horstmann.violet.application.gui.util.ckt.testcase;
//��������xml�ĵ�  border

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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

public class GetXML10 {
	public static void main(String[] args) {
		//String xml="UAVForXStream3.2.1+.xml";//��ʱ��Լ��
		//String xml="UAVForXStream3.3.0.xml";//��ʱ��Լ��         111
		//String xml="rc_loopForXStream1.01.xml";//��ʱ��Լ��      222
		//String xml="arm_motors_checkForXStream1.01.xml";//��ʱ��Լ��   333
		//String xml="UAVForXStream3.5.0.xml";//��ʱ��Լ��   444
		//String xml="UAVForXStream3.7.0.xml";//��ʱ��Լ��   555
		//String xml="UAVForXStream3.7.6.xml";
		String xml="UAVForXStream3.8.2.xml";

		Automatic auto=GetAutomatic.getAutomatic(xml);//���ԭʼ��ʱ���Զ���
		ArrayList<Automatic> testCase=StateCoverage__1.testCase(auto);//�������״̬���ǵĳ����������

		// 1������document���󣬴�������xml�ĵ�
		Document dom = DocumentHelper.createDocument();
		// 2���������ڵ�TCS
		org.dom4j.Element tcs = dom.addElement("TCS");
		// 3����TCS�ڵ������version����

		int i = 1;		
		//List<String> result1=new ArrayList<String>();//���in��������ʵ�������
		//List<String> result2=new ArrayList<String>();//���condition��������ʵ�������


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
				}

				//System.out.println("===========================in�������===========================");
				/////////////////////////////////////////////condition����ʼ///////////////////////////////////////
				//System.out.println("condition---->"+tran.getCondition());
				if(tran.getCondition().equals("null")){	
					result2.add(null);
				}else{
					if(!tran.getCondition().equals("null")){
						if(tran.getCondition().contains("--")){
							List<List<String>> con_result = new ArrayList<List<String>>();
							List<String> con_result1;
							String getcon[] = tran.getCondition().split("--");
							for(int ii=0;ii<getcon.length;ii++){
								if(!(GetMap.get_condMap(getcon[ii])==null)){
									String inn = getcon[ii].replace("false", "False").replace("true", "True").replace("->", "$");
									con_result1 = Result1.getResult(inn);
									if((con_result1.size()>0)&&!(con_result1.get(0).equals(null))){
										con_result.add(con_result1);
									}
								}
							}
							if((con_result.size()>0)&&!(con_result.get(0).equals(null))){
								System.out.println("in_result.size()--->"+con_result.size());
								dis(0,con_result);
								result2 = re;
							}else{
								if(!(GetMap.get_condMap(tran.getCondition())==null)){//map����Ϊ�գ���û�в���
									String inn = tran.getCondition().replace("false", "False").replace("true", "True").replace("->", "$");
									result2 = Result1.getResult(inn);
								}else{
									if((GetMap.get_condMap(tran.getCondition())==null)){
										result2.add(null);
									}
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
								res = sss+"%"+ttt2;
								//res = ttt2;
								result.add(res.toString());
							}
						}
					}
					if((result1.toString().equals("[null]"))&&!(result2.toString().equals("[null]"))){
						for(String ttt3:result2){
							if(ttt3!=null){
								res = sss+"%"+ttt3;
								//res = ttt3;
								result.add(res.toString());
							}
						}
					}
					if(!(result1.toString().equals("[null]"))&&!(result2.toString().equals("[null]"))){
						for(String ttt2:result1){
							for(String ttt3:result2){
								if(ttt2!=null&&ttt3!=null){
									res = sss+"%"+ttt2+","+ttt3;
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
			int num=10;//һ��·��100����������///////////////////////////////////////////////////////////////////////////////////
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
					operation.setText(cs[0]);
					Element input = process.addElement("input");
					input.setText(cs[1]);
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
		//		File file = new File("E:\\��Ŀ\\xml\\UAVForXStream3.3.0.xml");
		File file = new File("E:\\��Ŀ\\xml\\UAVForXStream3.8.2.xml+++border+++++++.xml");

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


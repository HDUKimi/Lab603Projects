package com.horstmann.violet.application.gui.util.ckt.xml;

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
import com.horstmann.violet.application.gui.util.ckt.handle.Transition;
import com.horstmann.violet.application.gui.util.ckt.testcase.GetMap;
import com.horstmann.violet.application.gui.util.ckt.testcase.Result1;
import com.horstmann.violet.application.gui.util.wj.util.GeneratePath;

public class borderTestXML {

	public static void main(String[] args) {
		//String xml = "EA4.1.0 ���ܳ���1ForXStream.xml";
		String xml = "EA4.1.0 ���ܳ���2ForXStream.xml";
		int testNum=0;
		Automatic auto=GetAutomatic.getAutomatic(xml);//���ԭʼ��ʱ���Զ���
		//ArrayList<Automatic> testCase=StateCoverage__1.testCase(auto);
		ArrayList<Automatic> testCase=GeneratePath.getFormatPathFromAutomatic(auto, 100);//�������·�����ǵĳ����������
        System.out.println("����·������"+testCase.size());
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

			for(Transition tran:a.getTransitionSet()){
				System.out.println();
				System.out.println("======��"+j+"��Ǩ�ƿ�ʼ======");
				System.out.println("Ǩ������:"+tran.getIn()+"---"+tran.getCondition());
				String sss = new String();
				List<String> result1=new ArrayList<String>();//���in��������ʵ�������
				List<String> result2=new ArrayList<String>();//���condition��������ʵ�������
				if(tran.getName().contains("(")){
					int index11=tran.getName().replace("!", "").replace("?", "").indexOf("(");
					sss=tran.getName().substring(0,index11);
					System.out.println("Ǩ��(����)���ƣ�"+sss);
				}
				else{
					sss = tran.getName().replace("!", "").replace("?", "");
					System.out.println("Ǩ��(����)���ƣ�"+sss);
				}
				System.out.println("Ǩ��Id��"+tran.getId());								
				//System.out.println("Դ״̬���ƣ�"+tran.getSource());
				//System.out.println("Ŀ��״̬���ƣ�"+tran.getTarget());

				//δ�����Լ������	
				//System.out.println("Լ����"+tran.getEventSet());//Լ������ʽ

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
								System.out.println("tran.getCondition()========>"+tran.getCondition());
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
									result.add(res.toString());
								}									
							}
						}
					}
				}	
                System.out.println("result--------------"+result);
				if(result.size()==0){
					//input.setText("��1Ϊ:"+null);
				}else{
					for(int ii=1;ii<=result.size();ii++){
						System.out.println("��"+ii+"Ϊ:"+result.get(ii-1));//������н�
						String s = "��"+ii+"Ϊ:"+result.get(ii-1);
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

			System.out.println("��"+i+"������·���Ͻ����"+numm);
			int testRouteNum = testCase.size();
			int num=1000/testRouteNum + 1;//һ��·��100����������///////////////////////////////////////////////////////////////////////////////////
			
			for(int nn=0;nn<cases.size();nn++){
				int n = cases.get(nn).size();
				if(n<=0){
					System.exit(0);
				}else{
					if(numm<num){
						numm = numm*n;	
					}
				}
				//System.out.println("��"+nn+"��Ǩ���Ͻ����Ϊ��"+cases.get(nn).size());
			}
			if(num>numm){   //���һ��·���Ͻ�С��100����ѡȡ��ʵ����
				num = numm;
			}
			System.out.println("ԭʼ�������"+numm+"ȡ��һ��·���������"+num);
			for(int n1=0;n1<num;n1++){
				// 4�������ӽڵ㼰�ڵ�����
				Element testcase = tcs.addElement("testcase");
				testNum++;
				System.out.println("�����������������"+testNum);
				System.out.println("---------------------cases.size():"+cases.size());
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
				}
			}
			////////
			

			//System.out.println("===========================��"+i+"������������ȡ���");
			i++;		
		}//for(Automatic a:testCase)

		System.out.println("�������·��������"+testCase.size());
		System.out.println("�����������������"+testNum);

		OutputFormat format = OutputFormat.createPrettyPrint();
        //6������xml�ļ�
		//File file = new File("E:\\XML\\test\\EA4.1.0 ���ܳ���1ForXStream.xml");
		File file = new File("E:\\XML\\test\\EA4.1.0 ���ܳ���2ForXStream+path+border.xml");
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

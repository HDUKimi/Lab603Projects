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
import com.horstmann.violet.application.gui.util.ckt.testcase.*;
import com.horstmann.violet.application.gui.util.wj.util.*;

public class PerformanceXML {
	public static void main(String[] args) throws Exception {
		//String xml="UAVForXStreamPerformTestV6.xml";//���ܲ��ԣ���Ҫ���Ը߶ȡ����١�����
		//String xml="UAVForXStreamGaoDu-v6.xml"; //���ܲ��ԣ���Ҫ��߶�
		String xml = "UAVForXStreamXuanTing.xml";//���ܲ��ԣ���Ҫ����ͣ
		Automatic auto=GetAutomatic.getAutomatic(xml);//���ԭʼ��ʱ���Զ���
		Automatic a=GeneratePath.getPerformPathFromAutomatic(auto);//�������״̬���ǵĳ����������

		// 1������document���󣬴�������xml�ĵ�
		Document dom = DocumentHelper.createDocument();
		// 2���������ڵ�TCS
		org.dom4j.Element tcs = dom.addElement("TCS");
		// 3����TCS�ڵ������version����

		int i = 1;					
		List<List<String>> cases = new ArrayList<List<String>>(); // ������������
		List<String> outtt=new ArrayList<String>();//out���
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


			/**
			 * ����in����Ĳ���ʽ�Ͳ�����ʵ����in
			 */
			//����in����Ĳ���ʽ�Ͳ������õ��������������һһ��Ӧ��map��������Ӳ���ʽ����
			//System.out.println("========================in========================");	
			System.out.println("in---->"+tran.getIn());	//in���������	
			if(tran.getIn().equals("null")){	
				result1.add(null);
			}else{				
				result1=Result1.preInResult(tran.getIn());
			}	

			/**
			 * ����condition����Ĳ���ʽ�Ͳ�����ʵ����condition
			 */

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
							//result2 = Result.getResult(tra);
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
					for(String ttt3:result2){
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
				}
				if(!(result1.toString().equals("[null]"))&&!(result2.toString().equals("[null]"))){
					for(String ttt2:result1){
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
		/**
		 * ����д��xml�ĵ���
		 */
		//�ѽ�ƴ����һ��
		List<String> casess = new ArrayList<String>();
		dis(0,cases);
		casess = re;
		List<String> ret = new ArrayList<String>();
		re = ret;
		//cases��ŵ���һ��������������ÿ��Ǩ���ϵĽ⣬result�ŵ���һ��Ǩ���ϵĶ����
		/////
		/*int numm=1;
		for(int nn=0;nn<cases.size();nn++){
			int n = cases.get(nn).size();
			numm = numm*n;
			//System.out.println("��"+nn+"��Ǩ���Ͻ����Ϊ��"+cases.get(nn).size());
		}*/
		int numm = casess.size();
		System.out.println("��"+i+"������·���Ͻ����"+numm);
		int num=1000;//һ��·��100����������///////////////////////////////////////////////////////////////////////////////////
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
				Element output = process.addElement("output");
				//System.out.println(outtt.get(nn).toString());
				output.setText(cs[2]);

			}
			//System.out.println("---------------------testcase");
			//System.out.println(a.getName());
		}
		////////
		//System.out.println("===========================��"+i+"������������ȡ���");
		i++;


		//}//for(Automatic a:testCase)

		//System.out.println("����������и�����"+testCase.size());
		System.out.println();
		/////////////////////
		/////////////////////

		OutputFormat format = OutputFormat.createPrettyPrint();
		//6������xml�ļ�
		File file = new File("E:\\��Ŀ\\xml\\UAVForXStreamXuanTing+__border+path+perform.xml");
   
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
	
	public static void createXML(Automatic a, String path) {

		// 1������document���󣬴�������xml�ĵ�
		Document dom = DocumentHelper.createDocument();
		// 2���������ڵ�TCS
		org.dom4j.Element tcs = dom.addElement("TCS");
		// 3����TCS�ڵ������version����

		int i = 1;
		List<List<String>> cases = new ArrayList<List<String>>(); // ������������
		List<String> outtt = new ArrayList<String>();// out���
		System.out.println("===========================���ڶ�ȡ��" + i + "����������");
		System.out.println("  ===>  ������������:" + a.getName());
		int j = 1;
		for (Transition tran : a.getTransitionSet()) {
			System.out.println();
			System.out.println("======��" + j + "��Ǩ�ƿ�ʼ======");
			System.out.println("Ǩ������:" + tran.getIn() + "---" + tran.getCondition());
			String sss = new String();
			List<String> result1 = new ArrayList<String>();// ���in��������ʵ�������
			List<String> result2 = new ArrayList<String>();// ���condition��������ʵ�������
			if (tran.getName().contains("(")) {
				int index11 = tran.getName().replace("!", "").replace("?", "").indexOf("(");
				sss = tran.getName().substring(0, index11);
				System.out.println("Ǩ��(����)���ƣ�" + sss);
			} else {
				sss = tran.getName().replace("!", "").replace("?", "");
				System.out.println("Ǩ��(����)���ƣ�" + sss);
			}
			System.out.println("Ǩ��Id��" + tran.getId());
			// System.out.println("Դ״̬���ƣ�"+tran.getSource());
			// System.out.println("Ŀ��״̬���ƣ�"+tran.getTarget());
			// δ�����Լ������
			// System.out.println("Լ����"+tran.getEventSet());//Լ������ʽ

			/**
			 * ����in����Ĳ���ʽ�Ͳ�����ʵ����in
			 */
			// ����in����Ĳ���ʽ�Ͳ������õ��������������һһ��Ӧ��map��������Ӳ���ʽ����
			// System.out.println("========================in========================");
			System.out.println("in---->" + tran.getIn()); // in���������
			if (tran.getIn().equals("null")) {
				result1.add(null);
			} else {
				result1 = Result1.preInResult(tran.getIn());
			}

			/**
			 * ����condition����Ĳ���ʽ�Ͳ�����ʵ����condition
			 */

			// System.out.println("condition---->"+tran.getCondition());
			if (tran.getCondition().equals("null")) {
				result2.add(null);
			} else {
				if (!tran.getCondition().equals("null")) {
					if (GetMap.get_condMap(tran.getCondition()) == null) {
						result2.add(null);
					} else {
						if (!(GetMap.get_condMap(tran.getCondition()) == null)) {
							String tra = tran.getCondition().replace("false", "False").replace("true", "True")
									.replace("->", "$");
							// result2 = Result.getResult(tra);
							System.out.println("tra----" + tra);
							result2 = Result1.getResult(tra);
							// result2 = testbdscs.getResult(tra);
							for (int ii = 0; ii < result2.size(); ii++) {
								// System.out.println("condition���"+ii+"Ϊ:"+result2.get(ii));
							}
						}
					}

				}
			}

			/**
			 * ����out����Ҫ�������Ϣ���洢��xml�� cktҪ�����Ϣckt
			 */
			String outP = new String();
			String s1 = new String();
			if (tran.getOut().contains("ckt")) {
				String[] out = tran.getOut().split(",");
				List<String> output = new ArrayList<String>();
				for (String s : out) {
					if (s.contains("ckt")) {
						String ss = s.replace("ckt", "");
						output.add(ss);
					}
				}
				if (output.size() > 1) {
					outP = output.get(0);
					for (int j1 = 1; j1 < output.size(); j1++) {
						s1 = output.get(j);
						outP = outP + "," + s1;
					}
				}
				if (output.size() == 1) {
					outP = output.get(0);
				}
				if (output.size() <= 0) {
					outP = "null";
				}
				outtt.add(outP);
			} else {
				outP = "null";
				outtt.add(outP);
			}
			/**
			 * ��in��condition�Ͻ������һ�� ��ʽ����������%in��condition�Ͻ�%out��Ҫ�������Ϣ
			 * ��ϵķ���result��
			 */
			List<String> result = new ArrayList<String>();// ���һ��Ǩ���ϵĽ��
			String res = new String();
			if ((result1.toString().equals("[null]")) && (result2.toString().equals("[null]"))) {
				res = sss + "%" + null + "%" + outP;
				// res = null;
				result.add(res.toString());
			} else {
				if (!(result1.toString().equals("[null]")) && (result2.toString().equals("[null]"))) {
					for (String ttt2 : result1) {
						if (ttt2 != null) {
							if (ttt2.contains("flag=1")) {
								res = sss + "flag=1" + "%" + ttt2.replace("flag=1", "") + "%" + outP;
							} else {
								res = sss + "%" + ttt2 + "%" + outP;
							}

							// res = ttt2;
							result.add(res.toString());
						}
					}
				}
				if ((result1.toString().equals("[null]")) && !(result2.toString().equals("[null]"))) {
					for (String ttt3 : result2) {
						if (ttt3 != null) {
							if (ttt3.contains("flag=1")) {
								res = sss + "flag=1" + "%" + ttt3.replace("flag=1", "") + "%" + outP;
							} else {
								res = sss + "%" + ttt3 + "%" + outP;
							}
							// res = sss+"%"+ttt3;
							// res = ttt3;
							result.add(res.toString());
						}
					}
				}
				if (!(result1.toString().equals("[null]")) && !(result2.toString().equals("[null]"))) {
					for (String ttt2 : result1) {
						for (String ttt3 : result2) {
							if (ttt2 != null && ttt3 != null) {
								if ((ttt2.contains("flag=1")) || (ttt3.contains("flag=1"))) {
									res = sss + "flag=1" + "%" + ttt2.replace("flag=1", "") + ","
											+ ttt3.replace("flag=1", "") + "%" + outP;
								} else {
									if (!(ttt2.contains("flag=1")) && !(ttt3.contains("flag=1"))) {
										res = sss + "%" + ttt2 + "," + ttt3 + "%" + outP;
									}
								}
								// res = sss+"%"+ttt2+","+ttt3;
								// res = ttt2+","+ttt3;
								result.add(res.toString());
							}
						}
					}
				}
			}
			System.out.println("result--------------" + result);
			if (result.size() == 0) {
				// System.out.println("-----------------0000000---------------------");
				// Element input = process.addElement("input");
				// input.setText("��1Ϊ:"+null);
			} else {
				for (int ii = 1; ii <= result.size(); ii++) {
					System.out.println("��" + ii + "Ϊ:" + result.get(ii - 1));// ������н�
					String s = "��" + ii + "Ϊ:" + result.get(ii - 1);
					// Element input = process.addElement("input");
					// input.setText(s);
				}
			}
			cases.add(result);
			////////////////////////////////////////////////////////////////////////////////////
			// System.out.println(" ======��"+j+"��Ǩ�ƽ���======");
			j++;
		} // for(Transition tran:a.getTransitionSet())
		/**
		 * ����д��xml�ĵ���
		 */
		// �ѽ�ƴ����һ��
		List<String> casess = new ArrayList<String>();
		dis(0, cases);
		casess = re;
		List<String> ret = new ArrayList<String>();
		re = ret;
		// cases��ŵ���һ��������������ÿ��Ǩ���ϵĽ⣬result�ŵ���һ��Ǩ���ϵĶ����
		/////
		/*
		 * int numm=1; for(int nn=0;nn<cases.size();nn++){ int n =
		 * cases.get(nn).size(); numm = numm*n;
		 * //System.out.println("��"+nn+"��Ǩ���Ͻ����Ϊ��"+cases.get(nn).size()); }
		 */
		int numm = casess.size();
		System.out.println("��" + i + "������·���Ͻ����" + numm);
		int num = 1000;// һ��·��100����������///////////////////////////////////////////////////////////////////////////////////
		if (num > numm) { // ���һ��·���Ͻ�С��100����ѡȡ��ʵ����
			num = numm;
		}
		for (int n1 = 0; n1 < num; n1++) {
			// 4�������ӽڵ㼰�ڵ�����
			Element testcase = tcs.addElement("testcase");
			// System.out.println("---------------------testcase"+n1);
			for (int nn = 0; nn < cases.size(); nn++) {// cases.size��ʾ�ߵĸ���
				// ��ӽڵ�
				Element process = testcase.addElement("process");
				Element operation = process.addElement("operation");
				int random = -1;
				if (random == -1) {
					random = new Random().nextInt(cases.get(nn).size());
				}
				// System.out.println("random-->"+random);
				String value = cases.get(nn).get(random);
				// System.out.println("��value-->"+value);
				String[] cs = value.toString().split("%");
				// System.out.println("operation-->"+cs[0]);
				if (cs[0].contains("flag=1")) {
					String name = cs[0].replace("flag=1", "");
					operation.setText(name);
				} else {
					if (!cs[0].contains("flag=1")) {
						operation.setText(cs[0]);
					}
				}

				Element input = process.addElement("input");
				if (cs[0].contains("flag=1")) {
					input.addAttribute("border", "1");
					input.setText(cs[1]);
				} else {
					input.setText(cs[1]);
				}
				// input.setText(cs[1]);
				// System.out.println("input-->"+cs[1]);
				Element output = process.addElement("output");
				// System.out.println(outtt.get(nn).toString());
				output.setText(cs[2]);

			}
			// System.out.println("---------------------testcase");
			// System.out.println(a.getName());
		}
		////////
		// System.out.println("===========================��"+i+"������������ȡ���");
		i++;

		// }//for(Automatic a:testCase)

		// System.out.println("����������и�����"+testCase.size());
		System.out.println();
		/////////////////////
		/////////////////////

		OutputFormat format = OutputFormat.createPrettyPrint();
		// 6������xml�ļ�
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

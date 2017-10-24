package com.horstmann.violet.application.gui.util.ckt.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.horstmann.violet.application.gui.util.ckt.handle.*;
import com.horstmann.violet.application.gui.util.ckt.testcase.GetMap;
import com.horstmann.violet.application.gui.util.ckt.testcase.OutSideResult;
import com.horstmann.violet.application.gui.util.ckt.testcase.PathCoverage_new;
import com.horstmann.violet.application.gui.util.ckt.testcase.Result1;
import com.horstmann.violet.application.gui.util.ckt.testcase.ResultOfTime;
import com.horstmann.violet.application.gui.util.ckt.testcase.pathCoverage2;
import com.horstmann.violet.application.gui.util.ckt.testcase.ResultOfTime;
import com.horstmann.violet.application.gui.util.wj.util.GeneratePath;
/**
 * ʱ��Լ������ʽ��ʵ������õ���xml
 * @author Administrator
 *
 */
public class outXMLofTime {

	public static void main(String[] args) {

		//String xml = "EAElevatorV2ForXStream.xml";
		//String xml = "EAElevatorForXStream.xml";
		//String xml = "EAElevator7ForXStream.xml";
//		String xml = "EAElevator53ForXStream.xml";
		String xml="D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\SequenceToUppal\\EAElevator71ForXStream.xml";
		Automatic automatic = GetAutomatic.getAutomatic(xml);// ���ԭʼ��ʱ���Զ���
		Automatic new_automatic = IPR__1.iPR(automatic);// ��ò�ֺ��ʱ���Զ���
		Automatic aTDRTAutomatic = ATDTR__1.aTDRT(new_automatic, automatic);// ���ȥ������ʱ��Ǩ�ƺ��ʱ���Զ���
		//������ֹ״̬����
		for(State state:aTDRTAutomatic.getStateSet()) {
			int k1= 0;
			for(Transition tran:aTDRTAutomatic.getTransitionSet()){//�ж�Ŀ��״̬�Ƿ��ѱ�����
				if(state.getName().equals(tran.getSource())){//�ҳ��Դ�״̬Ϊ����Ǩ��
					k1=1;
				}
			}
			if(k1==0){
				state.setFinalState(true);
			}		
		}
		
		//״̬���ǣ�ʱ�䣩
		//ArrayList<Automatic> testCase = StateCoverage__1.testCase(aTDRTAutomatic);// �������״̬���ǵĳ����������
		//ArrayList<Automatic> testCase =GeneratePath.getFormatPathFromAutomatic(aTDRTAutomatic, 9);
		//ArrayList<Automatic> testCase =GeneratePath.getFormatPathFromAutomatic(automatic, 3);
		//ArrayList<Automatic> testCase = pathCoverage2.testCase(aTDRTAutomatic);
		
		//·������
		ArrayList<Automatic> testCase = PathCoverage_new.testCase(aTDRTAutomatic);
		
		/*System.out.println(testCase.size() + "-----����·���ĸ���");
		for(Automatic auto : testCase){
			for(Transition tran : auto.getTransitionSet()){
				System.out.println(tran.getName() + "------" + tran.getTranTimeName());
			}			
		}*/
		

		ArrayList<ArrayList<String>> all_inequalitys = Get_inequality__1.get_AllInequalitys(testCase);// ÿ���������������һ������ʽ��
		// 1������document���󣬴�������xml�ĵ�
		Document dom = DocumentHelper.createDocument();
		// 2���������ڵ�TCS
		org.dom4j.Element tcs = dom.addElement("TCS");
		// 3����TCS�ڵ������version����
		
		int num = 0;//���������������
		int i = 0;
		
		//ArrayList<Map<Automatic, String>> cases = new ArrayList<Map<Automatic, String>>();//������������
		Map<Automatic, String> casesAndTime= new HashMap<Automatic, String>();//��Ź��ܺ�ʱ���Ӧ��ϵ
		
		//ArrayList<ArrayList<Automatic>> casesAndTime = new ArrayList<ArrayList<Automatic>>();//������������
		
		int indexk=-1;
		
		for (Automatic a : testCase) {
			System.out.println("===========================���ڶ�ȡ��" + i + "����������");
			System.out.println("  ===>  ������������:" + a.getName());
			indexk++;
			for(int k=0; k<a.getTransitionSet().size(); k++){							
									
				Automatic path = new Automatic();//path��һ����������
				path.setInitState(a.getInitState());
				path.setName(a.getName());
				path.setStateSet(a.getStateSet());
				path.setTransitionSet(a.getTransitionSet());
				
				//�������·��ʱ����Լ��
				String s = null;
				for (int n = 0; n < all_inequalitys.get(indexk).size(); n++) {
					if (n == 0) {
						s = all_inequalitys.get(indexk).get(0).toString();
					}
					if ((n > 0)) {
						s = s + "," + all_inequalitys.get(indexk).get(n).toString();
					}
				}
				
				int yesOrNo = 0; //�ж��ܷ�������������ֵ
				for(int m=0; m<a.getTransitionSet().size(); m++){
					
					System.out.println();
					System.out.println("======��" + m + "��Ǩ�ƿ�ʼ======");
					Transition tran = path.getTransitionSet().get(m);//��ȡ��m��Ǩ��
					System.out.println("Ǩ������" + tran.getName());
					System.out.println("Ǩ������:" + tran.getIn() + "---" + tran.getCondition());
					List<String> result1 = new ArrayList<String>();// ���in��������ʵ�������
					List<String> result2 = new ArrayList<String>();// ���condition��������ʵ�������

					// ����in����Ĳ���ʽ�Ͳ������õ��������������һһ��Ӧ��map��������Ӳ���ʽ����
					// System.out.println("========================in========================");
					System.out.println("in---->" + tran.getIn()); // in���������
					if (tran.getIn().equals("null")) {
						result1.add(null);
					} else {
						
						if (tran.getIn().contains("--")) {
							List<List<String>> in_result = new ArrayList<List<String>>();
							List<String> in_result1;
							// String rrr = null;
							String getin[] = tran.getIn().split("--");
							for (int ii = 0; ii < getin.length; ii++) {
								if (!(GetMap.get_inMap(getin[ii]) == null)) {
									String inn = getin[ii].replace("False", "false").replace("True", "true").replace("->",
											"$");
									//in_result1 = Result1.getResult(inn);
									if(m == k){//��m��Ǩ��ȡ�߽�ֵ���
										in_result1 = OutSideResult.getResultOfTime(inn);
										if(in_result1.size() > 0){
											yesOrNo = 1;
										}
									}else{ //ȡ��ȷ��
										in_result1 = ResultOfTime.getResultOfTime(inn);
									}
									//in_result1 = ResultOfTime.getResultOfTime(inn);
									//in_result1 = OutSideResult.getResultOfTime(inn);
									
									if ((in_result1.size() > 0) && !(in_result1.get(0).equals(null))) {
										in_result.add(in_result1);
									}
								}
							}
							if ((in_result.size() > 0) && !(in_result.get(0).equals(null))) {
								System.out.println("in_result.size()--->" + in_result.size());
								dis(0, in_result);
								result1 = re;
							}
						} else {
							if (!(GetMap.get_inMap(tran.getIn()) == null)) {// map����Ϊ�գ���û�в���
								String inn = tran.getIn().replace("False", "false").replace("True", "true").replace("->",
										"$");
								//result1 = Result1.getResult(inn);
								if(m == k){
									result1 = OutSideResult.getResultOfTime(inn);
									if(result1.size() > 0){
										yesOrNo = 1;
									}
								}else{
									result1 = ResultOfTime.getResultOfTime(inn);
								}
								//result1 = ResultOfTime.getResultOfTime(inn);
								//result1 = OutSideResult.getResultOfTime(inn);
							} else {
								if ((GetMap.get_inMap(tran.getIn()) == null)) {
									result1.add(null);
								}
							}
						}
					}
					///////////////////////////////////////////// condition����ʼ///////////////////////////////////////
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
									//result2 = Result1.getResult(tra);
									if(m == k){
										result2 = OutSideResult.getResultOfTime(tra);
										if(result2.size() > 0){
											yesOrNo = 1;
										}
									}else{
										result2 = ResultOfTime.getResultOfTime(tra);
									}
									//result2 = ResultOfTime.getResultOfTime(tra);
									//result2 = OutSideResult.getResultOfTime(tra);
									// result2 = testbdscs.getResult(tra);
									System.out.println("condition:"+tran.getCondition().toString());
									for (int ii = 0; ii < result2.size(); ii++) {
										System.out.println("condition���"+ii+"Ϊ:"+result2.get(ii));
									}
								}
							}

						}
					}
					List<String> result = new ArrayList<String>();// ���һ��Ǩ���ϵĽ��
					String res = new String();
					if ((result1.toString().equals("[null]")) && (result2.toString().equals("[null]"))) {
						// res = sss + "%" + null;
						res = "null";
						result.add(res.toString());
					} else {
						if (!(result1.toString().equals("[null]")) && (result2.toString().equals("[null]"))) {
							for (String ttt2 : result1) {
								if (ttt2 != null) {
									res = ttt2.replace("flag=1", "");
									result.add(res.toString());
								}
							}
						}
						if ((result1.toString().equals("[null]")) && !(result2.toString().equals("[null]"))) {
							for (String ttt3 : result2) {
								res = ttt3.replace("flag=1", "");
								result.add(res.toString());
							}
						}
						if (!(result1.toString().equals("[null]")) && !(result2.toString().equals("[null]"))) {
							for (String ttt2 : result1) {
								for (String ttt3 : result2) {
									res = ttt2.replace("flag=1", "") + "," + ttt3.replace("flag=1", "");
									result.add(res.toString());
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
							String ss = "��" + ii + "Ϊ:" + result.get(ii - 1);
							// Element input = process.addElement("input");
							// input.setText(s);
						}
					}
					tran.setResult(result);	
					
				}
				if(yesOrNo == 1){
					casesAndTime.put(path, s);
					//cases.add(casesAndTime);
				}				
			}
			// cases��ŵ���һ��������������ÿ��Ǩ���ϵĽ⣬result�ŵ���һ��Ǩ���ϵĶ����
		}
		
		//for(int sum=0; sum<casesAndTime.size(); sum++){
			
			for(Automatic cases: casesAndTime.keySet()){
				// 4�������ӽڵ㼰�ڵ�����
				Element testcase = tcs.addElement("testcase");
				for(int m=0; m<cases.getTransitionSet().size(); m++){
					Transition tran = cases.getTransitionSet().get(m);
					// ��ӽڵ�
					Element process = testcase.addElement("process");
					Element operation = process.addElement("operation");
					operation.setText(tran.getName());
					Element input = process.addElement("input");
					input.setText(tran.getResult().get(0).replace("False", "false").replace("True", "true"));
					Element time = process.addElement("time");
					time.setText(tran.getTranTimeName());
				}
				Element limit = testcase.addElement("limit");
				Element operation = limit.addElement("operation");
				operation.setText(casesAndTime.get(cases));
			}
		//}
		
		
		
			
		System.out.println();
		System.out.println("*********************************");
		//System.out.println("��ȷ�Ĳ�������������" + rightNum);
		//System.out.println("����Ĳ�������������" + errorNum);
		System.out.println("�ܲ�����������Ϊ��" + (num));
		System.out.println("*********************************");
		System.out.println();
		
		//**********************************************************************************
		
	
		

		OutputFormat format = OutputFormat.createPrettyPrint();
		// 6������xml�ļ�
		//File file = new File("E:\\XML\\EAElevatorForXStream-State-time.xml"); //״̬����
		File file = new File("E:\\XML\\EAElevator53ForXStream-Path-time-outside.xml"); //·������
		// formatXML(file);
		XMLWriter writer;

		try {
			writer = new XMLWriter(new FileOutputStream(file), format);
			writer.write(dom);
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		//�������·��
		System.out.println(testCase.size() + "-----����·���ĸ���");
		int number = 0;
		for(Automatic auto : testCase){
			number++;
			System.out.println("����·��" + number);
			for(Transition tran : auto.getTransitionSet()){
				System.out.println(tran.getName() + "------" + tran.getTranTimeName());
			}	
			System.out.println();
		}

	}

	public static String s = "";
	public static List<String> re = new ArrayList<String>();

	public static void dis(int n, List<List<String>> l) {
		if (n == l.size()) {
			re.add(s);
			return;
		} else {
			List<String> sCollection = l.get(n);
			for (int i = 0; i < sCollection.size(); i++) {
				String s1 = s;
				if (s == "") {
					s = sCollection.get(i);
				} else {
					s = s + "," + sCollection.get(i);
				}
				dis(n + 1, l);
				s = s1;
			}
		}
	}
	
	public static String replece(String s){
		if(s.contains(">=")){
			s = s.replace(">=", "<");
		}else{
			if(s.contains(">")){
				s = s.replace(">", "<=");
			}else{
				if(s.contains("<=")){
					s = s.replace("<=", ">");
				}else{
					if(s.contains("<")){
						s = s.replace("<", ">=");
					}
				}
			}
			
		}	
		return s;
	}
	
	public static int tranMaxNumber(ArrayList<Transition> tranSet){
		int max = 0;
		for(int i=0; i<tranSet.size(); i++){
			int num = tranSet.get(i).getResult().size();
			if(num > max){
				max = num;
			}
		}
		return max;
	}

}

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

import com.horstmann.violet.application.gui.util.ckt.handle.*;
import com.horstmann.violet.application.gui.util.ckt.output.ShowInfor;
import com.horstmann.violet.application.gui.util.ckt.testcase.GetMap;
import com.horstmann.violet.application.gui.util.ckt.testcase.PathCoverage_new;
import com.horstmann.violet.application.gui.util.ckt.testcase.ResultOfTime;
import com.horstmann.violet.application.gui.util.ckt.testcase.pathCoverage2;
import com.horstmann.violet.application.gui.util.ckt.testcase.ResultOfTime;
import com.horstmann.violet.application.gui.util.wj.util.GeneratePath;
import com.horstmann.violet.application.gui.util.wqq.AutoMataTransfrom2.A;
/**
 * ʱ��Լ������ʽ��ʵ������õ���xml
 * @author Administrator
 *
 */
public class XmlOfTime {

	public static void main(String[] args) {

		//String xml = "EAElevatorV2ForXStream.xml";
		String xml = "D:\\ModelDriverProjectFile\\UPPAL\\2.UML_Model_Transfer\\TimeTest\\EAElevatorForXStream.xml";
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
		
//		ArrayList<Automatic> testCase = StateCoverage__1.testCase(aTDRTAutomatic);// �������״̬���ǵĳ����������
		//ArrayList<Automatic> testCase =GeneratePath.getFormatPathFromAutomatic(aTDRTAutomatic, 9);
		//ArrayList<Automatic> testCase =GeneratePath.getFormatPathFromAutomatic(automatic, 3);
		//ArrayList<Automatic> testCase = pathCoverage2.testCase(aTDRTAutomatic);
		
		for(State s:aTDRTAutomatic.getStateSet()){
			System.out.println(s.getId());
		}
		
		ArrayList<Automatic> testCase = PathCoverage_new.testCase(aTDRTAutomatic);
		
		System.out.println(testCase.size() + "-----����·���ĸ���");
		for(Automatic auto : testCase){
			for(Transition tran : auto.getTransitionSet()){
				System.out.print(tran.getTranTimeName()+"  ");
			}	
			System.out.println();
		}
		

//		ArrayList<ArrayList<String>> all_inequalitys = Get_inequality__1.get_AllInequalitys(testCase);// ÿ���������������һ������ʽ��
//		int i = 0;
//		for (Automatic a : testCase) {
//			System.out.println("===========================���ڶ�ȡ��" + i + "����������");
//			System.out.println("  ===>  ������������:" + a.getName());
//			int j = 1;
//			// 4�������ӽڵ㼰�ڵ�����
//			// Element testcase = tcs.addElement("testcase");
//
//			for (Transition tran : a.getTransitionSet()) {
//				System.out.println();
//				System.out.println("======��" + j + "��Ǩ�ƿ�ʼ======");
//				System.out.println("Ǩ������:" + tran.getIn() + "---" + tran.getCondition());
//				List<String> result1 = new ArrayList<String>();// ���in��������ʵ�������
//				List<String> result2 = new ArrayList<String>();// ���condition��������ʵ�������
//
//				// ����in����Ĳ���ʽ�Ͳ������õ��������������һһ��Ӧ��map��������Ӳ���ʽ����
//				// System.out.println("========================in========================");
//				System.out.println("in---->" + tran.getIn()); // in���������
//				if (tran.getIn().equals("null")) {
//					result1.add(null);
//				} else {
//
//					if (tran.getIn().contains("--")) {
//						List<List<String>> in_result = new ArrayList<List<String>>();
//						List<String> in_result1;
//						// String rrr = null;
//						String getin[] = tran.getIn().split("--");
//						for (int ii = 0; ii < getin.length; ii++) {
//							if (!(GetMap.get_inMap(getin[ii]) == null)) {
//								String inn = getin[ii].replace("false", "False").replace("true", "True").replace("->",
//										"$");
//								//in_result1 = Result1.getResult(inn);
//								in_result1 = ResultOfTime.getResultOfTime(inn);
//								if ((in_result1.size() > 0) && !(in_result1.get(0).equals(null))) {
//									in_result.add(in_result1);
//								}
//							}
//						}
//						if ((in_result.size() > 0) && !(in_result.get(0).equals(null))) {
//							System.out.println("in_result.size()--->" + in_result.size());
//							dis(0, in_result);
//							result1 = re;
//						}
//					} else {
//						if (!(GetMap.get_inMap(tran.getIn()) == null)) {// map����Ϊ�գ���û�в���
//							String inn = tran.getIn().replace("false", "False").replace("true", "True").replace("->",
//									"$");
//							//result1 = Result1.getResult(inn);
//							result1 = ResultOfTime.getResultOfTime(inn);
//						} else {
//							if ((GetMap.get_inMap(tran.getIn()) == null)) {
//								result1.add(null);
//							}
//						}
//					}
//				}
//				///////////////////////////////////////////// condition����ʼ///////////////////////////////////////
//				// System.out.println("condition---->"+tran.getCondition());
//				if (tran.getCondition().equals("null")) {
//					result2.add(null);
//				} else {
//					if (!tran.getCondition().equals("null")) {
//						if (GetMap.get_condMap(tran.getCondition()) == null) {
//							result2.add(null);
//						} else {
//							if (!(GetMap.get_condMap(tran.getCondition()) == null)) {
//								String tra = tran.getCondition().replace("false", "False").replace("true", "True")
//										.replace("->", "$");
//								// result2 = Result.getResult(tra);
//								System.out.println("tra----" + tra);
//								//result2 = Result1.getResult(tra);
//								result2 = ResultOfTime.getResultOfTime(tra);
//								// result2 = testbdscs.getResult(tra);
//								System.out.println("condition:"+tran.getCondition().toString());
//								for (int ii = 0; ii < result2.size(); ii++) {
//									System.out.println("condition���"+ii+"Ϊ:"+result2.get(ii));
//								}
//							}
//						}
//
//					}
//				}
//				List<String> result = new ArrayList<String>();// ���һ��Ǩ���ϵĽ��
//				String res = new String();
//				if ((result1.toString().equals("[null]")) && (result2.toString().equals("[null]"))) {
//					// res = sss + "%" + null;
//					res = "null";
//					result.add(res.toString());
//				} else {
//					if (!(result1.toString().equals("[null]")) && (result2.toString().equals("[null]"))) {
//						for (String ttt2 : result1) {
//							if (ttt2 != null) {
//								res = ttt2.replace("flag=1", "");
//								result.add(res.toString());
//							}
//						}
//					}
//					if ((result1.toString().equals("[null]")) && !(result2.toString().equals("[null]"))) {
//						for (String ttt3 : result2) {
//							res = ttt3.replace("flag=1", "");
//							result.add(res.toString());
//						}
//					}
//					if (!(result1.toString().equals("[null]")) && !(result2.toString().equals("[null]"))) {
//						for (String ttt2 : result1) {
//							for (String ttt3 : result2) {
//								res = ttt2.replace("flag=1", "") + "," + ttt3.replace("flag=1", "");
//								result.add(res.toString());
//							}
//
//						}
//					}
//				}
//				System.out.println("result--------------" + result);
//				if (result.size() == 0) {
//					// System.out.println("-----------------0000000---------------------");
//					// Element input = process.addElement("input");
//					// input.setText("��1Ϊ:"+null);
//				} else {
//					for (int ii = 1; ii <= result.size(); ii++) {
//						System.out.println("��" + ii + "Ϊ:" + result.get(ii - 1));// ������н�
//						String s = "��" + ii + "Ϊ:" + result.get(ii - 1);
//						// Element input = process.addElement("input");
//						// input.setText(s);
//					}
//				}
//				tran.setResult(result);
//				////////////////////////////////////////////////////////////////////////////////////
//				// System.out.println(" ======��"+j+"��Ǩ�ƽ���======");
//				j++;
//			} // for(Transition tran:a.getTransitionSet())
//
//			// cases��ŵ���һ��������������ÿ��Ǩ���ϵĽ⣬result�ŵ���һ��Ǩ���ϵĶ����
//
//		}
//		System.out.println("�ܹ�" + all_inequalitys.size() + "������ʽ��");
//
//		int e = 1;
//		for (ArrayList<String> inequalitys : all_inequalitys) {
//			System.out.println("��" + e + "������ʽ��");
//			for (String s : inequalitys) {
//				System.out.println(s);
//			}
//			System.out.println("***************");
//			e++;
//		}
//		System.out.println(testCase.size() + "-----����·���ĸ���");
//		// 1������document���󣬴�������xml�ĵ�
//		Document dom = DocumentHelper.createDocument();
//		// 2���������ڵ�TCS
//		org.dom4j.Element tcs = dom.addElement("TCS");
//		// 3����TCS�ڵ������version����
//
//		for (int i1 = 0; i1 < testCase.size(); i1++) {
//			// 4�������ӽڵ㼰�ڵ�����
//			Element testcase = tcs.addElement("testcase");
//			for (int j = 0; j < testCase.get(i1).getTransitionSet().size(); j++) {
//				// ��ӽڵ�
//				Element process = testcase.addElement("process");
//				Element operation = process.addElement("operation");
//				operation.setText(testCase.get(i1).getTransitionSet().get(j).getName());
//				Element input = process.addElement("input");
//				int random = -1;
//				if (random == -1) {
//					random = new Random().nextInt(testCase.get(i1).getTransitionSet().get(j).getResult().size());
//				}
//				input.setText(testCase.get(i1).getTransitionSet().get(j).getResult().get(random));
//				Element time = process.addElement("time");
//				time.setText(testCase.get(i1).getTransitionSet().get(j).getTranTimeName());
//			}
//			Element limit = testcase.addElement("limit");
//			Element operation = limit.addElement("operation");
//			String s = null;
//			for (int k = 0; k < all_inequalitys.get(i1).size(); k++) {
//				if (k == 0) {
//					s = all_inequalitys.get(i1).get(0).toString();
//				}
//				// System.out.println("
//				// "+all_inequalitys.get(i).get(k).toString());
//				if (/* (all_inequalitys.get(i).size()>1)&& */(k > 0)) {
//					// System.out.println("--===");
//					s = s + "," + all_inequalitys.get(i1).get(k).toString();
//					// System.out.println("sss:"+s);
//				}
//				// s = s.replace("&lt;", "<").replace("&gt;",
//				// ">").replace("&lt;=",
//				// "<=").replace("&gt;=", ">=");
//				// String s = all_inequalitys.get(i).get(k);
//
//			}
//			System.out.println("s:" + s);
//			operation.setText(s);
//		}
//
//		OutputFormat format = OutputFormat.createPrettyPrint();
//		// 6������xml�ļ�
//		File file = new File("E:\\XML\\EAElevatorV2ForXStream1-time.xml");
//		// formatXML(file);
//		XMLWriter writer;
//
//		try {
//			writer = new XMLWriter(new FileOutputStream(file), format);
//			writer.write(dom);
//			writer.close();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		
//		
//		//�������·��
//		System.out.println(testCase.size() + "-----����·���ĸ���");
//		int number = 0;
//		for(Automatic auto : testCase){
//			number++;
//			System.out.println("����·��" + number);
//			for(Transition tran : auto.getTransitionSet()){
//				System.out.println(tran.getName() + "------" + tran.getTranTimeName());
//			}	
//			System.out.println();
//		}

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
	
	public static void findEndStateList(Automatic auto){
		
		for(State state:auto.getStateSet()) {
			int k1= 0;
			for(Transition tran:auto.getTransitionSet()){//�ж�Ŀ��״̬�Ƿ��ѱ�����
				if(state.getName().equals(tran.getSource())){//�ҳ��Դ�״̬Ϊ����Ǩ��
					k1=1;
				}
			}
			if(k1==0){
				state.setFinalState(true);
			}		
		}
		
	}
	
public static ArrayList<Automatic> collectResult(ArrayList<Automatic> testcase){
		
		for (Automatic a : testcase) {

			for (Transition tran : a.getTransitionSet()) {
				ShowInfor.print("Ǩ������:" + tran.getIn() + "---" + tran.getCondition());
				List<String> result1 = new ArrayList<String>();// ���in��������ʵ�������
				List<String> result2 = new ArrayList<String>();// ���condition��������ʵ�������

				// ����in����Ĳ���ʽ�Ͳ������õ��������������һһ��Ӧ��map��������Ӳ���ʽ����
				// System.out.println("========================in========================");
				// ����in���������
				ShowInfor.print("in---->" + tran.getIn()); // in���������
				if (tran.getIn() == null) {
					result1.add(null);
				} else {

					if (tran.getIn().contains("--")) {
						List<List<String>> in_result = new ArrayList<List<String>>();
						List<String> in_result1;
						// String rrr = null;
						String getin[] = tran.getIn().split("--");
						for (int ii = 0; ii < getin.length; ii++) {
							if (!(GetMap.get_inMap(getin[ii]) == null)) {
								String inn = getin[ii].replace("false", "False").replace("true", "True").replace("->",
										"$");
								//in_result1 = Result1.getResult(inn);
								in_result1 = ResultOfTime.getResultOfTime(inn);
								if ((in_result1.size() > 0) && !(in_result1.get(0).equals(null))) {
									in_result.add(in_result1);
								}
							}
						}
						if ((in_result.size() > 0) && !(in_result.get(0).equals(null))) {
							ShowInfor.print("in_result.size()--->" + in_result.size());
							dis(0, in_result);
							result1 = re;
						}
					} else {
						if (!(GetMap.get_inMap(tran.getIn()) == null)) {// map����Ϊ�գ���û�в���
							String inn = tran.getIn().replace("false", "False").replace("true", "True").replace("->",
									"$");
							//result1 = Result1.getResult(inn);
							result1 = ResultOfTime.getResultOfTime(inn);
						} else {
							if ((GetMap.get_inMap(tran.getIn()) == null)) {
								result1.add(null);
							}
						}
					}
				}
				///////////////////////////////////////////// condition����ʼ///////////////////////////////////////
				ShowInfor.print("condition---->" + tran.getCondition());
				if (tran.getCondition() == null) {
					result2.add(null);
				} else {
					if (tran.getCondition() != null) {
						if (GetMap.get_condMap(tran.getCondition()) == null) {
							result2.add(null);
						} else {
							if (!(GetMap.get_condMap(tran.getCondition()) == null)) {
								String tra = tran.getCondition().replace("false", "False").replace("true", "True")
										.replace("->", "$");
								// result2 = Result.getResult(tra);
								System.out.println("tra----" + tra);
								//result2 = Result1.getResult(tra);
								result2 = ResultOfTime.getResultOfTime(tra);
								// result2 = testbdscs.getResult(tra);
								System.out.println("condition:"+tran.getCondition().toString());
								for (int ii = 0; ii < result2.size(); ii++) {
									ShowInfor.print("condition���" + ii + "Ϊ:" + result2.get(ii));
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
						String s = "��" + ii + "Ϊ:" + result.get(ii - 1);
						// Element input = process.addElement("input");
						// input.setText(s);
					}
				}
				tran.setResult(result);
				////////////////////////////////////////////////////////////////////////////////////
			} // for(Transition tran:a.getTransitionSet())

		}
		
		return testcase;

	}

	public static void produceXML(String path, List<Automatic> testCase, List<Automatic> testcaselimit) {

		ArrayList<ArrayList<String>> all_inequalitys = Get_inequality__1.get_AllInequalitys((ArrayList<Automatic>) testcaselimit);// ÿ���������������һ������ʽ��
		
		// 1������document���󣬴�������xml�ĵ�
		Document dom = DocumentHelper.createDocument();
		// 2���������ڵ�TCS
		org.dom4j.Element tcs = dom.addElement("TCS");
		// 3����TCS�ڵ������version����

		for (int i1 = 0; i1 < testCase.size(); i1++) {
			// 4�������ӽڵ㼰�ڵ�����
			Element testcase = tcs.addElement("testcase");
			for (int j = 0; j < testCase.get(i1).getTransitionSet().size(); j++) {
				// ��ӽڵ�
				Element process = testcase.addElement("process");
				Element operation = process.addElement("operation");
				operation.setText(testCase.get(i1).getTransitionSet().get(j).getName());
				Element input = process.addElement("input");
				int random = -1;
				if (random == -1) {
					random = new Random().nextInt(testCase.get(i1).getTransitionSet().get(j).getResult().size());
				}
				input.setText(testCase.get(i1).getTransitionSet().get(j).getResult().get(random));
				Element time = process.addElement("time");
				time.setText(testCase.get(i1).getTransitionSet().get(j).getTranTimeName());
			}
			Element limit = testcase.addElement("limit");
			Element operation = limit.addElement("operation");
			String s = null;
			for (int k = 0; k < all_inequalitys.get(i1).size(); k++) {
				if (k == 0) {
					s = all_inequalitys.get(i1).get(0).toString();
				}
				// System.out.println("
				// "+all_inequalitys.get(i).get(k).toString());
				if (/* (all_inequalitys.get(i).size()>1)&& */(k > 0)) {
					// System.out.println("--===");
					s = s + "," + all_inequalitys.get(i1).get(k).toString();
					// System.out.println("sss:"+s);
				}
				// s = s.replace("&lt;", "<").replace("&gt;",
				// ">").replace("&lt;=",
				// "<=").replace("&gt;=", ">=");
				// String s = all_inequalitys.get(i).get(k);

			}
			System.out.println("s:" + s);
			operation.setText(s);
		}

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

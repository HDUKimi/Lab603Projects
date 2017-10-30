package com.horstmann.violet.application.gui.util.ckt.output;

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
import com.horstmann.violet.application.gui.util.wj.util.GeneratePath;

public class forPlatform {

	public static void main(String[] args) {
		// ����ʱ���Զ������õ�һ��Automatic
		String xml = "UAVForXStreamPerformTestV6.xml";
		Automatic a = GetAutomatic.getAutomatic(xml);
	}

	/**
	 * ����ʱ���Զ������õ�һ��Automatic
	 * 
	 * @param xml
	 *            ����һ��xml�ĵ�
	 * @return
	 */
	public static Automatic plat1(String xml) {
		Automatic a = GetAutomatic.getAutomatic(xml);// ���ԭʼ��ʱ���Զ���
		// auto.getClockSet().toString().equals("[]")
		return a;
	}

	/**
	 * ����״̬��֣��õ�һ��Automatic
	 * 
	 * @param a
	 *            ����ԭʼʱ���Զ���
	 * @return
	 */
	public static Automatic plat2(Automatic a) {
		// Automatic a = GetAutomatic.getAutomatic(xml);//���ԭʼ��ʱ���Զ���
		Automatic automatic = IPR__1.iPR(a);// ��ò�ֺ��ʱ���Զ���
		return automatic;
	}

	/**
	 * ȥ������ʱ��Ǩ�ƣ��õ�һ��Automatic
	 * 
	 * @param a
	 *            ����ԭʼʱ���Զ���
	 * @return
	 */
	public static Automatic plat3(Automatic a) {
		// Automatic a = GetAutomatic.getAutomatic(xml);//���ԭʼ��ʱ���Զ���
		Automatic automatic = IPR__1.iPR(a);// ��ò�ֺ��ʱ���Զ���
		Automatic aTDRTAutomatic = ATDTR__1.aTDRT(automatic, a);// ���ȥ������ʱ��Ǩ�ƺ��ʱ���Զ���
		return aTDRTAutomatic;
	}

	/**
	 * ״̬���ǣ��õ�һ��״̬������Automatic
	 * 
	 * @param a
	 *            ����Ҫ����״̬���ǵ�ʱ���Զ���
	 * @return
	 */
	public static Automatic plat4(Automatic a) {
		// Automatic a = GetAutomatic.getAutomatic(xml);
		// Automatic automatic=IPR__1.iPR(a);//��ò�ֺ��ʱ���Զ���
		// Automatic
		// aTDRTAutomatic=ATDTR__1.aTDRT(automatic,a);//���ȥ������ʱ��Ǩ�ƺ��ʱ���Զ���
		Automatic DFStree = StateCoverage__1.DFSTree(a);
		return DFStree;
	}

	/**
	 * ״̬���ǣ��õ�һ����������ArrayList<Automatic>
	 * 
	 * @param a
	 *            ����Ҫ����״̬���ǵ�ʱ���Զ��������ǽ���DFSTree��ʱ���Զ���
	 * @return
	 */
	public static ArrayList<Automatic> plat5(Automatic a) {
		// Automatic a = GetAutomatic.getAutomatic(xml);
		// Automatic automatic=IPR__1.iPR(a);//��ò�ֺ��ʱ���Զ���
		// Automatic
		// aTDRTAutomatic=ATDTR__1.aTDRT(automatic,a);//���ȥ������ʱ��Ǩ�ƺ��ʱ���Զ���
		ArrayList<Automatic> testCase = StateCoverage__1.testCase(a);// �������״̬���ǵĳ����������
		return testCase;
	}

	/**
	 * ·�����ǣ��õ�һ����������ArrayList<Automatic>��in��condition��δ����
	 * 
	 * @param auto
	 *            ����Ҫ����״̬���ǵ�ʱ���Զ���
	 * @param n
	 *            ����Ҫ���ɵ�·����Ŀ
	 * @return
	 */
	public static ArrayList<Automatic> plat6(Automatic auto, int n) {
		// Automatic a = GetAutomatic.getAutomatic(xml);
		// Automatic automatic=IPR__1.iPR(a);//��ò�ֺ��ʱ���Զ���
		// Automatic auto=ATDTR__1.aTDRT(automatic,a);//���ȥ������ʱ��Ǩ�ƺ��ʱ���Զ���
		ArrayList<Automatic> testCase=GeneratePath.getFormatPathFromAutomatic(auto, n, 0);//n=2000,�������·�����ǵĳ����������
		return testCase;
	}

	/**
	 * ����������У��õ������������ArrayList<Automatic> ��Ǩ���ϵĲ���ʽ�������������limit������
	 * 
	 * @param xml
	 * @return
	 */
	public static ArrayList<Automatic> plat7(ArrayList<Automatic> testcase) {
		// Automatic a = GetAutomatic.getAutomatic(xml);
		// Automatic automatic=IPR__1.iPR(a);//��ò�ֺ��ʱ���Զ���
		// Automatic auto=ATDTR__1.aTDRT(automatic,a);//���ȥ������ʱ��Ǩ�ƺ��ʱ���Զ���
		// ArrayList<Automatic>
		// testCase=GeneratePath.getFormatPathFromAutomatic(auto,
		// 2000);//�������·�����ǵĳ����������
		ArrayList<Automatic> collectLimit = collectLimit(testcase);
		return collectLimit;
	}

	/**
	 * ʵ���������������ѽ������һ��
	 * 
	 * @param testcase
	 * @return
	 */
	public static ArrayList<Automatic> plat8(ArrayList<Automatic> collectLimit) {
		// Automatic a = GetAutomatic.getAutomatic(xml);
		// Automatic automatic=IPR__1.iPR(a);//��ò�ֺ��ʱ���Զ���
		// Automatic auto=ATDTR__1.aTDRT(automatic,a);//���ȥ������ʱ��Ǩ�ƺ��ʱ���Զ���
		// ArrayList<Automatic>
		// testCase=GeneratePath.getFormatPathFromAutomatic(auto,
		// 2000);//�������·�����ǵĳ����������
		// ArrayList<Automatic> collectLimit =
		// collectLimit(testcase);//�Ѳ�����������ʽ���һ��д��ʱ���Զ���
		ArrayList<Automatic> collectResult = collectResult(collectLimit);// ��ʵ����������һ��д��ʱ���Զ���
		return collectResult;
	}

	/**
	 * �Ѽ�ʱ���Զ�����������д��ʱ���Զ�����
	 * 
	 * @param auto
	 * @return
	 */
	public static ArrayList<Automatic> collectLimit(ArrayList<Automatic> auto) {
		// ArrayList<Automatic> automatic = new ArrayList<Automatic>();
		for (Automatic a : auto) {
			// ArrayList<Transition> TransitionSet = new
			// ArrayList<Transition>();
			for (Transition tran : a.getTransitionSet()) {
				String cond = "";
				ShowInfor.print(3, "in�������ݣ�" + tran.getIn());
				// if(tran.getIn().equals("null")){
				// }
				if (tran.getIn() == null || tran.getIn().equals("null")) {
				} else {
					if (tran.getIn().contains("--")) {
						String s[] = tran.getIn().split("--");
						for (String s1 : s) {
							if (cond == "") {
								cond = s1.split("#")[0];
							} else {
								cond = cond + "," + s1.split("#")[0];
							}
						}
					} else {
						if (cond == "") {
							cond = tran.getIn().split("#")[0];
						} else {
							cond = cond + "," + tran.getIn().split("#")[0];
						}
					}
				}
				ShowInfor.print(3, "condition�������ݣ�" + tran.getCondition());
				// if(tran.getCondition().equals("null")){
				// }
				if (tran.getCondition() == null || tran.getCondition().equals("null")) {
				} else {
					if (tran.getCondition().contains("--")) {
						String s[] = tran.getCondition().split("--");
						for (String s1 : s) {
							if (cond == "") {
								cond = s1.split("#")[0];
							} else {
								cond = cond + "," + s1.split("#")[0];
							}
						}
					} else {
						if (cond == "") {
							cond = tran.getCondition().split("#")[0];
						} else {
							cond = cond + "," + tran.getCondition().split("#")[0];
						}

					}
				}
				tran.setLimit(cond); // �Ѽ�����Լ������Ϊ��������
			}
		}
		return auto;
	}

	/**
	 * �Ѽ�ʱ���Զ���ʵ����������д��ʱ���Զ�����
	 * 
	 * @param testcase
	 * @return
	 */
	public static ArrayList<Automatic> collectResult(ArrayList<Automatic> testcase) {
		for (Automatic a : testcase) {
			for (Transition tran : a.getTransitionSet()) {
				ShowInfor.print(4, "Ǩ������:" + tran.getIn() + "---" + tran.getCondition());
				String sss = new String();
				List<String> result1 = new ArrayList<String>();// ���in��������ʵ�������
				List<String> result2 = new ArrayList<String>();// ���condition��������ʵ�������
				if (tran.getName() == null) {

				} else {
					if (tran.getName().contains("(")) {
						int index11 = tran.getName().replace("!", "").replace("?", "").indexOf("(");
						sss = tran.getName().substring(0, index11);
						ShowInfor.print(4, "Ǩ��(����)���ƣ�" + sss);
					} else {
						sss = tran.getName().replace("!", "").replace("?", "");
						ShowInfor.print(4, "Ǩ��(����)���ƣ�" + sss);
					}
				}

				tran.setName(sss);// ��Ǩ����������������Լ�������������ȥ��
				// ����in���������
				ShowInfor.print(4, "in---->" + tran.getIn()); // in���������
				if (tran.getIn() == null||tran.getIn().equals("null")) {
					result1.add(null);
				} else {
					if (tran.getIn().contains("--")) {
						List<List<String>> in_result = new ArrayList<List<String>>();
						List<String> in_result1;
						String getin[] = tran.getIn().split("--");
						for (int ii = 0; ii < getin.length; ii++) {
							if (!(GetMap.get_inMap(getin[ii]) == null)) {
								String inn = getin[ii].replace("false", "False").replace("true", "True").replace("->",
										"$");
								in_result1 = Result1.getResult(inn);
								if ((in_result1.size() > 0) && !(in_result1.get(0).equals(null))) {
									in_result.add(in_result1);
								}
							}
						}
						if ((in_result.size() > 0) && !(in_result.get(0).equals(null))) {
							ShowInfor.print(4, "in_result.size()--->" + in_result.size());
							dis(0, in_result);
							result1 = re;
						}
					} else {
						if (!(GetMap.get_inMap(tran.getIn()) == null)) {// map����Ϊ�գ���û�в���
							String inn = tran.getIn().replace("false", "False").replace("true", "True").replace("->",
									"$");
							// result1 = Result1.getResult(inn);
							result1 = Result.getResult(inn);
						} else {
							if ((GetMap.get_inMap(tran.getIn()) == null)) {
								result1.add(null);
							}
						}
					}
				}
				// ����condition
				ShowInfor.print(4, "condition---->" + tran.getCondition());
				if (tran.getCondition() == null||tran.getCondition().equals("null")) {
					result2.add(null);
				} else {
					if (tran.getCondition() != null) {
						if (GetMap.get_condMap(tran.getCondition()) == null) {
							result2.add(null);
						} else {
							if (!(GetMap.get_condMap(tran.getCondition()) == null)) {
								String tra = tran.getCondition().replace("false", "False").replace("true", "True")
										.replace("->", "$");
								// ShowInfor.print("tra----"+tra);
								// result2 = Result1.getResult(tra);
								result2 = Result.getResult(tra);
								for (int ii = 0; ii < result2.size(); ii++) {
									ShowInfor.print(4, "condition���" + ii + "Ϊ:" + result2.get(ii));
								}
							}
						}
					}
				}
				// ��Ͻ�
				List<String> result = new ArrayList<String>();// ���һ��Ǩ���ϵĽ��
				String res = new String();
				if ((result1.toString().equals("[null]")) && (result2.toString().equals("[null]"))) {
					res = sss + "%" + null;
					// res = null;
					result.add(res.toString());
				} else {
					if (!(result1.toString().equals("[null]")) && (result2.toString().equals("[null]"))) {
						for (String ttt2 : result1) {
							if (ttt2 != null) {
								if (ttt2.contains("flag=1")) {
									res = sss + "flag=1" + "%" + ttt2.replace("flag=1", "");
								} else {
									res = sss + "%" + ttt2;
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
									res = sss + "flag=1" + "%" + ttt3.replace("flag=1", "");
								} else {
									res = sss + "%" + ttt3;
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
												+ ttt3.replace("flag=1", "");
									} else {
										if (!(ttt2.contains("flag=1")) && !(ttt3.contains("flag=1"))) {
											res = sss + "%" + ttt2 + "," + ttt3;
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
				tran.setResult(result);
			} // for(Transition tran:a.getTransitionSet())
		}
		return testcase;
	}

	public static void produceXML(String path, List<Automatic> testcaseresult) {

		// 1������document���󣬴�������xml�ĵ�
		Document dom = DocumentHelper.createDocument();
		// 2���������ڵ�TCS
		org.dom4j.Element tcs = dom.addElement("TCS");
		// 3����TCS�ڵ������version����

		int index = 1;
		int testNum = 0;
		for (Automatic auto : testcaseresult) {

			List<List<String>> cases = new ArrayList<List<String>>();

			for (Transition t : auto.getTransitionSet()) {
				cases.add(t.getResult());
			}

			// cases��ŵ���һ��������������ÿ��Ǩ���ϵĽ⣬result�ŵ���һ��Ǩ���ϵĶ����
			/////
			int numm = 1;
			System.out.println("��" + index + "������·���Ͻ����" + numm);
			int testRouteNum = testcaseresult.size();
			int num = 2000 / testRouteNum + 1;// һ��·��100����������///////////////////////////////////////////////////////////////////////////////////
			for (int nn = 0; nn < cases.size(); nn++) {
				int n = cases.get(nn).size();
				if (n <= 0) {
					System.exit(0);
				} else {
					if (numm < num) {
						numm = numm * n;
					}
				}
				// ShowInfor.print("��"+nn+"��Ǩ���Ͻ����Ϊ��"+cases.get(nn).size());
			}
			ShowInfor.print(4, "��" + index++ + "������·���Ͻ����" + numm);
			// if(num>5000){ //����������������5000������
			// num = 5000;
			// }
			// int
			// num=3;//һ��·��100����������///////////////////////////////////////////////////////////////////////////////////
			if (num > numm) { // ���һ��·���Ͻ�С��100����ѡȡ��ʵ����
				num = numm;
			}
			System.out.println("ԭʼ�������" + numm + "ȡ��һ��·���������" + num);
			for (int n1 = 0; n1 < num; n1++) {
				// 4�������ӽڵ㼰�ڵ�����
				Element testcase = tcs.addElement("testcase");
				testNum++;
				ShowInfor.print(4, "�����������������" + testNum);
				ShowInfor.print(4, "---------------------cases.size():" + cases.size());
				for (int nn = 0; nn < cases.size(); nn++) {// cases.size��ʾ�ߵĸ���
					// ��ӽڵ�
					Element process = testcase.addElement("process");
					Element operation = process.addElement("operation");

					int random = -1;
					if (random == -1) {
						random = new Random().nextInt(cases.get(nn).size());
					}
					// ShowInfor.print("random-->"+random);
					String value = cases.get(nn).get(random);
					// ShowInfor.print("��value-->"+value);
					String[] cs = value.toString().split("%");
					// ShowInfor.print("operation-->"+cs[0]);
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
					// ShowInfor.print("input-->"+cs[1]);
				}
				// ShowInfor.print("---------------------testcase");
			}

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

	/**
	 * �����Ͻ�
	 */
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
}

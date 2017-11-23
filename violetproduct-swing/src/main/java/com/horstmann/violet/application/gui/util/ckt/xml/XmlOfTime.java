package com.horstmann.violet.application.gui.util.ckt.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.horstmann.violet.application.gui.util.ckt.handle.*;
import com.horstmann.violet.application.gui.util.ckt.output.ShowInfor;
import com.horstmann.violet.application.gui.util.ckt.testcase.GetMap;
import com.horstmann.violet.application.gui.util.ckt.testcase.OutSideResult;
import com.horstmann.violet.application.gui.util.ckt.testcase.PathCoverage_new;
import com.horstmann.violet.application.gui.util.ckt.testcase.Result1;
import com.horstmann.violet.application.gui.util.ckt.testcase.ResultOfTime;
import com.horstmann.violet.application.gui.util.ckt.testcase.pathCoverage2;
import com.horstmann.violet.application.gui.util.ckt.testcase.ResultOfTime;
import com.horstmann.violet.application.gui.util.wj.util.GeneratePath;
import com.horstmann.violet.application.gui.util.wqq.AutoMataTransfrom2.A;

/**
 * 时间约束不等式，实例化后得到的xml
 * 
 * @author Administrator
 *
 */
public class XmlOfTime {

	public static void main(String[] args) {

		//String xml = "EAElevatorV2ForXStream.xml";
		//String xml = "EAElevatorForXStream.xml";
		//String xml = "EAElevator7ForXStream.xml";
		String xml = "EAElevatorForXStream.xml";
		Automatic automatic = GetAutomatic.getAutomatic(xml);// 获得原始的时间自动机
		Automatic new_automatic = IPR__1.iPR(automatic);// 获得拆分后的时间自动机
		Automatic aTDRTAutomatic = ATDTR__1.aTDRT(new_automatic, automatic);// 获得去除抽象时间迁移后的时间自动机
		//搜索终止状态集合
		for(State state:aTDRTAutomatic.getStateSet()) {
			int k1= 0;
			for(Transition tran:aTDRTAutomatic.getTransitionSet()){//判断目标状态是否已被访问
				if(state.getName().equals(tran.getSource())){//找出以此状态为起点的迁移
					k1=1;
				}
			}
			if(k1==0){
				state.setFinalState(true);
			}		
		}
		
		//状态覆盖（时间）
		//ArrayList<Automatic> testCase = StateCoverage__1.testCase(aTDRTAutomatic);// 获得满足状态覆盖的抽象测试序列
		//ArrayList<Automatic> testCase =GeneratePath.getFormatPathFromAutomatic(aTDRTAutomatic, 9);
		//ArrayList<Automatic> testCase =GeneratePath.getFormatPathFromAutomatic(automatic, 3);
		//ArrayList<Automatic> testCase = pathCoverage2.testCase(aTDRTAutomatic);
		
		//路径覆盖
		ArrayList<Automatic> testCase = PathCoverage_new.testCase(aTDRTAutomatic);
		
		/*System.out.println(testCase.size() + "-----测试路径的个数");
		for(Automatic auto : testCase){
			for(Transition tran : auto.getTransitionSet()){
				System.out.println(tran.getName() + "------" + tran.getTranTimeName());
			}			
		}*/
		

		ArrayList<ArrayList<String>> all_inequalitys = Get_inequality__1.get_AllInequalitys(testCase);// 每个抽象测试序列有一个不等式组
		int i = 0;
		for (Automatic a : testCase) {
			System.out.println("===========================正在读取第" + i + "条测试用例");
			System.out.println("  ===>  测试用例名字:" + a.getName());
			int j = 1;
			for (Transition tran : a.getTransitionSet()) {
				System.out.println();
				System.out.println("======第" + j + "条迁移开始======");
				System.out.println("迁移名称" + tran.getName());
				System.out.println("迁移内容:" + tran.getIn() + "---" + tran.getCondition());
				List<String> result1 = new ArrayList<String>();// 存放in里面最终实例化结果
				List<String> result2 = new ArrayList<String>();// 存放condition里面最终实例化结果

				// 处理in里面的不等式和参数，得到参数类型与参数一一对应的map，进行添加不等式操作
				// System.out.println("========================in========================");
				System.out.println("in---->" + tran.getIn()); // in里面的内容
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
								in_result1 = ResultOfTime.getResultOfTime(inn);
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
						if (!(GetMap.get_inMap(tran.getIn()) == null)) {// map里面为空，即没有参数
							String inn = tran.getIn().replace("False", "false").replace("True", "true").replace("->",
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
				///////////////////////////////////////////// condition处理开始///////////////////////////////////////
				// System.out.println("condition---->"+tran.getCondition());
				if (tran.getCondition().equals("null")) {
					result2.add(null);
				} else {
					if (!tran.getCondition().equals("null")) {
						if (GetMap.get_condMap(tran.getCondition()) == null) {
							result2.add(null);
						} else {
							if (!(GetMap.get_condMap(tran.getCondition()) == null)) {
								String tra = tran.getCondition().replace("False", "false").replace("True", "true")
										.replace("->", "$");
								// result2 = Result.getResult(tra);
								System.out.println("tra----" + tra);
								//result2 = Result1.getResult(tra);
								result2 = ResultOfTime.getResultOfTime(tra);
								// result2 = testbdscs.getResult(tra);
								System.out.println("condition:"+tran.getCondition().toString());
								for (int ii = 0; ii < result2.size(); ii++) {
									System.out.println("condition里解"+ii+"为:"+result2.get(ii));
								}
							}
						}

					}
				}
				List<String> result = new ArrayList<String>();// 存放一条迁移上的结果
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
					// input.setText("解1为:"+null);
				} else {
					for (int ii = 1; ii <= result.size(); ii++) {
						System.out.println("解" + ii + "为:" + result.get(ii - 1));// 输出所有解
						String s = "解" + ii + "为:" + result.get(ii - 1);
						// Element input = process.addElement("input");
						// input.setText(s);
					}
				}
				tran.setResult(result);
				////////////////////////////////////////////////////////////////////////////////////
				// System.out.println(" ======第"+j+"条迁移结束======");
				j++;
			} // for(Transition tran:a.getTransitionSet())

			// cases里放的是一条测试用例上上每条迁移上的解，result放的是一条迁移上的多组解

		}
		
		System.out.println();
		System.out.println("----------------------------");
		System.out.println("总共" + all_inequalitys.size() + "个不等式组");
		System.out.println();
		
		int e = 1;
		for (ArrayList<String> inequalitys : all_inequalitys) {
			System.out.println("第" + e + "个不等式组");
			for (String s : inequalitys) {
				System.out.println(s);
			}
			System.out.println("***************");
			e++;
		}
		System.out.println(testCase.size() + "-----测试路径的个数");
		// 1、创建document对象，代表整个xml文档
		Document dom = DocumentHelper.createDocument();
		// 2、创建根节点TCS
		org.dom4j.Element tcs = dom.addElement("TCS");
		// 3、向TCS节点中添加version属性

		//************************************************************************************
		int rightNum = 0;
		int addNum = 0;
		
		for (int i1 = 0; i1 < testCase.size(); i1++) {
			int max = tranMaxNumber(testCase.get(i1).getTransitionSet());
			System.out.println("---------------------------------------------------"+max);
			for(int i2 = 0; i2 < max; i2++){
				// 4、生成子节点及节点内容
				Element testcase = tcs.addElement("testcase");
				for (int j = 0; j < testCase.get(i1).getTransitionSet().size(); j++) {
					// 添加节点
					Element process = testcase.addElement("process");
					Element operation = process.addElement("operation");
					operation.setText(testCase.get(i1).getTransitionSet().get(j).getName());
					Element input = process.addElement("input");
					//功能按顺序取值
					if(testCase.get(i1).getTransitionSet().get(j).getResult().size() > addNum){
						String cs =testCase.get(i1).getTransitionSet().get(j).getResult().get(addNum).replace("False", "false").replace("True", "true");
						cs = dealresult(cs);//把解中的！给替换掉
						input.setText(cs);
					}else{
						int random = -1;
						if (random == -1) {
							random = new Random().nextInt(testCase.get(i1).getTransitionSet().get(j).getResult().size());
						}
						String cs = testCase.get(i1).getTransitionSet().get(j).getResult().get(random).replace("False", "false").replace("True", "true");
						cs = dealresult(cs);
						input.setText(cs);
					}					
					
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
				rightNum++;
				System.out.println("第"+rightNum+"个测试用例集合----" + s);
				operation.setText(s);
				addNum++;
			}				
		}
		
		//**********************************************************
		//为了满足定义域覆盖，增加错误的测试用例
		int add2Num = 0;
		System.out.println();
		System.out.println("===========边界出错测试用例==========");
		int errorNum = 0;
		for (int i1 = 0; i1 < testCase.size(); i1++) {
			
			int max = tranMaxNumber(testCase.get(i1).getTransitionSet());
			System.out.println("---------------------------------------------------"+max);
			for(int i2 = 0; i2 < max; i2++){
				for (int k1 = 0; k1 < all_inequalitys.get(i1).size(); k1++) {
					String str = all_inequalitys.get(i1).get(k1).toString();
					if(Result1.getNumber(str)){
						// 4、生成子节点及节点内容
						Element testcase = tcs.addElement("testcase");
						testcase.addAttribute("result", "TIMEerror");
						for (int j = 0; j < testCase.get(i1).getTransitionSet().size(); j++) {
							// 添加节点
							Element process = testcase.addElement("process");
							Element operation = process.addElement("operation");
							operation.setText(testCase.get(i1).getTransitionSet().get(j).getName());
							Element input = process.addElement("input");
							//功能按顺序取值
							if(testCase.get(i1).getTransitionSet().get(j).getResult().size() > addNum){
								input.setText(testCase.get(i1).getTransitionSet().get(j).getResult().get(addNum).replace("False", "false").replace("True", "true"));
							}else{
								int random = -1;
								if (random == -1) {
									random = new Random().nextInt(testCase.get(i1).getTransitionSet().get(j).getResult().size());
								}
								input.setText(testCase.get(i1).getTransitionSet().get(j).getResult().get(random).replace("False", "false").replace("True", "true"));
							}	
							Element time = process.addElement("time");
							time.setText(testCase.get(i1).getTransitionSet().get(j).getTranTimeName());
						}
						Element limit = testcase.addElement("limit");
						Element operation = limit.addElement("operation");
						String s = null;
						for (int k = 0; k < all_inequalitys.get(i1).size(); k++) {
							
							if (k == 0 ) {
								s = all_inequalitys.get(i1).get(0).toString();
								if(k == k1){
									s = replece(s);
								}		
							}
							// System.out.println("
							// "+all_inequalitys.get(i).get(k).toString());
							if (/* (all_inequalitys.get(i).size()>1)&& */(k > 0)) {
								//s = s + "," + all_inequalitys.get(i1).get(k).toString();
								str = all_inequalitys.get(i1).get(k).toString();
								if(k == k1){
									s = s + "," + replece(str);
								}else{
									s = s + "," + str;
								}
								// System.out.println("sss:"+s);
							}
							// s = s.replace("&lt;", "<").replace("&gt;",
							// ">").replace("&lt;=",
							// "<=").replace("&gt;=", ">=");
							// String s = all_inequalitys.get(i).get(k);
						}
						errorNum++;
						System.out.println("第"+errorNum+"个测试用例集合----" + s);
						operation.setText(s);
						add2Num++;
						}
					}
				}			
			}
			
		System.out.println();
		System.out.println("*********************************");
		System.out.println("正确的测试用例个数：" + rightNum);
		System.out.println("错误的测试用例个数：" + errorNum);
		System.out.println("总测试用例个数为：" + (rightNum+errorNum));
		System.out.println("*********************************");
		System.out.println();
		
		//**********************************************************************************
		
	

		OutputFormat format = OutputFormat.createPrettyPrint();
		// 6、生成xml文件
		//File file = new File("E:\\XML\\EAElevatorForXStream-State-time.xml"); //状态覆盖
		File file = new File("E:\\XML\\EAElevatorForXStream-Path-GNmore-Time-outside.xml"); //路径覆盖
		// formatXML(file);
		XMLWriter writer;

		try {
			writer = new XMLWriter(new FileOutputStream(file), format);
			writer.write(dom);
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		//输出测试路径
		System.out.println(testCase.size() + "-----测试路径的个数");
		int number = 0;
		for(Automatic auto : testCase){
			number++;
			System.out.println("测试路径" + number);
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

	public static String replece(String s) {
		if (s.contains(">=")) {
			s = s.replace(">=", "<");
		} else {
			if (s.contains(">")) {
				s = s.replace(">", "<=");
			} else {
				if (s.contains("<=")) {
					s = s.replace("<=", ">");
				} else {
					if (s.contains("<")) {
						s = s.replace("<", ">=");
					}
				}
			}

		}
		return s;
	}

	//处理解中有！，把！替换，解取反
	public static String dealresult(String cs){
		//把解的值非去掉 换成确切值
		if(!(cs.toString() == null)){
			String[] val = cs.split(",");
			for(int k=0; k<val.length; k++){
				if(val[k].contains("!") && !(val[k].contains("!!"))){
					if(val[k].contains("false")){
						val[k] = val[k].replace("!", "").replace("false", "true");
					}else{
						if(val[k].contains("true")){
							val[k] = val[k].replace("!", "").replace("true", "false");
						}
					}
				}
			}
			String vall = val[0];
			for(int k=1; k<val.length; k++){
				vall = vall + "," +val[k];
			}
			cs = vall;
		}
		return cs;
	}
	
	public static int tranMaxNumber(ArrayList<Transition> tranSet){
		int max = 0;
		for (int i = 0; i < tranSet.size(); i++) {
			int num = tranSet.get(i).getResult().size();
			if (num > max) {
				max = num;
			}
		}
		return max;
	}

	public static void findEndStateList(Automatic auto) {

		for (State state : auto.getStateSet()) {
			int k1 = 0;
			for (Transition tran : auto.getTransitionSet()) {// 判断目标状态是否已被访问
				if (state.getName().equals(tran.getSource())) {// 找出以此状态为起点的迁移
					k1 = 1;
				}
			}
			if (k1 == 0) {
				state.setFinalState(true);
			}
		}

	}

	public static ArrayList<Automatic> collectResult(ArrayList<Automatic> testcase) {

		for (Automatic a : testcase) {

			for (Transition tran : a.getTransitionSet()) {
				ShowInfor.print(4, "迁移内容:" + tran.getIn() + "---" + tran.getCondition());
				List<String> result1 = new ArrayList<String>();// 存放in里面最终实例化结果
				List<String> result2 = new ArrayList<String>();// 存放condition里面最终实例化结果

				// 处理in里面的不等式和参数，得到参数类型与参数一一对应的map，进行添加不等式操作
				// System.out.println("========================in========================");
				// 处理in里面的内容
				ShowInfor.print(4, "in---->" + tran.getIn()); // in里面的内容
				if (tran.getIn() == null || tran.getIn().equals("null")) {
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
								// in_result1 = Result1.getResult(inn);
								in_result1 = ResultOfTime.getResultOfTime(inn);
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
						if (!(GetMap.get_inMap(tran.getIn()) == null)) {// map里面为空，即没有参数
							String inn = tran.getIn().replace("False", "false").replace("True", "true").replace("->",
									"$");
							// result1 = Result1.getResult(inn);
							result1 = ResultOfTime.getResultOfTime(inn);
						} else {
							if ((GetMap.get_inMap(tran.getIn()) == null)) {
								result1.add(null);
							}
						}
					}
				}
				///////////////////////////////////////////// condition处理开始///////////////////////////////////////
				ShowInfor.print(4, "condition---->" + tran.getCondition());
				if (tran.getCondition() == null || tran.getCondition().equals("null")) {
					result2.add(null);
				} else {
					if (tran.getCondition() != null || !tran.getCondition().equals("null")) {
						if (GetMap.get_condMap(tran.getCondition()) == null) {
							result2.add(null);
						} else {
							if (!(GetMap.get_condMap(tran.getCondition()) == null)) {
								String tra = tran.getCondition().replace("False", "false").replace("True", "true")
										.replace("->", "$");
								// result2 = Result.getResult(tra);
								System.out.println("tra----" + tra);
								// result2 = Result1.getResult(tra);
								result2 = ResultOfTime.getResultOfTime(tra);
								// result2 = testbdscs.getResult(tra);
								System.out.println("condition:" + tran.getCondition().toString());
								for (int ii = 0; ii < result2.size(); ii++) {
									ShowInfor.print(4, "condition里解" + ii + "为:" + result2.get(ii));
								}
							}
						}

					}
				}
				List<String> result = new ArrayList<String>();// 存放一条迁移上的结果
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
					// input.setText("解1为:"+null);
				} else {
					for (int ii = 1; ii <= result.size(); ii++) {
						System.out.println("解" + ii + "为:" + result.get(ii - 1));// 输出所有解
						String s = "解" + ii + "为:" + result.get(ii - 1);
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

		ArrayList<ArrayList<String>> all_inequalitys = Get_inequality__1
				.get_AllInequalitys((ArrayList<Automatic>) testcaselimit);// 每个抽象测试序列有一个不等式组

		// 1、创建document对象，代表整个xml文档
		Document dom = DocumentHelper.createDocument();
		// 2、创建根节点TCS
		org.dom4j.Element tcs = dom.addElement("TCS");
		// 3、向TCS节点中添加version属性

		// ************************************************************************************
		int rightNum = 0;
		int addNum = 0;
		for (int i1 = 0; i1 < testCase.size(); i1++) {
			int max = tranMaxNumber(testCase.get(i1).getTransitionSet());
			System.out.println("---------------------------------------------------" + max);
			for (int i2 = 0; i2 < max; i2++) {
				// 4、生成子节点及节点内容
				Element testcase = tcs.addElement("testcase");
				for (int j = 0; j < testCase.get(i1).getTransitionSet().size(); j++) {
					// 添加节点
					Element process = testcase.addElement("process");
					Element operation = process.addElement("operation");
					operation.setText(testCase.get(i1).getTransitionSet().get(j).getName());
					Element input = process.addElement("input");
					// 功能按顺序取值
					if (testCase.get(i1).getTransitionSet().get(j).getResult().size() > addNum) {
						input.setText(testCase.get(i1).getTransitionSet().get(j).getResult().get(addNum)
								.replace("False", "false").replace("True", "true"));
					} else {
						int random = -1;
						if (random == -1) {
							random = new Random()
									.nextInt(testCase.get(i1).getTransitionSet().get(j).getResult().size());
						}
						input.setText(testCase.get(i1).getTransitionSet().get(j).getResult().get(random)
								.replace("False", "false").replace("True", "true"));
					}

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
				rightNum++;
				System.out.println("第" + rightNum + "个测试用例集合----" + s);
				operation.setText(s);
				addNum++;
			}

			OutputFormat format = OutputFormat.createPrettyPrint();
			// 6、生成xml文件
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
	
	public static void producePerformanceXML(String path, List<Automatic> testCase) {
		
		ArrayList<ArrayList<String>> all_inequalitys = Get_inequality__1.get_AllInequalitys((ArrayList<Automatic>) testCase);// 每个抽象测试序列有一个不等式组
		int i = 0;
		for (Automatic a : testCase) {
			System.out.println("===========================正在读取第" + i + "条测试用例");
			System.out.println("  ===>  测试用例名字:" + a.getName());
			int j = 1;
			for (Transition tran : a.getTransitionSet()) {
				System.out.println();
				System.out.println("======第" + j + "条迁移开始======");
				System.out.println("迁移名称" + tran.getName());
				System.out.println("迁移内容:" + tran.getIn() + "---" + tran.getCondition());
				List<String> result1 = new ArrayList<String>();// 存放in里面最终实例化结果
				List<String> result2 = new ArrayList<String>();// 存放condition里面最终实例化结果

				// 处理in里面的不等式和参数，得到参数类型与参数一一对应的map，进行添加不等式操作
				// System.out.println("========================in========================");
				System.out.println("in---->" + tran.getIn()); // in里面的内容
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
								in_result1 = ResultOfTime.getResultOfTime(inn);
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
						if (!(GetMap.get_inMap(tran.getIn()) == null)) {// map里面为空，即没有参数
							String inn = tran.getIn().replace("False", "false").replace("True", "true").replace("->",
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
				///////////////////////////////////////////// condition处理开始///////////////////////////////////////
				// System.out.println("condition---->"+tran.getCondition());
				if (tran.getCondition().equals("null")) {
					result2.add(null);
				} else {
					if (!tran.getCondition().equals("null")) {
						if (GetMap.get_condMap(tran.getCondition()) == null) {
							result2.add(null);
						} else {
							if (!(GetMap.get_condMap(tran.getCondition()) == null)) {
								String tra = tran.getCondition().replace("False", "false").replace("True", "true")
										.replace("->", "$");
								// result2 = Result.getResult(tra);
								System.out.println("tra----" + tra);
								//result2 = Result1.getResult(tra);
								result2 = ResultOfTime.getResultOfTime(tra);
								// result2 = testbdscs.getResult(tra);
								System.out.println("condition:"+tran.getCondition().toString());
								for (int ii = 0; ii < result2.size(); ii++) {
									System.out.println("condition里解"+ii+"为:"+result2.get(ii));
								}
							}
						}

					}
				}
				List<String> result = new ArrayList<String>();// 存放一条迁移上的结果
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
					// input.setText("解1为:"+null);
				} else {
					for (int ii = 1; ii <= result.size(); ii++) {
						System.out.println("解" + ii + "为:" + result.get(ii - 1));// 输出所有解
						String s = "解" + ii + "为:" + result.get(ii - 1);
						// Element input = process.addElement("input");
						// input.setText(s);
					}
				}
				tran.setResult(result);
				////////////////////////////////////////////////////////////////////////////////////
				// System.out.println(" ======第"+j+"条迁移结束======");
				j++;
			} // for(Transition tran:a.getTransitionSet())

			// cases里放的是一条测试用例上上每条迁移上的解，result放的是一条迁移上的多组解

		}
		
		System.out.println();
		System.out.println("----------------------------");
		System.out.println("总共" + all_inequalitys.size() + "个不等式组");
		System.out.println();
		
		int e = 1;
		for (ArrayList<String> inequalitys : all_inequalitys) {
			System.out.println("第" + e + "个不等式组");
			for (String s : inequalitys) {
				System.out.println(s);
			}
			System.out.println("***************");
			e++;
		}
		System.out.println(testCase.size() + "-----测试路径的个数");
		// 1、创建document对象，代表整个xml文档
		Document dom = DocumentHelper.createDocument();
		// 2、创建根节点TCS
		org.dom4j.Element tcs = dom.addElement("TCS");
		// 3、向TCS节点中添加version属性
		
		//************************************************************************************
		int rightNum = 0;
		int addNum = 0;
		
		for (int i1 = 0; i1 < testCase.size(); i1++) {
			int max = tranMaxNumber(testCase.get(i1).getTransitionSet());
			System.out.println("---------------------------------------------------"+max);
			for(int i2 = 0; i2 < max; i2++){
				// 4、生成子节点及节点内容
				Element testcase = tcs.addElement("testcase");
				for (int j = 0; j < testCase.get(i1).getTransitionSet().size(); j++) {
					// 添加节点
					Element process = testcase.addElement("process");
					Element operation = process.addElement("operation");
					operation.setText(testCase.get(i1).getTransitionSet().get(j).getName());
					Element input = process.addElement("input");
					//功能按顺序取值
					if(testCase.get(i1).getTransitionSet().get(j).getResult().size() > addNum){
						String cs =testCase.get(i1).getTransitionSet().get(j).getResult().get(addNum).replace("False", "false").replace("True", "true");
						cs = dealresult(cs);//把解中的！给替换掉
						input.setText(cs);
					}else{
						int random = -1;
						if (random == -1) {
							random = new Random().nextInt(testCase.get(i1).getTransitionSet().get(j).getResult().size());
						}
						String cs = testCase.get(i1).getTransitionSet().get(j).getResult().get(random).replace("False", "false").replace("True", "true");
						cs = dealresult(cs);
						input.setText(cs);
					}					
					
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
				rightNum++;
				System.out.println("第"+rightNum+"个测试用例集合----" + s);
				operation.setText(s);
				addNum++;
			}				
		}

		OutputFormat format = OutputFormat.createPrettyPrint();
		// 6、生成xml文件
		//File file = new File("E:\\XML\\EAElevatorForXStream-State-time.xml"); //状态覆盖
		File file = new File(path); //路径覆盖
		// formatXML(file);
		XMLWriter writer;

		try {
			writer = new XMLWriter(new FileOutputStream(file), format);
			writer.write(dom);
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	
	public static void produceXML(String path, List<Automatic> testCase) {
		
		ArrayList<ArrayList<String>> all_inequalitys = Get_inequality__1.get_AllInequalitys((ArrayList<Automatic>) testCase);// 每个抽象测试序列有一个不等式组
		int i = 0;
		for (Automatic a : testCase) {
			System.out.println("===========================正在读取第" + i + "条测试用例");
			System.out.println("  ===>  测试用例名字:" + a.getName());
			int j = 1;
			for (Transition tran : a.getTransitionSet()) {
				System.out.println();
				System.out.println("======第" + j + "条迁移开始======");
				System.out.println("迁移名称" + tran.getName());
				System.out.println("迁移内容:" + tran.getIn() + "---" + tran.getCondition());
				List<String> result1 = new ArrayList<String>();// 存放in里面最终实例化结果
				List<String> result2 = new ArrayList<String>();// 存放condition里面最终实例化结果

				// 处理in里面的不等式和参数，得到参数类型与参数一一对应的map，进行添加不等式操作
				// System.out.println("========================in========================");
				System.out.println("in---->" + tran.getIn()); // in里面的内容
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
								in_result1 = ResultOfTime.getResultOfTime(inn);
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
						if (!(GetMap.get_inMap(tran.getIn()) == null)) {// map里面为空，即没有参数
							String inn = tran.getIn().replace("False", "false").replace("True", "true").replace("->",
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
				///////////////////////////////////////////// condition处理开始///////////////////////////////////////
				// System.out.println("condition---->"+tran.getCondition());
				if (tran.getCondition().equals("null")) {
					result2.add(null);
				} else {
					if (!tran.getCondition().equals("null")) {
						if (GetMap.get_condMap(tran.getCondition()) == null) {
							result2.add(null);
						} else {
							if (!(GetMap.get_condMap(tran.getCondition()) == null)) {
								String tra = tran.getCondition().replace("False", "false").replace("True", "true")
										.replace("->", "$");
								// result2 = Result.getResult(tra);
								System.out.println("tra----" + tra);
								//result2 = Result1.getResult(tra);
								result2 = ResultOfTime.getResultOfTime(tra);
								// result2 = testbdscs.getResult(tra);
								System.out.println("condition:"+tran.getCondition().toString());
								for (int ii = 0; ii < result2.size(); ii++) {
									System.out.println("condition里解"+ii+"为:"+result2.get(ii));
								}
							}
						}

					}
				}
				List<String> result = new ArrayList<String>();// 存放一条迁移上的结果
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
					// input.setText("解1为:"+null);
				} else {
					for (int ii = 1; ii <= result.size(); ii++) {
						System.out.println("解" + ii + "为:" + result.get(ii - 1));// 输出所有解
						String s = "解" + ii + "为:" + result.get(ii - 1);
						// Element input = process.addElement("input");
						// input.setText(s);
					}
				}
				tran.setResult(result);
				////////////////////////////////////////////////////////////////////////////////////
				// System.out.println(" ======第"+j+"条迁移结束======");
				j++;
			} // for(Transition tran:a.getTransitionSet())

			// cases里放的是一条测试用例上上每条迁移上的解，result放的是一条迁移上的多组解

		}
		
		System.out.println();
		System.out.println("----------------------------");
		System.out.println("总共" + all_inequalitys.size() + "个不等式组");
		System.out.println();
		
		int e = 1;
		for (ArrayList<String> inequalitys : all_inequalitys) {
			System.out.println("第" + e + "个不等式组");
			for (String s : inequalitys) {
				System.out.println(s);
			}
			System.out.println("***************");
			e++;
		}
		System.out.println(testCase.size() + "-----测试路径的个数");
		// 1、创建document对象，代表整个xml文档
		Document dom = DocumentHelper.createDocument();
		// 2、创建根节点TCS
		org.dom4j.Element tcs = dom.addElement("TCS");
		// 3、向TCS节点中添加version属性

		//************************************************************************************
		int rightNum = 0;
		int addNum = 0;
		
		for (int i1 = 0; i1 < testCase.size(); i1++) {
			int max = tranMaxNumber(testCase.get(i1).getTransitionSet());
			System.out.println("---------------------------------------------------"+max);
			for(int i2 = 0; i2 < max; i2++){
				// 4、生成子节点及节点内容
				Element testcase = tcs.addElement("testcase");
				for (int j = 0; j < testCase.get(i1).getTransitionSet().size(); j++) {
					// 添加节点
					Element process = testcase.addElement("process");
					Element operation = process.addElement("operation");
					operation.setText(testCase.get(i1).getTransitionSet().get(j).getName());
					Element input = process.addElement("input");
					//功能按顺序取值
					if(testCase.get(i1).getTransitionSet().get(j).getResult().size() > addNum){
						String cs =testCase.get(i1).getTransitionSet().get(j).getResult().get(addNum).replace("False", "false").replace("True", "true");
						cs = dealresult(cs);//把解中的！给替换掉
						input.setText(cs);
					}else{
						int random = -1;
						if (random == -1) {
							random = new Random().nextInt(testCase.get(i1).getTransitionSet().get(j).getResult().size());
						}
						String cs = testCase.get(i1).getTransitionSet().get(j).getResult().get(random).replace("False", "false").replace("True", "true");
						cs = dealresult(cs);
						input.setText(cs);
					}					
					
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
				rightNum++;
				System.out.println("第"+rightNum+"个测试用例集合----" + s);
				operation.setText(s);
				addNum++;
			}				
		}
	
		//**********************************************************
		//为了满足定义域覆盖，增加错误的测试用例
		int add2Num = 0;
		System.out.println();
		System.out.println("===========边界出错测试用例==========");
		int errorNum = 0;
		for (int i1 = 0; i1 < testCase.size(); i1++) {
			
			int max = tranMaxNumber(testCase.get(i1).getTransitionSet());
			System.out.println("---------------------------------------------------"+max);
			for(int i2 = 0; i2 < max; i2++){
				for (int k1 = 0; k1 < all_inequalitys.get(i1).size(); k1++) {
					String str = all_inequalitys.get(i1).get(k1).toString();
					if(Result1.getNumber(str)){
						// 4、生成子节点及节点内容
						Element testcase = tcs.addElement("testcase");
						testcase.addAttribute("result", "TIMEerror");
						for (int j = 0; j < testCase.get(i1).getTransitionSet().size(); j++) {
							// 添加节点
							Element process = testcase.addElement("process");
							Element operation = process.addElement("operation");
							operation.setText(testCase.get(i1).getTransitionSet().get(j).getName());
							Element input = process.addElement("input");
							
							//功能按顺序取值
							if(testCase.get(i1).getTransitionSet().get(j).getResult().size() > addNum){
								String cs =testCase.get(i1).getTransitionSet().get(j).getResult().get(addNum).replace("False", "false").replace("True", "true");
								cs = dealresult(cs);//把解中的！给替换掉
								input.setText(cs);
							}else{
								int random = -1;
								if (random == -1) {
									random = new Random().nextInt(testCase.get(i1).getTransitionSet().get(j).getResult().size());
								}
								String cs = testCase.get(i1).getTransitionSet().get(j).getResult().get(random).replace("False", "false").replace("True", "true");
								cs = dealresult(cs);
								input.setText(cs);
							}	
							
							Element time = process.addElement("time");
							time.setText(testCase.get(i1).getTransitionSet().get(j).getTranTimeName());
						}
						Element limit = testcase.addElement("limit");
						Element operation = limit.addElement("operation");
						String s = null;
						for (int k = 0; k < all_inequalitys.get(i1).size(); k++) {
							
							if (k == 0 ) {
								s = all_inequalitys.get(i1).get(0).toString();
								if(k == k1){
									s = replece(s);
								}		
							}
							// System.out.println("
							// "+all_inequalitys.get(i).get(k).toString());
							if (/* (all_inequalitys.get(i).size()>1)&& */(k > 0)) {
								//s = s + "," + all_inequalitys.get(i1).get(k).toString();
								str = all_inequalitys.get(i1).get(k).toString();
								if(k == k1){
									s = s + "," + replece(str);
								}else{
									s = s + "," + str;
								}
								// System.out.println("sss:"+s);
							}
							// s = s.replace("&lt;", "<").replace("&gt;",
							// ">").replace("&lt;=",
							// "<=").replace("&gt;=", ">=");
							// String s = all_inequalitys.get(i).get(k);
						}
						errorNum++;
						System.out.println("第"+errorNum+"个测试用例集合----" + s);
						operation.setText(s);
						add2Num++;
						}
					}
				}			
			}
			
		System.out.println();
		System.out.println("*********************************");
		System.out.println("正确的测试用例个数：" + rightNum);
		System.out.println("错误的测试用例个数：" + errorNum);
		System.out.println("总测试用例个数为：" + (rightNum+errorNum));
		System.out.println("*********************************");
		System.out.println();
		
		//**********************************************************************************
		
	
		
		all_inequalitys = Get_inequality__1.get_AllInequalitys((ArrayList<Automatic>) testCase);// 每个抽象测试序列有一个不等式组
//		// 1、创建document对象，代表整个xml文档
//		Document dom = DocumentHelper.createDocument();
//		// 2、创建根节点TCS
//		org.dom4j.Element tcs = dom.addElement("TCS");
//		// 3、向TCS节点中添加version属性
		
		int num1 = 0;//计算测试用例数量
		i = 0;
		
		//ArrayList<Map<Automatic, String>> cases = new ArrayList<Map<Automatic, String>>();//测试用例集合
		Map<Automatic, String> casesAndTime= new LinkedHashMap<Automatic, String>();//存放功能和时间对应关系
		
		//ArrayList<ArrayList<Automatic>> casesAndTime = new ArrayList<ArrayList<Automatic>>();//测试用例集合
		
		int indexk=-1;
		
		for (Automatic a : testCase) {
			System.out.println("===========================正在读取第" + i + "条测试用例");
			System.out.println("  ===>  测试用例名字:" + a.getName());
			indexk++;
			for(int k=0; k<a.getTransitionSet().size(); k++){							
				System.out.println("===========================================================================k值："+k);				
				Automatic pathAuto = new Automatic();//path是一个测试用例
				pathAuto.setInitState(a.getInitState());
				pathAuto.setName(a.getName());
				pathAuto.setStateSet(a.getStateSet());
				pathAuto.setTransitionSet(a.getTransitionSet());
				
				//求出这条路径时间总约束
				String s = null;
				for (int n = 0; n < all_inequalitys.get(indexk).size(); n++) {
					if (n == 0) {
						s = all_inequalitys.get(indexk).get(0).toString();
					}
					if ((n > 0)) {
						s = s + "," + all_inequalitys.get(indexk).get(n).toString();
					}
				}
				
				int yesOrNo = 0; //判断能否求出定义域外的值
				//回路边，同迁移，取值越界，但是取值相同
				int vvv = 0;
				for(int m=0; m<a.getTransitionSet().size(); m++){
					vvv=0;
					for(int n=0; n<m; n++){
						if(pathAuto.getTransitionSet().get(n).getName().equals(pathAuto.getTransitionSet().get(m).getName())){
							vvv = 1;
							pathAuto.getTransitionSet().get(m).setResult(pathAuto.getTransitionSet().get(n).getResult());
							break;
						}
					}
					if(vvv == 1){
						continue;
					}else{
					System.out.println("===========================================================================m值："+m);
					
					System.out.println();
					System.out.println("======第" + m + "条迁移开始======");
					Transition tran = pathAuto.getTransitionSet().get(m);//获取第m条迁移
					System.out.println("迁移名称" + tran.getName());
					System.out.println("迁移内容:" + tran.getIn() + "---" + tran.getCondition());
					List<String> result1 = new ArrayList<String>();// 存放in里面最终实例化结果
					List<String> result2 = new ArrayList<String>();// 存放condition里面最终实例化结果

					// 处理in里面的不等式和参数，得到参数类型与参数一一对应的map，进行添加不等式操作
					System.out.println("========================in========================");
					System.out.println("in---->" + tran.getIn()); // in里面的内容
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
									if(m == k){//第m条迁移取边界值外的
										in_result1 = OutSideResult.getResultOfTime(inn);
										if((in_result1.size() > 0) && !(in_result1.toString().equals("[null]"))){
											yesOrNo = 1;
											List<String> in_result11 = new ArrayList<String>();
											for(int t = 0; t<in_result1.size(); t++){
												String strr = in_result1.get(t).toString() + "***********";
												in_result11.add(strr);
												in_result1.remove(in_result1.get(t));
											}
											in_result1 = in_result11;
											System.out.println("边界外值+++++++++++++++++++++++++++++++++++++++++++++++++++++");
											System.out.println(in_result1.toString());
										}
									}else{ //取正确解
										in_result1 = ResultOfTime.getResultOfTime(inn);
										System.out.println("-边界内值-------------------------------------------------------");
										System.out.println(in_result1.toString());
									}
									//in_result1 = ResultOfTime.getResultOfTime(inn);
									//in_result1 = OutSideResult.getResultOfTime(inn);
									
									if ((in_result1.size() > 0) && !(in_result1.get(0).equals(null))) {
										in_result.add(in_result1);
									}
								}
							}
							if ((in_result.size() > 0) && !(in_result.get(0).equals(null))) {
//								System.out.println("in_result.size()--->" + in_result.size());
								dis(0, in_result);
								result1 = re;
							}
						} else {
							if (!(GetMap.get_inMap(tran.getIn()) == null)) {// map里面为空，即没有参数
								String inn = tran.getIn().replace("False", "false").replace("True", "true").replace("->",
										"$");
								//result1 = Result1.getResult(inn);
								if(m == k){
									result1 = OutSideResult.getResultOfTime(inn);
									if((result1.size() > 0) && !(result1.toString().equals("[null]"))){
										yesOrNo = 1;
										List<String> result11 = new ArrayList<String>();
										for(int t = 0; t<result1.size(); t++){
											String strr = result1.get(t).toString() + "***********";
											result11.add(strr);
											result1.remove(result1.get(t));
										}
										result1 = result11;
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
					///////////////////////////////////////////// condition处理开始///////////////////////////////////////
					System.out.println("condition----------->"+tran.getCondition());
					if (tran.getCondition().equals("null")) {
						result2.add(null);
					} else {
						if (!tran.getCondition().equals("null")) {
							if (GetMap.get_condMap(tran.getCondition()) == null) {
								result2.add(null);
							} else {
								if (!(GetMap.get_condMap(tran.getCondition()) == null)) {
									String tra = tran.getCondition().replace("False", "false").replace("True", "true")
											.replace("->", "$");
									// result2 = Result.getResult(tra);
//									System.out.println("tra----" + tra);
									//result2 = Result1.getResult(tra);
									if(m == k){
										result2 = OutSideResult.getResultOfTime(tra);
										if((result2.size() > 0) && !(result2.toString().equals("[null]"))){
											yesOrNo = 1;
											List<String> result11  = new ArrayList<String>();
											for(int t = 0; t<result2.size(); t++){
												String strr = result2.get(t).toString() + "***********";
												result11.add(strr);
												result2.remove(result2.get(t));
											}
											result2 = result11;
											System.out.println("边界外值+++++++++++++++++++++++++++++++++++++++++++++++++++++");
											System.out.println(result2.toString());
										}
									}else{
										result2 = ResultOfTime.getResultOfTime(tra);
										System.out.println("-边界内值-------------------------------------------------------");
										System.out.println(result2.toString());
									}
									//result2 = ResultOfTime.getResultOfTime(tra);
									//result2 = OutSideResult.getResultOfTime(tra);
									// result2 = testbdscs.getResult(tra);
//									System.out.println("condition:"+tran.getCondition().toString());
									for (int ii = 0; ii < result2.size(); ii++) {
//										System.out.println("condition里解"+ii+"为:"+result2.get(ii));
									}
								}
							}

						}
					}
					List<String> result = new ArrayList<String>();// 存放一条迁移上的结果
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
//					System.out.println("result--------------" + result);
					if (result.size() == 0) {
						// System.out.println("-----------------0000000---------------------");
						// Element input = process.addElement("input");
						// input.setText("解1为:"+null);
					} else {
						for (int ii = 1; ii <= result.size(); ii++) {
//							System.out.println("解" + ii + "为:" + result.get(ii - 1));// 输出所有解
							String ss = "解" + ii + "为:" + result.get(ii - 1);
							// Element input = process.addElement("input");
							// input.setText(s);
						}
					}
					tran.setResult(result);	
					}
				}
				if(yesOrNo == 1){		
					casesAndTime.put(pathAuto, s);
					System.out.println("*********************    一条成功用例         ********************");
					for(int tt=0; tt<pathAuto.getTransitionSet().size(); tt++){
						System.out.println(pathAuto.getTransitionSet().get(tt).getName());
						System.out.println(pathAuto.getTransitionSet().get(tt).getResult());
					}
					System.out.println("*******************************************************");
					System.out.println();
					//cases.add(casesAndTime);
					
					
					// 4、生成子节点及节点内容
					Element testcase = tcs.addElement("testcase");
					num1++;
					testcase.addAttribute("result", "GNerror");
					for(int m=0; m<pathAuto.getTransitionSet().size(); m++){
						Transition tran = pathAuto.getTransitionSet().get(m);
						// 添加节点
						Element process = testcase.addElement("process");
						Element operation = process.addElement("operation");
						operation.setText(tran.getName());
						Element input = process.addElement("input");
						System.out.println();
						System.out.println("*****"+tran.getResult().toString());
						System.out.println();
						if(tran.getResult().get(0).toString().contains("***********")){
							input.addAttribute("GN", "Error");
						}
						String str = tran.getResult().get(0).toString().replace("False", "false").replace("True", "true").replace("***********", "").replace("||", ",").replace("==", "=");
						str = dealresult(str);//把解中的！给替换掉
						input.setText(str);
//						if((tran.getResult().get(0).contains("False")) ||(tran.getResult().get(0).contains("True"))){
//							input.setText(tran.getResult().get(0).replace("False", "false").replace("True", "true").replace("***********", "").replace("==", "=")/*.replace("||", ",")*/);
//						}else{
//							input.setText(tran.getResult().get(0).toString().replace("***********", "").replace("==", "=").replace("||", ","));
//						}
						
						Element time = process.addElement("time");
						time.setText(tran.getTranTimeName());
					}
					Element limit = testcase.addElement("limit");
					Element operation = limit.addElement("operation");
					//operation.setText(casesAndTime.get(path));
					operation.setText(s);
									
				}						
			}
			// cases里放的是一条测试用例上上每条迁移上的解，result放的是一条迁移上的多组解
		}
		
		//for(int sum=0; sum<casesAndTime.size(); sum++){		
		System.out.println();
		System.out.println("*********************************");
		//System.out.println("正确的测试用例个数：" + rightNum);
		//System.out.println("错误的测试用例个数：" + errorNum);
		System.out.println("总测试用例个数为：" + (num1));
		System.out.println("*********************************");
		System.out.println();
		//**********************************************************************************

		
		

		OutputFormat format = OutputFormat.createPrettyPrint();
		// 6、生成xml文件
		//File file = new File("E:\\XML\\EAElevatorForXStream-State-time.xml"); //状态覆盖
		File file = new File(path); //路径覆盖
		// formatXML(file);
		XMLWriter writer;

		try {
			writer = new XMLWriter(new FileOutputStream(file), format);
			writer.write(dom);
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
//		//输出测试路径
//		System.out.println(testCase.size() + "-----测试路径的个数");
//		int number = 0;
//		for(Automatic auto : testCase){
//			number++;
//			System.out.println("测试路径" + number);
//			for(Transition tran : auto.getTransitionSet()){
//				System.out.println(tran.getName() + "------" + tran.getTranTimeName());
//			}	
//			System.out.println();
//		}
		
	}

}

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

import com.horstmann.violet.application.gui.util.ckt.handle.ATDTR__1;
import com.horstmann.violet.application.gui.util.ckt.handle.Automatic;
import com.horstmann.violet.application.gui.util.ckt.handle.GetAutomatic;
import com.horstmann.violet.application.gui.util.ckt.handle.Get_inequality__1;
import com.horstmann.violet.application.gui.util.ckt.handle.IPR__1;
import com.horstmann.violet.application.gui.util.ckt.handle.State;
import com.horstmann.violet.application.gui.util.ckt.handle.Transition;
import com.horstmann.violet.application.gui.util.ckt.testcase.GetMap;
import com.horstmann.violet.application.gui.util.ckt.testcase.PathCoverage_new;
import com.horstmann.violet.application.gui.util.ckt.testcase.Result1;
import com.horstmann.violet.application.gui.util.ckt.testcase.ResultOfTime;

public class borderTimeXML {

	public static void main(String[] args) {

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
				String sss = new String();
				List<String> result1 = new ArrayList<String>();// 存放in里面最终实例化结果
				List<String> result2 = new ArrayList<String>();// 存放condition里面最终实例化结果
				if(tran.getName().contains("(")){
					int index11=tran.getName().replace("!", "").replace("?", "").indexOf("(");
					sss=tran.getName().substring(0,index11);
					System.out.println("迁移(激励)名称："+sss);
				}
				else{
					sss = tran.getName().replace("!", "").replace("?", "");
					System.out.println("迁移(激励)名称："+sss);
				}

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
								in_result1 = Result1.getResult(inn);
								//in_result1 = ResultOfTime.getResultOfTime(inn);
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
							result1 = Result1.getResult(inn);
							//result1 = ResultOfTime.getResultOfTime(inn);
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
								System.out.println("tra----" + tra);
								result2 = Result1.getResult(tra);
								//result2 = ResultOfTime.getResultOfTime(tra);
								// result2 = testbdscs.getResult(tra);
								System.out.println("condition:"+tran.getCondition().toString());
								for (int ii = 0; ii < result2.size(); ii++) {
									System.out.println("condition里解"+ii+"为:"+result2.get(ii));
								}
							}
						}

					}
				}
////////////////////为input添加求出来的解
			List<String> result=new ArrayList<String>();//存放一条迁移上的结果
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
				// 4、生成子节点及节点内容
				Element testcase = tcs.addElement("testcase");
				for (int j = 0; j < testCase.get(i1).getTransitionSet().size(); j++) {
					// 添加节点
					Element process = testcase.addElement("process");
					Element operation = process.addElement("operation");
					//operation.setText(testCase.get(i1).getTransitionSet().get(j).getName());
					//Element input = process.addElement("input");
					
					//功能按顺序取值
					/*if(testCase.get(i1).getTransitionSet().get(j).getResult().size() > addNum){
						input.setText(testCase.get(i1).getTransitionSet().get(j).getResult().get(addNum).replace("False", "false").replace("True", "true"));
					}else{*/
						int random = -1;
						if (random == -1) {
							random = new Random().nextInt(testCase.get(i1).getTransitionSet().get(j).getResult().size());
						}
						String value = testCase.get(i1).getTransitionSet().get(j).getResult().get(random).replace("False", "false").replace("True", "true");
						String[] cs =value.toString().split("%");
						if(cs[0].contains("flag=1")){
							String name = cs[0].replace("flag=1", "");
							operation.setText(name);
						}else{
							if(!cs[0].contains("flag=1")){
								operation.setText(cs[0]);
							}
						}
						cs[1] = XmlOfTime.dealresult(cs[1]);						
						Element input = process.addElement("input");
						if(cs[0].contains("flag=1")){
							input.addAttribute("border","1");
							input.setText(cs[1]);
						}else{
							input.setText(cs[1]);
						}
					//}					
					
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
		
		System.out.println();
		System.out.println("*********************************");
		System.out.println("正确的测试用例个数：" + rightNum);
		//System.out.println("错误的测试用例个数：" + errorNum);
		//System.out.println("总测试用例个数为：" + (rightNum+errorNum));
		System.out.println("*********************************");
		System.out.println();
		
		//**********************************************************************************
		
	

		OutputFormat format = OutputFormat.createPrettyPrint();
		// 6、生成xml文件
		//File file = new File("E:\\XML\\EAElevatorForXStream-State-time.xml"); //状态覆盖
		File file = new File("E:\\XML\\EAElevatorForXStream-Path-border-Time.xml"); //路径覆盖
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
	
	public static void produceBorderXML(String path, ArrayList<Automatic> testCase) {
		
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
				String sss = new String();
				List<String> result1 = new ArrayList<String>();// 存放in里面最终实例化结果
				List<String> result2 = new ArrayList<String>();// 存放condition里面最终实例化结果
				if(tran.getName().contains("(")){
					int index11=tran.getName().replace("!", "").replace("?", "").indexOf("(");
					sss=tran.getName().substring(0,index11);
					System.out.println("迁移(激励)名称："+sss);
				}
				else{
					sss = tran.getName().replace("!", "").replace("?", "");
					System.out.println("迁移(激励)名称："+sss);
				}

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
								in_result1 = Result1.getResult(inn);
								//in_result1 = ResultOfTime.getResultOfTime(inn);
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
							result1 = Result1.getResult(inn);
							//result1 = ResultOfTime.getResultOfTime(inn);
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
								System.out.println("tra----" + tra);
								result2 = Result1.getResult(tra);
								//result2 = ResultOfTime.getResultOfTime(tra);
								// result2 = testbdscs.getResult(tra);
								System.out.println("condition:"+tran.getCondition().toString());
								for (int ii = 0; ii < result2.size(); ii++) {
									System.out.println("condition里解"+ii+"为:"+result2.get(ii));
								}
							}
						}

					}
				}
////////////////////为input添加求出来的解
			List<String> result=new ArrayList<String>();//存放一条迁移上的结果
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
				// 4、生成子节点及节点内容
				Element testcase = tcs.addElement("testcase");
				for (int j = 0; j < testCase.get(i1).getTransitionSet().size(); j++) {
					// 添加节点
					Element process = testcase.addElement("process");
					Element operation = process.addElement("operation");
					//operation.setText(testCase.get(i1).getTransitionSet().get(j).getName());
					//Element input = process.addElement("input");
					
					//功能按顺序取值
					/*if(testCase.get(i1).getTransitionSet().get(j).getResult().size() > addNum){
						input.setText(testCase.get(i1).getTransitionSet().get(j).getResult().get(addNum).replace("False", "false").replace("True", "true"));
					}else{*/
						int random = -1;
						if (random == -1) {
							random = new Random().nextInt(testCase.get(i1).getTransitionSet().get(j).getResult().size());
						}
						String value = testCase.get(i1).getTransitionSet().get(j).getResult().get(random).replace("False", "false").replace("True", "true");
						String[] cs =value.toString().split("%");
						if(cs[0].contains("flag=1")){
							String name = cs[0].replace("flag=1", "");
							operation.setText(name);
						}else{
							if(!cs[0].contains("flag=1")){
								operation.setText(cs[0]);
							}
						}
						cs[1] = XmlOfTime.dealresult(cs[1]);						
						Element input = process.addElement("input");
						if(cs[0].contains("flag=1")){
							input.addAttribute("border","1");
							input.setText(cs[1]);
						}else{
							input.setText(cs[1]);
						}
					//}					
					
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
		
		System.out.println();
		System.out.println("*********************************");
		System.out.println("正确的测试用例个数：" + rightNum);
		//System.out.println("错误的测试用例个数：" + errorNum);
		//System.out.println("总测试用例个数为：" + (rightNum+errorNum));
		System.out.println("*********************************");
		System.out.println();
		
		//**********************************************************************************
		
	

		OutputFormat format = OutputFormat.createPrettyPrint();
		// 6、生成xml文件
		//File file = new File("E:\\XML\\EAElevatorForXStream-State-time.xml"); //状态覆盖
		File file = new File(path); //路径覆盖//路径覆盖
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

}

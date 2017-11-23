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
		int i = 0;
		for (Automatic a : testCase) {
			System.out.println("===========================���ڶ�ȡ��" + i + "����������");
			System.out.println("  ===>  ������������:" + a.getName());
			int j = 1;
			for (Transition tran : a.getTransitionSet()) {
				System.out.println();
				System.out.println("======��" + j + "��Ǩ�ƿ�ʼ======");
				System.out.println("Ǩ������" + tran.getName());
				System.out.println("Ǩ������:" + tran.getIn() + "---" + tran.getCondition());
				String sss = new String();
				List<String> result1 = new ArrayList<String>();// ���in��������ʵ�������
				List<String> result2 = new ArrayList<String>();// ���condition��������ʵ�������
				if(tran.getName().contains("(")){
					int index11=tran.getName().replace("!", "").replace("?", "").indexOf("(");
					sss=tran.getName().substring(0,index11);
					System.out.println("Ǩ��(����)���ƣ�"+sss);
				}
				else{
					sss = tran.getName().replace("!", "").replace("?", "");
					System.out.println("Ǩ��(����)���ƣ�"+sss);
				}

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
						if (!(GetMap.get_inMap(tran.getIn()) == null)) {// map����Ϊ�գ���û�в���
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
								String tra = tran.getCondition().replace("False", "false").replace("True", "true")
										.replace("->", "$");
								System.out.println("tra----" + tra);
								result2 = Result1.getResult(tra);
								//result2 = ResultOfTime.getResultOfTime(tra);
								// result2 = testbdscs.getResult(tra);
								System.out.println("condition:"+tran.getCondition().toString());
								for (int ii = 0; ii < result2.size(); ii++) {
									System.out.println("condition���"+ii+"Ϊ:"+result2.get(ii));
								}
							}
						}

					}
				}
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
				// System.out.println(" ======��"+j+"��Ǩ�ƽ���======");
				j++;
			} // for(Transition tran:a.getTransitionSet())

			// cases��ŵ���һ��������������ÿ��Ǩ���ϵĽ⣬result�ŵ���һ��Ǩ���ϵĶ����

		}
		
		System.out.println();
		System.out.println("----------------------------");
		System.out.println("�ܹ�" + all_inequalitys.size() + "������ʽ��");
		System.out.println();
		
		int e = 1;
		for (ArrayList<String> inequalitys : all_inequalitys) {
			System.out.println("��" + e + "������ʽ��");
			for (String s : inequalitys) {
				System.out.println(s);
			}
			System.out.println("***************");
			e++;
		}
		System.out.println(testCase.size() + "-----����·���ĸ���");
		// 1������document���󣬴�������xml�ĵ�
		Document dom = DocumentHelper.createDocument();
		// 2���������ڵ�TCS
		org.dom4j.Element tcs = dom.addElement("TCS");
		// 3����TCS�ڵ������version����

		//************************************************************************************
		int rightNum = 0;
		int addNum = 0;
		
		for (int i1 = 0; i1 < testCase.size(); i1++) {
				// 4�������ӽڵ㼰�ڵ�����
				Element testcase = tcs.addElement("testcase");
				for (int j = 0; j < testCase.get(i1).getTransitionSet().size(); j++) {
					// ��ӽڵ�
					Element process = testcase.addElement("process");
					Element operation = process.addElement("operation");
					//operation.setText(testCase.get(i1).getTransitionSet().get(j).getName());
					//Element input = process.addElement("input");
					
					//���ܰ�˳��ȡֵ
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
				System.out.println("��"+rightNum+"��������������----" + s);
				operation.setText(s);
				addNum++;				
		}
		
		System.out.println();
		System.out.println("*********************************");
		System.out.println("��ȷ�Ĳ�������������" + rightNum);
		//System.out.println("����Ĳ�������������" + errorNum);
		//System.out.println("�ܲ�����������Ϊ��" + (rightNum+errorNum));
		System.out.println("*********************************");
		System.out.println();
		
		//**********************************************************************************
		
	

		OutputFormat format = OutputFormat.createPrettyPrint();
		// 6������xml�ļ�
		//File file = new File("E:\\XML\\EAElevatorForXStream-State-time.xml"); //״̬����
		File file = new File("E:\\XML\\EAElevatorForXStream-Path-border-Time.xml"); //·������
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
	
	public static void produceBorderXML(String path, ArrayList<Automatic> testCase) {
		
		ArrayList<ArrayList<String>> all_inequalitys = Get_inequality__1.get_AllInequalitys(testCase);// ÿ���������������һ������ʽ��
		int i = 0;
		for (Automatic a : testCase) {
			System.out.println("===========================���ڶ�ȡ��" + i + "����������");
			System.out.println("  ===>  ������������:" + a.getName());
			int j = 1;
			for (Transition tran : a.getTransitionSet()) {
				System.out.println();
				System.out.println("======��" + j + "��Ǩ�ƿ�ʼ======");
				System.out.println("Ǩ������" + tran.getName());
				System.out.println("Ǩ������:" + tran.getIn() + "---" + tran.getCondition());
				String sss = new String();
				List<String> result1 = new ArrayList<String>();// ���in��������ʵ�������
				List<String> result2 = new ArrayList<String>();// ���condition��������ʵ�������
				if(tran.getName().contains("(")){
					int index11=tran.getName().replace("!", "").replace("?", "").indexOf("(");
					sss=tran.getName().substring(0,index11);
					System.out.println("Ǩ��(����)���ƣ�"+sss);
				}
				else{
					sss = tran.getName().replace("!", "").replace("?", "");
					System.out.println("Ǩ��(����)���ƣ�"+sss);
				}

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
						if (!(GetMap.get_inMap(tran.getIn()) == null)) {// map����Ϊ�գ���û�в���
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
								String tra = tran.getCondition().replace("False", "false").replace("True", "true")
										.replace("->", "$");
								System.out.println("tra----" + tra);
								result2 = Result1.getResult(tra);
								//result2 = ResultOfTime.getResultOfTime(tra);
								// result2 = testbdscs.getResult(tra);
								System.out.println("condition:"+tran.getCondition().toString());
								for (int ii = 0; ii < result2.size(); ii++) {
									System.out.println("condition���"+ii+"Ϊ:"+result2.get(ii));
								}
							}
						}

					}
				}
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
				// System.out.println(" ======��"+j+"��Ǩ�ƽ���======");
				j++;
			} // for(Transition tran:a.getTransitionSet())

			// cases��ŵ���һ��������������ÿ��Ǩ���ϵĽ⣬result�ŵ���һ��Ǩ���ϵĶ����

		}
		
		System.out.println();
		System.out.println("----------------------------");
		System.out.println("�ܹ�" + all_inequalitys.size() + "������ʽ��");
		System.out.println();
		
		int e = 1;
		for (ArrayList<String> inequalitys : all_inequalitys) {
			System.out.println("��" + e + "������ʽ��");
			for (String s : inequalitys) {
				System.out.println(s);
			}
			System.out.println("***************");
			e++;
		}
		System.out.println(testCase.size() + "-----����·���ĸ���");
		// 1������document���󣬴�������xml�ĵ�
		Document dom = DocumentHelper.createDocument();
		// 2���������ڵ�TCS
		org.dom4j.Element tcs = dom.addElement("TCS");
		// 3����TCS�ڵ������version����

		//************************************************************************************
		int rightNum = 0;
		int addNum = 0;
		
		for (int i1 = 0; i1 < testCase.size(); i1++) {
				// 4�������ӽڵ㼰�ڵ�����
				Element testcase = tcs.addElement("testcase");
				for (int j = 0; j < testCase.get(i1).getTransitionSet().size(); j++) {
					// ��ӽڵ�
					Element process = testcase.addElement("process");
					Element operation = process.addElement("operation");
					//operation.setText(testCase.get(i1).getTransitionSet().get(j).getName());
					//Element input = process.addElement("input");
					
					//���ܰ�˳��ȡֵ
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
				System.out.println("��"+rightNum+"��������������----" + s);
				operation.setText(s);
				addNum++;				
		}
		
		System.out.println();
		System.out.println("*********************************");
		System.out.println("��ȷ�Ĳ�������������" + rightNum);
		//System.out.println("����Ĳ�������������" + errorNum);
		//System.out.println("�ܲ�����������Ϊ��" + (rightNum+errorNum));
		System.out.println("*********************************");
		System.out.println();
		
		//**********************************************************************************
		
	

		OutputFormat format = OutputFormat.createPrettyPrint();
		// 6������xml�ļ�
		//File file = new File("E:\\XML\\EAElevatorForXStream-State-time.xml"); //״̬����
		File file = new File(path); //·������//·������
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

}

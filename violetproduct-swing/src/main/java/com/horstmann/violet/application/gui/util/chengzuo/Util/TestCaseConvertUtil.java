package com.horstmann.violet.application.gui.util.chengzuo.Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.horstmann.violet.application.gui.homeTabbedPanel.HomeAllTabbedPanel;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.Pair;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.TestCase;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.TestCaseResult;
import com.horstmann.violet.application.gui.util.chengzuo.Bean.myProcess;

/**
 * �������� ����������
 * 
 * @author geek
 */
public class TestCaseConvertUtil {

	/**
	 * ���ַ������� ����ƥ�䣬��ȡ���
	 * 
	 * @param current
	 *            ƥ�䴮
	 * @param regEx
	 *            �������ʽ
	 * @return
	 */
	public static List<String> stringRegEx(String current, String regEx) {
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(current);
		List<String> result = new ArrayList<String>();
		while (mat.find()) {
			result.add(mat.group(1).replace("[", " ").replace("]", " ").trim());
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map testCaseStatistics(List<TestCase> testCases) {
		//����ܵ�ͳ�ƽ��
		Map finallStatisticsResult = new HashMap<>();
		
		//�Ƚ���
		Comparator<String> comp = new Comparator<String>() {
			public int compare(String o1, String o2) {
				return Integer.parseInt(o1) - Integer.parseInt(o2);
			}
		};
		List<Pair> hs = new ArrayList<>(),ts = new ArrayList<>();
		Map<String, List<Pair>> hb = new TreeMap<>(comp), ht = new TreeMap<>(comp);
		List<Pair> tmp,tmp2;
		for (TestCase testCase : testCases) {
			String speed = testCase.getResult().getWind_speed(), high = testCase.getResult().getTakeoff_alt(),
					battery = testCase.getResult().getBattery_remaining(), time = testCase.getResult().getTime();
			if ("0%".equals(battery)) {
				hs.add(new Pair(speed, high));
				ts.add(new Pair(speed, time));
			}
			
			if (!hb.containsKey(speed) || !ht.containsKey(speed)) {
				tmp = new ArrayList<>();
				tmp.add(new Pair(high, battery));
				hb.put(speed, tmp);

				tmp2 = new ArrayList<>();
				tmp2.add(new Pair(high, time));
				ht.put(speed, tmp2);
			} else {
				tmp = hb.get(speed);
				tmp.add(new Pair(high, battery));

				tmp2 = ht.get(speed);
				tmp2.add(new Pair(high, time));
			}

		}
	
		// 1.�߶ȡ����ٹ�ϵ
		finallStatisticsResult.put("high-speed", hs);
		// 2.���١�ʱ���ϵ
		finallStatisticsResult.put("time-speed", ts);
		// 3.�������߶ȹ�ϵ
		finallStatisticsResult.put("high-battery", hb);
		// 4.�߶ȡ�ʱ���ϵ
		finallStatisticsResult.put("high-time", ht);

		return finallStatisticsResult;

	}

	/**
	 * �����������ַ���ת���ɼ�������ʵ��
	 * 
	 * @param processList
	 *            ���������ַ���
	 * @return
	 */
	public static List<myProcess> string2ProcessList(String processList) {
		List<myProcess> list = new ArrayList<myProcess>();
		// 1.���ָ������������ַ���
		String[] tmp = processList.split("\\)");
		// 2.��ÿ�������ڵ���д���
		for (String process : tmp) {
			// 2.1.�ַ�����ʽ����
			process += ")";
			process = process.trim();
			// 2.2. �����ڵ�����
			myProcess my = new myProcess();
			// ���� �Ƿ�ɹ�ִ�� # ��ʾ�ɹ���& ��ʾʧ��
			if (process.contains("#")) {
				my.setProcessExec(true);
			} else {
				my.setProcessExec(false);
			}
			// 2.3.����ID
			my.setProcessID(Integer.parseInt(stringRegEx(process, "ProcessID:([\\s|\\S]*?)ProcessName:").get(0)));
			// 2.4.����Name
			my.setProcessName(stringRegEx(process, "ProcessName:([\\s|\\S]*?)\\(").get(0));
			// 2.5.��������
			my.setProcessParam(stringRegEx(process, "ProcessParameter:([\\s|\\S]*?)ProcessStatus:").get(0));
			// 2.6.����״̬
			my.setProcessStatus(stringRegEx(process, "ProcessStatus:([\\s|\\S]*?)\\)").get(0));
			// System.out.println(my);
			list.add(my);
		}
		return list;
	}

	/***
	 * �����ַ��� ���������������
	 * 
	 * @param str
	 *            �ӷ�������ȡ���ַ���
	 * @return
	 */
	public static void buildTestCaseList(List<TestCase> list, String str) {
		
		int starttype=HomeAllTabbedPanel.getStarttype();
		System.err.println(starttype);
		
		// 1.��*�Ž�������������
		String[] tmp = str.split("\\*");
		// 2.��ÿ�����������ַ������н�����װ
		for (String s : tmp) {
			TestCase testCase = new TestCase();
			// 2.1.�ַ�����ʽ����
			s = s.replace("\n", "");
			// 2.2.��ȡ��������ID
			testCase.setTestCaseID(stringRegEx(s, "testcCaseID:([\\s|\\S]*?)-->processList:").get(0));
			// 2.3.���켤������
			String processList = stringRegEx(s, "processList:([\\s|\\S]*?)-->execStatus").get(0);
			testCase.setProcessList(string2ProcessList(processList));
			// 2.4.��������ִ��״̬
			// ���� ˵�� : 1.������������,�޷���Ӧ��ִ�г����Ҳ��Ժ�ʱ:[��׼ȷ] 2.���Ժ�ʱ:
			// 3.����ִ�й����г�����ѭ�������׳��쳣!
			TestCaseResult testCaseResult = new TestCaseResult();
			String exeState = "", t = stringRegEx(s, "execStatus:([\\s|\\S]*?)-->resultStatus:").get(0);
			if (t.contains(":")) {
				String[] r = t.split(":");
				switch (r[0]) {
				case "1":
					exeState = "������������,�޷���Ӧ��ִ�г����Ҳ��Ժ�ʱ:" + r[1] + "[��׼ȷ]";
					break;
				case "2":
					exeState = "���Ժ�ʱ:" + r[1];
					break;
				}
				if(starttype == 2){
					testCaseResult.setExeTime(Double.valueOf(r[1]));
					testCaseResult.setTakeoff_alt(Double.valueOf(r[2].substring("takeoff_alt".length())));
					testCaseResult.setBattery_remaining(Double.valueOf(r[3].substring("battery_remaining".length())));
					testCaseResult.setTime(Double.valueOf(r[4].substring("time".length())));
					testCaseResult.setWind_speed(Double.valueOf(r[5].substring("wind_speed".length())));
				}else{
					testCaseResult.setExeTime(Double.valueOf(r[1]));
				}
				
			} else {
				exeState = "����ִ�й����г�����ѭ�������׳��쳣!";
			}
			testCaseResult.setResultDetail(exeState);
			testCase.setResult(testCaseResult);
			// 2.5.�����������
			// ���� ˵�� : 1.������������,�޷���Ӧ��ִ�г���! 2.����ִ�гɹ�!��ʱ: 3.������ֳ�����ѭ�������׳��쳣!
			String result = "";
			t = stringRegEx(s, "resultStatus:([\\s|\\S]*?)]").get(0);
			if (!t.contains(":")) {
				switch (t) {
				case "1":
					result = "������������,�޷���Ӧ��ִ�г���!";
					break;
				case "3":
					result = "������ֳ�����ѭ�������׳��쳣!";
					break;
				}
			} else {
				result = "����ִ�гɹ�!��ʱ:" + t.split(":")[1];
			}
			testCase.setState(result);
			// 2.6.�����������ָ�ʽ
			testCase.setDetail(testCase.showTestCase());
			// 2.7.���������������
			list.add(testCase);
		}
		if(starttype == 2){
			//�������0%
			for(int i=0;i<list.size();i++){
				if(i+1 <list.size()){
					if(list.get(i).getResult().getBattery_remaining().equals("0%") &&
						list.get(i+1).getResult().getBattery_remaining().equals("0%")){
						list.remove(i);
					}
				}
			}
		}
	}

	/**
	 * �ַ���д���ļ� ���ڲ�������̫����Ҫͨ���������ļ�����ʽ�鿴
	 * 
	 * @param content
	 */
	public static void string2File(String content) {
		System.out.println(content);
		try {
			File file = new File("testFile.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWritter = new FileWriter(file.getName(), true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(content);
			bufferWritter.close();

			System.out.println("File Save Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����Ϊ��λ��ȡ�ļ��������ڶ������еĸ�ʽ���ļ�
	 */
	public static String readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 0;
			// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
			while ((tempString = reader.readLine()) != null) {
				// ��ʾ�к�
				line++;
				sb.append(tempString);
				sb.append("\n");
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		// System.out.println(TestCaseConvertUtil.class.getResource("/").getPath());//user.dirָ���˵�ǰ��·��
		String str = readFileByLines(
				"E://��Ŀ//SVN//�������ƽ̨����//Lab603Projects//violetproduct-swing//src//main//java//com//horstmann//violet//application//gui//util//chengzuo//Util//result.txt");
	   
		List<TestCase> list = new ArrayList<>();
		buildTestCaseList(list, str);

		Map  m =testCaseStatistics(list);
		Map<String, List<Pair>> hb = (Map<String, List<Pair>>) m.get("high-battery");
		for (Map.Entry<String, List<Pair>> entry : hb.entrySet()) {  
			  System.out.println(entry.getKey());
			  for (Pair pair : entry.getValue()) {
				System.out.println("\t"+pair);
			}
		  
		}  
	}
}
package com.horstmann.violet.application.ckt.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class GetResult {

	public static void main(String[] args) {
		//String s = "a>=0&&a<=2#a:int--b>0&&b<=7#b:int--Commands[3][8]#Commands[3][8]:sz";
		//String s = "seq != 0#seq:int";
		//String s = "homelng>=0&&homelng<=120#homelng:double";
		//String s = "homelat>=20&&homelat<=54#homelat:double--homelng>=0&&homelng<=120#homelng:double--homealt>=50&&homealt<=100#homealt:double--Commands[3][8]#Commands[3][8]:fsz";
		//String s = "use_int==false#use_int:boolean";
		//String s = "baud#baud:string--baud#baud:enum_int--portname#portname:enum_string_error--portname#portname:enum_string_right";
		String s = "groundspeed>0&&groundspeed<4#groundspeed:float";
		String result = getResult(s);
	}
	
	public static String getResult(String initialCondition){
		String condition = initialCondition.trim()/*.substring(11, initialCondition.trim().length()-1)*/;
		System.out.println(condition);
		//Map<String,String> map = new HashMap<String,String>();//ӳ�����������֮���ϵ
		String[] con = condition.split("--");//--���ڰѲ���ʽ�ֿ�
		String resultOfInt = new String();
		String resultOfDouble = new String();
		String resultOfSz = new String();
		String resultOfBoolean = new String();
		String resultOfFsz = new String();
		String resultOfEnumInt = new String();
		String resultOfEnumStringError = new String();
		String resultOfEnumStringRight = new String();
		String resultOfString = new String();
		for(String s : con){
			if(s.contains("#")){ //s��һ������ʽ��#ǰ�ǲ���ʽ��#�������в���ʽ�в�����������
				String[] s1 = s.split("#");//s1[0]�ǲ���ʽ��s1[1]�����в�����������Ӧ����
				//Map<String,String> map = new HashMap<String,String>();//ӳ�����������֮���ϵ
				//map = getMap(s1[1]);//������������Ӧ���ʹ���map��
				//����ʽ�����ݲ����������
				String[] s11 = s1[1].split(":");
				Random rand = new Random();
				if(s11[1].equals("int")){
					String bdsAndCs = addBdsAndCs(getMap(s1[1]), 1);
					String bds = bdsAndCs.split("@@")[0];
					String cs = bdsAndCs.split("@@")[1];
					String bdsOfInt = s1[0] + "," + bds;
					int resultNum = 4;
					String solution = Mathematica.getSolutionOfInt(bdsOfInt, cs, resultNum);	
					String[] solutions = solution.split(",");
					int k = rand.nextInt(solutions.length);//������4�飬���ǿ�����ʵ��С��4
					//System.out.println("k= " + solutions.length);
					//System.out.println("��============�� " + solution);
					resultOfInt=resultOfInt + "," + solutions[k].toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
					//System.out.println("�����ͽ⣺" + resultOfInt);
				}
				if(s11[1].contains("double")){
					String bdsAndCs = addBdsAndCs(getMap(s1[1]), 2);
					String bds = bdsAndCs.split("@@")[0];
					String cs = bdsAndCs.split("@@")[1];
					String bdsOfDouble = s1[0] + "," + bds;
					int resultNum = 4;
					String solution = Mathematica.getSolutionOfDouble(bdsOfDouble, cs, resultNum);
					String[] solutions = solution.split(",");
					int k = rand.nextInt(solutions.length);
					//System.out.println("��============�� " + solution);
					resultOfDouble= resultOfDouble + "," + solutions[k].toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
					//System.out.println("С���ͽ⣺" + resultOfDouble);
				}
				if(s11[1].contains("float")){
					String bdsAndCs = addBdsAndCs(getMap(s1[1]), 3);
					String bds = bdsAndCs.split("@@")[0];
					String cs = bdsAndCs.split("@@")[1];
					String bdsOfFloat = s1[0] + "," + bds;
					int resultNum = 4;
					String solution = Mathematica.getSolutionOfDouble(bdsOfFloat, cs, resultNum);
					String[] solutions = solution.split(",");
					int k = rand.nextInt(solutions.length);
					//System.out.println("��============�� " + solution);
					resultOfDouble= resultOfDouble + "," + solutions[k].toString().replace("{", "").replace("}", "").replace(" ", "").replace("->", "=").replace("(", "").replace(")", "");
					//System.out.println("С���ͽ⣺" + resultOfDouble);
				}
				if(s11[1].contains("fsz")){
					resultOfFsz = resultOfSz + "," + getFszResult(s1[0]);
					//System.out.println("������⣺" + resultOfFsz);
				}else{
					if(s11[1].contains("sz")){
						resultOfSz = resultOfSz + "," + getSzResult(s1[0]);
						//System.out.println("����⣺" + resultOfSz);
					}
				}
				///////
				
				if(s11[1].contains("boolean")){
					resultOfBoolean = resultOfBoolean + "," + getBooleanResult(s1[0]);
					//System.out.println("�����⣺" + resultOfBoolean);
				}
				if(s11[1].contains("enum_int")){
					resultOfEnumInt = resultOfEnumInt + "," + getEnumIntResult(s1[0]);
					//System.out.println("�����⣺" + resultOfBoolean);
				}
				if(s11[1].contains("enum_string_error")){
					resultOfEnumStringError = resultOfEnumStringError + "," + getEnumStringErrorResult(s1[0]);
					//System.out.println("�����⣺" + resultOfBoolean);
				}
				if(s11[1].contains("enum_string_right")){
					resultOfEnumStringRight = resultOfEnumStringRight + "," + getEnumStringRightResult(s1[0]);
					//System.out.println("�����⣺" + resultOfBoolean);
				}
				if(s11[1].equals("string")){
					resultOfString = resultOfString + "," + getStringResult(s1[0]);
						//System.out.println("�����⣺" + resultOfBoolean);	
				}
				///////
				
			}
		}
		
		String resultOfTransition = new String();
		if(resultOfInt.length()>1){
			resultOfTransition = resultOfTransition + "," + resultOfInt.trim().substring(1, resultOfInt.length());
		}
		if(resultOfDouble.length()>1){
			resultOfTransition = resultOfTransition + "," + resultOfDouble.trim().substring(1, resultOfDouble.length());
		}
		if(resultOfFsz.length()>1){
			resultOfTransition = resultOfTransition + "," + resultOfFsz.trim().substring(1, resultOfFsz.length());
		}

		if(resultOfSz.length()>1){
			resultOfTransition = resultOfTransition + "," + resultOfSz.trim().substring(1, resultOfSz.length());
		}
		if(resultOfBoolean.length()>1){
			resultOfTransition = resultOfTransition + "," + resultOfBoolean.trim().substring(1, resultOfBoolean.length());
		}
		
		if(resultOfEnumInt.length()>1){
			resultOfTransition = resultOfTransition + "," + resultOfEnumInt.trim().substring(1, resultOfEnumInt.length());
		}
		if(resultOfEnumStringError.length()>1){
			resultOfTransition = resultOfTransition + "," + resultOfEnumStringError.trim().substring(1, resultOfEnumStringError.length());
		}
		if(resultOfEnumStringRight.length()>1){
			resultOfTransition = resultOfTransition + "," + resultOfEnumStringRight.trim().substring(1, resultOfEnumStringRight.length());
		}
		if(resultOfString.length()>1){
			resultOfTransition = resultOfTransition + "," + resultOfString.trim().substring(1, resultOfString.length());
		}
		
		if(resultOfTransition.length()>1){
			resultOfTransition = resultOfTransition.trim().substring(1, resultOfTransition.length());
		}
		System.out.println("Ǩ���Ͻ⣺" + resultOfTransition);
		return resultOfTransition;	
		
	}
	/**
	 * 
	 * @param stype ��#��� cs1��style��cs2:style
	 * @return Map<String String> ��Ų����Ͷ�Ӧ�Ĳ�������
	 */
	public static Map<String,String> getMap(String stype){
		Map<String,String> map = new HashMap<String,String>();//ӳ�����������֮���ϵ
		String[] css = stype.trim().split(",");
		for(String cs : css){
			String[] cs1 = cs.trim().split(":");//cs1[0]�ǲ�����cs1[1]�Ƕ�Ӧ����
			map.put(cs1[0], cs1[1]);
		}
		return map;
	}
	
	/**
	 * ���ݲ����Ͳ�����Ӧ��������Ӳ���ʽ�Ͳ���
	 * �����ַ�����ʽΪ  ����ʽ###����
	 * @param map
	 * @param n  1��������int���ͣ�2����С��double����
	 * @return
	 */
	public static String addBdsAndCs(Map<String,String> map, int n){
		
		/*Map<String,String> map = new HashMap<String,String>();//ӳ�����������֮���ϵ
		String[] css = stype.trim().split(",");
		for(String cs : css){
			String[] cs1 = cs.trim().split(":");//cs1[0]�ǲ�����cs1[1]�Ƕ�Ӧ����
			map.put(cs1[0], cs1[1]);
		}
		//return map;
*/		
		
		List<String> list=new ArrayList<String>(); //��������Ͳ���
		String s = new String();
		String cs = new String();
		Set<String> keySet = map.keySet();
		if(n==1){//�˲���ʽ������int��
			for(String key : keySet){
				if(map.get(key).equals("int")){
					s = s + "," + "-128<=" + key.trim() + "<=127";
					cs = cs + "," + key.trim();
				}
			}
		}
		if(n==2){//�˲���ʽ��С��double��
			for(String key : keySet){
				if(map.get(key).equals("double")){
					s = s + "," + "-128.00<=" + key.trim() + "<=127.00";
					cs = cs + "," + key.trim();
				}
			}
		}
		if(n==3){//�˲���ʽ��С��float��
			for(String key : keySet){
				if(map.get(key).equals("float")){
					s = s + "," + "0<=" + key.trim() + "<=128.00";
					cs = cs + "," + key.trim();
				}
			}
		}
		
		if(s.length()>1){
			s = s.trim().substring(1, s.length());
		}
		if(cs.length()>1){
			cs = cs.trim().substring(1, cs.length());
		}
		String ss = s + "@@" + cs;
		return ss;
		
	}
	/**
	 * ��ȡboolean�Ľ�
	 * @param s
	 * @return
	 */
	public static String getBooleanResult(String s){
		if(s.contains("!")){
			if(s.contains("false")){
				s = s.replace("!", "").replace("==", "=").replace("(", "").replace(")", "").replace("false", "true");
			}else{
				if(s.contains("true")){
					s = s.replace("==", "=").replace("(", "").replace(")", "").replace("true", "false");
				}
			}
		}else{
			s = s.replace("==", "=").replace("(", "").replace(")", "");
		}
		return s;
	}
	
	/**
	 * ��ȡString�Ľ�
	 * @param s
	 * @return
	 */
	public static String getStringResult(String s){
		if(!s.contains("=")){
			String str1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			StringBuffer ss = new StringBuffer();
			Random rand = new Random();
			for(int k=0; k<4; k++){
				int number = rand.nextInt(52);
				ss.append(str1.charAt(number));
			}
			s = s + "=" + ss.toString();
		}
		return s;
	}
	/**
	 * ��ȡenum_int�Ľ�
	 * @param s
	 * @return
	 */
	public static String getEnumIntResult(String s){
		if(!s.contains("=")){
			Random rand = new Random();
			int k = rand.nextInt(12);
			switch(k){
			case 0 : s = s + "=1200";break;
			case 1 : s = s + "=2400";break;
			case 2 : s = s + "=4800";break;
			case 3 : s = s + "=9600";break;
			case 4 : s = s + "=19200";break;
			case 5 : s = s + "=38400";break;
			case 6 : s = s + "=57600";break;
			case 7 : s = s + "=111100";break;
			case 8 : s = s + "=115200";break;
			case 9 : s = s + "=500000";break;
			case 10 : s = s + "=921600";break;
			case 11 : s = s + "=1500000";break;
			}
		}
		return s;
	}
	/**
	 * ��ȡenum_string_error�Ľ�
	 * @param s
	 * @return
	 */
	public static String getEnumStringErrorResult(String s){
		if(!s.contains("=")){
			Random rand = new Random();
			int k = rand.nextInt(6);
			switch(k){
			case 0 : s = s + "=/COM1";break;
			case 1 : s = s + "=/COM2";break;
			case 2 : s = s + "=/COM3";break;
			case 3 : s = s + "=/com1";break;
			case 4 : s = s + "=/com2";break;
			case 5 : s = s + "=/com3";break;
			}
		}
		return s;
	}
	/**
	 * ��ȡenum_string_right�Ľ�
	 * @param s
	 * @return
	 */
	public static String getEnumStringRightResult(String s){
		if(!s.contains("=")){
			Random rand = new Random();
			int k = rand.nextInt(2);
			switch(k){
			case 0 : s = s + "=COM3";break;
			case 1 : s = s + "=com3";break;
			}
		}
		return s;
	}
	/**
	 * ��ȡ����Ľ�
	 * @param s
	 * @return
	 */
	public static String/*double[][]*/ getSzResult(String s){
		int a = s.trim().indexOf("[");
		int b = s.trim().indexOf("]");
		int a1 = s.trim().lastIndexOf("[");
		int b1 = s.trim().lastIndexOf("]");
		String str = s.trim().substring(0, a);////////////----------szҪ���str��Ӧ��
		int m = Integer.parseInt(s.substring(a+1, b));
		int n = Integer.parseInt(s.substring(a1+1, b1));
		double[][] sz = new double[m][n];
		String resultOfSz = new String();
		for(int i=0; i<m; i++){
			for(int j=0; j<n; j++){
				Random rand = new Random();
				if(j == 0){
					sz[i][j] = (int)(rand.nextInt(7) + 16);//16~22
					resultOfSz = resultOfSz + "," + str + "[" + i + "][" + j + "]"+ "=" + sz[i][j];
				}else{
					if(j==1 || j==2 || j==3 || j==4){
						sz[i][j] = 0;
						resultOfSz = resultOfSz + "," + str + "[" + i + "][" + j + "]"+ "=" + sz[i][j];
					}else{
						if(j == 5){//20~54
							sz[i][j] = Math.random() * 34 + 20/*Double.valueOf(df.format((Math.random() * 34 + 20)))*/;
							resultOfSz = resultOfSz + "," + str + "[" + i + "][" + j + "]"+ "=" + sz[i][j];
						}else{
							if(j == 6){//70~130
								sz[i][j] = Math.random() * 60 + 70;
								resultOfSz = resultOfSz + "," + str + "[" + i + "][" + j + "]"+ "=" + sz[i][j];
							}else{
								if(j == 7){//0~120
									sz[i][j] = Math.random() * 120;
									resultOfSz = resultOfSz + "," + str + "[" + i + "][" + j + "]"+ "=" + sz[i][j];
								}else{
									sz[i][j] = 0;//j>8��ȡֵ��Ϊ0
									resultOfSz = resultOfSz + "," + str + "[" + i + "][" + j + "]"+ "=" + sz[i][j];
								}
							}
						}
					}
				}					
			}
		}
		if(resultOfSz.length()>1){
			resultOfSz = resultOfSz.trim().substring(1, resultOfSz.length());
		}
		
		return resultOfSz;
	}
	
	/**
	 * ��ȡ������Ľ�
	 * @param s
	 * @return
	 */
	public static String/*double[][]*/ getFszResult(String s){
		int a = s.trim().indexOf("[");
		int b = s.trim().indexOf("]");
		int a1 = s.trim().lastIndexOf("[");
		int b1 = s.trim().lastIndexOf("]");
		String str = s.trim().substring(0, a);////////////----------szҪ���str��Ӧ��
		int m = Integer.parseInt(s.substring(a+1, b));
		int n = Integer.parseInt(s.substring(a1+1, b1));
		String[][] sz = new String[m][n];
		String resultOfSz = new String();
		for(int i=0; i<m; i++){
			for(int j=0; j<n; j++){
				Random rand = new Random();
				if(j==0){
					int r = (int)(rand.nextInt(7) + 16);
					sz[i][j] = r+"";
					resultOfSz = resultOfSz + "," + str + "[" + i + "][" + j + "]"+ "=" + sz[i][j];
				}else{
					String str1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
					StringBuffer ss = new StringBuffer();
					for(int k=0; k<4; k++){
						int number = rand.nextInt(52);
						ss.append(str1.charAt(number));
					}
					sz[i][j] = ss.toString();
					resultOfSz = resultOfSz + "," + str + "[" + i + "][" + j + "]"+ "=" + sz[i][j];	
				}		
			}
		}
		if(resultOfSz.length()>1){
			resultOfSz = resultOfSz.trim().substring(1, resultOfSz.length());
		}
		
		return resultOfSz;
	}
	
}

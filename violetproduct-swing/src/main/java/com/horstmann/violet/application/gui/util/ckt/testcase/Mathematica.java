package com.horstmann.violet.application.gui.util.ckt.testcase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wolfram.jlink.KernelLink;
import com.wolfram.jlink.MathLinkException;
import com.wolfram.jlink.MathLinkFactory;



public class Mathematica {
	public static void main(String[] args) {
		String s1 = "target_climb_rate==0.1,-32768.00<=target_climb_rate<=32769.00";
		String s2 = "target_climb_rate";
		String solution = Mathematica.getSolution4(s1, s2, 5);
		System.out.println(solution.toString());
	}
	
	
	private static String parameter1;    //不等式组合
	private static String parameter2;    //参数
	private static String result;        //所求的解

	private static Map<String, String> varToVoc;

	public static String getSolution(String param1, String param2) {

		KernelLink ml = null;

		String path = "-linkmode launch -linkname 'D:\\coding\\10.2\\MathKernel.exe'";
		try {

			ml = MathLinkFactory.createKernelLink(path);
		} catch (MathLinkException e) {
			System.out.println("Fatal error opening link: " + e.getMessage());
			return null;
		}

		try {
			// Get rid of the initial InputNamePacket the kernel will send
			// when it is launched.
			ml.discardAnswer();

			ml.evaluate("<<MyPackage.m");
			ml.discardAnswer();

			ml.putFunction("FindInstance", 3);

			ml.endPacket();
			ml.waitForAnswer();
			String result1 = ml.getString();

			// If you want the result back as a string, use evaluateToInputForm
			// or evaluateToOutputForm. The second arg for either is the
			// requested page width for formatting the string. Pass 0 for
			// PageWidth->Infinity. These methods get the result in one
			// step--no need to call waitForAnswer.
			String strResult = ml.evaluateToOutputForm(
					"A = SetAccuracy[FindInstance[" + param1 + ", " + param2
					+ ", 50], 20]; A[[RandomInteger[{1, Length[A]}]]]",
					0);
			// System.out.println(strResult);
			return strResult;

		} catch (MathLinkException e) {
			System.out.println("MathLinkException occurred: " + e.getMessage());
		} finally {
			ml.close();
		}
		return null;

	}
	/**
	 * 返回求的整数值解
	 * @param param1  bds
	 * @param param2  cs
	 * @return
	 */
	public static KernelLink ml;
	public static final String PATH ="-linkmode launch -linkname 'D:\\coding\\10.2\\MathKernel.exe'";
	public static String getSolution2(String param1, String param2, int resultNum) {

//		KernelLink ml = null;
//
//		String path = "-linkmode launch -linkname 'D:\\Mathematica\\10.2\\MathKernel.exe'";
		try {

			if (ml == null) {

				ml = MathLinkFactory.createKernelLink(PATH);
				ml.discardAnswer();

				ml.evaluate("<<MyPackage.m");
				ml.discardAnswer();

				ml.putFunction("FindInstance", 3);

				ml.endPacket();
				ml.waitForAnswer();
				String result1 = ml.getString();
			}
		} catch (MathLinkException e) {
			System.out.println("Fatal error opening link: " + e.getMessage());
			return null;
		}

		try {
			// Get rid of the initial InputNamePacket the kernel will send
			// when it is launched.
//			ml.discardAnswer();
//
//			ml.evaluate("<<MyPackage.m");
//			ml.discardAnswer();
//
//			ml.putFunction("FindInstance", 3);
//
//			ml.endPacket();
//			ml.waitForAnswer();
//			String result1 = ml.getString();

			// If you want the result back as a string, use evaluateToInputForm
			// or evaluateToOutputForm. The second arg for either is the
			// requested page width for formatting the string. Pass 0 for
			// PageWidth->Infinity. These methods get the result in one
			// step--no need to call waitForAnswer.

			// 将param1和param2中参数标识符按顺序全都替换为26个字母,防止变量命名不符合mathematica规则导致无法运算
			System.out.println("进入mma中整数不等式"+param1);
			System.out.println("进入mma中整数参数"+param2);
			replace(param1, param2);
			//System.out.println(parameter1 + "======" + parameter2);
			//			System.out.println("不等式："+parameter1 + "----》所求参数：" + parameter2);
			String strResult;
			if(resultNum==1){
				strResult = ml.evaluateToOutputForm(
						"FindInstance[{" + parameter1 + "}, {"
								+ parameter2 + "},Integers, 1]", 0); 
			}else{
				strResult = ml.evaluateToOutputForm(
						"FindInstance[{" + parameter1 + "}, {"
								+ parameter2 + "},Integers, 10]", 0); 
			}
			/*String strResult = ml.evaluateToOutputForm(
					"SetAccuracy[FindInstance[{" + parameter1 + "}, {"
							+ parameter2 + "}, 1], 3]", 0);*/
//			String strResult = ml.evaluateToOutputForm(
//					"FindInstance[{" + parameter1 + "}, {"
//							+ parameter2 + "},Integers, 1]", 0);   //////////////////////////////////////40
			System.out.println("mma中整数不等式"+parameter1);
			System.out.println("mma中整数参数"+parameter2);
			// System.out.println(strResult);
			// 再将替换的参数标识符替换回原样
			recovery(strResult);
			//System.out.println(result);
			return result;

		} catch (Exception e) {
			System.out.println("MathLinkException occurred: " + e.getMessage());
		} finally {
//			ml.close();
			// ml = null;
		}
		return null;
	}
	/**
	 * 返回求的布尔解
	 * @param param1  boolbds
	 * @param param2  boolcs
	 * @return
	 */
	public static String getSolution3(String param1, String param2, int resultNum) {

		KernelLink ml = null;
		String path = "-linkmode launch -linkname 'D:\\Mathematica\\10.2\\MathKernel.exe'";
		try {

			ml = MathLinkFactory.createKernelLink(path);
		} catch (MathLinkException e) {
			System.out.println("Fatal error opening link: " + e.getMessage());
			return null;
		}

		try {
			// Get rid of the initial InputNamePacket the kernel will send
			// when it is launched.
			ml.discardAnswer();

			ml.evaluate("<<MyPackage.m");
			ml.discardAnswer();

			ml.putFunction("FindInstance", 3);

			ml.endPacket();
			ml.waitForAnswer();
			String result1 = ml.getString();

			// If you want the result back as a string, use evaluateToInputForm
			// or evaluateToOutputForm. The second arg for either is the
			// requested page width for formatting the string. Pass 0 for
			// PageWidth->Infinity. These methods get the result in one
			// step--no need to call waitForAnswer.

			// 将param1和param2中参数标识符按顺序全都替换为26个字母,防止变量命名不符合mathematica规则导致无法运算
			replace(param1, param2);
			//System.out.println(parameter1 + "======" + parameter2);
			//			System.out.println("不等式："+parameter1 + "----》所求参数：" + parameter2);
			String strResult = ml.evaluateToOutputForm(
					"FindInstance[{" + parameter1 + "}, {"
							+ parameter2 + "},Booleans, resultNum]", 0);
//			System.out.println("mma中布尔不等式"+parameter1);
//			System.out.println("mma中布尔参数"+parameter2);
			// System.out.println(strResult);
			// 再将替换的参数标识符替换回原样
			recovery(strResult);
			//System.out.println(result);
			return result;

		} catch (MathLinkException e) {
			System.out.println("MathLinkException occurred: " + e.getMessage());
		} finally {
			ml.close();
		}
		return null;
	}





	/**
	 * 返回求的小数的解
	 * @param param1  boolbds
	 * @param param2  boolcs
	 * @return
	 */
	public static String getSolution4(String param1, String param2, int resultNum) {

//		KernelLink ml = null;
//		
//		String path = "-linkmode launch -linkname 'D:\\Mathematica\\10.2\\MathKernel.exe'";
		try {

			//ml = MathLinkFactory.createKernelLink(path);
			if (ml == null) {

				ml = MathLinkFactory.createKernelLink(PATH);
				ml.discardAnswer();

				ml.evaluate("<<MyPackage.m");
				ml.discardAnswer();

				ml.putFunction("FindInstance", 3);

				ml.endPacket();
				ml.waitForAnswer();
				String result1 = ml.getString();
			}
		} catch (MathLinkException e) {
			System.out.println("Fatal error opening link: " + e.getMessage());
			return null;
		}

		try {
			// Get rid of the initial InputNamePacket the kernel will send
			// when it is launched.
//			ml.discardAnswer();
//
//			ml.evaluate("<<MyPackage.m");
//			ml.discardAnswer();
//
//			ml.putFunction("FindInstance", 3);
//
//			ml.endPacket();
//			ml.waitForAnswer();
//			String result1 = ml.getString();

			// If you want the result back as a string, use evaluateToInputForm
			// or evaluateToOutputForm. The second arg for either is the
			// requested page width for formatting the string. Pass 0 for
			// PageWidth->Infinity. These methods get the result in one
			// step--no need to call waitForAnswer.

			// 将param1和param2中参数标识符按顺序全都替换为26个字母,防止变量命名不符合mathematica规则导致无法运算
			replace(param1, param2);
			//System.out.println(parameter1 + "======" + parameter2);
			//			System.out.println("不等式："+parameter1 + "----》所求参数：" + parameter2);
//			String strResult = ml.evaluateToOutputForm(
//					"SetAccuracy[FindInstance[{" + parameter1 + "}, {"
//							+ parameter2 + "}, 1], 3]", 0);////////////////////////////////////////////////
			String strResult;
			if(resultNum==1){
				strResult = ml.evaluateToOutputForm(
						"FindInstance[{" + parameter1 + "}, {"
								+ parameter2 + "}, 1]", 0); 
			}else{
				strResult = ml.evaluateToOutputForm(
						"FindInstance[{" + parameter1 + "}, {"
								+ parameter2 + "}, 10]", 0); 
			}

//			String strResult = ml.evaluateToOutputForm(
//					"FindInstance[{" + parameter1 + "}, {"
//							+ parameter2 + "}, resultNum]", 0);
			
			
			System.out.println("mma中小数不等式"+parameter1);
            System.out.println("mma中小数参数"+parameter2);
			System.out.println(strResult);
			// 再将替换的参数标识符替换回原样
			recovery(strResult);
			System.out.println(result);
			return result;

		} catch (Exception e) {
			System.out.println("MathLinkException occurred: " + e.getMessage());
		} finally {
//			ml.close();
		}
		return null;
	}


	
	/*public static void closes(){
		if(ml==null){
			ml.close();
		}
	}*/



	/**
	 * 将计算结果中的变量还原为本来的参数标识符
	 * 
	 * @param strResult
	 */
	private static void recovery(String strResult) {
		Set<String> keySet = varToVoc.keySet();
		for (String key : keySet) {
			strResult = strResult.replace(varToVoc.get(key) + " ", key.trim());
			//System.out.println(varToVoc.get(key)+"--"+key.trim());//输出函数对应关系
		}
		result = strResult;
	}

	/**
	 * 将参数标识符用字母表替换
	 * 
	 * @param param1
	 * @param param2
	 */
	private static void replace1(String param1, String param2) {
		String[] vars = param2.split(",");
		varToVoc = new HashMap<String, String>();
		char vocabulary = 'a';		
		for (String var : vars) {
			param2 = param2.replace(var.trim(), vocabulary + "");
			param1 = param1.replace(var.trim(), vocabulary + "");
			varToVoc.put(var.trim(), vocabulary + "");
			vocabulary = (char) (vocabulary + 1);
		}
		parameter1 = param1;
		parameter2 = param2;
		// System.out.println(param1 + "---" + param2);

	}
	
	
	
	/**
	 * 将参数标识符用字母表替换
	 * 
	 * @param param1
	 * @param param2
	 */
	private static void replace(String param1, String param2) {
		String[] vars = param2.split(",");
		varToVoc = new HashMap<String, String>();
		char vocabulary = 'a';
		// 替换参数组
		for (int i = 0; i < vars.length; i++) {
			varToVoc.put(vars[i].trim(), vocabulary + "");
			vars[i] = vocabulary + "";
			vocabulary = (char) (vocabulary + 1);
		}
		// 替换不等式组
		String[] inequals = param1.split(",");
		//for (int i = inequals.length-1; i >=0; i--) {
		for (int i = 0; i <inequals.length; i++) {
			//System.out.println(i+":::"+inequals[i].trim());
			String inequ = inequals[i].trim();
			List<String> names = filter(inequ);         //
			for (String name : names) {
				
				String value = varToVoc.get(name);   //a b c
				//System.out.println("取代的值："+value);
				//System.out.println("替换的参数："+name);
				inequals[i] = inequals[i].replace(name, value);//////有问题
				//System.out.println("替换后不等式"+inequals[i]);
			}
		}

		parameter1 = compose(inequals);
		parameter2 = compose(vars);
		// System.out.println(param1 + "---" + param2);

	}

	private static String compose(String[] vars) {
		String str = "";
		for (int i = 0; i < vars.length; i++) {
			if (i < vars.length - 1) {
				str = str + vars[i] + ",";
			} else {
				str = str + vars[i];
			}
		}
		return str;
	}

	private static List<String> filter(String inequ) {
		List<String> list=new ArrayList<String>();
		List<String> list1=new ArrayList<String>();
		Set<String> keySet = varToVoc.keySet();
		String[] split = inequ.split("[-+<>=!||]=?");
		
		for (String string : split) {
            //System.out.println("++++++  "+string);
			for (String key : keySet) {
				if (string.trim().equals(key.trim())) {
					//System.out.println("--->  "+string.trim());
					list.add(string.trim());
					//return string.trim();
				}
			}
		}
		
		list.sort(new Comparator<String>() {
			@Override
			public int compare(String str1, String str2) {
				
				return str2.length()-str1.length();
			}
		});
		return list;
		//return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	


}

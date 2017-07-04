package com.horstmann.violet.application.gui.util.ckt.testcase;

import org.junit.Test;

import com.horstmann.violet.application.gui.util.ckt.testcase.Test_1;
import com.wolfram.jlink.KernelLink;
import com.wolfram.jlink.MathLinkException;
import com.wolfram.jlink.MathLinkFactory;



public class Test_1 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String param1 = "x__tt_y==11,y__t__v>22";
		String param2 ="x__tt_y,y__t__v";
		int resultNum = getMathNum(param1);
		System.out.println(resultNum+"==========================");
		String solution = Mathematica.getSolution2(param1, param2,resultNum);
		System.out.println(solution);
	}

	
	
	
	
	
	public static String getSolution(String param1, String param2) {

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
			String strResult = ml.evaluateToOutputForm(
					"A = SetAccuracy[FindInstance[" + param1 + ", " + param2
							+ ", 1], 3]; A[[RandomInteger[{1, Length[A]}]]]",
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

	public static String getSolution2(String param1, String param2) {

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
			String strResult = ml.evaluateToOutputForm(
					"SetAccuracy[FindInstance[{" + param1 + "}, {" + param2
							+ "}, 1], 3]", 0);
			// System.out.println(strResult);
			return strResult;

		} catch (MathLinkException e) {
			System.out.println("MathLinkException occurred: " + e.getMessage());
		} finally {
			ml.close();
		}
		return null;
	}
	
	
	/*public static void main(String[] args) {
		String param1 = "x>y";
		String param2 ="x,y";
		String solution = getSolution2(param1,param2);
		System.out.println(solution);
	} 
		*/
	public static int getMathNum(String bds) {
		String[] bNum = bds.split(",");
		int resultNum;
		if (bNum.length > 8) {
			resultNum = 1;
		} else {
			resultNum = 5;
		}
		return resultNum;
	}

	
}




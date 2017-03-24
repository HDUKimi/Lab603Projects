package com.horstmann.violet.application.gui.util.wj.util;

import java.io.File;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import com.horstmann.violet.application.gui.util.ckt.handle.*;
import com.horstmann.violet.application.gui.util.ckt.testcase.*;
import com.horstmann.violet.application.gui.util.wj.bean.*;

import Jama.Matrix;

public class Test {
	public static void main(String args[]) throws Exception {
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long time1=System.currentTimeMillis();  
		String TimeString = time.format(new java.util.Date());
		System.out.println(TimeString);
		
		// ÓÃ·¨
		Automatic automatic = GetAutomatic.getAutomatic("UAVForXStream.xml");
		GeneratePath.getFormatPathFromAutomatic(automatic, 8000);
//		GeneratePath.getPerformPathFromAutomatic(automatic);
		

		TimeString = time.format(new java.util.Date());
		System.out.println(TimeString);
	}
}

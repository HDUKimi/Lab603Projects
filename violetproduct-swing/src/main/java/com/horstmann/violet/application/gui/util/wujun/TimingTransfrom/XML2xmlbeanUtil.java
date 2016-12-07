package com.horstmann.violet.application.gui.util.wujun.TimingTransfrom;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.horstmann.violet.application.gui.util.wujun.TimingTransfrom.TimingDiagramGraph;


public class XML2xmlbeanUtil {
	// ���ڵ�����
	private static TimingDiagramGraph td;
	
	public static void read(File file) {
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			byte[] buf = new byte[is.available()];
			is.read(buf);
			// �������޸ĸ��ڵ���
			td = XStreamTransformUtil.toBean(TimingDiagramGraph.class, buf);
		} catch (Exception e) {
			System.out.println("��ȡ�쳣:" + e);
		} finally {
			try{
				is.close();
			} catch (Exception e) {
				System.out.println("�ر��쳣");
			}
		}
	}
	
	public static TimingDiagramGraph getTd(File file) {
		
		read(file);
		return td;
	}
}

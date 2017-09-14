package com.horstmann.violet.application.gui.util.tanchao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderWrapperTool {

	/**
	 * 对文件名按中文+数字从大到小进行排序
	 * @param file
	 * @return
	 */
	public static String[] SortFileNameList(String[] file){
		
		List<OrderWrapper> namelist=new ArrayList<>();
		
		for(String name:file){
			namelist.add(new OrderWrapper(name));
		}
		
		Collections.sort(namelist);
		
		String[] filename=new String[namelist.size()];
		
		int index=0;
		for(OrderWrapper s:namelist){
			filename[index]=s.getName();
			index++;
		}
		
		return filename;
	}
	
}

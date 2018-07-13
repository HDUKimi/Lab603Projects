package com.horstmann.violet.application.ckt.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.sound.midi.Synthesizer;

public class test {

	public static void main(String[] args) {
		/*State s = null;
		State s1 = new State();
		s1.setStateName("sss");
		//s = s1;
		System.out.println(s);
		if(s == null){
			System.out.println("----");
		}else{
			System.out.println("****");
		}*/
		/*String s = "name=s1";
		String s1 = s.substring(5, s.length());
		System.out.println(s1);
		String s2 = "condition={input}";
		String s21 = s2.substring(11, s2.length()-1);
		System.out.println(s21);*/
		Map<Transition, Integer> map = new HashMap<>();
		ArrayList<Transition> outTrans = new ArrayList<Transition>();
		Transition t1 = new Transition();
		Transition t2 = new Transition();
		Transition t3 = new Transition();
		Transition t4 = new Transition();
		t1.setName("t1");
		t2.setName("t3");
		t3.setName("t2");
		t4.setName("t4");
		outTrans.add(t1);
		outTrans.add(t2);
		outTrans.add(t3);
		outTrans.add(t4);
		for(int k=0; k<outTrans.size(); k++){
			map.put(outTrans.get(k), 0);
		}
		
		System.out.println("map.values()===" + map.values());
		System.out.print("map.keySet()===");
		for(Transition t : map.keySet()){
			System.out.print(t.getName() + " ");
		}
		System.out.println();
		/*System.out.println("---" + map.keySet());
		System.out.println("===" + map.values());*/
		for(int i=0; i<map.size(); i++){
			if(i%2 == 0){
				map.put(outTrans.get(i), map.get(outTrans.get(i))+1);
			}else{
				map.put(outTrans.get(i), map.get(outTrans.get(i))+3);
			}	
		}
		System.out.println("=====原始数据=====");	
		System.out.println("t1=" + map.get(t1) + " t2=" + map.get(t2) + " t3=" + map.get(t3) + " t4=" + map.get(t4));
		
		System.out.println("map.values()===" + map.values());
		System.out.print("map.keySet()===");
		for(Transition t : map.keySet()){
			System.out.print(t.getName() + " ");
		}
		System.out.println();
		System.out.println("-----------------");
		System.out.println("======排序后=====");
	
		// 降序比较器 
		Comparator<Map.Entry<Transition, Integer>> valueComparator = new Comparator<Map.Entry<Transition,Integer>>() { 
		public int compare(Entry<Transition, Integer> o1, Entry<Transition, Integer> o2) {
			return o2.getValue()-o1.getValue(); 
			} 
		}; 
			
		List<Map.Entry<Transition, Integer>> entryList = 
			new ArrayList<Map.Entry<Transition, Integer>>(map.entrySet());
		Collections.sort(entryList, valueComparator);
				
		for(Map.Entry<Transition, Integer> entry : entryList){
			System.out.println(entry.getKey().getName() + ":" + entry.getValue()); 
		}
		System.out.println("最大值====>  " + entryList.get(0).getKey().getName());

	}

}

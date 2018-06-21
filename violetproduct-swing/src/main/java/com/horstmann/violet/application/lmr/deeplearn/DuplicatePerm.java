package com.horstmann.violet.application.lmr.deeplearn;

import java.util.ArrayList;
import java.util.List;

public class DuplicatePerm {
	
	public static List<List<Integer>> list=new ArrayList<>();
	
	public static void main(String[] args) {
		int[] a = { 1, 2,3,1 };
		list=new ArrayList<>();
		System.out.println(perm(a, 0));
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).toString());
		}
	}
	
	public static List<List<Double>> DFSDouble(int[] a){
		
		list=new ArrayList<>();
		
		perm(a, 0);
		
		List<List<Double>> rl=new ArrayList<>();
		
		for(int i=0;i<list.size();i++){
			List<Double> l=new ArrayList<>();
			for(int j=0;j<list.get(i).size();j++){
				l.add(list.get(i).get(j).doubleValue());
			}
			rl.add(l);
		}
		
		return rl;
	}
	
	public static List<List<Integer>> DFSInteger(int[] a){
		
		list=new ArrayList<>();
		
		perm(a, 0);
		
		return list;
		
//		List<List<Double>> rl=new ArrayList<>();
//		
//		for(int i=0;i<list.size();i++){
//			List<Double> l=new ArrayList<>();
//			for(int j=0;j<list.get(i).size();j++){
//				l.add(list.get(i).get(j).doubleValue());
//			}
//			rl.add(l);
//		}
//		
//		return rl;
	}

	public static int perm(int[] a, int begin) {
		if (begin == a.length) {
			List<Integer> l=new ArrayList<>();
			for (int i = 0; i < a.length; i++) {
//				System.out.print(a[i] + " ");
				l.add(a[i]);
			}
			list.add(l);
//			System.out.println();
			return 1;
		}

		int count = 0;
		for (int i = begin; i < a.length; i++) {
			if (isSwap(a, begin, i)) {
				swap(a, begin, i);
				count += perm(a, begin + 1);
				swap(a, begin, i);
			}
		}
		return count;
	}

	public static void swap(int[] a, int begin, int end) {
		int temp = a[begin];
		a[begin] = a[end];
		a[end] = temp;
	}

	public static boolean isSwap(int[] a, int begin, int end) {
		for (int i = end; i > begin; i--) {
			if (a[end] == a[i - 1]) {
				return false;
			}
		}
		return true;
	}
}

package com.horstmann.violet.application.gui.util.ckt.test;


import java.util.ArrayList;
import java.util.List;

public class TestDfs {
	List<List<String>> l = new ArrayList<List<String>>();
	String s ="";
	public TestDfs() {
		// TODO Auto-generated constructor stub
		List<String> l1 = new ArrayList<String>();
		l1.add("A");
		l1.add("B");
		List<String> l2 = new ArrayList<String>();
		l2.add("C");
		l2.add("D");
		List<String> l3 = new ArrayList<String>();
		l3.add("E");
		l3.add("F");
		l3.add("G");
		l.add(l1);
		l.add(l2);
		l.add(l3);
	}
	public static void main(String[] args) {
		//System.out.println("Are you ok!");
		TestDfs t = new TestDfs();
		t.dis(0);
	}

	void dis(int n) {
		if (n == l.size()) {
			System.out.println(s);
			return;
		} else {
			List<String> sCollection = l.get(n);
			for (int i = 0; i < sCollection.size(); i++) {
				String s1 = s;
				s += sCollection.get(i);
				dis(n+1);
				s = s1;
			}
		}
	}
}

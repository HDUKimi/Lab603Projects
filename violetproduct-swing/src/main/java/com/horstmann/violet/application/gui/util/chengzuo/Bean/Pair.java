package com.horstmann.violet.application.gui.util.chengzuo.Bean;

import java.util.Comparator;

public class Pair implements Comparator<Pair>{

	private String first;
	private String second;
	
	public Pair() {
	}
	
	public Pair(String first, String second) {
		this.first = first;
		this.second = second;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getSecond() {
		return second;
	}
	public void setSecond(String second) {
		this.second = second;
	}

	@Override
	public int compare(Pair o1, Pair o2) {
		return Integer.parseInt(o1.first) - Integer.parseInt(o2.first);
	}

	@Override
	public String toString() {
		return "Date [first=" + first + ", second=" + second + "]";
	}
}

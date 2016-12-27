package com.horstmann.violet.application.gui.util.wujun.TDVerification;

import java.util.ArrayList;

public class UppaalLocation {
	String id;
	String name;
	double x = 0;
	ArrayList<UppaalTransition> transitions = new ArrayList<>();
	ArrayList<Integer> startTimeList = new ArrayList<>();
	ArrayList<Integer> endTimeList = new ArrayList<>();
	ArrayList<String> timeDurationList = new ArrayList<>();
	String finl = "false";
	String init = "false";
	
	
	
	public ArrayList<Integer> getStartTimeList() {
		return startTimeList;
	}
	public void setStartTimeList(ArrayList<Integer> startTimeList) {
		this.startTimeList = startTimeList;
	}
	public ArrayList<Integer> getEndTimeList() {
		return endTimeList;
	}
	public void setEndTimeList(ArrayList<Integer> endTimeList) {
		this.endTimeList = endTimeList;
	}
	
	public ArrayList<String> getTimeDurationList() {
		return timeDurationList;
	}
	public void setTimeDurationList(ArrayList<String> timeDurationList) {
		this.timeDurationList = timeDurationList;
	}
	public String getFinl() {
		return finl;
	}
	public void setFinl(String finl) {
		this.finl = finl;
	}
	public String getInit() {
		return init;
	}
	public void setInit(String init) {
		this.init = init;
	}
	public ArrayList<UppaalTransition> getTransitions() {
		return transitions;
	}
	public void setTransitions(ArrayList<UppaalTransition> transitions) {
		this.transitions = transitions;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	@Override
	public String toString() {
		return "UppaalLocation [id=" + id + ", name=" + name + ", x=" + x + ", transitions=" + transitions + ", finl="
				+ finl + ", init=" + init + "]";
	}
	
}

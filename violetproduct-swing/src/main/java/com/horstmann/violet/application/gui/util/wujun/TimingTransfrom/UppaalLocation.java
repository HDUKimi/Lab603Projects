package com.horstmann.violet.application.gui.util.wujun.TimingTransfrom;

import java.util.ArrayList;

public class UppaalLocation {

	ArrayList<Integer> startTimeList = new ArrayList<Integer>();
	ArrayList<Integer> endTimeList = new ArrayList<Integer>();

	String startTime;
	String endTime;
	String finl = "false";
	String init = "false";

	String Name;
	int id;
	int Type = 0; // 0为正常 1为紧迫
	
	boolean needForAutomata = true;

	String Invariant;// 时间约束
	ArrayList<String> timeDurationList = new ArrayList<String>();
	


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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	String lineEAID;

	public String getLineEAID() {
		return lineEAID;
	}

	public void setLineEAID(String lineEAID) {
		this.lineEAID = lineEAID;
	}

	public int getType() {
		return Type;
	}

	public void setType(int type) {
		Type = type;
	}

	public String getInvariant() {
		return Invariant;
	}

	public void setInvariant(String invariant) {
		Invariant = invariant;
	}

	public ArrayList<Integer> getEndTimeList() {
		return endTimeList;
	}

	public void setEndTimeList(ArrayList<Integer> endTimeList) {
		this.endTimeList = endTimeList;
	}

	public ArrayList<Integer> getStartTimeList() {
		return startTimeList;
	}

	public void setStartTimeList(ArrayList<Integer> startTimeList) {
		this.startTimeList = startTimeList;
	}
	
	public boolean isNeedForAutomata() {
		return needForAutomata;
	}

	public void setNeedForAutomata(boolean needForAutomata) {
		this.needForAutomata = needForAutomata;
	}
}

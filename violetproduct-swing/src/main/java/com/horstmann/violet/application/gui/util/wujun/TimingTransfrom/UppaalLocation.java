package com.horstmann.violet.application.gui.util.wujun.TimingTransfrom;

import java.util.ArrayList;

public class UppaalLocation {

	ArrayList<Integer> timeStarts = new ArrayList<Integer>();
	String startTime;
	String endTime;
	String finl = "false";
	String init = "false";

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

	public ArrayList<Integer> getTimeStarts() {
		return timeStarts;
	}

	public void setTimeStarts(ArrayList<Integer> timeStarts) {
		this.timeStarts = timeStarts;
	}

	boolean needForAutomata = true;

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

	String Name;
	int id;
	int Type = 0; // 0为正常 1为紧迫

	public int getType() {
		return Type;
	}

	public void setType(int type) {
		Type = type;
	}

	String Invariant;

	public String getInvariant() {
		return Invariant;
	}

	public void setInvariant(String invariant) {
		Invariant = invariant;
	}
}

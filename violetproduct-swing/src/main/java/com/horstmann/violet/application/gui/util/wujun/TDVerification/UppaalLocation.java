package com.horstmann.violet.application.gui.util.wujun.TDVerification;

import java.util.ArrayList;
import java.util.List;

import com.horstmann.violet.application.gui.util.wj.util.PathCoverUtil;

public class UppaalLocation {
	String id;
	String name;
	double x = 0;
	ArrayList<UppaalTransition> uppaalTransitions = new ArrayList<UppaalTransition>();
	ArrayList<Integer> startTimeList = new ArrayList<Integer>();
	ArrayList<Integer> endTimeList = new ArrayList<Integer>();
	ArrayList<String> timeDurationList = new ArrayList<String>();
	String timeDuration;
	boolean init = false;
	boolean finl = false;
	// boolean visit = false;
	public int visit = 0;
	
	List<List<PathTuple>> uppaalPathTuples = new ArrayList<List<PathTuple>>();

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

	public boolean isInit() {
		return init;
	}

	public void setInit(boolean init) {
		this.init = init;
	}

	public boolean isFinl() {
		return finl;
	}

	public void setFinl(boolean finl) {
		this.finl = finl;
	}

	public ArrayList<UppaalTransition> getUppaalTransitions() {
		return uppaalTransitions;
	}

	public void setUppaalTransitions(ArrayList<UppaalTransition> uppaalTransitions) {
		this.uppaalTransitions = uppaalTransitions;
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

	public int getVisit() {
		return visit;
	}

	public void setVisit(int visit) {
		this.visit = visit;
	}

	public String getTimeDuration() {
		return timeDuration;
	}

	public void setTimeDuration(String timeDuration) {
		this.timeDuration = timeDuration;
	}

	public List<List<PathTuple>> getUppaalPathTuples() {
		return uppaalPathTuples;
	}

	public void setUppaalPathTuples(List<List<PathTuple>> uppaalPathTuples) {
		this.uppaalPathTuples = uppaalPathTuples;
	}

	@Override
	public String toString() {
		return "UppaalLocation [id=" + id + ", name=" + name + ", x=" + x + ", transitions=" + uppaalTransitions + ", finl="
				+ finl + ", init=" + init + "]";
	}

}

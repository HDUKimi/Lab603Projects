package com.horstmann.violet.application.gui.util.wujun.TDVerification;

import java.util.HashMap;

public class UppaalTransition {
	
	String id;
	String name;
	String fromName;
	String toName;
	String target;
	String source;
	double x = 0;
	double time = 0;
	int startTime;
	int endTime;
	boolean out =false;
	String timeDuration;
	
	
	public String getTimeDuration() {
		return timeDuration;
	}
	public void setTimeDuration(String timeDuration) {
		this.timeDuration = timeDuration;
	}
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public String getName() {
		if (name == null) {
			return "null";
		}
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOut() {
		return out;
	}
	public void setOut(boolean out) {
		this.out = out;
	}
	HashMap<String, String> label;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public HashMap<String, String> getLabel() {
		return label;
	}
	public void setLabel(HashMap<String, String> label) {
		this.label = label;
	}
	@Override
	public String toString() {
		return "UppaalTransition [name=" + name + ", fromName=" + fromName + ", toName=" + toName + ", target=" + target
				+ ", source=" + source + ", x=" + x + ", time=" + time + ", startTime=" + startTime + ", endTime="
				+ endTime + ", out=" + out + ", timeDuration=" + timeDuration + ", label=" + label + "]";
	}
	
	
	
	 
}

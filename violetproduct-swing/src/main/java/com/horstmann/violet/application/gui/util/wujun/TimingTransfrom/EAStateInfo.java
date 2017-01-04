package com.horstmann.violet.application.gui.util.wujun.TimingTransfrom;

public class EAStateInfo {
	String startTime;//状态开始的时间
	String name;
	String DConst;//时间约束
	String Event;
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String value) {
		this.startTime = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDConst() {
		return DConst;
	}

	public void setDConst(String dConst) {
		DConst = dConst;
	}

	

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}

	@Override
	public String toString() {
		return "EAStateInfo [startTime=" + startTime + ", name=" + name + ", DConst=" + DConst + ", Event=" + Event
				+ "]";
	}

}

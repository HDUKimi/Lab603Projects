package com.horstmann.violet.application.gui.util.wujun.TimingTransfrom;

import com.thoughtworks.xstream.annotations.XStreamAlias;

//String startTime;//״̬��ʼ��ʱ��
//String name;
//String DConst;//ʱ��Լ��
//String Event;
public class LifelineState {

	@XStreamAlias("startPointTime")
	String  startPointTime;
	
	@XStreamAlias("name")
	String  name;
	
	@XStreamAlias("TimeConstraint")
	String  timeConstraint;

	public String getStartPointTime() {
		return startPointTime;
	}

	public void setStartPointTime(String startPointTime) {
		this.startPointTime = startPointTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTimeConstraint() {
		return timeConstraint;
	}

	public void setTimeConstraint(String timeConstraint) {
		this.timeConstraint = timeConstraint;
	}
	
	
	
}

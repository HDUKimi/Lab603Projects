package com.horstmann.violet.application.gui.util.wujun.TDVerification;

public class TransitionVerificationDisplay {
	UppaalTransition transition;
	int startTime;
	int endTime;
	String timeDuration;
	boolean result;
	public TransitionVerificationDisplay(UppaalTransition transition, int startTime, int endTime, String timeDuration,
			boolean resu) {
		super();
		this.transition = transition;
		this.startTime = startTime;
		this.endTime = endTime;
		this.timeDuration = timeDuration;
		this.result = resu;
	}
	public UppaalTransition getTransition() {
		return transition;
	}
	public void setTransition(UppaalTransition transition) {
		this.transition = transition;
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
	public String getTimeDuration() {
		return timeDuration;
	}
	public void setTimeDuration(String timeDuration) {
		this.timeDuration = timeDuration;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	
}

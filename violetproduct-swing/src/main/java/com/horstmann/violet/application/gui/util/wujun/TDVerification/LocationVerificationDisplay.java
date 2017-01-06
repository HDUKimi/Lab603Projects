package com.horstmann.violet.application.gui.util.wujun.TDVerification;

public class LocationVerificationDisplay {
	
	UppaalLocation location;
	int startTime;
	int endTime;
	String timeDuration;
	boolean result;
	
	
	

	public LocationVerificationDisplay(UppaalLocation location, int startTime, int endTime, String timeDuration,
			boolean result) {
		super();
		this.location = location;
		this.startTime = startTime;
		this.endTime = endTime;
		this.timeDuration = timeDuration;
		this.result = result;
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

	public UppaalLocation getLocation() {
		return location;
	}
	public void setLocation(UppaalLocation location) {
		this.location = location;
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
	
}

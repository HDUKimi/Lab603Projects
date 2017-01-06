package com.horstmann.violet.application.gui.util.wujun.TDVerification;

public class PathTuple {
	
	
	boolean finl;// 最后一组 transition为null
	UppaalLocation location;
	UppaalTransition transition;
	
	
	
	public PathTuple(UppaalLocation location, UppaalTransition transition) {
		this.location = location;
		if (transition == null) {
			this.finl = true;
		} else {
			this.transition = transition;
		}
	}
	
	public void setFinl(boolean finl) {
		this.finl = finl;
	}
	private boolean getfinl() {
		return finl;
	}
	public UppaalLocation getLocation() {
		return location;
	}
	public void setLocation(UppaalLocation location) {
		this.location = location;
	}
	public UppaalTransition getTransition() {
		return transition;
	}
	public void setTransition(UppaalTransition transition) {
		this.transition = transition;
	}
	
	
}

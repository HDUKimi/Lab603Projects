package com.horstmann.violet.application.gui.util.wujun.TDVerification;

class PathTuple {
	public PathTuple(UppaalLocation location, UppaalTransition transition) {
		this.location = location;
		if (transition == null) {
			this.finl = true;
		} else {
			this.transition = transition;
		}
	}
	boolean finl;// 最后一组 transition为null
	UppaalLocation location;
	UppaalTransition transition;
	public void setFinl(boolean finl) {
		this.finl = finl;
	}
	private boolean getfinl() {
		return finl;
	}
}

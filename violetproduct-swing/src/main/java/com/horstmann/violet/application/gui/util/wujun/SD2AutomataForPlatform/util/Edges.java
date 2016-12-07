package com.horstmann.violet.application.gui.util.wujun.SD2AutomataForPlatform.util;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("edges") 
public class Edges extends Entity{
	@XStreamImplicit(itemFieldName = "CallEdge")
	private List<CallEdge> CallEdges;
	@XStreamImplicit(itemFieldName = "TimeEdge")
	private List<TimeEdge> TimeEdges;
	public List<CallEdge> getCallEdges() {
		return CallEdges;
	}
	public void setCallEdges(List<CallEdge> callEdges) {
		CallEdges = callEdges;
	}
	public List<TimeEdge> getTimeEdges() {
		return TimeEdges;
	}
	public void setTimeEdges(List<TimeEdge> timeEdges) {
		TimeEdges = timeEdges;
	}
	
	
}

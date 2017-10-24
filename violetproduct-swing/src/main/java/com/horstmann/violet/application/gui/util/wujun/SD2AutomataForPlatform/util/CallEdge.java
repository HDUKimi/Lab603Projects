package com.horstmann.violet.application.gui.util.wujun.SD2AutomataForPlatform.util;

import java.awt.geom.Line2D.Double;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("CallEdge")
public class CallEdge extends Entity{
	//根节点 属性id
	@XStreamAsAttribute
	String id;
	
	@XStreamAlias("ID")
	String EAID;
	
	@XStreamAlias("message")
	String message;
	
	// 参数
	@XStreamAlias("parameter")
	String parameter;
	
	@XStreamAlias("input")
	String input;
	
	@XStreamAlias("output")
	String output;
	
	@XStreamAlias("timing")
	String timing;
	
	// 开始的激活点
	Start start;
	@XStreamAlias("start")
	public class Start {
		@XStreamAsAttribute
		@XStreamAlias("reference")
		public String fromNodeId;
	}
	
	// 结束的激活点
	End end;
	@XStreamAlias("end")
	public class End {
		@XStreamAsAttribute
		@XStreamAlias("reference")
		public String toNodeId;
	}
	@XStreamAlias("startLocation")
	PointStart startLocation;
	@XStreamAlias("startLocation")
	public class PointStart {
		@XStreamAsAttribute
		public double x;
		@XStreamAsAttribute
		public double y;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public String getTiming() {
		return timing;
	}
	public void setTiming(String timing) {
		this.timing = timing;
	}
	public PointStart getStartLocation() {
		return startLocation;
	}
	public void setStartLocation(PointStart startLocation) {
		this.startLocation = startLocation;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEAID() {
		return EAID;
	}
	public void setEAID(String eAID) {
		EAID = eAID;
	}
	public String getParameterStringValue() {
		return parameter;
	}
	public void setParameterStringValue(String parameterStringValue) {
		this.parameter = parameterStringValue;
	}
	public Start getStart() {
		return start;
	}
	public void setStart(Start start) {
		this.start = start;
	}
	public End getEnd() {
		return end;
	}
	public void setEnd(End end) {
		this.end = end;
	}
	
	
}

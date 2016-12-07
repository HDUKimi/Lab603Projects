package com.horstmann.violet.application.gui.util.wujun.TimingTransfrom;

import java.awt.geom.Line2D.Double;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("SendMessageEdge")
public class SendMessageEdge extends Entity{
	//根节点 属性id
	@XStreamAsAttribute
	String id;
	
	@XStreamAlias("name")
	String name;
	
	@XStreamAlias("TimeConstraint")
	String timeConstraint;
	
	// 开始的lifelineid
	Start start;
	@XStreamAlias("start")
	public class Start {
		@XStreamAsAttribute
		@XStreamAlias("reference")
		public String fromLifelineId;
	}
	
	// 结束的lifelineid
	End end;
	@XStreamAlias("end")
	public class End {
		@XStreamAsAttribute
		@XStreamAlias("reference")
		public String toLifelineId;
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
	
	@XStreamAlias("StarttimePoint")
	String starttimePoint;
	
	@XStreamAlias("EndtimePoint")
	String endtimePoint;

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

	public String getTimeConstraint() {
		return timeConstraint;
	}

	public void setTimeConstraint(String timeConstraint) {
		this.timeConstraint = timeConstraint;
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

	public PointStart getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(PointStart startLocation) {
		this.startLocation = startLocation;
	}

	public String getStarttimePoint() {
		return starttimePoint;
	}

	public void setStarttimePoint(String starttimePoint) {
		this.starttimePoint = starttimePoint;
	}

	public String getEndtimePoint() {
		return endtimePoint;
	}

	public void setEndtimePoint(String endtimePoint) {
		this.endtimePoint = endtimePoint;
	}
	
	
	
}

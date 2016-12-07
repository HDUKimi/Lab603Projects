package com.horstmann.violet.application.gui.util.wujun.TimingTransfrom;

public class EAMessage {

	String duration;// 時間約束
	String inner;
	String sourceId;//source生命线id
	String tragetId;//target生命线id
	String sourceName;//source生命线名
	String tragetName;//target生命线名
	String name;//消息名
	String sendTime;//发送时间
	String receiveTime;//接受时间
	String connectorId;//id
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getTragetId() {
		return tragetId;
	}

	public void setTragetId(String tragetId) {
		this.tragetId = tragetId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getTragetName() {
		return tragetName;
	}

	public void setTragetName(String tragetName) {
		this.tragetName = tragetName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getConnectorId() {
		return connectorId;
	}

	public void setConnectorId(String connectorId) {
		this.connectorId = connectorId;
	}

	public String getInner() {
		return inner;
	}

	public void setInner(String inner) {
		this.inner = inner;
	}

}

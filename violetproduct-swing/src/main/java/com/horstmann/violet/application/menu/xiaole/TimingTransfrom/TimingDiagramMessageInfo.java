package com.horstmann.violet.application.menu.xiaole.TimingTransfrom;

public class TimingDiagramMessageInfo {

	private String ID;//表示是哪条消息
	private String SEQTE;//表示消息接受的时间点
	private String SEQTX;//表示消息接受后改变的状态
	private String SEQTS;//表示消息开始的时间点 
	private String MessageStartID;//表示消息传递的timelifeline节点
	private String MessageEndID;//表示消息接受的timelifeline节点
	private String name;//消息的名称
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessageStartID() {
		return MessageStartID;
	}

	public void setMessageStartID(String messageStartID) {
		MessageStartID = messageStartID;
	}

	public String getMessageEndID() {
		return MessageEndID;
	}

	public void setMessageEndID(String messageEndID) {
		MessageEndID = messageEndID;
	}

	public String getSEQTE() {
		return SEQTE;
	}

	public void setSEQTE(String sEQTE) {
		SEQTE = sEQTE;
	}

	public String getSEQTX() {
		return SEQTX;
	}

	public void setSEQTX(String sEQTX) {
		SEQTX = sEQTX;
	}

	public String getSEQTS() {
		return SEQTS;
	}

	public void setSEQTS(String sEQTS) {
		SEQTS = sEQTS;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	
}

package com.horstmann.violet.application.menu.xiaole.TimingTransfrom;

public class TimingDiagramMessageInfo {

	private String ID;//��ʾ��������Ϣ
	private String SEQTE;//��ʾ��Ϣ���ܵ�ʱ���
	private String SEQTX;//��ʾ��Ϣ���ܺ�ı��״̬
	private String SEQTS;//��ʾ��Ϣ��ʼ��ʱ��� 
	private String MessageStartID;//��ʾ��Ϣ���ݵ�timelifeline�ڵ�
	private String MessageEndID;//��ʾ��Ϣ���ܵ�timelifeline�ڵ�
	private String name;//��Ϣ������
	

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

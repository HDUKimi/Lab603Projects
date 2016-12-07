package com.horstmann.violet.application.menu.xiaole.TimingTransfrom;

import java.util.ArrayList;

public class StateLifelineInfo {
 
	private String name;
	private String id;
	private String geometry;//λ��������Ϣ
	private ArrayList<String> states=new ArrayList<String>();//״̬
	private String runstate;
	private ArrayList<String> MessageIds=new ArrayList<String>();//��ʾ��һ��StateLifeline�ڵ��ɼ�����Ϣ����
	
	
	public ArrayList<String> getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states.add(states);
	}
	public String getGeometry() {
		return geometry;
	}
	public void setGeometry(String geometry) {
		this.geometry = geometry;
	}
	public String getRunstate() {
		return runstate;
	}
	public void setRunstate(String runstate) {
		this.runstate = runstate;
	}
	public ArrayList<String> getMessageIds() {
		return MessageIds;
	}
	public void setMessageIds(String messageId) {
		MessageIds.add(messageId);
	}
	public String getName() {
		return name;                                                                                                                    
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}

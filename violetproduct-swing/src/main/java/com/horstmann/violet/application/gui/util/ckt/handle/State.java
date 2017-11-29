package com.horstmann.violet.application.gui.util.ckt.handle;


import java.io.Serializable;
import java.util.ArrayList;


public class State implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5461019574228556861L;
	
	private int id;//�������
	private String name;//����
	private String showName;
	private DBM_element[][] invariantDBM ;//״̬�е�ʱ�Ӳ���ʽ����
	private DBM_element[][] addClockRelationDBM ;//������ʱ�ӹ�ϵ�Ĳ���ʽ����
	private String position;//�ж�����״̬λ���Ƿ���ͬ�ı�־λ
	private String type;//���ڿ�����ӵ�״̬�������ԣ��ǲ��ǳ�ʼ״̬��
	boolean finalState;//��־״̬�Ƿ�Ϊ��ֹ״̬
	boolean starState;
	private int stateAccessTimes = 0;//״̬�ڵ�ķ��ʴ���
	
	public int getStateAccessTimes() {
		return stateAccessTimes;
	}
	public void setStateAccessTimes(int stateAccessTimes) {
		this.stateAccessTimes = stateAccessTimes;
	}
	public boolean isStarState() {
		return starState;
	}
	public void setStarState(boolean starState) {
		this.starState = starState;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public DBM_element[][] getAddClockRelationDBM() {
		return addClockRelationDBM;
	}
	public void setAddClockRelationDBM(DBM_element[][] addClockRelationDBM) {
		this.addClockRelationDBM = addClockRelationDBM;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DBM_element[][] getInvariantDBM() {
		return invariantDBM;
	}
	public void setInvariantDBM(DBM_element[][] invariantDBM) {
		this.invariantDBM = invariantDBM;
	}
	public boolean isFinalState() {
		return finalState;
	}
	public void setFinalState(boolean finalState) {
		this.finalState = finalState;
	}
	
	
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	@Override
	public String toString() {
		return "״̬ " + id + " [id=" + id + ", name=" + name + ", showName=" + showName + ", finalState="
				+ finalState + ", type=" + type + "]";
	}
	
	
	
}

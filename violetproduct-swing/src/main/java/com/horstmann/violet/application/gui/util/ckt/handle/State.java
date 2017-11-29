package com.horstmann.violet.application.gui.util.ckt.handle;


import java.io.Serializable;
import java.util.ArrayList;


public class State implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5461019574228556861L;
	
	private int id;//后来添加
	private String name;//名称
	private String showName;
	private DBM_element[][] invariantDBM ;//状态中的时钟不变式集合
	private DBM_element[][] addClockRelationDBM ;//加入了时钟关系的不变式矩阵
	private String position;//判断两个状态位置是否相同的标志位
	private String type;//后期可能添加的状态类型属性（是不是初始状态）
	boolean finalState;//标志状态是否为终止状态
	boolean starState;
	private int stateAccessTimes = 0;//状态节点的访问次数
	
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
		return "状态 " + id + " [id=" + id + ", name=" + name + ", showName=" + showName + ", finalState="
				+ finalState + ", type=" + type + "]";
	}
	
	
	
}

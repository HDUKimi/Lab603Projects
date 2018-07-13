package com.horstmann.violet.application.ckt.entity;

import java.util.ArrayList;

public class State {
	String id;
	private String stateName; //状态节点名称
	boolean finalState; //标志状态是否为终止状态
	private ArrayList<Transition> nextTranSet = new ArrayList<Transition>(); //状态节点的下条迁移集合
	private ArrayList<Transition> proTranSet = new ArrayList<Transition>(); //到达状态节点的迁移集合
	private int stateAccessTimes = 0;				//状态节点的访问次数
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getStateAccessTimes() {
		return stateAccessTimes;
	}
	public void setStateAccessTimes(int stateAccessTimes) {
		this.stateAccessTimes = stateAccessTimes;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public boolean isFinalState() {
		return finalState;
	}
	public void setFinalState(boolean finalState) {
		this.finalState = finalState;
	}
	public ArrayList<Transition> getNextTranSet() {
		return nextTranSet;
	}
	public void setNextTranSet(ArrayList<Transition> nextTranSet) {
		this.nextTranSet = nextTranSet;
	}
	public ArrayList<Transition> getProTranSet() {
		return proTranSet;
	}
	public void setProTranSet(ArrayList<Transition> proTranSet) {
		this.proTranSet = proTranSet;
	}
	@Override
	public String toString() {
		return "State [id=" + id + ", stateName=" + stateName + ", finalState=" + finalState + 
				 ", stateAccessTimes=" + stateAccessTimes + "]";
	}
	
}

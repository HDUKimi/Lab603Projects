package com.horstmann.violet.application.ckt.entity;

import java.util.ArrayList;

public class Transition {
	String id;
	private String name; // 迁移名称（激励）
	private double probability; // 迁移概率
	private String condition;//迁移上条件
	private ArrayList<Parameter> parameters;
	private State startState; // 迁移的源状态
	private State endState; // 迁移的目标状态
	private String transitionType; // 迁移类型
	private String resultOfCondition;//迁移上条件
	String expectResult;//标识路径，用于得到最终路径上的预期结果--true||false
	
	
	public String getResultOfCondition() {
		return resultOfCondition;
	}
	public void setResultOfCondition(String resultOfCondition) {
		this.resultOfCondition = resultOfCondition;
	}
	public ArrayList<Parameter> getParameters() {
		return parameters;
	}
	public void setParameters(ArrayList<Parameter> parameters) {
		this.parameters = parameters;
	}
	public State getStartState() {
		return startState;
	}
	public void setStartState(State startState) {
		this.startState = startState;
	}
	public State getEndState() {
		return endState;
	}
	public void setEndState(State endState) {
		this.endState = endState;
	}
	public String getExpectResult() {
		return expectResult;
	}
	public void setExpectResult(String expectResult) {
		this.expectResult = expectResult;
	}
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
	public double getProbability() {
		return probability;
	}
	public void setProbability(double probability) {
		this.probability = probability;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getTransitionType() {
		return transitionType;
	}
	public void setTransitionType(String transitionType) {
		this.transitionType = transitionType;
	}
	@Override
	public String toString() {
		return "Transition [id=" + id + ", name=" + name + ", probability=" + probability + ", condition=" + condition
				+ ", parameters=" + parameters + ", startState=" + startState.getStateName() + ", endState=" + endState.getStateName()
				+ ", transitionType=" + transitionType + ", resultOfCondition=" + resultOfCondition + ", expectResult="
				+ expectResult + "]";
	}
	
}

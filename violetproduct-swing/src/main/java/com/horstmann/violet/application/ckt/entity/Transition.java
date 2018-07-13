package com.horstmann.violet.application.ckt.entity;

import java.util.ArrayList;

public class Transition {
	String id;
	private String name; // Ǩ�����ƣ�������
	private double probability; // Ǩ�Ƹ���
	private String condition;//Ǩ��������
	private ArrayList<Parameter> parameters;
	private State startState; // Ǩ�Ƶ�Դ״̬
	private State endState; // Ǩ�Ƶ�Ŀ��״̬
	private String transitionType; // Ǩ������
	private String resultOfCondition;//Ǩ��������
	String expectResult;//��ʶ·�������ڵõ�����·���ϵ�Ԥ�ڽ��--true||false
	
	
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

package com.horstmann.violet.application.ckt.entity;

import java.util.ArrayList;
import java.util.List;

public class Markov {
	private List<State> states = new ArrayList<State>(); // 表头结点集合
	private List<Transition> transitions = new ArrayList<Transition>(); // 整个markov链的迁移集合
	private State initialState; // 初始状态节点
	private State finalState; // 终止状态节点
	public List<State> getStates() {
		return states;
	}
	public void setStates(List<State> states) {
		this.states = states;
	}
	public List<Transition> getTransitions() {
		return transitions;
	}
	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
	}
	public State getInitialState() {
		return initialState;
	}
	public void setInitialState(State initialState) {
		this.initialState = initialState;
	}
	public State getFinalState() {
		return finalState;
	}
	public void setFinalState(State finalState) {
		this.finalState = finalState;
	}
	
	
}

package com.horstmann.violet.application.ckt.entity;

import java.util.ArrayList;
import java.util.List;

public class Markov {
	private List<State> states = new ArrayList<State>(); // ��ͷ��㼯��
	private List<Transition> transitions = new ArrayList<Transition>(); // ����markov����Ǩ�Ƽ���
	private State initialState; // ��ʼ״̬�ڵ�
	private State finalState; // ��ֹ״̬�ڵ�
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

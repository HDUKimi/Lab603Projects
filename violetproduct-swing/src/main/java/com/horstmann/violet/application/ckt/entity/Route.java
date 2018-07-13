package com.horstmann.violet.application.ckt.entity;

import java.util.ArrayList;
import java.util.List;

public class Route {
	private int id; //��ʾ�ڼ������ɵ�·��
	private List<Transition> transitionList = new ArrayList<Transition>();// ·����Ǩ�����м���
	//private String tcSequence;// ·����������
	private double routeProbability; // ·������
	String RouteResult;//��ʶ·�������ڵõ�����·���ϵ�Ԥ�ڽ��--true||false
	
	public String getRouteResult() {
		return RouteResult;
	}
	public void setRouteResult(String routeResult) {
		RouteResult = routeResult;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Transition> getTransitionList() {
		return transitionList;
	}
	public void setTransitionList(List<Transition> transitionList) {
		this.transitionList = transitionList;
	}
	/*public String getTcSequence() {
		return tcSequence;
	}
	public void setTcSequence(String tcSequence) {
		this.tcSequence = tcSequence;
	}*/
	public double getRouteProbability() {
		return routeProbability;
	}
	public void setRouteProbability(double routeProbability) {
		this.routeProbability = routeProbability;
	}
	@Override
	public String toString() {
		return "Route [id=" + id + ", transitionList=" + transitionList.size() + ", routeProbability=" + routeProbability
				+ ", RouteResult=" + RouteResult + "]";
	}
	
	
}

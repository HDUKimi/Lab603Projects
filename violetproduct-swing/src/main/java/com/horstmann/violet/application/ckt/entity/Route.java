package com.horstmann.violet.application.ckt.entity;

import java.util.ArrayList;
import java.util.List;

public class Route {
	private int id; //表示第几次生成的路径
	private List<Transition> transitionList = new ArrayList<Transition>();// 路径中迁移序列集合
	//private String tcSequence;// 路径测试序列
	private double routeProbability; // 路径概率
	String RouteResult;//标识路径，用于得到最终路径上的预期结果--true||false
	
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

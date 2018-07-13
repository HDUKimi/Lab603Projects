package com.horstmann.violet.application.ckt.entity;

import java.util.ArrayList;
import java.util.Map;

public class Parameter {
	
	private String domain;
	private Map parameterMap;
	private String result;
	private ArrayList<String> results;// 数可取的有效值集合
	
	
	public Map getParameterMap() {
		return parameterMap;
	}
	public void setParameterMap(Map parameterMap) {
		this.parameterMap = parameterMap;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public ArrayList<String> getResults() {
		return results;
	}
	public void setResults(ArrayList<String> results) {
		this.results = results;
	}
	
	
	
}

package com.horstmann.violet.application.gui.util.chengzuo.Bean;

import java.io.Serializable;
import java.text.DecimalFormat;

public class TestCaseResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8755921071995628981L;

	//ִ��ʱ��
	private double exeTime;
	
	//�߶�
	private double takeoff_alt;
	
	//����
	private double battery_remaining;
	
	//����
	private double wind_speed;

	//ʱ��
	private double time;

	private String resultDetail;
	
	DecimalFormat decimalFormat = new DecimalFormat("###");
	
	public TestCaseResult() {
	}

	public TestCaseResult(double exeTime,double wind_speed, double takeoff_alt, double battery_remaining, double time) {
		this.exeTime = exeTime;
		this.takeoff_alt = takeoff_alt;
		this.battery_remaining = battery_remaining;
		this.time = time;
		this.wind_speed = wind_speed;
	}

	
	public String getWind_speed() {
		return decimalFormat.format(wind_speed);
	}

	public void setWind_speed(double wind_speed) {
		this.wind_speed = wind_speed;
	}

	public String getExeTime() {
		return decimalFormat.format(exeTime);
	}

	public void setExeTime(double exeTime) {
		this.exeTime = exeTime;
	}

	public String getTakeoff_alt() {
		return decimalFormat.format(takeoff_alt);
	}

	public void setTakeoff_alt(double akeoff_alt) {
		this.takeoff_alt = akeoff_alt;
	}

	public String getBattery_remaining() {
		String tmp = decimalFormat.format(battery_remaining);
		if(tmp.equals("-0"))
			tmp = "0";
		return tmp+"%";
//		return tmp;
	}

	public void setBattery_remaining(double battery_remaining) {
		this.battery_remaining = battery_remaining;
	}

	public String getTime() {
		return decimalFormat.format(time);
	}

	public void setTime(double time) {
		this.time = time;
	}

	public String getResultDetail() {
		return resultDetail;
	}

	public void setResultDetail(String resultDetail) {
		this.resultDetail = resultDetail;
	}

	@Override
	public String toString() {
		
		return "���Խ��: [ִ��ʱ��=" + decimalFormat.format(exeTime) 
				+ ", ����=" + decimalFormat.format(wind_speed) 
				+ ", ��ɸ߶�=" + decimalFormat.format(takeoff_alt) 
				+ ", ʣ�����="+ decimalFormat.format(battery_remaining) 
				+ "%, ����ʱ�� =" + decimalFormat.format(time) + "]";
	}
	
}

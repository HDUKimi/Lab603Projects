package com.horstmann.violet.application.gui.stepCenterTabbedPane;

public class UppaalProcessModel {

	private int id;
	private int state;
	private String name;
	private String operation;
	private int progress;
	private String time;

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getState() {
		return state;
	}



	public void setState(int state) {
		this.state = state;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getOperation() {
		return operation;
	}



	public void setOperation(String operation) {
		this.operation = operation;
	}



	public int getProgress() {
		return progress;
	}



	public void setProgress(int progress) {
		this.progress = progress;
	}



	public String getTime() {
		return time;
	}



	public void setTime(String time) {
		this.time = time;
	}
	
	@Override
	public String toString() {
		return "UppaalProcessModel [id=" + id + ", state=" + state + ", name=" + name + ", operation=" + operation
				+ ", progress=" + progress + ", time=" + time + "]";
	}
	
}

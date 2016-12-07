package com.horstmann.violet.application.menu.xiaole.TimingTransfrom;

import java.util.ArrayList;
import java.util.List;

public class VioletStateLifelineInfo {

	private String ID;
	private String state0;
	private String states;
	private String name;
	private String LocationX;
	private String LocationY;
	private String width;
	private String height;
	private List<VioletHorizontalChildInfo> horizonchilds=new ArrayList<VioletHorizontalChildInfo>();
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	
	public List<VioletHorizontalChildInfo> getHorizonchilds() {
		return horizonchilds;
	}
	public void setHorizonchilds(List<VioletHorizontalChildInfo> horizonchilds) {
		this.horizonchilds = horizonchilds;
	}
	public String getState0() {
		return state0;
	}
	public void setState0(String state0) {
		this.state0 = state0;
	}
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocationX() {
		return LocationX;
	}
	public void setLocationX(String locationX) {
		LocationX = locationX;
	}
	public String getLocationY() {
		return LocationY;
	}
	public void setLocationY(String locationY) {
		LocationY = locationY;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	
	
	
}

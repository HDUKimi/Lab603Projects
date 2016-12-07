package com.horstmann.violet.application.gui.util.wujun.SequenceTransfrom;

import java.util.ArrayList;

public class WJLifeline 
{
	
	String lifeLineId="null";
	String lifeLineName="null";
	
	ArrayList<String> activationIDs = new ArrayList<>();
	
	
	public String getLifeLineId() {
		return lifeLineId;
	}
	public void setLifeLineId(String lifeLineId) {
		this.lifeLineId = lifeLineId;
	}
	public String getLifeLineName() {
		return lifeLineName;
	}
	public void setLifeLineName(String lifeLineName) {
		this.lifeLineName = lifeLineName;
	}
	public ArrayList<String> getActivationIDs() {
		return activationIDs;
	}
	public void setActivationIDs(ArrayList<String> activationIDs) {
		this.activationIDs = activationIDs;
	}
	public void setlifeLineName(String lifeLineName)
	{
		this.lifeLineName=lifeLineName;
	}
	public String getlifeLineName()
	{
		return lifeLineName;
	}
}

/**
 * 
 */
package com.horstmann.violet.application.gui.util.wujun.TimingTransfrom;

import java.util.ArrayList;

/**
 * @author bojan
 *
 */
public class EALifeline {
	
	String name;
	String LifelineID;
	ArrayList<EAStateInfo> stateInfos;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLifelineID() {
		return LifelineID;
	}

	public void setLifelineID(String lifelineID) {
		LifelineID = lifelineID;
	}
	public ArrayList<EAStateInfo> getStateInfos() {
		return stateInfos;
	}

	public void setStateInfos(ArrayList<EAStateInfo> stateInfos) {
		this.stateInfos = stateInfos;
	}

}

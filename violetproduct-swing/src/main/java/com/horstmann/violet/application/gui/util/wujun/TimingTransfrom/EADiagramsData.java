package com.horstmann.violet.application.gui.util.wujun.TimingTransfrom;

import java.util.ArrayList;

public class EADiagramsData {
	ArrayList<String> ids = new ArrayList<String>();

	ArrayList<EALifeline> lifelines = new ArrayList<EALifeline>();
	ArrayList<EAMessage> connectors = new ArrayList<EAMessage>();

	public String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDiagramID() {
		return diagramID;
	}

	public void setDiagramID(String diagramID) {
		this.diagramID = diagramID;
	}

	public String diagramID;

	public ArrayList<String> getIds() {
		return ids;
	}

	public void setIds(ArrayList<String> ids) {
		this.ids = ids;
	}

	public ArrayList<EALifeline> getLifelines() {
		return lifelines;
	}

	public void setLifelines(ArrayList<EALifeline> lifelines) {
		this.lifelines = lifelines;
	}

	public ArrayList<EAMessage> getConnectors() {
		return connectors;
	}

	public void setConnectors(ArrayList<EAMessage> connectors) {
		this.connectors = connectors;
	}

}
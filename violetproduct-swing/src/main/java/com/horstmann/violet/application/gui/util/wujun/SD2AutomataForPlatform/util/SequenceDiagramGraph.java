package com.horstmann.violet.application.gui.util.wujun.SD2AutomataForPlatform.util;

// ¸ù½Úµã
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("SequenceDiagramGraph")
public class SequenceDiagramGraph extends Entity{
	@XStreamAsAttribute
	String id;
	
	@XStreamAlias("nodes")
	Nodes nodes;
	
	@XStreamAlias("edges")
	Edges edges;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Nodes getNodes() {
		return nodes;
	}

	public void setNodes(Nodes nodes) {
		this.nodes = nodes;
	}

	public Edges getEdges() {
		return edges;
	}

	public void setEdges(Edges edges) {
		this.edges = edges;
	}
	
	
}

package com.horstmann.violet.application.gui.util.wujun.SD2AutomataForPlatform.util;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("children")
public class Children extends Entity{
	@XStreamImplicit(itemFieldName = "ActivationBarNode")
	List<ActivationBarNode> nodes;
	
	public void setNodes(List<ActivationBarNode> nodes) {
		this.nodes = nodes;
	}

	public List<ActivationBarNode> getNodes() {
		return nodes;
	}
	
}

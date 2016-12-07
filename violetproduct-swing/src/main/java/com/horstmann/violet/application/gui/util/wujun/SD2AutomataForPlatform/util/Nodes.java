package com.horstmann.violet.application.gui.util.wujun.SD2AutomataForPlatform.util;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("nodes")
public class Nodes extends Entity{
	
	@XStreamImplicit(itemFieldName = "LifelineNode")
	private List<LifelineNode> liflines;
	@XStreamImplicit(itemFieldName = "CombinedFragment")
	private List<CombinedFragment> fragments;
	
	
	public List<LifelineNode> getLiflines() {
		return liflines;
	}
	public void setLiflines(List<LifelineNode> liflines) {
		this.liflines = liflines;
	}
	public List<CombinedFragment> getFragments() {
		return fragments;
	}
	public void setFragments(List<CombinedFragment> fragments) {
		this.fragments = fragments;
	}
	
	
}
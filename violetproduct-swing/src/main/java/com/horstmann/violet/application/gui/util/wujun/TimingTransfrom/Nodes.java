package com.horstmann.violet.application.gui.util.wujun.TimingTransfrom;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("nodes")
public class Nodes extends Entity{
	
	@XStreamImplicit(itemFieldName = "StateLifeline")
	private List<Lifeline> liflines;

	
	public List<Lifeline> getLiflines() {
		return liflines;
	}
	public void setLiflines(List<Lifeline> liflines) {
		this.liflines = liflines;
	}
	
}
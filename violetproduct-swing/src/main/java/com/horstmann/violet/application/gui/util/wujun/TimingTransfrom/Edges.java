package com.horstmann.violet.application.gui.util.wujun.TimingTransfrom;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("edges") 
public class Edges extends Entity{
	@XStreamImplicit(itemFieldName = "SendMessageEdge")
	private List<SendMessageEdge> sendMessageEdge;

	public List<SendMessageEdge> getSendMessageEdge() {
		return sendMessageEdge;
	}

	public void setSendMessageEdge(List<SendMessageEdge> sendMessageEdge) {
		this.sendMessageEdge = sendMessageEdge;
	}
	
	
}

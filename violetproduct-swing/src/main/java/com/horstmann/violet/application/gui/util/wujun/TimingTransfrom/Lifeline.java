package com.horstmann.violet.application.gui.util.wujun.TimingTransfrom;

import java.util.List;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;


@XStreamAlias("StateLifeline")
public class Lifeline extends Entity{
	
	@XStreamAsAttribute
	String id;
	
	@XStreamAlias("ID")
	String EAID;
	
	
	
	Name name;
	@XStreamAlias("name")
	public class Name {
		@XStreamAsAttribute
		String id;
		@XStreamAlias("text")
		public String text;
		
	}
	Schildren schildren;
	@XStreamAlias("schildren")
	public class Schildren {
		
		@XStreamAlias("horizontalchild")
		public Horizontalchild horizontalchild;
		@XStreamAlias("horizontalchild")
		public class Horizontalchild {
			@XStreamImplicit(itemFieldName = "com.horstmann.violet.product.diagram.abstracts.edge.HorizontalChild")
			public List<LifelineState> states;
		}
		
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEAID() {
		return EAID;
	}
	public void setEAID(String eAID) {
		EAID = eAID;
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}
	public Schildren getSchildren() {
		return schildren;
	}
	public void setSchildren(Schildren schildren) {
		this.schildren = schildren;
	}
	
	
	
}

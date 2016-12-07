package com.horstmann.violet.application.gui.util.wujun.SD2AutomataForPlatform.util;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.awt.geom.Line2D.Double;
import java.util.List;
// 操作域
public class FragmentPart extends Entity{
	
	// 操作域的条件
	@XStreamAlias("conditionText")
	String conditionText;
	
	// 包含的消息的EAID
	@XStreamAlias("coveredMessagesID")
	coveredMessagesID MessagesID;
	@XStreamAlias("coveredMessagesID")
	public class coveredMessagesID {
		@XStreamImplicit(itemFieldName = "string")
		public List<String> messageIDs;
	}
	// 操作域的高度
	@XStreamAlias("size")
	String height;
	
	// lin2DLine.x1,y1 左上角的点 x2,y2 右上角的点  ！！注意是line2D.Double
	@XStreamAlias("borderline")
	Double line2DLine;

	public String getConditionText() {
		return conditionText;
	}

	public void setConditionText(String conditionText) {
		this.conditionText = conditionText;
	}

	public coveredMessagesID getMessagesID() {
		return MessagesID;
	}

	public void setMessagesID(coveredMessagesID messagesID) {
		MessagesID = messagesID;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public Double getLine2DLine() {
		return line2DLine;
	}

	public void setLine2DLine(Double line2dLine) {
		line2DLine = line2dLine;
	}
	
	
}

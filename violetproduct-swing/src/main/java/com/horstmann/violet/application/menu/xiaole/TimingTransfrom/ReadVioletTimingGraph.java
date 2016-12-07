package com.horstmann.violet.application.menu.xiaole.TimingTransfrom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;






import java.util.Map;
import java.util.UUID;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReadVioletTimingGraph {
      SAXReader reader =new SAXReader();
      List<StateLifelineInfo> statelifelines=new ArrayList<StateLifelineInfo>();
      List<TimingDiagramMessageInfo> TimingDiagramMessages=new ArrayList<TimingDiagramMessageInfo>();
      Map<String,List<String>> StatelifelineContainMessages=new HashMap<String, List<String>>();
      Map<String,String> MessageStartIdOnStatelifeline=new HashMap<String, String>();
      Map<String,String> MessageEndIdOnStatelifeline=new HashMap<String, String>();
      int boundx=0;//把坐标转化成时间
      public void ReadVioletXml() throws DocumentException
      {
    	  Document dom=reader.read("TimingDiagram.timing.violet.xml");
    	  Element root=dom.getRootElement();
    	  Element nodes=root.element("nodes");
    	  Element edges=root.element("edges");
    	  List<Element> Statelifelines=nodes.elements("State_Lifeline");
    	  List<Element> Messages=edges.elements("SendMessageEdge");
    	  for(Element temp:Statelifelines)
    	  {
    		  statelifelines.add(new StateLifelineInfo());
    	  }
    	  for(Element temp:Messages)//遍历XML获取消息信息，创建消息对象
    	  {
    		  TimingDiagramMessages.add(new TimingDiagramMessageInfo());
    	  }
    	  for(Element temp:Messages)
    	  {
    		  int index=Messages.indexOf(temp);
    		  TimingDiagramMessages.get(index).setID(temp.attribute("id").getValue());//设置ID
    		  TimingDiagramMessages.get(index).setSEQTX(temp.element("EndState").getText());//设置SEQTX属性
    		  TimingDiagramMessages.get(index).setSEQTS(temp.elementText("StarttimePoint"));//设置SEQTS
    		  TimingDiagramMessages.get(index).setSEQTE(temp.elementText("EndTimePoint"));//设置SEQTE
    		  List<String> startandendId=new ArrayList<String>();
    		  startandendId.add(temp.element("start").attribute("reference").getValue());
    		  startandendId.add(temp.element("end").attribute("reference").getValue());
    		  StatelifelineContainMessages.put(temp.attribute("id").getValue(), startandendId);
    		  MessageStartIdOnStatelifeline.put(temp.attribute("id").getValue(), 
    				  temp.element("start").attribute("reference").getValue());
    		  MessageEndIdOnStatelifeline.put(temp.attribute("id").getValue(),
    				  temp.element("end").attribute("reference").getValue());
    		  //将消息的ID与起始终止的StateLifeline节点对应起来
    		  
    		 
    	  } 
    	  
    	  for(Element temp:Statelifelines)
    	  {
    		  int index=statelifelines.indexOf(temp);
    		  String lifelineID=temp.attribute("id").getValue();
    		  boundx=Integer.parseInt(temp.element("location").attributeValue("x"));
    		  //通过lifelineId获取每个lifeline上的MessageId,以便构造EA格式中的links标签下属性信息
    		  for(Map.Entry<String, String> entry :MessageStartIdOnStatelifeline.entrySet())
    		  {
    			  if(lifelineID.equals(entry.getValue()))
    			  {
    				  statelifelines.get(index).setMessageIds(entry.getKey());
    			  }
    		  }
    		  for(Map.Entry<String, String> entry :MessageEndIdOnStatelifeline.entrySet())
    		  {
    			  if(lifelineID.equals(entry.getValue()))
    			  {
    				  statelifelines.get(index).setMessageIds(entry.getKey());
    			  }
    		  }   		
    		  statelifelines.get(index).setId(temp.attribute("id").getValue());//设置ID
    		  statelifelines.get(index).setName(temp.element("name").element("text").getText());//设置name
    		  statelifelines.get(index).setStates(temp.element("state0").elementText("text"));
    		  String[] OtherStates=temp.element("states").elementText("text").split("\\|");
    		  for(String otherstate :OtherStates)
    		  statelifelines.get(index).setStates(temp.element("states").elementText(otherstate));//设置状态信息
    		 //解决xref state状态标签
    		  List<Element> horizontalchilds=temp.elements("com.horstmann.violet.product.diagram.abstracts.edge.HorizontalChild");
    		  String runstate=null;
    		  int width=Integer.parseInt(temp.elementText("width"));
    		  int height=Integer.parseInt(temp.elementText("height"));
    		  for(Element horizontalchild: horizontalchilds)
    		  {
    		  String startpoint=horizontalchild.element("startPoint").attributeValue("x");
    		  String startpointAstime=String.valueOf(((Integer.parseInt(startpoint)-boundx)*(width-150)/100));
    		  String state =horizontalchild.elementText("state");
    		  String condition=horizontalchild.elementText("condition");
    		  String continuetime=horizontalchild.elementText("continuetime");
    		  String var="@VAR;Variable="+state+";"+"Value="+startpointAstime+";"+"Op=="+
    		  "DConst="+continuetime+"Event="+condition+";"+"@ENDVAR;";
    		  runstate+=var;
    		  }
    		  statelifelines.get(index).setRunstate(runstate); 
    		  //解决runstate标签
    		  String top=temp.element("location").attributeValue("y");
    		  String bottom=String.valueOf((Integer.parseInt(top)+width));
    		  String left=temp.element("location").attributeValue("x");
    		  String right=String.valueOf((Integer.parseInt(top)+height));
    		  String geometry="Left="+left+";"
    				  +"Top="+top+";"
    				  +"Right="+right+";"
    				  +"Bottom="+bottom+";";
    		  statelifelines.get(index).setGeometry(geometry); 
    		  //解决geometry标签
    	  }
    	
      }
      
	public Map<String, String> getMessageStartIdOnStatelifeline() {
		return MessageStartIdOnStatelifeline;
	}

	public void setMessageStartIdOnStatelifeline(
			Map<String, String> messageStartIdOnStatelifeline) {
		MessageStartIdOnStatelifeline = messageStartIdOnStatelifeline;
	}

	public Map<String, String> getMessageEndIdOnStatelifeline() {
		return MessageEndIdOnStatelifeline;
	}

	public void setMessageEndIdOnStatelifeline(
			Map<String, String> messageEndIdOnStatelifeline) {
		MessageEndIdOnStatelifeline = messageEndIdOnStatelifeline;
	}

	public Map<String, List<String>> getStatelifelineContainMessages() {
		return StatelifelineContainMessages;
	}
	public void setStatelifelineContainMessages(
			Map<String, List<String>> statelifelineContainMessages) {
		StatelifelineContainMessages = statelifelineContainMessages;
	}		
}

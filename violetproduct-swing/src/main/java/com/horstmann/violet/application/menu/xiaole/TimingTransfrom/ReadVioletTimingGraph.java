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
      int boundx=0;//������ת����ʱ��
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
    	  for(Element temp:Messages)//����XML��ȡ��Ϣ��Ϣ��������Ϣ����
    	  {
    		  TimingDiagramMessages.add(new TimingDiagramMessageInfo());
    	  }
    	  for(Element temp:Messages)
    	  {
    		  int index=Messages.indexOf(temp);
    		  TimingDiagramMessages.get(index).setID(temp.attribute("id").getValue());//����ID
    		  TimingDiagramMessages.get(index).setSEQTX(temp.element("EndState").getText());//����SEQTX����
    		  TimingDiagramMessages.get(index).setSEQTS(temp.elementText("StarttimePoint"));//����SEQTS
    		  TimingDiagramMessages.get(index).setSEQTE(temp.elementText("EndTimePoint"));//����SEQTE
    		  List<String> startandendId=new ArrayList<String>();
    		  startandendId.add(temp.element("start").attribute("reference").getValue());
    		  startandendId.add(temp.element("end").attribute("reference").getValue());
    		  StatelifelineContainMessages.put(temp.attribute("id").getValue(), startandendId);
    		  MessageStartIdOnStatelifeline.put(temp.attribute("id").getValue(), 
    				  temp.element("start").attribute("reference").getValue());
    		  MessageEndIdOnStatelifeline.put(temp.attribute("id").getValue(),
    				  temp.element("end").attribute("reference").getValue());
    		  //����Ϣ��ID����ʼ��ֹ��StateLifeline�ڵ��Ӧ����
    		  
    		 
    	  } 
    	  
    	  for(Element temp:Statelifelines)
    	  {
    		  int index=statelifelines.indexOf(temp);
    		  String lifelineID=temp.attribute("id").getValue();
    		  boundx=Integer.parseInt(temp.element("location").attributeValue("x"));
    		  //ͨ��lifelineId��ȡÿ��lifeline�ϵ�MessageId,�Ա㹹��EA��ʽ�е�links��ǩ��������Ϣ
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
    		  statelifelines.get(index).setId(temp.attribute("id").getValue());//����ID
    		  statelifelines.get(index).setName(temp.element("name").element("text").getText());//����name
    		  statelifelines.get(index).setStates(temp.element("state0").elementText("text"));
    		  String[] OtherStates=temp.element("states").elementText("text").split("\\|");
    		  for(String otherstate :OtherStates)
    		  statelifelines.get(index).setStates(temp.element("states").elementText(otherstate));//����״̬��Ϣ
    		 //���xref state״̬��ǩ
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
    		  //���runstate��ǩ
    		  String top=temp.element("location").attributeValue("y");
    		  String bottom=String.valueOf((Integer.parseInt(top)+width));
    		  String left=temp.element("location").attributeValue("x");
    		  String right=String.valueOf((Integer.parseInt(top)+height));
    		  String geometry="Left="+left+";"
    				  +"Top="+top+";"
    				  +"Right="+right+";"
    				  +"Bottom="+bottom+";";
    		  statelifelines.get(index).setGeometry(geometry); 
    		  //���geometry��ǩ
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

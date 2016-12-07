package com.horstmann.violet.application.menu.xiaole.TimingTransfrom;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.omg.CORBA.Environment;

import com.horstmann.violet.application.menu.xiaole.SequenceTransfrom.PackageInfo;


public class WriteEATimingGraph {

  PackageInfo packageInfo =new PackageInfo();
 // ReadVioletTimingGraph read=new ReadVioletTimingGraph();
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
	  List<Element> Statelifelines=nodes.elements("State__Lifeline");
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
		  TimingDiagramMessages.get(index).setName(temp.elementText("name"));
		  TimingDiagramMessages.get(index).setID(temp.attribute("id").getValue());//����ID
		  TimingDiagramMessages.get(index).setSEQTX(temp.element("EndState").getText());//����SEQTX����
		  TimingDiagramMessages.get(index).setSEQTS(temp.elementText("StarttimePoint"));//����SEQTS
		  TimingDiagramMessages.get(index).setSEQTE(temp.elementText("EndtimePoint"));//����SEQTE
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
		  int index=Statelifelines.indexOf(temp);		
		  String lifelineID=temp.attribute("id").getValue();
		  boundx=Integer.parseInt(temp.element("location").attributeValue("x").
			substring(0,temp.element("location").attributeValue("x").length()-2));
		  //ͨ��lifelineId��ȡÿ��lifeline�ϵ�MessageId,�Ա㹹��EA��ʽ�е�links��ǩ��������Ϣ
		  for(Map.Entry<String, String> entry :MessageStartIdOnStatelifeline.entrySet())
		  {		
			 
			  if(entry.getValue().equals(lifelineID))
			  {	  
				  statelifelines.get(index).setMessageIds(entry.getKey());					
			  }			  
		  } 
		//System.out.println(MessageStartIdOnStatelifeline);
		//System.out.println(MessageEndIdOnStatelifeline);
		  for(Map.Entry<String, String> entry :MessageEndIdOnStatelifeline.entrySet())
		  {
			 
			  if(entry.getValue().equals(lifelineID))
			  {
				  statelifelines.get(index).setMessageIds(entry.getKey());
			  }
		  } 
		  System.out.println(statelifelines.get(index).getMessageIds());
		  statelifelines.get(index).setId(temp.attribute("id").getValue());//����ID
		  statelifelines.get(index).setName(temp.element("name").element("text").getText());//����name
		  statelifelines.get(index).setStates(temp.element("state0").element("text").getText());		
		  String[] OtherStates=temp.element("states").elementText("text").split("\n");
		  for(String otherstate :OtherStates)
		  {
		  statelifelines.get(index).setStates(otherstate);
		  }//����״̬��Ϣ
		 //���xref state״̬��ǩ
		  Element schild=temp.element("schildren");
		  Element horizontalChild=schild.element("horizontalchild");
		  List<Element> horizontalchilds=horizontalChild.elements("com.horstmann.violet.product.diagram.abstracts.edge.HorizontalChild");		
		  String runstate="";
		  int width=Integer.parseInt(temp.elementText("width").substring(0,temp.elementText("width").length()-2));
		  int height=Integer.parseInt(temp.elementText("height").substring(0,temp.elementText("height").length()-2));
		  for(Element horizontalchild: horizontalchilds)
		  {	
		
		  int horizontalindex=horizontalchilds.indexOf(horizontalchild);
		  horizontalchild=horizontalchilds.get(horizontalchilds.size()-1-horizontalindex);
		  String startpoint=horizontalchild.element("startPoint").attributeValue("x");		  
		  String startpointAstime=String.valueOf(((Integer.parseInt(startpoint.substring(0,startpoint.length()-2))-boundx-150)*100/(width-150)));  		
		  String state =horizontalchild.elementText("state");
		  String condition=horizontalchild.elementText("condition");
		  String continuetime=horizontalchild.elementText("continuetime");
		  if(condition==null)
		  {
			  condition="";
		  }
		  if(continuetime==null)
		  {
			  continuetime="";
		  }
		  String var="@VAR;Variable="+state+";"+"Value="+startpointAstime+";"+"Op=="+";"+
		  "DConst="+continuetime+";"+"Event="+condition+";"+"@ENDVAR;";
		  runstate+=var;		  
		  }
		  statelifelines.get(index).setRunstate(runstate); 
		  //���runstate��ǩ 
		  String top=temp.element("location").attributeValue("y").substring(0,temp.element("location").attributeValue("y").length()-2);		 
		  String bottom=String.valueOf((Integer.parseInt(top)+height)); 
		  String left=temp.element("location").attributeValue("x").substring(0,temp.element("location").attributeValue("x").length()-2);
		  String right=String.valueOf((Integer.parseInt(left)+width));
		  String geometry="Left="+left+";"
				  +"Top="+top+";"
				  +"Right="+right+";"
				  +"Bottom="+bottom+";";
		  statelifelines.get(index).setGeometry(geometry); 
		  //���geometry��ǩ
	  }

	
  }
  public void WriteEATimingDiagram(String filename){
		
	    Document doc = DocumentHelper.createDocument();
	    Element XMI = doc.addElement("xmi:XMI");
	    XMI.addAttribute("xmi:version", "2.1");
	    XMI.addNamespace("uml", "http://schema.omg.org/spec/UML/2.1");
	    XMI.addNamespace("xmi", "http://schema.omg.org/spec/XMI/2.1");
	    Element Documentation=XMI.addElement("xmi:Documentation");
	    Documentation.addAttribute("exporter", "Enterprise Architect");
	    Documentation.addAttribute("exporterVersion", "6.5");
	   //ͷ
	    	    
	    Element Model=XMI.addElement("uml:Model");
	    Model.addAttribute("xmi:type","uml:Model");
	    Model.addAttribute("name","EA_Model");
	   //Model ��ǩ 
	    
	    Element packagedElement=Model.addElement("packagedElement");
	    packagedElement.addAttribute("xmi:type","uml:Package");
	    packagedElement.addAttribute("xmi:id",packageInfo.id);
	    //packagedElement��ǩ
	    
	    Element ownedBehavior=packagedElement.addElement("ownedBehavior");
	    ownedBehavior.addAttribute("xmi:type","uml:Interaction");
	    ownedBehavior.addAttribute("xmi:id", "EAID_IN000000_C5A_D3F7_4321_B37B_01F132FAA48");//���Ǹ���
	    ownedBehavior.addAttribute("name", "EA_Interaction1");
	    //ownedBehavior
	    //����<lifeline>
	    for(StateLifelineInfo temp:statelifelines){
	    	Element lifeline=ownedBehavior.addElement("lifeline");
	    	lifeline.addAttribute("xmi:type","uml:Lifeline");	    	
	    	lifeline.addAttribute("xmi:id", transIDtoEAID(temp.getId()));
	    	lifeline.addAttribute("name", temp.getName()); 
	      
	    }
	    
	  //����<Extension>
	    Element Extension=XMI.addElement("xmi:Extension");
	    //����<elements>
	    Element elements=Extension.addElement("elements");
	    //����elements�µ�<element>����������Ϣ  
	    for(StateLifelineInfo temp:statelifelines)
	    {
	    Element element=elements.addElement("element");
	    element.addAttribute("xmi:idref", transIDtoEAID(temp.getId()));
	    element.addAttribute("xmi:type", "uml:TimeLine");
	    element.addAttribute("name", temp.getName());
	    Element properties =element.addElement("properties");
	    properties.addAttribute("isSpecification", "false");
	    properties.addAttribute("sType", "TimeLine");
	    properties.addAttribute("nType", "0");
	    Element xrefs=element.addElement("xrefs");
	    String startxrefprop="$XREFPROP=$XID={";
	    String endxrefprop="}$XID;";
	    UUID xrefid =UUID.randomUUID();	  
	    String xrefendId=transIDtoEAID(temp.getId()).substring(5, transIDtoEAID(temp.getId()).length()).replace("_", "-");
	    String xrefpropID=xrefid.toString();
	    String XrefProp=startxrefprop+xrefpropID+endxrefprop;//���Xref��ǩ
	    ArrayList<String>states=temp.getStates();//��ȡ��ÿ��statelifeline��״̬
	    String allpar="";
	    for(String state: states)
	    {
	    	//int index=states.indexOf(state);
	    	//state=states.get(states.size()-1-index);
	    	String Par="@PAR;"+"Name="+state+";Size=40;@ENDPAR;";
	    	allpar=allpar+Par;
	    }
	   
	    xrefs.addAttribute("value", XrefProp
	    		+ "$NAM=Partitions$NAM;$TYP=element property$TYP;$VIS=Public$VIS;$PAR=0$PAR;"//�����һ��
	    		+ "$DES="//����
	    		+  allpar.replace(" ","")//����par�ֶ�
	    		+ "$DES;" 
	    		+ "$CLT={"
	    		+  xrefendId
	    		+ "}$CLT;$ENDXREF;");//����Ҫ���״̬�ֶ�
	    Element extendedProperties=element.addElement("extendedProperties");
	    extendedProperties.addAttribute("runstate",temp.getRunstate());//����Ҫ�������ʱ״̬�ı���Ϣ
	    Element links=element.addElement("links");
	   
	    
	    ArrayList<String> MessageIds=temp.getMessageIds();
	   
	    for(String messageid :MessageIds)
	    {
	    Element Sequence=links.addElement("Sequence");
	    Sequence.addAttribute("xmi:id", transIDtoEAID(messageid));
	    Sequence.addAttribute("start", transIDtoEAID(MessageStartIdOnStatelifeline.get(messageid)));
	    Sequence.addAttribute("end", transIDtoEAID(MessageEndIdOnStatelifeline.get(messageid)));	    
	    } 
	  
	    } 
	 Element connectors= Extension.addElement("connectors");
	 for(TimingDiagramMessageInfo temp: TimingDiagramMessages )
	 
	 {
		
		 Element connector = connectors.addElement("connector");
		 connector.addAttribute("xmi:idref", transIDtoEAID(temp.getID()));
		 Element properties=connector.addElement("properties");
		 properties.addAttribute("name", temp.getName());
		 properties.addAttribute("ea_type", "Sequence");
		 properties.addAttribute("subtype", "Timing");
		 properties.addAttribute("direction", "Source -&gt; Destination");
		 Element style=connector.addElement("style");
		 String seqte=temp.getSEQTE();
		 String seqtx=temp.getSEQTX();
		 String seqts=temp.getSEQTS();
		 style.addAttribute("value", "SEQTE="+seqte
				 +";"+"SEQTX="+seqtx+";"+"SEQTS="+seqts+";");//	����style��ǩ	 		 		 
	 }
	 Element diagrams=Extension.addElement("diagrams");
	 Element diagram=diagrams.addElement("diagram");
	 diagram.addAttribute("xmi:id","EAID_06C8CCFD_943B_4b03_8E16_C40675586A88");
	 Element model=diagram.addElement("model"); 
	 model.addAttribute("package", packageInfo.id);
	 model.addAttribute("owner", packageInfo.id);
	 Element properties = diagram.addElement("properties");
	 properties.addAttribute("name", "1");
	 properties.addAttribute("type", "Timing");
	 Element Diagramelements=diagram.addElement("elements");
	 for(StateLifelineInfo temp:statelifelines)
	 {
		Element element=Diagramelements.addElement("element");
		element.addAttribute("geometry", temp.getGeometry());//
		element.addAttribute("subject", transIDtoEAID(temp.getId()));//		
	 }
	 for(TimingDiagramMessageInfo temp :TimingDiagramMessages)
	 {
		 Element element=Diagramelements.addElement("element");
		 element.addAttribute("subject", transIDtoEAID(temp.getID()));//
	 }
	  outputXml(doc, filename);
	 
	}
  public String transIDtoEAID(String id)
  {	
	
	if(id.length()==1)	
	return "EAID_"+"7"+id+"D5"+id+"B"+id+id+"_D"+id+"D1_4"+id+"27_A"+id+"33_C1"+id+"A90355FED";
	if(id.length()==2)
	return "EAID_"+id+"D"+id+"B"+id+"_D7"+id+"_45"+id+"_A4"+id+"_C"+id+"A903"+id+"FED";
	if(id.length()==3)
    return "EAID_"+"73D53B33_D3D1_4327_A333_C13A90355"+id;
	return null;
  }
  public void outputXml(Document doc, String filename) {
	    try {
	      //�����������Ŀ�ĵ�
	      FileWriter fw = new FileWriter(filename);
	       
	      //���������ʽ���ַ���
	      OutputFormat format 
	        = OutputFormat.createPrettyPrint();
	      format.setEncoding("UTF-8");
	       
	      //�����������xml�ļ���XMLWriter����
	      XMLWriter xmlWriter 
	        = new XMLWriter(fw, format);
	      xmlWriter.write(doc);//*****
	      xmlWriter.close(); 
	    } catch (IOException e) {
	      e.printStackTrace();
	    }   
	  }
}

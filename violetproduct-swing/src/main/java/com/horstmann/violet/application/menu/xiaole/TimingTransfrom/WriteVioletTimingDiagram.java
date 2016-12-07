package com.horstmann.violet.application.menu.xiaole.TimingTransfrom;

import java.io.File;
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

import com.horstmann.violet.application.gui.util.xiaole.UppaalTransfrom.VioletMessageInfo;

public class WriteVioletTimingDiagram {
    SAXReader reader=new SAXReader();
    List<VioletStateLifelineInfo> VioletStatelines=new ArrayList<VioletStateLifelineInfo>();
    List<VioletHorizontalChildInfo>VioletHorizontalChilds;
    List<VioletMessageInfo>VioletMessages=new ArrayList<VioletMessageInfo>();   
	String allstates=null;//状态信息
	Map<String,String> startreferenceID=new HashMap<String, String>();//存放消息对应的startlifelineID
	Map<String,String> endreferenceID=new HashMap<String, String>();//存放消息对应的endlifelineID
    public void readEATimingDiagram(String path) throws DocumentException{   
     	File file = new File(path);
    	Document dom=reader.read(file);
    	Element root=dom.getRootElement();   
    	List<Element> lifelines=root.element("Extension").element("elements").elements("element");
    	lifelines.remove(0);//得到lifeline标签
    	List<Element> Sequences=lifelines.get(0).element("links").elements("Sequence");
    	String Bottom=null;
		//boolean startexist=false;
		//boolean endexist=false;
		for(Element firstStatelineSequence :Sequences)//处理第一个Sequence。为了防止Map重复添加相同键值
		{
		startreferenceID.put(firstStatelineSequence.attributeValue("id"), firstStatelineSequence.attributeValue("start"));
		endreferenceID.put(firstStatelineSequence.attributeValue("id"), firstStatelineSequence.attributeValue("end"));
		}
	
    	Element diagrams=root.element("Extension").element("diagrams");   	
    	List<Element> geometryelements=diagrams.element("diagram").element("elements").elements("element"); 
    	for(Element lfline:lifelines)
    	{   		
    		VioletStatelines.add(new VioletStateLifelineInfo());
    	}   	
      
    	for(Element lifeline:lifelines)
    	{   	
    		List<VioletHorizontalChildInfo> VioletHorizontalChilds= new ArrayList<VioletHorizontalChildInfo>();
    		int index=lifelines.indexOf(lifeline);//下标
    		
    		VioletStatelines.get(index).setName(lifeline.attributeValue("name"));//设置name
    		VioletStatelines.get(index).setID(lifeline.attributeValue("idref"));//设置ID
    		String xrefvalue=lifeline.element("xrefs").attributeValue("value");//状态信息
    		String splitstates[]=xrefvalue.split("\\;");
    		List<String> states=new ArrayList<String>();//状态信息
    		for(String splitstate: splitstates)
    		{
    			if("N".equals(splitstate.substring(0,1)))//定位到Name字段
    					{
    				      String splitname[]=splitstate.split("\\=");
    				      String name=splitname[1];
    				       states.add(name);
    					}
    		}
    		Sequences=lifeline.element("links").elements("Sequence");
    		//boolean startexist=false;
    		//boolean endexist=false;
    		
    		for(Element sequence : Sequences)
    		{ 
    			
    			while(startreferenceID.entrySet().iterator().hasNext())//存在着重复键值对
    			{
    				
    				Map.Entry entry=startreferenceID.entrySet().iterator().next();
    				if(!(sequence.attribute("id").equals(entry.getKey())))
    				{
    					
    					startreferenceID.put(sequence.attributeValue("id"), sequence.attributeValue("start")); 
   				           break;
    				}
    			}
    			while(endreferenceID.entrySet().iterator().hasNext())
    			{
    				
    				Map.Entry entry=endreferenceID.entrySet().iterator().next();
    				if(!(sequence.attribute("id").equals(entry.getKey())))
    				{
    					endreferenceID.put(sequence.attributeValue("id"), sequence.attributeValue("end")); 
   				           break;
    				}
    			}   			    			
    		}
    	//System.out.println(startreferenceID);
    		VioletStatelines.get(index).setState0(states.get(0));//设置第一个状态
    	    int statesize=states.size();  
    	    String combinstates[]=new String[10];
    	    String fixstates="";  	   
    	    for(int i=1;i<statesize;i++)
    	    {
    	    	if(i<statesize-1)
    	    	{ 
    	    		combinstates[i]=states.get(i)+"\n";   	    		
    	    	}
    	    	else
    	    	{
    	    		combinstates[i]=states.get(i);   	    		
    	    	} 	    	
    	    }    	 
    		for(int i=1;i<statesize;i++)
    		{
    			fixstates=fixstates.concat(combinstates[i]);    		
    		}
    		//System.out.println(fixstates);
    		VioletStatelines.get(index).setStates(fixstates);//设置其他状态
    		allstates=states.get(0)+"\n"+fixstates;
    	    String runstate=lifeline.element("extendedProperties").attributeValue("runstate");
    	    String runstatesSplit[]=runstate.split("\\;"); 
    	    int horizontalsize=0;  
    	   
    	    for(String horizontal : runstatesSplit)
    	    {
    	    	if("Var".equals(horizontal.substring(0,3)))//如果是状态字段
    	    	{
    	    		 VioletHorizontalChilds.add(new VioletHorizontalChildInfo());
    	    		 horizontalsize++;  
  	    		 
    	    	}   
    	    }    	   
    	    String geometryInfo=geometryelements.get(index).attributeValue("geometry");//获取到EA中的geometry字段
    		String geometryInfoSplit[]=geometryInfo.split("\\;");
    		String left=null,right=null,top=null,bottom=null;
    		for(String gSplit:geometryInfoSplit)
    		{
    			if(gSplit.substring(0,2).equals("Le"))//left字段
    			{
    				String LeftInfo[]=gSplit.split("\\=");
    			    left=LeftInfo[1];
    			}
    			if(gSplit.substring(0,2).equals("To"))//Top字段
    			{
    			
    				String TopInfo[]=gSplit.split("\\=");
    				top=TopInfo[1];
    				
    			}
    			if(gSplit.substring(0,2).equals("Ri"))//Right字段
    			{
    			
    				String RightInfo[]=gSplit.split("\\=");
    				right=RightInfo[1];
    			}
    			if(gSplit.substring(0,2).equals("Bo"))//Bottom字段
    			{
    				
    				String BottomInfo[]=gSplit.split("\\=");
    				bottom=BottomInfo[1];
    			} 
    		}
    	    String width=String.valueOf(Integer.parseInt(right)-Integer.parseInt(left));
    	    String height=String.valueOf(Integer.parseInt(bottom)-Integer.parseInt(top));
    	   //设置宽和高
    		VioletStatelines.get(index).setWidth(width);
    		VioletStatelines.get(index).setHeight(height);
    	   //设置location坐标	
    		VioletStatelines.get(index).setLocationX(left);
    		VioletStatelines.get(index).setLocationY(top);   			
    		Bottom=String.valueOf(Integer.parseInt(bottom));
    	      for(int splitIndex=1,horizontalIndex=0;splitIndex<runstatesSplit.length;horizontalIndex++,splitIndex+=3)
    	        {   
    	  
    	    	if("Var".equals(runstatesSplit[splitIndex].substring(0,3)))//如果是状态字段
    	    	{
    	    		
    	    		String tempString1[]=runstatesSplit[splitIndex].split("\\=");// 
    	    		VioletHorizontalChilds.get(horizontalsize-1-horizontalIndex).setState(tempString1[1]);//设置状态字段 
    	    		splitIndex+=1;//定位到下一个字段
    	    		String tempString2[]=runstatesSplit[splitIndex].split("\\=");    	    		
    	    		VioletHorizontalChilds.get(horizontalsize-1-horizontalIndex).setStartPointX(tempString2[1]); //设置value字段
    	    		splitIndex+=2;//定位到Event或者DConst字段//或者后面没有字段
    	    		int temp=splitIndex;
    	    	       if(temp<runstatesSplit.length){    	    	    	
    	    		        if("Eve".equals(runstatesSplit[temp].substring(0,3)))
    	    		        {//判断后面有没有Event字段   
    	    		        
    	    	      	      String tempString3=runstatesSplit[temp].substring(6);
    	    	      	    
    	    	    	      VioletHorizontalChilds.get(horizontalsize-1-horizontalIndex).setCondition(tempString3);
    	    	    	      splitIndex+=1;//定位到Event后面的DConst字段    	   	    	     
    	    	    	      String tempString4[]=runstatesSplit[splitIndex].split("\\=");    	    
    	    	    		  VioletHorizontalChilds.get(horizontalsize-1-horizontalIndex).setContinuetime(tempString4[1]);
    	    		        }
    	    	    	    else if("DCo".equals(runstatesSplit[temp].substring(0,3)))  
    	    	    		  {
    	    	    		  String tempString5[]=runstatesSplit[temp].split("\\=");
    	    	    		 
        	    	    	  VioletHorizontalChilds.get(horizontalsize-1-horizontalIndex).setContinuetime(tempString5[1]);
        	  	    	  
    	    	    		  }	   		            	    	         
    	    	            else
    	    	            {    	    	         
    	    	             VioletHorizontalChilds.get(horizontalsize-1-horizontalIndex).setCondition("");
    	    	             VioletHorizontalChilds.get(horizontalsize-1-horizontalIndex).setContinuetime("");//给一个空的字符串
    	    	             splitIndex-=1;//定位到下一个horizontal字段
    	    	          
    	    	            }	
    	   
    	    	       } 
    	         }
    	    	  	      
    	        } 
    	    for(int i=0;i<horizontalsize;i++)
    	    {	
    	    	if(i+1<horizontalsize)
    	    	{ 
    	    		VioletHorizontalChilds.get(i).setEndPointX(VioletHorizontalChilds.get(i+1).getStartPointX());
    	    	}
    	    	else 
    	    	{
    	    	VioletHorizontalChilds.get(i).setEndPointX("100");
    	    	}//最后一个节点X坐标是statelifeline的最右边X坐标
    	    }
    	 
    	    for(VioletHorizontalChildInfo hchild: VioletHorizontalChilds)//设置Y坐标   	    	
    	    {
    	      String ALLstates[]=allstates.split("\n");//获取到所有的状态字段    	   
    	      for(int statesIndex=0;statesIndex<ALLstates.length;statesIndex++)
    	      {    	 
    	    	 
    	    	  if(hchild.getState().equals(ALLstates[statesIndex]))
    	    	  {    	    
    	    		
    	    		  String Y=String.valueOf(Integer.parseInt(Bottom)-30*(statesIndex+1));
    	    		  hchild.setStartPointY(Y);
    	    	      hchild.setEndPointY(Y);
    	    	  }   	       	    	
    	      } 
    	      hchild.setID(UUID.randomUUID().toString());//这里的horiozontalchildID
    	    } 
    	
    	
    	    VioletStatelines.get(index).setHorizonchilds(VioletHorizontalChilds);    	     	    
    	}   	
    	//开始处理消息信息   	  		
    	Element connectors=root.element("Extension").element("connectors");//获取到connectors标签
    	List<Element> connector=connectors.elements("connector");
    	for(Element conn : connector)
    	{
    		VioletMessages.add(new VioletMessageInfo());		
    	}
    
    	for(Element Conn : connector)
    	{    
    		int connIndex=connector.indexOf(Conn);
    		VioletMessages.get(connIndex).setName(Conn.element("properties").attributeValue("name"));
    		VioletMessages.get(connIndex).setID(Conn.attributeValue("idref"));
    		
    		String stylevalue=Conn.element("style").attributeValue("value");
    		String stylevalueSplit[]=stylevalue.split("\\;");
    		for(String valueSplit : stylevalueSplit)
    		{
    			
    			String value[]=valueSplit.split("\\=");
    			if("E".equals(value[0].substring(value[0].length()-1)))
    			{
    				VioletMessages.get(connIndex).setEndLocationX(value[1]);
    				VioletMessages.get(connIndex).setEndtimePoint(value[1]);
    			}
    			if("X".equals(value[0].substring(value[0].length()-1)))
    			{
    				VioletMessages.get(connIndex).setEndstate(value[1]);
    			}
    			if("S".equals(value[0].substring(value[0].length()-1)))
    			{
    				VioletMessages.get(connIndex).setStartLocationX(value[1]);
    				VioletMessages.get(connIndex).setStarttimePoint(value[1]);
    				
    			}   					
    		}
    	}
    		for(VioletMessageInfo messageInfo: VioletMessages)//设置belongtoFlag和referenceID
    		{
    		
    			messageInfo.setStartReferenceID(startreferenceID.get(messageInfo.getID()));
    			messageInfo.setEndReferenceID(endreferenceID.get(messageInfo.getID()));
    			for(VioletStateLifelineInfo statelifeline: VioletStatelines)
    			{    	
    				
    		
    				if(messageInfo.getStartReferenceID().equals(statelifeline.getID()))
    				{	
    		
    					for(VioletHorizontalChildInfo hchildinfo:statelifeline.getHorizonchilds())
    					{
    					
    						//System.out.println(statelifeline.getHorizonchilds());
    					if(messageInfo.getStarttimePoint().equals(hchildinfo.getEndPointX()))
    							{   
    					
    						        int startflag=statelifeline.getHorizonchilds().indexOf(hchildinfo);
    						        
    						        messageInfo.setBelongtostartflag(String.valueOf(startflag));
    						       
    							}
    					}
    				   
    				}
    				if(messageInfo.getEndReferenceID().equals(statelifeline.getID()))
    				{	
    					for(VioletHorizontalChildInfo hchildinfo:statelifeline.getHorizonchilds())
    					{	    						    						    					
    					if(messageInfo.getEndtimePoint().equals(hchildinfo.getEndPointX()))
    							{	 						
    						        int endflag=statelifeline.getHorizonchilds().indexOf(hchildinfo);
    						        messageInfo.setBelongtoendflag(String.valueOf(endflag));
    							}   					
    				    }
    				}
    			}
    		}
    		
    	}    	   	  	
    
    
    
    
    public void WriteVioletTiming(String filename)
    {
    	Document doc = DocumentHelper.createDocument();
    	Element TimingDiagramGraph=doc.addElement("TimingDiagramGraph");
    	TimingDiagramGraph.addAttribute("id", "1");
    	Element nodes = TimingDiagramGraph.addElement("nodes");
    	nodes.addAttribute("id", "2"); 
    	int i=5;
    	for(VioletStateLifelineInfo violetstateline : VioletStatelines)   	
    	{
    	
    	 Element Statelifeline=nodes.addElement("StateLifeline");
    	 Statelifeline.addAttribute("id", violetstateline.getID());
    	 Element children=Statelifeline.addElement("children");
    	 children.addAttribute("id", String.valueOf(i-1));
    	 Element schildren=Statelifeline.addElement("schildren");
    	 schildren.addAttribute("class","com.horstmann.violet.product.diagram.timing.StateLine");
    	 schildren.addAttribute("id", String.valueOf(i));
    	 Element horizontalchild=schildren.addElement("horizontalchild");
    	 horizontalchild.addAttribute("id",String.valueOf(i+1));
    	 for(VioletHorizontalChildInfo horichild : violetstateline.getHorizonchilds())
    	 {
    	 Element HorizontalChild=horizontalchild.addElement("com.horstmann.violet.product.diagram.abstracts.edge.HorizontalChild");
    	 HorizontalChild.addAttribute("id", horichild.getID());
    	 Element startPoint = HorizontalChild.addElement("startPoint");
    	 Element endPoint = HorizontalChild.addElement("endPoint");
    	 Element state=HorizontalChild.addElement("state").addText(horichild.getState()==null?"":horichild.getState());
    	 Element condition=HorizontalChild.addElement("condition").addText(horichild.getCondition()==null?"":horichild.getCondition());
    	 Element continuetime=HorizontalChild.addElement("continuetime").addText(horichild.getContinuetime()==null?"":horichild.getContinuetime());
    	 startPoint.addAttribute("class", "Point2D.Double").addAttribute("id",UUID.randomUUID().toString()).
    	 addAttribute("x",transPointFromTimetoLocation(violetstateline.getLocationX(),horichild.getStartPointX(), violetstateline.getWidth())).
    	 addAttribute("y",horichild.getEndPointY());
    	 endPoint.addAttribute("class", "Point2D.Double").addAttribute("id",UUID.randomUUID().toString()).
    	 addAttribute("x", transPointFromTimetoLocation(violetstateline.getLocationX(),horichild.getEndPointX(), violetstateline.getWidth())).
    	 addAttribute("y", horichild.getEndPointY());
    	 }
    	
         Element location=Statelifeline.addElement("location");
         location.addAttribute("class", "Point2D.Double").addAttribute("id", UUID.randomUUID().toString()).
         addAttribute("x", violetstateline.getLocationX()).addAttribute("y", violetstateline.getLocationY());
    	 Element id= Statelifeline.addElement("id");   	 
         id.addAttribute("id", UUID.randomUUID().toString());
         Element state0=Statelifeline.addElement("state0");
         state0.addAttribute("id", UUID.randomUUID().toString());
         Element text=state0.addElement("text");
         text.addText(violetstateline.getState0());
         Element width=Statelifeline.addElement("width");
         width.addText(violetstateline.getWidth());
         Element height=Statelifeline.addElement("height");
         height.addText(violetstateline.getHeight());
         Element s=Statelifeline.addElement("s");
         s.addAttribute("reference", String.valueOf(i));
         Element name=Statelifeline.addElement("name");
         Element nametext=name.addElement("text");
        
         nametext.addText(violetstateline.getName());
         Element states=Statelifeline.addElement("states");
         Element statesText=states.addElement("text");
         System.out.println(violetstateline.getStates());
         statesText.addText(violetstateline.getStates());
       
         i+=10;
    	 }
    	 

    Element edges=TimingDiagramGraph.addElement("edges");
    for(VioletMessageInfo message:VioletMessages)
    {
    	
    	Element SendMessageEdge=edges.addElement("SendMessageEdge");
    	SendMessageEdge.addAttribute("id", message.getID());
    	Element start = SendMessageEdge.addElement("start");
    	Element end = SendMessageEdge.addElement("end");
    	start.addAttribute("class", "StateLifeline").
    	addAttribute("reference", message.getStartReferenceID());
    	end.addAttribute("class", "StateLifeline").
    	addAttribute("reference", message.getEndReferenceID());
    	Element transitionPoints=SendMessageEdge.addElement("transitionPoints");
    	transitionPoints.addAttribute("id", UUID.randomUUID().toString());
    	Element name=SendMessageEdge.addElement("name");
    	name.addText(message.getName());
    	Element TimeConstraint =SendMessageEdge.addElement("TimeConstraint");
    	TimeConstraint.addText("");	
    	Element Condition=SendMessageEdge.addElement("Condition");
    	Condition.addText("");
    	Element StarttimePoint =SendMessageEdge.addElement("StarttimePoint");
    	StarttimePoint.addText(message.getStarttimePoint());
    	Element EndtimePoint =SendMessageEdge.addElement("EndtimePoint");
    	EndtimePoint.addText(message.getEndtimePoint());
    	Element EndState=SendMessageEdge.addElement("EndState");
    	EndState.addText(message.getEndstate());
    	Element belongtostartflag=SendMessageEdge.addElement("belongtostartflag");
    	belongtostartflag.addText(message.getBelongtostartflag());
    	Element belongtoendflag=SendMessageEdge.addElement("belongtoendflag");
    	belongtoendflag.addText(message.getBelongtoendflag());
   	
    }  	
    	outputXml(doc,filename);
    	
    }
    public String transPointFromTimetoLocation(String boundx,String Pointx,String width)
    {
    	return String.valueOf(Integer.parseInt(Pointx)*(Integer.parseInt(width)-150)/100
    			+Integer.parseInt(boundx)+150);
    	
    }
    public void outputXml(Document doc, String filename) {
	    try {
	      //定义输出流的目的地
	      FileWriter fw = new FileWriter(filename);
	       
	      //定义输出格式和字符集
	      OutputFormat format 
	        = OutputFormat.createPrettyPrint();
	      format.setEncoding("UTF-8");
	       
	      //定义用于输出xml文件的XMLWriter对象
	      XMLWriter xmlWriter 
	        = new XMLWriter(fw, format);
	      xmlWriter.write(doc);//*****
	      xmlWriter.close(); 
	    } catch (IOException e) {
	      e.printStackTrace();
	    }   
	  }
}
	
	
	
	


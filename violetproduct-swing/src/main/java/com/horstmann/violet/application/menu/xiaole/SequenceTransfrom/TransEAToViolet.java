package com.horstmann.violet.application.menu.xiaole.SequenceTransfrom;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class TransEAToViolet { 
	SAXReader reader = new SAXReader();
	List<LifeLineNodeInfo> LifeLines=new ArrayList<LifeLineNodeInfo>();
	List<MessageInfo> Messages=new ArrayList<MessageInfo>();
	List<ReturnEdgeInfo> ReturnEdges=new ArrayList<ReturnEdgeInfo>();
	List<CallEdgeInfo> CallEdges=new ArrayList<CallEdgeInfo>();
	List<VLCombinedFragmentInfo> CombinedFragments=new ArrayList<VLCombinedFragmentInfo>();
	List<VLFragmentPartInfo> FragmentParts=new ArrayList<VLFragmentPartInfo>();
	List<ActivationBarNodeInfo> ActivationBarNodes=new ArrayList<ActivationBarNodeInfo>();
	List<String> CallEdgesId=new ArrayList<String>();
	List<String> ReturnEdgesId=new ArrayList<String>();
    public void ReadEATimingGraph(String url) throws Exception
    {
    	Document dom=reader.read(url);
    	Element root=dom.getRootElement();
    	Element Extension=root.element("Extension");
    	Element Elements=Extension.element("elements");
    
    	 //处理消息信息
        Element Model=root.element("Model");
        Element packagedElement1=Model.element("packagedElement");
        Element packagedElement2=packagedElement1.element("packagedElement");
        Element ownedBehavior=packagedElement2.element("ownedBehavior");
        List<Element> messageElements=ownedBehavior.elements("message");
        for(Element messageElement : messageElements)
        {
        	if(messageElement.attributeValue("messageSort").equals("synchCall"))
        	{
        		CallEdgeInfo calledge=new CallEdgeInfo();
        		calledge.setId(messageElement.attributeValue("id"));
        		calledge.setMiddleLabel(messageElement.attributeValue("name"));
        		CallEdges.add(calledge);
        		CallEdgesId.add(calledge.getId());
        	}
        	if(messageElement.attributeValue("messageSort").equals("reply"))
        	{
        		ReturnEdgeInfo returnedge=new ReturnEdgeInfo();
        		returnedge.setId(messageElement.attributeValue("id"));
        		returnedge.setMiddleLabel(messageElement.attributeValue("name"));
        		ReturnEdges.add(returnedge);  
        		ReturnEdgesId.add(returnedge.getId());
        	}    	    	
        }
        //处理消息信息
        Element connectors=Extension.element("connectors");
        List<Element> connectorElements=connectors.elements("connector");
        for(Element connectorElement : connectorElements)
        {
        //处理发送消息
          for(CallEdgeInfo callEdge :CallEdges)
          {
        	  if(callEdge.getId().equals(connectorElement.attributeValue("idref")))
        	  {
        		  Element extendedProperties=connectorElement.element("extendedProperties");
        		  String Properties=extendedProperties.attributeValue("sequence_points");
        		  String SplitProperties[]=Properties.split("\\;");
        		  for(String splitproperty : SplitProperties)
        		  {
        			  if(splitproperty.startsWith("PtStartX"))
        			  {
        				  String LocationXsplit[]=splitproperty.split("\\=");
        				  callEdge.setStartLocationX(LocationXsplit[1]);
        			  }
        			  if(splitproperty.startsWith("PtStartY"))
        			  {
        				  String LocationYsplit[]=splitproperty.split("\\=");
        				  callEdge.setStartLocationY(LocationYsplit[1].substring(1));
        			  }
        			  if(splitproperty.startsWith("PtEndX"))
        			  {
        				  String LocationXsplit[]=splitproperty.split("\\=");
        				  callEdge.setEndLocationX(LocationXsplit[1]);
        			  }
        			  if(splitproperty.startsWith("PtEndY"))
        			  {
        				  String LocationYsplitp[]=splitproperty.split("\\=");
        				  callEdge.setEndLocationY(LocationYsplitp[1].substring(1));
        			  }
        			}     			  
       		 } 
        	}//发送消息解析到此结束
          //处理返回消息
          for(ReturnEdgeInfo ReturnEdge :ReturnEdges)
          {
        	  if(ReturnEdge.getId().equals(connectorElement.attributeValue("idref")))
        	  {
        		  Element extendedProperties=connectorElement.element("extendedProperties");
        		  String Properties=extendedProperties.attributeValue("sequence_points");
        		  String SplitProperties[]=Properties.split("\\;");
        		  for(String splitproperty : SplitProperties)
        		  {
        			  if(splitproperty.startsWith("PtStartX"))
        			  {
        				  String LocationXsplit[]=splitproperty.split("\\=");
        				  ReturnEdge.setStartLocationX(LocationXsplit[1]);
        			  }
        			  if(splitproperty.startsWith("PtStartY"))
        			  {
        				  String LocationYsplit[]=splitproperty.split("\\=");
        				  ReturnEdge.setStartLocationY(LocationYsplit[1].substring(1));
        			  }
        			  if(splitproperty.startsWith("PtEndX"))
        			  {
        				  String LocationXsplit[]=splitproperty.split("\\=");
        				  ReturnEdge.setEndLocationX(LocationXsplit[1]);
        			  }
        			  if(splitproperty.startsWith("PtEndY"))
        			  {
        				  String LocationYsplitp[]=splitproperty.split("\\=");
        				  ReturnEdge.setEndLocationY(LocationYsplitp[1].substring(1));
        			  }
        			}     			  
       		 } 
        	}//返回消息解析到此结束 
        }	//connector标签解析到此结束          
      //获取到图中每个元素的element标签
    	List<Element> elements=Elements.elements("element");	     
    	for(Element element : elements)
    	{
    		FragmentParts=new ArrayList<VLFragmentPartInfo>();//
    		if(element.attributeValue("type").equals("uml:InteractionFragment"))
    		{//如果是组合片段
    			VLCombinedFragmentInfo combinedFragment=new VLCombinedFragmentInfo();
    			combinedFragment.setId(element.attributeValue("idref"));
    			Element xrefs=element.element("xrefs");
    			//获取到Xrefs标签
    			String Value=xrefs.attributeValue("value");   			
    			String SplitValues[]=Value.split("\\;");
    			for(int i=0;i<SplitValues.length;i++)
    			{
    				if(SplitValues[i].substring(0,4).equals("Name"))
    				{
    					String SplitNames[]=SplitValues[i].split("\\=");
    					String name=SplitNames[1];
    					VLFragmentPartInfo fragmentpart=new VLFragmentPartInfo();
    					fragmentpart.setConditionText(name);//设置condition	
                        String Splitsize=SplitValues[i+1];
                        String SplitSizes[]=Splitsize.split("\\=");
                        String size=SplitSizes[1];
                        fragmentpart.setSize(size);//设置size
                        fragmentpart.setId(GenerateID());
                        FragmentParts.add(fragmentpart);
                        
                        
    				}				
    			}
    			//因为EA中的fragmentPart是从下到上的
    			//而Violet中的fragmentPart是从上到下的
    			//故逆序该List即可
    			List<VLFragmentPartInfo> ReverseFragmentParts=new ArrayList<VLFragmentPartInfo>();
    			for(int i=0;i<FragmentParts.size();i++)
    			{    		
    				ReverseFragmentParts.add(i, FragmentParts.get(FragmentParts.size()-1-i)); 
    			}
    			System.out.println(ReverseFragmentParts);
    			combinedFragment.setFragmentParts(ReverseFragmentParts);
                CombinedFragments.add(combinedFragment);
    		}
    		if(element.attributeValue("type").equals("uml:Sequence"))    			
    		{//如果是lifelineNode   			
    			LifeLineNodeInfo lifeLineNode=new LifeLineNodeInfo();
    			String lifelineId=element.attributeValue("idref");
    			String lifelinename=element.attributeValue("name");
    			lifeLineNode.setId(lifelineId);
    			lifeLineNode.setName(lifelinename);
    			//接下来对links标签进行解析，用于创建ActivationBarNode
    		    Element links=element.element("links");
    		    //获取到Sequence标签，这里的Sequence标签即为从该lifelineNode
    		    //发送或接收的所有消息
    		    List<Element> Sequence=links.elements("Sequence");
    		    boolean isfirstLifelineNode=false;
    			for(Element sequence : Sequence)
    			{
    				//第一种情况
    				//一般的lifelineNode(不是初始的lifelineNode)
    				//该lifelineNode有消息向其发送
    				//有几个消息就新建几个ActivationBarNode
    				if(sequence.attributeValue("end").equals(lifelineId)
    						&&CallEdgesId.contains(sequence.attributeValue("id")))
    				{
    					isfirstLifelineNode=true;     					
    					ActivationBarNodeInfo activationBarNode=new ActivationBarNodeInfo();
    					activationBarNode.setId(GenerateID());//新建ID
    					activationBarNode.setParentId(lifelineId);//新建父节点ID
    					activationBarNode.setLocationX("32");//默认离lifelineNode节点X轴的偏差
    					for(CallEdgeInfo calledge : CallEdges){
    					//接收一个消息新建一个ActivationBar
    					if(calledge.getId().equals(sequence.attributeValue("id")))
    					{
    					activationBarNode.setLocationY(calledge.getEndLocationY());
    					calledge.setEndReferenceId(activationBarNode.getId());
    					
    					} 					
    				}
    					
    					lifeLineNode.getActivationBarNodes().add(activationBarNode);
    				}
    			}
    				//第二种情况：
    				//如果没有一个消息向其发送，则该lifelineNode为初始lifelineNode
    				//新建一个ActivationBarNode即可   				
    				if(!isfirstLifelineNode)    						
    				{    					
    					ActivationBarNodeInfo activationBarNode=new ActivationBarNodeInfo();
    					activationBarNode.setId(GenerateID());
    					activationBarNode.setLocationX("32");//默认的第一个activationBar的位置信息
    					activationBarNode.setLocationY("122");
    					for(Element sequence1 : Sequence)
    					{
    					for(CallEdgeInfo calledge : CallEdges)
    					{
    						//对初始lifelineNode节点上发送的消息进行处理
    						if(calledge.getId().equals(sequence1.attributeValue("id"))
    								&&sequence1.attributeValue("start").equals(lifelineId))
    						{
    							calledge.setStartReferenceId(activationBarNode.getId());    							
    						}
    						
    					}
    					for(ReturnEdgeInfo returnedge : ReturnEdges)
    					{
    						//对初始lifelineNode节点上接收的消息进行处理
    						if(returnedge.getId().equals(sequence1.attributeValue("id"))
    								&&sequence1.attributeValue("end").equals(lifelineId))
    						{
    							returnedge.setEndReferenceId(activationBarNode.getId());
    						}
    					}
    					//添加ActivationBarNode节点    					    					
    				}    				
    					lifeLineNode.getActivationBarNodes().add(activationBarNode); 
    			}    			
    			for(Element sequence : Sequence)
    			{
    			//进一步对Edges的相对于ActivationBar的startReferenceID和EndReferenceID进行处理
    			int mindistance=1000;
    			int distance=0;
    				//1.首先对该lifelineNode的callEdge进行处理
    				for(CallEdgeInfo calledge:CallEdges)
    				{
    					if(calledge.getId().equals(sequence.attributeValue("id"))
    							&&sequence.attributeValue("start").equals(lifelineId))
    					{
    					for(ActivationBarNodeInfo activationBarNode : lifeLineNode.getActivationBarNodes())
    	    			{
    	    				int LocationY=Integer.parseInt(activationBarNode.getLocationY());    	    										
    						int messageLocationY=Integer.parseInt(calledge.getEndLocationY());
    						distance=messageLocationY-LocationY;//这里的distance即为边距离activationBarNode的距离
    						if(distance>=0&&distance<mindistance)
    						{
    							mindistance=distance;
    							calledge.setStartReferenceId(activationBarNode.getId());
    						}
    					}
    				    }
    				}
    				 mindistance=1000;
        			 distance=0;
    				for(ReturnEdgeInfo returnedge :ReturnEdges)
    				{
    					if(returnedge.getId().equals(sequence.attributeValue("id"))
    							&&sequence.attributeValue("start").equals(lifelineId))
    					{
    					for(ActivationBarNodeInfo activationBarNode : lifeLineNode.getActivationBarNodes())
    	    			{
    	    				int LocationY=Integer.parseInt(activationBarNode.getLocationY());    	    										
    						int messageLocationY=Integer.parseInt(returnedge.getEndLocationY());
    						distance=messageLocationY-LocationY;//这里的distance即为边距离activationBarNode的距离
    						if(distance>=0&&distance<mindistance)
    						{
    							mindistance=distance;
    							returnedge.setStartReferenceId(activationBarNode.getId());
    						}
    					}
    				    }
    					if(returnedge.getId().equals(sequence.attributeValue("id"))
    							&&sequence.attributeValue("end").equals(lifelineId))
    					{
    					for(ActivationBarNodeInfo activationBarNode : lifeLineNode.getActivationBarNodes())
    	    			{
    	    				int LocationY=Integer.parseInt(activationBarNode.getLocationY());    	    										
    						int messageLocationY=Integer.parseInt(returnedge.getEndLocationY());
    						distance=messageLocationY-LocationY;//这里的distance即为边距离activationBarNode的距离
    						if(distance>=0&&distance<mindistance)
    						{
    							mindistance=distance;
    							returnedge.setEndReferenceId(activationBarNode.getId());
    						}
    					}
    				    }    					
    				}    				        		 
    			}   		
    			LifeLines.add(lifeLineNode);   		
    		}    	
    	}
    Element diagrams=Extension.element("diagrams");
    Element diagram=diagrams.element("diagram");
    Element diagramelements=diagram.element("elements");
    List<Element> geometryElements=diagramelements.elements("element");
    for(Element geometryElement : geometryElements)
    {
    	//处理坐标信息
    for(VLCombinedFragmentInfo combinedFragment : CombinedFragments){
    	if(combinedFragment.getId().equals(geometryElement.attributeValue("subject")))
    		//通过ID设置组合片段相应的坐标信息
    			{
    		      String geometry=geometryElement.attributeValue("geometry");
    		      String SplitGeometrys[]=geometry.split("\\;");
    		      String Left=null,Top=null,Right=null,Bottom=null;
    		      for(String splitgeometry : SplitGeometrys)
    		      {
    		    	  if(splitgeometry.substring(0,1).equals("L"))
    		    		  //
    		    	  {
    		    		 String Lefts[]=splitgeometry.split("\\=");
    		    		 Left=Lefts[1];
    		    	  }
    		    	  if(splitgeometry.substring(0,1).equals("T"))
    		    	  {
    		    		  String Tops[]=splitgeometry.split("\\=");
    		    		  Top=Tops[1];
    		    	  }
    		    	  if(splitgeometry.substring(0,1).equals("R"))
    		    	  {
    		    		  String Rights[]=splitgeometry.split("\\=");
    		    		  Right=Rights[1];
    		    	  }
    		    	  if(splitgeometry.substring(0,1).equals("B"))
    		    	  {
    		    		  String Bottoms[]=splitgeometry.split("\\=");
    		    		  Bottom=Bottoms[1]; 
    		    	  }    		    	   		    	  
    		      }
    		      combinedFragment.setLocationX(Left);
    		      combinedFragment.setLocationY(Top);
    		      combinedFragment.setHeight(String.valueOf(Integer.parseInt(Bottom)-Integer.parseInt(Top)));
    		      combinedFragment.setWidth(String.valueOf(Integer.parseInt(Right)-Integer.parseInt(Left)));  
    			}  
         }
    for(LifeLineNodeInfo lifeline : LifeLines)
    {//设置lifelineNode的坐标信息
    	if(lifeline.getId().equals(geometryElement.attributeValue("subject")))
    		//通过ID设置Lifeline相应的坐标信息
    			{
    		      String geometry=geometryElement.attributeValue("geometry");
    		      String SplitGeometrys[]=geometry.split("\\;");
    		      String Left=null,Top=null,Right=null,Bottom=null;
    		      for(String splitgeometry : SplitGeometrys)
    		      {
    		    	  if(splitgeometry.substring(0,1).equals("L"))
    		    		  //
    		    	  {
    		    		 String Lefts[]=splitgeometry.split("\\=");
    		    		 Left=Lefts[1];
    		    	  }
    		    	  if(splitgeometry.substring(0,1).equals("T"))
    		    	  {
    		    		  String Tops[]=splitgeometry.split("\\=");
    		    		  Top=Tops[1];
    		    	  }
    		    	  if(splitgeometry.substring(0,1).equals("R"))
    		    	  {
    		    		  String Rights[]=splitgeometry.split("\\=");
    		    		  Right=Rights[1];
    		    	  }
    		    	  if(splitgeometry.substring(0,1).equals("B"))
    		    	  {
    		    		  String Bottoms[]=splitgeometry.split("\\=");
    		    		  Bottom=Bottoms[1]; 
    		    	  }    		    	   		    	  
    		      }
    		      lifeline.setLocationX(Left);
    		      lifeline.setLocationY("0");//这里默认为0    		    
    			}  
    	}
    }  
    //开始解析组合片段的嵌套关系   
    List<Element> fragments=ownedBehavior.elements("fragment");   
    for(Element fragment:fragments)//首先对fragment标签进行解析
    {
    	//分两种情况
    	//1.组合片段
    	//2.无组合片段(在EA中,无组合片段也会有fragment,其中标签的xmi:type为"uml:OccurrenceSpecification")
    	Element operand=fragment.element("operand");    	
    	SetCombinedFragmentInfo(fragment, operand);
    }  
    }		
	//按照常用习惯以及绘图意义，组合片段内必包含message，否则无意义
    //这里迭代的把所有的组合片段解析出来即可
    public void SetCombinedFragmentInfo(Element fragment,Element operand){	
    	if(fragment.attributeValue("type").equals("uml:CombinedFragment"))
    	{    
    		
    	for(VLCombinedFragmentInfo combinedfragment : CombinedFragments)
    	{
    		
    		if(combinedfragment.getId().equals(fragment.attributeValue("id")))
    		{
    			
    			combinedfragment.setType(fragment.attributeValue("interactionOperator"));   			   			
    		}
    	}
    	List<Element> nestingfragments =operand.elements("fragment");
    	for(Element nestingfragment:nestingfragments)
    	{
    		operand=nestingfragment.element("operand");
    		SetCombinedFragmentInfo(nestingfragment, operand);
    	}
    	}
    } 
    public void WriteVioletSequence(String filename)
    {
    	//创建根节点
    	 Document doc = DocumentHelper.createDocument();
    	 Element SequenceDiagramGraph=doc.addElement("SequenceDiagramGraph").addAttribute("id", GenerateID());
    	 Element nodes=SequenceDiagramGraph.addElement("nodes").addAttribute("id", GenerateID());    	 
    	 //处理lifelineNode
    	 for(LifeLineNodeInfo lifeline : LifeLines)
    	 { 		 
    		 Element LifelineNode=nodes.addElement("LifelineNode").addAttribute("id", lifeline.getId());
    		 Element children=LifelineNode.addElement("children").addAttribute("id", GenerateID());
    		 for(ActivationBarNodeInfo activationBarNode : lifeline.getActivationBarNodes()){
    		 Element ActivationBarNode=children.addElement("ActivationBarNode");
    		 setColor(ActivationBarNode);
    		 ActivationBarNode.addAttribute("id", activationBarNode.getId());
    		 ActivationBarNode.addElement("children");
    		 ActivationBarNode.addElement("parent").addAttribute("class", "LifelineNode")
    		 .addAttribute("reference", lifeline.getId());
    		 ActivationBarNode.addElement("location").addAttribute("class", "Point2D.Double")
    		 .addAttribute("id", GenerateID()).addAttribute("x", activationBarNode.getLocationX())
    		 .addAttribute("y", activationBarNode.getLocationY());
    		 }
    		 LifelineNode.addElement("location").addAttribute("class", "Point2D.Double")
    		 .addAttribute("id", GenerateID()).addAttribute("x", lifeline.getLocationX())
    		 .addAttribute("y", lifeline.getLocationY());
    		 setColor(LifelineNode);
    		 Element name=LifelineNode.addElement("name").addAttribute("id", GenerateID());
    		 name.addElement("text").addText(lifeline.getName());
    		 
    	 }
    	 
    	//处理CombinedFragment
    	 for(VLCombinedFragmentInfo combinedFragment : CombinedFragments)
    	 {
    		 int size=0;
    		 Element CombinedFragment=nodes.addElement("CombinedFragment");
    		 CombinedFragment.addElement("location").addAttribute("class", "Point2D.Double")
    		 .addAttribute("id", GenerateID()).addAttribute("x", combinedFragment.getLocationX())
    		 .addAttribute("y", combinedFragment.getLocationY());
    		 CombinedFragment.addElement("type").addAttribute("id", GenerateID())
    		 .addAttribute("name", combinedFragment.getType().toUpperCase());
    		 Element fragmentParts=CombinedFragment.addElement("fragmentParts");
    		 Element conditions=CombinedFragment.addElement("conditions"); 
    		 CombinedFragment.addElement("ID").addText(GenerateID());
    		 for(VLFragmentPartInfo fragmentpart : combinedFragment.getFragmentParts())
    		 {
    			size+=Integer.parseInt(fragmentpart.getSize());
    			//int fragmentpartIndex=combinedFragment.getFragmentParts().indexOf(fragmentpart);
    			conditions.addElement("string").addText(fragmentpart.getConditionText());
    			Element fragmentPart=fragmentParts.addElement("com.horstmann.violet.product.diagram.abstracts.property.FragmentPart");
    			fragmentPart.addElement("conditionText").addText(fragmentpart.getConditionText());
    			Element borderline=fragmentPart.addElement("borderline").addAttribute("class", "java.awt.geom.Line2D$Double")
    					.addAttribute("id", GenerateID());
    			fragmentPart.addElement("coveredMessagesID").addAttribute("id",GenerateID());
    			fragmentPart.addElement("nestingChildNodesID").addAttribute("id", GenerateID());
    			borderline.addElement("x1").addText(combinedFragment.getLocationX());
    			if(combinedFragment.getFragmentParts().size()>1){
    				//有大于等于2个以上的fragmentpart
    			String Y1=String.valueOf(Integer.parseInt(combinedFragment.getLocationY())
    					+size-Integer.parseInt(fragmentpart.getSize()));
    			borderline.addElement("y1").addText(Y1);
    			borderline.addElement("x2").addText(String.valueOf(Integer.parseInt(combinedFragment.getLocationX())+
    					Integer.parseInt(combinedFragment.getWidth())));;
    			borderline.addElement("y2").addText(Y1);
    			}
    			else
    				//处理没有fragmenpart的情况
    			{
    			String Y1=String.valueOf(Integer.parseInt(combinedFragment.getLocationY()));
    			borderline.addElement("y1").addText(Y1);
    			borderline.addElement("x2").addText(String.valueOf(Integer.parseInt(combinedFragment.getLocationX())+
    					Integer.parseInt(combinedFragment.getWidth())));;
    			borderline.addElement("y2").addText(Y1);
    			}
    			fragmentPart.addElement("borderlinehaschanged").setText("true");
    			}
    		   CombinedFragment.addElement("width").addText(combinedFragment.getWidth());
    		   CombinedFragment.addElement("height").addText(combinedFragment.getHeight());    			    	 
    	 }       	 
    	 Element edges=SequenceDiagramGraph.addElement("edges").addAttribute("id", GenerateID());
    	 //处理CallEdges
    	 for(CallEdgeInfo calledge :CallEdges)
    	 {
    		
    		Element Calledge=edges.addElement("CallEdge").addAttribute("id", calledge.getId());
    		Calledge.addElement("start").addAttribute("class", "ActivationBarNode")
    		.addAttribute("reference", calledge.getStartReferenceId());
    		Calledge.addElement("end").addAttribute("class", "ActivationBarNode")
    		.addAttribute("reference", calledge.getEndReferenceId());
    		Calledge.addElement("ID").addText(GenerateID());
    		Calledge.addElement("startLocation").addAttribute("class", "Point2D.Double")
    		.addAttribute("id", GenerateID()).addAttribute("x", calledge.getStartLocationX())
    		.addAttribute("y", calledge.getStartLocationY());
    		Calledge.addElement("endLocation").addAttribute("class", "Point2D.Double")
    		.addAttribute("id", GenerateID()).addAttribute("x", calledge.getEndLocationX())
    		.addAttribute("y", calledge.getEndLocationY());
    		Calledge.addElement("middleLabel").addText(calledge.getMiddleLabel());   	   		 
    	 }
    	 //处理ReturnEdges
    	 for(ReturnEdgeInfo returnedge : ReturnEdges)
    	 {
    		 Element Returnedge=edges.addElement("ReturnEdge").addAttribute("id", returnedge.getId());
    		 Returnedge.addElement("start").addAttribute("class", "ActivationBarNode")
     		.addAttribute("reference", returnedge.getStartReferenceId());
    		 Returnedge.addElement("end").addAttribute("class", "ActivationBarNode")
     		.addAttribute("reference", returnedge.getEndReferenceId());
    		 Returnedge.addElement("startLocation").addAttribute("class", "Point2D.Double")
     		.addAttribute("id", GenerateID()).addAttribute("x", returnedge.getStartLocationX())
     		.addAttribute("y", returnedge.getStartLocationY());
    		 Returnedge.addElement("endLocation").addAttribute("class", "Point2D.Double")
     		.addAttribute("id", GenerateID()).addAttribute("x", returnedge.getEndLocationX())
     		.addAttribute("y", returnedge.getEndLocationY());
    		 Returnedge.addElement("ID").addText(GenerateID());
    		 Returnedge.addElement("middleLabel").addText(returnedge.getMiddleLabel());   	   	 
    	 }    	     	 
    	 outputXml(doc, filename);     	 
    }
    public void setColor(Element Node)
   	{
   	Element backgroundColor=Node.addElement("backgroundColor");
   	backgroundColor.addAttribute("id",UUID.randomUUID().toString());
   	Element red =backgroundColor.addElement("red");
   	red.addText("255");
   	Element green =backgroundColor.addElement("green");
   	green.addText("255");
   	Element blue=backgroundColor.addElement("blue");
   	blue.addText("255");
   	Element alpha =backgroundColor.addElement("alpha");
   	alpha.addText("255");
   	Element borderColor=Node.addElement("borderColor");
   	borderColor.addAttribute("id",UUID.randomUUID().toString());
   	Element red1 =borderColor.addElement("red");
   	red1.addText("191");
   	Element green1 =borderColor.addElement("green");
   	green1.addText("191");
   	Element blue1=borderColor.addElement("blue");
   	blue1.addText("191");
   	Element alpha1 =borderColor.addElement("alpha");
   	alpha1.addText("255");
   	
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
	private String GenerateID() {
		// TODO Auto-generated method stub
		return UUID.randomUUID().toString();
	}
}			

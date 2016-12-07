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
    
    	 //������Ϣ��Ϣ
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
        //������Ϣ��Ϣ
        Element connectors=Extension.element("connectors");
        List<Element> connectorElements=connectors.elements("connector");
        for(Element connectorElement : connectorElements)
        {
        //��������Ϣ
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
        	}//������Ϣ�������˽���
          //��������Ϣ
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
        	}//������Ϣ�������˽��� 
        }	//connector��ǩ�������˽���          
      //��ȡ��ͼ��ÿ��Ԫ�ص�element��ǩ
    	List<Element> elements=Elements.elements("element");	     
    	for(Element element : elements)
    	{
    		FragmentParts=new ArrayList<VLFragmentPartInfo>();//
    		if(element.attributeValue("type").equals("uml:InteractionFragment"))
    		{//��������Ƭ��
    			VLCombinedFragmentInfo combinedFragment=new VLCombinedFragmentInfo();
    			combinedFragment.setId(element.attributeValue("idref"));
    			Element xrefs=element.element("xrefs");
    			//��ȡ��Xrefs��ǩ
    			String Value=xrefs.attributeValue("value");   			
    			String SplitValues[]=Value.split("\\;");
    			for(int i=0;i<SplitValues.length;i++)
    			{
    				if(SplitValues[i].substring(0,4).equals("Name"))
    				{
    					String SplitNames[]=SplitValues[i].split("\\=");
    					String name=SplitNames[1];
    					VLFragmentPartInfo fragmentpart=new VLFragmentPartInfo();
    					fragmentpart.setConditionText(name);//����condition	
                        String Splitsize=SplitValues[i+1];
                        String SplitSizes[]=Splitsize.split("\\=");
                        String size=SplitSizes[1];
                        fragmentpart.setSize(size);//����size
                        fragmentpart.setId(GenerateID());
                        FragmentParts.add(fragmentpart);
                        
                        
    				}				
    			}
    			//��ΪEA�е�fragmentPart�Ǵ��µ��ϵ�
    			//��Violet�е�fragmentPart�Ǵ��ϵ��µ�
    			//�������List����
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
    		{//�����lifelineNode   			
    			LifeLineNodeInfo lifeLineNode=new LifeLineNodeInfo();
    			String lifelineId=element.attributeValue("idref");
    			String lifelinename=element.attributeValue("name");
    			lifeLineNode.setId(lifelineId);
    			lifeLineNode.setName(lifelinename);
    			//��������links��ǩ���н��������ڴ���ActivationBarNode
    		    Element links=element.element("links");
    		    //��ȡ��Sequence��ǩ�������Sequence��ǩ��Ϊ�Ӹ�lifelineNode
    		    //���ͻ���յ�������Ϣ
    		    List<Element> Sequence=links.elements("Sequence");
    		    boolean isfirstLifelineNode=false;
    			for(Element sequence : Sequence)
    			{
    				//��һ�����
    				//һ���lifelineNode(���ǳ�ʼ��lifelineNode)
    				//��lifelineNode����Ϣ���䷢��
    				//�м�����Ϣ���½�����ActivationBarNode
    				if(sequence.attributeValue("end").equals(lifelineId)
    						&&CallEdgesId.contains(sequence.attributeValue("id")))
    				{
    					isfirstLifelineNode=true;     					
    					ActivationBarNodeInfo activationBarNode=new ActivationBarNodeInfo();
    					activationBarNode.setId(GenerateID());//�½�ID
    					activationBarNode.setParentId(lifelineId);//�½����ڵ�ID
    					activationBarNode.setLocationX("32");//Ĭ����lifelineNode�ڵ�X���ƫ��
    					for(CallEdgeInfo calledge : CallEdges){
    					//����һ����Ϣ�½�һ��ActivationBar
    					if(calledge.getId().equals(sequence.attributeValue("id")))
    					{
    					activationBarNode.setLocationY(calledge.getEndLocationY());
    					calledge.setEndReferenceId(activationBarNode.getId());
    					
    					} 					
    				}
    					
    					lifeLineNode.getActivationBarNodes().add(activationBarNode);
    				}
    			}
    				//�ڶ��������
    				//���û��һ����Ϣ���䷢�ͣ����lifelineNodeΪ��ʼlifelineNode
    				//�½�һ��ActivationBarNode����   				
    				if(!isfirstLifelineNode)    						
    				{    					
    					ActivationBarNodeInfo activationBarNode=new ActivationBarNodeInfo();
    					activationBarNode.setId(GenerateID());
    					activationBarNode.setLocationX("32");//Ĭ�ϵĵ�һ��activationBar��λ����Ϣ
    					activationBarNode.setLocationY("122");
    					for(Element sequence1 : Sequence)
    					{
    					for(CallEdgeInfo calledge : CallEdges)
    					{
    						//�Գ�ʼlifelineNode�ڵ��Ϸ��͵���Ϣ���д���
    						if(calledge.getId().equals(sequence1.attributeValue("id"))
    								&&sequence1.attributeValue("start").equals(lifelineId))
    						{
    							calledge.setStartReferenceId(activationBarNode.getId());    							
    						}
    						
    					}
    					for(ReturnEdgeInfo returnedge : ReturnEdges)
    					{
    						//�Գ�ʼlifelineNode�ڵ��Ͻ��յ���Ϣ���д���
    						if(returnedge.getId().equals(sequence1.attributeValue("id"))
    								&&sequence1.attributeValue("end").equals(lifelineId))
    						{
    							returnedge.setEndReferenceId(activationBarNode.getId());
    						}
    					}
    					//���ActivationBarNode�ڵ�    					    					
    				}    				
    					lifeLineNode.getActivationBarNodes().add(activationBarNode); 
    			}    			
    			for(Element sequence : Sequence)
    			{
    			//��һ����Edges�������ActivationBar��startReferenceID��EndReferenceID���д���
    			int mindistance=1000;
    			int distance=0;
    				//1.���ȶԸ�lifelineNode��callEdge���д���
    				for(CallEdgeInfo calledge:CallEdges)
    				{
    					if(calledge.getId().equals(sequence.attributeValue("id"))
    							&&sequence.attributeValue("start").equals(lifelineId))
    					{
    					for(ActivationBarNodeInfo activationBarNode : lifeLineNode.getActivationBarNodes())
    	    			{
    	    				int LocationY=Integer.parseInt(activationBarNode.getLocationY());    	    										
    						int messageLocationY=Integer.parseInt(calledge.getEndLocationY());
    						distance=messageLocationY-LocationY;//�����distance��Ϊ�߾���activationBarNode�ľ���
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
    						distance=messageLocationY-LocationY;//�����distance��Ϊ�߾���activationBarNode�ľ���
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
    						distance=messageLocationY-LocationY;//�����distance��Ϊ�߾���activationBarNode�ľ���
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
    	//����������Ϣ
    for(VLCombinedFragmentInfo combinedFragment : CombinedFragments){
    	if(combinedFragment.getId().equals(geometryElement.attributeValue("subject")))
    		//ͨ��ID�������Ƭ����Ӧ��������Ϣ
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
    {//����lifelineNode��������Ϣ
    	if(lifeline.getId().equals(geometryElement.attributeValue("subject")))
    		//ͨ��ID����Lifeline��Ӧ��������Ϣ
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
    		      lifeline.setLocationY("0");//����Ĭ��Ϊ0    		    
    			}  
    	}
    }  
    //��ʼ�������Ƭ�ε�Ƕ�׹�ϵ   
    List<Element> fragments=ownedBehavior.elements("fragment");   
    for(Element fragment:fragments)//���ȶ�fragment��ǩ���н���
    {
    	//���������
    	//1.���Ƭ��
    	//2.�����Ƭ��(��EA��,�����Ƭ��Ҳ����fragment,���б�ǩ��xmi:typeΪ"uml:OccurrenceSpecification")
    	Element operand=fragment.element("operand");    	
    	SetCombinedFragmentInfo(fragment, operand);
    }  
    }		
	//���ճ���ϰ���Լ���ͼ���壬���Ƭ���ڱذ���message������������
    //��������İ����е����Ƭ�ν�����������
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
    	//�������ڵ�
    	 Document doc = DocumentHelper.createDocument();
    	 Element SequenceDiagramGraph=doc.addElement("SequenceDiagramGraph").addAttribute("id", GenerateID());
    	 Element nodes=SequenceDiagramGraph.addElement("nodes").addAttribute("id", GenerateID());    	 
    	 //����lifelineNode
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
    	 
    	//����CombinedFragment
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
    				//�д��ڵ���2�����ϵ�fragmentpart
    			String Y1=String.valueOf(Integer.parseInt(combinedFragment.getLocationY())
    					+size-Integer.parseInt(fragmentpart.getSize()));
    			borderline.addElement("y1").addText(Y1);
    			borderline.addElement("x2").addText(String.valueOf(Integer.parseInt(combinedFragment.getLocationX())+
    					Integer.parseInt(combinedFragment.getWidth())));;
    			borderline.addElement("y2").addText(Y1);
    			}
    			else
    				//����û��fragmenpart�����
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
    	 //����CallEdges
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
    	 //����ReturnEdges
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
	private String GenerateID() {
		// TODO Auto-generated method stub
		return UUID.randomUUID().toString();
	}
}			

package com.horstmann.violet.application.gui.util.layout;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/*
 * ��ȡXML�ļ�
 */
	public class ReadXml {
		SAXReader reader= new SAXReader();	
		Document dom;
		Element root;
		List<Element> Templatelist=new ArrayList<Element>();//XMl��Template����
		int A[][]=new int[20][20];	
		int TransitionNum=0,
		    LocationNum=0,
		    TemplateNum=0;
		List<Element> transitionlist=new ArrayList<Element>();//transition����
		List<Element> locationlist=new ArrayList<Element>();//location����
	  public ReadXml(String filename) throws DocumentException
	  {
			dom=reader.read(filename);	
			root=dom.getRootElement();	
			Templatelist=root.elements("template");
	  }
		public int[][] find(int a) throws Exception {	
			transitionlist=Templatelist.get(a).elements("transition");
			locationlist=Templatelist.get(a).elements("location");
			int m=0,n=0,N=0;		
		    for(Element transition :transitionlist)
		    {		      
		    	String source=transition.element("source").attribute("ref").getValue();
		    	String target=transition.element("target").attribute("ref").getValue();  	  
		    		for(Element location: locationlist)			    		
		    		 {		    			
		    			if(source.equals(location.attribute("id").getValue())==true)
		    			{ 		    				
		    				N=locationlist.indexOf(location);
		    				m=N;
		    			}		    					    		 		    			    	
		    		    if(target.equals(location.attribute("id").getValue())==true)
		    		    { 
		    		       N=locationlist.indexOf(location);
		    			   n=N;		    			  	    			   
		    		    }	    		   		    		          
		    		}	
		    		A[m][n]=1; 		    		    		
		   		}		  
		    return A;//����ÿ��Template�бߵļ��ϣ��ö�ά�����ʾ
			}		
		public int getTransitionNum(int a) {
		transitionlist=Templatelist.get(a).elements("transition");			
		Iterator transitionIterator = transitionlist.iterator();
			while(transitionIterator.hasNext())
			{
				TransitionNum++;
				transitionIterator.next();//
			}			
			return TransitionNum;
		}
		public void setTransitionNum(int transitionNum) {
			TransitionNum = transitionNum;
		}
		public int getLocationNum(int a) {				
			locationlist=Templatelist.get(a).elements("location");
			Iterator locationIterator = locationlist.iterator();					
			while(locationIterator.hasNext())
			{
				LocationNum++;
				locationIterator.next();//
			}										
			return LocationNum;
		}
		public void setLocationNum(int locationNum) {
			LocationNum = locationNum;
		}		
		public int getTemplateNum() throws DocumentException {//��ȡTemplate����
		
			Iterator TemplateIterator = Templatelist.iterator();
			while(TemplateIterator.hasNext())
			{
			    TemplateNum++;
			    TemplateIterator.next();
			}		
			return TemplateNum;
		}
		public void setTemplateNum(int templateNum) {
			TemplateNum = templateNum;
		}
		public String[] GetID(int a) throws Exception//��ȡÿ��Template�еĶ�������
		{
			locationlist=Templatelist.get(a).elements("location");
		    int j=0;
			String VertexID[]=new String[20];
			for(Element location:locationlist)		
       		{			
			 VertexID[j]=location.attribute("id").getValue();
			 j++;
       	    }
			return VertexID;
		}//�������еķ��������Ĳ�������a,a�����������������ǵڼ���Template	
	}	
		    	
		    	
		    
		
			
		
	
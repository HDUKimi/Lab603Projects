package com.horstmann.violet.application.gui.util.wujun.TDVerification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.dom4j.Element;
public class ReadAutomata {
		
	ArrayList<UppaalTemPlate> uppaalTemplates = new ArrayList<UppaalTemPlate>();
	ArrayList<UppaalLocation> locations = new ArrayList<UppaalLocation>();
	ArrayList<UppaalTransition> transitions = new ArrayList<UppaalTransition>();
			
	public ArrayList<UppaalTemPlate> getUppaalTemplates() {
		return uppaalTemplates;
	}
	
	public void load(Element root) throws Exception{
		
		ArrayList<Element> templateList = new ArrayList<Element>();
		
		
		templateList.addAll(root.elements("template"));
		
		
		for(Object oTemplate: templateList)
		{
			Element templateI = (Element)oTemplate;
			
			UppaalTemPlate template = new UppaalTemPlate();
			template.setName(templateI.element("name").getText());//设置template的名字
			
			locations = new ArrayList<UppaalLocation>();
			transitions = new ArrayList<UppaalTransition>();
			
			ArrayList<Element> locationList = new ArrayList<Element>();			
			locationList.addAll(templateI.elements("location"));//读取该template的locations		
			
			
			for(Object oLocation: locationList)
			{
				Element locationI = (Element)oLocation;	
				
				UppaalLocation location = new UppaalLocation();
				location.setId(locationI.attributeValue("id"));
				location.setName(locationI.element("name").getText());
				location.setFinl(Boolean.valueOf(locationI.attributeValue("finl")));
				location.setInit(Boolean.valueOf(locationI.attributeValue("init")));
				if (locationI.element("label") != null
						&& locationI.element("label").attributeValue("kind")
								.equals("invariant")) {//设置x
					
					String temp = locationI.element("label").getTextTrim();
					if (temp.contains("t...t")) {
						temp = temp.substring(5, temp.length());
					}
					StringBuilder sb = new StringBuilder();
					char[] chars = temp.toCharArray();
					
					for(int i = 0; i < temp.length(); i++) {
						
						if (Character.isDigit(chars[i]) || chars[i] == '.') {
							sb.append(chars[i]);
						}
					}
					
					location.setX(Double.valueOf(sb.toString()));  
				}
				
				ArrayList<Element> momentElements = new ArrayList<Element>();
				momentElements.addAll((ArrayList<Element>) locationI.elements("moment"));
				for(Element element : momentElements) {
					location.startTimeList.add(Integer.valueOf(element.attributeValue("startTime")));
					location.endTimeList.add(Integer.valueOf(element.attributeValue("endTime")));
					location.timeDurationList.add(element.attributeValue("timeDuration"));
//					System.err.println(element.attributeValue("timeDuration"));
				}
				
				locations.add(location);
			}
			template.setLocations(locations);
			
			ArrayList<Element> transitionList = new ArrayList<Element>();			
			transitionList.addAll(templateI.elements("transition"));
			for(Object oTransition: transitionList)//读取transition
			{
				Element transitionI = (Element)oTransition;	
				
				UppaalTransition transition = new UppaalTransition();
				
				ArrayList<Element> labels = (ArrayList) transitionI.elements("label");
				Iterator label_Iterator = labels.iterator();
				
				transition.setSource(transitionI.element("source").attributeValue("ref").substring(2));
				transition.setTarget(transitionI.element("target").attributeValue("ref").substring(2));
					
				
				while(label_Iterator.hasNext())
				{
					Element label = (Element) label_Iterator.next();
					transition.setStartTime(Integer.valueOf(label.attributeValue("startTime")));
					transition.setEndTime(Integer.valueOf(label.attributeValue("endTime")));
					if(label.attributeValue("kind").equals("assignment"))
					{
						String temp = label.getTextTrim();
						String[] t = temp.split("=");
						
						transition.setX(Double.valueOf(t[1]));  //设置x.
					}
					else if(label.attributeValue("kind").equals("synchronisation"))
					{
						transition.setFromName(label.attributeValue("from"));
						transition.setToName(label.attributeValue("to"));
						transition.setName(label.getTextTrim());
						
						if (!label.attributeValue("duration").equals("null")) {
							transition.setTimeDuration(label.attributeValue("duration"));
						}
						
						transition.setOut(true);//设置out
						
					}
					else{
						transition.setName(label.getTextTrim());
					}
					
				}
				
				
				transitions.add(transition);
			}
			
			template.setTransitions(transitions);
			
			
			uppaalTemplates.add(template);
		}
		
	}
}

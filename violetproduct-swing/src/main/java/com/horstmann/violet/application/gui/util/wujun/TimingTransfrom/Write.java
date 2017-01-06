package com.horstmann.violet.application.gui.util.wujun.TimingTransfrom;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.xml.transform.Templates;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import antlr.debug.TraceAdapter;

public class Write {

	public static void creatXML(String Path, HashSet<String> global_declarations,
			HashSet<String> template_instantiations, ArrayList<UppaalTemPlate> temPlates) throws IOException {
		int x = 30, y = 30;
		Document document = DocumentHelper.createDocument(); // 创建文档
		Element nta = document.addElement("nta");
		Element declaration = nta.addElement("declaration");
		String sdeclaration = global_declarations.toString().substring(1, global_declarations.toString().length() - 1);
		declaration.setText("chan" + " " + sdeclaration + ";clock x;");// 写入全局变量

		Iterator<UppaalTemPlate> templateIterator = temPlates.iterator();

		while (templateIterator.hasNext()) {// 写入template
			UppaalTemPlate template = templateIterator.next();
			
			Element tem = nta.addElement("template");
			Element nameElement = tem.addElement("name");
			String xx = String.valueOf(x++);
			String yy = String.valueOf(y++);
			nameElement.addAttribute("x", xx);
			nameElement.addAttribute("y", yy);
			nameElement.setText(template.getName());
			tem.addElement("declaration");// .setText("// Place local
											// declarations here.");
			int inittemp = -1;
			Iterator<UppaalLocation> locationIterator = template.locations.iterator();
			Iterator<UppaalTransition> transitonIterator = template.transitions.iterator();

			while (locationIterator.hasNext()) {// 写入location
				UppaalLocation location = locationIterator.next();
				Element loc = tem.addElement("location");

				loc.addAttribute("init", location.getInit());
				loc.addAttribute("finl", location.getFinl());
				loc.addAttribute("id", "id" + location.getId());
				loc.addAttribute("x", xx);
				loc.addAttribute("y", yy);
				Element name2 = loc.addElement("name");
				name2.addAttribute("x", xx);
				name2.addAttribute("y", yy);
				name2.setText(location.getName()); // 不同时间的横线都是同一个状态
													// //+"|start:"+location.getStartTime()+";"+"end:"+location.getEndTime());
				if (location.getInvariant() != null) {
					Element invariant_ele = loc.addElement("label");
					invariant_ele.addAttribute("kind", "invariant");
					invariant_ele.addAttribute("x", xx);
					invariant_ele.addAttribute("y", yy);
					invariant_ele.setText(location.getInvariant());
				}

				if (location.getType() == 1) {
					loc.addElement("urgent");
				}

				if (location.getInit().equals("true"))
					inittemp = location.getId();

				// 写入   这个状态在不同时刻的开始时间、结束时间、时间约束
				if (location.endTimeList.size() < location.startTimeList.size()) {
					location.endTimeList.add(Integer.MAX_VALUE);
				}
				for(int i = 0; i < location.getStartTimeList().size(); i++) {
					int startT = location.startTimeList.get(i);
					int endT = location.endTimeList.get(i) == 0 ? Integer.MAX_VALUE : location.endTimeList.get(i);
					String timeDuration = "null";
					if (location.timeDurationList.size() >= i + 1) {
						timeDuration = location.timeDurationList.get(i);
					}
					if (timeDuration == null) {
						timeDuration = "null";
					}
					loc.addElement("moment").addAttribute("startTime", String.valueOf(startT))
											.addAttribute("endTime", String.valueOf(endT))
											.addAttribute("timeDuration", timeDuration);
				}
			}
			tem.addElement("init").addAttribute("ref", "id" + inittemp);
			while (transitonIterator.hasNext()) {// 写入状态迁移
				UppaalTransition transition = transitonIterator.next();
				Element tran = tem.addElement("transition");
				if (transition.getEndTime() == null) {
					transition.setEndTime(transition.getStartTime());
				}
				// tran.addElement("")
				tran.addElement("source").addAttribute("ref", "id" + transition.getSourceId());
				tran.addElement("target").addAttribute("ref", "id" + transition.getTargetId());
				String[] tempStrings = transition.getKind();
				String[] tempStrings2 = transition.getNameText();
				// String tempInner = transition.getInner();

				int i = 0;
				boolean out = false;
				if (tempStrings[i] == null) {
					tran.addElement("label")
					.addAttribute("kind", "none")
					.addAttribute("x", xx)
					.addAttribute("y", yy)
					.addAttribute("startTime", transition.getStartTime())
					.addAttribute("endTime", transition.getStartTime())
					.addAttribute("from", transition.getFromName())
					.addAttribute("to", transition.getToName()).addAttribute("duration", "null")
					.setText("null");
				}
				while (tempStrings[i] != null) {
					if (tempStrings[i].equals("synchronisation")) {
						if (!tempStrings2[i].contains("?")) {
							out = true;
						}
						String duration = transition.getDuration();
						if (duration != null) {
							if (tempStrings2[i].contains("?")) {
								duration = "null";
							} 
							tran.addElement("label")
							.addAttribute("kind", tempStrings[i])
							.addAttribute("x", xx)
							.addAttribute("y", yy)
							.addAttribute("startTime", transition.getStartTime())
							.addAttribute("endTime", transition.getEndTime())
							.addAttribute("from", transition.getFromName())
							.addAttribute("to", transition.getToName()).addAttribute("duration", duration)
							.setText(tempStrings2[i]);

						} else {
							tran.addElement("label")
							.addAttribute("kind", tempStrings[i])
							.addAttribute("x", xx)
							.addAttribute("y", yy)
							.addAttribute("startTime", transition.getStartTime())
							.addAttribute("endTime", transition.getEndTime())
							.addAttribute("from", transition.getFromName())
							.addAttribute("to", transition.getToName()).addAttribute("duration", "null")
							.setText(tempStrings2[i]);
						}
						i++;
					} else {
						
						tran.addElement("label")
						.addAttribute("kind", tempStrings[i])
						.addAttribute("x", xx)
						.addAttribute("y", yy)
						.addAttribute("startTime", transition.getStartTime())
						.addAttribute("endTime", transition.getEndTime())
						.addAttribute("from", transition.getFromName())
						.addAttribute("to", transition.getToName()).addAttribute("duration", "null")
						.setText(tempStrings2[i]);
						
						i++;
					}

				}
				if (!out) {
					tran.addAttribute("out", "false");
				} else {
					tran.addAttribute("out", "true");
				}
				

			}
			// 只写入第一个template
			break;
		}
		Element sysElement = nta.addElement("system");
		String instantiations = template_instantiations.toString().substring(1,
				template_instantiations.toString().length() - 1);
		sysElement.setText("system" + " " + instantiations + ";");

		String doString = document.asXML();
		String[] out = doString.split("[?]>");
		// String dTDString = "?><!DOCTYPE nta PUBLIC '-//Uppaal Team//DTD Flat
		// System 1.1//EN'
		// 'http://www.it.uu.se/research/group/darts/uppaal/flat-1_1.dtd'>";
		FileOutputStream outputStream = new FileOutputStream(Path);
		outputStream.write(doString.getBytes());

		// outputStream.write(out[0].getBytes());
		// outputStream.write(dTDString.getBytes());
		// outputStream.write(out[1].getBytes());
		outputStream.close();
		// System.out.println(doString);

	}

}

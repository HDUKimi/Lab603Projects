package com.horstmann.violet.application.gui.util.wujun.TDVerification;

import java.util.ArrayList;
import java.util.HashSet;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.horstmann.violet.application.gui.util.wujun.TDVerification.RowStringsForDisplay.MessageCompare;
import com.horstmann.violet.application.gui.util.wujun.TDVerification.RowStringsForDisplay.StateCompare;
import com.horstmann.violet.application.gui.util.wujun.TimingTransfrom.Display;
import com.horstmann.violet.application.gui.util.wujun.TimingTransfrom.EADiagramsData;
import com.horstmann.violet.application.gui.util.wujun.TimingTransfrom.EALifeline;
import com.horstmann.violet.application.gui.util.wujun.TimingTransfrom.EAMessage;
import com.horstmann.violet.application.gui.util.wujun.TimingTransfrom.EAStateInfo;
import com.horstmann.violet.application.gui.util.wujun.TimingTransfrom.ReadTimingDiagram;

public class CompareEAtoAutomata {
	// ea
	ArrayList<EALifeline> allLifelines;
	ArrayList<EAMessage> allConnectors;
	ArrayList<EADiagramsData> diagramsDatas;
	// 自动机
	ArrayList<UppaalTemPlate> templates;
	ArrayList<UppaalTransition> transitions;
	ArrayList<UppaalLocation> locations;
	// 返回RowStringsForDisplay  （其中包含状态的对比list 和消息的对比list）
	public RowStringsForDisplay compareFromXMLPath(String eaPath, String automataPath) throws Exception {
		RowStringsForDisplay row = new RowStringsForDisplay();
		readEA(eaPath);
		readAutomata(automataPath);
		for(EADiagramsData diagramsData : diagramsDatas) {
			if (diagramsData.getName().equals(templates.get(0).getName())) {
				// 找状态 与location对比
				for(EALifeline lifeline : diagramsData.getLifelines()) {
					for(EAStateInfo stateInfo : lifeline.getStateInfos()) {
						// 从自动机中找到一个location
						boolean find = false;
						for(UppaalLocation location : locations) {
							if (stateInfo.getName().equals(location.getName().split(":")[0])) {
								StateCompare sc = new StateCompare(stateInfo, location, "ok");
								row.getStateCompareList().add(sc);
								find = true;
								break;
							}
						}
						if (!find) {
							row.getStateCompareList().add(new StateCompare(stateInfo, null, "false"));
						}
						
					}
				}
				
				// 找message 与transition对比
				for(EAMessage message : diagramsData.getConnectors()) {
					// 从自动机中找到一个transition
					boolean find = false;
					for(UppaalTransition transition : transitions) {
						if (message.getName().equals(transition.getName())) {
							MessageCompare mc = new MessageCompare(message, transition, "ok");
							row.getMessageCompareList().add(mc);
							find = true;
							break;
						}
					}
					if (!find) {
						row.getMessageCompareList().add(new MessageCompare(message, null, "false"));
					}
				}
			}
		}
		return row;
	}

	private void readAutomata(String automataPath) throws Exception {
		// 读取自动机.xml 中的数据
		SAXReader reader = new SAXReader();// 获取解析器
		Document dom = reader.read(automataPath);// 解析XML获取代表整个文档的dom对象
		Element root = dom.getRootElement();// 获取根节点

		ReadAutomata uppaal = new ReadAutomata();
		uppaal.load(root);

		templates = uppaal.getUppaalTemplates();
		transitions = templates.get(0).getTransitions();
		locations = templates.get(0).getLocations();
	}

	private void readEA(String eaPath) throws Exception {
		// 读取ea.xml中的数据-------------------------------------------------------------------------------------------
		SAXReader reader = new SAXReader();// 获取解析器

		Document dom = reader.read(eaPath);// 解析XML获取代表整个文档的dom对象

		Element root = dom.getRootElement();// 获取根节点
		
		ReadTimingDiagram uml = new ReadTimingDiagram();
		uml.load(root);
		if (uml.hasNoLifeline()) {
			System.exit(0);
			Display.println("没有找到生命线，退出");
		}

		allLifelines = uml.getTimingLine();
		allConnectors = uml.getConnector();
		diagramsDatas = uml.getUmlAllDiagramData();
		
		for (EADiagramsData diagramsData : diagramsDatas) {// 遍历一张图
			ArrayList<EALifeline> lifelines = new ArrayList<EALifeline>(); // 存储这张图lifeline
			ArrayList<EAMessage> connectors = new ArrayList<EAMessage>(); // 存储这张图message
			if (diagramsData.getLifelines() != null) {// 直接添加本图有的成员lifelineList
				lifelines.addAll(diagramsData.getLifelines());
			}
			if (diagramsData.getConnectors() != null) {// 直接添加本图有的成员connectorList
				connectors.addAll(diagramsData.getConnectors());
			}
			for (EALifeline lifeline : allLifelines) {// 根据id获得本图的lifeline
				if (diagramsData.getIds().contains(lifeline.getLifelineID())) {
					lifelines.add(lifeline);
				}
			}
			for (EAMessage con : allConnectors) {// 根据id获得本图的消息
				if (diagramsData.getIds().contains(con.getConnectorId())) {
					connectors.add(con);
				}
			}
		}
	}

}

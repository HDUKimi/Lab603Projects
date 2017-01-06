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
	static ArrayList<EALifeline> allLifelines;
	static ArrayList<EAMessage> allConnectors;
	static ArrayList<EADiagramsData> diagramsDatas;
	static EADiagramsData automata_EADiagramData;
	// 自动机
	static ArrayList<UppaalTemPlate> templates;
	static ArrayList<UppaalTransition> transitions;
	static ArrayList<UppaalLocation> locations;
	// 返回RowStringsForDisplay  （其中包含状态的对比list 和消息的对比list）
	public static RowStringsForDisplay compareFromXMLPath(String eaPath, String automataPath) throws Exception {
		RowStringsForDisplay row = new RowStringsForDisplay();
		readEA(eaPath);
		readAutomata(automataPath);
		for(EADiagramsData diagramsData : diagramsDatas) {
			if (diagramsData.getName().equals(templates.get(0).getName())) {
				System.out.println("-------------------------根据时序图中的状态查找Location-------------------------");
				automata_EADiagramData = diagramsData;
				// 找状态 与location对比
				for(EALifeline lifeline : diagramsData.getLifelines()) {
					for(EAStateInfo stateInfo : lifeline.getStateInfos()) {
						
						// 从自动机中找到一个location
						boolean find = false;
						for(UppaalLocation location : locations) {
							if (stateInfo.getName().equals(location.getName().split(":")[0])
									&& (
											stateInfo.getDConst() == null ||
											location.getTimeDurationList().contains(stateInfo.getDConst())
										)
								) 
							{
								StateCompare sc = new StateCompare(stateInfo, location, "ok");
								System.out.println(stateInfo.getName() + "|" + location.getName() + "|" + "ok");
								System.out.println(stateInfo.getDConst() + "\n");
								row.getStateCompareList().add(sc);
								find = true;
								break;
							}
						}
						if (!find) {
							row.getStateCompareList().add(new StateCompare(stateInfo, null, "false"));
							System.out.println(stateInfo.getName() + "|" + "null | false");
						}
						
					}
				}
				System.out.println("-------------------------根据时序图中的消息查找Transition-------------------------");

				// 找message 与transition对比
				for(EAMessage message : diagramsData.getConnectors()) {
					
					// 从自动机中找到一个transition
					boolean find = false;
					for(UppaalTransition transition : transitions) {
						if (message.getName().equals(transition.getName())
								&& ( 
										message.getDuration() == null 
										|| message.getDuration().equals(transition.getTimeDuration()))
								   ) 
						{
							MessageCompare mc = new MessageCompare(message, transition, "ok");
							System.out.println(message.getName() +"|" + transition.getName() +"|"+ "ok");
							System.out.println(message.getDuration() + "\n");
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
	// 返回一个list代表路径上累加时间的过程 如果和时序图的不一致 则返回null
	public static ArrayList<Integer> verificationPathTupleTime(ArrayList<PathTuple> path) {
		System.out.println("-------------------------累加路径时间值验证时间刻度-------------------------");
		System.out.println("初始化时间和");
		ArrayList<Integer> res = new ArrayList<>();
		int timeSum = 0;
		int nextTime = 0;
		
		for(int i = 0; i < path.size() - 1; i++) {
			UppaalLocation location = path.get(i).getLocation();
			UppaalTransition transition = path.get(i).getTransition();
			if (transition.out && transition.getName().contains("?")) {// 不重复计算
				res.add(timeSum);// location的时间为
				res.add(timeSum);// transition的时间   都不变
				continue;
			}
			int LocationCountIndex = 0;
			for(int time : location.getStartTimeList()) {
				if (time == nextTime) {
					break;
				}
				LocationCountIndex++;
			}
			if (LocationCountIndex >= location.getStartTimeList().size()) {
				System.out.println("根据nextTime查找这个location的所有开始时间失败！");
			}
			System.out.println("累加location:" + location.getName() + "的耗时:" + (location.getEndTimeList().get(LocationCountIndex) - location.getStartTimeList().get(LocationCountIndex)));
			timeSum += location.getEndTimeList().get(LocationCountIndex) - location.getStartTimeList().get(LocationCountIndex);
			res.add(timeSum);// Location的时间
			
			System.out.println("累加transition:" + transition.getName() + "的耗时:" + (transition.getEndTime() - transition.getStartTime()));
			timeSum += transition.getEndTime() - transition.getStartTime();
			nextTime = transition.getEndTime();
			res.add(timeSum);// Transition的时间
		}
		
		PathTuple lastPathTuple = path.get(path.size() - 1);
		UppaalLocation lastLocation = lastPathTuple.getLocation();
		int lastStartTime = findTimingDiagramLastStateStartTime();// ea 最后一个状态的开始时间
		System.out.println("EA最后一个状态的开始时间：" + lastStartTime);
		System.out.println("自动机路径累加的时间和:" + timeSum);
		if (lastStartTime == timeSum) {
			return res;
		} else {
			return null;
		}
	}
	
	// 查找时序图中最后一个状态的开始时间（最大）
	private static int findTimingDiagramLastStateStartTime() {
		int max = 0;
		for(EALifeline lifeline : automata_EADiagramData.getLifelines()) {
			for(EAStateInfo stateInfo : lifeline.getStateInfos()) {
				if (Integer.valueOf(stateInfo.getStartTime()) > max) {
					max = Integer.valueOf(stateInfo.getStartTime());
				}
			}
		}	
		return max;
	}

	private static void readAutomata(String automataPath) throws Exception {
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

	private static void readEA(String eaPath) throws Exception {
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
			diagramsData.setLifelines(lifelines);
			diagramsData.setConnectors(connectors);
		}
	}

}

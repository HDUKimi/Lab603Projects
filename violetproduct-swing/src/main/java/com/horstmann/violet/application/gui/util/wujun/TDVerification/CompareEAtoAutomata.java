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
	// �Զ���
	static ArrayList<UppaalTemPlate> templates;
	static ArrayList<UppaalTransition> transitions;
	static ArrayList<UppaalLocation> locations;
	// ����RowStringsForDisplay  �����а���״̬�ĶԱ�list ����Ϣ�ĶԱ�list��
	public static RowStringsForDisplay compareFromXMLPath(String eaPath, String automataPath) throws Exception {
		RowStringsForDisplay row = new RowStringsForDisplay();
		readEA(eaPath);
		readAutomata(automataPath);
		for(EADiagramsData diagramsData : diagramsDatas) {
			if (diagramsData.getName().equals(templates.get(0).getName())) {
				System.out.println("-------------------------����ʱ��ͼ�е�״̬����Location-------------------------");
				automata_EADiagramData = diagramsData;
				// ��״̬ ��location�Ա�
				for(EALifeline lifeline : diagramsData.getLifelines()) {
					for(EAStateInfo stateInfo : lifeline.getStateInfos()) {
						
						// ���Զ������ҵ�һ��location
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
				System.out.println("-------------------------����ʱ��ͼ�е���Ϣ����Transition-------------------------");

				// ��message ��transition�Ա�
				for(EAMessage message : diagramsData.getConnectors()) {
					
					// ���Զ������ҵ�һ��transition
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
	// ����һ��list����·�����ۼ�ʱ��Ĺ��� �����ʱ��ͼ�Ĳ�һ�� �򷵻�null
	public static ArrayList<Integer> verificationPathTupleTime(ArrayList<PathTuple> path) {
		System.out.println("-------------------------�ۼ�·��ʱ��ֵ��֤ʱ��̶�-------------------------");
		System.out.println("��ʼ��ʱ���");
		ArrayList<Integer> res = new ArrayList<>();
		int timeSum = 0;
		int nextTime = 0;
		
		for(int i = 0; i < path.size() - 1; i++) {
			UppaalLocation location = path.get(i).getLocation();
			UppaalTransition transition = path.get(i).getTransition();
			if (transition.out && transition.getName().contains("?")) {// ���ظ�����
				res.add(timeSum);// location��ʱ��Ϊ
				res.add(timeSum);// transition��ʱ��   ������
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
				System.out.println("����nextTime�������location�����п�ʼʱ��ʧ�ܣ�");
			}
			System.out.println("�ۼ�location:" + location.getName() + "�ĺ�ʱ:" + (location.getEndTimeList().get(LocationCountIndex) - location.getStartTimeList().get(LocationCountIndex)));
			timeSum += location.getEndTimeList().get(LocationCountIndex) - location.getStartTimeList().get(LocationCountIndex);
			res.add(timeSum);// Location��ʱ��
			
			System.out.println("�ۼ�transition:" + transition.getName() + "�ĺ�ʱ:" + (transition.getEndTime() - transition.getStartTime()));
			timeSum += transition.getEndTime() - transition.getStartTime();
			nextTime = transition.getEndTime();
			res.add(timeSum);// Transition��ʱ��
		}
		
		PathTuple lastPathTuple = path.get(path.size() - 1);
		UppaalLocation lastLocation = lastPathTuple.getLocation();
		int lastStartTime = findTimingDiagramLastStateStartTime();// ea ���һ��״̬�Ŀ�ʼʱ��
		System.out.println("EA���һ��״̬�Ŀ�ʼʱ�䣺" + lastStartTime);
		System.out.println("�Զ���·���ۼӵ�ʱ���:" + timeSum);
		if (lastStartTime == timeSum) {
			return res;
		} else {
			return null;
		}
	}
	
	// ����ʱ��ͼ�����һ��״̬�Ŀ�ʼʱ�䣨���
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
		// ��ȡ�Զ���.xml �е�����
		SAXReader reader = new SAXReader();// ��ȡ������
		Document dom = reader.read(automataPath);// ����XML��ȡ���������ĵ���dom����
		Element root = dom.getRootElement();// ��ȡ���ڵ�

		ReadAutomata uppaal = new ReadAutomata();
		uppaal.load(root);

		templates = uppaal.getUppaalTemplates();
		transitions = templates.get(0).getTransitions();
		locations = templates.get(0).getLocations();
	}

	private static void readEA(String eaPath) throws Exception {
		// ��ȡea.xml�е�����-------------------------------------------------------------------------------------------
		SAXReader reader = new SAXReader();// ��ȡ������

		Document dom = reader.read(eaPath);// ����XML��ȡ���������ĵ���dom����

		Element root = dom.getRootElement();// ��ȡ���ڵ�
		
		ReadTimingDiagram uml = new ReadTimingDiagram();
		uml.load(root);
		if (uml.hasNoLifeline()) {
			System.exit(0);
			Display.println("û���ҵ������ߣ��˳�");
		}

		allLifelines = uml.getTimingLine();
		allConnectors = uml.getConnector();
		diagramsDatas = uml.getUmlAllDiagramData();
		
		for (EADiagramsData diagramsData : diagramsDatas) {// ����һ��ͼ
			ArrayList<EALifeline> lifelines = new ArrayList<EALifeline>(); // �洢����ͼlifeline
			ArrayList<EAMessage> connectors = new ArrayList<EAMessage>(); // �洢����ͼmessage
			if (diagramsData.getLifelines() != null) {// ֱ����ӱ�ͼ�еĳ�ԱlifelineList
				lifelines.addAll(diagramsData.getLifelines());
			}
			if (diagramsData.getConnectors() != null) {// ֱ����ӱ�ͼ�еĳ�ԱconnectorList
				connectors.addAll(diagramsData.getConnectors());
			}
			for (EALifeline lifeline : allLifelines) {// ����id��ñ�ͼ��lifeline
				if (diagramsData.getIds().contains(lifeline.getLifelineID())) {
					lifelines.add(lifeline);
				}
			}
			for (EAMessage con : allConnectors) {// ����id��ñ�ͼ����Ϣ
				if (diagramsData.getIds().contains(con.getConnectorId())) {
					connectors.add(con);
				}
			}
			diagramsData.setLifelines(lifelines);
			diagramsData.setConnectors(connectors);
		}
	}

}

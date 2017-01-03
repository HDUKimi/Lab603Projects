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
				System.out.println("-------find location:");
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
				System.out.println("\n\n-----find transition");
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

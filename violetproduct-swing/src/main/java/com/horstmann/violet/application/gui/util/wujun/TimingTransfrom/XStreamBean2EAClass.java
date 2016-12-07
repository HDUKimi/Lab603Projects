package com.horstmann.violet.application.gui.util.wujun.TimingTransfrom;

import java.util.ArrayList;

import com.horstmann.violet.application.gui.util.wujun.TimingTransfrom.EADiagramsData;
import com.horstmann.violet.application.gui.util.wujun.TimingTransfrom.EALifeline;
import com.horstmann.violet.application.gui.util.wujun.TimingTransfrom.EAMessage;
import com.horstmann.violet.application.gui.util.wujun.TimingTransfrom.EAStateInfo;
import com.horstmann.violet.application.gui.util.wujun.TimingTransfrom.Lifeline;
import com.horstmann.violet.application.gui.util.wujun.TimingTransfrom.LifelineState;
import com.horstmann.violet.application.gui.util.wujun.TimingTransfrom.SendMessageEdge;
import com.horstmann.violet.application.gui.util.wujun.TimingTransfrom.TimingDiagramGraph;

public class XStreamBean2EAClass {
	public static EADiagramsData fromTimingDiagramGraph(TimingDiagramGraph td, String name) {
		
		EADiagramsData eaDiagramsData = new EADiagramsData();
		
		setDiagramData(eaDiagramsData, td, name);
		
		setLifelines(eaDiagramsData, td);
		
		setMessages(eaDiagramsData, td);
		
		return eaDiagramsData;
	}

	private static void setMessages(EADiagramsData eaDiagramsData, TimingDiagramGraph td) {
		ArrayList<EAMessage> resMessageArray = new ArrayList<>();
		for(SendMessageEdge message : td.getEdges().getSendMessageEdge()) {
			EAMessage eaMessage = new EAMessage();
			eaMessage.setConnectorId(message.getId());
			eaMessage.setDuration(message.getTimeConstraint());
			eaMessage.setName(message.getName());
			eaMessage.setSourceId(message.getStart().fromLifelineId);
			eaMessage.setTragetId(message.getEnd().toLifelineId);
			eaMessage.setSendTime(message.getStarttimePoint());
			eaMessage.setReceiveTime(message.getEndtimePoint());
			String sourceLifelineId = eaMessage.getSourceId();
			String targetLifelineId = eaMessage.getTragetId();
			for(EALifeline lifeline : eaDiagramsData.getLifelines()) {
				String lifelineId = lifeline.getLifelineID();
				if (lifelineId.equals(sourceLifelineId)) {
					eaMessage.setSourceName(lifeline.getName());
				}
				if (lifelineId.equals(targetLifelineId)) {
					eaMessage.setTragetName(lifeline.getName());
				}
			}
			resMessageArray.add(eaMessage);
		}
		eaDiagramsData.setConnectors(resMessageArray);
	}

	private static void setLifelines(EADiagramsData eaDiagramsData, TimingDiagramGraph td) {
		ArrayList<EALifeline> reslifelineArray = new ArrayList<>();
		for(Lifeline lifeline : td.getNodes().getLiflines()) {
			EALifeline eaLifeline = new EALifeline();
			eaLifeline.setLifelineID(lifeline.getId());
			eaLifeline.setName(lifeline.getName().text);
			// 设置lifelin 的所有状态
			ArrayList<EAStateInfo> lifelineStates = new ArrayList<>();
			for(LifelineState state: lifeline.getSchildren().horizontalchild.states) {
				EAStateInfo eaStateInfo = new EAStateInfo();
				eaStateInfo.setName(state.getName());
				eaStateInfo.setDConst(state.getTimeConstraint());
				eaStateInfo.setStartTime(state.getStartPointTime());
				lifelineStates.add(eaStateInfo);
			}
			eaLifeline.setStateInfos(lifelineStates);
			
			reslifelineArray.add(eaLifeline);
		}
		eaDiagramsData.setLifelines(reslifelineArray);
	}

	private static void setDiagramData(EADiagramsData eaDiagramsData, TimingDiagramGraph td, String name) {
		eaDiagramsData.setName(name);
		eaDiagramsData.setDiagramID(td.getId());
	}
}

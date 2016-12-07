package com.horstmann.violet.application.gui.util.wujun.SD2AutomataForPlatform.util;

import java.awt.geom.Line2D.Double;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import com.horstmann.violet.application.gui.util.wujun.SequenceTransfrom.FixFragmentTool;
import com.horstmann.violet.application.gui.util.wujun.SequenceTransfrom.REF;
import com.horstmann.violet.application.gui.util.wujun.SequenceTransfrom.WJDiagramsData;
import com.horstmann.violet.application.gui.util.wujun.SequenceTransfrom.WJFragment;
import com.horstmann.violet.application.gui.util.wujun.SequenceTransfrom.WJLifeline;
import com.horstmann.violet.application.gui.util.wujun.SequenceTransfrom.WJMessage;
import com.horstmann.violet.application.gui.util.wujun.SequenceTransfrom.WJRectangle;


public class PlatformBean2WJclass {

	
	static SequenceDiagramGraph mPlatformSD;
	
	// 从平台导出的xml --xstream--> xsPlatformBean --这个方法完成这个功能--> WJDiagramsData
	public static WJDiagramsData transform(SequenceDiagramGraph sd0, String name) {
		
		mPlatformSD = sd0;
		
		WJDiagramsData WJSD = new WJDiagramsData();
		
		initDiagramData(WJSD, name);
		
		setLifeline(WJSD);
		
		setFragment(WJSD);
		
		setMessage(WJSD);
		
		setRefIndex(WJSD);
		
		return WJSD;
	}

	

	private static void setRefIndex(WJDiagramsData WJSD) {
		for(REF ref : WJSD.getRefArray()) {
			ref.setIndex(FixFragmentTool.refIndexInDiagram(ref, WJSD));			
		}
	}

	private static void setMessage(WJDiagramsData WJSD) {
//	V	String inFragId="null";//在哪个片段中
//	V	String inFragName="null"; // 组合片段的名称 opt break...
//	V	String id="null";   // 消息的id
//	V	String sourceId="null"; // 激活点id
//	V	String tragetId="null"; // 激活点id
//	V	String fromId;   //从哪个对象到哪个对象
//	V	String toId;	//从哪个对象到哪个对象
//	X?	String name="null"; // 消息的名字	
//	X	String inString; // 输入
//	X	String outString;// 输出
//  X   String SEQTC;// 消息上的时间
		
		ArrayList<WJFragment> fragments = WJSD.getFragmentArray();
		ArrayList<WJLifeline> lifelines = WJSD.getLifelineArray();
		for(CallEdge callEdge : mPlatformSD.getEdges().getCallEdges()) {
			WJMessage message = new WJMessage();
			
			message.setConnectorId(callEdge.getEAID());// 消息的id
			//message.setName(callEdge.getParameterStringValue());// ?????
			message.setSourceId(callEdge.getStart().fromNodeId);// 激活点id
			message.setTargetId(callEdge.getEnd().toNodeId);// 激活点id
			message.setName(callEdge.getName());// 消息的名字
			message.setInString(callEdge.getInput());// 输入
			message.setOutString(callEdge.getOutput());// 输出
			message.setSEQTC(callEdge.getTiming());// 时间约束
			message.setPointY(callEdge.getStartLocation().y);
			for(WJLifeline lifeline : lifelines) { // 找到 从哪个对象 到哪个对象
				if (lifeline.getActivationIDs().contains(message.getSourceId())) {
					message.setFromId(lifeline.getLifeLineId());
				}
				if (lifeline.getActivationIDs().contains(message.getTargetId())) {
					message.setToId(lifeline.getLifeLineId());
				}
			}
			if (mPlatformSD.getEdges().getTimeEdges() != null) {
				for(TimeEdge timeEdge : mPlatformSD.getEdges().getTimeEdges()) {
					if (callEdge.getId().equals(timeEdge.getEndEdge().toMessageId)) {
						message.setSEQDO(timeEdge.getTimeStringValue());
					}
				}
			}
			
			
			// 求包含这个消息并且 面积最小的fragment
			WJFragment theMinAreaContainsThisMessage = new WJFragment();
			double minArea = java.lang.Double.MAX_VALUE;
			for(WJFragment fragment : fragments) {
				if (fragment.getContainsMessageIDs().contains(message.getConnectorId())) {
					WJRectangle rectangle = fragment.getRectangle();
					double area = (rectangle.getRight() - rectangle.getLeft()) *
							(rectangle.getBottom() - rectangle.getTop());
					if (area < minArea) {
						minArea = area;
						theMinAreaContainsThisMessage = fragment;
					}
				}
			}
			// 设置 inFrag信息
			message.setInFragId(theMinAreaContainsThisMessage.getFragId());
			message.setInFragName(theMinAreaContainsThisMessage.getFragType());
			
			
			WJSD.getMessageArray().add(message);
		}
		
	}

	private static void setFragment(WJDiagramsData WJSD) {
		if (mPlatformSD.getNodes().getFragments() == null) {
			return ;
		}
		ArrayList<WJFragment> resFragmentList = new ArrayList<WJFragment>();
		
		// 设置reference 的类型 得到实际的Type Name
		HashMap<String, String> referenceType = new HashMap<String, String>();
		// 映射引用id 到 typeName
		for(CombinedFragment combinedFragment : mPlatformSD.getNodes().getFragments()) {
			if (combinedFragment.getType().name != null) { 
				referenceType.put(combinedFragment.getType().id, combinedFragment.getType().name);
			}
		}
		// 得到引用的type类型 
		for(CombinedFragment combinedFragment : mPlatformSD.getNodes().getFragments()) {
			if (combinedFragment.getType().name == null) { 
				combinedFragment.getType().name = referenceType.get(combinedFragment.getType().reference);
			}
			// 转为小写
			combinedFragment.getType().name = combinedFragment.getType().name.toLowerCase();
		}
		
		// 正式读取信息
		for(CombinedFragment combinedFragment : mPlatformSD.getNodes().getFragments()) {
			// 所有的条件
			List<String> conditions = combinedFragment.getConditions().strings;
			String fragmentEAID = combinedFragment.getEAID();
			String typeName = combinedFragment.getType().name;
			boolean needComId = typeName.equals("alt") || typeName.equals("par");
			int index = 0;
			for(FragmentPart part : combinedFragment.getFragmentParts().fragmentParts) {
				WJFragment fragment = new WJFragment();
				fragment.setFragType(typeName);
				fragment.setFragId(fragmentEAID + "-" + index);
				fragment.setFragCondition(conditions.get(index++));
//				fragment.getContainsMessageIDs().addAll(part.getMessagesID().messageIDs); 
				// 平台有bug不能直接读取 ， 有可能为空 会崩溃
				// 需要 自己判断消息的开始location坐标 ，在不在fragment里面 ，还需要要求一个最小的fragment（在setMessage中实现）
				fragment.setRectangle(getRectangleByXYAndWH(part, combinedFragment.getWidth(), combinedFragment.getHeight()));
				fragment.getContainsMessageIDs().addAll(getMessageIDsWitchInTheFragment(
						mPlatformSD.getEdges().getCallEdges(), fragment));
				
				if (needComId) { // alt par 需要comId
					fragment.setComId(fragmentEAID);
				}
				if (fragment.getFragType().equals("ref")) {
					REF ref = new REF();
					ref.setDiagramName(fragment.getFragCondition());
					ref.setRefID(fragment.getFragCondition());
					ref.setRectangle(fragment.getRectangle());
					ref.setInFragID("null");
					ref.setInFragName("SD");
					//ref.setIndex(FixFragmentTool.refIndexInDiagram(ref, WJSD));
					WJSD.getRefArray().add(ref);
				} else {
					resFragmentList.add(fragment);
				}
				
			}
		}
		
		WJSD.setFragmentArray(resFragmentList);
		// fix bigId
		FixFragmentTool.fixFragmentsOfOneDiagram(WJSD);
		
	}

//  那些 消息 在这个fragment中
	private static Collection<? extends String> getMessageIDsWitchInTheFragment(List<CallEdge> callEdges,
			WJFragment fragment) {
		ArrayList<String> resMessageIds = new ArrayList<String>();
		WJRectangle rectangle = fragment.getRectangle();
		
		for(CallEdge callEdge : callEdges) {
			double x = callEdge.getStartLocation().x;
			double y = callEdge.getStartLocation().y;
			if (x > rectangle.getLeft() && x < rectangle.getRight()
					&& y > rectangle.getTop() && y < rectangle.getBottom()) {// 说明fragment 包含这个calledge
				resMessageIds.add(callEdge.getEAID());
			}
		}
		return resMessageIds;
	}

	// 计算操作域的WJrectangle
	private static WJRectangle getRectangleByXYAndWH(FragmentPart part, double width, double height) {
		Double upLine = part.getLine2DLine();
		double partHeight = java.lang.Double.valueOf(part.getHeight());
		double left = upLine.getX1();
		double right = upLine.getX2();
		double top = upLine.getY1();
		double bottom = top + partHeight;
		WJRectangle resRectangle = new WJRectangle(left, top, right, bottom);
		return resRectangle;
	}
	
	// 广度优先遍历 activationBarNode 得到这个lifeline包含的所有激活点id
	private static ArrayList<String> getActivationIDs(LifelineNode lifelineNode) {
		ArrayList<String> resIDs = new ArrayList<String>();
		Children c = lifelineNode.getC();
		
		Queue<ActivationBarNode> queue = new LinkedList<ActivationBarNode>();
		if (lifelineNode.getC().getNodes() == null) {
			return resIDs;
		}
		// 进第一层的activationBarNode
		for(ActivationBarNode node : lifelineNode.getC().getNodes()) {
			queue.offer(node);
		}
		//　BFS
		while(!queue.isEmpty()) {
			ActivationBarNode nodeFather = queue.poll();
			resIDs.add(nodeFather.getId());
			
			Children cFather = nodeFather.getC();
			if (cFather.getNodes() != null) {
				for(ActivationBarNode nodeChild : cFather.getNodes()) {
					queue.offer(nodeChild);
				}
			}
			
		}
		return resIDs;
	}
	
	private static void setLifeline(WJDiagramsData WJSD) {
		for (LifelineNode lifelineNode : mPlatformSD.getNodes().getLiflines()) {
			WJLifeline lifeline = new WJLifeline();
			WJSD.getIds().add(lifelineNode.getEAID());
			lifeline.setLifeLineId(lifelineNode.getEAID());
			lifeline.setlifeLineName(lifelineNode.getName().text);
			lifeline.setActivationIDs(getActivationIDs(lifelineNode));
			WJSD.getLifelineArray().add(lifeline);
		}
	}



	private static void initDiagramData(WJDiagramsData WJSD, String name) {
		
		ArrayList <String> ids = new ArrayList<String>(); // 所有消息，组合片段，生命线的EAID
		ArrayList <WJLifeline> lifelineArray = new ArrayList <WJLifeline>();
		ArrayList <WJFragment> fragmentArray = new ArrayList <WJFragment>();
		ArrayList <WJMessage> messageArray = new ArrayList <WJMessage>();
		
		// 首先是sd的基本信息
		WJSD.setName(name);
		WJSD.setIds(ids);
		WJSD.setDiagramID(mPlatformSD.getId());
		WJSD.setLifelineArray(lifelineArray);
		WJSD.setMessageArray(messageArray);
		WJSD.setFragmentArray(fragmentArray);
	}
	
}

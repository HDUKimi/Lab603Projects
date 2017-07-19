package com.horstmann.violet.application.gui.util.wujun.TimingTransfrom;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.horstmann.violet.application.gui.MainFrame;
import com.horstmann.violet.application.gui.util.wujun.TimingTransfrom.Display;
import com.horstmann.violet.application.gui.util.wujun.TimingTransfrom.TimingDiagramGraph;
import com.horstmann.violet.application.gui.util.wujun.TimingTransfrom.XML2xmlbeanUtil;
import com.horstmann.violet.application.gui.util.wujun.TimingTransfrom.XStreamBean2EAClass;

public class TimingEAtoUppaal {

	private static int state=0;
	private static MainFrame mainFrame;
	public static String diagramDataName = null;

	public static void transEA(String filename,String path,MainFrame mainframe,int state) throws Exception {
		// TODO Auto-generated method stub
		// Global global=new Global();

		TimingEAtoUppaal.state=state;
		mainFrame=mainframe;
		
		// 1.声明变量------------------------------------------------------------------------------------------------------
		HashSet<String> template_instantiations = new HashSet<String>(); // 存储template的name
		HashSet<String> global_declarations = new HashSet<String>(); // 存储channel的name
		ArrayList<UppaalTemPlate> temPlates = new ArrayList<UppaalTemPlate>(); // 存储所有template
		ArrayList<EALifeline> allLifelines = new ArrayList<EALifeline>(); // 存储所有lifeline
		ArrayList<EAMessage> allConnectors = new ArrayList<EAMessage>(); // 存储所有message
		ArrayList<EADiagramsData> diagramsDatas = new ArrayList<EADiagramsData>();
		UppaalLocation FL = new UppaalLocation(); // 存储上一个location
		int id = 0; // location的ID计数器
		// 1.声明变量end---------------------------------------------------------------------------------------------------

		Display.println("================================正在读取时序图xml信息================================");
		// 2.读取uml.xml中的数据-------------------------------------------------------------------------------------------
		SAXReader reader = new SAXReader();// 获取解析器
System.out.println(path);
File file = new File(path);
Document dom= reader.read(file);// 解析XML获取代表整个文档的dom对象
System.out.println("--------"+path);
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
		// ---------------------------------------------------------------------*

		String name = "";
		// *** 读取平台xml
		// String path = "coffeeAndTea.timing.violet.xml";
		// name = path.split(".timing.violet.xml")[0];
		// TimingDiagramGraph td0 = XML2xmlbeanUtil.getTd(new
		// File("coffeeAndTea.timing.violet.xml"));
		// EADiagramsData eaDiagramsData =
		// XStreamBean2EAClass.fromTimingDiagramGraph(td0, name);
		// diagramsDatas.add(eaDiagramsData);
		// *** end

		for (EADiagramsData diagramsData : diagramsDatas) {// 遍历一张图
			Display.println("===>  图名为:" + diagramsData.getName() + "\n");
			Display.println("初始化数据...");
			template_instantiations = new HashSet<String>(); // 存储template的name
			global_declarations = new HashSet<String>(); // 存储channel的name
			temPlates = new ArrayList<UppaalTemPlate>(); // 存储所有template
			FL = new UppaalLocation(); // 存储上一个location

			ArrayList<EALifeline> Lifelines = new ArrayList<EALifeline>(); // 存储这张图lifeline
			ArrayList<EAMessage> Connectors = new ArrayList<EAMessage>(); // 存储这张图message
			if (diagramsData.getLifelines() != null) {// 直接添加本图有的成员lifelineList
				Lifelines.addAll(diagramsData.getLifelines());
			}
			if (diagramsData.getConnectors() != null) {// 直接添加本图有的成员connectorList
				Connectors.addAll(diagramsData.getConnectors());
			}
			for (EALifeline lifeline : allLifelines) {// 根据id获得本图的lifeline
				if (diagramsData.ids.contains(lifeline.LifelineID)) {
					Lifelines.add(lifeline);
				}
			}

			for (EAMessage con : allConnectors) {// 根据id获得本图的消息
				if (diagramsData.ids.contains(con.connectorId)) {
					Connectors.add(con);
				}
			}

			/* 对message的优化查找 */
			HashMap<String, EAMessage> sourceIDsendTime_connector = new HashMap<String, EAMessage>(); // 用于查找FL是否是消息发送方
			HashMap<String, EAMessage> tragertIDreceiveTime_connector = new HashMap<String, EAMessage>();// 用于查找FL是否是消息接受方

			Iterator<EAMessage> cIterator = Connectors.iterator(); // 遍历所有消息
																	// 构建sourceIDsendTime_connector
																	// 和tragertIDreceiveTime_connector
			while (cIterator.hasNext()) {
				EAMessage temp = cIterator.next();
				sourceIDsendTime_connector.put("#sourceIdIs" + temp.getSourceId() + "#sendTimeIs" + temp.getSendTime(),
						temp);
				tragertIDreceiveTime_connector
						.put("#tragetIdIs" + temp.getTragetId() + "#receiveTimeIs" + temp.getReceiveTime(), temp);
			}
			// 2.读取uml.xml中的数据end----------------------------------------------------------------------------------------

			// EX 存储 消息（不是转折点消息， 从某个状态发出 ，但这个状态没有发生改变）
			ArrayList<EAMessage> EXconnectorsSender = new ArrayList<EAMessage>();
			EXconnectorsSender.addAll(Connectors);

			// EX 存储 消息（不是转折点消息， 由某个状态发接受 ，但这个状态没有发生改变）
			// ..和上一个list合并可以得到需要添加的消息（接受或者发送状态没有发生改变）
			ArrayList<EAMessage> EXconnectorsReciever = new ArrayList<EAMessage>();

			EXconnectorsReciever.addAll(Connectors);
			// 3.遍历所有生命线--------------------------------------------------------------------------------------------------
			// 首先对lifelines 排序 按照第一个状态的开始时间
			Collections.sort(Lifelines, new Comparator<EALifeline>() {

				@Override
				public int compare(EALifeline o1, EALifeline o2) {
					int t1 = Integer.valueOf(o1.getStateInfos().get(0).getStartTime());
					int t2 = Integer.valueOf(o2.getStateInfos().get(0).getStartTime());
					return t1 - t2;
				}
			});
			Iterator<EALifeline> lineIterator = Lifelines.iterator();

			// 记录状态最大时间-> 终状
			int maxTime = 0;
			UppaalLocation lastLocation = new UppaalLocation();

			while (lineIterator.hasNext()) {
				// id = 0;
				// ArrayList<String> stateNameArrayList=new ArrayList<String>();
				EALifeline line = lineIterator.next();
				Iterator<EAStateInfo> lineState = line.getStateInfos().iterator();
				template_instantiations.add(line.getName());// 第4步:在UPRAAL中声明一个Template取名为ID
				UppaalTemPlate template = new UppaalTemPlate();
				template.setName(line.getName());
				template.id = line.getLifelineID();
				Display.println("\n-------------------------获取生命线信息-------------------------");
				Display.println("--->  检测名为" + line.getName() + "的生命线\n");

				/* 对当前template 旧location和旧transition优化查找 */
				HashMap<String, UppaalLocation> name_oldLocations = new HashMap<String, UppaalLocation>();// 查找是否是当前template的旧状态
				HashMap<String, UppaalTransition> nameSnameT_oldTransition = new HashMap<String, UppaalTransition>();// 查找是否是当前template的旧状态转移
				Display.println("获取" + line.getName() + "上的状态");

				// 3.1对其中一个lifeline的state进行遍历--------------------------------------------------------------------------
				while (lineState.hasNext()) { // 第5步:若State or Condition
												// S不为空转第6步,否则转第2步。

					EAStateInfo State = lineState.next();
					UppaalLocation location = new UppaalLocation();
					location.startTimeList.add(Integer.valueOf(State.getStartTime()));// 添加状态所在的开始时间
					FL.endTimeList.add(Integer.valueOf(State.getStartTime()));
					if (Integer.valueOf(State.getStartTime()) > maxTime) { // 记录最大时间
																			// 的location
						maxTime = Integer.valueOf(State.getStartTime());
						lastLocation = location;
					}
					UppaalTransition transition = new UppaalTransition();
					location.setLineEAID(line.getLifelineID());
					location.setName(State.getName() + ":" + line.getName());
					location.setStartTime(State.getStartTime());
					Display.println("\n下一个状态：" + location.getName());

					String print_transition_name = new String(); // 用于测试
																	// 记录状态转移的name
					// 3.1.1初始状态
					if (State.startTime.equals("0")) // 第6步:若S是第一个State or
														// Condition,转第7步,否则转第9步。
					{
						Display.println("获得初始状态 " + State.name);

						location.setInit("true"); // 第7步:在UPRAAL中声明一个initial
													// Location
													// B,转第8步。
						location.setInvariant(null); // 第8步:设置B的Invariant为空,转第28步
						location.setId(id++);
						location.setInvariant(State.DConst);
						Display.println("将location--" + location.getName() + "添加到template中");
						template.locations.add(location);
						name_oldLocations.put(location.Name, location);

						FL = location;
					} else {
						// 3.1.2非初始状态
						// 3.1.2.1非初始状态 是新状态
						// if (!name_oldLocations.containsKey(location.Name)) //
						// 第9步:若S是一个新的State
						// or
						if (!name_oldLocations.containsKey(location.Name)) // Condition转第11步,
						{// 新的State
							Display.println("新状态：" + State.name);
							location.setId(id++); // 第11步:在UPPAAL中声明一个Location
													// B并声明一个迁移FL→B,转第12步。
							location.setLineEAID(line.getLifelineID());
							// Display.println();

							transition.setSourceId(FL.getId());
							transition.setTargetId(location.getId());
							transition.setNameS(FL.getName());
							transition.setNameT(location.getName());
							transition.setStartTime(State.getStartTime());
							FL.setEndTime(State.getStartTime());
							String c = new String(); // c记录持续约束的情况
							if (State.DConst == null)
								c = "null";
							else
								c = State.DConst;
							// 3.1.2.1.1非初始状态 是新状态 持续约束三种不同的情况
							switch (c) {
							case "null":
								// 第19步:将B设置为normal
								// Location,转第20步。
								location.setInvariant(null); // 第20步:设置B的Invariant为空,转第21步。
								location.timeDurationList.add("null");
								template.locations.add(location);
								name_oldLocations.put(location.Name, location);

								Display.println("持续约束为null");
								Display.println("将location:" + location.getName() + "添加到template中");
								break;

							case "0":
								location.setType(1);
								Display.println("紧迫位置：" + State.getName()); // 第17步:将B设置为urgent
																			// Location
								location.setInvariant(null); // 第18步:设置B的Invariant为空,转第21步
								location.timeDurationList.add("0");
								template.locations.add(location);
								name_oldLocations.put(location.Name, location);

								Display.println("持续约束为0");
								Display.println("将location:" + location.getName() + "添加到template中");
								break;

							default:// t..t+n
								Display.println("invariant==" + c);
								location.setInvariant(c);
								location.timeDurationList.add(c);
								// int temp = Index(transition.Kind);
								// transition.Kind[temp] = "assignment";
								// transition.nameText[temp] = "t=0";

								template.locations.add(location);
								name_oldLocations.put(location.Name, location);

								Display.println("持续约束为: " + State.DConst);
								Display.println("将location:" + location.getName() + "添加到template中");
								break;

							}

						} else {
							// 3.1.2.2非初始状态 是旧状态
							Display.println("旧状态：" + State.getName());
							if (lastLocation == location) {
								lastLocation = name_oldLocations.get(location.Name);
							}
							location = name_oldLocations.get(location.Name);

							location.startTimeList.add(Integer.valueOf(State.getStartTime()));// 添加状态所在的开始时间
							location.timeDurationList.add(State.DConst);
							transition.setSourceId(FL.getId());
							transition.setTargetId(location.getId());
							transition.setNameS(FL.getName());
							transition.setNameT(location.getName());
							transition.setStartTime(State.getStartTime());
							

						}
						// 3.1.2得到该状态的迁移事件Event
						if (State.getEvent() != null) // 第21步:若S的Event或TimeConstraint!=NULL转第22步,否则转第23步。
						{
							int temp = Index(transition.Kind);
							transition.Kind[temp] = "guard";
							transition.nameText[temp] = State.getEvent(); // 第22步:设置FL→B的Guard域为Event&TimeConstraint,转第23步。

						}

						// 3.1.3判断FL（上一个状态）是否是massage的发送方

						String SendKeyString = "#sourceIdIs" + FL.getLineEAID() + "#sendTimeIs" + State.getStartTime();
						if (sourceIDsendTime_connector.containsKey(SendKeyString)) // 第23步:如果FL是一个消息message的发送者,转第24步,否则转第26步。
						{
							EAMessage eamessage = sourceIDsendTime_connector.get(SendKeyString);
							String sender_name = eamessage.getName();
							String EAid = eamessage.getConnectorId();
							print_transition_name = sender_name;
							global_declarations.add(sender_name);
							Display.println("消息名为：" + sender_name); // 第24步:在UPPAAL中声明一个chan
																	// message,转第25步。

							int temp = Index(transition.Kind);
							// transition.setInner(sourceIDsendTime_connector.get(SendKeyString).getInner());
							transition.Kind[temp] = "synchronisation";
							transition.nameText[temp] = sender_name + "!"; // 第25步:设置FL→B的Sync域为message!,转第28步。
							// Display.println(transition.nameText[temp]);

							transition.setStartTime(State.getStartTime());// 为了模型验证增加的发送时间记录
							transition.setEndTime(eamessage.getReceiveTime());
							transition.setEAid(EAid);
							transition.setFromName(eamessage.getSourceName());
							transition.setToName(eamessage.getTragetName());
							transition.setDuration(eamessage.getDuration());
							EXconnectorsSender.remove(eamessage);
						}
						// 3.1.4判断FL（上一个状态）是否是massage的接受方
						String ReceiveKeyString = "#tragetIdIs" + FL.getLineEAID() + "#receiveTimeIs"
								+ State.getStartTime();
						if (tragertIDreceiveTime_connector.containsKey(ReceiveKeyString)) // 第26步:如果FL是一个消息message的接收者,转第27步,否则
						// 转第28步。
						{
							EAMessage eamessage = tragertIDreceiveTime_connector.get(ReceiveKeyString);
							String receiver_name = eamessage.getName();
							String EAid = eamessage.getConnectorId();
							print_transition_name = receiver_name;
							global_declarations.add(receiver_name); // 第24步:在UPPAAL中声明一个chan
							Display.println("消息名为：" + receiver_name); // message,转第25步。

							int temp = Index(transition.Kind);
							// transition.setInner(eamessage.getInner());
							transition.Kind[temp] = "synchronisation";
							transition.nameText[temp] = receiver_name + "?"; // 第27步:设置FL→B的Sync域为message?,转第28步
							transition.setStartTime(State.getStartTime());// 为了模型验证增加的发送时间记录
							transition.setEndTime(eamessage.getReceiveTime());				
							transition.setEAid(EAid);
							transition.setFromName(eamessage.getSourceName());
							transition.setToName(eamessage.getTragetName());
							transition.setDuration(eamessage.getDuration());
							EXconnectorsReciever.remove(eamessage);
						}
						// ↓ 不管重复 不重复 都添加
						template.transitions.add(transition);
						Display.println("添加transition： (" + FL.Name + ")--" + print_transition_name + "-->("
								+ location.Name + ")");

						// ↑ 不重复 才添加
						// 3.1.5 判断是否是已经存在的迁移
						// String
						// STKeyString="#nameSIs"+transition.nameS+"#nameTIs"+transition.nameT;
						//
						// if (transition.getNameText().equals("CoffeeNext!")) {
						// Display.println(1);
						// }
						//
						// if(!nameSnameT_oldTransition.containsKey(STKeyString))//无key
						// 说明不存在nameS->nameT的迁移
						// {
						// template.transitions.add(transition);
						// nameSnameT_oldTransition.put(STKeyString,
						// transition);
						// Display.println("添加"+FL.Name+"--"+print_transition_name+"("+State.getEvent()+")"+"->"+location.Name);
						// }
						// else
						// {
						// Display.println("已经存在"+FL.Name+"--"+print_transition_name+"("+State.getEvent()+")"+"->"+location.Name);
						// }

						FL = location;
					}

				}
				// 3.1对其中一个lifeline的state进行遍历end-----------------------------------------------------------------------

				lastLocation.setFinl("true");
				temPlates.add(template);

			}
			// 3.遍历所有生命线end-----------------------------------------------------------------------------------------------

			// ex.1添加移除后剩下的消息（不是途中的转折点消息， 从某个状态发出 ，但这个状态没有发生改变）
			HashSet<EAMessage> EXconnectors = new HashSet<EAMessage>();
			EXconnectors.addAll(EXconnectorsReciever);
			EXconnectors.addAll(EXconnectorsSender);
			ArrayList<UppaalTransition> EXTransitions = new ArrayList<UppaalTransition>();
			addEXconnectors(EXconnectors, temPlates, EXTransitions);// connector->transition

			Display.println("\n-------------------------合并template-------------------------");
			// 合并templates
			mergeTemplates(temPlates);
			// 处理消息的夸对象
			outMessageConnectAutomatas(temPlates.get(0));
			// ex.2 添加剩下的消息
			temPlates.get(0).getTransitions().addAll(EXTransitions);

			
			//　将endTime最小的初始状态作为真正的初始状态
			UppaalLocation initLocation = null;
			UppaalLocation finalLocation = null;
			int minEnd = Integer.MAX_VALUE;
			int maxStart = 0;
			for(UppaalLocation location : temPlates.get(0).getLocations()) {
				if (location.getInit().equals("true")) {
					location.setInit("false");
					if (location.getEndTimeList().get(0) < minEnd) {
						minEnd = location.getEndTimeList().get(0);
						initLocation = location;
					}
					if (location.getStartTimeList().get(location.getStartTimeList().size() - 1) > maxStart) {
						finalLocation = location;
					}
				}
			}
			initLocation.setInit("true");
			finalLocation.setFinl("true");
			
			temPlates.get(0).setName(diagramsData.getName());
			
			String baseurl="D:\\ModelDriverProjectFile\\WJXML\\"+filename+"\\";
		    File basefile=new File(baseurl);
		    if(!basefile.exists()){
		    	while(!basefile.mkdirs()){
		    	
		    	}
		    }
			
			
			Display.println("开始写入xml:" + diagramsData.getName() + ".xml");
			// 4.写入到UPPAAL.xml中----------------------------------------------------------------------------------------------
			Write.creatXML(baseurl+diagramsData.getName() + ".xml", global_declarations, template_instantiations, temPlates);
			Write2.creatXML(baseurl+diagramsData.getName() + "UPPAAL.xml", global_declarations, template_instantiations, temPlates);
			setDiagramDataName(diagramsData.getName());
			// 4.写入到UPPAAL.xml中end-------------------------------------------------------------------------------------------
			Display.println(".....写入完成!");
			Display.println("================================转换完成================================");
		} // 一张图完毕
	}
	// 添加不同对象的自动机 的连接  connecter
	private static void addEXconnectors(HashSet<EAMessage> eXconnectors, ArrayList<UppaalTemPlate> temPlates,
			ArrayList<UppaalTransition> eXTransitions) {

		for (EAMessage Connector : eXconnectors) {

			String sourceId = Connector.getSourceId();
			UppaalTemPlate sourceTemplate = new UppaalTemPlate();
			String targetId = Connector.getTragetId();
			UppaalTemPlate targetTemplate = new UppaalTemPlate();
			int sendTime = Integer.valueOf(Connector.getSendTime());
			int recieveTime = Integer.valueOf(Connector.getReceiveTime());

			UppaalTransition transition = new UppaalTransition();

			// 找到对应的template
			for (UppaalTemPlate template : temPlates) {
				if (template.id.equals(sourceId)) {
					sourceTemplate = template;
				} else if (template.id.equals(targetId)) {
					targetTemplate = template;
				}
			}

			transition.setFromName(sourceTemplate.getName());
			transition.setToName(targetTemplate.getName());
			transition.setKind(new String[] { "synchronisation", null, null, null, null });
			transition.setNameText(
					new String[] { Connector.getName() + "|" + Connector.getDuration(), null, null, null, null });
			transition.setStartTime(Connector.getSendTime());
			transition.setEndTime(Connector.getReceiveTime());
			// 从sourceTemplate中找到sourceLocation
			int maxTime = -1;
			for (UppaalLocation location : sourceTemplate.getLocations()) {
				for (int time : location.getStartTimeList()) {
					if (time > maxTime && sendTime > time) {
						maxTime = time;
						transition.setSourceId(location.getId());
					}
				}
			}
			maxTime = -1;
			for (UppaalLocation location : targetTemplate.getLocations()) {
				for (int time : location.getStartTimeList()) {
					if (time > maxTime && recieveTime > time) {
						maxTime = time;
						transition.setTargetId(location.getId());
					}
				}
			}
			eXTransitions.add(transition);
		}

	}
	// 合并所有template到template[0]
	private static void mergeTemplates(ArrayList<UppaalTemPlate> temPlates) {
		UppaalTemPlate template0 = temPlates.get(0);
		for (int i = 1; i < temPlates.size(); i++) {
			template0.getLocations().addAll(temPlates.get(i).getLocations());
			template0.getTransitions().addAll(temPlates.get(i).getTransitions());
		}
	}
	
	private static void outMessageConnectAutomatas(UppaalTemPlate uppaalTemPlate) {
		ArrayList<UppaalTransition> outTransitions = new ArrayList<UppaalTransition>();
		// 找出外部message放到outTransition
		for (UppaalTransition transitionI : uppaalTemPlate.getTransitions()) {
			boolean out = false;
			int i = 0;
			String[] tempStrings = transitionI.getKind();
			while (tempStrings[i] != null) {// 判断是否是外部message
				if (tempStrings[i].equals("synchronisation")) {
					out = true;
					break;
				}
				i++;
			}
			transitionI.setOutKindIndex(i);
			if (out) {
				outTransitions.add(transitionI);
			}
		}
		// 遍历outTransition 新增自动机之间的连接transition
		for (UppaalTransition transitionI : outTransitions) {
			String nameI = transitionI.getNameText()[transitionI.outKindIndex];
			int lengthI = nameI.length();
			for (UppaalTransition transitionJ : outTransitions) {
				String nameJ = transitionJ.getNameText()[transitionJ.outKindIndex];
				int lengthJ = nameJ.length();
				if (nameI.substring(lengthI - 1, lengthI).equals("!")
						&& nameJ.substring(lengthJ - 1, lengthJ).equals("?")
						&& transitionI.getEAid().equals(transitionJ.getEAid())) {
					// 是相同的消息不同形态 cofee! 和 cofee?
					UppaalTransition addTransition = (UppaalTransition) transitionI.clone();

					// cofee! -> cofee//  去掉后面的！
					addTransition.getNameText()[addTransition.outKindIndex] = addTransition
							.getNameText()[addTransition.outKindIndex].substring(0, lengthI - 1);
					
					addTransition.setSourceId(transitionI.getSourceId());
					addTransition.setTargetId(transitionJ.getSourceId());
					uppaalTemPlate.getTransitions().add(addTransition);

					// 去掉对象内的！消息
					uppaalTemPlate.getTransitions().remove(transitionI);
				}
			}
		}
	}

	
	public static UppaalLocation findState(ArrayList<UppaalLocation> locations, UppaalLocation location) {
		Iterator<UppaalLocation> LIterator = locations.iterator();
		while (LIterator.hasNext()) {
			UppaalLocation temp = LIterator.next();
			if (temp.getName().equals(location.getName())) {
				return temp;
			}
		}

		return null;
	}

	public static boolean ArraysContainsString(String[] s, String str) {
		for (int i = 0; i < s.length; i++) {
			if (s[i] == null)
				break;
			if (s[i].equals(str))
				return true;
		}

		return false;
	}

	public static boolean isnewState(ArrayList<UppaalLocation> nameList, String name) {
		Iterator<UppaalLocation> string = nameList.iterator();
		while (string.hasNext()) {
			UppaalLocation temp = string.next();
			if (temp.getName().equals(name)) {
				// Display.println("compare: "+name);
				return false;
			}
		}

		return true;
	}

	public static String DurationConstraint(String str) {

		if (str.equals("0")) {
			return "0";
		} else {
			String[] strings = str.split("[+]");
			// Display.println(strings[1]);
			return strings[1];
		}

	}

	public static int Index(String[] str) {

		for (int i = 0; i < str.length; i++)
			if (str[i] == null)
				return i;
		return -1;
	}

	public static String SenderName(ArrayList<EAMessage> Connectors, String ID, String value) {
		// String messageName=new String();
		Iterator<EAMessage> cIterator = Connectors.iterator();
		while (cIterator.hasNext()) {
			EAMessage temp = cIterator.next();
			Display.println(temp.getSourceId());
			if (ID.equals(temp.getSourceId()) && temp.getSendTime().equals(value)) { // Display.println("send
																						// "+temp.getName());
				return temp.getName();

			}
		}

		return null;
	}

	public static String ReceiverName(ArrayList<EAMessage> Connectors, String ID, String value) {
		// String messageName=new String();
		Iterator<EAMessage> cIterator = Connectors.iterator();
		while (cIterator.hasNext()) {
			EAMessage temp = cIterator.next();
			if (ID.equals(temp.getTragetId()) && temp.getReceiveTime().equals(value)) {
				return temp.getName();

			}
		}

		return null;
	}

	public static String getDiagramDataName() {
		return diagramDataName;
	}

	public static void setDiagramDataName(String diagramDataName) {
		TimingEAtoUppaal.diagramDataName = diagramDataName;
	}
	
	public static MainFrame getMainFrame() {
		return mainFrame;
	}
	
	public static int getState() {
		return state;
	}
	
}

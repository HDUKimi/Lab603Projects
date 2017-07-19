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
		
		// 1.��������------------------------------------------------------------------------------------------------------
		HashSet<String> template_instantiations = new HashSet<String>(); // �洢template��name
		HashSet<String> global_declarations = new HashSet<String>(); // �洢channel��name
		ArrayList<UppaalTemPlate> temPlates = new ArrayList<UppaalTemPlate>(); // �洢����template
		ArrayList<EALifeline> allLifelines = new ArrayList<EALifeline>(); // �洢����lifeline
		ArrayList<EAMessage> allConnectors = new ArrayList<EAMessage>(); // �洢����message
		ArrayList<EADiagramsData> diagramsDatas = new ArrayList<EADiagramsData>();
		UppaalLocation FL = new UppaalLocation(); // �洢��һ��location
		int id = 0; // location��ID������
		// 1.��������end---------------------------------------------------------------------------------------------------

		Display.println("================================���ڶ�ȡʱ��ͼxml��Ϣ================================");
		// 2.��ȡuml.xml�е�����-------------------------------------------------------------------------------------------
		SAXReader reader = new SAXReader();// ��ȡ������
System.out.println(path);
File file = new File(path);
Document dom= reader.read(file);// ����XML��ȡ���������ĵ���dom����
System.out.println("--------"+path);
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
		// ---------------------------------------------------------------------*

		String name = "";
		// *** ��ȡƽ̨xml
		// String path = "coffeeAndTea.timing.violet.xml";
		// name = path.split(".timing.violet.xml")[0];
		// TimingDiagramGraph td0 = XML2xmlbeanUtil.getTd(new
		// File("coffeeAndTea.timing.violet.xml"));
		// EADiagramsData eaDiagramsData =
		// XStreamBean2EAClass.fromTimingDiagramGraph(td0, name);
		// diagramsDatas.add(eaDiagramsData);
		// *** end

		for (EADiagramsData diagramsData : diagramsDatas) {// ����һ��ͼ
			Display.println("===>  ͼ��Ϊ:" + diagramsData.getName() + "\n");
			Display.println("��ʼ������...");
			template_instantiations = new HashSet<String>(); // �洢template��name
			global_declarations = new HashSet<String>(); // �洢channel��name
			temPlates = new ArrayList<UppaalTemPlate>(); // �洢����template
			FL = new UppaalLocation(); // �洢��һ��location

			ArrayList<EALifeline> Lifelines = new ArrayList<EALifeline>(); // �洢����ͼlifeline
			ArrayList<EAMessage> Connectors = new ArrayList<EAMessage>(); // �洢����ͼmessage
			if (diagramsData.getLifelines() != null) {// ֱ����ӱ�ͼ�еĳ�ԱlifelineList
				Lifelines.addAll(diagramsData.getLifelines());
			}
			if (diagramsData.getConnectors() != null) {// ֱ����ӱ�ͼ�еĳ�ԱconnectorList
				Connectors.addAll(diagramsData.getConnectors());
			}
			for (EALifeline lifeline : allLifelines) {// ����id��ñ�ͼ��lifeline
				if (diagramsData.ids.contains(lifeline.LifelineID)) {
					Lifelines.add(lifeline);
				}
			}

			for (EAMessage con : allConnectors) {// ����id��ñ�ͼ����Ϣ
				if (diagramsData.ids.contains(con.connectorId)) {
					Connectors.add(con);
				}
			}

			/* ��message���Ż����� */
			HashMap<String, EAMessage> sourceIDsendTime_connector = new HashMap<String, EAMessage>(); // ���ڲ���FL�Ƿ�����Ϣ���ͷ�
			HashMap<String, EAMessage> tragertIDreceiveTime_connector = new HashMap<String, EAMessage>();// ���ڲ���FL�Ƿ�����Ϣ���ܷ�

			Iterator<EAMessage> cIterator = Connectors.iterator(); // ����������Ϣ
																	// ����sourceIDsendTime_connector
																	// ��tragertIDreceiveTime_connector
			while (cIterator.hasNext()) {
				EAMessage temp = cIterator.next();
				sourceIDsendTime_connector.put("#sourceIdIs" + temp.getSourceId() + "#sendTimeIs" + temp.getSendTime(),
						temp);
				tragertIDreceiveTime_connector
						.put("#tragetIdIs" + temp.getTragetId() + "#receiveTimeIs" + temp.getReceiveTime(), temp);
			}
			// 2.��ȡuml.xml�е�����end----------------------------------------------------------------------------------------

			// EX �洢 ��Ϣ������ת�۵���Ϣ�� ��ĳ��״̬���� �������״̬û�з����ı䣩
			ArrayList<EAMessage> EXconnectorsSender = new ArrayList<EAMessage>();
			EXconnectorsSender.addAll(Connectors);

			// EX �洢 ��Ϣ������ת�۵���Ϣ�� ��ĳ��״̬������ �������״̬û�з����ı䣩
			// ..����һ��list�ϲ����Եõ���Ҫ��ӵ���Ϣ�����ܻ��߷���״̬û�з����ı䣩
			ArrayList<EAMessage> EXconnectorsReciever = new ArrayList<EAMessage>();

			EXconnectorsReciever.addAll(Connectors);
			// 3.��������������--------------------------------------------------------------------------------------------------
			// ���ȶ�lifelines ���� ���յ�һ��״̬�Ŀ�ʼʱ��
			Collections.sort(Lifelines, new Comparator<EALifeline>() {

				@Override
				public int compare(EALifeline o1, EALifeline o2) {
					int t1 = Integer.valueOf(o1.getStateInfos().get(0).getStartTime());
					int t2 = Integer.valueOf(o2.getStateInfos().get(0).getStartTime());
					return t1 - t2;
				}
			});
			Iterator<EALifeline> lineIterator = Lifelines.iterator();

			// ��¼״̬���ʱ��-> ��״
			int maxTime = 0;
			UppaalLocation lastLocation = new UppaalLocation();

			while (lineIterator.hasNext()) {
				// id = 0;
				// ArrayList<String> stateNameArrayList=new ArrayList<String>();
				EALifeline line = lineIterator.next();
				Iterator<EAStateInfo> lineState = line.getStateInfos().iterator();
				template_instantiations.add(line.getName());// ��4��:��UPRAAL������һ��Templateȡ��ΪID
				UppaalTemPlate template = new UppaalTemPlate();
				template.setName(line.getName());
				template.id = line.getLifelineID();
				Display.println("\n-------------------------��ȡ��������Ϣ-------------------------");
				Display.println("--->  �����Ϊ" + line.getName() + "��������\n");

				/* �Ե�ǰtemplate ��location�;�transition�Ż����� */
				HashMap<String, UppaalLocation> name_oldLocations = new HashMap<String, UppaalLocation>();// �����Ƿ��ǵ�ǰtemplate�ľ�״̬
				HashMap<String, UppaalTransition> nameSnameT_oldTransition = new HashMap<String, UppaalTransition>();// �����Ƿ��ǵ�ǰtemplate�ľ�״̬ת��
				Display.println("��ȡ" + line.getName() + "�ϵ�״̬");

				// 3.1������һ��lifeline��state���б���--------------------------------------------------------------------------
				while (lineState.hasNext()) { // ��5��:��State or Condition
												// S��Ϊ��ת��6��,����ת��2����

					EAStateInfo State = lineState.next();
					UppaalLocation location = new UppaalLocation();
					location.startTimeList.add(Integer.valueOf(State.getStartTime()));// ���״̬���ڵĿ�ʼʱ��
					FL.endTimeList.add(Integer.valueOf(State.getStartTime()));
					if (Integer.valueOf(State.getStartTime()) > maxTime) { // ��¼���ʱ��
																			// ��location
						maxTime = Integer.valueOf(State.getStartTime());
						lastLocation = location;
					}
					UppaalTransition transition = new UppaalTransition();
					location.setLineEAID(line.getLifelineID());
					location.setName(State.getName() + ":" + line.getName());
					location.setStartTime(State.getStartTime());
					Display.println("\n��һ��״̬��" + location.getName());

					String print_transition_name = new String(); // ���ڲ���
																	// ��¼״̬ת�Ƶ�name
					// 3.1.1��ʼ״̬
					if (State.startTime.equals("0")) // ��6��:��S�ǵ�һ��State or
														// Condition,ת��7��,����ת��9����
					{
						Display.println("��ó�ʼ״̬ " + State.name);

						location.setInit("true"); // ��7��:��UPRAAL������һ��initial
													// Location
													// B,ת��8����
						location.setInvariant(null); // ��8��:����B��InvariantΪ��,ת��28��
						location.setId(id++);
						location.setInvariant(State.DConst);
						Display.println("��location--" + location.getName() + "��ӵ�template��");
						template.locations.add(location);
						name_oldLocations.put(location.Name, location);

						FL = location;
					} else {
						// 3.1.2�ǳ�ʼ״̬
						// 3.1.2.1�ǳ�ʼ״̬ ����״̬
						// if (!name_oldLocations.containsKey(location.Name)) //
						// ��9��:��S��һ���µ�State
						// or
						if (!name_oldLocations.containsKey(location.Name)) // Conditionת��11��,
						{// �µ�State
							Display.println("��״̬��" + State.name);
							location.setId(id++); // ��11��:��UPPAAL������һ��Location
													// B������һ��Ǩ��FL��B,ת��12����
							location.setLineEAID(line.getLifelineID());
							// Display.println();

							transition.setSourceId(FL.getId());
							transition.setTargetId(location.getId());
							transition.setNameS(FL.getName());
							transition.setNameT(location.getName());
							transition.setStartTime(State.getStartTime());
							FL.setEndTime(State.getStartTime());
							String c = new String(); // c��¼����Լ�������
							if (State.DConst == null)
								c = "null";
							else
								c = State.DConst;
							// 3.1.2.1.1�ǳ�ʼ״̬ ����״̬ ����Լ�����ֲ�ͬ�����
							switch (c) {
							case "null":
								// ��19��:��B����Ϊnormal
								// Location,ת��20����
								location.setInvariant(null); // ��20��:����B��InvariantΪ��,ת��21����
								location.timeDurationList.add("null");
								template.locations.add(location);
								name_oldLocations.put(location.Name, location);

								Display.println("����Լ��Ϊnull");
								Display.println("��location:" + location.getName() + "��ӵ�template��");
								break;

							case "0":
								location.setType(1);
								Display.println("����λ�ã�" + State.getName()); // ��17��:��B����Ϊurgent
																			// Location
								location.setInvariant(null); // ��18��:����B��InvariantΪ��,ת��21��
								location.timeDurationList.add("0");
								template.locations.add(location);
								name_oldLocations.put(location.Name, location);

								Display.println("����Լ��Ϊ0");
								Display.println("��location:" + location.getName() + "��ӵ�template��");
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

								Display.println("����Լ��Ϊ: " + State.DConst);
								Display.println("��location:" + location.getName() + "��ӵ�template��");
								break;

							}

						} else {
							// 3.1.2.2�ǳ�ʼ״̬ �Ǿ�״̬
							Display.println("��״̬��" + State.getName());
							if (lastLocation == location) {
								lastLocation = name_oldLocations.get(location.Name);
							}
							location = name_oldLocations.get(location.Name);

							location.startTimeList.add(Integer.valueOf(State.getStartTime()));// ���״̬���ڵĿ�ʼʱ��
							location.timeDurationList.add(State.DConst);
							transition.setSourceId(FL.getId());
							transition.setTargetId(location.getId());
							transition.setNameS(FL.getName());
							transition.setNameT(location.getName());
							transition.setStartTime(State.getStartTime());
							

						}
						// 3.1.2�õ���״̬��Ǩ���¼�Event
						if (State.getEvent() != null) // ��21��:��S��Event��TimeConstraint!=NULLת��22��,����ת��23����
						{
							int temp = Index(transition.Kind);
							transition.Kind[temp] = "guard";
							transition.nameText[temp] = State.getEvent(); // ��22��:����FL��B��Guard��ΪEvent&TimeConstraint,ת��23����

						}

						// 3.1.3�ж�FL����һ��״̬���Ƿ���massage�ķ��ͷ�

						String SendKeyString = "#sourceIdIs" + FL.getLineEAID() + "#sendTimeIs" + State.getStartTime();
						if (sourceIDsendTime_connector.containsKey(SendKeyString)) // ��23��:���FL��һ����Ϣmessage�ķ�����,ת��24��,����ת��26����
						{
							EAMessage eamessage = sourceIDsendTime_connector.get(SendKeyString);
							String sender_name = eamessage.getName();
							String EAid = eamessage.getConnectorId();
							print_transition_name = sender_name;
							global_declarations.add(sender_name);
							Display.println("��Ϣ��Ϊ��" + sender_name); // ��24��:��UPPAAL������һ��chan
																	// message,ת��25����

							int temp = Index(transition.Kind);
							// transition.setInner(sourceIDsendTime_connector.get(SendKeyString).getInner());
							transition.Kind[temp] = "synchronisation";
							transition.nameText[temp] = sender_name + "!"; // ��25��:����FL��B��Sync��Ϊmessage!,ת��28����
							// Display.println(transition.nameText[temp]);

							transition.setStartTime(State.getStartTime());// Ϊ��ģ����֤���ӵķ���ʱ���¼
							transition.setEndTime(eamessage.getReceiveTime());
							transition.setEAid(EAid);
							transition.setFromName(eamessage.getSourceName());
							transition.setToName(eamessage.getTragetName());
							transition.setDuration(eamessage.getDuration());
							EXconnectorsSender.remove(eamessage);
						}
						// 3.1.4�ж�FL����һ��״̬���Ƿ���massage�Ľ��ܷ�
						String ReceiveKeyString = "#tragetIdIs" + FL.getLineEAID() + "#receiveTimeIs"
								+ State.getStartTime();
						if (tragertIDreceiveTime_connector.containsKey(ReceiveKeyString)) // ��26��:���FL��һ����Ϣmessage�Ľ�����,ת��27��,����
						// ת��28����
						{
							EAMessage eamessage = tragertIDreceiveTime_connector.get(ReceiveKeyString);
							String receiver_name = eamessage.getName();
							String EAid = eamessage.getConnectorId();
							print_transition_name = receiver_name;
							global_declarations.add(receiver_name); // ��24��:��UPPAAL������һ��chan
							Display.println("��Ϣ��Ϊ��" + receiver_name); // message,ת��25����

							int temp = Index(transition.Kind);
							// transition.setInner(eamessage.getInner());
							transition.Kind[temp] = "synchronisation";
							transition.nameText[temp] = receiver_name + "?"; // ��27��:����FL��B��Sync��Ϊmessage?,ת��28��
							transition.setStartTime(State.getStartTime());// Ϊ��ģ����֤���ӵķ���ʱ���¼
							transition.setEndTime(eamessage.getReceiveTime());				
							transition.setEAid(EAid);
							transition.setFromName(eamessage.getSourceName());
							transition.setToName(eamessage.getTragetName());
							transition.setDuration(eamessage.getDuration());
							EXconnectorsReciever.remove(eamessage);
						}
						// �� �����ظ� ���ظ� �����
						template.transitions.add(transition);
						Display.println("���transition�� (" + FL.Name + ")--" + print_transition_name + "-->("
								+ location.Name + ")");

						// �� ���ظ� �����
						// 3.1.5 �ж��Ƿ����Ѿ����ڵ�Ǩ��
						// String
						// STKeyString="#nameSIs"+transition.nameS+"#nameTIs"+transition.nameT;
						//
						// if (transition.getNameText().equals("CoffeeNext!")) {
						// Display.println(1);
						// }
						//
						// if(!nameSnameT_oldTransition.containsKey(STKeyString))//��key
						// ˵��������nameS->nameT��Ǩ��
						// {
						// template.transitions.add(transition);
						// nameSnameT_oldTransition.put(STKeyString,
						// transition);
						// Display.println("���"+FL.Name+"--"+print_transition_name+"("+State.getEvent()+")"+"->"+location.Name);
						// }
						// else
						// {
						// Display.println("�Ѿ�����"+FL.Name+"--"+print_transition_name+"("+State.getEvent()+")"+"->"+location.Name);
						// }

						FL = location;
					}

				}
				// 3.1������һ��lifeline��state���б���end-----------------------------------------------------------------------

				lastLocation.setFinl("true");
				temPlates.add(template);

			}
			// 3.��������������end-----------------------------------------------------------------------------------------------

			// ex.1����Ƴ���ʣ�µ���Ϣ������;�е�ת�۵���Ϣ�� ��ĳ��״̬���� �������״̬û�з����ı䣩
			HashSet<EAMessage> EXconnectors = new HashSet<EAMessage>();
			EXconnectors.addAll(EXconnectorsReciever);
			EXconnectors.addAll(EXconnectorsSender);
			ArrayList<UppaalTransition> EXTransitions = new ArrayList<UppaalTransition>();
			addEXconnectors(EXconnectors, temPlates, EXTransitions);// connector->transition

			Display.println("\n-------------------------�ϲ�template-------------------------");
			// �ϲ�templates
			mergeTemplates(temPlates);
			// ������Ϣ�Ŀ����
			outMessageConnectAutomatas(temPlates.get(0));
			// ex.2 ���ʣ�µ���Ϣ
			temPlates.get(0).getTransitions().addAll(EXTransitions);

			
			//����endTime��С�ĳ�ʼ״̬��Ϊ�����ĳ�ʼ״̬
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
			
			
			Display.println("��ʼд��xml:" + diagramsData.getName() + ".xml");
			// 4.д�뵽UPPAAL.xml��----------------------------------------------------------------------------------------------
			Write.creatXML(baseurl+diagramsData.getName() + ".xml", global_declarations, template_instantiations, temPlates);
			Write2.creatXML(baseurl+diagramsData.getName() + "UPPAAL.xml", global_declarations, template_instantiations, temPlates);
			setDiagramDataName(diagramsData.getName());
			// 4.д�뵽UPPAAL.xml��end-------------------------------------------------------------------------------------------
			Display.println(".....д�����!");
			Display.println("================================ת�����================================");
		} // һ��ͼ���
	}
	// ��Ӳ�ͬ������Զ��� ������  connecter
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

			// �ҵ���Ӧ��template
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
			// ��sourceTemplate���ҵ�sourceLocation
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
	// �ϲ�����template��template[0]
	private static void mergeTemplates(ArrayList<UppaalTemPlate> temPlates) {
		UppaalTemPlate template0 = temPlates.get(0);
		for (int i = 1; i < temPlates.size(); i++) {
			template0.getLocations().addAll(temPlates.get(i).getLocations());
			template0.getTransitions().addAll(temPlates.get(i).getTransitions());
		}
	}
	
	private static void outMessageConnectAutomatas(UppaalTemPlate uppaalTemPlate) {
		ArrayList<UppaalTransition> outTransitions = new ArrayList<UppaalTransition>();
		// �ҳ��ⲿmessage�ŵ�outTransition
		for (UppaalTransition transitionI : uppaalTemPlate.getTransitions()) {
			boolean out = false;
			int i = 0;
			String[] tempStrings = transitionI.getKind();
			while (tempStrings[i] != null) {// �ж��Ƿ����ⲿmessage
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
		// ����outTransition �����Զ���֮�������transition
		for (UppaalTransition transitionI : outTransitions) {
			String nameI = transitionI.getNameText()[transitionI.outKindIndex];
			int lengthI = nameI.length();
			for (UppaalTransition transitionJ : outTransitions) {
				String nameJ = transitionJ.getNameText()[transitionJ.outKindIndex];
				int lengthJ = nameJ.length();
				if (nameI.substring(lengthI - 1, lengthI).equals("!")
						&& nameJ.substring(lengthJ - 1, lengthJ).equals("?")
						&& transitionI.getEAid().equals(transitionJ.getEAid())) {
					// ����ͬ����Ϣ��ͬ��̬ cofee! �� cofee?
					UppaalTransition addTransition = (UppaalTransition) transitionI.clone();

					// cofee! -> cofee//  ȥ������ģ�
					addTransition.getNameText()[addTransition.outKindIndex] = addTransition
							.getNameText()[addTransition.outKindIndex].substring(0, lengthI - 1);
					
					addTransition.setSourceId(transitionI.getSourceId());
					addTransition.setTargetId(transitionJ.getSourceId());
					uppaalTemPlate.getTransitions().add(addTransition);

					// ȥ�������ڵģ���Ϣ
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

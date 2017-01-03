package com.horstmann.violet.application.gui.util.wujun.TDVerification;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.horstmann.violet.application.gui.MainFrame;
import com.thoughtworks.xstream.core.util.Types;

public class ExistVerification {

	private MainFrame mainFrame;

	public static final int VERIFICATION_TYPE_EXIST = 1;
	public static final int VERIFICATION_TYPE_FRONT = 2;
	public static final int VERIFICATION_TYPE_BACK = 3;
	public static final int VERIFICATION_TYPE_TWOWAY = 4;
	private static String[] types = { "", "����һ������֤", "ǰ��һ������֤", "����һ������֤", "˫��һ������֤" };
	private String filePath;
	private static ArrayList<UppaalTemPlate> templates = new ArrayList<UppaalTemPlate>();
	private static ArrayList<UppaalTransition> transitions = new ArrayList<>();
	private static ArrayList<UppaalLocation> locations = new ArrayList<>();
	private static HashMap<String, UppaalLocation> locationById = new HashMap<>();
	private static ArrayList<PathTuple> pathTuples = new ArrayList<>();// ·��
	private static ArrayList<UppaalTransition> messages = new ArrayList<>();// ��Ϣ����

	// 1����
	// �ļ�·��
	public ExistVerification(String filePath) throws Exception {
		this.filePath = filePath;

		Display.println("================================���ڶ�ȡ�Զ���================================");

		SAXReader reader = new SAXReader();// ��ȡ������
		Document dom = reader.read(filePath);// ����XML��ȡ���������ĵ���dom����
		Element root = dom.getRootElement();// ��ȡ���ڵ�

		ReadAutomata uppaal = new ReadAutomata();
		uppaal.load(root);

		templates = uppaal.getUppaalTemplates();
		transitions = templates.get(0).getTransitions();
		locations = templates.get(0).getLocations();
		Display.println("===>  ��ȡ���Զ�������" + templates.get(0).getName());
		// ���transiton��sourceLocation��transitionList��
		setTansitionsToSourceLocation();

		// ��transition ����ʱ��������� ��ô ·��һ���ǰ�����Ϣ��˳�����ߵ�
		sortTransitions();

		// �ҳ���Ϣ����
		messages = sortedMessages();
		// �ҳ�·�� ����ʱ��˳��
		pathTuples = findPathByTime();
	}

	// ���
	// ��ȡ·��
	public ArrayList<PathTuple> getPath() {
		return pathTuples;
	}

	// ���
	// ��ȡ��Ϣ����
	public ArrayList<UppaalTransition> getMessages() {
		Display.println("-------------------------��ȡ��Ϣ����-------------------------\n");
		for (UppaalTransition transition : messages) {
			Display.println(transition.getName());
		}
		return messages;
	}

	// ���� return ���
	// ������Ҫ��ǵı�
	public List<UppaalTransition> getSelectedTransitionsIfExist(List<UppaalTransition> selectedTransition) {

		Display.println("-------------------------���ڽ��д���һ������֤-------------------------\n");
		Display.println("ѡ�����Ϣ���£�");
		for (UppaalTransition transition : selectedTransition) {
			Display.println(transition.getName() + "\n");
		}
		int i = 0;
		int j = 0;
		while (i < selectedTransition.size() && j < pathTuples.size() - 1) {// ���һ��tupleû��transition
			UppaalTransition transitionI = selectedTransition.get(i);
			UppaalTransition transitionJ = pathTuples.get(j).transition;
			if (transitionI.getName().equals(transitionJ.getName())) {
				Display.println("ƥ�䵽��Ϣ��" + transitionI.toString());
				i++;
				j++;
			} else {
				j++;
			}
		}
		if (i == selectedTransition.size()) {
			Display.println("һ������֤���");
			return selectedTransition;
		} else {
			Display.println("һ������֤ʧ��");
			return null;
		}
	}

	// ���� return ���
	// ����·��
	public List<PathTuple> getPathOfSelectedTransitions(List<UppaalTransition> selectedTransition) {
		ArrayList<PathTuple> res = new ArrayList<>();
		Display.println("-------------------------���ڽ���˳������֤-------------------------\n");
		Display.println("ѡ�����Ϣ���£�");
		for (UppaalTransition transition : selectedTransition) {
			Display.println(transition.getName() + "\n");
		}
		int i = 0;
		int j = 0;
		boolean findFirstTransition = false;
		while (i < selectedTransition.size() && j < pathTuples.size() - 1) {// ���һ��tupleû��transition
			UppaalTransition transitionI = selectedTransition.get(i);
			UppaalTransition transitionJ = pathTuples.get(j).transition;
			if (transitionI.getName().equals(transitionJ.getName())) {
				findFirstTransition = true;
				res.add(pathTuples.get(j));
				Display.println("ƥ�䵽��Ϣ��" + transitionI.toString());
				i++;
				j++;
			} else {
				if (findFirstTransition) {
					res.add(pathTuples.get(j));
				}
				j++;
			}
		}
		if (i == selectedTransition.size()) {
			Display.println("�ҵ�·��");
			return res;
		} else {
			Display.println("�Ҳ���·��");
			return null;
		}
	}

	// �����������Ϣ ȷ��һ��·��
	private ArrayList<PathTuple> findPathByTime() {
		ArrayList<PathTuple> res = new ArrayList<>();
		Display.println("-------------------------������Ϣȷ��·��-------------------------\n");
		for (UppaalTransition transition : transitions) {
			UppaalLocation location = locationById.get("id" + transition.getSource());
			Display.println("location: ");
			Display.println(location.toString());
			Display.println("transition: ");
			Display.println(transition.toString());
			PathTuple tuple = new PathTuple(location, transition);
			res.add(tuple);
			Display.println();
		}

		// ����һ���ڵ�
		UppaalLocation lastLocation = locationById.get("id" + transitions.get(transitions.size() - 1).getTarget());
		PathTuple last = new PathTuple(lastLocation, null);
		res.add(last);
		Display.println("location: ");
		Display.println(last.location.toString());

		return res;
	}

	// ��ð���ʱ��˳���������Ϣ���� ��ƽ̨��ʾ
	public ArrayList<UppaalTransition> sortedMessages() {

		ArrayList<UppaalTransition> res = new ArrayList<>();
		for (UppaalTransition transition : templates.get(0).getTransitions()) {
			// ��Ϣ��������? �Ҳ���null
			if (!transition.getName().contains("?") && !transition.getName().equals("null")) {
				res.add(transition);
			}
		}
		return res;
	}

	// ��������Ϣ��������
	private void sortTransitions() {
		Display.println("-------------------------������transition��������-------------------------\n");
		transitions = templates.get(0).transitions;
		Collections.sort(transitions, new Comparator<UppaalTransition>() {
			@Override
			public int compare(UppaalTransition o1, UppaalTransition o2) {
				return (int) (o1.getStartTime() - o2.getStartTime());
			}
		});
		for (UppaalTransition transition : templates.get(0).getTransitions()) {
			Display.println(transition.toString());
		}
		Display.println();
	}

	// ����location��������Ϣ
	private void setTansitionsToSourceLocation() {
		for (UppaalLocation locationI : templates.get(0).getLocations()) {
			locationById.put(locationI.getId(), locationI);
		}
		for (UppaalTransition transitionI : templates.get(0).getTransitions()) {
			String sourceId = "id" + transitionI.getSource();
			UppaalLocation sourceLocation = locationById.get(sourceId);
			sourceLocation.getTransitions().add(transitionI);
		}
	}

	// *ʵʱһ������֤
	public boolean verificationTimeDuration() {
		//1 ��
		boolean locationOK = verificationLocationTimeDuration();
		
		//2 ��
		boolean transitionOK = verificationTransitionTimeDuration();
		
		//3 ·��ʱ���
		boolean pathTupleOK = verificationPathTupleTime();
		
		//4 ì�ܵ�Լ��
//		boolean counterDuration = verificationCounterDuration();
		
		System.out.println("ʵʱһ������֤���" + (locationOK && transitionOK && pathTupleOK));
		return locationOK && transitionOK && pathTupleOK;
	}

	// �Ƿ����ì�ܵ�ʱ��Լ��
	private boolean verificationCounterDuration() {
		for(UppaalLocation locationP : locations) {
			for(UppaalLocation locationC : locations) {
				if (locationC == locationP) {
					continue;
				}
				
				if (!compareLocation(locationP, locationC)) {
					return false;
				}
			}
		}
		return true;
	}
	// ����location�Ƿ�ì�� ì�ܷ���false
	private boolean compareLocation(UppaalLocation locationP, UppaalLocation locationC) {
		for(int i = 0; i < locationP.getStartTimeList().size(); i++) {
			for(int j = 0; j < locationC.getStartTimeList().size(); j++) {
				int startP = locationP.getStartTimeList().get(i);
				int startC = locationC.getStartTimeList().get(j);
				int endP = locationP.getEndTimeList().get(i);
				int endC = locationC.getEndTimeList().get(j);
				if (startC >= startP && endC <= endP) { // ��ͬһ��ʱ����ϵ�״̬
					String durationP = locationP.getTimeDurationList().get(i);
					String durationC = locationC.getTimeDurationList().get(j);
					if (!compareDuration(durationP, durationC)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	// ����ʱ��Լ���Ƿ�ì�� ì�ܷ���false
	private boolean compareDuration(String durationP, String durationC) {
		if (durationP.equals("null") || durationC.equals("null")) {
			return true;
		}
		int time = 0;
		if (durationP.contains("=")) {
			time = Integer.valueOf(durationP.split("=")[1]);
		} else {
			if (durationP.contains(">")) {
				time = Integer.valueOf(durationP.split(">")[1]) + 1;
			} else if (durationP.contains("<")) {
				time = Integer.valueOf(durationP.split("<")[1]) - 1;
			}
		}
		return satisfy(time, durationC);
	}

	private boolean verificationPathTupleTime() {
		System.out.println("-------------------------�ۼ�·��ʱ��ֵ��֤ʱ��̶�-------------------------");
		ArrayList<PathTuple> path = getPath();
		
		System.out.println("��ʼ��ʱ���");
		int timeSum = 0;
		int nextTime = 0;
		for(int i = 0; i < path.size() - 1; i++) {
			UppaalLocation location = path.get(i).getLocation();
			UppaalTransition transition = path.get(i).getTransition();
			if (transition.out && transition.getName().contains("?")) {// ���ظ�����
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
			
			
			System.out.println("�ۼ�transition:" + transition.getName() + "�ĺ�ʱ:" + (transition.getEndTime() - transition.getStartTime()));
			timeSum += transition.getEndTime() - transition.getStartTime();
			nextTime = transition.getEndTime();
		}
		
		PathTuple lastPathTuple = path.get(path.size() - 1);
		UppaalLocation lastLocation = lastPathTuple.getLocation();
		int LocationCount = lastLocation.getStartTimeList().size();
		int lastStartTime = lastLocation.getStartTimeList().get(LocationCount - 1);// ���һ��״̬�Ŀ�ʼʱ��
		System.out.println("���һ��״̬�Ŀ�ʼʱ�䣺" + lastStartTime);
		System.out.println("�ۼӵ�ʱ���:" + timeSum);
		return lastStartTime == timeSum;
	}

	private boolean verificationLocationTimeDuration() {
		System.out.println("-------------------------��֤ÿһ��location�Ƿ�����ʱ��Լ��-------------------------");
		for (UppaalLocation location : locations) {
			for (int i = 0; i < location.getStartTimeList().size(); i++) {
				String timeDuration = location.getTimeDurationList().get(i);
				int startTime = location.getStartTimeList().get(i);
				int endTime = location.getEndTimeList().get(i);
				int time = endTime - startTime;
				System.out.println(location.getName());
				System.out.println("����ʱ��Σ�" + startTime + "-" + endTime);
				if (!satisfy(time, timeDuration)) {
					System.out.println("������ʱ��Լ��" + " " + timeDuration + "\n");
					return false;
				} else {
					System.out.println("����ʱ��Լ��" + " " + timeDuration + "\n");
				}
			}
		}
		return true;
	}

	private boolean verificationTransitionTimeDuration() {
		System.out.println("-------------------------��֤ÿһ��transition�Ƿ�����ʱ��Լ��-------------------------");
		for (UppaalTransition transition : transitions) {
			String timeDuration = transition.getTimeDuration();
			int startTime = transition.getStartTime();
			int endTime = transition.getEndTime();
			int time = endTime - startTime;
			System.out.println(transition.getName());
			System.out.println("����ʱ��Σ�" + startTime + "-" + endTime);
			if (!satisfy(time, timeDuration)) {
				System.out.println("������ʱ��Լ��" + " " + timeDuration + "\n");
				return false;
			} else {
				System.out.println("����ʱ��Լ��" + " " + timeDuration + "\n");
			}
		}
		return true;
	}

	// ״̬������ʱ�� �� ��Ϣ��ִ��ʱ�� �Ƿ�����ʱ��Լ��
	private boolean satisfy(int time, String timeDuration) {
		if (timeDuration == null || timeDuration.equals("null") || timeDuration.equals("0")) {
			return true;
		}
		if (timeDuration.contains("<")) {
			if (timeDuration.contains("<=")) {
				int limit = Integer.valueOf(timeDuration.split("<=")[1]);
				if (time <= limit) {
					return true;
				} else {
					return false;
				}
			} else {
				int limit = Integer.valueOf(timeDuration.split("<")[1]);
				if (time < limit) {
					return true;
				} else {
					return false;
				}
			}
		} else {
			if (timeDuration.contains(">=")) {
				int limit = Integer.valueOf(timeDuration.split(">=")[1]);
				if (time >= limit) {
					return true;
				} else {
					return false;
				}
			} else {
				int limit = Integer.valueOf(timeDuration.split(">")[1]);
				if (time > limit) {
					return true;
				} else {
					return false;
				}
			}
		}
	}
}

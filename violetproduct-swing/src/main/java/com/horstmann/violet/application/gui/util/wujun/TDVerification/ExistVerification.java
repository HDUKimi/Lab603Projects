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
	private static boolean verificationResult = true;
	public boolean getVerificationResult() {
		return verificationResult;
	}
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
		ArrayList<UppaalTransition> exists=new ArrayList<>();
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
				Display.process(selectedTransition.size());
				Display.println("ƥ�䵽��Ϣ��" + transitionI.toString());
				exists.add(transitionJ);
				i++;
				j++;
			} else {
				j++;
			}
		}
		if (i == selectedTransition.size()) {
			Display.println("һ������֤���");
			return exists;
		} else {
			while(i<selectedTransition.size()){
				Display.process(selectedTransition.size());
				i++;
			}
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
				Display.process(selectedTransition.size());
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
			while(i<selectedTransition.size()){
				Display.process(selectedTransition.size());
				i++;
			}
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
//		if(pathTuple.location==null){
//		continue;
//	}
//	if(pathTuple.location.timeDurationList==null){
//		continue;
//	}
//	String timeD = pathTuple.location.timeDurationList.get(0);
//	System.out.println(timeD+" -  - "+pathTuple.location.timeDurationList.get(0).toString());

	}
	
	// �ж�·���������Сʱ���Ƿ���������
		public boolean getPathByInput(String input) {
			int minTime = 0;
			int maxTime = 0;
			
			System.out.println("---------------------------------");
			
			for(PathTuple pathTuple :pathTuples) {
				pathTuple.getLocation().setVisit(0);
			}
			
			for(PathTuple pathTuple :pathTuples) {
				
//				if(pathTuple.transition==null){
//					continue;
//				}
//				if(pathTuple.transition.timeDuration==null){
//					continue;
//				}
//				String timeD = pathTuple.transition.timeDuration;
//				System.out.println(timeD+" -  - "+pathTuple.transition.toString());
				
				if(pathTuple.location.timeDurationList==null){
				}
				else{
					String timeD=pathTuple.location.timeDurationList.get(pathTuple.location.visit);
					pathTuple.location.visit++;
					if(timeD==null||timeD.equals("null")){
					}
					else{
						System.out.println(timeD);
						if(timeD.contains("=")) {
							minTime += timeD.contains("<=") ? Integer.valueOf(timeD.split("<=")[1]) : 0;
							maxTime += timeD.contains(">=") ? Integer.valueOf(timeD.split(">=")[1]) : 0;
						} else {
							minTime += timeD.contains("<") ? Integer.valueOf(timeD.split("<")[1]) : 0;
							maxTime += timeD.contains(">") ? Integer.valueOf(timeD.split(">")[1]) : 0;
						}
					}
				}
				
//				if(input.contains("=")) {
//					minTime += timeD.contains("<=") ? Integer.valueOf(timeD.split("<=")[1]) : 0;
//					maxTime += timeD.contains(">=") ? Integer.valueOf(timeD.split(">=")[1]) : 0;
//				} else {
//					minTime += timeD.contains("<") ? Integer.valueOf(timeD.split("<")[1]) : 0;
//					maxTime += timeD.contains(">") ? Integer.valueOf(timeD.split(">")[1]) : 0;
//				}

			}
			System.out.println(minTime+" - - "+maxTime);
			Display.process(1);
			if(input.contains("<")) {
				return satisfy(minTime, input);
			} else if(input.contains(">")){
				return satisfy(maxTime, input);
			} else {
				return false;
			}
		}


	// *ʵʱһ������֤
	public boolean verificationTimeDuration() {
		//1 ��
		List<LocationVerificationDisplay> lvdList = verificationLocationTimeDuration();
		
		//2 ��
		List<TransitionVerificationDisplay> tvcList = verificationTransitionTimeDuration();
		
		
		//4 ì�ܵ�Լ��
//		boolean counterDuration = verificationCounterDuration();
		
		System.out.println("ʵʱһ������֤���" + verificationResult);
		return verificationResult;
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

	
	// ��ȡ����location��ʱ��Լ����֤���
	public List<LocationVerificationDisplay> verificationLocationTimeDuration() {
		ArrayList<LocationVerificationDisplay> res = new ArrayList<>();
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
					LocationVerificationDisplay locationVerificationDisplay = 
							new LocationVerificationDisplay(location, startTime, endTime, timeDuration, false);
					res.add(locationVerificationDisplay);
					verificationResult = false;
				} else {
					System.out.println("����ʱ��Լ��" + " " + timeDuration + "\n");
					LocationVerificationDisplay locationVerificationDisplay = 
							new LocationVerificationDisplay(location, startTime, endTime, timeDuration, true);
					res.add(locationVerificationDisplay);
				}
				
			}
		}
		return res;
	}
	// ��ȡ����transition��ʱ��Լ����֤���
	public List<TransitionVerificationDisplay> verificationTransitionTimeDuration() {
		ArrayList<TransitionVerificationDisplay> res = new ArrayList<>();
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
				TransitionVerificationDisplay transitionVerificationDisplay = 
						new TransitionVerificationDisplay(transition, startTime, endTime, timeDuration, false);
				res.add(transitionVerificationDisplay);
				verificationResult = false;
			} else {
				System.out.println("����ʱ��Լ��" + " " + timeDuration + "\n");
				TransitionVerificationDisplay transitionVerificationDisplay = 
						new TransitionVerificationDisplay(transition, startTime, endTime, timeDuration, true);
				res.add(transitionVerificationDisplay);
			}
		}
		return res;
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

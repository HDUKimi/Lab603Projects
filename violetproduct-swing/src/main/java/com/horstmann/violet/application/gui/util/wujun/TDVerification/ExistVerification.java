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

		Read uppaal = new Read();
		uppaal.load(root);

		templates = uppaal.getUppaalTemplates();
		transitions = templates.get(0).getTransitions();
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
	// ������Ҫ��ǵ����
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
		Display.println("-------------------------���ڽ��д���һ������֤-------------------------\n");
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
					res.add(pathTuples.get(i));
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
			Display.println("�õ�location: ");
			Display.println(location.toString());
			Display.println("�õ�transition: ");
			Display.println(transition.toString());
			PathTuple tuple = new PathTuple(location, transition);
			res.add(tuple);
			Display.println();
		}
		UppaalLocation lastLocation = locationById.get(transitions.get(transitions.size() - 1).getTarget());
		PathTuple last = new PathTuple(lastLocation, null);
		res.add(last);
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
				return (int) (o1.getTime() - o2.getTime());
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
}

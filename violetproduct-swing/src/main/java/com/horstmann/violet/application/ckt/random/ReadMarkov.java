package com.horstmann.violet.application.ckt.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.horstmann.violet.application.ckt.entity.*;

/**
 * ��ȡ���Markov����XML�ļ�
 * @author ckt
 *
 */
public class ReadMarkov { 
	public static void main(String[] args) throws Exception {
		String fileOfStart = /*"writeWP.markov.violet.xml"*/"readWP.markov.violet.xml";
		Markov markov = readMarkov(fileOfStart);
		System.out.println("=========================");
		for(Transition t : markov.getTransitions()){
			System.out.println(t.getName() + "��" + t.getCondition().trim());
		}
		/*//���Ըı��ʼ״̬��״̬�����г�ʼ״̬�Ƿ�ı�
		System.out.println("--------");
		markov.getInitialState().setStateName("wenwnewnewen");
		System.out.println(markov.getInitialState().getStateName());
		for(State s : markov.getStates()){
			System.out.println(toString(s));
		}*/
	}
	
	/**
	 * ����dom���������ȡxml�ļ����ڴ����γ�markov�����ڽӱ��ʾ�ṹ
	 * @throws Exception 
	 */
	public static Markov readMarkov(String fileOfStart) throws Exception{
		Markov markov = new Markov();
		//1.����һ��SAXReader����
		SAXReader reader = new SAXReader();
		//2���� xml�ļ����ص� SAXReader�У�����ȡ document����
		Document dom = reader.read(fileOfStart);
		
		Element root = dom.getRootElement();//��ȡ���ڵ�
		//System.out.println("---" + root.getName());
		Element nodes = root.element("nodes");
		Object initialList = nodes.element("MarkovStartNode");
		markov.setInitialState(dealStateList(initialList));
		markov.getStates().add(markov.getInitialState());
		/*//״̬��Ϣ���
		for(State s : states){
			System.out.println(toString(s));
		}*/
		List stateList = nodes.elements("MarkovNode");
		for(Object stateNode : stateList){
			markov.getStates().add(dealStateList(stateNode));
		}
		/*//״̬��Ϣ���
		for(State s : markov.getStates()){
			System.out.println(toString(s));
		}*/
		Element edges = root.element("edges");//��ȡǨ�ƽڵ�
		List transitionList = edges.elements("MarkovTransitionEdge");
		dealTransitionList(markov.getTransitions(), transitionList, markov.getStates());
		
		
		//Ϊmarkov������״̬�����Ƿ�����ֹ״̬���Լ�ÿ��״̬��ǰ���ͺ��Ǩ�Ƽ���
		for(State s : markov.getStates()){
			int k = 0;
			for(Transition t : markov.getTransitions()){
				if(t.getStartState().getStateName().equals(s.getStateName())){
					s.getNextTranSet().add(t);
					k = 1;
				}
				if(t.getEndState().getStateName().equals(s.getStateName())){
					s.getProTranSet().add(t);
				}
			}
			if(k == 0){
				s.setFinalState(true);
			}else{
				s.setFinalState(false);
			}
		}
		//״̬��Ϣ���
		System.out.println("---------״̬��Ϣ--------");
		System.out.println("״̬�ܸ�����" + markov.getStates().size());
		for(State s : markov.getStates()){
			System.out.println(toString(s));
		}
		System.out.println("-----------------------");
		System.out.println("---------Ǩ����Ϣ--------");
		System.out.println("Ǩ���ܸ�����" + markov.getTransitions().size());
		//Ǩ����Ϣ���
		for(Transition t : markov.getTransitions()){
			System.out.println(toString(t));
		}
		System.out.println("-----------------------");
		return markov;
	}
	/**
	 * ���״̬��Ϣ
	 */
	public static String toString(State s) {
		String ss = "id=" + s.getId() + " Name=" + s.getStateName() 
		+ " finalState=" + s.isFinalState() + " proTranSet����=" 
				+ s.getProTranSet().size() + " nextTranSet����=" 
		+ s.getNextTranSet().size();
		return ss;
	}
	/**
	 * ���Ǩ����Ϣ
	 */
	public static String toString(Transition t) {
		System.out.println(t.getName()+": "+t.getStartState().getStateName()+"-->"+t.getEndState().getStateName());
		String tt = "  id=" + t.getId() + "***Name=" + t.getName() + 
				"***condition=" + t.getCondition()+ "***prob=" + t.getProbability() 
				+ "***startState=[" + toString(t.getStartState()) + "]***endState=[" 
				+ toString(t.getEndState()) + "]";
		return tt;
	}
	/**
	 * ��xml��״̬�ڵ���Ϣ�������ݽṹ��
	 * @param stateList
	 * @param states
	 */
	public static State dealStateList(Object stateNode){

		//List<Transition> outTransitions = new ArrayList<Transition>(); // �����洢��������ÿ��״̬�ڵ�ĳ�Ǩ�Ƽ���
		Element state = (Element) stateNode;
		Attribute attribute =state.attribute("id");
		String id = attribute.getValue().trim();
		Element name = state.element("name")/*.element("text")*/;
		State headState = new State(); // ÿ������һ��״̬�ͽ����װ��һ����ͷ�ڵ�
		// ����ͷ�����Ӧ���Ը�ֵ
		headState.setId(id);
		//System.out.println("name.getText()----" + name.getText());
		headState.setStateName(name.getText());
		headState.setStateAccessTimes(0);
		//states.add(headState); //����ǰ��״̬�ڵ����״̬�ڵ㼯����	
		return headState;
	}
	
	/**
	 * ��xml��Ǩ�ƽڵ���Ϣ�������ݽṹ��
	 * @param stateList
	 * @param states
	 */
	public static void dealTransitionList(List<Transition> transitions, List transitionList, List<State> states){
		for(Object transitionNode : transitionList){
			Transition t = new Transition();
			//List<Transition> outTransitions = new ArrayList<Transition>(); // �����洢��������ÿ��״̬�ڵ�ĳ�Ǩ�Ƽ���
			Element transition = (Element) transitionNode;
			Attribute attribute =transition.attribute("id");
			String id = attribute.getValue();
			t.setId(id);
			//�ҵ���Ǩ�ƵĿ�ʼ״̬�ڵ�
			Element startTransition = transition.element("start");
			Attribute startTransitionId =startTransition.attribute("reference");
			String startId = startTransitionId.getValue().trim();
			State startState = getStateFromStates(startId,states);
			//�ҵ���Ǩ�Ƶ���ֹ״̬�ڵ�
			Attribute endTransitionId =transition.element("end").attribute("reference");
			String endId = endTransitionId.getValue().trim();
			State endState = getStateFromStates(endId,states);
			if(!(startState==null)&&!(endState==null)){
				t.setStartState(startState);
				t.setEndState(endState);
			}else{
				System.err.println("��Ǩ����ǰ���޺�״̬------����");
			}
			//�ҵ��������ơ����������ʵȸ�����Ϣ�ڵ�
			Element informations = transition.element("probability");
			String information = informations.getText().trim();
			String[] info = information.split(";");
			for(String s : info){
				if(s.contains("name=")){
					String name = s.substring(5, s.length());
					t.setName(name);
				}
				if(s.contains("condition=")){
					String condition = s.substring(11, s.trim().length()-1);
					t.setCondition(condition);
					//------------------------------------------------------
					//ΪǨ�����ò�����Ϣ
					
					/*ArrayList<Parameter> parameters = new ArrayList<Parameter>();
					String[] con = condition.split("--");//--���ڰѲ���ʽ�ֿ�
					for(String ss : con){
						if(ss.contains("#")){ //ss��һ������ʽ��#ǰ�ǲ���ʽ��#�������в���ʽ�в�����������
							String[] ss1 = ss.split("#");//ss1[0]�ǲ���ʽ��ss1[1]�����в�����������Ӧ����
							String[] css = ss1[1].trim().split(",");
							for(String cs : css){
								String[] cs1 = cs.trim().split(":");//cs1[0]�ǲ�����cs1[1]�Ƕ�Ӧ����
								Parameter parameter = new Parameter();
								parameter.setName(cs1[0]);
								parameter.setType(cs1[1]);
								parameter
							}
							
							
						}
						
						
					}*/
					
					//------------------------------------------------------
				}
				if(s.contains("prob=")){
					Double probability = Double.parseDouble(s.substring(5, s.length()));
					t.setProbability(probability);
				}
				if(s.contains("result=")){
					String expectResult = s.substring(7, s.length());
					t.setExpectResult(expectResult);
				}
			}	
			transitions.add(t);
		}				
	}
	/**
	 * ����״̬id��״̬�����ҵ���id
	 */
	public static State getStateFromStates(String id, List<State> states){
		State ss = null;
		for(State s : states){
			if(id.equals(s.getId())){
				ss = s;
				break;
			}
		}
		return ss;
	}
}

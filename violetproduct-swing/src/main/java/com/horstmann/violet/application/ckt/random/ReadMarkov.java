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
 * 读取存放Markov链的XML文件
 * @author ckt
 *
 */
public class ReadMarkov { 
	public static void main(String[] args) throws Exception {
		String fileOfStart = /*"writeWP.markov.violet.xml"*/"readWP.markov.violet.xml";
		Markov markov = readMarkov(fileOfStart);
		System.out.println("=========================");
		for(Transition t : markov.getTransitions()){
			System.out.println(t.getName() + "：" + t.getCondition().trim());
		}
		/*//测试改变初始状态，状态集合中初始状态是否改变
		System.out.println("--------");
		markov.getInitialState().setStateName("wenwnewnewen");
		System.out.println(markov.getInitialState().getStateName());
		for(State s : markov.getStates()){
			System.out.println(toString(s));
		}*/
	}
	
	/**
	 * 利用dom对象解析读取xml文件在内存中形成markov链的邻接表表示结构
	 * @throws Exception 
	 */
	public static Markov readMarkov(String fileOfStart) throws Exception{
		Markov markov = new Markov();
		//1.创建一个SAXReader对象
		SAXReader reader = new SAXReader();
		//2、将 xml文件加载到 SAXReader中，并获取 document对象
		Document dom = reader.read(fileOfStart);
		
		Element root = dom.getRootElement();//获取根节点
		//System.out.println("---" + root.getName());
		Element nodes = root.element("nodes");
		Object initialList = nodes.element("MarkovStartNode");
		markov.setInitialState(dealStateList(initialList));
		markov.getStates().add(markov.getInitialState());
		/*//状态信息输出
		for(State s : states){
			System.out.println(toString(s));
		}*/
		List stateList = nodes.elements("MarkovNode");
		for(Object stateNode : stateList){
			markov.getStates().add(dealStateList(stateNode));
		}
		/*//状态信息输出
		for(State s : markov.getStates()){
			System.out.println(toString(s));
		}*/
		Element edges = root.element("edges");//获取迁移节点
		List transitionList = edges.elements("MarkovTransitionEdge");
		dealTransitionList(markov.getTransitions(), transitionList, markov.getStates());
		
		
		//为markov中所有状态设置是否是终止状态、以及每个状态的前驱和后继迁移集合
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
		//状态信息输出
		System.out.println("---------状态信息--------");
		System.out.println("状态总个数：" + markov.getStates().size());
		for(State s : markov.getStates()){
			System.out.println(toString(s));
		}
		System.out.println("-----------------------");
		System.out.println("---------迁移信息--------");
		System.out.println("迁移总个数：" + markov.getTransitions().size());
		//迁移信息输出
		for(Transition t : markov.getTransitions()){
			System.out.println(toString(t));
		}
		System.out.println("-----------------------");
		return markov;
	}
	/**
	 * 输出状态信息
	 */
	public static String toString(State s) {
		String ss = "id=" + s.getId() + " Name=" + s.getStateName() 
		+ " finalState=" + s.isFinalState() + " proTranSet数量=" 
				+ s.getProTranSet().size() + " nextTranSet数量=" 
		+ s.getNextTranSet().size();
		return ss;
	}
	/**
	 * 输出迁移信息
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
	 * 把xml中状态节点信息存在数据结构中
	 * @param stateList
	 * @param states
	 */
	public static State dealStateList(Object stateNode){

		//List<Transition> outTransitions = new ArrayList<Transition>(); // 用来存储遍历到的每个状态节点的出迁移集合
		Element state = (Element) stateNode;
		Attribute attribute =state.attribute("id");
		String id = attribute.getValue().trim();
		Element name = state.element("name")/*.element("text")*/;
		State headState = new State(); // 每遍历到一个状态就将其封装成一个表头节点
		// 给表头结点相应属性赋值
		headState.setId(id);
		//System.out.println("name.getText()----" + name.getText());
		headState.setStateName(name.getText());
		headState.setStateAccessTimes(0);
		//states.add(headState); //将当前的状态节点存入状态节点集合中	
		return headState;
	}
	
	/**
	 * 把xml中迁移节点信息存在数据结构中
	 * @param stateList
	 * @param states
	 */
	public static void dealTransitionList(List<Transition> transitions, List transitionList, List<State> states){
		for(Object transitionNode : transitionList){
			Transition t = new Transition();
			//List<Transition> outTransitions = new ArrayList<Transition>(); // 用来存储遍历到的每个状态节点的出迁移集合
			Element transition = (Element) transitionNode;
			Attribute attribute =transition.attribute("id");
			String id = attribute.getValue();
			t.setId(id);
			//找到此迁移的开始状态节点
			Element startTransition = transition.element("start");
			Attribute startTransitionId =startTransition.attribute("reference");
			String startId = startTransitionId.getValue().trim();
			State startState = getStateFromStates(startId,states);
			//找到此迁移的终止状态节点
			Attribute endTransitionId =transition.element("end").attribute("reference");
			String endId = endTransitionId.getValue().trim();
			State endState = getStateFromStates(endId,states);
			if(!(startState==null)&&!(endState==null)){
				t.setStartState(startState);
				t.setEndState(endState);
			}else{
				System.err.println("此迁移无前或无后状态------出错");
			}
			//找到含有名称、条件、概率等各种信息节点
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
					//为迁移设置参数信息
					
					/*ArrayList<Parameter> parameters = new ArrayList<Parameter>();
					String[] con = condition.split("--");//--用于把不等式分开
					for(String ss : con){
						if(ss.contains("#")){ //ss是一个不等式，#前是不等式，#后是所有不等式中参数及其类型
							String[] ss1 = ss.split("#");//ss1[0]是不等式，ss1[1]是所有参数及参数对应类型
							String[] css = ss1[1].trim().split(",");
							for(String cs : css){
								String[] cs1 = cs.trim().split(":");//cs1[0]是参数，cs1[1]是对应类型
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
	 * 根据状态id从状态集中找到此id
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

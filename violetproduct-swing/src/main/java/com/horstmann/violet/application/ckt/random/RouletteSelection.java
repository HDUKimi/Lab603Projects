package com.horstmann.violet.application.ckt.random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.horstmann.violet.application.ckt.entity.*;

import java.util.TreeMap;


public class RouletteSelection {
	/*public static void main(String[] args) throws Exception {
		Markov markov = ReadMarkov.readMarkov();
		List<Route> routes = randomRoute(markov, 100);
		int i = 0;
		for(Route route : routes){
			if(route.getRouteResult().contains("false")){
				i++;
				//Test.systemRoute(route);
			}	
			Test.systemRoute(route);
		}
		System.out.println("����" + i + "��·��Ԥ����false");
		
	}*/
	/**
	 * ��������Ʒ�ģ���������n��·������ÿ��·���������̶ģ�10��Ͷȡ����������
	 * @param markov ��ȡ������Ʒ�ģ��ͼ
	 * @param n ��Ҫ���ɵ�·������
	 * @return
	 */
	public static List<Route> randomRoute(Markov markov, int n){
		List<Route> routes= new ArrayList<Route>();
		for(int i=0; i<n; i++){
			int flag = 1;//���ڱ�ʶ��·���Ƿ�����ȷ·��,Ĭ������ȷ��
			Route route = new Route();
			route.setId(i+1);
			State initialState = markov.getInitialState();
			//System.out.println("��ʼ״̬��Ϣ" + ReadMarkov.toString(initialState));
			Transition t = roulette(initialState);
			//System.out.println("  ��ѡǨ��������Ϣ" + ReadMarkov.toString(t));
			Double probability = t.getProbability();
			if(t.getExpectResult().contains("false")){
				flag = 0;
			}
			route.getTransitionList().add(t);
			State state = t.getEndState();
			//System.out.println("  ��ѡǨ�Ƴ�״̬====" + ReadMarkov.toString(state));
			while(!state.isFinalState()){
				//System.out.println("��״̬������ֹ״̬��������Ǩ��");
				Transition t1 = roulette(state);
				//System.out.println("  ��ѡǨ��������Ϣ" + ReadMarkov.toString(t1));
				if(t1.getExpectResult().contains("false")){
					flag = 0;
				}
				route.getTransitionList().add(t1);
				probability = probability * t1.getProbability();
				state = t1.getEndState();
				//System.out.println("  ��ѡǨ�Ƴ�״̬====" + ReadMarkov.toString(state));
			}
			route.setRouteProbability(probability);
			if(flag == 0){
				route.setRouteResult("false");
			}else{
				route.setRouteResult("true");
			}
			routes.add(route);
			///////////////////////////////////////
			//Test.systemRoute(route);

		}
		return routes;		
	}
	
	
	/**
	 * ���̶��㷨ѡ������Ǩ��
	 * @return
	 */
	public static Transition roulette(State s){
		
		double pointer = 0.0;//pointerָʾÿ�����ε��ұ߽磬��������ɨ���ж�
		List<Transition> outTrans = s.getNextTranSet();//��Ÿ�״̬�����г�Ǩ��
		Transition[] tranNumber = new Transition[outTrans.size()];//���״̬s��Ǩ�Ƶ����
		Map<Transition, Integer> map = new HashMap<>();
		for(int k=0; k<outTrans.size(); k++){
			map.put(outTrans.get(k), 0);
		}
		//10��ת���̣����䵽�ĸ����������࣬ѡ����һ��Ǩ��
		//for(int i=0; i<10; i++){
			pointer = 0.0;
			double rand = Math.random();
			//System.out.println("  1.�������rand---" + rand);
			for(int j=0; j<outTrans.size(); j++){
				pointer += outTrans.get(j).getProbability();
				if(rand <= pointer){
					map.put(outTrans.get(j), map.get(outTrans.get(j))+1);	
					break;
				}
			}
		//}
		// ����Ƚ��� 
		Comparator<Map.Entry<Transition, Integer>> valueComparator = new Comparator<Map.Entry<Transition,Integer>>() { 
			public int compare(Entry<Transition, Integer> o1, Entry<Transition, Integer> o2) {
				return o2.getValue()-o1.getValue(); 
				} 
			}; 		
		List<Map.Entry<Transition, Integer>> entryList = 
				new ArrayList<Map.Entry<Transition, Integer>>(map.entrySet());
		Collections.sort(entryList, valueComparator);
		
		/*for(Map.Entry<Transition, Integer> entry : entryList){
			System.out.println(entry.getKey() + ":" + entry.getValue()); 
		}*/
		//System.out.println("====>  " + entryList.get(0).getKey());//ֵ���Ǩ��
		
		return entryList.get(0).getKey();
	}
	
}

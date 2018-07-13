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
		System.out.println("共有" + i + "条路径预期是false");
		
	}*/
	/**
	 * 根据马尔科夫模型随机生成n条路径，且每条路径采用轮盘赌（10次投取最大）随机生成
	 * @param markov 读取的马尔科夫模型图
	 * @param n 需要生成的路径个数
	 * @return
	 */
	public static List<Route> randomRoute(Markov markov, int n){
		List<Route> routes= new ArrayList<Route>();
		for(int i=0; i<n; i++){
			int flag = 1;//用于标识此路径是否是正确路径,默认是正确的
			Route route = new Route();
			route.setId(i+1);
			State initialState = markov.getInitialState();
			//System.out.println("初始状态信息" + ReadMarkov.toString(initialState));
			Transition t = roulette(initialState);
			//System.out.println("  被选迁移名称信息" + ReadMarkov.toString(t));
			Double probability = t.getProbability();
			if(t.getExpectResult().contains("false")){
				flag = 0;
			}
			route.getTransitionList().add(t);
			State state = t.getEndState();
			//System.out.println("  被选迁移出状态====" + ReadMarkov.toString(state));
			while(!state.isFinalState()){
				//System.out.println("此状态不是终止状态，找下条迁移");
				Transition t1 = roulette(state);
				//System.out.println("  被选迁移名称信息" + ReadMarkov.toString(t1));
				if(t1.getExpectResult().contains("false")){
					flag = 0;
				}
				route.getTransitionList().add(t1);
				probability = probability * t1.getProbability();
				state = t1.getEndState();
				//System.out.println("  被选迁移出状态====" + ReadMarkov.toString(state));
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
	 * 轮盘赌算法选择下条迁移
	 * @return
	 */
	public static Transition roulette(State s){
		
		double pointer = 0.0;//pointer指示每个区段的右边界，从左往右扫描判断
		List<Transition> outTrans = s.getNextTranSet();//存放该状态的所有出迁移
		Transition[] tranNumber = new Transition[outTrans.size()];//存放状态s出迁移的序号
		Map<Transition, Integer> map = new HashMap<>();
		for(int k=0; k<outTrans.size(); k++){
			map.put(outTrans.get(k), 0);
		}
		//10次转轮盘，看落到哪个区域次数最多，选择哪一条迁移
		//for(int i=0; i<10; i++){
			pointer = 0.0;
			double rand = Math.random();
			//System.out.println("  1.随机种子rand---" + rand);
			for(int j=0; j<outTrans.size(); j++){
				pointer += outTrans.get(j).getProbability();
				if(rand <= pointer){
					map.put(outTrans.get(j), map.get(outTrans.get(j))+1);	
					break;
				}
			}
		//}
		// 降序比较器 
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
		//System.out.println("====>  " + entryList.get(0).getKey());//值最大迁移
		
		return entryList.get(0).getKey();
	}
	
}

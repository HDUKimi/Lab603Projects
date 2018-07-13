package com.horstmann.violet.application.ckt.xml;

import java.util.List;

import com.horstmann.violet.application.ckt.entity.Route;
import com.horstmann.violet.application.ckt.entity.Transition;
import com.horstmann.violet.application.ckt.random.GetResult;

public class RouteInstantiate {

	public static void instantiation(List<Route> routes){
		
		System.out.println("*****************ʵ����Ǩ��******************");
		for(Route route : routes){
			for(Transition t : route.getTransitionList()){
				//System.out.println("=====" + t.getCondition());
				String resultOfTransition = GetResult.getResult(t.getCondition());
				//System.out.println("==========Ǩ���Ͻ�Ϊ��" + resultOfTransition);
				t.setResultOfCondition(resultOfTransition);
			}
			
//			System.out.println(route.toString());
//			for(Transition transition:route.getTransitionList()){
//				System.out.println(transition.toString());
//			}
		}
	}
	
}

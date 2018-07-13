package com.horstmann.violet.application.ckt.xml;

import java.util.List;

import com.horstmann.violet.application.ckt.entity.Route;
import com.horstmann.violet.application.ckt.entity.Transition;
import com.horstmann.violet.application.ckt.random.GetResult;

public class RouteInstantiate {

	public static void instantiation(List<Route> routes){
		
		System.out.println("*****************实例化迁移******************");
		for(Route route : routes){
			for(Transition t : route.getTransitionList()){
				//System.out.println("=====" + t.getCondition());
				String resultOfTransition = GetResult.getResult(t.getCondition());
				//System.out.println("==========迁移上解为：" + resultOfTransition);
				t.setResultOfCondition(resultOfTransition);
			}
			
//			System.out.println(route.toString());
//			for(Transition transition:route.getTransitionList()){
//				System.out.println(transition.toString());
//			}
		}
	}
	
}

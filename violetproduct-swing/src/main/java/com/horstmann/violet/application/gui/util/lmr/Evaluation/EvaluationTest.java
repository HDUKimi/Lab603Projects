package com.horstmann.violet.application.gui.util.lmr.Evaluation;

import java.util.List;

import org.dom4j.DocumentException;

public class EvaluationTest {

	public static void main(String[] args) {
		
		String uppaalName="EA4.1.0 ¹¦ÄÜ³¡¾°1";
		int uppaalType=1;
		
		Evaluation evaluation=new Evaluation(uppaalName, uppaalType);
		
		try {
			evaluation.LoadUppaalXmlData();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		evaluation.LinkUppaalLocationToUppaalTransition();
		
		System.out.println(evaluation.getUppaalLocations().size()+" - "+evaluation.getUppaalTransitions().size());
		
//		for(EvaluationUppaalLocation location:evaluation.getUppaalLocations()){
//			System.out.println(location.getUppaalTransitions().size()+" - "+location.isFinl());
//		}
//		System.out.println("------------------------------");
//		for(EvaluationUppaalTransition transition:evaluation.getUppaalTransitions()){
//			System.out.println(transition.toString());
//		}
		
//		System.out.println("------------------------------");
//		evaluation.LinkUppaalLocationToUppaalTransition();
//		
//		
//		for(EvaluationUppaalLocation location:evaluation.getUppaalLocations()){
//			System.out.println(location.toString());
//		}
//		System.out.println("------------------------------");
//		for(EvaluationUppaalTransition transition:evaluation.getUppaalTransitions()){
//			System.out.println(transition.toString());
//		}
		
//		System.out.println(evaluation.FindUppaalTransitionByMessage("loop()"));
//		System.out.println(evaluation.FindUppaalTransitionByMessage("read_AHRS()"));
//		
//		evaluation.LinkUppaalLocationToUppaalTransition();
		
//		evaluation.FindAllUppaalPath();
//		System.out.println(evaluation.FindUppaalPathTupleByMessages("loop()", "read_AHRS()").size());
		
//		for(EvaluationUppaalLocation uppaalLocation:evaluation.getUppaalLocations()){
//			System.out.println(uppaalLocation.getName()+" - "+uppaalLocation.getUppaalTransitions().size());
//		}
		
//		evaluation.FindAllUppaalPath();
//		
//		System.out.println(evaluation.getUppaalPaths().size());
//		
//		for(List<EvaluationUppaalTuple> uppaalTuples:evaluation.getUppaalPaths()){
//			System.out.println();
//			System.out.println("------------------------------"+uppaalTuples.size());
//			for(EvaluationUppaalTuple uppaalTuple:uppaalTuples){
////				System.out.print((uppaalTuple.getUppaalTransition()!=null?uppaalTuple.getUppaalTransition().getName():"")+" -> ");
//				System.out.print(uppaalTuple.getUppaalLocation().getTimeDuration()+" -> ");
//			}
//		}
//		
//		System.out.println(evaluation.CheckTimeByInput("t>25"));
		
		
		evaluation.FindAllUppaalPath();
		
		System.out.println("+-+ "+evaluation.FindStartUppaalLocation().getUppaalPathTuples().size());
		for(List<EvaluationUppaalTuple> uppaalTuples:evaluation.FindStartUppaalLocation().getUppaalPathTuples()){
			System.out.println(uppaalTuples.size());
		}
		
	}
	
}

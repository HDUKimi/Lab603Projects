package com.horstmann.violet.application.ckt.random;

import java.util.List;

import com.horstmann.violet.application.ckt.entity.Markov;
import com.horstmann.violet.application.ckt.entity.Route;
import com.horstmann.violet.application.ckt.entity.Transition;


public class TestCases {




	public static void main(String[] args) throws Exception {
		String fileOfStart = "writeWP.markov.violet.1.xml";
		//String fileOfStart = "writeWP.markov.violet2.xml";
		List<Route> testcases = getTestCase(fileOfStart);

	}


	/**
	 * 
	 * @param fileOfStart ģ�͵ĳ�ʼxml�ļ�
	 * @return ��������
	 * @throws Exception
	 */
	public static List<Route> getTestCase(String fileOfStart) throws Exception{
		//String fileOfStart = "writeWP.markov.violet.xml";
		//String fileOfStart = "generatePacket.markov.violet.xml";
		//String fileOfStart = "readPacket.markov.violet.xml";
		//String fileOfStart = "writeWP.markov.violet2.xml";
		//��1������xml�ļ�ת��ΪMarkovģ��
		Markov markov = ReadMarkov.readMarkov(fileOfStart);
		//��2�����������̶��������·��
		List<Route> routes = RouletteSelection.randomRoute(markov, 100);
		//��ӡ���·����Ϣ
		/*System.out.println("**************���̶����ɵ�·����Ϣ**************");
		int i = 0;
		for(Route route : routes){
			if(route.getRouteResult().contains("false")){
				i++;
				//Test.systemRoute(route);
			}	
			systemRoute(route);//��ӡ·���ϵ���Ϣ
		}
		System.out.println("����" + i + "��·��Ԥ����false");
		System.out.println("*******************************************");
		System.out.println();*/
		
		//��3����Ϊ·���ϵ�ÿ��Ǩ��ʵ����
		System.out.println("*****************ʵ����Ǩ��******************");
		for(Route route : routes){
			for(Transition t : route.getTransitionList()){
				//System.out.println("=====" + t.getCondition());
				String resultOfTransition = GetResult.getResult(t.getCondition());
				//System.out.println("==========Ǩ���Ͻ�Ϊ��" + resultOfTransition);
				t.setResultOfCondition(resultOfTransition);
			}
			systemRoute(route);//��ӡ·���ϵ���Ϣ
		}
	
		return routes;
	}

	public static void systemRoute(Route route) {
		System.out.println("-------��������" + route.getId() + "-------");
		System.out.println("·����Ǩ�Ƹ�����" + route.getTransitionList().size());
		for(Transition t : route.getTransitionList()){
			System.out.println("Ǩ�����ƣ�" + t.getName() + "  Ǩ��id��" + t.getId() + "  Ǩ�Ƹ��ʣ�" + t.getProbability() + "  Ǩ�ƽ����" + t.getExpectResult());
			System.out.println("Ǩ��������" + t.getCondition());
			System.out.println("Ǩ�������⣺" + t.getResultOfCondition());
			System.out.println("  --------");
		}
		System.out.println("·�����ʣ�" + route.getRouteProbability());
		System.out.println("·��Ԥ�ڽ����" + route.getRouteResult());
		System.out.println("-----------------------------------");
	}


}

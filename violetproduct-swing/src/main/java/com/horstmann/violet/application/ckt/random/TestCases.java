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
	 * @param fileOfStart 模型的初始xml文件
	 * @return 测试用例
	 * @throws Exception
	 */
	public static List<Route> getTestCase(String fileOfStart) throws Exception{
		//String fileOfStart = "writeWP.markov.violet.xml";
		//String fileOfStart = "generatePacket.markov.violet.xml";
		//String fileOfStart = "readPacket.markov.violet.xml";
		//String fileOfStart = "writeWP.markov.violet2.xml";
		//第1步：将xml文件转换为Markov模型
		Markov markov = ReadMarkov.readMarkov(fileOfStart);
		//第2步：利用轮盘赌生成随机路径
		List<Route> routes = RouletteSelection.randomRoute(markov, 100);
		//打印输出路径信息
		/*System.out.println("**************轮盘赌生成的路径信息**************");
		int i = 0;
		for(Route route : routes){
			if(route.getRouteResult().contains("false")){
				i++;
				//Test.systemRoute(route);
			}	
			systemRoute(route);//打印路径上的信息
		}
		System.out.println("共有" + i + "条路径预期是false");
		System.out.println("*******************************************");
		System.out.println();*/
		
		//第3步：为路径上的每条迁移实例化
		System.out.println("*****************实例化迁移******************");
		for(Route route : routes){
			for(Transition t : route.getTransitionList()){
				//System.out.println("=====" + t.getCondition());
				String resultOfTransition = GetResult.getResult(t.getCondition());
				//System.out.println("==========迁移上解为：" + resultOfTransition);
				t.setResultOfCondition(resultOfTransition);
			}
			systemRoute(route);//打印路径上的信息
		}
	
		return routes;
	}

	public static void systemRoute(Route route) {
		System.out.println("-------测试用例" + route.getId() + "-------");
		System.out.println("路径总迁移个数：" + route.getTransitionList().size());
		for(Transition t : route.getTransitionList()){
			System.out.println("迁移名称：" + t.getName() + "  迁移id：" + t.getId() + "  迁移概率：" + t.getProbability() + "  迁移结果：" + t.getExpectResult());
			System.out.println("迁移条件：" + t.getCondition());
			System.out.println("迁移条件解：" + t.getResultOfCondition());
			System.out.println("  --------");
		}
		System.out.println("路径概率：" + route.getRouteProbability());
		System.out.println("路径预期结果：" + route.getRouteResult());
		System.out.println("-----------------------------------");
	}


}

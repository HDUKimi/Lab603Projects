package com.horstmann.violet.application.gui.util.wj.util;

import java.util.ArrayList;
import java.util.HashMap;

import com.horstmann.violet.application.gui.util.ckt.handle.Automatic;
import com.horstmann.violet.application.gui.util.ckt.handle.State;

public class DataHelper {

	// 设置根据id找状态的hashmap
	public static HashMap<String, State> getStateIdHashMap(ArrayList<State> states) {
		HashMap<String, State> findStateByID = new HashMap<String, State>();
		for (State state : states) {
			findStateByID.put(state.getPosition(), state);
		}
		return findStateByID;
	}

	// 获取状态的重要度
	public static double[] getStatesImportantPoint(Automatic automatic) {
		ImpJudger ij = new ImpJudger(automatic);
		return ij.getStatesImportantPoints();
	}

	/**
	 * 根据概率 获取随机积分
	 * 
	 * @param rdm
	 * @return
	 */
	// 这里的参数是一个存储概率集合当然你也可以用数组
	public static Integer randomPoints(ArrayList<Double> rdm) {
		ArrayList<Double> cdm = rdm;
		// 这里是将概率集合重组 比如 0.2 0.3 0.1 0.4 四个数据的集合
		for (int i = 1; i < cdm.size() - 1; i++) {
			cdm.set(i, cdm.get(i) + cdm.get(i - 1));
		}
		cdm.set(cdm.size() - 1, 1.0);
		// 重组后 变成0.2 0.5 0.6 1.0 四个数据的集合
		double randomNumber;
		randomNumber = Math.random();
		// 随机生成一个0到1的随机数
		// 接下就很简单了你只要循环重组集合 如果随机数小于那个值 就是返回 I 就好了
		for (int i = 0; i < cdm.size(); i++) {
			if (randomNumber < cdm.get(i)) {
				return i;
			}
		}
		return -1; // 正常情况之下不应该到这一步的。因为cdf的最后一个概率应该是1

	}
}

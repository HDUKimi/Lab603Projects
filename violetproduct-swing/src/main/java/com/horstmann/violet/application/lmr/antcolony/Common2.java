package com.horstmann.violet.application.lmr.antcolony;

/**
 * 公共函数以及变量
 */
public class Common2 {
	/**
	 * 信息启发式因子，信息素的重要程度
	 */
	public static double ALPHA = 1.0;
	/**
	 * 期望启发式因子, 城市间距离的重要程度
	 */
	public static double BETA = 5.0;
	/**
	 * 信息素挥发系数
	 */
	public static double ROU = 0.05;

	/**
	 * ACS的伪随机比例选择规则的算法参数，
	 */
	public static double ACSQ = 0.01;

	/**
	 * 蚂蚁数量
	 */
	public static int N_ANT_COUNT = 30;
	/**
	 * 迭代次数
	 */
	public static int N_IT_COUNT = 100;
	/**
	 * 分割数目，把变量的取值范围分割
	 */
	public static int N_SPLIT_COUNT = 100;
	/**
	 * 函数样本编号
	 */
	public static int N_FUN_ID = 1;
	
	/**
	 * 变量的个数
	 */
	public static int VAR_NUM;
	/**
	 * 变量的实时取值上限
	 */
	public static double[] VAR_LOW;
	/**
	 * 变量的实时取值下限
	 */
	public static double[] VAR_UPPER;
	/**
	 * 变量的取值上限
	 */
	public static double[] VAR_Scale_LOW;
	/**
	 * 变量的实时取值下限
	 */
	public static double[] VAR_Scale_UPPER;
	/**
	 * 变量的范围缩小参数
	 */
	public static double VAR_DELTA=0.99;
	
	/**
	 * 总信息素
	 */
	public static double DBQ = 1.0;
	/**
	 * 初始信息素
	 */
	public static double DBS = 0.1;

	/**
	 * 信息素的最大值
	 */
	public static double DBS_MAX = 1;
	/**
	 * 信息素的最小值
	 */
	public static double DBS_MIN = 0.1;
	/**
	 * 信息素的最大值与最小值的比例
	 */
	public static double DBS_RATE; 
	
	/**
	 * 蚂蚁一次搜索找到最优路径的概率
	 */
	public static double PB_Best=0.05;

	/**
	 * 一个标志数,用来初始化一个比较大的最优路径
	 */
	public static final double DB_MAX = Math.pow(10, 9);
	/**
	 * 一个标志数,用来初始化一个比较小的最优路径
	 */
	public static final double DB_MIN = -Math.pow(10, 9);

	/**
	 * 每个区域的信息量
	 */
	public static double[][] g_Area;
	
	/**
	 * 求最大值（1）或者最小值（-1）
	 */
	public static int Type;

	/**
	 * 返回指定范围内的随机整数
	 * 
	 * @param nLow
	 *            上限
	 * @param nUpper
	 *            下限
	 * @return
	 */
	public static int rnd(int nLow, int nUpper) {
		return (int) (Math.random() * (nUpper - nLow) + nLow);
	}

	/**
	 * 返回指定范围内的随机浮点数
	 * 
	 * @param dbLow
	 *            上限
	 * @param dbUpper
	 *            下限
	 * @return
	 */
	public static double rnd(double dbLow, double dbUpper) {
		return Math.random() * (dbUpper - dbLow) + dbLow;
	}

	/**
	 * 返回0到1之间的随机浮点数
	 * 
	 * @return
	 */
	public static double rnd() {
		return Math.random();
	}
	
	/**
	 * 根据变量下标和区域编号返回该区域的中心点x值
	 * @param varIndex	变量下标
	 * @param areaNo	区域编号
	 * @return
	 */
	public static double getFunVarVal(int varIndex,int areaNo){
		return VAR_LOW[varIndex]+(VAR_UPPER[varIndex]-VAR_LOW[varIndex])*0.5/N_SPLIT_COUNT*(2*areaNo+1);
	}
	
	/**
	 * 根据变量下标和区域编号返回该区域的随机点x值
	 * @param varIndex	变量下标
	 * @param areaNo	区域编号
	 * @return
	 */
	public static double rndAreaVarVal(int varIndex,int areaNo){
		return VAR_LOW[varIndex]+(VAR_UPPER[varIndex]-VAR_LOW[varIndex])*1.0/N_SPLIT_COUNT*(areaNo+rnd());
	}

}

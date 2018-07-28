package com.horstmann.violet.application.lmr.antcolony;

import java.util.Arrays;

/**
 * 算法
 */
public class Solve3 {

	/**
	 * 蚂蚁数组
	 */
	public Ant3[] m_antAry = new Ant3[Common3.N_ANT_COUNT];
	/**
	 * 定义一组蚂蚁,用来保存每一次搜索中较优结果,不参与搜索
	 */
	public Ant3[] m_betterAnts = new Ant3[Common3.N_IT_COUNT];
	/**
	 * 定义一组蚂蚁,用来保存每一次搜索结束的最终最优结果,不参与搜索
	 */
	public Ant3[] m_bestAnts = new Ant3[Common3.N_IT_COUNT];

	/**
	 * 算法开始
	 */
	public void Start() {
		initParameter();
		initTrial();
		Iterator();
		ShowResult();
	}

	/**
	 * 初始化参数
	 */
	public void initParameter() {

		Common3.ALPHA = 1.0;
		Common3.BETA = 1.0;
		Common3.ROU = 0.3;
		Common3.DBQ = 1.0;
		Common3.DBS = 1.0;

		m_antAry = new Ant3[Common3.N_ANT_COUNT];
		m_betterAnts = new Ant3[Common3.N_IT_COUNT];
		m_bestAnts = new Ant3[Common3.N_IT_COUNT];
		
		Common3.g_Area=new double[Common3.VAR_NUM][Common3.N_SPLIT_COUNT];

	}

	/**
	 * 初始化蚂蚁
	 */
	public void initTrial() {

		for (int i = 0; i < Common3.N_ANT_COUNT; i++) {
			m_antAry[i] = new Ant3();
		}
		for (int i = 0; i < Common3.N_IT_COUNT; i++) {
			m_betterAnts[i] = new Ant3();
			if(Common3.Type==1){
				m_betterAnts[i].m_nCurFunVal = Common3.DB_MIN;// 把较优蚂蚁的路径长度设置为一个很小值
			}
			else{
				m_betterAnts[i].m_nCurFunVal = Common3.DB_MAX;// 把较优蚂蚁的路径长度设置为一个很大值
			}
		}
		for (int i = 0; i < Common3.N_IT_COUNT; i++) {
			m_bestAnts[i] = new Ant3();
			if(Common3.Type==1){
				m_bestAnts[i].m_nCurFunVal = Common3.DB_MIN;// 把最优蚂蚁的路径长度设置为一个很小值
			}
			else{
				m_bestAnts[i].m_nCurFunVal = Common3.DB_MAX;// 把最优蚂蚁的路径长度设置为一个很大值
			}
			
		}

	}

	/**
	 * 迭代
	 */
	public void Iterator() {
		// 迭代次数内进行循环
		for (int i = 0; i < Common3.N_IT_COUNT; i++) {

			// 蚂蚁搜索前,进行初始化
			for (int k = 0; k < Common3.N_ANT_COUNT; k++) {
				m_antAry[k].Init();
			}
			// 初始化信息素
			for (int q = 0; q < Common3.VAR_NUM; q++) {
				for (int k = 0; k < Common3.N_SPLIT_COUNT; k++) {
					Common3.g_Area[q][k] = Common3.DBS;
				}
			}

			// 循环
			for (int j = 0; j < Common3.N_IT_COUNT; j++) {
				// 循环k只蚂蚁
				for (int k = 0; k < Common3.N_ANT_COUNT; k++) {
					// 每次循环移动一次，转移到下一区域
					for (int q = 0; q < Common3.VAR_NUM; q++) {
						m_antAry[k].Move(q,ChooseNextCity(m_antAry[k],q));
					}
					
					//计算函数值
					m_antAry[k].UpdateVal();
					
				}
				
//				//检查每只蚂蚁的评估值
//				CheckAntVal();
				
				// 更新信息素
				UpdateArea();

			}
			
			//保存全局最优以及局部最优结果
			SaveBetterAndBest(i);

			//更新变量的取值范围
			UpdateVar();

			System.out.println(i+" "+m_bestAnts[i].m_nCurFunVal);
			
			if(m_bestAnts[i].m_nCurFunVal<=Common3.E){
				m_bestAnts[m_bestAnts.length-1] = m_bestAnts[i].clone();
				break;
			}
			
			
		}

	}

	/**
	 * 保存全局最优以及局部最优结果
	 * @param i 
	 */
	private void SaveBetterAndBest(int i) {
		
		if(Common3.Type==1){
			// 保存局部较优结果
			for (int j = 0; j < Common3.N_ANT_COUNT; j++) {
				if (m_antAry[j].m_nCurFunVal > m_betterAnts[i].m_nCurFunVal) {
					// 保存局部较优结果
					m_betterAnts[i] = m_antAry[j].clone();
				}
			}

			// 保存全局最优结果
			if (i == 0 || m_betterAnts[i].m_nCurFunVal > m_bestAnts[i - 1].m_nCurFunVal) {
				m_bestAnts[i] =m_betterAnts[i].clone();
			} else {
				m_bestAnts[i] = m_bestAnts[i - 1].clone();
			}
		}
		else{
			// 保存局部较优结果
			for (int j = 0; j < Common3.N_ANT_COUNT; j++) {
				if (m_antAry[j].m_nCurFunVal < m_betterAnts[i].m_nCurFunVal) {
					// 保存局部较优结果
					m_betterAnts[i] = m_antAry[j].clone();
				}
			}

			// 保存全局最优结果
			if (i == 0 || m_betterAnts[i].m_nCurFunVal < m_bestAnts[i - 1].m_nCurFunVal) {
				m_bestAnts[i] = m_betterAnts[i].clone();
			} else {
				m_bestAnts[i] = m_bestAnts[i - 1].clone();
			}
		}
		
	}

	/**
	 * 蚂蚁选择下一个城市 返回值为城市编号
	 */
	public int ChooseNextCity(Ant3 ant,int varIndex) {

		int nSelectedCity = ant.m_nCurAreaNo[varIndex];// 返回结果,初始化为-1
//		int nSelectedCity = -1;

		double dbTotal = 0.0;// 计算当前城市和没去过城市的信息素的总和
		double[] prob = new double[Common3.N_SPLIT_COUNT];// 用来保存各个城市被选中的概率
		
		for (int i = 0; i < Common3.N_SPLIT_COUNT; i++) {
			prob[i]=Common3.g_Area[varIndex][i];
			dbTotal=dbTotal+ Common3.g_Area[varIndex][i];
		}
		
		// 计算概率
		for (int i = 0; i < Common.N_SPLIT_COUNT; i++) {
			prob[i] = prob[i] / dbTotal;
		}

		// 进行轮盘选择
		double dbTemp = 0.0;
		if (dbTotal > 0.0)// 如果总的信息素大于0
		{
//			dbTemp=Common3.rnd(0.0, dbTotal);//取一个随机数
			dbTemp = Common3.rnd();

			for (int i = 0; i < Common3.N_SPLIT_COUNT; i++) {
//				if (ant.m_nCurAreaNo[varIndex] != i && prob[i]>0.0) {// 该区域没有去过

					dbTemp = dbTemp - prob[i];// 相当于轮盘

					if (dbTemp < 0.0) {
						// 轮盘停止转动,记下城市编号,跳出循环
						nSelectedCity = i;
						break;
					}
//				}
			}
		}

		return nSelectedCity;
	}

	/**
	 * 更新环境信息素
	 */
	public void UpdateArea() {
		
//		for (int q = 0; q < Common3.VAR_NUM; q++) {
//			// 临时数组,保存各只蚂蚁在区域新留下的信息素
//			double[] dbTempAry = new double[Common3.N_SPLIT_COUNT];
//
//			// 全部设置为0;
//			for (int i = 0; i < Common3.N_SPLIT_COUNT; i++) {
//				dbTempAry[i] = 0.0;
//			}
//
//			// 计算新增加的信息素,保存到临时变量
//			for (int i = 0; i < Common3.N_ANT_COUNT; i++) {
//				if(m_antAry[i].m_dbDiffVal>0.0){
//					dbTempAry[m_antAry[i].m_nCurAreaNo[q]] = Common3.DBQ / m_antAry[i].m_dbDiffVal;
//				}
//			}
//
//			// 更新环境信息素
//			for (int i = 0; i < Common3.N_SPLIT_COUNT; i++) {
//				// 最新的环境信息素 = 留存的信息素 + 新留下的信息素
//				Common3.g_Area[q][i] = (1 - Common3.ROU) * Common3.g_Area[q][i] + dbTempAry[i];
//			}
//		}
		
		//对蚂蚁进行排序
		Ant3 antTemp;
		for (int i=0;i<Common3.N_ANT_COUNT;i++)
		{
			for (int j=i+1;j<Common3.N_ANT_COUNT;j++)
			{
				if(Common3.Type==1){
					if (m_antAry[i].m_nCurFunVal < m_antAry[j].m_nCurFunVal)
					{
						antTemp=m_antAry[i];
						m_antAry[i]=m_antAry[j];
						m_antAry[j]=antTemp;
					}
				}
				else{
					if (m_antAry[i].m_nCurFunVal > m_antAry[j].m_nCurFunVal)
					{
						antTemp=m_antAry[i];
						m_antAry[i]=m_antAry[j];
						m_antAry[j]=antTemp;
					}
				}
			}
		}
		
		// 临时数组,保存各只蚂蚁在区域新留下的信息素
		double[][] dbTempAry = new double[Common3.VAR_NUM][Common3.N_SPLIT_COUNT];

		for (int q = 0; q < Common3.VAR_NUM; q++) {
			// 全部设置为0;
			for (int i = 0; i < Common3.N_SPLIT_COUNT; i++) {
				dbTempAry[q][i] = 0.0;
			}
		}

		for (int q = 0; q < Common3.VAR_NUM; q++) {
			// 计算新增加的信息素,保存到临时变量
			for (int i = 0; i < Common3.N_ANT_COUNT; i++) {
				dbTempAry[q][m_antAry[i].m_nCurAreaNo[q]] += (Common3.DBQ / m_antAry[i].m_nCurFunVal)*Math.sqrt(Common3.N_ANT_COUNT-i);
			}
		}

		for (int q = 0; q < Common3.VAR_NUM; q++) {
			// 更新环境信息素
			for (int i = 0; i < Common3.N_SPLIT_COUNT; i++) {
				// 最新的环境信息素 = 留存的信息素 + 新留下的信息素
				Common3.g_Area[q][i] = (1 - Common3.ROU) * Common3.g_Area[q][i] + dbTempAry[q][i];
			}
		}
		
	}
	
	/**
	 * 更新变量取值范围
	 */
	public void UpdateVar() {
		
		for (int q = 0; q < Common3.VAR_NUM; q++) {
			double dbTemp=-1.0;
			int dbIndex=0;
			
			for (int i = 0; i < Common3.N_SPLIT_COUNT; i++) {
				if(Common3.g_Area[q][i]>dbTemp){
					dbTemp=Common3.g_Area[q][i];
					dbIndex=i;
				}
			}
			
			dbTemp=Common3.VAR_LOW[q]+(Common3.VAR_UPPER[q]-Common3.VAR_LOW[q])*0.5/Common3.N_SPLIT_COUNT*(2*dbIndex+1);
			Common3.VAR_LOW[q]=dbTemp-(Common3.VAR_UPPER[q]-Common3.VAR_LOW[q])*0.5*Common3.VAR_DELTA;
			Common3.VAR_UPPER[q]=dbTemp+(Common3.VAR_UPPER[q]-Common3.VAR_LOW[q])*0.5*Common3.VAR_DELTA;
			
			Common3.VAR_LOW[q]=Math.max(Common3.VAR_Scale_LOW[q], Common3.VAR_LOW[q]);
			Common3.VAR_UPPER[q]=Math.min(Common3.VAR_Scale_UPPER[q], Common3.VAR_UPPER[q]);
			
		}
		
	}

	/**
	 * 展示结果
	 */
	public void ShowResult() {
//		System.out.println(m_bestAnts[m_bestAnts.length-1].m_nCurXVal+" - "+m_bestAnts[m_bestAnts.length-1].m_nCurFunVal);
		
//		FLineChart.initSeriesCollection();
//		FLineChart.AddSeriesCollection3(m_bestAnts, "Best");
		
		System.out.println(m_bestAnts[m_bestAnts.length-1].m_nCurFunVal);
		System.out.println(Arrays.toString(m_bestAnts[m_bestAnts.length-1].m_nCurVarVal));
		System.out.println("*----------------");
		
	}

}

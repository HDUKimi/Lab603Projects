package com.horstmann.violet.application.lmr.antcolony;

/**
 * 算法
 */
public class Solve {
	
	public String name;

	/**
	 * 蚂蚁数组
	 */
	public Ant[] m_antAry = new Ant[Common.N_ANT_COUNT];
	/**
	 * 定义一组蚂蚁,用来保存每一次搜索中较优结果,不参与搜索
	 */
	public Ant[] m_betterAnts = new Ant[Common.N_IT_COUNT];
	/**
	 * 定义一组蚂蚁,用来保存每一次搜索结束的最终最优结果,不参与搜索
	 */
	public Ant[] m_bestAnts = new Ant[Common.N_IT_COUNT];

	public Solve() {
	}
	
	public Solve(String name) {
		this.name=name;
	}

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

		Common.ALPHA = 1.0;
		Common.BETA = 1.0;
		Common.ROU = 0.3;
		Common.DBQ = 1.0;
		Common.DBS = 1.0;

		m_antAry = new Ant[Common.N_ANT_COUNT];
		m_betterAnts = new Ant[Common.N_IT_COUNT];
		m_bestAnts = new Ant[Common.N_IT_COUNT];
		
		Common.g_Area=new double[Common.N_SPLIT_COUNT];

	}

	/**
	 * 初始化蚂蚁
	 */
	public void initTrial() {

		for (int i = 0; i < Common.N_ANT_COUNT; i++) {
			m_antAry[i] = new Ant();
		}
		for (int i = 0; i < Common.N_IT_COUNT; i++) {
			m_betterAnts[i] = new Ant();
			if(Common.Type==1){
				m_betterAnts[i].m_nCurFunVal = Common.DB_MIN;// 把较优蚂蚁的路径长度设置为一个很小值
			}
			else{
				m_betterAnts[i].m_nCurFunVal = Common.DB_MAX;// 把较优蚂蚁的路径长度设置为一个很大值
			}
		}
		for (int i = 0; i < Common.N_IT_COUNT; i++) {
			m_bestAnts[i] = new Ant();
			if(Common.Type==1){
				m_bestAnts[i].m_nCurFunVal = Common.DB_MIN;// 把最优蚂蚁的路径长度设置为一个很小值
			}
			else{
				m_bestAnts[i].m_nCurFunVal = Common.DB_MAX;// 把最优蚂蚁的路径长度设置为一个很大值
			}
			
		}

	}

	/**
	 * 迭代
	 */
	public void Iterator() {
		// 迭代次数内进行循环
		for (int i = 0; i < Common.N_IT_COUNT; i++) {

			// 蚂蚁搜索前,进行初始化
			for (int k = 0; k < Common.N_ANT_COUNT; k++) {
				m_antAry[k].Init();
			}
			// 初始化信息素
			for (int k = 0; k < Common.N_SPLIT_COUNT; k++) {
				Common.g_Area[k] = Common.DBS;
			}

			// 循环
			for (int j = 0; j < Common.N_IT_COUNT; j++) {
				// 循环k只蚂蚁
				for (int k = 0; k < Common.N_ANT_COUNT; k++) {
					// 每次循环移动一次，转移到下一区域
					m_antAry[k].Move(ChooseNextCity(m_antAry[k]));
				}

				// 更新信息素
				UpdateArea();

			}
			
			//保存全局最优以及局部最优结果
			SaveBetterAndBest(i);

			//更新变量的取值范围
			UpdateVar();
			
		}

	}

	/**
	 * 保存全局最优以及局部最优结果
	 * @param i 
	 */
	private void SaveBetterAndBest(int i) {
		
		if(Common.Type==1){
			// 保存局部较优结果
			for (int j = 0; j < Common.N_ANT_COUNT; j++) {
				if (m_antAry[j].m_nCurFunVal > m_betterAnts[i].m_nCurFunVal) {
					// 保存局部较优结果
					try {
						m_betterAnts[i] = (Ant) m_antAry[j].clone();
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
			}

			// 保存全局最优结果
			if (i == 0 || m_betterAnts[i].m_nCurFunVal > m_bestAnts[i - 1].m_nCurFunVal) {
				try {
					m_bestAnts[i] = (Ant) m_betterAnts[i].clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			} else {
				try {
					m_bestAnts[i] = (Ant) m_bestAnts[i - 1].clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		}
		else{
			// 保存局部较优结果
			for (int j = 0; j < Common.N_ANT_COUNT; j++) {
				if (m_antAry[j].m_nCurFunVal < m_betterAnts[i].m_nCurFunVal) {
					// 保存局部较优结果
					try {
						m_betterAnts[i] = (Ant) m_antAry[j].clone();
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
			}

			// 保存全局最优结果
			if (i == 0 || m_betterAnts[i].m_nCurFunVal < m_bestAnts[i - 1].m_nCurFunVal) {
				try {
					m_bestAnts[i] = (Ant) m_betterAnts[i].clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			} else {
				try {
					m_bestAnts[i] = (Ant) m_bestAnts[i - 1].clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	/**
	 * 蚂蚁选择下一个城市 返回值为城市编号
	 */
	public int ChooseNextCity(Ant ant) {

		int nSelectedCity = ant.m_nCurAreaNo;// 返回结果,初始化为-1

		double dbTotal = 0.0;// 计算当前城市和没去过城市的信息素的总和
		double[] prob = new double[Common.N_SPLIT_COUNT];// 用来保存各个城市被选中的概率
		
		//生成各区域随机点，并计算对应的函数值
		for (int i = 0; i < Common.N_SPLIT_COUNT; i++) {
			ant.m_nAreaXVal[i]=Common.rndAreaXVal(i);
			ant.m_nAreaFunVal[i]=AcoMainFun.getFunResult(ant.m_nAreaXVal[i]);
		}
		
//		if(Common.Type==1){
//			if(ant.m_nCurFunVal<ant.m_nAreaXVal[ant.m_nCurAreaNo]){
//				ant.m_nCurFunVal=ant.m_nAreaXVal[ant.m_nCurAreaNo];
//				ant.m_nCurXVal=ant.m_nAreaXVal[ant.m_nCurAreaNo];
//			}
//		}
//		else{
//			if(ant.m_nCurFunVal>ant.m_nAreaXVal[ant.m_nCurAreaNo]){
//				ant.m_nCurFunVal=ant.m_nAreaXVal[ant.m_nCurAreaNo];
//				ant.m_nCurXVal=ant.m_nAreaXVal[ant.m_nCurAreaNo];
//			}
//		}

		for (int i = 0; i < Common.N_SPLIT_COUNT; i++) {

			if (ant.m_nCurAreaNo != i) {

				double fc, fi;
				// 计算区域i和当前所处的区域的目标函数值
//				fc=Math.max(ant.m_nCurFunVal, ant.m_nAreaXVal[ant.m_nCurAreaNo]);
				fc=ant.m_nCurFunVal;
				fi=ant.m_nAreaFunVal[i];
				
				if(Common.Type==1){
					//最大
					if (fc - fi < 0) {
						// 该区域i和当前区域的信息素
						prob[i] = Math.pow(Common.g_Area[ant.m_nCurAreaNo], Common.ALPHA) * Math.pow(fi - fc, Common.BETA);
						dbTotal = dbTotal + prob[i];// 累加信息素
					} else {
						// 差值大于0，不转移，选中概率为0
						prob[i] = 0.0;
					}
				}
				else{
					//最小
					if (fi - fc < 0) {
						// 该区域i和当前区域的信息素
						prob[i] = Math.pow(Common.g_Area[ant.m_nCurAreaNo], Common.ALPHA) * Math.pow(fc - fi, Common.BETA);
						dbTotal = dbTotal + prob[i];// 累加信息素
					} else {
						// 差值大于0，不转移，选中概率为0
						prob[i] = 0.0;
					}
				}

			}

		}

		// 计算概率
		for (int i = 0; i < Common.N_SPLIT_COUNT; i++) {
			prob[i] = prob[i] / dbTotal;
		}
		
//		System.out.println(Arrays.toString(prob));
//		System.out.println(dbTotal);

		// 进行轮盘选择
		double dbTemp = 0.0;
		if (dbTotal > 0.0)// 如果总的信息素大于0
		{
			// dbTemp=PublicFun.rnd(0.0, dbTotal);//取一个随机数
			dbTemp = Common.rnd();

			for (int i = 0; i < Common.N_SPLIT_COUNT; i++) {
				if (ant.m_nCurAreaNo != i && prob[i]>0.0) {// 该区域没有去过

					dbTemp = dbTemp - prob[i];// 相当于轮盘

					if (dbTemp < 0.0) {
						// 轮盘停止转动,记下城市编号,跳出循环
						nSelectedCity = i;
						break;
					}
				}

			}
		}

		return nSelectedCity;
	}

	/**
	 * 更新环境信息素
	 */
	public void UpdateArea() {
		// 临时数组,保存各只蚂蚁在区域新留下的信息素
		double[] dbTempAry = new double[Common.N_SPLIT_COUNT];

		// 全部设置为0;
		for (int i = 0; i < Common.N_SPLIT_COUNT; i++) {
			dbTempAry[i] = 0.0;
		}

		// 计算新增加的信息素,保存到临时变量
		for (int i = 0; i < Common.N_ANT_COUNT; i++) {
			if(m_antAry[i].m_dbDiffVal>0.0){
				dbTempAry[m_antAry[i].m_nCurAreaNo] = Common.DBQ / m_antAry[i].m_dbDiffVal;
			}
		}

		// 更新环境信息素
		for (int i = 0; i < Common.N_SPLIT_COUNT; i++) {
			// 最新的环境信息素 = 留存的信息素 + 新留下的信息素
			Common.g_Area[i] = (1 - Common.ROU) * Common.g_Area[i] + dbTempAry[i];
		}
		
		
	}
	
	/**
	 * 更新变量取值范围
	 */
	public void UpdateVar() {
		
		double dbTemp=-1.0;
		int dbIndex=0;
		
		for (int i = 0; i < Common.N_SPLIT_COUNT; i++) {
			if(Common.g_Area[i]>dbTemp){
				dbTemp=Common.g_Area[i];
				dbIndex=i;
			}
		}
		
		dbTemp=Common.VAR_LOW+(Common.VAR_UPPER-Common.VAR_LOW)*0.5/Common.N_SPLIT_COUNT*(2*dbIndex+1);
		Common.VAR_LOW=dbTemp-(Common.VAR_UPPER-Common.VAR_LOW)*0.5*Common.VAR_DELTA;
		Common.VAR_UPPER=dbTemp+(Common.VAR_UPPER-Common.VAR_LOW)*0.5*Common.VAR_DELTA;
		
		Common.VAR_LOW=Math.max(Common.VAR_Scale_LOW, Common.VAR_LOW);
		Common.VAR_UPPER=Math.min(Common.VAR_Scale_UPPER, Common.VAR_UPPER);
		
	}

	/**
	 * 展示结果
	 */
	public void ShowResult() {
		System.out.println(m_bestAnts[m_bestAnts.length-1].m_nCurXVal+" - "+m_bestAnts[m_bestAnts.length-1].m_nCurFunVal);
		
//		FLineChart.AddSeriesCollection(m_betterAnts, "Better");
		FLineChart.AddSeriesCollection(m_bestAnts, name);
		
	}

}

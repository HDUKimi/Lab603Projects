package com.horstmann.violet.application.lmr.antcolony;

/**
 * 函数
 */
public class AcoMainFun {
	
	static int index=0;
	

	/**
	 * 初始化
	 */
	public void InitData(){
		
		FLineChart.initSeriesCollection();
		ModelDataChart.initSeriesCollection();
		ModelUChart.initSeriesCollection();
		ModelYChart.initSeriesCollection();
		
		if(Common.N_FUN_ID==1){
			Common.VAR_Scale_LOW=-1;
			Common.VAR_Scale_UPPER=2;
			Common.VAR_LOW=-1;
			Common.VAR_UPPER=2;
			Common.Type=1;
		}
		else if(Common.N_FUN_ID==2){
			Model.n=31;
			Model.sumTime=new int[]{0,9,21,32,36,43,45,50,58,63,70,71,77,78,87,91,92,95,98,104,105,116,149,156,247,249,250,337,384,396,405,540};
			Model.endTime=540+10;
		}
		else if(Common.N_FUN_ID==3){
			Model.n=26;
			Model.sumTime=new int[]{0,9,21,32,36,43,45,50,58,63,70,71,77,78,87,91,92,95,98,104,105,116,149,156,247,249,250};
			Model.endTime=250+10;
		}
		else if(Common.N_FUN_ID==4){
			Model.n=39;
			Model.sumTime=new int[]{0,1,1,3,3,3,3,3,3,3,3,3,3,6,8,8,18,18,18,18,26,26,26,27,30,36,38,40,40,42,44,47,47,47,48,50,54,54,55,56};
			Model.endTime=56+10;
		}
		else if(Common.N_FUN_ID==5){
			Model.n=10;
			Model.sumTime=new int[]{0,7,18,26,36,51,73,93,118,146,181};
			Model.endTime=181+10;
		}
		else if(Common.N_FUN_ID==6){
			Model.n=10;
			Model.sumTime=new int[]{0,10,18,32,49,64,86,105,132,167,207};
			Model.endTime=207+10;
		}
		
		Solve solve=new Solve();
		Solve2 solve2=new Solve2();
		Solve2 solve3=new Solve2();
		
		if(Common.N_FUN_ID==1){
			solve.Start();
			Main.frame.bestAntTA.append("x: "+solve.m_bestAnts[solve.m_bestAnts.length-1].m_nCurXVal+"\n");
			Main.frame.bestAntTA.append("f(x): "+solve.m_bestAnts[solve.m_bestAnts.length-1].m_nCurFunVal+"\n");
		}
		else{
			Common.Type=-1;
			Model.initData();
			
			DealJMModel();
//			
//			DealGOModel();
//			
			DealMusaModel();
			
			DealLVModel();
			
			ModelDataChart.AddSeriesCollection();
//			ModelUChart.AddSeriesCollection();
//			ModelYChart.AddSeriesCollection();
			
		}
		
//		Main.frame.acoChartPanel.removeAll();
//		new FLineChart().createChart();
//		Main.frame.acoChartPanel.add(new FLineChart().createChart());
//		
//		Main.frame.funChartPanel.removeAll();
//		Main.frame.funChartPanel.add(new ModelDataChart().createChart());
//		
//		Main.frame.uChartPanel.removeAll();
//		Main.frame.uChartPanel.add(new ModelUChart().createChart());
//		
//		Main.frame.yChartPanel.removeAll();
//		Main.frame.yChartPanel.add(new ModelYChart().createChart());
//		
//		Main.frame.betterAntTA.setVisible(false);
//		Main.frame.bestAntTA.setCaretPosition(0);
//
//		Main.frame.rootPanel.setVisible(false);
//		Main.frame.rootPanel.repaint();
//		Main.frame.rootPanel.setVisible(true);
		
	}
	
	/**
	 * JM模型
	 */
	private void DealJMModel() {
		
		System.out.println("-----JM-----");
		
		Common.VAR_Scale_LOW=Model.n;
		Common.VAR_Scale_UPPER=100;
		Common.VAR_LOW=Model.n;
		Common.VAR_UPPER=100;
		
		index=1;
		
		Solve solve=new Solve("JM");
		solve.Start();
		
		JMModel.a=solve.m_bestAnts[solve.m_bestAnts.length-1].m_nCurXVal;
		JMModel.GetB();
		JMModel.GetData();
//		JMModel.ShowData();
		
	}
	
	/**
	 * GO模型
	 */
	private void DealGOModel() {
		System.out.println("-----Go-----");
		
		Common.VAR_Scale_LOW=0;
		Common.VAR_Scale_UPPER=1;
		Common.VAR_LOW=0;
		Common.VAR_UPPER=1;
		
		index=2;
		
		Solve solve=new Solve("GO");
		solve.Start();
		
		GOModel.b=solve.m_bestAnts[solve.m_bestAnts.length-1].m_nCurXVal;
		GOModel.GetA();
		GOModel.GetData();
//		GOModel.ShowData();
	}

	/**
	 * Musa模型
	 */
	private void DealMusaModel() {
		System.out.println("-----Musa-----");
		
		Common.VAR_Scale_LOW=0;
		Common.VAR_Scale_UPPER=1;
		Common.VAR_LOW=0;
		Common.VAR_UPPER=1;
		
		index=3;
		
		Solve solve=new Solve("Musa");
		solve.Start();
		
		MusaModel.b=solve.m_bestAnts[solve.m_bestAnts.length-1].m_nCurXVal;
		MusaModel.GetA();
		MusaModel.GetData();
//		MusaModel.ShowData();
	}

	/**
	 * LV模型
	 */
	private void DealLVModel() {
		System.out.println("-----LV-----");
		
//		Common2.Type=-1;
//		Common2.VAR_NUM=2;
//		Common2.VAR_LOW=new double[]{0,0};
//		Common2.VAR_UPPER=new double[]{100000,100000};
//		Common2.VAR_Scale_LOW=new double[]{0,0};
//		Common2.VAR_Scale_UPPER=new double[]{100000,100000};
//		
//		index=4;
//		
//		Solve2 solve2=new Solve2();
//		solve2.Start();
//		
//		LVModel.b0=solve2.m_bestAnts[solve2.m_bestAnts.length-1].m_nCurVarVal[0];
//		LVModel.b1=solve2.m_bestAnts[solve2.m_bestAnts.length-1].m_nCurVarVal[1];
		
		Common3.Type=-1;
		Common3.VAR_NUM=2;
		Common3.VAR_LOW=new double[]{0,0};
		Common3.VAR_UPPER=new double[]{100000,100000};
		Common3.VAR_Scale_LOW=new double[]{0,0};
		Common3.VAR_Scale_UPPER=new double[]{100000,100000};
		
		index=4;
		
		Solve3 solve3=new Solve3();
		solve3.Start();
		
		LVModel.b0=solve3.m_bestAnts[solve3.m_bestAnts.length-1].m_nCurVarVal[0];
		LVModel.b1=solve3.m_bestAnts[solve3.m_bestAnts.length-1].m_nCurVarVal[1];
		
		
		LVModel.a=LVModel.GetA();
		LVModel.GetData();
//		LVModel.ShowData();
	}

	/**
	 * 
	 * @param x	变量
	 * @return	函数值
	 */
	public static double getFunResult(double x){
		
		if(Common.N_FUN_ID==1){
			return Fun1(x);
		}
		else {
			if(index==1){
				return JMModel.GetA(x);
			}
			else if(index==2){
				return GOModel.GetB(x);
			}
			else if(index==3){
				return MusaModel.GetB(x);
			}
		}
		return 0;
		
	}
	
	private static double Fun1(double x) {
		return x*Math.sin(10.0*Math.PI*x)+2;
	}

//	private static double Fun2(double x) {
//		
//		int t,p;
//		double f;
//		
//		t=0;
//		p=0;
//		for(int i=1;i<=n;i++){
//			t+=fragTime[i];
//			p+=(i-1)*fragTime[i];
//		}
//		
//		f=0.0;
//		for(int i=1;i<=n;i++){
//			if(x-i+1==0){
//				continue;
//			}
//			f+=1.0/(x-i+1);
//		}
//		f-=(n*1.0)/(x-p*1.0/t);
//		if(f<0.0){
//			f=-f;
//		}
//		
//		return f;
//	}
	
}

package com.horstmann.violet.application.lmr.antcolony;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * JM可靠性模型
 */
public class JMModel {

	/**
	 * 模型固有错误数a
	 */
	public static double a;
	/**
	 * 模型比例常数φ
	 */
	public static double b;
	
	/**
	 * 可靠度
	 */
	public static double Reliable;
	/**
	 * 不可靠度
	 */
	public static double aoReliable;
	/**
	 * 失效率
	 */
	public static double FailureRate;
	/**
	 * 剩余故障数
	 */
	public static double ResidualFaults;
	/**
	 * 平均失效等待时间
	 */
	public static double MTTF;
	
	/**
	 * 预测的失效时间
	 */
	public static List<Double> PredictFailureTimeList;
	/**
	 * 预测的失效时间间隔
	 */
	public static List<Double> PredictFailureTimeFragList;
	/**
	 * 预测的不可靠度F(t)
	 */
	public static List<Double> PredictNoReliableList;
	/**
	 * 预测的概率分布密度f(t)
	 */
	public static List<Double> PredictDistributionDensityList;
	
	/**
	 * U图的序列
	 */
	public static List<Double> UList;
	/**
	 * Y图的序列
	 */
	public static List<Double> YList;
	
	/**
	 * 模型拟合度
	 */
	public static double FD;
	/**
	 * 模型准确性，序列似然度
	 */
	public static double PL;
	/**
	 * 模型偏差，U图的KS距离
	 */
	public static double KS_U;
	/**
	 * 模型偏差趋势，Y图的KS距离
	 */
	public static double KS_Y;
	/**
	 * 模型噪声
	 */
	public static double MN;
	
	
	/**
	 * 获取预测数据
	 */
	public static void GetData(){
		
		SetFailureRate();
		SetResidualFaults();
		SetMTTF();
		SetReliable();
		
		SetPredictFailureTimeAndFragList();
		SetPredictNoReliableList();
		SetPredictDistributionDensityList();
		
		setFD();
		setPL();
		setKS_U();
		setKS_Y();
		setMN();
	}
	
	/**
	 * 展示结果
	 */
	public static void ShowData(){
		Main.frame.TA_JM.setText("");
		Main.frame.TA_JM.append("----------JM模型----------\n");
		Main.frame.TA_JM.append("初始错误数 n: "+Model.n+"\n");
		Main.frame.TA_JM.append("固有错误数 N: "+a+"\n");
		Main.frame.TA_JM.append("比例常数 φ: "+b+"\n");
		Main.frame.TA_JM.append("\n");
		Main.frame.TA_JM.append("\n");
		Main.frame.TA_JM.append("可靠度： "+Reliable+"\n");
		Main.frame.TA_JM.append("失效率： "+FailureRate+"\n");
		Main.frame.TA_JM.append("剩余故障数： "+ResidualFaults+"\n");
		Main.frame.TA_JM.append("MTTF： "+MTTF+"\n");
		Main.frame.TA_JM.append("\n");
		Main.frame.TA_JM.append("模型拟合度： "+FD+"\n");
		Main.frame.TA_JM.append("模型准确性： "+PL+"\n");
		Main.frame.TA_JM.append("模型偏差： "+KS_U+"\n");
		Main.frame.TA_JM.append("模型偏差趋势： "+KS_Y+"\n");
		Main.frame.TA_JM.append("模型噪声： "+MN+"\n");
		
	}

	
	/**
	 * 计算固有错误数，求精度
	 * @param x	未知数，固有错误数
	 * @return	
	 */
	static double GetA(double x) {
		
		int t,p;
		double f;
		
		t=0;
		p=0;
		for(int i=1;i<=Model.n;i++){
			t+=Model.fragTime[i];
			p+=(i-1)*Model.fragTime[i];
		}
		
		f=0.0;
		for(int i=1;i<=Model.n;i++){
			if(x-i+1==0){
				continue;
			}
			f+=1.0/(x-i+1);
		}
		f-=(Model.n*1.0)/(x-1.0/t*p);
		
		if(f<0.0){
			f=-f;
		}
		
		return f;
	}
	
	/**
	 * 计算比例常数
	 */
	static void GetB() {
		
		int t,p;
		
		t=0;
		p=0;
		for(int i=1;i<=Model.n;i++){
			t+=Model.fragTime[i];
			p+=(i-1)*Model.fragTime[i];
		}
		
		b=Model.n*1.0/(a*t-p);
		
	}
	
	/**
	 * 计算失效率
	 */
	static void SetFailureRate(){
		FailureRate=b*(a-Model.n+1);
	}
	
	/**
	 * 计算剩余故障数
	 */
	static void SetResidualFaults(){
		ResidualFaults=a-Model.n;
	}
	
	/**
	 * 计算平均失效等待时间
	 */
	static void SetMTTF(){
		MTTF=1.0/(b*(a-Model.n+1));
	}
	
	/**
	 * 计算可靠度
	 */
	static void SetReliable(){
//		Reliable=Model.PowE(-b*(a-Model.n+1)*Model.fragTime[Model.n]);
		Reliable=Model.PowE(-b*(a-Model.n+1));
	}
	
	/**
	 * 计算预测的失效时间和失效时间间隔
	 */
	static void SetPredictFailureTimeAndFragList(){
		
		PredictFailureTimeList=new ArrayList<>();
		PredictFailureTimeList.add(0.0);
		
		PredictFailureTimeFragList=new ArrayList<>();
		PredictFailureTimeFragList.add(0.0);
		
		double sum=0.0;
		for(int i=1;i<=a;i++){
			double f=1.0/(b*(a-i+1));
			sum+=f;
			PredictFailureTimeFragList.add(f);
			PredictFailureTimeList.add(sum);
		}
	}
	
	/**
	 * 计算预测的不可靠度F(t)
	 */
	static void SetPredictNoReliableList(){
		
		PredictNoReliableList=new ArrayList<>();
		PredictNoReliableList.add(0.0);
		
		for(int i=1;i<Model.fragTime.length;i++){
			double f=1.0-Math.pow(Math.E, -b*(a-i+1)*Model.fragTime[i]);
			PredictNoReliableList.add(f);
		}
		
//		System.out.println(PredictNoReliableList.toString());
		
	}
	
	/**
	 * 计算预测的概率分布密度f(t)
	 */
	static void SetPredictDistributionDensityList() {
		
		PredictDistributionDensityList=new ArrayList<>();
		PredictDistributionDensityList.add(0.0);
		
		for(int i=1;i<Model.fragTime.length;i++){
			double f=b*(a-i+1)*Math.pow(Math.E, -b*(a-i+1)*Model.fragTime[i]);
			PredictDistributionDensityList.add(f);
		}
		
		System.out.println(PredictDistributionDensityList.toString());
		
	}
	
	/**
	 * 计算模型拟合度
	 */
	static void setFD(){

		double t1,t2,t;
		
		t=0.0;
		for(int i=1;i<PredictNoReliableList.size();i++){
			t1=PredictNoReliableList.get(i)-(i-1)*1.0/PredictNoReliableList.size();
			t2=i*1.0/PredictNoReliableList.size()-PredictNoReliableList.get(i);
			
			if(t1<0){
				t1=-t1;
			}
			if(t2<0){
				t2=-t2;
			}
			
			t=Math.max(t, Math.max(t1, t2));
			
		}
		
		FD=t;
		
	}
	
	/**
	 * 计算模型准确性，序列似然度
	 */
	static void setPL(){
		
		double f;
		
		f=1.0;
		
		for(int i=Model.n;i<PredictDistributionDensityList.size();i++){
			f*=PredictDistributionDensityList.get(i);
		}
		
		PL=f;
	
	}
	
	/**
	 * 计算模型偏差，U图的KS距离
	 */
	static void setKS_U(){
		
		UList=new ArrayList<>();
		
		for(int i=1;i<PredictNoReliableList.size();i++){
			UList.add(PredictNoReliableList.get(i));
		}
			
		UList.sort(new Comparator<Double>() {
			@Override
			public int compare(Double o1, Double o2) {
				if(o1.doubleValue()>o2.doubleValue()){
					return 1;
				}
				else if(o1.doubleValue()==o2.doubleValue()){
					return 0;
				}
				else{
					return -1;
				}
			}
		});
		
		double max=0.0;
		double t;
		
		for(int i=0;i<UList.size();i++){
//			if(UList.get(i)==0.0){
//				continue;
//			}
			t=Math.abs(UList.get(i)-i*1.0/(UList.size()+1));
			max=Math.max(max, t);
			t=Math.abs(UList.get(i)-(i+1)*1.0/(UList.size()+1));
			max=Math.max(max, t);
		}
//		max=Math.max(max, Math.abs(1-UList.size()*1.0/(UList.size()+1)));
		
		KS_U=max;
		
	}
	
	/**
	 * 模型偏差，Y图的KS距离
	 */
	static void setKS_Y(){
		
		YList=new ArrayList<>();
		List<Double> XList=new ArrayList<>();
		double x,s,p;
		
		s=0.0;
		for(int i=1;i<PredictNoReliableList.size();i++){
			x=-Math.log(1-PredictNoReliableList.get(i));
			XList.add(x);
			s+=x;
		}
		
		p=0.0;
		for(int i=0;i<XList.size()-1;i++){
			p+=XList.get(i);
			YList.add(p/s);
		}
			
		YList.sort(new Comparator<Double>() {
			@Override
			public int compare(Double o1, Double o2) {
				if(o1.doubleValue()>o2.doubleValue()){
					return 1;
				}
				else if(o1.doubleValue()==o2.doubleValue()){
					return 0;
				}
				else{
					return -1;
				}
			}
		});
		
		double max=0.0;
		double t;
		
//		max=Math.max(max, 1.0/(YList.size()+1));
		for(int i=0;i<YList.size();i++){
//			if(YList.get(i)==0.0){
//				continue;
//			}
			t=Math.abs((i+1)*1.0/(YList.size()+1)-YList.get(i));
			max=Math.max(max, t);
			t=Math.abs((i+2)*1.0/(YList.size()+1)-YList.get(i));
			max=Math.max(max, t);
		}
		
		KS_Y=max;
		
//		System.out.println(KS_Y);
		
	}
	
	/**
	 * 计算模型噪声
	 */
	static void setMN(){
		
		double f,t;
		
		t=0.0;
		for(int i=2;i<=Model.n;i++){
			f=(PredictFailureTimeFragList.get(i)-PredictFailureTimeFragList.get(i-1))/PredictFailureTimeFragList.get(i-1);
			if(f<0){
				f=-f;
			}
			t+=f;
		}
		
		MN=t;
		
	}
	
}

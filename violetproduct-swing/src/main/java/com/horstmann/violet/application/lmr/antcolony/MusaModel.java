package com.horstmann.violet.application.lmr.antcolony;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Musa可靠性模型
 */
public class MusaModel {

	/**
	 * 模型固有错误数a
	 */
	public static double a;
	/**
	 * 模型比例常数b
	 */
	public static double b;
	
	/**
	 * 可靠度
	 */
	public static double Reliable;
	/**
	 * 不可靠度
	 */
	public static double NoReliable;
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
		SetNoReliable();
		
		SetPredictFailureTimeAndFragList();
		SetPredicrNoReliableList();
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
		Main.frame.TA_Musa.setText("");
		Main.frame.TA_Musa.append("----------Musa模型----------\n");
		Main.frame.TA_Musa.append("初始错误数 n: "+Model.n+"\n");
		Main.frame.TA_Musa.append("固有错误数 a: "+a+"\n");
		Main.frame.TA_Musa.append("比例常数 b: "+b+"\n");
		Main.frame.TA_Musa.append("\n");
		Main.frame.TA_Musa.append("\n");
		Main.frame.TA_Musa.append("可靠度： "+Reliable+"\n");
		Main.frame.TA_Musa.append("失效率： "+FailureRate+"\n");
		Main.frame.TA_Musa.append("剩余故障数： "+ResidualFaults+"\n");
		Main.frame.TA_Musa.append("MTTF： "+MTTF+"\n");
		Main.frame.TA_Musa.append("\n");
		Main.frame.TA_Musa.append("模型拟合度： "+FD+"\n");
		Main.frame.TA_Musa.append("模型准确性： "+PL+"\n");
		Main.frame.TA_Musa.append("模型偏差： "+KS_U+"\n");
		Main.frame.TA_Musa.append("模型偏差趋势： "+KS_Y+"\n");
		Main.frame.TA_Musa.append("模型噪声： "+MN+"\n");
	}

	
	/**
	 * 计算a
	 */
	static void GetA() {
		
		a=Model.n*1.0/(1-Math.pow(Math.E, -b*Model.endTime));
		
	}
	
	/**
	 * 计算比例常数b
	 */
	static double GetB(double x) {
		
		double t,f;
		
		t=0.0;
		for(int i=1;i<=Model.n;i++){
			t+=Model.sumTime[i];
		}
		
		f=t;
		f+=(Model.n*Model.endTime*Math.pow(Math.E, -x*Model.endTime))*1.0/(1-Math.pow(Math.E, -x*Model.endTime));
		f-=Model.n*1.0/x;
		
		if(f<0.0){
			f=-f;
		}
		
		return f;
		
	}
	
	/**
	 * 计算失效率
	 */
	static void SetFailureRate(){
//		FailureRate=a*b*Model.PowE(-b*Model.endTime);
		FailureRate=a*b*Model.PowE(-b*Model.sumTime[Model.n]);
	}
	
	/**
	 * 计算剩余故障数
	 */
	static void SetResidualFaults(){
//		ResidualFaults=a-Model.n;
		ResidualFaults=a*Model.PowE(-b*Model.sumTime[Model.n]);
	}
	
	/**
	 * 计算平均失效等待时间
	 */
	static void SetMTTF(){
		MTTF=1.0/(a*b*Model.PowE(-b*Model.sumTime[Model.n]));
	}
	
	/**
	 * 计算可靠度
	 */
	static void SetReliable(){
//		Reliable=Model.PowE(-a*(1-Model.PowE(-b*Model.fragTime[Model.n]))*Model.PowE(-b*Model.sumTime[Model.n-1]));
		Reliable=Model.PowE(-a*b*Model.PowE(-b*Model.sumTime[Model.n]));
	}
	
	/**
	 * 计算不可靠度
	 */
	static void SetNoReliable(){
		NoReliable=1-Reliable;
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
			double f=1.0/(a*b*Model.PowE(-b*sum));
			sum+=f;
			PredictFailureTimeFragList.add(f);
			PredictFailureTimeList.add(sum);
		}
	}
	
	/**
	 * 计算预测的不可靠度F(t)
	 */
	static void SetPredicrNoReliableList(){
		
		PredictNoReliableList=new ArrayList<>();
		PredictNoReliableList.add(0.0);
		
		for(int i=1;i<Model.sumTime.length;i++){
			double f=1-Model.PowE(-a*(1-Model.PowE(-b*Model.fragTime[i]))*Model.PowE(-b*Model.sumTime[i-1]));
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
			double f=a*b*Model.PowE(-b*(Model.sumTime[i-1]+Model.fragTime[i]))*Model.PowE(-a*(1-Model.PowE(-b*Model.fragTime[i]))*Model.PowE(-b*Model.sumTime[i-1]));
			PredictDistributionDensityList.add(f);
		}
		
//		System.out.println(PredictDistributionDensityList.toString());
		
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
		
//		System.out.println(FD);
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
		
//		System.out.println(UList.toString());
		
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


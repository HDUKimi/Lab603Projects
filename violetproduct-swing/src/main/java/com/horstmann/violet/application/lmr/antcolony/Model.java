package com.horstmann.violet.application.lmr.antcolony;

/**
 * 可靠性模型参数
 */
public class Model {

	/**
	 * 失效数
	 */
	public static int n;
	/**
	 * 测试的总时间
	 */
	public static int endTime;
	/**
	 * 失效时刻
	 */
	public static int[] sumTime;
	/**
	 * 失效间隔
	 */
	public static int[] fragTime;
	/**
	 * 失效数量
	 */
	public static int[] sumFaults;
	/**
	 * 失效间隔数量
	 */
	public static int[] fragFaults;
	/**
	 * 不重复的失效时刻
	 */
	public static int[] sumTime_NoRepeat;
	/**
	 * 不重复的失效间隔
	 */
	public static int[] fragTime_NoRepeat;
	
	/**
	 * 初始化
	 */
	public static void initData(){
		
		//计算失效间隔
		fragTime=new int[n+1];
		for(int i=1;i<=n;i++){
			fragTime[i]=sumTime[i]-sumTime[i-1];
		}
		
		//计算失效数量
		int[] temp=new int[n+1];
		int sum=0,index=1;
		for(int i=1;i<=n;i++){
			sum++;
			while(i+1<=n&&sumTime[i]==sumTime[i+1]){
				sum++;
				i++;
			}
			temp[index++]=sum;
		}
		sumFaults=new int[index];
		System.arraycopy(temp, 0, sumFaults, 0, index);
		
		//计算失效间隔数量
		fragFaults=new int[index];
		for(int i=1;i<index;i++){
			fragFaults[i]=sumFaults[i]-sumFaults[i-1];
		}
		
		//计算不重复的失效时刻
		sumTime_NoRepeat=new int[index];
		index=1;
		for(int i=1;i<=n;i++){
			while(i+1<=n&&sumTime[i]==sumTime[i+1]){
				i++;
			}
			sumTime_NoRepeat[index++]=sumTime[i];
		}
		
		//计算不重复的失效间隔
		fragTime_NoRepeat=new int[index];
		for(int i=1;i<index;i++){
			fragTime_NoRepeat[i]=sumTime_NoRepeat[i]-sumTime_NoRepeat[i-1];
		}
		
//		System.out.println(sumTime.length+" - "+Arrays.toString(sumTime));
//		System.out.println(fragTime.length+" - "+Arrays.toString(fragTime));
//		System.out.println(temp.length+" - "+Arrays.toString(temp));
//		System.out.println(sumFaults.length+" - "+Arrays.toString(sumFaults));
//		System.out.println(fragFaults.length+" - "+Arrays.toString(fragFaults));
//		System.out.println(sumTime_NoRepeat.length+" - "+Arrays.toString(sumTime_NoRepeat));
		
	}
	
	/**
	 * 计算
	 * @param x
	 * @return
	 */
	public static double PowE(double x){
		return Math.pow(Math.E, x);
	}
	
	public static double ln(double x){
		return Math.log(x);
	}
	
}

package com.horstmann.violet.application.lmr.antcolony;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/** 
 * 蚂蚁类 
 * 使用对象的复制,必须实现Cloneable接口,然后重写Object中clone()方法 
 */  
public class Ant3 implements Serializable 
{  

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 蚂蚁转移区域的差值
     */
    public double m_dbDiffVal;
    /**
     * 选取过的变量
     */
    public int m_nMovedCount;
    
    /**
     * 当前所在区域的编号  
     */
    public int[] m_nCurAreaNo;
    /**
     * 当前所在区域的x值  
     */
    public double[] m_nCurVarVal;
    /**
     * 当前所在区域的函数值  
     */
    public double m_nCurFunVal;
    
    /**
     * 当前路径所在区域的编号  
     */
    public int[] m_nTempAreaNo;
    /**
     * 当前路径所在区域的x值  
     */
    public double[] m_nTempVarVal;
    /**
     * 当前路径所在区域的函数值  
     */
    public double m_nTempFunVal;
    
    /**
     * 该次循环中所有区域的随机x点
     */
    public double[][] m_nAreaVarVal;
    /**
     * 该次循环中所有区域的随机x点对应的函数值
     */
    public double[] m_nAreaFunVal;
      
      
    /** 
     * 初始化函数,蚂蚁每次搜索前调用该方法 
     */  
    public void Init()  
    {  
    	
    	m_nMovedCount=0;
    	
    	m_nCurAreaNo=new int[Common3.VAR_NUM];
    	m_nCurVarVal=new double[Common3.VAR_NUM];
    	m_nCurFunVal=Integer.MAX_VALUE;
    	
    	m_nTempAreaNo=new int[Common3.VAR_NUM];
    	m_nTempVarVal=new double[Common3.VAR_NUM];
    	
//    	//随机选择一个出发区域  
//    	for(int i=0;i<Common3.VAR_NUM;i++){
//    		m_nCurAreaNo[i]=Common3.rnd(0, Common3.N_SPLIT_COUNT);
//    		m_nCurVarVal[i]=Common3.rndAreaVarVal(i,m_nCurAreaNo[i]);
//    	}
//    	m_nCurFunVal=getAntVal();
//        
//        m_nAreaVarVal=new double[Common3.VAR_NUM][Common3.N_SPLIT_COUNT];
//        m_nAreaFunVal=new double[Common3.N_SPLIT_COUNT];
          
    }  
      
    @Override
	public Ant3 clone(){
    	Ant3 ant = null;

		try {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			ObjectOutputStream outputStream = new ObjectOutputStream(output);
			outputStream.writeObject(this);

			ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
			ObjectInputStream inputStream = new ObjectInputStream(input);
			ant = (Ant3) inputStream.readObject();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ant;
	}
    
      
    /** 
     * 蚂蚁移动到下一区域
     * 
     */  
    public void Move(int varIndex, int nAreaNo)  
    {  
//    	m_nCurAreaNo[varIndex]=nAreaNo;
//    	m_nCurVarVal[varIndex]=Common3.rndAreaVarVal(varIndex, nAreaNo);
    	
    	m_nTempAreaNo[varIndex]=nAreaNo;
    	m_nTempVarVal[varIndex]=Common3.rndAreaVarVal(varIndex, nAreaNo);
    	
    	m_nMovedCount++;
    } 
    
    /**
     * 更新函数值
     */
    public void UpdateVal() {
    	
//    	m_nCurFunVal=getAntVal();
    	
    	m_nTempFunVal=getAntVal(m_nTempVarVal);
    	
    	if(m_nTempFunVal<=m_nCurFunVal){
    		for(int i=0;i<m_nCurAreaNo.length;i++){
    			m_nCurAreaNo[i]=m_nTempAreaNo[i];
    		}
    		for(int i=0;i<m_nCurAreaNo.length;i++){
    			m_nCurVarVal[i]=m_nTempVarVal[i];
    		}
    		m_nCurFunVal=m_nTempFunVal;
    	}
    	else{
    		//局部搜索
    		for(int i=0;i<m_nCurAreaNo.length;i++){
    			int nAreaNo=m_nCurAreaNo[i];
    			m_nTempAreaNo[i]=nAreaNo;
    	    	m_nTempVarVal[i]=Common3.rndAreaVarVal(i, nAreaNo);
    		}
    		m_nTempFunVal=getAntVal(m_nTempVarVal);
    		
    		if(m_nTempFunVal<=m_nCurFunVal){
        		for(int i=0;i<m_nCurAreaNo.length;i++){
        			m_nCurAreaNo[i]=m_nTempAreaNo[i];
        		}
        		for(int i=0;i<m_nCurAreaNo.length;i++){
        			m_nCurVarVal[i]=m_nTempVarVal[i];
        		}
        		m_nCurFunVal=m_nTempFunVal;
        	}
    	}
    	
	}
      
    /** 
     * 返回蚂蚁经过的长度 
     */  
//    public void setAntVal()  
//    {  
//    	double r=0.0;
////    	for(int i=0;i<2;i++){
////    		r+=m_nCurVarVal[i]*m_nCurVarVal[i];
////    	}
//    	
//    	double[] a=m_nCurVarVal;
//    	
//		r = 3 * (1 - a[0]) * (1 - a[0])
//				* Math.pow(Math.E,
//						-a[0] * a[0] - (a[1] + 1) * (a[1] + 1))
//								- 10 * (a[0] / 5 - a[0] * a[0] * a[0] - a[1] * a[1] * a[1] * a[1] * a[1])
//										* Math.pow(Math.E, -a[0] * a[0] - a[1] * a[1])
//								- 1 / 3 * Math.pow(Math.E, -(a[0] + 1) * (a[0] + 1) - a[1] * a[1]);
//
//    	m_nCurFunVal= r;
//    	
//    } 
    
    public double getAntVal()  
    {  
    	double r=0.0;
//    	for(int i=0;i<2;i++){
//    		r+=m_nCurVarVal[i]*m_nCurVarVal[i];
//    	}
    	
    	double[] a=m_nCurVarVal;
    	
//		r = 3 * (1 - a[0]) * (1 - a[0])
//				* Math.pow(Math.E,
//						-a[0] * a[0] - (a[1] + 1) * (a[1] + 1))
//								- 10 * (a[0] / 5 - a[0] * a[0] * a[0] - a[1] * a[1] * a[1] * a[1] * a[1])
//										* Math.pow(Math.E, -a[0] * a[0] - a[1] * a[1])
//								- 1 / 3 * Math.pow(Math.E, -(a[0] + 1) * (a[0] + 1) - a[1] * a[1]);

//    	r=0.8/(a[0]*a[0]+a[1]*a[1]+1)+0.2*(a[0]*a[0]+3*a[1]*a[1]+1);
    	
//    	r=100*(a[0]*a[0]-a[1])*(a[0]*a[0]-a[1])+(1-a[0])*(1-a[0]);
    	
    	
//    	r=a[0]*Math.sin(10.0*Math.PI*a[0])+2;
    	
    	r=LVModel.GetB0B1(a[0], a[1]);
    	
    	return r;
    	
    }
    
    public double getAntVal(double[] a){
   
    	double r=0.0;
    	r=LVModel.GetB0B1(a[0], a[1]);
    	
    	return r;
    }
    

}  

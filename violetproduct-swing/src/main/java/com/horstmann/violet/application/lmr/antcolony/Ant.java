package com.horstmann.violet.application.lmr.antcolony;

/** 
 * 蚂蚁类 
 * 使用对象的复制,必须实现Cloneable接口,然后重写Object中clone()方法 
 */  
public class Ant implements Cloneable  
{  

    /**
     * 蚂蚁转移区域的差值
     */
    public double m_dbDiffVal;
    /**
     * 当前所在区域的编号  
     */
    public int m_nCurAreaNo;
    /**
     * 当前所在区域的x值  
     */
    public double m_nCurXVal;
    /**
     * 当前所在区域的函数值  
     */
    public double m_nCurFunVal;
    
    /**
     * 该次循环中所有区域的随机x点
     */
    public double[] m_nAreaXVal;
    /**
     * 该次循环中所有区域的随机x点对应的函数值
     */
    public double[] m_nAreaFunVal;
      
      
    /** 
     * 初始化函数,蚂蚁每次搜索前调用该方法 
     */  
    public void Init()  
    {  
    	//随机选择一个出发区域  
        m_nCurAreaNo=Common.rnd(0,Common.N_SPLIT_COUNT);
        
        //随机选择区域中的x点
        m_nCurXVal=Common.rndAreaXVal(m_nCurAreaNo);
        m_nCurFunVal=AcoMainFun.getFunResult(m_nCurXVal);
        
        m_nAreaXVal=new double[Common.N_SPLIT_COUNT];
        m_nAreaFunVal=new double[Common.N_SPLIT_COUNT];
          
    }  
      
    /** 
     * 覆盖Object中的clone()方法 
     * 实现对象的复制 
     */  
    protected Object clone() throws CloneNotSupportedException  
    {  
        return super.clone();  
    }  
    
      
    /** 
     * 蚂蚁移动到下一区域
     * 
     */  
    public void Move(int nAreaNo)  
    {  
    	
    	double fi, fj;
		// 计算当前区域i和下一转移区域j的目标函数值
		fi = m_nCurFunVal;
		fj = m_nAreaFunVal[nAreaNo];
		
		if(m_nCurAreaNo==nAreaNo){
			//局部搜索
			if(Common.Type==1){
				if(fj>fi){
					m_dbDiffVal=fj-fi;
				}
				else{
					m_dbDiffVal=0.0;
				}
			}
			else{
				if(fi>fj){
					m_dbDiffVal=fi-fj;
				}
				else{
					m_dbDiffVal=0.0;
				}
			}
			if(m_dbDiffVal>0.0){
				m_nCurXVal=m_nAreaXVal[nAreaNo];
		        m_nCurFunVal=m_nAreaFunVal[nAreaNo];
			}
		}
		else{
			if(Common.Type==1){
				m_dbDiffVal=fj-fi;
			}
			else{
				m_dbDiffVal=fi-fj;
			}
			m_nCurAreaNo=nAreaNo;//改变当前区域为选择的区域  
	        m_nCurXVal=m_nAreaXVal[nAreaNo];
	        m_nCurFunVal=m_nAreaFunVal[nAreaNo];
		}
		
    } 
      
    /** 
     * 返回蚂蚁经过的长度 
     */  
    public double getAntVal()  
    {  
        return this.m_nCurFunVal;  
    } 
}  

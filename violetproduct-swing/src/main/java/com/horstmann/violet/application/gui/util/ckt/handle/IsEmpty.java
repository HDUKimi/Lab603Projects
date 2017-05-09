package com.horstmann.violet.application.gui.util.ckt.handle;



public class IsEmpty {
	/**
	 * �ж�һ��DBM_element[][]�Ƿ�Ϊ�գ����Ϊ�շ���1�����򷵻�-1
	 * @param DBM
	 * @return
	 */
	public static int isEmpty(DBM_element[][] DBM){
		DBM_element[][] fDBM=Floyds.floyds(DBM);//��DBM�淶�������ж��Ƿ�Ϊ��
		int len=DBM.length;
		for(int i=0;i<len;i++){
			if(fDBM[i][i].getValue()!=0||fDBM[i][i].isStrictness()!=true){
				return 1;
			}
		}
		return -1;
	}
	
}
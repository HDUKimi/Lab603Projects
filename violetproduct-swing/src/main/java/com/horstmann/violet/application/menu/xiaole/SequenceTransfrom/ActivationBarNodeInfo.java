package com.horstmann.violet.application.menu.xiaole.SequenceTransfrom;

public class ActivationBarNodeInfo {

	private String Id;
	private String parentId;
	private String LocationX;
	private String LocationY;
	private String Height;//��������Height,Ϊ��ȷ��returnEdge�Ľ�����
	//����һ��activationBarNode
	
	public String getId() {
		return Id;
	}
	public String getHeight() {
		return Height;
	}
	public void setHeight(String height) {
		Height = height;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getLocationX() {
		return LocationX;
	}
	public void setLocationX(String locationX) {
		LocationX = locationX;
	}
	public String getLocationY() {
		return LocationY;
	}
	public void setLocationY(String locationY) {
		LocationY = locationY;
	}		
}

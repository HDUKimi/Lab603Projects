package com.horstmann.violet.application.gui.util.wujun.TDVerification;

import java.util.ArrayList;

import com.horstmann.violet.application.gui.util.wujun.TimingTransfrom.EAMessage;
import com.horstmann.violet.application.gui.util.wujun.TimingTransfrom.EAStateInfo;

public class RowStringsForDisplay {
	
	ArrayList<StateCompare> stateCompareList;
	ArrayList<MessageCompare> messageCompareList;
	
	public static class StateCompare {
		
		public StateCompare(EAStateInfo stateInfo, UppaalLocation location, String result) {
			super();
			this.stateInfo = stateInfo;
			this.location = location;
			this.result = result;
		}
		
		EAStateInfo stateInfo;
		UppaalLocation location;
		String result;
		public EAStateInfo getStateInfo() {
			return stateInfo;
		}
		public void setStateInfo(EAStateInfo stateInfo) {
			this.stateInfo = stateInfo;
		}
		public UppaalLocation getLocation() {
			return location;
		}
		public void setLocation(UppaalLocation location) {
			this.location = location;
		}
		public String getResult() {
			return result;
		}
		public void setResult(String result) {
			this.result = result;
		}
		
	}
	
	public static class MessageCompare {
		
		public MessageCompare(EAMessage message, UppaalTransition transition, String result) {
			super();
			this.message = message;
			this.transition = transition;
			this.result = result;
		}
		EAMessage message;
		UppaalTransition transition;
		String result;
		public EAMessage getMessage() {
			return message;
		}
		public void setMessage(EAMessage message) {
			this.message = message;
		}
		public UppaalTransition getTransition() {
			return transition;
		}
		public void setTransition(UppaalTransition transition) {
			this.transition = transition;
		}
		public String getResult() {
			return result;
		}
		public void setResult(String result) {
			this.result = result;
		}
		
	}

	public ArrayList<StateCompare> getStateCompareList() {
		return stateCompareList;
	}

	public void setStateCompareList(ArrayList<StateCompare> stateCompareList) {
		this.stateCompareList = stateCompareList;
	}

	public ArrayList<MessageCompare> getMessageCompareList() {
		return messageCompareList;
	}

	public void setMessageCompareList(ArrayList<MessageCompare> messageCompareList) {
		this.messageCompareList = messageCompareList;
	}
	
	
}

package com.horstmann.violet.application.gui.util.ckt.handle;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Automatic implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3860073665431298814L;
	
	private String name;//名称
	private State InitState;//初始状态
	private ArrayList<Transition> TransitionSet;//转换集合
	private ArrayList<State> StateSet;//状态集合
	private ArrayList<String> ClockSet;//时钟集合
	private ArrayList<String> InequalitySet;//不等式集合
	private double pathOfImportance;
	private double PathOfAllImpPercentage;
	
	
	
	public double getMax(){
		double max = StateSet.get(0).getImportance();
		for(State state:StateSet){
			if(max < state.getImportance()){
				max = state.getImportance();
			}
		}
		return max;
	}
	
	public double getMin(){
		double min = StateSet.get(0).getImportance();
		for(State state:StateSet){
			if(min > state.getImportance()){
				min = state.getImportance();
			}
		}
		return min;
	}
	
	public double getPathOfAllImpPercentage() {
		return PathOfAllImpPercentage;
	}

	public void setPathOfAllImpPercentage(double pathOfAllImpPercentage) {
		PathOfAllImpPercentage = pathOfAllImpPercentage;
	}

	
	public double getPathOfImportance() {
		return pathOfImportance;
	}
	public void setPathOfImportance(double pathOfImportance) {
		this.pathOfImportance = pathOfImportance;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public State getInitState() {
		return InitState;
	}
	public void setInitState(State initState) {
		InitState = initState;
	}
	public ArrayList<Transition> getTransitionSet() {
		return TransitionSet;
	}
	public void setTransitionSet(ArrayList<Transition> transitionSet) {
		TransitionSet = transitionSet;
	}
	public ArrayList<State> getStateSet() {
		return StateSet;
	}
	public void setStateSet(ArrayList<State> stateSet) {
		StateSet = stateSet;
	}
	public ArrayList<String> getClockSet() {
		return ClockSet;
	}
	public void setClockSet(ArrayList<String> clockSet) {
		ClockSet = clockSet;
	}
	public ArrayList<String> getInequalitySet() {
		return InequalitySet;
	}
	public void setInequalitySet(ArrayList<String> inequalitySet) {
		InequalitySet = inequalitySet;
	}
	
	@Override
	public Automatic clone(){
		Automatic automatic = null;

		try {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			ObjectOutputStream outputStream = new ObjectOutputStream(output);
			outputStream.writeObject(this);

			ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
			ObjectInputStream inputStream = new ObjectInputStream(input);
			automatic = (Automatic) inputStream.readObject();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return automatic;
	}
	
}

package com.horstmann.violet.application.gui.util.lmr.Evaluation;

import java.util.ArrayList;
import java.util.List;

public class EvaluationUppaalLocation {

	private String id;
	private String name;
	private String timeDuration;
	private boolean init = false;
	private boolean finl = false;
	private boolean visit = false;
	private List<EvaluationUppaalTransition> uppaalTransitions = new ArrayList<>();
	private List<List<EvaluationUppaalTuple>> uppaalPathTuples = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTimeDuration() {
		return timeDuration;
	}

	public void setTimeDuration(String timeDuration) {
		this.timeDuration = timeDuration;
	}

	public boolean isInit() {
		return init;
	}

	public void setInit(boolean init) {
		this.init = init;
	}

	public boolean isFinl() {
		return finl;
	}

	public void setFinl(boolean finl) {
		this.finl = finl;
	}

	public boolean isVisit() {
		return visit;
	}

	public void setVisit(boolean visit) {
		this.visit = visit;
	}

	public List<EvaluationUppaalTransition> getUppaalTransitions() {
		return uppaalTransitions;
	}

	public void setUppaalTransitions(List<EvaluationUppaalTransition> uppaalTransitions) {
		this.uppaalTransitions = uppaalTransitions;
	}

	public List<List<EvaluationUppaalTuple>> getUppaalPathTuples() {
		return uppaalPathTuples;
	}

	public void setUppaalPathTuples(List<List<EvaluationUppaalTuple>> uppaalPathTuples) {
		this.uppaalPathTuples = uppaalPathTuples;
	}

	@Override
	public String toString() {
		return "EvaluationUppaalLocation [id=" + id + ", name=" + name + ", timeDuration=" + timeDuration + ", init="
				+ init + ", finl=" + finl + ", uppaalTransitions.size()=" + uppaalTransitions.size()
				+ ", uppaalTransitions=" + uppaalTransitions + "]";
	}

}

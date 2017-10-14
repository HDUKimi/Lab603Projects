package com.horstmann.violet.application.gui.util.lmr.Evaluation;

public class EvaluationUppaalTuple {

	private EvaluationUppaalLocation uppaalLocation;
	private EvaluationUppaalTransition uppaalTransition;

	public EvaluationUppaalTuple(EvaluationUppaalLocation uppaalLocation, EvaluationUppaalTransition uppaalTransition) {
		this.uppaalLocation = uppaalLocation;
		this.uppaalTransition = uppaalTransition;
	}

	public EvaluationUppaalLocation getUppaalLocation() {
		return uppaalLocation;
	}

	public void setUppaalLocation(EvaluationUppaalLocation uppaalLocation) {
		this.uppaalLocation = uppaalLocation;
	}

	public EvaluationUppaalTransition getUppaalTransition() {
		return uppaalTransition;
	}

	public void setUppaalTransition(EvaluationUppaalTransition uppaalTransition) {
		this.uppaalTransition = uppaalTransition;
	}

}

package com.horstmann.violet.application.gui.util.lmr.Evaluation;

public class EvaluationUppaalTransition {

	private String id;
	private String name;
	private String source;
	private String target;
	private String timeDuration;

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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTimeDuration() {
		return timeDuration;
	}

	public void setTimeDuration(String timeDuration) {
		this.timeDuration = timeDuration;
	}

	@Override
	public String toString() {
		return "EvaluationUppaalTransition [id=" + id + ", name=" + name + ", source=" + source + ", target=" + target
				+ ", timeDuration=" + timeDuration + "]";
	}

}

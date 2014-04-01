package org.bugtracker.model;

public enum Priority {

	LOW("Baixa"), MEDIUM("M�dia"), HIGH("Alta");

	private String name;

	Priority(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

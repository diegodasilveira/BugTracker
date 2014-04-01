package org.bugtracker.model;

public enum Status {

	OPEN("Aberta"), IN_PROGRESS("Em progresso"), CLOSED("Fechada");

	private String name;

	Status(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

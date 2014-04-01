package org.bugtracker.filter;

import org.bugtracker.model.Priority;
import org.bugtracker.model.Project;
import org.bugtracker.model.Status;

public class IssueFilter {

	private Project project;

	private Priority priority;

	private Status status;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}

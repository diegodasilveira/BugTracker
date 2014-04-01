package org.bugtracker.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.bugtracker.model.Project;
import org.bugtracker.service.ProjectService;

@ManagedBean
@ViewScoped
public class ProjectController {

	@EJB
	private ProjectService projectService;

	private Project project;

	private List<Project> projects;

	@PostConstruct
	public void findAll() {
		projects = projectService.findAll();
	}

	public void prepare() {
		project = new Project();
	}

	public void saveProject() {

		if (project.getId() == null) {
			projectService.insert(project);
			projects.add(0, project);
		} else {
			projectService.update(project);
		}

	}

	public void deleteProject() {
		projectService.delete(project.getId());
		projects.remove(project);
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

}

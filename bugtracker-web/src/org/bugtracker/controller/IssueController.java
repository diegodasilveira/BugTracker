package org.bugtracker.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.bugtracker.filter.IssueFilter;
import org.bugtracker.model.Issue;
import org.bugtracker.model.Priority;
import org.bugtracker.model.Project;
import org.bugtracker.model.Status;
import org.bugtracker.model.User;
import org.bugtracker.service.IssueService;
import org.bugtracker.service.ProjectService;
import org.bugtracker.service.UserService;
import org.bugtracker.util.FacesUtil;

@ManagedBean
@ViewScoped
public class IssueController {

	@EJB
	private IssueService issueService;

	@EJB
	private ProjectService projectService;

	@EJB
	private UserService userService;

	private Issue issue;

	private List<Issue> issues;

	private List<Project> projects;

	private List<User> users;

	private List<SelectItem> priorities;

	private List<SelectItem> status;

	private IssueFilter issueFilter = new IssueFilter();

	public void findByFilter() {
		issues = issueService.findByFilter(issueFilter);
	}

	public void findByFilterCriteria() {
		issues = issueService.findByFilterCriteria(issueFilter);
	}

	public String viewDetail() {
		return "issueDetail.xhtml?faces-redirect=true&includeViewParams=true&id=" + issue.getId();
	}

	@PostConstruct
	public void findAll() {
		issues = issueService.findAll();
		projects = projectService.findAll();
		users = userService.findAllOrderByName();

		priorities = new ArrayList<SelectItem>();
		for (Priority priority : Priority.values()) {
			priorities.add(new SelectItem(priority.name(), priority.getName()));
		}

		status = new ArrayList<SelectItem>();
		for (Status stat : Status.values()) {
			status.add(new SelectItem(stat.name(), stat.getName()));
		}
	}

	public void prepare() {
		issue = new Issue();
	}

	public void saveIssue() {

		if (issue.getId() == null) {
			issue.setCreationDate(new Date());
			issue.setReporter(FacesUtil.getCurrentUser());
			issueService.insert(issue);
			issues.add(0, issue);
		} else {
			issueService.update(issue);
		}

	}

	public void deleteIssue() {
		issueService.delete(issue.getId());
		issues.remove(issue);
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public List<Issue> getIssues() {
		return issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<SelectItem> getPriorities() {
		return priorities;
	}

	public void setPriorities(List<SelectItem> priorities) {
		this.priorities = priorities;
	}

	public List<SelectItem> getStatus() {
		return status;
	}

	public void setStatus(List<SelectItem> status) {
		this.status = status;
	}

	public IssueFilter getIssueFilter() {
		return issueFilter;
	}

	public void setIssueFilter(IssueFilter issueFilter) {
		this.issueFilter = issueFilter;
	}
}

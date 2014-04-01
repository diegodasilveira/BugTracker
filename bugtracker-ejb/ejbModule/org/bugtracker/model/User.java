package org.bugtracker.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "fmk_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "LOGIN")
	private String login;

	@Column(name = "NAME")
	private String name;

	@Column(name = "PASSWORD")
	private String password;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Comment> comments;

	@OneToMany(mappedBy = "reporter", fetch = FetchType.LAZY)
	private List<Issue> reportedIssues;

	@OneToMany(mappedBy = "assignee", fetch = FetchType.LAZY)
	private List<Issue> assignedIssues;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Issue> getReportedIssues() {
		return reportedIssues;
	}

	public void setReportedIssues(List<Issue> reportedIssues) {
		this.reportedIssues = reportedIssues;
	}

	public List<Issue> getAssignedIssues() {
		return assignedIssues;
	}

	public void setAssignedIssues(List<Issue> assignedIssues) {
		this.assignedIssues = assignedIssues;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof User)) {
			return false;
		} else if (this == obj) {
			return true;
		} else {
			User u = (User) obj;
			return u.getId().equals(getId());
		}
	}

}

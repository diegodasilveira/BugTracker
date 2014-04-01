package org.bugtracker.controller;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.bugtracker.model.Comment;
import org.bugtracker.model.Issue;
import org.bugtracker.service.CommentService;
import org.bugtracker.service.IssueService;
import org.bugtracker.util.FacesUtil;

@ManagedBean
@ViewScoped
public class IssueDetailController {

	@EJB
	private IssueService issueService;

	@EJB
	private CommentService commentService;

	private Issue issue;

	private String id;

	private Comment comment;

	@PostConstruct
	public void findIssue() {
		id = FacesUtil.getParam("id");
		issue = issueService.findByID(Long.valueOf(id));
	}

	public void prepareComment() {
		comment = new Comment();
	}

	public void saveComment() {
		if (comment.getId() == null) {
			comment.setUser(FacesUtil.getCurrentUser());
			comment.setIssue(issue);
			comment.setCreationDate(new Date());
			commentService.insert(comment);
			issueService.refresh(issue.getId());
			issue.getComments().add(0, comment);
		} else {
			commentService.update(comment);
		}
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

}

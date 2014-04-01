package org.bugtracker.service;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.bugtracker.model.Comment;

@Stateless
@Local
public class CommentService extends AbstractService<Comment> {

	@Override
	protected Class<Comment> getEntityClass() {
		return Comment.class;
	}

}

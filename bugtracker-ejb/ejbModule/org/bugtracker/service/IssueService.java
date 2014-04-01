package org.bugtracker.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.bugtracker.filter.IssueFilter;
import org.bugtracker.model.Issue;

@Stateless
public class IssueService extends AbstractService<Issue> {

	@EJB
	ProjectService projectService;

	public List<Issue> findAll() {
		return em.createQuery("select i from Issue i order by i.id desc", Issue.class).getResultList();
	}

	@Override
	protected Class<Issue> getEntityClass() {
		return Issue.class;
	}

	public List<Issue> findByFilterCriteria(IssueFilter issueFilter) {

		CriteriaBuilder cBuilder = em.getCriteriaBuilder();

		CriteriaQuery<Issue> cQuery = cBuilder.createQuery(Issue.class);

		Root<Issue> issue = cQuery.from(Issue.class);

		if (issueFilter.getProject() != null) {
			cQuery.where(cBuilder.equal(issue.get("project"), issueFilter.getProject()));
		}
		if (issueFilter.getPriority() != null) {
			cQuery.where(cBuilder.equal(issue.get("priority"), issueFilter.getPriority()));
		}
		if (issueFilter.getStatus() != null) {
			cQuery.where(cBuilder.equal(issue.get("status"), issueFilter.getStatus()));
		}

		cQuery.orderBy(cBuilder.desc(issue.get("id")));

		return em.createQuery(cQuery).getResultList();
	}

	public List<Issue> findByFilter(IssueFilter issueFilter) {
		boolean where = true;
		StringBuilder queryBuilder = new StringBuilder("select i from Issue i ");

		if (issueFilter.getProject() != null) {
			if (where) {
				queryBuilder.append("where i.project = :project ");
				where = false;
			} else {
				queryBuilder.append("and i.project = :project ");
			}
		}

		if (issueFilter.getPriority() != null) {
			if (where) {
				queryBuilder.append("where i.priority = :priority ");
				where = false;
			} else {
				queryBuilder.append("and i.priority = :priority ");
			}
		}

		if (issueFilter.getStatus() != null) {
			if (where) {
				queryBuilder.append("where i.status = :status ");
				where = false;
			} else {
				queryBuilder.append("and i.status = :status ");
			}
		}
		queryBuilder.append("order by i.id desc");

		TypedQuery<Issue> query = em.createQuery(queryBuilder.toString(), Issue.class);
		if (issueFilter.getProject() != null) {
			query.setParameter("project", issueFilter.getProject());
		}
		if (issueFilter.getPriority() != null) {
			query.setParameter("priority", issueFilter.getPriority());
		}
		if (issueFilter.getStatus() != null) {
			query.setParameter("status", issueFilter.getStatus());
		}
		return query.getResultList();
	}

}

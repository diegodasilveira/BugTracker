package org.bugtracker.service;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.bugtracker.model.Project;

@Stateless
@Local
public class ProjectService extends AbstractService<Project> {

	public List<Project> findAll() {
		return em.createQuery("select p from Project p order by p.name", Project.class).getResultList();
	}

	@Override
	protected Class<Project> getEntityClass() {
		return Project.class;
	}

}

package org.bugtracker.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public abstract class AbstractService<T> {

	@PersistenceContext
	protected EntityManager em;

	protected abstract Class<T> getEntityClass();

	public void refresh(Long id) {
		T entity = em.getReference(getEntityClass(), id);
		em.refresh(entity);
	}

	public T findByID(Long id) {
		return em.find(getEntityClass(), id);
	}

	@Transactional
	public void update(T entity) {
		em.merge(entity);
	}

	@Transactional
	public void insert(T entity) {
		em.persist(entity);
	}

	@Transactional
	public void delete(Long id) {
		T entity = em.getReference(getEntityClass(), id);
		em.remove(entity);
	}

}

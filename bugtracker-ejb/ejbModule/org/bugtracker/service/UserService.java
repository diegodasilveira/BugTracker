package org.bugtracker.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.bugtracker.model.User;

@Stateless
public class UserService extends AbstractService<User> {

	public List<User> findAll() {
		return em.createQuery("select u from User u order by u.login", User.class).getResultList();
	}

	public List<User> findAllOrderByName() {
		return em.createQuery("select u from User u order by u.name", User.class).getResultList();
	}

	public User findByLoginAndPassword(String login, String password) {
		TypedQuery<User> query = em.createQuery("select u from User u where u.login = :login and u.password = :password", User.class);
		query.setParameter("login", login);
		query.setParameter("password", password);
		List<User> users = query.getResultList();
		if (users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
	}

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

}

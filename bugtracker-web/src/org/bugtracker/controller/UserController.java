package org.bugtracker.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.bugtracker.model.User;
import org.bugtracker.service.UserService;

@ManagedBean
@ViewScoped
public class UserController {

	@EJB
	private UserService userService;

	private User user;

	private List<User> users;

	@PostConstruct
	public void findAll() {
		users = userService.findAll();
	}

	public void prepare() {
		user = new User();
	}

	public void deleteUser() {
		userService.delete(user.getId());
		users.remove(user);
	}

	public void saveUser() {
		if (user.getId() == null) {
			userService.insert(user);
			users.add(0, user);
		} else {
			userService.update(user);
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}

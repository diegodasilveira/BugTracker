package org.bugtracker.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.bugtracker.model.User;
import org.bugtracker.service.UserService;
import org.bugtracker.util.FacesUtil;

@ManagedBean
@SessionScoped
public class LoginController {

	@EJB
	private UserService userService;

	private User user;

	private String login;

	private String password;

	public String processLogin() {
		user = userService.findByLoginAndPassword(login, password);
		if (user == null) {
			FacesUtil.getHttpSession().setAttribute("user", null);
			FacesUtil.getHttpSession().invalidate();
			return "/faces/login.xhtml";
		} else {
			FacesUtil.getHttpSession().setAttribute("user", user);
			return "/faces/index.xhtml";
		}
	}

	public String processLogout() {
		FacesUtil.getHttpSession().setAttribute("user", null);
		FacesUtil.getHttpSession().invalidate();
		return "/faces/login.xhtml";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

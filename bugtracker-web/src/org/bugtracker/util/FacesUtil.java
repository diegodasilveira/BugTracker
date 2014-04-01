package org.bugtracker.util;

import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.bugtracker.model.User;

public class FacesUtil {

	public static String getParam(String paramName) {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		return params.get(paramName);
	}

	public static HttpSession getHttpSession() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		return session;
	}

	public static User getCurrentUser() {
		return (User) FacesUtil.getHttpSession().getAttribute("user");
	}
}

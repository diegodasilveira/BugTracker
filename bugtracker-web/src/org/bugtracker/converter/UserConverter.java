package org.bugtracker.converter;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.bugtracker.model.User;
import org.bugtracker.service.UserService;

@ManagedBean
@ApplicationScoped
public class UserConverter implements Converter {

	@EJB
	UserService userService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return userService.findByID(Long.valueOf(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return ((User) value).getId().toString();
	}

}

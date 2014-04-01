package org.bugtracker.converter;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.bugtracker.model.Project;
import org.bugtracker.service.ProjectService;

@ManagedBean
@ApplicationScoped
public class ProjectConverter implements Converter {

	@EJB
	ProjectService projectService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return projectService.findByID(Long.valueOf(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return "";
		} else {
			return ((Project) value).getId().toString();
		}
	}

}

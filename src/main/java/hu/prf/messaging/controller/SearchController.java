package hu.prf.messaging.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import hu.prf.messaging.dao.GroupDAO;
import hu.prf.messaging.entity.Group;

@Named
@ViewScoped
public class SearchController implements Serializable {

	private static final long serialVersionUID = 1450699621154920134L;

	@Inject
	private GroupDAO groupDAO;
	
	private String substr="";

	public String getSubstr() {
		return substr;
	}

	public void setSubstr(String substr) {
		if(substr!=null)
		this.substr = substr;
	}

	public List<Group> search(){
		return groupDAO.findBySubStr(substr);
	}

}

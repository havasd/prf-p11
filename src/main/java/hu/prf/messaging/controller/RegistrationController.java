package hu.prf.messaging.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import hu.prf.messaging.dao.UserDAO;
import hu.prf.messaging.entity.User;

@Named
@ViewScoped
public class RegistrationController implements Serializable {

	private static final long serialVersionUID = -4776518416312717324L;

	@Inject
	private UserDAO userDAO;

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void init() {
		user = new User();
	}

	@Transactional
	public String register() {
		userDAO.persist(user);
		return "/content/login.xhtml?faces-redirect=true";
	}

}

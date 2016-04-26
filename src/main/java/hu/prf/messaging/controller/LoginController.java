package hu.prf.messaging.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import hu.prf.messaging.dao.UserDAO;
import hu.prf.messaging.entity.User;
import hu.prf.messaging.util.Session;

@Named
@ViewScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1450699621154920134L;

	@Inject
	private UserDAO userDAO;

	@Inject
	private Session session;

	private String email;

	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Transactional
	public String login() {
		List<User> results = userDAO.findByEmailAndPassword(email, password);
		if (!results.isEmpty()) {
			session.setUserId(results.get(0).getId());
			return "/content/home.xhtml?faces-redirect=true";
		}
		return "/content/login.xhtml?faces-redirect=true&a=login_error";
	}

}

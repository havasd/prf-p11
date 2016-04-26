package hu.prf.messaging.controller;

import java.io.Serializable;
import java.util.Objects;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import hu.prf.messaging.dao.UserDAO;
import hu.prf.messaging.entity.User;
import hu.prf.messaging.util.UserValidator;

@Named
@ViewScoped
public class RegistrationController implements Serializable {

	private static final long serialVersionUID = -4776518416312717324L;

	@Inject
	private UserDAO userDAO;

	@Inject
	private UserValidator userValidator;

	private User user;

	private String birth;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void init() {
		user = new User();
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	@Transactional
	public String register() {
		String errors = userValidator.validate(user, birth);

		boolean errExist = false;
		if (userDAO.checkEmailExistance(user.getEmail())) {
			errExist = true;
		}

		boolean errPass = false;
		if (user.getPassword().length() < 6) {
			errPass = true;
		}

		if (Objects.equals(errors, "") && !errPass && !errExist) {
			userDAO.persist(user);
			return "/content/login.xhtml?faces-redirect=true";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("/content/register.xhtml?faces-redirect=true&a=error")
			.append(errors);
		if (errExist) {
			sb.append("&err_exist=1");
		}
		if (errPass) {
			sb.append("&err_pass=1");
		}
		return sb.toString();
	}

}

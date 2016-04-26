package hu.prf.messaging.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Objects;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import hu.prf.messaging.dao.UserDAO;
import hu.prf.messaging.entity.User;
import hu.prf.messaging.util.Session;
import hu.prf.messaging.util.UserValidator;

@Named
@ViewScoped
public class UserController implements Serializable {

	private static final long serialVersionUID = -2130922499519394532L;

	@Inject
	private UserDAO userDAO;

	@Inject
	private Session session;

	@Inject
	private UserValidator userValidator;

	private int id;

	private User user;

	private String birth;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public void loadFromId() {
		user = userDAO.findEntity((long) id);
	}

	public void loadCurrent() {
		user = userDAO.findEntity(session.getUserId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		birth = sdf.format(user.getBirthDate());
	}

	@Transactional
	public String save() {
		String errors = userValidator.validate(user, birth);
		User old = userDAO.findEntity(session.getUserId());

		boolean errExist = false;
		if (!Objects.equals(old.getEmail(), user.getEmail()) && userDAO.checkEmailExistance(user.getEmail())) {
			errExist = true;
		}

		boolean errPass = false;
		if (user.getPassword() == null || user.getPassword().isEmpty()) {
			user.setPassword(old.getPassword());
		} else if (user.getPassword().length() < 6) {
			errPass = true;
		}

		if (Objects.equals(errors, "") && !errPass && !errExist) {
			userDAO.merge(user);
			return "/content/user-view.xhtml?faces-redirect=true&u=" + user.getId();
		}

		StringBuilder sb = new StringBuilder();
		sb.append("/content/user-edit.xhtml?faces-redirect=true&a=error")
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

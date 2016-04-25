package hu.prf.messaging.view;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import hu.prf.messaging.dao.UserDAO;
import hu.prf.messaging.entity.User;
import hu.prf.messaging.util.Session;

@Named
@ViewScoped
public class UserView implements Serializable {

	private static final long serialVersionUID = -2130922499519394532L;

	private int id;

	private User user;

	@Inject
	private UserDAO userDAO;

	@Inject
	private Session session;

	public void load() {
		user = userDAO.findEntity((long) id);
	}

	public void loadCurrent() {
		user = userDAO.findEntity(session.getUserId());
	}

	@Transactional
	public String save() {
		if (user.getPassword() == null || user.getPassword().isEmpty()) {
			User oldUser = userDAO.findEntity(session.getUserId());
			user.setPassword(oldUser.getPassword());
		}
		userDAO.merge(user);
		return "/content/user/view.xhtml?faces-redirect=true&u=" + user.getId();
	}

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

}

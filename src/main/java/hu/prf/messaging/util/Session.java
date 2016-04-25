package hu.prf.messaging.util;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import hu.prf.messaging.entity.User;

@Named
public class Session implements Serializable {

	private static final long serialVersionUID = -107194239698117736L;

	@Inject
	private HttpServletRequest request;

	public User getUser() {
		return (User) request.getSession().getAttribute("user");
	}

	public void setUser(User user) {
		request.getSession().setAttribute("user", user);
	}

	public boolean isLoggedIn() {
		return request.getSession().getAttribute("user") != null;
	}

	public void invalidate() {
		request.getSession().invalidate();
	}

}

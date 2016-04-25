package hu.prf.messaging.util;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
public class Session implements Serializable {

	private static final long serialVersionUID = -107194239698117736L;

	@Inject
	private HttpServletRequest request;

	public long getUserId() {
		return (Long) request.getSession().getAttribute("user-id");
	}

	public void setUserId(long userId) {
		request.getSession().setAttribute("user-id", userId);
	}

	public boolean isLoggedIn() {
		return request.getSession().getAttribute("user-id") != null;
	}

	public void invalidate() {
		request.getSession().invalidate();
	}

}

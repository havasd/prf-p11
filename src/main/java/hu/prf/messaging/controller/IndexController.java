package hu.prf.messaging.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import hu.prf.messaging.util.HttpParam;
import hu.prf.messaging.util.Session;

@Named
public class IndexController implements Serializable {

	private static final long serialVersionUID = 4791311366878719085L;

	@Inject
	private Session session;

	@Inject
	private HttpParam httpParam;

	public void init() {
		if (Objects.equals(httpParam.getParam("a"), "logout")) {
			session.invalidate();
		}

		try {
			if (session.isLoggedIn()) {
				FacesContext.getCurrentInstance().getExternalContext().redirect("content/home.xhtml");
			} else {
				FacesContext.getCurrentInstance().getExternalContext().redirect("content/login.xhtml");
			}
		} catch (IOException ex) {
			// that's a problem...
		}
	}

	public boolean loggedIn() {
		return session.isLoggedIn();
	}

	public long userId() {
		return session.getUserId();
	}

}

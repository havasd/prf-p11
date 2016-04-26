package hu.prf.messaging.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import hu.prf.messaging.util.Session;

@Named
@ViewScoped
public class IndexController implements Serializable {

	private static final long serialVersionUID = 4791311366878719085L;

	@Inject
	private Session session;

	private String action;

	public void init() {
		System.out.println(action);
		try {
			if (Objects.equals(action, "logout")) {
				session.invalidate();
				FacesContext.getCurrentInstance().getExternalContext().redirect("content/login.xhtml");
			} else if (session.isLoggedIn()) {
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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}

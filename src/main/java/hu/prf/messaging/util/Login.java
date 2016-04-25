package hu.prf.messaging.util;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import hu.prf.messaging.controller.AbstractEntityAction;
import hu.prf.messaging.dao.GenericDAO;
import hu.prf.messaging.dao.UserDAO;
import hu.prf.messaging.entity.Address;
import hu.prf.messaging.entity.User;

@Named
@ViewScoped
public class Login extends AbstractEntityAction<User, Long> implements Serializable {

	private static final long serialVersionUID = 7534503603164943071L;

	private static final String NAVIGATION_TARGET_AFTER_PERSIST = "/index";

	@Inject
	private Session session;

	@Inject
	private Credentials credentials;

	@Inject
	private HttpParam httpParam;

	@Inject
	private UserDAO userDAO;

	public Login() {
		super(User.class);
		load();
	}

	public void init() {
		logger.severe("Login#init");
		logger.severe("param(a) = " + httpParam.getParam("a"));
		if (Objects.equals(httpParam.getParam("a"), "logout")) {
			logout();
		}

		if (session.isLoggedIn()) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("content/home.xhtml");
			} catch (Exception e) {
				logger.severe("Login#login: redirect failed");
			}
		}
	}

	public void login() {
		logger.severe("Entered login.");
		List<User> results = ((UserDAO)getEntityDao()).findByEmailAndPassword(credentials.getEmail(), credentials.getPassword());
		if (!results.isEmpty()) {
			User user = results.get(0);
			session.setUser(user);

			setEntity(user);
			setId(getEntity().getId());
			logger.severe("Account identified. User: " + getEntity().toString());

			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("content/home.xhtml");
			} catch (Exception e) {
				logger.severe("Login#login: redirect failed");
			}
		}
		else {
			logger.severe("Account not found returning.");
			// perhaps add code here to report a failed login
		}
	}

	public void logout() {
		logger.severe("Logging out.");

		session.invalidate();

		setEntity(null);
		setId(null);
	}

	public boolean isLoggedIn() {
		return session.isLoggedIn();
	}

	@Override
	protected void afterCreation() {
		getEntity().setAddress(new Address());
	}

	@Override
	protected GenericDAO<User, Long> getEntityDao() {
		return userDAO;
	}

	@Override
	protected String getNavigationTargetAfterPersist() {
		return NAVIGATION_TARGET_AFTER_PERSIST;
	}

	public User getCurrentUser() {
		return session.getUser();
	}

}

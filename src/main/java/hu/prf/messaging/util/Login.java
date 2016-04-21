package hu.prf.messaging.util;

import hu.prf.messaging.controller.core.AbstractEntityAction;
import hu.prf.messaging.dao.core.GenericDAO;
import hu.prf.messaging.dao.user.UserDAO;
import hu.prf.messaging.entity.place.Address;
import hu.prf.messaging.entity.user.User;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import java.util.logging.Logger;

@SessionScoped @Named
public class Login extends AbstractEntityAction<User, Long> implements Serializable {
   /**
	 * 
	 */
	private static final long serialVersionUID = 7534503603164943071L;

	private static final String NAVIGATION_TARGET_AFTER_PERSIST = "/index";
	
	@Inject
	private Logger logger;
	
	@Inject
	private Credentials credentials;

	@Inject
	private UserDAO userDAO;
	
	public Login() {
		super(User.class);
		load();
	}
	
	public void login() {
		logger.severe("Entered login.");
		List<User> results = ((UserDAO)getEntityDao()).findByEmailAndPassword(credentials.getEmail(), credentials.getPassword());
		if (!results.isEmpty()) {
			setEntity(results.get(0));
			setId(getEntity().getId());
			logger.severe("Account identified. User: " + getEntity().toString());
		}
		else {
			logger.severe("Account not found returning.");
			// perhaps add code here to report a failed login
		}
	}

	public void logout() {
		logger.severe("Logging out." + getEntity().toString());
		persist();
		setEntity(null);
		setId(null);
	}

	public boolean isLoggedIn() {
		return getId() != null;
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

	
	@Produces @LoggedIn User getCurrentUser() {
		return getEntity();
	}
}
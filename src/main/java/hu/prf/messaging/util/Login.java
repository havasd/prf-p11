package hu.prf.messaging.util;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import hu.prf.messaging.controller.core.AbstractEntityAction;
import hu.prf.messaging.dao.core.GenericDAO;
import hu.prf.messaging.dao.user.UserDAO;
import hu.prf.messaging.entity.Address;
import hu.prf.messaging.entity.User;

@Named
public class Login extends AbstractEntityAction<User, Long> implements Serializable {
   /**
	 * 
	 */
	private static final long serialVersionUID = 7534503603164943071L;

	private static final String NAVIGATION_TARGET_AFTER_PERSIST = "/index";
	
	@Inject
	private HttpServletRequest request;
	
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
	
	public void init() {
		logger.severe("Login#init: param(a) = " + request.getParameter("a"));
		if (Objects.equals(request.getParameter("a"), "logout")) {
			logout();
		}
	}
	
	public void login() {
		logger.severe("Entered login.");
		List<User> results = ((UserDAO)getEntityDao()).findByEmailAndPassword(credentials.getEmail(), credentials.getPassword());
		if (!results.isEmpty()) {
			User user = results.get(0);
			request.getSession().setAttribute("user", user);
			
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
		
		request.getSession().invalidate();
		
		setEntity(null);
		setId(null);
	}

	public boolean isLoggedIn() {
		return request.getSession().getAttribute("user") != null;
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
		return (User) request.getSession().getAttribute("user");
	}
	
}

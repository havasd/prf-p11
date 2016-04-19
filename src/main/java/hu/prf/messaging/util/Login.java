package hu.prf.messaging.util;

import hu.prf.messaging.dao.user.UserDAO;
import hu.prf.messaging.entity.user.User;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped @Named
public class Login implements Serializable {
   /**
	 * 
	 */
	private static final long serialVersionUID = 7534503603164943071L;

	@Inject
	private Credentials credentials;

	private User user;

	@Inject
	private UserDAO userDAO;

	public void login() {
		List<User> results = userDAO.findByEmailAndPassword(credentials.getEmail(), credentials.getPassword());
		if (!results.isEmpty()) {
			user = results.get(0);
		}
		else {
			// perhaps add code here to report a failed login
		}
	}

	public void logout() {
		user = null;
	}

	public boolean isLoggedIn() {
		return user != null;
	}

	@Produces @LoggedIn User getCurrentUser() {
		return user;
	}
}

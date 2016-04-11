package hu.prf.messaging.dao.user;

import hu.prf.messaging.dao.core.GenericDAO;
import hu.prf.messaging.entity.user.User;

public class UserDAO extends GenericDAO<User, Long> {

	private static final long serialVersionUID = -5859058016736013679L;

	public UserDAO() {
		super(User.class);
	}

}

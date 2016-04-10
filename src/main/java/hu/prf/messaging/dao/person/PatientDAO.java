package hu.prf.messaging.dao.person;

import hu.prf.messaging.dao.core.GenericDAO;
import hu.prf.messaging.entity.person.User;

public class PatientDAO extends GenericDAO<User, Long> {

	private static final long serialVersionUID = -5859058016736013679L;

	public PatientDAO() {
		super(User.class);
	}

}

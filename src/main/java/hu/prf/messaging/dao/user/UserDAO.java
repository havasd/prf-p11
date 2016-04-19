package hu.prf.messaging.dao.user;

import hu.prf.messaging.dao.core.GenericDAO;
import hu.prf.messaging.entity.user.User;

import java.util.List;

import javax.persistence.TypedQuery;

public class UserDAO extends GenericDAO<User, Long> {

	private static final long serialVersionUID = -5859058016736013679L;

	public UserDAO() {
		super(User.class);
	}

	public  List<User> findByEmailAndPassword(String email, String password) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("select ");
		queryBuilder.append("	u ");
		queryBuilder.append("from ");
		queryBuilder.append("	User u ");
		queryBuilder.append("where ");
		queryBuilder.append("	u.email = :email and u.password = :password");
		
		TypedQuery<User> query = getEntityManager().createQuery(queryBuilder.toString(), getEntityClass());
		query.setParameter("email", email);
		query.setParameter("password", password);
		return query.getResultList();
	}
}

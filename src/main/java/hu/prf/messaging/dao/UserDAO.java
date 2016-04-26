package hu.prf.messaging.dao;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import hu.prf.messaging.entity.User;
import hu.prf.messaging.util.Session;

@Named
public class UserDAO extends GenericDAO<User, Long> {

	private static final long serialVersionUID = -5859058016736013679L;

	@Inject
	private Session session;

	public UserDAO() {
		super(User.class);
	}

	public List<User> findByEmailAndPassword(String email, String password) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("select ");
		queryBuilder.append("	u ");
		queryBuilder.append("from ");
		queryBuilder.append("	User u ");
		queryBuilder.append("where ");
		queryBuilder.append("	u.email = :email and u.password = :password");

		TypedQuery<User> query = getEntityManager().createQuery(queryBuilder.toString(), User.class);
		query.setParameter("email", email);
		query.setParameter("password", password);
		return query.getResultList();
	}

	public List<User> listOtherUsers() {
		long id = session.getUserId();
		TypedQuery<User> q = getEntityManager().createQuery("select u from User u where u.id <> :id order by u.name", User.class);
		q.setParameter("id", id);
		return q.getResultList();
	}

	public List<User> getUnseenMessageSenders() {
		TypedQuery<User> q = getEntityManager().createQuery(
			"select u " +
			"from User u " +
				"where u in (" +
				"select m.sender " +
				"from Message m " +
				"where m.reciever.id = :uid and m.seen = false" +
			")",
			User.class);
		q.setParameter("uid", session.getUserId());
		return q.getResultList();
	}

	public User getLoggedInUser() {
		return findEntity(session.getUserId());
	}

}

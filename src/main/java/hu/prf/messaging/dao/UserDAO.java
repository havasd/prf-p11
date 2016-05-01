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
	
	public List<User> getOldMessagePartners() {
		TypedQuery<User> q = getEntityManager().createQuery(
			"select u " +
			"from User u " +
				"where u in (" +
				"select m.sender " +
				"from Message m " +
				"where m.reciever.id = :uid and m.seen = true " +
				"order by m.date" +
			")",
			User.class);
		q.setParameter("uid", session.getUserId());
		List<User> l1= q.getResultList();
		List<User> l2=getOldMessagePartners2();
		boolean add=true;
		for(User u2:l2){
			for(User u1:l1){
				if(u1.getId()==u2.getId())
					add=false;
			}
			if(add)
				l1.add(u2);
			else
				add=true;
		}
		return l1;
	}
	public List<User> getOldMessagePartners2() {
		TypedQuery<User> q = getEntityManager().createQuery(
			"select u " +
			"from User u " +
				"where u in (" +
				"select m.reciever " +
				"from Message m " +
				"where m.sender.id = :uid " +
				"order by m.date" +
			")",
			User.class);
		q.setParameter("uid", session.getUserId());
		return q.getResultList();
	}

	public User getLoggedInUser() {
		return findEntity(session.getUserId());
	}

	/**
	 * @return true if email already exist
	 */
	public boolean checkEmailExistance(String email) {
		TypedQuery<Long> q = getEntityManager().createQuery(
			"select count(u.id) " +
			"from User u " +
			"where u.email = :email ",
			Long.class);
		q.setParameter("email", email);
		return q.getSingleResult() > 0;
	}
	
	public boolean isInGroup(Long groupID){
		TypedQuery<Long> q = getEntityManager().createQuery(
			"select count(m.id) " +
			"from Membership m " +
			"where m.group.id = :groupID and m.user.id = :id",
			Long.class);
		q.setParameter("groupID", groupID);
		q.setParameter("id", session.getUserId());
		return q.getSingleResult() > 0;
	}

}

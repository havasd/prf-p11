package hu.prf.messaging.dao;


import javax.inject.Named;
import javax.persistence.TypedQuery;

import hu.prf.messaging.entity.Membership;

@Named
public class MembershipDAO extends GenericDAO<Membership, Long> {

	private static final long serialVersionUID = -5899328697322490825L;

	public MembershipDAO() {
		super(Membership.class);
	}

	public Membership getByIds(long userId, long groupID) {
		TypedQuery<Membership> q = getEntityManager().createQuery(
				"select m " +
				"from Membership m " +
				"where m.group.id = :groupID and m.user.id = :userId",
				Membership.class);
			q.setParameter("groupID", groupID);
			q.setParameter("userId", userId);
			return q.getSingleResult();
	}

	public Long count(long id) {
		TypedQuery<Long> q = getEntityManager().createQuery(
				"select count(m.group.id) " +
				"from Membership m " +
				"where m.group.id = :id ",
				Long.class);
		q.setParameter("id", id);
		return q.getSingleResult();
	}

}

package hu.prf.messaging.dao;

import java.util.List;

import javax.inject.Named;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import hu.prf.messaging.entity.Message;

@Named
public class MessageDAO extends GenericDAO<Message, Long> {

	private static final long serialVersionUID = -5899328697322490825L;

	public MessageDAO() {
		super(Message.class);
	}

	public List<Message> getTwoUsersMessages(long userId1, long userId2) {
		TypedQuery<Message> q = getEntityManager().createQuery(
			"select m " +
			"from Message m " +
			"where m.sender.id = :uid1 and m.reciever.id = :uid2 or " +
			      "m.sender.id = :uid2 and m.reciever.id = :uid1 " +
			"order by m.date",
			Message.class);
		q.setParameter("uid1", userId1);
		q.setParameter("uid2", userId2);
		return q.getResultList();
	}

	public void setMessagesToSeen(long loggedInUid, long otherUid) {
		Query q = getEntityManager().createQuery(
			"update Message " +
			"set seen = true " +
			"where sender.id = :other_uid and reciever.id = :logged_in_uid");
		q.setParameter("logged_in_uid", loggedInUid);
		q.setParameter("other_uid", otherUid);
		q.executeUpdate();
	}

}

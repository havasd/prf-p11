package hu.prf.messaging.dao;

import java.util.List;

import javax.inject.Named;
import javax.persistence.TypedQuery;

import hu.prf.messaging.entity.GroupMessage;

@Named
public class GroupMessageDAO extends GenericDAO<GroupMessage, Long> {

	private static final long serialVersionUID = -5899328697322490825L;

	public GroupMessageDAO() {
		super(GroupMessage.class);
	}

	public List<GroupMessage> getGroupMessages(long groupId) {
		TypedQuery<GroupMessage> q = getEntityManager().createQuery(
			"select m " +
			"from GroupMessage m " +
			"where m.group.id = :groupId " +
			"order by m.date",
			GroupMessage.class);
		q.setParameter("groupId", groupId);
		q.setParameter("groupId", groupId);
		return q.getResultList();
	}

}

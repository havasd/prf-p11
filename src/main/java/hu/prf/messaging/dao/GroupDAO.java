package hu.prf.messaging.dao;

import java.util.List;

import javax.inject.Named;
import javax.persistence.TypedQuery;
import hu.prf.messaging.entity.Group;

@Named
public class GroupDAO extends GenericDAO<Group, Long> {

	private static final long serialVersionUID = -5859058016736013679L;
	
	public GroupDAO() {
		super(Group.class);
	}

	public List<Group> findBySubStr(String substr) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("select ");
		queryBuilder.append("	g ");
		queryBuilder.append("from ");
		queryBuilder.append("	Group g ");
		queryBuilder.append("where ");
		queryBuilder.append("	g.name  LIKE '%"+substr+"%' ");
		queryBuilder.append("order by	g.name ");

		TypedQuery<Group> query = getEntityManager().createQuery(queryBuilder.toString(), Group.class);
		return query.getResultList();
	}
	
	public boolean checkNameExistance(String name) {
		TypedQuery<Long> q = getEntityManager().createQuery(
			"select count(g.id) " +
			"from Group g " +
			"where g.name = :name ",
			Long.class);
		q.setParameter("name", name);
		return q.getSingleResult() > 0;
	}

}

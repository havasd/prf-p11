package hu.prf.messaging.dao.measurement;

import java.util.List;

import javax.persistence.TypedQuery;

import hu.prf.messaging.dao.core.GenericDAO;
import hu.prf.messaging.entity.measurement.WeightData;
import hu.prf.messaging.entity.person.User;

public class WeightDataDAO extends GenericDAO<WeightData, Long> {

	private static final long serialVersionUID = 1286322127054098460L;

	public WeightDataDAO() {
		super(WeightData.class);
	}
	
	public List<WeightData> findByPatient(User patient) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("	select ");
		queryBuilder.append("		weightData ");
		queryBuilder.append("	from ");
		queryBuilder.append("		WeightData weightData ");
		queryBuilder.append("	where ");
		queryBuilder.append("		weightData.patient = :patient ");
		
		TypedQuery<WeightData> query = getEntityManager().createQuery(queryBuilder.toString(), getEntityClass());
		query.setParameter("patient", patient);
		return query.getResultList();
	}

}

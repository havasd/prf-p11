package hu.prf.messaging.dao.core;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class GenericDAO<EntityType, IdentifierType extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Class<EntityType> entityClass;

	@Inject
	private EntityManager entityManager;

	protected GenericDAO(final Class<EntityType> entityClass) {
		this.entityClass = entityClass;
	}

	public EntityType findEntity(final IdentifierType identifier) {
		return entityManager.find(entityClass, identifier);
	}

	public List<EntityType> list() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<EntityType> criteriaQuery = criteriaBuilder.createQuery(entityClass);
		Root<EntityType> entityRoot = criteriaQuery.from(entityClass);
		criteriaQuery.select(entityRoot);
		
		TypedQuery<EntityType> query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}

	public void persist(final EntityType entity) {
		entityManager.persist(entity);
	}

	public void remove(final EntityType entity) {
		entityManager.remove(entity);
	}

	public void refresh(final EntityType entity) {
		entityManager.refresh(entity);
	}
	
	public EntityType merge(final EntityType entity) {
		return entityManager.merge(entity);
	}
	
	public void flush() {
		entityManager.flush();
	}
	
	public Class<EntityType> getEntityClass() {
		return entityClass;
	}
	
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}

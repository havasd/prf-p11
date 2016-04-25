package hu.prf.messaging.controller;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;

import hu.prf.messaging.dao.GenericDAO;

public abstract class AbstractEntityAction<EntityType, IdentifierType extends Serializable> implements Serializable {

	private static final long serialVersionUID = -1990165498495936667L;

	@Inject
	protected Logger logger;

	private IdentifierType id;

	private EntityType entity;

	private Class<EntityType> entityClass;

	/**
	 * This constructor is needed because CDI needs no-args constructor in abstract superclasses of CDI beans,
	 * even if this constructor is not used by child classes.
	 */
	public AbstractEntityAction() {
		// Nothing to do here.
	}

	public AbstractEntityAction(Class<EntityType> entityClass) {
		this.entityClass = entityClass;
	}

	public void load() {
		if (null == id) {
			try {
				entity = entityClass.newInstance();
				afterCreation();
			} catch (InstantiationException | IllegalAccessException e) {
				logger.severe("Cannot instantiate entity.");
			}
		} else {
			entity = getEntityDao().findEntity(id);
		}
	}

	protected void afterCreation() {
		// Nothing to do here.
	}

	@Transactional
	public String persist() {
		// Entity instance is detached here in case of editing, so merge() should be used instead of persist().
		logger.severe("Persist object: " + getEntity().toString());
		getEntityDao().merge(getEntity());
		return getNavigationTargetAfterPersist();
	}

	protected void beforeRemoving(EntityType entityToDelete) {
		// Nothing to do here.
	}

	@Transactional
	public void remove(IdentifierType identifier) {
		EntityType entityToRemove = getEntityDao().findEntity(identifier);
		beforeRemoving(entityToRemove);
		getEntityDao().remove(entityToRemove);
	}

	protected abstract GenericDAO<EntityType, IdentifierType> getEntityDao();

	protected abstract String getNavigationTargetAfterPersist();

	public IdentifierType getId() {
		return id;
	}

	public void setId(IdentifierType id) {
		this.id = id;
	}

	public EntityType getEntity() {
		return entity;
	}

	public void setEntity(EntityType entity) {
		this.entity = entity;
	}

}

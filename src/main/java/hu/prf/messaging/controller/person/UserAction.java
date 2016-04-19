package hu.prf.messaging.controller.person;

import hu.prf.messaging.controller.core.AbstractEntityAction;
import hu.prf.messaging.dao.core.GenericDAO;
import hu.prf.messaging.dao.user.UserDAO;
import hu.prf.messaging.entity.place.Address;
import hu.prf.messaging.entity.user.User;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class UserAction extends AbstractEntityAction<User, Long> {
	
	private static final long serialVersionUID = -7067061243883686127L;
	
	private static final String NAVIGATION_TARGET_AFTER_PERSIST = "index";
	
	@Inject
	private UserDAO userDAO;
	
	//@Inject
	//private MeasurementDataDAO measurementDataDAO;

	public UserAction() {
		super(User.class);
	}
	
	@Override
	protected void afterCreation() {
		getEntity().setAddress(new Address());
	}
	
	@Override
	protected void beforeRemoving(User entityToRemove) {
		// groups messages ?
	}

	@Override
	protected GenericDAO<User, Long> getEntityDao() {
		return userDAO;
	}

	@Override
	protected String getNavigationTargetAfterPersist() {
		return NAVIGATION_TARGET_AFTER_PERSIST;
	}

}

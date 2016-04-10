package hu.prf.messaging.controller.person;

import hu.prf.messaging.controller.core.AbstractDataModel;
import hu.prf.messaging.dao.core.GenericDAO;
import hu.prf.messaging.dao.person.PatientDAO;
import hu.prf.messaging.entity.person.User;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class PatientDataModel extends AbstractDataModel<User, Long> {
	
	private static final long serialVersionUID = -7210487598216166015L;
	
	@Inject
	private PatientDAO patientDAO;

	@Override
	protected GenericDAO<User, Long> getEntityDao() {
		return patientDAO;
	}

}

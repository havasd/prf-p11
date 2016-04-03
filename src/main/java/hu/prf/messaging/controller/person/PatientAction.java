package hu.prf.messaging.controller.person;

import hu.prf.messaging.controller.core.AbstractEntityAction;
import hu.prf.messaging.dao.core.GenericDAO;
import hu.prf.messaging.dao.measurement.MeasurementDataDAO;
import hu.prf.messaging.dao.person.PatientDAO;
import hu.prf.messaging.entity.person.Patient;
import hu.prf.messaging.entity.place.Address;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class PatientAction extends AbstractEntityAction<Patient, Long> {
	
	private static final long serialVersionUID = -7067061243883686127L;
	
	private static final String NAVIGATION_TARGET_AFTER_PERSIST = "/content/patient/list?faces-redirect=true";
	
	@Inject
	private PatientDAO patientDAO;
	
	@Inject
	private MeasurementDataDAO measurementDataDAO;

	public PatientAction() {
		super(Patient.class);
	}
	
	@Override
	protected void afterCreation() {
		getEntity().setAddress(new Address());
	}
	
	@Override
	protected void beforeRemoving(Patient entityToRemove) {
		measurementDataDAO.removeByPatient(entityToRemove);
	}

	@Override
	protected GenericDAO<Patient, Long> getEntityDao() {
		return patientDAO;
	}

	@Override
	protected String getNavigationTargetAfterPersist() {
		return NAVIGATION_TARGET_AFTER_PERSIST;
	}

}

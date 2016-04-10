package hu.prf.messaging.controller.person;

import hu.prf.messaging.dao.person.PatientDAO;
import hu.prf.messaging.entity.person.User;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class SelectedPatientContainer implements Serializable {

	private static final long serialVersionUID = -387708421748479164L;
	
	@Inject
	private PatientDAO patientDAO;
	
	private User selectedPatient;
	
	public void selectPatient(Long patientIdentifier) {
		if (null != patientIdentifier) {
			this.selectedPatient = patientDAO.findEntity(patientIdentifier);
		}
	}
	
	public User getSelectedPatient() {
		return selectedPatient;
	}

}

package hu.prf.messaging.controller.measurement;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import hu.prf.messaging.controller.core.AbstractDataModel;
import hu.prf.messaging.controller.person.SelectedPatientContainer;
import hu.prf.messaging.dao.core.GenericDAO;
import hu.prf.messaging.dao.measurement.BloodPressureDataDAO;
import hu.prf.messaging.entity.measurement.BloodPressureData;

@Named
@ViewScoped
public class BloodPressureDataModel extends AbstractDataModel<BloodPressureData, Long> {
	
	private static final long serialVersionUID = -7776424479888565750L;
	
	@Inject
	private BloodPressureDataDAO bloodPressureDataDAO;
	
	@Inject
	private SelectedPatientContainer selectedPatientContainer;
	
	@Override
	public void load() {
		setList(bloodPressureDataDAO.findByPatient(selectedPatientContainer.getSelectedPatient()));
	}

	@Override
	protected GenericDAO<BloodPressureData, Long> getEntityDao() {
		return bloodPressureDataDAO;
	}
	
}

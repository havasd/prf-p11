package hu.prf.messaging.controller.measurement;

import java.util.Date;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import hu.prf.messaging.controller.core.AbstractEntityAction;
import hu.prf.messaging.controller.person.SelectedPatientContainer;
import hu.prf.messaging.dao.core.GenericDAO;
import hu.prf.messaging.dao.measurement.BloodPressureDataDAO;
import hu.prf.messaging.entity.measurement.BloodPressureData;

@Named
@ViewScoped
public class BloodPressureDataAction extends AbstractEntityAction<BloodPressureData, Long> {
	
	private static final long serialVersionUID = 4335991674417481192L;
	
	private static final String NAVIGATION_TARGET_AFTER_PERSIST = "/content/bloodpressuredata/list?faces-redirect=true";
	
	@Inject
	private BloodPressureDataDAO bloodPressureDataDAO;
	
	@Inject
	private SelectedPatientContainer selectedPatientContainer;
	
	public BloodPressureDataAction() {
		super(BloodPressureData.class);
	}
	
	@Override
	protected void afterCreation() {
		getEntity().setPatient(selectedPatientContainer.getSelectedPatient());
		getEntity().setDate(new Date());
	}

	@Override
	protected GenericDAO<BloodPressureData, Long> getEntityDao() {
		return bloodPressureDataDAO;
	}

	@Override
	protected String getNavigationTargetAfterPersist() {
		return NAVIGATION_TARGET_AFTER_PERSIST;
	}

}

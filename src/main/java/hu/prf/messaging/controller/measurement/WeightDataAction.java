package hu.prf.messaging.controller.measurement;

import java.util.Date;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import hu.prf.messaging.controller.core.AbstractEntityAction;
import hu.prf.messaging.controller.person.SelectedPatientContainer;
import hu.prf.messaging.dao.core.GenericDAO;
import hu.prf.messaging.dao.measurement.WeightDataDAO;
import hu.prf.messaging.entity.measurement.WeightData;

@Named
@ViewScoped
public class WeightDataAction extends AbstractEntityAction<WeightData, Long> {
	
	private static final long serialVersionUID = 228976602070357967L;
	
	private static final String NAVIGATION_TARGET_AFTER_PERSIST = "/content/weightdata/list?faces-redirect=true";

	@Inject
	private WeightDataDAO weightDataDAO;
	
	@Inject
	private SelectedPatientContainer selectedPatientContainer;
	
	public WeightDataAction() {
		super(WeightData.class);
	}
	
	@Override
	protected void afterCreation() {
		getEntity().setPatient(selectedPatientContainer.getSelectedPatient());
		getEntity().setDate(new Date());
	}

	@Override
	protected GenericDAO<WeightData, Long> getEntityDao() {
		return weightDataDAO;
	}

	@Override
	protected String getNavigationTargetAfterPersist() {
		return NAVIGATION_TARGET_AFTER_PERSIST;
	}

}

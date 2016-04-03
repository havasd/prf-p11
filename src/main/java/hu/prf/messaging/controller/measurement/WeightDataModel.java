package hu.prf.messaging.controller.measurement;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import hu.prf.messaging.controller.core.AbstractDataModel;
import hu.prf.messaging.controller.person.SelectedPatientContainer;
import hu.prf.messaging.dao.core.GenericDAO;
import hu.prf.messaging.dao.measurement.WeightDataDAO;
import hu.prf.messaging.entity.measurement.WeightData;

@Named
@ViewScoped
public class WeightDataModel extends AbstractDataModel<WeightData, Long> {
	
	private static final long serialVersionUID = -3446654405173782044L;
	
	@Inject
	private WeightDataDAO weightDataDAO;
	
	@Inject
	private SelectedPatientContainer selectedPatientContainer;
	
	@Override
	public void load() {
		setList(weightDataDAO.findByPatient(selectedPatientContainer.getSelectedPatient()));
	}

	@Override
	protected GenericDAO<WeightData, Long> getEntityDao() {
		return weightDataDAO;
	}

}

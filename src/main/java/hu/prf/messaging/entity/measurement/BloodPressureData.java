package hu.prf.messaging.entity.measurement;

import hu.prf.messaging.entity.person.Patient;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

@Entity
public class BloodPressureData extends MeasurementData implements Serializable {
	
	private static final long serialVersionUID = 5960163422991948734L;

	private Integer systolic;
	
	private Integer diastolic;
	
	private Integer pulse;
	
	public BloodPressureData() {
	}

	public BloodPressureData(Patient patient, Date date, Integer systolic, Integer diastolic, Integer pulse) {
		super(patient, date);
		this.setMeasurementType(MeasurementType.BLOOD_PRESSURE);
		this.systolic = systolic;
		this.diastolic = diastolic;
		this.pulse = pulse;
	}

	public Integer getSystolic() {
		return systolic;
	}

	public void setSystolic(Integer systolic) {
		this.systolic = systolic;
	}

	public Integer getDiastolic() {
		return diastolic;
	}

	public void setDiastolic(Integer diastolic) {
		this.diastolic = diastolic;
	}

	public Integer getPulse() {
		return pulse;
	}

	public void setPulse(Integer pulse) {
		this.pulse = pulse;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((diastolic == null) ? 0 : diastolic.hashCode());
		result = prime * result + ((pulse == null) ? 0 : pulse.hashCode());
		result = prime * result
				+ ((systolic == null) ? 0 : systolic.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BloodPressureData other = (BloodPressureData) obj;
		if (diastolic == null) {
			if (other.diastolic != null)
				return false;
		} else if (!diastolic.equals(other.diastolic))
			return false;
		if (pulse == null) {
			if (other.pulse != null)
				return false;
		} else if (!pulse.equals(other.pulse))
			return false;
		if (systolic == null) {
			if (other.systolic != null)
				return false;
		} else if (!systolic.equals(other.systolic))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BloodPressureData [systolic=" + systolic + ", diastolic="
				+ diastolic + ", pulse=" + pulse + "]";
	}

}

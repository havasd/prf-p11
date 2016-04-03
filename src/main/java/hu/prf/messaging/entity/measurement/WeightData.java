package hu.prf.messaging.entity.measurement;

import hu.prf.messaging.entity.person.Patient;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

@Entity
public class WeightData extends MeasurementData implements Serializable {
	
	private static final long serialVersionUID = 5783715584745555907L;
	
	private Double weight;
	
	public WeightData() {
	}

	public WeightData(Patient patient, Date date, Double weight) {
		super(patient, date);
		this.setMeasurementType(MeasurementType.WEIGHT);
		this.weight = weight;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
		WeightData other = (WeightData) obj;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WeightData [weight=" + weight + "]";
	}

}

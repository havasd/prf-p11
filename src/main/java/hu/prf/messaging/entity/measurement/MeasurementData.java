package hu.prf.messaging.entity.measurement;

import hu.prf.messaging.entity.person.Patient;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class MeasurementData implements Serializable {
	
	private static final long serialVersionUID = 6890096282731390574L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@Enumerated(EnumType.STRING)
	private MeasurementType measurementType;
	
	public MeasurementData() {
		// TODO Auto-generated constructor stub
	}

	public MeasurementData(Patient patient, Date date) {
		super();
		this.patient = patient;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public MeasurementType getMeasurementType() {
		return measurementType;
	}
	
	public void setMeasurementType(MeasurementType measurementType) {
		this.measurementType = measurementType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((measurementType == null) ? 0 : measurementType.hashCode());
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeasurementData other = (MeasurementData) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (measurementType != other.measurementType)
			return false;
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MeasurementData [id=" + id + ", patient=" + patient + ", date="
				+ date + ", measurementType=" + measurementType + "]";
	}

}

package hu.prf.messaging.entity.person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "patient_group",
		uniqueConstraints = @UniqueConstraint(name = "unique_name", columnNames = {"name"}))
public class PatientGroup implements Serializable {
	
	private static final long serialVersionUID = 7425511414878798652L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "patientgroup_patient", joinColumns = @JoinColumn(name = "patientgroup_id"), 
			inverseJoinColumns = @JoinColumn(name = "patient_id"))
	private List<Patient> patients;
	
	public PatientGroup() {
	}

	public PatientGroup(String name, List<Patient> patients) {
		super();
		this.name = name;
		this.patients = patients;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public List<Patient> getPatients() {
		if (null == patients) {
			patients = new ArrayList<Patient>();
		}
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		PatientGroup other = (PatientGroup) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PatientGroup [id=" + id + ", name=" + name + ", patients="
				+ patients + "]";
	}
	
}

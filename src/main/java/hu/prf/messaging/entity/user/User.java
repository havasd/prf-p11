package hu.prf.messaging.entity.user;

import hu.prf.messaging.entity.message.MessageThread;
import hu.prf.messaging.entity.place.Address;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user",
		uniqueConstraints = @UniqueConstraint(name = "unique_name", columnNames = {"email"}))
public class User implements Serializable {
	
	private static final long serialVersionUID = -1583355608420130917L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	@OneToMany
	private List<UserGroup> groups;
	
	@OneToMany
	private List<MessageThread> messageThreads;
	
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@Embedded
	private Address address;
	
	public User() {
	}

	public User(String name, String email, String password,
			List<UserGroup> groups, List<MessageThread> messageThreads,
			Date birthDate, Address address) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.groups = groups;
		this.messageThreads = messageThreads;
		this.birthDate = birthDate;
		this.address = address;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UserGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<UserGroup> groups) {
		this.groups = groups;
	}

	public List<MessageThread> getMessageThreads() {
		return messageThreads;
	}

	public void setMessageThreads(List<MessageThread> messageThreads) {
		this.messageThreads = messageThreads;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((groups == null) ? 0 : groups.hashCode());
		result = prime * result
				+ ((messageThreads == null) ? 0 : messageThreads.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
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
		User other = (User) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (groups == null) {
			if (other.groups != null)
				return false;
		} else if (!groups.equals(other.groups))
			return false;
		if (messageThreads == null) {
			if (other.messageThreads != null)
				return false;
		} else if (!messageThreads.equals(other.messageThreads))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email
				+ ", password=" + password + ", groups=" + groups
				+ ", messageThreads=" + messageThreads + ", birthDate="
				+ birthDate + ", address=" + address + "]";
	}
}

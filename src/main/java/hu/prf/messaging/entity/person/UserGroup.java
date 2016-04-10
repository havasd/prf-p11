package hu.prf.messaging.entity.person;

import hu.prf.messaging.entity.message.MessageThread;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user_group",
		uniqueConstraints = @UniqueConstraint(name = "unique_name", columnNames = {"name"}))
public class UserGroup implements Serializable {
	
	private static final long serialVersionUID = 7425511414878798652L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<User> users;

	@OneToOne(fetch = FetchType.LAZY)
	private MessageThread messageThread;
	
	
	public UserGroup() {
	}

	public UserGroup(String name, List<User> users, MessageThread messageThread) {
		super();
		this.name = name;
		this.users = users;
		this.messageThread = messageThread;
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


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}


	public MessageThread getMessageThread() {
		return messageThread;
	}


	public void setMessageThread(MessageThread messageThread) {
		this.messageThread = messageThread;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((messageThread == null) ? 0 : messageThread.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
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
		UserGroup other = (UserGroup) obj;
		if (messageThread == null) {
			if (other.messageThread != null)
				return false;
		} else if (!messageThread.equals(other.messageThread))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserGroup [id=" + id + ", name=" + name + ", users=" + users
				+ ", messageThread=" + messageThread + "]";
	}

}

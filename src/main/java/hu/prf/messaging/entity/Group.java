package hu.prf.messaging.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user_group",
		uniqueConstraints = @UniqueConstraint(name = "unique_name", columnNames = {"name"}))
public class Group implements Serializable {
	
	private static final long serialVersionUID = 7425511414878798652L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	private String name;
	
	@ManyToMany
	private List<User> users;

	@OneToMany
	private List<Post> posts;
	
	public Group() {
		
	}

	public Group(Long id, String name, List<User> users, List<Post> posts) {
		super();
		this.id = id;
		this.name = name;
		this.users = users;
		this.posts = posts;
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

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * TODO: update if needed
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
		return result;
	}

	/**
	 * TODO: update if needed
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
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
		return "UserGroup [id=" + id + ", name=" + name + ", users=" + users + "]";
	}

}

package hu.prf.messaging.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "membership")
public class Membership implements Serializable {

	private static final long serialVersionUID = 7059389259996377956L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@ManyToOne
	private User user;

	@ManyToOne
	private Group group;

	public Membership() {

	}

	public Membership(User u, Group g) {
		this.user = u;
		this.group = g;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User u) {
		this.user = u;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group g) {
		this.group = g;
	}

}

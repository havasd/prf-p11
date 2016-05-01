package hu.prf.messaging.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "groupmessage")
public class GroupMessage implements Serializable {

	private static final long serialVersionUID = 7059389259996377956L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	private String text;

	@ManyToOne
	private User sender;

	@ManyToOne
	private Group group;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	public GroupMessage() {

	}

	public GroupMessage(String text, User sender, Group group, Date date) {
		this.text = text;
		this.sender = sender;
		this.group = group;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}

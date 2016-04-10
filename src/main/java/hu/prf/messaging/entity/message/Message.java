package hu.prf.messaging.entity.message;

import hu.prf.messaging.entity.person.User;

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
@Table(name = "message")
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	private String text;

	@ManyToOne
	private User sender;

	@Temporal(TemporalType.DATE)
	private Date date;

	public Message() {
	}

	public Message(String text, User sender, Date date) {
		super();
		this.text = text;
		this.sender = sender;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + text.hashCode();
		result = prime * result + sender.hashCode();
		result = prime * result + date.hashCode();
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
		Message other = (Message) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;

		if (!sender.equals(other.sender)) {
			return false;
		}
		if (!date.equals(other.date)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", text=" + text + ", sender=" + sender
				+ ", date=" + date + "]";
	}

}

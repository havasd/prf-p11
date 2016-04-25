package hu.prf.messaging.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import hu.prf.messaging.dao.MessageDAO;
import hu.prf.messaging.dao.UserDAO;
import hu.prf.messaging.entity.Message;
import hu.prf.messaging.entity.User;
import hu.prf.messaging.util.Session;

@Named
@ViewScoped
public class MessageView implements Serializable {

	private static final long serialVersionUID = 3346759248048030119L;

	@Inject
	private Session session;

	@Inject
	private UserDAO userDAO;

	@Inject
	private MessageDAO messageDAO;

	private long id;

	private List<Message> messages;

	private String newMessageText;

	private User loggedInUser;

	private User otherUser;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public User getOtherUser() {
		return otherUser;
	}

	public void setOtherUser(User otherUser) {
		this.otherUser = otherUser;
	}

	public String getNewMessageText() {
		return newMessageText;
	}

	public void setNewMessageText(String newMessageText) {
		this.newMessageText = newMessageText;
	}

	public void load() {
		loggedInUser = userDAO.findEntity(session.getUserId());
		otherUser = userDAO.findEntity(id);
		if (loggedInUser != null && otherUser != null) {
			messages = messageDAO.getTwoUsersMessages(loggedInUser.getId(), otherUser.getId());
		}
	}

	@Transactional
	public String sendNewMessage() {
		Message message = new Message(newMessageText, loggedInUser, otherUser, new Date());
		messageDAO.persist(message);
		return "/content/user/message.xhtml?faces-redirect=true&u=" + otherUser.getId();
	}

}

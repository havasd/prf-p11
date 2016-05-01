package hu.prf.messaging.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import hu.prf.messaging.dao.GroupDAO;
import hu.prf.messaging.dao.GroupMessageDAO;
import hu.prf.messaging.dao.UserDAO;
import hu.prf.messaging.entity.Group;
import hu.prf.messaging.entity.GroupMessage;
import hu.prf.messaging.entity.User;
import hu.prf.messaging.util.Session;

@Named
@ViewScoped
public class GroupMessageController implements Serializable {

	private static final long serialVersionUID = 3346759248048030119L;

	@Inject
	private Session session;

	@Inject
	private UserDAO userDAO;
	
	@Inject
	private GroupDAO groupDAO;

	@Inject
	private GroupMessageDAO groupMessageDAO;

	private long id;

	private List<GroupMessage> messages;

	private String newMessageText;

	private User loggedInUser;

	private Group group;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<GroupMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<GroupMessage> messages) {
		this.messages = messages;
	}

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public Group getGroup() {
		return group;
	}

	public void getGroup(Group group) {
		this.group = group;
	}

	public String getNewMessageText() {
		return newMessageText;
	}

	public void setNewMessageText(String newMessageText) {
		this.newMessageText = newMessageText;
	}

	@Transactional
	public void load() {
		loggedInUser = userDAO.findEntity(session.getUserId());
		group = groupDAO.findEntity(id);
		System.out.println("load: "+group+", "+loggedInUser);
		if (loggedInUser != null && group != null) {
			messages=groupMessageDAO.getGroupMessages(id);
		}
	}

	@Transactional
	public String sendNewMessage() {
		GroupMessage message = new GroupMessage(newMessageText, loggedInUser, group, new Date());
		System.out.println("persist: "+group+", "+loggedInUser);
		groupMessageDAO.persist(message);
		return "/content/group.xhtml?faces-redirect=true&g=" + group.getId();
	}

}

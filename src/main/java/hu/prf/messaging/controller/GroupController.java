package hu.prf.messaging.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import hu.prf.messaging.dao.GroupDAO;
import hu.prf.messaging.dao.MembershipDAO;
import hu.prf.messaging.dao.UserDAO;
import hu.prf.messaging.entity.Group;
import hu.prf.messaging.entity.Membership;
import hu.prf.messaging.entity.User;
import hu.prf.messaging.util.Session;

@Named
@ViewScoped
public class GroupController implements Serializable {

	private static final long serialVersionUID = -4776518416312717324L;

	@Inject
	private GroupDAO groupDAO;
	
	@Inject
	private MembershipDAO membershipDAO;
	
	@Inject
	private Session session;

	@Inject
	private UserDAO userDAO;

	private Group group;

	private boolean isUserIn;

	private long id;
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void init() {
		group = new Group();
	}

	@Transactional
	public String save() {

		boolean errExist = false;
		if (groupDAO.checkNameExistance(group.getName())) {
			errExist = true;
		}

		boolean errLong = false;
		if (group.getName().length() < 1) {
			errLong = true;
		}

		if (!errLong && !errExist) {
			groupDAO.persist(group);
			return "/content/group-create-success.xhtml";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("/content/group-create.xhtml?faces-redirect=true&a=error");
		if (errExist) {
			sb.append("&err_exist=1");
		}
		if (errLong) {
			sb.append("&err_long=1");
		}
		return sb.toString();
	}
	
	@Transactional
	public void load() {
		User loggedInUser = userDAO.findEntity(session.getUserId());
		group = groupDAO.findEntity(id);
		if (loggedInUser != null && group != null) {
			isUserIn = userDAO.isInGroup(id);
		}
	}
	
	@Transactional
	public String join() {
		membershipDAO.persist(new Membership(userDAO.findEntity(session.getUserId()),groupDAO.findEntity(id)));
		return "/content/group.xhtml?g="+id;
	}

	@Transactional
	public String leave() {
		System.out.println("why not delete: "+id);
		membershipDAO.remove(new Membership(userDAO.findEntity(session.getUserId()),groupDAO.findEntity(id)));
		System.out.println("delete: "+id);
		return "/content/group.xhtml?g="+id;
	}
	
	public boolean getIsUserIn() {
		return isUserIn;
	}

	public void setIsUserIn(boolean isUserIn) {
		this.isUserIn = isUserIn;
	}

}

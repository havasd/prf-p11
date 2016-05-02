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
	
	private boolean editable;
	
	private boolean memberEdit;
	
	private Long memberCtr;

	private Group group;

	private boolean isUserIn;

	private long id;
	
	private String description="";
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

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
			memberCtr=membershipDAO.count(id);
			if(group.getDescription()!=null)
				description=group.getDescription();
		}
	}
	
	@Transactional
	public String join() {
		membershipDAO.persist(new Membership(userDAO.findEntity(session.getUserId()),groupDAO.findEntity(id)));
		return "/content/group.xhtml?faces-redirect=true&g="+id;
	}

	@Transactional
	public String leave() {
		Membership membership=membershipDAO.getByIds(session.getUserId(),id);
		membershipDAO.remove(membership);
		return "/content/group.xhtml?faces-redirect=true&g="+id;
	}
	
	public boolean getIsUserIn() {
		return isUserIn;
	}

	public void setIsUserIn(boolean isUserIn) {
		this.isUserIn = isUserIn;
	}

	public Long getMemberCtr() {
		return memberCtr;
	}

	public void setMemberCtr(Long memberCtr) {
		this.memberCtr = memberCtr;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	public String edit() {
		editable=true;
		return "/content/group.xhtml?faces-redirect=true&g="+id+"&e=true";
	}
	
	@Transactional
	public String saveEdit() {
		editable=false;
		group.setDescription(description);
		System.out.println("merge: "+group+", "+group.getDescription()+", "+description);
		groupDAO.merge(group);
		return "/content/group.xhtml?faces-redirect=true&g="+id+"&e=false";
	}

	public boolean getMemberEdit() {
		return memberEdit;
	}

	public void setMemberEdit(boolean memberEdit) {
		this.memberEdit = memberEdit;
	}

}

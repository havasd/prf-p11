package hu.prf.messaging.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import hu.prf.messaging.dao.GroupDAO;
import hu.prf.messaging.entity.Group;

@Named
@ViewScoped
public class GroupController implements Serializable {

	private static final long serialVersionUID = -4776518416312717324L;

	@Inject
	private GroupDAO groupDAO;

	private Group group;

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
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

}

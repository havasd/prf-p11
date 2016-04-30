package hu.prf.messaging.dao;


import javax.inject.Named;

import hu.prf.messaging.entity.Membership;

@Named
public class MembershipDAO extends GenericDAO<Membership, Long> {

	private static final long serialVersionUID = -5899328697322490825L;

	public MembershipDAO() {
		super(Membership.class);
	}

}

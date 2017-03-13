package com.transporter.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.transporter.dao.AuthenticatedUserDao;
import com.transporter.model.user.AuthenticatedUser;

@Repository
public class AuthenticatedUserDaoImpl implements AuthenticatedUserDao {

	@Autowired
	private SessionFactory session;
	
	//add a new user into the database
	@Override
	public void add(AuthenticatedUser authUser) {
		session.getCurrentSession().save(authUser);
	}

	//edit an existing user's information in the database
	@Override
	public void edit(AuthenticatedUser authUser) {
		session.getCurrentSession().update(authUser);
	}

	//retrieve a particular Authenticated User from the database
	@Override
	public AuthenticatedUser getAuthUser(int userId) {
		return (AuthenticatedUser) session.getCurrentSession().get(AuthenticatedUser.class, userId);
	}

	//retrieve a list of all Authenticatd Users from the database
	@SuppressWarnings("unchecked")
	@Override
	public List<AuthenticatedUser> getAllAuthUser() {
		return session.getCurrentSession().createQuery("from AuthenticatedUser").list();
	}

	//check if login user is an existing Authenticated User from the database
	@SuppressWarnings("unchecked")
	@Override
	public AuthenticatedUser getAuthUserByLoginDetails(String usernameOrEmail, String password) {

		List<AuthenticatedUser> verifiedUsers = session.getCurrentSession().createQuery("from AuthenticatedUser "
				+ "where password=:password "
				+ "and (username=:uidOrEmail or email=:uidOrEmail)")
				.setParameter("password", password)
				.setParameter("uidOrEmail", usernameOrEmail)
				.list();

		if(!verifiedUsers.isEmpty())
			return verifiedUsers.get(0);
		else
			return null;

	}

}

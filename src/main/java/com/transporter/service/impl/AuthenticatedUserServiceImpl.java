package com.transporter.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transporter.dao.AuthenticatedUserDao;
import com.transporter.model.user.AuthenticatedUser;
import com.transporter.service.AuthenticatedUserService;

@Service
public class AuthenticatedUserServiceImpl implements AuthenticatedUserService{

	@Autowired
	private AuthenticatedUserDao authenticatedUserDao;
	
	@Transactional
	public void add(AuthenticatedUser authUser) {
		authenticatedUserDao.add(authUser);
	}

	@Transactional
	public void edit(AuthenticatedUser authUser) {
		authenticatedUserDao.edit(authUser);
	}

	@Transactional
	public AuthenticatedUser getAuthUser(int userId) {
		return authenticatedUserDao.getAuthUser(userId);
	}

	@Transactional
	public List<AuthenticatedUser> getAllAuthUser() {
		return authenticatedUserDao.getAllAuthUser();
	}

	@Transactional
	public AuthenticatedUser getAuthUserByLoginDetails(String usernameOrEmail, String password) {
		return authenticatedUserDao.getAuthUserByLoginDetails(usernameOrEmail, password);
	}

	@Transactional
	public boolean loginUser(String usernameOrEmail, String password, HttpSession session) {

		AuthenticatedUser verifiedUser = getAuthUserByLoginDetails(usernameOrEmail, password);
		if(verifiedUser != null){
			session.setAttribute("user", verifiedUser);
			session.setAttribute("username", verifiedUser.getUsername());
			session.setAttribute("userfullname", verifiedUser.getFullName());
			return true;
		}
		session.invalidate();
		return false;

	}

}

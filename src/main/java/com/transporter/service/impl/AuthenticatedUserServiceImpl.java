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
// add an authenticated user	
	@Transactional
	public void add(AuthenticatedUser authUser) {
		authenticatedUserDao.add(authUser);
	}
// edit an existing authenticated user
	@Transactional
	public void edit(AuthenticatedUser authUser) {
		authenticatedUserDao.edit(authUser);
	}
// get an authenticated user based on his ID
	@Transactional
	public AuthenticatedUser getAuthUser(int userId) {
		return authenticatedUserDao.getAuthUser(userId);
	}
// get the list of all authenticated user
	@Transactional
	public List<AuthenticatedUser> getAllAuthUser() {
		return authenticatedUserDao.getAllAuthUser();
	}
// get an authenticated user based on his email and passowrd
	@Transactional
	public AuthenticatedUser getAuthUserByLoginDetails(String usernameOrEmail, String password) {
		return authenticatedUserDao.getAuthUserByLoginDetails(usernameOrEmail, password);
	}
// allow user to login based on the email and password
	@Transactional
	public boolean loginUser(String usernameOrEmail, String password, HttpSession httpSession) {
		AuthenticatedUser verifiedUser = getAuthUserByLoginDetails(usernameOrEmail, password);
		if(verifiedUser != null){
			httpSession.setAttribute("user", verifiedUser);
			httpSession.setAttribute("username", verifiedUser.getUsername());
			httpSession.setAttribute("userfullname", verifiedUser.getFullName());
			return true;
		}
		httpSession.invalidate();
		return false;
	}
// check if the user is authenticated
	public boolean isAuthenticated(HttpSession httpSession){
		AuthenticatedUser authUser = (AuthenticatedUser) httpSession.getAttribute("user");
		if(authUser == null) return false;
		return authUser.isAuthenticated();
	}
	
}

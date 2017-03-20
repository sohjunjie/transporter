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
	public boolean loginUser(String usernameOrEmail, String password, HttpSession httpSession) {
		AuthenticatedUser verifiedUser = getAuthUserByLoginDetails(usernameOrEmail, password);
		if(verifiedUser != null){
			httpSession.setAttribute("user", verifiedUser);
			httpSession.setAttribute("username", verifiedUser.getUsername());
			httpSession.setAttribute("userfullname", verifiedUser.getFullName());
			httpSession.removeAttribute("unauthorisedAccessCount");
			return true;
		}
		httpSession.invalidate();
		return false;
	}

	public boolean isAuthenticated(HttpSession httpSession){
		AuthenticatedUser authUser = (AuthenticatedUser) httpSession.getAttribute("user");
		if(authUser == null) return false;
		return authUser.isAuthenticated();
	}

	public void logUnauthorisedAccessCount(HttpSession httpSession, String unauthEmailOrUsername){
		int unauthAccessCount = (int) httpSession.getAttribute("unauthorisedAccessCount") + 1;
		if(unauthAccessCount >= 5){
			// send unauthorised login email
			httpSession.removeAttribute("unauthorisedAccessCount");
			return;
		}
		httpSession.setAttribute("unauthorisedAccessCount", unauthAccessCount);

	}

}

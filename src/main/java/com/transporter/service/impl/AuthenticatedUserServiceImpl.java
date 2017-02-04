package com.transporter.service.impl;

import java.util.List;

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

}

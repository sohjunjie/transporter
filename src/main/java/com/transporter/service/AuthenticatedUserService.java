package com.transporter.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.transporter.model.user.AuthenticatedUser;

public interface AuthenticatedUserService {

	public void add(AuthenticatedUser authUser);
	public void edit(AuthenticatedUser authUser);
	public AuthenticatedUser getAuthUser(int userId);
	public List<AuthenticatedUser> getAllAuthUser();
	public AuthenticatedUser getAuthUserByLoginDetails(String usernameOrEmail, String password);
	public boolean loginUser(String usernameOrEmail, String password, HttpSession session);

}

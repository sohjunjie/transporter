package com.transporter.service;

import java.util.List;

import com.transporter.model.AuthenticatedUser;

public interface AuthenticatedUserService {

	public void add(AuthenticatedUser authUser);
	public void edit(AuthenticatedUser authUser);
	public AuthenticatedUser getAuthUser(int userId);
	public List<AuthenticatedUser> getAllAuthUser();
	
}

package com.transporter.model;

public class AnonymousUser implements User {
	
	public AnonymousUser() {}

	public String getUsername() { return ""; }
	public String getFullName() { return ""; }
	public String getShortName() { return ""; }
	
	public boolean isAnonymous() { return true; }
	public boolean isAuthenticated() { return false; }

}

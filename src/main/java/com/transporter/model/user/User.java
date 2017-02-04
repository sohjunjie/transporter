package com.transporter.model.user;

interface User {

	String getUsername();
	String getFullName();
	String getShortName();
	
	boolean isAnonymous();
	boolean isAuthenticated();
	
}

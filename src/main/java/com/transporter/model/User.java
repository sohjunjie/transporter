package com.transporter.model;

interface User {

	String getUsername();
	String getFullName();
	String getShortName();
	
	boolean isAnonymous();
	boolean isAuthenticated();
	
}

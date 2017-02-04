package com.transporter.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="auth_user")
@Inheritance(strategy=InheritanceType.JOINED)
public class AuthenticatedUser implements User {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String email;
	@Column
	private String firstname;
	@Column
	private String lastname;
	
	public AuthenticatedUser(){ }
	public AuthenticatedUser(int userId, String email, String username, String firstname, String lastname,
			String password) {
		super();
		this.userId = userId;
		this.email = email;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
	}
	public int getUserId() {
		return userId;
	}
	public String getUsername() {
		return username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstname() {
		return this.firstname;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAnonymous() { return false; }
	public boolean isAuthenticated() { return true; }

	public String getFullName() { return this.getFirstname() + this.getLastname(); }
	public String getShortName() { return this.getFirstname(); }

}

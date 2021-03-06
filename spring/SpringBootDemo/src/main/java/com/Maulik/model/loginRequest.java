package com.Maulik.model;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class loginRequest 
{
	@Valid
	@Email
	@Size(min=4, max=25,message="Enter Email")
	public	String email;
	@NotEmpty(message="not empty")
	@Size(min=6, max=25,message="Enter Password")
	public	String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

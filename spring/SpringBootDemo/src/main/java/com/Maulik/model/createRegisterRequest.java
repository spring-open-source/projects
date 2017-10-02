package com.Maulik.model;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class createRegisterRequest
{
public	Long id;

@Valid
@Size(min=4, max=15,message="Enter Firstname Between 4 to 15")
public	String firstname;
@Size(min=4, max=15,message="Enter LastName Between 4 to 15")
public	String lastname;
@Email
@Size(min=4, max=25,message="Enter Email")
public	String email;
@NotEmpty(message="not empty")
@Size(min=6, max=25,message="Enter Password")
public	String password;
@NotEmpty(message="not empty")
@Size(min=10, max=10,message="Enter Mobile No")
public	String mobile;
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getFirstname() {
	return firstname;
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

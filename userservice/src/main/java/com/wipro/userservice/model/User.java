package com.wipro.userservice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;


@Entity
public class User {

	@Id
	@NotEmpty
	@NotBlank(message = "user id is mandatory")
	private String userId;
	@NotEmpty
	@NotBlank(message = "firstName is mandatory")
	private String firstName;
	@NotEmpty
	@NotBlank(message = "lastName is mandatory")
	private String lastName;
	@NotEmpty
	@NotBlank(message = "password is mandatory")
	private String password;
	@Email
	@NotBlank(message = "email is mandatory")
	private String email;

	@CreationTimestamp
	private Date createdDate;

	public User() {	}

	
	public User(String userId, String firstName, String lastName, String password, String email, Date createdDate) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.createdDate = createdDate;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


}


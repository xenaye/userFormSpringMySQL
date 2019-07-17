package com.xenaye.projects.userservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;
	private String firstName;
	private String lastName;
	private String code;
	private String email;
	private String profilePicPath;
	private String password;
	private String profile;
	private Integer emailToUser;
	private Date expiration;

	public User(Long id, String firstName, String lastName, String code, String email, String profilePicPath, String password, String profile, Integer emailToUser, Date expiration) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.code = code;
		this.email = email;
		this.profilePicPath = profilePicPath;
		this.password = password;
		this.profile = profile;
		this.emailToUser = emailToUser;
		this.expiration = expiration;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setProfilePicPath(String profilePicPath) {
		this.profilePicPath = profilePicPath;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public void setEmailToUser(Integer emailToUser) {
		this.emailToUser = emailToUser;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getCode() {
		return code;
	}

	public String getEmail() {
		return email;
	}

	public String getProfilePicPath() {
		return profilePicPath;
	}

	public String getPassword() {
		return password;
	}

	public String getProfile() {
		return profile;
	}

	public Integer getEmailToUser() {
		return emailToUser;
	}

	public Date getExpiration() {
		return expiration;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", code='" + code + '\'' +
				", email='" + email + '\'' +
				", profilePicPath='" + profilePicPath + '\'' +
				", password='" + password + '\'' +
				", profile='" + profile + '\'' +
				", emailToUser=" + emailToUser +
				", expiration=" + expiration +
				'}';
	}
}

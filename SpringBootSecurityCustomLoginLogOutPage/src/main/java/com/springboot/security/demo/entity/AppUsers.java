package com.springboot.security.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="appusers")

public class AppUsers {

	@Column(name="UserID")
	@Id
	private long id;
	@Column(name="UserName")
	private String userName;
	@Column(name="Password")
	private String password;

	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "AppUsers [id=" + id + ", userName=" + userName + ", password=" + password + "]";
	}
	
	

}

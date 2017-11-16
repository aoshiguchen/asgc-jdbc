package com.github.aoshiguchen.framework.jdbc.entity;

import com.github.aoshiguchen.framework.jdbc.annotation.NotColumn;

public class User {

	private String id;
	
	private String name;
	
	private String code;
	
//	@NotColumn
//	@Column("user_type")
//	@Column("name")
	private String userType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", code=" + code
				+ ", userType=" + userType + "]";
	}

	
	
	
	
}

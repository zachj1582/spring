package com.spring.exercises.model.response;

public class UserResponse {
	
	private String userId;
	private String first_name;
	private String last_name;
	private String email;
	
	
	public UserResponse() {
		
	}

	public UserResponse(String userId, String first_name, String last_name, String email) {
		super();
		this.userId = userId;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

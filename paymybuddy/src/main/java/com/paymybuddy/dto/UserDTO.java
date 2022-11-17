package com.paymybuddy.dto;

import com.paymybuddy.entity.User;

public class UserDTO  {
	
	private Integer id;
	private String name;
	private String email;
	private Integer phoneNumber;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public UserDTO() {
		
	}
	
	public UserDTO(User user) {
		this.setId(user.getId());
		this.setName(user.getName());
		this.setEmail(user.getEmail());
		this.setPhoneNumber(user.getPhoneNumber());
	}
	
	
	// TODO
//	public fromUserToUserDTO(User user) {
//		
//		UserDTO userDTO = new UserDTO();
//		
//		return userDTO;
//	}

}

package com.avantari.entity;

import org.springframework.stereotype.Component;


public class Customer {
	//PROPERTIES
	
	
	
	
	private String name;
	
	private String email;
	private String password;
	
	
	
	//GETTER OR ACCESSOR METHODS
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	
	//SETTER OR MUTATOR METHODS
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

	@Override
	public String toString() {
		return "Customer [name=" + name + ", email=" + email + ", password="
				+ password +  "]";
	}

	
}
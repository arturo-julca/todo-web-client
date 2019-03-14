package com.avantica.todowebclient.model;

public class AppUser {

	String username;
	String password;
	
	public AppUser(){
		
	}
	
	public AppUser(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "AppUser [username=" + username + ", password=" + password + "]";
	}
	
}

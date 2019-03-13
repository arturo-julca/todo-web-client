package com.avantica.todowebclient.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class ToDo {

	private Long id;
	private String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime time;
	private String email;
	
	public ToDo() {		
	}
	
	public ToDo(Long id, String name, LocalDateTime time, String email) {
		this.id = id;
		this.name = name;
		this.time = time;
		this.email = email;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "ToDo [id=" + id + ", name=" + name + ", time=" + time + ", email=" + email + "]";
	}
	
}
